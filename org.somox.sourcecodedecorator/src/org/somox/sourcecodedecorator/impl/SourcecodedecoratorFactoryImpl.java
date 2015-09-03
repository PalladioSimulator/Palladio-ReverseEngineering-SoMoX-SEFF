/**
 */
package org.somox.sourcecodedecorator.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.somox.sourcecodedecorator.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SourcecodedecoratorFactoryImpl extends EFactoryImpl implements SourcecodedecoratorFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SourcecodedecoratorFactory init() {
        try {
            SourcecodedecoratorFactory theSourcecodedecoratorFactory = (SourcecodedecoratorFactory)EPackage.Registry.INSTANCE.getEFactory(SourcecodedecoratorPackage.eNS_URI);
            if (theSourcecodedecoratorFactory != null) {
                return theSourcecodedecoratorFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SourcecodedecoratorFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SourcecodedecoratorFactoryImpl() {
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
            case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK: return createFileLevelSourceCodeLink();
            case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK: return createMethodLevelSourceCodeLink();
            case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK: return createControlFlowLevelSourceCodeLink();
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY: return createSourceCodeDecoratorRepository();
            case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK: return createInterfaceSourceCodeLink();
            case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK: return createComponentImplementingClassesLink();
            case SourcecodedecoratorPackage.PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK: return createPCMSystemImplementatingClassesLink();
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK: return createDataTypeSourceCodeLink();
            case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK: return createInnerDatatypeSourceCodeLink();
            case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK: return createAbstractActionClassMethodLink();
            case SourcecodedecoratorPackage.METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK: return createMethodLevelResourceDemandingInternalBehaviorLink();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FileLevelSourceCodeLink createFileLevelSourceCodeLink() {
        FileLevelSourceCodeLinkImpl fileLevelSourceCodeLink = new FileLevelSourceCodeLinkImpl();
        return fileLevelSourceCodeLink;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public MethodLevelSourceCodeLink createMethodLevelSourceCodeLink() {
        MethodLevelSourceCodeLinkImpl methodLevelSourceCodeLink = new MethodLevelSourceCodeLinkImpl();
        return methodLevelSourceCodeLink;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ControlFlowLevelSourceCodeLink createControlFlowLevelSourceCodeLink() {
        ControlFlowLevelSourceCodeLinkImpl controlFlowLevelSourceCodeLink = new ControlFlowLevelSourceCodeLinkImpl();
        return controlFlowLevelSourceCodeLink;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SourceCodeDecoratorRepository createSourceCodeDecoratorRepository() {
        SourceCodeDecoratorRepositoryImpl sourceCodeDecoratorRepository = new SourceCodeDecoratorRepositoryImpl();
        return sourceCodeDecoratorRepository;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public InterfaceSourceCodeLink createInterfaceSourceCodeLink() {
        InterfaceSourceCodeLinkImpl interfaceSourceCodeLink = new InterfaceSourceCodeLinkImpl();
        return interfaceSourceCodeLink;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ComponentImplementingClassesLink createComponentImplementingClassesLink() {
        ComponentImplementingClassesLinkImpl componentImplementingClassesLink = new ComponentImplementingClassesLinkImpl();
        return componentImplementingClassesLink;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PCMSystemImplementatingClassesLink createPCMSystemImplementatingClassesLink() {
        PCMSystemImplementatingClassesLinkImpl pcmSystemImplementatingClassesLink = new PCMSystemImplementatingClassesLinkImpl();
        return pcmSystemImplementatingClassesLink;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataTypeSourceCodeLink createDataTypeSourceCodeLink() {
        DataTypeSourceCodeLinkImpl dataTypeSourceCodeLink = new DataTypeSourceCodeLinkImpl();
        return dataTypeSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InnerDatatypeSourceCodeLink createInnerDatatypeSourceCodeLink() {
        InnerDatatypeSourceCodeLinkImpl innerDatatypeSourceCodeLink = new InnerDatatypeSourceCodeLinkImpl();
        return innerDatatypeSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractActionClassMethodLink createAbstractActionClassMethodLink() {
        AbstractActionClassMethodLinkImpl abstractActionClassMethodLink = new AbstractActionClassMethodLinkImpl();
        return abstractActionClassMethodLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MethodLevelResourceDemandingInternalBehaviorLink createMethodLevelResourceDemandingInternalBehaviorLink() {
        MethodLevelResourceDemandingInternalBehaviorLinkImpl methodLevelResourceDemandingInternalBehaviorLink = new MethodLevelResourceDemandingInternalBehaviorLinkImpl();
        return methodLevelResourceDemandingInternalBehaviorLink;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SourcecodedecoratorPackage getSourcecodedecoratorPackage() {
        return (SourcecodedecoratorPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static SourcecodedecoratorPackage getPackage() {
        return SourcecodedecoratorPackage.eINSTANCE;
    }

} //SourcecodedecoratorFactoryImpl
