package org.palladiosimulator.somox.ast2seff.visitors;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.BranchActionCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.ExternalCallActionCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.LoopActionCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.SeffCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.SetVariableActionCreator;
import org.palladiosimulator.generator.fluent.shared.components.VariableUsageCreator;
import org.palladiosimulator.pcm.core.CoreFactory;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.parameter.VariableCharacterisationType;
import org.palladiosimulator.pcm.reliability.ResourceTimeoutFailureType;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.somox.ast2seff.util.NameUtil;
import org.palladiosimulator.somox.ast2seff.util.SwitchStatementUtil;

public class Ast2SeffVisitor extends ASTVisitor {
    private static final Logger LOGGER = Logger.getLogger(Ast2SeffVisitor.class);

    private ActionSeff actionSeff;
    private ASTNode rootNode;
    private Map<ASTNode, ServiceEffectSpecification> externalNodes;

    private FluentRepositoryFactory fluentFactory;
    private int methodInliningDepth = 0;

    /**
     *
     * Constructor for the visitor object Sets all relevant variables for the public visit functions of the class
     *
     * @param methodPalladioInformation object to give access to necessary transformation information
     * @param actionSeff                current action SEFF creator object which is used to model the SEFF elements
     * @param methodPalladionInfoMap    object to give access to the information of all methods which should be modeled
     * @param componentInformation      object for the current SEFF component which gets modeled
     * @param fluentFactory             factory object to create additional SEFF elements and fetch created SEFF
     *                                  elements from the repository
     */
    public Ast2SeffVisitor(ActionSeff actionSeff, ASTNode rootNode,
            Map<ASTNode, ServiceEffectSpecification> externalNodes, FluentRepositoryFactory fluentFactory) {
        this.actionSeff = actionSeff;
        this.rootNode = rootNode;
        this.externalNodes = externalNodes;
        this.fluentFactory = fluentFactory;
    }

    /**
     *
     * Constructor for the visitor object Sets all relevant variables for the public visit functions of the class Has a
     * additional parameter to set the depth for the method inlining process
     *
     * @param actionSeff             current action SEFF creator object which is used to model the SEFF elements
     * @param methodPalladionInfoMap object to give access to the information of all methods which should be modeled
     * @param componentInformation   object for the current SEFF component which gets modeled
     * @param fluentFactory          factory object to create additional SEFF elements and fetch created SEFF elements
     *                               from the repository
     * @param methodInliningDepth    integer value to set the current method inlining depth
     */
    private Ast2SeffVisitor(ActionSeff actionSeff, ASTNode rootNode,
            Map<ASTNode, ServiceEffectSpecification> externalNodes, FluentRepositoryFactory fluentFactory,
            int methodInliningDepth) {
        this.actionSeff = actionSeff;
        this.rootNode = rootNode;
        this.externalNodes = externalNodes;
        this.fluentFactory = fluentFactory;
        this.methodInliningDepth = methodInliningDepth;
    }

    /**
     *
     * Function to start the traversal of a MethodDeclaration
     *
     * @param methodPalladioInformation object to give access to necessary transformation information
     * @param actionSeff                current action SEFF creator object which is used to model the SEFF elements
     * @param methodPalladionInfoMap    object to give access to the information of all methods which should be modeled
     * @param componentInformation      object for the current SEFF component which gets modeled
     * @param fluentFactory             factory object to create additional SEFF elements and fetch created SEFF
     *                                  elements from the repository
     * @return ActionSeff object which contains the transformed the complete MethodDeclaration
     */
    public static ActionSeff perform(ActionSeff actionSeff, ASTNode rootNode,
            Map<ASTNode, ServiceEffectSpecification> externalNodes, FluentRepositoryFactory fluentFactory) {
        Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(actionSeff, rootNode, externalNodes,
                fluentFactory);
        rootNode.accept(newFunctionCallClassificationVisitor);
        return actionSeff;
    }

    /**
     *
     * Function to start the traversal of a ASTNode object (inner statements)
     *
     * @param node       ASTNode object which should be traversed and transformed to SEFF elements
     * @param actionSeff current action SEFF creator object which is used to model the SEFF elements
     * @return ActionSeff object which contains the transformed the ASTNode object
     */
    private ActionSeff perform(ASTNode node, ActionSeff actionSeff) {
        Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(actionSeff, rootNode, externalNodes,
                fluentFactory);
        node.accept(newFunctionCallClassificationVisitor);
        return actionSeff;
    }

