package org.somox.gast2seff.visitors;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.annotations.AnnotationAttribute;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.statements.Statement;

public class VisitorUtils {

    private static final Logger logger = Logger.getLogger(VisitorUtils.class.getSimpleName());

    // no public ctors
    private VisitorUtils() {
    }

    /**
     * Get and returns the first method or constructor called contained in the statement. TODO: What
     * happens if more than one constructor or method called in one statement?
     *
     * @param statement
     *            A statement
     * @return The method or constructor call if the statement contains one. Otherwise: an empty
     *         list
     */
    public static Method getMethodCall(final Statement statement) {
        final Queue<Method> calledMethods = new LinkedList<Method>();
        final EList<MethodCall> methodCalls = statement.getChildrenByType(MethodCall.class);
        if (null != methodCalls) {
            for (final MethodCall methodCall : methodCalls) {
                if (methodCall.getTarget() instanceof AnnotationAttribute) {
                    logger.info("Annotation Attribut found within statement " + statement);
                }
                if (methodCall.getTarget() instanceof Method) {
                    calledMethods.add((Method) methodCall.getTarget());
                }
            }
        }
        final EList<Method> childMethodsOfStatement = statement.getChildrenByType(Method.class);
        if (null != childMethodsOfStatement && 0 < childMethodsOfStatement.size()) {
            logger.info("Add " + childMethodsOfStatement.size() + " childMethods to calledMethod list");
            calledMethods.addAll(childMethodsOfStatement);
        }
        if (0 == calledMethods.size()) {
            return null;
        } else if (1 != calledMethods.size()) {
            logger.warn("Found " + calledMethods.size() + " called Methods in Statement " + statement
                    + " - returning the first.");
        }

        return calledMethods.poll();

    }
}
