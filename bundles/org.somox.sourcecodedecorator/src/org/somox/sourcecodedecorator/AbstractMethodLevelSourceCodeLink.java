/**
 */
package org.somox.sourcecodedecorator;

import org.emftext.language.java.members.Member;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Abstract Method Level Source Code Link</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.AbstractMethodLevelSourceCodeLink#getFunction <em>Function</em>}</li>
 * </ul>
 *
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getAbstractMethodLevelSourceCodeLink()
 * @model abstract="true"
 * @generated
 */
public interface AbstractMethodLevelSourceCodeLink extends FileLevelSourceCodeLink {
    /**
	 * Returns the value of the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Function</em>' reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Function</em>' reference.
	 * @see #setFunction(Member)
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getAbstractMethodLevelSourceCodeLink_Function()
	 * @model ordered="false"
	 * @generated
	 */
    Member getFunction();

    /**
	 * Sets the value of the '{@link org.somox.sourcecodedecorator.AbstractMethodLevelSourceCodeLink#getFunction <em>Function</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' reference.
	 * @see #getFunction()
	 * @generated
	 */
    void setFunction(Member value);

} // AbstractMethodLevelSourceCodeLink
