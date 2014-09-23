package org.somox.analyzer.simplemodelanalyzer.detection;

import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.somox.analyzer.ModelAnalyzerException;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;

public class PairwiseRelationComputationTask implements
Callable<ClusteringRelation[]> {

    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(PairwiseRelationComputationTask.class);

    final private IMetric overallMetric;
    final private ClusteringRelation firstRelation;
    final private ClusteringRelation secondRelation;
    final private Map<MetricID, IMetric> allMetrics;

    public PairwiseRelationComputationTask(
            final IMetric overallMetric,
            final ClusteringRelation firstRelation,
            final ClusteringRelation secondRelation,
            final Map<MetricID,IMetric> allMetrics) {
        super();

        this.overallMetric = overallMetric;
        this.firstRelation = firstRelation;
        this.secondRelation = secondRelation;
        this.allMetrics = allMetrics;
    }

    @Override
    public ClusteringRelation[] call() throws Exception {
        final ClusteringRelation result[] = new ClusteringRelation[2];
        result[0] = computeClusteringRelation(
                firstRelation,
                null);
        result[1] = computeClusteringRelation(
                secondRelation,
                firstRelation);
        return result;
    }

    /**
     * Create a new {@link ClusteringRelation} based on the metrics computed for two given class links.
     * @param componentCandidates list of all component candidates found so far.
     * @param metricComputationStrategy metric calculation strategy
     * @param firstComponentCandidate first component candidate
     * @param secondComponentCandidate second component candidate
     * @return Evaluation of a pair of component candidates using the passed
     * 			<pre>metricComputationStrategy</pre>.
     * @throws ModelAnalyzerException
     */
    private ClusteringRelation computeClusteringRelation(
            final ClusteringRelation relationToCompute,
            final ClusteringRelation oppositeRelation) throws ModelAnalyzerException {
        if (oppositeRelation != null) {
            for (final Map.Entry<MetricID, Double> entry : oppositeRelation.getResult().entrySet()) {
                if (allMetrics.get(entry.getKey()).isCommutative()) {
                    relationToCompute.setResultMetric(entry.getKey(), entry.getValue());
                }
            }
        }
        overallMetric.computeDirected(relationToCompute);
        return relationToCompute;
    }
}
