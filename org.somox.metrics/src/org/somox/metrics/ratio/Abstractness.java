package org.somox.metrics.ratio;

import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractRatioMetric;
import org.somox.metrics.basic.AbstractTypesCount;
import org.somox.metrics.basic.TotalTypesCount;

/**
 * Abstractness metric
 *  The ratio of the number of internal abstract types (i.e abstract classes and interfaces)
 *  to the number of internal types. The range for this metric is 0 to 1, with A=0 indicating
 *  a completely concrete assembly and A=1 indicating a completely abstract assembly.
 *  see http://www.ndepend.com/Metrics.aspx#Abstractness
 * 
 * @author Steffen Becker, Grischa Liebel
 */
public class Abstractness extends AbstractRatioMetric {

    public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.Abstractness");

    /**
     * {@inheritDoc}
     */
    @Override
    public MetricID getMID () {
        return METRIC_ID;
    }

    @Override
    protected MetricID getNumeratorMetricID() {
        return AbstractTypesCount.METRIC_ID;
    }

    @Override
    protected MetricID getDenominatorMetricID() {
        return TotalTypesCount.METRIC_ID;
    }

}