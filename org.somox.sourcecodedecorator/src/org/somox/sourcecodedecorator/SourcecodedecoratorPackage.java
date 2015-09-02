/**
 */
package org.somox.sourcecodedecorator;

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
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorFactory
 * @model kind="package"
 * @generated
 */
public interface SourcecodedecoratorPackage extends EPackage {
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
	String eNS_URI = "http://somox.org/SourceCodeDecorator/2.0";

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
	SourcecodedecoratorPackage eINSTANCE = org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl.init();

	/**
     * The meta object id for the '{@link org.somox.sourcecodedecorator.impl.FileLevelSourceCodeLinkImpl <em>File Level Source Code Link</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.somox.sourcecodedecorator.impl.FileLevelSourceCodeLinkImpl
     * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getFileLevelSourceCodeLink()
     * @generated
     */
	int FILE_LEVEL_SOURCE_CODE_LINK = 0;

	/**
     * The feature id for the '<em><b>Repository Component</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT = 0;

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
     * The number of operations of the '<em>File Level Source Code Link</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_LEVEL_SOURCE_CODE_LINK_OPERATION_COUNT = 0;

	/**
     * The meta object id for the '{@link org.somox.sourcecodedecorator.impl.MethodLevelSourceCodeLinkImpl <em>Method Level Source Code Link</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.somox.sourcecodedecorator.impl.MethodLevelSourceCodeLinkImpl
     * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getMethodLevelSourceCodeLink()
     * @generated
     */
	int METHOD_LEVEL_SOURCE_CODE_LINK = 1;

	/**
     * The feature id for the '<em><b>Repository Component</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int METHOD_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT = FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT;

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
     * The number of operations of the '<em>Method Level Source Code Link</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int METHOD_LEVEL_SOURCE_CODE_LINK_OPERATION_COUNT = FILE_LEVEL_SOURCE_CODE_LINK_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.somox.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl <em>Control Flow Level Source Code Link</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.somox.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl
     * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getControlFlowLevelSourceCodeLink()
     * @generated
     */
	int CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK = 2;

	/**
     * The feature id for the '<em><b>Repository Component</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT = METHOD_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT;

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
     * The number of operations of the '<em>Control Flow Level Source Code Link</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK_OPERATION_COUNT = METHOD_LEVEL_SOURCE_CODE_LINK_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl <em>Source Code Decorator Repository</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl
     * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getSourceCodeDecoratorRepository()
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
     * The feature id for the '<em><b>Data Type Source Code Link</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK = 5;

    /**
     * The feature id for the '<em><b>Abstract Action Class Method Link</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK = 6;

    /**
     * The number of structural features of the '<em>Source Code Decorator Repository</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SOURCE_CODE_DECORATOR_REPOSITORY_FEATURE_COUNT = 7;

	/**
     * The number of operations of the '<em>Source Code Decorator Repository</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SOURCE_CODE_DECORATOR_REPOSITORY_OPERATION_COUNT = 0;

	/**
     * The meta object id for the '{@link org.somox.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl <em>Interface Source Code Link</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.somox.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl
     * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getInterfaceSourceCodeLink()
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
     * The number of operations of the '<em>Interface Source Code Link</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTERFACE_SOURCE_CODE_LINK_OPERATION_COUNT = 0;

	/**
     * The meta object id for the '{@link org.somox.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl <em>Component Implementing Classes Link</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.somox.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl
     * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getComponentImplementingClassesLink()
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
     * The number of operations of the '<em>Component Implementing Classes Link</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENT_IMPLEMENTING_CLASSES_LINK_OPERATION_COUNT = 0;

	/**
     * The meta object id for the '{@link org.somox.sourcecodedecorator.impl.PCMSystemImplementatingClassesLinkImpl <em>PCM System Implementating Classes Link</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.somox.sourcecodedecorator.impl.PCMSystemImplementatingClassesLinkImpl
     * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getPCMSystemImplementatingClassesLink()
     * @generated
     */
	int PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK = 6;

