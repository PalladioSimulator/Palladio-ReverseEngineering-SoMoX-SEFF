package org.somox.metrics.tabs;

import java.util.ArrayList;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.somox.analyzer.ModelAnalyzerTabGroupBlackboard;

/**
 * This class provides an AbstractLaunchConfigurationTab that holds the MetricTabs
 * as sub tabs.
 * 
 * @author Michael Hauck
 *
 */
public class MetricTabGroup extends AbstractLaunchConfigurationTab {
	private ArrayList<MetricTab> metricTabs = null;
	
	public MetricTabGroup(ModelAnalyzerTabGroupBlackboard modelAnalyzerTabGroupBlackboard, ArrayList<MetricTab> metricTabs) {
		this.metricTabs = metricTabs;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new org.eclipse.swt.layout.GridLayout(1, false));
		
		TabFolder tabs = new TabFolder(composite, SWT.NONE);
		for (int i=0; i<metricTabs.size(); i++) {
			TabItem tabItem = new TabItem(tabs, SWT.NONE);
			metricTabs.get(i).createControl(tabs);
			metricTabs.get(i).setParentLaunchConfigurationTab(this);
			tabItem.setControl(metricTabs.get(i).getControl());
			tabItem.setText(metricTabs.get(i).getName());
		}
		tabs.pack();
		tabs.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		setControl(composite);
	}

	@Override
	public String getName() {
		return "SoMoX Configuration";
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		for (ILaunchConfigurationTab tab : metricTabs) {
			tab.initializeFrom(configuration);
		}
	}

	@Override
	public void performApply(
			ILaunchConfigurationWorkingCopy configuration) {
		for (ILaunchConfigurationTab tab : metricTabs) {
			tab.performApply(configuration);
		}
	}
	
	@Override
	public void setDefaults(
			ILaunchConfigurationWorkingCopy configuration) {
		for (ILaunchConfigurationTab tab : metricTabs) {
			tab.setDefaults(configuration);
		}	
	}
	
	public void updateLaunchConfigurationDialogFromChild() {
		updateLaunchConfigurationDialog();
	}
	
	public void setDirtyFromChild(boolean dirty) {
		setDirty(dirty);
	}
	
	public boolean isValid(ILaunchConfiguration launchConfig) {
		return true;
	}
	
	public boolean canSave() {
		return true;
	}
	

	
}