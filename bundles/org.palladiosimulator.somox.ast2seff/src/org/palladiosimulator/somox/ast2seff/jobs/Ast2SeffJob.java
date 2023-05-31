package org.palladiosimulator.somox.ast2seff.jobs;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.net4j.util.om.monitor.SubMonitor;
import org.palladiosimulator.generator.fluent.repository.api.RepoAddition;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.SeffCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationInterfaceCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationSignatureCreator;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
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
 * Transformer converting a {@link ASTNode JDT AST} instance into a {@link ServiceEffectSpecification Palladio Component
 * Model SEFF} using the Palladio FluentApiModelGenerator.
 *
 * @author Marcel Rühle, Fabian Wenzel, Moritz Gstuer
 */
public class Ast2SeffJob implements IBlackboardInteractingJob<Blackboard<Object>> {
    private static final Logger LOGGER = Logger.getLogger(Ast2SeffJob.class);
    private static final FluentRepositoryFactory fluentFactory = new FluentRepositoryFactory();

    /** The SoMoX blackboard to interact with. */
    private Blackboard<Object> blackboard;
    private final String ast2SeffMapKey;
    private final String repositoryOutputKey;

    private Map<ASTNode, ServiceEffectSpecification> ast2SeffMap;

    public Ast2SeffJob(Blackboard<Object> blackboard, String ast2SeffMapKey, String repositoryOutputKey) {
        this.blackboard = Objects.requireNonNull(blackboard);
        this.ast2SeffMapKey = Objects.requireNonNull(ast2SeffMapKey);
        this.repositoryOutputKey = Objects.requireNonNull(repositoryOutputKey);
    }

    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        LOGGER.info("Starting SEFF Creation Job.");
        monitor.subTask("Loading AST node to SEFF association map from blackboard");
        this.ast2SeffMap = (Map<ASTNode, ServiceEffectSpecification>) this.blackboard
                .getPartition(this.ast2SeffMapKey);

        final IProgressMonitor subMonitor = SubMonitor.convert(monitor);
        subMonitor.setTaskName("Creating SEFF behaviour");

        LOGGER.info("Creating " + ast2SeffMap.size() + " SEFFs.");
        RepoAddition repoAddition = fluentFactory.newRepository().withName("Repository");
        createSeffsForComponents(repoAddition, monitor);

        LOGGER.info("Created SEFFs. Creating Repository.");
        Repository repository = repoAddition.createRepositoryNow();

        LOGGER.info("Created Repository. Writing Result to Blackboard.");
        this.blackboard.addPartition(repositoryOutputKey, repository);

