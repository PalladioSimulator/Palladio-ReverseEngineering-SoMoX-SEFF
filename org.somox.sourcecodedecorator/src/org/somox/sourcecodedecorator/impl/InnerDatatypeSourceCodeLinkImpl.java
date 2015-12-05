/**
 */
package org.somox.sourcecodedecorator.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.emftext.language.java.members.Field;
import org.palladiosimulator.pcm.repository.InnerDeclaration;
import org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Inner Datatype Source Code Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.somox.sourcecodedecorator.impl.InnerDatatypeSourceCodeLinkImpl#getField
 * <em>Field</em>}</li>
 * <li>{@link org.somox.sourcecodedecorator.impl.InnerDatatypeSourceCodeLinkImpl#getInnerDeclaration
 * <em>Inner Declaration</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InnerDatatypeSourceCodeLinkImpl extends MinimalEObjectImpl.Container
        implements InnerDatatypeSourceCodeLink {
    /**
     * The cached value of the '{@link #getField() <em>Field</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getField()
     * @generated
     * @ordered
     */
    protected Field field;

    /**
     * The cached value of the '{@link #getInnerDeclaration() <em>Inner Declaration</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInnerDeclaration()
     * @generated
     * @ordered
     */
    protected InnerDeclaration innerDeclaration;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InnerDatatypeSourceCodeLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.INNER_DATATYPE_SOURCE_CODE_LINK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Field getField() {
        if (this.field != null && this.field.eIsProxy()) {
            final InternalEObject oldField = (InternalEObject) this.field;
            this.field = (Field) this.eResolveProxy(oldField);
            if (this.field != oldField) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD, oldField, this.field));
                }
            }
        }
        return this.field;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Field basicGetField() {
        return this.field;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setField(final Field newField) {
        final Field oldField = this.field;
        this.field = newField;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD, oldField, this.field));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InnerDeclaration getInnerDeclaration() {
        if (this.innerDeclaration != null && ((EObject) this.innerDeclaration).eIsProxy()) {
            final InternalEObject oldInnerDeclaration = (InternalEObject) this.innerDeclaration;
            this.innerDeclaration = (InnerDeclaration) this.eResolveProxy(oldInnerDeclaration);
            if (this.innerDeclaration != oldInnerDeclaration) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION,
                            oldInnerDeclaration, this.innerDeclaration));
                }
            }
        }
        return this.innerDeclaration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InnerDeclaration basicGetInnerDeclaration() {
        return this.innerDeclaration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInnerDeclaration(final InnerDeclaration newInnerDeclaration) {
        final InnerDeclaration oldInnerDeclaration = this.innerDeclaration;
        this.innerDeclaration = newInnerDeclaration;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION, oldInnerDeclaration,
                    this.innerDeclaration));
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
        case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD:
            if (resolve) {
                return this.getField();
            }
            return this.basicGetField();
        case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION:
            if (resolve) {
                return this.getInnerDeclaration();
            }
            return this.basicGetInnerDeclaration();
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
        case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD:
            this.setField((Field) newValue);
            return;
        case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION:
            this.setInnerDeclaration((InnerDeclaration) newValue);
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
        case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD:
            this.setField((Field) null);
            return;
        case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION:
            this.setInnerDeclaration((InnerDeclaration) null);
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
        case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__FIELD:
            return this.field != null;
        case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK__INNER_DECLARATION:
            return this.innerDeclaration != null;
        }
        return super.eIsSet(featureID);
    }

} // InnerDatatypeSourceCodeLinkImpl
