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
public class somoxgastFactoryImpl extends EFactoryImpl implements somoxgastFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static somoxgastFactory init() {
		try {
			somoxgastFactory thesomoxgastFactory = (somoxgastFactory)EPackage.Registry.INSTANCE.getEFactory("http://q-impress.eu/qimpressgast"); 
			if (thesomoxgastFactory != null) {
				return thesomoxgastFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new somoxgastFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public somoxgastFactoryImpl() {
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
			case somoxgastPackage.GAST_BEHAVIOUR: return createGASTBehaviour();
			case somoxgastPackage.GAST_BEHAVIOUR_REPOSITORY: return createGASTBehaviourRepository();
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
	public somoxgastPackage getsomoxgastPackage() {
		return (somoxgastPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static somoxgastPackage getPackage() {
		return somoxgastPackage.eINSTANCE;
	}

} //somoxgastFactoryImpl
