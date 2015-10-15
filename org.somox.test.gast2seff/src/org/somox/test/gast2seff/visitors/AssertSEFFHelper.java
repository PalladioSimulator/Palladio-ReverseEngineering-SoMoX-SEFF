package org.somox.test.gast2seff.visitors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.InternalCallAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;

public class AssertSEFFHelper {

    private AssertSEFFHelper() {

    }

    public static void assertSeffEquals(final ResourceDemandingBehaviour seff,
            final ResourceDemandingBehaviour expectedSEFF) {
        final List<AbstractAction> abstractActions = seff.getSteps_Behaviour();
        final List<AbstractAction> expectedAbstractActions = expectedSEFF.getSteps_Behaviour();
        assertEquals("Seff should have the same number of actions as expected", expectedAbstractActions.size(),
                abstractActions.size());

        for (int i = 0; i < abstractActions.size(); i++) {
            final AbstractAction abstractAction = abstractActions.get(i);
            final AbstractAction expectedAbstractAction = expectedAbstractActions.get(i);
            assertEquals(
                    "Abstract action " + abstractAction + " should have the expected class "
                            + expectedAbstractAction.getClass(),
                    abstractAction.getClass(), expectedAbstractAction.getClass());
            if (abstractAction instanceof ExternalCallAction) {
                assertExternalCallActionEquals((ExternalCallAction) abstractAction,
                        (ExternalCallAction) expectedAbstractAction);
            } else if (abstractAction instanceof InternalCallAction) {
                assertInternalCallActionEquals((InternalCallAction) abstractAction,
                        (InternalCallAction) expectedAbstractAction);
            } else if (abstractAction instanceof InternalAction) {
                assertInternalAction((InternalAction) abstractAction, (InternalAction) expectedAbstractAction);
            } else if (abstractAction instanceof BranchAction) {
                assertBranchAction((BranchAction) abstractAction, (BranchAction) expectedAbstractAction);
            } else if (abstractAction instanceof StartAction || abstractAction instanceof StopAction) {
                // no need to check anything else
            } else if (abstractAction instanceof LoopAction) {
                assertLoopAction((LoopAction) abstractAction, (LoopAction) expectedAbstractAction);
            } else {
                fail("Can not asssert AbstractAction " + abstractAction);
            }
        }

    }

    private static void assertLoopAction(final LoopAction loopAction, final LoopAction expectedloopAction) {
        final ResourceDemandingBehaviour resourceDemandingBehaviour = loopAction.getBodyBehaviour_Loop();
        final ResourceDemandingBehaviour expectedResourceDemandingBehaviour = expectedloopAction
                .getBodyBehaviour_Loop();
        assertSeffEquals(resourceDemandingBehaviour, expectedResourceDemandingBehaviour);
    }

    private static void assertBranchAction(final BranchAction branchAction, final BranchAction expectedBranchAction) {
        assertEquals("BranchActions should have the same size", expectedBranchAction.getBranches_Branch().size(),
                branchAction.getBranches_Branch().size());
        for (int i = 0; i < branchAction.getBranches_Branch().size(); i++) {
            final AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(i);
            final AbstractBranchTransition expectedBranchTransition = expectedBranchAction.getBranches_Branch().get(i);
            assertEquals("Branch transitions must have the same class", expectedBranchTransition.getClass(),
                    branchTransition.getClass());
            final ResourceDemandingBehaviour behaviour = branchTransition.getBranchBehaviour_BranchTransition();
            final ResourceDemandingBehaviour expectedBehaviour = expectedBranchTransition
                    .getBranchBehaviour_BranchTransition();
            assertSeffEquals(behaviour, expectedBehaviour);
        }
    }

    private static void assertInternalAction(final InternalAction abstractAction,
            final InternalAction expectedAbstractAction) {
        // nothing to compare for internal action.
    }

    private static void assertInternalCallActionEquals(final InternalCallAction abstractAction,
            final InternalCallAction expectedAbstractAction) {
        final ResourceDemandingBehaviour calledResourceDemandingInternalBehaviour = abstractAction
                .getCalledResourceDemandingInternalBehaviour();
        final ResourceDemandingBehaviour expectedCalledResourceDemandingInternalBehaviour = expectedAbstractAction
                .getCalledResourceDemandingInternalBehaviour();
        if (!isRecursiveCall(abstractAction, calledResourceDemandingInternalBehaviour, expectedAbstractAction,
                expectedCalledResourceDemandingInternalBehaviour)) {
            assertSeffEquals(calledResourceDemandingInternalBehaviour,
                    expectedCalledResourceDemandingInternalBehaviour);
        }
    }

    private static boolean isRecursiveCall(final InternalCallAction abstractAction,
            final ResourceDemandingBehaviour calledResourceDemandingInternalBehaviour,
            final InternalCallAction expectedAbstractAction,
            final ResourceDemandingBehaviour expectedCalledResourceDemandingInternalBehaviour) {
        final boolean firstIsRecursive = isRecursiveCall(abstractAction, calledResourceDemandingInternalBehaviour);
        final boolean secondIsRecursive = isRecursiveCall(expectedAbstractAction,
                expectedCalledResourceDemandingInternalBehaviour);
        assertEquals("The InternalCallActions " + abstractAction + " and " + expectedAbstractAction
                + " both have to be recursive or not", firstIsRecursive, secondIsRecursive);
        return firstIsRecursive;
    }

    private static boolean isRecursiveCall(final InternalCallAction abstractAction,
            final ResourceDemandingBehaviour calledResourceDemandingInternalBehaviour) {
        return abstractAction
                .getResourceDemandingBehaviour_AbstractAction() == calledResourceDemandingInternalBehaviour;
    }

    private static void assertExternalCallActionEquals(final ExternalCallAction externalCallAction,
            final ExternalCallAction expectedExternalCallAction) {
        assertEquals("Role of external actions is not the same",
                expectedExternalCallAction.getRole_ExternalService().getId(),
                externalCallAction.getRole_ExternalService().getId());
        assertEquals("Call service of external call actions is not the same",
                expectedExternalCallAction.getCalledService_ExternalService().getId(),
                externalCallAction.getCalledService_ExternalService().getId());
    }

}
