/**
 */
package org.somox.sourcecodedecorator.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * This is the item provider adapter for a
 * {@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink} object. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 *
 * @generated
 */
public class DataTypeSourceCodeLinkItemProvider extends FileLevelSourceCodeLinkItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public DataTypeSourceCodeLinkItemProvider(final AdapterFactory adapterFactory) {
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

            this.addJaMoPPTypePropertyDescriptor(object);
            this.addPcmDataTypePropertyDescriptor(object);
            this.addInnerDatatypeSourceCodeLinkPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Ja Mo PP Type feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addJaMoPPTypePropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_DataTypeSourceCodeLink_jaMoPPType_feature"),
                this.getString("_UI_PropertyDescriptor_description", "_UI_DataTypeSourceCodeLink_jaMoPPType_feature",
                        "_UI_DataTypeSourceCodeLink_type"),
                SourcecodedecoratorPackage.Literals.DATA_TYPE_SOURCE_CODE_LINK__JA_MO_PP_TYPE, true, false, true, null,
                null, null));
    }

    /**
     * This adds a property descriptor for the Pcm Data Type feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected void addPcmDataTypePropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_DataTypeSourceCodeLink_pcmDataType_feature"),
                this.getString("_UI_PropertyDescriptor_description", "_UI_DataTypeSourceCodeLink_pcmDataType_feature",
                        "_UI_DataTypeSourceCodeLink_type"),
                SourcecodedecoratorPackage.Literals.DATA_TYPE_SOURCE_CODE_LINK__PCM_DATA_TYPE, true, false, true, null,
                null, null));
    }

    /**
     * This adds a property descriptor for the Inner Datatype Source Code Link feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addInnerDatatypeSourceCodeLinkPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_DataTypeSourceCodeLink_innerDatatypeSourceCodeLink_feature"),
                this.getString("_UI_PropertyDescriptor_description",
                        "_UI_DataTypeSourceCodeLink_innerDatatypeSourceCodeLink_feature",
                        "_UI_DataTypeSourceCodeLink_type"),
                SourcecodedecoratorPackage.Literals.DATA_TYPE_SOURCE_CODE_LINK__INNER_DATATYPE_SOURCE_CODE_LINK, true,
                false, true, null, null, null));
    }

    /**
     * This returns DataTypeSourceCodeLink.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/DataTypeSourceCodeLink"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        return this.getString("_UI_DataTypeSourceCodeLink_type");
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

}
