package org.somox.ui.runconfig;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.somox.analyzer.ModelAnalyzerTabGroupBlackboard;
import org.somox.metrics.abstractmetrics.AbstractMetric;
import org.somox.metrics.tabs.MetricTab;
import org.somox.ui.Activator;
import org.somox.ui.runconfig.tabs.ModelAnalyzerInputTab;
import org.somox.ui.runconfig.tabs.ModelAnalyzerStrategySelectionTab;

/**
 * The main job of a tab group is to specify the tabs that will appear in the
 * LaunchConfigurationDelegate and set their order. These tabs may have been specially written for
 * the particular config type in question, or they may be general purpose tabs that appear for
 * multiple config types.
 *
 * @author Michael Hauck
 */
public class ModelAnalyzerTabGroup extends AbstractLaunchConfigurationTabGroup {

    /**
     * @uml.property name="modelAnalyzerTabGroupBlackboard"
     * @uml.associationEnd
     */
    private ModelAnalyzerTabGroupBlackboard modelAnalyzerTabGroupBlackboard = null;

    public ModelAnalyzerTabGroup() {
        this.modelAnalyzerTabGroupBlackboard = new ModelAnalyzerTabGroupBlackboard();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.
     * ILaunchConfigurationDialog, java.lang.String)
     */
    @Override
    public void createTabs(final ILaunchConfigurationDialog dialog, final String mode) {
        // IConfigurationElement[] analyzers =
        // Platform.getExtensionRegistry().getConfigurationElementsFor("org.somox.core.modelanalyzer");
        /*
         * ArrayList<ILaunchConfigurationTab> tabList = new ArrayList<ILaunchConfigurationTab>();
         * for (int i=0; i<analyzers.length; i++) { Object o = null; try { o =
         * analyzers[i].createExecutableExtension("class"); } catch (CoreException e) {
         * SoMoXCoreLogger.logInfo("Warning: Specified Analyzer " + analyzers[i].getName() +
         * " cannot be loaded."); } if (o == null) { continue; } if (o instanceof ModelAnalyzer) {
         * ModelAnalyzer analyzer = (ModelAnalyzer)o; tabList.add(new
         * ModelAnalyzerInputTab(analyzer));
         *
         * } }
         */

        final ArrayList<ILaunchConfigurationTab> tabList = this.getCoreAnalyzerTabs();
        // tabList.addAll(getMetricTabs());
        this.setTabs(tabList.toArray(new ILaunchConfigurationTab[0]));
    }

    protected ArrayList<ILaunchConfigurationTab> getCoreAnalyzerTabs() {
        final ArrayList<ILaunchConfigurationTab> tabList = new ArrayList<ILaunchConfigurationTab>();
        final ModelAnalyzerInputTab modelAnalyzerInputTab = new ModelAnalyzerInputTab();
        modelAnalyzerInputTab.setModelAnalyzerTabGroupBlackboard(this.modelAnalyzerTabGroupBlackboard);
        tabList.add(modelAnalyzerInputTab);

        final ModelAnalyzerStrategySelectionTab strategySelectionTab = new ModelAnalyzerStrategySelectionTab();
        strategySelectionTab.setModelAnalyzerTabGroupBlackboard(this.modelAnalyzerTabGroupBlackboard);
        tabList.add(strategySelectionTab);

        return tabList;
    }

    /**
     * @return
     * @uml.property name="modelAnalyzerTabGroupBlackboard"
     */
    protected ModelAnalyzerTabGroupBlackboard getModelAnalyzerTabGroupBlackboard() {
        return this.modelAnalyzerTabGroupBlackboard;
    }

    public static ArrayList<MetricTab> getMetricTabs(
            final ModelAnalyzerTabGroupBlackboard modelAnalyzerTabGroupBlackboard) {
        final ArrayList<MetricTab> tabList = new ArrayList<MetricTab>();
        final IConfigurationElement[] metrics = Platform.getExtensionRegistry()
                .getConfigurationElementsFor("org.somox.core.metric");
        for (final IConfigurationElement metric2 : metrics) {
            Object o = null;
            try {
                o = metric2.createExecutableExtension("class");
            } catch (final CoreException e) {
                Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                        "Warning: Specified Analyzer Metric " + metric2.getName() + " cannot be loaded."));
            }
            if (o == null) {
                continue;
            }
            if (o instanceof AbstractMetric) {
                final AbstractMetric metric = (AbstractMetric) o;
                final MetricTab metricTab = metric.getLaunchConfigurationTab();
                if (metricTab != null) {
                    metricTab.setModelAnalyzerTabGroupBlackboard(modelAnalyzerTabGroupBlackboard);
                    tabList.add(metricTab);
                }
            }
        }
        return tabList;
    }

}
