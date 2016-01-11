package org.somox.analyzer.simplemodelanalyzer.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.jobs.SaveSoMoXModelsJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SimpleModelAnalyzerJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.gast2seff.jobs.GAST2SEFFJob;
import org.somox.ui.runconfig.ModelAnalyzerConfiguration;

import de.uka.ipd.sdq.workflow.extension.AbstractExtendableJob;
import de.uka.ipd.sdq.workflow.extension.ExtendableJobConfiguration;

/**
 * Job running a complete analysis with SoMoX using a {@link SimpleModelAnalyzerJob}. The job is
 * extendible through extension points to allow 3rd party tools to easily integrate with it. Tools
 * can offer their {@link de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob
 * AbstractWorkflowExtensionJobs} through Palladio’s extandable job API at this class’ ID constants’
 * values.
 *
 * <p>
 * Extending jobs will get the {@link SoMoXBlackboard} used by all jobs passed as Blackboard. They
 * may modify it as they wish. The job’s configuration HashMap will be build as follows:
 * <table summary="The configuration map passed to extending jobs">
 * <tr>
 * <th>Map Key
 * <th>Map Value Type
 * <th>Description
 * <tr>
 * <td>{@code somoxConfigurationMap}
 * <td>{@code HashMap<String, Object>}
 * <td>The used {@link org.somox.configuration.SoMoXConfiguration SoMoXConfiguration’s} attribute
 * map representation.
 * <tr>
 * <td>{@code architecture}
 * <td>{@link org.palladiosimulator.pcm.repository.Repository}
 * <td>The examined software’s PCM repository
 * <tr>
 * <tr>
 * <td>{@code allocation}
 * <td>{@link org.palladiosimulator.pcm.allocation.Allocation}
 * <td>The examined software’s PCM allocation model.
 * <tr>
 * <td>{@code QoS}
 * <td>{@link org.palladiosimulator.pcm.qosannotations.QoSAnnotations}
 * <td>The examined software’s PCM quality of service annotation model.
 * <tr>
 * <td>{@code system}
 * <td>{@link org.palladiosimulator.pcm.system.System}
 * <td>The examined software’s PCM system model.
 * </table>
 *
 * <p>
 * Jobs that can have dependencies to SoMoX can simply obtain all data by using the passed
 * {@link SoMoXBlackboard} and building the {@link org.somox.configuration.SoMoXConfiguration} out
 * of the {@code somoxConfigurationMap} job configuration property using
 * {@link org.somox.configuration.SoMoXConfiguration#SoMoXConfiguration(Map)}.
 *
 * Jobs not wishing to have any dependencies to SoMoX can still access all PCM objects known to
 * SoMoX from the job configuration.
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
            final HashMap<String, Object> attributes = new HashMap<String, Object>();
            // TODO: Fix bug about AbstractExtendableJob’s propagation of the Blackboard (WFE-70)
            final AnalysisResult analysisResult = ((SoMoXBlackboard) myBlackboard).getAnalysisResult();

            attributes.put("architecture", analysisResult.getInternalArchitectureModel());
            attributes.put("allocation", analysisResult.getAllocation());
            attributes.put("QoS", analysisResult.getQosAnnotationModel());
            attributes.put("system", analysisResult.getSystemModel());
            attributes.put("somoxConfigurationMap", config.getSomoxConfiguration().toMap());

            return attributes;
        }

    }
}
