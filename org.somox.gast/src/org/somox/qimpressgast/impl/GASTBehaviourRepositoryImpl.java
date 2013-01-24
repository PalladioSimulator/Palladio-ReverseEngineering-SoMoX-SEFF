/**
 */
package org.somox.qimpressgast.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.somox.qimpressgast.GASTBehaviour;
import org.somox.qimpressgast.GASTBehaviourRepository;
import org.somox.qimpressgast.somoxgastPackage;
import org.somox.qimpressgast.qimpressgastPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GAST Behaviour Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.qimpressgast.impl.GASTBehaviourRepositoryImpl#getGastbehaviour <em>Gastbehaviour</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GASTBehaviourRepositoryImpl extends EObjectImpl implements GASTBehaviourRepository {
	/**
	 * The cached value of the '{@link #getGastbehaviour() <em>Gastbehaviour</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGastbehaviour()
	 * @generated
	 * @ordered
	 */
	protected EList<GASTBehaviour> gastbehaviour;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GASTBehaviourRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return somoxgastPackage.Literals.GAST_BEHAVIOUR_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GASTBehaviour> getGastbehaviour() {
		if (gastbehaviour == null) {
			gastbehaviour = new EObjectContainmentEList<GASTBehaviour>(GASTBehaviour.class, this, somoxgastPackage.GAST_BEHAVIOUR_REPOSITORY__GASTBEHAVIOUR);
		}
		return gastbehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case somoxgastPackage.GAST_BEHAVIOUR_REPOSITORY__GASTBEHAVIOUR:
				return ((InternalEList<?>)getGastbehaviour()).basicRemove(otherEnd, msgs);
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
			case somoxgastPackage.GAST_BEHAVIOUR_REPOSITORY__GASTBEHAVIOUR:
				return getGastbehaviour();
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
			case somoxgastPackage.GAST_BEHAVIOUR_REPOSITORY__GASTBEHAVIOUR:
				getGastbehaviour().clear();
				getGastbehaviour().addAll((Collection<? extends GASTBehaviour>)newValue);
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
			case somoxgastPackage.GAST_BEHAVIOUR_REPOSITORY__GASTBEHAVIOUR:
				getGastbehaviour().clear();
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
			case somoxgastPackage.GAST_BEHAVIOUR_REPOSITORY__GASTBEHAVIOUR:
				return gastbehaviour != null && !gastbehaviour.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GASTBehaviourRepositoryImpl
