/**
 */
package org.somox.sourcecodedecorator.impl;

import de.uka.ipd.sdq.pcm.repository.Interface;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.emftext.language.java.types.Type;

import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interface Source Code Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl#getGastClass <em>Gast Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InterfaceSourceCodeLinkImpl extends MinimalEObjectImpl.Container implements InterfaceSourceCodeLink {
	/**
	 * The cached value of the '{@link #getInterface() <em>Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected Interface interface_;

	/**
	 * The cached value of the '{@link #getGastClass() <em>Gast Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGastClass()
	 * @generated
	 * @ordered
	 */
	protected Type gastClass;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InterfaceSourceCodeLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SourcecodedecoratorPackage.Literals.INTERFACE_SOURCE_CODE_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interface getInterface() {
		if (interface_ != null && interface_.eIsProxy()) {
			InternalEObject oldInterface = (InternalEObject)interface_;
			interface_ = (Interface)eResolveProxy(oldInterface);
			if (interface_ != oldInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE, oldInterface, interface_));
			}
		}
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interface basicGetInterface() {
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(Interface newInterface) {
		Interface oldInterface = interface_;
		interface_ = newInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE, oldInterface, interface_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getGastClass() {
		if (gastClass != null && gastClass.eIsProxy()) {
			InternalEObject oldGastClass = (InternalEObject)gastClass;
			gastClass = (Type)eResolveProxy(oldGastClass);
			if (gastClass != oldGastClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS, oldGastClass, gastClass));
			}
		}
		return gastClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetGastClass() {
		return gastClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGastClass(Type newGastClass) {
		Type oldGastClass = gastClass;
		gastClass = newGastClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS, oldGastClass, gastClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE:
				if (resolve) return getInterface();
				return basicGetInterface();
			case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS:
				if (resolve) return getGastClass();
				return basicGetGastClass();
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
			case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE:
				setInterface((Interface)newValue);
				return;
			case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS:
				setGastClass((Type)newValue);
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
			case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE:
				setInterface((Interface)null);
				return;
			case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS:
				setGastClass((Type)null);
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
			case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE:
				return interface_ != null;
			case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS:
				return gastClass != null;
		}
		return super.eIsSet(featureID);
	}

} //InterfaceSourceCodeLinkImpl
