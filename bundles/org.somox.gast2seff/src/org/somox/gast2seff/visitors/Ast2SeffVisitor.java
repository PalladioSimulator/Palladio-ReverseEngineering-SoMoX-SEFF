package org.somox.gast2seff.visitors;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.palladiosimulator.pcm.core.CoreFactory;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.AcquireAction;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ReleaseAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.somox.kdmhelper.MethodAssociation;

public class Ast2SeffVisitor extends ASTVisitor {

	private static final Logger logger = Logger.getLogger(Ast2SeffVisitor.class);
		
	private EList<AbstractAction> actionList; 
	private Map<String, MethodAssociation> methodNameMap;
	private BasicComponent basicComponent;
	private ASTNode node;
	private MethodAssociation methodAssociation;
	
	public Ast2SeffVisitor(MethodAssociation methodAssociation, EList<AbstractAction> actionList, Map<String, MethodAssociation> methodNameMap) {
		this.actionList = actionList;
		this.methodNameMap = methodNameMap;
		this.methodAssociation = methodAssociation;
		this.basicComponent = methodAssociation.getBasicComponent();
		this.node = methodAssociation.getAstNode();
	}
	
	public static void perform(MethodAssociation methodAssociation, EList<AbstractAction> actionList, Map<String, MethodAssociation> methodNameMap) {
		Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(methodAssociation, actionList, methodNameMap);
		methodAssociation.getAstNode().accept(newFunctionCallClassificationVisitor);
	}
	
	
	public boolean visit(final ExpressionStatement expressionStatement) {
		
		Expression expression = expressionStatement.getExpression();

		if (expression instanceof MethodInvocation && this.isExternal((MethodInvocation) expression)) {
			MethodInvocation methodInvocation = (MethodInvocation) expression;
			MethodAssociation externalMethodAssociation = this.getExternalMethodAssociation(methodInvocation);
			BasicComponent externalBasicComponent = externalMethodAssociation.getBasicComponent();
			
			if (!externalBasicComponent.equals(basicComponent)) {
				createExternalCallAction(methodInvocation, externalBasicComponent);
			} else {
				createInternalAction();	
			}
		} else {
			createInternalAction();
		}
		return super.visit(expressionStatement);
	}
	
	private void createExternalCallAction(MethodInvocation methodInvocation, BasicComponent externalBasicComponent) {
		ExternalCallAction externalCall = SeffFactory.eINSTANCE.createExternalCallAction();
		if (methodInvocation.arguments().isEmpty()) {
			externalCall.setEntityName(methodInvocation.getName().toString());
		} else {
			externalCall.setEntityName(methodInvocation.getName().toString() + "(" + methodInvocation.arguments().toString() + ")");
		}

		OperationProvidedRole operationProvidedRole = (OperationProvidedRole) externalBasicComponent.getProvidedRoles_InterfaceProvidingEntity().get(0);
		OperationInterface operationInterface = operationProvidedRole.getProvidedInterface__OperationProvidedRole();
		OperationRequiredRole operationRequiredRole = null;
		if (basicComponent.getRequiredRoles_InterfaceRequiringEntity().size() == 0) {
			operationRequiredRole = RepositoryFactory.eINSTANCE.createOperationRequiredRole();
			operationRequiredRole.setEntityName(basicComponent.getEntityName());
			basicComponent.getRequiredRoles_InterfaceRequiringEntity().add(operationRequiredRole);
		} else {
			operationRequiredRole = (OperationRequiredRole) basicComponent.getRequiredRoles_InterfaceRequiringEntity().get(0);
		}
		operationRequiredRole.setRequiredInterface__OperationRequiredRole(operationInterface);
		externalCall.setRole_ExternalService(operationRequiredRole);
		this.actionList.add(externalCall);
	}
	
