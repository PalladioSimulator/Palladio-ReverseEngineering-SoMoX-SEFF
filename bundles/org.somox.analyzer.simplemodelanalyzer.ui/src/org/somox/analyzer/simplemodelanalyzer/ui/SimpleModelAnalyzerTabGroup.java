package org.somox.analyzer.simplemodelanalyzer.ui;

import java.util.ArrayList;

import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
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

}
