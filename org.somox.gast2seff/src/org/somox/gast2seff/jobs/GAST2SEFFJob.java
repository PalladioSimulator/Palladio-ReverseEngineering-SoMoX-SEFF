/**
 *
 */
package org.somox.gast2seff.jobs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.statements.StatementListContainer;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.gast2seff.visitors.BasicFunctionClassificationStrategy;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor;
import org.somox.gast2seff.visitors.IFunctionClassificationStrategy;
import org.somox.gast2seff.visitors.VisitorUtils;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.seff2javaast.SEFF2JavaAST;
import org.somox.seff2javaast.SEFF2MethodMapping;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

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
public class GAST2SEFFJob implements IBlackboardInteractingJob<SoMoXBlackboard> {

    private final Logger logger = Logger.getLogger(GAST2SEFFJob.class);

    /** The SoMoX blackboard to interact with. */
    private SoMoXBlackboard blackboard;

    /**
     * The resource set used to load and store all resources needed for the transformation
     */
    private final ResourceSet resourceSet = new ResourceSetImpl();

    /**
     * Resources containing the models
     */
    private SEFF2JavaAST gastBehaviourRepositoryModel;
    private SourceCodeDecoratorRepository sourceCodeDecoratorModel;
    private Root root;

    private FunctionCallClassificationVisitor typeVisitor;

    public GAST2SEFFJob() {
        super();
        // performance optimisation:
        final Map<URI, Resource> cache = new HashMap<URI, Resource>();
        ((ResourceSetImpl) this.resourceSet).setURIResourceMap(cache);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        monitor.subTask("loading models from blackboard");

        final AnalysisResult result = this.blackboard.getAnalysisResult();
        this.gastBehaviourRepositoryModel = result.getSeff2JavaAST();
        this.sourceCodeDecoratorModel = result.getSourceCodeDecoratorRepository();
        this.root = result.getRoot();

        final IProgressMonitor subMonitor = new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN);
        subMonitor.setTaskName("Creating SEFF behaviour");

        final Iterator<SEFF2MethodMapping> iterator = this.gastBehaviourRepositoryModel.getSeff2MethodMappings()
                .iterator();
        while (iterator.hasNext()) {
            final SEFF2MethodMapping astBehaviour = iterator.next();
            final ResourceDemandingSEFF seff = (ResourceDemandingSEFF) astBehaviour.getSeff();
            final String name = seff.getId();
            this.logger.info("Found AST behaviour, generating SEFF behaviour for it: " + name);

            this.generateSEFFForGASTBehaviour(seff);
            monitor.worked(1);
        }

        // Create default annotations
        final DefaultQosAnnotationsBuilder qosAnnotationBuilder = new DefaultQosAnnotationsBuilder();
        qosAnnotationBuilder.buildDefaultQosAnnotations(this.gastBehaviourRepositoryModel.getSeff2MethodMappings());

        subMonitor.done();
    }

    /*
     * (non-Javadoc)
     *
     * @see de.uka.ipd.sdq.workflow.IJob#getName()
     */
    @Override
    public String getName() {
        return "GAST2SEFF Transformation Job";
    }

    /**
     * Create a new PCM SEFF.
     *
     * @param seff
     *            The SEFF which is filled by this method
     * @return The completed SEFF, returned for convenience
     * @throws JobFailedException
     */
    private ResourceDemandingSEFF createSeff(final ResourceDemandingSEFF seff) throws JobFailedException {
        final StartAction start = SeffFactory.eINSTANCE.createStartAction();
        final StopAction stop = SeffFactory.eINSTANCE.createStopAction();
        seff.getSteps_Behaviour().add(start);

        // initialise for new component / seff to reverse engineer:
        final BasicComponent basicComponent = (BasicComponent) seff.eContainer();
        final IFunctionClassificationStrategy basicFunctionClassifierStrategy = new BasicFunctionClassificationStrategy(
                this.sourceCodeDecoratorModel, basicComponent, this.root);
        this.typeVisitor = new FunctionCallClassificationVisitor(basicFunctionClassifierStrategy);

        final StatementListContainer body = this.findBody(seff);// GAST2SEFFCHANGE
        this.logger.trace("visiting (seff entry): " + seff.getId());
        if (body != null) {
            VisitorUtils.visitJaMoPPMethod(seff, basicComponent, body, this.sourceCodeDecoratorModel, this.typeVisitor);
        } else {
            this.logger.warn("Found GAST behaviour (" + seff.getId() + ") without a method body... Skipping it...");
        }

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
    private StatementListContainer findBody(final ResourceDemandingSEFF seff) throws JobFailedException {// GAST2SEFFCHANGE

        // assert
        // onlyOnceAsGastBehaviour(this.gastBehaviourRepositoryModel.getSeff2MethodMappings(),
        // seff);
        // TODO burkha 16.05.2013 remove this after checking
        this.onlyOnceAsGastBehaviour(this.gastBehaviourRepositoryModel.getSeff2MethodMappings(), seff);

        for (final SEFF2MethodMapping behaviour : this.gastBehaviourRepositoryModel.getSeff2MethodMappings()) {
            if (((ResourceDemandingSEFF) behaviour.getSeff()).getId().equals(seff.getId())) {
                this.logger.debug("Matching SEFF found " + seff.getId());
                return behaviour.getBlockstatement();
            }
        }
        this.logger.warn("Checked gastBehaviourRepository for " + seff.getId() + " but found none");
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
            this.logger.error("Assertion fails - onlyOnceAsGastBehaviour: i = " + i + " for "
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
        this.createSeff(gastBehaviourStub);

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
    public void setBlackboard(final SoMoXBlackboard blackBoard) {
        this.blackboard = blackBoard;
    }

    @Override
    public void cleanup(final IProgressMonitor monitor) throws CleanupFailedException {
    }
}
