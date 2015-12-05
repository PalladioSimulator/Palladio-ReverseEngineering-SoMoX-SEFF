/**
 */
package org.somox.sourcecodedecorator.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Component Implementing Classes Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl#isIsCompositeComponent
 * <em>Is Composite Component</em>}</li>
 * <li>{@link org.somox.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl#getComponent
 * <em>Component</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl#getImplementingClasses
 * <em>Implementing Classes</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl#getSubComponents
 * <em>Sub Components</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl#getProvidedInterfaces
 * <em>Provided Interfaces</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl#getRequiredInterfaces
 * <em>Required Interfaces</em>}</li>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.ComponentImplementingClassesLinkImpl#isIsInitialComponent
 * <em>Is Initial Component</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComponentImplementingClassesLinkImpl extends MinimalEObjectImpl.Container
        implements ComponentImplementingClassesLink {
    /**
     * The default value of the '{@link #isIsCompositeComponent() <em>Is Composite Component</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isIsCompositeComponent()
     * @generated
     * @ordered
     */
    protected static final boolean IS_COMPOSITE_COMPONENT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #getComponent() <em>Component</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getComponent()
     * @generated
     * @ordered
     */
    protected RepositoryComponent component;

    /**
     * The cached value of the '{@link #getImplementingClasses() <em>Implementing Classes</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getImplementingClasses()
     * @generated
     * @ordered
     */
    protected EList<ConcreteClassifier> implementingClasses;

    /**
     * The cached value of the '{@link #getSubComponents() <em>Sub Components</em>}' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSubComponents()
     * @generated
     * @ordered
     */
    protected EList<ComponentImplementingClassesLink> subComponents;

    /**
     * The cached value of the '{@link #getProvidedInterfaces() <em>Provided Interfaces</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getProvidedInterfaces()
     * @generated
     * @ordered
     */
    protected EList<InterfaceSourceCodeLink> providedInterfaces;

    /**
     * The cached value of the '{@link #getRequiredInterfaces() <em>Required Interfaces</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRequiredInterfaces()
     * @generated
     * @ordered
     */
    protected EList<InterfaceSourceCodeLink> requiredInterfaces;

    /**
     * The default value of the '{@link #isIsInitialComponent() <em>Is Initial Component</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isIsInitialComponent()
     * @generated
     * @ordered
     */
    protected static final boolean IS_INITIAL_COMPONENT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsInitialComponent() <em>Is Initial Component</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isIsInitialComponent()
     * @generated
     * @ordered
     */
    protected boolean isInitialComponent = IS_INITIAL_COMPONENT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ComponentImplementingClassesLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.COMPONENT_IMPLEMENTING_CLASSES_LINK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated not
     */
    @Override
    public boolean isIsCompositeComponent() {
        return this.getSubComponents().size() > 0;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSetIsCompositeComponent() {
        // TODO: implement this method to return whether the 'Is Composite Component' attribute is
        // set
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RepositoryComponent getComponent() {
        if (this.component != null && ((EObject) this.component).eIsProxy()) {
            final InternalEObject oldComponent = (InternalEObject) this.component;
            this.component = (RepositoryComponent) this.eResolveProxy(oldComponent);
            if (this.component != oldComponent) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT, oldComponent,
                            this.component));
                }
            }
        }
        return this.component;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public RepositoryComponent basicGetComponent() {
        return this.component;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setComponent(final RepositoryComponent newComponent) {
        final RepositoryComponent oldComponent = this.component;
        this.component = newComponent;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT, oldComponent,
                    this.component));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ConcreteClassifier> getImplementingClasses() {
        if (this.implementingClasses == null) {
            this.implementingClasses = new EObjectResolvingEList<ConcreteClassifier>(ConcreteClassifier.class, this,
                    SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES);
        }
        return this.implementingClasses;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ComponentImplementingClassesLink> getSubComponents() {
        if (this.subComponents == null) {
            this.subComponents = new EObjectResolvingEList<ComponentImplementingClassesLink>(
                    ComponentImplementingClassesLink.class, this,
                    SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS);
        }
        return this.subComponents;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<InterfaceSourceCodeLink> getProvidedInterfaces() {
        if (this.providedInterfaces == null) {
            this.providedInterfaces = new EObjectResolvingEList<InterfaceSourceCodeLink>(InterfaceSourceCodeLink.class,
                    this, SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES);
        }
        return this.providedInterfaces;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<InterfaceSourceCodeLink> getRequiredInterfaces() {
        if (this.requiredInterfaces == null) {
            this.requiredInterfaces = new EObjectResolvingEList<InterfaceSourceCodeLink>(InterfaceSourceCodeLink.class,
                    this, SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES);
        }
        return this.requiredInterfaces;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isIsInitialComponent() {
        return this.isInitialComponent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIsInitialComponent(final boolean newIsInitialComponent) {
        final boolean oldIsInitialComponent = this.isInitialComponent;
        this.isInitialComponent = newIsInitialComponent;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT,
                    oldIsInitialComponent, this.isInitialComponent));
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
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_COMPOSITE_COMPONENT:
            return this.isIsCompositeComponent();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT:
            if (resolve) {
                return this.getComponent();
            }
            return this.basicGetComponent();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES:
            return this.getImplementingClasses();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS:
            return this.getSubComponents();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES:
            return this.getProvidedInterfaces();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES:
            return this.getRequiredInterfaces();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT:
            return this.isIsInitialComponent();
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
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT:
            this.setComponent((RepositoryComponent) newValue);
            return;
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES:
            this.getImplementingClasses().clear();
            this.getImplementingClasses().addAll((Collection<? extends ConcreteClassifier>) newValue);
            return;
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS:
            this.getSubComponents().clear();
            this.getSubComponents().addAll((Collection<? extends ComponentImplementingClassesLink>) newValue);
            return;
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES:
            this.getProvidedInterfaces().clear();
            this.getProvidedInterfaces().addAll((Collection<? extends InterfaceSourceCodeLink>) newValue);
            return;
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES:
            this.getRequiredInterfaces().clear();
            this.getRequiredInterfaces().addAll((Collection<? extends InterfaceSourceCodeLink>) newValue);
            return;
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT:
            this.setIsInitialComponent((Boolean) newValue);
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
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT:
            this.setComponent((RepositoryComponent) null);
            return;
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES:
            this.getImplementingClasses().clear();
            return;
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS:
            this.getSubComponents().clear();
            return;
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES:
            this.getProvidedInterfaces().clear();
            return;
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES:
            this.getRequiredInterfaces().clear();
            return;
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT:
            this.setIsInitialComponent(IS_INITIAL_COMPONENT_EDEFAULT);
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
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_COMPOSITE_COMPONENT:
            return this.isSetIsCompositeComponent();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT:
            return this.component != null;
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES:
            return this.implementingClasses != null && !this.implementingClasses.isEmpty();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS:
            return this.subComponents != null && !this.subComponents.isEmpty();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES:
            return this.providedInterfaces != null && !this.providedInterfaces.isEmpty();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES:
            return this.requiredInterfaces != null && !this.requiredInterfaces.isEmpty();
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT:
            return this.isInitialComponent != IS_INITIAL_COMPONENT_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }

        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (isInitialComponent: ");
        result.append(this.isInitialComponent);
        result.append(')');
        return result.toString();
    }

} // ComponentImplementingClassesLinkImpl
