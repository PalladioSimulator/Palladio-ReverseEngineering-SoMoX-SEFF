package org.somox.analyzer.simplemodelanalyzer.detection;

import java.util.Map;
import java.util.concurrent.Callable;

import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * A pair of component candidate nodes. Used as immutable data structure to store component
 * candidates to compute pairwise comparisons.
 */
public final class NodePair {

    /**
     * First component candidate
     */
    private final ComponentImplementingClassesLink first;

    /**
     * Second component candidate
     */
    private final ComponentImplementingClassesLink second;

    public NodePair(final ComponentImplementingClassesLink first, final ComponentImplementingClassesLink second) {
        super();

        if (first == null || second == null) {
            throw new IllegalArgumentException("None of the nodes must be null");
        }

        this.first = first;
        this.second = second;
    }

    public Callable<ClusteringRelation[]> getWorkTask(final IMetric overallMetric,
            final Map<MetricID, IMetric> allMetrics) {
        return new PairwiseRelationComputationTask(overallMetric, new ClusteringRelation(this.first, this.second),
                new ClusteringRelation(this.second, this.first), allMetrics);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.first.hashCode() ^ this.second.hashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final NodePair other = (NodePair) obj;
        return (this.first == other.first && this.second == other.second)
                || (this.first == other.second && this.second == other.first);
    }

}
