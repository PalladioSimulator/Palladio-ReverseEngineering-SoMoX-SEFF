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

import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;

import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AnnotationTypeMemberDeclarationItemProvider
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
	public AnnotationTypeMemberDeclarationItemProvider(AdapterFactory adapterFactory) {
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

			addUsagesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Usages feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUsagesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AnnotationTypeMemberDeclaration_usages_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AnnotationTypeMemberDeclaration_usages_feature", "_UI_AnnotationTypeMemberDeclaration_type"),
				 JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Usages(),
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
			childrenFeatures.add(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default());
			childrenFeatures.add(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Type());
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
	 * This returns AnnotationTypeMemberDeclaration.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AnnotationTypeMemberDeclaration"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((AnnotationTypeMemberDeclaration)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_AnnotationTypeMemberDeclaration_type") :
			getString("_UI_AnnotationTypeMemberDeclaration_type") + " " + label;
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

		switch (notification.getFeatureID(AnnotationTypeMemberDeclaration.class)) {
			case JavaPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT:
			case JavaPackage.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE:
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
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createAnnotation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createArrayAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createArrayCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createArrayInitializer()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createArrayLengthAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createAssignment()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createCastExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createClassInstanceCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createConditionalExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createInfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createInstanceofExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createNumberLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createNullLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createParenthesizedExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createPostfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createPrefixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createSingleVariableAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createSuperFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createSuperMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createThisExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createTypeAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createTypeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createUnresolvedItemAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default(),
				 JavaFactory.eINSTANCE.createVariableDeclarationExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Type(),
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
			childFeature == JavaPackage.eINSTANCE.getBodyDeclaration_Annotations() ||
			childFeature == JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Default() ||
			childFeature == JavaPackage.eINSTANCE.getAnnotationTypeMemberDeclaration_Type();

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
