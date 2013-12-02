package org.somox.metrics.tabs;

import org.emftext.language.java.*;
import org.emftext.language.java.types.Type;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;

//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;

public class CheckboxContentProvider implements ITreeContentProvider {

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Root) {
			return ((Root) parentElement).getPackages().toArray();
		} else if (parentElement instanceof Package) {
			Object [] packages = ((Package) parentElement).getOwnedPackages().toArray();
			return packages;
//			Object [] classes = ((Package) parentElement).getOwnedElements().toArray();
//			
//			Object [] elements = new Object [packages.length + classes.length];
//			System.arraycopy(packages, 0, elements, 0, packages.length);
//			System.arraycopy(classes, 0, elements, packages.length, classes.length);
//			return elements;
		}
		return null;
	}

	public Object getParent(Object element) {
		if (element instanceof Type) {
			return KDMHelper.getSurroundingPackage(((Type) element));
		} else if (element instanceof Package) {
			return ((Package)element).getPackage();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof Root) {
			//return ((Root) element).getPackages().size()>0;
			return true;
		} else if (element instanceof Package) {			
			return (((Package) element).getOwnedPackages().size()
					+((Package) element).getOwnedElements().size()) > 0;
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
