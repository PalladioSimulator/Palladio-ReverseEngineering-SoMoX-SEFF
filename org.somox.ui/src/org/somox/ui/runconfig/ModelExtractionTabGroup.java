package org.somox.ui.runconfig;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.somox.ui.runconfig.tabs.ModelExtractionInputTab;

/**
 * The main job of a tab group is to specify the tabs that will appear in the
 * LaunchConfigurationDelegate and set their order. These tabs may have been
 * specially written for the particular config type in question, or they may be
 * general purpose tabs that appear for multiple config types.
 * 
 * @author Michael Hauck
 */
public class ModelExtractionTabGroup extends AbstractLaunchConfigurationTabGroup {

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog, java.lang.String)
	 */
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
				new ModelExtractionInputTab(), new CommonTab()};
		setTabs(tabs);
	}
}
