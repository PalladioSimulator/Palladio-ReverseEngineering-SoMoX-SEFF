/**
 */
package org.somox.metrics.dsl.metricDSL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stepwise Metric</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.somox.metrics.dsl.metricDSL.StepwiseMetric#getInnerMetric <em>Inner Metric</em>}</li>
 *   <li>{@link org.somox.metrics.dsl.metricDSL.StepwiseMetric#getSteps <em>Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getStepwiseMetric()
 * @model
 * @generated
 */
public interface StepwiseMetric extends MetricDefinition
{
  /**
   * Returns the value of the '<em><b>Inner Metric</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Inner Metric</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Inner Metric</em>' reference.
   * @see #setInnerMetric(Metric)
   * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getStepwiseMetric_InnerMetric()
   * @model
   * @generated
   */
  Metric getInnerMetric();

  /**
   * Sets the value of the '{@link org.somox.metrics.dsl.metricDSL.StepwiseMetric#getInnerMetric <em>Inner Metric</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Inner Metric</em>' reference.
   * @see #getInnerMetric()
   * @generated
   */
  void setInnerMetric(Metric value);

  /**
   * Returns the value of the '<em><b>Steps</b></em>' containment reference list.
   * The list contents are of type {@link org.somox.metrics.dsl.metricDSL.BoundAndWeight}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Steps</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Steps</em>' containment reference list.
   * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getStepwiseMetric_Steps()
   * @model containment="true"
   * @generated
   */
  EList<BoundAndWeight> getSteps();

} // StepwiseMetric
