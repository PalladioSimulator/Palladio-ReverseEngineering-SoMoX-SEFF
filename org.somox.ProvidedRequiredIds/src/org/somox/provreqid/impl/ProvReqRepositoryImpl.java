/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.somox.provreqid.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.somox.provreqid.ProvReqRepository;
import org.somox.provreqid.ProvidedService;
import org.somox.provreqid.ProvreqidPackage;
import org.somox.provreqid.RequiredService;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Prov Req Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.provreqid.impl.ProvReqRepositoryImpl#getProvidedServices <em>Provided Services</em>}</li>
 *   <li>{@link org.somox.provreqid.impl.ProvReqRepositoryImpl#getRequiredServices <em>Required Services</em>}</li>
 *   <li>{@link org.somox.provreqid.impl.ProvReqRepositoryImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProvReqRepositoryImpl extends EObjectImpl implements ProvReqRepository {
	/**
	 * The cached value of the '{@link #getProvidedServices() <em>Provided Services</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidedServices()
	 * @generated
	 * @ordered
	 */
	protected EList<ProvidedService> providedServices;

	/**
	 * The cached value of the '{@link #getRequiredServices() <em>Required Services</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredServices()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiredService> requiredServices;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProvReqRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProvreqidPackage.Literals.PROV_REQ_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProvidedService> getProvidedServices() {
		if (providedServices == null) {
			providedServices = new EObjectContainmentEList<ProvidedService>(ProvidedService.class, this, ProvreqidPackage.PROV_REQ_REPOSITORY__PROVIDED_SERVICES);
		}
		return providedServices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequiredService> getRequiredServices() {
		if (requiredServices == null) {
			requiredServices = new EObjectContainmentEList<RequiredService>(RequiredService.class, this, ProvreqidPackage.PROV_REQ_REPOSITORY__REQUIRED_SERVICES);
		}
		return requiredServices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProvreqidPackage.PROV_REQ_REPOSITORY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProvreqidPackage.PROV_REQ_REPOSITORY__PROVIDED_SERVICES:
				return ((InternalEList<?>)getProvidedServices()).basicRemove(otherEnd, msgs);
			case ProvreqidPackage.PROV_REQ_REPOSITORY__REQUIRED_SERVICES:
				return ((InternalEList<?>)getRequiredServices()).basicRemove(otherEnd, msgs);
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
			case ProvreqidPackage.PROV_REQ_REPOSITORY__PROVIDED_SERVICES:
				return getProvidedServices();
			case ProvreqidPackage.PROV_REQ_REPOSITORY__REQUIRED_SERVICES:
				return getRequiredServices();
			case ProvreqidPackage.PROV_REQ_REPOSITORY__NAME:
				return getName();
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
			case ProvreqidPackage.PROV_REQ_REPOSITORY__PROVIDED_SERVICES:
				getProvidedServices().clear();
				getProvidedServices().addAll((Collection<? extends ProvidedService>)newValue);
				return;
			case ProvreqidPackage.PROV_REQ_REPOSITORY__REQUIRED_SERVICES:
				getRequiredServices().clear();
				getRequiredServices().addAll((Collection<? extends RequiredService>)newValue);
				return;
			case ProvreqidPackage.PROV_REQ_REPOSITORY__NAME:
				setName((String)newValue);
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
			case ProvreqidPackage.PROV_REQ_REPOSITORY__PROVIDED_SERVICES:
				getProvidedServices().clear();
				return;
			case ProvreqidPackage.PROV_REQ_REPOSITORY__REQUIRED_SERVICES:
				getRequiredServices().clear();
				return;
			case ProvreqidPackage.PROV_REQ_REPOSITORY__NAME:
				setName(NAME_EDEFAULT);
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
			case ProvreqidPackage.PROV_REQ_REPOSITORY__PROVIDED_SERVICES:
				return providedServices != null && !providedServices.isEmpty();
			case ProvreqidPackage.PROV_REQ_REPOSITORY__REQUIRED_SERVICES:
				return requiredServices != null && !requiredServices.isEmpty();
			case ProvreqidPackage.PROV_REQ_REPOSITORY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ProvReqRepositoryImpl
