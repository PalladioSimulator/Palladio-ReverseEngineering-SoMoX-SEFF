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
package org.eclipse.gmt.modisco.omg.kdm.source.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;
import org.eclipse.gmt.modisco.omg.kdm.source.SourcePackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.source.InventoryContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class InventoryContainerItemProvider
	extends AbstractInventoryElementItemProvider
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
	public InventoryContainerItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT);
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
	 * This returns InventoryContainer.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/InventoryContainer")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((InventoryContainer)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_InventoryContainer_type") : //$NON-NLS-1$
			getString("_UI_InventoryContainer_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(InventoryContainer.class)) {
			case SourcePackage.INVENTORY_CONTAINER__INVENTORY_ELEMENT:
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
				(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT,
				 SourceFactory.eINSTANCE.createInventoryItem()));

		newChildDescriptors.add
			(createChildParameter
				(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT,
				 SourceFactory.eINSTANCE.createSourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT,
				 SourceFactory.eINSTANCE.createImage()));

		newChildDescriptors.add
			(createChildParameter
				(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT,
				 SourceFactory.eINSTANCE.createConfiguration()));

		newChildDescriptors.add
			(createChildParameter
				(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT,
				 SourceFactory.eINSTANCE.createResourceDescription()));

		newChildDescriptors.add
			(createChildParameter
				(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT,
				 SourceFactory.eINSTANCE.createBinaryFile()));

		newChildDescriptors.add
			(createChildParameter
				(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT,
				 SourceFactory.eINSTANCE.createExecutableFile()));

		newChildDescriptors.add
			(createChildParameter
				(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT,
				 SourceFactory.eINSTANCE.createInventoryContainer()));

		newChildDescriptors.add
			(createChildParameter
				(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT,
				 SourceFactory.eINSTANCE.createDirectory()));

		newChildDescriptors.add
			(createChildParameter
				(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT,
				 SourceFactory.eINSTANCE.createProject()));

		newChildDescriptors.add
			(createChildParameter
				(SourcePackage.Literals.INVENTORY_CONTAINER__INVENTORY_ELEMENT,
				 SourceFactory.eINSTANCE.createInventoryElement()));
	}

}
