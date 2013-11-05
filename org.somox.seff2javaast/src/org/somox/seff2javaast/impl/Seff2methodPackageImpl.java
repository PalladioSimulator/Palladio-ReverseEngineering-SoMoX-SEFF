/**
 */
package org.somox.seff2javaast.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.somox.seff2javaast.SEFF2JavaAST;
import org.somox.seff2javaast.SEFF2MethodMapping;
import org.somox.seff2javaast.Seff2methodFactory;
import org.somox.seff2javaast.Seff2methodPackage;

import de.uka.ipd.sdq.pcm.PcmPackage;
import de.uka.ipd.sdq.pcm.seff.SeffPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Seff2methodPackageImpl extends EPackageImpl implements Seff2methodPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass seff2MethodMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass seff2JavaASTEClass = null;

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
	 * @see org.somox.seff2javaast.Seff2methodPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Seff2methodPackageImpl() {
		super(eNS_URI, Seff2methodFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Seff2methodPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Seff2methodPackage init() {
		if (isInited) return (Seff2methodPackage)EPackage.Registry.INSTANCE.getEPackage(Seff2methodPackage.eNS_URI);

		// Obtain or create and register package
		Seff2methodPackageImpl theSeff2methodPackage = (Seff2methodPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Seff2methodPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Seff2methodPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JavaPackage.eINSTANCE.eClass();
		PcmPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSeff2methodPackage.createPackageContents();

		// Initialize created meta-data
		theSeff2methodPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSeff2methodPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Seff2methodPackage.eNS_URI, theSeff2methodPackage);
		return theSeff2methodPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSEFF2MethodMapping() {
		return seff2MethodMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSEFF2MethodMapping_Blockstatement() {
		return (EReference)seff2MethodMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSEFF2MethodMapping_Seff() {
		return (EReference)seff2MethodMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSEFF2JavaAST() {
		return seff2JavaASTEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSEFF2JavaAST_Seff2MethodMappings() {
		return (EReference)seff2JavaASTEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Seff2methodFactory getSeff2methodFactory() {
		return (Seff2methodFactory)getEFactoryInstance();
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
		seff2MethodMappingEClass = createEClass(SEFF2_METHOD_MAPPING);
		createEReference(seff2MethodMappingEClass, SEFF2_METHOD_MAPPING__BLOCKSTATEMENT);
		createEReference(seff2MethodMappingEClass, SEFF2_METHOD_MAPPING__SEFF);

		seff2JavaASTEClass = createEClass(SEFF2_JAVA_AST);
		createEReference(seff2JavaASTEClass, SEFF2_JAVA_AST__SEFF2_METHOD_MAPPINGS);
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
		SeffPackage theSeffPackage = (SeffPackage)EPackage.Registry.INSTANCE.getEPackage(SeffPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(seff2MethodMappingEClass, SEFF2MethodMapping.class, "SEFF2MethodMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSEFF2MethodMapping_Blockstatement(), theJavaPackage.getBlock(), null, "blockstatement", null, 1, 1, SEFF2MethodMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSEFF2MethodMapping_Seff(), theSeffPackage.getServiceEffectSpecification(), null, "seff", null, 1, 1, SEFF2MethodMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(seff2JavaASTEClass, SEFF2JavaAST.class, "SEFF2JavaAST", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSEFF2JavaAST_Seff2MethodMappings(), this.getSEFF2MethodMapping(), null, "seff2MethodMappings", null, 0, -1, SEFF2JavaAST.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //Seff2methodPackageImpl
