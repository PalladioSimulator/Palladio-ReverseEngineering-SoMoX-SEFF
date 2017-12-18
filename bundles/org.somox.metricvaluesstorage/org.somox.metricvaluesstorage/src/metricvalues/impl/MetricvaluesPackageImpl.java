/**
 */
package metricvalues.impl;

import metricvalues.Component;
import metricvalues.ComponentCandidate;
import metricvalues.Iteration;
import metricvalues.MetricValue;
import metricvalues.MetricValuesModel;
import metricvalues.MetricvaluesFactory;
import metricvalues.MetricvaluesPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.emftext.language.java.annotations.AnnotationsPackage;

import org.emftext.language.java.arrays.ArraysPackage;

import org.emftext.language.java.classifiers.ClassifiersPackage;

import org.emftext.language.java.commons.CommonsPackage;

import org.emftext.language.java.containers.ContainersPackage;

import org.emftext.language.java.expressions.ExpressionsPackage;

import org.emftext.language.java.generics.GenericsPackage;

import org.emftext.language.java.imports.ImportsPackage;

import org.emftext.language.java.instantiations.InstantiationsPackage;

import org.emftext.language.java.literals.LiteralsPackage;

import org.emftext.language.java.members.MembersPackage;

import org.emftext.language.java.modifiers.ModifiersPackage;

import org.emftext.language.java.operators.OperatorsPackage;

import org.emftext.language.java.parameters.ParametersPackage;

import org.emftext.language.java.references.ReferencesPackage;

import org.emftext.language.java.statements.StatementsPackage;

import org.emftext.language.java.types.TypesPackage;

