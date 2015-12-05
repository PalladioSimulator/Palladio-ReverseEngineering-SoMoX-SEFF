/**
 */
package org.somox.sourcecodedecorator.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.palladiosimulator.pcm.repository.Interface;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Interface Source Code Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.somox.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl#getInterface
 * <em>Interface</em>}</li>
 * <li>{@link org.somox.sourcecodedecorator.impl.InterfaceSourceCodeLinkImpl#getGastClass
 * <em>Gast Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InterfaceSourceCodeLinkImpl extends MinimalEObjectImpl.Container implements InterfaceSourceCodeLink {
    /**
     * The cached value of the '{@link #getInterface() <em>Interface</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInterface()
     * @generated
     * @ordered
     */
    protected Interface interface_;

    /**
     * The cached value of the '{@link #getGastClass() <em>Gast Class</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getGastClass()
     * @generated
     * @ordered
     */
    protected ConcreteClassifier gastClass;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InterfaceSourceCodeLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.INTERFACE_SOURCE_CODE_LINK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Interface getInterface() {
        if (this.interface_ != null && ((EObject) this.interface_).eIsProxy()) {
            final InternalEObject oldInterface = (InternalEObject) this.interface_;
            this.interface_ = (Interface) this.eResolveProxy(oldInterface);
            if (this.interface_ != oldInterface) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE, oldInterface,
                            this.interface_));
                }
            }
        }
        return this.interface_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Interface basicGetInterface() {
        return this.interface_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInterface(final Interface newInterface) {
        final Interface oldInterface = this.interface_;
        this.interface_ = newInterface;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE, oldInterface, this.interface_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ConcreteClassifier getGastClass() {
        if (this.gastClass != null && this.gastClass.eIsProxy()) {
            final InternalEObject oldGastClass = (InternalEObject) this.gastClass;
            this.gastClass = (ConcreteClassifier) this.eResolveProxy(oldGastClass);
            if (this.gastClass != oldGastClass) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS, oldGastClass,
                            this.gastClass));
                }
            }
        }
        return this.gastClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ConcreteClassifier basicGetGastClass() {
        return this.gastClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setGastClass(final ConcreteClassifier newGastClass) {
        final ConcreteClassifier oldGastClass = this.gastClass;
        this.gastClass = newGastClass;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS, oldGastClass, this.gastClass));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE:
            if (resolve) {
                return this.getInterface();
            }
            return this.basicGetInterface();
        case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS:
            if (resolve) {
                return this.getGastClass();
            }
            return this.basicGetGastClass();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE:
            this.setInterface((Interface) newValue);
            return;
        case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS:
            this.setGastClass((ConcreteClassifier) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE:
            this.setInterface((Interface) null);
            return;
        case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS:
            this.setGastClass((ConcreteClassifier) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__INTERFACE:
            return this.interface_ != null;
        case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK__GAST_CLASS:
            return this.gastClass != null;
        }
        return super.eIsSet(featureID);
    }

} // InterfaceSourceCodeLinkImpl
