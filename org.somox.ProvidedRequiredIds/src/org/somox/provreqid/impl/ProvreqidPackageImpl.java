/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.somox.provreqid.impl;

import de.fzi.gast.accesses.accessesPackage;

import de.fzi.gast.annotations.annotationsPackage;

import de.fzi.gast.core.corePackage;

import de.fzi.gast.functions.functionsPackage;

import de.fzi.gast.statements.statementsPackage;

import de.fzi.gast.types.typesPackage;

import de.fzi.gast.variables.variablesPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.somox.provreqid.ProvReqRepository;
import org.somox.provreqid.ProvidedService;
import org.somox.provreqid.ProvreqidFactory;
import org.somox.provreqid.ProvreqidPackage;
import org.somox.provreqid.RequiredService;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProvreqidPackageImpl extends EPackageImpl implements ProvreqidPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requiredServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass provReqRepositoryEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.somox.provreqid.ProvreqidPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ProvreqidPackageImpl() {
		super(eNS_URI, ProvreqidFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ProvreqidPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ProvreqidPackage init() {
		if (isInited) return (ProvreqidPackage)EPackage.Registry.INSTANCE.getEPackage(ProvreqidPackage.eNS_URI);

		// Obtain or create and register package
		ProvreqidPackageImpl theProvreqidPackage = (ProvreqidPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ProvreqidPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ProvreqidPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		statementsPackage.eINSTANCE.eClass();
		corePackage.eINSTANCE.eClass();
		annotationsPackage.eINSTANCE.eClass();
		typesPackage.eINSTANCE.eClass();
		accessesPackage.eINSTANCE.eClass();
		variablesPackage.eINSTANCE.eClass();
		functionsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theProvreqidPackage.createPackageContents();

		// Initialize created meta-data
		theProvreqidPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theProvreqidPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ProvreqidPackage.eNS_URI, theProvreqidPackage);
		return theProvreqidPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedService() {
		return providedServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvidedService_GastMethod() {
		return (EReference)providedServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequiredService() {
		return requiredServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequiredService_GastMethod() {
		return (EReference)requiredServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvReqRepository() {
		return provReqRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvReqRepository_ProvidedServices() {
		return (EReference)provReqRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvReqRepository_RequiredServices() {
		return (EReference)provReqRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvReqRepository_Name() {
		return (EAttribute)provReqRepositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProvreqidFactory getProvreqidFactory() {
		return (ProvreqidFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		providedServiceEClass = createEClass(PROVIDED_SERVICE);
		createEReference(providedServiceEClass, PROVIDED_SERVICE__GAST_METHOD);

		requiredServiceEClass = createEClass(REQUIRED_SERVICE);
		createEReference(requiredServiceEClass, REQUIRED_SERVICE__GAST_METHOD);

		provReqRepositoryEClass = createEClass(PROV_REQ_REPOSITORY);
		createEReference(provReqRepositoryEClass, PROV_REQ_REPOSITORY__PROVIDED_SERVICES);
		createEReference(provReqRepositoryEClass, PROV_REQ_REPOSITORY__REQUIRED_SERVICES);
		createEAttribute(provReqRepositoryEClass, PROV_REQ_REPOSITORY__NAME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		functionsPackage thefunctionsPackage = (functionsPackage)EPackage.Registry.INSTANCE.getEPackage(functionsPackage.eNS_URI);
		accessesPackage theaccessesPackage = (accessesPackage)EPackage.Registry.INSTANCE.getEPackage(accessesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(providedServiceEClass, ProvidedService.class, "ProvidedService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProvidedService_GastMethod(), thefunctionsPackage.getMethod(), null, "gastMethod", null, 1, 1, ProvidedService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requiredServiceEClass, RequiredService.class, "RequiredService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRequiredService_GastMethod(), theaccessesPackage.getFunctionAccess(), null, "gastMethod", null, 1, 1, RequiredService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(provReqRepositoryEClass, ProvReqRepository.class, "ProvReqRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProvReqRepository_ProvidedServices(), this.getProvidedService(), null, "providedServices", null, 0, -1, ProvReqRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvReqRepository_RequiredServices(), this.getRequiredService(), null, "requiredServices", null, 0, -1, ProvReqRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvReqRepository_Name(), ecorePackage.getEString(), "name", null, 0, 1, ProvReqRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ProvreqidPackageImpl
