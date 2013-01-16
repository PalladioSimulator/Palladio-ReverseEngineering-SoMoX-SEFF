/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.sourcecodedecorator;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage
 * @generated
 */
public interface SourceCodeDecoratorFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SourceCodeDecoratorFactory eINSTANCE = eu.qimpress.sourcecodedecorator.impl.SourceCodeDecoratorFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>File Level Source Code Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Level Source Code Link</em>'.
	 * @generated
	 */
	FileLevelSourceCodeLink createFileLevelSourceCodeLink();

	/**
	 * Returns a new object of class '<em>Method Level Source Code Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Level Source Code Link</em>'.
	 * @generated
	 */
	MethodLevelSourceCodeLink createMethodLevelSourceCodeLink();

	/**
	 * Returns a new object of class '<em>Control Flow Level Source Code Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Control Flow Level Source Code Link</em>'.
	 * @generated
	 */
	ControlFlowLevelSourceCodeLink createControlFlowLevelSourceCodeLink();

	/**
	 * Returns a new object of class '<em>Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Repository</em>'.
	 * @generated
	 */
	SourceCodeDecoratorRepository createSourceCodeDecoratorRepository();

	/**
	 * Returns a new object of class '<em>Interface Source Code Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interface Source Code Link</em>'.
	 * @generated
	 */
	InterfaceSourceCodeLink createInterfaceSourceCodeLink();

	/**
	 * Returns a new object of class '<em>Component Implementing Classes Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Implementing Classes Link</em>'.
	 * @generated
	 */
	ComponentImplementingClassesLink createComponentImplementingClassesLink();

	/**
	 * Returns a new object of class '<em>Samm System Implementating Classes Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Samm System Implementating Classes Link</em>'.
	 * @generated
	 */
	SammSystemImplementatingClassesLink createSammSystemImplementatingClassesLink();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SourceCodeDecoratorPackage getSourceCodeDecoratorPackage();

} //SourceCodeDecoratorFactory
