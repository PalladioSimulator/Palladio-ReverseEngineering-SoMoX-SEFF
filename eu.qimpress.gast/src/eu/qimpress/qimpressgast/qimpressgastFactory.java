/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.qimpressgast;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see eu.qimpress.qimpressgast.qimpressgastPackage
 * @generated
 */
public interface qimpressgastFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	qimpressgastFactory eINSTANCE = eu.qimpress.qimpressgast.impl.qimpressgastFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>GAST Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>GAST Behaviour</em>'.
	 * @generated
	 */
	GASTBehaviour createGASTBehaviour();

	/**
	 * Returns a new object of class '<em>GAST Behaviour Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>GAST Behaviour Repository</em>'.
	 * @generated
	 */
	GASTBehaviourRepository createGASTBehaviourRepository();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	qimpressgastPackage getqimpressgastPackage();

} //qimpressgastFactory
