/**
 */
package org.somox.sourcecodedecorator.impl;

import de.uka.ipd.sdq.identifier.IdentifierPackage;
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
import org.somox.sourcecodedecorator.SEFF2MethodMapping;
import org.somox.sourcecodedecorator.SeffElementSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class SourcecodedecoratorPackageImpl extends EPackageImpl implements SourcecodedecoratorPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fileLevelSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass methodLevelSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass controlFlowLevelSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass sourceCodeDecoratorRepositoryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass interfaceSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass componentImplementingClassesLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass pcmSystemImplementatingClassesLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass dataTypeSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass innerDatatypeSourceCodeLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractActionClassMethodLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass methodLevelResourceDemandingInternalBehaviorLinkEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractMethodLevelSourceCodeLinkEClass = null;

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
    private EClass seffElementSourceCodeLinkEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link SourcecodedecoratorPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static SourcecodedecoratorPackage init() {
        if (isInited) return (SourcecodedecoratorPackage)EPackage.Registry.INSTANCE.getEPackage(SourcecodedecoratorPackage.eNS_URI);

        // Obtain or create and register package
        SourcecodedecoratorPackageImpl theSourcecodedecoratorPackage = (SourcecodedecoratorPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SourcecodedecoratorPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SourcecodedecoratorPackageImpl());

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
     * @generated
     */
    @Override
    public EClass getFileLevelSourceCodeLink() {
        return fileLevelSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFileLevelSourceCodeLink_RepositoryComponent() {
        return (EReference)fileLevelSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFileLevelSourceCodeLink_File() {
        return (EReference)fileLevelSourceCodeLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getMethodLevelSourceCodeLink() {
        return methodLevelSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getMethodLevelSourceCodeLink_Operation() {
        return (EReference)methodLevelSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getControlFlowLevelSourceCodeLink() {
        return controlFlowLevelSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getControlFlowLevelSourceCodeLink_AbstractAction() {
        return (EReference)controlFlowLevelSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getControlFlowLevelSourceCodeLink_Statement() {
        return (EReference)controlFlowLevelSourceCodeLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSourceCodeDecoratorRepository() {
        return sourceCodeDecoratorRepositoryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_FileLevelSourceCodeLink() {
        return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_MethodLevelSourceCodeLink() {
        return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_ControlFlowLevelSourceCodeLink() {
        return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_InterfaceSourceCodeLink() {
        return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_ComponentImplementingClassesLink() {
        return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_DataTypeSourceCodeLink() {
        return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_AbstractActionClassMethodLink() {
        return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSourceCodeDecoratorRepository_MethodLevelResourceDemandingInternalBehaviorLink() {
        return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSourceCodeDecoratorRepository_Seff2MethodMappings() {
        return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSourceCodeDecoratorRepository_SeffElementsSourceCodeLinks() {
        return (EReference)sourceCodeDecoratorRepositoryEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getInterfaceSourceCodeLink() {
        return interfaceSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getInterfaceSourceCodeLink_Interface() {
        return (EReference)interfaceSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getInterfaceSourceCodeLink_GastClass() {
        return (EReference)interfaceSourceCodeLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getComponentImplementingClassesLink() {
        return componentImplementingClassesLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getComponentImplementingClassesLink_IsCompositeComponent() {
        return (EAttribute)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getComponentImplementingClassesLink_Component() {
        return (EReference)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getComponentImplementingClassesLink_ImplementingClasses() {
        return (EReference)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getComponentImplementingClassesLink_SubComponents() {
        return (EReference)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getComponentImplementingClassesLink_ProvidedInterfaces() {
        return (EReference)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getComponentImplementingClassesLink_RequiredInterfaces() {
        return (EReference)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getComponentImplementingClassesLink_IsInitialComponent() {
        return (EAttribute)componentImplementingClassesLinkEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPCMSystemImplementatingClassesLink() {
        return pcmSystemImplementatingClassesLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getPCMSystemImplementatingClassesLink_SystemModel() {
        return (EReference)pcmSystemImplementatingClassesLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getDataTypeSourceCodeLink() {
        return dataTypeSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDataTypeSourceCodeLink_JaMoPPType() {
        return (EReference)dataTypeSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDataTypeSourceCodeLink_PcmDataType() {
        return (EReference)dataTypeSourceCodeLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDataTypeSourceCodeLink_InnerDatatypeSourceCodeLink() {
        return (EReference)dataTypeSourceCodeLinkEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getInnerDatatypeSourceCodeLink() {
        return innerDatatypeSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getInnerDatatypeSourceCodeLink_Field() {
        return (EReference)innerDatatypeSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getInnerDatatypeSourceCodeLink_InnerDeclaration() {
        return (EReference)innerDatatypeSourceCodeLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbstractActionClassMethodLink() {
        return abstractActionClassMethodLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAbstractActionClassMethodLink_ClassMethod() {
        return (EReference)abstractActionClassMethodLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAbstractActionClassMethodLink_AbstractAction() {
        return (EReference)abstractActionClassMethodLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getMethodLevelResourceDemandingInternalBehaviorLink() {
        return methodLevelResourceDemandingInternalBehaviorLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getMethodLevelResourceDemandingInternalBehaviorLink_ResourceDemandingInternalBehaviour() {
        return (EReference)methodLevelResourceDemandingInternalBehaviorLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbstractMethodLevelSourceCodeLink() {
        return abstractMethodLevelSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAbstractMethodLevelSourceCodeLink_Function() {
        return (EReference)abstractMethodLevelSourceCodeLinkEClass.getEStructuralFeatures().get(0);
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
    public EReference getSEFF2MethodMapping_Seff() {
        return (EReference)seff2MethodMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSEFF2MethodMapping_StatementListContainer() {
        return (EReference)seff2MethodMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSeffElementSourceCodeLink() {
        return seffElementSourceCodeLinkEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSeffElementSourceCodeLink_SeffElement() {
        return (EReference)seffElementSourceCodeLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSeffElementSourceCodeLink_Statement() {
        return (EReference)seffElementSourceCodeLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SourcecodedecoratorFactory getSourcecodedecoratorFactory() {
        return (SourcecodedecoratorFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        fileLevelSourceCodeLinkEClass = createEClass(FILE_LEVEL_SOURCE_CODE_LINK);
        createEReference(fileLevelSourceCodeLinkEClass, FILE_LEVEL_SOURCE_CODE_LINK__REPOSITORY_COMPONENT);
        createEReference(fileLevelSourceCodeLinkEClass, FILE_LEVEL_SOURCE_CODE_LINK__FILE);

        methodLevelSourceCodeLinkEClass = createEClass(METHOD_LEVEL_SOURCE_CODE_LINK);
        createEReference(methodLevelSourceCodeLinkEClass, METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION);

        controlFlowLevelSourceCodeLinkEClass = createEClass(CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK);
        createEReference(controlFlowLevelSourceCodeLinkEClass, CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION);
        createEReference(controlFlowLevelSourceCodeLinkEClass, CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT);

        sourceCodeDecoratorRepositoryEClass = createEClass(SOURCE_CODE_DECORATOR_REPOSITORY);
        createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK);
        createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK);
        createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK);
        createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK);
        createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK);
        createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK);
        createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK);
        createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK);
        createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__SEFF2_METHOD_MAPPINGS);
        createEReference(sourceCodeDecoratorRepositoryEClass, SOURCE_CODE_DECORATOR_REPOSITORY__SEFF_ELEMENTS_SOURCE_CODE_LINKS);

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

        pcmSystemImplementatingClassesLinkEClass = createEClass(PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK);
        createEReference(pcmSystemImplementatingClassesLinkEClass, PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SYSTEM_MODEL);

        dataTypeSourceCodeLinkEClass = createEClass(DATA_TYPE_SOURCE_CODE_LINK);
        createEReference(dataTypeSourceCodeLinkEClass, DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE);
        createEReference(dataTypeSourceCodeLinkEClass, DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE);
        createEReference(dataTypeSourceCodeLinkEClass, DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK);

        innerDatatypeSourceCodeLinkEClass = createEClass(INNER_DATATYPE_SOURCE_CODE_LINK);
        createEReference(innerDatatypeSourceCodeLinkEClass, INNER_DATATYPE_SOURCE_CODE_LINK__FIELD);
        createEReference(innerDatatypeSourceCodeLinkEClass, INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION);

        abstractActionClassMethodLinkEClass = createEClass(ABSTRACT_ACTION_CLASS_METHOD_LINK);
        createEReference(abstractActionClassMethodLinkEClass, ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD);
        createEReference(abstractActionClassMethodLinkEClass, ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION);

        methodLevelResourceDemandingInternalBehaviorLinkEClass = createEClass(METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK);
        createEReference(methodLevelResourceDemandingInternalBehaviorLinkEClass, METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK__RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR);

        abstractMethodLevelSourceCodeLinkEClass = createEClass(ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK);
        createEReference(abstractMethodLevelSourceCodeLinkEClass, ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION);

        seff2MethodMappingEClass = createEClass(SEFF2_METHOD_MAPPING);
        createEReference(seff2MethodMappingEClass, SEFF2_METHOD_MAPPING__SEFF);
        createEReference(seff2MethodMappingEClass, SEFF2_METHOD_MAPPING__STATEMENT_LIST_CONTAINER);

        seffElementSourceCodeLinkEClass = createEClass(SEFF_ELEMENT_SOURCE_CODE_LINK);
        createEReference(seffElementSourceCodeLinkEClass, SEFF_ELEMENT_SOURCE_CODE_LINK__SEFF_ELEMENT);
        createEReference(seffElementSourceCodeLinkEClass, SEFF_ELEMENT_SOURCE_CODE_LINK__STATEMENT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
        RepositoryPackage theRepositoryPackage = (RepositoryPackage)EPackage.Registry.INSTANCE.getEPackage(RepositoryPackage.eNS_URI);
        ContainersPackage theContainersPackage = (ContainersPackage)EPackage.Registry.INSTANCE.getEPackage(ContainersPackage.eNS_URI);
        SeffPackage theSeffPackage = (SeffPackage)EPackage.Registry.INSTANCE.getEPackage(SeffPackage.eNS_URI);
        StatementsPackage theStatementsPackage = (StatementsPackage)EPackage.Registry.INSTANCE.getEPackage(StatementsPackage.eNS_URI);
        ClassifiersPackage theClassifiersPackage = (ClassifiersPackage)EPackage.Registry.INSTANCE.getEPackage(ClassifiersPackage.eNS_URI);
        SystemPackage theSystemPackage = (SystemPackage)EPackage.Registry.INSTANCE.getEPackage(SystemPackage.eNS_URI);
        TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);
        MembersPackage theMembersPackage = (MembersPackage)EPackage.Registry.INSTANCE.getEPackage(MembersPackage.eNS_URI);
        IdentifierPackage theIdentifierPackage = (IdentifierPackage)EPackage.Registry.INSTANCE.getEPackage(IdentifierPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        methodLevelSourceCodeLinkEClass.getESuperTypes().add(this.getAbstractMethodLevelSourceCodeLink());
        controlFlowLevelSourceCodeLinkEClass.getESuperTypes().add(this.getMethodLevelSourceCodeLink());
        pcmSystemImplementatingClassesLinkEClass.getESuperTypes().add(this.getComponentImplementingClassesLink());
        dataTypeSourceCodeLinkEClass.getESuperTypes().add(this.getFileLevelSourceCodeLink());
        methodLevelResourceDemandingInternalBehaviorLinkEClass.getESuperTypes().add(this.getAbstractMethodLevelSourceCodeLink());
        abstractMethodLevelSourceCodeLinkEClass.getESuperTypes().add(this.getFileLevelSourceCodeLink());

        // Initialize classes, features, and operations; add parameters
        initEClass(fileLevelSourceCodeLinkEClass, FileLevelSourceCodeLink.class, "FileLevelSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFileLevelSourceCodeLink_RepositoryComponent(), theRepositoryPackage.getRepositoryComponent(), null, "repositoryComponent", null, 0, 1, FileLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getFileLevelSourceCodeLink_File(), theContainersPackage.getCompilationUnit(), null, "file", null, 0, 1, FileLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(methodLevelSourceCodeLinkEClass, MethodLevelSourceCodeLink.class, "MethodLevelSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMethodLevelSourceCodeLink_Operation(), theRepositoryPackage.getSignature(), null, "operation", null, 0, 1, MethodLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(controlFlowLevelSourceCodeLinkEClass, ControlFlowLevelSourceCodeLink.class, "ControlFlowLevelSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getControlFlowLevelSourceCodeLink_AbstractAction(), theSeffPackage.getAbstractAction(), null, "abstractAction", null, 0, 1, ControlFlowLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getControlFlowLevelSourceCodeLink_Statement(), theStatementsPackage.getStatement(), null, "statement", null, 0, 1, ControlFlowLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(sourceCodeDecoratorRepositoryEClass, SourceCodeDecoratorRepository.class, "SourceCodeDecoratorRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSourceCodeDecoratorRepository_FileLevelSourceCodeLink(), this.getFileLevelSourceCodeLink(), null, "fileLevelSourceCodeLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getSourceCodeDecoratorRepository_MethodLevelSourceCodeLink(), this.getMethodLevelSourceCodeLink(), null, "methodLevelSourceCodeLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getSourceCodeDecoratorRepository_ControlFlowLevelSourceCodeLink(), this.getControlFlowLevelSourceCodeLink(), null, "controlFlowLevelSourceCodeLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getSourceCodeDecoratorRepository_InterfaceSourceCodeLink(), this.getInterfaceSourceCodeLink(), null, "interfaceSourceCodeLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getSourceCodeDecoratorRepository_ComponentImplementingClassesLink(), this.getComponentImplementingClassesLink(), null, "componentImplementingClassesLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getSourceCodeDecoratorRepository_DataTypeSourceCodeLink(), this.getDataTypeSourceCodeLink(), null, "dataTypeSourceCodeLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getSourceCodeDecoratorRepository_AbstractActionClassMethodLink(), this.getAbstractActionClassMethodLink(), null, "abstractActionClassMethodLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSourceCodeDecoratorRepository_MethodLevelResourceDemandingInternalBehaviorLink(), this.getMethodLevelResourceDemandingInternalBehaviorLink(), null, "methodLevelResourceDemandingInternalBehaviorLink", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSourceCodeDecoratorRepository_Seff2MethodMappings(), this.getSEFF2MethodMapping(), null, "seff2MethodMappings", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSourceCodeDecoratorRepository_SeffElementsSourceCodeLinks(), this.getSeffElementSourceCodeLink(), null, "seffElementsSourceCodeLinks", null, 0, -1, SourceCodeDecoratorRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(interfaceSourceCodeLinkEClass, InterfaceSourceCodeLink.class, "InterfaceSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getInterfaceSourceCodeLink_Interface(), theRepositoryPackage.getInterface(), null, "interface", null, 0, 1, InterfaceSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getInterfaceSourceCodeLink_GastClass(), theClassifiersPackage.getConcreteClassifier(), null, "gastClass", null, 0, 1, InterfaceSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(componentImplementingClassesLinkEClass, ComponentImplementingClassesLink.class, "ComponentImplementingClassesLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getComponentImplementingClassesLink_IsCompositeComponent(), ecorePackage.getEBoolean(), "isCompositeComponent", null, 1, 1, ComponentImplementingClassesLink.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
        initEReference(getComponentImplementingClassesLink_Component(), theRepositoryPackage.getRepositoryComponent(), null, "component", null, 1, 1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getComponentImplementingClassesLink_ImplementingClasses(), theClassifiersPackage.getConcreteClassifier(), null, "implementingClasses", null, 1, -1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getComponentImplementingClassesLink_SubComponents(), this.getComponentImplementingClassesLink(), null, "subComponents", null, 0, -1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getComponentImplementingClassesLink_ProvidedInterfaces(), this.getInterfaceSourceCodeLink(), null, "providedInterfaces", null, 0, -1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getComponentImplementingClassesLink_RequiredInterfaces(), this.getInterfaceSourceCodeLink(), null, "requiredInterfaces", null, 0, -1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getComponentImplementingClassesLink_IsInitialComponent(), ecorePackage.getEBoolean(), "isInitialComponent", null, 1, 1, ComponentImplementingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(pcmSystemImplementatingClassesLinkEClass, PCMSystemImplementatingClassesLink.class, "PCMSystemImplementatingClassesLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPCMSystemImplementatingClassesLink_SystemModel(), theSystemPackage.getSystem(), null, "systemModel", null, 0, 1, PCMSystemImplementatingClassesLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(dataTypeSourceCodeLinkEClass, DataTypeSourceCodeLink.class, "DataTypeSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDataTypeSourceCodeLink_JaMoPPType(), theTypesPackage.getType(), null, "jaMoPPType", null, 0, 1, DataTypeSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDataTypeSourceCodeLink_PcmDataType(), theRepositoryPackage.getDataType(), null, "pcmDataType", null, 0, 1, DataTypeSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDataTypeSourceCodeLink_InnerDatatypeSourceCodeLink(), this.getInnerDatatypeSourceCodeLink(), null, "innerDatatypeSourceCodeLink", null, 0, -1, DataTypeSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(innerDatatypeSourceCodeLinkEClass, InnerDatatypeSourceCodeLink.class, "InnerDatatypeSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getInnerDatatypeSourceCodeLink_Field(), theMembersPackage.getField(), null, "field", null, 0, 1, InnerDatatypeSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInnerDatatypeSourceCodeLink_InnerDeclaration(), theRepositoryPackage.getInnerDeclaration(), null, "innerDeclaration", null, 0, 1, InnerDatatypeSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractActionClassMethodLinkEClass, AbstractActionClassMethodLink.class, "AbstractActionClassMethodLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbstractActionClassMethodLink_ClassMethod(), theMembersPackage.getClassMethod(), null, "classMethod", null, 0, 1, AbstractActionClassMethodLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractActionClassMethodLink_AbstractAction(), theSeffPackage.getAbstractAction(), null, "abstractAction", null, 0, 1, AbstractActionClassMethodLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(methodLevelResourceDemandingInternalBehaviorLinkEClass, MethodLevelResourceDemandingInternalBehaviorLink.class, "MethodLevelResourceDemandingInternalBehaviorLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMethodLevelResourceDemandingInternalBehaviorLink_ResourceDemandingInternalBehaviour(), theSeffPackage.getResourceDemandingInternalBehaviour(), null, "resourceDemandingInternalBehaviour", null, 0, 1, MethodLevelResourceDemandingInternalBehaviorLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractMethodLevelSourceCodeLinkEClass, AbstractMethodLevelSourceCodeLink.class, "AbstractMethodLevelSourceCodeLink", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbstractMethodLevelSourceCodeLink_Function(), theMembersPackage.getMember(), null, "function", null, 0, 1, AbstractMethodLevelSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

        initEClass(seff2MethodMappingEClass, SEFF2MethodMapping.class, "SEFF2MethodMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSEFF2MethodMapping_Seff(), theSeffPackage.getServiceEffectSpecification(), null, "seff", null, 1, 1, SEFF2MethodMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSEFF2MethodMapping_StatementListContainer(), theStatementsPackage.getStatementListContainer(), null, "statementListContainer", null, 1, 1, SEFF2MethodMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(seffElementSourceCodeLinkEClass, SeffElementSourceCodeLink.class, "SeffElementSourceCodeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSeffElementSourceCodeLink_SeffElement(), theIdentifierPackage.getIdentifier(), null, "seffElement", null, 1, 1, SeffElementSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSeffElementSourceCodeLink_Statement(), theStatementsPackage.getStatement(), null, "statement", null, 1, -1, SeffElementSourceCodeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // SourcecodedecoratorPackageImpl
