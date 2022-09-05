package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.RepoAddition;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationInterfaceCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationSignatureCreator;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.InternalCallAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.palladiosimulator.somox.ast2seff.models.ComponentInformation;
import org.palladiosimulator.somox.ast2seff.models.MethodBundlePair;
import org.palladiosimulator.somox.ast2seff.models.MethodPalladioInformation;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

public class ExpressionStatementVisitorTest {
	
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
	public void internalActionTest() {
		
		RepoAddition repoAddition = create.newRepository().withName("Simple Repository");
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodPalladioInfoMap = new HashMap<>();
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		MethodInvocation methodInvocation = ast.newMethodInvocation();
		methodInvocation.setName(ast.newSimpleName("SimpleName"));
		methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
		ExpressionStatement expressionStatement = ast.newExpressionStatement(methodInvocation);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", expressionStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("expressionStatement", "expressionStatement", "Simple Component", methodBundlePair);
	
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodPalladioInfoMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(actionList.size(), 3);
		assertTrue(actionList.get(1) instanceof InternalAction);
		
	}
	
	@Test
	public void internalCallActionTest() {
		RepoAddition repoAddition = create.newRepository().withName("Simple Repository");
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodPalladioInfoMap = new HashMap<>();
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		MethodInvocation methodInvocation = ast.newMethodInvocation();
		methodInvocation.setName(ast.newSimpleName("SimpleName"));
		methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
		ExpressionStatement expressionStatement = ast.newExpressionStatement(methodInvocation);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", expressionStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("expressionStatement", "expressionStatement", "Simple Component", methodBundlePair);
		OperationSignatureCreator methodOperationSignature = create.newOperationSignature().withName("expressionStatement");
		OperationInterfaceCreator bundleOperationInterfaceCreator = create.newOperationInterface().withName("Simple Component");
		bundleOperationInterfaceCreator.withOperationSignature(methodOperationSignature);
		repoAddition.addToRepository(bundleOperationInterfaceCreator);
		
		methodPalladioInfoMap.put("unknown.SimpleName", methodPalladioInformation);
		
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodPalladioInfoMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(actionList.size(), 3);
		assertTrue(actionList.get(1) instanceof InternalCallAction);
	}
	
	@Test
	public void externalCallActionTest() {
		
		RepoAddition repoAddition = create.newRepository().withName("Simple Repository");
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodPalladioInformation> methodPalladioInfoMap = new HashMap<>();
		BasicComponentCreator basicComponentCreator = create.newBasicComponent();
		ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
		
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		MethodInvocation methodInvocation = ast.newMethodInvocation();
		methodInvocation.setName(ast.newSimpleName("SimpleName"));
		methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
		ExpressionStatement expressionStatement = ast.newExpressionStatement(methodInvocation);
		MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", expressionStatement);
		MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("expressionStatement", "expressionStatement", "Simple Component", methodBundlePair);
		MethodPalladioInformation methodPalladioInformationTwo = new MethodPalladioInformation("expressionStatementTwo", "expressionStatementTwo", "Simple Component Two", methodBundlePair);
		OperationSignatureCreator methodOperationSignature = create.newOperationSignature().withName("expressionStatementTwo");
		OperationInterfaceCreator bundleOperationInterfaceCreator = create.newOperationInterface().withName("Simple Component Two");
		bundleOperationInterfaceCreator.withOperationSignature(methodOperationSignature);
		repoAddition.addToRepository(bundleOperationInterfaceCreator);
		
		methodPalladioInfoMap.put("unknown.SimpleName", methodPalladioInformationTwo);
		
		actionSeff = Ast2SeffVisitor.perform(methodPalladioInformation, actionSeff, methodPalladioInfoMap, componentInformation, create);
		
		ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(actionList.size(), 3);
		assertTrue(actionList.get(1) instanceof ExternalCallAction);
	}
	
	
}
