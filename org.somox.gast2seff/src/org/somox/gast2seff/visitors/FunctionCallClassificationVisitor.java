package org.somox.gast2seff.visitors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.expressions.util.ExpressionsSwitch;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.statements.Assert;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.DoWhileLoop;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.ForEachLoop;
import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.statements.WhileLoop;
import org.emftext.language.java.statements.util.StatementsSwitch;
import org.somox.kdmhelper.KDMHelper;

/**
 * Classifies function calls are internal, library, or external calls. Transitively assigns that
 * type to outer statements like loops or branches.
 *
 * @author Steffen Becker, Klaus Krogmann
 *
 */
// TODO: constructor calls
// TODO: Method calls within method calls
// TODO: Method calls in conditions (expressions)
public class FunctionCallClassificationVisitor extends ComposedSwitch<Collection<BitSet>> {// GAST2SEFFCHANGE

    private static final Logger logger = Logger.getLogger(JaMoPPStatementVisitor.class);

    private final MethodCallFinder methodCallFinder;

    public FunctionCallClassificationVisitor(final IFunctionClassificationStrategy strategy,
            final MethodCallFinder methodCallFinder) {
        super();

        this.methodCallFinder = methodCallFinder;
        this.myStrategy = strategy;
        this.addSwitch(new MembersClassification());
        this.addSwitch(new StatementClassification());
        this.addSwitch(new ExpressionClassification());
    }

    public enum FunctionCallType {
        /**
         * Classifies a component external call.
         */
        EXTERNAL,

        /**
         * Classifies a call to a system library.
         */
        LIBRARY,

        /**
         * Classifies an call to a method in the same class.
         */
        INTERNAL,

        /**
         * Indicates whether a statements which has been visited before.
         */
        VISITED,

        /**
         * Classifies a call as an internal call that contains an external call
         */
        INTERNAL_CALL_CONTAINING_EXTERNAL_CALL
    }

    private final HashMap<Commentable, List<BitSet>> annotations = new HashMap<Commentable, List<BitSet>>();
    private IFunctionClassificationStrategy myStrategy = null;

    private class MembersClassification extends MembersSwitch<Collection<BitSet>> {
        @Override
        public Collection<BitSet> caseStatementListContainer(final StatementListContainer object) {
            return FunctionCallClassificationVisitor.this.handleStatementListContainer(object);
        }

    }

    private class StatementClassification extends StatementsSwitch<Collection<BitSet>> {

        @Override
        public Collection<BitSet> caseStatement(final Statement object) {
            return FunctionCallClassificationVisitor.this.handleFormerSimpleStatement(object);
        }

        @Override
        public Collection<BitSet> caseBlock(final Block block) {
            return FunctionCallClassificationVisitor.this.handleStatementListContainer(block);
        }

        @Override
        public Collection<BitSet> caseCondition(final Condition condition) {
            if (FunctionCallClassificationVisitor.this.annotations.containsKey(condition)) {
                return FunctionCallClassificationVisitor.this.annotations.get(condition);
            }

            final List<Statement> branchStatements = new ArrayList<Statement>();
            if (null != condition.getCondition()) {
                this.doSwitch(condition.getCondition());
            }

            // statement is the if-part
            if (null != condition.getStatement()) {
                this.doSwitch(condition.getStatement());
                branchStatements.add(condition.getStatement());
            }

            if (null != condition.getElseStatement()) {
                this.doSwitch(condition.getElseStatement());
                branchStatements.add(condition.getElseStatement());
            }
            final List<BitSet> myType = Arrays.asList(
                    FunctionCallClassificationVisitor.this.computeChildAnnotations(new BitSet(), branchStatements));
            FunctionCallClassificationVisitor.this.putBitSetInAnnotations(condition, myType);
            return myType;
        }

