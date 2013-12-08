/**
 */
package org.somox.seff2javaast;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SEFF2 Java AST</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.somox.seff2javaast.SEFF2JavaAST#getSeff2MethodMappings <em>Seff2 Method Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.seff2javaast.Seff2javaastPackage#getSEFF2JavaAST()
 * @model
 * @generated
 */
public interface SEFF2JavaAST extends EObject {
	/**
	 * Returns the value of the '<em><b>Seff2 Method Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.somox.seff2javaast.SEFF2MethodMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seff2 Method Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seff2 Method Mappings</em>' containment reference list.
	 * @see org.somox.seff2javaast.Seff2javaastPackage#getSEFF2JavaAST_Seff2MethodMappings()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<SEFF2MethodMapping> getSeff2MethodMappings();

} // SEFF2JavaAST
