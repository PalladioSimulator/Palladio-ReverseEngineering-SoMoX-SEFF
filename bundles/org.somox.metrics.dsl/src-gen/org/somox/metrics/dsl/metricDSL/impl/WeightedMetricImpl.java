/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.somox.metrics.dsl.metricDSL.MetricAndWeight;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.WeightedMetric;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Weighted Metric</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.WeightedMetricImpl#getWeights <em>Weights</em>}
 * </li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WeightedMetricImpl extends MetricDefinitionImpl implements WeightedMetric {
    /**
     * The cached value of the '{@link #getWeights() <em>Weights</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeights()
     * @generated
     * @ordered
     */
    protected EList<MetricAndWeight> weights;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected WeightedMetricImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricDSLPackage.Literals.WEIGHTED_METRIC;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<MetricAndWeight> getWeights() {
        if (this.weights == null) {
            this.weights = new EObjectContainmentEList<MetricAndWeight>(MetricAndWeight.class, this,
                    MetricDSLPackage.WEIGHTED_METRIC__WEIGHTS);
        }
        return this.weights;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case MetricDSLPackage.WEIGHTED_METRIC__WEIGHTS:
            return ((InternalEList<?>) this.getWeights()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case MetricDSLPackage.WEIGHTED_METRIC__WEIGHTS:
            return this.getWeights();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case MetricDSLPackage.WEIGHTED_METRIC__WEIGHTS:
            this.getWeights().clear();
            this.getWeights().addAll((Collection<? extends MetricAndWeight>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case MetricDSLPackage.WEIGHTED_METRIC__WEIGHTS:
            this.getWeights().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case MetricDSLPackage.WEIGHTED_METRIC__WEIGHTS:
            return this.weights != null && !this.weights.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // WeightedMetricImpl
