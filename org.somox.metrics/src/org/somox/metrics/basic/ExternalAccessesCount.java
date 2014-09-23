package org.somox.metrics.basic;

import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractCountingMetric;
import org.somox.metrics.tabs.BlacklistTab;
import org.somox.metrics.tabs.MetricTab;

/**
 * Counts accesses from component1 to all classes besides those of component1
 * @author Snowball
 */
public class ExternalAccessesCount extends AbstractCountingMetric {

    public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.basic.ExternalAccessesCount");

    /**
     * {@inheritDoc}
     */
    @Override
    public MetricTab getLaunchConfigurationTab() {
        // TODO: Move to general SoMoX config as this is not specific for a single metric...
        return new BlacklistTab();
    }

    @Override
    protected void internalComputeDirected (
            final ClusteringRelation relationToCompute) {
        relationToCompute.setResultMetric(getMID(),
                getAccessGraphCache().calculateNumberOfExternalAccesses(
                        this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getSourceComponent())));
    }

    @Override
    public MetricID getMID() {
        return METRIC_ID;
    }

    @Override
    public boolean isCommutative() {
        return false;
    }
}
