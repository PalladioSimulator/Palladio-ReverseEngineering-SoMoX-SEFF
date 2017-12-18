package org.somox.kdmhelper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.ResourcesPlugin;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.statements.StatementListContainer;

public class SoMoXUtil {

    private SoMoXUtil() {

    }

    /**
     * Determines whether SoMoX is executed within a new Eclipse or whether it runs standalone
     *
     * @return
     */
    public static boolean isStandalone() {
        try {
            ResourcesPlugin.getWorkspace();
        } catch (final IllegalStateException e) {
            // this means we run standalone
            return true;
        }
        return false;
    }

    public static Collection<StatementListContainer> findImplementingMethods(final InterfaceMethod interfaceMethod,
            final Collection<ConcreteClassifier> implementingClasses) {
        final ConcreteClassifier interfaceOfMethod = interfaceMethod.getContainingConcreteClassifier();
        final Set<StatementListContainer> implementingStatementListContainers = new HashSet<StatementListContainer>();
        if (null != implementingClasses) {
            for (final ConcreteClassifier classInComponent : implementingClasses) {
                if (KDMHelper.getSuperTypes(classInComponent).contains(interfaceOfMethod)) {
                    // find the overriden interface method
                    for (final Method methodInClass : classInComponent.getMethods()) {
                        if (EqualityChecker.areFunctionsEqual(interfaceMethod, methodInClass)
                                && methodInClass instanceof ClassMethod) {
                            implementingStatementListContainers.add(KDMHelper.getBody(methodInClass));
                        }
                    }
                }
            }
        }
        return implementingStatementListContainers;
    }
}
