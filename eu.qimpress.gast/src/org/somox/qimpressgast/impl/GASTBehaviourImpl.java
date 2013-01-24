/**
 */
package org.somox.qimpressgast.impl;

import eu.qimpress.samm.behaviour.GastBehaviourStub;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.gmt.modisco.java.Block;

import org.somox.qimpressgast.GASTBehaviour;
import org.somox.qimpressgast.somoxgastPackage;
import org.somox.qimpressgast.qimpressgastPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GAST Behaviour</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.qimpressgast.impl.GASTBehaviourImpl#getBlockstatement <em>Blockstatement</em>}</li>
 *   <li>{@link org.somox.qimpressgast.impl.GASTBehaviourImpl#getGastbehaviourstub <em>Gastbehaviourstub</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GASTBehaviourImpl extends EObjectImpl implements GASTBehaviour {
	/**
	 * The cached value of the '{@link #getBlockstatement() <em>Blockstatement</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlockstatement()
	 * @generated
	 * @ordered
	 */
	protected Block blockstatement;

	/**
	 * The cached value of the '{@link #getGastbehaviourstub() <em>Gastbehaviourstub</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGastbehaviourstub()
	 * @generated
	 * @ordered
	 */
	protected GastBehaviourStub gastbehaviourstub;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GASTBehaviourImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return somoxgastPackage.Literals.GAST_BEHAVIOUR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getBlockstatement() {
		if (blockstatement != null && blockstatement.eIsProxy()) {
			InternalEObject oldBlockstatement = (InternalEObject)blockstatement;
			blockstatement = (Block)eResolveProxy(oldBlockstatement);
			if (blockstatement != oldBlockstatement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, somoxgastPackage.GAST_BEHAVIOUR__BLOCKSTATEMENT, oldBlockstatement, blockstatement));
			}
		}
		return blockstatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block basicGetBlockstatement() {
		return blockstatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlockstatement(Block newBlockstatement) {
		Block oldBlockstatement = blockstatement;
		blockstatement = newBlockstatement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, somoxgastPackage.GAST_BEHAVIOUR__BLOCKSTATEMENT, oldBlockstatement, blockstatement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GastBehaviourStub getGastbehaviourstub() {
		if (gastbehaviourstub != null && gastbehaviourstub.eIsProxy()) {
			InternalEObject oldGastbehaviourstub = (InternalEObject)gastbehaviourstub;
			gastbehaviourstub = (GastBehaviourStub)eResolveProxy(oldGastbehaviourstub);
			if (gastbehaviourstub != oldGastbehaviourstub) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, somoxgastPackage.GAST_BEHAVIOUR__GASTBEHAVIOURSTUB, oldGastbehaviourstub, gastbehaviourstub));
			}
		}
		return gastbehaviourstub;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GastBehaviourStub basicGetGastbehaviourstub() {
		return gastbehaviourstub;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGastbehaviourstub(GastBehaviourStub newGastbehaviourstub) {
		GastBehaviourStub oldGastbehaviourstub = gastbehaviourstub;
		gastbehaviourstub = newGastbehaviourstub;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, somoxgastPackage.GAST_BEHAVIOUR__GASTBEHAVIOURSTUB, oldGastbehaviourstub, gastbehaviourstub));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case somoxgastPackage.GAST_BEHAVIOUR__BLOCKSTATEMENT:
				if (resolve) return getBlockstatement();
				return basicGetBlockstatement();
			case somoxgastPackage.GAST_BEHAVIOUR__GASTBEHAVIOURSTUB:
				if (resolve) return getGastbehaviourstub();
				return basicGetGastbehaviourstub();
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
			case somoxgastPackage.GAST_BEHAVIOUR__BLOCKSTATEMENT:
				setBlockstatement((Block)newValue);
				return;
			case somoxgastPackage.GAST_BEHAVIOUR__GASTBEHAVIOURSTUB:
				setGastbehaviourstub((GastBehaviourStub)newValue);
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
			case somoxgastPackage.GAST_BEHAVIOUR__BLOCKSTATEMENT:
				setBlockstatement((Block)null);
				return;
			case somoxgastPackage.GAST_BEHAVIOUR__GASTBEHAVIOURSTUB:
				setGastbehaviourstub((GastBehaviourStub)null);
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
			case somoxgastPackage.GAST_BEHAVIOUR__BLOCKSTATEMENT:
				return blockstatement != null;
			case somoxgastPackage.GAST_BEHAVIOUR__GASTBEHAVIOURSTUB:
				return gastbehaviourstub != null;
		}
		return super.eIsSet(featureID);
	}

} //GASTBehaviourImpl
