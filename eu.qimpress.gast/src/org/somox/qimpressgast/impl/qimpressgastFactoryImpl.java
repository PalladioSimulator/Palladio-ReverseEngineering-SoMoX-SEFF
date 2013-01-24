/**
 */
package org.somox.qimpressgast.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.somox.qimpressgast.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class qimpressgastFactoryImpl extends EFactoryImpl implements qimpressgastFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static qimpressgastFactory init() {
		try {
			qimpressgastFactory theqimpressgastFactory = (qimpressgastFactory)EPackage.Registry.INSTANCE.getEFactory("http://q-impress.eu/qimpressgast"); 
			if (theqimpressgastFactory != null) {
				return theqimpressgastFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new qimpressgastFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public qimpressgastFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case qimpressgastPackage.GAST_BEHAVIOUR: return createGASTBehaviour();
			case qimpressgastPackage.GAST_BEHAVIOUR_REPOSITORY: return createGASTBehaviourRepository();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GASTBehaviour createGASTBehaviour() {
		GASTBehaviourImpl gastBehaviour = new GASTBehaviourImpl();
		return gastBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GASTBehaviourRepository createGASTBehaviourRepository() {
		GASTBehaviourRepositoryImpl gastBehaviourRepository = new GASTBehaviourRepositoryImpl();
		return gastBehaviourRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public qimpressgastPackage getqimpressgastPackage() {
		return (qimpressgastPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static qimpressgastPackage getPackage() {
		return qimpressgastPackage.eINSTANCE;
	}

} //qimpressgastFactoryImpl
