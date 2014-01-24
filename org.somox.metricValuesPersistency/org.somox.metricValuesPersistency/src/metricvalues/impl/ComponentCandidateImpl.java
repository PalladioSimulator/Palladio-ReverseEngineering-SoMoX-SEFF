/**
 */
package metricvalues.impl;

import java.util.Collection;

import metricvalues.Component;
import metricvalues.ComponentCandidate;
import metricvalues.MetricValue;
import metricvalues.MetricvaluesPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Candidate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link metricvalues.impl.ComponentCandidateImpl#getMetricValuesList <em>Metric Values</em>}</li>
 *   <li>{@link metricvalues.impl.ComponentCandidateImpl#getFirstComponent <em>First Component</em>}</li>
 *   <li>{@link metricvalues.impl.ComponentCandidateImpl#getSecondComponent <em>Second Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentCandidateImpl extends EObjectImpl implements ComponentCandidate {
	/**
	 * The cached value of the '{@link #getMetricValuesList() <em>Metric Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetricValuesList()
	 * @generated
	 * @ordered
	 */
	protected EList<MetricValue> metricValues;

	/**
	 * The empty value for the '{@link #getMetricValues() <em>Metric Values</em>}' array accessor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetricValues()
	 * @generated
	 * @ordered
	 */
	protected static final MetricValue[] METRIC_VALUES_EEMPTY_ARRAY = new MetricValue [0];

	/**
	 * The cached value of the '{@link #getFirstComponent() <em>First Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstComponent()
	 * @generated
	 * @ordered
	 */
	protected Component firstComponent;

	/**
	 * The cached value of the '{@link #getSecondComponent() <em>Second Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecondComponent()
	 * @generated
	 * @ordered
	 */
	protected Component secondComponent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentCandidateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetricvaluesPackage.Literals.COMPONENT_CANDIDATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetricValue[] getMetricValues() {
		if (metricValues == null || metricValues.isEmpty()) return METRIC_VALUES_EEMPTY_ARRAY;
		BasicEList<MetricValue> list = (BasicEList<MetricValue>)metricValues;
		list.shrink();
		return (MetricValue[])list.data();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetricValue getMetricValues(int index) {
		return getMetricValuesList().get(index);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMetricValuesLength() {
		return metricValues == null ? 0 : metricValues.size();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetricValues(MetricValue[] newMetricValues) {
		((BasicEList<MetricValue>)getMetricValuesList()).setData(newMetricValues.length, newMetricValues);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetricValues(int index, MetricValue element) {
		getMetricValuesList().set(index, element);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetricValue> getMetricValuesList() {
		if (metricValues == null) {
			metricValues = new EObjectContainmentEList<MetricValue>(MetricValue.class, this, MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES);
		}
		return metricValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component getFirstComponent() {
		if (firstComponent != null && firstComponent.eIsProxy()) {
			InternalEObject oldFirstComponent = (InternalEObject)firstComponent;
			firstComponent = (Component)eResolveProxy(oldFirstComponent);
			if (firstComponent != oldFirstComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT, oldFirstComponent, firstComponent));
			}
		}
		return firstComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component basicGetFirstComponent() {
		return firstComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirstComponent(Component newFirstComponent) {
		Component oldFirstComponent = firstComponent;
		firstComponent = newFirstComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT, oldFirstComponent, firstComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component getSecondComponent() {
		if (secondComponent != null && secondComponent.eIsProxy()) {
			InternalEObject oldSecondComponent = (InternalEObject)secondComponent;
			secondComponent = (Component)eResolveProxy(oldSecondComponent);
			if (secondComponent != oldSecondComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT, oldSecondComponent, secondComponent));
			}
		}
		return secondComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component basicGetSecondComponent() {
		return secondComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecondComponent(Component newSecondComponent) {
		Component oldSecondComponent = secondComponent;
		secondComponent = newSecondComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT, oldSecondComponent, secondComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES:
				return ((InternalEList<?>)getMetricValuesList()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES:
				return getMetricValuesList();
			case MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT:
				if (resolve) return getFirstComponent();
				return basicGetFirstComponent();
			case MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT:
				if (resolve) return getSecondComponent();
				return basicGetSecondComponent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES:
				getMetricValuesList().clear();
				getMetricValuesList().addAll((Collection<? extends MetricValue>)newValue);
				return;
			case MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT:
				setFirstComponent((Component)newValue);
				return;
			case MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT:
				setSecondComponent((Component)newValue);
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
			case MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES:
				getMetricValuesList().clear();
				return;
			case MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT:
				setFirstComponent((Component)null);
				return;
			case MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT:
				setSecondComponent((Component)null);
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
			case MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES:
				return metricValues != null && !metricValues.isEmpty();
			case MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT:
				return firstComponent != null;
			case MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT:
				return secondComponent != null;
		}
		return super.eIsSet(featureID);
	}

} //ComponentCandidateImpl
