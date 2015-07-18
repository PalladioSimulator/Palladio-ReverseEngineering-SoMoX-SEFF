package org.somox.gast2seff.visitors;

import org.apache.log4j.Logger;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.Signature;

public class DefaultInterfaceOfExternalCallFinder implements InterfaceOfExternalCallFinding {

    private static final Logger logger = Logger.getLogger(DefaultInterfaceOfExternalCallFinder.class.getSimpleName());

    private final SourceCodeDecoratorRepository sourceCodeDecoratorRepository;
    private final BasicComponent basicComponent;

    public DefaultInterfaceOfExternalCallFinder(final SourceCodeDecoratorRepository sourceCodeDecoratorRepository,
            final BasicComponent basicComponent) {
        this.sourceCodeDecoratorRepository = sourceCodeDecoratorRepository;
        this.basicComponent = basicComponent;
    }

    /**
     * Query the interface port for the function access using the source code decorator.
     *
     * @param calledMethod
     *            The access to find in the PCM
     * @return interface port and operation for corresponding to the access.
     */
    @Override
    public InterfacePortOperationTuple getCalledInterfacePort(final Method calledMethod) { // GAST2SEFFCHANGE
        final InterfacePortOperationTuple interfacePortOperationTuple = new InterfacePortOperationTuple();
        final ConcreteClassifier accessedConcreteClassifier = calledMethod.getContainingConcreteClassifier();

        for (final RequiredRole requiredRole : this.basicComponent.getRequiredRoles_InterfaceRequiringEntity()) {
            for (final InterfaceSourceCodeLink ifLink : this.sourceCodeDecoratorRepository.getInterfaceSourceCodeLink()) {
                if (requiredRole instanceof OperationRequiredRole) {
                    final OperationRequiredRole operReqRole = (OperationRequiredRole) requiredRole;
                    if (operReqRole.getRequiredInterface__OperationRequiredRole().equals(ifLink.getInterface())) {
                        final ConcreteClassifier gastClass = ifLink.getGastClass();
                        if (gastClass.equals(accessedConcreteClassifier)) {

                            logger.trace("accessed interface port " + operReqRole.getEntityName());
                            interfacePortOperationTuple.role = operReqRole;
                            // query operation:
                            interfacePortOperationTuple.signature = this.queryInterfaceOperation(calledMethod);

                            return interfacePortOperationTuple;
                        }
                    }
                }
            }
        }
        logger.warn("found no if port for " + accessedConcreteClassifier);
        return interfacePortOperationTuple;
    }

    /**
     * Signature query
     *
     * @param invokedMethod
     *            The method invocation to find in the SAMM
     * @return Signature corresponding to function access
     */
    private Signature queryInterfaceOperation(final Method invokedMethod) { // GAST2SEFFCHANGE
        for (final MethodLevelSourceCodeLink methodLink : this.sourceCodeDecoratorRepository
                .getMethodLevelSourceCodeLink()) {

            final Member methodSourceCodeDecorator = methodLink.getFunction();
            if (methodSourceCodeDecorator.equals(invokedMethod)) { // GAST2SEFFCHANGE

                logger.trace("accessed operation " + methodLink.getOperation().getEntityName());
                return methodLink.getOperation();
            }
        }

        logger.warn("no accessed operation found for " + invokedMethod.getContainingConcreteClassifier() + "::"
                + invokedMethod.getName()); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
        return null;
    }

}
