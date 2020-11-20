package org.somox.metrics.tabs;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.types.Type;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;

//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;

public class CheckboxContentProvider implements ITreeContentProvider {

    @Override
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof Root) {
            return ((Root) parentElement).getPackages().toArray();
        } else if (parentElement instanceof Package) {
            final Object[] packages = KDMHelper
                    .getOwnedPackages((org.emftext.language.java.containers.Package) parentElement).toArray();
            return packages;
            // Object [] classes = ((Package) parentElement).getOwnedElements().toArray();
            //
            // Object [] elements = new Object [packages.length + classes.length];
            // System.arraycopy(packages, 0, elements, 0, packages.length);
            // System.arraycopy(classes, 0, elements, packages.length, classes.length);
            // return elements;
        }
        return null;
    }

    @Override
    public Object getParent(final Object element) {
        if (element instanceof Type) {
            return KDMHelper.getSurroundingPackage(((Type) element));
        } else if (element instanceof Package) {
            return KDMHelper.getPackage((Package) element);
        }
        return null;
    }

    @Override
    public boolean hasChildren(final Object element) {
        if (element instanceof Root) {
            // return ((Root) element).getPackages().size()>0;
            return true;
        } else if (element instanceof Package) {
            return KDMHelper.getOwnedPackages(((Package) element)).size()
                    + KDMHelper.getOwnedElements((Package) element).size() > 0;
        }
        return false;
    }

    @Override
    public Object[] getElements(final Object inputElement) {
        return this.getChildren(inputElement);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

}
