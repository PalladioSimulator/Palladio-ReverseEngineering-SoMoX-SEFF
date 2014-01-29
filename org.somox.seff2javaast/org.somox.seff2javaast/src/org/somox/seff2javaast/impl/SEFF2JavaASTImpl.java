/**
 */
package org.somox.seff2javaast.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.somox.seff2javaast.SEFF2JavaAST;
import org.somox.seff2javaast.SEFF2MethodMapping;
import org.somox.seff2javaast.Seff2javaastPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SEFF2 Java AST</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.seff2javaast.impl.SEFF2JavaASTImpl#getSeff2MethodMappings <em>Seff2 Method Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SEFF2JavaASTImpl extends MinimalEObjectImpl.Container implements SEFF2JavaAST {
	/**
	 * The cached value of the '{@link #getSeff2MethodMappings() <em>Seff2 Method Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeff2MethodMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<SEFF2MethodMapping> seff2MethodMappings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SEFF2JavaASTImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Seff2javaastPackage.Literals.SEFF2_JAVA_AST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SEFF2MethodMapping> getSeff2MethodMappings() {
		if (seff2MethodMappings == null) {
			seff2MethodMappings = new EObjectContainmentEList<SEFF2MethodMapping>(SEFF2MethodMapping.class, this, Seff2javaastPackage.SEFF2_JAVA_AST__SEFF2_METHOD_MAPPINGS);
		}
		return seff2MethodMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Seff2javaastPackage.SEFF2_JAVA_AST__SEFF2_METHOD_MAPPINGS:
				return ((InternalEList<?>)getSeff2MethodMappings()).basicRemove(otherEnd, msgs);
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
			case Seff2javaastPackage.SEFF2_JAVA_AST__SEFF2_METHOD_MAPPINGS:
				return getSeff2MethodMappings();
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
			case Seff2javaastPackage.SEFF2_JAVA_AST__SEFF2_METHOD_MAPPINGS:
				getSeff2MethodMappings().clear();
				getSeff2MethodMappings().addAll((Collection<? extends SEFF2MethodMapping>)newValue);
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
			case Seff2javaastPackage.SEFF2_JAVA_AST__SEFF2_METHOD_MAPPINGS:
				getSeff2MethodMappings().clear();
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
			case Seff2javaastPackage.SEFF2_JAVA_AST__SEFF2_METHOD_MAPPINGS:
				return seff2MethodMappings != null && !seff2MethodMappings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SEFF2JavaASTImpl
