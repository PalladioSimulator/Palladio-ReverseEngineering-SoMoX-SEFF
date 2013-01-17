package org.somox.kdmhelper.metamodeladdition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.Type;
import org.somox.kdmhelper.KDMHelper;

public class Root {

	private List<Model> models = new ArrayList<Model>();

	public List<Model> getModels() {
		return models;
	}

	public void addModels(Collection<Model> modelsFromResource) {
		models.addAll(modelsFromResource);
		addPackagesToIDMapping(modelsFromResource);
	}

	private static HashMap<ASTNode, String> nodeToIDMap = new HashMap<ASTNode, String>();

	// TODO test
	public static String getIdForPackage(ASTNode pack) {
		if (nodeToIDMap.containsKey(pack)) {
			return nodeToIDMap.get(pack);
		} else {
			return null;
		}
	}

	private void addPackagesToIDMapping(Collection<Model> modelsFromResource) {
		for (Model model : modelsFromResource) {
			for (Iterator<EObject> it = model.eAllContents(); it.hasNext();) {
				EObject element = it.next();
				if (element instanceof org.eclipse.gmt.modisco.java.Package) {
					if (!nodeToIDMap.containsKey(element)) {
						nodeToIDMap.put((ASTNode) element,
								EcoreUtil.generateUUID());
					}
				}
			}
		}
	}

	// TODO fix for UI
	public Collection<org.eclipse.gmt.modisco.java.Package> getPackages() {
		Collection<Package> result = new ArrayList<Package>();
		for (Model model : models) {
			result.addAll(model.getOwnedElements());
//			for (Iterator<EObject> it = model.eAllContents(); it.hasNext();) {
//				EObject element = it.next();
//				if (element instanceof org.eclipse.gmt.modisco.java.Package) {
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
		for (Model model : models) {
			for (Iterator<EObject> it = model.eAllContents(); it.hasNext();) {
				EObject element = it.next();
				if (element instanceof ClassDeclaration) {
					ClassDeclaration clazz = (ClassDeclaration) element;
					if (!KDMHelper.isInnerClass(clazz)) {
						result.add((ClassDeclaration) element);
					}
				} else if (element instanceof EnumDeclaration) {
					result.add((Type) element);
				} else if (element instanceof PrimitiveType) {
					result.add((Type) element);
				}
			}
		}
		return result;
	}
}