/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.MetricAndWeight;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Metric And Weight</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.MetricAndWeightImpl#getMetric <em>Metric</em>}
 * </li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.MetricAndWeightImpl#getWeight <em>Weight</em>}
 * </li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetricAndWeightImpl extends MinimalEObjectImpl.Container implements MetricAndWeight {
    /**
     * The cached value of the '{@link #getMetric() <em>Metric</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getMetric()
     * @generated
     * @ordered
     */
    protected Metric metric;

    /**
     * The cached value of the '{@link #getWeight() <em>Weight</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getWeight()
     * @generated
     * @ordered
     */
    protected org.somox.metrics.dsl.metricDSL.Number weight;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MetricAndWeightImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricDSLPackage.Literals.METRIC_AND_WEIGHT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Metric getMetric() {
        if (this.metric != null && this.metric.eIsProxy()) {
            final InternalEObject oldMetric = (InternalEObject) this.metric;
            this.metric = (Metric) this.eResolveProxy(oldMetric);
            if (this.metric != oldMetric) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            MetricDSLPackage.METRIC_AND_WEIGHT__METRIC, oldMetric, this.metric));
                }
            }
        }
        return this.metric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Metric basicGetMetric() {
        return this.metric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMetric(final Metric newMetric) {
        final Metric oldMetric = this.metric;
        this.metric = newMetric;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.METRIC_AND_WEIGHT__METRIC,
                    oldMetric, this.metric));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public org.somox.metrics.dsl.metricDSL.Number getWeight() {
        if (this.weight != null && this.weight.eIsProxy()) {
            final InternalEObject oldWeight = (InternalEObject) this.weight;
            this.weight = (org.somox.metrics.dsl.metricDSL.Number) this.eResolveProxy(oldWeight);
            if (this.weight != oldWeight) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT, oldWeight, this.weight));
                }
            }
        }
        return this.weight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public org.somox.metrics.dsl.metricDSL.Number basicGetWeight() {
        return this.weight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeight(final org.somox.metrics.dsl.metricDSL.Number newWeight) {
        final org.somox.metrics.dsl.metricDSL.Number oldWeight = this.weight;
        this.weight = newWeight;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT,
                    oldWeight, this.weight));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case MetricDSLPackage.METRIC_AND_WEIGHT__METRIC:
            if (resolve) {
                return this.getMetric();
            }
            return this.basicGetMetric();
        case MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT:
            if (resolve) {
                return this.getWeight();
            }
            return this.basicGetWeight();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case MetricDSLPackage.METRIC_AND_WEIGHT__METRIC:
            this.setMetric((Metric) newValue);
            return;
        case MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT:
            this.setWeight((org.somox.metrics.dsl.metricDSL.Number) newValue);
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
        case MetricDSLPackage.METRIC_AND_WEIGHT__METRIC:
            this.setMetric((Metric) null);
            return;
        case MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT:
            this.setWeight((org.somox.metrics.dsl.metricDSL.Number) null);
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
        case MetricDSLPackage.METRIC_AND_WEIGHT__METRIC:
            return this.metric != null;
        case MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT:
            return this.weight != null;
        }
        return super.eIsSet(featureID);
    }

} // MetricAndWeightImpl
