package org.somox.gast2seff.visitors;

import org.emftext.language.java.members.ClassMethod;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.seff.ResourceDemandingInternalBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.somox.sourcecodedecorator.MethodLevelResourceDemandingInternalBehaviorLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

public class DefaultResourceDemandingBehaviourForClassMethodFinder
        implements ResourceDemandingBehaviourForClassMethodFinding {

    private final SourceCodeDecoratorRepository sourceCodeDecoratorRepository;
    private final BasicComponent basicComponent;

    public DefaultResourceDemandingBehaviourForClassMethodFinder(
            final SourceCodeDecoratorRepository sourceCodeDecoratorRepository, final BasicComponent basicComponent) {
        this.sourceCodeDecoratorRepository = sourceCodeDecoratorRepository;
        this.basicComponent = basicComponent;
    }

    @Override
    public ResourceDemandingInternalBehaviour getCorrespondingResourceDemandingInternalBehaviour(
            final ClassMethod classMethod) {
        for (final MethodLevelResourceDemandingInternalBehaviorLink methodLevelResourceDemandingInternalBehaviorLink : this.sourceCodeDecoratorRepository
                .getMethodLevelResourceDemandingInternalBehaviorLink()) {
            if (methodLevelResourceDemandingInternalBehaviorLink.getFunction() == classMethod) {
                if (null != methodLevelResourceDemandingInternalBehaviorLink.getResourceDemandingInternalBehaviour()) {
                    return methodLevelResourceDemandingInternalBehaviorLink.getResourceDemandingInternalBehaviour();
                }
            }
        }
        return null;
    }

    @Override
    public ResourceDemandingSEFF getCorrespondingRDSEFForClassMethod(final ClassMethod classMethod) {
        final Signature signature = this.getCorrespondingSignatureForClassMethod(classMethod);
        if (null == signature) {
            return null;
        }
        return this.findSEFFInComponentForSignature(this.basicComponent, signature);
    }

    private ResourceDemandingSEFF findSEFFInComponentForSignature(final BasicComponent basicComponent,
            final Signature signature) {
        for (final ServiceEffectSpecification seff : basicComponent.getServiceEffectSpecifications__BasicComponent()) {
            if (signature == seff.getDescribedService__SEFF()) {
                return (ResourceDemandingSEFF) seff;
            }
        }
        return null;
    }

    private Signature getCorrespondingSignatureForClassMethod(final ClassMethod classMethod) {
        for (final MethodLevelSourceCodeLink methodLevelSourceCodeLink : this.sourceCodeDecoratorRepository
                .getMethodLevelSourceCodeLink()) {
            if (methodLevelSourceCodeLink.getFunction() == classMethod) {
                final Signature signature = methodLevelSourceCodeLink.getOperation();
                if (null != signature) {
                    return signature;
                }
            }
        }
        return null;
    }

}
