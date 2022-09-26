package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.palladiosimulator.somox.ast2seff.models.ComponentInformation;
import org.palladiosimulator.somox.ast2seff.models.MethodBundlePair;
import org.palladiosimulator.somox.ast2seff.models.MethodPalladioInformation;
import org.palladiosimulator.somox.ast2seff.util.NameUtil;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

public class EnhancedForStatementVisitorTest {

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
		EnhancedForStatement forStatement = ast.newEnhancedForStatement();
		SingleVariableDeclaration variableDeclaration = ast.newSingleVariableDeclaration();
		variableDeclaration.setName(ast.newSimpleName("test"));
		Expression expression = ast.newSimpleName("testList");
		forStatement.setExpression(expression);
		forStatement.setParameter(variableDeclaration);
		
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", forStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("forStatement", "forStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodBundlePair, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(3, actionList.size());
		assertTrue(actionList.get(1) instanceof LoopAction);
		assertEquals("@position: for (int test : testList)", actionList.get(1).getEntityName());
		
		LoopAction loopAction = (LoopAction) actionList.get(1);
		ResourceDemandingBehaviour resourceDemandingBehaviour = loopAction.getBodyBehaviour_Loop();
		
		assertEquals(2, resourceDemandingBehaviour.getSteps_Behaviour().size());
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction);
	}
	
	@Test
	public void singleStatementTest() {
		
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		EnhancedForStatement forStatement = ast.newEnhancedForStatement();
		Block block = ast.newBlock();
		MethodInvocation methodInvocation = ast.newMethodInvocation();
		methodInvocation.setName(ast.newSimpleName("SimpleName"));
		methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
		block.statements().add(ast.newExpressionStatement(methodInvocation));
		forStatement.setBody(block);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", forStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("forStatement", "forStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodBundlePair, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(3, actionList.size());
		assertTrue(actionList.get(1) instanceof LoopAction);
		
		LoopAction loopAction = (LoopAction) actionList.get(1);
		ResourceDemandingBehaviour resourceDemandingBehaviour = loopAction.getBodyBehaviour_Loop();
		
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
		EnhancedForStatement forStatement = ast.newEnhancedForStatement();
		Block block = ast.newBlock();
		EnhancedForStatement innerForStatement = ast.newEnhancedForStatement();
		innerForStatement.setBody(ast.newBlock());
		forStatement.setBody(innerForStatement);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", forStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("forStatement", "forStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodBundlePair, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(3, actionList.size());
		assertTrue(actionList.get(1) instanceof LoopAction);
		
		LoopAction loopAction = (LoopAction) actionList.get(1);
		ResourceDemandingBehaviour resourceDemandingBehaviour = loopAction.getBodyBehaviour_Loop();
		
		assertEquals(3, resourceDemandingBehaviour.getSteps_Behaviour().size());
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof LoopAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof StopAction);
	}
	
	@Test
	public void statementInsideOtherStatementTest() {
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
		
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		EnhancedForStatement forStatement = ast.newEnhancedForStatement();
		Block block = ast.newBlock();
		WhileStatement whileStatement = ast.newWhileStatement();
		block.statements().add(whileStatement);
		forStatement.setBody(block);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", forStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("forStatement", "forStatement", "Interface", methodBundlePair);
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		actionSeff = Ast2SeffVisitor.perform(methodBundlePair, actionSeff, methodNameMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(3, actionList.size());
		assertTrue(actionList.get(1) instanceof LoopAction);
		
		LoopAction loopAction = (LoopAction) actionList.get(1);
		ResourceDemandingBehaviour resourceDemandingBehaviour = loopAction.getBodyBehaviour_Loop();
		
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof LoopAction);
		assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof StopAction);
	}
}
