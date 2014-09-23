package org.somox.metrics.basic;

import java.util.Set;

import org.apache.log4j.Logger;
import org.emftext.language.java.types.Type;
import org.somox.filter.BaseFilter;
import org.somox.filter.FilteredCollectionsFactory;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractCountingMetric;

public class InterfaceAccessesCount extends AbstractCountingMetric {

    private static final Logger logger = Logger.getLogger(InterfaceAccessesCount.class);

    public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.basic.InterfaceAccessesCount");

    private final static BaseFilter<Type> interfaceClassesFilter = new BaseFilter<Type>() {

        @Override
        public boolean passes(final Type clazz) {
            return KDMHelper.isInterface(clazz);
        }
    };

    @Override
    protected void internalComputeDirected (
            final ClusteringRelation relationToCompute) {

        final Set<Type> classes1 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getSourceComponent());
        final Set<Type> classes2 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getTargetComponent());

        final long accessesToInterfaces =
                getAccessGraphCache().calculateNumberOfAccessesToClassesInSet(
                        classes1,
                        FilteredCollectionsFactory.getFilteredHashSet(interfaceClassesFilter, classes2));
        if(logger.isTraceEnabled()) {
            logger.trace(relationToCompute.getSourceComponent() + " --> "+relationToCompute.getTargetComponent() + " Interface Accesses = "+accessesToInterfaces);
        }

        relationToCompute.setResultMetric(getMID(), accessesToInterfaces);
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
