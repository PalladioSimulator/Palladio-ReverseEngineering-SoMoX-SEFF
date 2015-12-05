package org.somox.common;

/**
 * Information on metric and sub-metrics and their weights
 *
 * @author Klaus Krogmann
 *
 */
public class MetricsDetails {

    /**
     * Name in the preferences file
     */
    public String metricWeightPeferenceName;

    /**
     * Label which is a short explanation of a metric
     */
    public String metricLabel;

    /**
     * Explanation of a metric / weight of a metric
     */
    public String metricExplanantion;

    /**
     * Group Information of the metric
     */
    public enum GroupID {
        GROUP_CLUSTERING, GROUP_MERGING, GROUP_METRICS
    };

    public GroupID group;

    /**
     * See public fields
     *
     * @param metricWeightPeferenceNameP
     * @param metricLabelP
     * @param metricExplanantionP
     * @param groupInfo
     */
    public MetricsDetails(final String metricWeightPeferenceNameP, final String metricLabelP,
            final String metricExplanantionP, final GroupID groupInfo) {
        this.metricWeightPeferenceName = metricWeightPeferenceNameP;
        this.metricLabel = metricLabelP;
        this.metricExplanantion = metricExplanantionP;
        this.group = groupInfo;
    }

}
