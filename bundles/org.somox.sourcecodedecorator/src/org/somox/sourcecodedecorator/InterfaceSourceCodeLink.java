/**
 */
package org.somox.sourcecodedecorator;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.palladiosimulator.pcm.repository.Interface;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Interface Source Code Link</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.InterfaceSourceCodeLink#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.InterfaceSourceCodeLink#getGastClass <em>Gast Class</em>}</li>
 * </ul>
 *
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getInterfaceSourceCodeLink()
 * @model
 * @generated
 */
public interface InterfaceSourceCodeLink extends EObject {
    /**
	 * Returns the value of the '<em><b>Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interface</em>' reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' reference.
	 * @see #setInterface(Interface)
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getInterfaceSourceCodeLink_Interface()
	 * @model ordered="false"
	 * @generated
	 */
    Interface getInterface();

    /**
	 * Sets the value of the '{@link org.somox.sourcecodedecorator.InterfaceSourceCodeLink#getInterface <em>Interface</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' reference.
	 * @see #getInterface()
	 * @generated
	 */
    void setInterface(Interface value);

    /**
	 * Returns the value of the '<em><b>Gast Class</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Gast Class</em>' reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Gast Class</em>' reference.
	 * @see #setGastClass(ConcreteClassifier)
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getInterfaceSourceCodeLink_GastClass()
	 * @model ordered="false"
	 * @generated
	 */
    ConcreteClassifier getGastClass();

    /**
	 * Sets the value of the '{@link org.somox.sourcecodedecorator.InterfaceSourceCodeLink#getGastClass <em>Gast Class</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gast Class</em>' reference.
	 * @see #getGastClass()
	 * @generated
	 */
    void setGastClass(ConcreteClassifier value);

} // InterfaceSourceCodeLink
