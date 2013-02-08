package org.somox.analyzer.simplemodelanalyzer.ui;

import java.util.ArrayList;

import org.apache.log4j.Level;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.somox.analyzer.simplemodelanalyzer.jobs.SaveSoMoXModelsJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SimpleModelAnalyzerJob;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.configuration.SOMOXConfigurationBuilderByPreferences;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.ui.runconfig.ModelAnalyzerConfiguration;

import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.OrderPreservingBlackboardCompositeJob;
import de.uka.ipd.sdq.workflow.Workflow;
import de.uka.ipd.sdq.workflow.launchconfig.AbstractWorkflowBasedLaunchConfigurationDelegate;
import de.uka.ipd.sdq.workflow.logging.console.LoggerAppenderStruct;
import eu.qimpress.reverseengineering.gast2seff.jobs.GAST2SEFFJob;

/**
 * The class is defined by the delegate attribute of a launchConfigurationType
 * extension and performs launching for a Model Analyzer launch configuration.
 * 
 * @author Michael Hauck
 */
public class SimpleModelAnalyzerConfigurationDelegate
		extends
		AbstractWorkflowBasedLaunchConfigurationDelegate<ModelAnalyzerConfiguration, Workflow> {
	
	protected IJob createWorkflowJob(final ModelAnalyzerConfiguration config,
			ILaunch launch) throws CoreException {
		OrderPreservingBlackboardCompositeJob<SoMoXBlackboard> somoxJob = new OrderPreservingBlackboardCompositeJob<SoMoXBlackboard>();
		somoxJob.setBlackboard(new SoMoXBlackboard());
		somoxJob.add(new SimpleModelAnalyzerJob(config));
		
		// Get the project location.
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		somoxJob.add(new GAST2SEFFJob(config.getSomoxConfiguration()));
		
		somoxJob.add(new SaveSoMoXModelsJob(config.getSomoxConfiguration()));
		
		
		return somoxJob;
	}

	protected ModelAnalyzerConfiguration deriveConfiguration(
			ILaunchConfiguration launchconfiguration, String mode)
			throws CoreException {
		ModelAnalyzerConfiguration config = new ModelAnalyzerConfiguration();
		
		SoMoXConfiguration somoxConfiguration = (new SOMOXConfigurationBuilderByPreferences()).createSOMOXConfiguration(launchconfiguration.getAttributes());
		config.setSomoxConfiguration(somoxConfiguration);
		
		return config;
	}

	@Override
	protected ArrayList<LoggerAppenderStruct> setupLogging(Level logLevel) throws CoreException {
		ArrayList<LoggerAppenderStruct> loggerList = super.setupLogging(logLevel); 
		loggerList.add(setupLogger("org.somox", logLevel, logLevel
				.isGreaterOrEqual(Level.DEBUG) ? DETAILED_LOG_PATTERN
				: SHORT_LOG_PATTERN));
		return loggerList;
	}

}
