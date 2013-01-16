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
package org.eclipse.gmt.modisco.omg.kdm.action.provider;


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

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionFactory;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionPackage;

import org.eclipse.gmt.modisco.omg.kdm.code.CodeFactory;

import org.eclipse.gmt.modisco.omg.kdm.code.provider.AbstractCodeElementItemProvider;

import org.eclipse.gmt.modisco.omg.kdm.core.provider.KdmEditPlugin;

import org.eclipse.gmt.modisco.omg.kdm.data.DataFactory;

import org.eclipse.gmt.modisco.omg.kdm.event.EventFactory;

import org.eclipse.gmt.modisco.omg.kdm.platform.PlatformFactory;

import org.eclipse.gmt.modisco.omg.kdm.ui.UiFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.action.ActionElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ActionElementItemProvider
	extends AbstractCodeElementItemProvider
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
	public ActionElementItemProvider(AdapterFactory adapterFactory) {
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
				 getString("_UI_ActionElement_kind_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ActionElement_kind_feature", "_UI_ActionElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ActionPackage.Literals.ACTION_ELEMENT__KIND,
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
			childrenFeatures.add(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT);
			childrenFeatures.add(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION);
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
	 * This returns ActionElement.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ActionElement")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ActionElement)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ActionElement_type") : //$NON-NLS-1$
			getString("_UI_ActionElement_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(ActionElement.class)) {
			case ActionPackage.ACTION_ELEMENT__KIND:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ActionPackage.ACTION_ELEMENT__CODE_ELEMENT:
			case ActionPackage.ACTION_ELEMENT__ACTION_RELATION:
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
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 ActionFactory.eINSTANCE.createActionElement()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 ActionFactory.eINSTANCE.createBlockUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 ActionFactory.eINSTANCE.createExceptionUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 ActionFactory.eINSTANCE.createTryUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 ActionFactory.eINSTANCE.createCatchUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 ActionFactory.eINSTANCE.createFinallyUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createComputationalObject()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createDatatype()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createModule()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createCompilationUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createSharedUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createLanguageUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createCodeAssembly()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createPackage()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createControlElement()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createCallableUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createMethodUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createDataElement()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createStorableUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createItemUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createIndexUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createMemberUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createParameterUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createValue()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createValueList()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createPrimitiveType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createBooleanType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createCharType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createOrdinalType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createDateType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createTimeType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createIntegerType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createDecimalType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createScaledType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createFloatType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createVoidType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createStringType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createBitType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createBitstringType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createOctetType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createOctetstringType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createEnumeratedType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createCompositeType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createChoiceType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createRecordType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createDerivedType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createArrayType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createPointerType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createRangeType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createBagType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createSetType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createSequenceType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createSignature()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createTypeUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createSynonymUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createClassUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createInterfaceUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createTemplateUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createTemplateParameter()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createTemplateType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createPreprocessorDirective()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createMacroUnit()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createMacroDirective()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createIncludeDirective()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createConditionalDirective()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createNamespace()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__CODE_ELEMENT,
				 CodeFactory.eINSTANCE.createCodeElement()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createControlFlow()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createEntryFlow()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createFlow()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createTrueFlow()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createFalseFlow()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createGuardedFlow()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createCalls()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createDispatches()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createReads()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createWrites()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createAddresses()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createCreates()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createExitFlow()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createExceptionFlow()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createThrows()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createCompliesTo()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createUsesType()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 ActionFactory.eINSTANCE.createActionRelationship()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 PlatformFactory.eINSTANCE.createManagesResource()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 PlatformFactory.eINSTANCE.createReadsResource()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 PlatformFactory.eINSTANCE.createWritesResource()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 PlatformFactory.eINSTANCE.createDefinedBy()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 DataFactory.eINSTANCE.createReadsColumnSet()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 DataFactory.eINSTANCE.createHasContent()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 DataFactory.eINSTANCE.createWritesColumnSet()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 DataFactory.eINSTANCE.createProducesDataEvent()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 DataFactory.eINSTANCE.createManagesData()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 EventFactory.eINSTANCE.createReadsState()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 EventFactory.eINSTANCE.createProducesEvent()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 EventFactory.eINSTANCE.createHasState()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 UiFactory.eINSTANCE.createReadsUI()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 UiFactory.eINSTANCE.createWritesUI()));

		newChildDescriptors.add
			(createChildParameter
				(ActionPackage.Literals.ACTION_ELEMENT__ACTION_RELATION,
				 UiFactory.eINSTANCE.createManagesUI()));
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
