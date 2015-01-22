package org.somox.metrics.basic;

import java.util.Set;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractCountingMetric;

public class TotalTypesCount extends AbstractCountingMetric {

    public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.basic.TotalTypesCount");

    @Override
    protected void internalComputeDirected(final ClusteringRelation relationToCompute) {
        final Set<ConcreteClassifier> allClasses = this.calculateUnion(relationToCompute.getSourceComponent(),
                relationToCompute.getTargetComponent());
        relationToCompute.setResultMetric(this.getMID(), allClasses.size());
    }

    @Override
    public MetricID getMID() {
        return METRIC_ID;
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

}
