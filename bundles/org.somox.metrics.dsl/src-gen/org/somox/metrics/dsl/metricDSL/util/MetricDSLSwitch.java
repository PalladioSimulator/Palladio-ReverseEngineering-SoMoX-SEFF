/**
 */
package org.somox.metrics.dsl.metricDSL.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.somox.metrics.dsl.metricDSL.BoundAndWeight;
import org.somox.metrics.dsl.metricDSL.Constant;
import org.somox.metrics.dsl.metricDSL.ExternalMetric;
import org.somox.metrics.dsl.metricDSL.InternalMetric;
import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.MetricAndWeight;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.MetricDefinition;
import org.somox.metrics.dsl.metricDSL.MetricModel;
import org.somox.metrics.dsl.metricDSL.Parameter;
import org.somox.metrics.dsl.metricDSL.RatioMetric;
import org.somox.metrics.dsl.metricDSL.StepwiseMetric;
import org.somox.metrics.dsl.metricDSL.WeightedMetric;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the
 * call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for
 * each class of the model, starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the result of the switch.
 * <!-- end-user-doc -->
 *
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage
 * @generated
 */
public class MetricDSLSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static MetricDSLPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MetricDSLSwitch() {
        if (modelPackage == null) {
            modelPackage = MetricDSLPackage.eINSTANCE;
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
        case MetricDSLPackage.METRIC_MODEL: {
            final MetricModel metricModel = (MetricModel) theEObject;
            T result = this.caseMetricModel(metricModel);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.METRIC: {
            final Metric metric = (Metric) theEObject;
            T result = this.caseMetric(metric);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.EXTERNAL_METRIC: {
            final ExternalMetric externalMetric = (ExternalMetric) theEObject;
            T result = this.caseExternalMetric(externalMetric);
            if (result == null) {
                result = this.caseMetric(externalMetric);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.INTERNAL_METRIC: {
            final InternalMetric internalMetric = (InternalMetric) theEObject;
            T result = this.caseInternalMetric(internalMetric);
            if (result == null) {
                result = this.caseMetric(internalMetric);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.NUMBER: {
            final org.somox.metrics.dsl.metricDSL.Number number = (org.somox.metrics.dsl.metricDSL.Number) theEObject;
            T result = this.caseNumber(number);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.PARAMETER: {
            final Parameter parameter = (Parameter) theEObject;
            T result = this.caseParameter(parameter);
            if (result == null) {
                result = this.caseNumber(parameter);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.CONSTANT: {
            final Constant constant = (Constant) theEObject;
            T result = this.caseConstant(constant);
            if (result == null) {
                result = this.caseNumber(constant);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.METRIC_DEFINITION: {
            final MetricDefinition metricDefinition = (MetricDefinition) theEObject;
            T result = this.caseMetricDefinition(metricDefinition);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.WEIGHTED_METRIC: {
            final WeightedMetric weightedMetric = (WeightedMetric) theEObject;
            T result = this.caseWeightedMetric(weightedMetric);
            if (result == null) {
                result = this.caseMetricDefinition(weightedMetric);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.STEPWISE_METRIC: {
            final StepwiseMetric stepwiseMetric = (StepwiseMetric) theEObject;
            T result = this.caseStepwiseMetric(stepwiseMetric);
            if (result == null) {
                result = this.caseMetricDefinition(stepwiseMetric);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.RATIO_METRIC: {
            final RatioMetric ratioMetric = (RatioMetric) theEObject;
            T result = this.caseRatioMetric(ratioMetric);
            if (result == null) {
                result = this.caseMetricDefinition(ratioMetric);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.BOUND_AND_WEIGHT: {
            final BoundAndWeight boundAndWeight = (BoundAndWeight) theEObject;
            T result = this.caseBoundAndWeight(boundAndWeight);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MetricDSLPackage.METRIC_AND_WEIGHT: {
            final MetricAndWeight metricAndWeight = (MetricAndWeight) theEObject;
            T result = this.caseMetricAndWeight(metricAndWeight);
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
     * Returns the result of interpreting the object as an instance of '<em>Metric Model</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Metric Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMetricModel(final MetricModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Metric</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Metric</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMetric(final Metric object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>External Metric</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>External Metric</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExternalMetric(final ExternalMetric object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Internal Metric</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Internal Metric</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInternalMetric(final InternalMetric object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Number</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Number</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNumber(final org.somox.metrics.dsl.metricDSL.Number object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseParameter(final Parameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Constant</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Constant</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConstant(final Constant object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Metric Definition</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Metric Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMetricDefinition(final MetricDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Weighted Metric</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Weighted Metric</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWeightedMetric(final WeightedMetric object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Stepwise Metric</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Stepwise Metric</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStepwiseMetric(final StepwiseMetric object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ratio Metric</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ratio Metric</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRatioMetric(final RatioMetric object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Bound And Weight</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Bound And Weight</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBoundAndWeight(final BoundAndWeight object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Metric And Weight</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Metric And Weight</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMetricAndWeight(final MetricAndWeight object) {
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

} // MetricDSLSwitch
