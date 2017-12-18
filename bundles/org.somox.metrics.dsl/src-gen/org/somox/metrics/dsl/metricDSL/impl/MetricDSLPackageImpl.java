/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.somox.metrics.dsl.metricDSL.BoundAndWeight;
import org.somox.metrics.dsl.metricDSL.Constant;
import org.somox.metrics.dsl.metricDSL.ExternalMetric;
import org.somox.metrics.dsl.metricDSL.InternalMetric;
import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.MetricAndWeight;
import org.somox.metrics.dsl.metricDSL.MetricDSLFactory;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.MetricDefinition;
import org.somox.metrics.dsl.metricDSL.MetricModel;
import org.somox.metrics.dsl.metricDSL.Parameter;
import org.somox.metrics.dsl.metricDSL.RatioMetric;
import org.somox.metrics.dsl.metricDSL.StepwiseMetric;
import org.somox.metrics.dsl.metricDSL.WeightedMetric;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class MetricDSLPackageImpl extends EPackageImpl implements MetricDSLPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass metricModelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass metricEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass externalMetricEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass internalMetricEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass numberEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass parameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass constantEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass metricDefinitionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass weightedMetricEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass stepwiseMetricEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass ratioMetricEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass boundAndWeightEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass metricAndWeightEClass = null;

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
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private MetricDSLPackageImpl() {
        super(eNS_URI, MetricDSLFactory.eINSTANCE);
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
     * This method is used to initialize {@link MetricDSLPackage#eINSTANCE} when that field is
     * accessed. Clients should not invoke it directly. Instead, they should simply access that
     * field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static MetricDSLPackage init() {
        if (isInited) {
            return (MetricDSLPackage) EPackage.Registry.INSTANCE.getEPackage(MetricDSLPackage.eNS_URI);
        }

        // Obtain or create and register package
        final MetricDSLPackageImpl theMetricDSLPackage = (MetricDSLPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof MetricDSLPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new MetricDSLPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theMetricDSLPackage.createPackageContents();

        // Initialize created meta-data
        theMetricDSLPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theMetricDSLPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(MetricDSLPackage.eNS_URI, theMetricDSLPackage);
        return theMetricDSLPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMetricModel() {
        return this.metricModelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetricModel_ImportURI() {
        return (EAttribute) this.metricModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMetricModel_Metrics() {
        return (EReference) this.metricModelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMetric() {
        return this.metricEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMetric_Name() {
        return (EAttribute) this.metricEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getExternalMetric() {
        return this.externalMetricEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getInternalMetric() {
        return this.internalMetricEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getInternalMetric_ShortName() {
        return (EAttribute) this.internalMetricEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getInternalMetric_Description() {
        return (EAttribute) this.internalMetricEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInternalMetric_Parameter() {
        return (EReference) this.internalMetricEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInternalMetric_Definition() {
        return (EReference) this.internalMetricEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNumber() {
        return this.numberEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNumber_Name() {
        return (EAttribute) this.numberEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getParameter() {
        return this.parameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getParameter_Shortname() {
        return (EAttribute) this.parameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getParameter_Description() {
        return (EAttribute) this.parameterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getParameter_DefaultValue() {
        return (EAttribute) this.parameterEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getConstant() {
        return this.constantEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getConstant_Value() {
        return (EAttribute) this.constantEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMetricDefinition() {
        return this.metricDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getWeightedMetric() {
        return this.weightedMetricEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getWeightedMetric_Weights() {
        return (EReference) this.weightedMetricEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getStepwiseMetric() {
        return this.stepwiseMetricEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getStepwiseMetric_InnerMetric() {
        return (EReference) this.stepwiseMetricEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getStepwiseMetric_Steps() {
        return (EReference) this.stepwiseMetricEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRatioMetric() {
        return this.ratioMetricEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRatioMetric_NominatorMetric() {
        return (EReference) this.ratioMetricEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRatioMetric_DenominatorMetric() {
        return (EReference) this.ratioMetricEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBoundAndWeight() {
        return this.boundAndWeightEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getBoundAndWeight_UpperBound() {
        return (EReference) this.boundAndWeightEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getBoundAndWeight_Weight() {
        return (EReference) this.boundAndWeightEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMetricAndWeight() {
        return this.metricAndWeightEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMetricAndWeight_Metric() {
        return (EReference) this.metricAndWeightEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMetricAndWeight_Weight() {
        return (EReference) this.metricAndWeightEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetricDSLFactory getMetricDSLFactory() {
        return (MetricDSLFactory) this.getEFactoryInstance();
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
        this.metricModelEClass = this.createEClass(METRIC_MODEL);
        this.createEAttribute(this.metricModelEClass, METRIC_MODEL__IMPORT_URI);
        this.createEReference(this.metricModelEClass, METRIC_MODEL__METRICS);

        this.metricEClass = this.createEClass(METRIC);
        this.createEAttribute(this.metricEClass, METRIC__NAME);

        this.externalMetricEClass = this.createEClass(EXTERNAL_METRIC);

        this.internalMetricEClass = this.createEClass(INTERNAL_METRIC);
        this.createEAttribute(this.internalMetricEClass, INTERNAL_METRIC__SHORT_NAME);
        this.createEAttribute(this.internalMetricEClass, INTERNAL_METRIC__DESCRIPTION);
        this.createEReference(this.internalMetricEClass, INTERNAL_METRIC__PARAMETER);
        this.createEReference(this.internalMetricEClass, INTERNAL_METRIC__DEFINITION);

        this.numberEClass = this.createEClass(NUMBER);
        this.createEAttribute(this.numberEClass, NUMBER__NAME);

        this.parameterEClass = this.createEClass(PARAMETER);
        this.createEAttribute(this.parameterEClass, PARAMETER__SHORTNAME);
        this.createEAttribute(this.parameterEClass, PARAMETER__DESCRIPTION);
        this.createEAttribute(this.parameterEClass, PARAMETER__DEFAULT_VALUE);

        this.constantEClass = this.createEClass(CONSTANT);
        this.createEAttribute(this.constantEClass, CONSTANT__VALUE);

        this.metricDefinitionEClass = this.createEClass(METRIC_DEFINITION);

        this.weightedMetricEClass = this.createEClass(WEIGHTED_METRIC);
        this.createEReference(this.weightedMetricEClass, WEIGHTED_METRIC__WEIGHTS);

        this.stepwiseMetricEClass = this.createEClass(STEPWISE_METRIC);
        this.createEReference(this.stepwiseMetricEClass, STEPWISE_METRIC__INNER_METRIC);
        this.createEReference(this.stepwiseMetricEClass, STEPWISE_METRIC__STEPS);

        this.ratioMetricEClass = this.createEClass(RATIO_METRIC);
        this.createEReference(this.ratioMetricEClass, RATIO_METRIC__NOMINATOR_METRIC);
        this.createEReference(this.ratioMetricEClass, RATIO_METRIC__DENOMINATOR_METRIC);

        this.boundAndWeightEClass = this.createEClass(BOUND_AND_WEIGHT);
        this.createEReference(this.boundAndWeightEClass, BOUND_AND_WEIGHT__UPPER_BOUND);
        this.createEReference(this.boundAndWeightEClass, BOUND_AND_WEIGHT__WEIGHT);

        this.metricAndWeightEClass = this.createEClass(METRIC_AND_WEIGHT);
        this.createEReference(this.metricAndWeightEClass, METRIC_AND_WEIGHT__METRIC);
        this.createEReference(this.metricAndWeightEClass, METRIC_AND_WEIGHT__WEIGHT);
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        this.externalMetricEClass.getESuperTypes().add(this.getMetric());
        this.internalMetricEClass.getESuperTypes().add(this.getMetric());
        this.parameterEClass.getESuperTypes().add(this.getNumber());
        this.constantEClass.getESuperTypes().add(this.getNumber());
        this.weightedMetricEClass.getESuperTypes().add(this.getMetricDefinition());
        this.stepwiseMetricEClass.getESuperTypes().add(this.getMetricDefinition());
        this.ratioMetricEClass.getESuperTypes().add(this.getMetricDefinition());

        // Initialize classes and features; add operations and parameters
        this.initEClass(this.metricModelEClass, MetricModel.class, "MetricModel", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getMetricModel_ImportURI(), this.ecorePackage.getEString(), "importURI", null, 0, -1,
                MetricModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getMetricModel_Metrics(), this.getMetric(), null, "metrics", null, 0, -1,
                MetricModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.metricEClass, Metric.class, "Metric", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getMetric_Name(), this.ecorePackage.getEString(), "name", null, 0, 1, Metric.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.externalMetricEClass, ExternalMetric.class, "ExternalMetric", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        this.initEClass(this.internalMetricEClass, InternalMetric.class, "InternalMetric", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getInternalMetric_ShortName(), this.ecorePackage.getEString(), "shortName", null, 0, 1,
                InternalMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getInternalMetric_Description(), this.ecorePackage.getEString(), "description", null,
                0, 1, InternalMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getInternalMetric_Parameter(), this.getNumber(), null, "parameter", null, 0, -1,
                InternalMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getInternalMetric_Definition(), this.getMetricDefinition(), null, "definition", null,
                0, 1, InternalMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.numberEClass, org.somox.metrics.dsl.metricDSL.Number.class, "Number", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getNumber_Name(), this.ecorePackage.getEString(), "name", null, 0, 1,
                org.somox.metrics.dsl.metricDSL.Number.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getParameter_Shortname(), this.ecorePackage.getEString(), "shortname", null, 0, 1,
                Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getParameter_Description(), this.ecorePackage.getEString(), "description", null, 0, 1,
                Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getParameter_DefaultValue(), this.ecorePackage.getEDouble(), "defaultValue", null, 0,
                1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.constantEClass, Constant.class, "Constant", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getConstant_Value(), this.ecorePackage.getEDouble(), "value", null, 0, 1,
                Constant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.metricDefinitionEClass, MetricDefinition.class, "MetricDefinition", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        this.initEClass(this.weightedMetricEClass, WeightedMetric.class, "WeightedMetric", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getWeightedMetric_Weights(), this.getMetricAndWeight(), null, "weights", null, 0, -1,
                WeightedMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.stepwiseMetricEClass, StepwiseMetric.class, "StepwiseMetric", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getStepwiseMetric_InnerMetric(), this.getMetric(), null, "innerMetric", null, 0, 1,
                StepwiseMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getStepwiseMetric_Steps(), this.getBoundAndWeight(), null, "steps", null, 0, -1,
                StepwiseMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.ratioMetricEClass, RatioMetric.class, "RatioMetric", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getRatioMetric_NominatorMetric(), this.getMetric(), null, "nominatorMetric", null, 0,
                1, RatioMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getRatioMetric_DenominatorMetric(), this.getMetric(), null, "denominatorMetric", null,
                0, 1, RatioMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.boundAndWeightEClass, BoundAndWeight.class, "BoundAndWeight", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getBoundAndWeight_UpperBound(), this.getNumber(), null, "upperBound", null, 0, 1,
                BoundAndWeight.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getBoundAndWeight_Weight(), this.getNumber(), null, "weight", null, 0, 1,
                BoundAndWeight.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.metricAndWeightEClass, MetricAndWeight.class, "MetricAndWeight", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getMetricAndWeight_Metric(), this.getMetric(), null, "metric", null, 0, 1,
                MetricAndWeight.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getMetricAndWeight_Weight(), this.getNumber(), null, "weight", null, 0, 1,
                MetricAndWeight.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        this.createResource(eNS_URI);
    }

} // MetricDSLPackageImpl
