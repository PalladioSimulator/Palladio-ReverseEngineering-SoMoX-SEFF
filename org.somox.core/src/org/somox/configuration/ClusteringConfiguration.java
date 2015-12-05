package org.somox.configuration;

public class ClusteringConfiguration {

    private double minComposeClusteringThreshold;
    private double clusteringComposeThresholdDecrement;
    private double maxComposeClusteringThreshold;

    private double minMergeClusteringThreshold;
    private double clusteringMergeThresholdDecrement;
    private double maxMergeClusteringThreshold;

    // COMPOSE
    public double getMaxComposeClusteringThreshold() {
        return this.maxComposeClusteringThreshold;
    }

    public void setMaxComposeClusteringThreshold(final double clusteringThreshold) {
        this.maxComposeClusteringThreshold = clusteringThreshold;
    }

    public double getMinComposeClusteringThreshold() {
        return this.minComposeClusteringThreshold;
    }

    public void setMinComposeClusteringThreshold(final double clusteringThreshold) {
        this.minComposeClusteringThreshold = clusteringThreshold;
    }

    public double getClusteringComposeThresholdDecrement() {
        return this.clusteringComposeThresholdDecrement;
    }

    public void setClusteringComposeThresholdDecrement(final double clusteringThreshold) {
        this.clusteringComposeThresholdDecrement = clusteringThreshold;
    }

    // MERGE
    public double getMinMergeClusteringThreshold() {
        return this.minMergeClusteringThreshold;
    }

    public void setMinMergeClusteringThreshold(final double minMergeClusteringThreshold) {
        this.minMergeClusteringThreshold = minMergeClusteringThreshold;
    }

    /**
     * Attention: in fact is an increment!
     *
     * @return
     */
    public double getClusteringMergeThresholdDecrement() {
        return this.clusteringMergeThresholdDecrement;
    }

    public void setClusteringMergeThresholdDecrement(final double clusteringMergeThresholdDecrement) {
        this.clusteringMergeThresholdDecrement = clusteringMergeThresholdDecrement;
    }

    public double getMaxMergeClusteringThreshold() {
        return this.maxMergeClusteringThreshold;
    }

    public void setMaxMergeClusteringThreshold(final double maxMergeClusteringThreshold) {
        this.maxMergeClusteringThreshold = maxMergeClusteringThreshold;
    }

}
