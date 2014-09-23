package org.somox.metrics.abstractmetrics;

import java.util.Map;

import org.apache.log4j.Logger;
import org.emftext.language.java.types.Type;
import org.jgrapht.DirectedGraph;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.ICompositionFunction;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.metrics.helper.ClassAccessGraphEdge;
import org.somox.metrics.helper.ComponentToImplementingClassesHelper;

//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;

/**
 * Base class for a metric which uses other (child) metrics and a function to
 * calculate the overall metric value.
 * 
 * @author Steffen Becker
 */
public abstract class AbstractComposedMetric extends AbstractMetric {

    private static final Logger logger = Logger.getLogger(AbstractComposedMetric.class);

    /**
     * List of all metrics composed in this metric
     */
    private IMetric[] allChildMetrics;

    /**
     * Strategy pattern. Contains the strategy how to compose the overall metric based on the single metrics available
     */
    private ICompositionFunction compositionFunction = null;

    /* (non-Javadoc)
     * @see org.somox.metrics.Metric#initialize(de.fzi.gast.core.Root, org.somox.configuration.SoMoXConfiguration, java.util.Map, org.jgrapht.DirectedGraph, org.somox.metrics.helper.ComponentToImplementingClassesHelper)
     */
    @Override
    public void initialize(
            final Root gastModel,
            final SoMoXConfiguration somoxConfiguration,
            final Map<MetricID, IMetric> allMetrics,
            final DirectedGraph<Type, ClassAccessGraphEdge> accessGraph,
            final ComponentToImplementingClassesHelper componentToImplementingClassesHelper) {
        super.initialize(gastModel, somoxConfiguration, allMetrics, accessGraph, componentToImplementingClassesHelper);

        this.compositionFunction = getCompositionFunction(somoxConfiguration);
        this.allChildMetrics = getChildMetrics(allMetrics);
    }

    /* (non-Javadoc)
     * @see org.somox.metrics.Metric#computeDirected(eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink, eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink, java.util.List)
     */
    @Override
    protected void internalComputeDirected(final ClusteringRelation relationToCompute) {

        for (final IMetric m : this.allChildMetrics) {
            if (!relationToCompute.getResult().containsKey(m.getMID())) {
                m.computeDirected(relationToCompute);
            }
        }
        if (logger.isDebugEnabled()) {
            for (final IMetric m : this.allChildMetrics) {
                assert relationToCompute.getResult().containsKey(m.getMID());
                assert relationToCompute.getResult().get(m.getMID()) != null;
            }
        }
        relationToCompute.setResultMetric(getMID(),
                this.compositionFunction.computeOverallDirectedMetricValue(relationToCompute.getResult()));
    }

    /* (non-Javadoc)
     * @see org.somox.metrics.Metric#isCommutative()
     */
    @Override
    public boolean isCommutative() {
        boolean result = true;
        for (final IMetric m : allChildMetrics) {
            result &= m.isCommutative();
        }
        return result;
    }

    /**
     * In a subclass override this method and return the subset of metrics in allMetrics which are needed
     * in this composed metric.
     * @param allMetrics The set of all metrics registered in the system via the metric extension point
     * @return The subset of all metrics needed in this composed metric
     */
    protected abstract IMetric[] getChildMetrics(Map<MetricID, IMetric> allMetrics);

    /**
     * Return the function used to compose the set of child metrics
     * @param somoxConfiguration The somox configuration object used to initialize the function
     * @return The function used to compute the composed metric
     */
    protected abstract ICompositionFunction getCompositionFunction(
            SoMoXConfiguration somoxConfiguration);

    /**
     * @return the allChildMetrics
     */
    public IMetric[] getAllChildMetrics() {
        return allChildMetrics;
    }

    /**
     * @param metrics
     * @param allMetrics
     * @return
     */
    protected IMetric[] metricIDToIMetric(final MetricID[] metrics, final Map<MetricID, IMetric> allMetrics) {
        final IMetric[] result = new IMetric[metrics.length];
        for (int i = 0; i < metrics.length; i++) {
            result[i] = allMetrics.get(metrics[i]);
        }
        return result;
    }
}