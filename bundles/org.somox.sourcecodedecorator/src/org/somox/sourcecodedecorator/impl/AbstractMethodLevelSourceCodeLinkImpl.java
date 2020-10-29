/**
 */
package org.somox.sourcecodedecorator.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.emftext.language.java.members.Member;
import org.somox.sourcecodedecorator.AbstractMethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Method Level Source Code Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.impl.AbstractMethodLevelSourceCodeLinkImpl#getFunction <em>Function</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractMethodLevelSourceCodeLinkImpl extends FileLevelSourceCodeLinkImpl
        implements AbstractMethodLevelSourceCodeLink {
    /**
     * The cached value of the '{@link #getFunction() <em>Function</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFunction()
     * @generated
     * @ordered
     */
    protected Member function;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected AbstractMethodLevelSourceCodeLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Member getFunction() {
        if (function != null && function.eIsProxy()) {
            InternalEObject oldFunction = (InternalEObject)function;
            function = (Member)eResolveProxy(oldFunction);
            if (function != oldFunction) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION, oldFunction, function));
            }
        }
        return function;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Member basicGetFunction() {
        return function;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFunction(Member newFunction) {
        Member oldFunction = function;
        function = newFunction;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION, oldFunction, function));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SourcecodedecoratorPackage.ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION:
                if (resolve) return getFunction();
                return basicGetFunction();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SourcecodedecoratorPackage.ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION:
                setFunction((Member)newValue);
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
            case SourcecodedecoratorPackage.ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION:
                setFunction((Member)null);
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
            case SourcecodedecoratorPackage.ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION:
                return function != null;
        }
        return super.eIsSet(featureID);
    }

} // AbstractMethodLevelSourceCodeLinkImpl
