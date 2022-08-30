/**
 *
 */
package org.somox.ast2seff.jobs;

import java.io.IOException;
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
import org.somox.ast2seff.models.ComponentInformation;
import org.somox.ast2seff.models.MethodBundlePair;
import org.somox.ast2seff.models.MethodPalladioInformation;
import org.somox.ast2seff.visitors.Ast2SeffVisitor;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Transformation Job transforming a SAM instance with GAST Behaviours into a SAM instance with SEFF
 * behaviours
 *
 * @author Steffen Becker, Klaus Krogmann
 */
public class Ast2SeffJob implements IBlackboardInteractingJob<Blackboard<Object>> {

	private static final Logger LOGGER = Logger.getLogger(Ast2SeffJob.class);
	private static final FluentRepositoryFactory create = new FluentRepositoryFactory();
	
	/** The SoMoX blackboard to interact with. */
	private Blackboard<Object> blackboard;
	
	private Map<String, MethodPalladioInformation> methodPalladioInfoMap = new HashMap<>();
	private Map<MethodBundlePair, MethodPalladioInformation> methodBundlePalladioInfoMap = new HashMap<>();
	private Map<String, List<MethodBundlePair>> bundleName2methodBundleMap;

	public Ast2SeffJob() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

		monitor.subTask("loading models from blackboard");
		
		try {
			this.bundleName2methodBundleMap = (Map<String, List<MethodBundlePair>>) this.blackboard.getPartition("bundleName2methodAssociationMap");
		} catch (Exception e) {
			LOGGER.error(e);
		}

        RepoAddition repoAddition = create.newRepository().withName("Simple Repository");
        
        LOGGER.info("Found " + bundleName2methodBundleMap.size() + " Bundles. Computing Interfaces.");
		int counter = 0;
        
        for(Map.Entry<String, List<MethodBundlePair>> entry : bundleName2methodBundleMap.entrySet()) {
        	String bundleName = entry.getKey();
        	List<MethodBundlePair> methodAssociationListOfBundle = entry.getValue();
        	LOGGER.info("Found " + methodAssociationListOfBundle.size() + " methods to " + bundleName + ". Computing Interfaces.");
        	counter += methodAssociationListOfBundle.size();
        	
        	OperationInterfaceCreator bundleOperationInterfaceCreator = create.newOperationInterface().withName(bundleName);
        	
        	for (MethodBundlePair methodBundlePair : methodAssociationListOfBundle) {
        		MethodDeclaration methodDeclaration = (MethodDeclaration) methodBundlePair.getAstNode();
        		OperationSignatureCreator methodOperationSignature = create.newOperationSignature().withName(methodDeclaration.getName().toString());
        		
    			//How is Bundle defined? Can one Bundle have multiple CompilationUnits?
        		String strPackageName = "unknown";
    			ASTNode root = methodDeclaration.getRoot();
    			if (root instanceof CompilationUnit) {
    				PackageDeclaration packageName = ((CompilationUnit) root).getPackage();
    				strPackageName = packageName.getName().toString() + "." + bundleName;
    			} else {
    				LOGGER.error("No CompilationUnit found for: " + methodDeclaration.toString());
    			}
    			
    			String key = strPackageName + "." + methodDeclaration.getName().toString();
    			String operationSignatureName = methodDeclaration.getName().toString();
    			String operationInterfaceName = bundleName;
    			if (!this.methodPalladioInfoMap.containsKey(key)) {
    				MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation(key, operationSignatureName, operationInterfaceName, methodBundlePair);
    				this.methodPalladioInfoMap.put(key, methodPalladioInformation);
    				this.methodBundlePalladioInfoMap.put(methodBundlePair, methodPalladioInformation);
    			}
        			
        		List<SingleVariableDeclaration> singleVariableDeclarationList = methodDeclaration.parameters();
        		
        		if (singleVariableDeclarationList != null && singleVariableDeclarationList.size() > 0) {
        			for (SingleVariableDeclaration variableDeclaration : singleVariableDeclarationList) {
        				Type type = variableDeclaration.getType();
        				String parameterName = variableDeclaration.getName().toString();
        				if (type.isPrimitiveType()) {
        					Primitive primitive = Primitive.STRING;
        					PrimitiveType primitiveType = (PrimitiveType) type;
        					String primitiveTypeCodeString = primitiveType.getPrimitiveTypeCode().toString();
        					
        					if (primitiveTypeCodeString.equals(PrimitiveType.INT.toString())) {
        						primitive = Primitive.INTEGER;
        					} else if (primitiveTypeCodeString.equals(PrimitiveType.SHORT.toString())) {
        						primitive = Primitive.INTEGER;
        					} else if (primitiveTypeCodeString.equals(PrimitiveType.DOUBLE.toString())) {
        						primitive = Primitive.DOUBLE;
        					} else if (primitiveTypeCodeString.equals(PrimitiveType.FLOAT.toString())) {
        						primitive = Primitive.DOUBLE;
        					} else if (primitiveTypeCodeString.equals(PrimitiveType.CHAR.toString())) {
        						primitive = Primitive.CHAR;
        					} else if (primitiveTypeCodeString.equals(PrimitiveType.BYTE.toString())) {
        						primitive = Primitive.BYTE;
        					} else if (primitiveTypeCodeString.equals(PrimitiveType.BOOLEAN.toString())) {
        						primitive = Primitive.BOOLEAN;
        					} else {
        						// TODO: handle error
        					}
        					
        					methodOperationSignature.withParameter(parameterName, primitive, ParameterModifier.IN);
        				} else if(type.isSimpleType()) {
        					//SimpleType simpleType = (SimpleType) type;
        					//CompositeDataTypeCreator compositeDataType = create.newCompositeDataType().withName(simpleType.toString());
//        					
//        					//testing stuff
        					//compositeDataType.withInnerDeclaration("counter", Primitive.INTEGER);
//        					IVariableBinding binding = variableDeclaration.resolveBinding();
//        					if(binding.getDeclaringClass() != null) {
//        						int test = 3;
//        						int testasdf = 5;
//        						
//        						//TODO: add primitiveTypes
//        						////Composite Data Type Snipped
//        						//EList<DataType> repositoryDataTypes = repository.getDataTypes__Repository();
//        						//PrimitiveDataType primitiveDataType = RepositoryFactory.eINSTANCE.createPrimitiveDataType();
//        						//primitiveDataType.setType(PrimitiveTypeEnum.INT);
//        						//primitiveDataType.setRepository__DataType(repository);
//        						//InnerDeclaration innerDataType = RepositoryFactory.eINSTANCE.createInnerDeclaration();
//        						//innerDataType.setEntityName("TestInnerName");
//        						//innerDataType.setDatatype_InnerDeclaration(primitiveDataType);
//        						//compositeDataType.getInnerDeclaration_CompositeDataType().add(innerDataType);
//        						//repositoryDataTypes.add(compositeDataType);
//        						////end Snipped
        					//}
        					//end testing stuff
        					//methodOperationSignature.withParameter(parameterName, compositeDataType.build(), ParameterModifier.IN);
        				}
        			}
        		}
        		
        		Type returnType = methodDeclaration.getReturnType2();
        		if(returnType != null && returnType.isPrimitiveType()) {
        			Primitive primitive = Primitive.STRING;
        			//PrimitiveDataType primitiveDataType = RepositoryFactory.eINSTANCE.createPrimitiveDataType();
        			PrimitiveType primitiveType = (PrimitiveType) returnType;
        			String primitiveTypeCodeString = primitiveType.getPrimitiveTypeCode().toString();
        			
        			//TODO: refactor
        			if (!primitiveTypeCodeString.equals(PrimitiveType.VOID.toString())) {
        				
        				if (primitiveTypeCodeString.equals(PrimitiveType.INT.toString())) {
        					primitive = Primitive.INTEGER;
        				} else if (primitiveTypeCodeString.equals(PrimitiveType.SHORT.toString())) {
        					primitive = Primitive.INTEGER;
        				} else if (primitiveTypeCodeString.equals(PrimitiveType.DOUBLE.toString())) {
        					primitive = Primitive.DOUBLE;
        				} else if (primitiveTypeCodeString.equals(PrimitiveType.FLOAT.toString())) {
        					primitive = Primitive.DOUBLE;
        				} else if (primitiveTypeCodeString.equals(PrimitiveType.CHAR.toString())) {
        					primitive = Primitive.CHAR;
        				} else if (primitiveTypeCodeString.equals(PrimitiveType.BYTE.toString())) {
        					primitive = Primitive.BYTE;
        				} else if (primitiveTypeCodeString.equals(PrimitiveType.BOOLEAN.toString())) {
        					primitive = Primitive.BOOLEAN;
        				} else {
        					// TODO: handle error
        				}
        				
        				//methodOperationSignature.withParameter("OutputPara", primitive, ParameterModifier.OUT);
        				methodOperationSignature.withReturnType(primitive);
        			}
        		}
        		
        		bundleOperationInterfaceCreator.withOperationSignature(methodOperationSignature);
        	}
        	repoAddition.addToRepository(bundleOperationInterfaceCreator);
        }

