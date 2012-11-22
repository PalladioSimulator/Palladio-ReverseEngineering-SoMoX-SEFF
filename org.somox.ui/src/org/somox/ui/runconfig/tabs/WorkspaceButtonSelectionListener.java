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
 * This is a helper class that provides a Listener for buttons that 
 * dispay a workspace selection dialog. The dialog can select files, directories
 * or projects and displays the selected element with a relative of absolute path
 * in the given text field.
 * 
 * @author Michael Hauck
 *
 */
public class WorkspaceButtonSelectionListener extends SelectionAdapter {

	private Text field;
	private boolean selectDirectories = false;
	private boolean selectProjects = false;
	private boolean showRelativePath = false;
/**
 * 
 * @param ownerTab the owner: an abstractlaunchconfigurationtab in which the button
 * of the listener is contained
 * @param field the text field which should contain the selected value
 * @param selectDirectories true if only directories are selectable
 * @param selectProjects true if only projects are selectable
 * @param showRelativePath true if the path should be displayed relative to the project
 */
	public WorkspaceButtonSelectionListener(Text field, boolean selectDirectories, boolean selectProjects, boolean showRelativePath) {
		this.field = field;
		this.selectDirectories = selectDirectories;
		this.selectProjects = selectProjects;
		this.showRelativePath = showRelativePath;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
	 * .swt.events.SelectionEvent)
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		String resultOpenFileDialog = "";
		/** create the dialog message */
		if (selectDirectories == true) {
			String msg = "Select a folder.";

			IResource dir = null;
			List<ViewerFilter> filters = new ArrayList<ViewerFilter>();
			if (selectProjects == true) {
				filters.add(new ViewerFilter() {
			        @Override
			        public boolean select(Viewer viewer, Object parentElement, Object element)
			        {
			          if (element instanceof IProject)
			          {
			            return true;
			          }
			          return false;
			        }
			      });
			}
			IContainer[] dirs = WorkspaceResourceDialog.openFolderSelection(
					null, null, msg, false, null, filters);
			try {
				if (dirs.length != 0) {
					dir = dirs[0];
				}
				if (dir != null) {
					if (showRelativePath == true) {
						if (selectProjects == true) {
							resultOpenFileDialog = dir.getFullPath().toOSString().substring(1);
						} else {
							resultOpenFileDialog = dir.getFullPath().toOSString().substring(1);
						}
					} else {
						resultOpenFileDialog = dir.getLocation().toOSString();
					}
				}
			} catch (Exception ce) {
				ce.printStackTrace();
			}
		} else {
			String msg = "Select a file.";
			
			IResource file = null;
			List<ViewerFilter> filters = new ArrayList<ViewerFilter>();
			IFile[] files = WorkspaceResourceDialog.openFileSelection(
					null, null, msg, false, null, filters);
			try {
				if (files.length != 0) {
					file = files[0];
				}
				if (file != null) {
					if (showRelativePath == true) {
						resultOpenFileDialog = file.getFullPath().toOSString().substring(1);
					} else {
						resultOpenFileDialog = file.getLocation().toOSString();
					}
				}
			} catch (Exception ce) {
				ce.printStackTrace();
			}
		}
		if (!resultOpenFileDialog.equals(new String(""))) {
			field.setText(resultOpenFileDialog);
		}
	}
}
