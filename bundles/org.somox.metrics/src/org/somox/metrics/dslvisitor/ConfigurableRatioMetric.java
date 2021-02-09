package org.somox.metrics.dslvisitor;

import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractRatioMetric;
import org.somox.metrics.dsl.metricDSL.RatioMetric;

public class ConfigurableRatioMetric extends AbstractRatioMetric {

    private final MetricID nominatorMetricID;
    private final MetricID denominatorMetricID;
    private final MetricID metricID;

    public ConfigurableRatioMetric(final String id, final RatioMetric metricDefinition) {
        super();
        this.nominatorMetricID = new MetricID(metricDefinition.getNominatorMetric().getName());
        this.denominatorMetricID = new MetricID(metricDefinition.getDenominatorMetric().getName());
        this.metricID = new MetricID(id);
    }

    @Override
    protected MetricID getDenominatorMetricID() {
        return denominatorMetricID;
    }

    @Override
    protected MetricID getNumeratorMetricID() {
        return nominatorMetricID;
    }

    @Override
    public MetricID getMID() {
        return metricID;
    }
}
