package org.somox.kdmhelper;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.internal.compiler.ast.ArrayReference;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.types.ClassifierReference;
//import org.eclipse.gmt.modisco.javCommentablede;
//import org.eclipse.gmt.modisco.java.Method;
//import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
//import org.eclipse.gmt.modisco.java.AbstractVariablesContainer;
//import org.eclipse.gmt.modisco.java.ArrayAccess;
//import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
//import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
//import org.eclipse.gmt.modisco.java.FieldAccess;
//import org.eclipse.gmt.modisco.java.FieldDeclaration;
//import org.eclipse.gmt.modisco.java.SingleVariableAccess;
//import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
//import org.eclipse.gmt.modisco.java.SuperFieldAccess;
//import org.eclipse.gmt.modisco.java.ThisExpression;
//import org.eclipse.gmt.modisco.java.Type;
//import org.eclipse.gmt.modisco.java.TypeAccess;
//import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.util.JavaSwitch;
import org.emftext.language.java.*;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;

/**
 * A EMF-Switch to get the accessed type of an access.
 * @author Oliver
 *
 */
public class GetAccessedType extends JavaSwitch<Type> {

	
	/**
	 * Singleton.
	 */
	private static GetAccessedType getInstance = new GetAccessedType();

	/**
	 * Computes the accessed type for an access.
	 * @param input The input access.
	 * @return The accessed Type from the access.
	 */
	public static Type getAccessedType(Commentable input) {
		return getInstance.doSwitch(input);
	}
	//TODO Override the methods in JavaSwitch
	
	protected Type doSwitch(EClass theEClass, ClassifierReference classifierReference) {
		return null;
	}

//	@Override
//	public Type caseAbstractMethodInvocation(Method object) {
//		if (object instanceof ClassInstanceCreation) {
//			return ((ClassInstanceCreation) object).getType().getType();
//		}
//		return object.getMethod().getAbstractTypeDeclaration();
//	}
//
//	
//	
//	@Override
//	public Type caseTypeAccess(TypeReference        object) {
//		return object.getType ();
//	}
//
//	@Override
//	public Type caseArrayAccess(ArrayReference object) {
//		return getInstance.doSwitch(object.getArray());
//	}
//
//	@Override
//	public Type caseFieldAccess(FieldAccess object) {
//		return getInstance.doSwitch(object.getField());
//		// return object.getField().getQualifier().get
//	}

	/**
	 * Used to compute in which type a {@link ASTNode} is placed.
	 * @param input
	 * @return
	 */
//	private static Type getAbstractTypeDeclarationContainer(Commentable input) {
//		Commentable node = input;
//		while (!(node.eContainer() instanceof AbstractTypeDeclaration)) {
//			node = (Commentable) node.eContainer();
//		}
//		return (Type) node.eContainer();
//	}

//	@Override
//	public Type caseSingleVariableAccess(SingleVariableAccess object) {
//
//		// ASTNode node = object;
//		// while (!(node.eContainer() instanceof AbstractTypeDeclaration)) {
//		// node = (ASTNode) node.eContainer();
//		// }
//		// ASTNode tempResult1 = (Type) (node.eContainer());
//
//		if (object.getVariable() != null) {
//			// if (object.getVariable() instanceof SingleVariableDeclaration
//			// | object.getVariable() instanceof EnumConstantDeclaration) {
//			// return getAbstractTypeDeclaration(object.getVariable());
//			// }
//			return getAbstractTypeDeclarationContainer(object.getVariable());
//
//		}
////		System.out.println("VDF");
//		return null;
//		// ASTNode tempResult2 =
//		// getInstance.doSwitch(object.getVariable().eContainer());
//
//		// alt
//		// if(object.getVariable() != null){
//		// if (object.getVariable() instanceof SingleVariableDeclaration |
//		// object.getVariable() instanceof EnumConstantDeclaration){
//		// return getInstance.doSwitch(object.getVariable());
//		// }
//		//
//		// }
//		// return getInstance.doSwitch(object.getVariable().eContainer());
//	}

//	@Override
//	public Type caseEnumConstantDeclaration(EnumConstantDeclaration object) {
//		return (Type) object.eContainer();
//	}
//
//	@Override
//	public Type caseSingleVariableDeclaration(SingleVariableDeclaration object) {
//		return getInstance.doSwitch(object.getType());
//	}
//
//	@Override
//	public Type caseAbstractVariablesContainer(AbstractVariablesContainer object) {
//		return object.getType().getType();
//	}
//
//	@Override
//	public Type caseFieldDeclaration(FieldDeclaration object) {
//		return object.getAbstractTypeDeclaration();
//	}
//
//	@Override
//	public Type caseSuperFieldAccess(SuperFieldAccess object) {
//		return new GetAccessedType().doSwitch(object.getField());
//	}
//
//	@Override
//	public Type caseThisExpression(ThisExpression object) {
//		EObject container = object.eContainer();
//		while (container != null) {
//			if (container instanceof Type) {
//				return (Type) container;
//			}
//			container = container.eContainer();
//		}
//		throw new IllegalArgumentException();
//	}

}
