package org.somox.analyzer.simplemodelanalyzer.ui;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.somox.analyzer.simplemodelanalyzer.jobs.SaveSoMoXModelsJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SimpleModelAnalyzerJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.gast2seff.jobs.GAST2SEFFJob;
import org.somox.ui.runconfig.ModelAnalyzerConfiguration;

import de.uka.ipd.sdq.workflow.extension.AbstractExtendableJob;
import de.uka.ipd.sdq.workflow.extension.ExtendableJobConfiguration;

/**
 * Job running a complete analysis with SoMoX using a {@link SimpleModelAnalyzerJob}. The job is
 * extendible through extension points to allow 3rd party tools to easily integrate with it. Tools
 * can offer their {@link de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob
 * AbstractWorkflowExtensionJobs} through Palladio’s extendible job API at this class’ ID constants’
 * values.
 *
 * <p>
 * Extending jobs will get the {@link SoMoXBlackboard} used by all jobs passed as Blackboard. They
 * may modify it as they wish. The job’s configuration HashMap will be the
 * {@linkplain SoMoXConfiguration#toMap() attribute representation} of the used
 * {@link SoMoXConfiguration}.
 *
 * @author Joshua Gleitze
 */
public class ExtendableCompleteSimpleModelAnalysisJob extends AbstractExtendableJob<SoMoXBlackboard> {

    /**
     * Workflow extension ID for before any analysis was run.
     */
    public static final String BEFORE_ALL_JOBS_EXTENSION_ID = "org.somox.analyzer.simplemodelanalyzer.launch.start";
    /**
     * Workflow extension ID for after SoMoX’ model jobs have run but before the model will be
     * written to disk.
     */
    public static final String AFTER_MODELS_JOB_EXTENSION_ID =
            "org.somox.analyzer.simplemodelanalyzer.launch.modelavailable";
    /**
     * Workflok extension ID for after all SoMoX’ jobs have run. The model has already been written
     * to a file and SoMoX will terminate after this point.
     */
    public static final String AFTER_ALL_JOBS_EXTENSION_ID = "org.somox.analyzer.simplemodelanalyzer.launch.end";

    /**
     * Creates the job. All extending jobs will be set up.
     *
     * @param config
     *            The configuration to use.
     * @throws CoreException
     *             If the used {@link SimpleModelAnalyzerJob} throws a CoreException.
     */
    public ExtendableCompleteSimpleModelAnalysisJob(final ModelAnalyzerConfiguration config) throws CoreException {
        final ExtendableJobConfiguration extensionJobsConfiguration = new SoMoXExtensionJobConfiguration(config);

        handleJobExtensions(ExtendableCompleteSimpleModelAnalysisJob.BEFORE_ALL_JOBS_EXTENSION_ID,
                extensionJobsConfiguration);

        this.add(new SimpleModelAnalyzerJob(config));
        this.add(new GAST2SEFFJob());

        handleJobExtensions(ExtendableCompleteSimpleModelAnalysisJob.AFTER_MODELS_JOB_EXTENSION_ID,
                extensionJobsConfiguration);

        this.add(new SaveSoMoXModelsJob(config.getSomoxConfiguration()));

        handleJobExtensions(ExtendableCompleteSimpleModelAnalysisJob.AFTER_ALL_JOBS_EXTENSION_ID,
                extensionJobsConfiguration);

    }

    /**
     * Builder of the configurations passed to jobs extending the
     * {@link ExtendableCompleteSimpleModelAnalysisJob}. See its documentation for available
     * attributes.
     *
     * @author Joshua Gleitze
     */
    private final class SoMoXExtensionJobConfiguration implements ExtendableJobConfiguration {
        private final ModelAnalyzerConfiguration config;

        /**
         * Builds the configuration.
         *
         * @param config
         *            The configuration used by the main job.
         */
        private SoMoXExtensionJobConfiguration(final ModelAnalyzerConfiguration config) {
            this.config = config;
        }

        @Override
        public Map<String, Object> getAttributes() {
            return config.getSomoxConfiguration().toMap();
        }

    }
}
