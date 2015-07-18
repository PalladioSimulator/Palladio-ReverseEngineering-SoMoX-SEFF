/**
 */
package org.somox.sourcecodedecorator.impl;

import org.palladiosimulator.pcm.repository.InnerDeclaration;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.emftext.language.java.members.Field;

import org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Inner Datatype Source Code Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.impl.InnerDatatypeSourceCodeLinkImpl#getField <em>Field</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.InnerDatatypeSourceCodeLinkImpl#getInnerDeclaration <em>Inner Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InnerDatatypeSourceCodeLinkImpl extends MinimalEObjectImpl.Container implements InnerDatatypeSourceCodeLink {
    /**
     * The cached value of the '{@link #getField() <em>Field</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getField()
     * @generated
     * @ordered
     */
    protected Field field;

    /**
     * The cached value of the '{@link #getInnerDeclaration() <em>Inner Declaration</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInnerDeclaration()
     * @generated
     * @ordered
     */
    protected InnerDeclaration innerDeclaration;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InnerDatatypeSourceCodeLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.INNER_DATATYPE_SOURCE_CODE_LINK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Field getField() {
        if (field != null && field.eIsProxy()) {
            InternalEObject oldField = (InternalEObject)field;
            field = (Field)eResolveProxy(oldField);
            if (field != oldField) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD, oldField, field));
            }
        }
        return field;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Field basicGetField() {
        return field;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setField(Field newField) {
        Field oldField = field;
        field = newField;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD, oldField, field));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InnerDeclaration getInnerDeclaration() {
        if (innerDeclaration != null && ((EObject)innerDeclaration).eIsProxy()) {
            InternalEObject oldInnerDeclaration = (InternalEObject)innerDeclaration;
            innerDeclaration = (InnerDeclaration)eResolveProxy(oldInnerDeclaration);
            if (innerDeclaration != oldInnerDeclaration) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION, oldInnerDeclaration, innerDeclaration));
            }
        }
        return innerDeclaration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InnerDeclaration basicGetInnerDeclaration() {
        return innerDeclaration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInnerDeclaration(InnerDeclaration newInnerDeclaration) {
        InnerDeclaration oldInnerDeclaration = innerDeclaration;
        innerDeclaration = newInnerDeclaration;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION, oldInnerDeclaration, innerDeclaration));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD:
                if (resolve) return getField();
                return basicGetField();
            case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION:
                if (resolve) return getInnerDeclaration();
                return basicGetInnerDeclaration();
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
            case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD:
                setField((Field)newValue);
                return;
            case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION:
                setInnerDeclaration((InnerDeclaration)newValue);
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
            case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD:
                setField((Field)null);
                return;
            case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION:
                setInnerDeclaration((InnerDeclaration)null);
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
            case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD:
                return field != null;
            case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION:
                return innerDeclaration != null;
        }
        return super.eIsSet(featureID);
    }

} //InnerDatatypeSourceCodeLinkImpl
