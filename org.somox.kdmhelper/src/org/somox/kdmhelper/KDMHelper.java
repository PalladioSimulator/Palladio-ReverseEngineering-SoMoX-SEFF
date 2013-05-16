package org.somox.kdmhelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.ArrayAccess;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.FieldAccess;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.ParameterizedType;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.SuperFieldAccess;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.ThisExpression;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;
import org.eclipse.modisco.java.composition.javaapplication.Java2File;
import org.eclipse.modisco.java.composition.javaapplication.JavaNodeSourceRegion;
import org.eclipse.modisco.java.composition.javaapplication.queries.GetASTNodeSourceRegion;

/**
 * This class contains a set of methods that are missing in the MoDisco Java
 * meta model in comparison to the SISSy G-AST meta model.
 * 
 * @author Oliver
 * 
 */
public class KDMHelper {

	/**
	 * SISSy does not model type parameters.<br>
	 * For example:
	 * 
	 * <pre>
	 * List &lt;String&gt;
	 * </pre>
	 * 
	 * only detects List as type access. <br>
	 * If this variable is true, then this class works like SISSy. Else, for the
	 * example it also returns the String type.
	 */
	public static boolean SISSYMODE = true;

	
	// /**
	// *
	// * @param clazz
	// * @return the real methods (not constructors) of a Class
	// */
	// public static List<AbstractMethodDeclaration> getMethods(ClassDeclaration
	// clazz){
	// List<AbstractMethodDeclaration> result = new
	// ArrayList<AbstractMethodDeclaration>();
	// for(BodyDeclaration bodyDec : clazz.getBodyDeclarations()){
	// if(bodyDec instanceof MethodDeclaration){
	// result.add((AbstractMethodDeclaration) bodyDec);
	// }
	// }
	// return result;
	// }

	// TODO test

	/**
	 * Returns the qualified name for a type.
	 * 
	 * @param input
	 *            the {@link ASTNode} object
	 * @return the full qualified name of the input object
	 */
	public static String computeFullQualifiedName(ASTNode input) {
		EObject pack = input;

		String result = "";

		if (pack instanceof NamedElement) {
			result = getNameOfNamedElement((NamedElement) pack);
		}

		while (pack != null) {
			if (pack.eContainer() != null
					&& pack.eContainer() instanceof NamedElement) {
				pack = pack.eContainer();
				result = getNameOfNamedElement((NamedElement) pack) + "." + result;
			} else {
				pack = pack.eContainer();
			}
		}
		result = removeLastPoint(result);
		return result;
	}
	
	public static String removeLastPoint(String result) {
	    if(result != null){
	        if(result.charAt(result.length()-1) == '.'){
	            return result.substring(0, result.length() - 1);
	        }
	        else{
	            return result;
	        }
	    }
	    return null;
    }

    private static String getNameOfNamedElement(NamedElement input){
		String result ="";
		if(input instanceof AbstractMethodDeclaration){
			result = input.getName() + "()";
		}else{
			result = input.getName();
		}
		return result;
	}

