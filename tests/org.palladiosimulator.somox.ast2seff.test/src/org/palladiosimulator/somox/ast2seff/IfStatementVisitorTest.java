package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
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

public class IfStatementVisitorTest {
	
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
		IfStatement ifStatement = ast.newIfStatement();
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", ifStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("ifStatement", "ifStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(3, actionList.size());
		assertTrue(actionList.get(1) instanceof BranchAction);
		assertEquals("If Branch", actionList.get(1).getEntityName());
		
		BranchAction branchAction = (BranchAction) actionList.get(1);
		AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(0);
		ResourceDemandingBehaviour resourceDemandingBehaviour = branchTransition.getBranchBehaviour_BranchTransition();
		
		assertEquals("Guarded Branch Transition", branchTransition.getEntityName());
		assertEquals(1, branchAction.getBranches_Branch().size());
		assertEquals(2, resourceDemandingBehaviour.getSteps_Behaviour().size());
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction);
	}
	
	@Test
	public void ifElseIfElseStatementTest() {
		
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		IfStatement ifStatement = ast.newIfStatement();
		Block block = ast.newBlock();
		IfStatement innerIfStatement = ast.newIfStatement();
		innerIfStatement.setThenStatement(ast.newBlock());
		innerIfStatement.setElseStatement(ast.newBlock());
		ifStatement.setThenStatement(ast.newBlock());
		ifStatement.setElseStatement(innerIfStatement);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", ifStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("ifStatement", "ifStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(3, actionList.size());
		assertTrue(actionList.get(1) instanceof BranchAction);
		
		BranchAction branchAction = (BranchAction) actionList.get(1);
		AbstractBranchTransition firstBranchTransition = branchAction.getBranches_Branch().get(0);
		AbstractBranchTransition secondBranchTransition = branchAction.getBranches_Branch().get(1);

		AbstractBranchTransition thirdBranchTransition = branchAction.getBranches_Branch().get(2);
		ResourceDemandingBehaviour firstResourceDemandingBehaviour = firstBranchTransition.getBranchBehaviour_BranchTransition();
		ResourceDemandingBehaviour secondResourceDemandingBehaviour = secondBranchTransition.getBranchBehaviour_BranchTransition();
		ResourceDemandingBehaviour thirdResourceDemandingBehaviour = thirdBranchTransition.getBranchBehaviour_BranchTransition();
		
		assertEquals(3, branchAction.getBranches_Branch().size());
		assertEquals(2, firstResourceDemandingBehaviour.getSteps_Behaviour().size());
		assertTrue(firstResourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(firstResourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction);
		assertEquals(2, secondResourceDemandingBehaviour.getSteps_Behaviour().size());
		assertTrue(secondResourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(secondResourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction);
		assertEquals(2, thirdResourceDemandingBehaviour.getSteps_Behaviour().size());
		assertTrue(thirdResourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(thirdResourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction);
	}
	
	@Test
	public void singleStatementTest() {
		
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		IfStatement ifStatement = ast.newIfStatement();
		Block block = ast.newBlock();
		MethodInvocation methodInvocation = ast.newMethodInvocation();
		methodInvocation.setName(ast.newSimpleName("SimpleName"));
		methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
		block.statements().add(ast.newExpressionStatement(methodInvocation));
		ifStatement.setThenStatement(block);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", ifStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("ifStatement", "ifStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(3, actionList.size());
		assertTrue(actionList.get(1) instanceof BranchAction);
		
		BranchAction branchAction = (BranchAction) actionList.get(1);
		AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(0);
		ResourceDemandingBehaviour resourceDemandingBehaviour = branchTransition.getBranchBehaviour_BranchTransition();
		
		assertEquals(1, branchAction.getBranches_Branch().size());
		assertEquals(3, resourceDemandingBehaviour.getSteps_Behaviour().size());
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof InternalAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof StopAction);
	}
	
	@Test
	public void statementInsideSameStatementTest() {
		
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		IfStatement ifStatement = ast.newIfStatement();
		Block block = ast.newBlock();
		IfStatement innerIfStatement = ast.newIfStatement();
		innerIfStatement.setThenStatement(ast.newBlock());
		ifStatement.setThenStatement(innerIfStatement);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", ifStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("ifStatement", "ifStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(3, actionList.size());
		assertTrue(actionList.get(1) instanceof BranchAction);
		
		BranchAction branchAction = (BranchAction) actionList.get(1);
		AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(0);
		ResourceDemandingBehaviour resourceDemandingBehaviour = branchTransition.getBranchBehaviour_BranchTransition();
		
		assertEquals(1, branchAction.getBranches_Branch().size());
		assertEquals(3, resourceDemandingBehaviour.getSteps_Behaviour().size());
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof BranchAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof StopAction);
	}
	
	@Test
	public void statementInsideOtherStatementTest() {
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		IfStatement ifStatement = ast.newIfStatement();
		Block block = ast.newBlock();
		WhileStatement whileStatement = ast.newWhileStatement();
		block.statements().add(whileStatement);
		ifStatement.setThenStatement(block);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", ifStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("ifStatement", "ifStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(3, actionList.size());
		assertTrue(actionList.get(1) instanceof BranchAction);
		
		BranchAction branchAction = (BranchAction) actionList.get(1);
		AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(0);
		ResourceDemandingBehaviour resourceDemandingBehaviour = branchTransition.getBranchBehaviour_BranchTransition();
		
		assertEquals(1, branchAction.getBranches_Branch().size());
		assertEquals(3, resourceDemandingBehaviour.getSteps_Behaviour().size());
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof LoopAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof StopAction);
	}

}
