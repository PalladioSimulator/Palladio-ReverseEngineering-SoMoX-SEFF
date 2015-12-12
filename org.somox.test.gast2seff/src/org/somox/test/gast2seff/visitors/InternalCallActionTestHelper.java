package org.somox.test.gast2seff.visitors;

import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.InternalCallAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingInternalBehaviour;
import org.palladiosimulator.pcm.seff.SeffFactory;

public class InternalCallActionTestHelper {

    public static InternalCallAction createRecursiveInternalCallAction() {
        return createInternalCallAction(true);
    }

    public static InternalCallAction createInternalCallAction(
            final AbstractAction... expectedBehaviorOfResourceInternalBehaviour) {
        return createInternalCallAction(false, expectedBehaviorOfResourceInternalBehaviour);
    }

    public static InternalCallAction createInternalCallAction(final boolean recursiveCallsItselfOnly,
            final AbstractAction... expectedBehaviorOfResourceInternalBehaviour) {
        final ResourceDemandingInternalBehaviour resourceDemandingInternalBehaviour =
                SeffFactory.eINSTANCE.createResourceDemandingInternalBehaviour();
        resourceDemandingInternalBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStartAction());
        if (recursiveCallsItselfOnly) {
            final InternalCallAction recursiveInternalCallAction = SeffFactory.eINSTANCE.createInternalCallAction();
            recursiveInternalCallAction.setCalledResourceDemandingInternalBehaviour(resourceDemandingInternalBehaviour);
            resourceDemandingInternalBehaviour.getSteps_Behaviour().add(recursiveInternalCallAction);
        } else if (null != expectedBehaviorOfResourceInternalBehaviour) {
            for (final AbstractAction abstractAction : expectedBehaviorOfResourceInternalBehaviour) {
                resourceDemandingInternalBehaviour.getSteps_Behaviour().add(abstractAction);
            }
        }
        resourceDemandingInternalBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
        final InternalCallAction internalCallAction = SeffFactory.eINSTANCE.createInternalCallAction();
        internalCallAction.setCalledResourceDemandingInternalBehaviour(resourceDemandingInternalBehaviour);
        return internalCallAction;
    }

}
