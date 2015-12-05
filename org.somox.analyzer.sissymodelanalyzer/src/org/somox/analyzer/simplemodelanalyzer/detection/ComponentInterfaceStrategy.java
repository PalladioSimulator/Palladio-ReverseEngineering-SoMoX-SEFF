package org.somox.analyzer.simplemodelanalyzer.detection;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.types.Type;
import org.somox.kdmhelper.KDMHelper;
//import de.fzi.gast.functions.Method;
//import de.fzi.gast.types.GASTClass;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * Default interface detection strategy. Conditions in GAST: interface or only virtual methods which
 * is not primitive or identified a component interface before (e.g. public methods used as
 * component interface due to a fall back strategy).
 *
 * @author Klaus Krogmann
 *
 */
public class ComponentInterfaceStrategy implements IComponentInterfaceStrategy {

    private final SourceCodeDecoratorRepository sourceCodeDecorator;

    /**
     * Default ctor for this strategy.
     *
     * @param sourceCodeDecorator
     *            decorator to check for (additional) interfaces which are to be considered as
     *            component interfaces. Interfaces from the source code decorator are considered as
     *            whitelisted.
     */
    public ComponentInterfaceStrategy(final SourceCodeDecoratorRepository sourceCodeDecorator) {
        this.sourceCodeDecorator = sourceCodeDecorator;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.analyzer.simplemodelanalyzer.detection.IComponentInterfaceStrategy#
     * isComponentInterface (de.fzi.gast.types.GASTClass)
     */
    @Override
    public boolean isComponentInterface(final ConcreteClassifier classToCheck) {
        return this.isRegularInterface(classToCheck) || this.isPureVirtualClass(classToCheck)
                || this.isClassifiedAsInterfaceViaSourceCodeDecorator(classToCheck);
    }

    /**
     * Checks whether the class has been identified as interface by the SISSy GAST model.
     *
     * @param classToCheck
     *            class to check for component interface status.
     * @return true if interface flag is set.
     */
    private boolean isRegularInterface(final Type classToCheck) {
        return KDMHelper.isInterface(classToCheck);
    }

    /**
     * Checks whether the class (even if no real interface) was used as an interface in previous
     * iterations as a fall back solution. Then, the class -- although being only an usual class --
     * is considered a component interface here to ensure consistency.
     *
     * @param classToCheck
     *            The class to check in for in the source code decorator.
     * @return true if the class appears in the source code decorator.
     */
    private boolean isClassifiedAsInterfaceViaSourceCodeDecorator(final Type classToCheck) {
        for (final InterfaceSourceCodeLink ifLink : this.sourceCodeDecorator.getInterfaceSourceCodeLink()) {
            if (ifLink.getGastClass().equals(classToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether all methods of a class are virtual and whether methods exist. This can be
     * interpreted as having an interface class.
     *
     * @param classToCheck
     *            The class to check
     * @return true if all methods are declared virtual; false else
     */
    private boolean isPureVirtualClass(final ConcreteClassifier classToCheck) {
        // do not consider "empty" classes with no methods as interface
        if (KDMHelper.getMethods(classToCheck).size() == 0) {
            return false;
        }
        for (final Method method : KDMHelper.getMethods(classToCheck)) {
            if (!KDMHelper.isVirtual(method)) {
                return false;
            }
            if (method instanceof ClassMethod) {
                final ClassMethod classMethod = (ClassMethod) method;
                final int size = classMethod.getStatements().size();
                if (size > 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
