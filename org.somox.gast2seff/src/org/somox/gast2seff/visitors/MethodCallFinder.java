package org.somox.gast2seff.visitors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationAttribute;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.statements.Statement;

/**
 * The class finds methodCalls within a statement. It also caches the found method calls for
 * statements to improve performance.
 *
 */
public class MethodCallFinder {

    private static final Logger logger = Logger.getLogger(MethodCallFinder.class.getSimpleName());

    private final HashMap<Statement, List<Method>> methodListCacheForStatement;

    public MethodCallFinder() {
        this.methodListCacheForStatement = new HashMap<Statement, List<Method>>();
    }

    /**
     * Get and returns the child method or constructor called contained in the statement.
     *
     * @param statement
     *            A statement
     * @return A list of methods called if the statement contains one. Otherwise: an empty list
     */
    public List<Method> getMethodCalls(final Statement statement) {
        if (!this.methodListCacheForStatement.containsKey(statement)) {
            final LinkedList<Method> calledMethods = new LinkedList<Method>();
            final Set<EObject> investigatedEObjects = new HashSet<EObject>();

            this.findMethodCallsInChildren(statement, calledMethods, investigatedEObjects);
            this.methodListCacheForStatement.put(statement, calledMethods);
        }
        return this.methodListCacheForStatement.get(statement);
    }

    private void findMethodCallsInChildren(final EObject eObject, final LinkedList<Method> calledMethods,
            final Set<EObject> investigatedEObjects) {
        if (null == eObject) {
            return;
        }
        final TreeIterator<EObject> treeIterator = eObject.eAllContents();
        while (treeIterator.hasNext()) {
            final EObject current = treeIterator.next();
            if (investigatedEObjects.contains(current)) {
                continue;
            }
            investigatedEObjects.add(current);
            if (current instanceof MethodCall) {
                final MethodCall methodCall = (MethodCall) current;
                this.findMethodCallsInArguments(methodCall.getArguments(), calledMethods, investigatedEObjects);
                this.addMethodToCollection(calledMethods, methodCall);
                this.findMethodCallsInNext(methodCall.getNext(), calledMethods, investigatedEObjects);
            }
        }
    }

    private void findMethodCallsInNext(final Reference next, final LinkedList<Method> calledMethods,
            final Set<EObject> investigatedEObjects) {
        this.findMethodCallsInChildren(next, calledMethods, investigatedEObjects);

    }

    private void findMethodCallsInArguments(final EList<Expression> arguments, final LinkedList<Method> calledMethods,
            final Set<EObject> investigatedEObjects) {
        for (final Expression expression : arguments) {
            if (expression instanceof MethodCall) {
                investigatedEObjects.add(expression);
                this.addMethodToCollection(calledMethods, (MethodCall) expression);
            }
            this.findMethodCallsInChildren(expression, calledMethods, investigatedEObjects);
        }

    }

    private void addMethodToCollection(final LinkedList<Method> calledMethods, final MethodCall methodCall) {
        if (methodCall.getTarget() instanceof Method) {
            final Method target = (Method) methodCall.getTarget();
            if (target instanceof AnnotationAttribute) {
                logger.info("Annotation Attribut found within methodCall" + methodCall + " target: "
                        + methodCall.getTarget().getName());
            } else if (target instanceof Method) {
                calledMethods.add(target);
            }
        }
    }
}
