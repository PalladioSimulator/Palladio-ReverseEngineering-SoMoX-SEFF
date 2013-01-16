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
package org.eclipse.gmt.modisco.omg.kdm.platform.provider;


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
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gmt.modisco.omg.kdm.core.provider.KdmEditPlugin;

import org.eclipse.gmt.modisco.omg.kdm.kdm.provider.KDMModelItemProvider;

import org.eclipse.gmt.modisco.omg.kdm.platform.PlatformFactory;
import org.eclipse.gmt.modisco.omg.kdm.platform.PlatformModel;
import org.eclipse.gmt.modisco.omg.kdm.platform.PlatformPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.platform.PlatformModel} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PlatformModelItemProvider
	extends KDMModelItemProvider
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
	public PlatformModelItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT);
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
	 * This returns PlatformModel.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/PlatformModel")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((PlatformModel)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_PlatformModel_type") : //$NON-NLS-1$
			getString("_UI_PlatformModel_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(PlatformModel.class)) {
			case PlatformPackage.PLATFORM_MODEL__PLATFORM_ELEMENT:
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
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createResourceType()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createNamingResource()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createMarshalledResource()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createMessagingResource()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createFileResource()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createExecutionResource()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createLockResource()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createStreamResource()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createDataManager()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createPlatformEvent()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createPlatformAction()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createExternalActor()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createDeployedComponent()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createDeployedSoftwareSystem()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createMachine()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createDeployedResource()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createRuntimeResource()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createProcess()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createThread()));

		newChildDescriptors.add
			(createChildParameter
				(PlatformPackage.Literals.PLATFORM_MODEL__PLATFORM_ELEMENT,
				 PlatformFactory.eINSTANCE.createPlatformElement()));
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
