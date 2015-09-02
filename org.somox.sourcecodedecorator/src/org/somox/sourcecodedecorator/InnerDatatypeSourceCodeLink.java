/**
 */
package org.somox.sourcecodedecorator;

import org.palladiosimulator.pcm.repository.InnerDeclaration;

import org.eclipse.emf.ecore.EObject;

import org.emftext.language.java.members.Field;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inner Datatype Source Code Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink#getField <em>Field</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink#getInnerDeclaration <em>Inner Declaration</em>}</li>
 * </ul>
 *
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getInnerDatatypeSourceCodeLink()
 * @model
 * @generated
 */
public interface InnerDatatypeSourceCodeLink extends EObject {
    /**
     * Returns the value of the '<em><b>Field</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Field</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Field</em>' reference.
     * @see #setField(Field)
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getInnerDatatypeSourceCodeLink_Field()
     * @model
     * @generated
     */
    Field getField();

    /**
     * Sets the value of the '{@link org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink#getField <em>Field</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Field</em>' reference.
     * @see #getField()
     * @generated
     */
    void setField(Field value);

    /**
     * Returns the value of the '<em><b>Inner Declaration</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Inner Declaration</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Inner Declaration</em>' reference.
     * @see #setInnerDeclaration(InnerDeclaration)
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getInnerDatatypeSourceCodeLink_InnerDeclaration()
     * @model
     * @generated
     */
    InnerDeclaration getInnerDeclaration();

    /**
     * Sets the value of the '{@link org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink#getInnerDeclaration <em>Inner Declaration</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Inner Declaration</em>' reference.
     * @see #getInnerDeclaration()
     * @generated
     */
    void setInnerDeclaration(InnerDeclaration value);

} // InnerDatatypeSourceCodeLink
