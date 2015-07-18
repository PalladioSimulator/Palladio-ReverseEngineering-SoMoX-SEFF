/**
 */
package org.somox.seff2javaast.impl;

import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.emftext.language.java.statements.StatementListContainer;

import org.somox.seff2javaast.SEFF2MethodMapping;
import org.somox.seff2javaast.Seff2javaastPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SEFF2 Method Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.seff2javaast.impl.SEFF2MethodMappingImpl#getBlockstatement <em>Blockstatement</em>}</li>
 *   <li>{@link org.somox.seff2javaast.impl.SEFF2MethodMappingImpl#getSeff <em>Seff</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SEFF2MethodMappingImpl extends MinimalEObjectImpl.Container implements SEFF2MethodMapping {
	/**
	 * The cached value of the '{@link #getBlockstatement() <em>Blockstatement</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlockstatement()
	 * @generated
	 * @ordered
	 */
	protected StatementListContainer blockstatement;

	/**
	 * The cached value of the '{@link #getSeff() <em>Seff</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeff()
	 * @generated
	 * @ordered
	 */
	protected ServiceEffectSpecification seff;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SEFF2MethodMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Seff2javaastPackage.Literals.SEFF2_METHOD_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementListContainer getBlockstatement() {
		if (blockstatement != null && blockstatement.eIsProxy()) {
			InternalEObject oldBlockstatement = (InternalEObject)blockstatement;
			blockstatement = (StatementListContainer)eResolveProxy(oldBlockstatement);
			if (blockstatement != oldBlockstatement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Seff2javaastPackage.SEFF2_METHOD_MAPPING__BLOCKSTATEMENT, oldBlockstatement, blockstatement));
			}
		}
		return blockstatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatementListContainer basicGetBlockstatement() {
		return blockstatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlockstatement(StatementListContainer newBlockstatement) {
		StatementListContainer oldBlockstatement = blockstatement;
		blockstatement = newBlockstatement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Seff2javaastPackage.SEFF2_METHOD_MAPPING__BLOCKSTATEMENT, oldBlockstatement, blockstatement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceEffectSpecification getSeff() {
		if (seff != null && seff.eIsProxy()) {
			InternalEObject oldSeff = (InternalEObject)seff;
			seff = (ServiceEffectSpecification)eResolveProxy(oldSeff);
			if (seff != oldSeff) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Seff2javaastPackage.SEFF2_METHOD_MAPPING__SEFF, oldSeff, seff));
			}
		}
		return seff;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceEffectSpecification basicGetSeff() {
		return seff;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeff(ServiceEffectSpecification newSeff) {
		ServiceEffectSpecification oldSeff = seff;
		seff = newSeff;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Seff2javaastPackage.SEFF2_METHOD_MAPPING__SEFF, oldSeff, seff));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Seff2javaastPackage.SEFF2_METHOD_MAPPING__BLOCKSTATEMENT:
				if (resolve) return getBlockstatement();
				return basicGetBlockstatement();
			case Seff2javaastPackage.SEFF2_METHOD_MAPPING__SEFF:
				if (resolve) return getSeff();
				return basicGetSeff();
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
			case Seff2javaastPackage.SEFF2_METHOD_MAPPING__BLOCKSTATEMENT:
				setBlockstatement((StatementListContainer)newValue);
				return;
			case Seff2javaastPackage.SEFF2_METHOD_MAPPING__SEFF:
				setSeff((ServiceEffectSpecification)newValue);
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
			case Seff2javaastPackage.SEFF2_METHOD_MAPPING__BLOCKSTATEMENT:
				setBlockstatement((StatementListContainer)null);
				return;
			case Seff2javaastPackage.SEFF2_METHOD_MAPPING__SEFF:
				setSeff((ServiceEffectSpecification)null);
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
			case Seff2javaastPackage.SEFF2_METHOD_MAPPING__BLOCKSTATEMENT:
				return blockstatement != null;
			case Seff2javaastPackage.SEFF2_METHOD_MAPPING__SEFF:
				return seff != null;
		}
		return super.eIsSet(featureID);
	}

} //SEFF2MethodMappingImpl
