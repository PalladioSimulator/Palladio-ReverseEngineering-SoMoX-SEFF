package org.somox.test.gast2seff.visitors;

import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.SeffFactory;

public class SEFFCreationHelper {

    public static void createAndAddInternalActionToSeff(final ResourceDemandingBehaviour expectedSeff) {
        final InternalAction internalAction = SEFFCreationHelper.createInternalAction();
        expectedSeff.getSteps_Behaviour().add(internalAction);
    }

    public static InternalAction createInternalAction() {
        final InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();
        internalAction.setEntityName("expectedInternalAction");
        return internalAction;
    }

    public static ExternalCallAction createExternalCallAction(final OperationRequiredRole operationRequiredRole,
            final OperationSignature operationSignature) {
        final ExternalCallAction externalCall = SeffFactory.eINSTANCE.createExternalCallAction();
        externalCall.setRole_ExternalService(operationRequiredRole);
        externalCall.setCalledService_ExternalService(operationSignature);
        return externalCall;
    }

    public static OperationRequiredRole findOperaitonRequiredRoleInBasicComponent(final BasicComponent basicComponent,
            final String requiredRoleName) {
        for (final RequiredRole requiredRole : basicComponent.getRequiredRoles_InterfaceRequiringEntity()) {
            if (requiredRole.getEntityName().equals(requiredRoleName)
                    && requiredRole instanceof OperationRequiredRole) {
                return (OperationRequiredRole) requiredRole;
            }
        }
        throw new RuntimeException("Could not find OperationRequiredRole " + requiredRoleName + " in BasicComponent "
                + basicComponent.getEntityName());
    }

    public static OperationInterface findOperationInterfaceWithName(final String interfaceName, final Repository repository) {
        for (final Interface opInterface : repository.getInterfaces__Repository()) {
            if (opInterface instanceof OperationInterface && interfaceName.equals(opInterface.getEntityName())) {
                return (OperationInterface) opInterface;
            }
        }
        throw new RuntimeException("Could not find OperationInterface " + interfaceName);
    }

    public static RepositoryComponent findComponentInPCMRepository(final String componentName, Repository repository) {
        for (final RepositoryComponent repoComp : repository.getComponents__Repository()) {
            if (componentName.equals(repoComp.getEntityName())) {
                return repoComp;
            }
        }
        throw new RuntimeException("Could not find RepositoryComponent " + componentName);
    }

    public static OperationSignature findOperationSignatureInInterface(final String methodName,
            final OperationInterface opInterface) {
        for (final OperationSignature opSig : opInterface.getSignatures__OperationInterface()) {
            if (methodName.equals(opSig.getEntityName())) {
                return opSig;
            }
        }
        throw new RuntimeException("Could not find OperationSignature " + methodName + " in OperationInterface "
                + opInterface.getEntityName());
    }

}
