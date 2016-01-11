package org.somox.analyzer.simplemodelanalyzer.ui;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.ui.runconfig.ModelAnalyzerConfiguration;

import de.uka.ipd.sdq.workflow.Workflow;
import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
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
 * jobs have finished. The model will have been saved at this point.
 * </ul>
 *
 * @author Michael Hauck, Joshua Gleitze
 */
public class SimpleModelAnalyzerConfigurationDelegate
        extends AbstractWorkflowBasedLaunchConfigurationDelegate<ModelAnalyzerConfiguration, Workflow> {
    /**
     * Keys of attributes that shall be interpreted as Doubles when being received from the launch
     * configuration. They will be parsed using {@link Double#parseDouble(String)}.
     */
    private static final String[] DOUBLE_ATTRIBUTES = {
            SoMoXConfiguration.SOMOX_WEIGHT_PACKAGE_MAPPING,
            SoMoXConfiguration.SOMOX_WEIGHT_MID_NAME_RESEMBLANCE,
            SoMoXConfiguration.SOMOX_WEIGHT_LOW_SLAQ,
            SoMoXConfiguration.SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE,
            SoMoXConfiguration.SOMOX_WEIGHT_LOW_COUPLING,
            SoMoXConfiguration.SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT,
            SoMoXConfiguration.SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT,
            SoMoXConfiguration.SOMOX_WEIGHT_HIGH_SLAQ,
            SoMoXConfiguration.SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE,
            SoMoXConfiguration.SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE,
            SoMoXConfiguration.SOMOX_WEIGHT_HIGH_COUPLING,
            SoMoXConfiguration.SOMOX_WEIGHT_DMS,
            SoMoXConfiguration.SOMOX_WEIGHT_DIRECTORY_MAPPING
    };

    /**
     * Keys of attributes that shall be interpreted as Percentages when being received from the
     * launch configuration. They will be parsed using {@link Double#parseDouble(String)} and
     * divided by 100.
     */
    private static final String[] PERCENTAGE_ATTRIBUTES =
            { SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_COMPOSE,
                    SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_COMPOSE,
                    SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_COMPOSE,
                    SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_MERGE,
                    SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_MERGE,
                    SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_MERGE };

    @Override
    protected IJob createWorkflowJob(final ModelAnalyzerConfiguration config, final ILaunch launch)
            throws CoreException {
        final SequentialBlackboardInteractingJob<Blackboard<?>> somoxJob = new ExtendableCompleteSimpleModelAnalysisJob(config);
        somoxJob.setBlackboard(new SoMoXBlackboard());

        return somoxJob;
    }

    @Override
    protected ModelAnalyzerConfiguration deriveConfiguration(final ILaunchConfiguration launchconfiguration,
            final String mode) throws CoreException {
        final ModelAnalyzerConfiguration config = new ModelAnalyzerConfiguration();

        // convert the attributes received into a attribute map compatible to SoMoXConfiguration
        Map<String, Object> attributeMap = launchconfiguration.getAttributes();

        for (String key : DOUBLE_ATTRIBUTES) {
            attributeMap.put(key, Double.parseDouble((String) attributeMap.get(key)));
        }

        for (String key : PERCENTAGE_ATTRIBUTES) {
            attributeMap.put(key, Double.parseDouble((String) attributeMap.get(key)) / 100.0d);
        }

        final SoMoXConfiguration somoxConfiguration = new SoMoXConfiguration(attributeMap);
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
