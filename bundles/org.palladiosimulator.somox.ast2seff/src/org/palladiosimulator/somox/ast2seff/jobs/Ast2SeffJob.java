/**
 *
 */
package org.palladiosimulator.somox.ast2seff.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.net4j.util.om.monitor.SubMonitor;
import org.palladiosimulator.generator.fluent.repository.api.RepoAddition;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.SeffCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationInterfaceCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationSignatureCreator;
import org.palladiosimulator.generator.fluent.repository.structure.internals.Primitive;
import org.palladiosimulator.generator.fluent.repository.structure.types.CompositeDataTypeCreator;
import org.palladiosimulator.pcm.repository.ParameterModifier;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.somox.ast2seff.models.ComponentInformation;
import org.palladiosimulator.somox.ast2seff.models.MethodBundlePair;
import org.palladiosimulator.somox.ast2seff.models.MethodPalladioInformation;
import org.palladiosimulator.somox.ast2seff.util.NameUtil;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Transformation Job transforming a SAM instance with AST Behaviours into a SAM instance with SEFF
 * behaviours
 *
 * @author Steffen Becker, Klaus Krogmann, Marcel Rühle, Fabian Wenzel
 */
public class Ast2SeffJob implements IBlackboardInteractingJob<Blackboard<Object>> {

    private static final Logger LOGGER = Logger.getLogger(Ast2SeffJob.class);
    private static final FluentRepositoryFactory create = new FluentRepositoryFactory();

    /** The SoMoX blackboard to interact with. */
    private Blackboard<Object> blackboard;

    private Map<String, MethodPalladioInformation> methodPalladioInfoMap = new HashMap<>();
    private Map<MethodBundlePair, MethodPalladioInformation> methodBundlePalladioInfoMap = new HashMap<>();
    private Map<String, List<MethodBundlePair>> bundleName2methodBundleMap;
    private List<String> parameterList = new ArrayList<String>();

    public Ast2SeffJob() { }

  
    
