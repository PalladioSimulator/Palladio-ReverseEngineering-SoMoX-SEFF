/**
 */
package org.somox.sourcecodedecorator;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Source Code Decorator Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getFileLevelSourceCodeLink <em>File Level Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getMethodLevelSourceCodeLink <em>Method Level Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getControlFlowLevelSourceCodeLink <em>Control Flow Level Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getInterfaceSourceCodeLink <em>Interface Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getComponentImplementingClassesLink <em>Component Implementing Classes Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSourceCodeDecoratorRepository()
 * @model
 * @generated
 */
public interface SourceCodeDecoratorRepository extends EObject {
	/**
	 * Returns the value of the '<em><b>File Level Source Code Link</b></em>' containment reference list.
	 * The list contents are of type {@link org.somox.sourcecodedecorator.FileLevelSourceCodeLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Level Source Code Link</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Level Source Code Link</em>' containment reference list.
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSourceCodeDecoratorRepository_FileLevelSourceCodeLink()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<FileLevelSourceCodeLink> getFileLevelSourceCodeLink();

	/**
	 * Returns the value of the '<em><b>Method Level Source Code Link</b></em>' containment reference list.
	 * The list contents are of type {@link org.somox.sourcecodedecorator.MethodLevelSourceCodeLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Level Source Code Link</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Level Source Code Link</em>' containment reference list.
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSourceCodeDecoratorRepository_MethodLevelSourceCodeLink()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<MethodLevelSourceCodeLink> getMethodLevelSourceCodeLink();

	/**
	 * Returns the value of the '<em><b>Control Flow Level Source Code Link</b></em>' containment reference list.
	 * The list contents are of type {@link org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Flow Level Source Code Link</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Flow Level Source Code Link</em>' containment reference list.
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSourceCodeDecoratorRepository_ControlFlowLevelSourceCodeLink()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ControlFlowLevelSourceCodeLink> getControlFlowLevelSourceCodeLink();

	/**
	 * Returns the value of the '<em><b>Interface Source Code Link</b></em>' containment reference list.
	 * The list contents are of type {@link org.somox.sourcecodedecorator.InterfaceSourceCodeLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface Source Code Link</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface Source Code Link</em>' containment reference list.
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSourceCodeDecoratorRepository_InterfaceSourceCodeLink()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<InterfaceSourceCodeLink> getInterfaceSourceCodeLink();

	/**
	 * Returns the value of the '<em><b>Component Implementing Classes Link</b></em>' containment reference list.
	 * The list contents are of type {@link org.somox.sourcecodedecorator.ComponentImplementingClassesLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Implementing Classes Link</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Implementing Classes Link</em>' containment reference list.
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSourceCodeDecoratorRepository_ComponentImplementingClassesLink()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ComponentImplementingClassesLink> getComponentImplementingClassesLink();

} // SourceCodeDecoratorRepository
