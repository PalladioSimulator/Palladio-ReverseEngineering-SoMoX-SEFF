package org.somox.common;

import java.util.ArrayList;
import java.util.List;

import org.somox.common.MetricsDetails.GroupID;

/**
 * List of constants related to SoMoX metrics and weights.
 *
 * @author Klaus Krogmann
 *
 */
public class SoMoXProjectPreferences {

    public static String SOMOX_PROJECT_NAME = "org.somox.project";
    public static String SOMOX_ANALYZER_INPUT_FILE = "org.somox.analyzer.inputfile";
    public static String SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES = "org.somox.analyzer.ReverseEngineerInterfacesNotAssignedToComponent";

    public static final String SOMOX_ANALYSER_WILDCARD_KEY = "org.somox.metrics.wildcards";
    public static final String BLACKLIST_CONFIGURATION_WILDCARDS_ADDITIONAL = "org.somox.metrics.wildcards.additional";

    // weights preference ids:
    public static final String SOMOX_WEIGHT_PACKAGE_MAPPING = "org.somox.packageMapping.weightPackageMapping";
    public static final String SOMOX_WEIGHT_DIRECTORY_MAPPING = "org.somox.directoryMapping.weightDirectoryMapping";
    public static final String SOMOX_WEIGHT_DMS = "org.somox.dms.weightDMS";
    public static final String SOMOX_WEIGHT_LOW_COUPLING = "org.somox.nameResemblance.weightLowCoupling";
    public static final String SOMOX_WEIGHT_HIGH_COUPLING = "org.somox.nameResemblance.weightHighCoupling";
    public static final String SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE = "org.somox.nameResemblance.weightLowNameResemblance";
    public static final String SOMOX_WEIGHT_MID_NAME_RESEMBLANCE = "org.somox.nameResemblance.weightMidNameResemblance";
    public static final String SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE = "org.somox.nameResemblance.weightHighNameResemblance";
    public static final String SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE = "org.somox.nameResemblance.weightHighestNameResemblance";
    public static final String SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT = "org.somox.interfaceViolation.weightInterfaceViolationRelevant";
    public static final String SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT = "org.somox.interfaceViolation.weightInterfaceViolationIrrelevant";
    public static final String SOMOX_WEIGHT_HIGH_SLAQ = "org.somox.subsystemComponent.weightHighSLAQ";
    public static final String SOMOX_WEIGHT_LOW_SLAQ = "org.somox.subsystemComponent.weightLowSLAQ";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_COMPOSE = "org.somox.clusteringThresholdMax.Compose";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_COMPOSE = "org.somox.clusteringThresholdMin.Compose";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_COMPOSE = "org.somox.clusteringThresholdDecrement.Compose";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_MERGE = "org.somox.clusteringThresholdMax.Merge";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_MERGE = "org.somox.clusteringThresholdMin.Merge";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_MERGE = "org.somox.clusteringThresholdDecrement.Merge";

    /**
     * Ordered List of all metrics weights and according descriptions. The order is relevant for the
     * GUI.
     */
    public List<MetricsDetails> orderedMetricDetails;

    public SoMoXProjectPreferences() {
        this.orderedMetricDetails = new ArrayList<MetricsDetails>();
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_MERGE,
                "Clustering Merge Threshold Min (Start Value)", "The minimal abstraction level of basic components.",
                GroupID.GROUP_MERGING));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_MERGE,
                "Clustering Merge Threshold Max (End Value)", "The maximal abstraction level of basic components.",
                GroupID.GROUP_MERGING));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_MERGE,
                "Clustering Merge Threshold Increment",
                "Indicator for the number of abstraction level between highest and lowest abstraction level.",
                GroupID.GROUP_MERGING));
        this.orderedMetricDetails
                .add(new MetricsDetails(SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT, "Merge: Interface Violation",
                        "Should basic components internally NOT communicate via interfaces? (high value: relevant; low value: irrelevant)",
                        GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_COMPOSE,
                "Clustering Composition Threshold Min (End Value)",
                "The minimal abstraction level of composite components.", GroupID.GROUP_CLUSTERING));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_COMPOSE,
                "Clustering Composition Threshold Max (Start Value)",
                "The maximal abstraction level of composite components.", GroupID.GROUP_CLUSTERING));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_COMPOSE,
                "Clustering Composition Threshold Decrement",
                "How many level of nesting of components? (high value: little levels; low value: many levels)",
                GroupID.GROUP_CLUSTERING));
        this.orderedMetricDetails
                .add(new MetricsDetails(SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT, "Composition: Interface Adherence",
                        "Should composite components communicate via interfaces? (high value: relevant; low value: irrelevant)",
                        GroupID.GROUP_METRICS));

        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_PACKAGE_MAPPING, "Package Mapping",
                "How much are packages component indicators?", GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_DIRECTORY_MAPPING, "Directory Mapping",
                "How much are directories component indicators?", GroupID.GROUP_METRICS));
        this.orderedMetricDetails
                .add(new MetricsDetails(SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE, "Highest Name Resemblance",
                        "Judges how much similar artefact names indicate components (importance to component detection for very similar names).",
                        GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE, "High Name Resemblance",
                "Judges how much similar artefact names indicate components (importance to component detection for pretty similar names).",
                GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_MID_NAME_RESEMBLANCE, "Mid Name Resemblance",
                "Judges how much similar artefact names indicate components (importance to component detection for partially similar names).",
                GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE, "Low Name Resemblance",
                "Judges how much similar artefact names indicate components (importance to component detection for little similar names).",
                GroupID.GROUP_METRICS));
        this.orderedMetricDetails
                .add(new MetricsDetails(SOMOX_WEIGHT_HIGH_SLAQ, "High SLAQ (Slice Layer Architecture Quality)",
                        "Are components indicated by Slices or Layers in the architecture and should these bricks be considered as components? (importance if pretty much SLAQ style)",
                        GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_LOW_SLAQ, "Low SLAQ",
                "Are components indicated by Slices or Layers in the architecture and should these bricks be considered as components? (importance if moderate SLAQ style)",
                GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_DMS, "DMS (Distance from the Main Sequence)",
                "How important is the balance of abstractness (interfaces, abstract classes) and concrete elements (classes, implementations, structs) for a component?",
                GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_HIGH_COUPLING, "High Coupling",
                "If code is coupled more than indicated by this threshold: Consider as coupled.",
                GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SOMOX_WEIGHT_LOW_COUPLING, "Low Coupling",
                "If code is coupled more than indicated by this threshold: Consider as partially coupled.",
                GroupID.GROUP_METRICS));
    }
}
