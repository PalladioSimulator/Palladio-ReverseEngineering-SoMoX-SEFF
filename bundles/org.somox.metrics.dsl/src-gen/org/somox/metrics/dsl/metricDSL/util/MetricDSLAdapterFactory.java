/**
 */
package org.somox.metrics.dsl.metricDSL.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.somox.metrics.dsl.metricDSL.BoundAndWeight;
import org.somox.metrics.dsl.metricDSL.Constant;
import org.somox.metrics.dsl.metricDSL.ExternalMetric;
import org.somox.metrics.dsl.metricDSL.InternalMetric;
import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.MetricAndWeight;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.MetricDefinition;
import org.somox.metrics.dsl.metricDSL.MetricModel;
import org.somox.metrics.dsl.metricDSL.Parameter;
import org.somox.metrics.dsl.metricDSL.RatioMetric;
import org.somox.metrics.dsl.metricDSL.StepwiseMetric;
import org.somox.metrics.dsl.metricDSL.WeightedMetric;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.somox.metrics.dsl.metricDSL.MetricDSLPackage
 * @generated
 */
public class MetricDSLAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static MetricDSLPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MetricDSLAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = MetricDSLPackage.eINSTANCE;
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
    protected MetricDSLSwitch<Adapter> modelSwitch = new MetricDSLSwitch<Adapter>() {
        @Override
        public Adapter caseMetricModel(final MetricModel object) {
            return MetricDSLAdapterFactory.this.createMetricModelAdapter();
        }

        @Override
        public Adapter caseMetric(final Metric object) {
            return MetricDSLAdapterFactory.this.createMetricAdapter();
        }

        @Override
        public Adapter caseExternalMetric(final ExternalMetric object) {
            return MetricDSLAdapterFactory.this.createExternalMetricAdapter();
        }

        @Override
        public Adapter caseInternalMetric(final InternalMetric object) {
            return MetricDSLAdapterFactory.this.createInternalMetricAdapter();
        }

        @Override
        public Adapter caseNumber(final org.somox.metrics.dsl.metricDSL.Number object) {
            return MetricDSLAdapterFactory.this.createNumberAdapter();
        }

        @Override
        public Adapter caseParameter(final Parameter object) {
            return MetricDSLAdapterFactory.this.createParameterAdapter();
        }

        @Override
        public Adapter caseConstant(final Constant object) {
            return MetricDSLAdapterFactory.this.createConstantAdapter();
        }

        @Override
        public Adapter caseMetricDefinition(final MetricDefinition object) {
            return MetricDSLAdapterFactory.this.createMetricDefinitionAdapter();
        }

        @Override
        public Adapter caseWeightedMetric(final WeightedMetric object) {
            return MetricDSLAdapterFactory.this.createWeightedMetricAdapter();
        }

        @Override
        public Adapter caseStepwiseMetric(final StepwiseMetric object) {
            return MetricDSLAdapterFactory.this.createStepwiseMetricAdapter();
        }

        @Override
        public Adapter caseRatioMetric(final RatioMetric object) {
            return MetricDSLAdapterFactory.this.createRatioMetricAdapter();
        }

        @Override
        public Adapter caseBoundAndWeight(final BoundAndWeight object) {
            return MetricDSLAdapterFactory.this.createBoundAndWeightAdapter();
        }

        @Override
        public Adapter caseMetricAndWeight(final MetricAndWeight object) {
            return MetricDSLAdapterFactory.this.createMetricAndWeightAdapter();
        }

        @Override
        public Adapter defaultCase(final EObject object) {
            return MetricDSLAdapterFactory.this.createEObjectAdapter();
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
     * Creates a new adapter for an object of class '
     * {@link org.somox.metrics.dsl.metricDSL.MetricModel <em>Metric Model</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.MetricModel
     * @generated
     */
    public Adapter createMetricModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.metrics.dsl.metricDSL.Metric
     * <em>Metric</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.Metric
     * @generated
     */
    public Adapter createMetricAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.somox.metrics.dsl.metricDSL.ExternalMetric <em>External Metric</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.ExternalMetric
     * @generated
     */
    public Adapter createExternalMetricAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.somox.metrics.dsl.metricDSL.InternalMetric <em>Internal Metric</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.InternalMetric
     * @generated
     */
    public Adapter createInternalMetricAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.metrics.dsl.metricDSL.Number
     * <em>Number</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.Number
     * @generated
     */
    public Adapter createNumberAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.somox.metrics.dsl.metricDSL.Parameter <em>Parameter</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.Parameter
     * @generated
     */
    public Adapter createParameterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.somox.metrics.dsl.metricDSL.Constant
     * <em>Constant</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.Constant
     * @generated
     */
    public Adapter createConstantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.somox.metrics.dsl.metricDSL.MetricDefinition <em>Metric Definition</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.MetricDefinition
     * @generated
     */
    public Adapter createMetricDefinitionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.somox.metrics.dsl.metricDSL.WeightedMetric <em>Weighted Metric</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.WeightedMetric
     * @generated
     */
    public Adapter createWeightedMetricAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.somox.metrics.dsl.metricDSL.StepwiseMetric <em>Stepwise Metric</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.StepwiseMetric
     * @generated
     */
    public Adapter createStepwiseMetricAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.somox.metrics.dsl.metricDSL.RatioMetric <em>Ratio Metric</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.RatioMetric
     * @generated
     */
    public Adapter createRatioMetricAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.somox.metrics.dsl.metricDSL.BoundAndWeight <em>Bound And Weight</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.BoundAndWeight
     * @generated
     */
    public Adapter createBoundAndWeightAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.somox.metrics.dsl.metricDSL.MetricAndWeight <em>Metric And Weight</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.somox.metrics.dsl.metricDSL.MetricAndWeight
     * @generated
     */
    public Adapter createMetricAndWeightAdapter() {
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

} // MetricDSLAdapterFactory
