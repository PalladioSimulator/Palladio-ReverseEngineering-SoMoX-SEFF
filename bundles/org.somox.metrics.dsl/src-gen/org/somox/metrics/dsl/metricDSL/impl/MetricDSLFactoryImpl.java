/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.somox.metrics.dsl.metricDSL.BoundAndWeight;
import org.somox.metrics.dsl.metricDSL.Constant;
import org.somox.metrics.dsl.metricDSL.ExternalMetric;
import org.somox.metrics.dsl.metricDSL.InternalMetric;
import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.MetricAndWeight;
import org.somox.metrics.dsl.metricDSL.MetricDSLFactory;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.MetricDefinition;
import org.somox.metrics.dsl.metricDSL.MetricModel;
import org.somox.metrics.dsl.metricDSL.Parameter;
import org.somox.metrics.dsl.metricDSL.RatioMetric;
import org.somox.metrics.dsl.metricDSL.StepwiseMetric;
import org.somox.metrics.dsl.metricDSL.WeightedMetric;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class MetricDSLFactoryImpl extends EFactoryImpl implements MetricDSLFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static MetricDSLFactory init() {
        try {
            final MetricDSLFactory theMetricDSLFactory = (MetricDSLFactory) EPackage.Registry.INSTANCE
                    .getEFactory(MetricDSLPackage.eNS_URI);
            if (theMetricDSLFactory != null) {
                return theMetricDSLFactory;
            }
        } catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new MetricDSLFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MetricDSLFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject create(final EClass eClass) {
        switch (eClass.getClassifierID()) {
        case MetricDSLPackage.METRIC_MODEL:
            return this.createMetricModel();
        case MetricDSLPackage.METRIC:
            return this.createMetric();
        case MetricDSLPackage.EXTERNAL_METRIC:
            return this.createExternalMetric();
        case MetricDSLPackage.INTERNAL_METRIC:
            return this.createInternalMetric();
        case MetricDSLPackage.NUMBER:
            return this.createNumber();
        case MetricDSLPackage.PARAMETER:
            return this.createParameter();
        case MetricDSLPackage.CONSTANT:
            return this.createConstant();
        case MetricDSLPackage.METRIC_DEFINITION:
            return this.createMetricDefinition();
        case MetricDSLPackage.WEIGHTED_METRIC:
            return this.createWeightedMetric();
        case MetricDSLPackage.STEPWISE_METRIC:
            return this.createStepwiseMetric();
        case MetricDSLPackage.RATIO_METRIC:
            return this.createRatioMetric();
        case MetricDSLPackage.BOUND_AND_WEIGHT:
            return this.createBoundAndWeight();
        case MetricDSLPackage.METRIC_AND_WEIGHT:
            return this.createMetricAndWeight();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetricModel createMetricModel() {
        final MetricModelImpl metricModel = new MetricModelImpl();
        return metricModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Metric createMetric() {
        final MetricImpl metric = new MetricImpl();
        return metric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExternalMetric createExternalMetric() {
        final ExternalMetricImpl externalMetric = new ExternalMetricImpl();
        return externalMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InternalMetric createInternalMetric() {
        final InternalMetricImpl internalMetric = new InternalMetricImpl();
        return internalMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public org.somox.metrics.dsl.metricDSL.Number createNumber() {
        final NumberImpl number = new NumberImpl();
        return number;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Parameter createParameter() {
        final ParameterImpl parameter = new ParameterImpl();
        return parameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Constant createConstant() {
        final ConstantImpl constant = new ConstantImpl();
        return constant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetricDefinition createMetricDefinition() {
        final MetricDefinitionImpl metricDefinition = new MetricDefinitionImpl();
        return metricDefinition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public WeightedMetric createWeightedMetric() {
        final WeightedMetricImpl weightedMetric = new WeightedMetricImpl();
        return weightedMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public StepwiseMetric createStepwiseMetric() {
        final StepwiseMetricImpl stepwiseMetric = new StepwiseMetricImpl();
        return stepwiseMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RatioMetric createRatioMetric() {
        final RatioMetricImpl ratioMetric = new RatioMetricImpl();
        return ratioMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BoundAndWeight createBoundAndWeight() {
        final BoundAndWeightImpl boundAndWeight = new BoundAndWeightImpl();
        return boundAndWeight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetricAndWeight createMetricAndWeight() {
        final MetricAndWeightImpl metricAndWeight = new MetricAndWeightImpl();
        return metricAndWeight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetricDSLPackage getMetricDSLPackage() {
        return (MetricDSLPackage) this.getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static MetricDSLPackage getPackage() {
        return MetricDSLPackage.eINSTANCE;
    }

} // MetricDSLFactoryImpl
