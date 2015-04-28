package org.somox.gast2seff.visitors;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
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
    public static MethodCall getMethodCall(final Statement statement) {
        final EList<MethodCall> methodCalls = statement.getChildrenByType(MethodCall.class);
        if (null == methodCalls || 0 == methodCalls.size()) {
            return null;
        }
        if (1 != methodCalls.size()) {
            logger.warn("Found " + methodCalls.size() + " MethodCalls in Statement " + statement
                    + " - returning the first.");
        }
        return methodCalls.get(0);

    }
}
