package org.somox.analyzer.simplemodelanalyzer.ui.test;

import org.eclipse.core.runtime.IProgressMonitor;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;

import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * Test class registered for
 * {@link org.somox.analyzer.simplemodelanalyzer.ui.ExtendableCompleteSimpleModelAnalysisJob#AFTER_MODELS_JOB_EXTENSION_ID}
 * . Performs injected test logic when being executed.
 *
 * @author Joshua Gleitze
 */
public class TestModelAvailableExtendingJob extends AbstractWorkflowExtensionJob<SoMoXBlackboard> {
    private static TestLogic logic;

    /**
     * Registers {@code logic} to be executed when this job is executed.
     *
     * @param testLogic
     *            The tes logi to execute.
     */
    public static void performTest(final TestLogic testLogic) {
        logic = testLogic;
    }

    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        super.execute(monitor);

        // report that we were called
        ExtendableCompleteSimpleModelAnalysisJobTest.reportCall(TestModelAvailableExtendingJob.class);
        if (logic != null) {
            logic.test(myBlackboard);
        }
    }
}