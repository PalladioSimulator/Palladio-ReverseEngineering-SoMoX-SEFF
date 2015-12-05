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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.emftext.language.java.types.Type;

import metricvalues.Component;
import metricvalues.MetricvaluesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Component</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link metricvalues.impl.ComponentImpl#getSubComponents <em>Sub Components</em>}</li>
 * <li>{@link metricvalues.impl.ComponentImpl#getName <em>Name</em>}</li>
 * <li>{@link metricvalues.impl.ComponentImpl#getId <em>Id</em>}</li>
 * <li>{@link metricvalues.impl.ComponentImpl#getClasses <em>Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends MinimalEObjectImpl.Container implements Component {
    /**
     * The cached value of the '{@link #getSubComponents() <em>Sub Components</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSubComponents()
     * @generated
     * @ordered
     */
    protected EList<Component> subComponents;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

    /**
     * The cached value of the '{@link #getClasses() <em>Classes</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getClasses()
     * @generated
     * @ordered
     */
    protected EList<Type> classes;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ComponentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricvaluesPackage.Literals.COMPONENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Component> getSubComponents() {
        if (this.subComponents == null) {
            this.subComponents = new EObjectContainmentEList<Component>(Component.class, this,
                    MetricvaluesPackage.COMPONENT__SUB_COMPONENTS);
        }
        return this.subComponents;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setName(final String newName) {
        final String oldName = this.name;
        this.name = newName;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.COMPONENT__NAME, oldName,
                    this.name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setId(final String newId) {
        final String oldId = this.id;
        this.id = newId;
        if (this.eNotificationRequired()) {
            this.eNotify(
                    new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.COMPONENT__ID, oldId, this.id));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Type> getClasses() {
        if (this.classes == null) {
            this.classes = new EObjectResolvingEList<Type>(Type.class, this, MetricvaluesPackage.COMPONENT__CLASSES);
        }
        return this.classes;
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
        case MetricvaluesPackage.COMPONENT__SUB_COMPONENTS:
            return ((InternalEList<?>) this.getSubComponents()).basicRemove(otherEnd, msgs);
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
        case MetricvaluesPackage.COMPONENT__SUB_COMPONENTS:
            return this.getSubComponents();
        case MetricvaluesPackage.COMPONENT__NAME:
            return this.getName();
        case MetricvaluesPackage.COMPONENT__ID:
            return this.getId();
        case MetricvaluesPackage.COMPONENT__CLASSES:
            return this.getClasses();
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
        case MetricvaluesPackage.COMPONENT__SUB_COMPONENTS:
            this.getSubComponents().clear();
            this.getSubComponents().addAll((Collection<? extends Component>) newValue);
            return;
        case MetricvaluesPackage.COMPONENT__NAME:
            this.setName((String) newValue);
            return;
        case MetricvaluesPackage.COMPONENT__ID:
            this.setId((String) newValue);
            return;
        case MetricvaluesPackage.COMPONENT__CLASSES:
            this.getClasses().clear();
            this.getClasses().addAll((Collection<? extends Type>) newValue);
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
        case MetricvaluesPackage.COMPONENT__SUB_COMPONENTS:
            this.getSubComponents().clear();
            return;
        case MetricvaluesPackage.COMPONENT__NAME:
            this.setName(NAME_EDEFAULT);
            return;
        case MetricvaluesPackage.COMPONENT__ID:
            this.setId(ID_EDEFAULT);
            return;
        case MetricvaluesPackage.COMPONENT__CLASSES:
            this.getClasses().clear();
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
        case MetricvaluesPackage.COMPONENT__SUB_COMPONENTS:
            return this.subComponents != null && !this.subComponents.isEmpty();
        case MetricvaluesPackage.COMPONENT__NAME:
            return NAME_EDEFAULT == null ? this.name != null : !NAME_EDEFAULT.equals(this.name);
        case MetricvaluesPackage.COMPONENT__ID:
            return ID_EDEFAULT == null ? this.id != null : !ID_EDEFAULT.equals(this.id);
        case MetricvaluesPackage.COMPONENT__CLASSES:
            return this.classes != null && !this.classes.isEmpty();
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
        result.append(" (name: ");
        result.append(this.name);
        result.append(", id: ");
        result.append(this.id);
        result.append(')');
        return result.toString();
    }

} // ComponentImpl
