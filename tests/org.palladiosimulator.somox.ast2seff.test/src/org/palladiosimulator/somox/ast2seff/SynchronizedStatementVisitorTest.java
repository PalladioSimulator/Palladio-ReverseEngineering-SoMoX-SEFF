package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.net4j.util.collection.Pair;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AcquireAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ReleaseAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

public class SynchronizedStatementVisitorTest extends VisitorTest {
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
        SynchronizedStatement synchronizedStatement = this.getAst().newSynchronizedStatement();

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "synchronizedStatement", synchronizedStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getFluentFactory());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

        assertEquals(actionList.size(), 4);
        assertTrue(actionList.get(0) instanceof StartAction);
        assertTrue(actionList.get(1) instanceof AcquireAction);
        assertTrue(actionList.get(2) instanceof ReleaseAction);
        assertTrue(actionList.get(3) instanceof StopAction);

    }

    @Test
    public void singleStatementTest() {
        SynchronizedStatement synchronizedStatement = this.getAst().newSynchronizedStatement();
        Block block = this.getAst().newBlock();
        MethodInvocation methodInvocation = this.getAst().newMethodInvocation();
        methodInvocation.setName(this.getAst().newSimpleName("SimpleName"));
        methodInvocation.setExpression(this.getAst().newQualifiedName(this.getAst().newName("Name"),
                this.getAst().newSimpleName("Qualified")));
        block.statements().add(this.getAst().newExpressionStatement(methodInvocation));
        synchronizedStatement.setBody(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "synchronizedStatement", synchronizedStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getFluentFactory());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

        assertEquals(actionList.size(), 5);
        assertTrue(actionList.get(0) instanceof StartAction);
        assertTrue(actionList.get(1) instanceof AcquireAction);
        assertTrue(actionList.get(2) instanceof InternalAction);
        assertTrue(actionList.get(3) instanceof ReleaseAction);
        assertTrue(actionList.get(4) instanceof StopAction);

    }

    @Test
    public void statementInsideSameStatementTest() {
        SynchronizedStatement synchronizedStatement = this.getAst().newSynchronizedStatement();
        Block block = this.getAst().newBlock();
        block.statements().add(this.getAst().newSynchronizedStatement());
        synchronizedStatement.setBody(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "synchronizedStatement", synchronizedStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getFluentFactory());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
        SynchronizedStatement synchronizedStatement = this.getAst().newSynchronizedStatement();
        Block block = this.getAst().newBlock();
        WhileStatement whileStatement = this.getAst().newWhileStatement();
        block.statements().add(whileStatement);
        synchronizedStatement.setBody(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "synchronizedStatement", synchronizedStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getFluentFactory());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
