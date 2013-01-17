/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.qimpressgast;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eu.qimpress.qimpressgast.qimpressgastFactory
 * @model kind="package"
 * @generated
 */
public interface qimpressgastPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "qimpressgast";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://q-impress.eu/qimpressgast";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "qimpressgast";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	qimpressgastPackage eINSTANCE = eu.qimpress.qimpressgast.impl.qimpressgastPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.qimpress.qimpressgast.impl.GASTBehaviourImpl <em>GAST Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.qimpress.qimpressgast.impl.GASTBehaviourImpl
	 * @see eu.qimpress.qimpressgast.impl.qimpressgastPackageImpl#getGASTBehaviour()
	 * @generated
	 */
	int GAST_BEHAVIOUR = 0;

	/**
	 * The feature id for the '<em><b>Blockstatement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAST_BEHAVIOUR__BLOCKSTATEMENT = 0;

	/**
	 * The feature id for the '<em><b>Gastbehaviourstub</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAST_BEHAVIOUR__GASTBEHAVIOURSTUB = 1;

	/**
	 * The number of structural features of the '<em>GAST Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAST_BEHAVIOUR_FEATURE_COUNT = 2;


	/**
	 * The meta object id for the '{@link eu.qimpress.qimpressgast.impl.GASTBehaviourRepositoryImpl <em>GAST Behaviour Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.qimpress.qimpressgast.impl.GASTBehaviourRepositoryImpl
	 * @see eu.qimpress.qimpressgast.impl.qimpressgastPackageImpl#getGASTBehaviourRepository()
	 * @generated
	 */
	int GAST_BEHAVIOUR_REPOSITORY = 1;

	/**
	 * The feature id for the '<em><b>Gastbehaviour</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAST_BEHAVIOUR_REPOSITORY__GASTBEHAVIOUR = 0;

	/**
	 * The number of structural features of the '<em>GAST Behaviour Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAST_BEHAVIOUR_REPOSITORY_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link eu.qimpress.qimpressgast.GASTBehaviour <em>GAST Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GAST Behaviour</em>'.
	 * @see eu.qimpress.qimpressgast.GASTBehaviour
	 * @generated
	 */
	EClass getGASTBehaviour();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.qimpressgast.GASTBehaviour#getBlockstatement <em>Blockstatement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Blockstatement</em>'.
	 * @see eu.qimpress.qimpressgast.GASTBehaviour#getBlockstatement()
	 * @see #getGASTBehaviour()
	 * @generated
	 */
	EReference getGASTBehaviour_Blockstatement();

	/**
	 * Returns the meta object for the reference '{@link eu.qimpress.qimpressgast.GASTBehaviour#getGastbehaviourstub <em>Gastbehaviourstub</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gastbehaviourstub</em>'.
	 * @see eu.qimpress.qimpressgast.GASTBehaviour#getGastbehaviourstub()
	 * @see #getGASTBehaviour()
	 * @generated
	 */
	EReference getGASTBehaviour_Gastbehaviourstub();

	/**
	 * Returns the meta object for class '{@link eu.qimpress.qimpressgast.GASTBehaviourRepository <em>GAST Behaviour Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GAST Behaviour Repository</em>'.
	 * @see eu.qimpress.qimpressgast.GASTBehaviourRepository
	 * @generated
	 */
	EClass getGASTBehaviourRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.qimpress.qimpressgast.GASTBehaviourRepository#getGastbehaviour <em>Gastbehaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Gastbehaviour</em>'.
	 * @see eu.qimpress.qimpressgast.GASTBehaviourRepository#getGastbehaviour()
	 * @see #getGASTBehaviourRepository()
	 * @generated
	 */
	EReference getGASTBehaviourRepository_Gastbehaviour();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	qimpressgastFactory getqimpressgastFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.qimpress.qimpressgast.impl.GASTBehaviourImpl <em>GAST Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.qimpress.qimpressgast.impl.GASTBehaviourImpl
		 * @see eu.qimpress.qimpressgast.impl.qimpressgastPackageImpl#getGASTBehaviour()
		 * @generated
		 */
		EClass GAST_BEHAVIOUR = eINSTANCE.getGASTBehaviour();

		/**
		 * The meta object literal for the '<em><b>Blockstatement</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAST_BEHAVIOUR__BLOCKSTATEMENT = eINSTANCE.getGASTBehaviour_Blockstatement();

		/**
		 * The meta object literal for the '<em><b>Gastbehaviourstub</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAST_BEHAVIOUR__GASTBEHAVIOURSTUB = eINSTANCE.getGASTBehaviour_Gastbehaviourstub();

		/**
		 * The meta object literal for the '{@link eu.qimpress.qimpressgast.impl.GASTBehaviourRepositoryImpl <em>GAST Behaviour Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.qimpress.qimpressgast.impl.GASTBehaviourRepositoryImpl
		 * @see eu.qimpress.qimpressgast.impl.qimpressgastPackageImpl#getGASTBehaviourRepository()
		 * @generated
		 */
		EClass GAST_BEHAVIOUR_REPOSITORY = eINSTANCE.getGASTBehaviourRepository();

		/**
		 * The meta object literal for the '<em><b>Gastbehaviour</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAST_BEHAVIOUR_REPOSITORY__GASTBEHAVIOUR = eINSTANCE.getGASTBehaviourRepository_Gastbehaviour();

	}

} //qimpressgastPackage
