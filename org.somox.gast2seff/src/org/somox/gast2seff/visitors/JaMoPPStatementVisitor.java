package org.somox.gast2seff.visitors;

import java.util.BitSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.TryBlock;
import org.somox.gast2seff.jobs.GAST2SEFFJob;
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

/**
 * A visitor which traverses a GAST behaviour and creates a SEFF matching the traversed behaviour.
 * The generated SEFF is abstracted based on a classification of the GAST statements into external
 * and internal service calls. <br>
 * <code>functionClassificationAnnotation</code> classifies which elements to hold when traversing
 * the GAST behaviour.
 *
 * @author Steffen Becker, Klaus Krogmann
 */
public class JaMoPPStatementVisitor extends AbstractJaMoPPStatementVisitor {

    private static final Logger logger = Logger.getLogger(JaMoPPStatementVisitor.class);

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
    public JaMoPPStatementVisitor(final Map<Commentable, BitSet> functionClassificationAnnotations,
            final de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour resourceDemandingBehaviour,
            final SourceCodeDecoratorRepository gastBehaviourRepository, final BasicComponent primitiveComponent) {
        super(functionClassificationAnnotations);

        this.seff = resourceDemandingBehaviour;
        this.sourceCodeDecoratorRepository = gastBehaviourRepository;
        this.primitiveComponent = primitiveComponent;
    }

    @Override
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

    /**
     * Handles loop statement. In the SISSy metamodel there was only one metamodel element, in the
     * JaMoPP Java there are three.
     *
     * @param loopStatement
     *            the loop statement
     * @param body
     *            the body of the loop statement
     * @return
     */
    @Override
    protected Object handleLoopStatement(final Statement loopStatement, final Statement body) {
        if (this.containsExternalCall(loopStatement)) {
            final de.uka.ipd.sdq.pcm.seff.LoopAction loop = SeffFactory.eINSTANCE.createLoopAction();
            loop.setBodyBehaviour_Loop(SeffFactory.eINSTANCE.createResourceDemandingBehaviour());
            this.seff.getSteps_Behaviour().add(loop);
            loop.getBodyBehaviour_Loop().getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStartAction());
            loop.setEntityName(this.positionToString(loopStatement));

            new JaMoPPStatementVisitor(this.functionClassificationAnnotation, loop.getBodyBehaviour_Loop(),
                    this.sourceCodeDecoratorRepository, this.primitiveComponent).doSwitch(body);

            loop.getBodyBehaviour_Loop().getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
            GAST2SEFFJob.connectActions(loop.getBodyBehaviour_Loop());
        } else {
            this.createInternalAction(loopStatement);
        }
        return new Object();
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

