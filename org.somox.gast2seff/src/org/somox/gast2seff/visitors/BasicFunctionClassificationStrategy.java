/**
 *
 */
package org.somox.gast2seff.visitors;

import java.util.List;

import org.apache.log4j.Logger;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferenceableElement;
import org.somox.kdmhelper.EqualityChecker;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

import de.uka.ipd.sdq.pcm.repository.BasicComponent;

/**
 * Implementation of {@link IFunctionClassificationStrategy}. Uses basic heuristics based on the
 * source code decorator and the GAST model to decide on the type of function calls.
 *
 * @author Steffen Becker, Klaus Krogmann
 *
 */
public class BasicFunctionClassificationStrategy extends AbstractFunctionClassificationStrategy implements
        IFunctionClassificationStrategy {

    private static Logger logger = Logger.getLogger(BasicFunctionClassificationStrategy.class);

    private final SourceCodeDecoratorRepository sourceCodeDecoratorRepository;
    private final BasicComponent primitiveComponent;
    private final Root root;

    /**
     * @param sourceCodeDecoratorRepository
     *            The source code decorator which links the component for which to classify
     *            statements and the GAST.
     * @param primitiveComponent
     *            The primitive component for which to decide whether the function access represents
     *            an external call.
     */
    public BasicFunctionClassificationStrategy(final SourceCodeDecoratorRepository sourceCodeDecoratorRepository,
            final BasicComponent primitiveComponent, final Root root) {
        this.sourceCodeDecoratorRepository = sourceCodeDecoratorRepository;
        this.primitiveComponent = primitiveComponent;
        this.root = root;
    }

    @Override
    protected boolean isExternalCall(final MethodCall methodCall) {
        final ComponentImplementingClassesLink compLink = this.queryComponentLink(this.primitiveComponent);
        final Method method = KDMHelper.getMethod(methodCall);
        for (final InterfaceSourceCodeLink ifLink : compLink.getRequiredInterfaces()) {
            final List<Method> methodsInInterface = KDMHelper.getMethods(ifLink.getGastClass());
            for (final Method methodInInterface : methodsInInterface) {
                if (EqualityChecker.areFunctionsEqual(method, methodInInterface)) {
                    logger.debug("Classified call as external call: " + KDMHelper.getMethod(methodCall).getName()
                            + " for component " + this.primitiveComponent.getEntityName());
                    return true;
                }
            }
        }

        logger.trace("no external call: " + KDMHelper.getMethod(methodCall).getName());
        return false;
    }

    private ComponentImplementingClassesLink queryComponentLink(final BasicComponent primitiveComponent) {
        for (final ComponentImplementingClassesLink compLink : this.sourceCodeDecoratorRepository
                .getComponentImplementingClassesLink()) {
            if (compLink.getComponent().equals(primitiveComponent)) {
                return compLink;
            }
        }
        final String msg = "Could not find a component implementing classes link in the source code "
                + "decorator for component " + primitiveComponent;
        logger.error(msg);
        throw new RuntimeException(msg);
    }

    /**
     * Checks whether the method call is a access to a library. This is the case if the compilation
     * unit of the target method is not in the root
     */
    @Override
    protected boolean isLibraryCall(final MethodCall methodCall) {
        final ReferenceableElement method = methodCall.getTarget();
        final CompilationUnit compilationUnit = method.getContainingCompilationUnit();
        final boolean isLibraryCall = !this.root.getCompilationUnits().contains(compilationUnit);
        if (isLibraryCall) {
            logger.debug("Classified call as library call: " + method.getName() + " for component "
                    + this.primitiveComponent.getEntityName());
        }
        return isLibraryCall;
    }

}
