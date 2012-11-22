/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.somox.provreqid;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Prov Req Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.somox.provreqid.ProvReqRepository#getProvidedServices <em>Provided Services</em>}</li>
 *   <li>{@link org.somox.provreqid.ProvReqRepository#getRequiredServices <em>Required Services</em>}</li>
 *   <li>{@link org.somox.provreqid.ProvReqRepository#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.provreqid.ProvreqidPackage#getProvReqRepository()
 * @model
 * @generated
 */
public interface ProvReqRepository extends EObject {
	/**
	 * Returns the value of the '<em><b>Provided Services</b></em>' containment reference list.
	 * The list contents are of type {@link org.somox.provreqid.ProvidedService}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provided Services</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provided Services</em>' containment reference list.
	 * @see org.somox.provreqid.ProvreqidPackage#getProvReqRepository_ProvidedServices()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProvidedService> getProvidedServices();

	/**
	 * Returns the value of the '<em><b>Required Services</b></em>' containment reference list.
	 * The list contents are of type {@link org.somox.provreqid.RequiredService}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Services</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Services</em>' containment reference list.
	 * @see org.somox.provreqid.ProvreqidPackage#getProvReqRepository_RequiredServices()
	 * @model containment="true"
	 * @generated
	 */
	EList<RequiredService> getRequiredServices();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.somox.provreqid.ProvreqidPackage#getProvReqRepository_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.somox.provreqid.ProvReqRepository#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ProvReqRepository
