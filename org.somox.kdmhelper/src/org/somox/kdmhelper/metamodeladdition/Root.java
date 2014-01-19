package org.somox.kdmhelper.metamodeladdition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.resource.*;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.*;
import org.somox.kdmhelper.KDMHelper;
import org.emftext.language.java.classifiers.Class;

import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.types.PrimitiveType;

//CompilationUnit statt Model 
//Commentable statt AstNode
public class Root {

	private List<CompilationUnit> models = new ArrayList<CompilationUnit>();

	public List<CompilationUnit> getCompilationUnits() {
		return models;
	}
	
	//CompilationUnit statt AstNode 
		// JavaRoot statt JavaAplication
	//CompilationUnit   statt Model

	public void addCompilationUnits(Collection<CompilationUnit> modelsFromResource) {
		models.addAll(modelsFromResource);
		addPackagesToIDMapping(modelsFromResource);
	}

	private static HashMap<Commentable, String> nodeToIDMap = new HashMap<Commentable, String>();

	// TODO test
	public static String getIdForPackage(Commentable pack) {
		if (nodeToIDMap.containsKey(pack)) {
			return nodeToIDMap.get(pack);
		} else {
			return null;
		}
	}

	private void addPackagesToIDMapping(Collection<CompilationUnit> modelsFromResource) {
		for (CompilationUnit model : modelsFromResource) {
			for (Iterator<EObject> it = model.eAllContents(); it.hasNext();) {
				EObject element = it.next();
				if (element instanceof Package) {
					if (!nodeToIDMap.containsKey(element)) {
						nodeToIDMap.put((Commentable) element,
								EcoreUtil.generateUUID());
					}
				}
			}
		}
	}

	// TODO fix for UI
	public Collection<Package> getPackages() {
		Collection<Package> result = new ArrayList<Package>();
		for (CompilationUnit model : models) {
			// (Collection<? extends Package>) added
			result.addAll((Collection<? extends Package>) model.eResource().getAllContents());//getOwnedElements
//			for (Iterator<EObject> it = model.eAllContents(); it.hasNext();) {
//				EObject element = it.next();
//				if (element instanceof Package) {
//					result.add((Package) element);
//				}
//			}
		}
		return result;
	}

	/**
	 * Returns ClassDeclaration, EnumDeclaration, PrimitiveType
	 * 
	 * @return
	 */
	public List<Type> getNormalClasses() {
		List<Type> result = new ArrayList<Type>();
		for (CompilationUnit model : models) {
			for (Iterator<EObject> it = model.eAllContents(); it.hasNext();) {
				EObject element = it.next();
				if (element instanceof Class) {
					Class clazz = (Class) element;
					if (!KDMHelper.isInnerClass(clazz)) {
						result.add((Class) element);
					}
				} else if (element instanceof Enumeration) {
					result.add((Type) element);
				} else if (element instanceof PrimitiveType) {
					result.add((Type) element);
				}
			}
		}
		return result;
	}
}