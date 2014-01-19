/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metricvalues.impl;

import java.util.Collection;

import metricvalues.Component;
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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link metricvalues.impl.ComponentImpl#getSubComponentsList <em>Sub Components</em>}</li>
 *   <li>{@link metricvalues.impl.ComponentImpl#getName <em>Name</em>}</li>
 *   <li>{@link metricvalues.impl.ComponentImpl#getId <em>Id</em>}</li>
 *   <li>{@link metricvalues.impl.ComponentImpl#getClassesList <em>Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends EObjectImpl implements Component
{
   /**
	 * The cached value of the '{@link #getSubComponentsList() <em>Sub Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getSubComponentsList()
	 * @generated
	 * @ordered
	 */
   protected EList<Component> subComponents;

   /**
	 * The empty value for the '{@link #getSubComponents() <em>Sub Components</em>}' array accessor.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getSubComponents()
	 * @generated
	 * @ordered
	 */
   protected static final Component[] SUB_COMPONENTS_EEMPTY_ARRAY = new Component [0];

   /**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
   protected static final String NAME_EDEFAULT = null;

   /**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
   protected String name = NAME_EDEFAULT;

   /**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
   protected static final String ID_EDEFAULT = null;

   /**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
   protected String id = ID_EDEFAULT;

   /**
	 * The cached value of the '{@link #getClassesList() <em>Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getClassesList()
	 * @generated
	 * @ordered
	 */
   protected EList<Type> classes;

   /**
	 * The empty value for the '{@link #getClasses() <em>Classes</em>}' array accessor.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getClasses()
	 * @generated
	 * @ordered
	 */
   protected static final Type[] CLASSES_EEMPTY_ARRAY = new Type [0];

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected ComponentImpl()
   {
		super();
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   @Override
   protected EClass eStaticClass()
   {
		return MetricvaluesPackage.Literals.COMPONENT;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public Component[] getSubComponents()
   {
		if (subComponents == null || subComponents.isEmpty()) return SUB_COMPONENTS_EEMPTY_ARRAY;
		BasicEList<Component> list = (BasicEList<Component>)subComponents;
		list.shrink();
		return (Component[])list.data();
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public Component getSubComponents(int index)
   {
		return getSubComponentsList().get(index);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public int getSubComponentsLength()
   {
		return subComponents == null ? 0 : subComponents.size();
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setSubComponents(Component[] newSubComponents)
   {
		((BasicEList<Component>)getSubComponentsList()).setData(newSubComponents.length, newSubComponents);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setSubComponents(int index, Component element)
   {
		getSubComponentsList().set(index, element);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EList<Component> getSubComponentsList()
   {
		if (subComponents == null) {
			subComponents = new EObjectContainmentEList<Component>(Component.class, this, MetricvaluesPackage.COMPONENT__SUB_COMPONENTS);
		}
		return subComponents;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getName()
   {
		return name;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setName(String newName)
   {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.COMPONENT__NAME, oldName, name));
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getId()
   {
		return id;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setId(String newId)
   {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.COMPONENT__ID, oldId, id));
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public Type[] getClasses()
   {
		if (classes == null || classes.isEmpty()) return CLASSES_EEMPTY_ARRAY;
		BasicEList<Type> list = (BasicEList<Type>)classes;
		list.shrink();
		return (Type[])list.data();
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public Type getClasses(int index)
   {
		return getClassesList().get(index);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public int getClassesLength()
   {
		return classes == null ? 0 : classes.size();
	}

   /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClasses(Type[] newClasses) {
		((BasicEList<Type>)getClassesList()).setData(newClasses.length, newClasses);
	}

			/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClasses(int index, Type element) {
		getClassesList().set(index, element);
	}

			/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EList<Type> getClassesList()
   {
		if (classes == null) {
			classes = new EObjectResolvingEList<Type>(Type.class, this, MetricvaluesPackage.COMPONENT__CLASSES);
		}
		return classes;
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
		switch (featureID) {
			case MetricvaluesPackage.COMPONENT__SUB_COMPONENTS:
				return ((InternalEList<?>)getSubComponentsList()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
		switch (featureID) {
			case MetricvaluesPackage.COMPONENT__SUB_COMPONENTS:
				return getSubComponentsList();
			case MetricvaluesPackage.COMPONENT__NAME:
				return getName();
			case MetricvaluesPackage.COMPONENT__ID:
				return getId();
			case MetricvaluesPackage.COMPONENT__CLASSES:
				return getClassesList();
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
   public void eSet(int featureID, Object newValue)
   {
		switch (featureID) {
			case MetricvaluesPackage.COMPONENT__SUB_COMPONENTS:
				getSubComponentsList().clear();
				getSubComponentsList().addAll((Collection<? extends Component>)newValue);
				return;
			case MetricvaluesPackage.COMPONENT__NAME:
				setName((String)newValue);
				return;
			case MetricvaluesPackage.COMPONENT__ID:
				setId((String)newValue);
				return;
			case MetricvaluesPackage.COMPONENT__CLASSES:
				getClassesList().clear();
				getClassesList().addAll((Collection<? extends Type>)newValue);
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
   public void eUnset(int featureID)
   {
		switch (featureID) {
			case MetricvaluesPackage.COMPONENT__SUB_COMPONENTS:
				getSubComponentsList().clear();
				return;
			case MetricvaluesPackage.COMPONENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MetricvaluesPackage.COMPONENT__ID:
				setId(ID_EDEFAULT);
				return;
			case MetricvaluesPackage.COMPONENT__CLASSES:
				getClassesList().clear();
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
   public boolean eIsSet(int featureID)
   {
		switch (featureID) {
			case MetricvaluesPackage.COMPONENT__SUB_COMPONENTS:
				return subComponents != null && !subComponents.isEmpty();
			case MetricvaluesPackage.COMPONENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MetricvaluesPackage.COMPONENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MetricvaluesPackage.COMPONENT__CLASSES:
				return classes != null && !classes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

   /**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   @Override
   public String toString()
   {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ComponentImpl
