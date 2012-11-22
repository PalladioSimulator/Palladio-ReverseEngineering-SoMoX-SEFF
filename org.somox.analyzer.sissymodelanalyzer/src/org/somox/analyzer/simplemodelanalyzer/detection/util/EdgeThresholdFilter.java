package org.somox.analyzer.simplemodelanalyzer.detection.util;

import org.somox.filter.BaseFilter;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;

/**
 * Filters based on edge threshold hold only.
 *
 */
public class EdgeThresholdFilter extends BaseFilter<ClusteringRelation> {

	private final MetricID metric;
	private final double threshold;

	public EdgeThresholdFilter(MetricID metric, double threshold) {
		super();
		this.metric = metric;
		this.threshold = threshold;
	}

	@Override
	public boolean passes(ClusteringRelation object) {
		assert object.getResult().containsKey(metric);
		double relationValue = object.getResult().get(metric);
		return relationValue > threshold;
	}
};
