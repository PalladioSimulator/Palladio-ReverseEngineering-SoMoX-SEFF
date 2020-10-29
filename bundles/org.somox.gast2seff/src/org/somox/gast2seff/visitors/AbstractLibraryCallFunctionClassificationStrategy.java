package org.somox.gast2seff.visitors;

import org.apache.log4j.Logger;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Method;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * Class that defines how library calls are marked but not how external calls are marked.
 *
 * @author Michael Langhammer
 *
 */
public abstract class AbstractLibraryCallFunctionClassificationStrategy extends AbstractFunctionClassificationStrategy {

    static Logger logger = Logger.getLogger(AbstractLibraryCallFunctionClassificationStrategy.class);

    protected final Root root;

    public AbstractLibraryCallFunctionClassificationStrategy(final Root root,
            final SourceCodeDecoratorRepository sourceCodeDecoratorRepository,
            final MethodCallFinder methodCallFinder) {
        super(methodCallFinder);
        this.root = root;
    }

    /**
     * Checks whether the method call is a access to a library. This is the case if the compilation
     * unit of the target method is not in the root
     */
    @Override
    protected boolean isLibraryCall(final Method method) {
        final CompilationUnit compilationUnit = method.getContainingCompilationUnit();
        final boolean isLibraryCall = !this.root.getCompilationUnits().contains(compilationUnit);
        if (isLibraryCall) {
            logger.debug("Classified call as library call: " + method.getName());
            // + " for component " + this.primitiveComponent.getEntityName());
        }
        return isLibraryCall;
    }
}
