package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TryStatement;
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

public class TryStatementVisitorTest extends VisitorTest {
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
        TryStatement tryStatement = ast.newTryStatement();

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "tryStatement", tryStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, create);
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

        assertEquals(actionList.size(), 3);
        assertTrue(actionList.get(1) instanceof BranchAction);
        assertEquals("Try Catch Branch", actionList.get(1).getEntityName());

        BranchAction branchAction = (BranchAction) actionList.get(1);
        AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(0);
        ResourceDemandingBehaviour resourceDemandingBehaviour = branchTransition.getBranchBehaviour_BranchTransition();

        assertEquals(branchAction.getBranches_Branch().size(), 1);
        assertEquals(resourceDemandingBehaviour.getSteps_Behaviour().size(), 2);
        assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
        assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction);
    }

    @Test
    public void tryWithTwoCatchStatements() {
        TryStatement tryStatement = ast.newTryStatement();
        CatchClause catchClauseOne = ast.newCatchClause();
        CatchClause catchClauseTwo = ast.newCatchClause();
        tryStatement.catchClauses().add(catchClauseOne);
        tryStatement.catchClauses().add(catchClauseTwo);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "tryStatement", tryStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, create);
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

        assertEquals(actionList.size(), 3);
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

        assertEquals(branchAction.getBranches_Branch().size(), 3);
        assertEquals(firstResourceDemandingBehaviour.getSteps_Behaviour().size(), 2);
        assertTrue(firstResourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
        assertTrue(firstResourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction);
        assertEquals(secondResourceDemandingBehaviour.getSteps_Behaviour().size(), 2);
        assertTrue(secondResourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
        assertTrue(secondResourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction);
        assertEquals(thirdResourceDemandingBehaviour.getSteps_Behaviour().size(), 2);
        assertTrue(thirdResourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
        assertTrue(thirdResourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction);
    }

    @Test
    public void singleStatementTest() {
        TryStatement tryStatement = ast.newTryStatement();
        Block block = ast.newBlock();
        MethodInvocation methodInvocation = ast.newMethodInvocation();
        methodInvocation.setName(ast.newSimpleName("SimpleName"));
        methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
        block.statements().add(ast.newExpressionStatement(methodInvocation));
        tryStatement.setBody(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "tryStatement", tryStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, create);
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
    public void statementInsideSameStatementTest() {
        TryStatement tryStatement = ast.newTryStatement();
        Block block = ast.newBlock();
        TryStatement innerTryStatement = ast.newTryStatement();
        innerTryStatement.setBody(ast.newBlock());
        block.statements().add(innerTryStatement);
        tryStatement.setBody(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "tryStatement", tryStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, create);
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
    public void statementInsideOtherStatementTest() {
        TryStatement tryStatement = ast.newTryStatement();
        Block block = ast.newBlock();
        WhileStatement whileStatement = ast.newWhileStatement();
        block.statements().add(whileStatement);
        tryStatement.setBody(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "tryStatement", tryStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, create);
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
        assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof LoopAction);
        assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof StopAction);
    }

}