    /**
     *
     * Function to start the traversal of a ASTNode object (inner statements) Special function to set the
     * methodInliningDepth for the MethodInling creation process
     *
     * @param node                ASTNode object which should be traversed and transformed to SEFF elements
     * @param actionSeff          current action SEFF creator object which is used to model the SEFF elements
     * @param methodInliningDepth integer to specify the current depth for the inner visitor traversal
     * @return ActionSeff object which contains the transformed the ASTNode object
     */
    private ActionSeff perform(ASTNode node, ActionSeff actionSeff, int methodInliningDepth) {
        Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(actionSeff, rootNode, externalNodes,
                fluentFactory, methodInliningDepth);
        node.accept(newFunctionCallClassificationVisitor);
        return actionSeff;
    }

    /**
     *
     * Transform an expression statement to a Method Inlining, Internal Action or External Action
     *
     * @param expressionStatement statement to transform
     * @return always false, no further visiting of child elements
     */
    @Override
    public boolean visit(final ExpressionStatement expressionStatement) {
        LOGGER.debug("Visit Expression Statement");
        Expression expression = expressionStatement.getExpression();

        if (expression instanceof Assignment) {
            /**
             * Limitation / Future Work
             *
             * Set Variable Actions only should be modeled for functions with return statement
             *
             **/
        } else if (expression instanceof MethodInvocation && isExternal((MethodInvocation) expression)) {
            MethodInvocation methodInvocation = (MethodInvocation) expression;
            // Never throws due to isExternal check above
            OperationInterface operationInterface = getOperationInterfaceOfInvocation(methodInvocation).orElseThrow();
            OperationSignature operationSignature = getOperationSignatureOfInvocation(methodInvocation).orElseThrow();
            if (!getOperationInterfaceOfRootNode().getEntityName()
                    .equals(operationInterface.getEntityName())) {
                createExternalCallAction(methodInvocation, operationInterface, operationSignature);
            } else {
                if (methodInliningDepth < 1) {
                    createMethodInlining();
                } else {
                    createInternalAction(expressionStatement);
                }
            }
        } else {
            createInternalAction(expressionStatement);
        }

        return false;
    }

    /**
     * Limitation / Future Work: This function could be restructured and further developed to implement internal call
     * actions where appropriate
     *
     * @param methodPalladioInformation information of method which gets inlined
     */
    private void createMethodInlining() {
        LOGGER.debug("Expression Statement is Method Inlining");
        this.perform(rootNode, actionSeff, methodInliningDepth + 1);
    }

    /**
     *
     * Transformation to an ExternalCallAction
     *
     * @param methodInvocation          invocation of external method
     * @param externalMethodInformation information of external method which gets referenced
     */
    private void createExternalCallAction(MethodInvocation methodInvocation, OperationInterface operationInterface,
            OperationSignature operationSignature) {
        LOGGER.debug("Expression Statement is External Call Action");

        ExternalCallActionCreator externalCallActionCreator = actionSeff.externalCallAction();
        OperationRequiredRole requiredRole = addRequiredInterfaceToComponent(operationInterface.getEntityName());
        externalCallActionCreator.withCalledService(operationSignature);
        externalCallActionCreator.withRequiredRole(requiredRole);
        // TODO Here is real signature needed due to parameters. How can this be designed more straightforward?
        OperationSignature calledFunctionSignature = (OperationSignature) getServiceEffectSpecificationOfInvocation(
                methodInvocation).orElseThrow().getDescribedService__SEFF();
        VariableUsageCreator variableUsage;
        if (!methodInvocation.arguments().isEmpty()) {
            if (calledFunctionSignature != null && (calledFunctionSignature.getParameters__OperationSignature()
                    .size() == methodInvocation.arguments().size())) {
                // try to get variables from interface
                EList<Parameter> calledFunctParameterList = calledFunctionSignature.getParameters__OperationSignature();

                for (int i = 0; i < calledFunctParameterList.size(); i++) {
                    Parameter para = calledFunctParameterList.get(i);
                    Expression castedArgument = (Expression) methodInvocation.arguments().get(i);
                    variableUsage = generateInputVariableUsage(castedArgument, para);
                    externalCallActionCreator.withInputVariableUsage(variableUsage);
                }
            } else {
                LOGGER.error("Called Function Signature Not Found Or Wrong Parameter Size");
            }
        }

        if (calledFunctionSignature != null && calledFunctionSignature.getReturnType__OperationSignature() != null) {
            DataType returnType = calledFunctionSignature.getReturnType__OperationSignature();
            variableUsage = generateOutputVariableUsage(returnType);
            externalCallActionCreator.withReturnVariableUsage(variableUsage);
        }

        actionSeff = externalCallActionCreator.withName(NameUtil.getEntityName(methodInvocation)).followedBy();
    }

