/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.RatioMetric;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Ratio Metric</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.RatioMetricImpl#getNominatorMetric
 * <em>Nominator Metric</em>}</li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.RatioMetricImpl#getDenominatorMetric
 * <em>Denominator Metric</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RatioMetricImpl extends MetricDefinitionImpl implements RatioMetric {
    /**
     * The cached value of the '{@link #getNominatorMetric() <em>Nominator Metric</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNominatorMetric()
     * @generated
     * @ordered
     */
    protected Metric nominatorMetric;

    /**
     * The cached value of the '{@link #getDenominatorMetric() <em>Denominator Metric</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDenominatorMetric()
     * @generated
     * @ordered
     */
    protected Metric denominatorMetric;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected RatioMetricImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricDSLPackage.Literals.RATIO_METRIC;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Metric getNominatorMetric() {
        if (this.nominatorMetric != null && this.nominatorMetric.eIsProxy()) {
            final InternalEObject oldNominatorMetric = (InternalEObject) this.nominatorMetric;
            this.nominatorMetric = (Metric) this.eResolveProxy(oldNominatorMetric);
            if (this.nominatorMetric != oldNominatorMetric) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC, oldNominatorMetric, this.nominatorMetric));
                }
            }
        }
        return this.nominatorMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Metric basicGetNominatorMetric() {
        return this.nominatorMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setNominatorMetric(final Metric newNominatorMetric) {
        final Metric oldNominatorMetric = this.nominatorMetric;
        this.nominatorMetric = newNominatorMetric;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC,
                    oldNominatorMetric, this.nominatorMetric));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Metric getDenominatorMetric() {
        if (this.denominatorMetric != null && this.denominatorMetric.eIsProxy()) {
            final InternalEObject oldDenominatorMetric = (InternalEObject) this.denominatorMetric;
            this.denominatorMetric = (Metric) this.eResolveProxy(oldDenominatorMetric);
            if (this.denominatorMetric != oldDenominatorMetric) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC, oldDenominatorMetric,
                            this.denominatorMetric));
                }
            }
        }
        return this.denominatorMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Metric basicGetDenominatorMetric() {
        return this.denominatorMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDenominatorMetric(final Metric newDenominatorMetric) {
        final Metric oldDenominatorMetric = this.denominatorMetric;
        this.denominatorMetric = newDenominatorMetric;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC, oldDenominatorMetric, this.denominatorMetric));
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
        case MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC:
            if (resolve) {
                return this.getNominatorMetric();
            }
            return this.basicGetNominatorMetric();
        case MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC:
            if (resolve) {
                return this.getDenominatorMetric();
            }
            return this.basicGetDenominatorMetric();
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
        case MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC:
            this.setNominatorMetric((Metric) newValue);
            return;
        case MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC:
            this.setDenominatorMetric((Metric) newValue);
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
        case MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC:
            this.setNominatorMetric((Metric) null);
            return;
        case MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC:
            this.setDenominatorMetric((Metric) null);
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
        case MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC:
            return this.nominatorMetric != null;
        case MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC:
            return this.denominatorMetric != null;
        }
        return super.eIsSet(featureID);
    }

} // RatioMetricImpl
