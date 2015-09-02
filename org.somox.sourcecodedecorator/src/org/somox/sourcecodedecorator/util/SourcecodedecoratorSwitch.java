/**
 */
package org.somox.sourcecodedecorator.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.somox.sourcecodedecorator.*;

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
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage
 * @generated
 */
public class SourcecodedecoratorSwitch<T> extends Switch<T> {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static SourcecodedecoratorPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SourcecodedecoratorSwitch() {
        if (modelPackage == null) {
            modelPackage = SourcecodedecoratorPackage.eINSTANCE;
        }
    }

	/**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param ePackage the package in question.
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
            case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK: {
                FileLevelSourceCodeLink fileLevelSourceCodeLink = (FileLevelSourceCodeLink)theEObject;
                T result = caseFileLevelSourceCodeLink(fileLevelSourceCodeLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK: {
                MethodLevelSourceCodeLink methodLevelSourceCodeLink = (MethodLevelSourceCodeLink)theEObject;
                T result = caseMethodLevelSourceCodeLink(methodLevelSourceCodeLink);
                if (result == null) result = caseFileLevelSourceCodeLink(methodLevelSourceCodeLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK: {
                ControlFlowLevelSourceCodeLink controlFlowLevelSourceCodeLink = (ControlFlowLevelSourceCodeLink)theEObject;
                T result = caseControlFlowLevelSourceCodeLink(controlFlowLevelSourceCodeLink);
                if (result == null) result = caseMethodLevelSourceCodeLink(controlFlowLevelSourceCodeLink);
                if (result == null) result = caseFileLevelSourceCodeLink(controlFlowLevelSourceCodeLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY: {
                SourceCodeDecoratorRepository sourceCodeDecoratorRepository = (SourceCodeDecoratorRepository)theEObject;
                T result = caseSourceCodeDecoratorRepository(sourceCodeDecoratorRepository);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK: {
                InterfaceSourceCodeLink interfaceSourceCodeLink = (InterfaceSourceCodeLink)theEObject;
                T result = caseInterfaceSourceCodeLink(interfaceSourceCodeLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK: {
                ComponentImplementingClassesLink componentImplementingClassesLink = (ComponentImplementingClassesLink)theEObject;
                T result = caseComponentImplementingClassesLink(componentImplementingClassesLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SourcecodedecoratorPackage.PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK: {
                PCMSystemImplementatingClassesLink pcmSystemImplementatingClassesLink = (PCMSystemImplementatingClassesLink)theEObject;
                T result = casePCMSystemImplementatingClassesLink(pcmSystemImplementatingClassesLink);
                if (result == null) result = caseComponentImplementingClassesLink(pcmSystemImplementatingClassesLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK: {
                DataTypeSourceCodeLink dataTypeSourceCodeLink = (DataTypeSourceCodeLink)theEObject;
                T result = caseDataTypeSourceCodeLink(dataTypeSourceCodeLink);
                if (result == null) result = caseFileLevelSourceCodeLink(dataTypeSourceCodeLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK: {
                InnerDatatypeSourceCodeLink innerDatatypeSourceCodeLink = (InnerDatatypeSourceCodeLink)theEObject;
                T result = caseInnerDatatypeSourceCodeLink(innerDatatypeSourceCodeLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK: {
                AbstractActionClassMethodLink abstractActionClassMethodLink = (AbstractActionClassMethodLink)theEObject;
                T result = caseAbstractActionClassMethodLink(abstractActionClassMethodLink);
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
     * Returns the result of interpreting the object as an instance of '<em>Source Code Decorator Repository</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Source Code Decorator Repository</em>'.
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
     * Returns the result of interpreting the object as an instance of '<em>PCM System Implementating Classes Link</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>PCM System Implementating Classes Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T casePCMSystemImplementatingClassesLink(PCMSystemImplementatingClassesLink object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Data Type Source Code Link</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Type Source Code Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataTypeSourceCodeLink(DataTypeSourceCodeLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Inner Datatype Source Code Link</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Inner Datatype Source Code Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInnerDatatypeSourceCodeLink(InnerDatatypeSourceCodeLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Action Class Method Link</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Action Class Method Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractActionClassMethodLink(AbstractActionClassMethodLink object) {
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

} //SourcecodedecoratorSwitch
