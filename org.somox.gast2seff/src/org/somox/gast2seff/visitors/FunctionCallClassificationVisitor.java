package org.somox.gast2seff.visitors;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.util.MembersSwitch;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.statements.Assert;
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
import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.KDMHelper;

//import de.fzi.gast.accesses.BaseAccess;//GAST2SEFFCHANGE
//import de.fzi.gast.accesses.FunctionAccess;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.BlockStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.Branch;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.BranchStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.LoopStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.SimpleStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.Statement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.util.statementsSwitch;//GAST2SEFFCHANGE

/**
 * Classifies function calls are internal, library, or external calls. Transitively assigns that
 * type to outer statements like loops or branches.
 *
 * @author Steffen Becker, Klaus Krogmann
 *
 */
public class FunctionCallClassificationVisitor extends ComposedSwitch<BitSet> {// GAST2SEFFCHANGE

    private static final Logger logger = Logger.getLogger(GastStatementVisitor.class);

    public FunctionCallClassificationVisitor(final IFunctionClassificationStrategy strategy) {
        super();

        this.myStrategy = strategy;
        this.addSwitch(new MembersClassification());
        this.addSwitch(new StatementClassification());
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
        VISITED
    }

    private final HashMap<Statement, BitSet> annotations = new HashMap<Statement, BitSet>();
    private IFunctionClassificationStrategy myStrategy = null;

    private class MembersClassification extends MembersSwitch<BitSet> {
        @Override
        public BitSet caseStatementListContainer(final StatementListContainer object) { // GAST2SEFFCHANGE
            if (FunctionCallClassificationVisitor.this.annotations.containsKey(object)) {
                return FunctionCallClassificationVisitor.this.annotations.get(object);
            }
            final BitSet myType = FunctionCallClassificationVisitor.this.computeChildAnnotations(new BitSet(),
                    object.getStatements());
            // FunctionCallClassificationVisitor.this.annotations.put(object, myType);
            return myType;
        }
    }

    private class StatementClassification extends StatementsSwitch<BitSet> {

        @Override
        public BitSet caseStatement(final Statement object) {
            FunctionCallClassificationVisitor.this.safePut(object, new BitSet());
            return new BitSet();
        }

        @Override
        public BitSet caseCondition(final Condition object) {
            if (FunctionCallClassificationVisitor.this.annotations.containsKey(object)) {
                return FunctionCallClassificationVisitor.this.annotations.get(object);
            }
            this.doSwitch(object.getStatement());
            if (object.getElseStatement() != null) {
                this.doSwitch(object.getElseStatement());
            }
            final List<Statement> branchStatements = new ArrayList<Statement>();
            branchStatements.add(object.getElseStatement());
            if (object.getElseStatement() != null) {
                branchStatements.add(object.getElseStatement());
            }
            final BitSet myType = FunctionCallClassificationVisitor.this.computeChildAnnotations(new BitSet(),
                    branchStatements);
            FunctionCallClassificationVisitor.this.annotations.put(object, myType);
            return myType;
        }

        @Override
        public BitSet caseSwitch(final org.emftext.language.java.statements.Switch switchStatement) {
            if (FunctionCallClassificationVisitor.this.annotations.containsKey(switchStatement)) {
                return FunctionCallClassificationVisitor.this.annotations.get(switchStatement);
            }
            final ArrayList<ArrayList<Statement>> branches = SwitchStatementHelper
                    .createBlockListFromSwitchStatement(switchStatement);
            for (final ArrayList<Statement> branch : branches) {
                // copied from the BlockCase
                FunctionCallClassificationVisitor.this.computeChildAnnotations(new BitSet(), branch);
            }
            final List<Statement> branchStatements = new ArrayList<Statement>();
            for (final ArrayList<Statement> branch : branches) {
                branchStatements.addAll(branch);
            }
            final BitSet myType = FunctionCallClassificationVisitor.this.computeChildAnnotations(new BitSet(),
                    branchStatements);
            FunctionCallClassificationVisitor.this.annotations.put(switchStatement, myType);

            return myType;
        }

        @Override
        public BitSet caseForLoop(final ForLoop object) {
            return FunctionCallClassificationVisitor.this.createBitSetLoop(object, object.getStatement());
        }

        @Override
        public BitSet caseForEachLoop(final ForEachLoop object) {
            return FunctionCallClassificationVisitor.this.createBitSetLoop(object, object.getStatement());
        }

        @Override
        public BitSet caseWhileLoop(final WhileLoop object) {
            return FunctionCallClassificationVisitor.this.createBitSetLoop(object, object.getStatement());
        }

