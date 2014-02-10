/**
 */
package metricvalues;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Candidate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link metricvalues.ComponentCandidate#getMetricValues <em>Metric Values</em>}</li>
 *   <li>{@link metricvalues.ComponentCandidate#getFirstComponent <em>First Component</em>}</li>
 *   <li>{@link metricvalues.ComponentCandidate#getSecondComponent <em>Second Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see metricvalues.MetricvaluesPackage#getComponentCandidate()
 * @model
 * @generated
 */
public interface ComponentCandidate extends EObject {
	/**
	 * Returns the value of the '<em><b>Metric Values</b></em>' containment reference list.
	 * The list contents are of type {@link metricvalues.MetricValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metric Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metric Values</em>' containment reference list.
	 * @see metricvalues.MetricvaluesPackage#getComponentCandidate_MetricValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<MetricValue> getMetricValues();

	/**
	 * Returns the value of the '<em><b>First Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Component</em>' reference.
	 * @see #setFirstComponent(Component)
	 * @see metricvalues.MetricvaluesPackage#getComponentCandidate_FirstComponent()
	 * @model required="true"
	 * @generated
	 */
	Component getFirstComponent();

	/**
	 * Sets the value of the '{@link metricvalues.ComponentCandidate#getFirstComponent <em>First Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Component</em>' reference.
	 * @see #getFirstComponent()
	 * @generated
	 */
	void setFirstComponent(Component value);

	/**
	 * Returns the value of the '<em><b>Second Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Second Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Second Component</em>' reference.
	 * @see #setSecondComponent(Component)
	 * @see metricvalues.MetricvaluesPackage#getComponentCandidate_SecondComponent()
	 * @model required="true"
	 * @generated
	 */
	Component getSecondComponent();

	/**
	 * Sets the value of the '{@link metricvalues.ComponentCandidate#getSecondComponent <em>Second Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Second Component</em>' reference.
	 * @see #getSecondComponent()
	 * @generated
	 */
	void setSecondComponent(Component value);

} // ComponentCandidate
