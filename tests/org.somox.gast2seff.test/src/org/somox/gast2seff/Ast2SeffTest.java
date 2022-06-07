package org.somox.gast2seff;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.impl.ResourceDemandingSEFFImpl;
import org.palladiosimulator.pcm.seff.impl.ServiceEffectSpecificationImpl;
import org.somox.gast2seff.jobs.Ast2Seff;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

public class Ast2SeffTest {

	@Disabled
    @Test
    public void testJobAst2Seff() throws JobFailedException, UserCanceledException {
        Ast2Seff ast2SeffJob = new Ast2Seff();
        // TODO Fill blackboard with information (like root compilation units) for Ast2Seff Job
        Blackboard<Object> blackboard = new Blackboard<>();
        ast2SeffJob.setBlackboard(blackboard);
        NullProgressMonitor progressMonitor = new NullProgressMonitor();
        ast2SeffJob.execute(progressMonitor);
        // TODO Formulate assertions for blackboard content (= results of execution)
    }
	
	private String[] getEntries(java.nio.file.Path dir, String suffix) {
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
        String  javaCoreVersion = JavaCore.latestSupportedJavaVersion();
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
        Arrays.fill(encodings,  StandardCharsets.UTF_8.toString());
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
    public void testSimpleClass() throws JobFailedException, UserCanceledException, IOException {
    	
        ASTParser parser = ASTParser.newParser(AST.getJLSLatest());
        parser.setResolveBindings(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setBindingsRecovery(true);
        File resource = new File("src/org/somox/gast2seff/resources/SimpleClass.java");
        java.nio.file.Path sourcePath = Paths.get(resource.toURI());
        String sourceString = new String(Files.readAllBytes(sourcePath));
        char[] source = sourceString.toCharArray();
        parser.setSource(source);
        parser.setUnitName(sourcePath.toAbsolutePath().toString());
        CompilationUnit astRoot = (CompilationUnit) parser.createAST(null);
        
        Map<MethodDeclaration, ResourceDemandingSEFF> methodBindingMap = new HashMap<>();
        
        List<MethodDeclaration> methodDeclarations = MethodDeclarationFinder.perform(astRoot);
        for (MethodDeclaration methodDeclaration : methodDeclarations) {
        	ResourceDemandingSEFF seff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
            methodBindingMap.put(methodDeclaration, seff);
        }

        Ast2Seff ast2SeffJob = new Ast2Seff();
        // TODO Fill blackboard with information (like root compilation units) for Ast2Seff Job
        Blackboard<Object> blackboard = new Blackboard<>();
        
        blackboard.addPartition("methodBindingMap", methodBindingMap);
        
        ast2SeffJob.setBlackboard(blackboard);
        NullProgressMonitor progressMonitor = new NullProgressMonitor();
        ast2SeffJob.execute(progressMonitor);
        // TODO Formulate assertions for blackboard content (= results of execution)
    }
    
    @Test
    public void testSimpleExternalClass() throws JobFailedException, UserCanceledException, IOException {
    	
        ASTParser parser = ASTParser.newParser(AST.getJLSLatest());
        parser.setResolveBindings(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setBindingsRecovery(true);
        File resource = new File("src/org/somox/gast2seff/resources/SimpleExternalClass.java");
        java.nio.file.Path sourcePath = Paths.get(resource.toURI());
        String sourceString = new String(Files.readAllBytes(sourcePath));
        char[] source = sourceString.toCharArray();
        parser.setSource(source);
        parser.setUnitName(sourcePath.toAbsolutePath().toString());
        CompilationUnit astRoot = (CompilationUnit) parser.createAST(null);
        
        Map<MethodDeclaration, ResourceDemandingSEFF> methodBindingMap = new HashMap<>();
        
        List<MethodDeclaration> methodDeclarations = MethodDeclarationFinder.perform(astRoot);
        for (MethodDeclaration methodDeclaration : methodDeclarations) {
        	ResourceDemandingSEFF seff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
            methodBindingMap.put(methodDeclaration, seff);
        }

        Ast2Seff ast2SeffJob = new Ast2Seff();
        // TODO Fill blackboard with information (like root compilation units) for Ast2Seff Job
        Blackboard<Object> blackboard = new Blackboard<>();
        
        blackboard.addPartition("methodBindingMap", methodBindingMap);
        
        ast2SeffJob.setBlackboard(blackboard);
        NullProgressMonitor progressMonitor = new NullProgressMonitor();
        ast2SeffJob.execute(progressMonitor);
        // TODO Formulate assertions for blackboard content (= results of execution)
    }
    
}
