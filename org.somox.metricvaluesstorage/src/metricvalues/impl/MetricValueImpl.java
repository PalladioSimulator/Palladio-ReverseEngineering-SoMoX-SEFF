/**
 */
package metricvalues.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import metricvalues.MetricValue;
import metricvalues.MetricvaluesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Metric Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link metricvalues.impl.MetricValueImpl#getMetricID <em>Metric ID</em>}</li>
 * <li>{@link metricvalues.impl.MetricValueImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetricValueImpl extends MinimalEObjectImpl.Container implements MetricValue {
    /**
     * The default value of the '{@link #getMetricID() <em>Metric ID</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMetricID()
     * @generated
     * @ordered
     */
    protected static final String METRIC_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMetricID() <em>Metric ID</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMetricID()
     * @generated
     * @ordered
     */
    protected String metricID = METRIC_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final double VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected double value = VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MetricValueImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricvaluesPackage.Literals.METRIC_VALUE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getMetricID() {
        return this.metricID;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMetricID(final String newMetricID) {
        final String oldMetricID = this.metricID;
        this.metricID = newMetricID;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUE__METRIC_ID,
                    oldMetricID, this.metricID));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getValue() {
        return this.value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setValue(final double newValue) {
        final double oldValue = this.value;
        this.value = newValue;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUE__VALUE,
                    oldValue, this.value));
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
        case MetricvaluesPackage.METRIC_VALUE__METRIC_ID:
            return this.getMetricID();
        case MetricvaluesPackage.METRIC_VALUE__VALUE:
            return this.getValue();
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
        case MetricvaluesPackage.METRIC_VALUE__METRIC_ID:
            this.setMetricID((String) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUE__VALUE:
            this.setValue((Double) newValue);
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
        case MetricvaluesPackage.METRIC_VALUE__METRIC_ID:
            this.setMetricID(METRIC_ID_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUE__VALUE:
            this.setValue(VALUE_EDEFAULT);
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
        case MetricvaluesPackage.METRIC_VALUE__METRIC_ID:
            return METRIC_ID_EDEFAULT == null ? this.metricID != null : !METRIC_ID_EDEFAULT.equals(this.metricID);
        case MetricvaluesPackage.METRIC_VALUE__VALUE:
            return this.value != VALUE_EDEFAULT;
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
        result.append(" (metricID: ");
        result.append(this.metricID);
        result.append(", value: ");
        result.append(this.value);
        result.append(')');
        return result.toString();
    }

} // MetricValueImpl
