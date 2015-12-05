/**
 */
package org.somox.sourcecodedecorator.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.emftext.language.java.members.ClassMethod;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.somox.sourcecodedecorator.AbstractActionClassMethodLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Action Class Method Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.somox.sourcecodedecorator.impl.AbstractActionClassMethodLinkImpl#getClassMethod
 * <em>Class Method</em>}</li>
 * <li>{@link org.somox.sourcecodedecorator.impl.AbstractActionClassMethodLinkImpl#getAbstractAction
 * <em>Abstract Action</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbstractActionClassMethodLinkImpl extends MinimalEObjectImpl.Container
        implements AbstractActionClassMethodLink {
    /**
     * The cached value of the '{@link #getClassMethod() <em>Class Method</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getClassMethod()
     * @generated
     * @ordered
     */
    protected ClassMethod classMethod;

    /**
     * The cached value of the '{@link #getAbstractAction() <em>Abstract Action</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAbstractAction()
     * @generated
     * @ordered
     */
    protected AbstractAction abstractAction;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractActionClassMethodLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.ABSTRACT_ACTION_CLASS_METHOD_LINK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ClassMethod getClassMethod() {
        if (this.classMethod != null && this.classMethod.eIsProxy()) {
            final InternalEObject oldClassMethod = (InternalEObject) this.classMethod;
            this.classMethod = (ClassMethod) this.eResolveProxy(oldClassMethod);
            if (this.classMethod != oldClassMethod) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD, oldClassMethod,
                            this.classMethod));
                }
            }
        }
        return this.classMethod;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ClassMethod basicGetClassMethod() {
        return this.classMethod;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setClassMethod(final ClassMethod newClassMethod) {
        final ClassMethod oldClassMethod = this.classMethod;
        this.classMethod = newClassMethod;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD, oldClassMethod,
                    this.classMethod));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AbstractAction getAbstractAction() {
        if (this.abstractAction != null && ((EObject) this.abstractAction).eIsProxy()) {
            final InternalEObject oldAbstractAction = (InternalEObject) this.abstractAction;
            this.abstractAction = (AbstractAction) this.eResolveProxy(oldAbstractAction);
            if (this.abstractAction != oldAbstractAction) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION,
                            oldAbstractAction, this.abstractAction));
                }
            }
        }
        return this.abstractAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public AbstractAction basicGetAbstractAction() {
        return this.abstractAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAbstractAction(final AbstractAction newAbstractAction) {
        final AbstractAction oldAbstractAction = this.abstractAction;
        this.abstractAction = newAbstractAction;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION, oldAbstractAction,
                    this.abstractAction));
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
        case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD:
            if (resolve) {
                return this.getClassMethod();
            }
            return this.basicGetClassMethod();
        case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION:
            if (resolve) {
                return this.getAbstractAction();
            }
            return this.basicGetAbstractAction();
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
        case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD:
            this.setClassMethod((ClassMethod) newValue);
            return;
        case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION:
            this.setAbstractAction((AbstractAction) newValue);
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
        case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD:
            this.setClassMethod((ClassMethod) null);
            return;
        case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION:
            this.setAbstractAction((AbstractAction) null);
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
        case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD:
            return this.classMethod != null;
        case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION:
            return this.abstractAction != null;
        }
        return super.eIsSet(featureID);
    }

} // AbstractActionClassMethodLinkImpl
