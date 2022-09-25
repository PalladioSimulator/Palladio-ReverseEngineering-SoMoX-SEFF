package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.palladiosimulator.somox.ast2seff.models.ComponentInformation;
import org.palladiosimulator.somox.ast2seff.models.MethodBundlePair;
import org.palladiosimulator.somox.ast2seff.models.MethodPalladioInformation;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

public class SwitchStatementVisitorTest {

	private static final FluentRepositoryFactory create = new FluentRepositoryFactory();
	
	// Testplan
	// 1. Test: Statement mit leerem Body
	// 2. Test: Statement mit einer System.out.println (Internal Action)
	// 3. Test: Statement mit gleichem Statement im Body
	// 4. Test: Statement mit anderem Statement im Body
	
	// Welche Assertions sollen immer getestet werden:
	// - Anzahl der Aktionen
	// - Falls ein innerer Block existiert, Anzahl der Aktionen überprüfen
	// - Existenz der zu generierenden Aktion überprüfen (z.B. Branch Action)
	
	@Test
	public void emptyBodyStatementTest() {
		
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		SwitchStatement switchStatement = ast.newSwitchStatement();
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", switchStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("switchStatement", "switchStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(actionList.size(), 3);
		assertTrue(actionList.get(1) instanceof BranchAction);
	}
	
