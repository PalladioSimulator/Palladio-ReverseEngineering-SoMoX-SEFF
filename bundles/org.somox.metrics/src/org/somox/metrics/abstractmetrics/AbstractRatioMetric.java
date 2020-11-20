package org.somox.metrics.abstractmetrics;

import java.util.Map;

import org.somox.configuration.SoMoXConfiguration;
import org.somox.metrics.ICompositionFunction;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;

/**
 * A metric which computes itself by dividing a nominator metric by a denominator metric
 *
 * @author Steffen Becker
 */
public abstract class AbstractRatioMetric extends AbstractComposedMetric implements IMetric {

    private final ICompositionFunction ratioFunction = new ICompositionFunction() {

        @Override
        public double computeOverallDirectedMetricValue(final Map<MetricID, Double> metricValues) {
            final double denominator = metricValues.get(AbstractRatioMetric.this.getDenominatorMetricID());
            if (denominator == 0.0) {
                return 0;
            }
            return metricValues.get(AbstractRatioMetric.this.getNumeratorMetricID()) / denominator;
        }

    };

    @Override
    protected IMetric[] getChildMetrics(final Map<MetricID, IMetric> allMetrics) {
        return new IMetric[] { this.getMetric(allMetrics, this.getNumeratorMetricID()),
                this.getMetric(allMetrics, this.getDenominatorMetricID()) };
    }

    protected abstract MetricID getNumeratorMetricID();

    protected abstract MetricID getDenominatorMetricID();

    @Override
    protected ICompositionFunction getCompositionFunction(final SoMoXConfiguration somoxConfiguration) {
        return this.ratioFunction;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.metrics.IMetric#isNormalised()
     */
    @Override
    public boolean isNormalised() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCommutative() {
        boolean result = true;
        for (final IMetric childMetric : this.getAllChildMetrics()) {
            if (!childMetric.isCommutative()) {
                result = false;
                break;
            }
        }
        return result;
    }

}
