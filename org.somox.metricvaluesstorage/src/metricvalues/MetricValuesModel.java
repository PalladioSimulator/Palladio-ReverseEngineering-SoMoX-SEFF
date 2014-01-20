/**
 */
package metricvalues;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Metric Values Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link metricvalues.MetricValuesModel#getIterations <em>Iterations</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getMinCompThreshold <em>Min Comp Threshold</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getMaxMergeThreshold <em>Max Merge Threshold</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightLowCoupling <em>Weight Low Coupling</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightHighCoupling <em>Weight High Coupling</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightLowNameResemblance <em>Weight Low Name Resemblance</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightMidNameResemblance <em>Weight Mid Name Resemblance</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightHighNameResemblance <em>Weight High Name Resemblance</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightInterfaceViolationRelevant <em>Weight Interface Violation Relevant</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightInterfaceViolationIrrelevant <em>Weight Interface Violation Irrelevant</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightHighSLAQ <em>Weight High SLAQ</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightLowSLAQ <em>Weight Low SLAQ</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightPackageMapping <em>Weight Package Mapping</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightDirectoryMapping <em>Weight Directory Mapping</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightDMS <em>Weight DMS</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWeightHighestNameResemblance <em>Weight Highest Name Resemblance</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getWildcardKey <em>Wildcard Key</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getAdditionalWildcards <em>Additional Wildcards</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getMinMergeThreshold <em>Min Merge Threshold</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getMaxComposeThreshold <em>Max Compose Threshold</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getComposeThresholdDecrement <em>Compose Threshold Decrement</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getMergeThresholdDecrement <em>Merge Threshold Decrement</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getExcludedPrefixesForNameResemblance <em>Excluded Prefixes For Name Resemblance</em>}</li>
 *   <li>{@link metricvalues.MetricValuesModel#getExcludedSuffixesForNameResemblance <em>Excluded Suffixes For Name Resemblance</em>}</li>
 * </ul>
 * </p>
 *
 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel()
 * @model
 * @generated
 */
public interface MetricValuesModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Iterations</b></em>' containment reference list.
	 * The list contents are of type {@link metricvalues.Iteration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iterations</em>' containment reference list.
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_Iterations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Iteration> getIterations();

	/**
	 * Returns the value of the '<em><b>Min Comp Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Comp Threshold</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Comp Threshold</em>' attribute.
	 * @see #setMinCompThreshold(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_MinCompThreshold()
	 * @model
	 * @generated
	 */
	double getMinCompThreshold();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getMinCompThreshold <em>Min Comp Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Comp Threshold</em>' attribute.
	 * @see #getMinCompThreshold()
	 * @generated
	 */
	void setMinCompThreshold(double value);

	/**
	 * Returns the value of the '<em><b>Max Merge Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Merge Threshold</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Merge Threshold</em>' attribute.
	 * @see #setMaxMergeThreshold(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_MaxMergeThreshold()
	 * @model
	 * @generated
	 */
	double getMaxMergeThreshold();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getMaxMergeThreshold <em>Max Merge Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Merge Threshold</em>' attribute.
	 * @see #getMaxMergeThreshold()
	 * @generated
	 */
	void setMaxMergeThreshold(double value);

	/**
	 * Returns the value of the '<em><b>Weight Low Coupling</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight Low Coupling</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight Low Coupling</em>' attribute.
	 * @see #setWeightLowCoupling(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightLowCoupling()
	 * @model
	 * @generated
	 */
	double getWeightLowCoupling();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightLowCoupling <em>Weight Low Coupling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight Low Coupling</em>' attribute.
	 * @see #getWeightLowCoupling()
	 * @generated
	 */
	void setWeightLowCoupling(double value);

	/**
	 * Returns the value of the '<em><b>Weight High Coupling</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight High Coupling</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight High Coupling</em>' attribute.
	 * @see #setWeightHighCoupling(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightHighCoupling()
	 * @model
	 * @generated
	 */
	double getWeightHighCoupling();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightHighCoupling <em>Weight High Coupling</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight High Coupling</em>' attribute.
	 * @see #getWeightHighCoupling()
	 * @generated
	 */
	void setWeightHighCoupling(double value);

	/**
	 * Returns the value of the '<em><b>Weight Low Name Resemblance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight Low Name Resemblance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight Low Name Resemblance</em>' attribute.
	 * @see #setWeightLowNameResemblance(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightLowNameResemblance()
	 * @model
	 * @generated
	 */
	double getWeightLowNameResemblance();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightLowNameResemblance <em>Weight Low Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight Low Name Resemblance</em>' attribute.
	 * @see #getWeightLowNameResemblance()
	 * @generated
	 */
	void setWeightLowNameResemblance(double value);

	/**
	 * Returns the value of the '<em><b>Weight Mid Name Resemblance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight Mid Name Resemblance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight Mid Name Resemblance</em>' attribute.
	 * @see #setWeightMidNameResemblance(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightMidNameResemblance()
	 * @model
	 * @generated
	 */
	double getWeightMidNameResemblance();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightMidNameResemblance <em>Weight Mid Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight Mid Name Resemblance</em>' attribute.
	 * @see #getWeightMidNameResemblance()
	 * @generated
	 */
	void setWeightMidNameResemblance(double value);

	/**
	 * Returns the value of the '<em><b>Weight High Name Resemblance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight High Name Resemblance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight High Name Resemblance</em>' attribute.
	 * @see #setWeightHighNameResemblance(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightHighNameResemblance()
	 * @model
	 * @generated
	 */
	double getWeightHighNameResemblance();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightHighNameResemblance <em>Weight High Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight High Name Resemblance</em>' attribute.
	 * @see #getWeightHighNameResemblance()
	 * @generated
	 */
	void setWeightHighNameResemblance(double value);

	/**
	 * Returns the value of the '<em><b>Weight Interface Violation Relevant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight Interface Violation Relevant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight Interface Violation Relevant</em>' attribute.
	 * @see #setWeightInterfaceViolationRelevant(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightInterfaceViolationRelevant()
	 * @model
	 * @generated
	 */
	double getWeightInterfaceViolationRelevant();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightInterfaceViolationRelevant <em>Weight Interface Violation Relevant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight Interface Violation Relevant</em>' attribute.
	 * @see #getWeightInterfaceViolationRelevant()
	 * @generated
	 */
	void setWeightInterfaceViolationRelevant(double value);

	/**
	 * Returns the value of the '<em><b>Weight Interface Violation Irrelevant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight Interface Violation Irrelevant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight Interface Violation Irrelevant</em>' attribute.
	 * @see #setWeightInterfaceViolationIrrelevant(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightInterfaceViolationIrrelevant()
	 * @model
	 * @generated
	 */
	double getWeightInterfaceViolationIrrelevant();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightInterfaceViolationIrrelevant <em>Weight Interface Violation Irrelevant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight Interface Violation Irrelevant</em>' attribute.
	 * @see #getWeightInterfaceViolationIrrelevant()
	 * @generated
	 */
	void setWeightInterfaceViolationIrrelevant(double value);

	/**
	 * Returns the value of the '<em><b>Weight High SLAQ</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight High SLAQ</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight High SLAQ</em>' attribute.
	 * @see #setWeightHighSLAQ(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightHighSLAQ()
	 * @model
	 * @generated
	 */
	double getWeightHighSLAQ();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightHighSLAQ <em>Weight High SLAQ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight High SLAQ</em>' attribute.
	 * @see #getWeightHighSLAQ()
	 * @generated
	 */
	void setWeightHighSLAQ(double value);

	/**
	 * Returns the value of the '<em><b>Weight Low SLAQ</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight Low SLAQ</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight Low SLAQ</em>' attribute.
	 * @see #setWeightLowSLAQ(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightLowSLAQ()
	 * @model
	 * @generated
	 */
	double getWeightLowSLAQ();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightLowSLAQ <em>Weight Low SLAQ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight Low SLAQ</em>' attribute.
	 * @see #getWeightLowSLAQ()
	 * @generated
	 */
	void setWeightLowSLAQ(double value);

	/**
	 * Returns the value of the '<em><b>Weight Package Mapping</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight Package Mapping</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight Package Mapping</em>' attribute.
	 * @see #setWeightPackageMapping(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightPackageMapping()
	 * @model
	 * @generated
	 */
	double getWeightPackageMapping();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightPackageMapping <em>Weight Package Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight Package Mapping</em>' attribute.
	 * @see #getWeightPackageMapping()
	 * @generated
	 */
	void setWeightPackageMapping(double value);

	/**
	 * Returns the value of the '<em><b>Weight Directory Mapping</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight Directory Mapping</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight Directory Mapping</em>' attribute.
	 * @see #setWeightDirectoryMapping(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightDirectoryMapping()
	 * @model
	 * @generated
	 */
	double getWeightDirectoryMapping();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightDirectoryMapping <em>Weight Directory Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight Directory Mapping</em>' attribute.
	 * @see #getWeightDirectoryMapping()
	 * @generated
	 */
	void setWeightDirectoryMapping(double value);

	/**
	 * Returns the value of the '<em><b>Weight DMS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight DMS</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight DMS</em>' attribute.
	 * @see #setWeightDMS(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightDMS()
	 * @model
	 * @generated
	 */
	double getWeightDMS();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightDMS <em>Weight DMS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight DMS</em>' attribute.
	 * @see #getWeightDMS()
	 * @generated
	 */
	void setWeightDMS(double value);

	/**
	 * Returns the value of the '<em><b>Weight Highest Name Resemblance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight Highest Name Resemblance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight Highest Name Resemblance</em>' attribute.
	 * @see #setWeightHighestNameResemblance(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WeightHighestNameResemblance()
	 * @model
	 * @generated
	 */
	double getWeightHighestNameResemblance();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWeightHighestNameResemblance <em>Weight Highest Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight Highest Name Resemblance</em>' attribute.
	 * @see #getWeightHighestNameResemblance()
	 * @generated
	 */
	void setWeightHighestNameResemblance(double value);

	/**
	 * Returns the value of the '<em><b>Wildcard Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wildcard Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wildcard Key</em>' attribute.
	 * @see #setWildcardKey(String)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_WildcardKey()
	 * @model
	 * @generated
	 */
	String getWildcardKey();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getWildcardKey <em>Wildcard Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wildcard Key</em>' attribute.
	 * @see #getWildcardKey()
	 * @generated
	 */
	void setWildcardKey(String value);

	/**
	 * Returns the value of the '<em><b>Additional Wildcards</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Additional Wildcards</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Additional Wildcards</em>' attribute.
	 * @see #setAdditionalWildcards(String)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_AdditionalWildcards()
	 * @model
	 * @generated
	 */
	String getAdditionalWildcards();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getAdditionalWildcards <em>Additional Wildcards</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Additional Wildcards</em>' attribute.
	 * @see #getAdditionalWildcards()
	 * @generated
	 */
	void setAdditionalWildcards(String value);

	/**
	 * Returns the value of the '<em><b>Min Merge Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Merge Threshold</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Merge Threshold</em>' attribute.
	 * @see #setMinMergeThreshold(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_MinMergeThreshold()
	 * @model
	 * @generated
	 */
	double getMinMergeThreshold();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getMinMergeThreshold <em>Min Merge Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Merge Threshold</em>' attribute.
	 * @see #getMinMergeThreshold()
	 * @generated
	 */
	void setMinMergeThreshold(double value);

	/**
	 * Returns the value of the '<em><b>Max Compose Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Compose Threshold</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Compose Threshold</em>' attribute.
	 * @see #setMaxComposeThreshold(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_MaxComposeThreshold()
	 * @model
	 * @generated
	 */
	double getMaxComposeThreshold();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getMaxComposeThreshold <em>Max Compose Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Compose Threshold</em>' attribute.
	 * @see #getMaxComposeThreshold()
	 * @generated
	 */
	void setMaxComposeThreshold(double value);

	/**
	 * Returns the value of the '<em><b>Compose Threshold Decrement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compose Threshold Decrement</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compose Threshold Decrement</em>' attribute.
	 * @see #setComposeThresholdDecrement(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_ComposeThresholdDecrement()
	 * @model
	 * @generated
	 */
	double getComposeThresholdDecrement();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getComposeThresholdDecrement <em>Compose Threshold Decrement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compose Threshold Decrement</em>' attribute.
	 * @see #getComposeThresholdDecrement()
	 * @generated
	 */
	void setComposeThresholdDecrement(double value);

	/**
	 * Returns the value of the '<em><b>Merge Threshold Decrement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Merge Threshold Decrement</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Merge Threshold Decrement</em>' attribute.
	 * @see #setMergeThresholdDecrement(double)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_MergeThresholdDecrement()
	 * @model
	 * @generated
	 */
	double getMergeThresholdDecrement();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getMergeThresholdDecrement <em>Merge Threshold Decrement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merge Threshold Decrement</em>' attribute.
	 * @see #getMergeThresholdDecrement()
	 * @generated
	 */
	void setMergeThresholdDecrement(double value);

	/**
	 * Returns the value of the '<em><b>Excluded Prefixes For Name Resemblance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Excluded Prefixes For Name Resemblance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Excluded Prefixes For Name Resemblance</em>' attribute.
	 * @see #setExcludedPrefixesForNameResemblance(String)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_ExcludedPrefixesForNameResemblance()
	 * @model
	 * @generated
	 */
	String getExcludedPrefixesForNameResemblance();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getExcludedPrefixesForNameResemblance <em>Excluded Prefixes For Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Excluded Prefixes For Name Resemblance</em>' attribute.
	 * @see #getExcludedPrefixesForNameResemblance()
	 * @generated
	 */
	void setExcludedPrefixesForNameResemblance(String value);

	/**
	 * Returns the value of the '<em><b>Excluded Suffixes For Name Resemblance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Excluded Suffixes For Name Resemblance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Excluded Suffixes For Name Resemblance</em>' attribute.
	 * @see #setExcludedSuffixesForNameResemblance(String)
	 * @see metricvalues.MetricvaluesPackage#getMetricValuesModel_ExcludedSuffixesForNameResemblance()
	 * @model
	 * @generated
	 */
	String getExcludedSuffixesForNameResemblance();

	/**
	 * Sets the value of the '{@link metricvalues.MetricValuesModel#getExcludedSuffixesForNameResemblance <em>Excluded Suffixes For Name Resemblance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Excluded Suffixes For Name Resemblance</em>' attribute.
	 * @see #getExcludedSuffixesForNameResemblance()
	 * @generated
	 */
	void setExcludedSuffixesForNameResemblance(String value);

} // MetricValuesModel
