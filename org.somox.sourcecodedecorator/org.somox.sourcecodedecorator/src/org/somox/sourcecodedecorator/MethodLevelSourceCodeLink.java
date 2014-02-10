/**
 */
package org.somox.sourcecodedecorator;

import de.uka.ipd.sdq.pcm.repository.Signature;

import org.emftext.language.java.members.Method;

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
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getMethodLevelSourceCodeLink()
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
	 * @see #setOperation(Signature)
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getMethodLevelSourceCodeLink_Operation()
	 * @model ordered="false"
	 * @generated
	 */
	Signature getOperation();

	/**
	 * Sets the value of the '{@link org.somox.sourcecodedecorator.MethodLevelSourceCodeLink#getOperation <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Signature value);

	/**
	 * Returns the value of the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function</em>' reference.
	 * @see #setFunction(Method)
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getMethodLevelSourceCodeLink_Function()
	 * @model ordered="false"
	 * @generated
	 */
	Method getFunction();

	/**
	 * Sets the value of the '{@link org.somox.sourcecodedecorator.MethodLevelSourceCodeLink#getFunction <em>Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(Method value);

} // MethodLevelSourceCodeLink
