/**
 * *******************************************************************************
 * Copyright (c) 2009 Mia-Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sebastien Minguet (Mia-Software) - initial API and implementation
 *     Frederic Madiot (Mia-Software) - initial API and implementation
 *     Fabien Giquel (Mia-Software) - initial API and implementation
 *     Gabriel Barbier (Mia-Software) - initial API and implementation
 *     Erwan Breton (Sodifrance) - initial API and implementation
 *     Romain Dervaux (Mia-Software) - initial API and implementation
 * *******************************************************************************
 */
package org.eclipse.gmt.modisco.java.emf.provider;


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
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gmt.modisco.java.Model;

import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmt.modisco.java.Model} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelItemProvider
	extends ItemProviderAdapter
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
	public ModelItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Model_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Model_name_feature", "_UI_Model_type"),
				 JavaPackage.eINSTANCE.getModel_Name(),
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
			childrenFeatures.add(JavaPackage.eINSTANCE.getModel_OwnedElements());
			childrenFeatures.add(JavaPackage.eINSTANCE.getModel_OrphanTypes());
			childrenFeatures.add(JavaPackage.eINSTANCE.getModel_UnresolvedItems());
			childrenFeatures.add(JavaPackage.eINSTANCE.getModel_CompilationUnits());
			childrenFeatures.add(JavaPackage.eINSTANCE.getModel_ClassFiles());
			childrenFeatures.add(JavaPackage.eINSTANCE.getModel_Archives());
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
	 * This returns Model.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Model"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Model)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Model_type") :
			getString("_UI_Model_type") + " " + label;
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

		switch (notification.getFeatureID(Model.class)) {
			case JavaPackage.MODEL__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case JavaPackage.MODEL__OWNED_ELEMENTS:
			case JavaPackage.MODEL__ORPHAN_TYPES:
			case JavaPackage.MODEL__UNRESOLVED_ITEMS:
			case JavaPackage.MODEL__COMPILATION_UNITS:
			case JavaPackage.MODEL__CLASS_FILES:
			case JavaPackage.MODEL__ARCHIVES:
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
				(JavaPackage.eINSTANCE.getModel_OwnedElements(),
				 JavaFactory.eINSTANCE.createPackage()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createAnnotationTypeDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createArrayType()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createClassDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createEnumDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createInterfaceDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createParameterizedType()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createPrimitiveType()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createPrimitiveTypeBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createPrimitiveTypeByte()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createPrimitiveTypeChar()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createPrimitiveTypeDouble()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createPrimitiveTypeShort()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createPrimitiveTypeFloat()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createPrimitiveTypeInt()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createPrimitiveTypeLong()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createPrimitiveTypeVoid()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createTypeParameter()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createUnresolvedAnnotationDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createUnresolvedClassDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createUnresolvedEnumDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createUnresolvedInterfaceDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createUnresolvedType()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createUnresolvedTypeDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_OrphanTypes(),
				 JavaFactory.eINSTANCE.createWildCardType()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedItem()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedAnnotationDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedAnnotationTypeMemberDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedClassDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedEnumDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedInterfaceDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedLabeledStatement()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedMethodDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedSingleVariableDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedType()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedTypeDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_UnresolvedItems(),
				 JavaFactory.eINSTANCE.createUnresolvedVariableDeclarationFragment()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_CompilationUnits(),
				 JavaFactory.eINSTANCE.createCompilationUnit()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_ClassFiles(),
				 JavaFactory.eINSTANCE.createClassFile()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getModel_Archives(),
				 JavaFactory.eINSTANCE.createArchive()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == JavaPackage.eINSTANCE.getModel_OrphanTypes() ||
			childFeature == JavaPackage.eINSTANCE.getModel_UnresolvedItems();

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return JavaEditPlugin.INSTANCE;
	}

}
