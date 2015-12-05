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
 *
 * @author Michael Hauck
 */
public class ModelAnalyzerInputTab extends AbstractLaunchConfigurationTab {

    /**
     * @uml.property name="blackboard"
     * @uml.associationEnd
     */
    private ModelAnalyzerTabGroupBlackboard blackboard = null;

    // Input fields
    private Text projectName = null;

    public void setModelAnalyzerTabGroupBlackboard(final ModelAnalyzerTabGroupBlackboard blackboard) {
        this.blackboard = blackboard;
    }

    @Override
    public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(SoMoXProjectPreferences.SOMOX_PROJECT_NAME, this.projectName.getText());
    }

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
        final ModifyListener modifyListener = new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                ModelAnalyzerInputTab.this.setDirty(true);
                ModelAnalyzerInputTab.this.updateLaunchConfigurationDialog();
            }
        };

        final Composite container = new Composite(parent, SWT.NONE);
        this.setControl(container);
        container.setLayout(new GridLayout());

        final Group projectDirectoryTypeGroup = new Group(container, SWT.NONE);
        final GridLayout glProjectDirectoryTypeGroup = new GridLayout();
        glProjectDirectoryTypeGroup.numColumns = 2;
        projectDirectoryTypeGroup.setLayout(glProjectDirectoryTypeGroup);
        projectDirectoryTypeGroup.setText("Project:");
        projectDirectoryTypeGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        this.projectName = new Text(projectDirectoryTypeGroup, SWT.SINGLE | SWT.BORDER);
        final GridData gd_projectDirectory = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd_projectDirectory.widthHint = 200;
        this.projectName.setLayoutData(gd_projectDirectory);
        this.projectName.addModifyListener(modifyListener);

        final Button projectworkspaceButton = new Button(projectDirectoryTypeGroup, SWT.NONE);
        projectworkspaceButton.setText("Workspace...");
        projectworkspaceButton
                .addSelectionListener(new WorkspaceButtonSelectionListener(this.projectName, true, true, true));

        final Composite composite = new Composite(container, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));
        {
            final GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
            gridData.widthHint = 302;
            composite.setLayoutData(gridData);
        }

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
            this.projectName.setText(configuration.getAttribute(SoMoXProjectPreferences.SOMOX_PROJECT_NAME, ""));
        } catch (final CoreException e) {
            this.projectName.setText("");
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

        if (this.projectName.getText().equals("")) {
            this.setErrorMessage("Project not specified");
            return false;
        }
        if (!ResourcesPlugin.getWorkspace().getRoot().exists(new Path(this.projectName.getText()))) {
            this.setErrorMessage("Project " + this.projectName.getText() + " does not exist");
            return false;
        }

        return true;
    }

    @Override
    public String getName() {
        return "General";
    }

    @Override
    public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
    }

    @Override
    public boolean canSave() {
        return true;
    }

    private void updateBlackboard(final String inputFile) {
        if (this.blackboard != null) {

            if (this.blackboard.getSomoxAnalyzerInputFile() == null) {
                if (inputFile != null) {
                    this.blackboard.setSomoxAnalyzerInputFile(inputFile);
                }
                return;
            }
            if (!this.blackboard.getSomoxAnalyzerInputFile().equals(inputFile)) {
                this.blackboard.setSomoxAnalyzerInputFile(inputFile);
            }
        }
    }

}
