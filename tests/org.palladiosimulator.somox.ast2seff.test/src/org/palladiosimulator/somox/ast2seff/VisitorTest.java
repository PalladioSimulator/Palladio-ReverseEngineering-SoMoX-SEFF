package org.palladiosimulator.somox.ast2seff;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.net4j.util.collection.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationSignatureCreator;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

public class VisitorTest {
    private AST ast;
    private FluentRepositoryFactory fluentFactory;

    @BeforeEach
    public void initializeTestClass() {
        this.fluentFactory = new FluentRepositoryFactory();
        fluentFactory.newRepository();
        this.ast = AST.newAST(AST.getJLSLatest(), false);
    }

    protected Pair<ASTNode, ServiceEffectSpecification> createMethodDeclarationWrappingWithEmptySeff(
            String componentName, String interfaceName, String methodName, Statement statement) {
        // Create an empty ServiceEffectSpecification
        OperationSignatureCreator operationSignatureCreator = fluentFactory.newOperationSignature()
                .withName(methodName);
        OperationInterface operationInterface = fluentFactory.newOperationInterface()
                .withName(interfaceName)
                .withOperationSignature(operationSignatureCreator)
                .build();
        BasicComponent basicComponent = fluentFactory.newBasicComponent()
                .withName(componentName)
                .provides(operationInterface)
                .build();
        ServiceEffectSpecification seff = fluentFactory.newSeff().buildRDSeff();
        seff.setBasicComponent_ServiceEffectSpecification(basicComponent);
        seff.setDescribedService__SEFF(operationInterface.getSignatures__OperationInterface().get(0));

        // Create a method declaration with given statement in body
        Block methodBlock = ast.newBlock();
        methodBlock.statements().add(statement);
        MethodDeclaration methodDeclaration = ast.newMethodDeclaration();
        methodDeclaration.setName(ast.newSimpleName(methodName));
        methodDeclaration.setBody(methodBlock);

        // Create & return pair for node seff association
        Pair<ASTNode, ServiceEffectSpecification> pair = new Pair<>(methodDeclaration, seff);
        return pair;
    }

    public AST getAst() {
        return this.ast;
    }

    public FluentRepositoryFactory getFluentFactory() {
        return this.fluentFactory;
    }
}
