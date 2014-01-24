package org.somox.gast2seff.visitors;



import java.util.ArrayList;
import java.util.BitSet;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.internal.resources.VariableDescription;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Switch;
//import org.eclipse.gmt.modisco.java.ASTNode;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.impl.*;
import org.emftext.language.java.statements.*;
import org.emftext.language.java.util.JavaSwitch;
//import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
//import org.eclipse.gmt.modisco.java.AssertStatement;
//import org.emftext.language.java.statements.Block;
//import org.eclipse.gmt.modisco.java.EnhancedForStatement;
//import org.eclipse.gmt.modisco.java.ExpressionStatement;
//import org.eclipse.gmt.modisco.java.ForStatement;
//import org.eclipse.gmt.modisco.java.IfStatement;
//
//import org.eclipse.gmt.modisco.java.SwitchStatement;
//import org.eclipse.gmt.modisco.java.TryStatement;
//import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
//import org.eclipse.gmt.modisco.java.WhileStatement;
//import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
//
//import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.somox.gast2seff.jobs.GAST2SEFFJob;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor.FunctionCallType;
import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.KDMHelper;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.OperationRequiredRole;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.pcm.repository.RequiredRole;
import de.uka.ipd.sdq.pcm.repository.Role;
import de.uka.ipd.sdq.pcm.repository.Signature;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.seff.InternalAction;
import de.uka.ipd.sdq.pcm.seff.SeffFactory;

//import eu.qimpress.samm.staticstructure.InterfacePort;
//import eu.qimpress.samm.staticstructure.Operation;
//import eu.qimpress.samm.staticstructure.PrimitiveComponent;
//import eu.qimpress.seff.AbstractBranchTransition;
//import eu.qimpress.seff.BranchAction;
//import eu.qimpress.seff.ExternalCallAction;
//import eu.qimpress.seff.InternalAction;
//import eu.qimpress.seff.LoopAction;
//import eu.qimpress.seff.ResourceDemandingBehaviour;
//import eu.qimpress.seff.seffFactory;
//import de.fzi.gast.accesses.Access;//GAST2SEFFCHANGE
//import de.fzi.gast.accesses.BaseAccess;//GAST2SEFFCHANGE
//import de.fzi.gast.accesses.FunctionAccess;//GAST2SEFFCHANGE
//import de.fzi.gast.core.Position;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.BlockStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.Branch;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.BranchStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.LoopStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.SimpleStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.Statement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.util.statementsSwitch;//GAST2SEFFCHANGE

/**
 * A visitor which traverses a GAST behaviour and creates a SEFF matching the traversed behaviour.
 * The generated SEFF is abstracted based on a classification of the GAST statements into external
 * and internal service calls. <br>
 * <code>functionClassificationAnnotation</code> classifies which elements to hold when traversing
 * the GAST behaviour.
 * 
 * @author Steffen Becker, Klaus Krogmann
 */
public class GastStatementVisitor extends JavaSwitch<Object> {// GAST2SEFFCHANGE

    private static final Logger logger = Logger.getLogger(GastStatementVisitor.class);

    /**
     * The RD-Behaviour to generate
     */
    private final de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour seff;

    /**
     * Mapping to SAMM repository (for external call lookup)
     */
    private final SourceCodeDecoratorRepository sourceCodeDecoratorRepository;

    /**
     * The component the created SEFF belongs to.
     */
    private final BasicComponent primitiveComponent;

    /**
     * Map which contains for each statement in the GAST model the type of the statement classified
     * according to {@link FunctionCallType}. Nodes of control flow constructs like loops and
     * branches carry the union of the annotations of their child statements
     */
    private final Map<Statement, BitSet> functionClassificationAnnotation;

    /**
     * Classification annotation of the last visited statement. Used to skip generating SEFF actions
     * if they should be omitted because of the SEFFs abstraction rule
     */
    private BitSet lastType = null;

