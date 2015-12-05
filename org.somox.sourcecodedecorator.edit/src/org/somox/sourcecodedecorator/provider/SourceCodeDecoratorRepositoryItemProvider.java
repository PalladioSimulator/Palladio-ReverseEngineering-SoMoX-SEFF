/**
 */
package org.somox.sourcecodedecorator.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * This is the item provider adapter for a
 * {@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 *
 * @generated
 */
public class SourceCodeDecoratorRepositoryItemProvider extends ItemProviderAdapter
        implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
        IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public SourceCodeDecoratorRepositoryItemProvider(final AdapterFactory adapterFactory) {
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

        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate
     * feature for an {@link org.eclipse.emf.edit.command.AddCommand},
     * {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(final Object object) {
        if (this.childrenFeatures == null) {
            super.getChildrenFeatures(object);
            this.childrenFeatures.add(
                    SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK);
            this.childrenFeatures.add(
                    SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK);
            this.childrenFeatures.add(
                    SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK);
            this.childrenFeatures.add(
                    SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK);
            this.childrenFeatures.add(
                    SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK);
            this.childrenFeatures.add(
                    SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK);
            this.childrenFeatures.add(
                    SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK);
            this.childrenFeatures.add(
                    SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK);
        }
        return this.childrenFeatures;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(final Object object, final Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns SourceCodeDecoratorRepository.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object,
                this.getResourceLocator().getImage("full/obj16/SourceCodeDecoratorRepository"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        return this.getString("_UI_SourceCodeDecoratorRepository_type");
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

        switch (notification.getFeatureID(SourceCodeDecoratorRepository.class)) {
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK:
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK:
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK:
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK:
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK:
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK:
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK:
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK:
            this.fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createFileLevelSourceCodeLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createMethodLevelSourceCodeLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createControlFlowLevelSourceCodeLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createDataTypeSourceCodeLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createMethodLevelResourceDemandingInternalBehaviorLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createMethodLevelSourceCodeLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createControlFlowLevelSourceCodeLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createControlFlowLevelSourceCodeLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__INTERFACE_SOURCE_CODE_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createInterfaceSourceCodeLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createComponentImplementingClassesLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__COMPONENT_IMPLEMENTING_CLASSES_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createPCMSystemImplementatingClassesLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createDataTypeSourceCodeLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__ABSTRACT_ACTION_CLASS_METHOD_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createAbstractActionClassMethodLink()));

        newChildDescriptors.add(this.createChildParameter(
                SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK,
                SourcecodedecoratorFactory.eINSTANCE.createMethodLevelResourceDemandingInternalBehaviorLink()));
    }

    /**
     * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getCreateChildText(final Object owner, final Object feature, final Object child,
            final Collection<?> selection) {
        final Object childFeature = feature;
        final Object childObject = child;

        final boolean qualify = childFeature == SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__FILE_LEVEL_SOURCE_CODE_LINK
                || childFeature == SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_SOURCE_CODE_LINK
                || childFeature == SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK
                || childFeature == SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__DATA_TYPE_SOURCE_CODE_LINK
                || childFeature == SourcecodedecoratorPackage.Literals.SOURCE_CODE_DECORATOR_REPOSITORY__METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK;

        if (qualify) {
            return this.getString("_UI_CreateChild_text2", new Object[] { this.getTypeText(childObject),
                    this.getFeatureText(childFeature), this.getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
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
