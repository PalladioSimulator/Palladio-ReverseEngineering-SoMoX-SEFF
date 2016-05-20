package org.somox.analyzer.simplemodelanalyzer.ui.test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.analyzer.simplemodelanalyzer.ui.ExtendableCompleteSimpleModelAnalysisJob;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.ui.runconfig.SoMoXModelAnalyzerConfiguration;

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
    /**
     * Path from the current workspace to the template workspace containing files to be copied into
     * this workspace.
     */
    private static final String TEMPLATE_WORKSPACE = "../workspace";
    private static final String TEST_PROJECT_NAME = "TestedProject";
    private SoMoXConfiguration somoxConfig;
    private SoMoXModelAnalyzerConfiguration modelAnalyzerConfig;
    private Runnable job;

    /**
     * Sets up the test workspace such that SoMoX can find everything it needs.
     *
     * @throws IOException
     *             If the template project cannot be copied into the workspace.
     * @throws CoreException
     *             If importing the template project fails.
     */
    @BeforeClass
    public static void setUpWorkspace() throws IOException, CoreException {
        // set up the workspace by copying in the template workspace
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IPath workspacePath = workspace.getRoot().getLocation();
        FileUtils.copyDirectory(workspacePath.append(TEMPLATE_WORKSPACE).toFile(), workspacePath.toFile());

        // import the tested project
        final IPath testedProjectPath = workspacePath.append(TEST_PROJECT_NAME).append(".project");
        final IProjectDescription description = workspace.loadProjectDescription(testedProjectPath);
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
        project.create(description, null);
        project.open(null);
    }

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
        this.modelAnalyzerConfig = new SoMoXModelAnalyzerConfiguration();
        this.somoxConfig = new SoMoXConfiguration();
        this.modelAnalyzerConfig.setMoxConfiguration(this.somoxConfig);
        this.somoxConfig.getFileLocations().setProjectName(TEST_PROJECT_NAME);

        final SequentialBlackboardInteractingJob<SoMoXBlackboard> somoxJob = new ExtendableCompleteSimpleModelAnalysisJob(
                this.modelAnalyzerConfig);
        somoxJob.setBlackboard(new SoMoXBlackboard());
        this.job = () -> {
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

        this.job.run();

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
