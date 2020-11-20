package org.somox.metrics;

import java.util.Map;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.jgrapht.DirectedGraph;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.metrics.helper.ClassAccessGraphEdge;
import org.somox.metrics.helper.ComponentToImplementingClassesHelper;
import org.somox.metrics.parameter.ParameterDescriptor;
import org.somox.metrics.tabs.MetricTab;

/**
 * Base interface for all SoMoX metrics.
 *
 */
public interface IMetric {

    /**
     * Computes the metric for the given clustering relation and stores the value in it
     */
    public void computeDirected(ClusteringRelation relationToCompute);

    /**
     * @return true if the metric gives the same result if firstComponent and secondComponent are
     *         swapped
     */
    public boolean isCommutative();

    /**
     * @return whether the metric is normalized. If it is normalized, values of this metric must be
     *         between 0.0 and 1.0.
     */
    public boolean isNormalised();

    /**
     * Initializes the Metric. Must be called before metrics are computed. Should be called again,
     * if the model changed
     *
     * @param gastModel
     *            the Root object of the GAST model
     * @param somoxConfiguration
     *            the configuration of the metric computation
     * @param allMetrics
     *            A map of all metrics available in the running SoMoX instance, maps metricID to
     *            Metric implementation
     * @param accessGraph
     *            A graph which contains the number of accesses from the source GASTClass to the
     *            target GASTClass for all edges in the
     * @param componentToClassHelper
     *            Helper initialized graph
     */
    public void initialize(Root gastModel, SoMoXConfiguration somoxConfiguration, Map<MetricID, IMetric> allMetrics,
            DirectedGraph<ConcreteClassifier, ClassAccessGraphEdge> accessGraph,
            ComponentToImplementingClassesHelper componentToClassHelper);

    /**
     * Returns the GUI launch configuration tab that can be used to configure the metric. Override
     * if the metric supplies a tab
     */
    public MetricTab getLaunchConfigurationTab();

    /**
     * Returns the Metric ID of the Metric
     *
     * @return the metric id of the instance
     */
    public MetricID getMID();

    /**
     * The parameters needed by this metric for its computation. Parameters will be shown on the UI
     * and their values will be passed to the metric during initialization of the metric
     *
     * @return A list of parameter descriptors for the parameters of this metric
     */
    public ParameterDescriptor[] getMetricParameters();

}