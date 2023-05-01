/**
 *
 */
package org.palladiosimulator.somox.ast2seff.jobs;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.net4j.util.om.monitor.SubMonitor;
import org.palladiosimulator.generator.fluent.repository.api.RepoAddition;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.SeffCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationInterfaceCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationSignatureCreator;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.somox.ast2seff.util.NameUtil;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Transformation Job transforming a JDT AST instance into a SEFF using the FluentAPI
 *
 * @author Marcel Rühle, Fabian Wenzel
 */
public class Ast2SeffJob implements IBlackboardInteractingJob<Blackboard<Object>> {
    private static final Logger LOGGER = Logger.getLogger(Ast2SeffJob.class);
    private static final FluentRepositoryFactory create = new FluentRepositoryFactory();

    /** The SoMoX blackboard to interact with. */
    private Blackboard<Object> blackboard;
    private final String repositoryOutputKey;

    private Map<ASTNode, ServiceEffectSpecification> ast2SeffMap;

    public Ast2SeffJob(Blackboard<Object> blackboard, String repositoryOutputKey) {
        this.blackboard = Objects.requireNonNull(blackboard);
        this.repositoryOutputKey = Objects.requireNonNull(repositoryOutputKey);
    }

    /**
     * This function executes the Ast2Seff execution process It requires that the bundleName2methodAssociationMap is set
     * inside the blackboard and processes it via the Ast2SeffVisitor
     *
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        LOGGER.info("Executing SEFF Creation Job.");
        monitor.subTask("Loading AST node to SEFF association map from blackboard");
        this.ast2SeffMap = (Map<ASTNode, ServiceEffectSpecification>) this.blackboard
                .getPartition("org.palladiosimulator.somox.analyzer.seff_associations");

        final IProgressMonitor subMonitor = SubMonitor.convert(monitor);
        subMonitor.setTaskName("Creating SEFF behaviour");

        // TODO Evaluate what informations have to be added to fluent repository before seff creation
        RepoAddition repoAddition = create.newRepository().withName("Repository");

        LOGGER.info("Creating " + ast2SeffMap.size() + " SEFFs.");
        createSeffsForComponents(repoAddition, monitor);

        LOGGER.info("Created SEFFs. Creating Repository.");
        Repository repository = repoAddition.createRepositoryNow();

        LOGGER.info("Created Repository. Creating XML.");
        this.generateSeffXmlFile(repository);

        LOGGER.info("Created XML. Task finished.");
        subMonitor.done();

        // TODO Fix wrong placeholder component in repository instead real component from placeholder seff
        // -> e.g. return list of correct seffs instead repository or copy content from placeholder seff to real one
        this.blackboard.addPartition(repositoryOutputKey, repository);
    }

    /**
     * This function creates the BasicComponents and their corresponding SEFF objects for each passed methodBundlePair
     *
     * @param repoAddition the Repository where BasicComponents are added
     * @param monitor      IProgressMonitor for IBlackboardInteractingJob interaction (@see
     *                     de.uka.ipd.sdq.workflow.IJob#IProgressMonitor(org.eclipse.core.runtime.IProgressMonitor))
     */
    private void createSeffsForComponents(RepoAddition repoAddition, IProgressMonitor monitor) {
        // Get all unique components from empty seff entities
        Multimap<BasicComponent, ASTNode> componentNodeMap = ArrayListMultimap.create();
        for (ASTNode node : this.ast2SeffMap.keySet()) {
            ServiceEffectSpecification placeholderSeff = this.ast2SeffMap.get(node);
            BasicComponent persistedComponent = placeholderSeff.getBasicComponent_ServiceEffectSpecification();
            componentNodeMap.put(persistedComponent, node);
        }

        // Remember already created operation interface creators & operation signature creators in map indexed by name
        Map<String, OperationInterfaceCreator> interfaceCreators = new HashMap<>();
        Map<String, OperationSignatureCreator> signatureCreators = new HashMap<>();

        // Iterate over all nodes of a component
        for (BasicComponent persistedComponent : componentNodeMap.keySet()) {
            // Create new placeholder component for fluent repository
            BasicComponentCreator basicComponentCreator = create.newBasicComponent()
                    .withName(persistedComponent.getEntityName());

            for (ASTNode node : componentNodeMap.get(persistedComponent)) {
                ServiceEffectSpecification placeholderSeff = this.ast2SeffMap.get(node);

                // Fetch persisted signature and create placeholder if not done already
                OperationSignature persistedSignature = (OperationSignature) placeholderSeff
                        .getDescribedService__SEFF();
                OperationSignatureCreator operationSignatureCreator = signatureCreators
                        .get(persistedSignature.getEntityName());
                if (Objects.isNull(operationSignatureCreator)) {
                    operationSignatureCreator = create.newOperationSignature()
                            .withName(persistedSignature.getEntityName());
                    signatureCreators.put(persistedSignature.getEntityName(), operationSignatureCreator);
                }

                // Fetch persisted interface, create placeholder if not done already,
                // and provide via placeholder component
                OperationInterface persistedInterface = ((OperationSignature) placeholderSeff
                        .getDescribedService__SEFF()).getInterface__OperationSignature();
                OperationInterfaceCreator operationInterfaceCreator = interfaceCreators
                        .get(persistedInterface.getEntityName());
                if (Objects.isNull(operationInterfaceCreator)) {
                    operationInterfaceCreator = create.newOperationInterface()
                            .withName(persistedInterface.getEntityName());
                    interfaceCreators.put(persistedInterface.getEntityName(), operationInterfaceCreator);
                }

                // Add signature to interface if not added already
                boolean signatureMissingInInterface = operationInterfaceCreator.build()
                        .getSignatures__OperationInterface().stream()
                        .noneMatch(signature -> signature.getEntityName().equals(persistedSignature.getEntityName()));
                if (signatureMissingInInterface) {
                    operationInterfaceCreator.withOperationSignature(operationSignatureCreator);
                }

                // Add provided role of interface to component if not added already
                boolean providedRoleMissing = basicComponentCreator.build()
                        .getProvidedRoles_InterfaceProvidingEntity().stream()
                        .map(role -> (OperationProvidedRole) role)
                        .map(role -> role.getProvidedInterface__OperationProvidedRole())
                        .noneMatch(operationInterface -> operationInterface.getEntityName()
                                .equals(persistedInterface.getEntityName()));
                if (providedRoleMissing) {
                    basicComponentCreator.provides(persistedInterface);
                }

                // Create fluent seff for node
                ActionSeff actionSeff = create.newSeff()
                        .onSignature(create.fetchOfSignature(persistedSignature.getEntityName()))
                        .withSeffBehaviour()
                        .withStartAction().withName(NameUtil.START_ACTION_NAME).followedBy();

                // Perform AST node visit to fill empty fluent seff with content
                SeffCreator actionSeffCreator = Ast2SeffVisitor.perform(actionSeff, node, this.ast2SeffMap, create)
                        .stopAction().withName(NameUtil.STOP_ACTION_NAME)
                        .createBehaviourNow();

                // Add completed seff to placeholder component
                basicComponentCreator.withServiceEffectSpecification(actionSeffCreator);
            }

            // Persist placeholder component in fluent repository
            repoAddition.addToRepository(basicComponentCreator);
            monitor.worked(1);
        }

        // Persist placeholder interfaces in fluent repository
        interfaceCreators.values().forEach(repoAddition::addToRepository);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.uka.ipd.sdq.workflow.IJob#getName()
     */
    @Override
    public String getName() {
        return "AST 2 SEFF Transformation Job";
    }

    /**
     * Sets the blackboard of the job.
     *
     * @param blackboard the new blackboard that should be set for the job
     */
    @Override
    public void setBlackboard(final Blackboard<Object> blackboard) {
        this.blackboard = blackboard;
    }

    @Override
    public void cleanup(final IProgressMonitor monitor) throws CleanupFailedException {
        // Nothing to cleanup after the job
    }

    /**
     * This function generates a XML File based on the given repository.
     *
     * @param repository the previously created repository that should be a file output
     */
    private void generateSeffXmlFile(final Repository repository) {
        EcorePlugin.ExtensionProcessor.process(null);
        Resource resource = new ResourceSetImpl().createResource(URI.createFileURI("Repository.xml"));
        resource.getContents().add(repository);

        try {
            resource.save(Collections.EMPTY_MAP);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
