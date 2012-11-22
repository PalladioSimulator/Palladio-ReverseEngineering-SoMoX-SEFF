package org.somox.metrics.tabs;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import de.fzi.gast.core.Root;
import de.fzi.gast.types.GASTClass;

public class CheckboxLabelProvider implements ILabelProvider {

	public Image getImage(Object element) {
		return null;
	}

	public String getText(Object element) {
		if (element instanceof Root) {
			return ("Root");
		} else if (element instanceof de.fzi.gast.core.Package) {
			return ((de.fzi.gast.core.Package) element).getSimpleName();
		} else if (element instanceof GASTClass) {
			return ((GASTClass) element).getSimpleName();
		}
		return null;
	}

	public void addListener(ILabelProviderListener listener) {
		
	}

	public void dispose() {
		
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
	
	}

}