        @Override
        public Collection<BitSet> caseSwitch(final org.emftext.language.java.statements.Switch switchStatement) {
            if (FunctionCallClassificationVisitor.this.annotations.containsKey(switchStatement)) {
                return FunctionCallClassificationVisitor.this.annotations.get(switchStatement);
            }
            final List<List<Statement>> branches =
                    SwitchStatementHelper.createBlockListFromSwitchStatement(switchStatement);
            for (final List<Statement> branch : branches) {
                // copied from the BlockCase
                FunctionCallClassificationVisitor.this.computeChildAnnotations(new BitSet(), branch);
            }
            final List<Statement> branchStatements = new ArrayList<Statement>();
            for (final List<Statement> branch : branches) {
                branchStatements.addAll(branch);
            }
            final List<BitSet> myType = Arrays.asList(
                    FunctionCallClassificationVisitor.this.computeChildAnnotations(new BitSet(), branchStatements));
            FunctionCallClassificationVisitor.this.putBitSetInAnnotations(switchStatement, myType);

            return myType;
        }

        @Override
        public Collection<BitSet> caseForLoop(final ForLoop object) {
            return FunctionCallClassificationVisitor.this.createBitSetLoop(object, object.getStatement());
        }

        @Override
        public Collection<BitSet> caseForEachLoop(final ForEachLoop object) {
            return FunctionCallClassificationVisitor.this.createBitSetLoop(object, object.getStatement());
        }

        @Override
        public Collection<BitSet> caseWhileLoop(final WhileLoop object) {
            return FunctionCallClassificationVisitor.this.createBitSetLoop(object, object.getStatement());
        }

        @Override
        public Collection<BitSet> caseDoWhileLoop(final DoWhileLoop object) {
            return FunctionCallClassificationVisitor.this.createBitSetLoop(object, object.getStatement());
        }

        @Override
        public Collection<BitSet> caseTryBlock(final TryBlock object) {
            if (FunctionCallClassificationVisitor.this.annotations.containsKey(object)) {
                return FunctionCallClassificationVisitor.this.annotations.get(object);
            }

            // handle try block
            FunctionCallClassificationVisitor.this.handleStatementListContainer(object);
            final List<Statement> allChildStatements = new ArrayList<Statement>();
            allChildStatements.addAll(object.getStatements());

            // handle guarded blocks
            for (final CatchBlock catchBlock : object.getCatcheBlocks()) {
                this.doSwitch(catchBlock);
                allChildStatements.addAll(catchBlock.getStatements());
            }
            // handle finally block
            if (object.getFinallyBlock() != null) {
                this.doSwitch(object.getFinallyBlock());
                allChildStatements.addAll(object.getFinallyBlock().getStatements());
            }
            final List<BitSet> myType = Arrays.asList(
                    FunctionCallClassificationVisitor.this.computeChildAnnotations(new BitSet(), allChildStatements));
            FunctionCallClassificationVisitor.this.putBitSetInAnnotations(object, myType);
            return myType;
        }

        @Override
        public Collection<BitSet> caseAssert(final Assert object) {
            return FunctionCallClassificationVisitor.this.handleFormerSimpleStatement(object);
        }

        @Override
        public Collection<BitSet> caseExpressionStatement(final ExpressionStatement object) {
            return FunctionCallClassificationVisitor.this.handleFormerSimpleStatement(object);
        }

        @Override
        public Collection<BitSet> caseLocalVariableStatement(final LocalVariableStatement object) {
            return FunctionCallClassificationVisitor.this.handleFormerSimpleStatement(object);
        }
    }

    // TODO: implement expression switch
    private class ExpressionClassification extends ExpressionsSwitch<Collection<BitSet>> {

    }

    private Collection<BitSet> handleStatementListContainer(final StatementListContainer object) {
        if (FunctionCallClassificationVisitor.this.annotations.containsKey(object)) {
            return FunctionCallClassificationVisitor.this.annotations.get(object);
        }
        final List<BitSet> myTypes = Arrays.asList(
                FunctionCallClassificationVisitor.this.computeChildAnnotations(new BitSet(), object.getStatements()));
        this.putBitSetInAnnotations(object, myTypes);
        return myTypes;
    }

