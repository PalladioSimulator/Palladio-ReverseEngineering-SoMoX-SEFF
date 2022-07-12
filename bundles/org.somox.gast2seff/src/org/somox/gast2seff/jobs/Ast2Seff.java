/**
 *
 */
package org.somox.gast2seff.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.net4j.util.om.monitor.SubMonitor;
import org.emftext.language.java.statements.StatementListContainer;
import org.palladiosimulator.generator.fluent.repository.api.Repo;
import org.palladiosimulator.generator.fluent.repository.api.RepoAddition;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.api.seff.Seff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.SeffCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationInterfaceCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationSignatureCreator;
import org.palladiosimulator.generator.fluent.repository.structure.internals.Primitive;
import org.palladiosimulator.generator.fluent.repository.structure.types.CompositeDataTypeCreator;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.ParameterModifier;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.PrimitiveTypeEnum;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.somox.gast2seff.visitors.Ast2SeffVisitor;
import org.somox.kdmhelper.MethodBundlePair;
import org.somox.kdmhelper.MethodPalladioInformation;
import org.somox.sourcecodedecorator.SEFF2MethodMapping;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

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
public class Ast2Seff implements IBlackboardInteractingJob<Blackboard<Object>> {

	private static final Logger LOGGER = Logger.getLogger(Ast2Seff.class);
	private static final FluentRepositoryFactory create = new FluentRepositoryFactory();
	
	/** The SoMoX blackboard to interact with. */
	private Blackboard<Object> blackboard;
	
	private Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
	private Map<String, List<MethodBundlePair>> bundleName2methodAssociationMap;
	//List<MethodAssociation> methodAssociationList = new ArrayList();
	
	/**
	 * Resources containing the models
	 */
	private SourceCodeDecoratorRepository sourceCodeDecoratorModel;
	private EList<SEFF2MethodMapping> Seff2MethodMappings;

	/**
	 * Field that indicates whether ResourceDemandingInternalBehaviour should be created for
	 * internal method calls or not. If set to true one RDIB will be created for each internal
	 * method. If set to false the intern method are inlined in the SEFF directly.
	 */
	private final boolean createResourceDemandingInternalBehaviour;

	public Ast2Seff() {
		this(false);
	}

