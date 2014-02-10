/**
 */
package metricvalues.impl;

import metricvalues.MetricValue;
import metricvalues.MetricvaluesPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metric Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link metricvalues.impl.MetricValueImpl#getMetricID <em>Metric ID</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValueImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetricValueImpl extends MinimalEObjectImpl.Container implements MetricValue {
	/**
	 * The default value of the '{@link #getMetricID() <em>Metric ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetricID()
	 * @generated
	 * @ordered
	 */
	protected static final String METRIC_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMetricID() <em>Metric ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetricID()
	 * @generated
	 * @ordered
	 */
	protected String metricID = METRIC_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final double VALUE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected double value = VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MetricValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetricvaluesPackage.Literals.METRIC_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMetricID() {
		return metricID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetricID(String newMetricID) {
		String oldMetricID = metricID;
		metricID = newMetricID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUE__METRIC_ID, oldMetricID, metricID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(double newValue) {
		double oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUE__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetricvaluesPackage.METRIC_VALUE__METRIC_ID:
				return getMetricID();
			case MetricvaluesPackage.METRIC_VALUE__VALUE:
				return getValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetricvaluesPackage.METRIC_VALUE__METRIC_ID:
				setMetricID((String)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUE__VALUE:
				setValue((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MetricvaluesPackage.METRIC_VALUE__METRIC_ID:
				setMetricID(METRIC_ID_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUE__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MetricvaluesPackage.METRIC_VALUE__METRIC_ID:
				return METRIC_ID_EDEFAULT == null ? metricID != null : !METRIC_ID_EDEFAULT.equals(metricID);
			case MetricvaluesPackage.METRIC_VALUE__VALUE:
				return value != VALUE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (metricID: ");
		result.append(metricID);
		result.append(", value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

} //MetricValueImpl
