package org.somox.ui.runconfig.tabs;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.somox.analyzer.ModelAnalyzerTabGroupBlackboard;
import org.somox.common.SoMoXProjectPreferences;

/**
 * The class defines a tab, which is responsible for the core input for a model analyzer.
 * @author  Michael Hauck
 */
public class ModelAnalyzerInputTab extends AbstractLaunchConfigurationTab {

	/**
	 * @uml.property  name="blackboard"
	 * @uml.associationEnd  
	 */
	private ModelAnalyzerTabGroupBlackboard blackboard = null;

	// Input fields
	private Text projectName = null;

	public void setModelAnalyzerTabGroupBlackboard(ModelAnalyzerTabGroupBlackboard blackboard) {
		this.blackboard = blackboard;
	}

	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(SoMoXProjectPreferences.SOMOX_PROJECT_NAME, projectName.getText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse
	 * .swt.widgets.Composite)
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	public void createControl(Composite parent) {
		final ModifyListener modifyListener = new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				ModelAnalyzerInputTab.this.setDirty(true);
				ModelAnalyzerInputTab.this.updateLaunchConfigurationDialog();
			}
		};

		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		container.setLayout(new GridLayout());

		Group projectDirectoryTypeGroup = new Group(container, SWT.NONE);
		GridLayout glProjectDirectoryTypeGroup = new GridLayout();
		glProjectDirectoryTypeGroup.numColumns = 2;
		projectDirectoryTypeGroup.setLayout(glProjectDirectoryTypeGroup);
		projectDirectoryTypeGroup.setText("Project:");
		projectDirectoryTypeGroup.setLayoutData(new GridData(SWT.FILL,
				SWT.CENTER, true, false));

		projectName = new Text(projectDirectoryTypeGroup,
				SWT.SINGLE | SWT.BORDER);
		final GridData gd_projectDirectory = new GridData(SWT.FILL, SWT.CENTER,
				true, false);
		gd_projectDirectory.widthHint = 200;
		projectName.setLayoutData(gd_projectDirectory);
		projectName.addModifyListener(modifyListener);

		Button projectworkspaceButton = new Button(projectDirectoryTypeGroup,
				SWT.NONE);
		projectworkspaceButton.setText("Workspace...");
		projectworkspaceButton.addSelectionListener(new WorkspaceButtonSelectionListener(
				projectName, true, true, true));

		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		{
			GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false,
					false, 1, 1);
			gridData.widthHint = 302;
			composite.setLayoutData(gridData);
		}


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse
	 * .debug.core.ILaunchConfiguration)
	 */
	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			projectName.setText(configuration.getAttribute(SoMoXProjectPreferences.SOMOX_PROJECT_NAME, ""));
		} catch (CoreException e) {
			projectName.setText("");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse
	 * .debug.core.ILaunchConfiguration)
	 */
	@Override
	public boolean isValid(ILaunchConfiguration launchConfig) {
		setErrorMessage(null);

		if (projectName.getText().equals("")) {
			setErrorMessage("Project not specified");
			return false;
		}
		if (!ResourcesPlugin.getWorkspace().getRoot().exists(new Path(
				projectName.getText()))) {
			setErrorMessage("Project " + projectName.getText() + " does not exist");
			return false;
		}
		
		return true;
	}

	public String getName() {
		return "General";
	}

	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
	}

	@Override
	public boolean canSave() {
		return true;
	}

	private void updateBlackboard(String inputFile) {
		if (blackboard != null) {

			if (blackboard.getSomoxAnalyzerInputFile() == null) {
				if (inputFile != null) {
					blackboard.setSomoxAnalyzerInputFile(inputFile);
				}
				return;
			}
			if (!blackboard.getSomoxAnalyzerInputFile().equals(inputFile)) {
				blackboard.setSomoxAnalyzerInputFile(inputFile);
			}
		}
	}

}
