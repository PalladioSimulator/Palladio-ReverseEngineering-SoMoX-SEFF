/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

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
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MetricDSLFactoryImpl extends EFactoryImpl implements MetricDSLFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static MetricDSLFactory init()
  {
    try
    {
      MetricDSLFactory theMetricDSLFactory = (MetricDSLFactory)EPackage.Registry.INSTANCE.getEFactory(MetricDSLPackage.eNS_URI);
      if (theMetricDSLFactory != null)
      {
        return theMetricDSLFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new MetricDSLFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MetricDSLFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case MetricDSLPackage.METRIC_MODEL: return createMetricModel();
      case MetricDSLPackage.METRIC: return createMetric();
      case MetricDSLPackage.EXTERNAL_METRIC: return createExternalMetric();
      case MetricDSLPackage.INTERNAL_METRIC: return createInternalMetric();
      case MetricDSLPackage.NUMBER: return createNumber();
      case MetricDSLPackage.PARAMETER: return createParameter();
      case MetricDSLPackage.CONSTANT: return createConstant();
      case MetricDSLPackage.METRIC_DEFINITION: return createMetricDefinition();
      case MetricDSLPackage.WEIGHTED_METRIC: return createWeightedMetric();
      case MetricDSLPackage.STEPWISE_METRIC: return createStepwiseMetric();
      case MetricDSLPackage.RATIO_METRIC: return createRatioMetric();
      case MetricDSLPackage.BOUND_AND_WEIGHT: return createBoundAndWeight();
      case MetricDSLPackage.METRIC_AND_WEIGHT: return createMetricAndWeight();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MetricModel createMetricModel()
  {
    MetricModelImpl metricModel = new MetricModelImpl();
    return metricModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Metric createMetric()
  {
    MetricImpl metric = new MetricImpl();
    return metric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExternalMetric createExternalMetric()
  {
    ExternalMetricImpl externalMetric = new ExternalMetricImpl();
    return externalMetric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InternalMetric createInternalMetric()
  {
    InternalMetricImpl internalMetric = new InternalMetricImpl();
    return internalMetric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.somox.metrics.dsl.metricDSL.Number createNumber()
  {
    NumberImpl number = new NumberImpl();
    return number;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Parameter createParameter()
  {
    ParameterImpl parameter = new ParameterImpl();
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Constant createConstant()
  {
    ConstantImpl constant = new ConstantImpl();
    return constant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MetricDefinition createMetricDefinition()
  {
    MetricDefinitionImpl metricDefinition = new MetricDefinitionImpl();
    return metricDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WeightedMetric createWeightedMetric()
  {
    WeightedMetricImpl weightedMetric = new WeightedMetricImpl();
    return weightedMetric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StepwiseMetric createStepwiseMetric()
  {
    StepwiseMetricImpl stepwiseMetric = new StepwiseMetricImpl();
    return stepwiseMetric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RatioMetric createRatioMetric()
  {
    RatioMetricImpl ratioMetric = new RatioMetricImpl();
    return ratioMetric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BoundAndWeight createBoundAndWeight()
  {
    BoundAndWeightImpl boundAndWeight = new BoundAndWeightImpl();
    return boundAndWeight;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MetricAndWeight createMetricAndWeight()
  {
    MetricAndWeightImpl metricAndWeight = new MetricAndWeightImpl();
    return metricAndWeight;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MetricDSLPackage getMetricDSLPackage()
  {
    return (MetricDSLPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static MetricDSLPackage getPackage()
  {
    return MetricDSLPackage.eINSTANCE;
  }

} //MetricDSLFactoryImpl
