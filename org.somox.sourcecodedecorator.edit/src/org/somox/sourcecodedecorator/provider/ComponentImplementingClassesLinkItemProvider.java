/**
 */
package org.somox.sourcecodedecorator.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * This is the item provider adapter for a
 * {@link org.somox.sourcecodedecorator.ComponentImplementingClassesLink} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class ComponentImplementingClassesLinkItemProvider extends ItemProviderAdapter
        implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
        IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public ComponentImplementingClassesLinkItemProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(final Object object) {
        if (this.itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            this.addIsCompositeComponentPropertyDescriptor(object);
            this.addComponentPropertyDescriptor(object);
            this.addImplementingClassesPropertyDescriptor(object);
            this.addSubComponentsPropertyDescriptor(object);
            this.addProvidedInterfacesPropertyDescriptor(object);
            this.addRequiredInterfacesPropertyDescriptor(object);
            this.addIsInitialComponentPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Is Composite Component feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addIsCompositeComponentPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_ComponentImplementingClassesLink_isCompositeComponent_feature"),
                this.getString("_UI_PropertyDescriptor_description",
                        "_UI_ComponentImplementingClassesLink_isCompositeComponent_feature",
                        "_UI_ComponentImplementingClassesLink_type"),
                SourcecodedecoratorPackage.Literals.COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_COMPOSITE_COMPONENT, false,
                false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Component feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addComponentPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_ComponentImplementingClassesLink_component_feature"),
                this.getString("_UI_PropertyDescriptor_description",
                        "_UI_ComponentImplementingClassesLink_component_feature",
                        "_UI_ComponentImplementingClassesLink_type"),
                SourcecodedecoratorPackage.Literals.COMPONENT_IMPLEMENTING_CLASSES_LINK__COMPONENT, true, false, true,
                null, null, null));
    }

    /**
     * This adds a property descriptor for the Implementing Classes feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addImplementingClassesPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_ComponentImplementingClassesLink_implementingClasses_feature"),
                this.getString("_UI_PropertyDescriptor_description",
                        "_UI_ComponentImplementingClassesLink_implementingClasses_feature",
                        "_UI_ComponentImplementingClassesLink_type"),
                SourcecodedecoratorPackage.Literals.COMPONENT_IMPLEMENTING_CLASSES_LINK__IMPLEMENTING_CLASSES, true,
                false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Sub Components feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addSubComponentsPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_ComponentImplementingClassesLink_subComponents_feature"),
                this.getString("_UI_PropertyDescriptor_description",
                        "_UI_ComponentImplementingClassesLink_subComponents_feature",
                        "_UI_ComponentImplementingClassesLink_type"),
                SourcecodedecoratorPackage.Literals.COMPONENT_IMPLEMENTING_CLASSES_LINK__SUB_COMPONENTS, true, false,
                true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Provided Interfaces feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addProvidedInterfacesPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_ComponentImplementingClassesLink_providedInterfaces_feature"),
                this.getString("_UI_PropertyDescriptor_description",
                        "_UI_ComponentImplementingClassesLink_providedInterfaces_feature",
                        "_UI_ComponentImplementingClassesLink_type"),
                SourcecodedecoratorPackage.Literals.COMPONENT_IMPLEMENTING_CLASSES_LINK__PROVIDED_INTERFACES, true,
                false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Required Interfaces feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addRequiredInterfacesPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_ComponentImplementingClassesLink_requiredInterfaces_feature"),
                this.getString("_UI_PropertyDescriptor_description",
                        "_UI_ComponentImplementingClassesLink_requiredInterfaces_feature",
                        "_UI_ComponentImplementingClassesLink_type"),
                SourcecodedecoratorPackage.Literals.COMPONENT_IMPLEMENTING_CLASSES_LINK__REQUIRED_INTERFACES, true,
                false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Is Initial Component feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addIsInitialComponentPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_ComponentImplementingClassesLink_isInitialComponent_feature"),
                this.getString("_UI_PropertyDescriptor_description",
                        "_UI_ComponentImplementingClassesLink_isInitialComponent_feature",
                        "_UI_ComponentImplementingClassesLink_type"),
                SourcecodedecoratorPackage.Literals.COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT, true,
                false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This returns ComponentImplementingClassesLink.gif. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object,
                this.getResourceLocator().getImage("full/obj16/ComponentImplementingClassesLink"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final ComponentImplementingClassesLink componentImplementingClassesLink = (ComponentImplementingClassesLink) object;
        return this.getString("_UI_ComponentImplementingClassesLink_type") + " "
                + componentImplementingClassesLink.isIsCompositeComponent();
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}
     * . <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void notifyChanged(final Notification notification) {
        this.updateChildren(notification);

        switch (notification.getFeatureID(ComponentImplementingClassesLink.class)) {
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_COMPOSITE_COMPONENT:
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK__IS_INITIAL_COMPONENT:
            this.fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that
     * can be created under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(final Collection<Object> newChildDescriptors, final Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return FEditPlugin.INSTANCE;
    }

}
