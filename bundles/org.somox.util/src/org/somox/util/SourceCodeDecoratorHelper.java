package org.somox.util;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.types.Type;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.Signature;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

public class SourceCodeDecoratorHelper {

    private final SourceCodeDecoratorRepository sourceCodeDecorator;

    public SourceCodeDecoratorHelper(final SourceCodeDecoratorRepository sourceCodeDecoratorRepository) {
        this.sourceCodeDecorator = sourceCodeDecoratorRepository;
    }

    public void createComponentImplementingClassesLink(final BasicComponent basicComponent, final Class jaMoPPClass) {
        final ComponentImplementingClassesLink componentImplementingClassLink = SourcecodedecoratorFactory.eINSTANCE
                .createComponentImplementingClassesLink();
        componentImplementingClassLink.setComponent(basicComponent);
        componentImplementingClassLink.getImplementingClasses().add(jaMoPPClass);
        this.sourceCodeDecorator.getComponentImplementingClassesLink().add(componentImplementingClassLink);
    }

    public void createInterfaceSourceCodeLink(final Interface pcmInterface,
            final ConcreteClassifier jaMoPPInterface) {
        final InterfaceSourceCodeLink interfaceSourceCodeLink = SourcecodedecoratorFactory.eINSTANCE
                .createInterfaceSourceCodeLink();
        interfaceSourceCodeLink.setInterface(pcmInterface);
        interfaceSourceCodeLink.setGastClass(jaMoPPInterface);
        this.sourceCodeDecorator.getInterfaceSourceCodeLink().add(interfaceSourceCodeLink);
    }

    public <T> T findPCMInterfaceForJaMoPPType(final Type type, java.lang.Class<T> interfaceClass) {
        final InterfaceSourceCodeLink ifSourceCodeLink = this.sourceCodeDecorator.getInterfaceSourceCodeLink()
                .stream()
                .filter(interfaceSourceCodeLink -> null != interfaceSourceCodeLink.getGastClass()
                        && null != interfaceSourceCodeLink.getInterface()
                        && interfaceSourceCodeLink.getGastClass().equals(type)
                        && interfaceClass.isInstance(interfaceSourceCodeLink.getInterface()))
                .findAny().orElse(null);

        return null == ifSourceCodeLink ? null : interfaceClass.cast(ifSourceCodeLink.getInterface());
    }

	public void createMethodLevelSourceCodeLink(final Signature signature, final Member jaMoPPMember) {
		final MethodLevelSourceCodeLink metodLevelLink = SourcecodedecoratorFactory.eINSTANCE
				.createMethodLevelSourceCodeLink();
		metodLevelLink.setFile(jaMoPPMember.getContainingCompilationUnit());
		metodLevelLink.setFunction(jaMoPPMember);
		metodLevelLink.setOperation(signature);
		this.sourceCodeDecorator.getMethodLevelSourceCodeLink().add(metodLevelLink);
	}

}
