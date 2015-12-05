/**
 */
package org.somox.sourcecodedecorator.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.somox.sourcecodedecorator.AbstractActionClassMethodLink;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink;
import org.somox.sourcecodedecorator.DataTypeSourceCodeLink;
import org.somox.sourcecodedecorator.FileLevelSourceCodeLink;
import org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.MethodLevelResourceDemandingInternalBehaviorLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class SourcecodedecoratorFactoryImpl extends EFactoryImpl implements SourcecodedecoratorFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static SourcecodedecoratorFactory init() {
        try {
            final SourcecodedecoratorFactory theSourcecodedecoratorFactory = (SourcecodedecoratorFactory) EPackage.Registry.INSTANCE
                    .getEFactory(SourcecodedecoratorPackage.eNS_URI);
            if (theSourcecodedecoratorFactory != null) {
                return theSourcecodedecoratorFactory;
            }
        } catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SourcecodedecoratorFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SourcecodedecoratorFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject create(final EClass eClass) {
        switch (eClass.getClassifierID()) {
        case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK:
            return this.createFileLevelSourceCodeLink();
        case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK:
            return this.createMethodLevelSourceCodeLink();
        case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
            return this.createControlFlowLevelSourceCodeLink();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY:
            return this.createSourceCodeDecoratorRepository();
        case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK:
            return this.createInterfaceSourceCodeLink();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK:
            return this.createComponentImplementingClassesLink();
        case SourcecodedecoratorPackage.PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK:
            return this.createPCMSystemImplementatingClassesLink();
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK:
            return this.createDataTypeSourceCodeLink();
        case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK:
            return this.createInnerDatatypeSourceCodeLink();
        case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK:
            return this.createAbstractActionClassMethodLink();
        case SourcecodedecoratorPackage.METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK:
            return this.createMethodLevelResourceDemandingInternalBehaviorLink();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FileLevelSourceCodeLink createFileLevelSourceCodeLink() {
        final FileLevelSourceCodeLinkImpl fileLevelSourceCodeLink = new FileLevelSourceCodeLinkImpl();
        return fileLevelSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MethodLevelSourceCodeLink createMethodLevelSourceCodeLink() {
        final MethodLevelSourceCodeLinkImpl methodLevelSourceCodeLink = new MethodLevelSourceCodeLinkImpl();
        return methodLevelSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ControlFlowLevelSourceCodeLink createControlFlowLevelSourceCodeLink() {
        final ControlFlowLevelSourceCodeLinkImpl controlFlowLevelSourceCodeLink = new ControlFlowLevelSourceCodeLinkImpl();
        return controlFlowLevelSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SourceCodeDecoratorRepository createSourceCodeDecoratorRepository() {
        final SourceCodeDecoratorRepositoryImpl sourceCodeDecoratorRepository = new SourceCodeDecoratorRepositoryImpl();
        return sourceCodeDecoratorRepository;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InterfaceSourceCodeLink createInterfaceSourceCodeLink() {
        final InterfaceSourceCodeLinkImpl interfaceSourceCodeLink = new InterfaceSourceCodeLinkImpl();
        return interfaceSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ComponentImplementingClassesLink createComponentImplementingClassesLink() {
        final ComponentImplementingClassesLinkImpl componentImplementingClassesLink = new ComponentImplementingClassesLinkImpl();
        return componentImplementingClassesLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PCMSystemImplementatingClassesLink createPCMSystemImplementatingClassesLink() {
        final PCMSystemImplementatingClassesLinkImpl pcmSystemImplementatingClassesLink = new PCMSystemImplementatingClassesLinkImpl();
        return pcmSystemImplementatingClassesLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DataTypeSourceCodeLink createDataTypeSourceCodeLink() {
        final DataTypeSourceCodeLinkImpl dataTypeSourceCodeLink = new DataTypeSourceCodeLinkImpl();
        return dataTypeSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InnerDatatypeSourceCodeLink createInnerDatatypeSourceCodeLink() {
        final InnerDatatypeSourceCodeLinkImpl innerDatatypeSourceCodeLink = new InnerDatatypeSourceCodeLinkImpl();
        return innerDatatypeSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AbstractActionClassMethodLink createAbstractActionClassMethodLink() {
        final AbstractActionClassMethodLinkImpl abstractActionClassMethodLink = new AbstractActionClassMethodLinkImpl();
        return abstractActionClassMethodLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MethodLevelResourceDemandingInternalBehaviorLink createMethodLevelResourceDemandingInternalBehaviorLink() {
        final MethodLevelResourceDemandingInternalBehaviorLinkImpl methodLevelResourceDemandingInternalBehaviorLink = new MethodLevelResourceDemandingInternalBehaviorLinkImpl();
        return methodLevelResourceDemandingInternalBehaviorLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SourcecodedecoratorPackage getSourcecodedecoratorPackage() {
        return (SourcecodedecoratorPackage) this.getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static SourcecodedecoratorPackage getPackage() {
        return SourcecodedecoratorPackage.eINSTANCE;
    }

} // SourcecodedecoratorFactoryImpl
