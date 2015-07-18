package org.somox.test.gast2seff.visitors;

import org.apache.log4j.Logger;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Method;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RequiredRole;

public abstract class JaMoPP2SEFFBaseTest extends JaMoPP2PCMBaseTest {

    static final Logger logger = Logger.getLogger(JaMoPP2SEFFBaseTest.class.getSimpleName());

    protected static final String TEST_DO_EXTERNAL_CALL = "testDoExternalCall";

    protected static final String TEST_DO_INTERNAL_CALL = "testDoInternalCall";

    protected static final String TEST_DO_LIBRARY_CALL = "testDoLibraryCall";

    OperationSignature findOperationSignatureWithName(final String methodName, final String interfaceName) {
        final OperationInterface opInterface = this.findOperationInterfaceWithName(interfaceName);
        for (final OperationSignature opSig : opInterface.getSignatures__OperationInterface()) {
            if (methodName.equals(opSig.getEntityName())) {
                return opSig;
            }
        }
        throw new RuntimeException("Could not find OperationSignature " + methodName + " in OperationInterface "
                + interfaceName);
    }

    @Override
    protected Method findMethodInClassifier(final String methodName, final String classifierName) {
        final ConcreteClassifier classifier = this.findConcreteClassifierWithName(classifierName);
        for (final Method method : classifier.getMethods()) {
            if (methodName.equals(method.getName())) {
                return method;
            }
        }
        throw new RuntimeException("Could not find Method " + methodName + " in Classifier " + classifierName);
    }

    ComponentImplementingClassesLink createAndAddComponentImplementingClassLink(final String componentName,
            final InterfaceSourceCodeLink providedInterface, final InterfaceSourceCodeLink requiredInterfacs) {
        final ComponentImplementingClassesLink componentImplementingClassLink = SourcecodedecoratorFactory.eINSTANCE
                .createComponentImplementingClassesLink();
        componentImplementingClassLink.setComponent(this.findComponentInPCMRepo(componentName));
        componentImplementingClassLink.getImplementingClasses().add(this.findClassForComponent(componentName));
        if (null != providedInterface) {
            componentImplementingClassLink.getProvidedInterfaces().add(providedInterface);
        }
        if (null != requiredInterfacs) {
            componentImplementingClassLink.getRequiredInterfaces().add(requiredInterfacs);
        }
        this.sourceCodeDecorator.getComponentImplementingClassesLink().add(componentImplementingClassLink);
        return componentImplementingClassLink;
    }

    @Override
    protected RepositoryComponent findComponentInPCMRepo(final String componentName) {
        for (final RepositoryComponent repoComp : this.pcmRepository.getComponents__Repository()) {
            if (componentName.equals(repoComp.getEntityName())) {
                return repoComp;
            }
        }
        throw new RuntimeException("Could not find RepositoryComponent " + componentName);
    }

    private ConcreteClassifier findClassForComponent(final String providingCompName) {
        final ConcreteClassifier classifier = this.findConcreteClassifierWithName(providingCompName + "Impl");
        return classifier;
    }

    InterfaceSourceCodeLink createAndAddInterface2InterfaceCorrespondence(final String interfaceName) {
        final InterfaceSourceCodeLink interfaecLink = SourcecodedecoratorFactory.eINSTANCE
                .createInterfaceSourceCodeLink();
        interfaecLink.setGastClass(this.findConcreteClassifierWithName(interfaceName));
        interfaecLink.setInterface(this.findOperationInterfaceWithName(interfaceName));
        this.sourceCodeDecorator.getInterfaceSourceCodeLink().add(interfaecLink);
        return interfaecLink;
    }

    private OperationInterface findOperationInterfaceWithName(final String interfaceName) {
        for (final Interface opInterface : this.pcmRepository.getInterfaces__Repository()) {
            if (opInterface instanceof OperationInterface && interfaceName.equals(opInterface.getEntityName())) {
                return (OperationInterface) opInterface;
            }
        }
        throw new RuntimeException("Could not find OperationInterface " + interfaceName);
    }

    private ConcreteClassifier findConcreteClassifierWithName(final String concreteClassifierName) {
        for (final CompilationUnit cu : compilationUnits.getCompilationUnits()) {
            for (final ConcreteClassifier concreteClassifier : cu.getClassifiers()) {
                if (concreteClassifierName.equals(concreteClassifier.getName())) {
                    return concreteClassifier;
                }
            }
        }
        throw new RuntimeException("Could not find ConcreteClassifier " + concreteClassifierName);
    }

    /**
     * Copied from:
     * http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method
     * Get the method name for the calling method of the getMethodMethod.
     *
     * @return method name
     */
    @Override
    protected String getTestMethodName() {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[2].getMethodName();
    }

    public OperationRequiredRole findOperaitonRequiredRoleInBasicComponent(final BasicComponent basicComponent,
            final String requiredRoleName) {
        for (final RequiredRole requiredRole : basicComponent.getRequiredRoles_InterfaceRequiringEntity()) {
            if (requiredRole.getEntityName().equals(requiredRoleName) && requiredRole instanceof OperationRequiredRole) {
                return (OperationRequiredRole) requiredRole;
            }
        }
        throw new RuntimeException("Could not find OperationRequiredRole " + requiredRoleName + " in BasicComponent "
                + basicComponent.getEntityName());
    }

    public OperationSignature findRequiredOperationSignatureInOperationRequiredRole(
            final OperationRequiredRole operationRequiredRole, final String operationSignatureName) {
        final OperationInterface requiredInterface = operationRequiredRole
                .getRequiredInterface__OperationRequiredRole();
        for (final OperationSignature opSig : requiredInterface.getSignatures__OperationInterface()) {
            if (opSig.getEntityName().equals(operationSignatureName)) {
                return opSig;
            }
        }
        throw new RuntimeException("Could not find OperationSignature " + operationSignatureName + " in Interface "
                + requiredInterface.getEntityName() + " for required role " + operationRequiredRole.getEntityName());
    }

}
