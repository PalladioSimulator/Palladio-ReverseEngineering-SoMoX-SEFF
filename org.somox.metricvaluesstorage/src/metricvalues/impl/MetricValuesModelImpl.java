/**
 */
package metricvalues.impl;

import java.util.Collection;

import metricvalues.Iteration;
import metricvalues.MetricValuesModel;
import metricvalues.MetricvaluesPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metric Values Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getIterations <em>Iterations</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getMinCompThreshold <em>Min Comp Threshold</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getMaxMergeThreshold <em>Max Merge Threshold</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightLowCoupling <em>Weight Low Coupling</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightHighCoupling <em>Weight High Coupling</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightLowNameResemblance <em>Weight Low Name Resemblance</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightMidNameResemblance <em>Weight Mid Name Resemblance</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightHighNameResemblance <em>Weight High Name Resemblance</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightInterfaceViolationRelevant <em>Weight Interface Violation Relevant</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightInterfaceViolationIrrelevant <em>Weight Interface Violation Irrelevant</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightHighSLAQ <em>Weight High SLAQ</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightLowSLAQ <em>Weight Low SLAQ</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightPackageMapping <em>Weight Package Mapping</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightDirectoryMapping <em>Weight Directory Mapping</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightDMS <em>Weight DMS</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightHighestNameResemblance <em>Weight Highest Name Resemblance</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getWildcardKey <em>Wildcard Key</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getAdditionalWildcards <em>Additional Wildcards</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getMinMergeThreshold <em>Min Merge Threshold</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getMaxComposeThreshold <em>Max Compose Threshold</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getComposeThresholdDecrement <em>Compose Threshold Decrement</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getMergeThresholdDecrement <em>Merge Threshold Decrement</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getExcludedPrefixesForNameResemblance <em>Excluded Prefixes For Name Resemblance</em>}</li>
 *   <li>{@link metricvalues.impl.MetricValuesModelImpl#getExcludedSuffixesForNameResemblance <em>Excluded Suffixes For Name Resemblance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetricValuesModelImpl extends MinimalEObjectImpl.Container implements MetricValuesModel {
	/**
	 * The cached value of the '{@link #getIterations() <em>Iterations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIterations()
	 * @generated
	 * @ordered
	 */
	protected EList<Iteration> iterations;

	/**
	 * The default value of the '{@link #getMinCompThreshold() <em>Min Comp Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinCompThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final double MIN_COMP_THRESHOLD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMinCompThreshold() <em>Min Comp Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinCompThreshold()
	 * @generated
	 * @ordered
	 */
	protected double minCompThreshold = MIN_COMP_THRESHOLD_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxMergeThreshold() <em>Max Merge Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxMergeThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final double MAX_MERGE_THRESHOLD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMaxMergeThreshold() <em>Max Merge Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxMergeThreshold()
	 * @generated
	 * @ordered
	 */
	protected double maxMergeThreshold = MAX_MERGE_THRESHOLD_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightLowCoupling() <em>Weight Low Coupling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightLowCoupling()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_LOW_COUPLING_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightLowCoupling() <em>Weight Low Coupling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightLowCoupling()
	 * @generated
	 * @ordered
	 */
	protected double weightLowCoupling = WEIGHT_LOW_COUPLING_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightHighCoupling() <em>Weight High Coupling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightHighCoupling()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_HIGH_COUPLING_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightHighCoupling() <em>Weight High Coupling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightHighCoupling()
	 * @generated
	 * @ordered
	 */
	protected double weightHighCoupling = WEIGHT_HIGH_COUPLING_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightLowNameResemblance() <em>Weight Low Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightLowNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_LOW_NAME_RESEMBLANCE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightLowNameResemblance() <em>Weight Low Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightLowNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected double weightLowNameResemblance = WEIGHT_LOW_NAME_RESEMBLANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightMidNameResemblance() <em>Weight Mid Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightMidNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_MID_NAME_RESEMBLANCE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightMidNameResemblance() <em>Weight Mid Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightMidNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected double weightMidNameResemblance = WEIGHT_MID_NAME_RESEMBLANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightHighNameResemblance() <em>Weight High Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightHighNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_HIGH_NAME_RESEMBLANCE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightHighNameResemblance() <em>Weight High Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightHighNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected double weightHighNameResemblance = WEIGHT_HIGH_NAME_RESEMBLANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightInterfaceViolationRelevant() <em>Weight Interface Violation Relevant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightInterfaceViolationRelevant()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_INTERFACE_VIOLATION_RELEVANT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightInterfaceViolationRelevant() <em>Weight Interface Violation Relevant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightInterfaceViolationRelevant()
	 * @generated
	 * @ordered
	 */
	protected double weightInterfaceViolationRelevant = WEIGHT_INTERFACE_VIOLATION_RELEVANT_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightInterfaceViolationIrrelevant() <em>Weight Interface Violation Irrelevant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightInterfaceViolationIrrelevant()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_INTERFACE_VIOLATION_IRRELEVANT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightInterfaceViolationIrrelevant() <em>Weight Interface Violation Irrelevant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightInterfaceViolationIrrelevant()
	 * @generated
	 * @ordered
	 */
	protected double weightInterfaceViolationIrrelevant = WEIGHT_INTERFACE_VIOLATION_IRRELEVANT_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightHighSLAQ() <em>Weight High SLAQ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightHighSLAQ()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_HIGH_SLAQ_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightHighSLAQ() <em>Weight High SLAQ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightHighSLAQ()
	 * @generated
	 * @ordered
	 */
	protected double weightHighSLAQ = WEIGHT_HIGH_SLAQ_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightLowSLAQ() <em>Weight Low SLAQ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightLowSLAQ()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_LOW_SLAQ_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightLowSLAQ() <em>Weight Low SLAQ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightLowSLAQ()
	 * @generated
	 * @ordered
	 */
	protected double weightLowSLAQ = WEIGHT_LOW_SLAQ_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightPackageMapping() <em>Weight Package Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightPackageMapping()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_PACKAGE_MAPPING_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightPackageMapping() <em>Weight Package Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightPackageMapping()
	 * @generated
	 * @ordered
	 */
	protected double weightPackageMapping = WEIGHT_PACKAGE_MAPPING_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightDirectoryMapping() <em>Weight Directory Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightDirectoryMapping()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_DIRECTORY_MAPPING_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightDirectoryMapping() <em>Weight Directory Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightDirectoryMapping()
	 * @generated
	 * @ordered
	 */
	protected double weightDirectoryMapping = WEIGHT_DIRECTORY_MAPPING_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightDMS() <em>Weight DMS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightDMS()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_DMS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightDMS() <em>Weight DMS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightDMS()
	 * @generated
	 * @ordered
	 */
	protected double weightDMS = WEIGHT_DMS_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeightHighestNameResemblance() <em>Weight Highest Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightHighestNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected static final double WEIGHT_HIGHEST_NAME_RESEMBLANCE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWeightHighestNameResemblance() <em>Weight Highest Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeightHighestNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected double weightHighestNameResemblance = WEIGHT_HIGHEST_NAME_RESEMBLANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getWildcardKey() <em>Wildcard Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWildcardKey()
	 * @generated
	 * @ordered
	 */
	protected static final String WILDCARD_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWildcardKey() <em>Wildcard Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWildcardKey()
	 * @generated
	 * @ordered
	 */
	protected String wildcardKey = WILDCARD_KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getAdditionalWildcards() <em>Additional Wildcards</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionalWildcards()
	 * @generated
	 * @ordered
	 */
	protected static final String ADDITIONAL_WILDCARDS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAdditionalWildcards() <em>Additional Wildcards</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionalWildcards()
	 * @generated
	 * @ordered
	 */
	protected String additionalWildcards = ADDITIONAL_WILDCARDS_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinMergeThreshold() <em>Min Merge Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinMergeThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final double MIN_MERGE_THRESHOLD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMinMergeThreshold() <em>Min Merge Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinMergeThreshold()
	 * @generated
	 * @ordered
	 */
	protected double minMergeThreshold = MIN_MERGE_THRESHOLD_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxComposeThreshold() <em>Max Compose Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxComposeThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final double MAX_COMPOSE_THRESHOLD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMaxComposeThreshold() <em>Max Compose Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxComposeThreshold()
	 * @generated
	 * @ordered
	 */
	protected double maxComposeThreshold = MAX_COMPOSE_THRESHOLD_EDEFAULT;

	/**
	 * The default value of the '{@link #getComposeThresholdDecrement() <em>Compose Threshold Decrement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComposeThresholdDecrement()
	 * @generated
	 * @ordered
	 */
	protected static final double COMPOSE_THRESHOLD_DECREMENT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getComposeThresholdDecrement() <em>Compose Threshold Decrement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComposeThresholdDecrement()
	 * @generated
	 * @ordered
	 */
	protected double composeThresholdDecrement = COMPOSE_THRESHOLD_DECREMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMergeThresholdDecrement() <em>Merge Threshold Decrement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMergeThresholdDecrement()
	 * @generated
	 * @ordered
	 */
	protected static final double MERGE_THRESHOLD_DECREMENT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMergeThresholdDecrement() <em>Merge Threshold Decrement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMergeThresholdDecrement()
	 * @generated
	 * @ordered
	 */
	protected double mergeThresholdDecrement = MERGE_THRESHOLD_DECREMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getExcludedPrefixesForNameResemblance() <em>Excluded Prefixes For Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExcludedPrefixesForNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected static final String EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExcludedPrefixesForNameResemblance() <em>Excluded Prefixes For Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExcludedPrefixesForNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected String excludedPrefixesForNameResemblance = EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getExcludedSuffixesForNameResemblance() <em>Excluded Suffixes For Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExcludedSuffixesForNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected static final String EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExcludedSuffixesForNameResemblance() <em>Excluded Suffixes For Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExcludedSuffixesForNameResemblance()
	 * @generated
	 * @ordered
	 */
	protected String excludedSuffixesForNameResemblance = EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MetricValuesModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetricvaluesPackage.Literals.METRIC_VALUES_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Iteration> getIterations() {
		if (iterations == null) {
			iterations = new EObjectContainmentEList<Iteration>(Iteration.class, this, MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS);
		}
		return iterations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMinCompThreshold() {
		return minCompThreshold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinCompThreshold(double newMinCompThreshold) {
		double oldMinCompThreshold = minCompThreshold;
		minCompThreshold = newMinCompThreshold;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD, oldMinCompThreshold, minCompThreshold));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMaxMergeThreshold() {
		return maxMergeThreshold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxMergeThreshold(double newMaxMergeThreshold) {
		double oldMaxMergeThreshold = maxMergeThreshold;
		maxMergeThreshold = newMaxMergeThreshold;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD, oldMaxMergeThreshold, maxMergeThreshold));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightLowCoupling() {
		return weightLowCoupling;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightLowCoupling(double newWeightLowCoupling) {
		double oldWeightLowCoupling = weightLowCoupling;
		weightLowCoupling = newWeightLowCoupling;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING, oldWeightLowCoupling, weightLowCoupling));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightHighCoupling() {
		return weightHighCoupling;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightHighCoupling(double newWeightHighCoupling) {
		double oldWeightHighCoupling = weightHighCoupling;
		weightHighCoupling = newWeightHighCoupling;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING, oldWeightHighCoupling, weightHighCoupling));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightLowNameResemblance() {
		return weightLowNameResemblance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightLowNameResemblance(double newWeightLowNameResemblance) {
		double oldWeightLowNameResemblance = weightLowNameResemblance;
		weightLowNameResemblance = newWeightLowNameResemblance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE, oldWeightLowNameResemblance, weightLowNameResemblance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightMidNameResemblance() {
		return weightMidNameResemblance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightMidNameResemblance(double newWeightMidNameResemblance) {
		double oldWeightMidNameResemblance = weightMidNameResemblance;
		weightMidNameResemblance = newWeightMidNameResemblance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE, oldWeightMidNameResemblance, weightMidNameResemblance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightHighNameResemblance() {
		return weightHighNameResemblance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightHighNameResemblance(double newWeightHighNameResemblance) {
		double oldWeightHighNameResemblance = weightHighNameResemblance;
		weightHighNameResemblance = newWeightHighNameResemblance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE, oldWeightHighNameResemblance, weightHighNameResemblance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightInterfaceViolationRelevant() {
		return weightInterfaceViolationRelevant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightInterfaceViolationRelevant(double newWeightInterfaceViolationRelevant) {
		double oldWeightInterfaceViolationRelevant = weightInterfaceViolationRelevant;
		weightInterfaceViolationRelevant = newWeightInterfaceViolationRelevant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT, oldWeightInterfaceViolationRelevant, weightInterfaceViolationRelevant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightInterfaceViolationIrrelevant() {
		return weightInterfaceViolationIrrelevant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightInterfaceViolationIrrelevant(double newWeightInterfaceViolationIrrelevant) {
		double oldWeightInterfaceViolationIrrelevant = weightInterfaceViolationIrrelevant;
		weightInterfaceViolationIrrelevant = newWeightInterfaceViolationIrrelevant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT, oldWeightInterfaceViolationIrrelevant, weightInterfaceViolationIrrelevant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightHighSLAQ() {
		return weightHighSLAQ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightHighSLAQ(double newWeightHighSLAQ) {
		double oldWeightHighSLAQ = weightHighSLAQ;
		weightHighSLAQ = newWeightHighSLAQ;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ, oldWeightHighSLAQ, weightHighSLAQ));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightLowSLAQ() {
		return weightLowSLAQ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightLowSLAQ(double newWeightLowSLAQ) {
		double oldWeightLowSLAQ = weightLowSLAQ;
		weightLowSLAQ = newWeightLowSLAQ;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ, oldWeightLowSLAQ, weightLowSLAQ));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightPackageMapping() {
		return weightPackageMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightPackageMapping(double newWeightPackageMapping) {
		double oldWeightPackageMapping = weightPackageMapping;
		weightPackageMapping = newWeightPackageMapping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING, oldWeightPackageMapping, weightPackageMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightDirectoryMapping() {
		return weightDirectoryMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightDirectoryMapping(double newWeightDirectoryMapping) {
		double oldWeightDirectoryMapping = weightDirectoryMapping;
		weightDirectoryMapping = newWeightDirectoryMapping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING, oldWeightDirectoryMapping, weightDirectoryMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightDMS() {
		return weightDMS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightDMS(double newWeightDMS) {
		double oldWeightDMS = weightDMS;
		weightDMS = newWeightDMS;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DMS, oldWeightDMS, weightDMS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWeightHighestNameResemblance() {
		return weightHighestNameResemblance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeightHighestNameResemblance(double newWeightHighestNameResemblance) {
		double oldWeightHighestNameResemblance = weightHighestNameResemblance;
		weightHighestNameResemblance = newWeightHighestNameResemblance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE, oldWeightHighestNameResemblance, weightHighestNameResemblance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWildcardKey() {
		return wildcardKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWildcardKey(String newWildcardKey) {
		String oldWildcardKey = wildcardKey;
		wildcardKey = newWildcardKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__WILDCARD_KEY, oldWildcardKey, wildcardKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAdditionalWildcards() {
		return additionalWildcards;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdditionalWildcards(String newAdditionalWildcards) {
		String oldAdditionalWildcards = additionalWildcards;
		additionalWildcards = newAdditionalWildcards;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS, oldAdditionalWildcards, additionalWildcards));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMinMergeThreshold() {
		return minMergeThreshold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinMergeThreshold(double newMinMergeThreshold) {
		double oldMinMergeThreshold = minMergeThreshold;
		minMergeThreshold = newMinMergeThreshold;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD, oldMinMergeThreshold, minMergeThreshold));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMaxComposeThreshold() {
		return maxComposeThreshold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxComposeThreshold(double newMaxComposeThreshold) {
		double oldMaxComposeThreshold = maxComposeThreshold;
		maxComposeThreshold = newMaxComposeThreshold;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD, oldMaxComposeThreshold, maxComposeThreshold));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getComposeThresholdDecrement() {
		return composeThresholdDecrement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComposeThresholdDecrement(double newComposeThresholdDecrement) {
		double oldComposeThresholdDecrement = composeThresholdDecrement;
		composeThresholdDecrement = newComposeThresholdDecrement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT, oldComposeThresholdDecrement, composeThresholdDecrement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMergeThresholdDecrement() {
		return mergeThresholdDecrement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMergeThresholdDecrement(double newMergeThresholdDecrement) {
		double oldMergeThresholdDecrement = mergeThresholdDecrement;
		mergeThresholdDecrement = newMergeThresholdDecrement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT, oldMergeThresholdDecrement, mergeThresholdDecrement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExcludedPrefixesForNameResemblance() {
		return excludedPrefixesForNameResemblance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExcludedPrefixesForNameResemblance(String newExcludedPrefixesForNameResemblance) {
		String oldExcludedPrefixesForNameResemblance = excludedPrefixesForNameResemblance;
		excludedPrefixesForNameResemblance = newExcludedPrefixesForNameResemblance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE, oldExcludedPrefixesForNameResemblance, excludedPrefixesForNameResemblance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExcludedSuffixesForNameResemblance() {
		return excludedSuffixesForNameResemblance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExcludedSuffixesForNameResemblance(String newExcludedSuffixesForNameResemblance) {
		String oldExcludedSuffixesForNameResemblance = excludedSuffixesForNameResemblance;
		excludedSuffixesForNameResemblance = newExcludedSuffixesForNameResemblance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE, oldExcludedSuffixesForNameResemblance, excludedSuffixesForNameResemblance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS:
				return ((InternalEList<?>)getIterations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS:
				return getIterations();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD:
				return getMinCompThreshold();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD:
				return getMaxMergeThreshold();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING:
				return getWeightLowCoupling();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING:
				return getWeightHighCoupling();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE:
				return getWeightLowNameResemblance();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE:
				return getWeightMidNameResemblance();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE:
				return getWeightHighNameResemblance();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT:
				return getWeightInterfaceViolationRelevant();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT:
				return getWeightInterfaceViolationIrrelevant();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ:
				return getWeightHighSLAQ();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ:
				return getWeightLowSLAQ();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING:
				return getWeightPackageMapping();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING:
				return getWeightDirectoryMapping();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DMS:
				return getWeightDMS();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE:
				return getWeightHighestNameResemblance();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WILDCARD_KEY:
				return getWildcardKey();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS:
				return getAdditionalWildcards();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD:
				return getMinMergeThreshold();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD:
				return getMaxComposeThreshold();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT:
				return getComposeThresholdDecrement();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT:
				return getMergeThresholdDecrement();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE:
				return getExcludedPrefixesForNameResemblance();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE:
				return getExcludedSuffixesForNameResemblance();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS:
				getIterations().clear();
				getIterations().addAll((Collection<? extends Iteration>)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD:
				setMinCompThreshold((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD:
				setMaxMergeThreshold((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING:
				setWeightLowCoupling((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING:
				setWeightHighCoupling((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE:
				setWeightLowNameResemblance((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE:
				setWeightMidNameResemblance((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE:
				setWeightHighNameResemblance((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT:
				setWeightInterfaceViolationRelevant((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT:
				setWeightInterfaceViolationIrrelevant((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ:
				setWeightHighSLAQ((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ:
				setWeightLowSLAQ((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING:
				setWeightPackageMapping((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING:
				setWeightDirectoryMapping((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DMS:
				setWeightDMS((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE:
				setWeightHighestNameResemblance((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WILDCARD_KEY:
				setWildcardKey((String)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS:
				setAdditionalWildcards((String)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD:
				setMinMergeThreshold((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD:
				setMaxComposeThreshold((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT:
				setComposeThresholdDecrement((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT:
				setMergeThresholdDecrement((Double)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE:
				setExcludedPrefixesForNameResemblance((String)newValue);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE:
				setExcludedSuffixesForNameResemblance((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS:
				getIterations().clear();
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD:
				setMinCompThreshold(MIN_COMP_THRESHOLD_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD:
				setMaxMergeThreshold(MAX_MERGE_THRESHOLD_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING:
				setWeightLowCoupling(WEIGHT_LOW_COUPLING_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING:
				setWeightHighCoupling(WEIGHT_HIGH_COUPLING_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE:
				setWeightLowNameResemblance(WEIGHT_LOW_NAME_RESEMBLANCE_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE:
				setWeightMidNameResemblance(WEIGHT_MID_NAME_RESEMBLANCE_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE:
				setWeightHighNameResemblance(WEIGHT_HIGH_NAME_RESEMBLANCE_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT:
				setWeightInterfaceViolationRelevant(WEIGHT_INTERFACE_VIOLATION_RELEVANT_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT:
				setWeightInterfaceViolationIrrelevant(WEIGHT_INTERFACE_VIOLATION_IRRELEVANT_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ:
				setWeightHighSLAQ(WEIGHT_HIGH_SLAQ_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ:
				setWeightLowSLAQ(WEIGHT_LOW_SLAQ_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING:
				setWeightPackageMapping(WEIGHT_PACKAGE_MAPPING_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING:
				setWeightDirectoryMapping(WEIGHT_DIRECTORY_MAPPING_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DMS:
				setWeightDMS(WEIGHT_DMS_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE:
				setWeightHighestNameResemblance(WEIGHT_HIGHEST_NAME_RESEMBLANCE_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WILDCARD_KEY:
				setWildcardKey(WILDCARD_KEY_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS:
				setAdditionalWildcards(ADDITIONAL_WILDCARDS_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD:
				setMinMergeThreshold(MIN_MERGE_THRESHOLD_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD:
				setMaxComposeThreshold(MAX_COMPOSE_THRESHOLD_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT:
				setComposeThresholdDecrement(COMPOSE_THRESHOLD_DECREMENT_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT:
				setMergeThresholdDecrement(MERGE_THRESHOLD_DECREMENT_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE:
				setExcludedPrefixesForNameResemblance(EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT);
				return;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE:
				setExcludedSuffixesForNameResemblance(EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS:
				return iterations != null && !iterations.isEmpty();
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD:
				return minCompThreshold != MIN_COMP_THRESHOLD_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD:
				return maxMergeThreshold != MAX_MERGE_THRESHOLD_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING:
				return weightLowCoupling != WEIGHT_LOW_COUPLING_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING:
				return weightHighCoupling != WEIGHT_HIGH_COUPLING_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE:
				return weightLowNameResemblance != WEIGHT_LOW_NAME_RESEMBLANCE_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE:
				return weightMidNameResemblance != WEIGHT_MID_NAME_RESEMBLANCE_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE:
				return weightHighNameResemblance != WEIGHT_HIGH_NAME_RESEMBLANCE_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT:
				return weightInterfaceViolationRelevant != WEIGHT_INTERFACE_VIOLATION_RELEVANT_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT:
				return weightInterfaceViolationIrrelevant != WEIGHT_INTERFACE_VIOLATION_IRRELEVANT_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ:
				return weightHighSLAQ != WEIGHT_HIGH_SLAQ_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ:
				return weightLowSLAQ != WEIGHT_LOW_SLAQ_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING:
				return weightPackageMapping != WEIGHT_PACKAGE_MAPPING_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING:
				return weightDirectoryMapping != WEIGHT_DIRECTORY_MAPPING_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DMS:
				return weightDMS != WEIGHT_DMS_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE:
				return weightHighestNameResemblance != WEIGHT_HIGHEST_NAME_RESEMBLANCE_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__WILDCARD_KEY:
				return WILDCARD_KEY_EDEFAULT == null ? wildcardKey != null : !WILDCARD_KEY_EDEFAULT.equals(wildcardKey);
			case MetricvaluesPackage.METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS:
				return ADDITIONAL_WILDCARDS_EDEFAULT == null ? additionalWildcards != null : !ADDITIONAL_WILDCARDS_EDEFAULT.equals(additionalWildcards);
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD:
				return minMergeThreshold != MIN_MERGE_THRESHOLD_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD:
				return maxComposeThreshold != MAX_COMPOSE_THRESHOLD_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT:
				return composeThresholdDecrement != COMPOSE_THRESHOLD_DECREMENT_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT:
				return mergeThresholdDecrement != MERGE_THRESHOLD_DECREMENT_EDEFAULT;
			case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE:
				return EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT == null ? excludedPrefixesForNameResemblance != null : !EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT.equals(excludedPrefixesForNameResemblance);
			case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE:
				return EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT == null ? excludedSuffixesForNameResemblance != null : !EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT.equals(excludedSuffixesForNameResemblance);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (minCompThreshold: ");
		result.append(minCompThreshold);
		result.append(", maxMergeThreshold: ");
		result.append(maxMergeThreshold);
		result.append(", weightLowCoupling: ");
		result.append(weightLowCoupling);
		result.append(", weightHighCoupling: ");
		result.append(weightHighCoupling);
		result.append(", weightLowNameResemblance: ");
		result.append(weightLowNameResemblance);
		result.append(", weightMidNameResemblance: ");
		result.append(weightMidNameResemblance);
		result.append(", weightHighNameResemblance: ");
		result.append(weightHighNameResemblance);
		result.append(", weightInterfaceViolationRelevant: ");
		result.append(weightInterfaceViolationRelevant);
		result.append(", weightInterfaceViolationIrrelevant: ");
		result.append(weightInterfaceViolationIrrelevant);
		result.append(", weightHighSLAQ: ");
		result.append(weightHighSLAQ);
		result.append(", weightLowSLAQ: ");
		result.append(weightLowSLAQ);
		result.append(", weightPackageMapping: ");
		result.append(weightPackageMapping);
		result.append(", weightDirectoryMapping: ");
		result.append(weightDirectoryMapping);
		result.append(", weightDMS: ");
		result.append(weightDMS);
		result.append(", weightHighestNameResemblance: ");
		result.append(weightHighestNameResemblance);
		result.append(", wildcardKey: ");
		result.append(wildcardKey);
		result.append(", additionalWildcards: ");
		result.append(additionalWildcards);
		result.append(", minMergeThreshold: ");
		result.append(minMergeThreshold);
		result.append(", maxComposeThreshold: ");
		result.append(maxComposeThreshold);
		result.append(", composeThresholdDecrement: ");
		result.append(composeThresholdDecrement);
		result.append(", mergeThresholdDecrement: ");
		result.append(mergeThresholdDecrement);
		result.append(", excludedPrefixesForNameResemblance: ");
		result.append(excludedPrefixesForNameResemblance);
		result.append(", excludedSuffixesForNameResemblance: ");
		result.append(excludedSuffixesForNameResemblance);
		result.append(')');
		return result.toString();
	}

} //MetricValuesModelImpl
