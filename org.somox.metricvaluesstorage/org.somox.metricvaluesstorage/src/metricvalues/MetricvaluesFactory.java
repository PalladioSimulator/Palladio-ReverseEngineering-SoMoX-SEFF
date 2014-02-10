/**
 */
package metricvalues;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see metricvalues.MetricvaluesPackage
 * @generated
 */
public interface MetricvaluesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MetricvaluesFactory eINSTANCE = metricvalues.impl.MetricvaluesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component</em>'.
	 * @generated
	 */
	Component createComponent();

	/**
	 * Returns a new object of class '<em>Component Candidate</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Candidate</em>'.
	 * @generated
	 */
	ComponentCandidate createComponentCandidate();

	/**
	 * Returns a new object of class '<em>Iteration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Iteration</em>'.
	 * @generated
	 */
	Iteration createIteration();

	/**
	 * Returns a new object of class '<em>Metric Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Metric Value</em>'.
	 * @generated
	 */
	MetricValue createMetricValue();

	/**
	 * Returns a new object of class '<em>Metric Values Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Metric Values Model</em>'.
	 * @generated
	 */
	MetricValuesModel createMetricValuesModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MetricvaluesPackage getMetricvaluesPackage();

} //MetricvaluesFactory
