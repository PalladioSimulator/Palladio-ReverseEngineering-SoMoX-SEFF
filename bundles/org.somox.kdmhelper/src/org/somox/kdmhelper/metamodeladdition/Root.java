package org.somox.kdmhelper.metamodeladdition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.somox.kdmhelper.KDMHelper;

public class Root {

    private final List<CompilationUnit> models = new ArrayList<CompilationUnit>();

    public List<CompilationUnit> getCompilationUnits() {
        return this.models;
    }

    public void addModels(final Collection<CompilationUnit> modelsFromResource) {
        this.models.addAll(modelsFromResource);
        this.addPackagesToIDMapping(modelsFromResource);
    }

    private static HashMap<Commentable, String> nodeToIDMap = new HashMap<Commentable, String>();

    // TODO test
    public static String getIdForPackage(final Commentable pack) {
        if (nodeToIDMap.containsKey(pack)) {
            return nodeToIDMap.get(pack);
        } else {
            return null;
        }
    }

    private void addPackagesToIDMapping(final Collection<CompilationUnit> modelsFromResource) {
        for (final CompilationUnit model : modelsFromResource) {
            for (final Iterator<EObject> it = model.eAllContents(); it.hasNext();) {
                final EObject element = it.next();
                if (element instanceof Package) {
                    if (!nodeToIDMap.containsKey(element)) {
                        nodeToIDMap.put((Commentable) element, EcoreUtil.generateUUID());
                    }
                }
            }
        }
    }

    // TODO fix for UI
    public Collection<Package> getPackages() {
        final Collection<Package> result = new ArrayList<Package>();
        for (final CompilationUnit model : this.models) {
            // (Collection<? extends Package>) added
            result.addAll((Collection<? extends Package>) model.eResource().getAllContents());// getOwnedElements
            // for (Iterator<EObject> it = model.eAllContents(); it.hasNext();) {
            // EObject element = it.next();
            // if (element instanceof Package) {
            // result.add((Package) element);
            // }
            // }
        }
        return result;
    }

    /**
     * Returns ClassDeclaration, EnumDeclaration
     *
     * @return
     */
    public List<ConcreteClassifier> getNormalClasses() {
        final List<ConcreteClassifier> result = new ArrayList<ConcreteClassifier>();
        for (final CompilationUnit model : this.models) {
            for (final Iterator<EObject> it = model.eAllContents(); it.hasNext();) {
                final EObject element = it.next();
                if (element instanceof Class) {
                    final Class clazz = (Class) element;
                    if (!KDMHelper.isInnerClass(clazz)) {
                        result.add((Class) element);
                    }
                } else if (element instanceof Enumeration) {
                    result.add((ConcreteClassifier) element);
                }
            }
        }
        return result;
    }
}