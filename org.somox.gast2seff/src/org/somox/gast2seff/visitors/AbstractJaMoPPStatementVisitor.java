package org.somox.gast2seff.visitors;

import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Method;
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
import org.emftext.language.java.statements.SynchronizedBlock;
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
    protected final Map<Commentable, List<BitSet>> functionClassificationAnnotation;
    /**
     * Classification annotation of the last visited statement. Used to skip generating SEFF actions
     * if they should be omitted because of the SEFFs abstraction rule
     */
    protected BitSet lastType = null;

    /**
     * Can be used to force the shouldSkip method to not skip the next statement, which eventually
     * results in a new SEFF element for the next statement.
     *
     */
    protected boolean doNotSkipNextStatement;

    /** The method call finder */
    protected MethodCallFinder methodCallFinder;

    public AbstractJaMoPPStatementVisitor(final Map<Commentable, List<BitSet>> functionClassificationAnnotations,
            final MethodCallFinder methodCallFinder) {
        this.doNotSkipNextStatement = false;
        this.functionClassificationAnnotation = functionClassificationAnnotations;
        this.methodCallFinder = methodCallFinder;

        this.addSwitch(new MemberVisitor());
        this.addSwitch(new StatementVisitor());
    }

    // abstract methods that have to be overriden by subclasses of the class
    protected abstract Object handleLoopStatement(final Statement object, final Statement statement);

    protected abstract Object handleCondition(final Condition condition);

    protected abstract Object handleSwitch(final Switch switchStatement);

    protected abstract Object handleClassMethod(ClassMethod classMethod, Statement callStatement);

    protected abstract Object handleTryBlock(final TryBlock object);

    /**
     * Per default we do not handle synchronized blocks. Instead we treat the synchronized statement
     * like any other statement. It is, however, up to subclasses to override this method and handle
     * synchronized blocks.
     */
    protected Object handleSynchronizedBlock(final SynchronizedBlock synchronizedBlock) {
        return null;
    }

    protected abstract void foundInternalAction(Statement object);

    protected abstract void foundExternalCall(Statement object, Method calledMethod, BitSet statementAnnotation);

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
            final Collection<BitSet> thisTypes = this.functionClassificationAnnotation.get(s);
            if (null == thisTypes) {
                logger.info("thisTypes == null - continue with next statement");
                continue;
            }
            for (final BitSet thisType : thisTypes) {
                if (!this.shouldSkip(this.lastType, thisType)) {
                    // Only generate elements for statements which should not be abstracted away
                    // avoid infinite recursion
                    if (!this.isVisitedStatement(thisType)) {
                        this.setVisited(thisTypes);
                        this.doSwitch(s);
                    }
                }
                this.lastType = thisType;
            }
        }
        return new Object();
    }

    protected Object handleFormerSimpleStatement(final Statement object) {
        final List<BitSet> statementAnnotations = this.functionClassificationAnnotation.get(object);
        final List<Method> calledMethods = this.methodCallFinder.getMethodCalls(object);
        if (0 < calledMethods.size()) {
            for (int i = 0; i < statementAnnotations.size(); i++) {
                final BitSet statementAnnotation = statementAnnotations.get(i);
                if (this.isExternalCall(statementAnnotation)) {
                    final Method calledMethod = calledMethods.get(i);
                    this.foundExternalCall(object, calledMethod, statementAnnotation);
                } else if (this.isInternalCall(statementAnnotation)) {
                    if (0 == calledMethods.size()) {
                        continue;
                    }
                    Method method = null;
                    if (i + 1 > calledMethods.size()) {
                        method = calledMethods.get(0);
                    } else {
                        method = calledMethods.get(i);
                    }
                    if (!(method instanceof ClassMethod)) {
                        logger.error("Referenceable element must be a class method");
                    } else {
                        final ClassMethod classMethod = (ClassMethod) method;

                        if (classMethod.getStatements() != null) {
                            this.handleClassMethod(classMethod, object);
                        } else {
                            String msg = "Behaviour not set in GAST for " + method.getName();
                            if (KDMHelper.getJavaNodeSourceRegion(object) != null
                                    && KDMHelper.getJavaNodeSourceRegion(object).getNamespacesAsString() != null) {
                                msg += ". Tried to call from "
                                        + KDMHelper.getJavaNodeSourceRegion(object).getNamespacesAsString() + ".";
                            } else {
                                msg += ". (caller position unknown)";
                            }
                            logger.warn(msg);
                        }
                    }
                } else {
                    this.foundInternalAction(object);
                }
            }
        } else {
            this.foundInternalAction(object);
        }
        return new Object();

    }

    private void setVisited(final Collection<BitSet> thisTypes) {
        for (final BitSet thisType : thisTypes) {
            this.setVisited(thisType);
        }

    }

    private class MemberVisitor extends MembersSwitch<Object> {
        @Override
        public Object caseStatementListContainer(final StatementListContainer object) {
            return AbstractJaMoPPStatementVisitor.this.handleStatementListContainer(object);
        }

        @Override
        public Object caseClassMethod(final ClassMethod classMethod) {
            return AbstractJaMoPPStatementVisitor.this.handleClassMethod(classMethod, null);
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
        public Object caseSynchronizedBlock(final SynchronizedBlock synchronizedBlock) {
            return AbstractJaMoPPStatementVisitor.this.handleSynchronizedBlock(synchronizedBlock);
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
        final Collection<BitSet> statementTypes = this.functionClassificationAnnotation.get(object);
        for (final BitSet statementType : statementTypes) {
            final boolean isExternalCall = statementType
                    .get(FunctionCallClassificationVisitor.getIndex(FunctionCallType.EXTERNAL));
            final boolean isInternalCallContainingExternalCall = statementType.get(FunctionCallClassificationVisitor
                    .getIndex(FunctionCallType.INTERNAL_CALL_CONTAINING_EXTERNAL_CALL));
            if (isExternalCall || isInternalCallContainingExternalCall) {
                return true;
            }
        }
        return false;
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
            // if (position != null) { // GAST2SEFFCHANGE//GAST2SEFFCHANGE
            // TODO change name of class; question: is fqnName of Class better than path?
            // positionString.append(KDMHelper.getSourceFile(position).getPath() +
            // KDMHelper.getSourceFile(position).getName());//GAST2SEFFCHANGE
            positionString.append(KDMHelper.computeFullQualifiedName(position)); // GAST2SEFFCHANGE
            // }
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

    protected boolean isInternalCallContainingExternalCall(final BitSet statementAnnotation) {
        return statementAnnotation.get(
                FunctionCallClassificationVisitor.getIndex(FunctionCallType.INTERNAL_CALL_CONTAINING_EXTERNAL_CALL));
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
        if (this.doNotSkipNextStatement) {
            this.doNotSkipNextStatement = false;
            this.lastType = null;
            return false;
        }
        if (lastType == null) {
            return false;
        }

        if (this.isExternalCall(thisType)) {
            return false;
        }

        if (this.isInternalCallContainingExternalCall(thisType)) {
            return false;
        }

        // Here I know that thisType is internal or library
        // Hence, I can skip this if the last type was not an external call
        return !this.isExternalCall(lastType);
    }

}
