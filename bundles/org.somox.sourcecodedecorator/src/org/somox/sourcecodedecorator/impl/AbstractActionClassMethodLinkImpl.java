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
 *   <li>{@link org.somox.sourcecodedecorator.impl.AbstractActionClassMethodLinkImpl#getClassMethod <em>Class Method</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.AbstractActionClassMethodLinkImpl#getAbstractAction <em>Abstract Action</em>}</li>
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
	 * @see #getAbstractAction()
	 * @generated
	 * @ordered
	 */
    protected AbstractAction abstractAction;

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    protected AbstractActionClassMethodLinkImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return SourcecodedecoratorPackage.Literals.ABSTRACT_ACTION_CLASS_METHOD_LINK;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public ClassMethod getClassMethod() {
		if (classMethod != null && classMethod.eIsProxy()) {
			InternalEObject oldClassMethod = (InternalEObject)classMethod;
			classMethod = (ClassMethod)eResolveProxy(oldClassMethod);
			if (classMethod != oldClassMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD, oldClassMethod, classMethod));
			}
		}
		return classMethod;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public ClassMethod basicGetClassMethod() {
		return classMethod;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setClassMethod(ClassMethod newClassMethod) {
		ClassMethod oldClassMethod = classMethod;
		classMethod = newClassMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD, oldClassMethod, classMethod));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public AbstractAction getAbstractAction() {
		if (abstractAction != null && ((EObject)abstractAction).eIsProxy()) {
			InternalEObject oldAbstractAction = (InternalEObject)abstractAction;
			abstractAction = (AbstractAction)eResolveProxy(oldAbstractAction);
			if (abstractAction != oldAbstractAction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION, oldAbstractAction, abstractAction));
			}
		}
		return abstractAction;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public AbstractAction basicGetAbstractAction() {
		return abstractAction;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setAbstractAction(AbstractAction newAbstractAction) {
		AbstractAction oldAbstractAction = abstractAction;
		abstractAction = newAbstractAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION, oldAbstractAction, abstractAction));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD:
				if (resolve) return getClassMethod();
				return basicGetClassMethod();
			case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION:
				if (resolve) return getAbstractAction();
				return basicGetAbstractAction();
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
			case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD:
				setClassMethod((ClassMethod)newValue);
				return;
			case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION:
				setAbstractAction((AbstractAction)newValue);
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
			case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD:
				setClassMethod((ClassMethod)null);
				return;
			case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION:
				setAbstractAction((AbstractAction)null);
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
			case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__CLASS_METHOD:
				return classMethod != null;
			case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK__ABSTRACT_ACTION:
				return abstractAction != null;
		}
		return super.eIsSet(featureID);
	}

} // AbstractActionClassMethodLinkImpl
