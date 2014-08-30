/**
 */
package org.somox.metrics.dsl.metricDSL;

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
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLFactory
 * @model kind="package"
 * @generated
 */
public interface MetricDSLPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "metricDSL";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.somox.org/metricDSL/1.0";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "metricDSL";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MetricDSLPackage eINSTANCE = org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl.init();

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.MetricModelImpl <em>Metric Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricModelImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getMetricModel()
   * @generated
   */
  int METRIC_MODEL = 0;

  /**
   * The feature id for the '<em><b>Import URI</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METRIC_MODEL__IMPORT_URI = 0;

  /**
   * The feature id for the '<em><b>Metrics</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METRIC_MODEL__METRICS = 1;

  /**
   * The number of structural features of the '<em>Metric Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METRIC_MODEL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.MetricImpl <em>Metric</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getMetric()
   * @generated
   */
  int METRIC = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METRIC__NAME = 0;

  /**
   * The number of structural features of the '<em>Metric</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METRIC_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.ExternalMetricImpl <em>External Metric</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.ExternalMetricImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getExternalMetric()
   * @generated
   */
  int EXTERNAL_METRIC = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_METRIC__NAME = METRIC__NAME;

  /**
   * The number of structural features of the '<em>External Metric</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_METRIC_FEATURE_COUNT = METRIC_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.InternalMetricImpl <em>Internal Metric</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.InternalMetricImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getInternalMetric()
   * @generated
   */
  int INTERNAL_METRIC = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERNAL_METRIC__NAME = METRIC__NAME;

  /**
   * The feature id for the '<em><b>Short Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERNAL_METRIC__SHORT_NAME = METRIC_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERNAL_METRIC__DESCRIPTION = METRIC_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERNAL_METRIC__PARAMETER = METRIC_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Definition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERNAL_METRIC__DEFINITION = METRIC_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Internal Metric</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERNAL_METRIC_FEATURE_COUNT = METRIC_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.NumberImpl <em>Number</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.NumberImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getNumber()
   * @generated
   */
  int NUMBER = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NUMBER__NAME = 0;

  /**
   * The number of structural features of the '<em>Number</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NUMBER_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.ParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.ParameterImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getParameter()
   * @generated
   */
  int PARAMETER = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__NAME = NUMBER__NAME;

  /**
   * The feature id for the '<em><b>Shortname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__SHORTNAME = NUMBER_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__DESCRIPTION = NUMBER_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Default Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__DEFAULT_VALUE = NUMBER_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_FEATURE_COUNT = NUMBER_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.ConstantImpl <em>Constant</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.ConstantImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getConstant()
   * @generated
   */
  int CONSTANT = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTANT__NAME = NUMBER__NAME;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTANT__VALUE = NUMBER_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Constant</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTANT_FEATURE_COUNT = NUMBER_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.MetricDefinitionImpl <em>Metric Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDefinitionImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getMetricDefinition()
   * @generated
   */
  int METRIC_DEFINITION = 7;

  /**
   * The number of structural features of the '<em>Metric Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METRIC_DEFINITION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.WeightedMetricImpl <em>Weighted Metric</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.WeightedMetricImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getWeightedMetric()
   * @generated
   */
  int WEIGHTED_METRIC = 8;

  /**
   * The feature id for the '<em><b>Weights</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEIGHTED_METRIC__WEIGHTS = METRIC_DEFINITION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Weighted Metric</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEIGHTED_METRIC_FEATURE_COUNT = METRIC_DEFINITION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.StepwiseMetricImpl <em>Stepwise Metric</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.StepwiseMetricImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getStepwiseMetric()
   * @generated
   */
  int STEPWISE_METRIC = 9;

  /**
   * The feature id for the '<em><b>Inner Metric</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STEPWISE_METRIC__INNER_METRIC = METRIC_DEFINITION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Steps</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STEPWISE_METRIC__STEPS = METRIC_DEFINITION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Stepwise Metric</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STEPWISE_METRIC_FEATURE_COUNT = METRIC_DEFINITION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.RatioMetricImpl <em>Ratio Metric</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.RatioMetricImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getRatioMetric()
   * @generated
   */
  int RATIO_METRIC = 10;

  /**
   * The feature id for the '<em><b>Nominator Metric</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RATIO_METRIC__NOMINATOR_METRIC = METRIC_DEFINITION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Denominator Metric</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RATIO_METRIC__DENOMINATOR_METRIC = METRIC_DEFINITION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Ratio Metric</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RATIO_METRIC_FEATURE_COUNT = METRIC_DEFINITION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.BoundAndWeightImpl <em>Bound And Weight</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.BoundAndWeightImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getBoundAndWeight()
   * @generated
   */
  int BOUND_AND_WEIGHT = 11;

  /**
   * The feature id for the '<em><b>Upper Bound</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOUND_AND_WEIGHT__UPPER_BOUND = 0;

  /**
   * The feature id for the '<em><b>Weight</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOUND_AND_WEIGHT__WEIGHT = 1;

  /**
   * The number of structural features of the '<em>Bound And Weight</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOUND_AND_WEIGHT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.somox.metrics.dsl.metricDSL.impl.MetricAndWeightImpl <em>Metric And Weight</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricAndWeightImpl
   * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getMetricAndWeight()
   * @generated
   */
  int METRIC_AND_WEIGHT = 12;

  /**
   * The feature id for the '<em><b>Metric</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METRIC_AND_WEIGHT__METRIC = 0;

  /**
   * The feature id for the '<em><b>Weight</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METRIC_AND_WEIGHT__WEIGHT = 1;

  /**
   * The number of structural features of the '<em>Metric And Weight</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METRIC_AND_WEIGHT_FEATURE_COUNT = 2;


  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.MetricModel <em>Metric Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Metric Model</em>'.
   * @see org.somox.metrics.dsl.metricDSL.MetricModel
   * @generated
   */
  EClass getMetricModel();

  /**
   * Returns the meta object for the attribute list '{@link org.somox.metrics.dsl.metricDSL.MetricModel#getImportURI <em>Import URI</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Import URI</em>'.
   * @see org.somox.metrics.dsl.metricDSL.MetricModel#getImportURI()
   * @see #getMetricModel()
   * @generated
   */
  EAttribute getMetricModel_ImportURI();

  /**
   * Returns the meta object for the containment reference list '{@link org.somox.metrics.dsl.metricDSL.MetricModel#getMetrics <em>Metrics</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Metrics</em>'.
   * @see org.somox.metrics.dsl.metricDSL.MetricModel#getMetrics()
   * @see #getMetricModel()
   * @generated
   */
  EReference getMetricModel_Metrics();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.Metric <em>Metric</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Metric</em>'.
   * @see org.somox.metrics.dsl.metricDSL.Metric
   * @generated
   */
  EClass getMetric();

  /**
   * Returns the meta object for the attribute '{@link org.somox.metrics.dsl.metricDSL.Metric#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.somox.metrics.dsl.metricDSL.Metric#getName()
   * @see #getMetric()
   * @generated
   */
  EAttribute getMetric_Name();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.ExternalMetric <em>External Metric</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>External Metric</em>'.
   * @see org.somox.metrics.dsl.metricDSL.ExternalMetric
   * @generated
   */
  EClass getExternalMetric();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.InternalMetric <em>Internal Metric</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Internal Metric</em>'.
   * @see org.somox.metrics.dsl.metricDSL.InternalMetric
   * @generated
   */
  EClass getInternalMetric();

  /**
   * Returns the meta object for the attribute '{@link org.somox.metrics.dsl.metricDSL.InternalMetric#getShortName <em>Short Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Short Name</em>'.
   * @see org.somox.metrics.dsl.metricDSL.InternalMetric#getShortName()
   * @see #getInternalMetric()
   * @generated
   */
  EAttribute getInternalMetric_ShortName();

  /**
   * Returns the meta object for the attribute '{@link org.somox.metrics.dsl.metricDSL.InternalMetric#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.somox.metrics.dsl.metricDSL.InternalMetric#getDescription()
   * @see #getInternalMetric()
   * @generated
   */
  EAttribute getInternalMetric_Description();

  /**
   * Returns the meta object for the containment reference list '{@link org.somox.metrics.dsl.metricDSL.InternalMetric#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter</em>'.
   * @see org.somox.metrics.dsl.metricDSL.InternalMetric#getParameter()
   * @see #getInternalMetric()
   * @generated
   */
  EReference getInternalMetric_Parameter();

  /**
   * Returns the meta object for the containment reference '{@link org.somox.metrics.dsl.metricDSL.InternalMetric#getDefinition <em>Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Definition</em>'.
   * @see org.somox.metrics.dsl.metricDSL.InternalMetric#getDefinition()
   * @see #getInternalMetric()
   * @generated
   */
  EReference getInternalMetric_Definition();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.Number <em>Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Number</em>'.
   * @see org.somox.metrics.dsl.metricDSL.Number
   * @generated
   */
  EClass getNumber();

  /**
   * Returns the meta object for the attribute '{@link org.somox.metrics.dsl.metricDSL.Number#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.somox.metrics.dsl.metricDSL.Number#getName()
   * @see #getNumber()
   * @generated
   */
  EAttribute getNumber_Name();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter</em>'.
   * @see org.somox.metrics.dsl.metricDSL.Parameter
   * @generated
   */
  EClass getParameter();

  /**
   * Returns the meta object for the attribute '{@link org.somox.metrics.dsl.metricDSL.Parameter#getShortname <em>Shortname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Shortname</em>'.
   * @see org.somox.metrics.dsl.metricDSL.Parameter#getShortname()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_Shortname();

  /**
   * Returns the meta object for the attribute '{@link org.somox.metrics.dsl.metricDSL.Parameter#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.somox.metrics.dsl.metricDSL.Parameter#getDescription()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_Description();

  /**
   * Returns the meta object for the attribute '{@link org.somox.metrics.dsl.metricDSL.Parameter#getDefaultValue <em>Default Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Default Value</em>'.
   * @see org.somox.metrics.dsl.metricDSL.Parameter#getDefaultValue()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_DefaultValue();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.Constant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constant</em>'.
   * @see org.somox.metrics.dsl.metricDSL.Constant
   * @generated
   */
  EClass getConstant();

  /**
   * Returns the meta object for the attribute '{@link org.somox.metrics.dsl.metricDSL.Constant#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.somox.metrics.dsl.metricDSL.Constant#getValue()
   * @see #getConstant()
   * @generated
   */
  EAttribute getConstant_Value();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.MetricDefinition <em>Metric Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Metric Definition</em>'.
   * @see org.somox.metrics.dsl.metricDSL.MetricDefinition
   * @generated
   */
  EClass getMetricDefinition();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.WeightedMetric <em>Weighted Metric</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Weighted Metric</em>'.
   * @see org.somox.metrics.dsl.metricDSL.WeightedMetric
   * @generated
   */
  EClass getWeightedMetric();

  /**
   * Returns the meta object for the containment reference list '{@link org.somox.metrics.dsl.metricDSL.WeightedMetric#getWeights <em>Weights</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Weights</em>'.
   * @see org.somox.metrics.dsl.metricDSL.WeightedMetric#getWeights()
   * @see #getWeightedMetric()
   * @generated
   */
  EReference getWeightedMetric_Weights();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.StepwiseMetric <em>Stepwise Metric</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Stepwise Metric</em>'.
   * @see org.somox.metrics.dsl.metricDSL.StepwiseMetric
   * @generated
   */
  EClass getStepwiseMetric();

  /**
   * Returns the meta object for the reference '{@link org.somox.metrics.dsl.metricDSL.StepwiseMetric#getInnerMetric <em>Inner Metric</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Inner Metric</em>'.
   * @see org.somox.metrics.dsl.metricDSL.StepwiseMetric#getInnerMetric()
   * @see #getStepwiseMetric()
   * @generated
   */
  EReference getStepwiseMetric_InnerMetric();

  /**
   * Returns the meta object for the containment reference list '{@link org.somox.metrics.dsl.metricDSL.StepwiseMetric#getSteps <em>Steps</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Steps</em>'.
   * @see org.somox.metrics.dsl.metricDSL.StepwiseMetric#getSteps()
   * @see #getStepwiseMetric()
   * @generated
   */
  EReference getStepwiseMetric_Steps();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.RatioMetric <em>Ratio Metric</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ratio Metric</em>'.
   * @see org.somox.metrics.dsl.metricDSL.RatioMetric
   * @generated
   */
  EClass getRatioMetric();

  /**
   * Returns the meta object for the reference '{@link org.somox.metrics.dsl.metricDSL.RatioMetric#getNominatorMetric <em>Nominator Metric</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Nominator Metric</em>'.
   * @see org.somox.metrics.dsl.metricDSL.RatioMetric#getNominatorMetric()
   * @see #getRatioMetric()
   * @generated
   */
  EReference getRatioMetric_NominatorMetric();

  /**
   * Returns the meta object for the reference '{@link org.somox.metrics.dsl.metricDSL.RatioMetric#getDenominatorMetric <em>Denominator Metric</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Denominator Metric</em>'.
   * @see org.somox.metrics.dsl.metricDSL.RatioMetric#getDenominatorMetric()
   * @see #getRatioMetric()
   * @generated
   */
  EReference getRatioMetric_DenominatorMetric();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.BoundAndWeight <em>Bound And Weight</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Bound And Weight</em>'.
   * @see org.somox.metrics.dsl.metricDSL.BoundAndWeight
   * @generated
   */
  EClass getBoundAndWeight();

  /**
   * Returns the meta object for the reference '{@link org.somox.metrics.dsl.metricDSL.BoundAndWeight#getUpperBound <em>Upper Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Upper Bound</em>'.
   * @see org.somox.metrics.dsl.metricDSL.BoundAndWeight#getUpperBound()
   * @see #getBoundAndWeight()
   * @generated
   */
  EReference getBoundAndWeight_UpperBound();

  /**
   * Returns the meta object for the reference '{@link org.somox.metrics.dsl.metricDSL.BoundAndWeight#getWeight <em>Weight</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Weight</em>'.
   * @see org.somox.metrics.dsl.metricDSL.BoundAndWeight#getWeight()
   * @see #getBoundAndWeight()
   * @generated
   */
  EReference getBoundAndWeight_Weight();

  /**
   * Returns the meta object for class '{@link org.somox.metrics.dsl.metricDSL.MetricAndWeight <em>Metric And Weight</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Metric And Weight</em>'.
   * @see org.somox.metrics.dsl.metricDSL.MetricAndWeight
   * @generated
   */
  EClass getMetricAndWeight();

  /**
   * Returns the meta object for the reference '{@link org.somox.metrics.dsl.metricDSL.MetricAndWeight#getMetric <em>Metric</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Metric</em>'.
   * @see org.somox.metrics.dsl.metricDSL.MetricAndWeight#getMetric()
   * @see #getMetricAndWeight()
   * @generated
   */
  EReference getMetricAndWeight_Metric();

  /**
   * Returns the meta object for the reference '{@link org.somox.metrics.dsl.metricDSL.MetricAndWeight#getWeight <em>Weight</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Weight</em>'.
   * @see org.somox.metrics.dsl.metricDSL.MetricAndWeight#getWeight()
   * @see #getMetricAndWeight()
   * @generated
   */
  EReference getMetricAndWeight_Weight();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  MetricDSLFactory getMetricDSLFactory();

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
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.MetricModelImpl <em>Metric Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricModelImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getMetricModel()
     * @generated
     */
    EClass METRIC_MODEL = eINSTANCE.getMetricModel();

    /**
     * The meta object literal for the '<em><b>Import URI</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METRIC_MODEL__IMPORT_URI = eINSTANCE.getMetricModel_ImportURI();

    /**
     * The meta object literal for the '<em><b>Metrics</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METRIC_MODEL__METRICS = eINSTANCE.getMetricModel_Metrics();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.MetricImpl <em>Metric</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getMetric()
     * @generated
     */
    EClass METRIC = eINSTANCE.getMetric();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METRIC__NAME = eINSTANCE.getMetric_Name();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.ExternalMetricImpl <em>External Metric</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.ExternalMetricImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getExternalMetric()
     * @generated
     */
    EClass EXTERNAL_METRIC = eINSTANCE.getExternalMetric();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.InternalMetricImpl <em>Internal Metric</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.InternalMetricImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getInternalMetric()
     * @generated
     */
    EClass INTERNAL_METRIC = eINSTANCE.getInternalMetric();

    /**
     * The meta object literal for the '<em><b>Short Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INTERNAL_METRIC__SHORT_NAME = eINSTANCE.getInternalMetric_ShortName();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INTERNAL_METRIC__DESCRIPTION = eINSTANCE.getInternalMetric_Description();

    /**
     * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INTERNAL_METRIC__PARAMETER = eINSTANCE.getInternalMetric_Parameter();

    /**
     * The meta object literal for the '<em><b>Definition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INTERNAL_METRIC__DEFINITION = eINSTANCE.getInternalMetric_Definition();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.NumberImpl <em>Number</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.NumberImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getNumber()
     * @generated
     */
    EClass NUMBER = eINSTANCE.getNumber();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NUMBER__NAME = eINSTANCE.getNumber_Name();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.ParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.ParameterImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getParameter()
     * @generated
     */
    EClass PARAMETER = eINSTANCE.getParameter();

    /**
     * The meta object literal for the '<em><b>Shortname</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER__SHORTNAME = eINSTANCE.getParameter_Shortname();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER__DESCRIPTION = eINSTANCE.getParameter_Description();

    /**
     * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER__DEFAULT_VALUE = eINSTANCE.getParameter_DefaultValue();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.ConstantImpl <em>Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.ConstantImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getConstant()
     * @generated
     */
    EClass CONSTANT = eINSTANCE.getConstant();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONSTANT__VALUE = eINSTANCE.getConstant_Value();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.MetricDefinitionImpl <em>Metric Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDefinitionImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getMetricDefinition()
     * @generated
     */
    EClass METRIC_DEFINITION = eINSTANCE.getMetricDefinition();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.WeightedMetricImpl <em>Weighted Metric</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.WeightedMetricImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getWeightedMetric()
     * @generated
     */
    EClass WEIGHTED_METRIC = eINSTANCE.getWeightedMetric();

    /**
     * The meta object literal for the '<em><b>Weights</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WEIGHTED_METRIC__WEIGHTS = eINSTANCE.getWeightedMetric_Weights();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.StepwiseMetricImpl <em>Stepwise Metric</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.StepwiseMetricImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getStepwiseMetric()
     * @generated
     */
    EClass STEPWISE_METRIC = eINSTANCE.getStepwiseMetric();

    /**
     * The meta object literal for the '<em><b>Inner Metric</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STEPWISE_METRIC__INNER_METRIC = eINSTANCE.getStepwiseMetric_InnerMetric();

    /**
     * The meta object literal for the '<em><b>Steps</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STEPWISE_METRIC__STEPS = eINSTANCE.getStepwiseMetric_Steps();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.RatioMetricImpl <em>Ratio Metric</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.RatioMetricImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getRatioMetric()
     * @generated
     */
    EClass RATIO_METRIC = eINSTANCE.getRatioMetric();

    /**
     * The meta object literal for the '<em><b>Nominator Metric</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RATIO_METRIC__NOMINATOR_METRIC = eINSTANCE.getRatioMetric_NominatorMetric();

    /**
     * The meta object literal for the '<em><b>Denominator Metric</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RATIO_METRIC__DENOMINATOR_METRIC = eINSTANCE.getRatioMetric_DenominatorMetric();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.BoundAndWeightImpl <em>Bound And Weight</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.BoundAndWeightImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getBoundAndWeight()
     * @generated
     */
    EClass BOUND_AND_WEIGHT = eINSTANCE.getBoundAndWeight();

    /**
     * The meta object literal for the '<em><b>Upper Bound</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BOUND_AND_WEIGHT__UPPER_BOUND = eINSTANCE.getBoundAndWeight_UpperBound();

    /**
     * The meta object literal for the '<em><b>Weight</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BOUND_AND_WEIGHT__WEIGHT = eINSTANCE.getBoundAndWeight_Weight();

    /**
     * The meta object literal for the '{@link org.somox.metrics.dsl.metricDSL.impl.MetricAndWeightImpl <em>Metric And Weight</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricAndWeightImpl
     * @see org.somox.metrics.dsl.metricDSL.impl.MetricDSLPackageImpl#getMetricAndWeight()
     * @generated
     */
    EClass METRIC_AND_WEIGHT = eINSTANCE.getMetricAndWeight();

    /**
     * The meta object literal for the '<em><b>Metric</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METRIC_AND_WEIGHT__METRIC = eINSTANCE.getMetricAndWeight_Metric();

    /**
     * The meta object literal for the '<em><b>Weight</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METRIC_AND_WEIGHT__WEIGHT = eINSTANCE.getMetricAndWeight_Weight();

  }

} //MetricDSLPackage
