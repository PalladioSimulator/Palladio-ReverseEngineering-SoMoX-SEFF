package org.somox.metrics.registry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.somox.metrics.Activator;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
//import org.somox.metrics.dsl.metricDSL.Metric;
//import org.somox.metrics.dsl.metricDSL.MetricModel;
//import org.somox.metrics.dslvisitor.DSLConfigurationVisitor;

/**
 * A registry storing all registered metrics in the running Eclipse instance. Note that this assumes
 * a reboot of Eclipse on configuration changes.
 *
 * @author Steffen Becker
 */
public class MetricsRegistry {

    private static final String METRIC_CLASS_ATTRIBUTE = "class";
    private static final String METRIC_ATTRIBUTE_NAME = "metric";
    private static final String METRIC_FORMULA_ATTRIBUTE_NAME = "metricformula";
    private static final String METRIC_DEFINITION_ATTRIBUTE_NAME = "metric_definition";
    private static final String ORG_SOMOX_CORE_METRIC_EXTENSION_ID = "org.somox.core.metric";
    private static final Logger LOG = Logger.getLogger(MetricsRegistry.class);
    private static final Map<MetricID, IMetric> registeredMetrics;

    static {
        registeredMetrics = Collections.unmodifiableMap(internalGetRegisteredMetrics());
    }

    /**
     * @return All registered metrics in the running Eclipse instance
     */
    public final static Map<MetricID, IMetric> getRegisteredMetrics() {
        return registeredMetrics;
    }

    private static Map<MetricID, IMetric> internalGetRegisteredMetrics() {
        final Map<MetricID, IMetric> allMetrics = new HashMap<MetricID, IMetric>();
        final IConfigurationElement[] metricExtensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(ORG_SOMOX_CORE_METRIC_EXTENSION_ID);
        for (final IConfigurationElement metricExtension : metricExtensions) {
            if (metricExtension.getName().equals(METRIC_ATTRIBUTE_NAME)) {
                parseBasicMetric(allMetrics, metricExtension);
            }
        }
        for (final IConfigurationElement metricExtension : metricExtensions) {
            if (metricExtension.getName().equals(METRIC_FORMULA_ATTRIBUTE_NAME)) {
                parseMetricDefinitionModel(allMetrics, metricExtension);
            }
        }
        return allMetrics;
    }

    /**
     * @param allMetrics
     * @param metricExtension
     */
    private static void parseMetricDefinitionModel(final Map<MetricID, IMetric> allMetrics,
            final IConfigurationElement metricExtension) {
        // final String resourcePath =
        // metricExtension.getAttribute(METRIC_DEFINITION_ATTRIBUTE_NAME);
        // final MetricModel metricDefinitions =
        // loadResource(metricExtension.getContributor().getName() + "/"
        // + resourcePath);
        // if (metricDefinitions != null) {
        // for (final Metric metricDefinition : metricDefinitions.getMetrics()) {
        // final IMetric newMetric = parseMetricDefintion(allMetrics,metricDefinition);
        // if (newMetric != null) {
        // if (!allMetrics.containsKey(newMetric.getMID())) {
        // allMetrics.put(newMetric.getMID(), newMetric);
        // }
        // } else {
        // Activator
        // .getPlugin()
        // .getLog()
        // .log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
        // "Unable to parse metric from metric defintion, ID was "+metricDefinition.getName()));
        // }
        // }
        // } else {
        // Activator
        // .getPlugin()
        // .getLog()
        // .log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
        // "Failed to load a metric definition from a metric DSL file. Some metrics might"
        // + "be unavailable and further errors may occur during SoMoX runs"));
        // }
    }

    /**
     * @param allMetrics
     * @param metricExtension
     */
    private static void parseBasicMetric(final Map<MetricID, IMetric> allMetrics,
            final IConfigurationElement metricExtension) {
        Object o = null;
        try {
            o = metricExtension.createExecutableExtension(METRIC_CLASS_ATTRIBUTE);
        } catch (final CoreException e) {
            Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                    "Warning: Specified Analyzer Metric " + metricExtension.getName() + " cannot be loaded."));
            throw new RuntimeException("Eclipse core failure", e);
        }
        if (o == null || !(o instanceof IMetric)) {
            Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                    "Warning: Specified Analyzer Metric " + metricExtension.getName() + " cannot be loaded."));
            throw new RuntimeException("Eclipse core failure. Could not instanciate metric", null);
        }
        allMetrics.put(((IMetric) o).getMID(), (IMetric) o);
    }

    // private static IMetric parseMetricDefintion(final Map<MetricID, IMetric> allMetrics, final
    // Metric metric) {
    // LOG.info("Parsing metric definition " + metric.getName());
    // return new DSLConfigurationVisitor(allMetrics).doSwitch(metric);
    // }
    //
    // private static MetricModel loadResource(final String resourcePath) {
    // final ResourceSet rs = new ResourceSetImpl();
    // final Resource r = rs.createResource(URI.createPlatformPluginURI(resourcePath, true));
    // try {
    // r.load(new HashMap<Object, Object>());
    // } catch (final IOException e) {
    // Activator
    // .getPlugin()
    // .getLog()
    // .log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, Status.OK,
    // "Failed to load metrics from stored DSL", e));
    // throw new RuntimeException(e);
    // }
    // return (MetricModel) r.getContents().get(0);
    // }
}
