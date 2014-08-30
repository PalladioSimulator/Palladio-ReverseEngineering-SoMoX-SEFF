/**
 */
package org.somox.metrics.dsl.metricDSL;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage
 * @generated
 */
public interface MetricDSLFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MetricDSLFactory eINSTANCE = org.somox.metrics.dsl.metricDSL.impl.MetricDSLFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Metric Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Metric Model</em>'.
   * @generated
   */
  MetricModel createMetricModel();

  /**
   * Returns a new object of class '<em>Metric</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Metric</em>'.
   * @generated
   */
  Metric createMetric();

  /**
   * Returns a new object of class '<em>External Metric</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>External Metric</em>'.
   * @generated
   */
  ExternalMetric createExternalMetric();

  /**
   * Returns a new object of class '<em>Internal Metric</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Internal Metric</em>'.
   * @generated
   */
  InternalMetric createInternalMetric();

  /**
   * Returns a new object of class '<em>Number</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Number</em>'.
   * @generated
   */
  Number createNumber();

  /**
   * Returns a new object of class '<em>Parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameter</em>'.
   * @generated
   */
  Parameter createParameter();

  /**
   * Returns a new object of class '<em>Constant</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Constant</em>'.
   * @generated
   */
  Constant createConstant();

  /**
   * Returns a new object of class '<em>Metric Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Metric Definition</em>'.
   * @generated
   */
  MetricDefinition createMetricDefinition();

  /**
   * Returns a new object of class '<em>Weighted Metric</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Weighted Metric</em>'.
   * @generated
   */
  WeightedMetric createWeightedMetric();

  /**
   * Returns a new object of class '<em>Stepwise Metric</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Stepwise Metric</em>'.
   * @generated
   */
  StepwiseMetric createStepwiseMetric();

  /**
   * Returns a new object of class '<em>Ratio Metric</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ratio Metric</em>'.
   * @generated
   */
  RatioMetric createRatioMetric();

  /**
   * Returns a new object of class '<em>Bound And Weight</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Bound And Weight</em>'.
   * @generated
   */
  BoundAndWeight createBoundAndWeight();

  /**
   * Returns a new object of class '<em>Metric And Weight</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Metric And Weight</em>'.
   * @generated
   */
  MetricAndWeight createMetricAndWeight();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  MetricDSLPackage getMetricDSLPackage();

} //MetricDSLFactory
