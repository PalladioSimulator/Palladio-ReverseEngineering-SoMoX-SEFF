package org.somox.metrics.tabs;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.fzi.gast.core.Root;
import de.fzi.gast.types.GASTClass;

public class CheckboxContentProvider implements ITreeContentProvider {

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Root) {
			return ((Root) parentElement).getPackages().toArray();
		} else if (parentElement instanceof de.fzi.gast.core.Package) {
			Object [] packages = ((de.fzi.gast.core.Package) parentElement).getSubPackages().toArray();
			Object [] classes = ((de.fzi.gast.core.Package) parentElement).getClasses().toArray();
			
			Object [] elements = new Object [packages.length + classes.length];
			System.arraycopy(packages, 0, elements, 0, packages.length);
			System.arraycopy(classes, 0, elements, packages.length, classes.length);
			return elements;
		}
		return null;
	}

	public Object getParent(Object element) {
		if (element instanceof GASTClass) {
			return ((GASTClass) element).getSurroundingPackage();
		} else if (element instanceof de.fzi.gast.core.Package) {
			return ((de.fzi.gast.core.Package)element).getSurroundingPackage();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof Root) {
			//return ((Root) element).getPackages().size()>0;
			return true;
		} else if (element instanceof de.fzi.gast.core.Package) {			
			return (((de.fzi.gast.core.Package) element).getSubPackages().size()
					+((de.fzi.gast.core.Package) element).getClasses().size()) > 0;
		}
		return false;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}