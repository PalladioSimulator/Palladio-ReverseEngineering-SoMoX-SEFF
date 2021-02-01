package org.annotationsmox.seffhelper.functionclassification;

import org.palladiosimulator.pcm.repository.BasicComponent;
import org.somox.gast2seff.visitors.IFunctionClassificationStrategy;
import org.somox.gast2seff.visitors.IFunctionClassificationStrategyFactory;
import org.somox.gast2seff.visitors.MethodCallFinder;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

public class AnnotationsMoxFunctionClassificationStrategyFactory implements IFunctionClassificationStrategyFactory {

    @Override
    public IFunctionClassificationStrategy createIFunctionClassificationStrategy(
            final SourceCodeDecoratorRepository sourceCodeDecoratorRepository, final BasicComponent primitiveComponent,
            final Root root, final MethodCallFinder methodCallFinder) {
        return new EJBFunctionClassificationStrategy(sourceCodeDecoratorRepository, primitiveComponent, root,
                methodCallFinder);
    }
}
