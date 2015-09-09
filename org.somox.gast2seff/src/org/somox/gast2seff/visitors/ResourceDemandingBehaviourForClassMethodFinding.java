package org.somox.gast2seff.visitors;

import org.emftext.language.java.members.ClassMethod;
import org.palladiosimulator.pcm.seff.ResourceDemandingInternalBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;

public interface ResourceDemandingBehaviourForClassMethodFinding {
    ResourceDemandingSEFF getCorrespondingRDSEFForClassMethod(final ClassMethod classMethod);

    ResourceDemandingInternalBehaviour getCorrespondingResourceDemandingInternalBehaviour(
            final ClassMethod classMethod);
}
