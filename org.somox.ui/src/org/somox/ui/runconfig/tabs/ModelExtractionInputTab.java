package org.somox.ui.runconfig.tabs;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.somox.configuration.AbstractMoxConfiguration;
import org.somox.configuration.ConfigurationDefinition;
import org.somox.ui.Activator;
import org.somox.ui.SoMoXUILogger;

/**
 * The class defines a tab, which is responsible for the input for a model extraction. This class
 * builds the tab dynamically dependent on the specified configuration elements for the extractor.
 * However, this is not really needed. Can be refactored in future to build a static tab.
 *
 * @author Michael Hauck
 */
public class ModelExtractionInputTab extends AbstractLaunchConfigurationTab {

    Text projectName = null;

    @Override
    public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
        final Iterator<Map.Entry<String, Text>> strintIt = this.stringAttributes.entrySet().iterator();
        while (strintIt.hasNext()) {
            final Map.Entry<String, Text> tmpEntry = strintIt.next();
            SoMoXUILogger.logInfo("Extraction Setting: " + tmpEntry.getKey());
            configuration.setAttribute(tmpEntry.getKey(), tmpEntry.getValue().getText());
        }
        final Iterator<Map.Entry<String, Button>> booleanIt = this.booleanAttributes.entrySet().iterator();
        while (booleanIt.hasNext()) {
            final Map.Entry<String, Button> tmpEntry = booleanIt.next();
            SoMoXUILogger.logInfo("Extraction Setting: " + tmpEntry.getKey());
            configuration.setAttribute(tmpEntry.getKey(), tmpEntry.getValue().getSelection());
        }

    }

    private final HashMap<String, Text> stringAttributes = new HashMap<String, Text>();
    private final HashMap<String, Button> booleanAttributes = new HashMap<String, Button>();

    // The configuration definitions
    private HashMap<String, ConfigurationDefinition> getConfigurationDefinitions() {
        HashMap<String, ConfigurationDefinition> configurationDefinitions = null;
        if (configurationDefinitions == null) {
            configurationDefinitions = new HashMap<String, ConfigurationDefinition>();
            final LinkedList<ConfigurationDefinition> configs = Activator.getDefault().getGuiSoMoXCoreController()
                    .getConfigurationDefinitions();
            final Iterator<ConfigurationDefinition> configIterator = configs.iterator();
            while (configIterator.hasNext()) {
                final ConfigurationDefinition config = configIterator.next();
                configurationDefinitions.put(config.getId(), config);
            }
        }
        return configurationDefinitions;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse
     * .swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final ModifyListener modifyListener = new ModifyListener() {
            @Override
            public void modifyText(final ModifyEvent e) {
                ModelExtractionInputTab.this.setDirty(true);
                ModelExtractionInputTab.this.updateLaunchConfigurationDialog();
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
        this.stringAttributes.put(AbstractMoxConfiguration.SOMOX_PROJECT_NAME, this.projectName);

        final LinkedList<ConfigurationDefinition> configs = Activator.getDefault().getGuiSoMoXCoreController()
                .getConfigurationDefinitions();
        SoMoXUILogger.logInfo("Number of configs to be build: " + configs.size());
        final Set<String> configurationDefinitionKeys = this.getConfigurationDefinitions().keySet();
        final Iterator<String> configIterator = configurationDefinitionKeys.iterator();
        final Group typeGroup = new Group(container, SWT.NONE);
        final GridLayout glTypeGroup = new GridLayout();
        glTypeGroup.numColumns = 4;
        typeGroup.setLayout(glTypeGroup);
        typeGroup.setText("SISSy configuration");
        typeGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        while (configIterator.hasNext()) {
            final String key = configIterator.next();
            final ConfigurationDefinition config = this.getConfigurationDefinitions().get(key);

            if (ConfigurationDefinition.Type.DIRECTORY.equals(config.getType())) {

                final Text textDirectory = new Text(typeGroup, SWT.NONE | SWT.SINGLE | SWT.BORDER);
                final GridData gd_textResourceTypeRepository = new GridData(SWT.FILL, SWT.CENTER, true, false);
                gd_textResourceTypeRepository.horizontalSpan = 2;
                textDirectory.setLayoutData(gd_textResourceTypeRepository);
                textDirectory.addModifyListener(modifyListener);

                final Button workspaceButton = new Button(typeGroup, SWT.NONE);
                workspaceButton.setText("Workspace...");
                workspaceButton
                        .addSelectionListener(new WorkspaceButtonSelectionListener(textDirectory, true, false, false));

                final Button buttonResourceTypeRepository = new Button(typeGroup, SWT.NONE);
                buttonResourceTypeRepository.setLayoutData(new GridData());
                buttonResourceTypeRepository.setText("File System...");
                buttonResourceTypeRepository.addSelectionListener(new SelectionAdapter() {

                    /*
                     * (non-Javadoc)
                     *
                     * @seeorg.eclipse.swt.events.SelectionAdapter# widgetSelected
                     * (org.eclipse.swt.events.SelectionEvent)
                     */
                    @Override
                    public void widgetSelected(final SelectionEvent e) {
                        String resultOpenFileDialog = "";

                        final DirectoryDialog dialog = new DirectoryDialog(ModelExtractionInputTab.this.getShell(),
                                SWT.OPEN);
                        dialog.setText("Select a folder.");

                        if (dialog.open() != null) {
                            resultOpenFileDialog = dialog.getFilterPath();
                        }

                        if (!resultOpenFileDialog.equals(new String(""))) {
                            textDirectory.setText(resultOpenFileDialog);
                        }
                    }
                });
                textDirectory.setText(config.getDefaultValue());
                this.stringAttributes.put(config.getId(), textDirectory);

            } else if (ConfigurationDefinition.Type.STRING.equals(config.getType())) {

                final Label stringLabel = new Label(typeGroup, SWT.NONE);
                stringLabel.setText(config.getName());
                final Text stringField = new Text(typeGroup, SWT.BORDER);
                final GridData gd_stringTypeGroup = new GridData(SWT.FILL, SWT.CENTER, true, false);
                gd_stringTypeGroup.horizontalSpan = 3;
                stringField.setLayoutData(gd_stringTypeGroup);
                stringField.addModifyListener(modifyListener);
                stringField.setText(config.getDefaultValue());
                this.stringAttributes.put(config.getId(), stringField);

            } else if (ConfigurationDefinition.Type.BOOLEAN.equals(config.getType())) {

                final Button booleanButton = new Button(typeGroup, SWT.CHECK);
                booleanButton.setText(config.getName());
                final GridData gd_booleanTypeGroup = new GridData(SWT.NONE, SWT.CENTER, true, false);
                booleanButton.setLayoutData(gd_booleanTypeGroup);
                booleanButton.addSelectionListener(new SelectionListener() {

                    @Override
                    public void widgetDefaultSelected(final SelectionEvent e) {
                        ModelExtractionInputTab.this.setDirty(true);
                        ModelExtractionInputTab.this.updateLaunchConfigurationDialog();
                    }

                    @Override
                    public void widgetSelected(final SelectionEvent e) {
                        ModelExtractionInputTab.this.setDirty(true);
                        ModelExtractionInputTab.this.updateLaunchConfigurationDialog();
                    }

                });
                gd_booleanTypeGroup.horizontalSpan = 4;
                if ((config.getDefaultValue() != null) && (config.getDefaultValue().equals("true"))) {
                    booleanButton.setEnabled(true);
                }
                this.booleanAttributes.put(config.getId(), booleanButton);
            }
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
            final Map attributes = configuration.getAttributes();
            final Set<String> keys = attributes.keySet();
            final Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                final String key = it.next();
                if (this.stringAttributes.containsKey(key)) {
                    this.stringAttributes.get(key).setText(attributes.get(key).toString());
                    continue;
                }
                if (this.booleanAttributes.containsKey(key)) {
                    if (attributes.get(key).toString().toLowerCase().equals("true")) {
                        this.booleanAttributes.get(key).setSelection(true);
                    }
                    continue;
                }

            }
        } catch (final CoreException e) {
            SoMoXUILogger.logError(this.getName(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse.debug.core.
     * ILaunchConfiguration)
     */
    @Override
    public boolean isValid(final ILaunchConfiguration launchConfig) {
        this.setErrorMessage(null);

        if (this.projectName.getText().equals("")) {
            this.setErrorMessage("Project not specified");
            return false;
        }
        final File projectDir = new File(
                Platform.getInstanceLocation().getURL().getFile() + this.projectName.getText());
        if (!projectDir.exists()) {
            this.setErrorMessage("Project " + this.projectName.getText() + " does not exist");
            return false;
        }

        // Check all dynamic configurations that are required
        final Set<String> keys = this.getConfigurationDefinitions().keySet();
        final Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            final String key = it.next();
            final ConfigurationDefinition config = this.getConfigurationDefinitions().get(key);
            if ((config.isRequired()) && ((config.getType().equals(ConfigurationDefinition.Type.DIRECTORY))
                    || (config.getType().equals(ConfigurationDefinition.Type.STRING)))) {
                if (this.stringAttributes.get(key).getText().equals("")) {
                    this.setErrorMessage("Field " + config.getName() + " must not be empty");
                    return false;
                }
            }

        }

        return true;
    }

    @Override
    public String getName() {
        return "Model Extraction";
    }

    @Override
    public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {

        // Set defaults for all dynamic configurations
        final Set<String> keys = this.getConfigurationDefinitions().keySet();
        final Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            final String key = it.next();
            final ConfigurationDefinition config = this.getConfigurationDefinitions().get(key);
            if ((config.getType().equals(ConfigurationDefinition.Type.DIRECTORY))
                    || (config.getType().equals(ConfigurationDefinition.Type.STRING))) {
                configuration.setAttribute(config.getId(), config.getDefaultValue());
            } else if (config.getType().equals(ConfigurationDefinition.Type.BOOLEAN)) {
                configuration.setAttribute(config.getId(), config.getDefaultValue());
            }

        }

    }

    @Override
    public boolean canSave() {
        return true;
    }

}
