/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.somox.metrics.dsl.metricDSL.BoundAndWeight;
import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.StepwiseMetric;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Stepwise Metric</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.StepwiseMetricImpl#getInnerMetric
 * <em>Inner Metric</em>}</li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.StepwiseMetricImpl#getSteps <em>Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StepwiseMetricImpl extends MetricDefinitionImpl implements StepwiseMetric {
    /**
     * The cached value of the '{@link #getInnerMetric() <em>Inner Metric</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInnerMetric()
     * @generated
     * @ordered
     */
    protected Metric innerMetric;

    /**
     * The cached value of the '{@link #getSteps() <em>Steps</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSteps()
     * @generated
     * @ordered
     */
    protected EList<BoundAndWeight> steps;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected StepwiseMetricImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricDSLPackage.Literals.STEPWISE_METRIC;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Metric getInnerMetric() {
        if (this.innerMetric != null && this.innerMetric.eIsProxy()) {
            final InternalEObject oldInnerMetric = (InternalEObject) this.innerMetric;
            this.innerMetric = (Metric) this.eResolveProxy(oldInnerMetric);
            if (this.innerMetric != oldInnerMetric) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC, oldInnerMetric, this.innerMetric));
                }
            }
        }
        return this.innerMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Metric basicGetInnerMetric() {
        return this.innerMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInnerMetric(final Metric newInnerMetric) {
        final Metric oldInnerMetric = this.innerMetric;
        this.innerMetric = newInnerMetric;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC,
                    oldInnerMetric, this.innerMetric));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<BoundAndWeight> getSteps() {
        if (this.steps == null) {
            this.steps = new EObjectContainmentEList<BoundAndWeight>(BoundAndWeight.class, this,
                    MetricDSLPackage.STEPWISE_METRIC__STEPS);
        }
        return this.steps;
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
        case MetricDSLPackage.STEPWISE_METRIC__STEPS:
            return ((InternalEList<?>) this.getSteps()).basicRemove(otherEnd, msgs);
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
        case MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC:
            if (resolve) {
                return this.getInnerMetric();
            }
            return this.basicGetInnerMetric();
        case MetricDSLPackage.STEPWISE_METRIC__STEPS:
            return this.getSteps();
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
        case MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC:
            this.setInnerMetric((Metric) newValue);
            return;
        case MetricDSLPackage.STEPWISE_METRIC__STEPS:
            this.getSteps().clear();
            this.getSteps().addAll((Collection<? extends BoundAndWeight>) newValue);
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
        case MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC:
            this.setInnerMetric((Metric) null);
            return;
        case MetricDSLPackage.STEPWISE_METRIC__STEPS:
            this.getSteps().clear();
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
        case MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC:
            return this.innerMetric != null;
        case MetricDSLPackage.STEPWISE_METRIC__STEPS:
            return this.steps != null && !this.steps.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // StepwiseMetricImpl
