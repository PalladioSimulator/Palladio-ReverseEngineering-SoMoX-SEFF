package org.somox.configuration;

public class ClusteringConfiguration {

    private double minComposeClusteringThreshold = .25;
    private double clusteringComposeThresholdDecrement = .1;
    private double maxComposeClusteringThreshold = 1;

    private double minMergeClusteringThreshold = .45;
    private double clusteringMergeThresholdDecrement = .1;
    private double maxMergeClusteringThreshold = 1;

    // COMPOSE
    public double getMaxComposeClusteringThreshold() {
        return maxComposeClusteringThreshold;
    }

    public void setMaxComposeClusteringThreshold(final double clusteringThreshold) {
        maxComposeClusteringThreshold = clusteringThreshold;
    }

    public double getMinComposeClusteringThreshold() {
        return minComposeClusteringThreshold;
    }

    public void setMinComposeClusteringThreshold(final double clusteringThreshold) {
        minComposeClusteringThreshold = clusteringThreshold;
    }

    public double getClusteringComposeThresholdDecrement() {
        return clusteringComposeThresholdDecrement;
    }

    public void setClusteringComposeThresholdDecrement(final double clusteringThreshold) {
        clusteringComposeThresholdDecrement = clusteringThreshold;
    }

    // MERGE
    public double getMinMergeClusteringThreshold() {
        return minMergeClusteringThreshold;
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
        return clusteringMergeThresholdDecrement;
    }

    public void setClusteringMergeThresholdDecrement(final double clusteringMergeThresholdDecrement) {
        this.clusteringMergeThresholdDecrement = clusteringMergeThresholdDecrement;
    }

    public double getMaxMergeClusteringThreshold() {
        return maxMergeClusteringThreshold;
    }

    public void setMaxMergeClusteringThreshold(final double maxMergeClusteringThreshold) {
        this.maxMergeClusteringThreshold = maxMergeClusteringThreshold;
    }

}
