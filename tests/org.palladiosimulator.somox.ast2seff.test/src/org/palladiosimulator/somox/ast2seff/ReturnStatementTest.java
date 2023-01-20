package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SetVariableAction;
import org.palladiosimulator.somox.ast2seff.models.ComponentInformation;
import org.palladiosimulator.somox.ast2seff.models.MethodBundlePair;
import org.palladiosimulator.somox.ast2seff.models.MethodPalladioInformation;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

public class ReturnStatementTest {
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

        BasicComponentCreator basicComponentCreator = create.newBasicComponent();
        AST ast = AST.newAST(AST.getJLSLatest(), false);
        ReturnStatement returnStatement = ast.newReturnStatement();
        MethodInvocation methodInvocation = ast.newMethodInvocation();
        methodInvocation.setName(ast.newSimpleName("ReturnExpression"));
        methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
        returnStatement.setExpression(methodInvocation);
        MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", returnStatement);
        MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("returnStatement",
                "returnStatement", "Interface", methodBundlePair);
        ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
        actionSeff = Ast2SeffVisitor.perform(methodBundlePair, actionSeff, methodNameMap, componentInformation, create);

        ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = seff.getSteps_Behaviour();

        assertEquals(actionList.size(), 3);
        assertTrue(actionList.get(1) instanceof SetVariableAction);

        SetVariableAction setVariableAction = (SetVariableAction) actionList.get(1);
        assertEquals("Name.Qualified.ReturnExpression()", setVariableAction.getEntityName());
    }

    @Test
    @Disabled
    public void booleanReturnStatementTest() {

        ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
        Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();

        BasicComponentCreator basicComponentCreator = create.newBasicComponent();
        AST ast = AST.newAST(AST.getJLSLatest(), false);
        ReturnStatement returnStatement = ast.newReturnStatement();
        MethodInvocation methodInvocation = ast.newMethodInvocation();
        methodInvocation.setName(ast.newSimpleName("ReturnExpression"));
        methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
        returnStatement.setExpression(methodInvocation);
        MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", returnStatement);
        MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("returnStatement",
                "returnStatement", "Interface", methodBundlePair);
        ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
        actionSeff = Ast2SeffVisitor.perform(methodBundlePair, actionSeff, methodNameMap, componentInformation, create);

        ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = seff.getSteps_Behaviour();

        assertEquals(actionList.size(), 3);
        assertTrue(actionList.get(1) instanceof SetVariableAction);

        SetVariableAction setVariableAction = (SetVariableAction) actionList.get(1);

    }

    @Test
    @Disabled
    public void charReturnStatementTest() {

        ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
        Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();

        BasicComponentCreator basicComponentCreator = create.newBasicComponent();
        AST ast = AST.newAST(AST.getJLSLatest(), false);
        ReturnStatement returnStatement = ast.newReturnStatement();
        MethodInvocation methodInvocation = ast.newMethodInvocation();
        methodInvocation.setName(ast.newSimpleName("ReturnExpression"));
        methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
        returnStatement.setExpression(methodInvocation);
        MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", returnStatement);
        MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("returnStatement",
                "returnStatement", "Interface", methodBundlePair);
        ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
        actionSeff = Ast2SeffVisitor.perform(methodBundlePair, actionSeff, methodNameMap, componentInformation, create);

        ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = seff.getSteps_Behaviour();

        assertEquals(actionList.size(), 3);
        assertTrue(actionList.get(1) instanceof SetVariableAction);

        SetVariableAction setVariableAction = (SetVariableAction) actionList.get(1);

        // assertEquals(setVariableAction.getEntityName(), "ReturnExpression");
    }

    @Test
    @Disabled
    public void stringReturnStatementTest() {

        ActionSeff actionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
        Map<String, MethodPalladioInformation> methodNameMap = new HashMap<>();

        BasicComponentCreator basicComponentCreator = create.newBasicComponent();
        AST ast = AST.newAST(AST.getJLSLatest(), false);
        ReturnStatement returnStatement = ast.newReturnStatement();
        MethodInvocation methodInvocation = ast.newMethodInvocation();
        methodInvocation.setName(ast.newSimpleName("ReturnExpression"));
        methodInvocation.setExpression(ast.newQualifiedName(ast.newName("Name"), ast.newSimpleName("Qualified")));
        returnStatement.setExpression(methodInvocation);
        MethodBundlePair methodBundlePair = new MethodBundlePair("Simple Component", returnStatement);
        MethodPalladioInformation methodPalladioInformation = new MethodPalladioInformation("returnStatement",
                "returnStatement", "Interface", methodBundlePair);
        ComponentInformation componentInformation = new ComponentInformation(basicComponentCreator);
        actionSeff = Ast2SeffVisitor.perform(methodBundlePair, actionSeff, methodNameMap, componentInformation, create);

        ResourceDemandingSEFF seff = actionSeff.stopAction().createBehaviourNow().buildRDSeff();
        EList<AbstractAction> actionList = seff.getSteps_Behaviour();

        assertEquals(actionList.size(), 3);
        assertTrue(actionList.get(1) instanceof SetVariableAction);

        SetVariableAction setVariableAction = (SetVariableAction) actionList.get(1);

        // assertEquals(setVariableAction.getEntityName(), "ReturnExpression");
    }

}
