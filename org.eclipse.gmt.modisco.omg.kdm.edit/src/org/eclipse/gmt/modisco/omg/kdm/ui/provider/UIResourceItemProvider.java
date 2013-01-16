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
package org.eclipse.gmt.modisco.omg.kdm.ui.provider;


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
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionFactory;

import org.eclipse.gmt.modisco.omg.kdm.core.provider.KDMEntityItemProvider;
import org.eclipse.gmt.modisco.omg.kdm.core.provider.KdmEditPlugin;

import org.eclipse.gmt.modisco.omg.kdm.source.SourceFactory;

import org.eclipse.gmt.modisco.omg.kdm.ui.UIResource;
import org.eclipse.gmt.modisco.omg.kdm.ui.UiFactory;
import org.eclipse.gmt.modisco.omg.kdm.ui.UiPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.ui.UIResource} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UIResourceItemProvider
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
	public UIResourceItemProvider(AdapterFactory adapterFactory) {
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

			addImplementationPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Implementation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addImplementationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractUIElement_implementation_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractUIElement_implementation_feature", "_UI_AbstractUIElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 UiPackage.Literals.ABSTRACT_UI_ELEMENT__IMPLEMENTATION,
				 true,
				 false,
				 true,
				 null,
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
			childrenFeatures.add(UiPackage.Literals.ABSTRACT_UI_ELEMENT__SOURCE);
			childrenFeatures.add(UiPackage.Literals.ABSTRACT_UI_ELEMENT__UI_RELATION);
			childrenFeatures.add(UiPackage.Literals.ABSTRACT_UI_ELEMENT__ABSTRACTION);
			childrenFeatures.add(UiPackage.Literals.UI_RESOURCE__UI_ELEMENT);
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
	 * This returns UIResource.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/UIResource")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((UIResource)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_UIResource_type") : //$NON-NLS-1$
			getString("_UI_UIResource_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(UIResource.class)) {
			case UiPackage.UI_RESOURCE__SOURCE:
			case UiPackage.UI_RESOURCE__UI_RELATION:
			case UiPackage.UI_RESOURCE__ABSTRACTION:
			case UiPackage.UI_RESOURCE__UI_ELEMENT:
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
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__SOURCE,
				 SourceFactory.eINSTANCE.createSourceRef()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__UI_RELATION,
				 UiFactory.eINSTANCE.createUILayout()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__UI_RELATION,
				 UiFactory.eINSTANCE.createDisplaysImage()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__UI_RELATION,
				 UiFactory.eINSTANCE.createDisplays()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__UI_RELATION,
				 UiFactory.eINSTANCE.createUIFlow()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__UI_RELATION,
				 UiFactory.eINSTANCE.createUIRelationship()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createActionElement()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createBlockUnit()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createExceptionUnit()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createTryUnit()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createCatchUnit()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.ABSTRACT_UI_ELEMENT__ABSTRACTION,
				 ActionFactory.eINSTANCE.createFinallyUnit()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.UI_RESOURCE__UI_ELEMENT,
				 UiFactory.eINSTANCE.createUIResource()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.UI_RESOURCE__UI_ELEMENT,
				 UiFactory.eINSTANCE.createUIDisplay()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.UI_RESOURCE__UI_ELEMENT,
				 UiFactory.eINSTANCE.createScreen()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.UI_RESOURCE__UI_ELEMENT,
				 UiFactory.eINSTANCE.createReport()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.UI_RESOURCE__UI_ELEMENT,
				 UiFactory.eINSTANCE.createUIField()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.UI_RESOURCE__UI_ELEMENT,
				 UiFactory.eINSTANCE.createUIElement()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.UI_RESOURCE__UI_ELEMENT,
				 UiFactory.eINSTANCE.createUIAction()));

		newChildDescriptors.add
			(createChildParameter
				(UiPackage.Literals.UI_RESOURCE__UI_ELEMENT,
				 UiFactory.eINSTANCE.createUIEvent()));
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