	/**
     * The feature id for the '<em><b>Is Composite Component</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__IS_COMPOSITE_COMPONENT = COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_COMPOSITE_COMPONENT;

	/**
     * The feature id for the '<em><b>Component</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__COMPONENT = COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT;

	/**
     * The feature id for the '<em><b>Implementing Classes</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__IMPLEMENTING_CLASSES = COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES;

	/**
     * The feature id for the '<em><b>Sub Components</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SUB_COMPONENTS = COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS;

	/**
     * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__PROVIDED_INTERFACES = COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES;

	/**
     * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__REQUIRED_INTERFACES = COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES;

	/**
     * The feature id for the '<em><b>Is Initial Component</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__IS_INITIAL_COMPONENT = COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT;

	/**
     * The feature id for the '<em><b>System Model</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SYSTEM_MODEL = COMPONENT_IMPLEMENTING_CLASSES_LINK_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>PCM System Implementating Classes Link</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK_FEATURE_COUNT = COMPONENT_IMPLEMENTING_CLASSES_LINK_FEATURE_COUNT + 1;

	/**
     * The number of operations of the '<em>PCM System Implementating Classes Link</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK_OPERATION_COUNT = COMPONENT_IMPLEMENTING_CLASSES_LINK_OPERATION_COUNT + 0;


	/**
     * The meta object id for the '{@link org.somox.sourcecodedecorator.impl.DataTypeSourceCodeLinkImpl <em>Data Type Source Code Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.sourcecodedecorator.impl.DataTypeSourceCodeLinkImpl
     * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getDataTypeSourceCodeLink()
     * @generated
     */
    int DATA_TYPE_SOURCE_CODE_LINK = 7;

    /**
     * The feature id for the '<em><b>Repository Component</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE_SOURCE_CODE_LINK__REPOSITORY_COMPONENT = FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT;

    /**
     * The feature id for the '<em><b>File</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE_SOURCE_CODE_LINK__FILE = FILE_LEVEL_SOURCE_CODE_LINK__FILE;

    /**
     * The feature id for the '<em><b>Ja Mo PP Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE = FILE_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Pcm Data Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE = FILE_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Inner Datatype Source Code Link</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK = FILE_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Data Type Source Code Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE_SOURCE_CODE_LINK_FEATURE_COUNT = FILE_LEVEL_SOURCE_CODE_LINK_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Data Type Source Code Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DATA_TYPE_SOURCE_CODE_LINK_OPERATION_COUNT = FILE_LEVEL_SOURCE_CODE_LINK_OPERATION_COUNT + 0;


    /**
     * The meta object id for the '{@link org.somox.sourcecodedecorator.impl.InnerDatatypeSourceCodeLinkImpl <em>Inner Datatype Source Code Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.sourcecodedecorator.impl.InnerDatatypeSourceCodeLinkImpl
     * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getInnerDatatypeSourceCodeLink()
     * @generated
     */
    int INNER_DATATYPE_SOURCE_CODE_LINK = 8;

    /**
     * The feature id for the '<em><b>Field</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INNER_DATATYPE_SOURCE_CODE_LINK__FIELD = 0;

    /**
     * The feature id for the '<em><b>Inner Declaration</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION = 1;

    /**
     * The number of structural features of the '<em>Inner Datatype Source Code Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INNER_DATATYPE_SOURCE_CODE_LINK_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Inner Datatype Source Code Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INNER_DATATYPE_SOURCE_CODE_LINK_OPERATION_COUNT = 0;


    /**
     * The meta object id for the '{@link org.somox.sourcecodedecorator.impl.AbstractActionClassMethodLinkImpl <em>Abstract Action Class Method Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.sourcecodedecorator.impl.AbstractActionClassMethodLinkImpl
     * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getAbstractActionClassMethodLink()
     * @generated
     */
    int ABSTRACT_ACTION_CLASS_METHOD_LINK = 9;

