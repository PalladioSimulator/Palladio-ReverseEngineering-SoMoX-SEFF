/**
 *
 */
package org.somox.gast2seff.visitors;

import org.apache.log4j.Logger;
import org.emftext.language.java.references.MethodCall;
import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.KDMHelper;
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

    /**
     * @param sourceCodeDecoratorRepository
     *            The source code decorator which links the component for which to classify
     *            statements and the GAST.
     * @param primitiveComponent
     *            The primitive component for which to decide whether the function access represents
     *            an external call.
     */
    public BasicFunctionClassificationStrategy(final SourceCodeDecoratorRepository sourceCodeDecoratorRepository,
            final BasicComponent primitiveComponent) {
        this.sourceCodeDecoratorRepository = sourceCodeDecoratorRepository;
        this.primitiveComponent = primitiveComponent;
    }

    @Override
    protected boolean isExternalCall(final MethodCall functionAccess) {// GAST2SEFFCHANGE

        final ComponentImplementingClassesLink compLink = this.queryComponentLink(this.primitiveComponent);
        for (final InterfaceSourceCodeLink ifLink : compLink.getRequiredInterfaces()) {
            if (KDMHelper.getMethods(ifLink.getGastClass()).contains(KDMHelper.getMethod(functionAccess))) {// GAST2SEFFCHANGE//GAST2SEFFCHANGE
                logger.debug("Classified call as external call: " + KDMHelper.getMethod(functionAccess).getName() + // GAST2SEFFCHANGE//GAST2SEFFCHANGE
                        " for component " + this.primitiveComponent.getEntityName());
                return true;
            }
        }

        logger.trace("no external call: " + KDMHelper.getMethod(functionAccess).getName());// GAST2SEFFCHANGE//GAST2SEFFCHANGE
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
                + "decorator for component " + primitiveComponent.getEntityName();
        logger.error(msg);
        throw new RuntimeException(msg);
    }

    @Override
    protected boolean isLibraryCall(final MethodCall functionAccess) {// GAST2SEFFCHANGE
        final org.emftext.language.java.types.Type targetClass = GetAccessedType.getAccessedType(functionAccess);// GAST2SEFFCHANGE//GAST2SEFFCHANGE
        if (targetClass == null) {
            logger.warn("Failed to classifiy library call because called GASTClass was unavailable.");
            return true;
        }
        logger.debug("Classified call as library call: " + KDMHelper.getMethod(functionAccess).getName() + // GAST2SEFFCHANGE//GAST2SEFFCHANGE
                " for component " + this.primitiveComponent.getEntityName());
        return KDMHelper.getJavaNodeSourceRegion(targetClass) == null
                || KDMHelper.getJavaNodeSourceRegion(targetClass) == null;// GAST2SEFFCHANGE//GAST2SEFFCHANGE
    }

}
