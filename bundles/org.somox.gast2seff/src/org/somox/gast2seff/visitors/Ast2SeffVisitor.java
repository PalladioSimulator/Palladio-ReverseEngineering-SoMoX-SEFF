package org.somox.gast2seff.visitors;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import de.uka.ipd.sdq.workflow.blackboard.Blackboard;

public class Ast2SeffVisitor extends ASTVisitor {

	private static final Logger logger = Logger.getLogger(Ast2SeffVisitor.class);
		
	private IFunctionClassificationStrategy functionClassificationStrategy = null;
	private EList<AbstractAction> actionList; 
	private Blackboard<Object> blackboard;
	
	public Ast2SeffVisitor(EList<AbstractAction> actionList, Blackboard<Object> blackboard) {
		this.actionList = actionList;
		this.blackboard = blackboard;
	}
	
	public static void perform(ASTNode node, EList<AbstractAction> actionList, Blackboard blackboard) {
		Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(actionList, blackboard);
		node.accept(newFunctionCallClassificationVisitor);
	}
	
//	public boolean visit(final MethodDeclaration methodDeclaration) {
//		
//		final Block body = methodDeclaration.getBody();
//		List<Statement> bodyStatement = body.statements();
//		for (Statement statement : bodyStatement) {
//			if (statement instanceof ExpressionStatement) {
//				InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();
//				this.resourceDemandingSEFF.getSteps_Behaviour().add(internalAction);
//			}
//			
//			if (statement instanceof IfStatement) {
//				BranchAction branchAction = generateBranchAction((IfStatement) statement);
//				this.resourceDemandingSEFF.getSteps_Behaviour().add(branchAction);
//			}
//		}
//		
//		return super.visit(methodDeclaration);
//	}
	
	public boolean visit(final ExpressionStatement expressionStatement) {
		
		InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();
		this.actionList.add(internalAction);
		return super.visit(expressionStatement);
	}
	
	
	public boolean visit(final IfStatement ifStatement) {
		BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
		ResourceDemandingBehaviour branchBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
		AbstractBranchTransition branchTransition = SeffFactory.eINSTANCE.createGuardedBranchTransition();
		StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
		branchBehaviour.getSteps_Behaviour().add(startAction);
		
		branchTransition.setEntityName(this.ifStatementToString(ifStatement.getExpression()));
		
		Ast2SeffVisitor.perform(ifStatement.getThenStatement(), branchBehaviour.getSteps_Behaviour(), this.blackboard);
		
		StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
		branchBehaviour.getSteps_Behaviour().add(stopAction);
		VisitorUtils.connectActions(branchBehaviour);
		branchTransition.setBranchBehaviour_BranchTransition(branchBehaviour);
		branchAction.getBranches_Branch().add(branchTransition);
		
		if(ifStatement.getElseStatement() != null) {
			ResourceDemandingBehaviour branchBehaviourElse = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
			AbstractBranchTransition branchTransitionElse = SeffFactory.eINSTANCE.createGuardedBranchTransition();
			StartAction startActionElse = SeffFactory.eINSTANCE.createStartAction();
			branchBehaviourElse.getSteps_Behaviour().add(startActionElse);
			branchTransitionElse.setEntityName(this.elseStatementToString(ifStatement.getExpression()));
			
			//Block elseBlock = (Block) ifStatement.getElseStatement();
			Ast2SeffVisitor.perform(ifStatement.getElseStatement(), branchBehaviourElse.getSteps_Behaviour(), this.blackboard);
			
			StopAction stopActionElse = SeffFactory.eINSTANCE.createStopAction();
			branchBehaviourElse.getSteps_Behaviour().add(stopActionElse);
			VisitorUtils.connectActions(branchBehaviourElse);
			branchTransitionElse.setBranchBehaviour_BranchTransition(branchBehaviourElse);
			branchAction.getBranches_Branch().add(branchTransitionElse);
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
		
		Ast2SeffVisitor.perform(forStatement.getBody(), bodyBehaviour.getSteps_Behaviour(), this.blackboard);
		
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
		
		Ast2SeffVisitor.perform(whileStatement.getBody(), bodyBehaviour.getSteps_Behaviour(), this.blackboard);
		
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
				Ast2SeffVisitor.perform(statement, branchBehaviour.getSteps_Behaviour(), this.blackboard);
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
	

	protected boolean isExternal(MethodDeclaration methodDeclaration) {
		Map<MethodDeclaration, ResourceDemandingSEFF> methodBindingMap = (Map<MethodDeclaration, ResourceDemandingSEFF>) this.blackboard.getPartition("methodBindingMap");
		//methodBindingMap.containsKey()
		return false;
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
		if(initializers instanceof VariableDeclarationExpression && expression instanceof InfixExpression && updaters instanceof PostfixExpression)
		{
			positionString.append(" from ").append(initializers).append(" to ").append(expression).append(" with ").append(updaters);
		} else {
			positionString.append("no position information available");
		}
		return positionString.toString();
	}
}
