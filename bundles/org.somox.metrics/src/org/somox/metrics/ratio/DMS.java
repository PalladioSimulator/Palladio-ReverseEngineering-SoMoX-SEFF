package org.somox.metrics.ratio;

import java.util.Map;

import org.somox.configuration.SoMoXConfiguration;
import org.somox.metrics.ICompositionFunction;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractComposedMetric;

/**
 * DMS metric, calculated by using Abstractness and Instability
 *
 * The perpendicular normalized distance of an assembly from the idealized line A + I = 1 (called
 * main sequence). This metric is an indicator of the assembly's balance between abstractness and
 * stability. An assembly squarely on the main sequence is optimally balanced with respect to its
 * abstractness and stability. Ideal assemblies are either completely abstract and stable (I=0, A=1)
 * or completely concrete and instable (I=1, A=0). The range for this metric is 0 to 1, with D=0
 * indicating an assembly that is coincident with the main sequence and D=1 indicating an assembly
 * that is as far from the main sequence as possible. see
 * http://www.ndepend.com/Metrics.aspx#DitFromMainSeq
 *
 * @author Steffen Becker, Grischa Liebel
 *
 */
public class DMS extends AbstractComposedMetric {

    public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.DMS");

    private static final ICompositionFunction compositionFunction = new ICompositionFunction() {

        @Override
        public double computeOverallDirectedMetricValue(final Map<MetricID, Double> metricValues) {
            final double abs = metricValues.get(Abstractness.METRIC_ID);

            // TODO: check: there should be no check here, based on abstractness alone one cannot
            // decide anything
            // if (abs >= 0.15 && abs <= 0.85) {
            final double ins = metricValues.get(Instability.METRIC_ID);

            // This formula is the formula for the normalized DMS. Its correctness can be proven by
            // mathematics
            // It is ensured to be between 0.0 and 1.0 if abstractness and instability are
            // implemented correctly
            final double dms = Math.abs(abs + ins - 1.0);
            assert dms >= 0.0 && dms <= 1.0;
            return dms;
        }

    };

    /**
     * {@inheritDoc}
     */
    @Override
    public MetricID getMID() {
        return METRIC_ID;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.metrics.ComposedMetric#getChildMetrics(java.util.Map)
     */
    @Override
    protected IMetric[] getChildMetrics(final Map<MetricID, IMetric> allMetrics) {
        return new IMetric[] { this.getMetric(allMetrics, Abstractness.METRIC_ID),
                this.getMetric(allMetrics, Instability.METRIC_ID) };
    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.metrics.ComposedMetric#getCompositionFunction(org.somox.configuration.
     * SoMoXConfiguration)
     */
    @Override
    protected ICompositionFunction getCompositionFunction(final SoMoXConfiguration somoxConfiguration) {
        return compositionFunction;
    }

    @Override
    public boolean isNormalised() {
        return true;
    }
}
