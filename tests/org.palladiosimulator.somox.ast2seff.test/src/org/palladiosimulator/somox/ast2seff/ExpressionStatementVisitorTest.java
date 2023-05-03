package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.net4j.util.collection.Pair;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.RepoAddition;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationInterfaceCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationSignatureCreator;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.somox.ast2seff.models.ComponentInformation;
import org.palladiosimulator.somox.ast2seff.models.MethodBundlePair;
import org.palladiosimulator.somox.ast2seff.models.MethodPalladioInformation;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

public class ExpressionStatementVisitorTest extends VisitorTest {
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
        MethodInvocation methodInvocation = this.getAst().newMethodInvocation();
        methodInvocation.setName(this.getAst().newSimpleName("SimpleName"));
        methodInvocation.setExpression(this.getAst().newQualifiedName(this.getAst().newName("Name"),
                this.getAst().newSimpleName("Qualified")));
        ExpressionStatement expressionStatement = this.getAst().newExpressionStatement(methodInvocation);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "Simple Component", "Interface", "expressionStatement", expressionStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getFluentFactory());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

        assertEquals(3, actionList.size());
        assertTrue(actionList.get(1) instanceof InternalAction);
    }

    @Test
    public void methodInliningTest() {
        RepoAddition repoAddition = this.getFluentFactory().newRepository().withName("SimpleRepository");
        MethodInvocation methodInvocation = this.getAst().newMethodInvocation();
        methodInvocation.setName(this.getAst().newSimpleName("SimpleName"));
        methodInvocation.setExpression(this.getAst().newQualifiedName(this.getAst().newName("Name"),
                this.getAst().newSimpleName("Qualified")));
        ExpressionStatement expressionStatement = this.getAst().newExpressionStatement(methodInvocation);

        // TODO Evaluate if following statements are needed for test and document
        OperationSignatureCreator methodOperationSignature = this.getFluentFactory().newOperationSignature()
                .withName("expressionStatement");
        OperationInterfaceCreator bundleOperationInterfaceCreator = this.getFluentFactory().newOperationInterface()
                .withName("ISimpleComponent");
        bundleOperationInterfaceCreator.withOperationSignature(methodOperationSignature);
        repoAddition.addToRepository(bundleOperationInterfaceCreator);

        // Get method declaration with created statement in body & empty seff for palladio information extraction
        Pair<ASTNode, ServiceEffectSpecification> astSeffPair = createMethodDeclarationWrappingWithEmptySeff(
                "SimpleComponent", "ISimpleComponent", "expressionStatement", expressionStatement);
        Map<ASTNode, ServiceEffectSpecification> nodes = new HashMap<>();
        nodes.put(astSeffPair.getElement1(), astSeffPair.getElement2());

        // Perform ast2seff conversion via visitor
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        actionSeff = Ast2SeffVisitor.perform(actionSeff, astSeffPair.getElement1(), nodes, this.getFluentFactory());
        ResourceDemandingSEFF completeSeff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = completeSeff.getSteps_Behaviour();

        assertEquals(3, actionList.size());
        assertTrue(actionList.get(1) instanceof InternalAction);
    }

    @Disabled
    @Test
    public void externalCallActionTest() {
        RepoAddition repoAddition = this.getFluentFactory().newRepository().withName("Simple Repository");
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        Map<String, MethodPalladioInformation> methodPalladioInfoMap = new HashMap<>();
        BasicComponentCreator basicComponentCreator = this.getFluentFactory().newBasicComponent();
        ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);

        AST ast = AST.newAST(AST.getJLSLatest(), false);
        MethodInvocation methodInvocation = ast.newMethodInvocation();
        methodInvocation.setName(ast.newSimpleName("SimpleName"));
        methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
        ExpressionStatement expressionStatement = ast.newExpressionStatement(methodInvocation);
        MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", expressionStatement);
        MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("expressionStatement",
                "expressionStatement", "Simple Component", methodBundlePair);
        MethodPalladioInformation methodPalladioInformationTwo = new MethodPalladioInformation("expressionStatementTwo",
                "expressionStatementTwo", "Simple Component Two", methodBundlePair);
        OperationSignatureCreator methodOperationSignature = this.getFluentFactory().newOperationSignature()
                .withName("expressionStatementTwo");
        OperationInterfaceCreator bundleOperationInterfaceCreator = this.getFluentFactory().newOperationInterface()
                .withName("Simple Component Two");
        bundleOperationInterfaceCreator.withOperationSignature(methodOperationSignature);
        repoAddition.addToRepository(bundleOperationInterfaceCreator);

        methodPalladioInfoMap.put("unknown.SimpleName", methodPalladioInformationTwo);

        // TODO Fix test like other visitor tests and fix isExternal in visitor
        // actionSeff = Ast2SeffVisitor.perform(methodBundlePair, actionSeff, methodPalladioInfoMap,
        // componentInformation, create);

        ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = seff.getSteps_Behaviour();

        assertEquals(3, actionList.size());
        assertTrue(actionList.get(1) instanceof ExternalCallAction);
    }

    @Disabled
    @Test
    public void externalCallActionWithTwoInterfacesTest() {
        RepoAddition repoAddition = this.getFluentFactory().newRepository().withName("Simple Repository");
        ActionSeff actionSeff = this.getFluentFactory().newSeff().withSeffBehaviour().withStartAction().followedBy();
        Map<String, MethodPalladioInformation> methodPalladioInfoMap = new HashMap<>();
        BasicComponentCreator basicComponentCreator = this.getFluentFactory().newBasicComponent();
        ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);

        AST ast = AST.newAST(AST.getJLSLatest(), false);

        MethodInvocation methodInvocation = ast.newMethodInvocation();
        methodInvocation.setName(ast.newSimpleName("SimpleName"));
        methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
        ExpressionStatement expressionStatement = ast.newExpressionStatement(methodInvocation);

        MethodInvocation methodInvocationTwo = ast.newMethodInvocation();
        methodInvocationTwo.setName(ast.newSimpleName("SimpleNameTwo"));
        methodInvocationTwo.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
        ExpressionStatement expressionStatementTwo = ast.newExpressionStatement(methodInvocationTwo);

        Block block = ast.newBlock();
        block.statements().add(expressionStatement);
        block.statements().add(expressionStatementTwo);

        MethodBundlePair methodBundlePair = new MethodBundlePair("SimpleComponent", block);
        MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("expressionStatement",
                "expressionStatement", "SimpleComponent", methodBundlePair);
        MethodPalladioInformation methodPalladioInformationTwo = new MethodPalladioInformation("expressionStatementTwo",
                "expressionStatementTwo", "ISimpleComponentTwo", methodBundlePair);
        OperationSignatureCreator methodOperationSignature = this.getFluentFactory().newOperationSignature()
                .withName("expressionStatementTwo");
        OperationInterfaceCreator bundleOperationInterfaceCreator = this.getFluentFactory().newOperationInterface()
                .withName("ISimpleComponentTwo");
        bundleOperationInterfaceCreator.withOperationSignature(methodOperationSignature);

        MethodBundlePair methodBundlePairTwo = new MethodBundlePair("SimpleComponentThree", expressionStatementTwo);
        MethodPalladioInformation methodPalladioInformationThree = new MethodPalladioInformation(
                "expressionStatementThree", "expressionStatementThree", "ISimpleComponentThree", methodBundlePairTwo);
        OperationSignatureCreator methodOperationSignatureTwo = this.getFluentFactory().newOperationSignature()
                .withName("expressionStatementThree");
        OperationInterfaceCreator bundleOperationInterfaceCreatorTwo = this.getFluentFactory().newOperationInterface()
                .withName("ISimpleComponentThree");
        bundleOperationInterfaceCreatorTwo.withOperationSignature(methodOperationSignatureTwo);

        repoAddition.addToRepository(bundleOperationInterfaceCreator);
        repoAddition.addToRepository(bundleOperationInterfaceCreatorTwo);

        methodPalladioInfoMap.put("unknown.SimpleName", methodPalladioInformationTwo);
        methodPalladioInfoMap.put("unknown.SimpleNameTwo", methodPalladioInformationThree);

        // TODO Fix test like other visitor tests and fix isExternal in visitor
        // actionSeff = Ast2SeffVisitor.perform(methodBundlePair, actionSeff, methodPalladioInfoMap,
        // componentInformation, create);

        ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = seff.getSteps_Behaviour();

        assertEquals(4, actionList.size());
        assertTrue(actionList.get(1) instanceof ExternalCallAction);
        assertTrue(actionList.get(2) instanceof ExternalCallAction);
    }

}
