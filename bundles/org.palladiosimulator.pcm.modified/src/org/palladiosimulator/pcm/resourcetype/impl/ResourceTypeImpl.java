/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.resourcetype.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.pcm.core.entity.EntityPackage;
import org.palladiosimulator.pcm.core.entity.ResourceInterfaceProvidingEntity;
import org.palladiosimulator.pcm.core.entity.ResourceProvidedRole;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;
import org.palladiosimulator.pcm.resourcetype.ResourceType;
import org.palladiosimulator.pcm.resourcetype.ResourcetypePackage;

import de.uka.ipd.sdq.units.Unit;
import de.uka.ipd.sdq.units.UnitCarryingElement;
import de.uka.ipd.sdq.units.UnitsPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Resource Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.pcm.resourcetype.impl.ResourceTypeImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.palladiosimulator.pcm.resourcetype.impl.ResourceTypeImpl#getResourceProvidedRoles__ResourceInterfaceProvidingEntity <em>Resource Provided Roles Resource Interface Providing Entity</em>}</li>
 *   <li>{@link org.palladiosimulator.pcm.resourcetype.impl.ResourceTypeImpl#getResourceRepository_ResourceType <em>Resource Repository Resource Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ResourceTypeImpl extends EntityImpl implements ResourceType {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2005-2015 by palladiosimulator.org";

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResourcetypePackage.Literals.RESOURCE_TYPE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Unit getUnit() {
		return (Unit) eDynamicGet(ResourcetypePackage.RESOURCE_TYPE__UNIT,
				UnitsPackage.Literals.UNIT_CARRYING_ELEMENT__UNIT, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUnit(Unit newUnit, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject) newUnit, ResourcetypePackage.RESOURCE_TYPE__UNIT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnit(Unit newUnit) {
		eDynamicSet(ResourcetypePackage.RESOURCE_TYPE__UNIT, UnitsPackage.Literals.UNIT_CARRYING_ELEMENT__UNIT,
				newUnit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicUnsetUnit(NotificationChain msgs) {
		return eDynamicInverseRemove((InternalEObject) getUnit(), ResourcetypePackage.RESOURCE_TYPE__UNIT, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetUnit() {
		eDynamicUnset(ResourcetypePackage.RESOURCE_TYPE__UNIT, UnitsPackage.Literals.UNIT_CARRYING_ELEMENT__UNIT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetUnit() {
		return eDynamicIsSet(ResourcetypePackage.RESOURCE_TYPE__UNIT,
				UnitsPackage.Literals.UNIT_CARRYING_ELEMENT__UNIT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public EList<ResourceProvidedRole> getResourceProvidedRoles__ResourceInterfaceProvidingEntity() {
		return (EList<ResourceProvidedRole>) eDynamicGet(
				ResourcetypePackage.RESOURCE_TYPE__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY,
				EntityPackage.Literals.RESOURCE_INTERFACE_PROVIDING_ENTITY__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY,
				true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceRepository getResourceRepository_ResourceType() {
		return (ResourceRepository) eDynamicGet(ResourcetypePackage.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE,
				ResourcetypePackage.Literals.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResourceRepository_ResourceType(
			ResourceRepository newResourceRepository_ResourceType, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newResourceRepository_ResourceType,
				ResourcetypePackage.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResourceRepository_ResourceType(ResourceRepository newResourceRepository_ResourceType) {
		eDynamicSet(ResourcetypePackage.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE,
				ResourcetypePackage.Literals.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE,
				newResourceRepository_ResourceType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getResourceProvidedRoles__ResourceInterfaceProvidingEntity())
					.basicAdd(otherEnd, msgs);
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetResourceRepository_ResourceType((ResourceRepository) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ResourcetypePackage.RESOURCE_TYPE__UNIT:
			return basicUnsetUnit(msgs);
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY:
			return ((InternalEList<?>) getResourceProvidedRoles__ResourceInterfaceProvidingEntity())
					.basicRemove(otherEnd, msgs);
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE:
			return basicSetResourceRepository_ResourceType(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE:
			return eInternalContainer().eInverseRemove(this,
					ResourcetypePackage.RESOURCE_REPOSITORY__AVAILABLE_RESOURCE_TYPES_RESOURCE_REPOSITORY,
					ResourceRepository.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ResourcetypePackage.RESOURCE_TYPE__UNIT:
			return getUnit();
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY:
			return getResourceProvidedRoles__ResourceInterfaceProvidingEntity();
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE:
			return getResourceRepository_ResourceType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ResourcetypePackage.RESOURCE_TYPE__UNIT:
			setUnit((Unit) newValue);
			return;
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY:
			getResourceProvidedRoles__ResourceInterfaceProvidingEntity().clear();
			getResourceProvidedRoles__ResourceInterfaceProvidingEntity()
					.addAll((Collection<? extends ResourceProvidedRole>) newValue);
			return;
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE:
			setResourceRepository_ResourceType((ResourceRepository) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ResourcetypePackage.RESOURCE_TYPE__UNIT:
			unsetUnit();
			return;
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY:
			getResourceProvidedRoles__ResourceInterfaceProvidingEntity().clear();
			return;
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE:
			setResourceRepository_ResourceType((ResourceRepository) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ResourcetypePackage.RESOURCE_TYPE__UNIT:
			return isSetUnit();
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY:
			return !getResourceProvidedRoles__ResourceInterfaceProvidingEntity().isEmpty();
		case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_REPOSITORY_RESOURCE_TYPE:
			return getResourceRepository_ResourceType() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == UnitCarryingElement.class) {
			switch (derivedFeatureID) {
			case ResourcetypePackage.RESOURCE_TYPE__UNIT:
				return UnitsPackage.UNIT_CARRYING_ELEMENT__UNIT;
			default:
				return -1;
			}
		}
		if (baseClass == ResourceInterfaceProvidingEntity.class) {
			switch (derivedFeatureID) {
			case ResourcetypePackage.RESOURCE_TYPE__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY:
				return EntityPackage.RESOURCE_INTERFACE_PROVIDING_ENTITY__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == UnitCarryingElement.class) {
			switch (baseFeatureID) {
			case UnitsPackage.UNIT_CARRYING_ELEMENT__UNIT:
				return ResourcetypePackage.RESOURCE_TYPE__UNIT;
			default:
				return -1;
			}
		}
		if (baseClass == ResourceInterfaceProvidingEntity.class) {
			switch (baseFeatureID) {
			case EntityPackage.RESOURCE_INTERFACE_PROVIDING_ENTITY__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY:
				return ResourcetypePackage.RESOURCE_TYPE__RESOURCE_PROVIDED_ROLES_RESOURCE_INTERFACE_PROVIDING_ENTITY;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} // ResourceTypeImpl
