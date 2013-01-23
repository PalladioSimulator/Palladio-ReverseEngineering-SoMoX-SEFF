/**
 */
package org.somox.sourcecodedecorator;

import eu.qimpress.samm.staticstructure.Operation;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Level Source Code Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.MethodLevelSourceCodeLink#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.MethodLevelSourceCodeLink#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.sourcecodedecorator.SourceCodeDecoratorPackage#getMethodLevelSourceCodeLink()
 * @model
 * @generated
 */
public interface MethodLevelSourceCodeLink extends FileLevelSourceCodeLink {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' reference.
	 * @see #setOperation(Operation)
	 * @see org.somox.sourcecodedecorator.SourceCodeDecoratorPackage#getMethodLevelSourceCodeLink_Operation()
	 * @model ordered="false"
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link org.somox.sourcecodedecorator.MethodLevelSourceCodeLink#getOperation <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function</em>' reference.
	 * @see #setFunction(AbstractMethodDeclaration)
	 * @see org.somox.sourcecodedecorator.SourceCodeDecoratorPackage#getMethodLevelSourceCodeLink_Function()
	 * @model ordered="false"
	 * @generated
	 */
	AbstractMethodDeclaration getFunction();

	/**
	 * Sets the value of the '{@link org.somox.sourcecodedecorator.MethodLevelSourceCodeLink#getFunction <em>Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(AbstractMethodDeclaration value);

} // MethodLevelSourceCodeLink
