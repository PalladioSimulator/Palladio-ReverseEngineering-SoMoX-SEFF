/**
 */
package metricvalues.impl;

import java.util.Collection;

import metricvalues.Component;
import metricvalues.ComponentCandidate;
import metricvalues.Iteration;
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
 * An implementation of the model object '<em><b>Iteration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link metricvalues.impl.IterationImpl#getComponentCandidatesList <em>Component Candidates</em>}</li>
 *   <li>{@link metricvalues.impl.IterationImpl#getComponentsList <em>Components</em>}</li>
 *   <li>{@link metricvalues.impl.IterationImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link metricvalues.impl.IterationImpl#getCurCompThreshold <em>Cur Comp Threshold</em>}</li>
 *   <li>{@link metricvalues.impl.IterationImpl#getCurMergeThreshold <em>Cur Merge Threshold</em>}</li>
 *   <li>{@link metricvalues.impl.IterationImpl#isIsMergeIteration <em>Is Merge Iteration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IterationImpl extends EObjectImpl implements Iteration {
	/**
	 * The cached value of the '{@link #getComponentCandidatesList() <em>Component Candidates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentCandidatesList()
	 * @generated
	 * @ordered
	 */
	protected EList<ComponentCandidate> componentCandidates;

	/**
	 * The empty value for the '{@link #getComponentCandidates() <em>Component Candidates</em>}' array accessor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentCandidates()
	 * @generated
	 * @ordered
	 */
	protected static final ComponentCandidate[] COMPONENT_CANDIDATES_EEMPTY_ARRAY = new ComponentCandidate [0];

	/**
	 * The cached value of the '{@link #getComponentsList() <em>Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentsList()
	 * @generated
	 * @ordered
	 */
	protected EList<Component> components;

	/**
	 * The empty value for the '{@link #getComponents() <em>Components</em>}' array accessor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected static final Component[] COMPONENTS_EEMPTY_ARRAY = new Component [0];

	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected int number = NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getCurCompThreshold() <em>Cur Comp Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurCompThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final double CUR_COMP_THRESHOLD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getCurCompThreshold() <em>Cur Comp Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurCompThreshold()
	 * @generated
	 * @ordered
	 */
	protected double curCompThreshold = CUR_COMP_THRESHOLD_EDEFAULT;

	/**
	 * The default value of the '{@link #getCurMergeThreshold() <em>Cur Merge Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurMergeThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final double CUR_MERGE_THRESHOLD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getCurMergeThreshold() <em>Cur Merge Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurMergeThreshold()
	 * @generated
	 * @ordered
	 */
	protected double curMergeThreshold = CUR_MERGE_THRESHOLD_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsMergeIteration() <em>Is Merge Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMergeIteration()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_MERGE_ITERATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsMergeIteration() <em>Is Merge Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMergeIteration()
	 * @generated
	 * @ordered
	 */
	protected boolean isMergeIteration = IS_MERGE_ITERATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IterationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetricvaluesPackage.Literals.ITERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentCandidate[] getComponentCandidates() {
		if (componentCandidates == null || componentCandidates.isEmpty()) return COMPONENT_CANDIDATES_EEMPTY_ARRAY;
		BasicEList<ComponentCandidate> list = (BasicEList<ComponentCandidate>)componentCandidates;
		list.shrink();
		return (ComponentCandidate[])list.data();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentCandidate getComponentCandidates(int index) {
		return getComponentCandidatesList().get(index);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getComponentCandidatesLength() {
		return componentCandidates == null ? 0 : componentCandidates.size();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentCandidates(ComponentCandidate[] newComponentCandidates) {
		((BasicEList<ComponentCandidate>)getComponentCandidatesList()).setData(newComponentCandidates.length, newComponentCandidates);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentCandidates(int index, ComponentCandidate element) {
		getComponentCandidatesList().set(index, element);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ComponentCandidate> getComponentCandidatesList() {
		if (componentCandidates == null) {
			componentCandidates = new EObjectContainmentEList<ComponentCandidate>(ComponentCandidate.class, this, MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES);
		}
		return componentCandidates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component[] getComponents() {
		if (components == null || components.isEmpty()) return COMPONENTS_EEMPTY_ARRAY;
		BasicEList<Component> list = (BasicEList<Component>)components;
		list.shrink();
		return (Component[])list.data();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component getComponents(int index) {
		return getComponentsList().get(index);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getComponentsLength() {
		return components == null ? 0 : components.size();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponents(Component[] newComponents) {
		((BasicEList<Component>)getComponentsList()).setData(newComponents.length, newComponents);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponents(int index, Component element) {
		getComponentsList().set(index, element);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Component> getComponentsList() {
		if (components == null) {
			components = new EObjectContainmentEList<Component>(Component.class, this, MetricvaluesPackage.ITERATION__COMPONENTS);
		}
		return components;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(int newNumber) {
		int oldNumber = number;
		number = newNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.ITERATION__NUMBER, oldNumber, number));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getCurCompThreshold() {
		return curCompThreshold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurCompThreshold(double newCurCompThreshold) {
		double oldCurCompThreshold = curCompThreshold;
		curCompThreshold = newCurCompThreshold;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.ITERATION__CUR_COMP_THRESHOLD, oldCurCompThreshold, curCompThreshold));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getCurMergeThreshold() {
		return curMergeThreshold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurMergeThreshold(double newCurMergeThreshold) {
		double oldCurMergeThreshold = curMergeThreshold;
		curMergeThreshold = newCurMergeThreshold;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.ITERATION__CUR_MERGE_THRESHOLD, oldCurMergeThreshold, curMergeThreshold));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsMergeIteration() {
		return isMergeIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsMergeIteration(boolean newIsMergeIteration) {
		boolean oldIsMergeIteration = isMergeIteration;
		isMergeIteration = newIsMergeIteration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.ITERATION__IS_MERGE_ITERATION, oldIsMergeIteration, isMergeIteration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES:
				return ((InternalEList<?>)getComponentCandidatesList()).basicRemove(otherEnd, msgs);
			case MetricvaluesPackage.ITERATION__COMPONENTS:
				return ((InternalEList<?>)getComponentsList()).basicRemove(otherEnd, msgs);
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
			case MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES:
				return getComponentCandidatesList();
			case MetricvaluesPackage.ITERATION__COMPONENTS:
				return getComponentsList();
			case MetricvaluesPackage.ITERATION__NUMBER:
				return getNumber();
			case MetricvaluesPackage.ITERATION__CUR_COMP_THRESHOLD:
				return getCurCompThreshold();
			case MetricvaluesPackage.ITERATION__CUR_MERGE_THRESHOLD:
				return getCurMergeThreshold();
			case MetricvaluesPackage.ITERATION__IS_MERGE_ITERATION:
				return isIsMergeIteration();
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
			case MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES:
				getComponentCandidatesList().clear();
				getComponentCandidatesList().addAll((Collection<? extends ComponentCandidate>)newValue);
				return;
			case MetricvaluesPackage.ITERATION__COMPONENTS:
				getComponentsList().clear();
				getComponentsList().addAll((Collection<? extends Component>)newValue);
				return;
			case MetricvaluesPackage.ITERATION__NUMBER:
				setNumber((Integer)newValue);
				return;
			case MetricvaluesPackage.ITERATION__CUR_COMP_THRESHOLD:
				setCurCompThreshold((Double)newValue);
				return;
			case MetricvaluesPackage.ITERATION__CUR_MERGE_THRESHOLD:
				setCurMergeThreshold((Double)newValue);
				return;
			case MetricvaluesPackage.ITERATION__IS_MERGE_ITERATION:
				setIsMergeIteration((Boolean)newValue);
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
			case MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES:
				getComponentCandidatesList().clear();
				return;
			case MetricvaluesPackage.ITERATION__COMPONENTS:
				getComponentsList().clear();
				return;
			case MetricvaluesPackage.ITERATION__NUMBER:
				setNumber(NUMBER_EDEFAULT);
				return;
			case MetricvaluesPackage.ITERATION__CUR_COMP_THRESHOLD:
				setCurCompThreshold(CUR_COMP_THRESHOLD_EDEFAULT);
				return;
			case MetricvaluesPackage.ITERATION__CUR_MERGE_THRESHOLD:
				setCurMergeThreshold(CUR_MERGE_THRESHOLD_EDEFAULT);
				return;
			case MetricvaluesPackage.ITERATION__IS_MERGE_ITERATION:
				setIsMergeIteration(IS_MERGE_ITERATION_EDEFAULT);
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
			case MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES:
				return componentCandidates != null && !componentCandidates.isEmpty();
			case MetricvaluesPackage.ITERATION__COMPONENTS:
				return components != null && !components.isEmpty();
			case MetricvaluesPackage.ITERATION__NUMBER:
				return number != NUMBER_EDEFAULT;
			case MetricvaluesPackage.ITERATION__CUR_COMP_THRESHOLD:
				return curCompThreshold != CUR_COMP_THRESHOLD_EDEFAULT;
			case MetricvaluesPackage.ITERATION__CUR_MERGE_THRESHOLD:
				return curMergeThreshold != CUR_MERGE_THRESHOLD_EDEFAULT;
			case MetricvaluesPackage.ITERATION__IS_MERGE_ITERATION:
				return isMergeIteration != IS_MERGE_ITERATION_EDEFAULT;
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
		result.append(" (number: ");
		result.append(number);
		result.append(", curCompThreshold: ");
		result.append(curCompThreshold);
		result.append(", curMergeThreshold: ");
		result.append(curMergeThreshold);
		result.append(", isMergeIteration: ");
		result.append(isMergeIteration);
		result.append(')');
		return result.toString();
	}

} //IterationImpl
