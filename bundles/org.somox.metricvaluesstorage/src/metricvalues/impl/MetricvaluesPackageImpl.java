/**
 */
package metricvalues.impl;

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

import metricvalues.Component;
import metricvalues.ComponentCandidate;
import metricvalues.Iteration;
import metricvalues.MetricValue;
import metricvalues.MetricValuesModel;
import metricvalues.MetricvaluesFactory;
import metricvalues.MetricvaluesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class MetricvaluesPackageImpl extends EPackageImpl implements MetricvaluesPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass componentEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass componentCandidateEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass iterationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass metricValueEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass metricValuesModelEClass = null;

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
     * @see metricvalues.MetricvaluesPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private MetricvaluesPackageImpl() {
        super(eNS_URI, MetricvaluesFactory.eINSTANCE);
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
     * This method is used to initialize {@link MetricvaluesPackage#eINSTANCE} when that field is
     * accessed. Clients should not invoke it directly. Instead, they should simply access that
     * field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static MetricvaluesPackage init() {
        if (isInited) {
            return (MetricvaluesPackage) EPackage.Registry.INSTANCE.getEPackage(MetricvaluesPackage.eNS_URI);
        }

        // Obtain or create and register package
        final MetricvaluesPackageImpl theMetricvaluesPackage = (MetricvaluesPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof MetricvaluesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new MetricvaluesPackageImpl());

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

        // Create package meta-data objects
        theMetricvaluesPackage.createPackageContents();

        // Initialize created meta-data
        theMetricvaluesPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theMetricvaluesPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(MetricvaluesPackage.eNS_URI, theMetricvaluesPackage);
        return theMetricvaluesPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getComponent() {
        return this.componentEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getComponent_SubComponents() {
        return (EReference) this.componentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getComponent_Name() {
        return (EAttribute) this.componentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getComponent_Id() {
        return (EAttribute) this.componentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getComponent_Classes() {
        return (EReference) this.componentEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getComponentCandidate() {
        return this.componentCandidateEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getComponentCandidate_MetricValues() {
        return (EReference) this.componentCandidateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getComponentCandidate_FirstComponent() {
        return (EReference) this.componentCandidateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getComponentCandidate_SecondComponent() {
        return (EReference) this.componentCandidateEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getIteration() {
        return this.iterationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getIteration_ComponentCandidates() {
        return (EReference) this.iterationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getIteration_Components() {
        return (EReference) this.iterationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getIteration_Number() {
        return (EAttribute) this.iterationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getIteration_CurCompThreshold() {
        return (EAttribute) this.iterationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getIteration_CurMergeThreshold() {
        return (EAttribute) this.iterationEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getIteration_IsMergeIteration() {
        return (EAttribute) this.iterationEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMetricValue() {
        return this.metricValueEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValue_MetricID() {
        return (EAttribute) this.metricValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValue_Value() {
        return (EAttribute) this.metricValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMetricValuesModel() {
        return this.metricValuesModelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMetricValuesModel_Iterations() {
        return (EReference) this.metricValuesModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_MinCompThreshold() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_MaxMergeThreshold() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightLowCoupling() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightHighCoupling() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightLowNameResemblance() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightMidNameResemblance() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightHighNameResemblance() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightInterfaceViolationRelevant() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightInterfaceViolationIrrelevant() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightHighSLAQ() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightLowSLAQ() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightPackageMapping() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightDirectoryMapping() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightDMS() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WeightHighestNameResemblance() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_WildcardKey() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_AdditionalWildcards() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_MinMergeThreshold() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_MaxComposeThreshold() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_ComposeThresholdDecrement() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_MergeThresholdDecrement() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_ExcludedPrefixesForNameResemblance() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricValuesModel_ExcludedSuffixesForNameResemblance() {
        return (EAttribute) this.metricValuesModelEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetricvaluesFactory getMetricvaluesFactory() {
        return (MetricvaluesFactory) this.getEFactoryInstance();
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
        this.componentEClass = this.createEClass(COMPONENT);
        this.createEReference(this.componentEClass, COMPONENT__SUB_COMPONENTS);
        this.createEAttribute(this.componentEClass, COMPONENT__NAME);
        this.createEAttribute(this.componentEClass, COMPONENT__ID);
        this.createEReference(this.componentEClass, COMPONENT__CLASSES);

        this.componentCandidateEClass = this.createEClass(COMPONENT_CANDIDATE);
        this.createEReference(this.componentCandidateEClass, COMPONENT_CANDIDATE__METRIC_VALUES);
        this.createEReference(this.componentCandidateEClass, COMPONENT_CANDIDATE__FIRST_COMPONENT);
        this.createEReference(this.componentCandidateEClass, COMPONENT_CANDIDATE__SECOND_COMPONENT);

        this.iterationEClass = this.createEClass(ITERATION);
        this.createEReference(this.iterationEClass, ITERATION__COMPONENT_CANDIDATES);
        this.createEReference(this.iterationEClass, ITERATION__COMPONENTS);
        this.createEAttribute(this.iterationEClass, ITERATION__NUMBER);
        this.createEAttribute(this.iterationEClass, ITERATION__CUR_COMP_THRESHOLD);
        this.createEAttribute(this.iterationEClass, ITERATION__CUR_MERGE_THRESHOLD);
        this.createEAttribute(this.iterationEClass, ITERATION__IS_MERGE_ITERATION);

        this.metricValueEClass = this.createEClass(METRIC_VALUE);
        this.createEAttribute(this.metricValueEClass, METRIC_VALUE__METRIC_ID);
        this.createEAttribute(this.metricValueEClass, METRIC_VALUE__VALUE);

        this.metricValuesModelEClass = this.createEClass(METRIC_VALUES_MODEL);
        this.createEReference(this.metricValuesModelEClass, METRIC_VALUES_MODEL__ITERATIONS);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_DMS);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__WILDCARD_KEY);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT);
        this.createEAttribute(this.metricValuesModelEClass, METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT);
        this.createEAttribute(this.metricValuesModelEClass,
                METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE);
        this.createEAttribute(this.metricValuesModelEClass,
                METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE);
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
        final TypesPackage theTypesPackage = (TypesPackage) EPackage.Registry.INSTANCE
                .getEPackage(TypesPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        this.initEClass(this.componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getComponent_SubComponents(), this.getComponent(), null, "subComponents", null, 0, -1,
                Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getComponent_Name(), this.ecorePackage.getEString(), "name", null, 0, 1,
                Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getComponent_Id(), this.ecorePackage.getEString(), "id", null, 0, 1, Component.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getComponent_Classes(), theTypesPackage.getType(), null, "classes", null, 0, -1,
                Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.componentCandidateEClass, ComponentCandidate.class, "ComponentCandidate", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getComponentCandidate_MetricValues(), this.getMetricValue(), null, "metricValues",
                null, 0, -1, ComponentCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getComponentCandidate_FirstComponent(), this.getComponent(), null, "firstComponent",
                null, 1, 1, ComponentCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getComponentCandidate_SecondComponent(), this.getComponent(), null, "secondComponent",
                null, 1, 1, ComponentCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.iterationEClass, Iteration.class, "Iteration", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getIteration_ComponentCandidates(), this.getComponentCandidate(), null,
                "componentCandidates", null, 0, -1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getIteration_Components(), this.getComponent(), null, "components", null, 0, -1,
                Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getIteration_Number(), this.ecorePackage.getEInt(), "number", null, 0, 1,
                Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getIteration_CurCompThreshold(), this.ecorePackage.getEDouble(), "curCompThreshold",
                null, 0, 1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getIteration_CurMergeThreshold(), this.ecorePackage.getEDouble(), "curMergeThreshold",
                null, 0, 1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getIteration_IsMergeIteration(), this.ecorePackage.getEBoolean(), "isMergeIteration",
                null, 0, 1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.metricValueEClass, MetricValue.class, "MetricValue", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getMetricValue_MetricID(), this.ecorePackage.getEString(), "metricID", null, 0, 1,
                MetricValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValue_Value(), this.ecorePackage.getEDouble(), "value", null, 0, 1,
                MetricValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.metricValuesModelEClass, MetricValuesModel.class, "MetricValuesModel", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getMetricValuesModel_Iterations(), this.getIteration(), null, "iterations", null, 0,
                -1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_MinCompThreshold(), this.ecorePackage.getEDouble(),
                "minCompThreshold", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_MaxMergeThreshold(), this.ecorePackage.getEDouble(),
                "maxMergeThreshold", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightLowCoupling(), this.ecorePackage.getEDouble(),
                "weightLowCoupling", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightHighCoupling(), this.ecorePackage.getEDouble(),
                "weightHighCoupling", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightLowNameResemblance(), this.ecorePackage.getEDouble(),
                "weightLowNameResemblance", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightMidNameResemblance(), this.ecorePackage.getEDouble(),
                "weightMidNameResemblance", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightHighNameResemblance(), this.ecorePackage.getEDouble(),
                "weightHighNameResemblance", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightInterfaceViolationRelevant(),
                this.ecorePackage.getEDouble(), "weightInterfaceViolationRelevant", null, 0, 1, MetricValuesModel.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightInterfaceViolationIrrelevant(),
                this.ecorePackage.getEDouble(), "weightInterfaceViolationIrrelevant", null, 0, 1,
                MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightHighSLAQ(), this.ecorePackage.getEDouble(),
                "weightHighSLAQ", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightLowSLAQ(), this.ecorePackage.getEDouble(), "weightLowSLAQ",
                null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightPackageMapping(), this.ecorePackage.getEDouble(),
                "weightPackageMapping", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightDirectoryMapping(), this.ecorePackage.getEDouble(),
                "weightDirectoryMapping", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightDMS(), this.ecorePackage.getEDouble(), "weightDMS", null, 0,
                1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WeightHighestNameResemblance(), this.ecorePackage.getEDouble(),
                "weightHighestNameResemblance", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_WildcardKey(), this.ecorePackage.getEString(), "wildcardKey",
                null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_AdditionalWildcards(), this.ecorePackage.getEString(),
                "additionalWildcards", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_MinMergeThreshold(), this.ecorePackage.getEDouble(),
                "minMergeThreshold", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_MaxComposeThreshold(), this.ecorePackage.getEDouble(),
                "maxComposeThreshold", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_ComposeThresholdDecrement(), this.ecorePackage.getEDouble(),
                "composeThresholdDecrement", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_MergeThresholdDecrement(), this.ecorePackage.getEDouble(),
                "mergeThresholdDecrement", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_ExcludedPrefixesForNameResemblance(),
                this.ecorePackage.getEString(), "excludedPrefixesForNameResemblance", null, 0, 1,
                MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMetricValuesModel_ExcludedSuffixesForNameResemblance(),
                this.ecorePackage.getEString(), "excludedSuffixesForNameResemblance", null, 0, 1,
                MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        // Create resource
        this.createResource(eNS_URI);
    }

} // MetricvaluesPackageImpl
