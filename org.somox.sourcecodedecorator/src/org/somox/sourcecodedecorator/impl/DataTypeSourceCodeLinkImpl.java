/**
 */
package org.somox.sourcecodedecorator.impl;

import org.palladiosimulator.pcm.repository.DataType;
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
import org.somox.sourcecodedecorator.DataTypeSourceCodeLink;
import org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Type Source Code Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.impl.DataTypeSourceCodeLinkImpl#getJaMoPPType <em>Ja Mo PP Type</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.DataTypeSourceCodeLinkImpl#getPcmDataType <em>Pcm Data Type</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.DataTypeSourceCodeLinkImpl#getInnerDatatypeSourceCodeLink <em>Inner Datatype Source Code Link</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DataTypeSourceCodeLinkImpl extends FileLevelSourceCodeLinkImpl implements DataTypeSourceCodeLink {
    /**
     * The cached value of the '{@link #getJaMoPPType() <em>Ja Mo PP Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJaMoPPType()
     * @generated
     * @ordered
     */
    protected Type jaMoPPType;

    /**
     * The cached value of the '{@link #getPcmDataType() <em>Pcm Data Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPcmDataType()
     * @generated
     * @ordered
     */
    protected DataType pcmDataType;

    /**
     * The cached value of the '{@link #getInnerDatatypeSourceCodeLink() <em>Inner Datatype Source Code Link</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInnerDatatypeSourceCodeLink()
     * @generated
     * @ordered
     */
    protected EList<InnerDatatypeSourceCodeLink> innerDatatypeSourceCodeLink;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataTypeSourceCodeLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.DATA_TYPE_SOURCE_CODE_LINK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Type getJaMoPPType() {
        if (jaMoPPType != null && jaMoPPType.eIsProxy()) {
            InternalEObject oldJaMoPPType = (InternalEObject)jaMoPPType;
            jaMoPPType = (Type)eResolveProxy(oldJaMoPPType);
            if (jaMoPPType != oldJaMoPPType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE, oldJaMoPPType, jaMoPPType));
            }
        }
        return jaMoPPType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Type basicGetJaMoPPType() {
        return jaMoPPType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setJaMoPPType(Type newJaMoPPType) {
        Type oldJaMoPPType = jaMoPPType;
        jaMoPPType = newJaMoPPType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE, oldJaMoPPType, jaMoPPType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataType getPcmDataType() {
        if (pcmDataType != null && ((EObject)pcmDataType).eIsProxy()) {
            InternalEObject oldPcmDataType = (InternalEObject)pcmDataType;
            pcmDataType = (DataType)eResolveProxy(oldPcmDataType);
            if (pcmDataType != oldPcmDataType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE, oldPcmDataType, pcmDataType));
            }
        }
        return pcmDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataType basicGetPcmDataType() {
        return pcmDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPcmDataType(DataType newPcmDataType) {
        DataType oldPcmDataType = pcmDataType;
        pcmDataType = newPcmDataType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE, oldPcmDataType, pcmDataType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<InnerDatatypeSourceCodeLink> getInnerDatatypeSourceCodeLink() {
        if (innerDatatypeSourceCodeLink == null) {
            innerDatatypeSourceCodeLink = new EObjectContainmentEList<InnerDatatypeSourceCodeLink>(InnerDatatypeSourceCodeLink.class, this, SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK);
        }
        return innerDatatypeSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK:
                return ((InternalEList<?>)getInnerDatatypeSourceCodeLink()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE:
                if (resolve) return getJaMoPPType();
                return basicGetJaMoPPType();
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE:
                if (resolve) return getPcmDataType();
                return basicGetPcmDataType();
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK:
                return getInnerDatatypeSourceCodeLink();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE:
                setJaMoPPType((Type)newValue);
                return;
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE:
                setPcmDataType((DataType)newValue);
                return;
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK:
                getInnerDatatypeSourceCodeLink().clear();
                getInnerDatatypeSourceCodeLink().addAll((Collection<? extends InnerDatatypeSourceCodeLink>)newValue);
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
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE:
                setJaMoPPType((Type)null);
                return;
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE:
                setPcmDataType((DataType)null);
                return;
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK:
                getInnerDatatypeSourceCodeLink().clear();
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
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE:
                return jaMoPPType != null;
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE:
                return pcmDataType != null;
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK:
                return innerDatatypeSourceCodeLink != null && !innerDatatypeSourceCodeLink.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //DataTypeSourceCodeLinkImpl
