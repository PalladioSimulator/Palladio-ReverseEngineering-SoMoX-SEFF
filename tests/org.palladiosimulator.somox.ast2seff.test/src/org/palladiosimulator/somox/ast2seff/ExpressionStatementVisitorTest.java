package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.net4j.util.collection.Pair;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.RepoAddition;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationInterfaceCreator;
import org.palladiosimulator.generator.fluent.repository.structure.interfaces.OperationSignatureCreator;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.palladiosimulator.somox.ast2seff.jobs.Ast2SeffJob;
import org.palladiosimulator.somox.ast2seff.util.CaseStudyAstParser;
import org.palladiosimulator.somox.ast2seff.util.MethodDeclarationFinder;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;

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

    @Test
    public void externalActionTest() throws Exception {
        Path caseStudyPath = Path.of("src/org/palladiosimulator/somox/ast2seff/casestudy/external");
        CaseStudyAstParser caseStudyParser = new CaseStudyAstParser(caseStudyPath);
        Map<String, CompilationUnit> compilationUnits = caseStudyParser.parseCasestudyPackage();

        // For each comp. unit fill ast2seff map with correct elements
        Repository repository = RepositoryFactory.eINSTANCE.createRepository();
        Map<ASTNode, ServiceEffectSpecification> ast2seffMap = new HashMap<>();
        for (CompilationUnit compilationUnit : compilationUnits.values()) {
            List<MethodDeclaration> methodDeclarations = MethodDeclarationFinder.perform(compilationUnit);

            // Associate method declarations with class names (-> usually single class per comp. unit)
            Multimap<String, MethodDeclaration> classDeclarations = HashMultimap.create();
            for (MethodDeclaration methodDeclaration : methodDeclarations) {
                TypeDeclaration typeDeclaration = (TypeDeclaration) methodDeclaration.getParent();
                String className = typeDeclaration.getName().toString();
                classDeclarations.put(className, methodDeclaration);
            }

            // For each class create one component, one interface, associated signatures, & seffs
            for (String className : classDeclarations.keySet()) {
                // Create component
                BasicComponent component = RepositoryFactory.eINSTANCE.createBasicComponent();
                component.setEntityName(className);
                component.setRepository__RepositoryComponent(repository);

                // Create operation interface
                OperationInterface operationInterface = RepositoryFactory.eINSTANCE.createOperationInterface();
                operationInterface.setEntityName(className + "able");
                operationInterface.setRepository__Interface(repository);

                // Add interface to component via provided role
                OperationProvidedRole providedRole = RepositoryFactory.eINSTANCE.createOperationProvidedRole();
                providedRole.setProvidedInterface__OperationProvidedRole(operationInterface);
                component.getProvidedRoles_InterfaceProvidingEntity().add(providedRole);

                // Create signatures & Create seffs
                for (MethodDeclaration methodDeclaration : classDeclarations.get(className)) {
                    String methodName = methodDeclaration.getName().toString();
                    OperationSignature operationSignature = RepositoryFactory.eINSTANCE.createOperationSignature();
                    operationSignature.setEntityName(methodName);

                    // Add signature to interface
                    operationInterface.getSignatures__OperationInterface().add(operationSignature);

                    // Create seff for signature & add to component
                    ResourceDemandingSEFF seff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
                    seff.setBasicComponent_ServiceEffectSpecification(component);
                    seff.setDescribedService__SEFF(operationSignature);

                    // Add AST seff association to map
                    ast2seffMap.put(methodDeclaration, seff);
                }
            }
        }

        // Initialize ast2seff job
        Blackboard<Object> blackboard = new Blackboard<>();
        String ast2SeffMapKey = "org.palladiosimulator.somox.analyzer.seff_associations";
        String repositoryOutputKey = "repository";
        Ast2SeffJob ast2SeffJob = new Ast2SeffJob(blackboard, ast2SeffMapKey, repositoryOutputKey);
        blackboard.addPartition(ast2SeffMapKey, ast2seffMap);

        // Execute ast2seff job
        NullProgressMonitor progressMonitor = new NullProgressMonitor();
        ast2SeffJob.execute(progressMonitor);

        // Retrieve repository from blackboard & perform assertions regarding repository structure
        Repository outputRepository = (Repository) blackboard.getPartition(repositoryOutputKey);
        assertNotNull(outputRepository);
        assertEquals(2, outputRepository.getComponents__Repository().size());
        assertEquals(2, outputRepository.getInterfaces__Repository().size());

        // Retrieve created seffs
        List<ResourceDemandingSEFF> seffs = outputRepository.getComponents__Repository().stream()
                .map(component -> (BasicComponent) component)
                .flatMap(component -> component.getServiceEffectSpecifications__BasicComponent().stream())
                .map(seff -> (ResourceDemandingSEFF) seff)
                .toList();
        assertEquals(2, seffs.size());

        // Check provide seff
        ResourceDemandingSEFF provideSeff = seffs.stream()
                .filter(seff -> seff.getDescribedService__SEFF().getEntityName().equals("provide"))
                .findFirst().orElseThrow();
        EList<AbstractAction> provideSteps = provideSeff.getSteps_Behaviour();
        assertEquals(2, provideSteps.size());
        assertTrue(provideSteps.get(0) instanceof StartAction);
        assertTrue(provideSteps.get(1) instanceof StopAction);

        // Check consume seff
        ResourceDemandingSEFF consumeSeff = seffs.stream()
                .filter(seff -> seff.getDescribedService__SEFF().getEntityName().equals("consume"))
                .findFirst().orElseThrow();
        EList<AbstractAction> consumeSteps = consumeSeff.getSteps_Behaviour();
        assertTrue(consumeSteps.size() >= 3);
        assertTrue(consumeSteps.get(0) instanceof StartAction);
        assertTrue(consumeSteps.get(consumeSteps.size() - 1) instanceof StopAction);

        // Check external call action of consume seff
        List<ExternalCallAction> externalCallActions = consumeSteps.stream()
                .filter(action -> action instanceof ExternalCallAction)
                .map(action -> (ExternalCallAction) action)
                .toList();
        assertEquals(1, externalCallActions.size());
        ExternalCallAction externalCallAction = externalCallActions.get(0);
        assertEquals(1, externalCallActions.size());
        assertEquals(provideSeff.getDescribedService__SEFF().getEntityName(),
                externalCallAction.getCalledService_ExternalService().getEntityName());
        assertEquals(1, consumeSeff.getBasicComponent_ServiceEffectSpecification()
                .getRequiredRoles_InterfaceRequiringEntity().size());
        OperationRequiredRole requiredRole = (OperationRequiredRole) consumeSeff
                .getBasicComponent_ServiceEffectSpecification()
                .getRequiredRoles_InterfaceRequiringEntity().get(0);
        assertEquals(requiredRole.getId(), externalCallAction.getRole_ExternalService().getId());
    }
}