    /**
     *
     * Add the Required Interface element for external calls to the current component Checks if the interface is already
     * required
     *
     * @param requiredInterfaceName name of the interface
     */
    private OperationRequiredRole addRequiredInterfaceToComponent(String requiredInterfaceName) {
        OperationInterface requiredInterface = fluentFactory.fetchOfOperationInterface(requiredInterfaceName);

        // Search for already existing required interface
        BasicComponent rootComponent = getComponentOfRootNode();
        for (RequiredRole requiredRole : rootComponent.getRequiredRoles_InterfaceRequiringEntity()) {
            OperationRequiredRole operationRequiredRole = (OperationRequiredRole) requiredRole;
            if (operationRequiredRole.getRequiredInterface__OperationRequiredRole().getEntityName()
                    .equals(requiredInterface.getEntityName())) {
                return operationRequiredRole;
            }
        }

        // Create new requiring role if interface is not required by component yet
        OperationRequiredRole requiredRole = RepositoryFactory.eINSTANCE.createOperationRequiredRole();
        requiredRole.setRequiredInterface__OperationRequiredRole(requiredInterface);
        rootComponent.getRequiredRoles_InterfaceRequiringEntity().add(requiredRole);
        return requiredRole;
    }

    /**
     *
     * Transformation to an InternalAction
     *
     * @param expressionStatement statement to transfrom to internal action
     */
    private void createInternalAction(final ExpressionStatement expressionStatement) {
        LOGGER.debug("Expression Statement is Internal Action");
        actionSeff = actionSeff.internalAction().withName(NameUtil.getEntityName(expressionStatement)).followedBy();
    }

    /**
     *
     * Transformation of an IfStatement to a BranchAction
     *
     * @param ifStatement statement to transform
     * @return always false, no further visiting of child elements
     */
    @Override
    public boolean visit(final IfStatement ifStatement) {
        LOGGER.debug("Visit If Statement");
        BranchActionCreator branchActionCreator = actionSeff.branchAction();
        branchActionCreator = generateBranchAction(ifStatement, branchActionCreator);

        if (ifStatement.getElseStatement() != null) {

            branchActionCreator = handleElseStatement(ifStatement.getElseStatement(), branchActionCreator);
        }

        actionSeff = branchActionCreator.withName(NameUtil.getEntityName(ifStatement)).followedBy();
        return false;
    }

    /**
     *
     * Transformation of an SynchronizedStatement to a AcquireAction and ReleaseAction with the body between the two
     * actions For all synchronized statements in on component only one passive resource gets modeled
     *
     * @param synchronizedStatement statement to transform
     * @return always false, no further visiting of child elements
     */
    @Override
    public boolean visit(final SynchronizedStatement synchronizedStatement) {
        LOGGER.debug("Visit Synchronized Statement");

        if (getComponentOfRootNode().getPassiveResource_BasicComponent().isEmpty()) {
            PCMRandomVariable randomVariable = CoreFactory.eINSTANCE.createPCMRandomVariable();
            randomVariable.setSpecification("1");
            PassiveResource pass = RepositoryFactory.eINSTANCE.createPassiveResource();
            pass.setCapacity_PassiveResource(randomVariable);
            pass.setResourceTimeoutFailureType__PassiveResource((ResourceTimeoutFailureType) fluentFactory
                    .newResourceTimeoutFailureType("PassiveResourceTimeoutFailure").build());
            pass.setEntityName("Passive Resource");
            getComponentOfRootNode().getPassiveResource_BasicComponent().add(pass);
        }

        PassiveResource passiveResource = getComponentOfRootNode().getPassiveResource_BasicComponent().stream()
                .filter(resource -> resource.getEntityName().equals("Passive Resource")).findFirst().orElseThrow();
        actionSeff.acquireAction().withName(NameUtil.ACQUIRE_ACTION_NAME)
                .withPassiveResource(passiveResource).followedBy();
        actionSeff = this.perform(synchronizedStatement.getBody(), actionSeff);
        actionSeff.releaseAction().withName(NameUtil.RELEASE_ACTION_NAME)
                .withPassiveResource(passiveResource).followedBy();

        return false;
    }

