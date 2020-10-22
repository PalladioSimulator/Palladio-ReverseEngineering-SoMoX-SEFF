/**
 */
package org.somox.metrics.dsl.metricDSL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Weighted Metric</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.WeightedMetric#getWeights <em>Weights</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getWeightedMetric()
 * @model
 * @generated
 */
public interface WeightedMetric extends MetricDefinition {
    /**
     * Returns the value of the '<em><b>Weights</b></em>' containment reference list. The list
     * contents are of type {@link org.somox.metrics.dsl.metricDSL.MetricAndWeight}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Weights</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Weights</em>' containment reference list.
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getWeightedMetric_Weights()
     * @model containment="true"
     * @generated
     */
    EList<MetricAndWeight> getWeights();

} // WeightedMetric
