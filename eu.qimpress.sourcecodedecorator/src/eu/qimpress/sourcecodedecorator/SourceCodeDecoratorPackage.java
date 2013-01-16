/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.sourcecodedecorator;

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
 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorFactory
 * @model kind="package"
 * @generated
 */
public interface SourceCodeDecoratorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sourcecodedecorator";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://q-impress.eu/sourcecodedecorator";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sourcecodedecorator";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SourceCodeDecoratorPackage eINSTANCE = eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.qimpress.sourcecodedecorator.impl.FileLevelSourceCodeLinkImpl <em>File Level Source Code Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.qimpress.sourcecodedecorator.impl.FileLevelSourceCodeLinkImpl
	 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getFileLevelSourceCodeLink()
	 * @generated
	 */
	int FILE_LEVEL_SOURCE_CODE_LINK = 0;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_LEVEL_SOURCE_CODE_LINK__COMPONENT_TYPE = 0;

	/**
	 * The feature id for the '<em><b>File</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_LEVEL_SOURCE_CODE_LINK__FILE = 1;

	/**
	 * The number of structural features of the '<em>File Level Source Code Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.qimpress.sourcecodedecorator.impl.MethodLevelSourceCodeLinkImpl <em>Method Level Source Code Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.qimpress.sourcecodedecorator.impl.MethodLevelSourceCodeLinkImpl
	 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getMethodLevelSourceCodeLink()
	 * @generated
	 */
	int METHOD_LEVEL_SOURCE_CODE_LINK = 1;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LEVEL_SOURCE_CODE_LINK__COMPONENT_TYPE = FILE_LEVEL_SOURCE_CODE_LINK__COMPONENT_TYPE;

	/**
	 * The feature id for the '<em><b>File</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LEVEL_SOURCE_CODE_LINK__FILE = FILE_LEVEL_SOURCE_CODE_LINK__FILE;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION = FILE_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION = FILE_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Method Level Source Code Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT = FILE_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.qimpress.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl <em>Control Flow Level Source Code Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.qimpress.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl
	 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getControlFlowLevelSourceCodeLink()
	 * @generated
	 */
	int CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK = 2;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__COMPONENT_TYPE = METHOD_LEVEL_SOURCE_CODE_LINK__COMPONENT_TYPE;

	/**
	 * The feature id for the '<em><b>File</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__FILE = METHOD_LEVEL_SOURCE_CODE_LINK__FILE;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__OPERATION = METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION;

	/**
	 * The feature id for the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__FUNCTION = METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION;

	/**
	 * The feature id for the '<em><b>Abstract Action</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION = METHOD_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT = METHOD_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Control Flow Level Source Code Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT = METHOD_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl <em>Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl
	 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getSourceCodeDecoratorRepository()
	 * @generated
	 */
	int SOURCE_CODE_DECORATOR_REPOSITORY = 3;

	/**
	 * The feature id for the '<em><b>File Level Source Code Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK = 0;

	/**
	 * The feature id for the '<em><b>Method Level Source Code Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK = 1;

	/**
	 * The feature id for the '<em><b>Control Flow Level Source Code Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK = 2;

	/**
	 * The feature id for the '<em><b>Interface Source Code Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK = 3;

	/**
	 * The feature id for the '<em><b>Component Implementing Classes Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK = 4;

	/**
	 * The number of structural features of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_CODE_DECORATOR_REPOSITORY_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link eu.qimpress.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl <em>Interface Source Code Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.qimpress.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl
	 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getInterfaceSourceCodeLink()
	 * @generated
	 */
	int INTERFACE_SOURCE_CODE_LINK = 4;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_SOURCE_CODE_LINK__INTERFACE = 0;

	/**
	 * The feature id for the '<em><b>Gast Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_SOURCE_CODE_LINK__GAST_CLASS = 1;

	/**
	 * The number of structural features of the '<em>Interface Source Code Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_SOURCE_CODE_LINK_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.qimpress.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl <em>Component Implementing Classes Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.qimpress.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl
	 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getComponentImplementingClassesLink()
	 * @generated
	 */
	int COMPONENT_IMPLEMENTING_CLASSES_LINK = 5;

	/**
	 * The feature id for the '<em><b>Is Composite Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_COMPOSITE_COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Implementing Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES = 2;

	/**
	 * The feature id for the '<em><b>Sub Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS = 3;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES = 4;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES = 5;

	/**
	 * The feature id for the '<em><b>Is Initial Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT = 6;

	/**
	 * The number of structural features of the '<em>Component Implementing Classes Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_IMPLEMENTING_CLASSES_LINK_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link eu.qimpress.sourcecodedecorator.impl.SammSystemImplementatingClassesLinkImpl <em>Samm System Implementating Classes Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.qimpress.sourcecodedecorator.impl.SammSystemImplementatingClassesLinkImpl
	 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getSammSystemImplementatingClassesLink()
	 * @generated
	 */
	int SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK = 6;

	/**
	 * The feature id for the '<em><b>Is Composite Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__IS_COMPOSITE_COMPONENT = COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_COMPOSITE_COMPONENT;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__COMPONENT = COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT;

	/**
	 * The feature id for the '<em><b>Implementing Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__IMPLEMENTING_CLASSES = COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES;

	/**
	 * The feature id for the '<em><b>Sub Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SUB_COMPONENTS = COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__PROVIDED_INTERFACES = COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__REQUIRED_INTERFACES = COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES;

	/**
	 * The feature id for the '<em><b>Is Initial Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__IS_INITIAL_COMPONENT = COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT;

	/**
	 * The feature id for the '<em><b>Service Architecture Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SERVICE_ARCHITECTURE_MODEL = COMPONENT_IMPLEMENTING_CLASSES_LINK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Samm System Implementating Classes Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK_FEATURE_COUNT = COMPONENT_IMPLEMENTING_CLASSES_LINK_FEATURE_COUNT + 1;

	/**
	 * Returns the meta object for class '{@link eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink <em>File Level Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Level Source Code Link</em>'.
	 * @see eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink
	 * @generated
	 */
	EClass getFileLevelSourceCodeLink();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink#getComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component Type</em>'.
	 * @see eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink#getComponentType()
	 * @see #getFileLevelSourceCodeLink()
	 * @generated
	 */
	EReference getFileLevelSourceCodeLink_ComponentType();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>File</em>'.
	 * @see eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink#getFile()
	 * @see #getFileLevelSourceCodeLink()
	 * @generated
	 */
	EReference getFileLevelSourceCodeLink_File();

	/**
	 * Returns the meta object for class '{@link eu.qimpress.sourcecodedecorator.MethodLevelSourceCodeLink <em>Method Level Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Level Source Code Link</em>'.
	 * @see eu.qimpress.sourcecodedecorator.MethodLevelSourceCodeLink
	 * @generated
	 */
	EClass getMethodLevelSourceCodeLink();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.sourcecodedecorator.MethodLevelSourceCodeLink#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation</em>'.
	 * @see eu.qimpress.sourcecodedecorator.MethodLevelSourceCodeLink#getOperation()
	 * @see #getMethodLevelSourceCodeLink()
	 * @generated
	 */
	EReference getMethodLevelSourceCodeLink_Operation();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.sourcecodedecorator.MethodLevelSourceCodeLink#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Function</em>'.
	 * @see eu.qimpress.sourcecodedecorator.MethodLevelSourceCodeLink#getFunction()
	 * @see #getMethodLevelSourceCodeLink()
	 * @generated
	 */
	EReference getMethodLevelSourceCodeLink_Function();

	/**
	 * Returns the meta object for class '{@link eu.qimpress.sourcecodedecorator.ControlFlowLevelSourceCodeLink <em>Control Flow Level Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Flow Level Source Code Link</em>'.
	 * @see eu.qimpress.sourcecodedecorator.ControlFlowLevelSourceCodeLink
	 * @generated
	 */
	EClass getControlFlowLevelSourceCodeLink();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getAbstractAction <em>Abstract Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Abstract Action</em>'.
	 * @see eu.qimpress.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getAbstractAction()
	 * @see #getControlFlowLevelSourceCodeLink()
	 * @generated
	 */
	EReference getControlFlowLevelSourceCodeLink_AbstractAction();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getStatement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Statement</em>'.
	 * @see eu.qimpress.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getStatement()
	 * @see #getControlFlowLevelSourceCodeLink()
	 * @generated
	 */
	EReference getControlFlowLevelSourceCodeLink_Statement();

	/**
	 * Returns the meta object for class '{@link eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Repository</em>'.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository
	 * @generated
	 */
	EClass getSourceCodeDecoratorRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository#getFileLevelSourceCodeLink <em>File Level Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>File Level Source Code Link</em>'.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository#getFileLevelSourceCodeLink()
	 * @see #getSourceCodeDecoratorRepository()
	 * @generated
	 */
	EReference getSourceCodeDecoratorRepository_FileLevelSourceCodeLink();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository#getMethodLevelSourceCodeLink <em>Method Level Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Level Source Code Link</em>'.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository#getMethodLevelSourceCodeLink()
	 * @see #getSourceCodeDecoratorRepository()
	 * @generated
	 */
	EReference getSourceCodeDecoratorRepository_MethodLevelSourceCodeLink();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository#getControlFlowLevelSourceCodeLink <em>Control Flow Level Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Control Flow Level Source Code Link</em>'.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository#getControlFlowLevelSourceCodeLink()
	 * @see #getSourceCodeDecoratorRepository()
	 * @generated
	 */
	EReference getSourceCodeDecoratorRepository_ControlFlowLevelSourceCodeLink();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository#getInterfaceSourceCodeLink <em>Interface Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interface Source Code Link</em>'.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository#getInterfaceSourceCodeLink()
	 * @see #getSourceCodeDecoratorRepository()
	 * @generated
	 */
	EReference getSourceCodeDecoratorRepository_InterfaceSourceCodeLink();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository#getComponentImplementingClassesLink <em>Component Implementing Classes Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Component Implementing Classes Link</em>'.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository#getComponentImplementingClassesLink()
	 * @see #getSourceCodeDecoratorRepository()
	 * @generated
	 */
	EReference getSourceCodeDecoratorRepository_ComponentImplementingClassesLink();

	/**
	 * Returns the meta object for class '{@link eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink <em>Interface Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface Source Code Link</em>'.
	 * @see eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink
	 * @generated
	 */
	EClass getInterfaceSourceCodeLink();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink#getInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Interface</em>'.
	 * @see eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink#getInterface()
	 * @see #getInterfaceSourceCodeLink()
	 * @generated
	 */
	EReference getInterfaceSourceCodeLink_Interface();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink#getGastClass <em>Gast Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gast Class</em>'.
	 * @see eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink#getGastClass()
	 * @see #getInterfaceSourceCodeLink()
	 * @generated
	 */
	EReference getInterfaceSourceCodeLink_GastClass();

	/**
	 * Returns the meta object for class '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink <em>Component Implementing Classes Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Implementing Classes Link</em>'.
	 * @see eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink
	 * @generated
	 */
	EClass getComponentImplementingClassesLink();

	/**
	 * Returns the meta object for the attribute '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#isCompositeComponent <em>Is Composite Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Composite Component</em>'.
	 * @see eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#isCompositeComponent()
	 * @see #getComponentImplementingClassesLink()
	 * @generated
	 */
	EAttribute getComponentImplementingClassesLink_IsCompositeComponent();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getComponent()
	 * @see #getComponentImplementingClassesLink()
	 * @generated
	 */
	EReference getComponentImplementingClassesLink_Component();

	/**
	 * Returns the meta object for the reference list '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getImplementingClasses <em>Implementing Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Implementing Classes</em>'.
	 * @see eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getImplementingClasses()
	 * @see #getComponentImplementingClassesLink()
	 * @generated
	 */
	EReference getComponentImplementingClassesLink_ImplementingClasses();

	/**
	 * Returns the meta object for the reference list '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getSubComponents <em>Sub Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub Components</em>'.
	 * @see eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getSubComponents()
	 * @see #getComponentImplementingClassesLink()
	 * @generated
	 */
	EReference getComponentImplementingClassesLink_SubComponents();

	/**
	 * Returns the meta object for the reference list '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getProvidedInterfaces <em>Provided Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Provided Interfaces</em>'.
	 * @see eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getProvidedInterfaces()
	 * @see #getComponentImplementingClassesLink()
	 * @generated
	 */
	EReference getComponentImplementingClassesLink_ProvidedInterfaces();

	/**
	 * Returns the meta object for the reference list '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getRequiredInterfaces <em>Required Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Required Interfaces</em>'.
	 * @see eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getRequiredInterfaces()
	 * @see #getComponentImplementingClassesLink()
	 * @generated
	 */
	EReference getComponentImplementingClassesLink_RequiredInterfaces();

	/**
	 * Returns the meta object for the attribute '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#isInitialComponent <em>Is Initial Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Initial Component</em>'.
	 * @see eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#isInitialComponent()
	 * @see #getComponentImplementingClassesLink()
	 * @generated
	 */
	EAttribute getComponentImplementingClassesLink_IsInitialComponent();

	/**
	 * Returns the meta object for class '{@link eu.qimpress.sourcecodedecorator.SammSystemImplementatingClassesLink <em>Samm System Implementating Classes Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Samm System Implementating Classes Link</em>'.
	 * @see eu.qimpress.sourcecodedecorator.SammSystemImplementatingClassesLink
	 * @generated
	 */
	EClass getSammSystemImplementatingClassesLink();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.sourcecodedecorator.SammSystemImplementatingClassesLink#getServiceArchitectureModel <em>Service Architecture Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Service Architecture Model</em>'.
	 * @see eu.qimpress.sourcecodedecorator.SammSystemImplementatingClassesLink#getServiceArchitectureModel()
	 * @see #getSammSystemImplementatingClassesLink()
	 * @generated
	 */
	EReference getSammSystemImplementatingClassesLink_ServiceArchitectureModel();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SourceCodeDecoratorFactory getSourceCodeDecoratorFactory();

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
		 * The meta object literal for the '{@link eu.qimpress.sourcecodedecorator.impl.FileLevelSourceCodeLinkImpl <em>File Level Source Code Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.qimpress.sourcecodedecorator.impl.FileLevelSourceCodeLinkImpl
		 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getFileLevelSourceCodeLink()
		 * @generated
		 */
		EClass FILE_LEVEL_SOURCE_CODE_LINK = eINSTANCE.getFileLevelSourceCodeLink();

		/**
		 * The meta object literal for the '<em><b>Component Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILE_LEVEL_SOURCE_CODE_LINK__COMPONENT_TYPE = eINSTANCE.getFileLevelSourceCodeLink_ComponentType();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILE_LEVEL_SOURCE_CODE_LINK__FILE = eINSTANCE.getFileLevelSourceCodeLink_File();

		/**
		 * The meta object literal for the '{@link eu.qimpress.sourcecodedecorator.impl.MethodLevelSourceCodeLinkImpl <em>Method Level Source Code Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.qimpress.sourcecodedecorator.impl.MethodLevelSourceCodeLinkImpl
		 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getMethodLevelSourceCodeLink()
		 * @generated
		 */
		EClass METHOD_LEVEL_SOURCE_CODE_LINK = eINSTANCE.getMethodLevelSourceCodeLink();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION = eINSTANCE.getMethodLevelSourceCodeLink_Operation();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION = eINSTANCE.getMethodLevelSourceCodeLink_Function();

		/**
		 * The meta object literal for the '{@link eu.qimpress.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl <em>Control Flow Level Source Code Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.qimpress.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl
		 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getControlFlowLevelSourceCodeLink()
		 * @generated
		 */
		EClass CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK = eINSTANCE.getControlFlowLevelSourceCodeLink();

		/**
		 * The meta object literal for the '<em><b>Abstract Action</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION = eINSTANCE.getControlFlowLevelSourceCodeLink_AbstractAction();

		/**
		 * The meta object literal for the '<em><b>Statement</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT = eINSTANCE.getControlFlowLevelSourceCodeLink_Statement();

		/**
		 * The meta object literal for the '{@link eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl <em>Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl
		 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getSourceCodeDecoratorRepository()
		 * @generated
		 */
		EClass SOURCE_CODE_DECORATOR_REPOSITORY = eINSTANCE.getSourceCodeDecoratorRepository();

		/**
		 * The meta object literal for the '<em><b>File Level Source Code Link</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK = eINSTANCE.getSourceCodeDecoratorRepository_FileLevelSourceCodeLink();

		/**
		 * The meta object literal for the '<em><b>Method Level Source Code Link</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK = eINSTANCE.getSourceCodeDecoratorRepository_MethodLevelSourceCodeLink();

		/**
		 * The meta object literal for the '<em><b>Control Flow Level Source Code Link</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK = eINSTANCE.getSourceCodeDecoratorRepository_ControlFlowLevelSourceCodeLink();

		/**
		 * The meta object literal for the '<em><b>Interface Source Code Link</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK = eINSTANCE.getSourceCodeDecoratorRepository_InterfaceSourceCodeLink();

		/**
		 * The meta object literal for the '<em><b>Component Implementing Classes Link</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK = eINSTANCE.getSourceCodeDecoratorRepository_ComponentImplementingClassesLink();

		/**
		 * The meta object literal for the '{@link eu.qimpress.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl <em>Interface Source Code Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.qimpress.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl
		 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getInterfaceSourceCodeLink()
		 * @generated
		 */
		EClass INTERFACE_SOURCE_CODE_LINK = eINSTANCE.getInterfaceSourceCodeLink();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERFACE_SOURCE_CODE_LINK__INTERFACE = eINSTANCE.getInterfaceSourceCodeLink_Interface();

		/**
		 * The meta object literal for the '<em><b>Gast Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERFACE_SOURCE_CODE_LINK__GAST_CLASS = eINSTANCE.getInterfaceSourceCodeLink_GastClass();

		/**
		 * The meta object literal for the '{@link eu.qimpress.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl <em>Component Implementing Classes Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.qimpress.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl
		 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getComponentImplementingClassesLink()
		 * @generated
		 */
		EClass COMPONENT_IMPLEMENTING_CLASSES_LINK = eINSTANCE.getComponentImplementingClassesLink();

		/**
		 * The meta object literal for the '<em><b>Is Composite Component</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_COMPOSITE_COMPONENT = eINSTANCE.getComponentImplementingClassesLink_IsCompositeComponent();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT = eINSTANCE.getComponentImplementingClassesLink_Component();

		/**
		 * The meta object literal for the '<em><b>Implementing Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES = eINSTANCE.getComponentImplementingClassesLink_ImplementingClasses();

		/**
		 * The meta object literal for the '<em><b>Sub Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS = eINSTANCE.getComponentImplementingClassesLink_SubComponents();

		/**
		 * The meta object literal for the '<em><b>Provided Interfaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES = eINSTANCE.getComponentImplementingClassesLink_ProvidedInterfaces();

		/**
		 * The meta object literal for the '<em><b>Required Interfaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES = eINSTANCE.getComponentImplementingClassesLink_RequiredInterfaces();

		/**
		 * The meta object literal for the '<em><b>Is Initial Component</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT = eINSTANCE.getComponentImplementingClassesLink_IsInitialComponent();

		/**
		 * The meta object literal for the '{@link eu.qimpress.sourcecodedecorator.impl.SammSystemImplementatingClassesLinkImpl <em>Samm System Implementating Classes Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.qimpress.sourcecodedecorator.impl.SammSystemImplementatingClassesLinkImpl
		 * @see eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorPackageImpl#getSammSystemImplementatingClassesLink()
		 * @generated
		 */
		EClass SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK = eINSTANCE.getSammSystemImplementatingClassesLink();

		/**
		 * The meta object literal for the '<em><b>Service Architecture Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SERVICE_ARCHITECTURE_MODEL = eINSTANCE.getSammSystemImplementatingClassesLink_ServiceArchitectureModel();

	}

} //SourceCodeDecoratorPackage