    /**
     * The feature id for the '<em><b>Class Method</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD = 0;

    /**
     * The feature id for the '<em><b>Abstract Action</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION = 1;

    /**
     * The number of structural features of the '<em>Abstract Action Class Method Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_ACTION_CLASS_METHOD_LINK_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Abstract Action Class Method Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_ACTION_CLASS_METHOD_LINK_OPERATION_COUNT = 0;


    /**
     * Returns the meta object for class '{@link org.somox.sourcecodedecorator.FileLevelSourceCodeLink <em>File Level Source Code Link</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>File Level Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.FileLevelSourceCodeLink
     * @generated
     */
	EClass getFileLevelSourceCodeLink();

	/**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.FileLevelSourceCodeLink#getRepositoryComponent <em>Repository Component</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Repository Component</em>'.
     * @see org.somox.sourcecodedecorator.FileLevelSourceCodeLink#getRepositoryComponent()
     * @see #getFileLevelSourceCodeLink()
     * @generated
     */
	EReference getFileLevelSourceCodeLink_RepositoryComponent();

	/**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.FileLevelSourceCodeLink#getFile <em>File</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>File</em>'.
     * @see org.somox.sourcecodedecorator.FileLevelSourceCodeLink#getFile()
     * @see #getFileLevelSourceCodeLink()
     * @generated
     */
	EReference getFileLevelSourceCodeLink_File();

	/**
     * Returns the meta object for class '{@link org.somox.sourcecodedecorator.MethodLevelSourceCodeLink <em>Method Level Source Code Link</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Method Level Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.MethodLevelSourceCodeLink
     * @generated
     */
	EClass getMethodLevelSourceCodeLink();

	/**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.MethodLevelSourceCodeLink#getOperation <em>Operation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Operation</em>'.
     * @see org.somox.sourcecodedecorator.MethodLevelSourceCodeLink#getOperation()
     * @see #getMethodLevelSourceCodeLink()
     * @generated
     */
	EReference getMethodLevelSourceCodeLink_Operation();

	/**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.MethodLevelSourceCodeLink#getFunction <em>Function</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Function</em>'.
     * @see org.somox.sourcecodedecorator.MethodLevelSourceCodeLink#getFunction()
     * @see #getMethodLevelSourceCodeLink()
     * @generated
     */
	EReference getMethodLevelSourceCodeLink_Function();

	/**
     * Returns the meta object for class '{@link org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink <em>Control Flow Level Source Code Link</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Control Flow Level Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink
     * @generated
     */
	EClass getControlFlowLevelSourceCodeLink();

	/**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getAbstractAction <em>Abstract Action</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Abstract Action</em>'.
     * @see org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getAbstractAction()
     * @see #getControlFlowLevelSourceCodeLink()
     * @generated
     */
	EReference getControlFlowLevelSourceCodeLink_AbstractAction();

	/**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getStatement <em>Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Statement</em>'.
     * @see org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getStatement()
     * @see #getControlFlowLevelSourceCodeLink()
     * @generated
     */
	EReference getControlFlowLevelSourceCodeLink_Statement();

	/**
     * Returns the meta object for class '{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository <em>Source Code Decorator Repository</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Source Code Decorator Repository</em>'.
     * @see org.somox.sourcecodedecorator.SourceCodeDecoratorRepository
     * @generated
     */
	EClass getSourceCodeDecoratorRepository();

	/**
     * Returns the meta object for the containment reference list '{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getFileLevelSourceCodeLink <em>File Level Source Code Link</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>File Level Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getFileLevelSourceCodeLink()
     * @see #getSourceCodeDecoratorRepository()
     * @generated
     */
	EReference getSourceCodeDecoratorRepository_FileLevelSourceCodeLink();

	/**
     * Returns the meta object for the containment reference list '{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getMethodLevelSourceCodeLink <em>Method Level Source Code Link</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Method Level Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getMethodLevelSourceCodeLink()
     * @see #getSourceCodeDecoratorRepository()
     * @generated
     */
	EReference getSourceCodeDecoratorRepository_MethodLevelSourceCodeLink();

