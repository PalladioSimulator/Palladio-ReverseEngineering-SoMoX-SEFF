/**
 */
package org.somox.sourcecodedecorator.impl;

import eu.qimpress.seff.AbstractAction;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gmt.modisco.java.Statement;

import org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Control Flow Level Source Code Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl#getAbstractAction <em>Abstract Action</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl#getStatement <em>Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ControlFlowLevelSourceCodeLinkImpl extends MethodLevelSourceCodeLinkImpl implements ControlFlowLevelSourceCodeLink {
	/**
	 * The cached value of the '{@link #getAbstractAction() <em>Abstract Action</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstractAction()
	 * @generated
	 * @ordered
	 */
	protected AbstractAction abstractAction;

	/**
	 * The cached value of the '{@link #getStatement() <em>Statement</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatement()
	 * @generated
	 * @ordered
	 */
	protected Statement statement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControlFlowLevelSourceCodeLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SourceCodeDecoratorPackage.Literals.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractAction getAbstractAction() {
		if (abstractAction != null && abstractAction.eIsProxy()) {
			InternalEObject oldAbstractAction = (InternalEObject)abstractAction;
			abstractAction = (AbstractAction)eResolveProxy(oldAbstractAction);
			if (abstractAction != oldAbstractAction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION, oldAbstractAction, abstractAction));
			}
		}
		return abstractAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractAction basicGetAbstractAction() {
		return abstractAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstractAction(AbstractAction newAbstractAction) {
		AbstractAction oldAbstractAction = abstractAction;
		abstractAction = newAbstractAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION, oldAbstractAction, abstractAction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getStatement() {
		if (statement != null && statement.eIsProxy()) {
			InternalEObject oldStatement = (InternalEObject)statement;
			statement = (Statement)eResolveProxy(oldStatement);
			if (statement != oldStatement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT, oldStatement, statement));
			}
		}
		return statement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement basicGetStatement() {
		return statement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatement(Statement newStatement) {
		Statement oldStatement = statement;
		statement = newStatement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT, oldStatement, statement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION:
				if (resolve) return getAbstractAction();
				return basicGetAbstractAction();
			case SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT:
				if (resolve) return getStatement();
				return basicGetStatement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION:
				setAbstractAction((AbstractAction)newValue);
				return;
			case SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT:
				setStatement((Statement)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION:
				setAbstractAction((AbstractAction)null);
				return;
			case SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT:
				setStatement((Statement)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION:
				return abstractAction != null;
			case SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT:
				return statement != null;
		}
		return super.eIsSet(featureID);
	}

} //ControlFlowLevelSourceCodeLinkImpl
