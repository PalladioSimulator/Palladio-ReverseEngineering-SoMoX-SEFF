package org.somox.analyzer.simplemodelanalyzer.detection;

import java.util.Map;
import java.util.concurrent.Callable;

import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;

import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

public class NodePair {
	
	private final ComponentImplementingClassesLink first;
	private final ComponentImplementingClassesLink second;
	
	
	public NodePair(
			ComponentImplementingClassesLink first,
			ComponentImplementingClassesLink second) {
		super();
		if (first == null || second == null) {
			throw new IllegalArgumentException("Non of the nodes must be null");
		}
		
		this.first = first;
		this.second = second;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return first.hashCode() ^ second.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodePair other = (NodePair) obj;
		return (first == other.first && second == other.second) || (first == other.second && second == other.first); 
	}

	public Callable<ClusteringRelation[]> getWorkTask(IMetric overallMetric, Map<MetricID,IMetric> allMetrics) {
		return new PairwiseRelationComputationTask(overallMetric,new ClusteringRelation(first, second),new ClusteringRelation(second, first),allMetrics);
	}
	
}