    /**
     *
     * Transformation of a TryStatement to a BranchAction
     *
     * @param tryStatement statement to transform
     * @return always false, no further visiting of child elements
     */
    @Override
    public boolean visit(final TryStatement tryStatement) {
        LOGGER.debug("Visit Try Statement");

        BranchActionCreator branchActionCreator = actionSeff.branchAction();
        branchActionCreator = generateBranchAction(tryStatement, branchActionCreator);

        List<CatchClause> catchClauseList = tryStatement.catchClauses();
        for (CatchClause catchClause : catchClauseList) {
            ActionSeff innerActionSeff = fluentFactory.newSeff().withSeffBehaviour().withStartAction()
                    .withName(NameUtil.START_ACTION_NAME).followedBy();
            innerActionSeff = this.perform(catchClause.getBody(), innerActionSeff);
            SeffCreator seffCreator = innerActionSeff.stopAction().withName(NameUtil.STOP_ACTION_NAME)
                    .createBehaviourNow();
            String condition = NameUtil.getCatchClauseConditionString(catchClause);
            branchActionCreator = branchActionCreator.withGuardedBranchTransition(condition, seffCreator,
                    NameUtil.getEntityName(tryStatement));
        }

        actionSeff = branchActionCreator.withName(NameUtil.getEntityName(tryStatement)).followedBy();
        return false;
    }

    /**
     *
     * Transformation of a ForStatement to a LoopAction
     *
     * @param forStatement statement to transform
     * @return always false, no further visiting of child elements
     */
    @Override
    public boolean visit(final ForStatement forStatement) {
        LOGGER.debug("Visit For Statement");
        LoopActionCreator loopActionCreator = actionSeff.loopAction();
        Expression forStatementExpression = forStatement.getExpression();
        if (forStatementExpression != null && forStatementExpression instanceof InfixExpression) {
            loopActionCreator
                    .withIterationCount(((InfixExpression) forStatementExpression).getRightOperand().toString());
        }
        loopActionCreator = generateLoopAction(forStatement.getBody(), loopActionCreator);
        actionSeff = loopActionCreator.withName(NameUtil.getEntityName(forStatement)).followedBy();
        return false;
    }

    /**
     *
     * Transformation of a EnhancedForStatement to a LoopAction
     *
     * @param enhancedForStatement statement to transform
     * @return always false, no further visiting of child elements
     */
    @Override
    public boolean visit(final EnhancedForStatement enhancedForStatement) {
        LOGGER.debug("Visit Enhanced For Statement");
        LoopActionCreator loopActionCreator = actionSeff.loopAction();
        Expression forStatementExpression = enhancedForStatement.getExpression();
        if (forStatementExpression != null && forStatementExpression instanceof SimpleName) {
            loopActionCreator.withIterationCount("1");
        }
        loopActionCreator = generateLoopAction(enhancedForStatement.getBody(), loopActionCreator);
        actionSeff = loopActionCreator.withName(NameUtil.getEntityName(enhancedForStatement)).followedBy();
        return false;
    }

    /**
     *
     * Transformation of a WhileStatement to a LoopAction
     *
     * @param whileStatement statement to transform
     * @return always false, no further visiting of child elements
     */
    @Override
    public boolean visit(final WhileStatement whileStatement) {
        LOGGER.debug("Visit While Statement");
        LoopActionCreator loopActionCreator = actionSeff.loopAction();
        Expression whileStatementExpression = whileStatement.getExpression();
        if (whileStatementExpression != null && whileStatementExpression instanceof SimpleName) {
            loopActionCreator.withIterationCount("1");
        }
        loopActionCreator = generateLoopAction(whileStatement.getBody(), loopActionCreator);
        actionSeff = loopActionCreator.withName(NameUtil.getEntityName(whileStatement)).followedBy();
        return false;
    }

