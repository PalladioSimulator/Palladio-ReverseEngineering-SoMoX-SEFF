package org.somox.metrics.basic;

import java.util.Set;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractCountingMetric;

public class InternalAccessesCount extends AbstractCountingMetric {

    public final static MetricID METRIC_ID = new MetricID("org.somox.metrics.basic.InternalAccessesCount");

    @Override
    protected void internalComputeDirected(final ClusteringRelation relationToCompute) {
        final Set<ConcreteClassifier> classes1 = this.getComponentToClassHelper()
                .deriveImplementingClasses(relationToCompute.getSourceComponent());
        final Set<ConcreteClassifier> classes2 = this.getComponentToClassHelper()
                .deriveImplementingClasses(relationToCompute.getTargetComponent());

        relationToCompute.setResultMetric(this.getMID(),
                this.getAccessGraphCache().calculateNumberOfAccessesToClassesInSet(classes1, classes2));
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
