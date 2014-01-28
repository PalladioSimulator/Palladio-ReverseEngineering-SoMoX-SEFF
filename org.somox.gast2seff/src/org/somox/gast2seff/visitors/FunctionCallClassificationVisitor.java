package org.somox.gast2seff.visitors;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.util.JavaSwitch;
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
public class FunctionCallClassificationVisitor extends JavaSwitch<BitSet> {// GAST2SEFFCHANGE

    private static final Logger logger = Logger.getLogger(GastStatementVisitor.class);

    public FunctionCallClassificationVisitor(final IFunctionClassificationStrategy strategy) {
        super();

        this.myStrategy = strategy;
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

//    @Override
//    public BitSet caseStatement(final Statement object) {
//        this.safePut(object, new BitSet());
//        return new BitSet();
//    }
//
//    @Override
//    public BitSet caseBlock(final Block object) { // GAST2SEFFCHANGE
//        if (this.annotations.containsKey(object)) {
//            return this.annotations.get(object);
//        }
//
//        final BitSet myType = this.computeChildAnnotations(new BitSet(), object.getStatements());
//        this.annotations.put(object, myType);
//
//        return myType;
//    }
//
//    @Override
//    public BitSet caseIfStatement(final If object) {
//        if (this.annotations.containsKey(object)) {
//            return this.annotations.get(object);
//        }
//
//        this.doSwitch(object.getThenStatement());
//        if (object.getElseStatement() != null) {
//            this.doSwitch(object.getElseStatement());
//        }
//
//        final List<Statement> branchStatements = new ArrayList<Statement>();
//        branchStatements.add(object.getThenStatement());
//        if (object.getElseStatement() != null) {
//            branchStatements.add(object.getElseStatement());
//        }
//
//        final BitSet myType = this.computeChildAnnotations(new BitSet(), branchStatements);
//        this.annotations.put(object, myType);
//
//        return myType;
//    }
//
//    @Override
//    public BitSet caseSwitchStatement(final SwitchStatement switchStatement) {
//        if (this.annotations.containsKey(switchStatement)) {
//            return this.annotations.get(switchStatement);
//        }
//
//        final ArrayList<ArrayList<Statement>> branches = SwitchStatementHelper
//                .createBlockListFromSwitchStatement(switchStatement);
//
//        for (final ArrayList<Statement> branch : branches) {
//            this.computeChildAnnotations(new BitSet(), branch); // copied from the
//            // BlockCase
//        }
//
//        final List<Statement> branchStatements = new ArrayList<Statement>();
//        for (final ArrayList<Statement> branch : branches) {
//            branchStatements.addAll(branch);
//        }
//        final BitSet myType = this.computeChildAnnotations(new BitSet(), branchStatements);
//        this.annotations.put(switchStatement, myType);
//
//        return myType;
//    }

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

//    @Override
//    public BitSet caseEnhancedForStatement(final EnhancedForStatement object) {
//        return this.createBitSetLoop(object, object.getBody());
//    }
//
//    @Override
//    public BitSet caseForStatement(final ForStatement object) {
//        return this.createBitSetLoop(object, object.getBody());
//    }
//
//    @Override
//    public BitSet caseWhileStatement(final WhileStatement object) {
//        return this.createBitSetLoop(object, object.getBody());
//    }
//
//    @Override
//    public BitSet caseTryStatement(final TryStatement object) { // GAST2SEFFCHANGE//GAST2SEFFCHANGE
//        if (this.annotations.containsKey(object)) {
//            return this.annotations.get(object);
//        }
//        final List<Statement> allChildStatements = new ArrayList<Statement>();
//
//        // handle guarded block
//        this.doSwitch(object.getBody()); // GAST2SEFFCHANGE
//        allChildStatements.addAll(object.getBody().getStatements()); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
//
//        // handle finally block
//        if (object.getFinally() != null) { // GAST2SEFFCHANGE
//            this.doSwitch(object.getFinally()); // GAST2SEFFCHANGE
//            allChildStatements.addAll(object.getFinally().getStatements()); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
//        }
//
//        final BitSet myType = this.computeChildAnnotations(new BitSet(), allChildStatements);
//
//        this.annotations.put(object, myType);
//
//        return myType;
//    }
//
//    @Override
//    public BitSet caseAssertStatement(final AssertStatement object) {
//        return this.handleFormerSimpleStatement(object);
//    }
//
//    @Override
//    public BitSet caseExpressionStatement(final ExpressionStatement object) {
//        return this.handleFormerSimpleStatement(object);
//    }
//
//    @Override
//    public BitSet caseVariableDeclarationStatement(final VariableDeclarationStatement object) {
//        return this.handleFormerSimpleStatement(object);
//    }

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
            final Block targetFunctionBody =   KDMHelper.getMethod(functionAccess).getBody(); // GAST2SEFFCHANGE//GAST2SEFFCHANGE//GAST2SEFFCHANGE
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
