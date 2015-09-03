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
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getFileLevelSourceCodeLink <em>File Level Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getMethodLevelSourceCodeLink <em>Method Level Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getControlFlowLevelSourceCodeLink <em>Control Flow Level Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getInterfaceSourceCodeLink <em>Interface Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getComponentImplementingClassesLink <em>Component Implementing Classes Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getDataTypeSourceCodeLink <em>Data Type Source Code Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getAbstractActionClassMethodLink <em>Abstract Action Class Method Link</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository#getMethodLevelResourceDemandingInternalBehaviorLink <em>Method Level Resource Demanding Internal Behavior Link</em>}</li>
 * </ul>
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

    /**
     * Returns the value of the '<em><b>Data Type Source Code Link</b></em>' containment reference list.
     * The list contents are of type {@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Type Source Code Link</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Type Source Code Link</em>' containment reference list.
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSourceCodeDecoratorRepository_DataTypeSourceCodeLink()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<DataTypeSourceCodeLink> getDataTypeSourceCodeLink();

    /**
     * Returns the value of the '<em><b>Abstract Action Class Method Link</b></em>' containment reference list.
     * The list contents are of type {@link org.somox.sourcecodedecorator.AbstractActionClassMethodLink}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Abstract Action Class Method Link</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Abstract Action Class Method Link</em>' containment reference list.
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSourceCodeDecoratorRepository_AbstractActionClassMethodLink()
     * @model containment="true"
     * @generated
     */
    EList<AbstractActionClassMethodLink> getAbstractActionClassMethodLink();

    /**
     * Returns the value of the '<em><b>Method Level Resource Demanding Internal Behavior Link</b></em>' containment reference list.
     * The list contents are of type {@link org.somox.sourcecodedecorator.MethodLevelResourceDemandingInternalBehaviorLink}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Method Level Resource Demanding Internal Behavior Link</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Method Level Resource Demanding Internal Behavior Link</em>' containment reference list.
     * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSourceCodeDecoratorRepository_MethodLevelResourceDemandingInternalBehaviorLink()
     * @model containment="true"
     * @generated
     */
    EList<MethodLevelResourceDemandingInternalBehaviorLink> getMethodLevelResourceDemandingInternalBehaviorLink();

} // SourceCodeDecoratorRepository
