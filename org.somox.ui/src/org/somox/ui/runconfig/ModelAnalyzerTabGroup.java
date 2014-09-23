package org.somox.ui.runconfig;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
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
 * The main job of a tab group is to specify the tabs that will appear in the LaunchConfigurationDelegate and set their order. These tabs may have been specially written for the particular config type in question, or they may be general purpose tabs that appear for multiple config types.
 * @author  Michael Hauck
 */
public class ModelAnalyzerTabGroup extends AbstractLaunchConfigurationTabGroup {
	
	/**
	 * @uml.property  name="modelAnalyzerTabGroupBlackboard"
	 * @uml.associationEnd  
	 */
	private ModelAnalyzerTabGroupBlackboard modelAnalyzerTabGroupBlackboard = null;
	
	public ModelAnalyzerTabGroup() {
		modelAnalyzerTabGroupBlackboard = new ModelAnalyzerTabGroupBlackboard(); 
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog, java.lang.String)
	 */
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		//IConfigurationElement[] analyzers = Platform.getExtensionRegistry().getConfigurationElementsFor("org.somox.core.modelanalyzer");
		/*ArrayList<ILaunchConfigurationTab> tabList = new ArrayList<ILaunchConfigurationTab>();
		for (int i=0; i<analyzers.length; i++) {
				Object o = null;
			try {
				o = analyzers[i].createExecutableExtension("class");
			} catch (CoreException e) {
				SoMoXCoreLogger.logInfo("Warning: Specified Analyzer " + analyzers[i].getName() + " cannot be loaded.");
			}
			if (o == null) {
				continue;
			}
			if (o instanceof ModelAnalyzer) {
				ModelAnalyzer analyzer = (ModelAnalyzer)o;
				tabList.add(new ModelAnalyzerInputTab(analyzer));
				
			}
		}*/
		
		ArrayList<ILaunchConfigurationTab> tabList = getCoreAnalyzerTabs();
		//tabList.addAll(getMetricTabs());
		setTabs(tabList.toArray(new ILaunchConfigurationTab[0]));
	}
	
	
	
	protected ArrayList<ILaunchConfigurationTab> getCoreAnalyzerTabs() {
		ArrayList<ILaunchConfigurationTab> tabList = new ArrayList<ILaunchConfigurationTab>();
		ModelAnalyzerInputTab modelAnalyzerInputTab = new ModelAnalyzerInputTab();
		modelAnalyzerInputTab.setModelAnalyzerTabGroupBlackboard(modelAnalyzerTabGroupBlackboard);
		tabList.add(modelAnalyzerInputTab);
		
		ModelAnalyzerStrategySelectionTab strategySelectionTab = new ModelAnalyzerStrategySelectionTab();
		strategySelectionTab.setModelAnalyzerTabGroupBlackboard(modelAnalyzerTabGroupBlackboard);
		tabList.add(strategySelectionTab);
		
		return tabList;		
	}
	
	/**
	 * @return
	 * @uml.property  name="modelAnalyzerTabGroupBlackboard"
	 */
	protected ModelAnalyzerTabGroupBlackboard getModelAnalyzerTabGroupBlackboard() {
		return modelAnalyzerTabGroupBlackboard;
	}
	
	public static ArrayList<MetricTab> getMetricTabs(ModelAnalyzerTabGroupBlackboard modelAnalyzerTabGroupBlackboard) {
		ArrayList<MetricTab> tabList = new ArrayList<MetricTab>();
		IConfigurationElement[] metrics = Platform.getExtensionRegistry().getConfigurationElementsFor("org.somox.core.metric");
		for (int i=0; i<metrics.length; i++) {
			Object o = null;
			try {
				o = metrics[i].createExecutableExtension("class");
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(
						new Status(Status.ERROR, Activator.PLUGIN_ID, 
						"Warning: Specified Analyzer Metric " + metrics[i].getName() + " cannot be loaded."));
			}
			if (o == null) {
				continue;
			}
			if (o instanceof AbstractMetric) {
				AbstractMetric metric = (AbstractMetric)o;
				MetricTab metricTab = metric.getLaunchConfigurationTab();
				if(metricTab != null) {					
					metricTab.setModelAnalyzerTabGroupBlackboard(modelAnalyzerTabGroupBlackboard);
					tabList.add(metricTab);
				}
			}
		}
		return tabList;		
	}
	
}
