package org.somox.metrics.dslvisitor;

import java.util.Map;

import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractStepwiseMetric;
import org.somox.metrics.dsl.metricDSL.BoundAndWeight;
import org.somox.metrics.dsl.metricDSL.StepwiseMetric;

public class ConfigurableStepwiseMetric extends AbstractStepwiseMetric {

    private final MetricID metricID;
    private final MetricID innerMetric;
    private final BoundAndWeightStruct[] boundsAndWeights;

    private static final DSLValueVisitor visitor = new DSLValueVisitor();

    public ConfigurableStepwiseMetric(final String id, final StepwiseMetric metricDefinition) {
        super();
        this.metricID = new MetricID(id);
        this.innerMetric = new MetricID(metricDefinition.getInnerMetric().getName());
        this.boundsAndWeights = new BoundAndWeightStruct[metricDefinition.getSteps().size()];
        for (int i = 0; i < metricDefinition.getSteps().size(); i++) {
            final BoundAndWeight singleWeight = metricDefinition.getSteps().get(i);
            this.boundsAndWeights[i] = new BoundAndWeightStruct(
                    visitor.doSwitch(singleWeight.getUpperBound()),
                    visitor.doSwitch(singleWeight.getWeight()));
        }
    }

    @Override
    protected BoundAndWeightStruct[] getBoundsAndWeights() {
        return boundsAndWeights;
    }

    @Override
    protected IMetric[] getChildMetrics(final Map<MetricID, IMetric> allMetrics) {
        return metricIDToIMetric(new MetricID[]{innerMetric}, allMetrics);
    }

    @Override
    public MetricID getMID() {
        return metricID;
    }

}
