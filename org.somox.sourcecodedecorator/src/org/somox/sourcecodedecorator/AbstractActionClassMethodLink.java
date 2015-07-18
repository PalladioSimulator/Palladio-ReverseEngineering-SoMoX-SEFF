/**
 */
package org.somox.sourcecodedecorator;

import org.palladiosimulator.pcm.seff.AbstractAction;

import org.eclipse.emf.ecore.EObject;

import org.emftext.language.java.members.ClassMethod;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Action Class Method Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.AbstractActionClassMethodLink#getClassMethod <em>Class Method</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.AbstractActionClassMethodLink#getAbstractAction <em>Abstract Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getAbstractActionClassMethodLink()
 * @model
 * @generated
 */
public interface AbstractActionClassMethodLink extends EObject {
    /**
     * Returns the value of the '<em><b>Class Method</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Class Method</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Class Method</em>' reference.
     * @see #setClassMethod(ClassMethod)
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getAbstractActionClassMethodLink_ClassMethod()
     * @model
     * @generated
     */
    ClassMethod getClassMethod();

    /**
     * Sets the value of the '{@link org.somox.sourcecodedecorator.AbstractActionClassMethodLink#getClassMethod <em>Class Method</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Class Method</em>' reference.
     * @see #getClassMethod()
     * @generated
     */
    void setClassMethod(ClassMethod value);

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
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getAbstractActionClassMethodLink_AbstractAction()
     * @model
     * @generated
     */
    AbstractAction getAbstractAction();

    /**
     * Sets the value of the '{@link org.somox.sourcecodedecorator.AbstractActionClassMethodLink#getAbstractAction <em>Abstract Action</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Abstract Action</em>' reference.
     * @see #getAbstractAction()
     * @generated
     */
    void setAbstractAction(AbstractAction value);

} // AbstractActionClassMethodLink
