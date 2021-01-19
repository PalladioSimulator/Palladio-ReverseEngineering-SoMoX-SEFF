/**
 */
package metricvalues.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import metricvalues.Component;
import metricvalues.ComponentCandidate;
import metricvalues.Iteration;
import metricvalues.MetricValue;
import metricvalues.MetricValuesModel;
import metricvalues.MetricvaluesPackage;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the
 * call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for
 * each class of the model, starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the result of the switch.
 * <!-- end-user-doc -->
 *
 * @see metricvalues.MetricvaluesPackage
 * @generated
 */
public class MetricvaluesSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static MetricvaluesPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MetricvaluesSwitch() {
        if (modelPackage == null) {
            modelPackage = MetricvaluesPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(final EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result;
     * it yields that result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(final int classifierID, final EObject theEObject) {
        switch (classifierID) {
        case MetricvaluesPackage.COMPONENT: {
            final Component component = (Component) theEObject;
            T result = this.caseComponent(component);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricvaluesPackage.COMPONENT_CANDIDATE: {
            final ComponentCandidate componentCandidate = (ComponentCandidate) theEObject;
            T result = this.caseComponentCandidate(componentCandidate);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricvaluesPackage.ITERATION: {
            final Iteration iteration = (Iteration) theEObject;
            T result = this.caseIteration(iteration);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricvaluesPackage.METRIC_VALUE: {
            final MetricValue metricValue = (MetricValue) theEObject;
            T result = this.caseMetricValue(metricValue);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricvaluesPackage.METRIC_VALUES_MODEL: {
            final MetricValuesModel metricValuesModel = (MetricValuesModel) theEObject;
            T result = this.caseMetricValuesModel(metricValuesModel);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        default:
            return this.defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Component</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Component</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComponent(final Component object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Component Candidate</em>
     * '. <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Component Candidate</em>
     *         '.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComponentCandidate(final ComponentCandidate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Iteration</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Iteration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIteration(final Iteration object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Metric Value</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Metric Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMetricValue(final MetricValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Metric Values Model</em>
     * '. <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Metric Values Model</em>
     *         '.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMetricValuesModel(final MetricValuesModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch, but this is the last case anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(final EObject object) {
        return null;
    }

} // MetricvaluesSwitch
