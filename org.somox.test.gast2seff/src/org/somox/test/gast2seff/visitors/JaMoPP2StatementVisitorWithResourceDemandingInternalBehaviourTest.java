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
        final InternalCallAction internalCallAction =
                InternalCallActionTestHelper.createInternalCallAction(externalCallAction);
        expectedSeff.getSteps_Behaviour().add(internalCallAction);
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Override
    public void testTryBlockWithInternalCallInTryLibraryCallInCatchAndExternalCallInFinally() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final InternalCallAction ica = InternalCallActionTestHelper.createRecursiveInternalCallAction();
        expectedSeff.getSteps_Behaviour().add(ica);
        final ExternalCallAction eca = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        expectedSeff.getSteps_Behaviour().add(eca);
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Override
    public void testSwitchCaseWithInternalCallInCaseAndExternalCallDefault() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ResourceDemandingBehaviour firstCaseBehavior = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        firstCaseBehavior.getSteps_Behaviour().add(InternalCallActionTestHelper.createRecursiveInternalCallAction());
        final ResourceDemandingBehaviour secoundCaseBehavior = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        final ResourceDemandingBehaviour defaultCaseBehavior = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        defaultCaseBehavior.getSteps_Behaviour().add(this.createExternalCallAction(OPERATION_SIGNATURE_NAME));
        final BranchAction branchAction =
                this.createBranchAction(firstCaseBehavior, secoundCaseBehavior, defaultCaseBehavior);
        expectedSeff.getSteps_Behaviour().add(branchAction);
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Override
    public void testTryBlockWithExternalCallInInternalCallInTryBlock() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction eca = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        final InternalCallAction internalCallAction = InternalCallActionTestHelper.createInternalCallAction(eca);
        expectedSeff.getSteps_Behaviour().add(internalCallAction);
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Override
    public void testDoInternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final InternalCallAction internalCallAction = InternalCallActionTestHelper.createRecursiveInternalCallAction();
        expectedSeff.getSteps_Behaviour().add(internalCallAction);
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
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
        final InternalCallAction internalCallAction = InternalCallActionTestHelper
                .createInternalCallAction(internalActionInInternalCall, loopActionInInternalCall);
        expectedSeff.getSteps_Behaviour().add(internalCallAction);
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Override
    @Test
    public void testForLoopWithInternalLibraryAndExternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final InternalCallAction internalCallAction = InternalCallActionTestHelper.createRecursiveInternalCallAction();
        final InternalAction ia = this.createInternalAction();
        final ExternalCallAction eca = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);

        final LoopAction loopAction = this.createLoopAction(internalCallAction, ia, eca);
        expectedSeff.getSteps_Behaviour().add(loopAction);
        this.doMethodTestGastStatementVisitor(super.getTestMethodName(), expectedSeff);
    }

    @Override
    @Test
    public void testForLoopWithInternalCallContainingExternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction eca = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        final InternalCallAction internalCallAction = InternalCallActionTestHelper.createInternalCallAction(eca);

        final LoopAction loopAction = this.createLoopAction(internalCallAction);
        expectedSeff.getSteps_Behaviour().add(loopAction);
        this.doMethodTestGastStatementVisitor(super.getTestMethodName(), expectedSeff);
    }

    @Override
    @Test
    public void testConditionWithExternalCallInIf() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();

        final ResourceDemandingBehaviour ifBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        ifBehaviour.getSteps_Behaviour().add(this.createExternalCallAction(OPERATION_SIGNATURE_NAME));
        final ResourceDemandingBehaviour elseBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        final InternalCallAction ica = InternalCallActionTestHelper
                .createInternalCallAction(InternalCallActionTestHelper.createRecursiveInternalCallAction());
        elseBehaviour.getSteps_Behaviour().add(ica);
        final BranchAction branchAction = this.createBranchAction(ifBehaviour, elseBehaviour);

        expectedSeff.getSteps_Behaviour().add(branchAction);
        this.doMethodTestGastStatementVisitor(super.getTestMethodName(), expectedSeff);
    }

    @Override
    @Test
    public void testConditionWithExternalCallInElse() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();

        final ResourceDemandingBehaviour ifBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        final InternalCallAction ica = InternalCallActionTestHelper
                .createInternalCallAction(InternalCallActionTestHelper.createRecursiveInternalCallAction());
        ifBehaviour.getSteps_Behaviour().add(ica);
        final ResourceDemandingBehaviour elseBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        elseBehaviour.getSteps_Behaviour().add(this.createExternalCallAction(OPERATION_SIGNATURE_NAME));
        final BranchAction branchAction = this.createBranchAction(ifBehaviour, elseBehaviour);

        expectedSeff.getSteps_Behaviour().add(branchAction);
        this.doMethodTestGastStatementVisitor(super.getTestMethodName(), expectedSeff);
    }

    @Override
    @Test
    public void testInternalCallAsInputForExternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final InternalCallAction internalCallAction1 =
                InternalCallActionTestHelper.createInternalCallAction(this.createInternalAction());
        final InternalCallAction internalCallAction2 =
                InternalCallActionTestHelper.createInternalCallAction(this.createInternalAction());
        expectedSeff.getSteps_Behaviour().add(internalCallAction1);
        expectedSeff.getSteps_Behaviour().add(internalCallAction2);
        final ExternalCallAction externalCallAction =
                this.createExternalCallAction(TEST_EXTERNAL_CALL_WITH_SIMPLE_PARAMETERS_AND_RETURN_TYPE);
        expectedSeff.getSteps_Behaviour().add(externalCallAction);
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Override
    @Test
    public void testExternalCallAsInputForInternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCallAction = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        final InternalCallAction internalCallAction =
                InternalCallActionTestHelper.createInternalCallAction(this.createInternalAction());
        expectedSeff.getSteps_Behaviour().add(externalCallAction);
        expectedSeff.getSteps_Behaviour().add(internalCallAction);

        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    private LoopAction createLoopAction(final AbstractAction... expectedLoopActions) {
        final LoopAction loopAction = SeffFactory.eINSTANCE.createLoopAction();
        final ResourceDemandingBehaviour loopBehavior = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        loopBehavior.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStartAction());
        loopAction.setBodyBehaviour_Loop(loopBehavior);
        for (final AbstractAction expectedLoopAction : expectedLoopActions) {
            loopAction.getBodyBehaviour_Loop().getSteps_Behaviour().add(expectedLoopAction);
        }
        loopBehavior.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
        loopAction.setEntityName("expectedLoopAction");
        return loopAction;
    }

    private BranchAction createBranchAction(final ResourceDemandingBehaviour... caseBehaviours) {
        final BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
        for (final ResourceDemandingBehaviour caseBehaviour : caseBehaviours) {
            caseBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
            caseBehaviour.getSteps_Behaviour().add(0, SeffFactory.eINSTANCE.createStartAction());
            final ProbabilisticBranchTransition branchTransition =
                    SeffFactory.eINSTANCE.createProbabilisticBranchTransition();
            branchTransition.setBranchBehaviour_BranchTransition(caseBehaviour);
            branchTransition.setEntityName("branch transition");
            branchAction.getBranches_Branch().add(branchTransition);
        }
        branchAction.setEntityName("expectedBranchAction");
        return branchAction;
    }

}