	/**
     * Returns the meta object for the containment reference list '{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getControlFlowLevelSourceCodeLink <em>Control Flow Level Source Code Link</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Control Flow Level Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getControlFlowLevelSourceCodeLink()
     * @see #getSourceCodeDecoratorRepository()
     * @generated
     */
	EReference getSourceCodeDecoratorRepository_ControlFlowLevelSourceCodeLink();

	/**
     * Returns the meta object for the containment reference list '{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getInterfaceSourceCodeLink <em>Interface Source Code Link</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Interface Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getInterfaceSourceCodeLink()
     * @see #getSourceCodeDecoratorRepository()
     * @generated
     */
	EReference getSourceCodeDecoratorRepository_InterfaceSourceCodeLink();

	/**
     * Returns the meta object for the containment reference list '{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getComponentImplementingClassesLink <em>Component Implementing Classes Link</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Component Implementing Classes Link</em>'.
     * @see org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getComponentImplementingClassesLink()
     * @see #getSourceCodeDecoratorRepository()
     * @generated
     */
	EReference getSourceCodeDecoratorRepository_ComponentImplementingClassesLink();

	/**
     * Returns the meta object for the containment reference list '{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getDataTypeSourceCodeLink <em>Data Type Source Code Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data Type Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getDataTypeSourceCodeLink()
     * @see #getSourceCodeDecoratorRepository()
     * @generated
     */
    EReference getSourceCodeDecoratorRepository_DataTypeSourceCodeLink();

    /**
     * Returns the meta object for the containment reference list '{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getAbstractActionClassMethodLink <em>Abstract Action Class Method Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Abstract Action Class Method Link</em>'.
     * @see org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getAbstractActionClassMethodLink()
     * @see #getSourceCodeDecoratorRepository()
     * @generated
     */
    EReference getSourceCodeDecoratorRepository_AbstractActionClassMethodLink();

    /**
     * Returns the meta object for class '{@link org.somox.sourcecodedecorator.InterfaceSourceCodeLink <em>Interface Source Code Link</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Interface Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.InterfaceSourceCodeLink
     * @generated
     */
	EClass getInterfaceSourceCodeLink();

	/**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.InterfaceSourceCodeLink#getInterface <em>Interface</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Interface</em>'.
     * @see org.somox.sourcecodedecorator.InterfaceSourceCodeLink#getInterface()
     * @see #getInterfaceSourceCodeLink()
     * @generated
     */
	EReference getInterfaceSourceCodeLink_Interface();

	/**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.InterfaceSourceCodeLink#getGastClass <em>Gast Class</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Gast Class</em>'.
     * @see org.somox.sourcecodedecorator.InterfaceSourceCodeLink#getGastClass()
     * @see #getInterfaceSourceCodeLink()
     * @generated
     */
	EReference getInterfaceSourceCodeLink_GastClass();

	/**
     * Returns the meta object for class '{@link org.somox.sourcecodedecorator.ComponentImplementingClassesLink <em>Component Implementing Classes Link</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Component Implementing Classes Link</em>'.
     * @see org.somox.sourcecodedecorator.ComponentImplementingClassesLink
     * @generated
     */
	EClass getComponentImplementingClassesLink();

	/**
     * Returns the meta object for the attribute '{@link org.somox.sourcecodedecorator.ComponentImplementingClassesLink#isIsCompositeComponent <em>Is Composite Component</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Composite Component</em>'.
     * @see org.somox.sourcecodedecorator.ComponentImplementingClassesLink#isIsCompositeComponent()
     * @see #getComponentImplementingClassesLink()
     * @generated
     */
	EAttribute getComponentImplementingClassesLink_IsCompositeComponent();