	@Test
	public void singleCaseSwitchStatementWithIfStatementTest() {
		
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		SwitchStatement switchStatement = ast.newSwitchStatement();
		SwitchCase switchCase = ast.newSwitchCase();
		IfStatement ifStatement = ast.newIfStatement();
		
		Block block = ast.newBlock();
		block.statements().add(ifStatement);
	
		switchStatement.statements().add(switchCase);
		switchStatement.statements().add(block);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", switchStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("switchStatement", "switchStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(actionList.size(), 3);
		assertTrue(actionList.get(1) instanceof BranchAction);
		
		BranchAction branchAction = (BranchAction) actionList.get(1);
		AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(0);
		ResourceDemandingBehaviour resourceDemandingBehaviour = branchTransition.getBranchBehaviour_BranchTransition();
		
		assertEquals(branchAction.getBranches_Branch().size(), 1);
		assertEquals(resourceDemandingBehaviour.getSteps_Behaviour().size(), 3);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof BranchAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof StopAction);
		
	}
	
	@Test
	public void singleCaseSwitchStatementWithExpressionStatementTest() {
		
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		SwitchStatement switchStatement = ast.newSwitchStatement();
		SwitchCase switchCase = ast.newSwitchCase();
		
		Block block = ast.newBlock();
		MethodInvocation methodInvocation = ast.newMethodInvocation();
		methodInvocation.setName(ast.newSimpleName("SimpleName"));
		methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
		block.statements().add(ast.newExpressionStatement(methodInvocation));
	
		switchStatement.statements().add(switchCase);
		switchStatement.statements().add(block);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", switchStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("switchStatement", "switchStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(actionList.size(), 3);
		assertTrue(actionList.get(1) instanceof BranchAction);
		
		BranchAction branchAction = (BranchAction) actionList.get(1);
		AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(0);
		ResourceDemandingBehaviour resourceDemandingBehaviour = branchTransition.getBranchBehaviour_BranchTransition();
		
		assertEquals(branchAction.getBranches_Branch().size(), 1);
		assertEquals(resourceDemandingBehaviour.getSteps_Behaviour().size(), 3);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof InternalAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof StopAction);
		
	}
	
	@Test
	public void singleCaseSwitchStatementWithSwitchStatementTest() {
		
		
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		SwitchStatement switchStatement = ast.newSwitchStatement();
		SwitchCase switchCase = ast.newSwitchCase();
		
		Block block = ast.newBlock();
		SwitchStatement innerSwitchStatement = ast.newSwitchStatement();
		SwitchCase innerSwitchCase = ast.newSwitchCase();
		MethodInvocation methodInvocation = ast.newMethodInvocation();
		methodInvocation.setName(ast.newSimpleName("SimpleName"));
		methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
		block.statements().add(innerSwitchStatement);
		block.statements().add(innerSwitchCase);
		
		switchStatement.statements().add(switchCase);
		switchStatement.statements().add(block);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", switchStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("switchStatement", "switchStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(actionList.size(), 3);
		assertTrue(actionList.get(1) instanceof BranchAction);
		
		BranchAction branchAction = (BranchAction) actionList.get(1);
		AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(0);
		ResourceDemandingBehaviour resourceDemandingBehaviour = branchTransition.getBranchBehaviour_BranchTransition();
		
		assertEquals(branchAction.getBranches_Branch().size(), 1);
		assertEquals(resourceDemandingBehaviour.getSteps_Behaviour().size(), 3);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof BranchAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof StopAction);
		
	}
	

	@Test
	public void twoCaseSwitchStatementTest() {
		
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		SwitchStatement switchStatement = ast.newSwitchStatement();
		SwitchCase switchCaseOne = ast.newSwitchCase();
		SwitchCase switchCaseTwo = ast.newSwitchCase();
		BreakStatement breakStatementOne = ast.newBreakStatement();
		BreakStatement breakStatementTwo = ast.newBreakStatement();
		
		Block blockOne = ast.newBlock();
		Block blockTwo = ast.newBlock();
		MethodInvocation methodInvocationOne = ast.newMethodInvocation();
		MethodInvocation methodInvocationTwo = ast.newMethodInvocation();
		methodInvocationOne.setName(ast.newSimpleName("SimpleName"));
		methodInvocationOne.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
		methodInvocationTwo.setName(ast.newSimpleName("SimpleName"));
		methodInvocationTwo.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
		blockOne.statements().add(ast.newExpressionStatement(methodInvocationOne));
		blockTwo.statements().add(ast.newExpressionStatement(methodInvocationTwo));
		
		switchStatement.statements().add(switchCaseOne);
		switchStatement.statements().add(blockOne);
		switchStatement.statements().add(breakStatementOne);
		switchStatement.statements().add(switchCaseTwo);
		switchStatement.statements().add(blockTwo);
		switchStatement.statements().add(breakStatementTwo);
		
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", switchStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("switchStatement", "switchStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(actionList.size(), 3);
		assertTrue(actionList.get(1) instanceof BranchAction);
		
		BranchAction branchAction = (BranchAction) actionList.get(1);
		AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(0);
		ResourceDemandingBehaviour resourceDemandingBehaviour = branchTransition.getBranchBehaviour_BranchTransition();
		
		assertEquals(branchAction.getBranches_Branch().size(), 2);
		assertEquals(resourceDemandingBehaviour.getSteps_Behaviour().size(), 3);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof InternalAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof StopAction);
		
	}
	


	@Test
	public void twoCaseSwitchStatementWithoutBreakTest() {
		
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		SwitchStatement switchStatement = ast.newSwitchStatement();
		SwitchCase switchCaseOne = ast.newSwitchCase();
		SwitchCase switchCaseTwo = ast.newSwitchCase();
		BreakStatement breakStatementOne = ast.newBreakStatement();
		
		Block blockOne = ast.newBlock();
		Block blockTwo = ast.newBlock();
		MethodInvocation methodInvocationOne = ast.newMethodInvocation();
		MethodInvocation methodInvocationTwo = ast.newMethodInvocation();
		methodInvocationOne.setName(ast.newSimpleName("SimpleName"));
		methodInvocationOne.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
		methodInvocationTwo.setName(ast.newSimpleName("SimpleName"));
		methodInvocationTwo.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
		blockOne.statements().add(ast.newExpressionStatement(methodInvocationOne));
		blockTwo.statements().add(ast.newExpressionStatement(methodInvocationTwo));
		
		switchStatement.statements().add(switchCaseOne);
		switchStatement.statements().add(blockOne);
		switchStatement.statements().add(switchCaseTwo);
		switchStatement.statements().add(blockTwo);
		switchStatement.statements().add(breakStatementOne);
		
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", switchStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("switchStatement", "switchStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(actionList.size(), 3);
		assertTrue(actionList.get(1) instanceof BranchAction);
		
		BranchAction branchAction = (BranchAction) actionList.get(1);
		AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(0);
		ResourceDemandingBehaviour resourceDemandingBehaviour = branchTransition.getBranchBehaviour_BranchTransition();
		
		assertEquals(branchAction.getBranches_Branch().size(), 2);
		assertEquals(resourceDemandingBehaviour.getSteps_Behaviour().size(), 4);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof InternalAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof InternalAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(3) instanceof StopAction);
		
	}
	
}
