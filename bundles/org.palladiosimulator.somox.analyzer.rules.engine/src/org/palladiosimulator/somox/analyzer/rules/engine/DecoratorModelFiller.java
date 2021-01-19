package org.palladiosimulator.somox.analyzer.rules.engine;

import java.util.List;

import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

/**
* This class takes a PCM repostory model and fills a SourceCodeDecoratorRepository model with it.
* Later, the decorator model can be used to extract SEFFs.
*/
public class DecoratorModelFiller {

	/**
    * Creates a SourceCodeDecoratorRepository model.
    *
    * @param  	repo the PCM repository model
    * @return	The SourceCodeDecoratorRepository model
    */
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