	/**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.ComponentImplementingClassesLink#getComponent <em>Component</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Component</em>'.
     * @see org.somox.sourcecodedecorator.ComponentImplementingClassesLink#getComponent()
     * @see #getComponentImplementingClassesLink()
     * @generated
     */
	EReference getComponentImplementingClassesLink_Component();

	/**
     * Returns the meta object for the reference list '{@link org.somox.sourcecodedecorator.ComponentImplementingClassesLink#getImplementingClasses <em>Implementing Classes</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Implementing Classes</em>'.
     * @see org.somox.sourcecodedecorator.ComponentImplementingClassesLink#getImplementingClasses()
     * @see #getComponentImplementingClassesLink()
     * @generated
     */
	EReference getComponentImplementingClassesLink_ImplementingClasses();

	/**
     * Returns the meta object for the reference list '{@link org.somox.sourcecodedecorator.ComponentImplementingClassesLink#getSubComponents <em>Sub Components</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Sub Components</em>'.
     * @see org.somox.sourcecodedecorator.ComponentImplementingClassesLink#getSubComponents()
     * @see #getComponentImplementingClassesLink()
     * @generated
     */
	EReference getComponentImplementingClassesLink_SubComponents();

	/**
     * Returns the meta object for the reference list '{@link org.somox.sourcecodedecorator.ComponentImplementingClassesLink#getProvidedInterfaces <em>Provided Interfaces</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Provided Interfaces</em>'.
     * @see org.somox.sourcecodedecorator.ComponentImplementingClassesLink#getProvidedInterfaces()
     * @see #getComponentImplementingClassesLink()
     * @generated
     */
	EReference getComponentImplementingClassesLink_ProvidedInterfaces();

	/**
     * Returns the meta object for the reference list '{@link org.somox.sourcecodedecorator.ComponentImplementingClassesLink#getRequiredInterfaces <em>Required Interfaces</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Required Interfaces</em>'.
     * @see org.somox.sourcecodedecorator.ComponentImplementingClassesLink#getRequiredInterfaces()
     * @see #getComponentImplementingClassesLink()
     * @generated
     */
	EReference getComponentImplementingClassesLink_RequiredInterfaces();

	/**
     * Returns the meta object for the attribute '{@link org.somox.sourcecodedecorator.ComponentImplementingClassesLink#isIsInitialComponent <em>Is Initial Component</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Initial Component</em>'.
     * @see org.somox.sourcecodedecorator.ComponentImplementingClassesLink#isIsInitialComponent()
     * @see #getComponentImplementingClassesLink()
     * @generated
     */
	EAttribute getComponentImplementingClassesLink_IsInitialComponent();

	/**
     * Returns the meta object for class '{@link org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink <em>PCM System Implementating Classes Link</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>PCM System Implementating Classes Link</em>'.
     * @see org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink
     * @generated
     */
	EClass getPCMSystemImplementatingClassesLink();

	/**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink#getSystemModel <em>System Model</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>System Model</em>'.
     * @see org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink#getSystemModel()
     * @see #getPCMSystemImplementatingClassesLink()
     * @generated
     */
	EReference getPCMSystemImplementatingClassesLink_SystemModel();

	/**
     * Returns the meta object for class '{@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink <em>Data Type Source Code Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data Type Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.DataTypeSourceCodeLink
     * @generated
     */
    EClass getDataTypeSourceCodeLink();

    /**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink#getJaMoPPType <em>Ja Mo PP Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Ja Mo PP Type</em>'.
     * @see org.somox.sourcecodedecorator.DataTypeSourceCodeLink#getJaMoPPType()
     * @see #getDataTypeSourceCodeLink()
     * @generated
     */
    EReference getDataTypeSourceCodeLink_JaMoPPType();

    /**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink#getPcmDataType <em>Pcm Data Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Pcm Data Type</em>'.
     * @see org.somox.sourcecodedecorator.DataTypeSourceCodeLink#getPcmDataType()
     * @see #getDataTypeSourceCodeLink()
     * @generated
     */
    EReference getDataTypeSourceCodeLink_PcmDataType();

