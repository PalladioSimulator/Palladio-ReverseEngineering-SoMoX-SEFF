package org.somox.test.gast2seff.visitors;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.InternalCallAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ProbabilisticBranchTransition;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingInternalBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;

public class JaMoPP2StatementVisitorWithResourceDemandingInternalBehaviourTest extends JaMoPPStatementVisitorTest {

    @BeforeClass
    public static void beforeClass() throws IOException {
        JaMoPP2PCMBaseTest.beforeClass();
        createResourceDemandingInternalBehaviourForClassMethods = true;
    }

    @Override
    public void testExternalCallInInternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCallAction = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        final InternalCallAction internalCallAction = this.createInternalCallAction(externalCallAction);
        expectedSeff.getSteps_Behaviour().add(internalCallAction);
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    @Override
    public void testTryBlockWithInternalCallInTryLibraryCallInCatchAndExternalCallInFinally() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final InternalCallAction ica = this.createInternalCallAction();
        expectedSeff.getSteps_Behaviour().add(ica);
        final ExternalCallAction eca = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        expectedSeff.getSteps_Behaviour().add(eca);
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    @Override
    public void testSwitchCaseWithInternalCallInCaseAndExternalCallDefault() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ResourceDemandingBehaviour firstCaseBehavior = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        firstCaseBehavior.getSteps_Behaviour().add(this.createInternalCallAction());
        final ResourceDemandingBehaviour secoundCaseBehavior = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        final ResourceDemandingBehaviour defaultCaseBehavior = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        defaultCaseBehavior.getSteps_Behaviour().add(this.createExternalCallAction(OPERATION_SIGNATURE_NAME));
        final BranchAction branchAction = this.createBranchAction(firstCaseBehavior, secoundCaseBehavior,
                defaultCaseBehavior);
        expectedSeff.getSteps_Behaviour().add(branchAction);
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    @Override
    public void testTryBlockWithExternalCallInInternalCallInTryBlock() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction eca = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        final InternalCallAction internalCallAction = this.createInternalCallAction(eca);
        expectedSeff.getSteps_Behaviour().add(internalCallAction);
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    @Override
    public void testDoInternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final InternalCallAction internalCallAction = this.createInternalCallAction();
        expectedSeff.getSteps_Behaviour().add(internalCallAction);
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    @Test
    public void testInternalCallThatContainsInternalActionAndForLoopWithExternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        expectedSeff.getSteps_Behaviour().add(this.createInternalAction());
        final InternalAction internalActionInInternalCall = this.createInternalAction();
        final LoopAction loopActionInInternalCall = SeffFactory.eINSTANCE.createLoopAction();
        loopActionInInternalCall.setEntityName("expectedLoopAction");
        final ResourceDemandingBehaviour loopBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        loopBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStartAction());
        loopBehaviour.getSteps_Behaviour().add(this.createExternalCallAction(OPERATION_SIGNATURE_NAME));
        loopBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
        loopActionInInternalCall.setBodyBehaviour_Loop(loopBehaviour);
        final InternalCallAction internalCallAction = this.createInternalCallAction(internalActionInInternalCall,
                loopActionInInternalCall);
        expectedSeff.getSteps_Behaviour().add(internalCallAction);
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    private BranchAction createBranchAction(final ResourceDemandingBehaviour... caseBehaviours) {
        final BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
        for (final ResourceDemandingBehaviour caseBehaviour : caseBehaviours) {
            caseBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
            caseBehaviour.getSteps_Behaviour().add(0, SeffFactory.eINSTANCE.createStartAction());
            final ProbabilisticBranchTransition branchTransition = SeffFactory.eINSTANCE
                    .createProbabilisticBranchTransition();
            branchTransition.setBranchBehaviour_BranchTransition(caseBehaviour);
            branchTransition.setEntityName("branch transition");
            branchAction.getBranches_Branch().add(branchTransition);
        }
        branchAction.setEntityName("expectedBranchAction");
        return branchAction;
    }

    private InternalCallAction createInternalCallAction(
            final AbstractAction... expectedBehaviorOfResourceInternalBehaviour) {
        final ResourceDemandingInternalBehaviour resourceDemandingInternalBehaviour = SeffFactory.eINSTANCE
                .createResourceDemandingInternalBehaviour();
        resourceDemandingInternalBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStartAction());
        for (final AbstractAction abstractAction : expectedBehaviorOfResourceInternalBehaviour) {
            resourceDemandingInternalBehaviour.getSteps_Behaviour().add(abstractAction);
        }
        resourceDemandingInternalBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
        final InternalCallAction internalCallAction = SeffFactory.eINSTANCE.createInternalCallAction();
        internalCallAction.setCalledResourceDemandingInternalBehaviour(resourceDemandingInternalBehaviour);
        return internalCallAction;
    }
}