	private void createInternalAction() {
		InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();
		this.actionList.add(internalAction);
	}
	
	
	public boolean visit(final IfStatement ifStatement) {
		BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
		ResourceDemandingBehaviour branchBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
		AbstractBranchTransition branchTransition = SeffFactory.eINSTANCE.createGuardedBranchTransition();
		StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
		branchBehaviour.getSteps_Behaviour().add(startAction);
		
		branchTransition.setEntityName(this.ifStatementToString(ifStatement.getExpression()));
		
		ifStatement.getThenStatement().accept(this);
		
		StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
		branchBehaviour.getSteps_Behaviour().add(stopAction);
		VisitorUtils.connectActions(branchBehaviour);
		branchTransition.setBranchBehaviour_BranchTransition(branchBehaviour);
		branchAction.getBranches_Branch().add(branchTransition);
		
		handleElseStatement(ifStatement.getElseStatement(), branchAction);
		
		this.actionList.add(branchAction);
		return false;
	}
	
	//TODO: Correct the entity name for the else if statement 
	private void handleElseStatement(Statement statement, BranchAction branchAction) {
		if (statement != null) {
			ResourceDemandingBehaviour branchBehaviourElse = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
			AbstractBranchTransition branchTransitionElse = SeffFactory.eINSTANCE.createGuardedBranchTransition();
			StartAction startActionElse = SeffFactory.eINSTANCE.createStartAction();
			branchBehaviourElse.getSteps_Behaviour().add(startActionElse);
			if (statement instanceof IfStatement) {
				IfStatement elseIfStatement = (IfStatement) statement;
				branchTransitionElse.setEntityName(this.ifStatementToString(elseIfStatement.getExpression()));
				elseIfStatement.getThenStatement().accept(this);
			} else {
				statement.accept(this);
			}

			StopAction stopActionElse = SeffFactory.eINSTANCE.createStopAction();
			branchBehaviourElse.getSteps_Behaviour().add(stopActionElse);
			VisitorUtils.connectActions(branchBehaviourElse);
			branchTransitionElse.setBranchBehaviour_BranchTransition(branchBehaviourElse);
			branchAction.getBranches_Branch().add(branchTransitionElse);
			
			if (statement instanceof IfStatement) {
				IfStatement elseIfStatement = (IfStatement) statement;
				handleElseStatement(elseIfStatement.getElseStatement(), branchAction);
			}
		}
	}
	
	public boolean visit(final SynchronizedStatement synchronizedStatement) {
		PassiveResource passiveResource = RepositoryFactory.eINSTANCE.createPassiveResource();

		passiveResource.setCapacity_PassiveResource(CoreFactory.eINSTANCE.createPCMRandomVariable());
		Expression exp = synchronizedStatement.getExpression();
		
		//TODO: Check for other things then "this"
		String className = this.getClassName(exp);
		String methodName = this.getMethodDeclaration(synchronizedStatement).getName().toString();
		MethodAssociation methodAssociation = this.methodNameMap.get(className + "." + methodName);
		passiveResource.setEntityName(className + "." + methodName);
		methodAssociation.getBasicComponent().getPassiveResource_BasicComponent().add(passiveResource);
		passiveResource.getCapacity_PassiveResource().setSpecification("1");
		//passiveResource.getCapacity_PassiveResource().setSpecification(String.valueOf(Integer.parseInt(passiveResource.getCapacity_PassiveResource().getSpecification()) + 1));

		final AcquireAction acquireAction = SeffFactory.eINSTANCE.createAcquireAction();
		this.actionList.add(acquireAction);
		acquireAction.setPassiveresource_AcquireAction(passiveResource);
		acquireAction.setEntityName(className + "." + methodName);

		synchronizedStatement.getBody().accept(this);

		final ReleaseAction releaseAction = SeffFactory.eINSTANCE.createReleaseAction();
		this.actionList.add(releaseAction);
		releaseAction.setPassiveResource_ReleaseAction(passiveResource);
		releaseAction.setEntityName(className + "." + methodName);
		return false;
	}
	
