/**
 */
package org.somox.sourcecodedecorator.impl;

import org.palladiosimulator.pcm.repository.Signature;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.emftext.language.java.members.Member;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Level Source Code Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.impl.MethodLevelSourceCodeLinkImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.MethodLevelSourceCodeLinkImpl#getFunction <em>Function</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MethodLevelSourceCodeLinkImpl extends FileLevelSourceCodeLinkImpl implements MethodLevelSourceCodeLink {
	/**
     * The cached value of the '{@link #getOperation() <em>Operation</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOperation()
     * @generated
     * @ordered
     */
	protected Signature operation;

	/**
     * The cached value of the '{@link #getFunction() <em>Function</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFunction()
     * @generated
     * @ordered
     */
	protected Member function;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected MethodLevelSourceCodeLinkImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.METHOD_LEVEL_SOURCE_CODE_LINK;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Signature getOperation() {
        if (operation != null && ((EObject)operation).eIsProxy()) {
            InternalEObject oldOperation = (InternalEObject)operation;
            operation = (Signature)eResolveProxy(oldOperation);
            if (operation != oldOperation) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION, oldOperation, operation));
            }
        }
        return operation;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Signature basicGetOperation() {
        return operation;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setOperation(Signature newOperation) {
        Signature oldOperation = operation;
        operation = newOperation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION, oldOperation, operation));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Member getFunction() {
        if (function != null && function.eIsProxy()) {
            InternalEObject oldFunction = (InternalEObject)function;
            function = (Member)eResolveProxy(oldFunction);
            if (function != oldFunction) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION, oldFunction, function));
            }
        }
        return function;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Member basicGetFunction() {
        return function;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFunction(Member newFunction) {
        Member oldFunction = function;
        function = newFunction;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION, oldFunction, function));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION:
                if (resolve) return getOperation();
                return basicGetOperation();
            case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION:
                if (resolve) return getFunction();
                return basicGetFunction();
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
            case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION:
                setOperation((Signature)newValue);
                return;
            case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION:
                setFunction((Member)newValue);
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
            case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION:
                setOperation((Signature)null);
                return;
            case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION:
                setFunction((Member)null);
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
            case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__OPERATION:
                return operation != null;
            case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK__FUNCTION:
                return function != null;
        }
        return super.eIsSet(featureID);
    }

} //MethodLevelSourceCodeLinkImpl
