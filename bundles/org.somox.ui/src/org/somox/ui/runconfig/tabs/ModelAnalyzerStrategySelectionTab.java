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
import org.somox.configuration.AbstractMoxConfiguration;

/**
 * The class defines a tab, which is responsible for the SoMoX strategy selection
 *
 * @author Klaus Krogmann
 */
public class ModelAnalyzerStrategySelectionTab extends AbstractLaunchConfigurationTab {

    /**
     * @uml.property name="blackboard"
     * @uml.associationEnd
     */
    private ModelAnalyzerTabGroupBlackboard blackboard = null;

    public void setModelAnalyzerTabGroupBlackboard(final ModelAnalyzerTabGroupBlackboard blackboard) {
        this.blackboard = blackboard;
    }

    @Override
    public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(
                AbstractMoxConfiguration.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES,
                this.reverseEngineerAllInterfaces.getSelection());
        configuration.setAttribute(
                AbstractMoxConfiguration.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERNAL_METHODS_AS_RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR,
                this.reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour.getSelection());
    }

    // Input fields
    Button reverseEngineerAllInterfaces = null;
    Button reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour = null;

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse
     * .swt.widgets.Composite)
     */
    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public void createControl(final Composite parent) {
        final SelectionListener selectionListener = new SelectionListener() {

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
                ModelAnalyzerStrategySelectionTab.this.setDirty(true);
                ModelAnalyzerStrategySelectionTab.this.updateLaunchConfigurationDialog();
            }

            @Override
            public void widgetSelected(final SelectionEvent e) {
                ModelAnalyzerStrategySelectionTab.this.setDirty(true);
                ModelAnalyzerStrategySelectionTab.this.updateLaunchConfigurationDialog();
            }
        };

        final Composite container = new Composite(parent, SWT.NONE);
        this.setControl(container);
        container.setLayout(new GridLayout());

        final Group inputFileTypeGroup = new Group(container, SWT.NONE);
        final GridLayout glInputFileTypeGroup = new GridLayout();
        glInputFileTypeGroup.numColumns = 1;
        inputFileTypeGroup.setLayout(glInputFileTypeGroup);
        inputFileTypeGroup.setText("Select Reverse Engineering Strategies:");
        inputFileTypeGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        this.reverseEngineerAllInterfaces = createAndAddSWTCheckButton(inputFileTypeGroup,
                "Reverse engineer interfaces which are not assigned to components",
                "Reverse engineer interfaces which are not assigned to components. If set to disabled, only interface which are provided or required by compoenents will be reverse engineered.",
                selectionListener, new SelectionListener() {

                    @Override
                    public void widgetSelected(final org.eclipse.swt.events.SelectionEvent e) {
                        ModelAnalyzerStrategySelectionTab.this.updateBlackboard(
                                ModelAnalyzerStrategySelectionTab.this.reverseEngineerAllInterfaces.getSelection());
                    }

                    @Override
                    public void widgetDefaultSelected(final SelectionEvent e) {
                        ModelAnalyzerStrategySelectionTab.this.updateBlackboard(
                                ModelAnalyzerStrategySelectionTab.this.reverseEngineerAllInterfaces.getSelection());
                    };

                });

        this.reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour = createAndAddSWTCheckButton(
                inputFileTypeGroup, "Reverse engineer internal method calls as ResourceDemandingInternalBehaviour",
                "Reverse engineer internal method calls as ResourceDemandingInternalBehaviour. If set to disabled, no ResourceDemandingInternalBehaviour (default) will be created. If enabled ResourceDemandingInternalBehaviour are created, which leads to bigger models. ",
                selectionListener, new SelectionListener() {

                    @Override
                    public void widgetSelected(final org.eclipse.swt.events.SelectionEvent e) {
                        ModelAnalyzerStrategySelectionTab.this.updateBlackboard(
                                ModelAnalyzerStrategySelectionTab.this.reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour
                                        .getSelection());
                    }

                    @Override
                    public void widgetDefaultSelected(final SelectionEvent e) {
                        ModelAnalyzerStrategySelectionTab.this.updateBlackboard(
                                ModelAnalyzerStrategySelectionTab.this.reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour
                                        .getSelection());
                    };

                });

        final Composite composite = new Composite(container, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));
        {
            final GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
            gridData.widthHint = 302;
            composite.setLayoutData(gridData);
        }
    }

    public static Button createAndAddSWTCheckButton(final Composite inputFileTypeGroup, final String label,
            final String toolTip, final SelectionListener... selectionListeners) {
        final Button button = new Button(inputFileTypeGroup, SWT.CHECK);
        button.setText(label);
        button.setToolTipText(toolTip);
        final GridData gd_inputFile = new GridData(SWT.FILL, SWT.CENTER, true, true);
        gd_inputFile.widthHint = 300;
        button.setLayoutData(gd_inputFile);
        for (final SelectionListener selectionListener : selectionListeners) {
            button.addSelectionListener(selectionListener);
        }
        return button;

    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse
     * .debug.core.ILaunchConfiguration)
     */
    @Override
    public void initializeFrom(final ILaunchConfiguration configuration) {
        try {
            this.reverseEngineerAllInterfaces.setSelection(configuration.getAttribute(
                    AbstractMoxConfiguration.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES,
                    false));
            this.reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour
                    .setSelection(configuration.getAttribute(
                            AbstractMoxConfiguration.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERNAL_METHODS_AS_RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR,
                            false));
        } catch (final CoreException e) {
            this.reverseEngineerAllInterfaces.setSelection(false);
            this.reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour.setSelection(false);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse
     * .debug.core.ILaunchConfiguration)
     */
    @Override
    public boolean isValid(final ILaunchConfiguration launchConfig) {
        this.setErrorMessage(null);

        return true;
    }

    @Override
    public String getName() {
        return "Strategy Selection";
    }

    @Override
    public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
    }

    @Override
    public boolean canSave() {
        return true;
    }

    private void updateBlackboard(final boolean inputFile) {
        if (this.blackboard != null) {
            // currently do nothing
        }
    }

}
