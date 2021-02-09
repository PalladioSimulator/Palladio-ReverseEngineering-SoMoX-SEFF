package org.somox.analyzer.simplemodelanalyzer.jobs;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
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
import org.somox.ui.runconfig.SoMoXModelAnalyzerConfiguration;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

/**
 * @author Snowball
 */
public class SimpleModelAnalyzerJob implements IBlackboardInteractingJob<SoMoXBlackboard> {

    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(SimpleModelAnalyzerJob.class);

    private final GUISoMoXCoreController controller;

    private HashMap<String, String> globalPreferences = new HashMap<String, String>();

    private final SoMoXConfiguration somoxConfiguration;

    /** The somox blackboard to interact with. */
    private SoMoXBlackboard blackboard = null;

    public SimpleModelAnalyzerJob(final SoMoXModelAnalyzerConfiguration config) throws CoreException {
        super();

        this.controller = this.getSoMoXController();

        this.somoxConfiguration = config.getMoxConfiguration();

        this.globalPreferences = this.getGlobalSoMoXPluginPreferences();
    }

    private GUISoMoXCoreController getSoMoXController() throws CoreException {
        // check that the controller is available
        final GUISoMoXCoreController controller = org.somox.ui.Activator.getDefault().getGuiSoMoXCoreController();

        if (controller == null) {
            throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                    "The SoMoX Core Controller can not be accessed. This indicates that the controller is not loaded yet. May be the SoMoX Core itself is not in place or not started yet."));
        }
        return controller;
    }

    private HashMap<String, String> getGlobalSoMoXPluginPreferences() throws CoreException {
        final HashMap<String, String> globalPreferences = new HashMap<String, String>();

        final IEclipsePreferences pluginPreferences = new DefaultScope().getNode(Activator.PLUGIN_ID);
        String[] availableProperties;
        try {
            availableProperties = pluginPreferences.keys();
        } catch (final BackingStoreException e) {
            throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "SoMoX Setup failed", e));
        }
        // Make a copy of the preferences to store run dialog
        // preferences as well
        for (final String availablePropertie : availableProperties) {
            globalPreferences.put(availablePropertie, pluginPreferences.get(availablePropertie, ""));
        }

        return globalPreferences;
    }

    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        try {
            final AnalysisResult result = this.controller.startAnalyze(SimpleModelAnalyzer.class.getName(), monitor,
                    this.globalPreferences, this.somoxConfiguration);
            this.blackboard.setAnalysisResult(result);
        } catch (final ModelAnalyzerException e) {
            throw new JobFailedException("SoMoX Failed", e);
        }
    }

    @Override
    public String getName() {
        return "SoMoX Analyzer Job";
    }

    /**
     * @param blackBoard
     *            the blackBoard to set
     */
    @Override
    public void setBlackboard(final SoMoXBlackboard blackBoard) {
        this.blackboard = blackBoard;
    }

    @Override
    public void cleanup(final IProgressMonitor arg0) throws CleanupFailedException {
        // TODO Auto-generated method stub

    }
}
