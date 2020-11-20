package org.somox.metrics;

import java.util.Map;

/**
 * Interface for a strategy pattern used to parameterize the way the overall metric is being
 * computed.
 *
 * @author Grischa Liebel, Klaus Krogmann, Steffen Becker
 */
public interface ICompositionFunction {

    /**
     * Computes the overall metric score based on Landrys weighting functions
     *
     * @param metricValues
     *            The ID for every metric and its calculated score
     * @return the overall score of the metric
     * @throws AnalyzerRuleException
     */
    public double computeOverallDirectedMetricValue(Map<MetricID, Double> metricValues);

}
