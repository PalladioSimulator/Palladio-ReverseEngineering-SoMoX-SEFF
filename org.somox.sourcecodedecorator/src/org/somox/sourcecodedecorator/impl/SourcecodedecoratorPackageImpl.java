/**
 */
package org.somox.sourcecodedecorator.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.imports.ImportsPackage;
import org.emftext.language.java.instantiations.InstantiationsPackage;
import org.emftext.language.java.literals.LiteralsPackage;
import org.emftext.language.java.members.MembersPackage;
import org.emftext.language.java.modifiers.ModifiersPackage;
import org.emftext.language.java.operators.OperatorsPackage;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.references.ReferencesPackage;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.types.TypesPackage;
import org.emftext.language.java.variables.VariablesPackage;
import org.palladiosimulator.pcm.PcmPackage;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.system.SystemPackage;
import org.somox.sourcecodedecorator.AbstractActionClassMethodLink;
import org.somox.sourcecodedecorator.AbstractMethodLevelSourceCodeLink;
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
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class SourcecodedecoratorPackageImpl extends EPackageImpl implements SourcecodedecoratorPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass fileLevelSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass methodLevelSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass controlFlowLevelSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass sourceCodeDecoratorRepositoryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass interfaceSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass componentImplementingClassesLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass pcmSystemImplementatingClassesLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dataTypeSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass innerDatatypeSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractActionClassMethodLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass methodLevelResourceDemandingInternalBehaviorLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractMethodLevelSourceCodeLinkEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI
     * value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init
     * init()}, which also performs initialization of the package, or returns the registered
     * package, if one already exists. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private SourcecodedecoratorPackageImpl() {
        super(eNS_URI, SourcecodedecoratorFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others
     * upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link SourcecodedecoratorPackage#eINSTANCE} when that
     * field is accessed. Clients should not invoke it directly. Instead, they should simply access
     * that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static SourcecodedecoratorPackage init() {
        if (isInited) {
            return (SourcecodedecoratorPackage) EPackage.Registry.INSTANCE
                    .getEPackage(SourcecodedecoratorPackage.eNS_URI);
        }

        // Obtain or create and register package
        final SourcecodedecoratorPackageImpl theSourcecodedecoratorPackage = (SourcecodedecoratorPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof SourcecodedecoratorPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new SourcecodedecoratorPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        AnnotationsPackage.eINSTANCE.eClass();
        ArraysPackage.eINSTANCE.eClass();
        ClassifiersPackage.eINSTANCE.eClass();
        CommonsPackage.eINSTANCE.eClass();
        ContainersPackage.eINSTANCE.eClass();
        ExpressionsPackage.eINSTANCE.eClass();
        GenericsPackage.eINSTANCE.eClass();
        ImportsPackage.eINSTANCE.eClass();
        InstantiationsPackage.eINSTANCE.eClass();
        LiteralsPackage.eINSTANCE.eClass();
        MembersPackage.eINSTANCE.eClass();
        ModifiersPackage.eINSTANCE.eClass();
        OperatorsPackage.eINSTANCE.eClass();
        ParametersPackage.eINSTANCE.eClass();
        ReferencesPackage.eINSTANCE.eClass();
        StatementsPackage.eINSTANCE.eClass();
        TypesPackage.eINSTANCE.eClass();
        VariablesPackage.eINSTANCE.eClass();
        PcmPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theSourcecodedecoratorPackage.createPackageContents();

        // Initialize created meta-data
        theSourcecodedecoratorPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theSourcecodedecoratorPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(SourcecodedecoratorPackage.eNS_URI, theSourcecodedecoratorPackage);
        return theSourcecodedecoratorPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFileLevelSourceCodeLink() {
        return this.fileLevelSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getFileLevelSourceCodeLink_RepositoryComponent() {
        return (EReference) this.fileLevelSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getFileLevelSourceCodeLink_File() {
        return (EReference) this.fileLevelSourceCodeLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMethodLevelSourceCodeLink() {
        return this.methodLevelSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMethodLevelSourceCodeLink_Operation() {
        return (EReference) this.methodLevelSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getControlFlowLevelSourceCodeLink() {
        return this.controlFlowLevelSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getControlFlowLevelSourceCodeLink_AbstractAction() {
        return (EReference) this.controlFlowLevelSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getControlFlowLevelSourceCodeLink_Statement() {
        return (EReference) this.controlFlowLevelSourceCodeLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSourceCodeDecoratorRepository() {
        return this.sourceCodeDecoratorRepositoryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_FileLevelSourceCodeLink() {
        return (EReference) this.sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_MethodLevelSourceCodeLink() {
        return (EReference) this.sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_ControlFlowLevelSourceCodeLink() {
        return (EReference) this.sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_InterfaceSourceCodeLink() {
        return (EReference) this.sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_ComponentImplementingClassesLink() {
        return (EReference) this.sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_DataTypeSourceCodeLink() {
        return (EReference) this.sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_AbstractActionClassMethodLink() {
        return (EReference) this.sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_MethodLevelResourceDemandingInternalBehaviorLink() {
        return (EReference) this.sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getInterfaceSourceCodeLink() {
        return this.interfaceSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInterfaceSourceCodeLink_Interface() {
        return (EReference) this.interfaceSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInterfaceSourceCodeLink_GastClass() {
        return (EReference) this.interfaceSourceCodeLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getComponentImplementingClassesLink() {
        return this.componentImplementingClassesLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getComponentImplementingClassesLink_IsCompositeComponent() {
        return (EAttribute) this.componentImplementingClassesLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getComponentImplementingClassesLink_Component() {
        return (EReference) this.componentImplementingClassesLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getComponentImplementingClassesLink_ImplementingClasses() {
        return (EReference) this.componentImplementingClassesLinkEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getComponentImplementingClassesLink_SubComponents() {
        return (EReference) this.componentImplementingClassesLinkEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getComponentImplementingClassesLink_ProvidedInterfaces() {
        return (EReference) this.componentImplementingClassesLinkEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getComponentImplementingClassesLink_RequiredInterfaces() {
        return (EReference) this.componentImplementingClassesLinkEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getComponentImplementingClassesLink_IsInitialComponent() {
        return (EAttribute) this.componentImplementingClassesLinkEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getPCMSystemImplementatingClassesLink() {
        return this.pcmSystemImplementatingClassesLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPCMSystemImplementatingClassesLink_SystemModel() {
        return (EReference) this.pcmSystemImplementatingClassesLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDataTypeSourceCodeLink() {
        return this.dataTypeSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDataTypeSourceCodeLink_JaMoPPType() {
        return (EReference) this.dataTypeSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDataTypeSourceCodeLink_PcmDataType() {
        return (EReference) this.dataTypeSourceCodeLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDataTypeSourceCodeLink_InnerDatatypeSourceCodeLink() {
        return (EReference) this.dataTypeSourceCodeLinkEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getInnerDatatypeSourceCodeLink() {
        return this.innerDatatypeSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInnerDatatypeSourceCodeLink_Field() {
        return (EReference) this.innerDatatypeSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInnerDatatypeSourceCodeLink_InnerDeclaration() {
        return (EReference) this.innerDatatypeSourceCodeLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractActionClassMethodLink() {
        return this.abstractActionClassMethodLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractActionClassMethodLink_ClassMethod() {
        return (EReference) this.abstractActionClassMethodLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractActionClassMethodLink_AbstractAction() {
        return (EReference) this.abstractActionClassMethodLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMethodLevelResourceDemandingInternalBehaviorLink() {
        return this.methodLevelResourceDemandingInternalBehaviorLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMethodLevelResourceDemandingInternalBehaviorLink_ResourceDemandingInternalBehaviour() {
        return (EReference) this.methodLevelResourceDemandingInternalBehaviorLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractMethodLevelSourceCodeLink() {
        return this.abstractMethodLevelSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractMethodLevelSourceCodeLink_Function() {
        return (EReference) this.abstractMethodLevelSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SourcecodedecoratorFactory getSourcecodedecoratorFactory() {
        return (SourcecodedecoratorFactory) this.getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on
     * any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (this.isCreated) {
            return;
        }
        this.isCreated = true;

        // Create classes and their features
        this.fileLevelSourceCodeLinkEClass = this.createEClass(FILE_LEVEL_SOURCE_CODE_LINK);
        this.createEReference(this.fileLevelSourceCodeLinkEClass, FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT);
        this.createEReference(this.fileLevelSourceCodeLinkEClass, FILE_LEVEL_SOURCE_CODE_LINK__FILE);

        this.methodLevelSourceCodeLinkEClass = this.createEClass(METHOD_LEVEL_SOURCE_CODE_LINK);
        this.createEReference(this.methodLevelSourceCodeLinkEClass, METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION);

        this.controlFlowLevelSourceCodeLinkEClass = this.createEClass(CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK);
        this.createEReference(this.controlFlowLevelSourceCodeLinkEClass,
                CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION);
        this.createEReference(this.controlFlowLevelSourceCodeLinkEClass,
                CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT);

        this.sourceCodeDecoratorRepositoryEClass = this.createEClass(SOURCE_CODE_DECORATOR_REPOSITORY);
        this.createEReference(this.sourceCodeDecoratorRepositoryEClass,
                SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK);
        this.createEReference(this.sourceCodeDecoratorRepositoryEClass,
                SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK);
        this.createEReference(this.sourceCodeDecoratorRepositoryEClass,
                SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK);
        this.createEReference(this.sourceCodeDecoratorRepositoryEClass,
                SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK);
        this.createEReference(this.sourceCodeDecoratorRepositoryEClass,
                SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK);
        this.createEReference(this.sourceCodeDecoratorRepositoryEClass,
                SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK);
        this.createEReference(this.sourceCodeDecoratorRepositoryEClass,
                SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK);
        this.createEReference(this.sourceCodeDecoratorRepositoryEClass,
                SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK);

        this.interfaceSourceCodeLinkEClass = this.createEClass(INTERFACE_SOURCE_CODE_LINK);
        this.createEReference(this.interfaceSourceCodeLinkEClass, INTERFACE_SOURCE_CODE_LINK__INTERFACE);
        this.createEReference(this.interfaceSourceCodeLinkEClass, INTERFACE_SOURCE_CODE_LINK__GAST_CLASS);

        this.componentImplementingClassesLinkEClass = this.createEClass(COMPONENT_IMPLEMENTING_CLASSES_LINK);
        this.createEAttribute(this.componentImplementingClassesLinkEClass,
                COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_COMPOSITE_COMPONENT);
        this.createEReference(this.componentImplementingClassesLinkEClass,
                COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT);
        this.createEReference(this.componentImplementingClassesLinkEClass,
                COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES);
        this.createEReference(this.componentImplementingClassesLinkEClass,
                COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS);
        this.createEReference(this.componentImplementingClassesLinkEClass,
                COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES);
        this.createEReference(this.componentImplementingClassesLinkEClass,
                COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES);
        this.createEAttribute(this.componentImplementingClassesLinkEClass,
                COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT);

        this.pcmSystemImplementatingClassesLinkEClass = this.createEClass(PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK);
        this.createEReference(this.pcmSystemImplementatingClassesLinkEClass,
                PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SYSTEM_MODEL);

        this.dataTypeSourceCodeLinkEClass = this.createEClass(DATA_TYPE_SOURCE_CODE_LINK);
        this.createEReference(this.dataTypeSourceCodeLinkEClass, DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE);
        this.createEReference(this.dataTypeSourceCodeLinkEClass, DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE);
        this.createEReference(this.dataTypeSourceCodeLinkEClass,
                DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK);

        this.innerDatatypeSourceCodeLinkEClass = this.createEClass(INNER_DATATYPE_SOURCE_CODE_LINK);
        this.createEReference(this.innerDatatypeSourceCodeLinkEClass, INNER_DATATYPE_SOURCE_CODE_LINK__FIELD);
        this.createEReference(this.innerDatatypeSourceCodeLinkEClass,
                INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION);

        this.abstractActionClassMethodLinkEClass = this.createEClass(ABSTRACT_ACTION_CLASS_METHOD_LINK);
        this.createEReference(this.abstractActionClassMethodLinkEClass,
                ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD);
        this.createEReference(this.abstractActionClassMethodLinkEClass,
                ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION);

        this.methodLevelResourceDemandingInternalBehaviorLinkEClass = this
                .createEClass(METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK);
        this.createEReference(this.methodLevelResourceDemandingInternalBehaviorLinkEClass,
                METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK__RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR);

        this.abstractMethodLevelSourceCodeLinkEClass = this.createEClass(ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK);
        this.createEReference(this.abstractMethodLevelSourceCodeLinkEClass,
                ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have
     * no affect on any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;

        // Initialize package
        this.setName(eNAME);
        this.setNsPrefix(eNS_PREFIX);
        this.setNsURI(eNS_URI);

        // Obtain other dependent packages
        final RepositoryPackage theRepositoryPackage = (RepositoryPackage) EPackage.Registry.INSTANCE
                .getEPackage(RepositoryPackage.eNS_URI);
        final ContainersPackage theContainersPackage = (ContainersPackage) EPackage.Registry.INSTANCE
                .getEPackage(ContainersPackage.eNS_URI);
        final SeffPackage theSeffPackage = (SeffPackage) EPackage.Registry.INSTANCE.getEPackage(SeffPackage.eNS_URI);
        final StatementsPackage theStatementsPackage = (StatementsPackage) EPackage.Registry.INSTANCE
                .getEPackage(StatementsPackage.eNS_URI);
        final ClassifiersPackage theClassifiersPackage = (ClassifiersPackage) EPackage.Registry.INSTANCE
                .getEPackage(ClassifiersPackage.eNS_URI);
        final SystemPackage theSystemPackage = (SystemPackage) EPackage.Registry.INSTANCE
                .getEPackage(SystemPackage.eNS_URI);
        final TypesPackage theTypesPackage = (TypesPackage) EPackage.Registry.INSTANCE
                .getEPackage(TypesPackage.eNS_URI);
        final MembersPackage theMembersPackage = (MembersPackage) EPackage.Registry.INSTANCE
                .getEPackage(MembersPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        this.methodLevelSourceCodeLinkEClass.getESuperTypes().add(this.getAbstractMethodLevelSourceCodeLink());
        this.controlFlowLevelSourceCodeLinkEClass.getESuperTypes().add(this.getMethodLevelSourceCodeLink());
        this.pcmSystemImplementatingClassesLinkEClass.getESuperTypes().add(this.getComponentImplementingClassesLink());
        this.dataTypeSourceCodeLinkEClass.getESuperTypes().add(this.getFileLevelSourceCodeLink());
        this.methodLevelResourceDemandingInternalBehaviorLinkEClass.getESuperTypes()
                .add(this.getAbstractMethodLevelSourceCodeLink());
        this.abstractMethodLevelSourceCodeLinkEClass.getESuperTypes().add(this.getFileLevelSourceCodeLink());

        // Initialize classes, features, and operations; add parameters
        this.initEClass(this.fileLevelSourceCodeLinkEClass, FileLevelSourceCodeLink.class, "FileLevelSourceCodeLink",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getFileLevelSourceCodeLink_RepositoryComponent(),
                theRepositoryPackage.getRepositoryComponent(), null, "repositoryComponent", null, 0, 1,
                FileLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getFileLevelSourceCodeLink_File(), theContainersPackage.getCompilationUnit(), null,
                "file", null, 0, 1, FileLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        this.initEClass(this.methodLevelSourceCodeLinkEClass, MethodLevelSourceCodeLink.class,
                "MethodLevelSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getMethodLevelSourceCodeLink_Operation(), theRepositoryPackage.getSignature(), null,
                "operation", null, 0, 1, MethodLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        this.initEClass(this.controlFlowLevelSourceCodeLinkEClass, ControlFlowLevelSourceCodeLink.class,
                "ControlFlowLevelSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getControlFlowLevelSourceCodeLink_AbstractAction(), theSeffPackage.getAbstractAction(),
                null, "abstractAction", null, 0, 1, ControlFlowLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getControlFlowLevelSourceCodeLink_Statement(), theStatementsPackage.getStatement(),
                null, "statement", null, 0, 1, ControlFlowLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        this.initEClass(this.sourceCodeDecoratorRepositoryEClass, SourceCodeDecoratorRepository.class,
                "SourceCodeDecoratorRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getSourceCodeDecoratorRepository_FileLevelSourceCodeLink(),
                this.getFileLevelSourceCodeLink(), null, "fileLevelSourceCodeLink", null, 0, -1,
                SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getSourceCodeDecoratorRepository_MethodLevelSourceCodeLink(),
                this.getMethodLevelSourceCodeLink(), null, "methodLevelSourceCodeLink", null, 0, -1,
                SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getSourceCodeDecoratorRepository_ControlFlowLevelSourceCodeLink(),
                this.getControlFlowLevelSourceCodeLink(), null, "controlFlowLevelSourceCodeLink", null, 0, -1,
                SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getSourceCodeDecoratorRepository_InterfaceSourceCodeLink(),
                this.getInterfaceSourceCodeLink(), null, "interfaceSourceCodeLink", null, 0, -1,
                SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getSourceCodeDecoratorRepository_ComponentImplementingClassesLink(),
                this.getComponentImplementingClassesLink(), null, "componentImplementingClassesLink", null, 0, -1,
                SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getSourceCodeDecoratorRepository_DataTypeSourceCodeLink(),
                this.getDataTypeSourceCodeLink(), null, "dataTypeSourceCodeLink", null, 0, -1,
                SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getSourceCodeDecoratorRepository_AbstractActionClassMethodLink(),
                this.getAbstractActionClassMethodLink(), null, "abstractActionClassMethodLink", null, 0, -1,
                SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getSourceCodeDecoratorRepository_MethodLevelResourceDemandingInternalBehaviorLink(),
                this.getMethodLevelResourceDemandingInternalBehaviorLink(), null,
                "methodLevelResourceDemandingInternalBehaviorLink", null, 0, -1, SourceCodeDecoratorRepository.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.interfaceSourceCodeLinkEClass, InterfaceSourceCodeLink.class, "InterfaceSourceCodeLink",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getInterfaceSourceCodeLink_Interface(), theRepositoryPackage.getInterface(), null,
                "interface", null, 0, 1, InterfaceSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getInterfaceSourceCodeLink_GastClass(), theClassifiersPackage.getConcreteClassifier(),
                null, "gastClass", null, 0, 1, InterfaceSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        this.initEClass(this.componentImplementingClassesLinkEClass, ComponentImplementingClassesLink.class,
                "ComponentImplementingClassesLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getComponentImplementingClassesLink_IsCompositeComponent(),
                this.ecorePackage.getEBoolean(), "isCompositeComponent", null, 1, 1,
                ComponentImplementingClassesLink.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_UNSETTABLE,
                !IS_ID, !IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getComponentImplementingClassesLink_Component(),
                theRepositoryPackage.getRepositoryComponent(), null, "component", null, 1, 1,
                ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getComponentImplementingClassesLink_ImplementingClasses(),
                theClassifiersPackage.getConcreteClassifier(), null, "implementingClasses", null, 1, -1,
                ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getComponentImplementingClassesLink_SubComponents(),
                this.getComponentImplementingClassesLink(), null, "subComponents", null, 0, -1,
                ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getComponentImplementingClassesLink_ProvidedInterfaces(),
                this.getInterfaceSourceCodeLink(), null, "providedInterfaces", null, 0, -1,
                ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEReference(this.getComponentImplementingClassesLink_RequiredInterfaces(),
                this.getInterfaceSourceCodeLink(), null, "requiredInterfaces", null, 0, -1,
                ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        this.initEAttribute(this.getComponentImplementingClassesLink_IsInitialComponent(),
                this.ecorePackage.getEBoolean(), "isInitialComponent", null, 1, 1,
                ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        this.initEClass(this.pcmSystemImplementatingClassesLinkEClass, PCMSystemImplementatingClassesLink.class,
                "PCMSystemImplementatingClassesLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getPCMSystemImplementatingClassesLink_SystemModel(), theSystemPackage.getSystem(),
                null, "systemModel", null, 0, 1, PCMSystemImplementatingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        this.initEClass(this.dataTypeSourceCodeLinkEClass, DataTypeSourceCodeLink.class, "DataTypeSourceCodeLink",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getDataTypeSourceCodeLink_JaMoPPType(), theTypesPackage.getType(), null, "jaMoPPType",
                null, 0, 1, DataTypeSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getDataTypeSourceCodeLink_PcmDataType(), theRepositoryPackage.getDataType(), null,
                "pcmDataType", null, 0, 1, DataTypeSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getDataTypeSourceCodeLink_InnerDatatypeSourceCodeLink(),
                this.getInnerDatatypeSourceCodeLink(), null, "innerDatatypeSourceCodeLink", null, 0, -1,
                DataTypeSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.innerDatatypeSourceCodeLinkEClass, InnerDatatypeSourceCodeLink.class,
                "InnerDatatypeSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getInnerDatatypeSourceCodeLink_Field(), theMembersPackage.getField(), null, "field",
                null, 0, 1, InnerDatatypeSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getInnerDatatypeSourceCodeLink_InnerDeclaration(),
                theRepositoryPackage.getInnerDeclaration(), null, "innerDeclaration", null, 0, 1,
                InnerDatatypeSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.abstractActionClassMethodLinkEClass, AbstractActionClassMethodLink.class,
                "AbstractActionClassMethodLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getAbstractActionClassMethodLink_ClassMethod(), theMembersPackage.getClassMethod(),
                null, "classMethod", null, 0, 1, AbstractActionClassMethodLink.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getAbstractActionClassMethodLink_AbstractAction(), theSeffPackage.getAbstractAction(),
                null, "abstractAction", null, 0, 1, AbstractActionClassMethodLink.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.methodLevelResourceDemandingInternalBehaviorLinkEClass,
                MethodLevelResourceDemandingInternalBehaviorLink.class,
                "MethodLevelResourceDemandingInternalBehaviorLink", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(
                this.getMethodLevelResourceDemandingInternalBehaviorLink_ResourceDemandingInternalBehaviour(),
                theSeffPackage.getResourceDemandingInternalBehaviour(), null, "resourceDemandingInternalBehaviour",
                null, 0, 1, MethodLevelResourceDemandingInternalBehaviorLink.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.abstractMethodLevelSourceCodeLinkEClass, AbstractMethodLevelSourceCodeLink.class,
                "AbstractMethodLevelSourceCodeLink", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getAbstractMethodLevelSourceCodeLink_Function(), theMembersPackage.getMember(), null,
                "function", null, 0, 1, AbstractMethodLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        // Create resource
        this.createResource(eNS_URI);
    }

} // SourcecodedecoratorPackageImpl
