package org.somox.gast2seff;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.nio.file.Path;
import java.util.ArrayList;
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
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.osgi.framework.util.ArrayMap;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.InfrastructureProvidedRole;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.impl.ResourceDemandingSEFFImpl;
import org.palladiosimulator.pcm.seff.impl.ServiceEffectSpecificationImpl;
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
        List<MethodAssociation> methodAssociationList = new ArrayList();
        Map<String, BasicComponent> basicComponentMap = new HashMap();
        
        for (var entry : compUnitMap.entrySet()) {
			List<MethodDeclaration> methodDeclarations = MethodDeclarationFinder.perform(entry.getValue());
			for (MethodDeclaration methodDeclaration : methodDeclarations) {
				List<IExtendedModifier> modifierList = methodDeclaration.modifiers();
				
				// Generate a seff for public methods only
				IExtendedModifier firstModifier = modifierList.get(0);
				if (firstModifier.isModifier()) {
					Modifier modifier = (Modifier) firstModifier;
					if (modifier.isPublic()) {
						ResourceDemandingSEFF seff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
						methodBindingMap.put(methodDeclaration, seff);
						TypeDeclaration typeDeclaration = (TypeDeclaration) methodDeclaration.getParent();
						String className = typeDeclaration.getName().toString();
						if (basicComponentMap.containsKey(className)) {
							methodAssociationList.add(new MethodAssociation(methodDeclaration, seff, basicComponentMap.get(className))); 
						} else {
							BasicComponent basicComponent = RepositoryFactory.eINSTANCE.createBasicComponent();
							basicComponentMap.put(className, basicComponent);
							methodAssociationList.add(new MethodAssociation(methodDeclaration, seff, basicComponent)); 
						}
					}
				}
			}
		}
        
       
        Ast2Seff ast2SeffJob = new Ast2Seff();
        // TODO Fill blackboard with information (like root compilation units) for Ast2Seff Job
        Blackboard<Object> blackboard = new Blackboard<>();
        
        blackboard.addPartition("methodBindingMap", methodBindingMap);
        blackboard.addPartition("methodAssociationList", methodAssociationList);
        
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
