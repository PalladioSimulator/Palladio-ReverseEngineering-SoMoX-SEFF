package org.somox.metrics.dslvisitor;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.somox.metrics.Activator;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.metrics.dsl.metricDSL.ExternalMetric;
import org.somox.metrics.dsl.metricDSL.InternalMetric;
import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.RatioMetric;
import org.somox.metrics.dsl.metricDSL.StepwiseMetric;
import org.somox.metrics.dsl.metricDSL.WeightedMetric;
import org.somox.metrics.dsl.metricDSL.util.MetricDSLSwitch;

public class DSLConfigurationVisitor extends MetricDSLSwitch<IMetric> {

    private final Map<MetricID,IMetric> registeredMetrics;

    /**
     * @param registeredMetrics
     */
    public DSLConfigurationVisitor(final Map<MetricID, IMetric> registeredMetrics) {
        super();
        this.registeredMetrics = registeredMetrics;
    }

    /* (non-Javadoc)
     * @see org.somox.metrics.dSL.util.DSLSwitch#caseMetric(org.somox.metrics.dSL.Metric)
     */
    @Override
    public IMetric caseInternalMetric(final InternalMetric object) {
        if (object.getDefinition() == null) {
            return null;
        }
        return this.doSwitch(object.getDefinition());
    }

    /* (non-Javadoc)
     * @see org.somox.metrics.dSL.util.DSLSwitch#caseRatioMetric(org.somox.metrics.dSL.RatioMetric)
     */
    @Override
    public IMetric caseRatioMetric(final RatioMetric object) {
        return new ConfigurableRatioMetric(
                ((Metric)object.eContainer()).getName(),
                object);
    }

    /* (non-Javadoc)
     * @see org.somox.metrics.dSL.util.DSLSwitch#caseStepwiseMetric(org.somox.metrics.dSL.StepwiseMetric)
     */
    @Override
    public IMetric caseStepwiseMetric(final StepwiseMetric object) {
        return new ConfigurableStepwiseMetric(
                ((Metric)object.eContainer()).getName(),
                object);
    }

    /* (non-Javadoc)
     * @see org.somox.metrics.dSL.util.DSLSwitch#caseWeightedMetric(org.somox.metrics.dSL.WeightedMetric)
     */
    @Override
    public IMetric caseWeightedMetric(final WeightedMetric object) {
        return new ConfigurableWeightedComposedMetric(
                ((Metric)object.eContainer()).getName(),
                object);
    }

    /* (non-Javadoc)
     * @see org.somox.metrics.dsl.metricDSL.util.MetricDSLSwitch#caseExternalMetric(org.somox.metrics.dsl.metricDSL.ExternalMetric)
     */
    @Override
    public IMetric caseExternalMetric(final ExternalMetric object) {
        final MetricID metricID = new MetricID(object.getName());
        if (!registeredMetrics.containsKey(metricID)) {
            Activator
            .getPlugin()
            .getLog()
            .log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                    "Unable to find referenced metric"));
            throw new RuntimeException("Failed to import external metric as it is not registered.");
        }
        return registeredMetrics.get(metricID);
    }


}