    /**
     * Constructor
     * 
     * @param functionClassificationAnnotations
     *            A map containing the type annotations for the nodes of the GAST model. Generated
     *            by a {@link FunctionCallClassificationVisitor}.
     * @param resourceDemandingBehaviour
     *            The RD-behaviour to generate
     * @param gastBehaviourRepository
     *            The gast behaviour which maps gast statements and SAMM repository.
     * @param primitiveComponent
     */
    public GastStatementVisitor(final Map<Statement, BitSet> functionClassificationAnnotations,
			final de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour resourceDemandingBehaviour,
            final SourceCodeDecoratorRepository gastBehaviourRepository, final BasicComponent primitiveComponent) {
        super();

        this.seff = resourceDemandingBehaviour;
        this.functionClassificationAnnotation = functionClassificationAnnotations;
        this.sourceCodeDecoratorRepository = gastBehaviourRepository;
        this.primitiveComponent = primitiveComponent;
    }

    public Object caseBlock(final Block object) { // GAST2SEFFCHANGE//GAST2SEFFCHANGE
        for (final Statement s : object.getStatements()) {
            final BitSet thisType = this.functionClassificationAnnotation.get(s);
            if (!this.shouldSkip(this.lastType, thisType)) { // Only generate elements for
                                                             // statements which should not be
                                                             // abstracted away
                // avoid infinite recursion
                if (!this.isVisitedStatement(thisType)) {
                    this.setVisited(thisType);
                    this.doSwitch(s);
                }
            }
            this.lastType = thisType;
        }
        return null;
    }

