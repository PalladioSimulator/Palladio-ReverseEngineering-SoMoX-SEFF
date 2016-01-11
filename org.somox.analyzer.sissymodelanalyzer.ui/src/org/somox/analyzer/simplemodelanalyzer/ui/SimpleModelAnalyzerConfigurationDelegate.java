package org.somox.analyzer.simplemodelanalyzer.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.somox.analyzer.simplemodelanalyzer.jobs.SaveSoMoXModelsJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SimpleModelAnalyzerJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.configuration.SOMOXConfigurationBuilderByPreferences;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.gast2seff.jobs.GAST2SEFFJob;
import org.somox.ui.runconfig.ModelAnalyzerConfiguration;

import de.uka.ipd.sdq.workflow.Workflow;
import de.uka.ipd.sdq.workflow.extension.ExtensionHelper;
import de.uka.ipd.sdq.workflow.extension.WorkflowExtension;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.launchconfig.AbstractWorkflowBasedLaunchConfigurationDelegate;
import de.uka.ipd.sdq.workflow.logging.console.LoggerAppenderStruct;

/**
 * The class is defined by the delegate attribute of a launchConfigurationType extension and
 * performs launching for a Model Analyzer launch configuration.<br />
 * It offers extension points through the Palladio Workflow Engine. Plugins whishing to extend the
 * launch may register their jobs at the extension point {@code de.uka.ipd.sdq.workflow.job} offered
 * by {@link de.uka.ipd.sdq.workflow}. SoMoX offers three points to extend the workflow (defined by
 * the extension point’s {@code workflowId} attribute:
 * <ul>
 * <li>{@code org.somox.launch.start}: Jobs registering at this workflow id will be executed before
 * any job is run by SoMoX
 * <li>{@code org.somox.launch.modelavailable}: Jobs registering at this workflow id will be
 * executed when all of SoMoX’ internal analysis jobs have run, but before the result is saved.
 * <li>{@code org.somox.launch.end}: Jobs registering at this workflow id will be executed after all
 * jobs have finished. The model will be saved at this point.
 * </ul>
 * 
 * @author Michael Hauck, Joshua Gleitze
 */
public class SimpleModelAnalyzerConfigurationDelegate
        extends AbstractWorkflowBasedLaunchConfigurationDelegate<ModelAnalyzerConfiguration, Workflow> {

    private static final String BEFORE_SIMPLE_MODEL_ANALYZER_JOB_EXTENSION_ID = "org.somox.launch.start";
    private static final String AFTER_MODELS_JOB_EXTENSION_ID = "org.somox.launch.modelavailable";
    private static final String AFTER_ALL_JOBS_EXTENSION_ID = "org.somox.launch.end";

    @Override
    protected IJob createWorkflowJob(final ModelAnalyzerConfiguration config, final ILaunch launch)
            throws CoreException {
        final SequentialBlackboardInteractingJob<SoMoXBlackboard> somoxJob =
                new SequentialBlackboardInteractingJob<SoMoXBlackboard>();
        // OrderPreservingBlackboardCompositeJob<SoMoXBlackboard> somoxJob = new
        // OrderPreservingBlackboardCompositeJob<SoMoXBlackboard>();
        somoxJob.setBlackboard(new SoMoXBlackboard());

        somoxJob.addAll(getExtensionJobs(BEFORE_SIMPLE_MODEL_ANALYZER_JOB_EXTENSION_ID));

        somoxJob.add(new SimpleModelAnalyzerJob(config));

        // Get the project location.
        somoxJob.add(new GAST2SEFFJob());
        
        somoxJob.addAll(getExtensionJobs(AFTER_MODELS_JOB_EXTENSION_ID));
        
        somoxJob.add(new SaveSoMoXModelsJob(config.getSomoxConfiguration()));

        somoxJob.addAll(getExtensionJobs(AFTER_ALL_JOBS_EXTENSION_ID));

        return somoxJob;
    }

    @Override
    protected ModelAnalyzerConfiguration deriveConfiguration(final ILaunchConfiguration launchconfiguration,
            final String mode) throws CoreException {
        final ModelAnalyzerConfiguration config = new ModelAnalyzerConfiguration();

        final SoMoXConfiguration somoxConfiguration = new SOMOXConfigurationBuilderByPreferences()
                .createSOMOXConfiguration(launchconfiguration.getAttributes());
        config.setSomoxConfiguration(somoxConfiguration);

        return config;
    }

    @Override
    protected ArrayList<LoggerAppenderStruct> setupLogging(final Level logLevel) throws CoreException {
        final ArrayList<LoggerAppenderStruct> loggerList = super.setupLogging(logLevel);
        loggerList.add(this.setupLogger("org.somox", logLevel,
                logLevel.isGreaterOrEqual(Level.DEBUG) ? DETAILED_LOG_PATTERN : SHORT_LOG_PATTERN));
        return loggerList;
    }

    /**
     * Gets all jobs registered for the given workflow id.
     * 
     * @param workflowId
     *            The workflow id the desired jobs have registered to.
     * @return All jobs registered to the provided workflow id.
     */
    private Collection<IJob> getExtensionJobs(String workflowId) {
        Collection<IJob> jobs = new ArrayList<IJob>();
        for (WorkflowExtension<?> extension : ExtensionHelper.getWorkflowExtensionsSortedByPriority(workflowId)) {
            jobs.add(extension.getWorkflowExtensionJob());
        }
        return jobs;
    }

}
