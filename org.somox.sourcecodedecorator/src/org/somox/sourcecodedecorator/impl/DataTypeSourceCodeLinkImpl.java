/**
 */
package org.somox.sourcecodedecorator.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.emftext.language.java.types.Type;
import org.palladiosimulator.pcm.repository.DataType;
import org.somox.sourcecodedecorator.DataTypeSourceCodeLink;
import org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Data Type Source Code Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.somox.sourcecodedecorator.impl.DataTypeSourceCodeLinkImpl#getJaMoPPType
 * <em>Ja Mo PP Type</em>}</li>
 * <li>{@link org.somox.sourcecodedecorator.impl.DataTypeSourceCodeLinkImpl#getPcmDataType
 * <em>Pcm Data Type</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.DataTypeSourceCodeLinkImpl#getInnerDatatypeSourceCodeLink
 * <em>Inner Datatype Source Code Link</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DataTypeSourceCodeLinkImpl extends FileLevelSourceCodeLinkImpl implements DataTypeSourceCodeLink {
    /**
     * The cached value of the '{@link #getJaMoPPType() <em>Ja Mo PP Type</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getJaMoPPType()
     * @generated
     * @ordered
     */
    protected Type jaMoPPType;

    /**
     * The cached value of the '{@link #getPcmDataType() <em>Pcm Data Type</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPcmDataType()
     * @generated
     * @ordered
     */
    protected DataType pcmDataType;

    /**
     * The cached value of the '{@link #getInnerDatatypeSourceCodeLink()
     * <em>Inner Datatype Source Code Link</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getInnerDatatypeSourceCodeLink()
     * @generated
     * @ordered
     */
    protected EList<InnerDatatypeSourceCodeLink> innerDatatypeSourceCodeLink;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DataTypeSourceCodeLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.DATA_TYPE_SOURCE_CODE_LINK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Type getJaMoPPType() {
        if (this.jaMoPPType != null && this.jaMoPPType.eIsProxy()) {
            final InternalEObject oldJaMoPPType = (InternalEObject) this.jaMoPPType;
            this.jaMoPPType = (Type) this.eResolveProxy(oldJaMoPPType);
            if (this.jaMoPPType != oldJaMoPPType) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE, oldJaMoPPType,
                            this.jaMoPPType));
                }
            }
        }
        return this.jaMoPPType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Type basicGetJaMoPPType() {
        return this.jaMoPPType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setJaMoPPType(final Type newJaMoPPType) {
        final Type oldJaMoPPType = this.jaMoPPType;
        this.jaMoPPType = newJaMoPPType;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE, oldJaMoPPType,
                    this.jaMoPPType));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DataType getPcmDataType() {
        if (this.pcmDataType != null && ((EObject) this.pcmDataType).eIsProxy()) {
            final InternalEObject oldPcmDataType = (InternalEObject) this.pcmDataType;
            this.pcmDataType = (DataType) this.eResolveProxy(oldPcmDataType);
            if (this.pcmDataType != oldPcmDataType) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE, oldPcmDataType,
                            this.pcmDataType));
                }
            }
        }
        return this.pcmDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DataType basicGetPcmDataType() {
        return this.pcmDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setPcmDataType(final DataType newPcmDataType) {
        final DataType oldPcmDataType = this.pcmDataType;
        this.pcmDataType = newPcmDataType;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE, oldPcmDataType,
                    this.pcmDataType));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<InnerDatatypeSourceCodeLink> getInnerDatatypeSourceCodeLink() {
        if (this.innerDatatypeSourceCodeLink == null) {
            this.innerDatatypeSourceCodeLink = new EObjectContainmentEList<InnerDatatypeSourceCodeLink>(
                    InnerDatatypeSourceCodeLink.class, this,
                    SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK);
        }
        return this.innerDatatypeSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK:
            return ((InternalEList<?>) this.getInnerDatatypeSourceCodeLink()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE:
            if (resolve) {
                return this.getJaMoPPType();
            }
            return this.basicGetJaMoPPType();
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE:
            if (resolve) {
                return this.getPcmDataType();
            }
            return this.basicGetPcmDataType();
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK:
            return this.getInnerDatatypeSourceCodeLink();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE:
            this.setJaMoPPType((Type) newValue);
            return;
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE:
            this.setPcmDataType((DataType) newValue);
            return;
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK:
            this.getInnerDatatypeSourceCodeLink().clear();
            this.getInnerDatatypeSourceCodeLink().addAll((Collection<? extends InnerDatatypeSourceCodeLink>) newValue);
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
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE:
            this.setJaMoPPType((Type) null);
            return;
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE:
            this.setPcmDataType((DataType) null);
            return;
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK:
            this.getInnerDatatypeSourceCodeLink().clear();
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
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE:
            return this.jaMoPPType != null;
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE:
            return this.pcmDataType != null;
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK:
            return this.innerDatatypeSourceCodeLink != null && !this.innerDatatypeSourceCodeLink.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // DataTypeSourceCodeLinkImpl
