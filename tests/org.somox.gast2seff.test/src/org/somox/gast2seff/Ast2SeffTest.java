package org.somox.gast2seff;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
    
}