    // TODO add path and name for "tried to call" line
    @Override
    protected Object handleFormerSimpleStatement(final Statement object) {
        final BitSet statementAnnotation = this.functionClassificationAnnotation.get(object);
        if (this.isExternalCall(statementAnnotation)) {
            this.createExternalCallAction(object);
        } else if (this.isInternalCall(statementAnnotation)) {
            final Method method = VisitorUtils.getMethodCall(object);
            if (!(method instanceof ClassMethod)) {
                logger.error("Referenceable element must be a class method");
            } else {
                final ClassMethod classMethod = (ClassMethod) method;

                if (classMethod.getStatements() != null) {

                    // avoid infinite recursion
                    final BitSet thisType = this.functionClassificationAnnotation.get(object);
                    if (!this.isVisitedStatement(thisType)) {
                        this.setVisited(thisType);
                        this.doSwitch(method);
                    }
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
            this.createInternalAction(object);
        }
        return new Object();
    }

    @Override
    protected Object handleSwitch(final Switch switchStatement) {
        if (super.containsExternalCall(switchStatement)) {
            final de.uka.ipd.sdq.pcm.seff.BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
            this.seff.getSteps_Behaviour().add(branchAction);
            branchAction.setEntityName(JaMoPPStatementVisitor.this.positionToString(switchStatement));

            final List<List<Statement>> branches = SwitchStatementHelper
                    .createBlockListFromSwitchStatement(switchStatement);

            for (final List<Statement> branch : branches) {
                final de.uka.ipd.sdq.pcm.seff.AbstractBranchTransition bt = SeffFactory.eINSTANCE
                        .createProbabilisticBranchTransition();
                bt.setBranchBehaviour_BranchTransition(SeffFactory.eINSTANCE.createResourceDemandingBehaviour());
                bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour()
                .add(SeffFactory.eINSTANCE.createStartAction());
                bt.setEntityName("parent "
                        + JaMoPPStatementVisitor.this.positionToString(switchStatement)
                        + "/"
                        + (branch.size() > 0 ? JaMoPPStatementVisitor.this.positionToLineNumber(KDMHelper
                                .getJavaNodeSourceRegion(branch.get(0)))
                                + " to "
                                // use parent position since branch position is empty
                                + JaMoPPStatementVisitor.this.positionToLineNumber(KDMHelper
                                        .getJavaNodeSourceRegion(branch.get(branch.size() - 1))) : ""));
                branchAction.getBranches_Branch().add(bt);
                final AbstractJaMoPPStatementVisitor visitor = new JaMoPPStatementVisitor(
                        JaMoPPStatementVisitor.this.functionClassificationAnnotation,
                        bt.getBranchBehaviour_BranchTransition(),
                        JaMoPPStatementVisitor.this.sourceCodeDecoratorRepository,
                        JaMoPPStatementVisitor.this.primitiveComponent);
                // Statement s = b.getStatement();
                // visitor.doSwitch(s);

                for (final Statement statement : branch) {
                    // copied from caseBlock
                    final BitSet thisType = JaMoPPStatementVisitor.this.functionClassificationAnnotation.get(statement);
                    if (!JaMoPPStatementVisitor.this.shouldSkip(JaMoPPStatementVisitor.this.lastType, thisType)) { // Only
                        // generate elements for statements which should not be abstracted away
                        // avoid infinite recursion
                        // if(!isVisitedStatement(thisType)) {
                        // setVisited(thisType);
                        // visitor.doSwitch(statement);//here visitor. was added in contrast to
                        // caseBlock
                        // }
                        // TODO the four lines above were temporarily removed
                        // in order to allow a a multiple use of a statement
                        // because of the new behaviour for switch statements (case without
                        // break)
                        visitor.doSwitch(statement); // here visitor. was added in contrast to
                        // caseBlock
                    }
                    this.lastType = thisType;
                    // end of copy
                }

                bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour()
                .add(SeffFactory.eINSTANCE.createStopAction());
                GAST2SEFFJob.connectActions(bt.getBranchBehaviour_BranchTransition());
            }
        } else {
            JaMoPPStatementVisitor.this.createInternalAction(switchStatement);
        }
        return new Object();
    }

    @Override
    protected Object handleTryBlock(final TryBlock tryBlock) {
        if (super.containsExternalCall(tryBlock)) {

            // visit guarded block
            final AbstractJaMoPPStatementVisitor visitor = new JaMoPPStatementVisitor(
                    JaMoPPStatementVisitor.this.functionClassificationAnnotation, JaMoPPStatementVisitor.this.seff,
                    JaMoPPStatementVisitor.this.sourceCodeDecoratorRepository,
                    JaMoPPStatementVisitor.this.primitiveComponent);
            for (final Statement statement : tryBlock.getStatements()) {
                visitor.doSwitch(statement);
            }

            // TODO:we do not visit catch block?

            // visit finally block if exists
            if (tryBlock.getFinallyBlock() != null) {
                new JaMoPPStatementVisitor(JaMoPPStatementVisitor.this.functionClassificationAnnotation,
                        JaMoPPStatementVisitor.this.seff, JaMoPPStatementVisitor.this.sourceCodeDecoratorRepository,
                        JaMoPPStatementVisitor.this.primitiveComponent).doSwitch(tryBlock.getFinallyBlock());
            }
        } else {
            this.createInternalAction(tryBlock);
        }
        return new Object();
    }

    @Override
    protected Object handleCondition(final Condition condition) {
        if (JaMoPPStatementVisitor.this.containsExternalCall(condition)) {
            final de.uka.ipd.sdq.pcm.seff.BranchAction branch = SeffFactory.eINSTANCE.createBranchAction();
            JaMoPPStatementVisitor.this.seff.getSteps_Behaviour().add(branch);
            branch.setEntityName(JaMoPPStatementVisitor.this.positionToString(condition)); // GAST2SEFFCHANGE

            final Statement ifStatement = condition.getStatement();
            this.handleIfOrElseBranch(condition, branch, ifStatement);

            final Statement elseStatement = condition.getElseStatement();
            if (elseStatement != null) {
                this.handleIfOrElseBranch(condition, branch, elseStatement);
            }
        } else {
            JaMoPPStatementVisitor.this.createInternalAction(condition);
        }
        return new Object();
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
        final de.uka.ipd.sdq.pcm.seff.AbstractBranchTransition bt = SeffFactory.eINSTANCE
                .createProbabilisticBranchTransition();
        bt.setBranchBehaviour_BranchTransition(SeffFactory.eINSTANCE.createResourceDemandingBehaviour());
        bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStartAction());
        bt.setEntityName("parent " + this.positionToString(input) + "/" + this.positionToString(ifElseStatement));
        // use parent position since branch position is empty//GAST2SEFFCHANGE//GAST2SEFFCHANGE
        branch.getBranches_Branch().add(bt);
        final AbstractJaMoPPStatementVisitor visitor = new JaMoPPStatementVisitor(
                this.functionClassificationAnnotation, bt.getBranchBehaviour_BranchTransition(),
                this.sourceCodeDecoratorRepository, this.primitiveComponent);
        // Statement s = b.getStatement();//GAST2SEFFCHANGE
        visitor.doSwitch(ifElseStatement);
        bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
        GAST2SEFFJob.connectActions(bt.getBranchBehaviour_BranchTransition());
    }

    private void createExternalCallAction(final Statement object) {
        final ExternalCallAction call = SeffFactory.eINSTANCE.createExternalCallAction();
        final Method calledMethod = VisitorUtils.getMethodCall(object);
        call.setEntityName(calledMethod.getName() + this.positionToString(object));
        final InterfacePortOperationTuple ifOperationTuple = this.getCalledInterfacePort(calledMethod);
        if (null == ifOperationTuple) {
            logger.warn("ifOperationTuple == null");
        } else {
            call.setRole_ExternalService((OperationRequiredRole) ifOperationTuple.role);
            call.setCalledService_ExternalService((OperationSignature) ifOperationTuple.signature);
            // call.setDocumentation(this.positionToString(KDMHelper.getJavaNodeSourceRegion(object)));
        }
        // // GAST2SEFFCHANGE
        this.seff.getSteps_Behaviour().add(call);
        this.lastType = this.functionClassificationAnnotation.get(object);
    }

    /**
     * Query the interface port for the function access using the source code decorator.
     *
     * @param calledMethod
     *            The access to find in the SAMM
     * @return interface port and operation for corresponding to the access.
     */
    private InterfacePortOperationTuple getCalledInterfacePort(final Method calledMethod) { // GAST2SEFFCHANGE
        final InterfacePortOperationTuple interfacePortOperationTuple = new InterfacePortOperationTuple();
        final ConcreteClassifier accessedConcreteClassifier = calledMethod.getContainingConcreteClassifier();

        for (final RequiredRole requiredRole : this.primitiveComponent.getRequiredRoles_InterfaceRequiringEntity()) {
            for (final InterfaceSourceCodeLink ifLink : this.sourceCodeDecoratorRepository.getInterfaceSourceCodeLink()) {
                if (requiredRole instanceof OperationRequiredRole) {
                    final OperationRequiredRole operReqRole = (OperationRequiredRole) requiredRole;
                    if (operReqRole.getRequiredInterface__OperationRequiredRole().equals(ifLink.getInterface())) {
                        final ConcreteClassifier gastClass = ifLink.getGastClass();
                        if (gastClass.equals(accessedConcreteClassifier)) {

                            logger.trace("accessed interface port " + operReqRole.getEntityName());
                            interfacePortOperationTuple.role = operReqRole;
                            // query operation:
                            interfacePortOperationTuple.signature = this.queryInterfaceOperation(calledMethod);

                            return interfacePortOperationTuple;
                        }
                    }
                }
            }
        }
        logger.warn("found no if port for " + accessedConcreteClassifier);
        return interfacePortOperationTuple;
    }

    /**
     * Signature query
     *
     * @param invokedMethod
     *            The method invocation to find in the SAMM
     * @return Signature corresponding to function access
     */
    private Signature queryInterfaceOperation(final Method invokedMethod) { // GAST2SEFFCHANGE
        for (final MethodLevelSourceCodeLink methodLink : this.sourceCodeDecoratorRepository
                .getMethodLevelSourceCodeLink()) {

            final Member methodSourceCodeDecorator = methodLink.getFunction();
            if (methodSourceCodeDecorator.equals(invokedMethod)) { // GAST2SEFFCHANGE

                logger.trace("accessed operation " + methodLink.getOperation().getEntityName());
                return methodLink.getOperation();
            }
        }

        logger.warn("no accessed operation found for " + invokedMethod.getContainingConcreteClassifier() + "::"
                + invokedMethod.getName()); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
        return null;
    }

    private void createInternalAction(final Statement statement) {
        final BitSet thisType = this.functionClassificationAnnotation.get(statement);
        if (!this.shouldSkip(this.lastType, thisType)) {
            final InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();

            internalAction.setEntityName("IA " + this.positionToString(statement)); // GAST2SEFFCHANGE
            // TODO
            // if (statement instanceof Block) { // GAST2SEFFADDED
            // ia.setDocumentation(this.blockToString((Block) statement) + "; Statement SISSyID: "
            // + KDMHelper.getSISSyID(statement)); // GAST2SEFFCHANGE
            // } else { // GAST2SEFFADDED
            // ia.setDocumentation("not a block" + "; Statement SISSyID: " +
            // KDMHelper.getSISSyID(statement)); // GAST2SEFFCHANGE//GAST2SEFFADDED
            // } // GAST2SEFFADDED
            this.seff.getSteps_Behaviour().add(internalAction);
        }
        this.lastType = thisType;
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

            for (final Statement statement : statements) {

                if (KDMHelper.getAllAccesses(statement) != null && // GAST2SEFFCHANGE
                        KDMHelper.getAllAccesses(statement).size() >= 1// GAST2SEFFCHANGE
                        ) {
                    final Commentable firstAccess = KDMHelper.getAllAccesses(statement).get(0); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
                    if (firstAccess instanceof Commentable) { // GAST2SEFFCHANGE
                        final Commentable access = firstAccess; // GAST2SEFFCHANGE//GAST2SEFFCHANGE

                        if (GetAccessedType.getAccessedType(access) != null) { // GAST2SEFFCHANGE
                            blockString
                            .append(" " + KDMHelper.getName(GetAccessedType.getAccessedType(access)) + "..."); // GAST2SEFFCHANGE//GAST2SEFFCHANGE
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

    private class InterfacePortOperationTuple {
        public Role role;
        public Signature signature;
    }

}
