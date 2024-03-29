package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.net4j.util.collection.Pair;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

public class IfStatementVisitorTest extends VisitorTest {
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
        IfStatement ifStatement = this.getAst().newIfStatement();

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "ifStatement", ifStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getFluentFactory());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
        IfStatement ifStatement = this.getAst().newIfStatement();
        IfStatement innerIfStatement = this.getAst().newIfStatement();
        innerIfStatement.setThenStatement(this.getAst().newBlock());
        innerIfStatement.setElseStatement(this.getAst().newBlock());
        ifStatement.setThenStatement(this.getAst().newBlock());
        ifStatement.setElseStatement(innerIfStatement);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "ifStatement", ifStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getFluentFactory());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

        assertEquals(3, actionList.size());
        assertTrue(actionList.get(1) instanceof BranchAction);

        BranchAction branchAction = (BranchAction) actionList.get(1);
        AbstractBranchTransition firstBranchTransition = branchAction.getBranches_Branch().get(0);
        AbstractBranchTransition secondBranchTransition = branchAction.getBranches_Branch().get(1);

        AbstractBranchTransition thirdBranchTransition = branchAction.getBranches_Branch().get(2);
        ResourceDemandingBehaviour firstResourceDemandingBehaviour = firstBranchTransition
                .getBranchBehaviour_BranchTransition();
        ResourceDemandingBehaviour secondResourceDemandingBehaviour = secondBranchTransition
                .getBranchBehaviour_BranchTransition();
        ResourceDemandingBehaviour thirdResourceDemandingBehaviour = thirdBranchTransition
                .getBranchBehaviour_BranchTransition();

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
        IfStatement ifStatement = this.getAst().newIfStatement();
        Block block = this.getAst().newBlock();
        MethodInvocation methodInvocation = this.getAst().newMethodInvocation();
        methodInvocation.setName(this.getAst().newSimpleName("SimpleName"));
        methodInvocation.setExpression(this.getAst().newQualifiedName(this.getAst().newName("Name"),
                this.getAst().newSimpleName("Qualified")));
        block.statements().add(this.getAst().newExpressionStatement(methodInvocation));
        ifStatement.setThenStatement(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "ifStatement", ifStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getFluentFactory());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
        IfStatement ifStatement = this.getAst().newIfStatement();
        Block block = this.getAst().newBlock();
        IfStatement innerIfStatement = this.getAst().newIfStatement();
        innerIfStatement.setThenStatement(this.getAst().newBlock());
        ifStatement.setThenStatement(innerIfStatement);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "ifStatement", ifStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getFluentFactory());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
        IfStatement ifStatement = this.getAst().newIfStatement();
        Block block = this.getAst().newBlock();
        WhileStatement whileStatement = this.getAst().newWhileStatement();
        block.statements().add(whileStatement);
        ifStatement.setThenStatement(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "ifStatement", ifStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getFluentFactory());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
