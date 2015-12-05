/**
 */
package org.somox.sourcecodedecorator.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.somox.sourcecodedecorator.AbstractActionClassMethodLink;
import org.somox.sourcecodedecorator.AbstractMethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink;
import org.somox.sourcecodedecorator.DataTypeSourceCodeLink;
import org.somox.sourcecodedecorator.FileLevelSourceCodeLink;
import org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.MethodLevelResourceDemandingInternalBehaviorLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the
 * call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for
 * each class of the model, starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the result of the switch.
 * <!-- end-user-doc -->
 *
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage
 * @generated
 */
public class SourcecodedecoratorSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static SourcecodedecoratorPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SourcecodedecoratorSwitch() {
        if (modelPackage == null) {
            modelPackage = SourcecodedecoratorPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param ePackage
     *            the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(final EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result;
     * it yields that result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(final int classifierID, final EObject theEObject) {
        switch (classifierID) {
        case SourcecodedecoratorPackage.FILE_LEVEL_SOURCE_CODE_LINK: {
            final FileLevelSourceCodeLink fileLevelSourceCodeLink = (FileLevelSourceCodeLink) theEObject;
            T result = this.caseFileLevelSourceCodeLink(fileLevelSourceCodeLink);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case SourcecodedecoratorPackage.METHOD_LEVEL_SOURCE_CODE_LINK: {
            final MethodLevelSourceCodeLink methodLevelSourceCodeLink = (MethodLevelSourceCodeLink) theEObject;
            T result = this.caseMethodLevelSourceCodeLink(methodLevelSourceCodeLink);
            if (result == null) {
                result = this.caseAbstractMethodLevelSourceCodeLink(methodLevelSourceCodeLink);
            }
            if (result == null) {
                result = this.caseFileLevelSourceCodeLink(methodLevelSourceCodeLink);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case SourcecodedecoratorPackage.CONTROL_FLOW_LEVEL_SOURCE_CODE_LINK: {
            final ControlFlowLevelSourceCodeLink controlFlowLevelSourceCodeLink = (ControlFlowLevelSourceCodeLink) theEObject;
            T result = this.caseControlFlowLevelSourceCodeLink(controlFlowLevelSourceCodeLink);
            if (result == null) {
                result = this.caseMethodLevelSourceCodeLink(controlFlowLevelSourceCodeLink);
            }
            if (result == null) {
                result = this.caseAbstractMethodLevelSourceCodeLink(controlFlowLevelSourceCodeLink);
            }
            if (result == null) {
                result = this.caseFileLevelSourceCodeLink(controlFlowLevelSourceCodeLink);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case SourcecodedecoratorPackage.SOURCE_CODE_DECORATOR_REPOSITORY: {
            final SourceCodeDecoratorRepository sourceCodeDecoratorRepository = (SourceCodeDecoratorRepository) theEObject;
            T result = this.caseSourceCodeDecoratorRepository(sourceCodeDecoratorRepository);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case SourcecodedecoratorPackage.INTERFACE_SOURCE_CODE_LINK: {
            final InterfaceSourceCodeLink interfaceSourceCodeLink = (InterfaceSourceCodeLink) theEObject;
            T result = this.caseInterfaceSourceCodeLink(interfaceSourceCodeLink);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case SourcecodedecoratorPackage.COMPONENT_IMPLEMENTING_CLASSES_LINK: {
            final ComponentImplementingClassesLink componentImplementingClassesLink = (ComponentImplementingClassesLink) theEObject;
            T result = this.caseComponentImplementingClassesLink(componentImplementingClassesLink);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case SourcecodedecoratorPackage.PCM_SYSTEM_IMPLEMENTATING_CLASSES_LINK: {
            final PCMSystemImplementatingClassesLink pcmSystemImplementatingClassesLink = (PCMSystemImplementatingClassesLink) theEObject;
            T result = this.casePCMSystemImplementatingClassesLink(pcmSystemImplementatingClassesLink);
            if (result == null) {
                result = this.caseComponentImplementingClassesLink(pcmSystemImplementatingClassesLink);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case SourcecodedecoratorPackage.DATA_TYPE_SOURCE_CODE_LINK: {
            final DataTypeSourceCodeLink dataTypeSourceCodeLink = (DataTypeSourceCodeLink) theEObject;
            T result = this.caseDataTypeSourceCodeLink(dataTypeSourceCodeLink);
            if (result == null) {
                result = this.caseFileLevelSourceCodeLink(dataTypeSourceCodeLink);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case SourcecodedecoratorPackage.INNER_DATATYPE_SOURCE_CODE_LINK: {
            final InnerDatatypeSourceCodeLink innerDatatypeSourceCodeLink = (InnerDatatypeSourceCodeLink) theEObject;
            T result = this.caseInnerDatatypeSourceCodeLink(innerDatatypeSourceCodeLink);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case SourcecodedecoratorPackage.ABSTRACT_ACTION_CLASS_METHOD_LINK: {
            final AbstractActionClassMethodLink abstractActionClassMethodLink = (AbstractActionClassMethodLink) theEObject;
            T result = this.caseAbstractActionClassMethodLink(abstractActionClassMethodLink);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case SourcecodedecoratorPackage.METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK: {
            final MethodLevelResourceDemandingInternalBehaviorLink methodLevelResourceDemandingInternalBehaviorLink = (MethodLevelResourceDemandingInternalBehaviorLink) theEObject;
            T result = this.caseMethodLevelResourceDemandingInternalBehaviorLink(
                    methodLevelResourceDemandingInternalBehaviorLink);
            if (result == null) {
                result = this.caseAbstractMethodLevelSourceCodeLink(methodLevelResourceDemandingInternalBehaviorLink);
            }
            if (result == null) {
                result = this.caseFileLevelSourceCodeLink(methodLevelResourceDemandingInternalBehaviorLink);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case SourcecodedecoratorPackage.ABSTRACT_METHOD_LEVEL_SOURCE_CODE_LINK: {
            final AbstractMethodLevelSourceCodeLink abstractMethodLevelSourceCodeLink = (AbstractMethodLevelSourceCodeLink) theEObject;
            T result = this.caseAbstractMethodLevelSourceCodeLink(abstractMethodLevelSourceCodeLink);
            if (result == null) {
                result = this.caseFileLevelSourceCodeLink(abstractMethodLevelSourceCodeLink);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        default:
            return this.defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>File Level Source Code Link</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>File Level Source Code Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFileLevelSourceCodeLink(final FileLevelSourceCodeLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Method Level Source Code Link</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Method Level Source Code Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMethodLevelSourceCodeLink(final MethodLevelSourceCodeLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Control Flow Level Source Code Link</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Control Flow Level Source Code Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseControlFlowLevelSourceCodeLink(final ControlFlowLevelSourceCodeLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Source Code Decorator Repository</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Source Code Decorator Repository</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSourceCodeDecoratorRepository(final SourceCodeDecoratorRepository object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Interface Source Code Link</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Interface Source Code Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInterfaceSourceCodeLink(final InterfaceSourceCodeLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Component Implementing Classes Link</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Component Implementing Classes Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComponentImplementingClassesLink(final ComponentImplementingClassesLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>PCM System Implementating Classes Link</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>PCM System Implementating Classes Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePCMSystemImplementatingClassesLink(final PCMSystemImplementatingClassesLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Data Type Source Code Link</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Data Type Source Code Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataTypeSourceCodeLink(final DataTypeSourceCodeLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Inner Datatype Source Code Link</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Inner Datatype Source Code Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInnerDatatypeSourceCodeLink(final InnerDatatypeSourceCodeLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Abstract Action Class Method Link</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Abstract Action Class Method Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractActionClassMethodLink(final AbstractActionClassMethodLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Method Level Resource Demanding Internal Behavior Link</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Method Level Resource Demanding Internal Behavior Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMethodLevelResourceDemandingInternalBehaviorLink(
            final MethodLevelResourceDemandingInternalBehaviorLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Abstract Method Level Source Code Link</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Abstract Method Level Source Code Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractMethodLevelSourceCodeLink(final AbstractMethodLevelSourceCodeLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch, but this is the last case anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(final EObject object) {
        return null;
    }

} // SourcecodedecoratorSwitch
