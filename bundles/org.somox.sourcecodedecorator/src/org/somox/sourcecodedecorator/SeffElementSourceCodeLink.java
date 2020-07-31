/**
 */
package org.somox.sourcecodedecorator;

import de.uka.ipd.sdq.identifier.Identifier;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.emftext.language.java.statements.Statement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Seff Element Source Code Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.SeffElementSourceCodeLink#getSeffElement <em>Seff Element</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SeffElementSourceCodeLink#getStatement <em>Statement</em>}</li>
 * </ul>
 *
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSeffElementSourceCodeLink()
 * @model
 * @generated
 */
public interface SeffElementSourceCodeLink extends EObject {
    /**
	 * Returns the value of the '<em><b>Seff Element</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Seff Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Seff Element</em>' reference.
	 * @see #setSeffElement(Identifier)
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSeffElementSourceCodeLink_SeffElement()
	 * @model required="true"
	 * @generated
	 */
    Identifier getSeffElement();

    /**
	 * Sets the value of the '{@link org.somox.sourcecodedecorator.SeffElementSourceCodeLink#getSeffElement <em>Seff Element</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seff Element</em>' reference.
	 * @see #getSeffElement()
	 * @generated
	 */
    void setSeffElement(Identifier value);

    /**
	 * Returns the value of the '<em><b>Statement</b></em>' reference list.
	 * The list contents are of type {@link org.emftext.language.java.statements.Statement}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Statement</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Statement</em>' reference list.
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSeffElementSourceCodeLink_Statement()
	 * @model required="true"
	 * @generated
	 */
    EList<Statement> getStatement();

} // SeffElementSourceCodeLink
