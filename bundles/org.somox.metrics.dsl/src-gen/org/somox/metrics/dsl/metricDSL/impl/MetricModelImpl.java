/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.MetricModel;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Metric Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.MetricModelImpl#getImportURI <em>Import URI</em>}
 * </li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.MetricModelImpl#getMetrics <em>Metrics</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetricModelImpl extends MinimalEObjectImpl.Container implements MetricModel {
    /**
     * The cached value of the '{@link #getImportURI() <em>Import URI</em>}' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getImportURI()
     * @generated
     * @ordered
     */
    protected EList<String> importURI;

    /**
     * The cached value of the '{@link #getMetrics() <em>Metrics</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMetrics()
     * @generated
     * @ordered
     */
    protected EList<Metric> metrics;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MetricModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricDSLPackage.Literals.METRIC_MODEL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<String> getImportURI() {
        if (this.importURI == null) {
            this.importURI = new EDataTypeEList<String>(String.class, this, MetricDSLPackage.METRIC_MODEL__IMPORT_URI);
        }
        return this.importURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Metric> getMetrics() {
        if (this.metrics == null) {
            this.metrics = new EObjectContainmentEList<Metric>(Metric.class, this,
                    MetricDSLPackage.METRIC_MODEL__METRICS);
        }
        return this.metrics;
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
        case MetricDSLPackage.METRIC_MODEL__METRICS:
            return ((InternalEList<?>) this.getMetrics()).basicRemove(otherEnd, msgs);
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
        case MetricDSLPackage.METRIC_MODEL__IMPORT_URI:
            return this.getImportURI();
        case MetricDSLPackage.METRIC_MODEL__METRICS:
            return this.getMetrics();
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
        case MetricDSLPackage.METRIC_MODEL__IMPORT_URI:
            this.getImportURI().clear();
            this.getImportURI().addAll((Collection<? extends String>) newValue);
            return;
        case MetricDSLPackage.METRIC_MODEL__METRICS:
            this.getMetrics().clear();
            this.getMetrics().addAll((Collection<? extends Metric>) newValue);
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
        case MetricDSLPackage.METRIC_MODEL__IMPORT_URI:
            this.getImportURI().clear();
            return;
        case MetricDSLPackage.METRIC_MODEL__METRICS:
            this.getMetrics().clear();
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
        case MetricDSLPackage.METRIC_MODEL__IMPORT_URI:
            return this.importURI != null && !this.importURI.isEmpty();
        case MetricDSLPackage.METRIC_MODEL__METRICS:
            return this.metrics != null && !this.metrics.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }

        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (importURI: ");
        result.append(this.importURI);
        result.append(')');
        return result.toString();
    }

} // MetricModelImpl
