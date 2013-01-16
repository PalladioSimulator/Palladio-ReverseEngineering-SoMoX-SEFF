/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.sourcecodedecorator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

import eu.qimpress.samm.staticstructure.ComponentType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Level Source Code Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Human readable information for the source code link
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getFileLevelSourceCodeLink()
 * @model
 * @generated
 */
public interface FileLevelSourceCodeLink extends EObject {
	/**
	 * Returns the value of the '<em><b>Component Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Type</em>' reference.
	 * @see #setComponentType(ComponentType)
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getFileLevelSourceCodeLink_ComponentType()
	 * @model ordered="false"
	 * @generated
	 */
	ComponentType getComponentType();

	/**
	 * Sets the value of the '{@link eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink#getComponentType <em>Component Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Type</em>' reference.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(ComponentType value);

	/**
	 * Returns the value of the '<em><b>File</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File</em>' reference.
	 * @see #setFile(SourceFile)
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getFileLevelSourceCodeLink_File()
	 * @model ordered="false"
	 * @generated
	 */
	SourceFile getFile();

	/**
	 * Sets the value of the '{@link eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink#getFile <em>File</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' reference.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(SourceFile value);

} // FileLevelSourceCodeLink
