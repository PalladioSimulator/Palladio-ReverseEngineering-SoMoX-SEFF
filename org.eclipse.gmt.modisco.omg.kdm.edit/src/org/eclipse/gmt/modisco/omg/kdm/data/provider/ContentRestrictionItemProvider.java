/**
 * 
 * Copyright (c) 2009 Hatha Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Nikolai Mansourov (Hatha Systems) - initial API and implementation
 *     Gabriel Barbier (Mia-Software) - minor evolutions for version 1.1
 */
package org.eclipse.gmt.modisco.omg.kdm.data.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionFactory;

import org.eclipse.gmt.modisco.omg.kdm.core.provider.KDMEntityItemProvider;
import org.eclipse.gmt.modisco.omg.kdm.core.provider.KdmEditPlugin;

import org.eclipse.gmt.modisco.omg.kdm.data.ContentRestriction;
import org.eclipse.gmt.modisco.omg.kdm.data.DataFactory;
import org.eclipse.gmt.modisco.omg.kdm.data.DataPackage;

import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ContentRestriction} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ContentRestrictionItemProvider
	extends KDMEntityItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentRestrictionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addKindPropertyDescriptor(object);
			addValuePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Kind feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addKindPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ContentRestriction_kind_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ContentRestriction_kind_feature", "_UI_ContentRestriction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 DataPackage.Literals.CONTENT_RESTRICTION__KIND,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Value feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addValuePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ContentRestriction_value_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ContentRestriction_value_feature", "_UI_ContentRestriction_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 DataPackage.Literals.CONTENT_RESTRICTION__VALUE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__SOURCE);
			childrenFeatures.add(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__DATA_RELATION);
			childrenFeatures.add(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__ABSTRACTION);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ContentRestriction.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ContentRestriction")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ContentRestriction)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ContentRestriction_type") : //$NON-NLS-1$
			getString("_UI_ContentRestriction_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ContentRestriction.class)) {
			case DataPackage.CONTENT_RESTRICTION__KIND:
			case DataPackage.CONTENT_RESTRICTION__VALUE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case DataPackage.CONTENT_RESTRICTION__SOURCE:
			case DataPackage.CONTENT_RESTRICTION__DATA_RELATION:
			case DataPackage.CONTENT_RESTRICTION__ABSTRACTION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__SOURCE,
				 SourceFactory.eINSTANCE.createSourceRef()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__DATA_RELATION,
				 DataFactory.eINSTANCE.createKeyRelation()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__DATA_RELATION,
				 DataFactory.eINSTANCE.createDataRelationship()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__DATA_RELATION,
				 DataFactory.eINSTANCE.createTypedBy()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__DATA_RELATION,
				 DataFactory.eINSTANCE.createReferenceTo()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__DATA_RELATION,
				 DataFactory.eINSTANCE.createRestrictionOf()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__DATA_RELATION,
				 DataFactory.eINSTANCE.createExtensionTo()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__DATA_RELATION,
				 DataFactory.eINSTANCE.createDatatypeOf()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createActionElement()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createBlockUnit()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createExceptionUnit()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createTryUnit()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createCatchUnit()));

		newChildDescriptors.add
			(createChildParameter
				(DataPackage.Literals.ABSTRACT_DATA_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createFinallyUnit()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return KdmEditPlugin.INSTANCE;
	}

}
