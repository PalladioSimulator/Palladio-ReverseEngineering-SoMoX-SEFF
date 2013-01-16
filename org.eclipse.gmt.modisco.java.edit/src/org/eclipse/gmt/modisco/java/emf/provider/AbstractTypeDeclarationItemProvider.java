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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;

import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmt.modisco.java.AbstractTypeDeclaration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractTypeDeclarationItemProvider
	extends BodyDeclarationItemProvider
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
	public AbstractTypeDeclarationItemProvider(AdapterFactory adapterFactory) {
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

			addUsagesInTypeAccessPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Usages In Type Access feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUsagesInTypeAccessPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Type_usagesInTypeAccess_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Type_usagesInTypeAccess_feature", "_UI_Type_type"),
				 JavaPackage.eINSTANCE.getType_UsagesInTypeAccess(),
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
			childrenFeatures.add(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations());
			childrenFeatures.add(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_CommentsBeforeBody());
			childrenFeatures.add(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_CommentsAfterBody());
			childrenFeatures.add(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_SuperInterfaces());
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
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((AbstractTypeDeclaration)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_AbstractTypeDeclaration_type") :
			getString("_UI_AbstractTypeDeclaration_type") + " " + label;
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

		switch (notification.getFeatureID(AbstractTypeDeclaration.class)) {
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS:
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_BEFORE_BODY:
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__COMMENTS_AFTER_BODY:
			case JavaPackage.ABSTRACT_TYPE_DECLARATION__SUPER_INTERFACES:
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
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createAnnotationTypeDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createAnnotationTypeMemberDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createConstructorDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createClassDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createEnumConstantDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createEnumDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createFieldDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createInitializer()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createInterfaceDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createMethodDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createUnresolvedAnnotationDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createUnresolvedAnnotationTypeMemberDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createUnresolvedClassDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createUnresolvedEnumDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createUnresolvedInterfaceDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createUnresolvedMethodDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations(),
				 JavaFactory.eINSTANCE.createUnresolvedTypeDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_CommentsBeforeBody(),
				 JavaFactory.eINSTANCE.createBlockComment()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_CommentsBeforeBody(),
				 JavaFactory.eINSTANCE.createJavadoc()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_CommentsBeforeBody(),
				 JavaFactory.eINSTANCE.createLineComment()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_CommentsAfterBody(),
				 JavaFactory.eINSTANCE.createBlockComment()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_CommentsAfterBody(),
				 JavaFactory.eINSTANCE.createJavadoc()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_CommentsAfterBody(),
				 JavaFactory.eINSTANCE.createLineComment()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAbstractTypeDeclaration_SuperInterfaces(),
				 JavaFactory.eINSTANCE.createTypeAccess()));
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
			childFeature == JavaPackage.eINSTANCE.getASTNode_Comments() ||
			childFeature == JavaPackage.eINSTANCE.getAbstractTypeDeclaration_CommentsBeforeBody() ||
			childFeature == JavaPackage.eINSTANCE.getAbstractTypeDeclaration_CommentsAfterBody();

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
