/**
 */
package org.somox.metrics.dsl.metricDSL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Internal Metric</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.InternalMetric#getShortName <em>Short Name</em>}</li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.InternalMetric#getDescription <em>Description</em>}
 * </li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.InternalMetric#getParameter <em>Parameter</em>}</li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.InternalMetric#getDefinition <em>Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getInternalMetric()
 * @model
 * @generated
 */
public interface InternalMetric extends Metric {
    /**
     * Returns the value of the '<em><b>Short Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Short Name</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Short Name</em>' attribute.
     * @see #setShortName(String)
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getInternalMetric_ShortName()
     * @model
     * @generated
     */
    String getShortName();

    /**
     * Sets the value of the '{@link org.somox.metrics.dsl.metricDSL.InternalMetric#getShortName
     * <em>Short Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Short Name</em>' attribute.
     * @see #getShortName()
     * @generated
     */
    void setShortName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getInternalMetric_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.somox.metrics.dsl.metricDSL.InternalMetric#getDescription
     * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Parameter</b></em>' containment reference list. The list
     * contents are of type {@link org.somox.metrics.dsl.metricDSL.Number}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameter</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Parameter</em>' containment reference list.
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getInternalMetric_Parameter()
     * @model containment="true"
     * @generated
     */
    EList<org.somox.metrics.dsl.metricDSL.Number> getParameter();

    /**
     * Returns the value of the '<em><b>Definition</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Definition</em>' containment reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Definition</em>' containment reference.
     * @see #setDefinition(MetricDefinition)
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getInternalMetric_Definition()
     * @model containment="true"
     * @generated
     */
    MetricDefinition getDefinition();

    /**
     * Sets the value of the '{@link org.somox.metrics.dsl.metricDSL.InternalMetric#getDefinition
     * <em>Definition</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Definition</em>' containment reference.
     * @see #getDefinition()
     * @generated
     */
    void setDefinition(MetricDefinition value);

} // InternalMetric
