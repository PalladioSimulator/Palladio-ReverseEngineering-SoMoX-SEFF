package org.somox.common;

import java.util.ArrayList;
import java.util.List;

import org.somox.common.MetricsDetails.GroupID;
import org.somox.configuration.SoMoXConfiguration;

/**
 * List of constants related to SoMoX metrics and weights.
 *
 * @author Klaus Krogmann
 *
 */
public class SoMoXProjectPreferences {

    /**
     * Ordered List of all metrics weights and according descriptions. The order is relevant for the
     * GUI.
     */
    public List<MetricsDetails> orderedMetricDetails;

    public SoMoXProjectPreferences() {
        this.orderedMetricDetails = new ArrayList<MetricsDetails>();
        this.orderedMetricDetails.add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_MERGE,
                "Clustering Merge Threshold Min (Start Value)", "The minimal abstraction level of basic components.",
                GroupID.GROUP_MERGING));
        this.orderedMetricDetails.add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_MERGE,
                "Clustering Merge Threshold Max (End Value)", "The maximal abstraction level of basic components.",
                GroupID.GROUP_MERGING));
        this.orderedMetricDetails
                .add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_MERGE,
                        "Clustering Merge Threshold Increment",
                        "Indicator for the number of abstraction level between highest and lowest abstraction level.",
                        GroupID.GROUP_MERGING));
        this.orderedMetricDetails.add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT,
                "Merge: Interface Violation", "Should basic components internally NOT communicate via interfaces? "
                        + "(high value: relevant; low value: irrelevant)",
                GroupID.GROUP_METRICS));
        this.orderedMetricDetails
                .add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_COMPOSE,
                        "Clustering Composition Threshold Min (End Value)",
                        "The minimal abstraction level of composite components.", GroupID.GROUP_CLUSTERING));
        this.orderedMetricDetails
                .add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_COMPOSE,
                        "Clustering Composition Threshold Max (Start Value)",
                        "The maximal abstraction level of composite components.", GroupID.GROUP_CLUSTERING));
        this.orderedMetricDetails
                .add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_COMPOSE,
                        "Clustering Composition Threshold Decrement",
                        "How many level of nesting of components? (high value: little levels; low value: many levels)",
                        GroupID.GROUP_CLUSTERING));
        this.orderedMetricDetails.add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT,
                "Composition: Interface Adherence",
                "Should composite components communicate via interfaces? (high value: relevant; low value: irrelevant)",
                GroupID.GROUP_METRICS));

        this.orderedMetricDetails.add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_PACKAGE_MAPPING,
                "Package Mapping", "How much are packages component indicators?", GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_DIRECTORY_MAPPING,
                "Directory Mapping", "How much are directories component indicators?", GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(
                new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE, "Highest Name Resemblance",
                        "Judges how much similar artefact names indicate components (importance to component detection for very similar names).",
                        GroupID.GROUP_METRICS));
        this.orderedMetricDetails
                .add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE, "High Name Resemblance",
                        "Judges how much similar artefact names indicate components (importance to component detection for pretty similar names).",
                        GroupID.GROUP_METRICS));
        this.orderedMetricDetails
                .add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_MID_NAME_RESEMBLANCE, "Mid Name Resemblance",
                        "Judges how much similar artefact names indicate components (importance to component detection for partially similar names).",
                        GroupID.GROUP_METRICS));
        this.orderedMetricDetails
                .add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE, "Low Name Resemblance",
                        "Judges how much similar artefact names indicate components (importance to component detection for little similar names).",
                        GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_HIGH_SLAQ,
                "High SLAQ (Slice Layer Architecture Quality)",
                "Are components indicated by Slices or Layers in the architecture and should these bricks be considered as components? (importance if pretty much SLAQ style)",
                GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_LOW_SLAQ, "Low SLAQ",
                "Are components indicated by Slices or Layers in the architecture and should these bricks be considered as components? (importance if moderate SLAQ style)",
                GroupID.GROUP_METRICS));
        this.orderedMetricDetails
                .add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_DMS, "DMS (Distance from the Main Sequence)",
                        "How important is the balance of abstractness (interfaces, abstract classes) and concrete elements (classes, implementations, structs) for a component?",
                        GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_HIGH_COUPLING, "High Coupling",
                "If code is coupled more than indicated by this threshold: Consider as coupled.",
                GroupID.GROUP_METRICS));
        this.orderedMetricDetails.add(new MetricsDetails(SoMoXConfiguration.SOMOX_WEIGHT_LOW_COUPLING, "Low Coupling",
                "If code is coupled more than indicated by this threshold: Consider as partially coupled.",
                GroupID.GROUP_METRICS));
    }
}
