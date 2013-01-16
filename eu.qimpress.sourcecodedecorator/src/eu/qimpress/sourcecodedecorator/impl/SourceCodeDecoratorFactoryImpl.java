/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.sourcecodedecorator.impl;

import eu.qimpress.sourcecodedecorator.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink;
import eu.qimpress.sourcecodedecorator.ControlFlowLevelSourceCodeLink;
import eu.qimpress.sourcecodedecorator.FileLevelSourceCodeLink;
import eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink;
import eu.qimpress.sourcecodedecorator.MethodLevelSourceCodeLink;
import eu.qimpress.sourcecodedecorator.SammSystemImplementatingClassesLink;
import eu.qimpress.sourcecodedecorator.SourceCodeDecoratorFactory;
import eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage;
import eu.qimpress.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SourceCodeDecoratorFactoryImpl extends EFactoryImpl implements SourceCodeDecoratorFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SourceCodeDecoratorFactory init() {
		try {
			SourceCodeDecoratorFactory theSourceCodeDecoratorFactory = (SourceCodeDecoratorFactory)EPackage.Registry.INSTANCE.getEFactory("http://q-impress.eu/sourcecodedecorator"); 
			if (theSourceCodeDecoratorFactory != null) {
				return theSourceCodeDecoratorFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SourceCodeDecoratorFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceCodeDecoratorFactoryImpl() {
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
			case SourceCodeDecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK: return createFileLevelSourceCodeLink();
			case SourceCodeDecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK: return createMethodLevelSourceCodeLink();
			case SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK: return createControlFlowLevelSourceCodeLink();
			case SourceCodeDecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY: return createSourceCodeDecoratorRepository();
			case SourceCodeDecoratorPackage.INTERFACE_SOURCE_CODE_LINK: return createInterfaceSourceCodeLink();
			case SourceCodeDecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK: return createComponentImplementingClassesLink();
			case SourceCodeDecoratorPackage.SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK: return createSammSystemImplementatingClassesLink();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileLevelSourceCodeLink createFileLevelSourceCodeLink() {
		FileLevelSourceCodeLinkImpl fileLevelSourceCodeLink = new FileLevelSourceCodeLinkImpl();
		return fileLevelSourceCodeLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodLevelSourceCodeLink createMethodLevelSourceCodeLink() {
		MethodLevelSourceCodeLinkImpl methodLevelSourceCodeLink = new MethodLevelSourceCodeLinkImpl();
		return methodLevelSourceCodeLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlFlowLevelSourceCodeLink createControlFlowLevelSourceCodeLink() {
		ControlFlowLevelSourceCodeLinkImpl controlFlowLevelSourceCodeLink = new ControlFlowLevelSourceCodeLinkImpl();
		return controlFlowLevelSourceCodeLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceCodeDecoratorRepository createSourceCodeDecoratorRepository() {
		SourceCodeDecoratorRepositoryImpl sourceCodeDecoratorRepository = new SourceCodeDecoratorRepositoryImpl();
		return sourceCodeDecoratorRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InterfaceSourceCodeLink createInterfaceSourceCodeLink() {
		InterfaceSourceCodeLinkImpl interfaceSourceCodeLink = new InterfaceSourceCodeLinkImpl();
		return interfaceSourceCodeLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentImplementingClassesLink createComponentImplementingClassesLink() {
		ComponentImplementingClassesLinkImpl componentImplementingClassesLink = new ComponentImplementingClassesLinkImpl();
		return componentImplementingClassesLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SammSystemImplementatingClassesLink createSammSystemImplementatingClassesLink() {
		SammSystemImplementatingClassesLinkImpl sammSystemImplementatingClassesLink = new SammSystemImplementatingClassesLinkImpl();
		return sammSystemImplementatingClassesLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceCodeDecoratorPackage getSourceCodeDecoratorPackage() {
		return (SourceCodeDecoratorPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SourceCodeDecoratorPackage getPackage() {
		return SourceCodeDecoratorPackage.eINSTANCE;
	}

} //SourceCodeDecoratorFactoryImpl
