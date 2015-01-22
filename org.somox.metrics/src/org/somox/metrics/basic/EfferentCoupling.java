package org.somox.metrics.basic;

import java.util.Set;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractCountingMetric;

//import de.fzi.gast.types.GASTClass;

/**
 * Efferent coupling (Ce): The number of types inside this assembly that depends on types outside
 * this assembly. High efferent coupling indicates that the concerned assembly is dependant. see
 * http://www.ndepend.com/Metrics.aspx#EfferentCoupling
 *
 * @author Steffen Becker
 */
public class EfferentCoupling extends AbstractCountingMetric {

    public static final MetricID METRIC_ID = new MetricID("org.somox.metric.basic.EfferentCoupling");

    @Override
    protected void internalComputeDirected(final ClusteringRelation relationToCompute) {

        final Set<ConcreteClassifier> internalClasses = this.calculateUnion(relationToCompute.getSourceComponent(),
                relationToCompute.getTargetComponent());

        relationToCompute.setResultMetric(METRIC_ID,
                this.getAccessGraphCache().calculateNumberOfExternalAccesses(internalClasses));
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
