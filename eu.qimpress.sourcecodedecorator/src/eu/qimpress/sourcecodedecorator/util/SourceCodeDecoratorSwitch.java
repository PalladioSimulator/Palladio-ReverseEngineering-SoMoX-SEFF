/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.sourcecodedecorator.util;

import eu.qimpress.sourcecodedecorator.*;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage
 * @generated
 */
public class SourceCodeDecoratorSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SourceCodeDecoratorPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceCodeDecoratorSwitch() {
		if (modelPackage == null) {
			modelPackage = SourceCodeDecoratorPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case SourceCodeDecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK: {
				FileLevelSourceCodeLink fileLevelSourceCodeLink = (FileLevelSourceCodeLink)theEObject;
				T result = caseFileLevelSourceCodeLink(fileLevelSourceCodeLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SourceCodeDecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK: {
				MethodLevelSourceCodeLink methodLevelSourceCodeLink = (MethodLevelSourceCodeLink)theEObject;
				T result = caseMethodLevelSourceCodeLink(methodLevelSourceCodeLink);
				if (result == null) result = caseFileLevelSourceCodeLink(methodLevelSourceCodeLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SourceCodeDecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK: {
				ControlFlowLevelSourceCodeLink controlFlowLevelSourceCodeLink = (ControlFlowLevelSourceCodeLink)theEObject;
				T result = caseControlFlowLevelSourceCodeLink(controlFlowLevelSourceCodeLink);
				if (result == null) result = caseMethodLevelSourceCodeLink(controlFlowLevelSourceCodeLink);
				if (result == null) result = caseFileLevelSourceCodeLink(controlFlowLevelSourceCodeLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SourceCodeDecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY: {
				SourceCodeDecoratorRepository sourceCodeDecoratorRepository = (SourceCodeDecoratorRepository)theEObject;
				T result = caseSourceCodeDecoratorRepository(sourceCodeDecoratorRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SourceCodeDecoratorPackage.INTERFACE_SOURCE_CODE_LINK: {
				InterfaceSourceCodeLink interfaceSourceCodeLink = (InterfaceSourceCodeLink)theEObject;
				T result = caseInterfaceSourceCodeLink(interfaceSourceCodeLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SourceCodeDecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK: {
				ComponentImplementingClassesLink componentImplementingClassesLink = (ComponentImplementingClassesLink)theEObject;
				T result = caseComponentImplementingClassesLink(componentImplementingClassesLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SourceCodeDecoratorPackage.SAMM_SYSTEM_IMPLEMENTATING_CLASSES_LINK: {
				SammSystemImplementatingClassesLink sammSystemImplementatingClassesLink = (SammSystemImplementatingClassesLink)theEObject;
				T result = caseSammSystemImplementatingClassesLink(sammSystemImplementatingClassesLink);
				if (result == null) result = caseComponentImplementingClassesLink(sammSystemImplementatingClassesLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Level Source Code Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Level Source Code Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFileLevelSourceCodeLink(FileLevelSourceCodeLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Level Source Code Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Level Source Code Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodLevelSourceCodeLink(MethodLevelSourceCodeLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Control Flow Level Source Code Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Control Flow Level Source Code Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseControlFlowLevelSourceCodeLink(ControlFlowLevelSourceCodeLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSourceCodeDecoratorRepository(SourceCodeDecoratorRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interface Source Code Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interface Source Code Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInterfaceSourceCodeLink(InterfaceSourceCodeLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Implementing Classes Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Implementing Classes Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentImplementingClassesLink(ComponentImplementingClassesLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Samm System Implementating Classes Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Samm System Implementating Classes Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSammSystemImplementatingClassesLink(SammSystemImplementatingClassesLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //SourceCodeDecoratorSwitch
