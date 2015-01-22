/**
 */
package org.somox.sourcecodedecorator.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink;
import org.somox.sourcecodedecorator.DataTypeSourceCodeLink;
import org.somox.sourcecodedecorator.FileLevelSourceCodeLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Source Code Decorator Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getFileLevelSourceCodeLink <em>File Level Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getMethodLevelSourceCodeLink <em>Method Level Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getControlFlowLevelSourceCodeLink <em>Control Flow Level Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getInterfaceSourceCodeLink <em>Interface Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getComponentImplementingClassesLink <em>Component Implementing Classes Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getDataTypeSourceCodeLink <em>Data Type Source Code Link</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SourceCodeDecoratorRepositoryImpl extends MinimalEObjectImpl.Container implements SourceCodeDecoratorRepository {
	/**
     * The cached value of the '{@link #getFileLevelSourceCodeLink() <em>File Level Source Code Link</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFileLevelSourceCodeLink()
     * @generated
     * @ordered
     */
	protected EList<FileLevelSourceCodeLink> fileLevelSourceCodeLink;

	/**
     * The cached value of the '{@link #getMethodLevelSourceCodeLink() <em>Method Level Source Code Link</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMethodLevelSourceCodeLink()
     * @generated
     * @ordered
     */
	protected EList<MethodLevelSourceCodeLink> methodLevelSourceCodeLink;

	/**
     * The cached value of the '{@link #getControlFlowLevelSourceCodeLink() <em>Control Flow Level Source Code Link</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getControlFlowLevelSourceCodeLink()
     * @generated
     * @ordered
     */
	protected EList<ControlFlowLevelSourceCodeLink> controlFlowLevelSourceCodeLink;

	/**
     * The cached value of the '{@link #getInterfaceSourceCodeLink() <em>Interface Source Code Link</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInterfaceSourceCodeLink()
     * @generated
     * @ordered
     */
	protected EList<InterfaceSourceCodeLink> interfaceSourceCodeLink;

	/**
     * The cached value of the '{@link #getComponentImplementingClassesLink() <em>Component Implementing Classes Link</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getComponentImplementingClassesLink()
     * @generated
     * @ordered
     */
	protected EList<ComponentImplementingClassesLink> componentImplementingClassesLink;

	/**
     * The cached value of the '{@link #getDataTypeSourceCodeLink() <em>Data Type Source Code Link</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataTypeSourceCodeLink()
     * @generated
     * @ordered
     */
    protected EList<DataTypeSourceCodeLink> dataTypeSourceCodeLink;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SourceCodeDecoratorRepositoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<FileLevelSourceCodeLink> getFileLevelSourceCodeLink() {
        if (fileLevelSourceCodeLink == null) {
            fileLevelSourceCodeLink = new EObjectContainmentEList<FileLevelSourceCodeLink>(FileLevelSourceCodeLink.class, this, SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK);
        }
        return fileLevelSourceCodeLink;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<MethodLevelSourceCodeLink> getMethodLevelSourceCodeLink() {
        if (methodLevelSourceCodeLink == null) {
            methodLevelSourceCodeLink = new EObjectContainmentEList<MethodLevelSourceCodeLink>(MethodLevelSourceCodeLink.class, this, SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK);
        }
        return methodLevelSourceCodeLink;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<ControlFlowLevelSourceCodeLink> getControlFlowLevelSourceCodeLink() {
        if (controlFlowLevelSourceCodeLink == null) {
            controlFlowLevelSourceCodeLink = new EObjectContainmentEList<ControlFlowLevelSourceCodeLink>(ControlFlowLevelSourceCodeLink.class, this, SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK);
        }
        return controlFlowLevelSourceCodeLink;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<InterfaceSourceCodeLink> getInterfaceSourceCodeLink() {
        if (interfaceSourceCodeLink == null) {
            interfaceSourceCodeLink = new EObjectContainmentEList<InterfaceSourceCodeLink>(InterfaceSourceCodeLink.class, this, SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK);
        }
        return interfaceSourceCodeLink;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<ComponentImplementingClassesLink> getComponentImplementingClassesLink() {
        if (componentImplementingClassesLink == null) {
            componentImplementingClassesLink = new EObjectContainmentEList<ComponentImplementingClassesLink>(ComponentImplementingClassesLink.class, this, SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK);
        }
        return componentImplementingClassesLink;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<DataTypeSourceCodeLink> getDataTypeSourceCodeLink() {
        if (dataTypeSourceCodeLink == null) {
            dataTypeSourceCodeLink = new EObjectContainmentEList<DataTypeSourceCodeLink>(DataTypeSourceCodeLink.class, this, SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK);
        }
        return dataTypeSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK:
                return ((InternalEList<?>)getFileLevelSourceCodeLink()).basicRemove(otherEnd, msgs);
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK:
                return ((InternalEList<?>)getMethodLevelSourceCodeLink()).basicRemove(otherEnd, msgs);
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
                return ((InternalEList<?>)getControlFlowLevelSourceCodeLink()).basicRemove(otherEnd, msgs);
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK:
                return ((InternalEList<?>)getInterfaceSourceCodeLink()).basicRemove(otherEnd, msgs);
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK:
                return ((InternalEList<?>)getComponentImplementingClassesLink()).basicRemove(otherEnd, msgs);
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK:
                return ((InternalEList<?>)getDataTypeSourceCodeLink()).basicRemove(otherEnd, msgs);
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
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK:
                return getFileLevelSourceCodeLink();
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK:
                return getMethodLevelSourceCodeLink();
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
                return getControlFlowLevelSourceCodeLink();
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK:
                return getInterfaceSourceCodeLink();
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK:
                return getComponentImplementingClassesLink();
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK:
                return getDataTypeSourceCodeLink();
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
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK:
                getFileLevelSourceCodeLink().clear();
                getFileLevelSourceCodeLink().addAll((Collection<? extends FileLevelSourceCodeLink>)newValue);
                return;
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK:
                getMethodLevelSourceCodeLink().clear();
                getMethodLevelSourceCodeLink().addAll((Collection<? extends MethodLevelSourceCodeLink>)newValue);
                return;
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
                getControlFlowLevelSourceCodeLink().clear();
                getControlFlowLevelSourceCodeLink().addAll((Collection<? extends ControlFlowLevelSourceCodeLink>)newValue);
                return;
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK:
                getInterfaceSourceCodeLink().clear();
                getInterfaceSourceCodeLink().addAll((Collection<? extends InterfaceSourceCodeLink>)newValue);
                return;
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK:
                getComponentImplementingClassesLink().clear();
                getComponentImplementingClassesLink().addAll((Collection<? extends ComponentImplementingClassesLink>)newValue);
                return;
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK:
                getDataTypeSourceCodeLink().clear();
                getDataTypeSourceCodeLink().addAll((Collection<? extends DataTypeSourceCodeLink>)newValue);
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
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK:
                getFileLevelSourceCodeLink().clear();
                return;
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK:
                getMethodLevelSourceCodeLink().clear();
                return;
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
                getControlFlowLevelSourceCodeLink().clear();
                return;
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK:
                getInterfaceSourceCodeLink().clear();
                return;
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK:
                getComponentImplementingClassesLink().clear();
                return;
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK:
                getDataTypeSourceCodeLink().clear();
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
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK:
                return fileLevelSourceCodeLink != null && !fileLevelSourceCodeLink.isEmpty();
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK:
                return methodLevelSourceCodeLink != null && !methodLevelSourceCodeLink.isEmpty();
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
                return controlFlowLevelSourceCodeLink != null && !controlFlowLevelSourceCodeLink.isEmpty();
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK:
                return interfaceSourceCodeLink != null && !interfaceSourceCodeLink.isEmpty();
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK:
                return componentImplementingClassesLink != null && !componentImplementingClassesLink.isEmpty();
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK:
                return dataTypeSourceCodeLink != null && !dataTypeSourceCodeLink.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SourceCodeDecoratorRepositoryImpl
