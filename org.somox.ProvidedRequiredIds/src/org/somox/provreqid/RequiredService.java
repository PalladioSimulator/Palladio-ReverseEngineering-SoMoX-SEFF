/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.somox.provreqid;

import de.fzi.gast.accesses.FunctionAccess;
import de.fzi.gast.functions.Method;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Required Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.somox.provreqid.RequiredService#getGastMethod <em>Gast Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.provreqid.ProvreqidPackage#getRequiredService()
 * @model
 * @generated
 */
public interface RequiredService extends EObject {
	/**
	 * Returns the value of the '<em><b>Gast Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gast Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gast Method</em>' reference.
	 * @see #setGastMethod(FunctionAccess)
	 * @see org.somox.provreqid.ProvreqidPackage#getRequiredService_GastMethod()
	 * @model required="true"
	 * @generated
	 */
	FunctionAccess getGastMethod();

	/**
	 * Sets the value of the '{@link org.somox.provreqid.RequiredService#getGastMethod <em>Gast Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gast Method</em>' reference.
	 * @see #getGastMethod()
	 * @generated
	 */
	void setGastMethod(FunctionAccess value);

} // RequiredService
