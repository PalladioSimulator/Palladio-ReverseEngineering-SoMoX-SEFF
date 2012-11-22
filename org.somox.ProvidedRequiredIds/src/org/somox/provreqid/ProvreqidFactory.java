/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.somox.provreqid;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.somox.provreqid.ProvreqidPackage
 * @generated
 */
public interface ProvreqidFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ProvreqidFactory eINSTANCE = org.somox.provreqid.impl.ProvreqidFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Provided Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provided Service</em>'.
	 * @generated
	 */
	ProvidedService createProvidedService();

	/**
	 * Returns a new object of class '<em>Required Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Required Service</em>'.
	 * @generated
	 */
	RequiredService createRequiredService();

	/**
	 * Returns a new object of class '<em>Prov Req Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Prov Req Repository</em>'.
	 * @generated
	 */
	ProvReqRepository createProvReqRepository();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ProvreqidPackage getProvreqidPackage();

} //ProvreqidFactory
