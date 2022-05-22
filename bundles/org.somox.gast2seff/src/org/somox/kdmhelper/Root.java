package org.somox.kdmhelper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

// Frage: Was beinhaltet Root? Alle Methoden? Alle Pakete? Was wird hier übergeben?
public class Root {

    private final List<CompilationUnit> models = new ArrayList<>();

    public List<CompilationUnit> getCompilationUnits() {
        return this.models;
    }

    // TODO: Was beinhaltet die Collection?
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
        		 nodeToIDMap.put(packageDeclaration, UUID.randomUUID().toString());
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
    public List<CompilationUnit> getNormalClasses() {
        final List<CompilationUnit> result = new ArrayList<>();
        for (final CompilationUnit model : this.models) {
        	List<AbstractTypeDeclaration> types = model.types();
        	for (final AbstractTypeDeclaration type : types) {        		
        		if (type instanceof TypeDeclaration || type instanceof EnumDeclaration) {
        			result.add(model);
        		}
        	}
        }
        return result;
    }
    
}