/**
 *
 */
package org.somox.gast2seff.visitors;

import java.util.List;

import org.apache.log4j.Logger;
import org.emftext.language.java.members.Method;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.somox.kdmhelper.EqualityChecker;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * Implementation of {@link IFunctionClassificationStrategy}. Uses basic heuristics based on the
 * source code decorator and the GAST model to decide on the type of function calls.
 *
 * @author Steffen Becker, Klaus Krogmann
 *
 */
public class BasicFunctionClassificationStrategy extends AbstractLibraryCallFunctionClassificationStrategy
        implements IFunctionClassificationStrategy {

    static Logger logger = Logger.getLogger(BasicFunctionClassificationStrategy.class);

    final private BasicComponent primitiveComponent;

    protected final SourceCodeDecoratorRepository sourceCodeDecoratorRepository;

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
        super(root, sourceCodeDecoratorRepository);
        this.sourceCodeDecoratorRepository = sourceCodeDecoratorRepository;
        this.primitiveComponent = primitiveComponent;
    }

    @Override
    protected boolean isExternalCall(final Method method) {
        final ComponentImplementingClassesLink compLink = this.queryComponentLink(this.primitiveComponent);
        if (null == compLink) {
            return false;
        }
        for (final InterfaceSourceCodeLink ifLink : compLink.getRequiredInterfaces()) {
            final List<Method> methodsInInterface = KDMHelper.getMethods(ifLink.getGastClass());
            for (final Method methodInInterface : methodsInInterface) {
                if (EqualityChecker.areFunctionsEqual(method, methodInInterface)) {
                    logger.debug("Classified call as external call: " + method.getName() + " for component "
                            + this.primitiveComponent.getEntityName());
                    return true;
                }
            }
        }

        logger.trace("no external call: " + method.getName());
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
        logger.warn(msg);
        return null;
    }

}
