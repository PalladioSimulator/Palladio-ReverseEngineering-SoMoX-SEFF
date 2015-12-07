package org.somox.gast2seff.visitors;

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
import org.emftext.language.java.statements.StatementListContainer;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

public class VisitorUtils {

    private static final Logger logger = Logger.getLogger(VisitorUtils.class.getSimpleName());

    // no public ctors
    private VisitorUtils() {
    }

    /**
     * Get and returns the child method or constructor called contained in the statement.
     *
     * @param statement
     *            A statement
     * @return A list of methods called if the statement contains one. Otherwise: an empty list
     */
    public static List<Method> getMethodCalls(final Statement statement) {
        final LinkedList<Method> calledMethods = new LinkedList<Method>();
        final Set<EObject> investigatedEObjects = new HashSet<EObject>();

        findMethodCallsInChildren(statement, calledMethods, investigatedEObjects);

        return calledMethods;
    }

    private static void findMethodCallsInChildren(final EObject eObject, final LinkedList<Method> calledMethods,
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
                findMethodCallsInArguments(methodCall.getArguments(), calledMethods, investigatedEObjects);
                addMethodToCollection(calledMethods, methodCall);
                findMethodCallsInNext(methodCall.getNext(), calledMethods, investigatedEObjects);
            }
        }
    }

    private static void findMethodCallsInNext(final Reference next, final LinkedList<Method> calledMethods,
            final Set<EObject> investigatedEObjects) {
        findMethodCallsInChildren(next, calledMethods, investigatedEObjects);

    }

    private static void findMethodCallsInArguments(final EList<Expression> arguments,
            final LinkedList<Method> calledMethods, final Set<EObject> investigatedEObjects) {
        for (final Expression expression : arguments) {
            findMethodCallsInChildren(expression, calledMethods, investigatedEObjects);
        }

    }

    private static void addMethodToCollection(final LinkedList<Method> calledMethods, final MethodCall methodCall) {
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

    public static final void visitJaMoPPMethod(final ResourceDemandingBehaviour seff,
            final BasicComponent basicComponent, final StatementListContainer body,
            final SourceCodeDecoratorRepository sourceCodeDecoratorModel,
            final FunctionCallClassificationVisitor typeVisitor) {
        visitJaMoPPMethod(seff, basicComponent, body, sourceCodeDecoratorModel, typeVisitor, null, null);
    }

    public static void visitJaMoPPMethod(final ResourceDemandingBehaviour seff, final BasicComponent basicComponent,
            final StatementListContainer body, final SourceCodeDecoratorRepository sourceCodeDecoratorModel,
            final FunctionCallClassificationVisitor typeVisitor,
            final InterfaceOfExternalCallFinding interfaceOfExternalCallFinder,
            final ResourceDemandingBehaviourForClassMethodFinding resourceDemandingBehaviourForClassMethodFinding) {
        final AbstractJaMoPPStatementVisitor visitor = new JaMoPPStatementVisitor(typeVisitor.getAnnotations(), seff,
                sourceCodeDecoratorModel, basicComponent, interfaceOfExternalCallFinder,
                resourceDemandingBehaviourForClassMethodFinding);

        // handle each statement
        for (final Statement st : body.getStatements()) {
            typeVisitor.doSwitch(st);
            visitor.doSwitch(st);
        }

    }

    /**
     * Add connections to the SEFF actions assuming the actions are stored in a sequential order
     *
     * @param seff
     *            The behaviour for which connections will be created
     */
    public static void connectActions(final ResourceDemandingBehaviour seff) {
        AbstractAction previous = null;
        for (final AbstractAction a : seff.getSteps_Behaviour()) {
            a.setPredecessor_AbstractAction(previous);
            previous = a;
        }
    }
}
