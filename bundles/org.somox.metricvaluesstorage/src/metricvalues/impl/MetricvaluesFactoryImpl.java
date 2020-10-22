/**
 */
package metricvalues.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import metricvalues.Component;
import metricvalues.ComponentCandidate;
import metricvalues.Iteration;
import metricvalues.MetricValue;
import metricvalues.MetricValuesModel;
import metricvalues.MetricvaluesFactory;
import metricvalues.MetricvaluesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class MetricvaluesFactoryImpl extends EFactoryImpl implements MetricvaluesFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static MetricvaluesFactory init() {
        try {
            final MetricvaluesFactory theMetricvaluesFactory = (MetricvaluesFactory) EPackage.Registry.INSTANCE
                    .getEFactory(MetricvaluesPackage.eNS_URI);
            if (theMetricvaluesFactory != null) {
                return theMetricvaluesFactory;
            }
        } catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new MetricvaluesFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MetricvaluesFactoryImpl() {
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
        case MetricvaluesPackage.COMPONENT:
            return this.createComponent();
        case MetricvaluesPackage.COMPONENT_CANDIDATE:
            return this.createComponentCandidate();
        case MetricvaluesPackage.ITERATION:
            return this.createIteration();
        case MetricvaluesPackage.METRIC_VALUE:
            return this.createMetricValue();
        case MetricvaluesPackage.METRIC_VALUES_MODEL:
            return this.createMetricValuesModel();
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
    public Component createComponent() {
        final ComponentImpl component = new ComponentImpl();
        return component;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ComponentCandidate createComponentCandidate() {
        final ComponentCandidateImpl componentCandidate = new ComponentCandidateImpl();
        return componentCandidate;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Iteration createIteration() {
        final IterationImpl iteration = new IterationImpl();
        return iteration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetricValue createMetricValue() {
        final MetricValueImpl metricValue = new MetricValueImpl();
        return metricValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetricValuesModel createMetricValuesModel() {
        final MetricValuesModelImpl metricValuesModel = new MetricValuesModelImpl();
        return metricValuesModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetricvaluesPackage getMetricvaluesPackage() {
        return (MetricvaluesPackage) this.getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static MetricvaluesPackage getPackage() {
        return MetricvaluesPackage.eINSTANCE;
    }

} // MetricvaluesFactoryImpl
