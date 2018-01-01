package org.somox.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;
import org.somox.analyzer.ModelAnalyzer;
import org.somox.controller.SoMoXCoreController;
import org.somox.core.impl.SoMoXCoreStandard;
import org.somox.extractor.SoftwareExtractor;

/**
 * The Activator for the core plugin.
 *
 * @author Benjamin Klatt
 */
public class Activator extends Plugin {

    // ---------------------------------
    // Static Data Fields
    // ---------------------------------

    /** The id of this plugin */
    public static final String PLUGIN_ID = "org.somox.core";

    public static final String EXTENSION_POINT_ID_SOFTWAREEXTRACTOR = "softwareextractor";
    public static final String EXTENSION_POINT_ID_MODELANALYZER = "modelanalyzer";
    public static final String EXTENSION_POINT_ID_CONTROLLER = "controller";

    /**
     * The shared instance
     *
     * @uml.property name="plugin"
     * @uml.associationEnd
     */
    private static Activator plugin;

    // ---------------------------------
    // Data fields
    // ---------------------------------

    /**
     * The core controller listener
     *
     * @uml.property name="somoxCore"
     * @uml.associationEnd
     */
    private SoMoXCore somoxCore = null;

    // ---------------------------------
    // Constructor
    // ---------------------------------

    public Activator() {
    }

    // ---------------------------------
    // Business Methods
    // ---------------------------------

    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        this.somoxCore = new SoMoXCoreStandard();
        this.connectCoreControllerExtensions();
        this.loadSoftwareExtractors();
        this.loadModelAnalyzers();
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Method to wake up the core extension
     */
    public static void wakeUp() {
    }

    // ---------------------------------
    // Helper Methods
    // ---------------------------------

    /**
     * Check the installed software extractor plugins and register them in the SoMoX Core
     *
     * @return The number of checked plugins
     */
    private int loadSoftwareExtractors() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();
        final IExtensionPoint point = registry.getExtensionPoint(PLUGIN_ID, EXTENSION_POINT_ID_SOFTWAREEXTRACTOR);
        if (point == null) {
            return 0;
        }
        final IExtension[] extensions = point.getExtensions();
        int i = 0;
        for (; i < extensions.length; i++) {
            final SoftwareExtractor extractor = this.buildSoftwareExtractor(extensions[i].getConfigurationElements());
            if (extractor != null) {
                // TODO: Do not set the software extractor list directly in the core.
                // Better store a list of the available extractors and define in
                // the configuration which one to use and their specific configurations
                this.somoxCore.addSoftwareExtractor(extractor.getClass().getName(), extractor);
            }
        }
        return i;
    }

    /**
     * Check the installed Model Analyzer plug-ins and register them in the SoMoX Core
     *
     * @return The number of checked plugins
     */
    private int loadModelAnalyzers() {

        // get the extensions from the registry
        final IExtensionRegistry registry = Platform.getExtensionRegistry();
        final IExtensionPoint point = registry.getExtensionPoint(PLUGIN_ID, EXTENSION_POINT_ID_MODELANALYZER);
        if (point == null) {
            return 0;
        }

        final IExtension[] extensions = point.getExtensions();
        int i = 0;
        for (; i < extensions.length; i++) {
            final ModelAnalyzer analyzer = this.buildModelAnalyzer(extensions[i].getConfigurationElements());
            if (analyzer != null) {
                // TODO: Do not set the model analyzer list directly in the core.
                // Better store a list of the available analyzers and define in
                // the configuration which one to use and their specific configurations
                this.somoxCore.addModelAnalyzer(analyzer.getClass().getName(), analyzer);
            }
        }
        return i;
    }

    /**
     * Connect the core to all available Core Controller Extensions
     *
     * @return The number of connected controllers
     */
    private int connectCoreControllerExtensions() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();
        final IExtensionPoint point = registry.getExtensionPoint(PLUGIN_ID, EXTENSION_POINT_ID_CONTROLLER);
        if (point == null) {
            return 0;
        }
        final IExtension[] extensions = point.getExtensions();
        int i = 0;
        for (; i < extensions.length; i++) {
            final SoMoXCoreController controller = this.buildController(extensions[i].getConfigurationElements());
            if (controller != null) {
                controller.setSoMoXCore(this.somoxCore);
            }
        }
        return i;
    }

    /**
     * Create a SoMoX Controller out of an extension point configuration
     *
     * @param configuration
     *            The configuration
     * @return The build SoMoX Controller or null if this was not possible
     */
    private SoMoXCoreController buildController(final IConfigurationElement[] configurations) {
        SoMoXCoreController controller = null;

        for (final IConfigurationElement configuration : configurations) {
            if (EXTENSION_POINT_ID_CONTROLLER.equals(configuration.getName())) {
                try {
                    controller = (SoMoXCoreController) configuration.createExecutableExtension("class");
                    controller.setSoMoXCore(this.somoxCore);
                } catch (final CoreException e) {
                    e.printStackTrace();
                }
            }
            this.getLog().log(new Status(IStatus.INFO, Activator.PLUGIN_ID, "contoller loaded"));
        }

        return controller;
    }

    /**
     * Create a SoMoX Controller out of an extension point configuration
     *
     * @param configuration
     *            The configuration
     * @return The build SoMoX Controller or null if this was not possible
     */
    private SoftwareExtractor buildSoftwareExtractor(final IConfigurationElement[] configurations) {
        SoftwareExtractor extractor = null;

        for (final IConfigurationElement configuration : configurations) {
            if (EXTENSION_POINT_ID_SOFTWAREEXTRACTOR.equals(configuration.getName())) {
                try {
                    extractor = (SoftwareExtractor) configuration.createExecutableExtension("class");
                } catch (final CoreException e) {
                    e.printStackTrace();
                }
            }
            this.getLog().log(new Status(IStatus.INFO, Activator.PLUGIN_ID, "extractor loaded"));
        }

        return extractor;
    }

    /**
     * Create a SoMoX Model Analyzer out of an extension point configuration
     *
     * @param configuration
     *            The configuration
     * @return The build SoMoX Model Analyzer or null if this was not possible
     */
    private ModelAnalyzer buildModelAnalyzer(final IConfigurationElement[] configurations) {
        ModelAnalyzer analyzer = null;

        for (final IConfigurationElement configuration : configurations) {
            if (EXTENSION_POINT_ID_MODELANALYZER.equals(configuration.getName())) {
                try {
                    analyzer = (ModelAnalyzer) configuration.createExecutableExtension("class");
                } catch (final CoreException e) {
                    e.printStackTrace();
                }
            } else {
                this.getLog()
                        .log(new Status(IStatus.INFO, Activator.PLUGIN_ID,
                                "Tried to build a model analyzer from a non model analyzer plug-in: "
                                        + configuration.getName()));
            }
        }

        return analyzer;
    }

    // ---------------------------------
    // Getters / Setters
    // ---------------------------------

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

    /**
     * @return
     * @uml.property name="plugin"
     */
    public static Plugin getPlugin() {
        return plugin;
    }
}
