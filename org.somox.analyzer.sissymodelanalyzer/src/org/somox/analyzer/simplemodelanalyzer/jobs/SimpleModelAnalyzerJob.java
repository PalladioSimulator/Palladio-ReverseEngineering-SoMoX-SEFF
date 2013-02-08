package org.somox.analyzer.simplemodelanalyzer.jobs;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.osgi.service.prefs.BackingStoreException;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.ModelAnalyzerException;
import org.somox.analyzer.simplemodelanalyzer.Activator;
import org.somox.analyzer.simplemodelanalyzer.SimpleModelAnalyzer;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.ui.GUISoMoXCoreController;
import org.somox.ui.runconfig.ModelAnalyzerConfiguration;

import de.uka.ipd.sdq.workflow.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;

/**
 * @author  Snowball
 */
public class SimpleModelAnalyzerJob implements IBlackboardInteractingJob<SoMoXBlackboard> {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(SimpleModelAnalyzerJob.class);
	
	private GUISoMoXCoreController controller;
	
	private HashMap<String, String> globalPreferences = new HashMap<String, String>();
	
	private SoMoXConfiguration somoxConfiguration;
	
	/** The somox blackboard to interact with. */
	private SoMoXBlackboard blackboard = null;
	
	public SimpleModelAnalyzerJob(ModelAnalyzerConfiguration config) throws CoreException {
		super();

		controller = getSoMoXController();
		
		this.somoxConfiguration = config.getSomoxConfiguration();
		
		this.globalPreferences = getGlobalSoMoXPluginPreferences();
	}

	private GUISoMoXCoreController getSoMoXController() throws CoreException {
		// check that the controller is available
		GUISoMoXCoreController controller = org.somox.ui.Activator.getDefault()
				.getGuiSoMoXCoreController();

		if (controller == null) {
			throw new CoreException(
					new Status(
							Status.ERROR,
							Activator.PLUGIN_ID,
							"The SoMoX Core Controller can not be accessed. This indicates that the controller is not loaded yet. May be the SoMoX Core itself is not in place or not started yet."));
		}
		return controller;
	}
	
	private HashMap<String, String> getGlobalSoMoXPluginPreferences() throws CoreException {
		HashMap<String, String> globalPreferences = new HashMap<String, String>();
		
		IEclipsePreferences pluginPreferences = new DefaultScope().getNode(Activator.PLUGIN_ID);
		String[] availableProperties;
		try {
			availableProperties = pluginPreferences.keys();
		} catch (BackingStoreException e) {
			throw new CoreException(new Status(Status.ERROR,Activator.PLUGIN_ID,"SoMoX Setup failed",e));
		}
		// Make a copy of the preferences to store run dialog
		// preferences as well
		for (int i = 0; i < availableProperties.length; i++) {
			globalPreferences.put
				(availableProperties[i],
					pluginPreferences
							.get(availableProperties[i],""));
		}
		
		return globalPreferences;
	}	
	
	public void execute(IProgressMonitor monitor) throws JobFailedException,
			UserCanceledException {
		try {
			AnalysisResult result = controller.startAnalyze(SimpleModelAnalyzer.class.getName(),
					monitor, this.globalPreferences, this.somoxConfiguration);
			blackboard.setAnalysisResult(result);
		} catch (ModelAnalyzerException e) {
			throw new JobFailedException("SoMoX Failed",e);
		}
	}

	public String getName() {
		return "SoMoX Analyzer Job";
	}

	public void rollback(IProgressMonitor monitor)
			throws RollbackFailedException {
		// Not needed.
	}

	/**
	 * @param blackBoard the blackBoard to set
	 */
	public void setBlackboard(SoMoXBlackboard blackBoard) {
		this.blackboard = blackBoard;
	}
}