    /**
     * Returns the meta object for the containment reference list '{@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink#getInnerDatatypeSourceCodeLink <em>Inner Datatype Source Code Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Inner Datatype Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.DataTypeSourceCodeLink#getInnerDatatypeSourceCodeLink()
     * @see #getDataTypeSourceCodeLink()
     * @generated
     */
    EReference getDataTypeSourceCodeLink_InnerDatatypeSourceCodeLink();

    /**
     * Returns the meta object for class '{@link org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink <em>Inner Datatype Source Code Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Inner Datatype Source Code Link</em>'.
     * @see org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink
     * @generated
     */
    EClass getInnerDatatypeSourceCodeLink();

    /**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink#getField <em>Field</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Field</em>'.
     * @see org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink#getField()
     * @see #getInnerDatatypeSourceCodeLink()
     * @generated
     */
    EReference getInnerDatatypeSourceCodeLink_Field();

    /**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink#getInnerDeclaration <em>Inner Declaration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Inner Declaration</em>'.
     * @see org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink#getInnerDeclaration()
     * @see #getInnerDatatypeSourceCodeLink()
     * @generated
     */
    EReference getInnerDatatypeSourceCodeLink_InnerDeclaration();

    /**
     * Returns the meta object for class '{@link org.somox.sourcecodedecorator.AbstractActionClassMethodLink <em>Abstract Action Class Method Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Action Class Method Link</em>'.
     * @see org.somox.sourcecodedecorator.AbstractActionClassMethodLink
     * @generated
     */
    EClass getAbstractActionClassMethodLink();

    /**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.AbstractActionClassMethodLink#getClassMethod <em>Class Method</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Class Method</em>'.
     * @see org.somox.sourcecodedecorator.AbstractActionClassMethodLink#getClassMethod()
     * @see #getAbstractActionClassMethodLink()
     * @generated
     */
    EReference getAbstractActionClassMethodLink_ClassMethod();

    /**
     * Returns the meta object for the reference '{@link org.somox.sourcecodedecorator.AbstractActionClassMethodLink#getAbstractAction <em>Abstract Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Abstract Action</em>'.
     * @see org.somox.sourcecodedecorator.AbstractActionClassMethodLink#getAbstractAction()
     * @see #getAbstractActionClassMethodLink()
     * @generated
     */
    EReference getAbstractActionClassMethodLink_AbstractAction();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	SourcecodedecoratorFactory getSourcecodedecoratorFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.somox.sourcecodedecorator.impl.FileLevelSourceCodeLinkImpl <em>File Level Source Code Link</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.somox.sourcecodedecorator.impl.FileLevelSourceCodeLinkImpl
         * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getFileLevelSourceCodeLink()
         * @generated
         */
		EClass FILE_LEVEL_SOURCE_CODE_LINK = eINSTANCE.getFileLevelSourceCodeLink();

		/**
         * The meta object literal for the '<em><b>Repository Component</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT = eINSTANCE.getFileLevelSourceCodeLink_RepositoryComponent();

		/**
         * The meta object literal for the '<em><b>File</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FILE_LEVEL_SOURCE_CODE_LINK__FILE = eINSTANCE.getFileLevelSourceCodeLink_File();

		/**
         * The meta object literal for the '{@link org.somox.sourcecodedecorator.impl.MethodLevelSourceCodeLinkImpl <em>Method Level Source Code Link</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.somox.sourcecodedecorator.impl.MethodLevelSourceCodeLinkImpl
         * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getMethodLevelSourceCodeLink()
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
         * The meta object literal for the '{@link org.somox.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl <em>Control Flow Level Source Code Link</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.somox.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl
         * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getControlFlowLevelSourceCodeLink()
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
         * The meta object literal for the '{@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl <em>Source Code Decorator Repository</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl
         * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getSourceCodeDecoratorRepository()
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
         * The meta object literal for the '<em><b>Data Type Source Code Link</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK = eINSTANCE.getSourceCodeDecoratorRepository_DataTypeSourceCodeLink();

        /**
         * The meta object literal for the '<em><b>Abstract Action Class Method Link</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK = eINSTANCE.getSourceCodeDecoratorRepository_AbstractActionClassMethodLink();

        /**
         * The meta object literal for the '{@link org.somox.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl <em>Interface Source Code Link</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.somox.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl
         * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getInterfaceSourceCodeLink()
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
         * The meta object literal for the '{@link org.somox.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl <em>Component Implementing Classes Link</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.somox.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl
         * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getComponentImplementingClassesLink()
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
         * The meta object literal for the '{@link org.somox.sourcecodedecorator.impl.PCMSystemImplementatingClassesLinkImpl <em>PCM System Implementating Classes Link</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.somox.sourcecodedecorator.impl.PCMSystemImplementatingClassesLinkImpl
         * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getPCMSystemImplementatingClassesLink()
         * @generated
         */
		EClass PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK = eINSTANCE.getPCMSystemImplementatingClassesLink();

