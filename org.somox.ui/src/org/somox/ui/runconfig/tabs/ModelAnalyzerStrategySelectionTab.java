package org.somox.ui.runconfig.tabs;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.somox.analyzer.ModelAnalyzerTabGroupBlackboard;
import org.somox.common.SoMoXProjectPreferences;

/**
 * The class defines a tab, which is responsible for the SoMoX strategy selection
 * @author Klaus Krogmann
 */
public class ModelAnalyzerStrategySelectionTab extends AbstractLaunchConfigurationTab {

	/**
	 * @uml.property  name="blackboard"
	 * @uml.associationEnd  
	 */
	private ModelAnalyzerTabGroupBlackboard blackboard = null;

	public void setModelAnalyzerTabGroupBlackboard(
			ModelAnalyzerTabGroupBlackboard blackboard) {
		this.blackboard = blackboard;
	}

	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(SoMoXProjectPreferences.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES, reverseEngineerAllInterfaces.getSelection());
	}

	// Input fields
	Button reverseEngineerAllInterfaces = null;	

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
		final SelectionListener selectionListener = new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				ModelAnalyzerStrategySelectionTab.this.setDirty(true);
				ModelAnalyzerStrategySelectionTab.this.updateLaunchConfigurationDialog();
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				ModelAnalyzerStrategySelectionTab.this.setDirty(true);
				ModelAnalyzerStrategySelectionTab.this.updateLaunchConfigurationDialog();
			}
		};

		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		container.setLayout(new GridLayout());

		Group inputFileTypeGroup = new Group(container, SWT.NONE);
		GridLayout glInputFileTypeGroup = new GridLayout();
		glInputFileTypeGroup.numColumns = 2;
		inputFileTypeGroup.setLayout(glInputFileTypeGroup);
		inputFileTypeGroup.setText("Select Reverse Engineering Strategies:");
		inputFileTypeGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false));

		reverseEngineerAllInterfaces = new Button(inputFileTypeGroup, SWT.CHECK);
		reverseEngineerAllInterfaces.setText("Reverse engineer interfaces which are not assigned to components");
		reverseEngineerAllInterfaces.setToolTipText("Reverse engineer interfaces which are not assigned to components. If set to disabled, only interface which are provided or required by compoenents will be reverse engineered.");
		final GridData gd_inputFile = new GridData(SWT.FILL, SWT.CENTER, true,
				true);
		gd_inputFile.widthHint = 300;
		reverseEngineerAllInterfaces.setLayoutData(gd_inputFile);
		reverseEngineerAllInterfaces.addSelectionListener(selectionListener);
		reverseEngineerAllInterfaces.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				updateBlackboard(reverseEngineerAllInterfaces.getSelection());					
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				updateBlackboard(reverseEngineerAllInterfaces.getSelection());
			};
			
		});

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
			reverseEngineerAllInterfaces.setSelection(
					configuration.getAttribute(SoMoXProjectPreferences.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES, false)
				);
		} catch (CoreException e) {
			reverseEngineerAllInterfaces.setSelection(false);
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

		return true;
	}

	public String getName() {
		return "Strategy Selection";
	}

	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
	}

	@Override
	public boolean canSave() {
		return true;
	}

	private void updateBlackboard(boolean inputFile) {
		if (blackboard != null) {
			// currently do nothing
		}
	}

}
