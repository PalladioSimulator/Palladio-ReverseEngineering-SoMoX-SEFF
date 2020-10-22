/**
 */
package org.somox.metrics.dsl.metricDSL;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Metric Model</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.MetricModel#getImportURI <em>Import URI</em>}</li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.MetricModel#getMetrics <em>Metrics</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getMetricModel()
 * @model
 * @generated
 */
public interface MetricModel extends EObject {
    /**
     * Returns the value of the '<em><b>Import URI</b></em>' attribute list. The list contents are
     * of type {@link java.lang.String}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Import URI</em>' attribute list isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Import URI</em>' attribute list.
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getMetricModel_ImportURI()
     * @model unique="false"
     * @generated
     */
    EList<String> getImportURI();

    /**
     * Returns the value of the '<em><b>Metrics</b></em>' containment reference list. The list
     * contents are of type {@link org.somox.metrics.dsl.metricDSL.Metric}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Metrics</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Metrics</em>' containment reference list.
     * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage#getMetricModel_Metrics()
     * @model containment="true"
     * @generated
     */
    EList<Metric> getMetrics();

} // MetricModel