import org.emftext.language.java.variables.VariablesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MetricvaluesPackageImpl extends EPackageImpl implements MetricvaluesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentCandidateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iterationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metricValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metricValuesModelEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see metricvalues.MetricvaluesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MetricvaluesPackageImpl() {
		super(eNS_URI, MetricvaluesFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MetricvaluesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MetricvaluesPackage init() {
		if (isInited) return (MetricvaluesPackage)EPackage.Registry.INSTANCE.getEPackage(MetricvaluesPackage.eNS_URI);

		// Obtain or create and register package
		MetricvaluesPackageImpl theMetricvaluesPackage = (MetricvaluesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MetricvaluesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MetricvaluesPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		AnnotationsPackage.eINSTANCE.eClass();
		ArraysPackage.eINSTANCE.eClass();
		ClassifiersPackage.eINSTANCE.eClass();
		CommonsPackage.eINSTANCE.eClass();
		ContainersPackage.eINSTANCE.eClass();
		ExpressionsPackage.eINSTANCE.eClass();
		GenericsPackage.eINSTANCE.eClass();
		ImportsPackage.eINSTANCE.eClass();
		InstantiationsPackage.eINSTANCE.eClass();
		LiteralsPackage.eINSTANCE.eClass();
		MembersPackage.eINSTANCE.eClass();
		ModifiersPackage.eINSTANCE.eClass();
		OperatorsPackage.eINSTANCE.eClass();
		ParametersPackage.eINSTANCE.eClass();
		ReferencesPackage.eINSTANCE.eClass();
		StatementsPackage.eINSTANCE.eClass();
		TypesPackage.eINSTANCE.eClass();
		VariablesPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMetricvaluesPackage.createPackageContents();

		// Initialize created meta-data
		theMetricvaluesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMetricvaluesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MetricvaluesPackage.eNS_URI, theMetricvaluesPackage);
		return theMetricvaluesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponent() {
		return componentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_SubComponents() {
		return (EReference)componentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Name() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Id() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_Classes() {
		return (EReference)componentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentCandidate() {
		return componentCandidateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentCandidate_MetricValues() {
		return (EReference)componentCandidateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentCandidate_FirstComponent() {
		return (EReference)componentCandidateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentCandidate_SecondComponent() {
		return (EReference)componentCandidateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIteration() {
		return iterationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIteration_ComponentCandidates() {
		return (EReference)iterationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIteration_Components() {
		return (EReference)iterationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIteration_Number() {
		return (EAttribute)iterationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIteration_CurCompThreshold() {
		return (EAttribute)iterationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIteration_CurMergeThreshold() {
		return (EAttribute)iterationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIteration_IsMergeIteration() {
		return (EAttribute)iterationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetricValue() {
		return metricValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValue_MetricID() {
		return (EAttribute)metricValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValue_Value() {
		return (EAttribute)metricValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetricValuesModel() {
		return metricValuesModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetricValuesModel_Iterations() {
		return (EReference)metricValuesModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_MinCompThreshold() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_MaxMergeThreshold() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightLowCoupling() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightHighCoupling() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightLowNameResemblance() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightMidNameResemblance() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightHighNameResemblance() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightInterfaceViolationRelevant() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightInterfaceViolationIrrelevant() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightHighSLAQ() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightLowSLAQ() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightPackageMapping() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightDirectoryMapping() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightDMS() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WeightHighestNameResemblance() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_WildcardKey() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_AdditionalWildcards() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_MinMergeThreshold() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_MaxComposeThreshold() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_ComposeThresholdDecrement() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_MergeThresholdDecrement() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_ExcludedPrefixesForNameResemblance() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetricValuesModel_ExcludedSuffixesForNameResemblance() {
		return (EAttribute)metricValuesModelEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetricvaluesFactory getMetricvaluesFactory() {
		return (MetricvaluesFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		componentEClass = createEClass(COMPONENT);
		createEReference(componentEClass, COMPONENT__SUB_COMPONENTS);
		createEAttribute(componentEClass, COMPONENT__NAME);
		createEAttribute(componentEClass, COMPONENT__ID);
		createEReference(componentEClass, COMPONENT__CLASSES);

		componentCandidateEClass = createEClass(COMPONENT_CANDIDATE);
		createEReference(componentCandidateEClass, COMPONENT_CANDIDATE__METRIC_VALUES);
		createEReference(componentCandidateEClass, COMPONENT_CANDIDATE__FIRST_COMPONENT);
		createEReference(componentCandidateEClass, COMPONENT_CANDIDATE__SECOND_COMPONENT);

		iterationEClass = createEClass(ITERATION);
		createEReference(iterationEClass, ITERATION__COMPONENT_CANDIDATES);
		createEReference(iterationEClass, ITERATION__COMPONENTS);
		createEAttribute(iterationEClass, ITERATION__NUMBER);
		createEAttribute(iterationEClass, ITERATION__CUR_COMP_THRESHOLD);
		createEAttribute(iterationEClass, ITERATION__CUR_MERGE_THRESHOLD);
		createEAttribute(iterationEClass, ITERATION__IS_MERGE_ITERATION);

		metricValueEClass = createEClass(METRIC_VALUE);
		createEAttribute(metricValueEClass, METRIC_VALUE__METRIC_ID);
		createEAttribute(metricValueEClass, METRIC_VALUE__VALUE);

		metricValuesModelEClass = createEClass(METRIC_VALUES_MODEL);
		createEReference(metricValuesModelEClass, METRIC_VALUES_MODEL__ITERATIONS);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__MIN_COMP_THRESHOLD);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__MAX_MERGE_THRESHOLD);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_LOW_COUPLING);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_HIGH_COUPLING);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_LOW_NAME_RESEMBLANCE);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_MID_NAME_RESEMBLANCE);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_HIGH_NAME_RESEMBLANCE);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_RELEVANT);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_INTERFACE_VIOLATION_IRRELEVANT);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_HIGH_SLAQ);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_LOW_SLAQ);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_PACKAGE_MAPPING);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_DIRECTORY_MAPPING);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_DMS);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WEIGHT_HIGHEST_NAME_RESEMBLANCE);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__WILDCARD_KEY);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__ADDITIONAL_WILDCARDS);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__MIN_MERGE_THRESHOLD);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__MAX_COMPOSE_THRESHOLD);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__COMPOSE_THRESHOLD_DECREMENT);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__MERGE_THRESHOLD_DECREMENT);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__EXCLUDED_PREFIXES_FOR_NAME_RESEMBLANCE);
		createEAttribute(metricValuesModelEClass, METRIC_VALUES_MODEL__EXCLUDED_SUFFIXES_FOR_NAME_RESEMBLANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponent_SubComponents(), this.getComponent(), null, "subComponents", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Name(), ecorePackage.getEString(), "name", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Id(), ecorePackage.getEString(), "id", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Classes(), theTypesPackage.getType(), null, "classes", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentCandidateEClass, ComponentCandidate.class, "ComponentCandidate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentCandidate_MetricValues(), this.getMetricValue(), null, "metricValues", null, 0, -1, ComponentCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentCandidate_FirstComponent(), this.getComponent(), null, "firstComponent", null, 1, 1, ComponentCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentCandidate_SecondComponent(), this.getComponent(), null, "secondComponent", null, 1, 1, ComponentCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iterationEClass, Iteration.class, "Iteration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIteration_ComponentCandidates(), this.getComponentCandidate(), null, "componentCandidates", null, 0, -1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIteration_Components(), this.getComponent(), null, "components", null, 0, -1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIteration_Number(), ecorePackage.getEInt(), "number", null, 0, 1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIteration_CurCompThreshold(), ecorePackage.getEDouble(), "curCompThreshold", null, 0, 1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIteration_CurMergeThreshold(), ecorePackage.getEDouble(), "curMergeThreshold", null, 0, 1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIteration_IsMergeIteration(), ecorePackage.getEBoolean(), "isMergeIteration", null, 0, 1, Iteration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metricValueEClass, MetricValue.class, "MetricValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetricValue_MetricID(), ecorePackage.getEString(), "metricID", null, 0, 1, MetricValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValue_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, MetricValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metricValuesModelEClass, MetricValuesModel.class, "MetricValuesModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetricValuesModel_Iterations(), this.getIteration(), null, "iterations", null, 0, -1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_MinCompThreshold(), ecorePackage.getEDouble(), "minCompThreshold", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_MaxMergeThreshold(), ecorePackage.getEDouble(), "maxMergeThreshold", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightLowCoupling(), ecorePackage.getEDouble(), "weightLowCoupling", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightHighCoupling(), ecorePackage.getEDouble(), "weightHighCoupling", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightLowNameResemblance(), ecorePackage.getEDouble(), "weightLowNameResemblance", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightMidNameResemblance(), ecorePackage.getEDouble(), "weightMidNameResemblance", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightHighNameResemblance(), ecorePackage.getEDouble(), "weightHighNameResemblance", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightInterfaceViolationRelevant(), ecorePackage.getEDouble(), "weightInterfaceViolationRelevant", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightInterfaceViolationIrrelevant(), ecorePackage.getEDouble(), "weightInterfaceViolationIrrelevant", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightHighSLAQ(), ecorePackage.getEDouble(), "weightHighSLAQ", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightLowSLAQ(), ecorePackage.getEDouble(), "weightLowSLAQ", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightPackageMapping(), ecorePackage.getEDouble(), "weightPackageMapping", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightDirectoryMapping(), ecorePackage.getEDouble(), "weightDirectoryMapping", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightDMS(), ecorePackage.getEDouble(), "weightDMS", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WeightHighestNameResemblance(), ecorePackage.getEDouble(), "weightHighestNameResemblance", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_WildcardKey(), ecorePackage.getEString(), "wildcardKey", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_AdditionalWildcards(), ecorePackage.getEString(), "additionalWildcards", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_MinMergeThreshold(), ecorePackage.getEDouble(), "minMergeThreshold", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_MaxComposeThreshold(), ecorePackage.getEDouble(), "maxComposeThreshold", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_ComposeThresholdDecrement(), ecorePackage.getEDouble(), "composeThresholdDecrement", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_MergeThresholdDecrement(), ecorePackage.getEDouble(), "mergeThresholdDecrement", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_ExcludedPrefixesForNameResemblance(), ecorePackage.getEString(), "excludedPrefixesForNameResemblance", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetricValuesModel_ExcludedSuffixesForNameResemblance(), ecorePackage.getEString(), "excludedSuffixesForNameResemblance", null, 0, 1, MetricValuesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //MetricvaluesPackageImpl
