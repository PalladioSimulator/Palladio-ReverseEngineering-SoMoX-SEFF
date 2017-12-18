package org.somox.metrics.abstractmetrics;

import java.util.Map;

import org.somox.configuration.SoMoXConfiguration;
import org.somox.metrics.ICompositionFunction;
import org.somox.metrics.MetricID;

public abstract class AbstractStepwiseMetric extends AbstractComposedMetric {

    public class BoundAndWeightStruct {
        private final double upperBound;
        private final double weight;

        public BoundAndWeightStruct(final double upperBound, final double weight) {
            super();
            this.upperBound = upperBound;
            this.weight = weight;
        }

        /**
         * @return the upperBound
         */
        public double getUpperBound() {
            return this.upperBound;
        }

        /**
         * @return the weight
         */
        public double getWeight() {
            return this.weight;
        }
    }

    protected BoundAndWeightStruct[] boundsAndWeights = this.getBoundsAndWeights();
    private final ICompositionFunction function = new ICompositionFunction() {

        @Override
        public double computeOverallDirectedMetricValue(final Map<MetricID, Double> metricValues) {
            assert AbstractStepwiseMetric.this.getAllChildMetrics().length == 1;
            final double innerMetricValue = metricValues
                    .get(AbstractStepwiseMetric.this.getAllChildMetrics()[0].getMID());
            for (final BoundAndWeightStruct range : AbstractStepwiseMetric.this.boundsAndWeights) {
                if (innerMetricValue < range.getUpperBound()) {
                    return innerMetricValue * range.getWeight();
                }
            }
            return innerMetricValue;
        }
    };

    @Override
    protected ICompositionFunction getCompositionFunction(final SoMoXConfiguration somoxConfiguration) {
        return this.function;
    }

    @Override
    public boolean isNormalised() {
        return true;
    }

    protected abstract BoundAndWeightStruct[] getBoundsAndWeights();
}
