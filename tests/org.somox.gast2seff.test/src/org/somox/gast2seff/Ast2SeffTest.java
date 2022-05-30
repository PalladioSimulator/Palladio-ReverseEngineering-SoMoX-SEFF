package org.somox.gast2seff;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
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
    
}
