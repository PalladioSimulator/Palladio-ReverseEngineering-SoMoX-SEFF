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
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.net4j.util.om.monitor.SubMonitor;
import org.emftext.language.java.statements.StatementListContainer;
import org.palladiosimulator.generator.fluent.repository.api.Repo;
import org.palladiosimulator.generator.fluent.repository.api.RepoAddition;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationInterfaceCreator;
import org.palladiosimulator.generator.fluent.repository.structure.internals.Primitive;
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
import org.somox.kdmhelper.MethodAssociation;
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
	
	private Map<String, MethodAssociation> methodNameMap = new HashMap<>();
	List<MethodAssociation> methodAssociationList = new ArrayList();
	
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
		
		this.methodAssociationList = (List<MethodAssociation>) this.blackboard.getPartition("methodAssociationList");

        RepoAddition repoAddition = create.newRepository().withName("Simple Repository");
        
        Map<BasicComponent, OperationInterfaceCreator> componentOperationInterfaceMap = new HashMap<>();
        
        for (MethodAssociation methodAssociation : methodAssociationList) {
			BasicComponent basicComponent = methodAssociation.getBasicComponent();
			
        	MethodDeclaration methodDeclaration = (MethodDeclaration) methodAssociation.getAstNode();
        	List<SingleVariableDeclaration> singleVariableDeclarationList = methodDeclaration.parameters();

        	OperationInterfaceCreator operationInterfaceCreator = null;
        	
        	if (componentOperationInterfaceMap.containsKey(basicComponent)) {
        		operationInterfaceCreator = componentOperationInterfaceMap.get(basicComponent);
        	} else {
        		operationInterfaceCreator = create.newOperationInterface()
        				.withName(basicComponent.getEntityName());
        	}
        	
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
        				
        				operationInterfaceCreator = operationInterfaceCreator.withOperationSignature(create.newOperationSignature()
                				.withName(methodDeclaration.getName().toString())
                				.withParameter(parameterName, primitive, ParameterModifier.NONE));
        			}	
        		}
        	}
        	componentOperationInterfaceMap.put(basicComponent, operationInterfaceCreator);
        }
        
        for (Map.Entry<BasicComponent, OperationInterfaceCreator> entry : componentOperationInterfaceMap.entrySet()) {
			OperationInterfaceCreator operationInterfaceCreator = entry.getValue();
			repoAddition = repoAddition.addToRepository(operationInterfaceCreator);
		}
        
        Repository repository = repoAddition.createRepositoryNow();
		
		// TODO: Same method name from different files?
		for (MethodAssociation methodAssociation : methodAssociationList) {
			MethodDeclaration methodDeclaration = (MethodDeclaration) methodAssociation.getAstNode();
			String strPackageName = "unknown";
			ASTNode root = methodDeclaration.getRoot();
			if (root instanceof CompilationUnit) {
				PackageDeclaration packageName = ((CompilationUnit) root).getPackage();
				strPackageName = packageName.getName().toString();
				strPackageName += "." + methodAssociation.getBasicComponent().getEntityName();
			} else {
				LOGGER.warn("No CompilationUnit found for: " + methodDeclaration.toString());
			}
			
			if (!this.methodNameMap.containsKey(strPackageName + "." + methodDeclaration.getName().toString()))
				this.methodNameMap.put(strPackageName + "." + methodDeclaration.getName().toString(), methodAssociation);
		}

		final IProgressMonitor subMonitor = SubMonitor.convert(monitor);
		subMonitor.setTaskName("Creating SEFF behaviour");
		
		for (MethodAssociation methodAssociation : methodAssociationList) {
			ResourceDemandingSEFF seff = methodAssociation.getSeff();
			final String name = seff.getId();
			LOGGER.info("Found AST behaviour, generating SEFF behaviour for it: " + name);
			
			seff = this.createSeff(seff, methodAssociation);
			methodAssociation.setSeff(seff);
			
			BasicComponent basicComponent = methodAssociation.getBasicComponent();
        	basicComponent.getServiceEffectSpecifications__BasicComponent().add(methodAssociation.getSeff());
			
			monitor.worked(1);
		}
		
		
		//Composite Data Type snippet
		EList<DataType> repositoryDataTypes = repository.getDataTypes__Repository();
		CompositeDataType compositeDataType = RepositoryFactory.eINSTANCE.createCompositeDataType();
		PrimitiveDataType primitiveDataType = RepositoryFactory.eINSTANCE.createPrimitiveDataType();
		primitiveDataType.setType(PrimitiveTypeEnum.INT);
		primitiveDataType.setRepository__DataType(repository);
		compositeDataType.setEntityName("TestDataType");
		compositeDataType.setRepository__DataType(repository);
		InnerDeclaration innerDataType = RepositoryFactory.eINSTANCE.createInnerDeclaration();
		innerDataType.setEntityName("TestInnerName");
		innerDataType.setDatatype_InnerDeclaration(primitiveDataType);
		compositeDataType.getInnerDeclaration_CompositeDataType().add(innerDataType);
		repositoryDataTypes.add(compositeDataType);
		//end snippet
		
		this.generateSeffXmlFile(repository);

//		final Iterator<SEFF2MethodMapping> iterator = this.sourceCodeDecoratorModel.getSeff2MethodMappings().iterator();
//		while (iterator.hasNext()) {
//			final SEFF2MethodMapping astBehaviour = iterator.next();
//			final ResourceDemandingSEFF seff = (ResourceDemandingSEFF) astBehaviour.getSeff();
//			final String name = seff.getId();
//			LOGGER.info("Found AST behaviour, generating SEFF behaviour for it: " + name);
//
//			this.generateSEFFForGASTBehaviour(seff);
//			monitor.worked(1);
//		}

		// Create default annotations
//		final DefaultQosAnnotationsBuilder qosAnnotationBuilder = new DefaultQosAnnotationsBuilder();
//		qosAnnotationBuilder.buildDefaultQosAnnotations(this.sourceCodeDecoratorModel.getSeff2MethodMappings());

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
	private ResourceDemandingSEFF createSeff(ResourceDemandingSEFF seff, MethodAssociation methodAssociation) throws JobFailedException {
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		
		return Ast2SeffVisitor.perform(methodAssociation, actionSeff, this.methodNameMap)
				.stopAction().createBehaviourNow().buildRDSeff();
	}
	
	private void generateSeffXmlFile(final Repository repository) {
		
		repository.setEntityName("Simple Repository");
		
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
