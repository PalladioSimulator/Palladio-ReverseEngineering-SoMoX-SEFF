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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;

import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmt.modisco.java.EnumConstantDeclaration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EnumConstantDeclarationItemProvider
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
	public EnumConstantDeclarationItemProvider(AdapterFactory adapterFactory) {
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

			addExtraArrayDimensionsPropertyDescriptor(object);
			addUsageInVariableAccessPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Extra Array Dimensions feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addExtraArrayDimensionsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_VariableDeclaration_extraArrayDimensions_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_VariableDeclaration_extraArrayDimensions_feature", "_UI_VariableDeclaration_type"),
				 JavaPackage.eINSTANCE.getVariableDeclaration_ExtraArrayDimensions(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Usage In Variable Access feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUsageInVariableAccessPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_VariableDeclaration_usageInVariableAccess_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_VariableDeclaration_usageInVariableAccess_feature", "_UI_VariableDeclaration_type"),
				 JavaPackage.eINSTANCE.getVariableDeclaration_UsageInVariableAccess(),
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
			childrenFeatures.add(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer());
			childrenFeatures.add(JavaPackage.eINSTANCE.getEnumConstantDeclaration_AnonymousClassDeclaration());
			childrenFeatures.add(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments());
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
	 * This returns EnumConstantDeclaration.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/EnumConstantDeclaration"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((EnumConstantDeclaration)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_EnumConstantDeclaration_type") :
			getString("_UI_EnumConstantDeclaration_type") + " " + label;
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

		switch (notification.getFeatureID(EnumConstantDeclaration.class)) {
			case JavaPackage.ENUM_CONSTANT_DECLARATION__EXTRA_ARRAY_DIMENSIONS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case JavaPackage.ENUM_CONSTANT_DECLARATION__INITIALIZER:
			case JavaPackage.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION:
			case JavaPackage.ENUM_CONSTANT_DECLARATION__ARGUMENTS:
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
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createAnnotation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createArrayAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createArrayCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createArrayInitializer()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createArrayLengthAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createAssignment()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createCastExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createClassInstanceCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createConditionalExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createInfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createInstanceofExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createNumberLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createNullLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createParenthesizedExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createPostfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createPrefixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createSingleVariableAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createSuperFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createSuperMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createThisExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createTypeAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createTypeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createUnresolvedItemAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getVariableDeclaration_Initializer(),
				 JavaFactory.eINSTANCE.createVariableDeclarationExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_AnonymousClassDeclaration(),
				 JavaFactory.eINSTANCE.createAnonymousClassDeclaration()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createAnnotation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createArrayAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createArrayCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createArrayInitializer()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createArrayLengthAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createAssignment()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createCastExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createClassInstanceCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createConditionalExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createInfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createInstanceofExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createNumberLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createNullLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createParenthesizedExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createPostfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createPrefixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createSingleVariableAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createSuperFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createSuperMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createThisExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createTypeAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createTypeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createUnresolvedItemAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments(),
				 JavaFactory.eINSTANCE.createVariableDeclarationExpression()));
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
			childFeature == JavaPackage.eINSTANCE.getBodyDeclaration_Annotations() ||
			childFeature == JavaPackage.eINSTANCE.getVariableDeclaration_Initializer() ||
			childFeature == JavaPackage.eINSTANCE.getEnumConstantDeclaration_Arguments();

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