		final IProgressMonitor subMonitor = SubMonitor.convert(monitor);
		subMonitor.setTaskName("Creating SEFF behaviour");
    	LOGGER.info("Interfaces done. Computing " + counter + " SEFFs.");
		
		for(Map.Entry<String, List<MethodBundlePair>> entry : bundleName2methodBundleMap.entrySet()) {
        	String bundleName = entry.getKey();
        	List<MethodBundlePair> methodBundleList = entry.getValue();
        	
        	BasicComponentCreator basicComponentCreator = create.newBasicComponent().withName(bundleName).provides(create.fetchOfOperationInterface(bundleName));
        	ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
        	
			for (MethodBundlePair methodBundlePair : methodBundleList) {
				MethodPalladioInformation methodPalladioInformation = methodBundlePalladioInfoMap.get(methodBundlePair);
				basicComponentCreator.withServiceEffectSpecification(this.createSeff(methodPalladioInformation, componentInformation).withName(methodPalladioInformation.getMethodName()));
				monitor.worked(1);
			}
			
			repoAddition.addToRepository(basicComponentCreator);
		}
		
		LOGGER.info("SEFFs done. Creating Repository.");
		Repository repository = repoAddition.createRepositoryNow();
		
		LOGGER.info("Repository done. Creating XML.");
		this.generateSeffXmlFile(repository);

		LOGGER.info("Task finished.");
		subMonitor.done();
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
	private SeffCreator createSeff(MethodPalladioInformation methodPalladioInformation, ComponentInformation componentInformation) throws JobFailedException {
		ActionSeff actionSeff = create.newSeff().onSignature(create.fetchOfSignature(methodPalladioInformation.getOperationSignatureName()))
				.withSeffBehaviour().withStartAction().withName("Start Action").followedBy();
		
		return Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, this.methodPalladioInfoMap, componentInformation, create)
				.stopAction().withName("Stop Action").createBehaviourNow();
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
	 * @param blackBoard
	 *            the blackBoard to set
	 */
	@Override
	public void setBlackboard(final Blackboard<Object> blackBoard) {
		this.blackboard = blackBoard;
	}

	@Override
	public void cleanup(final IProgressMonitor monitor) throws CleanupFailedException {
	}
}