    /*
     * (non-Javadoc)
     *
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
    	LOGGER.info("Executing SEFF Creation Job.");
    	
        monitor.subTask("Loading models from blackboard");

        try {
            this.bundleName2methodBundleMap = (Map<String, List<MethodBundlePair>>) this.blackboard
                .getPartition("bundleName2methodAssociationMap");
        } catch (Exception e) {
            LOGGER.error(e);
        }

        RepoAddition repoAddition = create.newRepository().withName("Repository");

        LOGGER.info("Found " + bundleName2methodBundleMap.size() + " Bundles. Computing Interfaces.");
        int counter = 0;
        
        createOperationInterfacesForRepository(repoAddition, counter);

        final IProgressMonitor subMonitor = SubMonitor.convert(monitor);
        subMonitor.setTaskName("Creating SEFF behaviour");
        
        LOGGER.info("Created Interfaces. Computing " + counter + " SEFFs.");
        createSeffsForComponents(repoAddition, monitor);

        LOGGER.info("Created SEFFs. Creating Repository.");
        Repository repository = repoAddition.createRepositoryNow();

        LOGGER.info("Created Repository. Creating XML.");
        this.generateSeffXmlFile(repository);

        LOGGER.info("Created XML. Task finished.");
        subMonitor.done();
    }
    
    private void createSeffsForComponents(RepoAddition repoAddition, IProgressMonitor monitor) {
        for (Map.Entry<String, List<MethodBundlePair>> entry : bundleName2methodBundleMap.entrySet()) {
            String bundleName = entry.getKey();
            String interfaceName = "I" + bundleName;
            
            List<MethodBundlePair> methodBundleList = entry.getValue();

            
            BasicComponentCreator basicComponentCreator = create.newBasicComponent()
                .withName(bundleName)
                .provides(create.fetchOfOperationInterface(interfaceName), interfaceName);
            ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);

            for (MethodBundlePair methodBundlePair : methodBundleList) {
                MethodPalladioInformation methodPalladioInformation = methodBundlePalladioInfoMap.get(methodBundlePair);
                try {
					basicComponentCreator
					    .withServiceEffectSpecification(this.createSeff(methodPalladioInformation, componentInformation)
					        .withName(methodPalladioInformation.getMethodName()));
				} catch (JobFailedException e) {
					LOGGER.error(e.toString());
				}
                monitor.worked(1);
            }

            repoAddition.addToRepository(basicComponentCreator);
        }
    }
    
    private void createOperationInterfacesForRepository(RepoAddition repoAddition, int counter) {
    	for (Map.Entry<String, List<MethodBundlePair>> entry : bundleName2methodBundleMap.entrySet()) {
            String bundleName = entry.getKey();
            String interfaceName = "I" + bundleName;
            List<MethodBundlePair> methodAssociationListOfBundle = entry.getValue();
            LOGGER.info("Found " + methodAssociationListOfBundle.size() + " methods to " + bundleName
                    + ". Computing Interfaces.");
            counter += methodAssociationListOfBundle.size();

            OperationInterfaceCreator bundleOperationInterfaceCreator = create.newOperationInterface()
                .withName(interfaceName);

            for (MethodBundlePair methodBundlePair : methodAssociationListOfBundle) {
                MethodDeclaration methodDeclaration = (MethodDeclaration) methodBundlePair.getAstNode();
                OperationSignatureCreator methodOperationSignature = create.newOperationSignature()
                    .withName(methodDeclaration.getName()
                        .toString());

                // How is Bundle defined? Can one Bundle have multiple CompilationUnits?
                String strPackageName = "unknown";
                ASTNode root = methodDeclaration.getRoot();
                if (root instanceof CompilationUnit) {
                    PackageDeclaration packageName = ((CompilationUnit) root).getPackage();
                    strPackageName = packageName.getName()
                        .toString() + "." + bundleName;
                } else {
                    LOGGER.error("No CompilationUnit found for: " + methodDeclaration.toString());
                }

                String key = strPackageName + "." + methodDeclaration.getName()
                    .toString();
                String operationSignatureName = methodDeclaration.getName()
                    .toString();
                if (!this.methodPalladioInfoMap.containsKey(key)) {
                    MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation(key,
                            operationSignatureName, interfaceName, methodBundlePair);
                    this.methodPalladioInfoMap.put(key, methodPalladioInformation);
                    this.methodBundlePalladioInfoMap.put(methodBundlePair, methodPalladioInformation);
                }

                List<SingleVariableDeclaration> singleVariableDeclarationList = methodDeclaration.parameters();

                if (singleVariableDeclarationList != null && singleVariableDeclarationList.size() > 0) {
                    setParametersToSignature(singleVariableDeclarationList, repoAddition, methodOperationSignature);
                }

                Type returnType = methodDeclaration.getReturnType2();
                if (returnType != null && returnType.isPrimitiveType()) {
                    PrimitiveType primitiveType = (PrimitiveType) returnType;
                    String primitiveTypeCodeString = primitiveType.getPrimitiveTypeCode()
                        .toString();

                    if (!primitiveTypeCodeString.equals(PrimitiveType.VOID.toString())) {
                        methodOperationSignature.withReturnType(this.getPrimitiveType(primitiveTypeCodeString));
                    }
                }

                bundleOperationInterfaceCreator.withOperationSignature(methodOperationSignature);
            }
            repoAddition.addToRepository(bundleOperationInterfaceCreator);
        }
    }
    
    private void setParametersToSignature(List<SingleVariableDeclaration> singleVariableDeclarationList, RepoAddition repoAddition, OperationSignatureCreator methodOperationSignature) {
    	for (SingleVariableDeclaration variableDeclaration : singleVariableDeclarationList) {
            Type type = variableDeclaration.getType();
            String parameterName = variableDeclaration.getName().toString();
            
            if (type.isPrimitiveType()) {
                PrimitiveType primitiveType = (PrimitiveType) type;
                String primitiveTypeCodeString = primitiveType.getPrimitiveTypeCode()
                    .toString();

                if (parameterList.contains(primitiveTypeCodeString)) {
                    Primitive primitive = this.getPrimitiveType(primitiveTypeCodeString);
                    methodOperationSignature.withParameter(parameterName, create.fetchOfDataType(primitive),
                            ParameterModifier.IN);
                } else {
                    Primitive primitive = this.getPrimitiveType(primitiveTypeCodeString);
                    methodOperationSignature.withParameter(parameterName, primitive, ParameterModifier.IN);
                    parameterList.add(primitiveTypeCodeString);
                }
            } else if (type.isSimpleType()) {
                SimpleType simpleType = (SimpleType) type;

                if (!parameterList.contains(simpleType.toString())) {
                    CompositeDataTypeCreator compositeDataType = create.newCompositeDataType()
                        .withName(simpleType.toString());
                    
                    // TODO: WHY is the name important? This is not a generic approach, needs to be fixed
                    if (simpleType.toString()
                        .equals("SimpleClass"))
                        compositeDataType = compositeDataType.withInnerDeclaration("counter",
                                Primitive.INTEGER);
                    repoAddition.addToRepository(compositeDataType);
                    parameterList.add(simpleType.toString());
                }
                methodOperationSignature.withParameter(parameterName,
                        create.fetchOfCompositeDataType(simpleType.toString()), ParameterModifier.IN);
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
     * Create a new PCM SEFF.
     *
     * @param seff
     *            The SEFF which is filled by this method
     * @param methodDeclaration
     * @return The completed SEFF, returned for convenience
     * @throws JobFailedException
     */
    private SeffCreator createSeff(MethodPalladioInformation methodPalladioInformation,
            ComponentInformation componentInformation) throws JobFailedException {
        ActionSeff actionSeff = create.newSeff()
            .onSignature(create.fetchOfSignature(methodPalladioInformation.getOperationSignatureName()))
            .withSeffBehaviour()
            .withStartAction()
            .withName(NameUtil.START_ACTION_NAME)
            .followedBy();

        return Ast2SeffVisitor
            .perform(methodPalladioInformation, actionSeff, this.methodPalladioInfoMap, componentInformation, create)
            .stopAction()
            .withName(NameUtil.STOP_ACTION_NAME)
            .createBehaviourNow();
    }

