package org.somox.gast2seff.visitors;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

public class VisitorUtils {

    // private static final Logger logger = Logger.getLogger(VisitorUtils.class.getSimpleName());

    // no public ctors
    private VisitorUtils() {
    }

    public static final void visitJaMoPPMethod(final ResourceDemandingBehaviour seff,
            final BasicComponent basicComponent, final StatementListContainer body,
            final SourceCodeDecoratorRepository sourceCodeDecoratorModel,
            final FunctionCallClassificationVisitor typeVisitor,
            final InterfaceOfExternalCallFindingFactory interfaceOfExternalCallFinderFactory,
            final MethodCallFinder methodCallFinder) {
        visitJaMoPPMethod(seff, basicComponent, body, sourceCodeDecoratorModel, typeVisitor,
                interfaceOfExternalCallFinderFactory, null, methodCallFinder);
    }

    public static void visitJaMoPPMethod(final ResourceDemandingBehaviour seff, final BasicComponent basicComponent,
            final StatementListContainer body, final SourceCodeDecoratorRepository sourceCodeDecoratorModel,
            final FunctionCallClassificationVisitor typeVisitor,
            final InterfaceOfExternalCallFindingFactory interfaceOfExternalCallFinderFactory,
            final ResourceDemandingBehaviourForClassMethodFinding resourceDemandingBehaviourForClassMethodFinding,
            final MethodCallFinder methodCallFinder) {
        final AbstractJaMoPPStatementVisitor visitor = new JaMoPPStatementVisitor(typeVisitor.getAnnotations(), seff,
                sourceCodeDecoratorModel, basicComponent, interfaceOfExternalCallFinderFactory,
                resourceDemandingBehaviourForClassMethodFinding, methodCallFinder);

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
