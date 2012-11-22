/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.somox.provreqid;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.somox.provreqid.ProvreqidFactory
 * @model kind="package"
 * @generated
 */
public interface ProvreqidPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "provreqid";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://somox.org/provreqid";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "provreqid";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ProvreqidPackage eINSTANCE = org.somox.provreqid.impl.ProvreqidPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.somox.provreqid.impl.ProvidedServiceImpl <em>Provided Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.somox.provreqid.impl.ProvidedServiceImpl
	 * @see org.somox.provreqid.impl.ProvreqidPackageImpl#getProvidedService()
	 * @generated
	 */
	int PROVIDED_SERVICE = 0;

	/**
	 * The feature id for the '<em><b>Gast Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE__GAST_METHOD = 0;

	/**
	 * The number of structural features of the '<em>Provided Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDED_SERVICE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.somox.provreqid.impl.RequiredServiceImpl <em>Required Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.somox.provreqid.impl.RequiredServiceImpl
	 * @see org.somox.provreqid.impl.ProvreqidPackageImpl#getRequiredService()
	 * @generated
	 */
	int REQUIRED_SERVICE = 1;

	/**
	 * The feature id for the '<em><b>Gast Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_SERVICE__GAST_METHOD = 0;

	/**
	 * The number of structural features of the '<em>Required Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_SERVICE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.somox.provreqid.impl.ProvReqRepositoryImpl <em>Prov Req Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.somox.provreqid.impl.ProvReqRepositoryImpl
	 * @see org.somox.provreqid.impl.ProvreqidPackageImpl#getProvReqRepository()
	 * @generated
	 */
	int PROV_REQ_REPOSITORY = 2;

	/**
	 * The feature id for the '<em><b>Provided Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROV_REQ_REPOSITORY__PROVIDED_SERVICES = 0;

	/**
	 * The feature id for the '<em><b>Required Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROV_REQ_REPOSITORY__REQUIRED_SERVICES = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROV_REQ_REPOSITORY__NAME = 2;

	/**
	 * The number of structural features of the '<em>Prov Req Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROV_REQ_REPOSITORY_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link org.somox.provreqid.ProvidedService <em>Provided Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provided Service</em>'.
	 * @see org.somox.provreqid.ProvidedService
	 * @generated
	 */
	EClass getProvidedService();

	/**
	 * Returns the meta object for the reference '{@link org.somox.provreqid.ProvidedService#getGastMethod <em>Gast Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gast Method</em>'.
	 * @see org.somox.provreqid.ProvidedService#getGastMethod()
	 * @see #getProvidedService()
	 * @generated
	 */
	EReference getProvidedService_GastMethod();

	/**
	 * Returns the meta object for class '{@link org.somox.provreqid.RequiredService <em>Required Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Required Service</em>'.
	 * @see org.somox.provreqid.RequiredService
	 * @generated
	 */
	EClass getRequiredService();

	/**
	 * Returns the meta object for the reference '{@link org.somox.provreqid.RequiredService#getGastMethod <em>Gast Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gast Method</em>'.
	 * @see org.somox.provreqid.RequiredService#getGastMethod()
	 * @see #getRequiredService()
	 * @generated
	 */
	EReference getRequiredService_GastMethod();

	/**
	 * Returns the meta object for class '{@link org.somox.provreqid.ProvReqRepository <em>Prov Req Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Prov Req Repository</em>'.
	 * @see org.somox.provreqid.ProvReqRepository
	 * @generated
	 */
	EClass getProvReqRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link org.somox.provreqid.ProvReqRepository#getProvidedServices <em>Provided Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Provided Services</em>'.
	 * @see org.somox.provreqid.ProvReqRepository#getProvidedServices()
	 * @see #getProvReqRepository()
	 * @generated
	 */
	EReference getProvReqRepository_ProvidedServices();

	/**
	 * Returns the meta object for the containment reference list '{@link org.somox.provreqid.ProvReqRepository#getRequiredServices <em>Required Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Required Services</em>'.
	 * @see org.somox.provreqid.ProvReqRepository#getRequiredServices()
	 * @see #getProvReqRepository()
	 * @generated
	 */
	EReference getProvReqRepository_RequiredServices();

	/**
	 * Returns the meta object for the attribute '{@link org.somox.provreqid.ProvReqRepository#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.somox.provreqid.ProvReqRepository#getName()
	 * @see #getProvReqRepository()
	 * @generated
	 */
	EAttribute getProvReqRepository_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ProvreqidFactory getProvreqidFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.somox.provreqid.impl.ProvidedServiceImpl <em>Provided Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.somox.provreqid.impl.ProvidedServiceImpl
		 * @see org.somox.provreqid.impl.ProvreqidPackageImpl#getProvidedService()
		 * @generated
		 */
		EClass PROVIDED_SERVICE = eINSTANCE.getProvidedService();

		/**
		 * The meta object literal for the '<em><b>Gast Method</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROVIDED_SERVICE__GAST_METHOD = eINSTANCE.getProvidedService_GastMethod();

		/**
		 * The meta object literal for the '{@link org.somox.provreqid.impl.RequiredServiceImpl <em>Required Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.somox.provreqid.impl.RequiredServiceImpl
		 * @see org.somox.provreqid.impl.ProvreqidPackageImpl#getRequiredService()
		 * @generated
		 */
		EClass REQUIRED_SERVICE = eINSTANCE.getRequiredService();

		/**
		 * The meta object literal for the '<em><b>Gast Method</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIRED_SERVICE__GAST_METHOD = eINSTANCE.getRequiredService_GastMethod();

		/**
		 * The meta object literal for the '{@link org.somox.provreqid.impl.ProvReqRepositoryImpl <em>Prov Req Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.somox.provreqid.impl.ProvReqRepositoryImpl
		 * @see org.somox.provreqid.impl.ProvreqidPackageImpl#getProvReqRepository()
		 * @generated
		 */
		EClass PROV_REQ_REPOSITORY = eINSTANCE.getProvReqRepository();

		/**
		 * The meta object literal for the '<em><b>Provided Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROV_REQ_REPOSITORY__PROVIDED_SERVICES = eINSTANCE.getProvReqRepository_ProvidedServices();

		/**
		 * The meta object literal for the '<em><b>Required Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROV_REQ_REPOSITORY__REQUIRED_SERVICES = eINSTANCE.getProvReqRepository_RequiredServices();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROV_REQ_REPOSITORY__NAME = eINSTANCE.getProvReqRepository_Name();

	}

} //ProvreqidPackage
