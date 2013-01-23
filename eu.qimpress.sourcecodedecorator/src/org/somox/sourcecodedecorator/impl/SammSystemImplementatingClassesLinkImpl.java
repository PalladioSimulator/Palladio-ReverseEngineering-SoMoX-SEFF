/**
 */
package org.somox.sourcecodedecorator.impl;

import eu.qimpress.samm.staticstructure.ServiceArchitectureModel;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.somox.sourcecodedecorator.SammSystemImplementatingClassesLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Samm System Implementating Classes Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.impl.SammSystemImplementatingClassesLinkImpl#getServiceArchitectureModel <em>Service Architecture Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SammSystemImplementatingClassesLinkImpl extends ComponentImplementingClassesLinkImpl implements SammSystemImplementatingClassesLink {
	/**
	 * The cached value of the '{@link #getServiceArchitectureModel() <em>Service Architecture Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceArchitectureModel()
	 * @generated
	 * @ordered
	 */
	protected ServiceArchitectureModel serviceArchitectureModel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SammSystemImplementatingClassesLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SourceCodeDecoratorPackage.Literals.SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceArchitectureModel getServiceArchitectureModel() {
		if (serviceArchitectureModel != null && serviceArchitectureModel.eIsProxy()) {
			InternalEObject oldServiceArchitectureModel = (InternalEObject)serviceArchitectureModel;
			serviceArchitectureModel = (ServiceArchitectureModel)eResolveProxy(oldServiceArchitectureModel);
			if (serviceArchitectureModel != oldServiceArchitectureModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourceCodeDecoratorPackage.SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SERVICE_ARCHITECTURE_MODEL, oldServiceArchitectureModel, serviceArchitectureModel));
			}
		}
		return serviceArchitectureModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceArchitectureModel basicGetServiceArchitectureModel() {
		return serviceArchitectureModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceArchitectureModel(ServiceArchitectureModel newServiceArchitectureModel) {
		ServiceArchitectureModel oldServiceArchitectureModel = serviceArchitectureModel;
		serviceArchitectureModel = newServiceArchitectureModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourceCodeDecoratorPackage.SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SERVICE_ARCHITECTURE_MODEL, oldServiceArchitectureModel, serviceArchitectureModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SourceCodeDecoratorPackage.SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SERVICE_ARCHITECTURE_MODEL:
				if (resolve) return getServiceArchitectureModel();
				return basicGetServiceArchitectureModel();
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
			case SourceCodeDecoratorPackage.SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SERVICE_ARCHITECTURE_MODEL:
				setServiceArchitectureModel((ServiceArchitectureModel)newValue);
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
			case SourceCodeDecoratorPackage.SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SERVICE_ARCHITECTURE_MODEL:
				setServiceArchitectureModel((ServiceArchitectureModel)null);
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
			case SourceCodeDecoratorPackage.SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK__SERVICE_ARCHITECTURE_MODEL:
				return serviceArchitectureModel != null;
		}
		return super.eIsSet(featureID);
	}

} //SammSystemImplementatingClassesLinkImpl
