package org.somox.ui.preferences;

import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.somox.configuration.ConfigurationDefinition;
import org.somox.ui.Activator;
import org.somox.ui.SoMoXUILogger;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. By
 * subclassing <samp>FieldEditorPreferencePage</samp>, we can use the field support built into JFace
 * that allows us to create a page that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that
 * belongs to the main plug-in class. That way, preferences can be accessed directly via the
 * preference store.
 */

public class SoMoXPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public SoMoXPreferencePage() {
        super(GRID);
        this.setPreferenceStore(Activator.getDefault().getPreferenceStore());
        this.setDescription("SoMoX Preference Page");
    }

    /**
     * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to
     * manipulate various types of preferences. Each field editor knows how to save and restore
     * itself.
     */
    @Override
    public void createFieldEditors() {

        final LinkedList<ConfigurationDefinition> configs = Activator.getDefault().getGuiSoMoXCoreController()
                .getGlobalConfigurationDefinitions();

        SoMoXUILogger.logInfo("Number of configs to be build: " + configs.size());

        final Iterator<ConfigurationDefinition> configIterator = configs.iterator();
        while (configIterator.hasNext()) {
            final ConfigurationDefinition config = configIterator.next();

            if (ConfigurationDefinition.Type.DIRECTORY.equals(config.getType())) {
                this.addField(new DirectoryFieldEditor(config.getId(), config.getName(), this.getFieldEditorParent()));

            } else if (ConfigurationDefinition.Type.STRING.equals(config.getType())) {
                final StringFieldEditor editor = new StringFieldEditor(config.getId(), config.getName(),
                        this.getFieldEditorParent());
                if (config.getDefaultValue() != null) {
                    this.getPreferenceStore().setDefault(config.getId(), config.getDefaultValue());
                }
                this.addField(editor);

            } else if (ConfigurationDefinition.Type.BOOLEAN.equals(config.getType())) {
                this.addField(new BooleanFieldEditor(config.getId(), config.getName(), this.getFieldEditorParent()));
            }
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    @Override
    public void init(final IWorkbench workbench) {
    }

}