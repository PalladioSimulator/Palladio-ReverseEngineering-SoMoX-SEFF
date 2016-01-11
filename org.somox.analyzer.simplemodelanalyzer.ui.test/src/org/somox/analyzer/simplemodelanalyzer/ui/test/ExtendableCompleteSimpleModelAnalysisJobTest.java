package org.somox.analyzer.simplemodelanalyzer.ui.test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.analyzer.simplemodelanalyzer.ui.ExtendableCompleteSimpleModelAnalysisJob;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.ui.runconfig.ModelAnalyzerConfiguration;

import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;

/**
 * Tests for {@link ExtendableCompleteSimpleModelAnalysisJob}.
 *
 * @author Joshua Gleitze
 */
public class ExtendableCompleteSimpleModelAnalysisJobTest {

    /**
     * Set of Classes reporting to have been called.
     */
    private static final Set<Class<?>> CALLED_CLASSES = new HashSet<>();
    private SoMoXConfiguration somoxConfig;
    private ModelAnalyzerConfiguration modelAnalyzerConfig;
    private Runnable job;

    /**
     * Removes all classes from {@link #CALLED_CLASSES}, to make sure that during the test, any
     * class in there proves a call of this test run (and not an old one).
     */
    @Before
    public void resetAllCalledClasses() {
        CALLED_CLASSES.clear();
    }

    /**
     * Sets up the job, ready to be run from {@link #job}.
     *
     * @throws CoreException
     *             If creating the job throws a CoreException.
     */
    @Before
    public void setUpJob() throws CoreException {
        modelAnalyzerConfig = new ModelAnalyzerConfiguration();
        somoxConfig = new SoMoXConfiguration();
        modelAnalyzerConfig.setSomoxConfiguration(somoxConfig);
        somoxConfig.getFileLocations().setProjectName("TestedProject");

        final SequentialBlackboardInteractingJob<Blackboard<?>> somoxJob =
                new ExtendableCompleteSimpleModelAnalysisJob(modelAnalyzerConfig);
        somoxJob.setBlackboard(new SoMoXBlackboard());
        job = () -> {
            try {
                somoxJob.execute(new NullProgressMonitor());
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        };

    }

    /**
     * Asserts that registered jobs get executed for all extension points and that the blackboard
     * they get is not {@code null}.
     */
    @Test
    public void testExtensionPointsGetCalled() {
        final TestLogic blackboardTest = (blackboard) -> {
            assertThat(blackboard, is(not(nullValue())));
        };
        TestStartExtendingJob.performTest(blackboardTest);
        TestModelAvailableExtendingJob.performTest(blackboardTest);
        TestEndExtendingJob.performTest(blackboardTest);

        job.run();

        assertThat(CALLED_CLASSES, hasItem(TestStartExtendingJob.class));
        assertThat(CALLED_CLASSES, hasItem(TestModelAvailableExtendingJob.class));
        assertThat(CALLED_CLASSES, hasItem(TestEndExtendingJob.class));
    }

    /**
     * Reports that {@code clazz} was executed.
     *
     * @param clazz
     *            A class that has been called.
     */
    public static void reportCall(final Class<?> clazz) {
        CALLED_CLASSES.add(clazz);
    }
}
