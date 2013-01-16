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

import org.eclipse.gmt.modisco.java.InfixExpression;
import org.eclipse.gmt.modisco.java.InfixExpressionKind;

import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmt.modisco.java.InfixExpression} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class InfixExpressionItemProvider
	extends ExpressionItemProvider
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
	public InfixExpressionItemProvider(AdapterFactory adapterFactory) {
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

			addOperatorPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Operator feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOperatorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InfixExpression_operator_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InfixExpression_operator_feature", "_UI_InfixExpression_type"),
				 JavaPackage.eINSTANCE.getInfixExpression_Operator(),
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
			childrenFeatures.add(JavaPackage.eINSTANCE.getInfixExpression_RightOperand());
			childrenFeatures.add(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand());
			childrenFeatures.add(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands());
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
	 * This returns InfixExpression.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/InfixExpression"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		InfixExpressionKind labelValue = ((InfixExpression)object).getOperator();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ?
			getString("_UI_InfixExpression_type") :
			getString("_UI_InfixExpression_type") + " " + label;
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

		switch (notification.getFeatureID(InfixExpression.class)) {
			case JavaPackage.INFIX_EXPRESSION__OPERATOR:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case JavaPackage.INFIX_EXPRESSION__RIGHT_OPERAND:
			case JavaPackage.INFIX_EXPRESSION__LEFT_OPERAND:
			case JavaPackage.INFIX_EXPRESSION__EXTENDED_OPERANDS:
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
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createAnnotation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createArrayAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createArrayCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createArrayInitializer()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createArrayLengthAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createAssignment()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createCastExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createClassInstanceCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createConditionalExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createInfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createInstanceofExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createNumberLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createNullLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createParenthesizedExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createPostfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createPrefixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createSingleVariableAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createSuperFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createSuperMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createThisExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createTypeAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createTypeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createUnresolvedItemAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_RightOperand(),
				 JavaFactory.eINSTANCE.createVariableDeclarationExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createAnnotation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createArrayAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createArrayCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createArrayInitializer()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createArrayLengthAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createAssignment()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createCastExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createClassInstanceCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createConditionalExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createInfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createInstanceofExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createNumberLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createNullLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createParenthesizedExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createPostfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createPrefixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createSingleVariableAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createSuperFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createSuperMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createThisExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createTypeAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createTypeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createUnresolvedItemAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_LeftOperand(),
				 JavaFactory.eINSTANCE.createVariableDeclarationExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createAnnotation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createArrayAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createArrayCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createArrayInitializer()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createArrayLengthAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createAssignment()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createCastExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createCharacterLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createClassInstanceCreation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createConditionalExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createInfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createInstanceofExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createNumberLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createNullLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createParenthesizedExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createPostfixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createPrefixExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createSingleVariableAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createSuperFieldAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createSuperMethodInvocation()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createThisExpression()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createTypeAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createTypeLiteral()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
				 JavaFactory.eINSTANCE.createUnresolvedItemAccess()));

		newChildDescriptors.add
			(createChildParameter
				(JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands(),
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
			childFeature == JavaPackage.eINSTANCE.getInfixExpression_RightOperand() ||
			childFeature == JavaPackage.eINSTANCE.getInfixExpression_LeftOperand() ||
			childFeature == JavaPackage.eINSTANCE.getInfixExpression_ExtendedOperands();

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
