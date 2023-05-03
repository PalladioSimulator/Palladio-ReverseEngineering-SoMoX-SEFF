package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.net4j.util.collection.Pair;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

public class SwitchStatementVisitorTest extends VisitorTest {
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
        SwitchStatement switchStatement = this.getAst().newSwitchStatement();

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "switchStatement", switchStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getCreate().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getCreate());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

        assertEquals(actionList.size(), 3);
        assertTrue(actionList.get(1) instanceof BranchAction);
    }

    @Test
    public void singleCaseSwitchStatementWithIfStatementTest() {
        SwitchStatement switchStatement = this.getAst().newSwitchStatement();
        SwitchCase switchCase = this.getAst().newSwitchCase();
        IfStatement ifStatement = this.getAst().newIfStatement();
        Block block = this.getAst().newBlock();
        block.statements().add(ifStatement);
        switchStatement.statements().add(switchCase);
        switchStatement.statements().add(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "switchStatement", switchStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getCreate().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getCreate());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
        SwitchStatement switchStatement = this.getAst().newSwitchStatement();
        SwitchCase switchCase = this.getAst().newSwitchCase();
        Block block = this.getAst().newBlock();
        MethodInvocation methodInvocation = this.getAst().newMethodInvocation();
        methodInvocation.setName(this.getAst().newSimpleName("SimpleName"));
        methodInvocation.setExpression(this.getAst().newQualifiedName(this.getAst().newName("Name"),
                this.getAst().newSimpleName("Qualified")));
        block.statements().add(this.getAst().newExpressionStatement(methodInvocation));
        switchStatement.statements().add(switchCase);
        switchStatement.statements().add(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "switchStatement", switchStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getCreate().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getCreate());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
        SwitchStatement switchStatement = this.getAst().newSwitchStatement();
        SwitchCase switchCase = this.getAst().newSwitchCase();
        Block block = this.getAst().newBlock();
        SwitchStatement innerSwitchStatement = this.getAst().newSwitchStatement();
        SwitchCase innerSwitchCase = this.getAst().newSwitchCase();
        MethodInvocation methodInvocation = this.getAst().newMethodInvocation();
        methodInvocation.setName(this.getAst().newSimpleName("SimpleName"));
        methodInvocation.setExpression(this.getAst().newQualifiedName(this.getAst().newName("Name"),
                this.getAst().newSimpleName("Qualified")));
        block.statements().add(innerSwitchStatement);
        block.statements().add(innerSwitchCase);
        switchStatement.statements().add(switchCase);
        switchStatement.statements().add(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "switchStatement", switchStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getCreate().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getCreate());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
        SwitchStatement switchStatement = this.getAst().newSwitchStatement();
        SwitchCase switchCaseOne = this.getAst().newSwitchCase();
        SwitchCase switchCaseTwo = this.getAst().newSwitchCase();
        BreakStatement breakStatementOne = this.getAst().newBreakStatement();
        BreakStatement breakStatementTwo = this.getAst().newBreakStatement();
        Block blockOne = this.getAst().newBlock();
        Block blockTwo = this.getAst().newBlock();
        MethodInvocation methodInvocationOne = this.getAst().newMethodInvocation();
        MethodInvocation methodInvocationTwo = this.getAst().newMethodInvocation();
        methodInvocationOne.setName(this.getAst().newSimpleName("SimpleName"));
        methodInvocationOne.setExpression(this.getAst().newQualifiedName(this.getAst().newName("Name"),
                this.getAst().newSimpleName("Qualified")));
        methodInvocationTwo.setName(this.getAst().newSimpleName("SimpleName"));
        methodInvocationTwo.setExpression(this.getAst().newQualifiedName(this.getAst().newName("Name"),
                this.getAst().newSimpleName("Qualified")));
        blockOne.statements().add(this.getAst().newExpressionStatement(methodInvocationOne));
        blockTwo.statements().add(this.getAst().newExpressionStatement(methodInvocationTwo));
        switchStatement.statements().add(switchCaseOne);
        switchStatement.statements().add(blockOne);
        switchStatement.statements().add(breakStatementOne);
        switchStatement.statements().add(switchCaseTwo);
        switchStatement.statements().add(blockTwo);
        switchStatement.statements().add(breakStatementTwo);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "switchStatement", switchStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getCreate().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getCreate());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
        SwitchStatement switchStatement = this.getAst().newSwitchStatement();
        SwitchCase switchCaseOne = this.getAst().newSwitchCase();
        SwitchCase switchCaseTwo = this.getAst().newSwitchCase();
        BreakStatement breakStatementOne = this.getAst().newBreakStatement();
        Block blockOne = this.getAst().newBlock();
        Block blockTwo = this.getAst().newBlock();
        MethodInvocation methodInvocationOne = this.getAst().newMethodInvocation();
        MethodInvocation methodInvocationTwo = this.getAst().newMethodInvocation();
        methodInvocationOne.setName(this.getAst().newSimpleName("SimpleName"));
        methodInvocationOne.setExpression(this.getAst().newQualifiedName(this.getAst().newName("Name"),
                this.getAst().newSimpleName("Qualified")));
        methodInvocationTwo.setName(this.getAst().newSimpleName("SimpleName"));
        methodInvocationTwo.setExpression(this.getAst().newQualifiedName(this.getAst().newName("Name"),
                this.getAst().newSimpleName("Qualified")));
        blockOne.statements().add(this.getAst().newExpressionStatement(methodInvocationOne));
        blockTwo.statements().add(this.getAst().newExpressionStatement(methodInvocationTwo));
        switchStatement.statements().add(switchCaseOne);
        switchStatement.statements().add(blockOne);
        switchStatement.statements().add(switchCaseTwo);
        switchStatement.statements().add(blockTwo);
        switchStatement.statements().add(breakStatementOne);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "switchStatement", switchStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getCreate().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getCreate());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
