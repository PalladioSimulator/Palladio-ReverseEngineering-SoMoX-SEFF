/**
 */
package metricvalues;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta
 * objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see metricvalues.MetricvaluesFactory
 * @model kind="package"
 * @generated
 */
public interface MetricvaluesPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "metricvalues";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://metricvalues/1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "metricvalues";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    MetricvaluesPackage eINSTANCE = metricvalues.impl.MetricvaluesPackageImpl.init();

    /**
     * The meta object id for the '{@link metricvalues.impl.ComponentImpl <em>Component</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see metricvalues.impl.ComponentImpl
     * @see metricvalues.impl.MetricvaluesPackageImpl#getComponent()
     * @generated
     */
    int COMPONENT = 0;

    /**
     * The feature id for the '<em><b>Sub Components</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT__SUB_COMPONENTS = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT__NAME = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT__ID = 2;

    /**
     * The feature id for the '<em><b>Classes</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT__CLASSES = 3;

    /**
     * The number of structural features of the '<em>Component</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>Component</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link metricvalues.impl.ComponentCandidateImpl
     * <em>Component Candidate</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see metricvalues.impl.ComponentCandidateImpl
     * @see metricvalues.impl.MetricvaluesPackageImpl#getComponentCandidate()
     * @generated
     */
    int COMPONENT_CANDIDATE = 1;

    /**
     * The feature id for the '<em><b>Metric Values</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT_CANDIDATE__METRIC_VALUES = 0;

    /**
     * The feature id for the '<em><b>First Component</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT_CANDIDATE__FIRST_COMPONENT = 1;

    /**
     * The feature id for the '<em><b>Second Component</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT_CANDIDATE__SECOND_COMPONENT = 2;

    /**
     * The number of structural features of the '<em>Component Candidate</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT_CANDIDATE_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Component Candidate</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT_CANDIDATE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link metricvalues.impl.IterationImpl <em>Iteration</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see metricvalues.impl.IterationImpl
     * @see metricvalues.impl.MetricvaluesPackageImpl#getIteration()
     * @generated
     */
    int ITERATION = 2;

    /**
     * The feature id for the '<em><b>Component Candidates</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ITERATION__COMPONENT_CANDIDATES = 0;

    /**
     * The feature id for the '<em><b>Components</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ITERATION__COMPONENTS = 1;

    /**
     * The feature id for the '<em><b>Number</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ITERATION__NUMBER = 2;

    /**
     * The feature id for the '<em><b>Cur Comp Threshold</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ITERATION__CUR_COMP_THRESHOLD = 3;

    /**
     * The feature id for the '<em><b>Cur Merge Threshold</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ITERATION__CUR_MERGE_THRESHOLD = 4;

    /**
     * The feature id for the '<em><b>Is Merge Iteration</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ITERATION__IS_MERGE_ITERATION = 5;

    /**
     * The number of structural features of the '<em>Iteration</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ITERATION_FEATURE_COUNT = 6;

    /**
     * The number of operations of the '<em>Iteration</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ITERATION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link metricvalues.impl.MetricValueImpl <em>Metric Value</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see metricvalues.impl.MetricValueImpl
     * @see metricvalues.impl.MetricvaluesPackageImpl#getMetricValue()
     * @generated
     */
    int METRIC_VALUE = 3;

    /**
     * The feature id for the '<em><b>Metric ID</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUE__METRIC_ID = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUE__VALUE = 1;

    /**
     * The number of structural features of the '<em>Metric Value</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Metric Value</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link metricvalues.impl.MetricValuesModelImpl
     * <em>Metric Values Model</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see metricvalues.impl.MetricValuesModelImpl
     * @see metricvalues.impl.MetricvaluesPackageImpl#getMetricValuesModel()
     * @generated
     */
    int METRIC_VALUES_MODEL = 4;

    /**
     * The feature id for the '<em><b>Iterations</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__ITERATIONS = 0;

    /**
     * The feature id for the '<em><b>Min Comp Threshold</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD = 1;

    /**
     * The feature id for the '<em><b>Max Merge Threshold</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD = 2;

    /**
     * The feature id for the '<em><b>Weight Low Coupling</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING = 3;

    /**
     * The feature id for the '<em><b>Weight High Coupling</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING = 4;

    /**
     * The feature id for the '<em><b>Weight Low Name Resemblance</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE = 5;

    /**
     * The feature id for the '<em><b>Weight Mid Name Resemblance</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE = 6;

    /**
     * The feature id for the '<em><b>Weight High Name Resemblance</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE = 7;

    /**
     * The feature id for the '<em><b>Weight Interface Violation Relevant</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT = 8;

    /**
     * The feature id for the '<em><b>Weight Interface Violation Irrelevant</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT = 9;

    /**
     * The feature id for the '<em><b>Weight High SLAQ</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ = 10;

    /**
     * The feature id for the '<em><b>Weight Low SLAQ</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ = 11;

    /**
     * The feature id for the '<em><b>Weight Package Mapping</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING = 12;

    /**
     * The feature id for the '<em><b>Weight Directory Mapping</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING = 13;

    /**
     * The feature id for the '<em><b>Weight DMS</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_DMS = 14;

    /**
     * The feature id for the '<em><b>Weight Highest Name Resemblance</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE = 15;

    /**
     * The feature id for the '<em><b>Wildcard Key</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__WILDCARD_KEY = 16;

    /**
     * The feature id for the '<em><b>Additional Wildcards</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS = 17;

    /**
     * The feature id for the '<em><b>Min Merge Threshold</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD = 18;

    /**
     * The feature id for the '<em><b>Max Compose Threshold</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD = 19;

    /**
     * The feature id for the '<em><b>Compose Threshold Decrement</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT = 20;

    /**
     * The feature id for the '<em><b>Merge Threshold Decrement</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT = 21;

    /**
     * The feature id for the '<em><b>Excluded Prefixes For Name Resemblance</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE = 22;

    /**
     * The feature id for the '<em><b>Excluded Suffixes For Name Resemblance</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE = 23;

    /**
     * The number of structural features of the '<em>Metric Values Model</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL_FEATURE_COUNT = 24;

    /**
     * The number of operations of the '<em>Metric Values Model</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int METRIC_VALUES_MODEL_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link metricvalues.Component <em>Component</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Component</em>'.
     * @see metricvalues.Component
     * @generated
     */
    EClass getComponent();

    /**
     * Returns the meta object for the containment reference list '
     * {@link metricvalues.Component#getSubComponents <em>Sub Components</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Sub Components</em>'.
     * @see metricvalues.Component#getSubComponents()
     * @see #getComponent()
     * @generated
     */
    EReference getComponent_SubComponents();

    /**
     * Returns the meta object for the attribute '{@link metricvalues.Component#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see metricvalues.Component#getName()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_Name();

    /**
     * Returns the meta object for the attribute '{@link metricvalues.Component#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see metricvalues.Component#getId()
     * @see #getComponent()
     * @generated
     */
    EAttribute getComponent_Id();

    /**
     * Returns the meta object for the reference list '{@link metricvalues.Component#getClasses
     * <em>Classes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Classes</em>'.
     * @see metricvalues.Component#getClasses()
     * @see #getComponent()
     * @generated
     */
    EReference getComponent_Classes();

    /**
     * Returns the meta object for class '{@link metricvalues.ComponentCandidate
     * <em>Component Candidate</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Component Candidate</em>'.
     * @see metricvalues.ComponentCandidate
     * @generated
     */
    EClass getComponentCandidate();

    /**
     * Returns the meta object for the containment reference list '
     * {@link metricvalues.ComponentCandidate#getMetricValues <em>Metric Values</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Metric Values</em>'.
     * @see metricvalues.ComponentCandidate#getMetricValues()
     * @see #getComponentCandidate()
     * @generated
     */
    EReference getComponentCandidate_MetricValues();

    /**
     * Returns the meta object for the reference '
     * {@link metricvalues.ComponentCandidate#getFirstComponent <em>First Component</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>First Component</em>'.
     * @see metricvalues.ComponentCandidate#getFirstComponent()
     * @see #getComponentCandidate()
     * @generated
     */
    EReference getComponentCandidate_FirstComponent();

    /**
     * Returns the meta object for the reference '
     * {@link metricvalues.ComponentCandidate#getSecondComponent <em>Second Component</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Second Component</em>'.
     * @see metricvalues.ComponentCandidate#getSecondComponent()
     * @see #getComponentCandidate()
     * @generated
     */
    EReference getComponentCandidate_SecondComponent();

    /**
     * Returns the meta object for class '{@link metricvalues.Iteration <em>Iteration</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Iteration</em>'.
     * @see metricvalues.Iteration
     * @generated
     */
    EClass getIteration();

    /**
     * Returns the meta object for the containment reference list '
     * {@link metricvalues.Iteration#getComponentCandidates <em>Component Candidates</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Component Candidates</em>'.
     * @see metricvalues.Iteration#getComponentCandidates()
     * @see #getIteration()
     * @generated
     */
    EReference getIteration_ComponentCandidates();

    /**
     * Returns the meta object for the containment reference list '
     * {@link metricvalues.Iteration#getComponents <em>Components</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Components</em>'.
     * @see metricvalues.Iteration#getComponents()
     * @see #getIteration()
     * @generated
     */
    EReference getIteration_Components();

    /**
     * Returns the meta object for the attribute '{@link metricvalues.Iteration#getNumber
     * <em>Number</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Number</em>'.
     * @see metricvalues.Iteration#getNumber()
     * @see #getIteration()
     * @generated
     */
    EAttribute getIteration_Number();

    /**
     * Returns the meta object for the attribute '{@link metricvalues.Iteration#getCurCompThreshold
     * <em>Cur Comp Threshold</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Cur Comp Threshold</em>'.
     * @see metricvalues.Iteration#getCurCompThreshold()
     * @see #getIteration()
     * @generated
     */
    EAttribute getIteration_CurCompThreshold();

    /**
     * Returns the meta object for the attribute '{@link metricvalues.Iteration#getCurMergeThreshold
     * <em>Cur Merge Threshold</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Cur Merge Threshold</em>'.
     * @see metricvalues.Iteration#getCurMergeThreshold()
     * @see #getIteration()
     * @generated
     */
    EAttribute getIteration_CurMergeThreshold();

    /**
     * Returns the meta object for the attribute '{@link metricvalues.Iteration#isIsMergeIteration
     * <em>Is Merge Iteration</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Is Merge Iteration</em>'.
     * @see metricvalues.Iteration#isIsMergeIteration()
     * @see #getIteration()
     * @generated
     */
    EAttribute getIteration_IsMergeIteration();

    /**
     * Returns the meta object for class '{@link metricvalues.MetricValue <em>Metric Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Metric Value</em>'.
     * @see metricvalues.MetricValue
     * @generated
     */
    EClass getMetricValue();

    /**
     * Returns the meta object for the attribute '{@link metricvalues.MetricValue#getMetricID
     * <em>Metric ID</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Metric ID</em>'.
     * @see metricvalues.MetricValue#getMetricID()
     * @see #getMetricValue()
     * @generated
     */
    EAttribute getMetricValue_MetricID();

    /**
     * Returns the meta object for the attribute '{@link metricvalues.MetricValue#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see metricvalues.MetricValue#getValue()
     * @see #getMetricValue()
     * @generated
     */
    EAttribute getMetricValue_Value();

    /**
     * Returns the meta object for class '{@link metricvalues.MetricValuesModel
     * <em>Metric Values Model</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Metric Values Model</em>'.
     * @see metricvalues.MetricValuesModel
     * @generated
     */
    EClass getMetricValuesModel();

    /**
     * Returns the meta object for the containment reference list '
     * {@link metricvalues.MetricValuesModel#getIterations <em>Iterations</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Iterations</em>'.
     * @see metricvalues.MetricValuesModel#getIterations()
     * @see #getMetricValuesModel()
     * @generated
     */
    EReference getMetricValuesModel_Iterations();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getMinCompThreshold <em>Min Comp Threshold</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Min Comp Threshold</em>'.
     * @see metricvalues.MetricValuesModel#getMinCompThreshold()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_MinCompThreshold();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getMaxMergeThreshold <em>Max Merge Threshold</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Max Merge Threshold</em>'.
     * @see metricvalues.MetricValuesModel#getMaxMergeThreshold()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_MaxMergeThreshold();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightLowCoupling <em>Weight Low Coupling</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight Low Coupling</em>'.
     * @see metricvalues.MetricValuesModel#getWeightLowCoupling()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightLowCoupling();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightHighCoupling <em>Weight High Coupling</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight High Coupling</em>'.
     * @see metricvalues.MetricValuesModel#getWeightHighCoupling()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightHighCoupling();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightLowNameResemblance
     * <em>Weight Low Name Resemblance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight Low Name Resemblance</em>'.
     * @see metricvalues.MetricValuesModel#getWeightLowNameResemblance()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightLowNameResemblance();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightMidNameResemblance
     * <em>Weight Mid Name Resemblance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight Mid Name Resemblance</em>'.
     * @see metricvalues.MetricValuesModel#getWeightMidNameResemblance()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightMidNameResemblance();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightHighNameResemblance
     * <em>Weight High Name Resemblance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight High Name Resemblance</em>'.
     * @see metricvalues.MetricValuesModel#getWeightHighNameResemblance()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightHighNameResemblance();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightInterfaceViolationRelevant
     * <em>Weight Interface Violation Relevant</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight Interface Violation Relevant</em>'.
     * @see metricvalues.MetricValuesModel#getWeightInterfaceViolationRelevant()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightInterfaceViolationRelevant();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightInterfaceViolationIrrelevant
     * <em>Weight Interface Violation Irrelevant</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Weight Interface Violation Irrelevant</em>'.
     * @see metricvalues.MetricValuesModel#getWeightInterfaceViolationIrrelevant()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightInterfaceViolationIrrelevant();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightHighSLAQ <em>Weight High SLAQ</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight High SLAQ</em>'.
     * @see metricvalues.MetricValuesModel#getWeightHighSLAQ()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightHighSLAQ();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightLowSLAQ <em>Weight Low SLAQ</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight Low SLAQ</em>'.
     * @see metricvalues.MetricValuesModel#getWeightLowSLAQ()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightLowSLAQ();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightPackageMapping <em>Weight Package Mapping</em>
     * }'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight Package Mapping</em>'.
     * @see metricvalues.MetricValuesModel#getWeightPackageMapping()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightPackageMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightDirectoryMapping
     * <em>Weight Directory Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight Directory Mapping</em>'.
     * @see metricvalues.MetricValuesModel#getWeightDirectoryMapping()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightDirectoryMapping();

    /**
     * Returns the meta object for the attribute '{@link metricvalues.MetricValuesModel#getWeightDMS
     * <em>Weight DMS</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight DMS</em>'.
     * @see metricvalues.MetricValuesModel#getWeightDMS()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightDMS();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWeightHighestNameResemblance
     * <em>Weight Highest Name Resemblance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Weight Highest Name Resemblance</em>'.
     * @see metricvalues.MetricValuesModel#getWeightHighestNameResemblance()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WeightHighestNameResemblance();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getWildcardKey <em>Wildcard Key</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Wildcard Key</em>'.
     * @see metricvalues.MetricValuesModel#getWildcardKey()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_WildcardKey();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getAdditionalWildcards <em>Additional Wildcards</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Additional Wildcards</em>'.
     * @see metricvalues.MetricValuesModel#getAdditionalWildcards()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_AdditionalWildcards();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getMinMergeThreshold <em>Min Merge Threshold</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Min Merge Threshold</em>'.
     * @see metricvalues.MetricValuesModel#getMinMergeThreshold()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_MinMergeThreshold();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getMaxComposeThreshold <em>Max Compose Threshold</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Max Compose Threshold</em>'.
     * @see metricvalues.MetricValuesModel#getMaxComposeThreshold()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_MaxComposeThreshold();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getComposeThresholdDecrement
     * <em>Compose Threshold Decrement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Compose Threshold Decrement</em>'.
     * @see metricvalues.MetricValuesModel#getComposeThresholdDecrement()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_ComposeThresholdDecrement();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getMergeThresholdDecrement
     * <em>Merge Threshold Decrement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Merge Threshold Decrement</em>'.
     * @see metricvalues.MetricValuesModel#getMergeThresholdDecrement()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_MergeThresholdDecrement();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getExcludedPrefixesForNameResemblance
     * <em>Excluded Prefixes For Name Resemblance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Excluded Prefixes For Name Resemblance</em>'.
     * @see metricvalues.MetricValuesModel#getExcludedPrefixesForNameResemblance()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_ExcludedPrefixesForNameResemblance();

    /**
     * Returns the meta object for the attribute '
     * {@link metricvalues.MetricValuesModel#getExcludedSuffixesForNameResemblance
     * <em>Excluded Suffixes For Name Resemblance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Excluded Suffixes For Name Resemblance</em>'.
     * @see metricvalues.MetricValuesModel#getExcludedSuffixesForNameResemblance()
     * @see #getMetricValuesModel()
     * @generated
     */
    EAttribute getMetricValuesModel_ExcludedSuffixesForNameResemblance();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    MetricvaluesFactory getMetricvaluesFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link metricvalues.impl.ComponentImpl
         * <em>Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see metricvalues.impl.ComponentImpl
         * @see metricvalues.impl.MetricvaluesPackageImpl#getComponent()
         * @generated
         */
        EClass COMPONENT = eINSTANCE.getComponent();

        /**
         * The meta object literal for the '<em><b>Sub Components</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMPONENT__SUB_COMPONENTS = eINSTANCE.getComponent_SubComponents();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute COMPONENT__NAME = eINSTANCE.getComponent_Name();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute COMPONENT__ID = eINSTANCE.getComponent_Id();

        /**
         * The meta object literal for the '<em><b>Classes</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMPONENT__CLASSES = eINSTANCE.getComponent_Classes();

        /**
         * The meta object literal for the '{@link metricvalues.impl.ComponentCandidateImpl
         * <em>Component Candidate</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see metricvalues.impl.ComponentCandidateImpl
         * @see metricvalues.impl.MetricvaluesPackageImpl#getComponentCandidate()
         * @generated
         */
        EClass COMPONENT_CANDIDATE = eINSTANCE.getComponentCandidate();

        /**
         * The meta object literal for the '<em><b>Metric Values</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMPONENT_CANDIDATE__METRIC_VALUES = eINSTANCE.getComponentCandidate_MetricValues();

        /**
         * The meta object literal for the '<em><b>First Component</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMPONENT_CANDIDATE__FIRST_COMPONENT = eINSTANCE.getComponentCandidate_FirstComponent();

        /**
         * The meta object literal for the '<em><b>Second Component</b></em>' reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMPONENT_CANDIDATE__SECOND_COMPONENT = eINSTANCE.getComponentCandidate_SecondComponent();

        /**
         * The meta object literal for the '{@link metricvalues.impl.IterationImpl
         * <em>Iteration</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see metricvalues.impl.IterationImpl
         * @see metricvalues.impl.MetricvaluesPackageImpl#getIteration()
         * @generated
         */
        EClass ITERATION = eINSTANCE.getIteration();

        /**
         * The meta object literal for the '<em><b>Component Candidates</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ITERATION__COMPONENT_CANDIDATES = eINSTANCE.getIteration_ComponentCandidates();

        /**
         * The meta object literal for the '<em><b>Components</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ITERATION__COMPONENTS = eINSTANCE.getIteration_Components();

        /**
         * The meta object literal for the '<em><b>Number</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ITERATION__NUMBER = eINSTANCE.getIteration_Number();

        /**
         * The meta object literal for the '<em><b>Cur Comp Threshold</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ITERATION__CUR_COMP_THRESHOLD = eINSTANCE.getIteration_CurCompThreshold();

        /**
         * The meta object literal for the '<em><b>Cur Merge Threshold</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ITERATION__CUR_MERGE_THRESHOLD = eINSTANCE.getIteration_CurMergeThreshold();

        /**
         * The meta object literal for the '<em><b>Is Merge Iteration</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ITERATION__IS_MERGE_ITERATION = eINSTANCE.getIteration_IsMergeIteration();

        /**
         * The meta object literal for the '{@link metricvalues.impl.MetricValueImpl
         * <em>Metric Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see metricvalues.impl.MetricValueImpl
         * @see metricvalues.impl.MetricvaluesPackageImpl#getMetricValue()
         * @generated
         */
        EClass METRIC_VALUE = eINSTANCE.getMetricValue();

        /**
         * The meta object literal for the '<em><b>Metric ID</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUE__METRIC_ID = eINSTANCE.getMetricValue_MetricID();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUE__VALUE = eINSTANCE.getMetricValue_Value();

        /**
         * The meta object literal for the '{@link metricvalues.impl.MetricValuesModelImpl
         * <em>Metric Values Model</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see metricvalues.impl.MetricValuesModelImpl
         * @see metricvalues.impl.MetricvaluesPackageImpl#getMetricValuesModel()
         * @generated
         */
        EClass METRIC_VALUES_MODEL = eINSTANCE.getMetricValuesModel();

        /**
         * The meta object literal for the '<em><b>Iterations</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference METRIC_VALUES_MODEL__ITERATIONS = eINSTANCE.getMetricValuesModel_Iterations();

        /**
         * The meta object literal for the '<em><b>Min Comp Threshold</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD = eINSTANCE.getMetricValuesModel_MinCompThreshold();

        /**
         * The meta object literal for the '<em><b>Max Merge Threshold</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD = eINSTANCE.getMetricValuesModel_MaxMergeThreshold();

        /**
         * The meta object literal for the '<em><b>Weight Low Coupling</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING = eINSTANCE.getMetricValuesModel_WeightLowCoupling();

        /**
         * The meta object literal for the '<em><b>Weight High Coupling</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING = eINSTANCE.getMetricValuesModel_WeightHighCoupling();

        /**
         * The meta object literal for the '<em><b>Weight Low Name Resemblance</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE = eINSTANCE
                .getMetricValuesModel_WeightLowNameResemblance();

        /**
         * The meta object literal for the '<em><b>Weight Mid Name Resemblance</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE = eINSTANCE
                .getMetricValuesModel_WeightMidNameResemblance();

        /**
         * The meta object literal for the '<em><b>Weight High Name Resemblance</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE = eINSTANCE
                .getMetricValuesModel_WeightHighNameResemblance();

        /**
         * The meta object literal for the '<em><b>Weight Interface Violation Relevant</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT = eINSTANCE
                .getMetricValuesModel_WeightInterfaceViolationRelevant();

        /**
         * The meta object literal for the '<em><b>Weight Interface Violation Irrelevant</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT = eINSTANCE
                .getMetricValuesModel_WeightInterfaceViolationIrrelevant();

        /**
         * The meta object literal for the '<em><b>Weight High SLAQ</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ = eINSTANCE.getMetricValuesModel_WeightHighSLAQ();

        /**
         * The meta object literal for the '<em><b>Weight Low SLAQ</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ = eINSTANCE.getMetricValuesModel_WeightLowSLAQ();

        /**
         * The meta object literal for the '<em><b>Weight Package Mapping</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING = eINSTANCE.getMetricValuesModel_WeightPackageMapping();

        /**
         * The meta object literal for the '<em><b>Weight Directory Mapping</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING = eINSTANCE
                .getMetricValuesModel_WeightDirectoryMapping();

        /**
         * The meta object literal for the '<em><b>Weight DMS</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_DMS = eINSTANCE.getMetricValuesModel_WeightDMS();

        /**
         * The meta object literal for the '<em><b>Weight Highest Name Resemblance</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE = eINSTANCE
                .getMetricValuesModel_WeightHighestNameResemblance();

        /**
         * The meta object literal for the '<em><b>Wildcard Key</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__WILDCARD_KEY = eINSTANCE.getMetricValuesModel_WildcardKey();

        /**
         * The meta object literal for the '<em><b>Additional Wildcards</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS = eINSTANCE.getMetricValuesModel_AdditionalWildcards();

        /**
         * The meta object literal for the '<em><b>Min Merge Threshold</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD = eINSTANCE.getMetricValuesModel_MinMergeThreshold();

        /**
         * The meta object literal for the '<em><b>Max Compose Threshold</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD = eINSTANCE.getMetricValuesModel_MaxComposeThreshold();

        /**
         * The meta object literal for the '<em><b>Compose Threshold Decrement</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT = eINSTANCE
                .getMetricValuesModel_ComposeThresholdDecrement();

        /**
         * The meta object literal for the '<em><b>Merge Threshold Decrement</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT = eINSTANCE
                .getMetricValuesModel_MergeThresholdDecrement();

        /**
         * The meta object literal for the '<em><b>Excluded Prefixes For Name Resemblance</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE = eINSTANCE
                .getMetricValuesModel_ExcludedPrefixesForNameResemblance();

        /**
         * The meta object literal for the '<em><b>Excluded Suffixes For Name Resemblance</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE = eINSTANCE
                .getMetricValuesModel_ExcludedSuffixesForNameResemblance();

    }

} // MetricvaluesPackage
