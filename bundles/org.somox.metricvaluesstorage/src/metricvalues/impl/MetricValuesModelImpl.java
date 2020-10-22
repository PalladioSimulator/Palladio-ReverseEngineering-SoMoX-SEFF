/**
 */
package metricvalues.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import metricvalues.Iteration;
import metricvalues.MetricValuesModel;
import metricvalues.MetricvaluesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Metric Values Model</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getIterations <em>Iterations</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getMinCompThreshold
 * <em>Min Comp Threshold</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getMaxMergeThreshold
 * <em>Max Merge Threshold</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightLowCoupling
 * <em>Weight Low Coupling</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightHighCoupling
 * <em>Weight High Coupling</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightLowNameResemblance
 * <em>Weight Low Name Resemblance</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightMidNameResemblance
 * <em>Weight Mid Name Resemblance</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightHighNameResemblance
 * <em>Weight High Name Resemblance</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightInterfaceViolationRelevant
 * <em>Weight Interface Violation Relevant</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightInterfaceViolationIrrelevant
 * <em>Weight Interface Violation Irrelevant</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightHighSLAQ <em>Weight High SLAQ</em>}
 * </li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightLowSLAQ <em>Weight Low SLAQ</em>}
 * </li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightPackageMapping
 * <em>Weight Package Mapping</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightDirectoryMapping
 * <em>Weight Directory Mapping</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightDMS <em>Weight DMS</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWeightHighestNameResemblance
 * <em>Weight Highest Name Resemblance</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getWildcardKey <em>Wildcard Key</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getAdditionalWildcards
 * <em>Additional Wildcards</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getMinMergeThreshold
 * <em>Min Merge Threshold</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getMaxComposeThreshold
 * <em>Max Compose Threshold</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getComposeThresholdDecrement
 * <em>Compose Threshold Decrement</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getMergeThresholdDecrement
 * <em>Merge Threshold Decrement</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getExcludedPrefixesForNameResemblance
 * <em>Excluded Prefixes For Name Resemblance</em>}</li>
 * <li>{@link metricvalues.impl.MetricValuesModelImpl#getExcludedSuffixesForNameResemblance
 * <em>Excluded Suffixes For Name Resemblance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetricValuesModelImpl extends MinimalEObjectImpl.Container implements MetricValuesModel {
    /**
     * The cached value of the '{@link #getIterations() <em>Iterations</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIterations()
     * @generated
     * @ordered
     */
    protected EList<Iteration> iterations;

    /**
     * The default value of the '{@link #getMinCompThreshold() <em>Min Comp Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMinCompThreshold()
     * @generated
     * @ordered
     */
    protected static final double MIN_COMP_THRESHOLD_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMinCompThreshold() <em>Min Comp Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMinCompThreshold()
     * @generated
     * @ordered
     */
    protected double minCompThreshold = MIN_COMP_THRESHOLD_EDEFAULT;

    /**
     * The default value of the '{@link #getMaxMergeThreshold() <em>Max Merge Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMaxMergeThreshold()
     * @generated
     * @ordered
     */
    protected static final double MAX_MERGE_THRESHOLD_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMaxMergeThreshold() <em>Max Merge Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMaxMergeThreshold()
     * @generated
     * @ordered
     */
    protected double maxMergeThreshold = MAX_MERGE_THRESHOLD_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightLowCoupling() <em>Weight Low Coupling</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightLowCoupling()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_LOW_COUPLING_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightLowCoupling() <em>Weight Low Coupling</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightLowCoupling()
     * @generated
     * @ordered
     */
    protected double weightLowCoupling = WEIGHT_LOW_COUPLING_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightHighCoupling() <em>Weight High Coupling</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightHighCoupling()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_HIGH_COUPLING_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightHighCoupling() <em>Weight High Coupling</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightHighCoupling()
     * @generated
     * @ordered
     */
    protected double weightHighCoupling = WEIGHT_HIGH_COUPLING_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightLowNameResemblance()
     * <em>Weight Low Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getWeightLowNameResemblance()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_LOW_NAME_RESEMBLANCE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightLowNameResemblance()
     * <em>Weight Low Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getWeightLowNameResemblance()
     * @generated
     * @ordered
     */
    protected double weightLowNameResemblance = WEIGHT_LOW_NAME_RESEMBLANCE_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightMidNameResemblance()
     * <em>Weight Mid Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getWeightMidNameResemblance()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_MID_NAME_RESEMBLANCE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightMidNameResemblance()
     * <em>Weight Mid Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getWeightMidNameResemblance()
     * @generated
     * @ordered
     */
    protected double weightMidNameResemblance = WEIGHT_MID_NAME_RESEMBLANCE_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightHighNameResemblance()
     * <em>Weight High Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getWeightHighNameResemblance()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_HIGH_NAME_RESEMBLANCE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightHighNameResemblance()
     * <em>Weight High Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getWeightHighNameResemblance()
     * @generated
     * @ordered
     */
    protected double weightHighNameResemblance = WEIGHT_HIGH_NAME_RESEMBLANCE_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightInterfaceViolationRelevant()
     * <em>Weight Interface Violation Relevant</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getWeightInterfaceViolationRelevant()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_INTERFACE_VIOLATION_RELEVANT_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightInterfaceViolationRelevant()
     * <em>Weight Interface Violation Relevant</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getWeightInterfaceViolationRelevant()
     * @generated
     * @ordered
     */
    protected double weightInterfaceViolationRelevant = WEIGHT_INTERFACE_VIOLATION_RELEVANT_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightInterfaceViolationIrrelevant()
     * <em>Weight Interface Violation Irrelevant</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getWeightInterfaceViolationIrrelevant()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_INTERFACE_VIOLATION_IRRELEVANT_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightInterfaceViolationIrrelevant()
     * <em>Weight Interface Violation Irrelevant</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getWeightInterfaceViolationIrrelevant()
     * @generated
     * @ordered
     */
    protected double weightInterfaceViolationIrrelevant = WEIGHT_INTERFACE_VIOLATION_IRRELEVANT_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightHighSLAQ() <em>Weight High SLAQ</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightHighSLAQ()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_HIGH_SLAQ_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightHighSLAQ() <em>Weight High SLAQ</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightHighSLAQ()
     * @generated
     * @ordered
     */
    protected double weightHighSLAQ = WEIGHT_HIGH_SLAQ_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightLowSLAQ() <em>Weight Low SLAQ</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightLowSLAQ()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_LOW_SLAQ_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightLowSLAQ() <em>Weight Low SLAQ</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightLowSLAQ()
     * @generated
     * @ordered
     */
    protected double weightLowSLAQ = WEIGHT_LOW_SLAQ_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightPackageMapping() <em>Weight Package Mapping</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightPackageMapping()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_PACKAGE_MAPPING_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightPackageMapping() <em>Weight Package Mapping</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightPackageMapping()
     * @generated
     * @ordered
     */
    protected double weightPackageMapping = WEIGHT_PACKAGE_MAPPING_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightDirectoryMapping()
     * <em>Weight Directory Mapping</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightDirectoryMapping()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_DIRECTORY_MAPPING_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightDirectoryMapping()
     * <em>Weight Directory Mapping</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightDirectoryMapping()
     * @generated
     * @ordered
     */
    protected double weightDirectoryMapping = WEIGHT_DIRECTORY_MAPPING_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightDMS() <em>Weight DMS</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightDMS()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_DMS_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightDMS() <em>Weight DMS</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWeightDMS()
     * @generated
     * @ordered
     */
    protected double weightDMS = WEIGHT_DMS_EDEFAULT;

    /**
     * The default value of the '{@link #getWeightHighestNameResemblance()
     * <em>Weight Highest Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getWeightHighestNameResemblance()
     * @generated
     * @ordered
     */
    protected static final double WEIGHT_HIGHEST_NAME_RESEMBLANCE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWeightHighestNameResemblance()
     * <em>Weight Highest Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getWeightHighestNameResemblance()
     * @generated
     * @ordered
     */
    protected double weightHighestNameResemblance = WEIGHT_HIGHEST_NAME_RESEMBLANCE_EDEFAULT;

    /**
     * The default value of the '{@link #getWildcardKey() <em>Wildcard Key</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWildcardKey()
     * @generated
     * @ordered
     */
    protected static final String WILDCARD_KEY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWildcardKey() <em>Wildcard Key</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWildcardKey()
     * @generated
     * @ordered
     */
    protected String wildcardKey = WILDCARD_KEY_EDEFAULT;

    /**
     * The default value of the '{@link #getAdditionalWildcards() <em>Additional Wildcards</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAdditionalWildcards()
     * @generated
     * @ordered
     */
    protected static final String ADDITIONAL_WILDCARDS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAdditionalWildcards() <em>Additional Wildcards</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAdditionalWildcards()
     * @generated
     * @ordered
     */
    protected String additionalWildcards = ADDITIONAL_WILDCARDS_EDEFAULT;

    /**
     * The default value of the '{@link #getMinMergeThreshold() <em>Min Merge Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMinMergeThreshold()
     * @generated
     * @ordered
     */
    protected static final double MIN_MERGE_THRESHOLD_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMinMergeThreshold() <em>Min Merge Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMinMergeThreshold()
     * @generated
     * @ordered
     */
    protected double minMergeThreshold = MIN_MERGE_THRESHOLD_EDEFAULT;

    /**
     * The default value of the '{@link #getMaxComposeThreshold() <em>Max Compose Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMaxComposeThreshold()
     * @generated
     * @ordered
     */
    protected static final double MAX_COMPOSE_THRESHOLD_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMaxComposeThreshold() <em>Max Compose Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMaxComposeThreshold()
     * @generated
     * @ordered
     */
    protected double maxComposeThreshold = MAX_COMPOSE_THRESHOLD_EDEFAULT;

    /**
     * The default value of the '{@link #getComposeThresholdDecrement()
     * <em>Compose Threshold Decrement</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getComposeThresholdDecrement()
     * @generated
     * @ordered
     */
    protected static final double COMPOSE_THRESHOLD_DECREMENT_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getComposeThresholdDecrement()
     * <em>Compose Threshold Decrement</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getComposeThresholdDecrement()
     * @generated
     * @ordered
     */
    protected double composeThresholdDecrement = COMPOSE_THRESHOLD_DECREMENT_EDEFAULT;

    /**
     * The default value of the '{@link #getMergeThresholdDecrement()
     * <em>Merge Threshold Decrement</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMergeThresholdDecrement()
     * @generated
     * @ordered
     */
    protected static final double MERGE_THRESHOLD_DECREMENT_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMergeThresholdDecrement()
     * <em>Merge Threshold Decrement</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMergeThresholdDecrement()
     * @generated
     * @ordered
     */
    protected double mergeThresholdDecrement = MERGE_THRESHOLD_DECREMENT_EDEFAULT;

    /**
     * The default value of the '{@link #getExcludedPrefixesForNameResemblance()
     * <em>Excluded Prefixes For Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExcludedPrefixesForNameResemblance()
     * @generated
     * @ordered
     */
    protected static final String EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExcludedPrefixesForNameResemblance()
     * <em>Excluded Prefixes For Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExcludedPrefixesForNameResemblance()
     * @generated
     * @ordered
     */
    protected String excludedPrefixesForNameResemblance = EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT;

    /**
     * The default value of the '{@link #getExcludedSuffixesForNameResemblance()
     * <em>Excluded Suffixes For Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExcludedSuffixesForNameResemblance()
     * @generated
     * @ordered
     */
    protected static final String EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExcludedSuffixesForNameResemblance()
     * <em>Excluded Suffixes For Name Resemblance</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExcludedSuffixesForNameResemblance()
     * @generated
     * @ordered
     */
    protected String excludedSuffixesForNameResemblance = EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MetricValuesModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricvaluesPackage.Literals.METRIC_VALUES_MODEL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Iteration> getIterations() {
        if (this.iterations == null) {
            this.iterations = new EObjectContainmentEList<Iteration>(Iteration.class, this,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS);
        }
        return this.iterations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getMinCompThreshold() {
        return this.minCompThreshold;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMinCompThreshold(final double newMinCompThreshold) {
        final double oldMinCompThreshold = this.minCompThreshold;
        this.minCompThreshold = newMinCompThreshold;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD, oldMinCompThreshold,
                    this.minCompThreshold));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getMaxMergeThreshold() {
        return this.maxMergeThreshold;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMaxMergeThreshold(final double newMaxMergeThreshold) {
        final double oldMaxMergeThreshold = this.maxMergeThreshold;
        this.maxMergeThreshold = newMaxMergeThreshold;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD, oldMaxMergeThreshold,
                    this.maxMergeThreshold));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightLowCoupling() {
        return this.weightLowCoupling;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightLowCoupling(final double newWeightLowCoupling) {
        final double oldWeightLowCoupling = this.weightLowCoupling;
        this.weightLowCoupling = newWeightLowCoupling;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING, oldWeightLowCoupling,
                    this.weightLowCoupling));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightHighCoupling() {
        return this.weightHighCoupling;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightHighCoupling(final double newWeightHighCoupling) {
        final double oldWeightHighCoupling = this.weightHighCoupling;
        this.weightHighCoupling = newWeightHighCoupling;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING, oldWeightHighCoupling,
                    this.weightHighCoupling));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightLowNameResemblance() {
        return this.weightLowNameResemblance;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightLowNameResemblance(final double newWeightLowNameResemblance) {
        final double oldWeightLowNameResemblance = this.weightLowNameResemblance;
        this.weightLowNameResemblance = newWeightLowNameResemblance;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE, oldWeightLowNameResemblance,
                    this.weightLowNameResemblance));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightMidNameResemblance() {
        return this.weightMidNameResemblance;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightMidNameResemblance(final double newWeightMidNameResemblance) {
        final double oldWeightMidNameResemblance = this.weightMidNameResemblance;
        this.weightMidNameResemblance = newWeightMidNameResemblance;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE, oldWeightMidNameResemblance,
                    this.weightMidNameResemblance));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightHighNameResemblance() {
        return this.weightHighNameResemblance;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightHighNameResemblance(final double newWeightHighNameResemblance) {
        final double oldWeightHighNameResemblance = this.weightHighNameResemblance;
        this.weightHighNameResemblance = newWeightHighNameResemblance;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE, oldWeightHighNameResemblance,
                    this.weightHighNameResemblance));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightInterfaceViolationRelevant() {
        return this.weightInterfaceViolationRelevant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightInterfaceViolationRelevant(final double newWeightInterfaceViolationRelevant) {
        final double oldWeightInterfaceViolationRelevant = this.weightInterfaceViolationRelevant;
        this.weightInterfaceViolationRelevant = newWeightInterfaceViolationRelevant;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT,
                    oldWeightInterfaceViolationRelevant, this.weightInterfaceViolationRelevant));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightInterfaceViolationIrrelevant() {
        return this.weightInterfaceViolationIrrelevant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightInterfaceViolationIrrelevant(final double newWeightInterfaceViolationIrrelevant) {
        final double oldWeightInterfaceViolationIrrelevant = this.weightInterfaceViolationIrrelevant;
        this.weightInterfaceViolationIrrelevant = newWeightInterfaceViolationIrrelevant;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT,
                    oldWeightInterfaceViolationIrrelevant, this.weightInterfaceViolationIrrelevant));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightHighSLAQ() {
        return this.weightHighSLAQ;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightHighSLAQ(final double newWeightHighSLAQ) {
        final double oldWeightHighSLAQ = this.weightHighSLAQ;
        this.weightHighSLAQ = newWeightHighSLAQ;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ, oldWeightHighSLAQ, this.weightHighSLAQ));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightLowSLAQ() {
        return this.weightLowSLAQ;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightLowSLAQ(final double newWeightLowSLAQ) {
        final double oldWeightLowSLAQ = this.weightLowSLAQ;
        this.weightLowSLAQ = newWeightLowSLAQ;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ, oldWeightLowSLAQ, this.weightLowSLAQ));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightPackageMapping() {
        return this.weightPackageMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightPackageMapping(final double newWeightPackageMapping) {
        final double oldWeightPackageMapping = this.weightPackageMapping;
        this.weightPackageMapping = newWeightPackageMapping;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING, oldWeightPackageMapping,
                    this.weightPackageMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightDirectoryMapping() {
        return this.weightDirectoryMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightDirectoryMapping(final double newWeightDirectoryMapping) {
        final double oldWeightDirectoryMapping = this.weightDirectoryMapping;
        this.weightDirectoryMapping = newWeightDirectoryMapping;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING, oldWeightDirectoryMapping,
                    this.weightDirectoryMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightDMS() {
        return this.weightDMS;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightDMS(final double newWeightDMS) {
        final double oldWeightDMS = this.weightDMS;
        this.weightDMS = newWeightDMS;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DMS, oldWeightDMS, this.weightDMS));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getWeightHighestNameResemblance() {
        return this.weightHighestNameResemblance;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWeightHighestNameResemblance(final double newWeightHighestNameResemblance) {
        final double oldWeightHighestNameResemblance = this.weightHighestNameResemblance;
        this.weightHighestNameResemblance = newWeightHighestNameResemblance;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE,
                    oldWeightHighestNameResemblance, this.weightHighestNameResemblance));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getWildcardKey() {
        return this.wildcardKey;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWildcardKey(final String newWildcardKey) {
        final String oldWildcardKey = this.wildcardKey;
        this.wildcardKey = newWildcardKey;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__WILDCARD_KEY, oldWildcardKey, this.wildcardKey));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getAdditionalWildcards() {
        return this.additionalWildcards;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAdditionalWildcards(final String newAdditionalWildcards) {
        final String oldAdditionalWildcards = this.additionalWildcards;
        this.additionalWildcards = newAdditionalWildcards;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS, oldAdditionalWildcards,
                    this.additionalWildcards));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getMinMergeThreshold() {
        return this.minMergeThreshold;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMinMergeThreshold(final double newMinMergeThreshold) {
        final double oldMinMergeThreshold = this.minMergeThreshold;
        this.minMergeThreshold = newMinMergeThreshold;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD, oldMinMergeThreshold,
                    this.minMergeThreshold));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getMaxComposeThreshold() {
        return this.maxComposeThreshold;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMaxComposeThreshold(final double newMaxComposeThreshold) {
        final double oldMaxComposeThreshold = this.maxComposeThreshold;
        this.maxComposeThreshold = newMaxComposeThreshold;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD, oldMaxComposeThreshold,
                    this.maxComposeThreshold));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getComposeThresholdDecrement() {
        return this.composeThresholdDecrement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setComposeThresholdDecrement(final double newComposeThresholdDecrement) {
        final double oldComposeThresholdDecrement = this.composeThresholdDecrement;
        this.composeThresholdDecrement = newComposeThresholdDecrement;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT, oldComposeThresholdDecrement,
                    this.composeThresholdDecrement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getMergeThresholdDecrement() {
        return this.mergeThresholdDecrement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMergeThresholdDecrement(final double newMergeThresholdDecrement) {
        final double oldMergeThresholdDecrement = this.mergeThresholdDecrement;
        this.mergeThresholdDecrement = newMergeThresholdDecrement;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT, oldMergeThresholdDecrement,
                    this.mergeThresholdDecrement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getExcludedPrefixesForNameResemblance() {
        return this.excludedPrefixesForNameResemblance;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExcludedPrefixesForNameResemblance(final String newExcludedPrefixesForNameResemblance) {
        final String oldExcludedPrefixesForNameResemblance = this.excludedPrefixesForNameResemblance;
        this.excludedPrefixesForNameResemblance = newExcludedPrefixesForNameResemblance;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE,
                    oldExcludedPrefixesForNameResemblance, this.excludedPrefixesForNameResemblance));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getExcludedSuffixesForNameResemblance() {
        return this.excludedSuffixesForNameResemblance;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExcludedSuffixesForNameResemblance(final String newExcludedSuffixesForNameResemblance) {
        final String oldExcludedSuffixesForNameResemblance = this.excludedSuffixesForNameResemblance;
        this.excludedSuffixesForNameResemblance = newExcludedSuffixesForNameResemblance;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE,
                    oldExcludedSuffixesForNameResemblance, this.excludedSuffixesForNameResemblance));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS:
            return ((InternalEList<?>) this.getIterations()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS:
            return this.getIterations();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD:
            return this.getMinCompThreshold();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD:
            return this.getMaxMergeThreshold();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING:
            return this.getWeightLowCoupling();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING:
            return this.getWeightHighCoupling();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE:
            return this.getWeightLowNameResemblance();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE:
            return this.getWeightMidNameResemblance();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE:
            return this.getWeightHighNameResemblance();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT:
            return this.getWeightInterfaceViolationRelevant();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT:
            return this.getWeightInterfaceViolationIrrelevant();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ:
            return this.getWeightHighSLAQ();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ:
            return this.getWeightLowSLAQ();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING:
            return this.getWeightPackageMapping();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING:
            return this.getWeightDirectoryMapping();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DMS:
            return this.getWeightDMS();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE:
            return this.getWeightHighestNameResemblance();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WILDCARD_KEY:
            return this.getWildcardKey();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS:
            return this.getAdditionalWildcards();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD:
            return this.getMinMergeThreshold();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD:
            return this.getMaxComposeThreshold();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT:
            return this.getComposeThresholdDecrement();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT:
            return this.getMergeThresholdDecrement();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE:
            return this.getExcludedPrefixesForNameResemblance();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE:
            return this.getExcludedSuffixesForNameResemblance();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS:
            this.getIterations().clear();
            this.getIterations().addAll((Collection<? extends Iteration>) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD:
            this.setMinCompThreshold((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD:
            this.setMaxMergeThreshold((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING:
            this.setWeightLowCoupling((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING:
            this.setWeightHighCoupling((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE:
            this.setWeightLowNameResemblance((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE:
            this.setWeightMidNameResemblance((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE:
            this.setWeightHighNameResemblance((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT:
            this.setWeightInterfaceViolationRelevant((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT:
            this.setWeightInterfaceViolationIrrelevant((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ:
            this.setWeightHighSLAQ((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ:
            this.setWeightLowSLAQ((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING:
            this.setWeightPackageMapping((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING:
            this.setWeightDirectoryMapping((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DMS:
            this.setWeightDMS((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE:
            this.setWeightHighestNameResemblance((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WILDCARD_KEY:
            this.setWildcardKey((String) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS:
            this.setAdditionalWildcards((String) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD:
            this.setMinMergeThreshold((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD:
            this.setMaxComposeThreshold((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT:
            this.setComposeThresholdDecrement((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT:
            this.setMergeThresholdDecrement((Double) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE:
            this.setExcludedPrefixesForNameResemblance((String) newValue);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE:
            this.setExcludedSuffixesForNameResemblance((String) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS:
            this.getIterations().clear();
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD:
            this.setMinCompThreshold(MIN_COMP_THRESHOLD_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD:
            this.setMaxMergeThreshold(MAX_MERGE_THRESHOLD_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING:
            this.setWeightLowCoupling(WEIGHT_LOW_COUPLING_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING:
            this.setWeightHighCoupling(WEIGHT_HIGH_COUPLING_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE:
            this.setWeightLowNameResemblance(WEIGHT_LOW_NAME_RESEMBLANCE_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE:
            this.setWeightMidNameResemblance(WEIGHT_MID_NAME_RESEMBLANCE_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE:
            this.setWeightHighNameResemblance(WEIGHT_HIGH_NAME_RESEMBLANCE_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT:
            this.setWeightInterfaceViolationRelevant(WEIGHT_INTERFACE_VIOLATION_RELEVANT_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT:
            this.setWeightInterfaceViolationIrrelevant(WEIGHT_INTERFACE_VIOLATION_IRRELEVANT_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ:
            this.setWeightHighSLAQ(WEIGHT_HIGH_SLAQ_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ:
            this.setWeightLowSLAQ(WEIGHT_LOW_SLAQ_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING:
            this.setWeightPackageMapping(WEIGHT_PACKAGE_MAPPING_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING:
            this.setWeightDirectoryMapping(WEIGHT_DIRECTORY_MAPPING_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DMS:
            this.setWeightDMS(WEIGHT_DMS_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE:
            this.setWeightHighestNameResemblance(WEIGHT_HIGHEST_NAME_RESEMBLANCE_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WILDCARD_KEY:
            this.setWildcardKey(WILDCARD_KEY_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS:
            this.setAdditionalWildcards(ADDITIONAL_WILDCARDS_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD:
            this.setMinMergeThreshold(MIN_MERGE_THRESHOLD_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD:
            this.setMaxComposeThreshold(MAX_COMPOSE_THRESHOLD_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT:
            this.setComposeThresholdDecrement(COMPOSE_THRESHOLD_DECREMENT_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT:
            this.setMergeThresholdDecrement(MERGE_THRESHOLD_DECREMENT_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE:
            this.setExcludedPrefixesForNameResemblance(EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT);
            return;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE:
            this.setExcludedSuffixesForNameResemblance(EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case MetricvaluesPackage.METRIC_VALUES_MODEL__ITERATIONS:
            return this.iterations != null && !this.iterations.isEmpty();
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD:
            return this.minCompThreshold != MIN_COMP_THRESHOLD_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD:
            return this.maxMergeThreshold != MAX_MERGE_THRESHOLD_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING:
            return this.weightLowCoupling != WEIGHT_LOW_COUPLING_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING:
            return this.weightHighCoupling != WEIGHT_HIGH_COUPLING_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE:
            return this.weightLowNameResemblance != WEIGHT_LOW_NAME_RESEMBLANCE_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE:
            return this.weightMidNameResemblance != WEIGHT_MID_NAME_RESEMBLANCE_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE:
            return this.weightHighNameResemblance != WEIGHT_HIGH_NAME_RESEMBLANCE_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT:
            return this.weightInterfaceViolationRelevant != WEIGHT_INTERFACE_VIOLATION_RELEVANT_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT:
            return this.weightInterfaceViolationIrrelevant != WEIGHT_INTERFACE_VIOLATION_IRRELEVANT_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ:
            return this.weightHighSLAQ != WEIGHT_HIGH_SLAQ_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ:
            return this.weightLowSLAQ != WEIGHT_LOW_SLAQ_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING:
            return this.weightPackageMapping != WEIGHT_PACKAGE_MAPPING_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING:
            return this.weightDirectoryMapping != WEIGHT_DIRECTORY_MAPPING_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_DMS:
            return this.weightDMS != WEIGHT_DMS_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE:
            return this.weightHighestNameResemblance != WEIGHT_HIGHEST_NAME_RESEMBLANCE_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__WILDCARD_KEY:
            return WILDCARD_KEY_EDEFAULT == null ? this.wildcardKey != null
                    : !WILDCARD_KEY_EDEFAULT.equals(this.wildcardKey);
        case MetricvaluesPackage.METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS:
            return ADDITIONAL_WILDCARDS_EDEFAULT == null ? this.additionalWildcards != null
                    : !ADDITIONAL_WILDCARDS_EDEFAULT.equals(this.additionalWildcards);
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD:
            return this.minMergeThreshold != MIN_MERGE_THRESHOLD_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD:
            return this.maxComposeThreshold != MAX_COMPOSE_THRESHOLD_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT:
            return this.composeThresholdDecrement != COMPOSE_THRESHOLD_DECREMENT_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT:
            return this.mergeThresholdDecrement != MERGE_THRESHOLD_DECREMENT_EDEFAULT;
        case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE:
            return EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT == null
                    ? this.excludedPrefixesForNameResemblance != null
                    : !EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT.equals(this.excludedPrefixesForNameResemblance);
        case MetricvaluesPackage.METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE:
            return EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT == null
                    ? this.excludedSuffixesForNameResemblance != null
                    : !EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE_EDEFAULT.equals(this.excludedSuffixesForNameResemblance);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }

        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (minCompThreshold: ");
        result.append(this.minCompThreshold);
        result.append(", maxMergeThreshold: ");
        result.append(this.maxMergeThreshold);
        result.append(", weightLowCoupling: ");
        result.append(this.weightLowCoupling);
        result.append(", weightHighCoupling: ");
        result.append(this.weightHighCoupling);
        result.append(", weightLowNameResemblance: ");
        result.append(this.weightLowNameResemblance);
        result.append(", weightMidNameResemblance: ");
        result.append(this.weightMidNameResemblance);
        result.append(", weightHighNameResemblance: ");
        result.append(this.weightHighNameResemblance);
        result.append(", weightInterfaceViolationRelevant: ");
        result.append(this.weightInterfaceViolationRelevant);
        result.append(", weightInterfaceViolationIrrelevant: ");
        result.append(this.weightInterfaceViolationIrrelevant);
        result.append(", weightHighSLAQ: ");
        result.append(this.weightHighSLAQ);
        result.append(", weightLowSLAQ: ");
        result.append(this.weightLowSLAQ);
        result.append(", weightPackageMapping: ");
        result.append(this.weightPackageMapping);
        result.append(", weightDirectoryMapping: ");
        result.append(this.weightDirectoryMapping);
        result.append(", weightDMS: ");
        result.append(this.weightDMS);
        result.append(", weightHighestNameResemblance: ");
        result.append(this.weightHighestNameResemblance);
        result.append(", wildcardKey: ");
        result.append(this.wildcardKey);
        result.append(", additionalWildcards: ");
        result.append(this.additionalWildcards);
        result.append(", minMergeThreshold: ");
        result.append(this.minMergeThreshold);
        result.append(", maxComposeThreshold: ");
        result.append(this.maxComposeThreshold);
        result.append(", composeThresholdDecrement: ");
        result.append(this.composeThresholdDecrement);
        result.append(", mergeThresholdDecrement: ");
        result.append(this.mergeThresholdDecrement);
        result.append(", excludedPrefixesForNameResemblance: ");
        result.append(this.excludedPrefixesForNameResemblance);
        result.append(", excludedSuffixesForNameResemblance: ");
        result.append(this.excludedSuffixesForNameResemblance);
        result.append(')');
        return result.toString();
    }

} // MetricValuesModelImpl