    /**
     * Creates a bit set for the a loop statement.
     *
     * @param loop
     *            the loop statement
     * @param body
     *            the body of the loop
     * @return the created bit set
     */
    private Collection<BitSet> createBitSetLoop(final Statement loop, final Statement body) {
        if (this.annotations.containsKey(loop)) {
            return this.annotations.get(loop);
        }

        this.doSwitch(body);

        final List<BitSet> myType = this.annotations.get(body);
        this.putBitSetInAnnotations(loop, myType);

        return myType;
    }

    /**
     * Helper Method to handle former SimpleStatement.
     *
     * <br>
     * Used in AssertStatement, ExpressionStatement, VariableDeclarationStatement
     *
     * @param statement
     *            the statement for which the bit set is computed
     * @return the computed bit set
     */
    private List<BitSet> handleFormerSimpleStatement(final Statement statement) {
        if (this.annotations.containsKey(statement)) {
            return this.annotations.get(statement);
        }

        final List<BitSet> myTypes = this.myStrategy.classifySimpleStatement(statement);
        this.putBitSetInAnnotations(statement, myTypes);
        final List<Method> calledMethods = this.methodCallFinder.getMethodCalls(statement);
        for (int i = 0; i < myTypes.size(); i++) {
            final BitSet myType = myTypes.get(i);
            if (myType.get(getIndex(FunctionCallType.INTERNAL))) {
                // Also annotate the internal method
                final Method calledMethod = calledMethods.get(i);
                final StatementListContainer targetFunctionBody = KDMHelper.getBody(calledMethod);
                Collection<BitSet> internalTypes = new ArrayList<BitSet>();
                if (targetFunctionBody != null) {
                    logger.trace("visiting internal call. accessed class: "
                            + calledMethod.getContainingConcreteClassifier());
                    internalTypes = this.doSwitch(targetFunctionBody);
                } else {
                    logger.warn("Behaviour not set in GAST for " + calledMethod.getName());
                }
                for (final BitSet internalType : internalTypes) {
                    // the internal call contains an external call
                    if (internalType.get(getIndex(FunctionCallType.EXTERNAL))) {
                        myType.set(getIndex(FunctionCallType.INTERNAL_CALL_CONTAINING_EXTERNAL_CALL));
                    }
                }
            }
        }
        return myTypes;
    }

    private void putBitSetInAnnotations(final Commentable object, final List<BitSet> type) {
        this.annotations.put(object, type);
    }

    private BitSet computeChildAnnotations(final BitSet initalValue, final List<Statement> childStatements) {
        // 1. visit all sub statements
        for (final Statement s : childStatements) {
            if (null != s) {
                this.doSwitch(s);
            }
        }

        // 2. compute own type iteratively
        final BitSet myType = initalValue;
        for (final Statement s : childStatements) {
            if (null != s) {
                this.myStrategy.mergeFunctionCallType(myType, this.annotations.get(s));
            }
        }

        return myType;
    }

    public static int getIndex(final FunctionCallType type) {
        switch (type) {
        case INTERNAL:
            return 0;
        case EXTERNAL:
            return 1;
        case LIBRARY:
            return 2;
        case VISITED:
            return 3;
        case INTERNAL_CALL_CONTAINING_EXTERNAL_CALL:
            return 4;
        }
        throw new UnsupportedOperationException();
    }

    public Map<Commentable, List<BitSet>> getAnnotations() {
        return Collections.unmodifiableMap(this.annotations);
    }

    @Override
    public Collection<BitSet> defaultCase(final EObject object) {
        logger.warn("Not handled object by function call visitor:\n  " + object);
        return super.defaultCase(object);
    }
}
