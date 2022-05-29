/**
 *
 */
package org.somox.gast2seff.jobs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.net4j.util.om.monitor.SubMonitor;
import org.emftext.language.java.statements.StatementListContainer;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.somox.gast2seff.visitors.DefaultResourceDemandingBehaviourForClassMethodFinder;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor;
import org.somox.gast2seff.visitors.IFunctionClassificationStrategy;
import org.somox.gast2seff.visitors.IFunctionClassificationStrategyFactory;
import org.somox.gast2seff.visitors.InterfaceOfExternalCallFindingFactory;
import org.somox.gast2seff.visitors.MethodCallFinder;
import org.somox.gast2seff.visitors.Ast2SeffVisitor;
import org.somox.gast2seff.visitors.ResourceDemandingBehaviourForClassMethodFinding;
import org.somox.gast2seff.visitors.VisitorUtils;
import org.somox.kdmhelper.Root;
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

	/** The SoMoX blackboard to interact with. */
	private Blackboard<Object> blackboard;
	
	private Map<MethodDeclaration, ResourceDemandingSEFF> methodBindingMap = new HashMap<>();

	/**
	 * Resources containing the models
	 */
	private SourceCodeDecoratorRepository sourceCodeDecoratorModel;
	private Root root;

	private MethodCallFinder methodCallFinder;

	/**
	 * Field that indicates whether ResourceDemandingInternalBehaviour should be created for
	 * internal method calls or not. If set to true one RDIB will be created for each internal
	 * method. If set to false the intern method are inlined in the SEFF directly.
	 */
	private final boolean createResourceDemandingInternalBehaviour;

	/**
	 * factory to create the used IFunctionClassifcationStrategy
	 */
	private final IFunctionClassificationStrategyFactory iFunctionClassificationStrategyFactory;

	private InterfaceOfExternalCallFindingFactory interfaceOfExternalCallFindingFactory;

	public Ast2Seff() {
		this(false, new IFunctionClassificationStrategyFactory() {
		}, new InterfaceOfExternalCallFindingFactory() {
		});
	}

	public Ast2Seff(final boolean createResourceDemandingInternalBehaviour) {
		this(createResourceDemandingInternalBehaviour, new IFunctionClassificationStrategyFactory() {
		}, new InterfaceOfExternalCallFindingFactory() {
		});
	}

	public Ast2Seff(final boolean createResourceDemandingInternalBehaviour,
			final IFunctionClassificationStrategyFactory iFunctionClassificationStrategyFactory,
			InterfaceOfExternalCallFindingFactory interfaceOfExternalCallFindingFactory) {
		this.createResourceDemandingInternalBehaviour = createResourceDemandingInternalBehaviour;
		this.iFunctionClassificationStrategyFactory = iFunctionClassificationStrategyFactory;
		this.interfaceOfExternalCallFindingFactory = interfaceOfExternalCallFindingFactory;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

		monitor.subTask("loading models from blackboard");

		// TODO this.sourceCodeDecoratorModel =
		// this.blackboard.getSourceCodeDecoratorRepository();
		// TODO this.root = this.blackboard.getRoot();
		
		this.methodBindingMap = (Map<MethodDeclaration, ResourceDemandingSEFF>) this.blackboard.getPartition("methodBindingMap");
		
		this.methodCallFinder = new MethodCallFinder();

		final IProgressMonitor subMonitor = SubMonitor.convert(monitor);
		subMonitor.setTaskName("Creating SEFF behaviour");
		
		for (var entry : methodBindingMap.entrySet()) {
			final ResourceDemandingSEFF seff = entry.getValue();
			final String name = seff.getId();
			LOGGER.info("Found AST behaviour, generating SEFF behaviour for it: " + name);
			
			this.createSeff(seff, entry.getKey());
			monitor.worked(1);
		}

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
		final DefaultQosAnnotationsBuilder qosAnnotationBuilder = new DefaultQosAnnotationsBuilder();
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
	private ResourceDemandingSEFF createSeff(final ResourceDemandingSEFF seff, MethodDeclaration methodDeclaration) throws JobFailedException {
		final StartAction start = SeffFactory.eINSTANCE.createStartAction();
		final StopAction stop = SeffFactory.eINSTANCE.createStopAction();
		seff.getSteps_Behaviour().add(start);

		Ast2SeffVisitor.perform(methodDeclaration, seff.getSteps_Behaviour());
		
		// initialise for new component / seff to reverse engineer:
//		final BasicComponent basicComponent = (BasicComponent) seff.eContainer();
//		final IFunctionClassificationStrategy basicFunctionClassifierStrategy = this.iFunctionClassificationStrategyFactory
//				.createIFunctionClassificationStrategy(this.sourceCodeDecoratorModel, basicComponent, this.root,
//						this.methodCallFinder);
//		FunctionCallClassificationVisitor typeVisitor = new FunctionCallClassificationVisitor(
//				basicFunctionClassifierStrategy,
//				this.methodCallFinder);
//
//		final StatementListContainer body = this.findBody(seff);
//		Ast2Seff.LOGGER.trace("visiting (seff entry): " + seff.getId());
//		if (body != null) {
//			if (this.createResourceDemandingInternalBehaviour) {
//				final ResourceDemandingBehaviourForClassMethodFinding defaultResourceDemandingBehaviourForClassMethodFinder = new DefaultResourceDemandingBehaviourForClassMethodFinder(
//						this.sourceCodeDecoratorModel, basicComponent);
//				VisitorUtils.visitJaMoPPMethod(seff, basicComponent, body, this.sourceCodeDecoratorModel,
//						typeVisitor, interfaceOfExternalCallFindingFactory,
//						defaultResourceDemandingBehaviourForClassMethodFinder, this.methodCallFinder);
//			} else {
//				VisitorUtils.visitJaMoPPMethod(seff, basicComponent, body, this.sourceCodeDecoratorModel,
//						typeVisitor, interfaceOfExternalCallFindingFactory, this.methodCallFinder);
//			}
//
//		} else {
//			Ast2Seff.LOGGER.warn("Found GAST behaviour (" + seff.getId() + ") without a method body... Skipping it...");
//		}

		seff.getSteps_Behaviour().add(stop);
		VisitorUtils.connectActions(seff);

		return seff;
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
