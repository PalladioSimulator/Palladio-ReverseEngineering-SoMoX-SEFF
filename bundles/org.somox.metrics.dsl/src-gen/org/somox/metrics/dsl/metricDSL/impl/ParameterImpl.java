/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.Parameter;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Parameter</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.ParameterImpl#getShortname <em>Shortname</em>}
 * </li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.ParameterImpl#getDescription <em>Description</em>
 * }</li>
 * <li>{@link org.somox.metrics.dsl.metricDSL.impl.ParameterImpl#getDefaultValue
 * <em>Default Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterImpl extends NumberImpl implements Parameter {
    /**
     * The default value of the '{@link #getShortname() <em>Shortname</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getShortname()
     * @generated
     * @ordered
     */
    protected static final String SHORTNAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getShortname() <em>Shortname</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getShortname()
     * @generated
     * @ordered
     */
    protected String shortname = SHORTNAME_EDEFAULT;

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
     * The default value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDefaultValue()
     * @generated
     * @ordered
     */
    protected static final double DEFAULT_VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDefaultValue()
     * @generated
     * @ordered
     */
    protected double defaultValue = DEFAULT_VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricDSLPackage.Literals.PARAMETER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getShortname() {
        return this.shortname;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setShortname(final String newShortname) {
        final String oldShortname = this.shortname;
        this.shortname = newShortname;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.PARAMETER__SHORTNAME,
                    oldShortname, this.shortname));
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
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.PARAMETER__DESCRIPTION,
                    oldDescription, this.description));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDefaultValue(final double newDefaultValue) {
        final double oldDefaultValue = this.defaultValue;
        this.defaultValue = newDefaultValue;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.PARAMETER__DEFAULT_VALUE,
                    oldDefaultValue, this.defaultValue));
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
        case MetricDSLPackage.PARAMETER__SHORTNAME:
            return this.getShortname();
        case MetricDSLPackage.PARAMETER__DESCRIPTION:
            return this.getDescription();
        case MetricDSLPackage.PARAMETER__DEFAULT_VALUE:
            return this.getDefaultValue();
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
        case MetricDSLPackage.PARAMETER__SHORTNAME:
            this.setShortname((String) newValue);
            return;
        case MetricDSLPackage.PARAMETER__DESCRIPTION:
            this.setDescription((String) newValue);
            return;
        case MetricDSLPackage.PARAMETER__DEFAULT_VALUE:
            this.setDefaultValue((Double) newValue);
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
        case MetricDSLPackage.PARAMETER__SHORTNAME:
            this.setShortname(SHORTNAME_EDEFAULT);
            return;
        case MetricDSLPackage.PARAMETER__DESCRIPTION:
            this.setDescription(DESCRIPTION_EDEFAULT);
            return;
        case MetricDSLPackage.PARAMETER__DEFAULT_VALUE:
            this.setDefaultValue(DEFAULT_VALUE_EDEFAULT);
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
        case MetricDSLPackage.PARAMETER__SHORTNAME:
            return SHORTNAME_EDEFAULT == null ? this.shortname != null : !SHORTNAME_EDEFAULT.equals(this.shortname);
        case MetricDSLPackage.PARAMETER__DESCRIPTION:
            return DESCRIPTION_EDEFAULT == null ? this.description != null
                    : !DESCRIPTION_EDEFAULT.equals(this.description);
        case MetricDSLPackage.PARAMETER__DEFAULT_VALUE:
            return this.defaultValue != DEFAULT_VALUE_EDEFAULT;
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
        result.append(" (shortname: ");
        result.append(this.shortname);
        result.append(", description: ");
        result.append(this.description);
        result.append(", defaultValue: ");
        result.append(this.defaultValue);
        result.append(')');
        return result.toString();
    }

} // ParameterImpl
