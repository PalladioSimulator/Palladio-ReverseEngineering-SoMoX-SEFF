package org.somox.gast2seff.visitors;

import java.util.BitSet;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.ClassMethod;
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
import org.somox.kdmhelper.KDMHelper;

public abstract class AbstractJaMoPPStatementVisitor extends ComposedSwitch<Object> {
    private static final Logger logger = Logger.getLogger(AbstractJaMoPPStatementVisitor.class);
    /**
     * Map which contains for each statement in the GAST model the type of the statement classified
     * according to {@link FunctionCallType}. Nodes of control flow constructs like loops and
     * branches carry the union of the annotations of their child statements
     */
    protected final Map<Commentable, BitSet> functionClassificationAnnotation;
    /**
     * Classification annotation of the last visited statement. Used to skip generating SEFF actions
     * if they should be omitted because of the SEFFs abstraction rule
     */
    protected BitSet lastType = null;
    private final boolean canSkipInternalCalls;

    public AbstractJaMoPPStatementVisitor(final Map<Commentable, BitSet> functionClassificationAnnotations,
            final boolean canSkipInternalCalls) {
        this.functionClassificationAnnotation = functionClassificationAnnotations;
        this.canSkipInternalCalls = canSkipInternalCalls;

        this.addSwitch(new MemberVisitor());
        this.addSwitch(new StatementVisitor());
    }

    public AbstractJaMoPPStatementVisitor(final Map<Commentable, BitSet> functionClassificationAnnotations) {
        this(functionClassificationAnnotations, true);
    }

    // abstract methods that have to be overriden by subclasses of the class
    protected abstract Object handleLoopStatement(final Statement object, final Statement statement);

    protected abstract Object handleCondition(final Condition condition);

    protected abstract Object handleSwitch(final Switch switchStatement);

    protected abstract Object handleClassMethod(ClassMethod classMethod);

    protected abstract Object handleFormerSimpleStatement(Statement object);

    protected abstract Object handleTryBlock(final TryBlock object);

    /**
     * handleStatementListContainer can be implemented in abstract class, cause it is similar in
     * current implementing classes. If necessary, however, it can be overriden of course.
     *
     * @param object
     *            the StatementListContainer (e.g. a Block or ClassMethod)
     * @return A Object to indicate that the Statement has been visited already
     */
    protected Object handleStatementListContainer(final StatementListContainer object) {
        for (final Statement s : object.getStatements()) {
            final BitSet thisType = this.functionClassificationAnnotation.get(s);
            if (!this.shouldSkip(this.lastType, thisType)) {
                // Only generate elements for statements which should not be abstracted away
                // avoid infinite recursion
                if (!this.isVisitedStatement(thisType)) {
                    this.setVisited(thisType);
                    this.doSwitch(s);
                }
            }
            this.lastType = thisType;
        }
        return new Object();
    }

    private class MemberVisitor extends MembersSwitch<Object> {
        @Override
        public Object caseStatementListContainer(final StatementListContainer object) {
            return AbstractJaMoPPStatementVisitor.this.handleStatementListContainer(object);
        }

        @Override
        public Object caseClassMethod(final ClassMethod classMethod) {
            return AbstractJaMoPPStatementVisitor.this.handleClassMethod(classMethod);
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

    @Override
    public Object defaultCase(final EObject object) {
        logger.warn("Not handled object by statement visitor:\n  " + object);
        return super.defaultCase(object);
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
        final boolean isExternalCall = this.functionClassificationAnnotation.get(object)
                .get(FunctionCallClassificationVisitor.getIndex(FunctionCallType.EXTERNAL));
        final boolean isInternalCallContainingExternalCall = this.functionClassificationAnnotation.get(object).get(
                FunctionCallClassificationVisitor.getIndex(FunctionCallType.INTERNAL_CALL_CONTAINING_EXTERNAL_CALL));
        return isExternalCall || isInternalCallContainingExternalCall;
    }

    protected boolean isVisitedStatement(final BitSet statementAnnotation) {
        return statementAnnotation.get(FunctionCallClassificationVisitor.getIndex(FunctionCallType.VISITED));
    }

    protected void setVisited(final BitSet thisType) {
        thisType.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.VISITED), true);

    }

    protected String positionToString(final Commentable position) {
        final StringBuilder positionString = new StringBuilder(" @position: ");
        if (position != null) {
            if (position != null && position.getClass() != null) { // GAST2SEFFCHANGE//GAST2SEFFCHANGE
                // TODO change name of class; question: is fqnName of Class better than path?
                // positionString.append(KDMHelper.getSourceFile(position).getPath() +
                // KDMHelper.getSourceFile(position).getName());//GAST2SEFFCHANGE
                positionString.append(KDMHelper.computeFullQualifiedName(position)); // GAST2SEFFCHANGE
            }
            if (null != position.getLayoutInformations() && 0 < position.getLayoutInformations().size()
                    && null != position.getLayoutInformations().get(0)) {
                final int startPos = position.getLayoutInformations().get(0).getStartOffset();
                final int layoutSize = position.getLayoutInformations().size();
                final int endPos = position.getLayoutInformations().get(layoutSize - 1).getStartOffset();
                if (startPos != endPos) {
                    positionString.append(" from ").append(startPos).append(" to ").append(endPos);
                } else {
                    positionString.append(" at ").append(startPos);
                }
            } else {
                positionString.append(" unknown exact possition");
            }

        } else {
            positionString.append("no position information available");
        }
        return positionString.toString();
    }

    protected String positionToLineNumber(final CompilationUnit position) {// GAST2SEFFCHANGE
        final StringBuilder positionString = new StringBuilder("line ");
        if (position != null) {
            positionString.append(position.getLayoutInformations().get(0).getStartOffset());
        } else {
            positionString.append("no position information available");
        }
        return positionString.toString();
    }

    protected boolean isExternalCall(final BitSet statementAnnotation) {
        return statementAnnotation.get(FunctionCallClassificationVisitor.getIndex(FunctionCallType.EXTERNAL));
    }

    protected boolean isInternalCall(final BitSet statementAnnotation) {
        return statementAnnotation.get(FunctionCallClassificationVisitor.getIndex(FunctionCallType.INTERNAL));
    }

    protected boolean isLibraryCall(final BitSet statementAnnotation) {
        return statementAnnotation.get(FunctionCallClassificationVisitor.getIndex(FunctionCallType.LIBRARY));
    }

    /**
     * Returns true if the statement with thisType should not generate an action in the newly
     * generated SEFF.
     *
     * @param lastType
     *            The type of the preceeding statement
     * @param thisType
     *            The type of the statement to test
     * @return true if the current statement should not generate an element in the SEFF, i.e., it
     *         should be abstracted and thrown away
     */
    protected boolean shouldSkip(final BitSet lastType, final BitSet thisType) {
        if (lastType == null) {
            return false;
        }
        // this is somewhat confusing, but: if we can not skip internal calls we only can skip lib
        // calls. Hence, we can skip if lastType isLibrary call and this type is library call. In
        // all other cases we can not skip the current statement.
        if (!this.canSkipInternalCalls) {
            return this.isLibraryCall(lastType) && this.isLibraryCall(thisType);
        }

        if (this.isExternalCall(thisType)) {
            return false;
        }

        // Here I know that thisType is internal or library
        // Hence, I can skip this if the last type was not an external call
        return !this.isExternalCall(lastType);
    }

}
