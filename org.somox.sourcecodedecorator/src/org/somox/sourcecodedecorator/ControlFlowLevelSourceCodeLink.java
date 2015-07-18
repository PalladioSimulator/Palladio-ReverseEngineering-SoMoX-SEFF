/**
 */
package org.somox.sourcecodedecorator;

import org.palladiosimulator.pcm.seff.AbstractAction;

import org.emftext.language.java.statements.Statement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Control Flow Level Source Code Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getAbstractAction <em>Abstract Action</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getStatement <em>Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getControlFlowLevelSourceCodeLink()
 * @model
 * @generated
 */
public interface ControlFlowLevelSourceCodeLink extends MethodLevelSourceCodeLink {
	/**
     * Returns the value of the '<em><b>Abstract Action</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract Action</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Abstract Action</em>' reference.
     * @see #setAbstractAction(AbstractAction)
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getControlFlowLevelSourceCodeLink_AbstractAction()
     * @model ordered="false"
     * @generated
     */
	AbstractAction getAbstractAction();

	/**
     * Sets the value of the '{@link org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getAbstractAction <em>Abstract Action</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Abstract Action</em>' reference.
     * @see #getAbstractAction()
     * @generated
     */
	void setAbstractAction(AbstractAction value);

	/**
     * Returns the value of the '<em><b>Statement</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statement</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Statement</em>' reference.
     * @see #setStatement(Statement)
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getControlFlowLevelSourceCodeLink_Statement()
     * @model ordered="false"
     * @generated
     */
	Statement getStatement();

	/**
     * Sets the value of the '{@link org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink#getStatement <em>Statement</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Statement</em>' reference.
     * @see #getStatement()
     * @generated
     */
	void setStatement(Statement value);

} // ControlFlowLevelSourceCodeLink
