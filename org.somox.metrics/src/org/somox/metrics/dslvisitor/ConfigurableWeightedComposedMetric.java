package org.somox.metrics.dslvisitor;

import java.util.Map;

import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractWeightedComposedMetric;
import org.somox.metrics.dsl.metricDSL.WeightedMetric;

public class ConfigurableWeightedComposedMetric extends
AbstractWeightedComposedMetric {

    private static final DSLValueVisitor visitor = new DSLValueVisitor();

    private final MetricID metricID;
    private final double[] weights;
    private final MetricID[] metrics;

    public ConfigurableWeightedComposedMetric(final String id, final WeightedMetric metricDefinition) {
        super();
        this.metricID = new MetricID(id);
        weights = new double[metricDefinition.getWeights().size()];
        metrics = new MetricID[metricDefinition.getWeights().size()];
        for (int i = 0; i < metricDefinition.getWeights().size(); i++) {
            weights[i] = visitor.doSwitch(metricDefinition.getWeights().get(i).getWeight());
            metrics[i] = new MetricID(metricDefinition.getWeights().get(i).getMetric().getName());
        }
    }

    @Override
    protected double[] getWeigths() {
        return weights;
    }

    @Override
    protected IMetric[] getChildMetrics(final Map<MetricID, IMetric> allMetrics) {
        return metricIDToIMetric(metrics,allMetrics);
    }

    @Override
    public MetricID getMID() {
        return metricID;
    }

}
