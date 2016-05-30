package org.somox.ui.runconfig.tabs;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.somox.configuration.AbstractMoxConfiguration;
import org.somox.configuration.SoMoXConfiguration;

/**
 * The class defines a tab, which is responsible for the core input for a model analyzer.
 *
 * @author Michael Hauck
 * @author Joshua Gleitze
 */
public class ModelAnalyzerInputTab extends AbstractLaunchConfigurationTab {

    /**
     * The GUI element the user can selecet projects in.
     */
    private ProjectSelector projectSelector;

    @Override
    public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
        final Set<String> selectedProjects = getSelectedProjects()
                .map(IProject::getName)
                .collect(Collectors.toSet());
        configuration.setAttribute(AbstractMoxConfiguration.SOMOX_PROJECT_NAME, selectedProjects);
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
        final Composite container = new Composite(parent, SWT.NONE);
        final GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        container.setLayout(layout);

        final Label projectsLabel = new Label(container, SWT.NONE);
        projectsLabel.setText("Projects to analyse:");

        projectSelector = new ProjectSelector(container, SWT.BORDER);
        projectSelector.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        projectSelector.treeViewer.addSelectionChangedListener(event -> {
            this.setDirty(true);
            this.updateLaunchConfigurationDialog();
        });
        this.setControl(container);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse
     * .debug.core.ILaunchConfiguration)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void initializeFrom(final ILaunchConfiguration configuration) {
        Object inputProject;
        final Set<String> selectedProjectNames;
        try {
            inputProject = configuration.getAttributes().get(SoMoXConfiguration.SOMOX_PROJECT_NAME);
        } catch (CoreException e) {
            inputProject = new String[0];
        }

        /*
         * Backward-compatibility: old versions of SoMoX used single input projects. These
         * configurations should still be supported.
         */
        if (inputProject instanceof String) {
            selectedProjectNames = new HashSet<>(Arrays.asList((String) inputProject));
        } else if (inputProject instanceof Set) {
            selectedProjectNames = (Set<String>) inputProject;
        } else {
            selectedProjectNames = Collections.EMPTY_SET;
        }

        final IProject[] selectedProjects = selectedProjectNames.stream()
                .map(ResourcesPlugin.getWorkspace().getRoot()::getProject)
                .filter(IProject::exists)
                .toArray(IProject[]::new);
        
        final int difference = selectedProjectNames.size() - selectedProjects.length; 
        if (difference != 0) {
            // There was a project in the configuration that does not exist in this workspace.
            this.setMessage(difference + " project" + (difference == 1 ? "" : "s") 
                    + " could not be found and were removed from the configuration");
            this.setDirty(true);
        }
        this.projectSelector.treeViewer.setCheckedElements(selectedProjects);
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

        if (!this.getSelectedProjects().findAny().isPresent()) {
            this.setErrorMessage("No project selected.");
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
    
    /**
     * Queries the projects selected in the the {@link #projectSelector}.
     * 
     * @return A stream to the selected projects.
     */
    private Stream<IProject> getSelectedProjects() {
        return Arrays.stream(projectSelector.treeViewer.getCheckedElements())
                .map(IProject.class::cast);
    }

    /**
     * A control with a filter text field letting the user select the projects to be analysed on the
     * {@link ModelAnalyzerInputTab}.
     * 
     * @author Joshua Gleitze
     */
    private class ProjectSelector extends FilteredTree {

        private CheckboxTreeViewer treeViewer;

        /**
         * Creates this component.
         * 
         * @param parent
         *            the parent Composite
         * @param treeStyle
         *            the style bits for the Tree
         */
        public ProjectSelector(Composite parent, int treeStyle) {
            super(parent, treeStyle, new PatternFilter(), true);
        }

        @Override
        protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
            treeViewer = new CheckboxTreeViewer(parent, style);
            treeViewer.setContentProvider(new ProjectContentProvider());
            treeViewer.setLabelProvider(new WorkbenchLabelProvider());
            treeViewer.setInput(ResourcesPlugin.getWorkspace().getRoot().getProjects());
            return treeViewer;
        }

        private class ProjectContentProvider implements ITreeContentProvider {

            private IProject[] projects;

            @Override
            public void dispose() {
            }

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                this.projects = (IProject[]) newInput;
            }

            @Override
            public Object[] getElements(Object inputElement) {
                return this.projects;
            }

            @Override
            public Object[] getChildren(Object parentElement) {
                return new Object[0];
            }

            @Override
            public Object getParent(Object element) {
                return null;
            }

            @Override
            public boolean hasChildren(Object element) {
                return false;
            }
        }

    }

}
