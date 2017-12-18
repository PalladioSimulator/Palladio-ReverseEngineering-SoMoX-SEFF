/**
 */
package metricvalues.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import metricvalues.Component;
import metricvalues.ComponentCandidate;
import metricvalues.MetricValue;
import metricvalues.MetricvaluesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Component Candidate</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link metricvalues.impl.ComponentCandidateImpl#getMetricValues <em>Metric Values</em>}</li>
 * <li>{@link metricvalues.impl.ComponentCandidateImpl#getFirstComponent <em>First Component</em>}
 * </li>
 * <li>{@link metricvalues.impl.ComponentCandidateImpl#getSecondComponent <em>Second Component</em>}
 * </li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentCandidateImpl extends MinimalEObjectImpl.Container implements ComponentCandidate {
    /**
     * The cached value of the '{@link #getMetricValues() <em>Metric Values</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMetricValues()
     * @generated
     * @ordered
     */
    protected EList<MetricValue> metricValues;

    /**
     * The cached value of the '{@link #getFirstComponent() <em>First Component</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFirstComponent()
     * @generated
     * @ordered
     */
    protected Component firstComponent;

    /**
     * The cached value of the '{@link #getSecondComponent() <em>Second Component</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSecondComponent()
     * @generated
     * @ordered
     */
    protected Component secondComponent;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ComponentCandidateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricvaluesPackage.Literals.COMPONENT_CANDIDATE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<MetricValue> getMetricValues() {
        if (this.metricValues == null) {
            this.metricValues = new EObjectContainmentEList<MetricValue>(MetricValue.class, this,
                    MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES);
        }
        return this.metricValues;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Component getFirstComponent() {
        if (this.firstComponent != null && this.firstComponent.eIsProxy()) {
            final InternalEObject oldFirstComponent = (InternalEObject) this.firstComponent;
            this.firstComponent = (Component) this.eResolveProxy(oldFirstComponent);
            if (this.firstComponent != oldFirstComponent) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT, oldFirstComponent,
                            this.firstComponent));
                }
            }
        }
        return this.firstComponent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Component basicGetFirstComponent() {
        return this.firstComponent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFirstComponent(final Component newFirstComponent) {
        final Component oldFirstComponent = this.firstComponent;
        this.firstComponent = newFirstComponent;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT, oldFirstComponent, this.firstComponent));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Component getSecondComponent() {
        if (this.secondComponent != null && this.secondComponent.eIsProxy()) {
            final InternalEObject oldSecondComponent = (InternalEObject) this.secondComponent;
            this.secondComponent = (Component) this.eResolveProxy(oldSecondComponent);
            if (this.secondComponent != oldSecondComponent) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT, oldSecondComponent,
                            this.secondComponent));
                }
            }
        }
        return this.secondComponent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Component basicGetSecondComponent() {
        return this.secondComponent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSecondComponent(final Component newSecondComponent) {
        final Component oldSecondComponent = this.secondComponent;
        this.secondComponent = newSecondComponent;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT, oldSecondComponent,
                    this.secondComponent));
        }
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
        case MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES:
            return ((InternalEList<?>) this.getMetricValues()).basicRemove(otherEnd, msgs);
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
        case MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES:
            return this.getMetricValues();
        case MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT:
            if (resolve) {
                return this.getFirstComponent();
            }
            return this.basicGetFirstComponent();
        case MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT:
            if (resolve) {
                return this.getSecondComponent();
            }
            return this.basicGetSecondComponent();
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
        case MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES:
            this.getMetricValues().clear();
            this.getMetricValues().addAll((Collection<? extends MetricValue>) newValue);
            return;
        case MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT:
            this.setFirstComponent((Component) newValue);
            return;
        case MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT:
            this.setSecondComponent((Component) newValue);
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
        case MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES:
            this.getMetricValues().clear();
            return;
        case MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT:
            this.setFirstComponent((Component) null);
            return;
        case MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT:
            this.setSecondComponent((Component) null);
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
        case MetricvaluesPackage.COMPONENT_CANDIDATE__METRIC_VALUES:
            return this.metricValues != null && !this.metricValues.isEmpty();
        case MetricvaluesPackage.COMPONENT_CANDIDATE__FIRST_COMPONENT:
            return this.firstComponent != null;
        case MetricvaluesPackage.COMPONENT_CANDIDATE__SECOND_COMPONENT:
            return this.secondComponent != null;
        }
        return super.eIsSet(featureID);
    }

} // ComponentCandidateImpl
