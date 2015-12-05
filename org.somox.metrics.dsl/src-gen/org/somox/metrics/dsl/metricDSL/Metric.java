/**
 */
package org.somox.metrics.dsl.metricDSL;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Metric</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.Metric#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getMetric()
 * @model
 * @generated
 */
public interface Metric extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getMetric_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.somox.metrics.dsl.metricDSL.Metric#getName <em>Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // Metric