    /**
     *
     * Transformation of a SwitchStatement to a BranchAction Usage of the SwitchStatementUtil to break down the
     * different cases to different blocks
     *
     * @param switchStatement statement to transform
     * @return always false, no further visiting of child elements
     */
    @Override
    public boolean visit(final SwitchStatement switchStatement) {
        LOGGER.debug("Visit Switch Statement");
        BranchActionCreator branchActionCreator = actionSeff.branchAction();
        branchActionCreator.withName(NameUtil.getEntityName(switchStatement));

        List<List<Statement>> blockList = SwitchStatementUtil.createBlockListFromSwitchStatement(switchStatement);
        LOGGER.debug("Generate Inner Branch Behaviour");
        for (List<Statement> block : blockList) {

            ActionSeff innerActionSeff = fluentFactory.newSeff().withSeffBehaviour().withStartAction()
                    .withName(NameUtil.START_ACTION_NAME).followedBy();

            for (Statement statement : block) {
                innerActionSeff = this.perform(statement, innerActionSeff);
            }

            SeffCreator seffCreator = innerActionSeff.stopAction().withName(NameUtil.STOP_ACTION_NAME)
                    .createBehaviourNow();
            SwitchCase switchCase = (SwitchCase) block.get(0);
            String condition = NameUtil.getSwitchCaseConditionString(switchCase);
            branchActionCreator = branchActionCreator.withGuardedBranchTransition(condition, seffCreator);
        }

        actionSeff = branchActionCreator.withName(NameUtil.getEntityName(switchStatement)).followedBy();
        return false;
    }

    /**
     *
     * Generate Set Variable Action for functions with a return statement
     *
     * @param returnStatement return statement of the function containing the object which gets returned
     * @return always false, no further visiting of child elements
     */
    @Override
    public boolean visit(final ReturnStatement returnStatement) {
        LOGGER.debug("Visit Return Statement");
        Expression returnExpression = returnStatement.getExpression();
        String entityName = NameUtil.getEntityName(returnStatement);
        SetVariableActionCreator setVariableActionCreator = actionSeff.setVariableAction().withName(entityName);
        VariableUsageCreator returnVariable = this.generateInputVariableUsage(returnExpression);
        setVariableActionCreator.withLocalVariableUsage(returnVariable);
        this.actionSeff = setVariableActionCreator.followedBy();
        return false;
    }

    /**
     * Limitation / Future Work
     *
     * Set Variable Actions only should be modeled for functions with return statement
     *
     */
    @Override
    public boolean visit(final VariableDeclarationStatement variableDeclarationStatement) {
        LOGGER.debug("Visit Variable Declaration Statement");
        return false;
    }

