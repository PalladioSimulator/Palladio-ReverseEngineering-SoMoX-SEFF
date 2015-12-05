package org.somox.analyzer.simplemodelanalyzer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.somox.analyzer.simplemodelanalyzer";

    // The shared instance
    /**
     * @uml.property name="plugin"
     * @uml.associationEnd
     */
    private static Activator plugin;

    /**
     * The constructor
     */
    public Activator() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        this.getLog().log(new Status(IStatus.INFO, Activator.PLUGIN_ID, "Analyzer Activator called"));

        // debug:
        // Logger rootLogger = Logger.getRootLogger();
        // rootLogger.addAppender(new ConsoleAppender(
        // new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN)));
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
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

}
