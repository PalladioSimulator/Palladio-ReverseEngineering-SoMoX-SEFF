package org.somox.analyzer.simplemodelanalyzer.detection.util;

import java.util.List;

import org.somox.filter.BaseFilter;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;

import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Filters based on edge threshold and vertex type: only initial
 * (primitive) components and at most one composite component pass.
 * @author Klaus Krogmann
 *
 */
public class VertexTypeAndEdgeThresholdFilter extends BaseFilter<ClusteringRelation> {

	private final MetricID metric;
	private final double threshold;

	public VertexTypeAndEdgeThresholdFilter(MetricID metric, double threshold) {
		super();
		this.metric = metric;
		this.threshold = threshold;
	}

	@Override
	public boolean passes(ClusteringRelation object) {
		assert object.getResult().containsKey(metric);
		double relationValue = object.getResult().get(metric);
		
		return (relationValue > threshold) &&
			onlyPrimitiveComponents(object.getComponents());				
	}
	
	private boolean onlyPrimitiveComponents(List<ComponentImplementingClassesLink> componentLinks) {
		
		for(ComponentImplementingClassesLink compLink : componentLinks) {
			if(!compLink.isIsCompositeComponent()) {
				return false;
			}
		}
		return true;
	}
};