    /**
     * Generate Input Variable Usages
     *
     * New feature, not included in JaMoPP version Behavior from MediaStore3 -> AudioWatermarking + Additional
     * Information: https://www.palladio-simulator.com/tools/tutorials/ (PCM Parameter (PDF) -> 18)
     *
     * @param expression object that is passed inside the statement
     * @param parameter  Optional parameter, if matching interface to function was found for additional
     * @return VariableUsageCreator object ready to be added to a Set Variable Action
     */
    private VariableUsageCreator generateInputVariableUsage(Expression expression, Parameter parameter) {
        VariableUsageCreator variableUsage = fluentFactory.newVariableUsage();

        String randomPCMName;
        DataType paraDataType = parameter.getDataType__Parameter();
        if (paraDataType instanceof PrimitiveDataType) {
            variableUsage.withNamespaceReference(((PrimitiveDataType) paraDataType).getType().toString(),
                    expression.toString());
            randomPCMName = ((PrimitiveDataType) paraDataType).getType().toString() + "." + expression.toString();
            variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.VALUE);
        } else if (paraDataType instanceof CompositeDataType) {
            variableUsage.withNamespaceReference(((CompositeDataType) paraDataType).getEntityName(),
                    expression.toString());
            randomPCMName = ((CompositeDataType) paraDataType).getEntityName() + "." + expression.toString();
            variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.BYTESIZE);
        } else {
            variableUsage.withNamespaceReference(parameter.getParameterName(), expression.toString());
            randomPCMName = parameter.getParameterName() + "." + expression.toString();
            variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.VALUE);
        }
        return variableUsage;
    }

    /**
     * Generate Input Variable Usages
     *
     * New feature, not included in JaMoPP version Behavior from MediaStore3 -> AudioWatermarking + Additional
     * Information: https://www.palladio-simulator.com/tools/tutorials/ (PCM Parameter (PDF) -> 18)
     *
     * @param expression object that is passed inside the statement
     * @return VariableUsageCreator object ready to be added to a Set Variable Action
     */
    private VariableUsageCreator generateInputVariableUsage(Expression expression) {
        VariableUsageCreator variableUsage = fluentFactory.newVariableUsage();
        variableUsage.withNamespaceReference("PrimitiveType", NameUtil.getExpressionClassName(expression));
        String randomPCMName = "PrimitiveType" + "." + NameUtil.getExpressionClassName(expression);
        variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.VALUE);
        return variableUsage;
    }

    /**
     *
     * Generate Output Variable Usages
     *
     * @param returnType type of the returned variable
     * @return VariableUsageCreator object ready to be added to a Set Variable Action
     */
    private VariableUsageCreator generateOutputVariableUsage(DataType returnType) {
        VariableUsageCreator variableUsage = fluentFactory.newVariableUsage();

        String randomPCMName = "tempVariable";

        if (returnType instanceof PrimitiveDataType) {
            variableUsage.withNamespaceReference(((PrimitiveDataType) returnType).getType().toString(), randomPCMName);
            randomPCMName = ((PrimitiveDataType) returnType).getType().toString() + "." + randomPCMName;
            variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.VALUE);
        } else if (returnType instanceof CompositeDataType) {
            variableUsage.withNamespaceReference(((CompositeDataType) returnType).getEntityName(), randomPCMName);
            randomPCMName = ((CompositeDataType) returnType).getEntityName() + "." + randomPCMName;
            variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.BYTESIZE);
        } else {
            variableUsage.withNamespaceReference(null, randomPCMName);
            variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.VALUE);
        }
        return variableUsage;
    }

    /**
     *
     * Generate a Branch Action Body for statements which could be modeled as branch (if, try)
     *
     * @param node                object to create the branch for
     * @param branchActionCreator object to add the branch transition with the branch seff
     * @return BranchActionCreator with GuardedBranchTransition
     */
    private BranchActionCreator generateBranchAction(ASTNode node, BranchActionCreator branchActionCreator) {

        String condition = "";

        if (node instanceof IfStatement) {
            IfStatement ifStatement = (IfStatement) node;
            node = ifStatement.getThenStatement();
            condition = NameUtil.getIfStatementConditionString(ifStatement);
        } else if (node instanceof TryStatement) {
            TryStatement tryStatement = (TryStatement) node;
            node = tryStatement.getBody();
            condition = NameUtil.getTryStatementConditionString();
        }

        LOGGER.debug("Generate Inner Branch Behaviour");
        ActionSeff innerActionSeff = fluentFactory.newSeff().withSeffBehaviour().withStartAction()
                .withName(NameUtil.START_ACTION_NAME).followedBy();
        innerActionSeff = this.perform(node, innerActionSeff);
        SeffCreator seffCreator = innerActionSeff.stopAction().withName(NameUtil.STOP_ACTION_NAME).createBehaviourNow();

        branchActionCreator.withGuardedBranchTransition(condition, seffCreator, "Guarded Branch Transition")
                .withName(NameUtil.BRANCH_ACTION_NAME);

        return branchActionCreator;
    }

    /**
     *
     * Create the branch for the Else Statement Can be a If Else Statement or an Else Statement
     *
     * @param statement           object to create the branch for
     * @param branchActionCreator object to add the branch transition with the branch seff
     * @return BranchActionCreator with GuardedBranchTransition
     */
    private BranchActionCreator handleElseStatement(Statement statement, BranchActionCreator branchActionCreator) {

        ActionSeff innerActionSeff = fluentFactory.newSeff().withSeffBehaviour().withStartAction()
                .withName(NameUtil.START_ACTION_NAME).followedBy();

        String condition = "condition";

        if (statement instanceof IfStatement) {
            LOGGER.debug("Else Statement is If Else Statement");
            IfStatement elseIfStatement = (IfStatement) statement;
            innerActionSeff = this.perform(elseIfStatement.getThenStatement(), innerActionSeff);
            condition = NameUtil.getIfStatementConditionString(elseIfStatement);
        } else {
            LOGGER.debug("If Statement is Else Statement");
            innerActionSeff = this.perform(statement, innerActionSeff);

            condition = NameUtil.getElseStatementConditionString();
        }

        SeffCreator seffCreator = innerActionSeff.stopAction().withName(NameUtil.STOP_ACTION_NAME).createBehaviourNow();

        branchActionCreator = branchActionCreator.withGuardedBranchTransition(condition, seffCreator,
                "Guarded Branch Transition");

        if (statement instanceof IfStatement) {
            IfStatement elseIfStatement = (IfStatement) statement;
            Statement elseStatement = elseIfStatement.getElseStatement();
            // `elseStatement` may be null if `statement` does not have an else branch.
            if (elseStatement != null) {
                branchActionCreator = handleElseStatement(elseStatement, branchActionCreator);
            }
        }

        return branchActionCreator;
    }

    /**
     *
     * Generate a Loop Action Body for any statement which could be modeled as loop (while, for, enriched for)
     *
     * @param node              object which can be modeled as loop action
     * @param loopActionCreator object to insert the created SEFF as body
     * @return LoopActionCreator with loop body
     */
    private LoopActionCreator generateLoopAction(ASTNode node, LoopActionCreator loopActionCreator) {
        LOGGER.debug("Generate Inner Loop Behaviour");
        ActionSeff innerActionSeff = fluentFactory.newSeff().withSeffBehaviour().withStartAction()
                .withName(NameUtil.START_ACTION_NAME).followedBy();
        innerActionSeff = this.perform(node, innerActionSeff);
        SeffCreator seffCreator = innerActionSeff.stopAction().withName(NameUtil.STOP_ACTION_NAME).createBehaviourNow();
        loopActionCreator.withLoopBody(seffCreator);
        return loopActionCreator;
    }

    private BasicComponent getComponentOfRootNode() {
        return this.externalNodes.get(this.rootNode).getBasicComponent_ServiceEffectSpecification();
    }

    private OperationInterface getOperationInterfaceOfRootNode() {
        ResourceDemandingSEFF seff = (ResourceDemandingSEFF) this.externalNodes.get(this.rootNode);
        OperationSignature signature = (OperationSignature) seff.getDescribedService__SEFF();
        return signature.getInterface__OperationSignature();
    }

    private boolean isExternal(MethodInvocation invocation) {
        BasicComponent rootComponent = getComponentOfRootNode();
        Optional<ServiceEffectSpecification> optionalSeff = getServiceEffectSpecificationOfInvocation(invocation);

        // Invocation is external call if seff for invocation exists, & caller & callee are different components
        // Important: Component names are unique.
        return optionalSeff.isPresent() && !optionalSeff.get()
                .getBasicComponent_ServiceEffectSpecification().getEntityName().equals(rootComponent.getEntityName());
    }

    private Optional<OperationInterface> getOperationInterfaceOfInvocation(MethodInvocation invocation) {
        Optional<ServiceEffectSpecification> optionalSeff = getServiceEffectSpecificationOfInvocation(invocation);
        if (optionalSeff.isPresent()) {
            OperationSignature signature = (OperationSignature) optionalSeff.get().getDescribedService__SEFF();
            return Optional.of(signature.getInterface__OperationSignature());
        }
        return Optional.empty();
    }

    private Optional<OperationSignature> getOperationSignatureOfInvocation(MethodInvocation invocation) {
        Optional<ServiceEffectSpecification> optionalSeff = getServiceEffectSpecificationOfInvocation(invocation);
        if (optionalSeff.isPresent()) {
            return Optional.of((OperationSignature) optionalSeff.get().getDescribedService__SEFF());
        }
        return Optional.empty();
    }

    private Optional<ServiceEffectSpecification> getServiceEffectSpecificationOfInvocation(
            MethodInvocation invocation) {
        IMethodBinding invocationBinding = invocation.resolveMethodBinding();
        for (ASTNode node : this.externalNodes.keySet()) {
            ServiceEffectSpecification seff = this.externalNodes.get(node);

            // Check if ast nodes represent same method
            MethodDeclaration methodDeclaration = (MethodDeclaration) node;
            IMethodBinding declarationBinding = methodDeclaration.resolveBinding();
            if (Objects.nonNull(declarationBinding) && Objects.nonNull(invocationBinding)
                    && declarationBinding.getKey().equals(invocationBinding.getKey())) {
                return Optional.of(seff);
            }
        }
        return Optional.empty();
    }
}
