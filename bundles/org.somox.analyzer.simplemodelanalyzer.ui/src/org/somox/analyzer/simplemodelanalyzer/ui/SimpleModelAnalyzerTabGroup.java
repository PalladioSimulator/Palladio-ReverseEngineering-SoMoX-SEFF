package org.somox.analyzer.simplemodelanalyzer.ui;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.somox.analyzer.ModelAnalyzerTabGroupBlackboard;
import org.somox.metrics.abstractmetrics.AbstractMetric;
import org.somox.metrics.tabs.MetricTab;
import org.somox.metrics.tabs.MetricTabGroup;
import org.somox.ui.runconfig.ModelAnalyzerTabGroup;

import de.uka.ipd.sdq.workflow.launchconfig.tabs.DebugEnabledCommonTab;

/**
 * The main job of a tab group is to specify the tabs that will appear in the
 * LaunchConfigurationDelegate and set their order. These tabs may have been specially written for
 * the particular config type in question, or they may be general purpose tabs that appear for
 * multiple config types.
 *
 * @author Michael Hauck
 */
public class SimpleModelAnalyzerTabGroup extends ModelAnalyzerTabGroup {

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.
     * ILaunchConfigurationDialog, java.lang.String)
     */
    @Override
    public void createTabs(final ILaunchConfigurationDialog dialog, final String mode) {
        final ArrayList<ILaunchConfigurationTab> tabList = this.getCoreAnalyzerTabs();

        // TODO: Introduce a Config Tab extension point here with the latest Palladio Workflow
        // engine
        final MetricTabGroup tab = new MetricTabGroup(this.getModelAnalyzerTabGroupBlackboard(),
                getMetricTabs(this.getModelAnalyzerTabGroupBlackboard()));

        tabList.add(tab);
        tabList.add(new DebugEnabledCommonTab());
        this.setTabs(tabList.toArray(new ILaunchConfigurationTab[0]));
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
                // Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Warning:
                // Specified Analyzer Metric " + metric2.getName() + " cannot be loaded."));
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