   // @Override
    public Object caseSwitchStatement(final Switch switchStatement) {
        if (this.containsExternalCall(switchStatement)) {
            final de.uka.ipd.sdq.pcm.seff.BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
            this.seff.getSteps_Behaviour().add(branchAction);
            branchAction.setEntityName(this.positionToString(KDMHelper.getJavaNodeSourceRegion(switchStatement)));
            // TODO DONE TEST
//            branchAction.setDocumentation(statementsToString(switchStatement.getStatements()));
//            branchAction.set("created from a switch statement");

            final ArrayList<ArrayList<Statement>> branches = SwitchStatementHelper
                    .createBlockListFromSwitchStatement(switchStatement);

            for (final ArrayList<Statement> branch : branches) {
                final de.uka.ipd.sdq.pcm.seff.AbstractBranchTransition bt = SeffFactory.eINSTANCE.createProbabilisticBranchTransition();
                bt.setBranchBehaviour_BranchTransition(SeffFactory.eINSTANCE.createResourceDemandingBehaviour());
                bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStartAction());
                bt.setEntityName("parent "
                        + this.positionToString(KDMHelper.getJavaNodeSourceRegion(switchStatement))
                        + "/"
                        + (branch.size() > 0 ? this.positionToLineNumber(KDMHelper.getJavaNodeSourceRegion(branch
                                .get(0)))
                                + " to "
                                + this.positionToLineNumber(KDMHelper.getJavaNodeSourceRegion(branch.get(branch.size() - 1)))
                                : "")); // use parent position since branch position is empty
                branchAction.getBranches_Branch().add(bt);
                final GastStatementVisitor visitor = new GastStatementVisitor(this.functionClassificationAnnotation,
                        bt.getBranchBehaviour_BranchTransition(), this.sourceCodeDecoratorRepository, this.primitiveComponent);
                // Statement s = b.getStatement();
                // visitor.doSwitch(s);

                for (final Statement statement : branch) {
                    // copied from caseBlock
                    final BitSet thisType = this.functionClassificationAnnotation.get(statement);
                    if (!this.shouldSkip(this.lastType, thisType)) { // Only generate elements for
                                                                     // statements which should not
                                                                     // be abstracted away
                        // avoid infinite recursion
                        // if(!isVisitedStatement(thisType)) {
                        // setVisited(thisType);
                        // visitor.doSwitch(statement);//here visitor. was added in contrast to
                        // caseBlock
                        // }
                        // TODO the four lines above were temporarily removed
                        // in order to allow a a multiple use of a statement
                        // because of the new behaviour for switch statements (case without break)
                        visitor.doSwitch(statement); // here visitor. was added in contrast to
                                                     // caseBlock
                    }
                    this.lastType = thisType;
                    // end of copy
                }

                bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
                GAST2SEFFJob.connectActions(bt.getBranchBehaviour_BranchTransition());
            }
        } else {
            this.createInternalAction(switchStatement);
        }
        return null;
    }


   // @Override
    //Condition statt IfStatement
    public Object caseIfStatement(final Condition input) {
        if (this.containsExternalCall(input)) {
            final de.uka.ipd.sdq.pcm.seff.BranchAction branch = SeffFactory.eINSTANCE.createBranchAction();
            this.seff.getSteps_Behaviour().add(branch);
            branch.setEntityName(this.positionToString(KDMHelper.getJavaNodeSourceRegion(input))); // GAST2SEFFCHANGE

            final Statement ifStatement = input.getStatement();
            this.handleIfOrElseBranch(input, branch, ifStatement);

            final Statement elseStatement = input.getElseStatement();
            if (elseStatement != null) {
                this.handleIfOrElseBranch(input, branch, elseStatement);
            }
        } else {
            this.createInternalAction(input);
        }
        return null;
    }

    /**
     * Handles the branch/block of an if or else block.
     * 
     * @param input
     *            the whole IfStatement
     * @param branch
     *            the branchAction(SEFF)
     * @param ifElseStatement
     *            the if or else Statement/Block
     */
    private void handleIfOrElseBranch(final Condition input, final de.uka.ipd.sdq.pcm.seff.BranchAction branch,
            final Statement ifElseStatement) {
        final de.uka.ipd.sdq.pcm.seff.AbstractBranchTransition bt = SeffFactory.eINSTANCE.createProbabilisticBranchTransition();
        bt.setBranchBehaviour_BranchTransition(SeffFactory.eINSTANCE.createResourceDemandingBehaviour());
        bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStartAction());
        bt.setEntityName("parent " + this.positionToString(KDMHelper.getJavaNodeSourceRegion(input)) + "/"
                + this.positionToString(KDMHelper.getJavaNodeSourceRegion(ifElseStatement))); 
		// use parent position since branch position is empty//GAST2SEFFCHANGE//GAST2SEFFCHANGE
        branch.getBranches_Branch().add(bt);
        final GastStatementVisitor visitor = new GastStatementVisitor(this.functionClassificationAnnotation,
                bt.getBranchBehaviour_BranchTransition(), this.sourceCodeDecoratorRepository, this.primitiveComponent);
        // Statement s = b.getStatement();//GAST2SEFFCHANGE
        visitor.doSwitch(ifElseStatement);
        bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
        GAST2SEFFJob.connectActions(bt.getBranchBehaviour_BranchTransition());
    }

    //@Override
    public Object caseEnhancedForStatement(final ForLoop object) {
        return this.handleLoopStatement(object, object.getStatement());
    }

   // @Override
    public Object caseForStatement(final ForLoop object) {//getStatement() statt getBody
        return this.handleLoopStatement(object, object.getStatement());
    }

   // @Override
    public Object caseWhileStatement(final WhileLoop object) {
        return this.handleLoopStatement(object, object.getStatement());
    }

    /**
     * Handles loop statement. In the SISSy metamodel there was only one metamodel element, in the
     * MoDisco Java there are three.
     * 
     * @param loopStatement
     *            the loop statement
     * @param body
     *            the body of the loop statement
     * @return
     */
    private Object handleLoopStatement(final Statement loopStatement, final Statement body) {
        if (this.containsExternalCall(loopStatement)) {
            final de.uka.ipd.sdq.pcm.seff.LoopAction loop = SeffFactory.eINSTANCE.createLoopAction();
            loop.setBodyBehaviour_Loop(SeffFactory.eINSTANCE.createResourceDemandingBehaviour());
            this.seff.getSteps_Behaviour().add(loop);
            loop.getBodyBehaviour_Loop().getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStartAction());
            loop.setEntityName(this.positionToString(KDMHelper.getJavaNodeSourceRegion(loopStatement))); // GAST2SEFFCHANGE
            // TODO DONE
//            loop.setDocumentation(blockToString(body));
//            loop.setDocumentation("created from a loop statement");

            new GastStatementVisitor(this.functionClassificationAnnotation, loop.getBodyBehaviour_Loop(),
                    this.sourceCodeDecoratorRepository, this.primitiveComponent).doSwitch(body);

            loop.getBodyBehaviour_Loop().getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
            GAST2SEFFJob.connectActions(loop.getBodyBehaviour_Loop());
        } else {
            this.createInternalAction(loopStatement);
        }
        return null;
    }

   // @Override
    public Object caseTryStatement(final TryBlock object) { // GAST2SEFFCHANGE
        if (this.containsExternalCall(object)) {

            // visit guarded block
            new GastStatementVisitor(this.functionClassificationAnnotation, this.seff,
                    this.sourceCodeDecoratorRepository, this.primitiveComponent).doSwitch((EObject) object.getStatements()); // GAST2SEFFCHANGE

            // visit finally block if exists
            if (object.getFinallyBlock()!= null) { // GAST2SEFFCHANGE
                new GastStatementVisitor(this.functionClassificationAnnotation, this.seff,
                        this.sourceCodeDecoratorRepository, this.primitiveComponent).doSwitch(object.getFinallyBlock()); // GAST2SEFFCHANGE
            }

        } else {
            this.createInternalAction(object);
        }
        return null;
    }

    // @Override
    // public Object caseSimpleStatement(SimpleStatement object) {
    // BitSet statementAnnotation = this.functionClassificationAnnotation.get(object);
    // if (isExternalCall(statementAnnotation)) {
    // createExternalCallAction(object);
    // } else if (isInternalCall(statementAnnotation)) {
    // AbstractMethodInvocation functionAccess = getFunctionAccess(object);//GAST2SEFFCHANGE
    // Block body =
    // functionAccess.getMethod().getBody();//GAST2SEFFCHANGE//GAST2SEFFCHANGE//GAST2SEFFCHANGE
    // if (body != null) {
    //
    // // avoid infinite recursion
    // BitSet thisType = this.functionClassificationAnnotation.get(object);
    // if(!isVisitedStatement(thisType)) {
    // setVisited(thisType);
    // doSwitch(body);
    // }
    // } else {
    // String msg =
    // "Behaviour not set in GAST for "+functionAccess.getMethod().getName();//GAST2SEFFCHANGE//GAST2SEFFCHANGE
    // if(KDMHelper.getJavaNodeSourceRegion(object) != null &&
    // KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(object)) != null &&
    // KDMHelper.computeFullQualifiedName(KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(object)))
    // != null)
    // {//GAST2SEFFCHANGE////GAST2SEFFCHANGE////GAST2SEFFCHANGE////GAST2SEFFCHANGE////GAST2SEFFCHANGE////GAST2SEFFCHANGE//
    // msg += ". Tried to call from " +
    // KDMHelper.computeFullQualifiedName(KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(object)))
    // + ".";//GAST2SEFFCHANGE////GAST2SEFFCHANGE////GAST2SEFFCHANGE//
    // } else {
    // msg += ". (caller position unknown)";
    // }
    // logger.warn(msg);
    // }
    // } else {
    // createInternalAction(object);
    // }
    // return null;
    // }

    //@Override
    public Object caseAssertStatement(final Assert object) {
        return this.handleFormerSimpleStatement(object);
    }

    // TODO add path and name for "tried to call" line
    private Object handleFormerSimpleStatement(final Statement object) {
        final BitSet statementAnnotation = this.functionClassificationAnnotation.get(object);
        if (this.isExternalCall(statementAnnotation)) {
            this.createExternalCallAction(object);
        } else if (this.isInternalCall(statementAnnotation)) {
            final MethodCall functionAccess = this.getFunctionAccess(object); // GAST2SEFFCHANGE
            final Block body = functionAccess.getMethod().getBody(); // GAST2SEFFCHANGE//GAST2SEFFCHANGE//GAST2SEFFCHANGE
            if (body != null) {

                // avoid infinite recursion
                final BitSet thisType = this.functionClassificationAnnotation.get(object);
                if (!this.isVisitedStatement(thisType)) {
                    this.setVisited(thisType);
                    this.doSwitch(body);
                }
            } else {
                String msg = "Behaviour not set in GAST for " + functionAccess.getMethod().getName(); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
                if (KDMHelper.getJavaNodeSourceRegion(object) != null
                        && KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(object)) != null
                        && (KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(object))).getPath() != null) { // GAST2SEFFCHANGE////GAST2SEFFCHANGE////GAST2SEFFCHANGE////GAST2SEFFCHANGE////GAST2SEFFCHANGE////GAST2SEFFCHANGE//
                    msg += ". Tried to call from "
                            + (KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(object))).getPath() + "."; // GAST2SEFFCHANGE////GAST2SEFFCHANGE////GAST2SEFFCHANGE//
                } else {
                    msg += ". (caller position unknown)";
                }
                logger.warn(msg);
            }
        } else {
            this.createInternalAction(object);
        }
        return null;
    }

    //@Override
    public Object caseExpressionStatement(final ExpressionStatement object) {
        return this.handleFormerSimpleStatement(object);
    }

    //@Override
    public Object caseVariableDeclarationStatement(final LocalVariableStatement object) {
        return this.handleFormerSimpleStatement(object);
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
    private boolean shouldSkip(final BitSet lastType, final BitSet thisType) {
        if (lastType == null) {
            return false;
        }

        if (this.isExternalCall(thisType)) {
            return false;
        }

        // Here I know that thisType is internal or library
        // Hence, I can skip this if the last type was not an external call
        return !this.isExternalCall(lastType);
    }

    private void createExternalCallAction(final Statement object) {// GAST2SEFFCHANGE
		final ExternalCallAction call = SeffFactory.eINSTANCE.createExternalCallAction();
        final MethodCall access = this.getFunctionAccess(object); // GAST2SEFFCHANGE
        call.setEntityName(access.getMethod().getName()); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
        final InterfacePortOperationTuple ifOperationTuple = this.getCalledInterfacePort(access);
        call.setRole_ExternalService((OperationRequiredRole) ifOperationTuple.role);
        call.setCalledService_ExternalService((OperationSignature) ifOperationTuple.signature);
//        call.setDocumentation(this.positionToString(KDMHelper.getJavaNodeSourceRegion(object))); // GAST2SEFFCHANGE
        this.seff.getSteps_Behaviour().add(call);
    }

    /**
     * Query the interface port for the function access using the source code decorator.
     * 
     * @param access
     *            The access to find in the SAMM
     * @return interface port and operation for corresponding to the access.
     */
    private InterfacePortOperationTuple getCalledInterfacePort(final MethodCall access) { // GAST2SEFFCHANGE

		final InterfacePortOperationTuple interfacePortOperationTuple = new InterfacePortOperationTuple();

		for (final RequiredRole requiredRole : primitiveComponent.getRequiredRoles_InterfaceRequiringEntity()) {
			for (final InterfaceSourceCodeLink ifLink : sourceCodeDecoratorRepository.getInterfaceSourceCodeLink()) {
				if (requiredRole instanceof OperationRequiredRole) {
					OperationRequiredRole operReqRole = (OperationRequiredRole) requiredRole;
					if (operReqRole.getRequiredInterface__OperationRequiredRole().equals(ifLink.getInterface())) {
						if (ifLink.getGastClass().equals(GetAccessedType.getAccessedType(access))) { // GAST2SEFFCHANGE

							logger.trace("accessed interface port " + operReqRole.getEntityName());
							interfacePortOperationTuple.role = operReqRole;
							// query operation:
							interfacePortOperationTuple.signature = queryInterfaceOperation(access);

							return interfacePortOperationTuple;
						}
					}
				}
			}
		}

        logger.warn("found no if port for " + GetAccessedType.getAccessedType(access).toString()); // GAST2SEFFCHANGE//GAST2SEFFCHANGE

        return interfacePortOperationTuple;
    }

    /**
	 * Signature query
	 * 
	 * @param methodInvocation
	 *            The method invocation to find in the SAMM
	 * @return Signature corresponding to function access
	 */
    private Signature queryInterfaceOperation(final MethodCall methodInvocation) { // GAST2SEFFCHANGE

		for (final MethodLevelSourceCodeLink methodLink : this.sourceCodeDecoratorRepository
                .getMethodLevelSourceCodeLink()) {

            if (methodLink.getFunction().equals(methodInvocation.getMethod())) { // GAST2SEFFCHANGE

                logger.trace("accessed operation " + methodLink.getOperation().getEntityName());
                return methodLink.getOperation();
            }
        }

        logger.warn("no accessed operation found for " + methodInvocation.getMethod().getName()); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
        return null;
    }

    private boolean isExternalCall(final BitSet statementAnnotation) {
        return statementAnnotation.get(FunctionCallClassificationVisitor.getIndex(FunctionCallType.EXTERNAL));
    }

    private boolean isInternalCall(final BitSet statementAnnotation) {
        return statementAnnotation.get(FunctionCallClassificationVisitor.getIndex(FunctionCallType.INTERNAL));
    }

    private boolean isVisitedStatement(final BitSet statementAnnotation) {
        return statementAnnotation.get(FunctionCallClassificationVisitor.getIndex(FunctionCallType.VISITED));
    }

    private void setVisited(final BitSet thisType) {
        thisType.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.VISITED), true);

    }

    private MethodCall getFunctionAccess(final Statement object) { // GAST2SEFFCHANGE//GAST2SEFFCHANGE
        for (final Commentable a : KDMHelper.getAllAccesses(object)) { // GAST2SEFFCHANGE//GAST2SEFFCHANGE
            if (a instanceof MethodCall) { // GAST2SEFFCHANGE
                return (MethodCall) a; // GAST2SEFFCHANGE
            }
        }
        return null;
    }

    private void createInternalAction(final Statement statement) {
		final InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();

        internalAction.setEntityName("IA " + this.positionToString(KDMHelper.getJavaNodeSourceRegion(statement))); // GAST2SEFFCHANGE
        // TODO
//        if (statement instanceof Block) { // GAST2SEFFADDED
//            ia.setDocumentation(this.blockToString((Block) statement) + "; Statement SISSyID: "
//                    + KDMHelper.getSISSyID(statement)); // GAST2SEFFCHANGE
//        } else { // GAST2SEFFADDED
//            ia.setDocumentation("not a block" + "; Statement SISSyID: " + KDMHelper.getSISSyID(statement)); // GAST2SEFFCHANGE//GAST2SEFFADDED
//        } // GAST2SEFFADDED
        this.seff.getSteps_Behaviour().add(internalAction);
    }

    private String blockToString(final Block blockstatement) { // GAST2SEFFCHANGE
        if (blockstatement != null) {
            final StringBuilder blockString = new StringBuilder("Block: ");
            blockString.append(blockstatement.toString());
            if (KDMHelper.getAllAccesses(blockstatement) != null && // GAST2SEFFCHANGE
                    KDMHelper.getAllAccesses(blockstatement).size() >= 1// GAST2SEFFCHANGE
            ) {
                final Commentable firstAccess = KDMHelper.getAllAccesses(blockstatement).get(0); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
                if (firstAccess instanceof Commentable) { // GAST2SEFFCHANGE
                    final Commentable access = firstAccess; // GAST2SEFFCHANGE//GAST2SEFFCHANGE

                    if (GetAccessedType.getAccessedType(access) != null) { // GAST2SEFFCHANGE
                        blockString.append(" " + KDMHelper.getName(GetAccessedType.getAccessedType(access)) + "..."); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
                    }
                }
            }
            return blockString.toString();
        } else {
            return "No blockstatement";
        }
    }

    private String statementsToString(final EList<Statement> statements) { // GAST2SEFFCHANGE
        if (statements != null) {
            final StringBuilder blockString = new StringBuilder("Block: ");
            blockString.append(statements.toString());

            for (Statement statement : statements) {

                if (KDMHelper.getAllAccesses(statement) != null && // GAST2SEFFCHANGE
                        KDMHelper.getAllAccesses(statement).size() >= 1// GAST2SEFFCHANGE
                        ) {
                    final Commentable firstAccess = KDMHelper.getAllAccesses(statement).get(0); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
                    if (firstAccess instanceof Commentable) { // GAST2SEFFCHANGE
                        final Commentable access = firstAccess; // GAST2SEFFCHANGE//GAST2SEFFCHANGE

                        if (GetAccessedType.getAccessedType(access) != null) { // GAST2SEFFCHANGE
                        	//toString() statt getName
                            blockString.append(" " + GetAccessedType.getAccessedType(access).toString()+ "..."); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
                        }
                        return blockString.toString();
                    }
                }
            }
            return blockString.toString();
            

        } else {
            return "No blockstatement";
        }
    }

    private String positionToString(final JavaNodeSourceRegion position) { // GAST2SEFFCHANGE
        final StringBuilder positionString = new StringBuilder("position: ");
        if (position != null) {
            if (KDMHelper.getSourceFile(position) != null && KDMHelper.getSourceFile(position).getClass() != null) { // GAST2SEFFCHANGE//GAST2SEFFCHANGE
                // TODO change name of class; question: is fqnName of Class better than path?
                // positionString.append(KDMHelper.getSourceFile(position).getPath() +
                // KDMHelper.getSourceFile(position).getName());//GAST2SEFFCHANGE
                positionString.append(KDMHelper.computeFullQualifiedName(position.getJavaNode())); // GAST2SEFFCHANGE
            }
			// TODO burkha 24.04.2013 If numbers are equal than write only one
            positionString.append(" from " + position.getStartLine());
            positionString.append(" to " + position.getEndLine());
        } else {
            positionString.append("no position information available");
        }
        return positionString.toString();
    }

    private String positionToLineNumber(final JavaNodeSourceRegion position)  {// GAST2SEFFCHANGE
        final StringBuilder positionString = new StringBuilder("line ");
        if (position != null) {
            positionString.append(position.getStartLine());
        } else {
            positionString.append("no position information available");
        }
        return positionString.toString();
    }

    /**
     * Returns true if the statement or one of its child statements (e.g., for loops or branches) is
     * an external service call
     * 
     * @param object
     *            The statement to check
     * @return true if the statement or one of its child statements is an external service call
     */
    private boolean containsExternalCall(final Statement object) {
        return this.functionClassificationAnnotation.get(object).get(
                FunctionCallClassificationVisitor.getIndex(FunctionCallType.EXTERNAL));
    }

    private class InterfacePortOperationTuple {
        public Role role;
        public Signature signature;
    }

    // TODO
    @Override
    public Object defaultCase(final EObject object) {
        logger.warn("Not handled object by statement visitor:\n  " + object);
        return super.defaultCase(object);
    }
}
