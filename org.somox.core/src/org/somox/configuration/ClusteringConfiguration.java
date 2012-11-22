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
		return maxComposeClusteringThreshold;
	}

	public void setMaxComposeClusteringThreshold(double clusteringThreshold) {
		this.maxComposeClusteringThreshold = clusteringThreshold;
	}

	public double getMinComposeClusteringThreshold() {
		return minComposeClusteringThreshold;
	}

	public void setMinComposeClusteringThreshold(double clusteringThreshold) {
		this.minComposeClusteringThreshold = clusteringThreshold;
	}
	
	public double getClusteringComposeThresholdDecrement() {
		return clusteringComposeThresholdDecrement;
	}

	public void setClusteringComposeThresholdDecrement(double clusteringThreshold) {
		this.clusteringComposeThresholdDecrement = clusteringThreshold;
	}

	// MERGE
	public double getMinMergeClusteringThreshold() {
		return minMergeClusteringThreshold;
	}

	public void setMinMergeClusteringThreshold(double minMergeClusteringThreshold) {
		this.minMergeClusteringThreshold = minMergeClusteringThreshold;
	}

	/**
	 * Attention: in fact is an increment!
	 * @return
	 */
	public double getClusteringMergeThresholdDecrement() {
		return clusteringMergeThresholdDecrement;
	}

	public void setClusteringMergeThresholdDecrement(
			double clusteringMergeThresholdDecrement) {
		this.clusteringMergeThresholdDecrement = clusteringMergeThresholdDecrement;
	}

	public double getMaxMergeClusteringThreshold() {
		return maxMergeClusteringThreshold;
	}

	public void setMaxMergeClusteringThreshold(double maxMergeClusteringThreshold) {
		this.maxMergeClusteringThreshold = maxMergeClusteringThreshold;
	}
	
	
}