        LOGGER.info("Task finished.");
        subMonitor.done();
    }

    private void createSeffsForComponents(RepoAddition repoAddition, IProgressMonitor monitor) {
        // Create placeholder interfaces with operation signatures and persist in fluent repository
        List<OperationInterface> persistedInterfaces = this.ast2SeffMap.values().stream()
                .map(seff -> ((OperationSignature) seff.getDescribedService__SEFF()).getInterface__OperationSignature())
                .distinct().toList();
        for (OperationInterface persistedInterface : persistedInterfaces) {
            OperationInterfaceCreator operationInterfaceCreator = fluentFactory.newOperationInterface()
                    .withName(persistedInterface.getEntityName());

            // Get signatures of interface from seffs, create signature creator, & add to interface creator
            List<OperationSignature> persistedSignatures = this.ast2SeffMap.values().stream()
                    .map(seff -> (OperationSignature) seff.getDescribedService__SEFF())
                    .filter(signature -> signature.getInterface__OperationSignature().getEntityName()
                            .equals(persistedInterface.getEntityName()))
                    .distinct().toList();
            for (OperationSignature persistedSignature : persistedSignatures) {
                // TODO Copy parameters and return type because needed in visitor
                OperationSignatureCreator operationSignatureCreator = fluentFactory.newOperationSignature()
                        .withName(persistedSignature.getEntityName());
                operationInterfaceCreator.withOperationSignature(operationSignatureCreator);
            }

            // Persist placeholder interface
            repoAddition.addToRepository(operationInterfaceCreator);
        }

        // Get all unique components from empty seff entities
        Multimap<BasicComponent, ASTNode> componentNodeMap = ArrayListMultimap.create();
        for (ASTNode node : this.ast2SeffMap.keySet()) {
            ServiceEffectSpecification placeholderSeff = this.ast2SeffMap.get(node);
            BasicComponent persistedComponent = placeholderSeff.getBasicComponent_ServiceEffectSpecification();
            componentNodeMap.put(persistedComponent, node);
        }

        // Replace unique persistent real components with placeholders
        Multimap<BasicComponent, ASTNode> fluentComponentNodeMap = ArrayListMultimap.create();
        for (BasicComponent persistedComponent : componentNodeMap.keySet()) {
            // Create new placeholder component for fluent repository
            BasicComponent placeholderComponent = fluentFactory.newBasicComponent()
                    .withName(persistedComponent.getEntityName()).build();

            // Persist placeholder component in fluent repository
            repoAddition.addToRepository(placeholderComponent);

            // Change map content by swapping real and fluent components
            Collection<ASTNode> nodes = componentNodeMap.get(persistedComponent);
            fluentComponentNodeMap.putAll(placeholderComponent, nodes);
        }

        // Create new ast2seff map with placeholder elements
        Map<ASTNode, ServiceEffectSpecification> ast2FluentSeffMap = new HashMap<>();
        for (ASTNode node : this.ast2SeffMap.keySet()) {
            ServiceEffectSpecification realSeff = this.ast2SeffMap.get(node);
            BasicComponent fluentComponent = fluentFactory
                    .fetchOfBasicComponent(realSeff.getBasicComponent_ServiceEffectSpecification().getEntityName());
            OperationSignature fluentSignature = fluentFactory
                    .fetchOfOperationSignature(realSeff.getDescribedService__SEFF().getEntityName());

            // Create new empty seff with elements that can be manipulated by visitor
            ServiceEffectSpecification fluentSeff = fluentFactory.newSeff()
                    .onSignature(fluentSignature)
                    .withSeffBehaviour()
                    .withStartAction().withName(NameUtil.START_ACTION_NAME).followedBy()
                    .stopAction().withName(NameUtil.STOP_ACTION_NAME)
                    .createBehaviourNow()
                    .build();
            fluentComponent.getServiceEffectSpecifications__BasicComponent().add(fluentSeff);

            // Add node & fluent seff to ast2FluentSeffMap
            ast2FluentSeffMap.put(node, fluentSeff);
        }

        // Iterate over all nodes of a component
        for (BasicComponent fluentComponent : fluentComponentNodeMap.keySet()) {
            for (ASTNode node : fluentComponentNodeMap.get(fluentComponent)) {
                ServiceEffectSpecification fluentSeff = ast2FluentSeffMap.get(node);
                OperationSignature fluentSignature = (OperationSignature) fluentSeff
                        .getDescribedService__SEFF();
                OperationInterface fluentInterface = fluentSignature.getInterface__OperationSignature();

                // Add provided role of interface to component if not added already
                boolean providedRoleMissing = fluentComponent
                        .getProvidedRoles_InterfaceProvidingEntity().stream()
                        .map(role -> (OperationProvidedRole) role)
                        .map(role -> role.getProvidedInterface__OperationProvidedRole())
                        .noneMatch(operationInterface -> operationInterface.getEntityName()
                                .equals(fluentInterface.getEntityName()));
                if (providedRoleMissing) {
                    OperationProvidedRole providedRole = RepositoryFactory.eINSTANCE.createOperationProvidedRole();
                    providedRole.setProvidedInterface__OperationProvidedRole(fluentInterface);
                    fluentComponent.getProvidedRoles_InterfaceProvidingEntity().add(providedRole);
                }

                // Create fluent seff for node
                ActionSeff actionSeff = fluentFactory.newSeff()
                        .onSignature(fluentSignature)
                        .withSeffBehaviour()
                        .withStartAction().withName(NameUtil.START_ACTION_NAME).followedBy();

                // Perform AST node visit to fill empty fluent seff with content
                SeffCreator actionSeffCreator = Ast2SeffVisitor
                        .perform(actionSeff, node, ast2FluentSeffMap, fluentFactory)
                        .stopAction().withName(NameUtil.STOP_ACTION_NAME)
                        .createBehaviourNow();

                // Add completed seff to placeholder component
                fluentComponent.getServiceEffectSpecifications__BasicComponent().add(actionSeffCreator.build());
            }
            monitor.worked(1);
        }

        // Remove empty seff placeholders from fluent components to avoid duplicated seffs
        for (BasicComponent fluentComponent : fluentComponentNodeMap.keySet()) {
            for (ASTNode node : fluentComponentNodeMap.get(fluentComponent)) {
                ServiceEffectSpecification emptySeffPlaceholder = ast2FluentSeffMap.get(node);
                fluentComponent.getServiceEffectSpecifications__BasicComponent().remove(emptySeffPlaceholder);
            }
        }
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
}
