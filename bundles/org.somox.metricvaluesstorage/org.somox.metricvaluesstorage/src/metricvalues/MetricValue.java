/**
 */
package metricvalues;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Metric Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link metricvalues.MetricValue#getMetricID <em>Metric ID</em>}</li>
 *   <li>{@link metricvalues.MetricValue#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see metricvalues.MetricvaluesPackage#getMetricValue()
 * @model
 * @generated
 */
public interface MetricValue extends EObject {
	/**
	 * Returns the value of the '<em><b>Metric ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metric ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metric ID</em>' attribute.
	 * @see #setMetricID(String)
	 * @see metricvalues.MetricvaluesPackage#getMetricValue_MetricID()
	 * @model
	 * @generated
	 */
	String getMetricID();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValue#getMetricID <em>Metric ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metric ID</em>' attribute.
	 * @see #getMetricID()
	 * @generated
	 */
	void setMetricID(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValue_Value()
	 * @model
	 * @generated
	 */
	double getValue();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(double value);

} // MetricValue
