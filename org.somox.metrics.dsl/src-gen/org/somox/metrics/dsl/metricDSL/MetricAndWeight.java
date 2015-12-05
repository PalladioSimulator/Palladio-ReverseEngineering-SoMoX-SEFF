/**
 */
package org.somox.metrics.dsl.metricDSL;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Metric And Weight</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.MetricAndWeight#getMetric <em>Metric</em>}</li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.MetricAndWeight#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getMetricAndWeight()
 * @model
 * @generated
 */
public interface MetricAndWeight extends EObject {
    /**
     * Returns the value of the '<em><b>Metric</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Metric</em>' reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Metric</em>' reference.
     * @see #setMetric(Metric)
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getMetricAndWeight_Metric()
     * @model
     * @generated
     */
    Metric getMetric();

    /**
     * Sets the value of the '{@link org.somox.metrics.dsl.metricDSL.MetricAndWeight#getMetric
     * <em>Metric</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Metric</em>' reference.
     * @see #getMetric()
     * @generated
     */
    void setMetric(Metric value);

    /**
     * Returns the value of the '<em><b>Weight</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Weight</em>' reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Weight</em>' reference.
     * @see #setWeight(org.somox.metrics.dsl.metricDSL.Number)
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getMetricAndWeight_Weight()
     * @model
     * @generated
     */
    org.somox.metrics.dsl.metricDSL.Number getWeight();

    /**
     * Sets the value of the '{@link org.somox.metrics.dsl.metricDSL.MetricAndWeight#getWeight
     * <em>Weight</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Weight</em>' reference.
     * @see #getWeight()
     * @generated
     */
    void setWeight(org.somox.metrics.dsl.metricDSL.Number value);

} // MetricAndWeight