	public Ast2Seff(final boolean createResourceDemandingInternalBehaviour) {
		this.createResourceDemandingInternalBehaviour = createResourceDemandingInternalBehaviour;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

		monitor.subTask("loading models from blackboard");
		
		//this.methodAssociationList = (List<MethodAssociation>) this.blackboard.getPartition("methodAssociationList");
		try {
			this.bundleName2methodAssociationMap = (Map<String, List<MethodBundlePair>>) this.blackboard.getPartition("bundleName2methodAssociationMap");
		} catch (Exception e) {
			LOGGER.error(e);
		}

        RepoAddition repoAddition = create.newRepository().withName("Simple Repository");
        
        //Map<BasicComponent, OperationInterfaceCreator> componentOperationInterfaceMap = new HashMap<>();
        
        LOGGER.info("Found " + bundleName2methodAssociationMap.size() + " Bundles. Computing Interfaces.");
        
        for(Map.Entry<String, List<MethodBundlePair>> entry : bundleName2methodAssociationMap.entrySet()) {
        	String bundleName = entry.getKey();
        	List<MethodBundlePair> methodAssociationListOfBundle = entry.getValue();
        	LOGGER.info("Found " + methodAssociationListOfBundle.size() + " methods to " + bundleName + ". Computing Interfaces.");
        	
        	OperationInterfaceCreator bundleOperationInterfaceCreator = create.newOperationInterface().withName(bundleName);
        	
        	for (MethodBundlePair methodAssociation : methodAssociationListOfBundle) {
        		MethodDeclaration methodDeclaration = (MethodDeclaration) methodAssociation.getAstNode();
        		OperationSignatureCreator methodOperationSignature = create.newOperationSignature().withName(methodDeclaration.getName().toString());
        		
    			//How is Bundle defined? Can one Bundle have multiple CompilationUnits?
        		String strPackageName = "unknown";
    			ASTNode root = methodDeclaration.getRoot();
    			if (root instanceof CompilationUnit) {
    				PackageDeclaration packageName = ((CompilationUnit) root).getPackage();
    				strPackageName = packageName.getName().toString() + "." + bundleName;
    			} else {
    				LOGGER.warn("No CompilationUnit found for: " + methodDeclaration.toString());
    			}
    			
    			String key = strPackageName + "." + methodDeclaration.getName().toString();
    			String operationSignatureName = methodDeclaration.getName().toString();
    			String operationInterfaceName = bundleName;
    			if (!this.methodNameMap.containsKey(key))
    				this.methodNameMap.put(key, new MethodPalladioInformation(key, operationSignatureName, operationInterfaceName));
        		
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
        					SimpleType simpleType = (SimpleType) type;
        					CompositeDataTypeCreator compositeDataType = create.newCompositeDataType().withName(simpleType.toString());
        					
        					//testing stuff
        					compositeDataType.withInnerDeclaration("counter", Primitive.INTEGER);
        					IVariableBinding binding = variableDeclaration.resolveBinding();
        					if(binding.getDeclaringClass() != null) {
        						int test = 3;
        						int testasdf = 5;
        						//TODO: add primitiveTypes
        						////Composite Data Type Snipped
        						//EList<DataType> repositoryDataTypes = repository.getDataTypes__Repository();
        						//PrimitiveDataType primitiveDataType = RepositoryFactory.eINSTANCE.createPrimitiveDataType();
        						//primitiveDataType.setType(PrimitiveTypeEnum.INT);
        						//primitiveDataType.setRepository__DataType(repository);
        						//InnerDeclaration innerDataType = RepositoryFactory.eINSTANCE.createInnerDeclaration();
        						//innerDataType.setEntityName("TestInnerName");
        						//innerDataType.setDatatype_InnerDeclaration(primitiveDataType);
        						//compositeDataType.getInnerDeclaration_CompositeDataType().add(innerDataType);
        						//repositoryDataTypes.add(compositeDataType);
        						////end Snipped
        					}
        					//end testing stuff
        					methodOperationSignature.withParameter(parameterName, compositeDataType.build(), ParameterModifier.IN);
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
        				
        				methodOperationSignature.withParameter("OutputPara", primitive, ParameterModifier.OUT);
        			}
        		}
        		
        		bundleOperationInterfaceCreator.withOperationSignature(methodOperationSignature);
        	}
        	repoAddition.addToRepository(bundleOperationInterfaceCreator);
        }

		final IProgressMonitor subMonitor = SubMonitor.convert(monitor);
		subMonitor.setTaskName("Creating SEFF behaviour");
		
		for(Map.Entry<String, List<MethodBundlePair>> entry : bundleName2methodAssociationMap.entrySet()) {
        	String bundleName = entry.getKey();
        	List<MethodBundlePair> methodAssociationListOfBundle = entry.getValue();
        	LOGGER.info("Found " + methodAssociationListOfBundle.size() + " methods to " + bundleName + ". Computing Interfaces.");
        	
        	BasicComponentCreator basicComponent = create.newBasicComponent().withName(bundleName);
        	
			for (MethodBundlePair methodAssociation : methodAssociationListOfBundle) {
				basicComponent.withServiceEffectSpecification(this.createSeff(methodAssociation));
				monitor.worked(1);
			}
			
			repoAddition.addToRepository(basicComponent);
		}
		
		Repository repository = repoAddition.createRepositoryNow();		
		this.generateSeffXmlFile(repository);

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
	private SeffCreator createSeff(MethodBundlePair methodAssociation) throws JobFailedException {
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		
		return Ast2SeffVisitor.perform(methodAssociation, actionSeff, this.methodNameMap)
				.stopAction().createBehaviourNow();
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
	 * Retrieve the matching GAST behaviour stub from the GAST Behaviour repository
	 *
	 * @param seff
	 *            The gast behaviour stub for which a matching GAST behaviour is needed
	 * @return The GAST behaviour matching the gast behaviour stub
	 * @throws JobFailedException
	 *             Thrown if the gast behaviour is missing in the model file
	 */
	private StatementListContainer findBody(final ResourceDemandingSEFF seff) throws JobFailedException {

		// assert
		// onlyOnceAsGastBehaviour(this.gastBehaviourRepositoryModel.getSeff2MethodMappings(),
		// seff);
		// TODO burkha 16.05.2013 remove this after checking
		this.onlyOnceAsGastBehaviour(this.sourceCodeDecoratorModel.getSeff2MethodMappings(), seff);

		for (final SEFF2MethodMapping behaviour : this.sourceCodeDecoratorModel.getSeff2MethodMappings()) {
			if (((ResourceDemandingSEFF) behaviour.getSeff()).getId().equals(seff.getId())) {
				Ast2Seff.LOGGER.debug("Matching SEFF found " + seff.getId());
				return behaviour.getStatementListContainer();
			}
		}
		Ast2Seff.LOGGER.warn("Checked gastBehaviourRepository for " + seff.getId() + " but found none");
		throw new JobFailedException("Unable to find operation body for given method");
	}

	/**
	 * For assertion only
	 *
	 * @param seff2MethodMappings
	 * @param seff
	 * @return
	 */
	private boolean onlyOnceAsGastBehaviour(final EList<SEFF2MethodMapping> seff2MethodMappings,
			final ServiceEffectSpecification seff) {
		int i = 0;
		for (final SEFF2MethodMapping mapping : seff2MethodMappings) {
			final ResourceDemandingSEFF seffMapping = (ResourceDemandingSEFF) mapping.getSeff();
			final ResourceDemandingSEFF seffInput = (ResourceDemandingSEFF) seff;
			if (seffMapping.getId().equals(seffInput.getId())) {
				i++;
			}
		}

		if (i != 1) {
			Ast2Seff.LOGGER.error("Assertion fails - onlyOnceAsGastBehaviour: i = " + i + " for "
					+ ((ResourceDemandingSEFF) seff).getId());
		}

		return i == 1; // must be exactly one
	}

	/**
	 * Generate a SEFF for the given GAST behaviour
	 *
	 * @param gastBehaviourStub
	 *            The gast behaviour stub for whose behaviour a SEFF is generated
	 * @return The generated SEFF
	 * @throws JobFailedException
	 */
	private ResourceDemandingSEFF generateSEFFForGASTBehaviour(final ResourceDemandingSEFF gastBehaviourStub)
			throws JobFailedException {
		// ResourceDemandingSEFF resourceDemandingSEFF =
		// SeffFactory.eINSTANCE.createResourceDemandingSEFF();

		// createSeff(gastBehaviourStub,resourceDemandingSEFF);
//		this.createSeff(gastBehaviourStub);

		// SeffBehaviourStub seffBehaviourStub = findOrCreateBehaviourStub(gastBehaviourStub);
		// resourceDemandingSEFF.setSeffBehaviourStub(seffBehaviourStub);

		return gastBehaviourStub;
	}

	/**
	 * Finds an existing SEFF behaviour stub and reuses it or creates a new SEFF behaviour stub if
	 * there is none for the given GAST behaviour stub
	 *
	 * @param gastBehaviourStub
	 *            The GAST behaviour stub for which a matching SEFF behaviour stub is searched
	 * @return The found or newly created SEFF behaviour stub
	 */
	// private SeffBehaviourStub findOrCreateBehaviourStub(ResourceDemandingSEFF gastBehaviourStub)
	// {
	// BasicComponent parentComponent = (BasicComponent) gastBehaviourStub.eContainer();
	// SeffBehaviourStub seffBehaviourStub = null;
	//
	// for (ServiceEffectSpecification behaviour :
	// parentComponent.getServiceEffectSpecifications__BasicComponent()) {
	// if (behaviour instanceof SeffBehaviourStub) {
	// SeffBehaviourStub candidateStub = (SeffBehaviourStub) behaviour;
	// if (candidateStub.getOperation() == gastBehaviourStub.getOperation) {
	// logger.debug("Found SEFF behaviour stub, reusing it...");
	// seffBehaviourStub = candidateStub;
	// break;
	// }
	// }
	// }
	//
	// if (seffBehaviourStub == null)
	// seffBehaviourStub = BehaviourFactory.eINSTANCE.createSeffBehaviourStub();
	//
	// seffBehaviourStub.setOperation(gastBehaviourStub.getOperation());
	// parentComponent.get.getOperationBehaviour().add(seffBehaviourStub);
	//
	// return seffBehaviourStub;
	// }

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
