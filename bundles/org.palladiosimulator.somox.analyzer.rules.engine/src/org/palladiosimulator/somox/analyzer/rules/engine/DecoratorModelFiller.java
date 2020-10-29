package org.palladiosimulator.somox.analyzer.rules.engine;

import java.util.List;

import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

public class DecoratorModelFiller {

    public static SourceCodeDecoratorRepository fillModel(Repository repo) {
        final SourceCodeDecoratorRepository sourceCodeDecoratorRepository = SourcecodedecoratorFactory.eINSTANCE
                .createSourceCodeDecoratorRepository();

        final List<RepositoryComponent> components = repo.getComponents__Repository();
        final List<Interface> interfaces = repo.getInterfaces__Repository();

        for (final RepositoryComponent comp : components) {
            final ComponentImplementingClassesLink componentlink = SourcecodedecoratorFactory.eINSTANCE
                    .createComponentImplementingClassesLink();
            componentlink.setComponent(comp);
            sourceCodeDecoratorRepository.getComponentImplementingClassesLink().add(componentlink);
        }

        for (final Interface inter : interfaces) {
            final InterfaceSourceCodeLink interlink = SourcecodedecoratorFactory.eINSTANCE
                    .createInterfaceSourceCodeLink();
            interlink.setInterface(inter);
            sourceCodeDecoratorRepository.getInterfaceSourceCodeLink().add(interlink);
        }

        return sourceCodeDecoratorRepository;

    }
}
