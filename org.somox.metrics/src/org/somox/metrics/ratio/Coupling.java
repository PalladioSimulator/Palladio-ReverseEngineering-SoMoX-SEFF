package org.somox.metrics.ratio;

import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractRatioMetric;
import org.somox.metrics.basic.ExternalAccessesCount;
import org.somox.metrics.basic.InternalAccessesCount;

/**
 * Coupling metric
 *
 * Efferent coupling (Ce): The number of types inside this assembly that depends on types outside
 * this assembly. High efferent coupling indicates that the concerned assembly is dependant. Notice
 * that types declared in framework assemblies are taken into account. see
 * http://www.ndepend.com/Metrics.aspx#EfferentCoupling
 *
 * Here it is the ratio of the number of accesses between the two component candidates to the total
 * number of accesses
 *
 * @author Steffen Becker, Grischa Liebel
 *
 */
public class Coupling extends AbstractRatioMetric {

    public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.Coupling");

    /**
     * {@inheritDoc}
     */
    @Override
    public MetricID getMID() {
        return METRIC_ID;
    }

    @Override
    protected MetricID getNumeratorMetricID() {
        return InternalAccessesCount.METRIC_ID;
    }

    @Override
    protected MetricID getDenominatorMetricID() {
        return ExternalAccessesCount.METRIC_ID;
    }
}
