package org.somox.ui;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

    /** The plug-in ID */
    public static final String PLUGIN_ID = "org.somox.ui";

    /**
     * The shared instance
     *
     * @uml.property name="plugin"
     * @uml.associationEnd
     */
    private static Activator plugin;

    /**
     * The SoMoX Core Controller to work with
     *
     * @uml.property name="guiSoMoXCoreController"
     * @uml.associationEnd
     */
    private GUISoMoXCoreController guiSoMoXCoreController = null;

    /**
     * The constructor
     */
    public Activator() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        // make sure that the core has been accessed and activates once
        org.somox.core.Activator.wakeUp();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path
     *
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(final String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    /**
     * @param guiSoMoXCoreController
     *            the guiSoMoXCoreController to set
     * @uml.property name="guiSoMoXCoreController"
     */
    public void setGuiSoMoXCoreController(final GUISoMoXCoreController guiSoMoXCoreController) {
        this.guiSoMoXCoreController = guiSoMoXCoreController;
    }

    /**
     * @return the guiSoMoXCoreController
     * @uml.property name="guiSoMoXCoreController"
     */
    public GUISoMoXCoreController getGuiSoMoXCoreController() {
        return this.guiSoMoXCoreController;
    }

    /**
     * @return
     * @uml.property name="plugin"
     */
    public static Plugin getPlugin() {
        return plugin;
    }
}
