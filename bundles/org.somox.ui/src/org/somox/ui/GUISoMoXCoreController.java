package org.somox.ui;

import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.ModelAnalyzerException;
import org.somox.configuration.ConfigurationDefinition;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.controller.SoMoXCoreController;
//import org.somox.controller.SoMoXCoreControllerListener;
//import org.somox.controller.events.StartAnalyzerEvent;
//import org.somox.controller.events.StartExtractorEvent;
import org.somox.core.SoMoXCore;

/**
 * The SoMoX Core Controller
 *
 * @author Benjamin Klatt
 */
public class GUISoMoXCoreController implements SoMoXCoreController {

    // ---------------------------------
    // Static Data Fields
    // ---------------------------------

    // ---------------------------------
    // Data fields
    // ---------------------------------

    /** Internal list of available somox core controller listenern */
    // private LinkedList<SoMoXCoreControllerListener> soMoXCoreControllerListenerList = new
    // LinkedList<SoMoXCoreControllerListener>();

    /**
     * The link to the SoMoXCore
     *
     * @uml.property name="soMoXCore"
     * @uml.associationEnd
     */
    private SoMoXCore soMoXCore = null;

    // ---------------------------------
    // Constructor
    // ---------------------------------

    /** Constructor publishing this controller to the Activator */
    public GUISoMoXCoreController() {
        Activator.getDefault().setGuiSoMoXCoreController(this);
    }

    public GUISoMoXCoreController(final SoMoXCore soMoXCore) {
        this.setSoMoXCore(soMoXCore);
    }

    // ---------------------------------
    // Business Methods
    // ---------------------------------

    /**
     * Create a start extraction event and fire it to all controller listeners
     *
     * @param progressMonitor
     *            The progress monitor to hand over
     */
    public void startExtraction(final IProgressMonitor progressMonitor, final HashMap<String, String> preferences) {
        // TODO what if soMoXCore is null?
        this.soMoXCore.runExtraction(progressMonitor, preferences);
    }

    /**
     * Start the model analysis process step
     *
     * @param progressMonitor
     * @throws ModelAnalyzerException
     */
    public AnalysisResult startAnalyze(final String analyzerID, final IProgressMonitor progressMonitor,
            final HashMap<String, String> globalPreferences, final SoMoXConfiguration somoxConfiguration)
                    throws ModelAnalyzerException {
        // TODO what if soMoXCore is null?
        try {
            return this.soMoXCore.runAnalyzer(analyzerID, progressMonitor, globalPreferences, somoxConfiguration);
        } catch (final ModelAnalyzerException e) {
            throw e;
        }
    }

    // ---------------------------------
    // Getters / Setters
    // ---------------------------------

    /**
     * @param somoxCore
     * @uml.property name="soMoXCore"
     */
    @Override
    public void setSoMoXCore(final SoMoXCore somoxCore) {
        this.soMoXCore = somoxCore;
    }

    @Override
    public LinkedList<ConfigurationDefinition> getConfigurationDefinitions() {
        return this.soMoXCore.getConfigurationDefinitions();
    }

    public LinkedList<ConfigurationDefinition> getGlobalConfigurationDefinitions() {
        return this.soMoXCore.getGlobalConfigurationDefinitions();
    }
}
