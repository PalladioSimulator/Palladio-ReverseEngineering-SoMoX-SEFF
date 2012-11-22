/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.somox.provreqid.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.somox.provreqid.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProvreqidFactoryImpl extends EFactoryImpl implements ProvreqidFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ProvreqidFactory init() {
		try {
			ProvreqidFactory theProvreqidFactory = (ProvreqidFactory)EPackage.Registry.INSTANCE.getEFactory("http://somox.org/provreqid"); 
			if (theProvreqidFactory != null) {
				return theProvreqidFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ProvreqidFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvreqidFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ProvreqidPackage.PROVIDED_SERVICE: return createProvidedService();
			case ProvreqidPackage.REQUIRED_SERVICE: return createRequiredService();
			case ProvreqidPackage.PROV_REQ_REPOSITORY: return createProvReqRepository();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvidedService createProvidedService() {
		ProvidedServiceImpl providedService = new ProvidedServiceImpl();
		return providedService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequiredService createRequiredService() {
		RequiredServiceImpl requiredService = new RequiredServiceImpl();
		return requiredService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvReqRepository createProvReqRepository() {
		ProvReqRepositoryImpl provReqRepository = new ProvReqRepositoryImpl();
		return provReqRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvreqidPackage getProvreqidPackage() {
		return (ProvreqidPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ProvreqidPackage getPackage() {
		return ProvreqidPackage.eINSTANCE;
	}

} //ProvreqidFactoryImpl
