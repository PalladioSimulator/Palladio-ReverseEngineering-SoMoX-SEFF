/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.somox.provreqid.impl;

import de.fzi.gast.accesses.FunctionAccess;
import de.fzi.gast.functions.Method;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.somox.provreqid.ProvreqidPackage;
import org.somox.provreqid.RequiredService;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Required Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.provreqid.impl.RequiredServiceImpl#getGastMethod <em>Gast Method</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RequiredServiceImpl extends EObjectImpl implements RequiredService {
	/**
	 * The cached value of the '{@link #getGastMethod() <em>Gast Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGastMethod()
	 * @generated
	 * @ordered
	 */
	protected FunctionAccess gastMethod;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequiredServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProvreqidPackage.Literals.REQUIRED_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionAccess getGastMethod() {
		if (gastMethod != null && gastMethod.eIsProxy()) {
			InternalEObject oldGastMethod = (InternalEObject)gastMethod;
			gastMethod = (FunctionAccess)eResolveProxy(oldGastMethod);
			if (gastMethod != oldGastMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProvreqidPackage.REQUIRED_SERVICE__GAST_METHOD, oldGastMethod, gastMethod));
			}
		}
		return gastMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionAccess basicGetGastMethod() {
		return gastMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGastMethod(FunctionAccess newGastMethod) {
		FunctionAccess oldGastMethod = gastMethod;
		gastMethod = newGastMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProvreqidPackage.REQUIRED_SERVICE__GAST_METHOD, oldGastMethod, gastMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProvreqidPackage.REQUIRED_SERVICE__GAST_METHOD:
				if (resolve) return getGastMethod();
				return basicGetGastMethod();
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
			case ProvreqidPackage.REQUIRED_SERVICE__GAST_METHOD:
				setGastMethod((FunctionAccess)newValue);
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
			case ProvreqidPackage.REQUIRED_SERVICE__GAST_METHOD:
				setGastMethod((FunctionAccess)null);
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
			case ProvreqidPackage.REQUIRED_SERVICE__GAST_METHOD:
				return gastMethod != null;
		}
		return super.eIsSet(featureID);
	}

} //RequiredServiceImpl
