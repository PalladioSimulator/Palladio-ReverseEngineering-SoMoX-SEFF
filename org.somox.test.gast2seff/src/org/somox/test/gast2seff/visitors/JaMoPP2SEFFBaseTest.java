package org.somox.test.gast2seff.visitors;

import org.apache.log4j.Logger;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Method;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

public abstract class JaMoPP2SEFFBaseTest extends JaMoPP2PCMBaseTest {

    static final Logger logger = Logger.getLogger(JaMoPP2SEFFBaseTest.class.getSimpleName());

    protected static final String TEST_DO_EXTERNAL_CALL = "testDoExternalCall";

    protected static final String TEST_DO_INTERNAL_CALL = "testDoInternalCall";

    protected static final String TEST_DO_LIBRARY_CALL = "testDoLibraryCall";

    OperationSignature findOperationSignatureWithName(final String methodName, final String interfaceName) {
        final OperationInterface opInterface = SEFFCreationHelper.findOperationInterfaceWithName(interfaceName,
                this.pcmRepository);
        return SEFFCreationHelper.findOperationSignatureInInterface(methodName, opInterface);
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
        return SEFFCreationHelper.findComponentInPCMRepository(componentName, this.pcmRepository);
    }

    private ConcreteClassifier findClassForComponent(final String providingCompName) {
        final ConcreteClassifier classifier = this.findConcreteClassifierWithName(providingCompName + "Impl");
        return classifier;
    }

    InterfaceSourceCodeLink createAndAddInterface2InterfaceCorrespondence(final String interfaceName) {
        final InterfaceSourceCodeLink interfaecLink = SourcecodedecoratorFactory.eINSTANCE
                .createInterfaceSourceCodeLink();
        interfaecLink.setGastClass(this.findConcreteClassifierWithName(interfaceName));
        interfaecLink
                .setInterface(SEFFCreationHelper.findOperationInterfaceWithName(interfaceName, this.pcmRepository));
        this.sourceCodeDecorator.getInterfaceSourceCodeLink().add(interfaecLink);
        return interfaecLink;
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
