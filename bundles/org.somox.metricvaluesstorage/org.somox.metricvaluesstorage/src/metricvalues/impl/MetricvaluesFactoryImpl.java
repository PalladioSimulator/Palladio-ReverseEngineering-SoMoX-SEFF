/**
 */
package metricvalues.impl;

import metricvalues.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MetricvaluesFactoryImpl extends EFactoryImpl implements MetricvaluesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MetricvaluesFactory init() {
		try {
			MetricvaluesFactory theMetricvaluesFactory = (MetricvaluesFactory)EPackage.Registry.INSTANCE.getEFactory(MetricvaluesPackage.eNS_URI);
			if (theMetricvaluesFactory != null) {
				return theMetricvaluesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MetricvaluesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetricvaluesFactoryImpl() {
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
			case MetricvaluesPackage.COMPONENT: return createComponent();
			case MetricvaluesPackage.COMPONENT_CANDIDATE: return createComponentCandidate();
			case MetricvaluesPackage.ITERATION: return createIteration();
			case MetricvaluesPackage.METRIC_VALUE: return createMetricValue();
			case MetricvaluesPackage.METRIC_VALUES_MODEL: return createMetricValuesModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component createComponent() {
		ComponentImpl component = new ComponentImpl();
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentCandidate createComponentCandidate() {
		ComponentCandidateImpl componentCandidate = new ComponentCandidateImpl();
		return componentCandidate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Iteration createIteration() {
		IterationImpl iteration = new IterationImpl();
		return iteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetricValue createMetricValue() {
		MetricValueImpl metricValue = new MetricValueImpl();
		return metricValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetricValuesModel createMetricValuesModel() {
		MetricValuesModelImpl metricValuesModel = new MetricValuesModelImpl();
		return metricValuesModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetricvaluesPackage getMetricvaluesPackage() {
		return (MetricvaluesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MetricvaluesPackage getPackage() {
		return MetricvaluesPackage.eINSTANCE;
	}

} //MetricvaluesFactoryImpl
