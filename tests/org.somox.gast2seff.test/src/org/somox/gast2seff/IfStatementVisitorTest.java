package org.somox.gast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.somox.gast2seff.visitors.Ast2SeffVisitor;
import org.somox.kdmhelper.MethodBundlePair;

public class IfStatementVisitorTest {
	
	private static final FluentRepositoryFactory create = new FluentRepositoryFactory();
	
	@Test
	public void emptyIfStatementVisitorTest() {
		
		ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		Map<String, MethodBundlePair> methodNameMap = new HashMap<>();
		BasicComponent basicComponent = RepositoryFactory.eINSTANCE.createBasicComponent();
		ResourceDemandingSEFF seff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		IfStatement ifStatement = ast.newIfStatement();
		MethodBundlePair methodAssociation = new MethodBundlePair(ifStatement, seff, basicComponent);
		actionSeff = Ast2SeffVisitor.perform(methodAssociation, actionSeff, methodNameMap);

		seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
		EList<AbstractAction> actionList = seff.getSteps_Behaviour();
		
		assertEquals(actionList.size(), 1);
		assertEquals(actionList.get(0) instanceof BranchAction, true);
		
		BranchAction branchAction = (BranchAction) actionList.get(0);
		AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(0);
		ResourceDemandingBehaviour resourceDemandingBehaviour = branchTransition.getBranchBehaviour_BranchTransition();
		
		assertEquals(resourceDemandingBehaviour.getSteps_Behaviour().size(), 2);
		assertEquals(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction, true);
		assertEquals(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction, true);
	}

}
