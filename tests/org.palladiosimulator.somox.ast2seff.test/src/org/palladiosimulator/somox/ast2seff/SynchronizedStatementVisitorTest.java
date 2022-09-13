package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AcquireAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ReleaseAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.palladiosimulator.somox.ast2seff.models.ComponentInformation;
import org.palladiosimulator.somox.ast2seff.models.MethodBundlePair;
import org.palladiosimulator.somox.ast2seff.models.MethodPalladioInformation;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

public class SynchronizedStatementVisitorTest {
	
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
			
			create.newRepository();
			BasicComponentCreator basicComponentCreator = create.newBasicComponent();
			AST ast = AST.newAST(AST.getJLSLatest(), false);
			SynchronizedStatement synchronizedStatement = ast.newSynchronizedStatement();
			MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", synchronizedStatement);
			MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("synchronizedStatement", "synchronizedStatement", "Interface", methodBundlePair);
			ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
			actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
			
			ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
			EList<AbstractAction> actionList = seff.getSteps_Behaviour();
			
			assertEquals(actionList.size(), 4);
			assertTrue(actionList.get(0) instanceof StartAction);
			assertTrue(actionList.get(1) instanceof AcquireAction);
			assertTrue(actionList.get(2) instanceof ReleaseAction);
			assertTrue(actionList.get(3) instanceof StopAction);
			
		}
		
		@Test
		public void singleStatementTest() {
			
			ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
			Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
			
			create.newRepository();
			BasicComponentCreator basicComponentCreator = create.newBasicComponent();
			AST ast = AST.newAST(AST.getJLSLatest(), false);
			SynchronizedStatement synchronizedStatement = ast.newSynchronizedStatement();
			
			Block block = ast.newBlock();
			MethodInvocation methodInvocation = ast.newMethodInvocation();
			methodInvocation.setName(ast.newSimpleName("SimpleName"));
			methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
			block.statements().add(ast.newExpressionStatement(methodInvocation));
			synchronizedStatement.setBody(block);
			
			MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", synchronizedStatement);
			MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("synchronizedStatement", "synchronizedStatement", "Interface", methodBundlePair);
			ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
			actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
			
			ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
			EList<AbstractAction> actionList = seff.getSteps_Behaviour();
			
			assertEquals(actionList.size(), 5);
			assertTrue(actionList.get(0) instanceof StartAction);
			assertTrue(actionList.get(1) instanceof AcquireAction);
			assertTrue(actionList.get(2) instanceof InternalAction);
			assertTrue(actionList.get(3) instanceof ReleaseAction);
			assertTrue(actionList.get(4) instanceof StopAction);

		}
		
		@Test
		public void statementInsideSameStatementTest() {
			
			ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
			Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
			
			create.newRepository();
			BasicComponentCreator basicComponentCreator = create.newBasicComponent();
			AST ast = AST.newAST(AST.getJLSLatest(), false);
			SynchronizedStatement synchronizedStatement = ast.newSynchronizedStatement();
			Block block = ast.newBlock();
			block.statements().add(ast.newSynchronizedStatement());
			synchronizedStatement.setBody(block);
			MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", synchronizedStatement);
			MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("synchronizedStatement", "synchronizedStatement", "Interface", methodBundlePair);
			ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
			actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
			
			ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
			EList<AbstractAction> actionList = seff.getSteps_Behaviour();
			
			assertEquals(actionList.size(), 6);
			assertTrue(actionList.get(0) instanceof StartAction);
			assertTrue(actionList.get(1) instanceof AcquireAction);
			assertTrue(actionList.get(2) instanceof AcquireAction);
			assertTrue(actionList.get(3) instanceof ReleaseAction);
			assertTrue(actionList.get(4) instanceof ReleaseAction);
			assertTrue(actionList.get(5) instanceof StopAction);
			
		}
		
		@Test
		public void statementInsideOtherStatementTest() {
			
			ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
			Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();
			
			create.newRepository();
			BasicComponentCreator basicComponentCreator = create.newBasicComponent();
			AST ast = AST.newAST(AST.getJLSLatest(), false);
			SynchronizedStatement synchronizedStatement = ast.newSynchronizedStatement();
			
			Block block = ast.newBlock();
			WhileStatement whileStatement = ast.newWhileStatement();
			block.statements().add(whileStatement);
			synchronizedStatement.setBody(block);
			
			MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", synchronizedStatement);
			MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("synchronizedStatement", "synchronizedStatement", "Interface", methodBundlePair);
			ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
			actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodNameMap, componentInformation, create);
			
			ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
			EList<AbstractAction> actionList = seff.getSteps_Behaviour();
			
			assertEquals(actionList.size(), 5);
			assertTrue(actionList.get(0) instanceof StartAction);
			assertTrue(actionList.get(1) instanceof AcquireAction);
			assertTrue(actionList.get(2) instanceof LoopAction);
			assertTrue(actionList.get(3) instanceof ReleaseAction);
			assertTrue(actionList.get(4) instanceof StopAction);
			
			LoopAction loopAction = (LoopAction) actionList.get(2);
			ResourceDemandingBehaviour resourceDemandingBehaviour = loopAction.getBodyBehaviour_Loop();
			
			assertEquals(resourceDemandingBehaviour.getSteps_Behaviour().size(), 2);
			assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
			assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction);
			

		}
	
}