	public boolean visit(final TryStatement tryStatement) {
		
		BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
		ResourceDemandingBehaviour branchBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
		AbstractBranchTransition branchTransition = SeffFactory.eINSTANCE.createGuardedBranchTransition();
		StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
		branchBehaviour.getSteps_Behaviour().add(startAction);

//		branchTransition.setEntityName(this.ifStatementToString(tryStatement.getExpression()));
		
		tryStatement.getBody().accept(this);
		
		StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
		branchBehaviour.getSteps_Behaviour().add(stopAction);
		VisitorUtils.connectActions(branchBehaviour);
		branchTransition.setBranchBehaviour_BranchTransition(branchBehaviour);
		branchAction.getBranches_Branch().add(branchTransition);
		
		List<CatchClause> catchClauseList = tryStatement.catchClauses();
		for (CatchClause catchClause : catchClauseList) {
			ResourceDemandingBehaviour catchBranchBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
			AbstractBranchTransition catchBranchTransition = SeffFactory.eINSTANCE.createGuardedBranchTransition();
			StartAction catchStartAction = SeffFactory.eINSTANCE.createStartAction();
			catchBranchBehaviour.getSteps_Behaviour().add(catchStartAction);
			
			catchClause.getBody().accept(this);
			
			StopAction catchStopAction = SeffFactory.eINSTANCE.createStopAction();
			catchBranchBehaviour.getSteps_Behaviour().add(catchStopAction);
			VisitorUtils.connectActions(catchBranchBehaviour);
			catchBranchTransition.setBranchBehaviour_BranchTransition(catchBranchBehaviour);
			branchAction.getBranches_Branch().add(catchBranchTransition);
		}
		
		this.actionList.add(branchAction);
		return false;
	}
	
	public boolean visit(final ForStatement forStatement) {
		LoopAction loopAction = SeffFactory.eINSTANCE.createLoopAction();
		ResourceDemandingBehaviour bodyBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
		loopAction.setBodyBehaviour_Loop(bodyBehaviour);
		StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
		bodyBehaviour.getSteps_Behaviour().add(startAction);
		
		Expression initializers = (Expression) forStatement.initializers().get(0);
		Expression updaters = (Expression) forStatement.updaters().get(0);
		loopAction.setEntityName(this.forStatementToString(initializers, forStatement.getExpression(), updaters));
		
		forStatement.getBody().accept(this);
		
		StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
		bodyBehaviour.getSteps_Behaviour().add(stopAction);
		VisitorUtils.connectActions(bodyBehaviour);
		this.actionList.add(loopAction);
		return false;
	}
	
	// TODO: Set entitiy name for the loop action
	public boolean visit(final EnhancedForStatement forStatement) {
		LoopAction loopAction = SeffFactory.eINSTANCE.createLoopAction();
		ResourceDemandingBehaviour bodyBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
		loopAction.setBodyBehaviour_Loop(bodyBehaviour);
		StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
		bodyBehaviour.getSteps_Behaviour().add(startAction);
		
//		Expression initializers = (Expression) forStatement.initializers().get(0);
//		Expression updaters = (Expression) forStatement.updaters().get(0);
//		loopAction.setEntityName(this.forStatementToString(initializers, forStatement.getExpression(), updaters));
		
		forStatement.getBody().accept(this);
		
		StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
		bodyBehaviour.getSteps_Behaviour().add(stopAction);
		VisitorUtils.connectActions(bodyBehaviour);
		this.actionList.add(loopAction);
		return false;
	}
	
	
	
	public boolean visit(final WhileStatement whileStatement) {
		LoopAction loopAction = SeffFactory.eINSTANCE.createLoopAction();
		ResourceDemandingBehaviour bodyBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
		loopAction.setBodyBehaviour_Loop(bodyBehaviour);
		StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
		bodyBehaviour.getSteps_Behaviour().add(startAction);
		
		loopAction.setEntityName(this.whileStatementToString(whileStatement.getExpression()));
		
		whileStatement.getBody().accept(this);
		
		StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
		bodyBehaviour.getSteps_Behaviour().add(stopAction);
		this.actionList.add(loopAction);
		VisitorUtils.connectActions(bodyBehaviour);
		return false;
	}
	
