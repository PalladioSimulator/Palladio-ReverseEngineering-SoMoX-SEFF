/**
 */
package org.somox.sourcecodedecorator.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.somox.sourcecodedecorator.*;
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
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage
 * @generated
 */
public class SourcecodedecoratorAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static SourcecodedecoratorPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public SourcecodedecoratorAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = SourcecodedecoratorPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc
     * --> This implementation returns <code>true</code> if the object is either the model's package
     * or is an instance object of the model. <!-- end-user-doc -->
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
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    protected SourcecodedecoratorSwitch<Adapter> modelSwitch = new SourcecodedecoratorSwitch<Adapter>() {
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
            public Adapter casePCMSystemImplementatingClassesLink(PCMSystemImplementatingClassesLink object) {
                return createPCMSystemImplementatingClassesLinkAdapter();
            }
            @Override
            public Adapter caseDataTypeSourceCodeLink(DataTypeSourceCodeLink object) {
                return createDataTypeSourceCodeLinkAdapter();
            }
            @Override
            public Adapter caseInnerDatatypeSourceCodeLink(InnerDatatypeSourceCodeLink object) {
                return createInnerDatatypeSourceCodeLinkAdapter();
            }
            @Override
            public Adapter caseAbstractActionClassMethodLink(AbstractActionClassMethodLink object) {
                return createAbstractActionClassMethodLinkAdapter();
            }
            @Override
            public Adapter caseMethodLevelResourceDemandingInternalBehaviorLink(MethodLevelResourceDemandingInternalBehaviorLink object) {
                return createMethodLevelResourceDemandingInternalBehaviorLinkAdapter();
            }
            @Override
            public Adapter caseAbstractMethodLevelSourceCodeLink(AbstractMethodLevelSourceCodeLink object) {
                return createAbstractMethodLevelSourceCodeLinkAdapter();
            }
            @Override
            public Adapter caseSEFF2MethodMapping(SEFF2MethodMapping object) {
                return createSEFF2MethodMappingAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.FileLevelSourceCodeLink <em>File Level Source Code Link</em>}'.
     * <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.FileLevelSourceCodeLink
     * @generated
     */
    public Adapter createFileLevelSourceCodeLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.MethodLevelSourceCodeLink <em>Method Level Source Code Link</em>}'.
     * <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.MethodLevelSourceCodeLink
     * @generated
     */
    public Adapter createMethodLevelSourceCodeLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink <em>Control Flow Level Source Code Link</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.ControlFlowLevelSourceCodeLink
     * @generated
     */
    public Adapter createControlFlowLevelSourceCodeLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.SourceCodeDecoratorRepository <em>Source Code Decorator Repository</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.SourceCodeDecoratorRepository
     * @generated
     */
    public Adapter createSourceCodeDecoratorRepositoryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.InterfaceSourceCodeLink <em>Interface Source Code Link</em>}'.
     * <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.InterfaceSourceCodeLink
     * @generated
     */
    public Adapter createInterfaceSourceCodeLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.ComponentImplementingClassesLink <em>Component Implementing Classes Link</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.ComponentImplementingClassesLink
     * @generated
     */
    public Adapter createComponentImplementingClassesLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink <em>PCM System Implementating Classes Link</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink
     * @generated
     */
    public Adapter createPCMSystemImplementatingClassesLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.DataTypeSourceCodeLink <em>Data Type Source Code Link</em>}'.
     * <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.DataTypeSourceCodeLink
     * @generated
     */
    public Adapter createDataTypeSourceCodeLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink <em>Inner Datatype Source Code Link</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink
     * @generated
     */
    public Adapter createInnerDatatypeSourceCodeLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.AbstractActionClassMethodLink <em>Abstract Action Class Method Link</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.AbstractActionClassMethodLink
     * @generated
     */
    public Adapter createAbstractActionClassMethodLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.MethodLevelResourceDemandingInternalBehaviorLink <em>Method Level Resource Demanding Internal Behavior Link</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.MethodLevelResourceDemandingInternalBehaviorLink
     * @generated
     */
    public Adapter createMethodLevelResourceDemandingInternalBehaviorLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.AbstractMethodLevelSourceCodeLink <em>Abstract Method Level Source Code Link</em>}'.
     * <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.AbstractMethodLevelSourceCodeLink
     * @generated
     */
    public Adapter createAbstractMethodLevelSourceCodeLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.sourcecodedecorator.SEFF2MethodMapping <em>SEFF2 Method Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.somox.sourcecodedecorator.SEFF2MethodMapping
     * @generated
     */
    public Adapter createSEFF2MethodMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc --> This default
     * implementation returns null. <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // SourcecodedecoratorAdapterFactory
