package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.generator.fluent.repository.api.RepoAddition;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.somox.ast2seff.jobs.Ast2SeffJob;
import org.palladiosimulator.somox.ast2seff.util.MethodDeclarationFinder;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

public class Ast2SeffTest {
    private String[] getEntries(Path dir, String suffix) {
        try (Stream<Path> paths = Files.walk(dir)) {
            return paths
                    .filter(path -> Files.isRegularFile(path)
                            && path.getFileName().toString().toLowerCase().endsWith(suffix))
                    .map(Path::toAbsolutePath).map(Path::normalize).map(Path::toString).toArray(i -> new String[i]);
        } catch (final IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private ASTParser getJavaParser() {
        String javaCoreVersion = JavaCore.latestSupportedJavaVersion();
        final ASTParser parser = ASTParser.newParser(AST.getJLSLatest());
        parser.setResolveBindings(true);
        parser.setBindingsRecovery(true);
        parser.setStatementsRecovery(true);
        parser.setCompilerOptions(Map.of(JavaCore.COMPILER_SOURCE, javaCoreVersion, JavaCore.COMPILER_COMPLIANCE,
                javaCoreVersion, JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, javaCoreVersion));
        return parser;
    }

    private Map<String, CompilationUnit> parseDirectory(Path dir) {
        ASTParser parser = getJavaParser();
        String[] classpathEntries = getEntries(dir, ".jar");
        final String[] sources = getEntries(dir, ".java");
        final String[] encodings = new String[sources.length];
        Arrays.fill(encodings, StandardCharsets.UTF_8.toString());
        final Map<String, CompilationUnit> compilationUnits = new HashMap<>();
        try {
            parser.setEnvironment(classpathEntries, new String[0], new String[0], true);
            parser.createASTs(sources, encodings, new String[0], new FileASTRequestor() {
                @Override
                public void acceptAST(final String sourceFilePath, final CompilationUnit ast) {
                    compilationUnits.put(sourceFilePath, ast);
                }
            }, new NullProgressMonitor());
        } catch (IllegalArgumentException | IllegalStateException e) {
            e.printStackTrace();
        }
        return compilationUnits;
    }

    @Test
    public void testAllClassesInDirectory() throws JobFailedException, UserCanceledException, IOException {

        Path directoryPath = Path.of("src/org/palladiosimulator/somox/ast2seff/res");
        Map<String, CompilationUnit> compUnitMap = parseDirectory(directoryPath);
        Map<ASTNode, ServiceEffectSpecification> ast2seffMap = new HashMap<>();

        for (var entry : compUnitMap.entrySet()) {
            List<MethodDeclaration> methodDeclarations = MethodDeclarationFinder.perform(entry.getValue());
            Map<String, BasicComponent> createdNamedComponents = new HashMap<>();
            Map<String, OperationInterface> createdInterfacesOfNamedComponents = new HashMap<>();

            for (MethodDeclaration methodDeclaration : methodDeclarations) {
                List<IExtendedModifier> modifierList = methodDeclaration.modifiers();

                // Check whether method declaration belongs to public method
                IExtendedModifier firstModifier = modifierList.get(0);
                if (firstModifier.isModifier()) {
                    Modifier modifier = (Modifier) firstModifier;
                    if (modifier.isPublic()) {
                        TypeDeclaration typeDeclaration = (TypeDeclaration) methodDeclaration.getParent();
                        String className = typeDeclaration.getName().toString();
                        String methodName = methodDeclaration.getName().toString();

                        // Create fluent factory & fluent repository
                        FluentRepositoryFactory fluentFactory = new FluentRepositoryFactory();
                        RepoAddition fluentRepository = fluentFactory.newRepository();

                        // Create operation signature for method declaration
                        OperationSignature operationSignature = RepositoryFactory.eINSTANCE.createOperationSignature();
                        operationSignature.setEntityName(methodName);

                        // Fetch component and interface if already created or create new if needed
                        BasicComponent basicComponent = createdNamedComponents.get(className);
                        OperationInterface operationInterface = createdInterfacesOfNamedComponents.get(className);
                        if (Objects.isNull(basicComponent)) {
                            operationInterface = fluentFactory.newOperationInterface()
                                    .withName("I" + className)
                                    .build();
                            basicComponent = fluentFactory.newBasicComponent()
                                    .withName(className)
                                    .provides(operationInterface)
                                    .build();
                            createdNamedComponents.put(className, basicComponent);
                            createdInterfacesOfNamedComponents.put(className, operationInterface);
                        }
                        operationInterface.getSignatures__OperationInterface().add(operationSignature);

                        // Create empty placeholder service effect specification for chosen method declarations
                        ServiceEffectSpecification seff = fluentFactory.newSeff().buildRDSeff();
                        seff.setBasicComponent_ServiceEffectSpecification(basicComponent);
                        seff.setDescribedService__SEFF(operationSignature);

                        // Add method declaration and ast node to map
                        ast2seffMap.put(methodDeclaration, seff);
                    }
                }
            }
        }

        Blackboard<Object> blackboard = new Blackboard<>();
        String repositoryOutputKey = "repository";
        Ast2SeffJob ast2SeffJob = new Ast2SeffJob(blackboard, repositoryOutputKey);
        blackboard.addPartition("org.palladiosimulator.somox.analyzer.seff_associations", ast2seffMap);

        NullProgressMonitor progressMonitor = new NullProgressMonitor();
        ast2SeffJob.execute(progressMonitor);

        Repository repository = (Repository) blackboard.getPartition(repositoryOutputKey);

        assertNotNull(repository);

        assertEquals(2, repository.getComponents__Repository().size());
        assertEquals(2, repository.getInterfaces__Repository().size());

        BasicComponent basicComponentOne = (BasicComponent) repository.getComponents__Repository().stream()
                .filter(component -> component.getEntityName().equals("SimpleExternalClass"))
                .findFirst().orElseThrow();
        BasicComponent basicComponentTwo = (BasicComponent) repository.getComponents__Repository().stream()
                .filter(component -> component.getEntityName().equals("SimpleClass"))
                .findFirst().orElseThrow();
        OperationInterface interfaceOne = (OperationInterface) repository.getInterfaces__Repository().stream()
                .filter(interFace -> interFace.getEntityName().equals("ISimpleExternalClass"))
                .findFirst().orElseThrow();
        OperationInterface interfaceTwo = (OperationInterface) repository.getInterfaces__Repository().stream()
                .filter(interFace -> interFace.getEntityName().equals("ISimpleClass"))
                .findFirst().orElseThrow();

        // TODO Add tests for correctness of relations between components, interfaces, & signatures
        assertEquals(4, basicComponentOne.getServiceEffectSpecifications__BasicComponent().size());
        assertEquals(14, basicComponentTwo.getServiceEffectSpecifications__BasicComponent().size());
        assertEquals(1, basicComponentOne.getProvidedRoles_InterfaceProvidingEntity().size());
        assertEquals(1, basicComponentOne.getRequiredRoles_InterfaceRequiringEntity().size());
        assertEquals(1, basicComponentTwo.getProvidedRoles_InterfaceProvidingEntity().size());
        assertEquals(4, interfaceOne.getSignatures__OperationInterface().size());
        assertEquals(14, interfaceTwo.getSignatures__OperationInterface().size());
    }

}
