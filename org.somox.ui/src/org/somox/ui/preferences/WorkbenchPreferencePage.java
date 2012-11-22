package org.somox.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class WorkbenchPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	// ---------------------------------
	// Static Data Fields
	// ---------------------------------

	// ---------------------------------
	// Data fields
	// ---------------------------------

	// ---------------------------------
	// Constructor
	// ---------------------------------

	public WorkbenchPreferencePage() {
	}

	public WorkbenchPreferencePage(String title) {
		super(title);
	}

	public WorkbenchPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	// ---------------------------------
	// Business Methods
	// ---------------------------------

	protected Control createContents(Composite parent) {
		return null;
	}

	public void init(IWorkbench workbench) {
	}

	// ---------------------------------
	// Helper Methods
	// ---------------------------------

	// ---------------------------------
	// Getters / Setters
	// ---------------------------------
}