		/**
         * The meta object literal for the '<em><b>System Model</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SYSTEM_MODEL = eINSTANCE.getPCMSystemImplementatingClassesLink_SystemModel();

        /**
         * The meta object literal for the '{@link org.somox.sourcecodedecorator.impl.DataTypeSourceCodeLinkImpl <em>Data Type Source Code Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.somox.sourcecodedecorator.impl.DataTypeSourceCodeLinkImpl
         * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getDataTypeSourceCodeLink()
         * @generated
         */
        EClass DATA_TYPE_SOURCE_CODE_LINK = eINSTANCE.getDataTypeSourceCodeLink();

        /**
         * The meta object literal for the '<em><b>Ja Mo PP Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE = eINSTANCE.getDataTypeSourceCodeLink_JaMoPPType();

        /**
         * The meta object literal for the '<em><b>Pcm Data Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE = eINSTANCE.getDataTypeSourceCodeLink_PcmDataType();

        /**
         * The meta object literal for the '<em><b>Inner Datatype Source Code Link</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK = eINSTANCE.getDataTypeSourceCodeLink_InnerDatatypeSourceCodeLink();

        /**
         * The meta object literal for the '{@link org.somox.sourcecodedecorator.impl.InnerDatatypeSourceCodeLinkImpl <em>Inner Datatype Source Code Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.somox.sourcecodedecorator.impl.InnerDatatypeSourceCodeLinkImpl
         * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getInnerDatatypeSourceCodeLink()
         * @generated
         */
        EClass INNER_DATATYPE_SOURCE_CODE_LINK = eINSTANCE.getInnerDatatypeSourceCodeLink();

        /**
         * The meta object literal for the '<em><b>Field</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INNER_DATATYPE_SOURCE_CODE_LINK__FIELD = eINSTANCE.getInnerDatatypeSourceCodeLink_Field();

        /**
         * The meta object literal for the '<em><b>Inner Declaration</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION = eINSTANCE.getInnerDatatypeSourceCodeLink_InnerDeclaration();

        /**
         * The meta object literal for the '{@link org.somox.sourcecodedecorator.impl.AbstractActionClassMethodLinkImpl <em>Abstract Action Class Method Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.somox.sourcecodedecorator.impl.AbstractActionClassMethodLinkImpl
         * @see org.somox.sourcecodedecorator.impl.SourcecodedecoratorPackageImpl#getAbstractActionClassMethodLink()
         * @generated
         */
        EClass ABSTRACT_ACTION_CLASS_METHOD_LINK = eINSTANCE.getAbstractActionClassMethodLink();

        /**
         * The meta object literal for the '<em><b>Class Method</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD = eINSTANCE.getAbstractActionClassMethodLink_ClassMethod();

        /**
         * The meta object literal for the '<em><b>Abstract Action</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION = eINSTANCE.getAbstractActionClassMethodLink_AbstractAction();

	}

} //SourcecodedecoratorPackage
