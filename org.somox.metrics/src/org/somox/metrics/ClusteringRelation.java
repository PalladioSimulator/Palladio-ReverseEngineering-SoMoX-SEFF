package org.somox.metrics;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * This class stores the computed metric of the relationship between a source and a target component.
 * 
 * @author  Klaus Krogmann, Steffen Becker
 */
public class ClusteringRelation {

    private final ComponentImplementingClassesLink sourceComponent;

    private final ComponentImplementingClassesLink targetComponent;

    private final Map<MetricID, Double> result = new HashMap<MetricID, Double>();

    private final Map<MetricID, Double> unmodifiableResult = Collections.unmodifiableMap(result);

    private static DecimalFormat format = new DecimalFormat(".00");

    public ClusteringRelation (final ComponentImplementingClassesLink sourceComponent, final ComponentImplementingClassesLink targetComponent) {
        if (sourceComponent == null || targetComponent == null || sourceComponent == targetComponent) {
            throw new IllegalArgumentException("Components must not be null or the same in a clustering relation");
        }

        this.sourceComponent = sourceComponent;
        this.targetComponent = targetComponent;
    }

    // TODO: Move to somewhere else
    //	private boolean isInvalid(Map<MetricID, Double> result) {
    //		return result2 < -1.0 || result2 > 1.0 || Double.isNaN(result2) || Double.isInfinite(result2);
    //	}

    public ComponentImplementingClassesLink getSourceComponent() {
        return sourceComponent;
    }

    public ComponentImplementingClassesLink getTargetComponent() {
        return targetComponent;
    }

    public List<ComponentImplementingClassesLink> getComponents() {
        return Collections.unmodifiableList(Arrays.asList(sourceComponent,targetComponent));
    }

    public void setResultMetric(final MetricID metric, final double value) {
        if (result.containsKey(metric)) {
            throw new IllegalArgumentException("Metric added to clustering relation is already set");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Metric value is not a valid number. Some computations went wrong");
        }
        result.put(metric,value);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Map.Entry<MetricID, Double> metricResult : result.entrySet()) {
            final String metricFullName = metricResult.getKey().getMetricID();
            stringBuilder.append(metricFullName.substring(metricFullName.lastIndexOf(".")+1));
            stringBuilder.append(" = ");
            stringBuilder.append(format.format(metricResult.getValue()));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public Map<MetricID, Double> getResult() {
        return unmodifiableResult;
    }
}
