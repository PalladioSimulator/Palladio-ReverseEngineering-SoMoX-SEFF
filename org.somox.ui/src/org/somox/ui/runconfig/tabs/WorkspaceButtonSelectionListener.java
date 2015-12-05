package org.somox.ui.runconfig.tabs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

/**
 *
 * This is a helper class that provides a Listener for buttons that dispay a workspace selection
 * dialog. The dialog can select files, directories or projects and displays the selected element
 * with a relative of absolute path in the given text field.
 *
 * @author Michael Hauck
 *
 */
public class WorkspaceButtonSelectionListener extends SelectionAdapter {

    private final Text field;
    private boolean selectDirectories = false;
    private boolean selectProjects = false;
    private boolean showRelativePath = false;

    /**
     *
     * @param ownerTab
     *            the owner: an abstractlaunchconfigurationtab in which the button of the listener
     *            is contained
     * @param field
     *            the text field which should contain the selected value
     * @param selectDirectories
     *            true if only directories are selectable
     * @param selectProjects
     *            true if only projects are selectable
     * @param showRelativePath
     *            true if the path should be displayed relative to the project
     */
    public WorkspaceButtonSelectionListener(final Text field, final boolean selectDirectories,
            final boolean selectProjects, final boolean showRelativePath) {
        this.field = field;
        this.selectDirectories = selectDirectories;
        this.selectProjects = selectProjects;
        this.showRelativePath = showRelativePath;

    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
     * .swt.events.SelectionEvent)
     */
    @Override
    public void widgetSelected(final SelectionEvent e) {
        String resultOpenFileDialog = "";
        /** create the dialog message */
        if (this.selectDirectories == true) {
            final String msg = "Select a folder.";

            IResource dir = null;
            final List<ViewerFilter> filters = new ArrayList<ViewerFilter>();
            if (this.selectProjects == true) {
                filters.add(new ViewerFilter() {
                    @Override
                    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                        if (element instanceof IProject) {
                            return true;
                        }
                        return false;
                    }
                });
            }
            final IContainer[] dirs = WorkspaceResourceDialog.openFolderSelection(null, null, msg, false, null,
                    filters);
            try {
                if (dirs.length != 0) {
                    dir = dirs[0];
                }
                if (dir != null) {
                    if (this.showRelativePath == true) {
                        if (this.selectProjects == true) {
                            resultOpenFileDialog = dir.getFullPath().toOSString().substring(1);
                        } else {
                            resultOpenFileDialog = dir.getFullPath().toOSString().substring(1);
                        }
                    } else {
                        resultOpenFileDialog = dir.getLocation().toOSString();
                    }
                }
            } catch (final Exception ce) {
                ce.printStackTrace();
            }
        } else {
            final String msg = "Select a file.";

            IResource file = null;
            final List<ViewerFilter> filters = new ArrayList<ViewerFilter>();
            final IFile[] files = WorkspaceResourceDialog.openFileSelection(null, null, msg, false, null, filters);
            try {
                if (files.length != 0) {
                    file = files[0];
                }
                if (file != null) {
                    if (this.showRelativePath == true) {
                        resultOpenFileDialog = file.getFullPath().toOSString().substring(1);
                    } else {
                        resultOpenFileDialog = file.getLocation().toOSString();
                    }
                }
            } catch (final Exception ce) {
                ce.printStackTrace();
            }
        }
        if (!resultOpenFileDialog.equals(new String(""))) {
            this.field.setText(resultOpenFileDialog);
        }
    }
}
