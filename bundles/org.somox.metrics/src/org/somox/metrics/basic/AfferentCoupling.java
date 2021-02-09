package org.somox.metrics.basic;

import java.util.Set;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractCountingMetric;

//import de.fzi.gast.types.GASTClass;

/**
 * Afferent coupling (Ca): The number of types outside this assembly that depend on types within
 * this assembly. High afferent coupling indicates that the concerned assemblies have many
 * responsibilities. see http://www.ndepend.com/Metrics.aspx#AfferentCoupling
 *
 * @author Steffen Becker
 */
public class AfferentCoupling extends AbstractCountingMetric {

    public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.basic.AfferentCoupling");

    @Override
    protected void internalComputeDirected(final ClusteringRelation relationToCompute) {
        final Set<ConcreteClassifier> internalClasses = this.calculateUnion(relationToCompute.getSourceComponent(),
                relationToCompute.getTargetComponent());
        relationToCompute.setResultMetric(METRIC_ID,
                this.getAccessGraphCache().calculateNumberOfIncommingAccesses(internalClasses));
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
