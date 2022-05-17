package org.somox.kdmhelper;


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
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PackageDeclaration;

public class Root {

    private final List<CompilationUnit> models = new ArrayList<>();

    public List<CompilationUnit> getCompilationUnits() {
        return this.models;
    }

    public void addModels(final Collection<CompilationUnit> modelsFromResource) {
        this.models.addAll(modelsFromResource);
        this.addPackagesToIDMapping(modelsFromResource);
    }

    private static HashMap<PackageDeclaration, String> nodeToIDMap = new HashMap<>();

    public static String getIdForPackage(final PackageDeclaration packageDec) {
        if (nodeToIDMap.containsKey(packageDec)) {
            return nodeToIDMap.get(packageDec);
        }
		return null;
    }

    private void addPackagesToIDMapping(final Collection<CompilationUnit> modelsFromResource) {
        for (final CompilationUnit model : modelsFromResource) {
        	
        	//TODO: What does getPackage returns?
        	PackageDeclaration packageDeclaration = model.getPackage();
        	
        	if (nodeToIDMap.containsKey(packageDeclaration)) {
        		 nodeToIDMap.put(packageDeclaration, EcoreUtil.generateUUID());
        	}
        }
    }

    // TODO Is this really adding all necessary packages?
    public Collection<PackageDeclaration> getPackages() {
        final Collection<PackageDeclaration> result = new ArrayList<>();
        for (final CompilationUnit model : this.models) {
            result.add(model.getPackage());
        }
        return result;
    }

    /**
     * Returns TypeDeclaration, EnumDeclaration
     *
     * @return
     */
    public List<ConcreteClassifier> getNormalClasses() {
        final List<ConcreteClassifier> result = new ArrayList<>();
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