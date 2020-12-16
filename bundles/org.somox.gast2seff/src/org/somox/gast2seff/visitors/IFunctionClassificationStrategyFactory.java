package org.somox.gast2seff.visitors;

import org.palladiosimulator.pcm.repository.BasicComponent;
import org.somox.gast2seff.jobs.GAST2SEFFJob;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * Factory that allows the creation of FunctionClassificationStrategy. The interface allows to
 * inject custom FunctionClassificationStrategies into the {@link GAST2SEFFJob}. Per default a
 * {@link BasicFunctionClassificationStrategy} is created.
 *
 * @author langhamm
 *
 */
public interface IFunctionClassificationStrategyFactory {
    default IFunctionClassificationStrategy createIFunctionClassificationStrategy(
            final SourceCodeDecoratorRepository sourceCodeDecoratorRepository, final BasicComponent primitiveComponent,
            final Root root, final MethodCallFinder methodCallFinder) {
        return new BasicFunctionClassificationStrategy(sourceCodeDecoratorRepository, primitiveComponent, root,
                methodCallFinder);
    }
}
