package org.palladiosimulator.somox.ast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.somox.ast2seff.jobs.Ast2SeffJob;
import org.palladiosimulator.somox.ast2seff.models.MethodBundlePair;
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

        Map<String,
                List<MethodBundlePair>> bundleName2methodAssociationMap = new HashMap<String, List<MethodBundlePair>>();

        for (var entry : compUnitMap.entrySet()) {
            List<MethodDeclaration> methodDeclarations = MethodDeclarationFinder.perform(entry.getValue());
            for (MethodDeclaration methodDeclaration : methodDeclarations) {
                List<IExtendedModifier> modifierList = methodDeclaration.modifiers();

                // Generate a SEFF for public methods
                IExtendedModifier firstModifier = modifierList.get(0);
                if (firstModifier.isModifier()) {
                    Modifier modifier = (Modifier) firstModifier;
                    if (modifier.isPublic()) {
                        TypeDeclaration typeDeclaration = (TypeDeclaration) methodDeclaration.getParent();
                        String className = typeDeclaration.getName().toString();
                        if (bundleName2methodAssociationMap.containsKey(className)) {
                            bundleName2methodAssociationMap.get(className)
                                    .add(new MethodBundlePair(className, methodDeclaration));
                        } else {
                            List<MethodBundlePair> methodAssociationList = new ArrayList<MethodBundlePair>();
                            methodAssociationList.add(new MethodBundlePair(className, methodDeclaration));
                            bundleName2methodAssociationMap.put(className, methodAssociationList);
                        }
                    }
                }
            }
        }

        Ast2SeffJob ast2SeffJob = new Ast2SeffJob();

        // TODO Fill blackboard with information (like root compilation units) for Ast2Seff Job
        Blackboard<Object> blackboard = new Blackboard<>();

        blackboard.addPartition("bundleName2methodAssociationMap", bundleName2methodAssociationMap);

        ast2SeffJob.setBlackboard(blackboard);
        NullProgressMonitor progressMonitor = new NullProgressMonitor();
        ast2SeffJob.execute(progressMonitor);

        Repository repository = (Repository) blackboard.getPartition("repository");

        assertNotNull(repository);

        assertEquals(2, repository.getComponents__Repository().size());
        assertEquals(2, repository.getInterfaces__Repository().size());

        BasicComponent basicComponentOne = (BasicComponent) repository.getComponents__Repository().get(0);
        BasicComponent basicComponentTwo = (BasicComponent) repository.getComponents__Repository().get(1);
        OperationInterface interfaceOne = (OperationInterface) repository.getInterfaces__Repository().get(0);
        OperationInterface interfaceTwo = (OperationInterface) repository.getInterfaces__Repository().get(1);

        assertEquals(4, basicComponentOne.getServiceEffectSpecifications__BasicComponent().size());
        assertEquals(14, basicComponentTwo.getServiceEffectSpecifications__BasicComponent().size());
        assertEquals(1, basicComponentOne.getProvidedRoles_InterfaceProvidingEntity().size());
        assertEquals(1, basicComponentOne.getRequiredRoles_InterfaceRequiringEntity().size());
        assertEquals(1, basicComponentTwo.getProvidedRoles_InterfaceProvidingEntity().size());
        assertEquals(4, interfaceOne.getSignatures__OperationInterface().size());
        assertEquals(14, interfaceTwo.getSignatures__OperationInterface().size());
    }

}
