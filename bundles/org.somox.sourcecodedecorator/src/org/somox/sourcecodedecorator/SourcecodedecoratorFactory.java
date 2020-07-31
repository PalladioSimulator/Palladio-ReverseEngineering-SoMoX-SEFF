/**
 */
package org.somox.sourcecodedecorator;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage
 * @generated
 */
public interface SourcecodedecoratorFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    SourcecodedecoratorFactory eINSTANCE = org.somox.sourcecodedecorator.impl.SourcecodedecoratorFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>File Level Source Code Link</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Level Source Code Link</em>'.
	 * @generated
	 */
    FileLevelSourceCodeLink createFileLevelSourceCodeLink();

    /**
	 * Returns a new object of class '<em>Method Level Source Code Link</em>'.
	 * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Level Source Code Link</em>'.
	 * @generated
	 */
    MethodLevelSourceCodeLink createMethodLevelSourceCodeLink();

    /**
     * Returns a new object of class '<em>Control Flow Level Source Code Link</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Control Flow Level Source Code Link</em>'.
     * @generated
     */
    ControlFlowLevelSourceCodeLink createControlFlowLevelSourceCodeLink();

    /**
     * Returns a new object of class '<em>Source Code Decorator Repository</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Source Code Decorator Repository</em>'.
     * @generated
     */
    SourceCodeDecoratorRepository createSourceCodeDecoratorRepository();

    /**
	 * Returns a new object of class '<em>Interface Source Code Link</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interface Source Code Link</em>'.
	 * @generated
	 */
    InterfaceSourceCodeLink createInterfaceSourceCodeLink();

    /**
     * Returns a new object of class '<em>Component Implementing Classes Link</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Component Implementing Classes Link</em>'.
     * @generated
     */
    ComponentImplementingClassesLink createComponentImplementingClassesLink();

    /**
     * Returns a new object of class '<em>PCM System Implementating Classes Link</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>PCM System Implementating Classes Link</em>'.
     * @generated
     */
    PCMSystemImplementatingClassesLink createPCMSystemImplementatingClassesLink();

    /**
	 * Returns a new object of class '<em>Data Type Source Code Link</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Type Source Code Link</em>'.
	 * @generated
	 */
    DataTypeSourceCodeLink createDataTypeSourceCodeLink();

    /**
	 * Returns a new object of class '<em>Inner Datatype Source Code Link</em>'.
	 * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Inner Datatype Source Code Link</em>'.
	 * @generated
	 */
    InnerDatatypeSourceCodeLink createInnerDatatypeSourceCodeLink();

    /**
     * Returns a new object of class '<em>Abstract Action Class Method Link</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Abstract Action Class Method Link</em>'.
     * @generated
     */
    AbstractActionClassMethodLink createAbstractActionClassMethodLink();

    /**
	 * Returns a new object of class '<em>Method Level Resource Demanding Internal Behavior Link</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Level Resource Demanding Internal Behavior Link</em>'.
	 * @generated
	 */
    MethodLevelResourceDemandingInternalBehaviorLink createMethodLevelResourceDemandingInternalBehaviorLink();

    /**
	 * Returns a new object of class '<em>SEFF2 Method Mapping</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>SEFF2 Method Mapping</em>'.
	 * @generated
	 */
    SEFF2MethodMapping createSEFF2MethodMapping();

    /**
	 * Returns a new object of class '<em>Seff Element Source Code Link</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Seff Element Source Code Link</em>'.
	 * @generated
	 */
    SeffElementSourceCodeLink createSeffElementSourceCodeLink();

    /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    SourcecodedecoratorPackage getSourcecodedecoratorPackage();

} // SourcecodedecoratorFactory
