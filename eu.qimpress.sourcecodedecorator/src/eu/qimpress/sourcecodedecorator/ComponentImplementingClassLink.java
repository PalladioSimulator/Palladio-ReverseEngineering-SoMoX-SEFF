/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.sourcecodedecorator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.Type;

import eu.qimpress.samm.staticstructure.ComponentType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Implementing Class Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassLink#isCompositeComponent <em>Is Composite Component</em>}</li>
 *   <li>{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassLink#getComponent <em>Component</em>}</li>
 *   <li>{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassLink#getImplementingClasses <em>Implementing Classes</em>}</li>
 *   <li>{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassLink#getSubComponents <em>Sub Components</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassLink()
 * @model
 * @generated
 */
public interface ComponentImplementingClassLink extends EObject {
	/**
	 * Returns the value of the '<em><b>Is Composite Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Composite Component</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Composite Component</em>' attribute.
	 * @see #setIsCompositeComponent(boolean)
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassLink_IsCompositeComponent()
	 * @model unique="false" required="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	boolean isCompositeComponent();

	/**
	 * Sets the value of the '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassLink#isCompositeComponent <em>Is Composite Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Composite Component</em>' attribute.
	 * @see #isCompositeComponent()
	 * @generated
	 */
	void setIsCompositeComponent(boolean value);

	/**
	 * Returns the value of the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' reference.
	 * @see #setComponent(ComponentType)
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassLink_Component()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ComponentType getComponent();

	/**
	 * Sets the value of the '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassLink#getComponent <em>Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(ComponentType value);

	/**
	 * Returns the value of the '<em><b>Implementing Classes</b></em>' reference list.
	 * The list contents are of type {@link de.fzi.gast.types.GASTClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementing Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementing Classes</em>' reference list.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassLink_ImplementingClasses()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EList<Type> getImplementingClasses();

	/**
	 * Returns the value of the '<em><b>Sub Components</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Components</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Components</em>' reference.
	 * @see #setSubComponents(ComponentImplementingClassLink)
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassLink_SubComponents()
	 * @model ordered="false"
	 * @generated
	 */
	ComponentImplementingClassLink getSubComponents();

	/**
	 * Sets the value of the '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassLink#getSubComponents <em>Sub Components</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Components</em>' reference.
	 * @see #getSubComponents()
	 * @generated
	 */
	void setSubComponents(ComponentImplementingClassLink value);

} // ComponentImplementingClassLink
