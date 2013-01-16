/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.sourcecodedecorator.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionPackage;
import org.eclipse.gmt.modisco.omg.kdm.build.BuildPackage;
import org.eclipse.gmt.modisco.omg.kdm.code.CodePackage;
import org.eclipse.gmt.modisco.omg.kdm.conceptual.ConceptualPackage;
import org.eclipse.gmt.modisco.omg.kdm.core.CorePackage;
import org.eclipse.gmt.modisco.omg.kdm.data.DataPackage;
import org.eclipse.gmt.modisco.omg.kdm.event.EventPackage;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KdmPackage;
import org.eclipse.gmt.modisco.omg.kdm.platform.PlatformPackage;
import org.eclipse.gmt.modisco.omg.kdm.source.SourcePackage;
import org.eclipse.gmt.modisco.omg.kdm.structure.StructurePackage;
import org.eclipse.gmt.modisco.omg.kdm.ui.UiPackage;

import eu.qimpress.samm.staticstructure.StaticstructurePackage;
import eu.qimpress.seff.seffPackage;
import eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink;
import eu.qimpress.sourcecodedecorator.ControlFlowLevelSourceCodeLink;
import eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink;
import eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink;
import eu.qimpress.sourcecodedecorator.MethodLevelSourceCodeLink;
import eu.qimpress.sourcecodedecorator.SammSystemImplementatingClassesLink;
import eu.qimpress.sourcecodedecorator.SourceCodeDecoratorFactory;
import eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage;
import eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SourceCodeDecoratorPackageImpl extends EPackageImpl implements SourceCodeDecoratorPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileLevelSourceCodeLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodLevelSourceCodeLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass controlFlowLevelSourceCodeLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sourceCodeDecoratorRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interfaceSourceCodeLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentImplementingClassesLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sammSystemImplementatingClassesLinkEClass = null;

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
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SourceCodeDecoratorPackageImpl() {
		super(eNS_URI, SourceCodeDecoratorFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link SourceCodeDecoratorPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SourceCodeDecoratorPackage init() {
		if (isInited) return (SourceCodeDecoratorPackage)EPackage.Registry.INSTANCE.getEPackage(SourceCodeDecoratorPackage.eNS_URI);

		// Obtain or create and register package
		SourceCodeDecoratorPackageImpl theSourceCodeDecoratorPackage = (SourceCodeDecoratorPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SourceCodeDecoratorPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SourceCodeDecoratorPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JavaPackage.eINSTANCE.eClass();
		CorePackage.eINSTANCE.eClass();
		KdmPackage.eINSTANCE.eClass();
		SourcePackage.eINSTANCE.eClass();
		CodePackage.eINSTANCE.eClass();
		ActionPackage.eINSTANCE.eClass();
		PlatformPackage.eINSTANCE.eClass();
		BuildPackage.eINSTANCE.eClass();
		ConceptualPackage.eINSTANCE.eClass();
		DataPackage.eINSTANCE.eClass();
		EventPackage.eINSTANCE.eClass();
		StructurePackage.eINSTANCE.eClass();
		UiPackage.eINSTANCE.eClass();
		seffPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSourceCodeDecoratorPackage.createPackageContents();

		// Initialize created meta-data
		theSourceCodeDecoratorPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSourceCodeDecoratorPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SourceCodeDecoratorPackage.eNS_URI, theSourceCodeDecoratorPackage);
		return theSourceCodeDecoratorPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFileLevelSourceCodeLink() {
		return fileLevelSourceCodeLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFileLevelSourceCodeLink_ComponentType() {
		return (EReference)fileLevelSourceCodeLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFileLevelSourceCodeLink_File() {
		return (EReference)fileLevelSourceCodeLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodLevelSourceCodeLink() {
		return methodLevelSourceCodeLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodLevelSourceCodeLink_Operation() {
		return (EReference)methodLevelSourceCodeLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodLevelSourceCodeLink_Function() {
		return (EReference)methodLevelSourceCodeLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getControlFlowLevelSourceCodeLink() {
		return controlFlowLevelSourceCodeLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getControlFlowLevelSourceCodeLink_AbstractAction() {
		return (EReference)controlFlowLevelSourceCodeLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getControlFlowLevelSourceCodeLink_Statement() {
		return (EReference)controlFlowLevelSourceCodeLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSourceCodeDecoratorRepository() {
		return sourceCodeDecoratorRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSourceCodeDecoratorRepository_FileLevelSourceCodeLink() {
		return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSourceCodeDecoratorRepository_MethodLevelSourceCodeLink() {
		return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSourceCodeDecoratorRepository_ControlFlowLevelSourceCodeLink() {
		return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSourceCodeDecoratorRepository_InterfaceSourceCodeLink() {
		return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSourceCodeDecoratorRepository_ComponentImplementingClassesLink() {
		return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterfaceSourceCodeLink() {
		return interfaceSourceCodeLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterfaceSourceCodeLink_Interface() {
		return (EReference)interfaceSourceCodeLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterfaceSourceCodeLink_GastClass() {
		return (EReference)interfaceSourceCodeLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentImplementingClassesLink() {
		return componentImplementingClassesLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponentImplementingClassesLink_IsCompositeComponent() {
		return (EAttribute)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentImplementingClassesLink_Component() {
		return (EReference)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentImplementingClassesLink_ImplementingClasses() {
		return (EReference)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentImplementingClassesLink_SubComponents() {
		return (EReference)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentImplementingClassesLink_ProvidedInterfaces() {
		return (EReference)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentImplementingClassesLink_RequiredInterfaces() {
		return (EReference)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponentImplementingClassesLink_IsInitialComponent() {
		return (EAttribute)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSammSystemImplementatingClassesLink() {
		return sammSystemImplementatingClassesLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSammSystemImplementatingClassesLink_ServiceArchitectureModel() {
		return (EReference)sammSystemImplementatingClassesLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceCodeDecoratorFactory getSourceCodeDecoratorFactory() {
		return (SourceCodeDecoratorFactory)getEFactoryInstance();
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
		fileLevelSourceCodeLinkEClass = createEClass(FILE_LEVEL_SOURCE_CODE_LINK);
		createEReference(fileLevelSourceCodeLinkEClass, FILE_LEVEL_SOURCE_CODE_LINK__COMPONENT_TYPE);
		createEReference(fileLevelSourceCodeLinkEClass, FILE_LEVEL_SOURCE_CODE_LINK__FILE);

		methodLevelSourceCodeLinkEClass = createEClass(METHOD_LEVEL_SOURCE_CODE_LINK);
		createEReference(methodLevelSourceCodeLinkEClass, METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION);
		createEReference(methodLevelSourceCodeLinkEClass, METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION);

		controlFlowLevelSourceCodeLinkEClass = createEClass(CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK);
		createEReference(controlFlowLevelSourceCodeLinkEClass, CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION);
		createEReference(controlFlowLevelSourceCodeLinkEClass, CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT);

		sourceCodeDecoratorRepositoryEClass = createEClass(SOURCE_CODE_DECORATOR_REPOSITORY);
		createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK);
		createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK);
		createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK);
		createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK);
		createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK);

		interfaceSourceCodeLinkEClass = createEClass(INTERFACE_SOURCE_CODE_LINK);
		createEReference(interfaceSourceCodeLinkEClass, INTERFACE_SOURCE_CODE_LINK__INTERFACE);
		createEReference(interfaceSourceCodeLinkEClass, INTERFACE_SOURCE_CODE_LINK__GAST_CLASS);

		componentImplementingClassesLinkEClass = createEClass(COMPONENT_IMPLEMENTING_CLASSES_LINK);
		createEAttribute(componentImplementingClassesLinkEClass, COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_COMPOSITE_COMPONENT);
		createEReference(componentImplementingClassesLinkEClass, COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT);
		createEReference(componentImplementingClassesLinkEClass, COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES);
		createEReference(componentImplementingClassesLinkEClass, COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS);
		createEReference(componentImplementingClassesLinkEClass, COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES);
		createEReference(componentImplementingClassesLinkEClass, COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES);
		createEAttribute(componentImplementingClassesLinkEClass, COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT);

		sammSystemImplementatingClassesLinkEClass = createEClass(SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK);
		createEReference(sammSystemImplementatingClassesLinkEClass, SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SERVICE_ARCHITECTURE_MODEL);
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
		StaticstructurePackage theStaticstructurePackage = (StaticstructurePackage)EPackage.Registry.INSTANCE.getEPackage(StaticstructurePackage.eNS_URI);
		SourcePackage theSourcePackage = (SourcePackage)EPackage.Registry.INSTANCE.getEPackage(SourcePackage.eNS_URI);
		JavaPackage theJavaPackage = (JavaPackage)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI);
		seffPackage theseffPackage = (seffPackage)EPackage.Registry.INSTANCE.getEPackage(seffPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		methodLevelSourceCodeLinkEClass.getESuperTypes().add(this.getFileLevelSourceCodeLink());
		controlFlowLevelSourceCodeLinkEClass.getESuperTypes().add(this.getMethodLevelSourceCodeLink());
		sammSystemImplementatingClassesLinkEClass.getESuperTypes().add(this.getComponentImplementingClassesLink());

		// Initialize classes and features; add operations and parameters
		initEClass(fileLevelSourceCodeLinkEClass, FileLevelSourceCodeLink.class, "FileLevelSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFileLevelSourceCodeLink_ComponentType(), theStaticstructurePackage.getComponentType(), null, "componentType", null, 0, 1, FileLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getFileLevelSourceCodeLink_File(), theSourcePackage.getSourceFile(), null, "file", null, 0, 1, FileLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(methodLevelSourceCodeLinkEClass, MethodLevelSourceCodeLink.class, "MethodLevelSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMethodLevelSourceCodeLink_Operation(), theStaticstructurePackage.getOperation(), null, "operation", null, 0, 1, MethodLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMethodLevelSourceCodeLink_Function(), theJavaPackage.getAbstractMethodDeclaration(), null, "function", null, 0, 1, MethodLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(controlFlowLevelSourceCodeLinkEClass, ControlFlowLevelSourceCodeLink.class, "ControlFlowLevelSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getControlFlowLevelSourceCodeLink_AbstractAction(), theseffPackage.getAbstractAction(), null, "abstractAction", null, 0, 1, ControlFlowLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getControlFlowLevelSourceCodeLink_Statement(), theJavaPackage.getStatement(), null, "statement", null, 0, 1, ControlFlowLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(sourceCodeDecoratorRepositoryEClass, SourceCodeDecoratorRepository.class, "SourceCodeDecoratorRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSourceCodeDecoratorRepository_FileLevelSourceCodeLink(), this.getFileLevelSourceCodeLink(), null, "fileLevelSourceCodeLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSourceCodeDecoratorRepository_MethodLevelSourceCodeLink(), this.getMethodLevelSourceCodeLink(), null, "methodLevelSourceCodeLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSourceCodeDecoratorRepository_ControlFlowLevelSourceCodeLink(), this.getControlFlowLevelSourceCodeLink(), null, "controlFlowLevelSourceCodeLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSourceCodeDecoratorRepository_InterfaceSourceCodeLink(), this.getInterfaceSourceCodeLink(), null, "interfaceSourceCodeLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSourceCodeDecoratorRepository_ComponentImplementingClassesLink(), this.getComponentImplementingClassesLink(), null, "componentImplementingClassesLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(interfaceSourceCodeLinkEClass, InterfaceSourceCodeLink.class, "InterfaceSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInterfaceSourceCodeLink_Interface(), theStaticstructurePackage.getInterface(), null, "interface", null, 0, 1, InterfaceSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInterfaceSourceCodeLink_GastClass(), theJavaPackage.getType(), null, "gastClass", null, 0, 1, InterfaceSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(componentImplementingClassesLinkEClass, ComponentImplementingClassesLink.class, "ComponentImplementingClassesLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentImplementingClassesLink_IsCompositeComponent(), ecorePackage.getEBoolean(), "isCompositeComponent", null, 1, 1, ComponentImplementingClassesLink.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentImplementingClassesLink_Component(), theStaticstructurePackage.getComponentType(), null, "component", null, 1, 1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentImplementingClassesLink_ImplementingClasses(), theJavaPackage.getType(), null, "implementingClasses", null, 1, -1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentImplementingClassesLink_SubComponents(), this.getComponentImplementingClassesLink(), null, "subComponents", null, 0, -1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentImplementingClassesLink_ProvidedInterfaces(), this.getInterfaceSourceCodeLink(), null, "providedInterfaces", null, 0, -1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentImplementingClassesLink_RequiredInterfaces(), this.getInterfaceSourceCodeLink(), null, "requiredInterfaces", null, 0, -1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getComponentImplementingClassesLink_IsInitialComponent(), ecorePackage.getEBoolean(), "isInitialComponent", null, 1, 1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(sammSystemImplementatingClassesLinkEClass, SammSystemImplementatingClassesLink.class, "SammSystemImplementatingClassesLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSammSystemImplementatingClassesLink_ServiceArchitectureModel(), theStaticstructurePackage.getServiceArchitectureModel(), null, "serviceArchitectureModel", null, 0, 1, SammSystemImplementatingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //SourceCodeDecoratorPackageImpl
