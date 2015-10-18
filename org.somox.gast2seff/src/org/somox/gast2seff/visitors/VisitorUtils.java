package org.somox.gast2seff.visitors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationAttribute;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.references.MethodCall;
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

        final List<MethodCall> methodCalls = findMethodCalls(statement);

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
        return calledMethods;

    }

    private static List<MethodCall> findMethodCalls(final EObject parentEObject) {
        if (null == parentEObject || null == parentEObject.eContents()) {
            return Collections.emptyList();
        }
        final List<MethodCall> methodCalls = new ArrayList<MethodCall>();
        if (parentEObject instanceof MethodCall) {
            handleMethodCall(methodCalls, (MethodCall) parentEObject);
        }
        for (final EObject eObject : parentEObject.eContents()) {
            if (eObject instanceof MethodCall) {
                handleMethodCall(methodCalls, (MethodCall) eObject);
            } else {
                methodCalls.addAll(findMethodCalls(eObject));
            }
        }
        return methodCalls;

    }

    private static void handleMethodCall(final List<MethodCall> methodCalls, final MethodCall methodCall) {
        for (int i = methodCall.getArguments().size() - 1; i >= 0; i--) {
            final Expression expression = methodCall.getArguments().get(i);
            methodCalls.addAll(findMethodCalls(expression));
        }
        methodCalls.add(methodCall);
        methodCalls.addAll(findMethodCalls(methodCall.getNext()));
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
