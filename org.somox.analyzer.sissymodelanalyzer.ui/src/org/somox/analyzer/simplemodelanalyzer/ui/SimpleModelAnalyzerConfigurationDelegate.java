package org.somox.analyzer.simplemodelanalyzer.ui;

import java.util.ArrayList;

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
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.launchconfig.AbstractWorkflowBasedLaunchConfigurationDelegate;
import de.uka.ipd.sdq.workflow.logging.console.LoggerAppenderStruct;

/**
 * The class is defined by the delegate attribute of a launchConfigurationType extension and
 * performs launching for a Model Analyzer launch configuration.
 *
 * @author Michael Hauck
 */
public class SimpleModelAnalyzerConfigurationDelegate
        extends AbstractWorkflowBasedLaunchConfigurationDelegate<ModelAnalyzerConfiguration, Workflow> {

    @Override
    protected IJob createWorkflowJob(final ModelAnalyzerConfiguration config, final ILaunch launch)
            throws CoreException {
        final SequentialBlackboardInteractingJob<SoMoXBlackboard> somoxJob =
                new SequentialBlackboardInteractingJob<SoMoXBlackboard>();
        // OrderPreservingBlackboardCompositeJob<SoMoXBlackboard> somoxJob = new
        // OrderPreservingBlackboardCompositeJob<SoMoXBlackboard>();
        somoxJob.setBlackboard(new SoMoXBlackboard());

        // TODO: Introduce an Workflow extension point here with the latest Palladio Workflow engine
        somoxJob.add(new SimpleModelAnalyzerJob(config));

        // Get the project location.
        somoxJob.add(new GAST2SEFFJob());

        somoxJob.add(new SaveSoMoXModelsJob(config.getSomoxConfiguration()));

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

}
