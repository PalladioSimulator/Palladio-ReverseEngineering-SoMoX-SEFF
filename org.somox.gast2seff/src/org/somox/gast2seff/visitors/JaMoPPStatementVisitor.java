package org.somox.gast2seff.visitors;

import java.util.BitSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.TryBlock;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.InternalCallAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingInternalBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.somox.gast2seff.visitors.InterfaceOfExternalCallFinding.InterfacePortOperationTuple;
import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.KDMHelper;
import org.somox.sourcecodedecorator.AbstractActionClassMethodLink;
import org.somox.sourcecodedecorator.MethodLevelResourceDemandingInternalBehaviorLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

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
    private final org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour seff;

    /**
     * Mapping to SAMM repository (for external call lookup)
     */
    private final SourceCodeDecoratorRepository sourceCodeDecoratorRepository;

    /**
     * The component the created SEFF belongs to.
     */
    private final BasicComponent primitiveComponent;

    private final InterfaceOfExternalCallFinding interfaceOfExternalCallFinder;

    /**
     * Interface, which is used to find corresponding SEFFs or ResourceDemandingInternalBehaviour
     * for a given class method If the field is null no ResourceDemandingInternalBehaviour for class
     * methods are created.
     */
    private final ResourceDemandingBehaviourForClassMethodFinding resourceDemandingBehaviourForClassMethodFinding;

    /**
     * Constructor Uses {@link DefaultInterfaceOfExternalCallFinder} to find interfaces of external
     * calls.
     *
     * @param functionClassificationAnnotations
     *            A map containing the type annotations for the nodes of the GAST model. Generated
     *            by a {@link FunctionCallClassificationVisitor}.
     * @param resourceDemandingBehaviour
     *            The RD-behaviour to generate
     * @param sourceCodeDecorator
     *            The gast behaviour which maps gast statements and SAMM repository.
     * @param primitiveComponent
     */
    public JaMoPPStatementVisitor(final Map<Commentable, BitSet> functionClassificationAnnotations,
            final org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour resourceDemandingBehaviour,
            final SourceCodeDecoratorRepository sourceCodeDecorator, final BasicComponent primitiveComponent) {
        this(functionClassificationAnnotations, resourceDemandingBehaviour, sourceCodeDecorator, primitiveComponent,
                null, null);
    }

    public JaMoPPStatementVisitor(final Map<Commentable, BitSet> functionClassificationAnnotations,
            final org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour resourceDemandingBehaviour,
            final SourceCodeDecoratorRepository sourceCodeDecorator, final BasicComponent primitiveComponent,
            final InterfaceOfExternalCallFinding interfaceOfExternalCallFinder,
            final ResourceDemandingBehaviourForClassMethodFinding resourceDemandingBehaviourForClassMethodFinding) {
        super(functionClassificationAnnotations, null == resourceDemandingBehaviourForClassMethodFinding);
        this.seff = resourceDemandingBehaviour;
        this.sourceCodeDecoratorRepository = sourceCodeDecorator;
        this.primitiveComponent = primitiveComponent;
        this.resourceDemandingBehaviourForClassMethodFinding = resourceDemandingBehaviourForClassMethodFinding;

        this.interfaceOfExternalCallFinder = this.configureInterfaceOfExternalCallFinding(sourceCodeDecorator,
                primitiveComponent, interfaceOfExternalCallFinder);
    }

    private InterfaceOfExternalCallFinding configureInterfaceOfExternalCallFinding(
            final SourceCodeDecoratorRepository sourceCodeDecorator, final BasicComponent primitiveComponent,
            final InterfaceOfExternalCallFinding interfaceOfExternalCallFinder) {
        if (null == interfaceOfExternalCallFinder) {
            if (null == sourceCodeDecorator) {
                throw new IllegalArgumentException(
                        "Can not use " + DefaultInterfaceOfExternalCallFinder.class.getSimpleName()
                                + " with ‘null' for source code decorator");
            }
            return new DefaultInterfaceOfExternalCallFinder(sourceCodeDecorator, primitiveComponent);
        } else {
            return interfaceOfExternalCallFinder;
        }
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
            final org.palladiosimulator.pcm.seff.LoopAction loop = SeffFactory.eINSTANCE.createLoopAction();
            this.createAbstracActionClassMethodLink(loop, loopStatement);
            loop.setBodyBehaviour_Loop(SeffFactory.eINSTANCE.createResourceDemandingBehaviour());
            this.seff.getSteps_Behaviour().add(loop);
            final StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
            loop.getBodyBehaviour_Loop().getSteps_Behaviour().add(startAction);
            this.createAbstracActionClassMethodLink(startAction, loopStatement);
            loop.setEntityName(this.positionToString(loopStatement));

            new JaMoPPStatementVisitor(this.functionClassificationAnnotation, loop.getBodyBehaviour_Loop(),
                    this.sourceCodeDecoratorRepository, this.primitiveComponent, this.interfaceOfExternalCallFinder,
                    this.resourceDemandingBehaviourForClassMethodFinding).doSwitch(body);

            final StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
            this.createAbstracActionClassMethodLink(stopAction, loopStatement);
            loop.getBodyBehaviour_Loop().getSteps_Behaviour().add(stopAction);
            VisitorUtils.connectActions(loop.getBodyBehaviour_Loop());
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
    // "Behaviour not set in GAST for
    // "+functionAccess.getMethod().getName();//GAST2SEFFCHANGE//GAST2SEFFCHANGE
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
                    this.handleClassMethod(classMethod);
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
            final org.palladiosimulator.pcm.seff.BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
            this.createAbstracActionClassMethodLink(branchAction, switchStatement);
            this.seff.getSteps_Behaviour().add(branchAction);
            branchAction.setEntityName(JaMoPPStatementVisitor.this.positionToString(switchStatement));

            final List<List<Statement>> branches = SwitchStatementHelper
                    .createBlockListFromSwitchStatement(switchStatement);

            for (final List<Statement> branch : branches) {
                final org.palladiosimulator.pcm.seff.AbstractBranchTransition bt = SeffFactory.eINSTANCE
                        .createProbabilisticBranchTransition();
                bt.setBranchBehaviour_BranchTransition(SeffFactory.eINSTANCE.createResourceDemandingBehaviour());
                final StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
                bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour().add(startAction);
                this.createAbstracActionClassMethodLink(startAction, switchStatement);
                bt.setEntityName("parent " + JaMoPPStatementVisitor.this.positionToString(switchStatement) + "/"
                        + (branch.size() > 0 ? JaMoPPStatementVisitor.this
                                .positionToLineNumber(KDMHelper.getJavaNodeSourceRegion(branch.get(0))) + " to "
                                // use parent position since branch position is empty
                                + JaMoPPStatementVisitor.this.positionToLineNumber(
                                        KDMHelper.getJavaNodeSourceRegion(branch.get(branch.size() - 1)))
                                : ""));
                branchAction.getBranches_Branch().add(bt);
                final AbstractJaMoPPStatementVisitor visitor = new JaMoPPStatementVisitor(
                        JaMoPPStatementVisitor.this.functionClassificationAnnotation,
                        bt.getBranchBehaviour_BranchTransition(),
                        JaMoPPStatementVisitor.this.sourceCodeDecoratorRepository,
                        JaMoPPStatementVisitor.this.primitiveComponent, this.interfaceOfExternalCallFinder,
                        this.resourceDemandingBehaviourForClassMethodFinding);
                // Statement s = b.getStatement();
                // visitor.doSwitch(s);

                for (final Statement statement : branch) {
                    // copied from caseBlock
                    final BitSet thisType = visitor.functionClassificationAnnotation.get(statement);
                    if (!visitor.shouldSkip(visitor.lastType, thisType)) { // Only
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
                    visitor.lastType = thisType;
                    // end of copy
                }

                final StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
                bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour().add(stopAction);
                this.createAbstracActionClassMethodLink(stopAction, switchStatement);
                VisitorUtils.connectActions(bt.getBranchBehaviour_BranchTransition());
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
                    JaMoPPStatementVisitor.this.primitiveComponent, this.interfaceOfExternalCallFinder,
                    this.resourceDemandingBehaviourForClassMethodFinding);
            for (final Statement statement : tryBlock.getStatements()) {
                visitor.doSwitch(statement);
            }

            // TODO:we do not visit catch block?

            // visit finally block if exists
            if (tryBlock.getFinallyBlock() != null) {
                new JaMoPPStatementVisitor(JaMoPPStatementVisitor.this.functionClassificationAnnotation,
                        JaMoPPStatementVisitor.this.seff, JaMoPPStatementVisitor.this.sourceCodeDecoratorRepository,
                        JaMoPPStatementVisitor.this.primitiveComponent, this.interfaceOfExternalCallFinder,
                        this.resourceDemandingBehaviourForClassMethodFinding).doSwitch(tryBlock.getFinallyBlock());
            }
        } else {
            this.createInternalAction(tryBlock);
        }
        return new Object();
    }

    @Override
    protected Object handleCondition(final Condition condition) {
        if (JaMoPPStatementVisitor.this.containsExternalCall(condition)) {
            final org.palladiosimulator.pcm.seff.BranchAction branch = SeffFactory.eINSTANCE.createBranchAction();
            this.createAbstracActionClassMethodLink(branch, condition);
            JaMoPPStatementVisitor.this.seff.getSteps_Behaviour().add(branch);
            branch.setEntityName(JaMoPPStatementVisitor.this.positionToString(condition));

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
     * If createResourceDemandingInternalBehaviourForClassMethods is set to false we handle the
     * class method like any other statement list container.
     *
     * If createResourceDemandingInternalBehaviourForClassMethods set to true a
     * ResourceDemandingInternalBehaviour for the class method will be created (if not created yet)
     * in the current method and called from the current SEFF. In the current SEFF an
     * InternalCallAction will be created that calls the ResourceDemandingInternalBehaviour. If
     * classMethod already has an SEFF that can be called from outside the component a
     * InternalCallAction is created that just calls the SEFF
     */
    @Override
    protected Object handleClassMethod(final ClassMethod classMethod) {
        if (null == this.resourceDemandingBehaviourForClassMethodFinding) {
            return this.handleStatementListContainer(classMethod);
        }
        final ResourceDemandingSEFF rdSEFF = this.resourceDemandingBehaviourForClassMethodFinding
                .getCorrespondingRDSEFForClassMethod(classMethod);
        if (null != rdSEFF) {
            // just create an Internal Action that calls the SEFF
            this.createInternalCallAction(rdSEFF, classMethod);
            return new Object();
        }
        final ResourceDemandingInternalBehaviour resourceDemandingInternalBehavior = this
                .getOrCreateResourceDemandingInternalBehaviour(classMethod);
        this.createInternalCallAction(resourceDemandingInternalBehavior, classMethod);
        return new Object();
    }

    private void createInternalCallAction(final ResourceDemandingBehaviour resourceDemandingBehavior,
            final ClassMethod classMethod) {
        final InternalCallAction internalCallAction = SeffFactory.eINSTANCE.createInternalCallAction();
        internalCallAction.setCalledResourceDemandingInternalBehaviour(resourceDemandingBehavior);
        internalCallAction.setEntityName(this.positionToString(classMethod));
        this.seff.getSteps_Behaviour().add(internalCallAction);
    }

    private ResourceDemandingInternalBehaviour getOrCreateResourceDemandingInternalBehaviour(
            final ClassMethod classMethod) {
        ResourceDemandingInternalBehaviour resourceDemandingInternalBehaviour = this.resourceDemandingBehaviourForClassMethodFinding
                .getCorrespondingResourceDemandingInternalBehaviour(classMethod);
        if (null != resourceDemandingInternalBehaviour) {
            return resourceDemandingInternalBehaviour;
        }
        resourceDemandingInternalBehaviour = this.createResourceDemandingInternalBehaviour(classMethod);
        return resourceDemandingInternalBehaviour;
    }

    private void createMethodLevelResourceDemandingInternalBehaviorLink(final ClassMethod classMethod,
            final ResourceDemandingInternalBehaviour resourceDemandingInternalBehaviour) {
        if (null != this.sourceCodeDecoratorRepository) {
            final MethodLevelResourceDemandingInternalBehaviorLink methodLevelResourceDemandingInternalBehaviorLink = SourcecodedecoratorFactory.eINSTANCE
                    .createMethodLevelResourceDemandingInternalBehaviorLink();
            methodLevelResourceDemandingInternalBehaviorLink.setFunction(classMethod);
            methodLevelResourceDemandingInternalBehaviorLink
                    .setResourceDemandingInternalBehaviour(resourceDemandingInternalBehaviour);
            this.sourceCodeDecoratorRepository.getMethodLevelResourceDemandingInternalBehaviorLink()
                    .add(methodLevelResourceDemandingInternalBehaviorLink);
        }
    }

    private ResourceDemandingInternalBehaviour createResourceDemandingInternalBehaviour(final ClassMethod classMethod) {
        final ResourceDemandingInternalBehaviour resourceDemandingInternalBehaviour = SeffFactory.eINSTANCE
                .createResourceDemandingInternalBehaviour();
        resourceDemandingInternalBehaviour
                .setBasicComponent_ResourceDemandingInternalBehaviour(this.primitiveComponent);
        this.createMethodLevelResourceDemandingInternalBehaviorLink(classMethod, resourceDemandingInternalBehaviour);
        final StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
        resourceDemandingInternalBehaviour.getSteps_Behaviour().add(startAction);
        final JaMoPPStatementVisitor methodVisitor = new JaMoPPStatementVisitor(this.functionClassificationAnnotation,
                resourceDemandingInternalBehaviour, this.sourceCodeDecoratorRepository, this.primitiveComponent,
                this.interfaceOfExternalCallFinder, this.resourceDemandingBehaviourForClassMethodFinding);
        methodVisitor.handleStatementListContainer(classMethod);
        final StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
        resourceDemandingInternalBehaviour.getSteps_Behaviour().add(stopAction);
        VisitorUtils.connectActions(resourceDemandingInternalBehaviour);
        return resourceDemandingInternalBehaviour;
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
    private void handleIfOrElseBranch(final Condition input, final org.palladiosimulator.pcm.seff.BranchAction branch,
            final Statement ifElseStatement) {
        final org.palladiosimulator.pcm.seff.AbstractBranchTransition bt = SeffFactory.eINSTANCE
                .createProbabilisticBranchTransition();
        bt.setBranchBehaviour_BranchTransition(SeffFactory.eINSTANCE.createResourceDemandingBehaviour());
        final StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
        this.createAbstracActionClassMethodLink(startAction, ifElseStatement);
        bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour().add(startAction);
        bt.setEntityName("parent " + this.positionToString(input) + "/" + this.positionToString(ifElseStatement));
        // use parent position since branch position is empty//GAST2SEFFCHANGE//GAST2SEFFCHANGE
        branch.getBranches_Branch().add(bt);
        final AbstractJaMoPPStatementVisitor visitor = new JaMoPPStatementVisitor(this.functionClassificationAnnotation,
                bt.getBranchBehaviour_BranchTransition(), this.sourceCodeDecoratorRepository, this.primitiveComponent,
                this.interfaceOfExternalCallFinder, this.resourceDemandingBehaviourForClassMethodFinding);
        // Statement s = b.getStatement();//GAST2SEFFCHANGE
        visitor.doSwitch(ifElseStatement);
        final StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
        this.createAbstracActionClassMethodLink(stopAction, ifElseStatement);
        bt.getBranchBehaviour_BranchTransition().getSteps_Behaviour().add(stopAction);
        VisitorUtils.connectActions(bt.getBranchBehaviour_BranchTransition());
    }

    private void createExternalCallAction(final Statement object) {
        final ExternalCallAction call = SeffFactory.eINSTANCE.createExternalCallAction();
        this.createAbstracActionClassMethodLink(call, object);
        final Method calledMethod = VisitorUtils.getMethodCall(object);
        call.setEntityName(calledMethod.getName() + this.positionToString(object));
        final InterfacePortOperationTuple ifOperationTuple = this.interfaceOfExternalCallFinder
                .getCalledInterfacePort(calledMethod);
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
    // "Behaviour not set in GAST for
    // "+functionAccess.getMethod().getName();//GAST2SEFFCHANGE//GAST2SEFFCHANGE
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

    private void createInternalAction(final Statement statement) {
        final BitSet thisType = this.functionClassificationAnnotation.get(statement);
        if (!this.shouldSkip(this.lastType, thisType)) {
            final InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();
            this.createAbstracActionClassMethodLink(internalAction, statement);
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

    private void createAbstracActionClassMethodLink(final AbstractAction abstractAction, final Statement statement) {
        final ClassMethod classMethod = this.getClassMethodFromCommentable(statement);
        if (null == classMethod) {
            logger.warn("Could not create AbstracActionClassMethodLink for AbstractAction " + abstractAction
                    + " and Statement " + statement);
            return;
        }
        final AbstractActionClassMethodLink abstractActionClassMethodLink = SourcecodedecoratorFactory.eINSTANCE
                .createAbstractActionClassMethodLink();
        abstractActionClassMethodLink.setAbstractAction(abstractAction);
        abstractActionClassMethodLink.setClassMethod(classMethod);
        if (null != this.sourceCodeDecoratorRepository) {
            this.sourceCodeDecoratorRepository.getAbstractActionClassMethodLink().add(abstractActionClassMethodLink);
        }
    }

    private ClassMethod getClassMethodFromCommentable(final Commentable statement) {
        if (statement.eContainer() instanceof ClassMethod) {
            return (ClassMethod) statement.eContainer();
        } else if (statement.eContainer() instanceof Commentable) {
            return this.getClassMethodFromCommentable((Commentable) statement.eContainer());
        }
        logger.warn("Could not found method for Commentable: " + statement);
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
    // "Behaviour not set in GAST for
    // "+functionAccess.getMethod().getName();//GAST2SEFFCHANGE//GAST2SEFFCHANGE
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

}
