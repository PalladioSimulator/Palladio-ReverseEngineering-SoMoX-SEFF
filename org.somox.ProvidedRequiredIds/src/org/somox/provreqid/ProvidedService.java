/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.somox.provreqid;

import de.fzi.gast.functions.Method;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provided Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.somox.provreqid.ProvidedService#getGastMethod <em>Gast Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.provreqid.ProvreqidPackage#getProvidedService()
 * @model
 * @generated
 */
public interface ProvidedService extends EObject {
	/**
	 * Returns the value of the '<em><b>Gast Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gast Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gast Method</em>' reference.
	 * @see #setGastMethod(Method)
	 * @see org.somox.provreqid.ProvreqidPackage#getProvidedService_GastMethod()
	 * @model required="true"
	 * @generated
	 */
	Method getGastMethod();

	/**
	 * Sets the value of the '{@link org.somox.provreqid.ProvidedService#getGastMethod <em>Gast Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gast Method</em>' reference.
	 * @see #getGastMethod()
	 * @generated
	 */
	void setGastMethod(Method value);

} // ProvidedService
