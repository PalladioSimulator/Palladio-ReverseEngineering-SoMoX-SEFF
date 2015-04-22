package org.somox.gast2seff.visitors;

import java.util.BitSet;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.statements.Assert;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.DoWhileLoop;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.ForEachLoop;
import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.Jump;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.statements.WhileLoop;
import org.emftext.language.java.statements.util.StatementsSwitch;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor.FunctionCallType;

public abstract class AbstractJaMoPPStatementVisitor extends ComposedSwitch<Object> {
    private static final Logger logger = Logger.getLogger(AbstractJaMoPPStatementVisitor.class);
    /**
     * Map which contains for each statement in the GAST model the type of the statement classified
     * according to {@link FunctionCallType}. Nodes of control flow constructs like loops and
     * branches carry the union of the annotations of their child statements
     */
    protected final Map<Commentable, BitSet> functionClassificationAnnotation;

    public AbstractJaMoPPStatementVisitor(final Map<Commentable, BitSet> functionClassificationAnnotations) {
        this.functionClassificationAnnotation = functionClassificationAnnotations;

        this.addSwitch(new MemberVisitor());
        this.addSwitch(new StatementVisitor());
    }

    // abstract methods that have to be overriden by subclasses of the class
    protected abstract Object handleLoopStatement(final Statement object, final Statement statement);

    protected abstract Object handleCondition(final Condition condition);

    protected abstract Object handleSwitch(final Switch switchStatement);

    protected abstract Object handleStatementListContainer(StatementListContainer object);

    protected abstract Object handleFormerSimpleStatement(Statement object);

    protected abstract Object handleTryBlock(final TryBlock object);

    private class MemberVisitor extends MembersSwitch<Object> {
        @Override
        public Object caseStatementListContainer(final StatementListContainer object) {
            return AbstractJaMoPPStatementVisitor.this.handleStatementListContainer(object);
        }
    }

    private class StatementVisitor extends StatementsSwitch<Object> {

        @Override
        public Object caseSwitch(final Switch switchStatement) {
            return AbstractJaMoPPStatementVisitor.this.handleSwitch(switchStatement);
        }

        @Override
        public Object caseCondition(final Condition condition) {
            return AbstractJaMoPPStatementVisitor.this.handleCondition(condition);
        }

        @Override
        public Object caseBlock(final Block block) {
            return AbstractJaMoPPStatementVisitor.this.handleStatementListContainer(block);
        }

        @Override
        public Object caseForEachLoop(final ForEachLoop object) {
            return AbstractJaMoPPStatementVisitor.this.handleLoopStatement(object, object.getStatement());
        }

        @Override
        public Object caseForLoop(final ForLoop object) {//
            return AbstractJaMoPPStatementVisitor.this.handleLoopStatement(object, object.getStatement());
        }

        @Override
        public Object caseWhileLoop(final WhileLoop object) {
            return AbstractJaMoPPStatementVisitor.this.handleLoopStatement(object, object.getStatement());
        }

        @Override
        public Object caseDoWhileLoop(final DoWhileLoop object) {
            return AbstractJaMoPPStatementVisitor.this.handleLoopStatement(object, object.getStatement());
        }

        @Override
        public Object caseTryBlock(final TryBlock object) {
            return AbstractJaMoPPStatementVisitor.this.handleTryBlock(object);

        }

        @Override
        public Object caseExpressionStatement(final ExpressionStatement object) {
            return AbstractJaMoPPStatementVisitor.this.handleFormerSimpleStatement(object);
        }

        @Override
        public Object caseLocalVariableStatement(final LocalVariableStatement object) {
            return AbstractJaMoPPStatementVisitor.this.handleFormerSimpleStatement(object);
        }

        @Override
        public Object caseAssert(final Assert object) {
            return AbstractJaMoPPStatementVisitor.this.handleFormerSimpleStatement(object);
        }

        @Override
        public Object caseStatement(final Statement statement) {
            return AbstractJaMoPPStatementVisitor.this.handleFormerSimpleStatement(statement);
        }

        @Override
        public Object caseJump(final Jump jump) {
            // ignore jump statements (break and continue)
            return new Object();
        }

        @Override
        public Object defaultCase(final EObject object) {
            return AbstractJaMoPPStatementVisitor.this.defaultCase(object);
        }
    }

    /**
     * Returns true if the statement or one of its child statements (e.g., for loops or branches) is
     * an external service call
     *
     * @param object
     *            The statement to check
     * @return true if the statement or one of its child statements is an external service call
     */
    protected boolean containsExternalCall(final Statement object) {
        final boolean isExternalCall = this.functionClassificationAnnotation.get(object).get(
                FunctionCallClassificationVisitor.getIndex(FunctionCallType.EXTERNAL));
        final boolean isInternalCallContainingExternalCall = this.functionClassificationAnnotation.get(object).get(
                FunctionCallClassificationVisitor.getIndex(FunctionCallType.INTERNAL_CALL_CONTAINING_EXTERNAL_CALL));
        return isExternalCall || isInternalCallContainingExternalCall;
    }

    @Override
    public Object defaultCase(final EObject object) {
        logger.warn("Not handled object by statement visitor:\n  " + object);
        return super.defaultCase(object);
    }

}
