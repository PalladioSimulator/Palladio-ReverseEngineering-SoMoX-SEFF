package org.somox.metrics.basic;

import java.util.Set;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.somox.filter.BaseFilter;
import org.somox.filter.FilteredCollectionsFactory;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractCountingMetric;

public class AbstractTypesCount extends AbstractCountingMetric {

    public static final MetricID METRIC_ID = new MetricID("org.somox.metric.basic.AbstractTypesCount");

    // filter used in this metric, gets abstract classes and interfaces
    private static final BaseFilter<ConcreteClassifier> abstractClassesFilter = new BaseFilter<ConcreteClassifier>() {

        @Override
        public boolean passes(final ConcreteClassifier object) {
            return KDMHelper.isAbstract(object) || KDMHelper.isInterface(object);
        }
    };

    @Override
    protected void internalComputeDirected(final ClusteringRelation relationToCompute) {

        final Set<ConcreteClassifier> allClasses = this.calculateUnion(relationToCompute.getSourceComponent(),
                relationToCompute.getTargetComponent());
        relationToCompute.setResultMetric(this.getMID(),
                FilteredCollectionsFactory.getFilteredHashSet(abstractClassesFilter, allClasses).size());
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
