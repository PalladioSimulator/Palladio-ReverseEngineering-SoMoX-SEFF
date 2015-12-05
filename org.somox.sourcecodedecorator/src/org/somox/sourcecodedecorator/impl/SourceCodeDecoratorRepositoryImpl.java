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
import org.somox.sourcecodedecorator.AbstractActionClassMethodLink;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink;
import org.somox.sourcecodedecorator.DataTypeSourceCodeLink;
import org.somox.sourcecodedecorator.FileLevelSourceCodeLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.MethodLevelResourceDemandingInternalBehaviorLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Source Code Decorator Repository</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getFileLevelSourceCodeLink
 * <em>File Level Source Code Link</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getMethodLevelSourceCodeLink
 * <em>Method Level Source Code Link</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getControlFlowLevelSourceCodeLink
 * <em>Control Flow Level Source Code Link</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getInterfaceSourceCodeLink
 * <em>Interface Source Code Link</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getComponentImplementingClassesLink
 * <em>Component Implementing Classes Link</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getDataTypeSourceCodeLink
 * <em>Data Type Source Code Link</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getAbstractActionClassMethodLink
 * <em>Abstract Action Class Method Link</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.SourceCodeDecoratorRepositoryImpl#getMethodLevelResourceDemandingInternalBehaviorLink
 * <em>Method Level Resource Demanding Internal Behavior Link</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SourceCodeDecoratorRepositoryImpl extends MinimalEObjectImpl.Container
        implements SourceCodeDecoratorRepository {
    /**
     * The cached value of the '{@link #getFileLevelSourceCodeLink()
     * <em>File Level Source Code Link</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getFileLevelSourceCodeLink()
     * @generated
     * @ordered
     */
    protected EList<FileLevelSourceCodeLink> fileLevelSourceCodeLink;

    /**
     * The cached value of the '{@link #getMethodLevelSourceCodeLink()
     * <em>Method Level Source Code Link</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getMethodLevelSourceCodeLink()
     * @generated
     * @ordered
     */
    protected EList<MethodLevelSourceCodeLink> methodLevelSourceCodeLink;

    /**
     * The cached value of the '{@link #getControlFlowLevelSourceCodeLink()
     * <em>Control Flow Level Source Code Link</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getControlFlowLevelSourceCodeLink()
     * @generated
     * @ordered
     */
    protected EList<ControlFlowLevelSourceCodeLink> controlFlowLevelSourceCodeLink;

    /**
     * The cached value of the '{@link #getInterfaceSourceCodeLink()
     * <em>Interface Source Code Link</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getInterfaceSourceCodeLink()
     * @generated
     * @ordered
     */
    protected EList<InterfaceSourceCodeLink> interfaceSourceCodeLink;

    /**
     * The cached value of the '{@link #getComponentImplementingClassesLink()
     * <em>Component Implementing Classes Link</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getComponentImplementingClassesLink()
     * @generated
     * @ordered
     */
    protected EList<ComponentImplementingClassesLink> componentImplementingClassesLink;

    /**
     * The cached value of the '{@link #getDataTypeSourceCodeLink()
     * <em>Data Type Source Code Link</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDataTypeSourceCodeLink()
     * @generated
     * @ordered
     */
    protected EList<DataTypeSourceCodeLink> dataTypeSourceCodeLink;

    /**
     * The cached value of the '{@link #getAbstractActionClassMethodLink()
     * <em>Abstract Action Class Method Link</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getAbstractActionClassMethodLink()
     * @generated
     * @ordered
     */
    protected EList<AbstractActionClassMethodLink> abstractActionClassMethodLink;

    /**
     * The cached value of the '{@link #getMethodLevelResourceDemandingInternalBehaviorLink()
     * <em>Method Level Resource Demanding Internal Behavior Link</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMethodLevelResourceDemandingInternalBehaviorLink()
     * @generated
     * @ordered
     */
    protected EList<MethodLevelResourceDemandingInternalBehaviorLink> methodLevelResourceDemandingInternalBehaviorLink;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected SourceCodeDecoratorRepositoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<FileLevelSourceCodeLink> getFileLevelSourceCodeLink() {
        if (this.fileLevelSourceCodeLink == null) {
            this.fileLevelSourceCodeLink = new EObjectContainmentEList<FileLevelSourceCodeLink>(
                    FileLevelSourceCodeLink.class, this,
                    SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK);
        }
        return this.fileLevelSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<MethodLevelSourceCodeLink> getMethodLevelSourceCodeLink() {
        if (this.methodLevelSourceCodeLink == null) {
            this.methodLevelSourceCodeLink = new EObjectContainmentEList<MethodLevelSourceCodeLink>(
                    MethodLevelSourceCodeLink.class, this,
                    SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK);
        }
        return this.methodLevelSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ControlFlowLevelSourceCodeLink> getControlFlowLevelSourceCodeLink() {
        if (this.controlFlowLevelSourceCodeLink == null) {
            this.controlFlowLevelSourceCodeLink = new EObjectContainmentEList<ControlFlowLevelSourceCodeLink>(
                    ControlFlowLevelSourceCodeLink.class, this,
                    SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK);
        }
        return this.controlFlowLevelSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<InterfaceSourceCodeLink> getInterfaceSourceCodeLink() {
        if (this.interfaceSourceCodeLink == null) {
            this.interfaceSourceCodeLink = new EObjectContainmentEList<InterfaceSourceCodeLink>(
                    InterfaceSourceCodeLink.class, this,
                    SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK);
        }
        return this.interfaceSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ComponentImplementingClassesLink> getComponentImplementingClassesLink() {
        if (this.componentImplementingClassesLink == null) {
            this.componentImplementingClassesLink = new EObjectContainmentEList<ComponentImplementingClassesLink>(
                    ComponentImplementingClassesLink.class, this,
                    SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK);
        }
        return this.componentImplementingClassesLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DataTypeSourceCodeLink> getDataTypeSourceCodeLink() {
        if (this.dataTypeSourceCodeLink == null) {
            this.dataTypeSourceCodeLink = new EObjectContainmentEList<DataTypeSourceCodeLink>(
                    DataTypeSourceCodeLink.class, this,
                    SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK);
        }
        return this.dataTypeSourceCodeLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AbstractActionClassMethodLink> getAbstractActionClassMethodLink() {
        if (this.abstractActionClassMethodLink == null) {
            this.abstractActionClassMethodLink = new EObjectContainmentEList<AbstractActionClassMethodLink>(
                    AbstractActionClassMethodLink.class, this,
                    SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK);
        }
        return this.abstractActionClassMethodLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<MethodLevelResourceDemandingInternalBehaviorLink> getMethodLevelResourceDemandingInternalBehaviorLink() {
        if (this.methodLevelResourceDemandingInternalBehaviorLink == null) {
            this.methodLevelResourceDemandingInternalBehaviorLink = new EObjectContainmentEList<MethodLevelResourceDemandingInternalBehaviorLink>(
                    MethodLevelResourceDemandingInternalBehaviorLink.class, this,
                    SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK);
        }
        return this.methodLevelResourceDemandingInternalBehaviorLink;
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
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK:
            return ((InternalEList<?>) this.getFileLevelSourceCodeLink()).basicRemove(otherEnd, msgs);
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK:
            return ((InternalEList<?>) this.getMethodLevelSourceCodeLink()).basicRemove(otherEnd, msgs);
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
            return ((InternalEList<?>) this.getControlFlowLevelSourceCodeLink()).basicRemove(otherEnd, msgs);
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK:
            return ((InternalEList<?>) this.getInterfaceSourceCodeLink()).basicRemove(otherEnd, msgs);
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK:
            return ((InternalEList<?>) this.getComponentImplementingClassesLink()).basicRemove(otherEnd, msgs);
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK:
            return ((InternalEList<?>) this.getDataTypeSourceCodeLink()).basicRemove(otherEnd, msgs);
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK:
            return ((InternalEList<?>) this.getAbstractActionClassMethodLink()).basicRemove(otherEnd, msgs);
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK:
            return ((InternalEList<?>) this.getMethodLevelResourceDemandingInternalBehaviorLink()).basicRemove(otherEnd,
                    msgs);
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
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK:
            return this.getFileLevelSourceCodeLink();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK:
            return this.getMethodLevelSourceCodeLink();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
            return this.getControlFlowLevelSourceCodeLink();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK:
            return this.getInterfaceSourceCodeLink();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK:
            return this.getComponentImplementingClassesLink();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK:
            return this.getDataTypeSourceCodeLink();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK:
            return this.getAbstractActionClassMethodLink();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK:
            return this.getMethodLevelResourceDemandingInternalBehaviorLink();
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
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK:
            this.getFileLevelSourceCodeLink().clear();
            this.getFileLevelSourceCodeLink().addAll((Collection<? extends FileLevelSourceCodeLink>) newValue);
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK:
            this.getMethodLevelSourceCodeLink().clear();
            this.getMethodLevelSourceCodeLink().addAll((Collection<? extends MethodLevelSourceCodeLink>) newValue);
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
            this.getControlFlowLevelSourceCodeLink().clear();
            this.getControlFlowLevelSourceCodeLink()
                    .addAll((Collection<? extends ControlFlowLevelSourceCodeLink>) newValue);
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK:
            this.getInterfaceSourceCodeLink().clear();
            this.getInterfaceSourceCodeLink().addAll((Collection<? extends InterfaceSourceCodeLink>) newValue);
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK:
            this.getComponentImplementingClassesLink().clear();
            this.getComponentImplementingClassesLink()
                    .addAll((Collection<? extends ComponentImplementingClassesLink>) newValue);
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK:
            this.getDataTypeSourceCodeLink().clear();
            this.getDataTypeSourceCodeLink().addAll((Collection<? extends DataTypeSourceCodeLink>) newValue);
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK:
            this.getAbstractActionClassMethodLink().clear();
            this.getAbstractActionClassMethodLink()
                    .addAll((Collection<? extends AbstractActionClassMethodLink>) newValue);
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK:
            this.getMethodLevelResourceDemandingInternalBehaviorLink().clear();
            this.getMethodLevelResourceDemandingInternalBehaviorLink()
                    .addAll((Collection<? extends MethodLevelResourceDemandingInternalBehaviorLink>) newValue);
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
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK:
            this.getFileLevelSourceCodeLink().clear();
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK:
            this.getMethodLevelSourceCodeLink().clear();
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
            this.getControlFlowLevelSourceCodeLink().clear();
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK:
            this.getInterfaceSourceCodeLink().clear();
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK:
            this.getComponentImplementingClassesLink().clear();
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK:
            this.getDataTypeSourceCodeLink().clear();
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK:
            this.getAbstractActionClassMethodLink().clear();
            return;
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK:
            this.getMethodLevelResourceDemandingInternalBehaviorLink().clear();
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
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK:
            return this.fileLevelSourceCodeLink != null && !this.fileLevelSourceCodeLink.isEmpty();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK:
            return this.methodLevelSourceCodeLink != null && !this.methodLevelSourceCodeLink.isEmpty();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
            return this.controlFlowLevelSourceCodeLink != null && !this.controlFlowLevelSourceCodeLink.isEmpty();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK:
            return this.interfaceSourceCodeLink != null && !this.interfaceSourceCodeLink.isEmpty();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK:
            return this.componentImplementingClassesLink != null && !this.componentImplementingClassesLink.isEmpty();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK:
            return this.dataTypeSourceCodeLink != null && !this.dataTypeSourceCodeLink.isEmpty();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK:
            return this.abstractActionClassMethodLink != null && !this.abstractActionClassMethodLink.isEmpty();
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK:
            return this.methodLevelResourceDemandingInternalBehaviorLink != null
                    && !this.methodLevelResourceDemandingInternalBehaviorLink.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // SourceCodeDecoratorRepositoryImpl
