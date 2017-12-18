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
import org.somox.metrics.dsl.metricDSL.InternalMetric;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.MetricDefinition;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Internal Metric</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.InternalMetricImpl#getShortName
 * <em>Short Name</em>}</li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.InternalMetricImpl#getDescription
 * <em>Description</em>}</li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.InternalMetricImpl#getParameter
 * <em>Parameter</em>}</li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.InternalMetricImpl#getDefinition
 * <em>Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InternalMetricImpl extends MetricImpl implements InternalMetric {
    /**
     * The default value of the '{@link #getShortName() <em>Short Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getShortName()
     * @generated
     * @ordered
     */
    protected static final String SHORT_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getShortName() <em>Short Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getShortName()
     * @generated
     * @ordered
     */
    protected String shortName = SHORT_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getParameter() <em>Parameter</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getParameter()
     * @generated
     * @ordered
     */
    protected EList<org.somox.metrics.dsl.metricDSL.Number> parameter;

    /**
     * The cached value of the '{@link #getDefinition() <em>Definition</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDefinition()
     * @generated
     * @ordered
     */
    protected MetricDefinition definition;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InternalMetricImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricDSLPackage.Literals.INTERNAL_METRIC;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getShortName() {
        return this.shortName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setShortName(final String newShortName) {
        final String oldShortName = this.shortName;
        this.shortName = newShortName;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.INTERNAL_METRIC__SHORT_NAME,
                    oldShortName, this.shortName));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDescription(final String newDescription) {
        final String oldDescription = this.description;
        this.description = newDescription;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.INTERNAL_METRIC__DESCRIPTION,
                    oldDescription, this.description));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<org.somox.metrics.dsl.metricDSL.Number> getParameter() {
        if (this.parameter == null) {
            this.parameter = new EObjectContainmentEList<org.somox.metrics.dsl.metricDSL.Number>(
                    org.somox.metrics.dsl.metricDSL.Number.class, this, MetricDSLPackage.INTERNAL_METRIC__PARAMETER);
        }
        return this.parameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetricDefinition getDefinition() {
        return this.definition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDefinition(final MetricDefinition newDefinition, NotificationChain msgs) {
        final MetricDefinition oldDefinition = this.definition;
        this.definition = newDefinition;
        if (this.eNotificationRequired()) {
            final ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    MetricDSLPackage.INTERNAL_METRIC__DEFINITION, oldDefinition, newDefinition);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDefinition(final MetricDefinition newDefinition) {
        if (newDefinition != this.definition) {
            NotificationChain msgs = null;
            if (this.definition != null) {
                msgs = ((InternalEObject) this.definition).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - MetricDSLPackage.INTERNAL_METRIC__DEFINITION, null, msgs);
            }
            if (newDefinition != null) {
                msgs = ((InternalEObject) newDefinition).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - MetricDSLPackage.INTERNAL_METRIC__DEFINITION, null, msgs);
            }
            msgs = this.basicSetDefinition(newDefinition, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.INTERNAL_METRIC__DEFINITION,
                    newDefinition, newDefinition));
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
        case MetricDSLPackage.INTERNAL_METRIC__PARAMETER:
            return ((InternalEList<?>) this.getParameter()).basicRemove(otherEnd, msgs);
        case MetricDSLPackage.INTERNAL_METRIC__DEFINITION:
            return this.basicSetDefinition(null, msgs);
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
        case MetricDSLPackage.INTERNAL_METRIC__SHORT_NAME:
            return this.getShortName();
        case MetricDSLPackage.INTERNAL_METRIC__DESCRIPTION:
            return this.getDescription();
        case MetricDSLPackage.INTERNAL_METRIC__PARAMETER:
            return this.getParameter();
        case MetricDSLPackage.INTERNAL_METRIC__DEFINITION:
            return this.getDefinition();
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
        case MetricDSLPackage.INTERNAL_METRIC__SHORT_NAME:
            this.setShortName((String) newValue);
            return;
        case MetricDSLPackage.INTERNAL_METRIC__DESCRIPTION:
            this.setDescription((String) newValue);
            return;
        case MetricDSLPackage.INTERNAL_METRIC__PARAMETER:
            this.getParameter().clear();
            this.getParameter().addAll((Collection<? extends org.somox.metrics.dsl.metricDSL.Number>) newValue);
            return;
        case MetricDSLPackage.INTERNAL_METRIC__DEFINITION:
            this.setDefinition((MetricDefinition) newValue);
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
        case MetricDSLPackage.INTERNAL_METRIC__SHORT_NAME:
            this.setShortName(SHORT_NAME_EDEFAULT);
            return;
        case MetricDSLPackage.INTERNAL_METRIC__DESCRIPTION:
            this.setDescription(DESCRIPTION_EDEFAULT);
            return;
        case MetricDSLPackage.INTERNAL_METRIC__PARAMETER:
            this.getParameter().clear();
            return;
        case MetricDSLPackage.INTERNAL_METRIC__DEFINITION:
            this.setDefinition((MetricDefinition) null);
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
        case MetricDSLPackage.INTERNAL_METRIC__SHORT_NAME:
            return SHORT_NAME_EDEFAULT == null ? this.shortName != null : !SHORT_NAME_EDEFAULT.equals(this.shortName);
        case MetricDSLPackage.INTERNAL_METRIC__DESCRIPTION:
            return DESCRIPTION_EDEFAULT == null ? this.description != null
                    : !DESCRIPTION_EDEFAULT.equals(this.description);
        case MetricDSLPackage.INTERNAL_METRIC__PARAMETER:
            return this.parameter != null && !this.parameter.isEmpty();
        case MetricDSLPackage.INTERNAL_METRIC__DEFINITION:
            return this.definition != null;
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
        result.append(" (shortName: ");
        result.append(this.shortName);
        result.append(", description: ");
        result.append(this.description);
        result.append(')');
        return result.toString();
    }

} // InternalMetricImpl