	// TODO refactor with
	/**
	 * For an access, returns the accessed types. <b>The result set does not
	 * contain null pointer.</b>
	 * 
	 * @param element
	 *            an access element
	 * @return the set of accessed types
	 */
	private static Set<Type> getAccessedTypes(ASTNode element) {
		Set<Type> result = new HashSet<Type>();
		// if(!isAccess(element)){
		// throw new IllegalArgumentException(element + " is not an access.");
		// }
		// added after fix
		// if(element instanceof TypeAccess){
		// TypeAccess access = (TypeAccess) element;
		// Type accessedType = access.getType();
		// if(accessedType instanceof ParameterizedType){
		// ParameterizedType paramType =(ParameterizedType) accessedType;
		// result.addAll(getAccessedTypesFromParameterizedType(paramType));
		// return result;
		// }
		// }

		Type accessedType = GetAccessedType.getAccessedType(element);
		if (SISSYMODE) {
			if (accessedType != null) {
				result.add(accessedType);
			}
		}
		else{//KDM Mode
			
//			TODO adopt this
			if (accessedType instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) accessedType;
				//1. add main type
				result.add(paramType.getType().getType());
				//2. add type arguments
				for(TypeAccess typeAccess : paramType.getTypeArguments()){
					if(typeAccess.getType() instanceof ParameterizedType){
						//recursive call
						result.addAll(getAccessedTypes(typeAccess));
					} else{
						result.add(GetAccessedType.getAccessedType(typeAccess));
					}
				}
			} else{//if a normal Type
				result.add(accessedType);
			}
		}
		return result;

	}

	/**
	 * For a {@link ParameterizedType} objects returns the accessed types.
	 * 
	 * @param input
	 *            a {@link ParameterizedType} object
	 * @return a set of the accessed types
	 */


	/**
	 * Returns all accessed types inside a type.
	 * 
	 * @param input
	 *            the input {@link Type}
	 * @return the list of accessed types
	 */
	public static List<Type> getAllAccessedClasses(Type input) {
		Set<Type> resultList = new HashSet<Type>();
		List<ASTNode> accesses = getAllAccesses(input);

		for (ASTNode node : accesses) {
			resultList.addAll(getAccessedTypes(node));
		}
		ArrayList<Type> returnSet = new ArrayList<Type>();
		returnSet.addAll(resultList);
		return returnSet;
	}

	/**
	 * Returns <b>all accesses inside an ASTNode object</b>.
	 * <br>
	 * Accesses <b>inside an {@link TagElement}</b> (for example
	 * in JavaDoc comments) <br>are <b>not in the result set</b>.
	 * 
	 * @param input an {@link ASTNode} object
	 * @return all accesses inside the ASTNode object
	 */
	public static List<ASTNode> getAllAccesses(ASTNode input) {
		List<ASTNode> result = new ArrayList<ASTNode>();
		TreeIterator<EObject> iterator = input.eAllContents();

		while (iterator.hasNext()) {
			EObject element = iterator.next();
			if (element instanceof ASTNode) {
				if (isAccess((ASTNode) element)) {
					// remove accesses in java doc tags
					if (element.eContainer() instanceof TagElement) {
						continue;
					}
					result.add((ASTNode) element);

					// TODO removlater add access to enum constant again to
					// assimiliate to SISSy results
					// if(element instanceof SingleVariableAccess){
					// SingleVariableAccess access = (SingleVariableAccess)
					// element;
					// VariableDeclaration variable = access.getVariable();
					// if(variable instanceof EnumConstantDeclaration){
					// result.add((ASTNode) element);
					// }
					// }

				}
			}
		}
		// add self access ??? (which type?) ThisExpression -> yes
		// SelfAccesses were removed in the SISSy GAST manually.
		return result;
	}

	/**
	 * Returns all inheritance type accesses: Super classes and super
	 * interfaces.
	 * 
	 * @param clazz
	 *            the input {@link Type}
	 * @return the list of inheritance type access
	 */
	public static List<TypeAccess> getInheritanceTypeAccesses(Type clazz) {
		List<TypeAccess> result = new ArrayList<TypeAccess>();
		if (clazz instanceof ClassDeclaration) {
			ClassDeclaration tempClass = (ClassDeclaration) clazz;
			result.addAll(tempClass.getSuperInterfaces());
			if (tempClass.getSuperClass() != null) {
				result.add(tempClass.getSuperClass());
			}
		}

		if (clazz instanceof InterfaceDeclaration) {
			InterfaceDeclaration tempInterface = (InterfaceDeclaration) clazz;
			result.addAll(tempInterface.getSuperInterfaces());
		}

		return result;
	}

	/**
	 * For a type returns all inner classes.
	 * 
	 * @param clazz
	 *            the input type
	 * @return the list of inner classes
	 */
	// SOMOXTODOCHANGE inner classes in inner classes???
	public static List<Type> getInnerClasses(Type clazz) {
		List<Type> result = new ArrayList<Type>();
		if (!(clazz instanceof ClassDeclaration)) {
			return result;
		}
		for (Iterator<EObject> iterator = clazz.eAllContents(); iterator
				.hasNext();) {
			EObject element = iterator.next();
			if (element instanceof ClassDeclaration) {
				if (isInnerClass((Type) element)) { // TODO unnecessary
					result.add((ClassDeclaration) element);
				}
			}
		}
		return result;
	}

	/**
	 * For an ASTNode computes the {@link JavaNodeSourceRegion} object.
	 * 
	 * @param node
	 *            the ASTNode object
	 * @return the {@link JavaNodeSourceRegion}
	 */
	public static JavaNodeSourceRegion getJavaNodeSourceRegion(ASTNode node) {
		GetASTNodeSourceRegion query = new GetASTNodeSourceRegion();
		try {
			return query.evaluate(node, null);
		} catch (ModelQueryExecutionException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Returns all real methods (not constructors) of a type.
	 * 
	 * @param input
	 *            the
	 * @return the real methods (not constructors) of a Class
	 */
	public static List<MethodDeclaration> getMethods(Type input) {
		List<MethodDeclaration> result = new ArrayList<MethodDeclaration>();
		// FIXEDMYBUG used ClassDecl instead of AbstractTypeDeclaration, missed
		// InterfaceDeclaration

		if (!(input instanceof AbstractTypeDeclaration)) {
			return result;
		}

		AbstractTypeDeclaration clazz = (AbstractTypeDeclaration) input;
		for (BodyDeclaration body : clazz.getBodyDeclarations()) {
			if (body instanceof MethodDeclaration) {
				result.add((MethodDeclaration) body);
			}
		}
		return result;
	}

	// TODO burkha 16.05.2013 test and fix, there is a bug in it
	/**
	 * Returns, if exist, the overridden member, else null.
	 * 
	 * @param methDec
	 *            the method object
	 * @return the overridden method
	 */
	public static MethodDeclaration getOverriddenMember(
			MethodDeclaration methDec) {
		if (methDec != null) {
			return methDec.getRedefinedMethodDeclaration();
		}
		return null;
	}

	// TODO implement
	/**
	 * Returns a string representing the {@link ASTNode} object.
	 * 
	 * @param node
	 *            the {@link ASTNode} object
	 * @return the toString string of the input object
	 */
	public static String getSISSyID(ASTNode node) {
		return node.toString();
	}

	/**
	 * Queries the {@link SourceFile} object for a {@link JavaNodeSourceRegion}
	 * object.
	 * 
	 * @param sourceRegion
	 *            the input object
	 * @return the {@link SourceFile} object
	 */
	//TODO refactor return
	public static SourceFile getSourceFile(JavaNodeSourceRegion sourceRegion) {
		SourceFile result = null;
		if (sourceRegion != null) {
			if (sourceRegion.eContainer() instanceof Java2File) {
				Java2File java2file = (Java2File) sourceRegion.eContainer();
				return java2file.getFile();
			}
		}
		return result;
	}

	/**
	 * Returns all super types of a type.
	 * 
	 * @param clazz
	 *            the input {@link Type}
	 * @return the list of super types
	 */
	public static List<Type> getSuperTypes(Type clazz) {
		List<Type> result = new ArrayList<Type>();
		if (clazz == null) {
			return result;
		}
		for (TypeAccess typeAccess : getInheritanceTypeAccesses(clazz)) {
			if (typeAccess != null) {
				result.add(typeAccess.getType());
			}
		}
		return result;
	}

	/**
	 * Returns the surrounding package of a type, else null.
	 * 
	 * @param input
	 *            the input {@link Type}
	 * @return the {@link Package} containing the type
	 */
	public static org.eclipse.gmt.modisco.java.Package getSurroundingPackage(
			Type input) {
		if (input instanceof AbstractTypeDeclaration) {
			AbstractTypeDeclaration type = (AbstractTypeDeclaration) input;
			return type.getPackage();
		} else {
			return null;
		}
	}

	// TODO test
	/**
	 * Returns whether the type is abstract.
	 * 
	 * @param input
	 *            the {@link Type} object
	 * @return true or false
	 */
	public static boolean isAbstract(Type input) {
		if (input instanceof BodyDeclaration) {
			BodyDeclaration body = (BodyDeclaration) input;
			Modifier modifier = body.getModifier();
			if (modifier != null) {
				InheritanceKind inhKind = modifier.getInheritance();
				if (inhKind != null) {
					return body.getModifier().getInheritance()
							.equals(InheritanceKind.ABSTRACT);
				}
			}
		}
		return false;
	}

	// TODO check refactor switch class
	// is fast, no refactoring needed
	/**
	 * 
	 * @param element
	 * @return true or false
	 */
	public static boolean isAccess(ASTNode element) {

		// is an AbstractMethodInvocation, but contains a type access, would
		// else create the TypeAccess twice
		if (element instanceof ClassInstanceCreation) {
			return false;
		}
		if (element instanceof AbstractMethodInvocation) {
			return true;
		}
		if (element instanceof ArrayAccess) {
			return true;
		}
		if (element instanceof FieldAccess) {
			return true;
		}
		if (element instanceof TypeAccess) {
			return true;
		}
		if (element instanceof ThisExpression) {
			return true;
		}
		if (element instanceof SingleVariableAccess) {
			return true;
		}
		if (element instanceof SuperFieldAccess) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if a type access is an inheritance type access.
	 * 
	 * @param inputTypeAccess
	 *            The type access to verify.
	 * @return true or false.
	 */
	public static boolean isInheritanceTypeAccess(TypeAccess inputTypeAccess) {
		if (inputTypeAccess.eContainer() instanceof AbstractTypeDeclaration) {

			AbstractTypeDeclaration atd = (AbstractTypeDeclaration) inputTypeAccess
					.eContainer();
			for (TypeAccess ta : getInheritanceTypeAccesses(atd)) {
				if (ta == inputTypeAccess) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns if the type is an inner class.
	 * 
	 * @param clazz
	 *            the input {@link Type}
	 * @return true or false
	 */
	public static boolean isInnerClass(Type clazz) {
		return (clazz instanceof ClassDeclaration & clazz.eContainer() instanceof ClassDeclaration);
	}

	/**
	 * Returns if the type is an interface.
	 * 
	 * @param input
	 *            the input object
	 * @return true or false
	 */
	public static boolean isInterface(ASTNode input) {
		return (input instanceof InterfaceDeclaration);
	}

	/**
	 * 
	 * @param method
	 *            The method to check the visibility.
	 * @param inputVisKind
	 *            the visibility kind to compare with
	 * @return true or false
	 */
	public static boolean isModifierOfKind(MethodDeclaration method,
			VisibilityKind inputVisKind) {
		Modifier modifier = method.getModifier();
		if (modifier == null) {
			return false;
		} else {
			VisibilityKind kind = method.getModifier().getVisibility();
			if (kind == null) {
				return false;
			} else {
				return kind.equals(inputVisKind);
			}

		}
	}

	// TODO test
	/**
	 * Returns whether the type is primitive or not.
	 * 
	 * @param input
	 *            the input {@link Type}
	 * @return true or false
	 */
	public static boolean isPrimitive(Type input) {
		return input instanceof PrimitiveType;
	}

	/**
	 * A virtual method can be overridden. In Java 1. Static methods cannot be
	 * overridden. 2. Non static private and final methods cannot be overridden.
	 * 
	 * @param bodyDec
	 *            the {@link BodyDeclaration} object
	 * @return true or false
	 */
	public static boolean isVirtual(BodyDeclaration bodyDec) {
		if (bodyDec == null || bodyDec.getModifier() == null) {
			return false;
		}

		if (bodyDec.getModifier().getVisibility() != null) {
			if (bodyDec.getModifier().getVisibility()
					.equals(VisibilityKind.PRIVATE)) {
				return false;
			}
		}

		if (bodyDec.getModifier().isStatic()) {
			return false;
		}
		
		InheritanceKind inherKind = bodyDec.getModifier().getInheritance(); 
		if (inherKind != null && inherKind.equals(InheritanceKind.FINAL)) {
			return false;
		}
		return true;
	}
}
