package org.somox.gast2seff;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.somox.gast2seff.jobs.Ast2Seff;
import org.somox.kdmhelper.MethodAssociation;

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

    @Disabled
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
    public void testAllClassesInDirectory() throws JobFailedException, UserCanceledException, IOException {
    	
    	Path directoryPath = Path.of("src/org/somox/gast2seff/resources");
    	Map<String, CompilationUnit> compUnitMap = parseDirectory(directoryPath);
        
        Map<MethodDeclaration, ResourceDemandingSEFF> methodBindingMap = new HashMap<>();
        Map<String, List<MethodAssociation>> bundleName2methodAssociationMap = new HashMap<String, List<MethodAssociation>>();
        
        for (var entry : compUnitMap.entrySet()) {
			List<MethodDeclaration> methodDeclarations = MethodDeclarationFinder.perform(entry.getValue());
			for (MethodDeclaration methodDeclaration : methodDeclarations) {
				List<IExtendedModifier> modifierList = methodDeclaration.modifiers();
				
				// Generate a seff for public methods only
				IExtendedModifier firstModifier = modifierList.get(0);
				if (firstModifier.isModifier()) {
					Modifier modifier = (Modifier) firstModifier;
					if (modifier.isPublic()) {
						TypeDeclaration typeDeclaration = (TypeDeclaration) methodDeclaration.getParent();
						String className = typeDeclaration.getName().toString();
						if (bundleName2methodAssociationMap.containsKey(className)) {
							bundleName2methodAssociationMap.get(className).add(new MethodAssociation(className, methodDeclaration, null, null)); 
						} else {
							List<MethodAssociation> methodAssociationList = new ArrayList<MethodAssociation>();
							methodAssociationList.add(new MethodAssociation(className, methodDeclaration, null, null));
							bundleName2methodAssociationMap.put(className, methodAssociationList); 
						}
					}
				}
			}
		}
        
       
        Ast2Seff ast2SeffJob = new Ast2Seff();
        // TODO Fill blackboard with information (like root compilation units) for Ast2Seff Job
        Blackboard<Object> blackboard = new Blackboard<>();
        
        blackboard.addPartition("bundleName2methodAssociationMap", bundleName2methodAssociationMap);
        
        ast2SeffJob.setBlackboard(blackboard);
        NullProgressMonitor progressMonitor = new NullProgressMonitor();
        ast2SeffJob.execute(progressMonitor);
        // TODO Formulate assertions for blackboard content (= results of execution)
    }
    
    @Test
    @Disabled
    public void testSimpleExternalClass() throws JobFailedException, UserCanceledException, IOException {
    	
        ASTParser parser = ASTParser.newParser(AST.getJLSLatest());
        parser.setResolveBindings(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setBindingsRecovery(true);
        parser.setStatementsRecovery(true);
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
