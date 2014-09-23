package org.somox.metrics.basic;

import java.util.Set;

import org.emftext.language.java.types.Type;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractCountingMetric;

public class InternalAccessesCount extends AbstractCountingMetric {

    public final static MetricID METRIC_ID = new MetricID("org.somox.metrics.basic.InternalAccessesCount");

    @Override
    protected void internalComputeDirected (
            final ClusteringRelation relationToCompute) {
        final Set<Type> classes1 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getSourceComponent());
        final Set<Type> classes2 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getTargetComponent());

        relationToCompute.setResultMetric(getMID(),
                getAccessGraphCache().calculateNumberOfAccessesToClassesInSet(
                        classes1, classes2));
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
