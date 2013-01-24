/**
 */
package org.somox.qimpressgast.impl;

import eu.qimpress.samm.annotation.AnnotationPackage;

import eu.qimpress.samm.behaviour.BehaviourPackage;

import eu.qimpress.samm.core.CorePackage;

import eu.qimpress.samm.datatypes.DatatypesPackage;

import eu.qimpress.samm.deployment.allocation.AllocationPackage;

import eu.qimpress.samm.deployment.hardware.HardwarePackage;

import eu.qimpress.samm.deployment.targetenvironment.TargetenvironmentPackage;

import eu.qimpress.samm.qosannotation.QosannotationPackage;

import eu.qimpress.samm.staticstructure.StaticstructurePackage;

import eu.qimpress.samm.usagemodel.UsagemodelPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gmt.modisco.java.emf.JavaPackage;

import org.somox.qimpressgast.GASTBehaviour;
import org.somox.qimpressgast.GASTBehaviourRepository;
import org.somox.qimpressgast.somoxgastFactory;
import org.somox.qimpressgast.somoxgastPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class somoxgastPackageImpl extends EPackageImpl implements somoxgastPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gastBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gastBehaviourRepositoryEClass = null;

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
	 * @see org.somox.qimpressgast.somoxgastPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private somoxgastPackageImpl() {
		super(eNS_URI, somoxgastFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link somoxgastPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static somoxgastPackage init() {
		if (isInited) return (somoxgastPackage)EPackage.Registry.INSTANCE.getEPackage(somoxgastPackage.eNS_URI);

		// Obtain or create and register package
		somoxgastPackageImpl thesomoxgastPackage = (somoxgastPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof somoxgastPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new somoxgastPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JavaPackage.eINSTANCE.eClass();
		StaticstructurePackage.eINSTANCE.eClass();
		CorePackage.eINSTANCE.eClass();
		DatatypesPackage.eINSTANCE.eClass();
		BehaviourPackage.eINSTANCE.eClass();
		TargetenvironmentPackage.eINSTANCE.eClass();
		HardwarePackage.eINSTANCE.eClass();
		AllocationPackage.eINSTANCE.eClass();
		AnnotationPackage.eINSTANCE.eClass();
		UsagemodelPackage.eINSTANCE.eClass();
		QosannotationPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thesomoxgastPackage.createPackageContents();

		// Initialize created meta-data
		thesomoxgastPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thesomoxgastPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(somoxgastPackage.eNS_URI, thesomoxgastPackage);
		return thesomoxgastPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGASTBehaviour() {
		return gastBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGASTBehaviour_Blockstatement() {
		return (EReference)gastBehaviourEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGASTBehaviour_Gastbehaviourstub() {
		return (EReference)gastBehaviourEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGASTBehaviourRepository() {
		return gastBehaviourRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGASTBehaviourRepository_Gastbehaviour() {
		return (EReference)gastBehaviourRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public somoxgastFactory getsomoxgastFactory() {
		return (somoxgastFactory)getEFactoryInstance();
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
		gastBehaviourEClass = createEClass(GAST_BEHAVIOUR);
		createEReference(gastBehaviourEClass, GAST_BEHAVIOUR__BLOCKSTATEMENT);
		createEReference(gastBehaviourEClass, GAST_BEHAVIOUR__GASTBEHAVIOURSTUB);

		gastBehaviourRepositoryEClass = createEClass(GAST_BEHAVIOUR_REPOSITORY);
		createEReference(gastBehaviourRepositoryEClass, GAST_BEHAVIOUR_REPOSITORY__GASTBEHAVIOUR);
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
		JavaPackage theJavaPackage = (JavaPackage)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI);
		BehaviourPackage theBehaviourPackage = (BehaviourPackage)EPackage.Registry.INSTANCE.getEPackage(BehaviourPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(gastBehaviourEClass, GASTBehaviour.class, "GASTBehaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGASTBehaviour_Blockstatement(), theJavaPackage.getBlock(), null, "blockstatement", null, 1, 1, GASTBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getGASTBehaviour_Gastbehaviourstub(), theBehaviourPackage.getGastBehaviourStub(), null, "gastbehaviourstub", null, 0, 1, GASTBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(gastBehaviourRepositoryEClass, GASTBehaviourRepository.class, "GASTBehaviourRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGASTBehaviourRepository_Gastbehaviour(), this.getGASTBehaviour(), null, "gastbehaviour", null, 0, -1, GASTBehaviourRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //somoxgastPackageImpl
