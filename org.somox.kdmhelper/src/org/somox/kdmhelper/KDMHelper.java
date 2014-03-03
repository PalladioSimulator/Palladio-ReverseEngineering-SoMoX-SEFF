package org.somox.kdmhelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.parser.TagElement;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.arrays.ArrayInstantiationByValues;
import org.emftext.language.java.arrays.ArraysFactory;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.containers.impl.PackageImpl;
import org.emftext.language.java.containers.*;
import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.Final;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Private;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.references.impl.SelfReferenceImpl;
//CompilationUnit statt Model 
//Commentable statt AstNode
import org.emftext.language.java.types.TypeReference;

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
	public static String getName(Type type) {
		if (type instanceof Classifier)
			return ((Classifier) type).getName();
		else if (type instanceof Package)
			return ((Package) type).getName();
		else if (type instanceof CompilationUnit)
			return ((CompilationUnit) type).getName();
		else if (type instanceof Method)
			return ((Method) type).getName();
		else if (type instanceof Parameter)
			return ((Parameter) type).getName();
		else if (type instanceof Member)
			return ((Member) type).getName();

		if (type instanceof Interface)
			return ((Interface) type).getName();
		return type.toString();
	}

	// TODO test

	/**
	 * Returns the qualified name for a type.
	 * 
	 * @param astClass
	 *            the {@link ASTNode} object
	 * @return the full qualified name of the input object
	 */
	public static String computeFullQualifiedName(Commentable astClass) {
		EObject pack = astClass;

		String result = "";

		if (pack instanceof NamedElement) {
			result = getNameOfNamedElement((NamedElement) pack);
		}

		while (pack != null) {

			if (pack.eContainer() != null
					&& pack.eContainer() instanceof NamedElement) {
				pack = pack.eContainer();
				result = getNameOfNamedElement((NamedElement) pack) + "."
						+ result;
			} else {
				pack = pack.eContainer();
			}
		}
		result = removeLastPoint(result);
		return result;
	}

	public static Method getMethod(MethodCall methodCall) {
		ReferenceableElement re = methodCall.getTarget();
		if (re instanceof Method) {
			Method method = (Method) re;
			return method;
		} else {
			return null;
			// TODO: log error
		}

	}

	public static String removeLastPoint(String result) {
		if (result != null) {
			if (result.charAt(result.length() - 1) == '.') {
				return result.substring(0, result.length() - 1);
			} else {
				return result;
			}
		}
		return null;
	}

	private static String getNameOfNamedElement(NamedElement input) {
		String result = "";
		if (input instanceof Method) {
			result = input.getName() + "()";
		} else {
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
	private static Set<Type> getAccessedTypes(Commentable element) {
		Set<Type> result = new HashSet<Type>();
		
		Type accessedType = GetAccessedType.getAccessedType(element);
		if (SISSYMODE) {
			if (accessedType != null) {
				result.add(accessedType);
			}
		} else {// KDM Mode

			// TODO adopt this
			if (accessedType instanceof Parameter) {
				Parameter paramType = (Parameter) accessedType;
				// 1. add main type
				result.add(paramType.getTypeReference().getTarget());
				// 2. add type arguments
				for (TypeArgument typeAccess : paramType.getTypeArguments()) {
					if (((TypeReference) typeAccess).getTarget() instanceof Parameter) {
						// recursive call
						result.addAll(getAccessedTypes(typeAccess));
					} else {
						result.add(GetAccessedType.getAccessedType(typeAccess));
					}
				}
			} else {// if a normal Type
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
		List<Commentable> accesses = getAllAccesses(input);

		for (Commentable node : accesses) {
			resultList.addAll(getAccessedTypes(node));
		}
		ArrayList<Type> returnSet = new ArrayList<Type>();
		returnSet.addAll(resultList);
		return returnSet;
	}

	/**
	 * Returns <b>all accesses inside an ASTNode object</b>. <br>
	 * Accesses <b>inside an {@link TagElement}</b> (for example in JavaDoc
	 * comments) <br>
	 * are <b>not in the result set</b>.
	 * 
	 * @param input
	 *            an {@link ASTNode} object
	 * @return all accesses inside the ASTNode object
	 */
	public static List<Commentable> getAllAccesses(Commentable input) {
		List<Commentable> result = new ArrayList<Commentable>();
		TreeIterator<EObject> iterator = input.eAllContents();

		while (iterator.hasNext()) {
			EObject element = iterator.next();
			if (element instanceof Commentable) {
				if (isAccess((Commentable) element)) {
					// remove accesses in java doc tags
					if (element.eContainer() instanceof TagElement) {
						continue;
					}
					result.add((Commentable) element);

				}
			}
		}
		return result;
	}

	/**
	 * Returns all inheritance type accesses: Super classes and super
	 * interfaces.
	 * 
	 * @param type
	 *            the input {@link Type}
	 * @return the list of inheritance type access
	 */
	public static List<TypeReference> getInheritanceTypeAccesses(Type type) {

		List<TypeReference> result = new ArrayList<TypeReference>();

		if (type instanceof Class) {
			Class tempClass = (Class) type;
			result.addAll(tempClass.getSuperTypeReferences());// getSuperTypeReferences
																// statt
																// getSuperInterfaces
			if (tempClass.getSuperClass() != null) {
				result.add(tempClass.getExtends());// getExtends statt
													// superClass
			}
		}

		if (type instanceof Interface) {
			Interface tempInterface = (Interface) type;
			result.addAll(tempInterface.getSuperTypeReferences());
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
		if (!(clazz instanceof Class)) {
			return result;
		}
		for (Iterator<EObject> iterator = clazz.eAllContents(); iterator
				.hasNext();) {
			EObject element = iterator.next();
			if (element instanceof Class) {
				if (isInnerClass((Type) element)) {
					result.add((Class) element);
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
	// JavaNodeSourceRegion
	// Commentable statt
	public static CompilationUnit getJavaNodeSourceRegion(Commentable node) {

		return node.getContainingCompilationUnit();

	}

	/**
	 * Returns all real methods (not constructors) of a type.
	 * 
	 * @param input
	 *            the
	 * @return the real methods (not constructors) of a Class
	 */
	public static List<Method> getMethods(Type input) {
		List<Method> result = new ArrayList<Method>();
		// FIXEDMYBUG used ClassDecl instead of AbstractTypeDeclaration, missed
		// InterfaceDeclaration

		if (!(input instanceof Type)) {
			return result;
		}

		Type clazz = (Type) input;
		for (Member body : clazz.getAllMembers(input)) {
			if (body instanceof Method) {
				result.add((Method) body);
			}
		}
		return result;
	}

	// TODO burkha 16.05.2013 test and fix, there is a bug in it
	// the MoDisco method getRedefinedMethodDeclaration only works for classes,
	// not for interfaces
	/**
	 * Returns, if exist, the overridden member, else null.
	 * 
	 * @param methDecInput
	 *            the method object
	 * @return the overridden method
	 */
	public static Method getOverriddenASTNode(Method methDecInput) {

		Method redefinedMethodDeclaration = getRedefinedMethodDeclaration(methDecInput);

		if (redefinedMethodDeclaration != null) {
			return redefinedMethodDeclaration;
		}

		Type typeOfMethod = getAbstractTypeDeclaration(((Object) methDecInput));
		List<Type> superTypes = getSuperTypes(typeOfMethod);

		for (Type type : superTypes) {
			List<Method> method = KDMHelper.getMethods(type);
			for (Method methodDeclaration : method) {
				if (EqualityChecker.areFunctionsEqual(methDecInput,
						methodDeclaration)) {
					return methodDeclaration;
				}
			}
		}

		return null;
	}

	private static Method getRedefinedMethodDeclaration(Method methDecInput) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Type getAbstractTypeDeclaration(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO implement
	/**
	 * Returns a string representing the {@link Commentable} object.
	 * 
	 * @param node
	 *            the {@link ASTNode} object
	 * @return the toString string of the input object
	 */
	public static String getSISSyID(Commentable node) {
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

	/**
	 * Returns all super types of a type.
	 * 
	 * @param type
	 *            the input {@link Type}
	 * @return the list of super types
	 */
	public static List<Type> getSuperTypes(Type type) {

		List<Type> result = new ArrayList<Type>();

		if (type == null) {
			return result;
		}

		for (TypeReference typeAccess : getInheritanceTypeAccesses(type)) {
			if (typeAccess != null) {
				result.add(typeAccess.getTarget());
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
	public static Package getSurroundingPackage(Type input) {
		return null;
		// input.getChildrenByType((Type)Package);//
		// input.getContainingPackageName()

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

		if (input instanceof Method)
			return ((Method) input).getModifiers().contains(
					ModifiersFactory.eINSTANCE.createAbstract());

		return false;
	}

	// TODO check refactor switch class
	// is fast, no refactoring needed
	/**
	 * 
	 * @param element
	 * @return true or false
	 */
	public static boolean isAccess(Commentable element) {

		// is an AbstractMethodInvocation, but contains a type access, would
		// else create the TypeAccess twice
		if (element instanceof TypeReference)
			if (element instanceof Class) {
				return false;
			}
		if (element instanceof Method) {
			return true;
		}
		if (element instanceof ArraysPackage) {
			return true;
		}
		if (element instanceof Field) {
			return true;
		}
		if (element instanceof TypeReference) {
			return true;
		}
		if (element instanceof SelfReference) {
			return true;
		}

		if (element instanceof Field) {
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
	public static boolean isInheritanceTypeAccess(TypeReference inputTypeAccess) {
		if (inputTypeAccess.eContainer() instanceof Type) {
			// Type statt AbstractTypeDeclaration
			Type atd = (Type) inputTypeAccess.eContainer();
			for (TypeReference ta : getInheritanceTypeAccesses(atd)) {
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
		return (clazz instanceof Class & clazz.eContainer() instanceof Class);
	}
	public static EClass[] getNewEClassEnumeration() {
		EClass[] en = null ;
		return  en;
	}

	/**
	 * Returns if the type is an interface.
	 * 
	 * @param input
	 *            the input object
	 * @return true or false
	 */
	public static boolean isInterface(Commentable input) {
		return (input instanceof Interface);
	}

	/**
	 * 
	 * @param method
	 *            The method to check the visibility.
	 * @param inputVisKind
	 *            the visibility kind to compare with
	 * @return true or false
	 */
	
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
	public static boolean isVirtual(Method bodyDec) {
		
		 if (bodyDec == null || bodyDec.getModifiers() == null) {
		 return false;
		 }
		
		 if (bodyDec.getModifiers().get(0) != null) {
			 if ((bodyDec.getModifiers().contains(ModifiersFactory.eINSTANCE.createPrivate()))|| 
					 (bodyDec.getModifiers().contains(ModifiersFactory.eINSTANCE.createStatic())) || 
					 (bodyDec.getModifiers().contains(ModifiersFactory.eINSTANCE.createFinal()))) 
			 {
				 return false;
			 }
		 }
	
		return true;
	}

	public static Block getBody(Method method) {
		Block block= StatementsFactory.eINSTANCE.createBlock();
		if(method instanceof ClassMethod)
		{
			EList<Statement> statements = ((ClassMethod)method).getStatements();
			if (statements != null)
			{
			block.getStatements().addAll(statements);
			}
		}
		
		return block;
	}

	public static EList<Package> getOwnedPackages(Package prefixPackage) {
		// TODO Test
				return null;//( (Object) prefixPackage).getSubpackages();
		
		
//		EList<Package> ownedPackages=null;
//		org.emftext.language.java.containers.Package p;
//		
//		for (CompilationUnit comUnit :prefixPackage.getCompilationUnits())
//		{
//			comUnit.getNamespaces();
//			if(comUnit.getContainingPackageName())
//		}
//		return null;
	}

	public static Object getPackage(Package element) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Collection<Package> getOwnedElements(Package element) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