	public boolean visit(final SwitchStatement switchStatement) {
		BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
		
		List<List<Statement>> blockList = SwitchStatementHelper.createBlockListFromSwitchStatement(switchStatement);
		// TODO: Do we need to build an ASTNode to enable the visitor pattern?
		for (List<Statement> block : blockList) {
			ResourceDemandingBehaviour branchBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
			AbstractBranchTransition branchTransition = SeffFactory.eINSTANCE.createGuardedBranchTransition();
			StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
			branchBehaviour.getSteps_Behaviour().add(startAction);
			
			for (Statement statement : block) {
				statement.accept(this);
			}
			
			StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
			branchBehaviour.getSteps_Behaviour().add(stopAction);
			VisitorUtils.connectActions(branchBehaviour);
			branchTransition.setBranchBehaviour_BranchTransition(branchBehaviour);
			branchAction.getBranches_Branch().add(branchTransition);
		}
		
		this.actionList.add(branchAction);
		return false;
	}
	
	// TODO: Further work
	public boolean visit(final VariableDeclarationStatement variableDeclarationStatement) {
		Type test = variableDeclarationStatement.getType();
		return super.visit(variableDeclarationStatement);
	}

	protected boolean isExternal(MethodInvocation methodInvocation) {
		String methodName = methodInvocation.getName().toString();
		String className = this.getClassName(methodInvocation);
		if (this.methodNameMap.containsKey(className + "." + methodName)) {
			return true;
		} else {
			return false;
		}
	}
	
	protected MethodDeclaration getMethodDeclaration(ASTNode node) {
		if(node instanceof MethodDeclaration)
			return (MethodDeclaration) node;
		else if(node.getParent() != null)
			return getMethodDeclaration(node.getParent());
		else
			return null;
	}
	
	protected MethodAssociation getExternalMethodAssociation(MethodInvocation methodInvocation) {
		String methodName = methodInvocation.getName().toString();
		String className = this.getClassName(methodInvocation);
		if (this.methodNameMap.containsKey(className + "." + methodName)) {
			return this.methodNameMap.get(className + "." + methodName);
		} else {
			return null;
		}
	}
	
	protected String getClassName(MethodInvocation methodInvocation) {
		String result = "unknown";
		Expression calledClass = methodInvocation.getExpression();
		ITypeBinding bindingExpression = calledClass.resolveTypeBinding();
		if(bindingExpression != null && bindingExpression.getPackage() != null)
			result = bindingExpression.getPackage().getName().toString();
		else
			logger.warn("No Class Name found for: " + methodInvocation.toString());
		return result;
	}
	
	protected String getClassName(Expression calledClass) {
		String result = "unknown";
		ITypeBinding bindingExpression = calledClass.resolveTypeBinding();
		if(bindingExpression != null && bindingExpression.getPackage() != null)
			result = bindingExpression.getPackage().getName().toString();
		else
			logger.warn("No Class Name found for: " + calledClass.toString());
		return result;
	}
	
	protected String whileStatementToString(Expression expression) {
		final StringBuilder positionString = new StringBuilder(" @position: ");
		positionString.append("expression name \"").append(expression).append("\"");
		return positionString.toString();
	}

	protected String ifStatementToString(Expression expression) {
		final StringBuilder positionString = new StringBuilder(" @position: ");
		positionString.append("Cond: ").append(expression).append("");
		return positionString.toString();
	}
	
	protected String elseStatementToString(Expression expression) {
		final StringBuilder positionString = new StringBuilder(" @position: ");
		positionString.append("Cond: !").append(expression).append("");
		return positionString.toString();
	}
	
	protected String forStatementToString(Expression initializers, Expression expression, Expression updaters) {
		final StringBuilder positionString = new StringBuilder(" @position: ");
		if (initializers instanceof VariableDeclarationExpression && expression instanceof InfixExpression && updaters instanceof PostfixExpression) {
			positionString.append(" from ").append(initializers).append(" to ").append(expression).append(" with ").append(updaters);
		} else {
			positionString.append("no position information available");
		}
		return positionString.toString();
	}
}
