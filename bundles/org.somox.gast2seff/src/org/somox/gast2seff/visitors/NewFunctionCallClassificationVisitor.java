package org.somox.gast2seff.visitors;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.WhileStatement;
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

public class NewFunctionCallClassificationVisitor extends ASTVisitor {

	private static final Logger logger = Logger.getLogger(NewFunctionCallClassificationVisitor.class);
		
	private IFunctionClassificationStrategy functionClassificationStrategy = null;
	private  EList<AbstractAction> actionList; 
	
	public NewFunctionCallClassificationVisitor(EList<AbstractAction> actionList) {
		this.actionList = actionList;
	}
	
	public static void perform(ASTNode node, EList<AbstractAction> actionList) {
		NewFunctionCallClassificationVisitor newFunctionCallClassificationVisitor = new NewFunctionCallClassificationVisitor(actionList);
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
		
		Block block = (Block) ifStatement.getThenStatement();
		NewFunctionCallClassificationVisitor.perform(block, branchBehaviour.getSteps_Behaviour());
		
		StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
		branchBehaviour.getSteps_Behaviour().add(stopAction);
		branchTransition.setBranchBehaviour_BranchTransition(branchBehaviour);
		branchAction.getBranches_Branch().add(branchTransition);
		this.actionList.add(branchAction);
		return false;
	}
	
	public boolean visit(final ForStatement forStatement) {
		LoopAction loopAction = SeffFactory.eINSTANCE.createLoopAction();
		this.actionList.add(loopAction);
		return super.visit(forStatement);
	}
	
	public boolean visit(final WhileStatement whileStatement) {
		LoopAction loopAction = SeffFactory.eINSTANCE.createLoopAction();
		this.actionList.add(loopAction);
		return super.visit(whileStatement);
	}
	
}
