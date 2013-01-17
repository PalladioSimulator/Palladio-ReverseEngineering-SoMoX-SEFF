package org.somox.metrics.tabs;

import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.somox.kdmhelper.metamodeladdition.Root;

//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;

public class CheckboxLabelProvider implements ILabelProvider {

	public Image getImage(Object element) {
		return null;
	}

	public String getText(Object element) {
		if (element instanceof Root) {
			return ("Root");
		} else if (element instanceof org.eclipse.gmt.modisco.java.Package) {
			return ((org.eclipse.gmt.modisco.java.Package) element).getName();
		} else if (element instanceof Type) {
			return ((Type) element).getName();
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
