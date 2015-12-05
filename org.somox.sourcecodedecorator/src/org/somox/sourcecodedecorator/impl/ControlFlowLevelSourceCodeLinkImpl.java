/**
 */
package org.somox.sourcecodedecorator.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.emftext.language.java.statements.Statement;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Control Flow Level Source Code Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.somox.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl#getAbstractAction
 * <em>Abstract Action</em>}</li>
 * <li>{@link org.somox.sourcecodedecorator.impl.ControlFlowLevelSourceCodeLinkImpl#getStatement
 * <em>Statement</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ControlFlowLevelSourceCodeLinkImpl extends MethodLevelSourceCodeLinkImpl
        implements ControlFlowLevelSourceCodeLink {
    /**
     * The cached value of the '{@link #getAbstractAction() <em>Abstract Action</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAbstractAction()
     * @generated
     * @ordered
     */
    protected AbstractAction abstractAction;

    /**
     * The cached value of the '{@link #getStatement() <em>Statement</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStatement()
     * @generated
     * @ordered
     */
    protected Statement statement;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ControlFlowLevelSourceCodeLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SourcecodedecoratorPackage.Literals.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AbstractAction getAbstractAction() {
        if (this.abstractAction != null && ((EObject) this.abstractAction).eIsProxy()) {
            final InternalEObject oldAbstractAction = (InternalEObject) this.abstractAction;
            this.abstractAction = (AbstractAction) this.eResolveProxy(oldAbstractAction);
            if (this.abstractAction != oldAbstractAction) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION,
                            oldAbstractAction, this.abstractAction));
                }
            }
        }
        return this.abstractAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public AbstractAction basicGetAbstractAction() {
        return this.abstractAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAbstractAction(final AbstractAction newAbstractAction) {
        final AbstractAction oldAbstractAction = this.abstractAction;
        this.abstractAction = newAbstractAction;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION, oldAbstractAction,
                    this.abstractAction));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Statement getStatement() {
        if (this.statement != null && this.statement.eIsProxy()) {
            final InternalEObject oldStatement = (InternalEObject) this.statement;
            this.statement = (Statement) this.eResolveProxy(oldStatement);
            if (this.statement != oldStatement) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT, oldStatement,
                            this.statement));
                }
            }
        }
        return this.statement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Statement basicGetStatement() {
        return this.statement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStatement(final Statement newStatement) {
        final Statement oldStatement = this.statement;
        this.statement = newStatement;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT, oldStatement,
                    this.statement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION:
            if (resolve) {
                return this.getAbstractAction();
            }
            return this.basicGetAbstractAction();
        case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT:
            if (resolve) {
                return this.getStatement();
            }
            return this.basicGetStatement();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION:
            this.setAbstractAction((AbstractAction) newValue);
            return;
        case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT:
            this.setStatement((Statement) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION:
            this.setAbstractAction((AbstractAction) null);
            return;
        case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT:
            this.setStatement((Statement) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__ABSTRACT_ACTION:
            return this.abstractAction != null;
        case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK__STATEMENT:
            return this.statement != null;
        }
        return super.eIsSet(featureID);
    }

} // ControlFlowLevelSourceCodeLinkImpl
