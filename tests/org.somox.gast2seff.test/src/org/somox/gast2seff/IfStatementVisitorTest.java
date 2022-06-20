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
import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.somox.gast2seff.visitors.Ast2SeffVisitor;
import org.somox.kdmhelper.MethodAssociation;

public class IfStatementVisitorTest {
	
	@Test
	public void emptyIfStatementVisitorTest() {
		EList<AbstractAction> actionList = new BasicEList();
		Map<String, MethodAssociation> methodNameMap = new HashMap<>();
		List<PassiveResource> passiveResourceList = new ArrayList<PassiveResource>();
		Ast2SeffVisitor visitor = new Ast2SeffVisitor(actionList, methodNameMap, passiveResourceList);
		AST ast = AST.newAST(AST.getJLSLatest(), false);
		IfStatement ifStatement = ast.newIfStatement();
		visitor.visit(ifStatement);

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
