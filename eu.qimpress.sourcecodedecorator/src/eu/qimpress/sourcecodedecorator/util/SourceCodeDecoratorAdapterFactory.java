/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.sourcecodedecorator.util;

import eu.qimpress.sourcecodedecorator.*;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink;
import eu.qimpress.sourcecodedecorator.ControlFlowLevelSourceCodeLink;
import eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink;
import eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink;
import eu.qimpress.sourcecodedecorator.MethodLevelSourceCodeLink;
import eu.qimpress.sourcecodedecorator.SammSystemImplementatingClassesLink;
import eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage;
import eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage
 * @generated
 */
public class SourceCodeDecoratorAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SourceCodeDecoratorPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceCodeDecoratorAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SourceCodeDecoratorPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SourceCodeDecoratorSwitch<Adapter> modelSwitch =
		new SourceCodeDecoratorSwitch<Adapter>() {
			@Override
			public Adapter caseFileLevelSourceCodeLink(FileLevelSourceCodeLink object) {
				return createFileLevelSourceCodeLinkAdapter();
			}
			@Override
			public Adapter caseMethodLevelSourceCodeLink(MethodLevelSourceCodeLink object) {
				return createMethodLevelSourceCodeLinkAdapter();
			}
			@Override
			public Adapter caseControlFlowLevelSourceCodeLink(ControlFlowLevelSourceCodeLink object) {
				return createControlFlowLevelSourceCodeLinkAdapter();
			}
			@Override
			public Adapter caseSourceCodeDecoratorRepository(SourceCodeDecoratorRepository object) {
				return createSourceCodeDecoratorRepositoryAdapter();
			}
			@Override
			public Adapter caseInterfaceSourceCodeLink(InterfaceSourceCodeLink object) {
				return createInterfaceSourceCodeLinkAdapter();
			}
			@Override
			public Adapter caseComponentImplementingClassesLink(ComponentImplementingClassesLink object) {
				return createComponentImplementingClassesLinkAdapter();
			}
			@Override
			public Adapter caseSammSystemImplementatingClassesLink(SammSystemImplementatingClassesLink object) {
				return createSammSystemImplementatingClassesLinkAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink <em>File Level Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink
	 * @generated
	 */
	public Adapter createFileLevelSourceCodeLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.qimpress.sourcecodedecorator.MethodLevelSourceCodeLink <em>Method Level Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.qimpress.sourcecodedecorator.MethodLevelSourceCodeLink
	 * @generated
	 */
	public Adapter createMethodLevelSourceCodeLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.qimpress.sourcecodedecorator.ControlFlowLevelSourceCodeLink <em>Control Flow Level Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.qimpress.sourcecodedecorator.ControlFlowLevelSourceCodeLink
	 * @generated
	 */
	public Adapter createControlFlowLevelSourceCodeLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository
	 * @generated
	 */
	public Adapter createSourceCodeDecoratorRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink <em>Interface Source Code Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink
	 * @generated
	 */
	public Adapter createInterfaceSourceCodeLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink <em>Component Implementing Classes Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink
	 * @generated
	 */
	public Adapter createComponentImplementingClassesLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.qimpress.sourcecodedecorator.SammSystemImplementatingClassesLink <em>Samm System Implementating Classes Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.qimpress.sourcecodedecorator.SammSystemImplementatingClassesLink
	 * @generated
	 */
	public Adapter createSammSystemImplementatingClassesLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SourceCodeDecoratorAdapterFactory
