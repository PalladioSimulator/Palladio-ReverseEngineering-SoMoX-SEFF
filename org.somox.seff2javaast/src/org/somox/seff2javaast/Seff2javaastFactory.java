/**
 */
package org.somox.seff2javaast;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.somox.seff2javaast.Seff2javaastPackage
 * @generated
 */
public interface Seff2javaastFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Seff2javaastFactory eINSTANCE = org.somox.seff2javaast.impl.Seff2javaastFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>SEFF2 Method Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SEFF2 Method Mapping</em>'.
	 * @generated
	 */
	SEFF2MethodMapping createSEFF2MethodMapping();

	/**
	 * Returns a new object of class '<em>SEFF2 Java AST</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SEFF2 Java AST</em>'.
	 * @generated
	 */
	SEFF2JavaAST createSEFF2JavaAST();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Seff2javaastPackage getSeff2javaastPackage();

} //Seff2javaastFactory
