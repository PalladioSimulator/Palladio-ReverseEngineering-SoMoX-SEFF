package org.annotationsmox.ui;

import java.util.ArrayList;
import java.util.List;

import org.annotationsmox.workflow.AnnotationsMoxWorkflowHooks;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.somox.ui.runconfig.ModelAnalyzerTabGroup;

import de.uka.ipd.sdq.workflow.launchconfig.extension.ExtendableTabGroup;
import de.uka.ipd.sdq.workflow.launchconfig.tabs.DebugEnabledCommonTab;

/**
 * This class represents the Tab group for the launch configuration
 * 
 * @author langhamm
 *
 */
public class AnnotationsMoxModelAnalyzerTabGroup extends ModelAnalyzerTabGroup {

    @Override
    public void createTabs(final ILaunchConfigurationDialog dialog, final String mode) {
        final ArrayList<ILaunchConfigurationTab> tabList = this.getCoreAnalyzerTabs();

        WorkflowExtensionsTabGroup workflowTabGroup = new WorkflowExtensionsTabGroup();
        List<ILaunchConfigurationTab> tabs = workflowTabGroup.createExtensionTabs(dialog, mode,
                AnnotationsMoxWorkflowHooks.POST_SAVE_MODELS);
        tabList.addAll(tabs);

        tabList.add(new DebugEnabledCommonTab());
        this.setTabs(tabList.toArray(new ILaunchConfigurationTab[0]));
    }

    private class WorkflowExtensionsTabGroup extends ExtendableTabGroup {

        @Override
        public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
            // nothing to do
        }

    }

}