        @Override
        public BitSet caseDoWhileLoop(final DoWhileLoop object) {
            return FunctionCallClassificationVisitor.this.createBitSetLoop(object, object.getStatement());
        }

        @Override
        public BitSet caseTryBlock(final TryBlock object) { // GAST2SEFFCHANGE//GAST2SEFFCHANGE
            if (FunctionCallClassificationVisitor.this.annotations.containsKey(object)) {
                return FunctionCallClassificationVisitor.this.annotations.get(object);
            }
            final List<Statement> allChildStatements = new ArrayList<Statement>();
            //
            // handle guarded block
            for (final CatchBlock catchBlock : object.getCatcheBlocks()) {
                this.doSwitch(catchBlock);
                allChildStatements.addAll(catchBlock.getStatements());
            }
            // handle finally block
            if (object.getFinallyBlock() != null) { // GAST2SEFFCHANGE
                this.doSwitch(object.getFinallyBlock()); // GAST2SEFFCHANGE
                allChildStatements.addAll(object.getFinallyBlock().getStatements()); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
            }
            final BitSet myType = FunctionCallClassificationVisitor.this.computeChildAnnotations(new BitSet(),
                    allChildStatements);
            FunctionCallClassificationVisitor.this.annotations.put(object, myType);
            return myType;
        }

        @Override
        public BitSet caseAssert(final Assert object) {
            return FunctionCallClassificationVisitor.this.handleFormerSimpleStatement(object);
        }

        @Override
        public BitSet caseExpressionStatement(final ExpressionStatement object) {
            return FunctionCallClassificationVisitor.this.handleFormerSimpleStatement(object);
        }

        @Override
        public BitSet caseLocalVariableStatement(final LocalVariableStatement object) {
            return FunctionCallClassificationVisitor.this.handleFormerSimpleStatement(object);
        }
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
    private BitSet createBitSetLoop(final Statement loop, final Statement body) {
        if (this.annotations.containsKey(loop)) {
            return this.annotations.get(loop);
        }

        this.doSwitch(body);

        final BitSet myType = this.annotations.get(body);
        this.annotations.put(loop, myType);

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
    private BitSet handleFormerSimpleStatement(final Statement statement) {
        if (this.annotations.containsKey(statement)) {
            return this.annotations.get(statement);
        }

        final BitSet myType = this.myStrategy.classifySimpleStatement(statement);
        this.annotations.put(statement, myType);

        if (myType.get(getIndex(FunctionCallType.INTERNAL))) {
            // Also annotate the internal method
            final MethodCall functionAccess = this.getFunctionAccess(statement); // GAST2SEFFCHANGE
            final StatementListContainer targetFunctionBody = KDMHelper.getBody(KDMHelper.getMethod(functionAccess)); // GAST2SEFFCHANGE//GAST2SEFFCHANGE//GAST2SEFFCHANGE
            if (targetFunctionBody != null) {
                logger.trace("visiting internal call. accessed class: "
                        + GetAccessedType.getAccessedType(functionAccess)); // GAST2SEFFCHANGE
                this.doSwitch(targetFunctionBody);
            } else {
                logger.warn("Behaviour not set in GAST for " + KDMHelper.getMethod(functionAccess).getName()); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
            }
        }

        return myType;
    }

    private MethodCall getFunctionAccess(final Statement object) { // GAST2SEFFCHANGE//GAST2SEFFCHANGE
        for (final Commentable a : KDMHelper.getAllAccesses(object)) { // GAST2SEFFCHANGE//GAST2SEFFCHANGE
            if (a instanceof MethodCall) { // GAST2SEFFCHANGE
                return (MethodCall) a; // GAST2SEFFCHANGE
            }
        }
        return null;
    }

    private void safePut(final Statement object, final BitSet type) {
        if (!this.annotations.containsKey(object)) {
            this.annotations.put(object, type);
        }
    }

    private BitSet computeChildAnnotations(final BitSet initalValue, final List<Statement> childStatements) {
        // 1. visit all sub statements
        for (final Statement s : childStatements) {
            this.doSwitch(s);
        }

        // 2. compute own type iteratively
        final BitSet myType = initalValue;
        for (final Statement s : childStatements) {
            this.myStrategy.mergeFunctionCallType(myType, this.annotations.get(s));
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
        }
        throw new UnsupportedOperationException();
    }

    public Map<Statement, BitSet> getAnnotations() {
        return Collections.unmodifiableMap(this.annotations);
    }

    @Override
    public BitSet defaultCase(final EObject object) {
        logger.warn("Not handled object by function call visitor:\n  " + object);
        return super.defaultCase(object);
    }
}
