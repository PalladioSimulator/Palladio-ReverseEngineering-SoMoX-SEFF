package org.somox.metrics.ratio;

import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractRatioMetric;
import org.somox.metrics.basic.InterfaceAccessesCount;
import org.somox.metrics.basic.InternalAccessesCount;

/**
 * AdherenceToInterfaceCommunication metric
 * 
 * This metric evaluates to amount of communication between two components going over interfaces.
 * 1.0 means all communication goes over interfaces, 0.0 means no communication goes over interfaces.
 * 
 * @author Steffen Becker, Grischa Liebel
 *
 */
public class AdherenceToInterfaceCommunication extends AbstractRatioMetric {

    public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.AdherenceToInterfaceCommunication");

    /**
     * {@inheritDoc}
     */
    @Override
    public MetricID getMID () {
        return METRIC_ID;
    }

    @Override
    protected MetricID getNumeratorMetricID() {
        return InterfaceAccessesCount.METRIC_ID;
    }

    @Override
    protected MetricID getDenominatorMetricID() {
        return InternalAccessesCount.METRIC_ID;
    }
}
