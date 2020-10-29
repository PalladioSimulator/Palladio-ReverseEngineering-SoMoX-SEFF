/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.somox.metrics.dsl.metricDSL.BoundAndWeight;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Bound And Weight</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.BoundAndWeightImpl#getUpperBound
 * <em>Upper Bound</em>}</li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.BoundAndWeightImpl#getWeight <em>Weight</em>}
 * </li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BoundAndWeightImpl extends MinimalEObjectImpl.Container implements BoundAndWeight {
    /**
     * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getUpperBound()
     * @generated
     * @ordered
     */
    protected org.somox.metrics.dsl.metricDSL.Number upperBound;

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
    protected BoundAndWeightImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricDSLPackage.Literals.BOUND_AND_WEIGHT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public org.somox.metrics.dsl.metricDSL.Number getUpperBound() {
        if (this.upperBound != null && this.upperBound.eIsProxy()) {
            final InternalEObject oldUpperBound = (InternalEObject) this.upperBound;
            this.upperBound = (org.somox.metrics.dsl.metricDSL.Number) this.eResolveProxy(oldUpperBound);
            if (this.upperBound != oldUpperBound) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            MetricDSLPackage.BOUND_AND_WEIGHT__UPPER_BOUND, oldUpperBound, this.upperBound));
                }
            }
        }
        return this.upperBound;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public org.somox.metrics.dsl.metricDSL.Number basicGetUpperBound() {
        return this.upperBound;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setUpperBound(final org.somox.metrics.dsl.metricDSL.Number newUpperBound) {
        final org.somox.metrics.dsl.metricDSL.Number oldUpperBound = this.upperBound;
        this.upperBound = newUpperBound;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.BOUND_AND_WEIGHT__UPPER_BOUND,
                    oldUpperBound, this.upperBound));
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
                            MetricDSLPackage.BOUND_AND_WEIGHT__WEIGHT, oldWeight, this.weight));
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
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.BOUND_AND_WEIGHT__WEIGHT,
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
        case MetricDSLPackage.BOUND_AND_WEIGHT__UPPER_BOUND:
            if (resolve) {
                return this.getUpperBound();
            }
            return this.basicGetUpperBound();
        case MetricDSLPackage.BOUND_AND_WEIGHT__WEIGHT:
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
        case MetricDSLPackage.BOUND_AND_WEIGHT__UPPER_BOUND:
            this.setUpperBound((org.somox.metrics.dsl.metricDSL.Number) newValue);
            return;
        case MetricDSLPackage.BOUND_AND_WEIGHT__WEIGHT:
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
        case MetricDSLPackage.BOUND_AND_WEIGHT__UPPER_BOUND:
            this.setUpperBound((org.somox.metrics.dsl.metricDSL.Number) null);
            return;
        case MetricDSLPackage.BOUND_AND_WEIGHT__WEIGHT:
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
        case MetricDSLPackage.BOUND_AND_WEIGHT__UPPER_BOUND:
            return this.upperBound != null;
        case MetricDSLPackage.BOUND_AND_WEIGHT__WEIGHT:
            return this.weight != null;
        }
        return super.eIsSet(featureID);
    }

} // BoundAndWeightImpl
