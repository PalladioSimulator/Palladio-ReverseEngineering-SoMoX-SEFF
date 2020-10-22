/**
 */
package org.somox.metrics.dsl.metricDSL;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Bound And Weight</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.BoundAndWeight#getUpperBound <em>Upper Bound</em>}
 * </li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.BoundAndWeight#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getBoundAndWeight()
 * @model
 * @generated
 */
public interface BoundAndWeight extends EObject {
    /**
     * Returns the value of the '<em><b>Upper Bound</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Upper Bound</em>' reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Upper Bound</em>' reference.
     * @see #setUpperBound(org.somox.metrics.dsl.metricDSL.Number)
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getBoundAndWeight_UpperBound()
     * @model
     * @generated
     */
    org.somox.metrics.dsl.metricDSL.Number getUpperBound();

    /**
     * Sets the value of the '{@link org.somox.metrics.dsl.metricDSL.BoundAndWeight#getUpperBound
     * <em>Upper Bound</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Upper Bound</em>' reference.
     * @see #getUpperBound()
     * @generated
     */
    void setUpperBound(org.somox.metrics.dsl.metricDSL.Number value);

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
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getBoundAndWeight_Weight()
     * @model
     * @generated
     */
    org.somox.metrics.dsl.metricDSL.Number getWeight();

    /**
     * Sets the value of the '{@link org.somox.metrics.dsl.metricDSL.BoundAndWeight#getWeight
     * <em>Weight</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Weight</em>' reference.
     * @see #getWeight()
     * @generated
     */
    void setWeight(org.somox.metrics.dsl.metricDSL.Number value);

} // BoundAndWeight
