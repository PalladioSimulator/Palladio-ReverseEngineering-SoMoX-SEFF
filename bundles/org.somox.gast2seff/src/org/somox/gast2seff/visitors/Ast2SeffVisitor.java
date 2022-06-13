package org.somox.gast2seff.visitors;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IPackageBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.palladiosimulator.pcm.core.CoreFactory;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
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
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.somox.kdmhelper.KDMHelper;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;

public class Ast2SeffVisitor extends ASTVisitor {

	private static final Logger logger = Logger.getLogger(Ast2SeffVisitor.class);
		
	private IFunctionClassificationStrategy functionClassificationStrategy = null;
	private EList<AbstractAction> actionList; 
	private Map<String, ResourceDemandingSEFF> methodNameMap;
	
	public Ast2SeffVisitor(EList<AbstractAction> actionList, Map<String, ResourceDemandingSEFF> methodNameMap) {
		this.actionList = actionList;
		this.methodNameMap = methodNameMap;
	}
	
	public static void perform(ASTNode node, EList<AbstractAction> actionList, Map<String, ResourceDemandingSEFF> methodNameMap) {
		Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(actionList, methodNameMap);
		node.accept(newFunctionCallClassificationVisitor);
	}
	
	public boolean visit(final ExpressionStatement expressionStatement) {
		
		Expression expression = expressionStatement.getExpression();
		if (expression instanceof MethodInvocation && this.isExternal((MethodInvocation) expression)) {
			MethodInvocation transformedExpression = (MethodInvocation) expression;
			ExternalCallAction externalCall = SeffFactory.eINSTANCE.createExternalCallAction();
			if (transformedExpression.arguments().isEmpty()) {
				externalCall.setEntityName(transformedExpression.getName().toString());				
			} else {
				externalCall.setEntityName(transformedExpression.getName().toString() + "(" + transformedExpression.arguments().toString() + ")");				
			}
			ResourceDemandingSEFF externalSeff = this.getExternalSeff(transformedExpression);
			//externalCall.setRole_ExternalService((OperationRequiredRole) ifOperationTuple.role);
			//externalCall.setCalledService_ExternalService((OperationSignature) ifOperationTuple.signature);
			this.actionList.add(externalCall);
		} else {
			InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();
			this.actionList.add(internalAction);
		}
		return super.visit(expressionStatement);
	}
	
	
	public boolean visit(final IfStatement ifStatement) {
		BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
		ResourceDemandingBehaviour branchBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
		AbstractBranchTransition branchTransition = SeffFactory.eINSTANCE.createGuardedBranchTransition();
		StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
		branchBehaviour.getSteps_Behaviour().add(startAction);
		
		branchTransition.setEntityName(this.ifStatementToString(ifStatement.getExpression()));
		
		Ast2SeffVisitor.perform(ifStatement.getThenStatement(), branchBehaviour.getSteps_Behaviour(), this.methodNameMap);
		
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
				
				Ast2SeffVisitor.perform(elseIfStatement.getThenStatement(), branchBehaviourElse.getSteps_Behaviour(), this.methodNameMap);
				
			} else {
				Block elseStatement = (Block) statement;
				Ast2SeffVisitor.perform(elseStatement, branchBehaviourElse.getSteps_Behaviour(), this.methodNameMap);
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
	
	// TODO: finish SynchronizedStatement
	public boolean visit(final SynchronizedStatement synchronizedStatement) {
		final PassiveResource passiveResource = RepositoryFactory.eINSTANCE.createPassiveResource();
//		passiveResource.setEntityName(this.positionToString(KDMHelper.getJavaNodeSourceRegion(synchronizedBlock)));

		passiveResource.setCapacity_PassiveResource(CoreFactory.eINSTANCE.createPCMRandomVariable());
//		this.primitiveComponent.getPassiveResource_BasicComponent().add(passiveResource);
//		passiveResource.getCapacity_PassiveResource().setSpecification("1");

//		JaMoPPStatementVisitor.logger.debug("start handling synchronized statement");
		final AcquireAction acquireAction = SeffFactory.eINSTANCE.createAcquireAction();
//		JaMoPPStatementVisitor.logger.debug("create acquireAction");

		this.actionList.add(acquireAction);

		acquireAction.setPassiveresource_AcquireAction(passiveResource);
//		acquireAction.setEntityName(this.positionToString(KDMHelper.getJavaNodeSourceRegion(synchronizedBlock)));

		Ast2SeffVisitor.perform(synchronizedStatement.getBody(), this.actionList, this.methodNameMap);
//		final Object ret = this.handleStatementListContainer(synchronizedBlock);

//		JaMoPPStatementVisitor.logger.debug("create releaseAction");
		final ReleaseAction releaseAction = SeffFactory.eINSTANCE.createReleaseAction();

		this.actionList.add(releaseAction);
		releaseAction.setPassiveResource_ReleaseAction(passiveResource);
//		releaseAction.setEntityName(this.positionToString(KDMHelper.getJavaNodeSourceRegion(synchronizedBlock)));
		return false;
	}
	
	public boolean visit(final TryStatement tryStatement) {
		
		BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
		ResourceDemandingBehaviour branchBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
		AbstractBranchTransition branchTransition = SeffFactory.eINSTANCE.createGuardedBranchTransition();
		StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
		branchBehaviour.getSteps_Behaviour().add(startAction);

//		branchTransition.setEntityName(this.ifStatementToString(tryStatement.getExpression()));
		
		Ast2SeffVisitor.perform(tryStatement.getBody(), branchBehaviour.getSteps_Behaviour(), this.methodNameMap);
		
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
			
			Ast2SeffVisitor.perform(catchClause.getBody(), catchBranchBehaviour.getSteps_Behaviour(), this.methodNameMap);
			
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
		
		Ast2SeffVisitor.perform(forStatement.getBody(), bodyBehaviour.getSteps_Behaviour(), this.methodNameMap);
		
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
		
		Ast2SeffVisitor.perform(forStatement.getBody(), bodyBehaviour.getSteps_Behaviour(), this.methodNameMap);
		
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
		
		Ast2SeffVisitor.perform(whileStatement.getBody(), bodyBehaviour.getSteps_Behaviour(), this.methodNameMap);
		
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
				Ast2SeffVisitor.perform(statement, branchBehaviour.getSteps_Behaviour(), this.methodNameMap);
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
	
	protected ResourceDemandingSEFF getExternalSeff(MethodInvocation methodInvocation) {
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
