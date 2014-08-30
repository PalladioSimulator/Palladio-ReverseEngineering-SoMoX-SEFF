/**
 */
package org.somox.metrics.dsl.metricDSL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ratio Metric</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.somox.metrics.dsl.metricDSL.RatioMetric#getNominatorMetric <em>Nominator Metric</em>}</li>
 *   <li>{@link org.somox.metrics.dsl.metricDSL.RatioMetric#getDenominatorMetric <em>Denominator Metric</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getRatioMetric()
 * @model
 * @generated
 */
public interface RatioMetric extends MetricDefinition
{
  /**
   * Returns the value of the '<em><b>Nominator Metric</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nominator Metric</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nominator Metric</em>' reference.
   * @see #setNominatorMetric(Metric)
   * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getRatioMetric_NominatorMetric()
   * @model
   * @generated
   */
  Metric getNominatorMetric();

  /**
   * Sets the value of the '{@link org.somox.metrics.dsl.metricDSL.RatioMetric#getNominatorMetric <em>Nominator Metric</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Nominator Metric</em>' reference.
   * @see #getNominatorMetric()
   * @generated
   */
  void setNominatorMetric(Metric value);

  /**
   * Returns the value of the '<em><b>Denominator Metric</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Denominator Metric</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Denominator Metric</em>' reference.
   * @see #setDenominatorMetric(Metric)
   * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getRatioMetric_DenominatorMetric()
   * @model
   * @generated
   */
  Metric getDenominatorMetric();

  /**
   * Sets the value of the '{@link org.somox.metrics.dsl.metricDSL.RatioMetric#getDenominatorMetric <em>Denominator Metric</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Denominator Metric</em>' reference.
   * @see #getDenominatorMetric()
   * @generated
   */
  void setDenominatorMetric(Metric value);

} // RatioMetric