    private void generateSeffXmlFile(final Repository repository) {

        EcorePlugin.ExtensionProcessor.process(null);
        Resource resource = new ResourceSetImpl().createResource(URI.createFileURI("Repository.xml"));
        resource.getContents().add(repository);

        try {
            resource.save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param blackBoard The BlackBoard to set
     */
    @Override
    public void setBlackboard(final Blackboard<Object> blackBoard) {
        this.blackboard = blackBoard;
    }

    @Override
    public void cleanup(final IProgressMonitor monitor) throws CleanupFailedException { }

    private Primitive getPrimitiveType(String primitiveTypeCodeString) {
        if (primitiveTypeCodeString.equals(PrimitiveType.INT.toString())) {
            return Primitive.INTEGER;
        } else if (primitiveTypeCodeString.equals(PrimitiveType.SHORT.toString())) {
            return Primitive.INTEGER;
        } else if (primitiveTypeCodeString.equals(PrimitiveType.DOUBLE.toString())) {
            return Primitive.DOUBLE;
        } else if (primitiveTypeCodeString.equals(PrimitiveType.FLOAT.toString())) {
            return Primitive.DOUBLE;
        } else if (primitiveTypeCodeString.equals(PrimitiveType.CHAR.toString())) {
            return Primitive.CHAR;
        } else if (primitiveTypeCodeString.equals(PrimitiveType.BYTE.toString())) {
            return Primitive.BYTE;
        } else if (primitiveTypeCodeString.equals(PrimitiveType.BOOLEAN.toString())) {
            return Primitive.BOOLEAN;
        } else {
            return Primitive.STRING; // String as default
        }
    }
}
