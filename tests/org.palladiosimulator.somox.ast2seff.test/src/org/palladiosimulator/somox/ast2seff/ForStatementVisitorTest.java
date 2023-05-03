package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PostfixExpression.Operator;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.net4j.util.collection.Pair;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

public class ForStatementVisitorTest extends VisitorTest {
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
        ForStatement forStatement = this.getAst().newForStatement();
        VariableDeclarationFragment variableDeclarationFragment = this.getAst().newVariableDeclarationFragment();
        variableDeclarationFragment.setName(this.getAst().newSimpleName("i"));
        variableDeclarationFragment.setInitializer(this.getAst().newNumberLiteral("0"));
        VariableDeclarationExpression variableDeclarationExpression = this.getAst()
                .newVariableDeclarationExpression(variableDeclarationFragment);
        forStatement.initializers().add(variableDeclarationExpression);
        InfixExpression infixExpression = this.getAst().newInfixExpression();
        infixExpression.setLeftOperand(this.getAst().newSimpleName("i"));
        infixExpression.setOperator(InfixExpression.Operator.toOperator("<"));
        infixExpression.setRightOperand(this.getAst().newNumberLiteral("10"));
        forStatement.setExpression(infixExpression);
        PostfixExpression postfixExpression = this.getAst().newPostfixExpression();
        postfixExpression.setOperand(this.getAst().newSimpleName("i"));
        postfixExpression.setOperator(Operator.toOperator("++"));
        forStatement.updaters().add(postfixExpression);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "forStatement", forStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getCreate().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getCreate());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

        assertEquals(3, actionList.size());
        assertTrue(actionList.get(1) instanceof LoopAction);
        assertEquals("@position:  from int i=0 to i < 10 with i++", actionList.get(1).getEntityName());

        LoopAction loopAction = (LoopAction) actionList.get(1);
        ResourceDemandingBehaviour resourceDemandingBehaviour = loopAction.getBodyBehaviour_Loop();

        assertEquals(2, resourceDemandingBehaviour.getSteps_Behaviour().size());
        assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
        assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof StopAction);
    }

    @Test
    public void singleStatementTest() {
        ForStatement forStatement = this.getAst().newForStatement();
        forStatement.initializers()
                .add(this.getAst().newVariableDeclarationExpression(this.getAst().newVariableDeclarationFragment()));
        forStatement.setExpression(this.getAst().newInfixExpression());
        forStatement.updaters().add(this.getAst().newPostfixExpression());
        Block block = this.getAst().newBlock();
        MethodInvocation methodInvocation = this.getAst().newMethodInvocation();
        methodInvocation.setName(this.getAst().newSimpleName("SimpleName"));
        methodInvocation.setExpression(this.getAst().newQualifiedName(this.getAst().newName("Name"),
                this.getAst().newSimpleName("Qualified")));
        block.statements().add(this.getAst().newExpressionStatement(methodInvocation));
        forStatement.setBody(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "forStatement", forStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getCreate().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getCreate());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
        ForStatement forStatement = this.getAst().newForStatement();
        forStatement.initializers()
                .add(this.getAst().newVariableDeclarationExpression(this.getAst().newVariableDeclarationFragment()));
        forStatement.setExpression(this.getAst().newInfixExpression());
        forStatement.updaters().add(this.getAst().newPostfixExpression());
        Block block = this.getAst().newBlock();
        ForStatement innerForStatement = this.getAst().newForStatement();
        innerForStatement.initializers()
                .add(this.getAst().newVariableDeclarationExpression(this.getAst().newVariableDeclarationFragment()));
        innerForStatement.setExpression(this.getAst().newInfixExpression());
        innerForStatement.updaters().add(this.getAst().newPostfixExpression());
        innerForStatement.setBody(this.getAst().newBlock());
        forStatement.setBody(innerForStatement);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "forStatement", forStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getCreate().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getCreate());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

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
        ForStatement forStatement = this.getAst().newForStatement();
        forStatement.initializers()
                .add(this.getAst().newVariableDeclarationExpression(this.getAst().newVariableDeclarationFragment()));
        forStatement.setExpression(this.getAst().newInfixExpression());
        forStatement.updaters().add(this.getAst().newPostfixExpression());
        Block block = this.getAst().newBlock();
        WhileStatement whileStatement = this.getAst().newWhileStatement();
        block.statements().add(whileStatement);
        forStatement.setBody(block);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "forStatement", forStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getCreate().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getCreate());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

        assertEquals(3, actionList.size());
        assertTrue(actionList.get(1) instanceof LoopAction);

        LoopAction loopAction = (LoopAction) actionList.get(1);
        ResourceDemandingBehaviour resourceDemandingBehaviour = loopAction.getBodyBehaviour_Loop();

        assertEquals(3, resourceDemandingBehaviour.getSteps_Behaviour().size());
        assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(0) instanceof StartAction);
        assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(1) instanceof LoopAction);
        assertTrue(resourceDemandingBehaviour.getSteps_Behaviour().get(2) instanceof StopAction);
    }

}
