/**
 */
package metricvalues.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import metricvalues.Component;
import metricvalues.ComponentCandidate;
import metricvalues.Iteration;
import metricvalues.MetricValue;
import metricvalues.MetricValuesModel;
import metricvalues.MetricvaluesPackage;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 *
 * @see metricvalues.MetricvaluesPackage
 * @generated
 */
public class MetricvaluesAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static MetricvaluesPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MetricvaluesAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = MetricvaluesPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc
     * --> This implementation returns <code>true</code> if the object is either the model's package
     * or is an instance object of the model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(final Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected MetricvaluesSwitch<Adapter> modelSwitch = new MetricvaluesSwitch<Adapter>() {
        @Override
        public Adapter caseComponent(final Component object) {
            return MetricvaluesAdapterFactory.this.createComponentAdapter();
        }

        @Override
        public Adapter caseComponentCandidate(final ComponentCandidate object) {
            return MetricvaluesAdapterFactory.this.createComponentCandidateAdapter();
        }

        @Override
        public Adapter caseIteration(final Iteration object) {
            return MetricvaluesAdapterFactory.this.createIterationAdapter();
        }

        @Override
        public Adapter caseMetricValue(final MetricValue object) {
            return MetricvaluesAdapterFactory.this.createMetricValueAdapter();
        }

        @Override
        public Adapter caseMetricValuesModel(final MetricValuesModel object) {
            return MetricvaluesAdapterFactory.this.createMetricValuesModelAdapter();
        }

        @Override
        public Adapter defaultCase(final EObject object) {
            return MetricvaluesAdapterFactory.this.createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(final Notifier target) {
        return this.modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '{@link metricvalues.Component
     * <em>Component</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all
     * the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see metricvalues.Component
     * @generated
     */
    public Adapter createComponentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link metricvalues.ComponentCandidate
     * <em>Component Candidate</em>}'. <!-- begin-user-doc --> This default implementation returns
     * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will
     * catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see metricvalues.ComponentCandidate
     * @generated
     */
    public Adapter createComponentCandidateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link metricvalues.Iteration
     * <em>Iteration</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all
     * the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see metricvalues.Iteration
     * @generated
     */
    public Adapter createIterationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link metricvalues.MetricValue
     * <em>Metric Value</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all
     * the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see metricvalues.MetricValue
     * @generated
     */
    public Adapter createMetricValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link metricvalues.MetricValuesModel
     * <em>Metric Values Model</em>}'. <!-- begin-user-doc --> This default implementation returns
     * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will
     * catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see metricvalues.MetricValuesModel
     * @generated
     */
    public Adapter createMetricValuesModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default
     * implementation returns null. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // MetricvaluesAdapterFactory
