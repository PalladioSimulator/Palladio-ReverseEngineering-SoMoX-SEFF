package org.somox.metrics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

/**
 * A registry storing all registered metrics in the running Eclipse instance. 
 * Note that this assumes a reboot of Eclipse on configuration changes.
 * 
 * @author Steffen Becker
 */
public class MetricsRegistry {

	private static final String METRIC_DEFINITION_ATTRIBUTE_NAME = "metric_definition";
	private static final String ORG_SOMOX_CORE_METRIC_EXTENSION_ID = "org.somox.core.metric";
	private static final Logger logger = Logger.getLogger(MetricsRegistry.class);
	
	/**
	 * @return All registered metrics in the running Eclipse instance
	 */
	public static Map<MetricID, IMetric> getRegisteredMetrics() {
		return Collections.unmodifiableMap(internalGetRegisteredMetrics());
	}
	
	private static Map<MetricID, IMetric> internalGetRegisteredMetrics() {
		Map<MetricID, IMetric> allMetrics = new HashMap<MetricID, IMetric>();
		IConfigurationElement[] metricExtensions = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						ORG_SOMOX_CORE_METRIC_EXTENSION_ID);
		for (IConfigurationElement metricExtension : metricExtensions) {
			if (metricExtension.getName().equals("metric")) {
				Object o = null;
				try {
					o = metricExtension.createExecutableExtension("class");
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(
							new Status(Status.ERROR, Activator.PLUGIN_ID,
									"Warning: Specified Analyzer Metric "
											+ metricExtension.getName()
											+ " cannot be loaded."));
					throw new RuntimeException("Eclipse core failure", e);
				}
				if (o == null || !(o instanceof AbstractMetric)) {
					Activator.getDefault().getLog().log(
							new Status(Status.ERROR, Activator.PLUGIN_ID,
									"Warning: Specified Analyzer Metric "
											+ metricExtension.getName()
											+ " cannot be loaded."));
					throw new RuntimeException(
							"Eclipse core failure. Could not instanciate metric",
							null);
				}
				allMetrics.put(((AbstractMetric) o).getMID(), (AbstractMetric) o);
			} else {
//				String resourcePath = metricExtension.getAttribute(METRIC_DEFINITION_ATTRIBUTE_NAME);
//				MetricModel metricDefinitions = loadResource(metricExtension.getContributor().getName()+"/"+resourcePath);
//				if (metricDefinitions != null) {
//					for (EObject o : metricDefinitions.getMetrics()) {
//						IMetric m = parseMetricDefintion((Metric)o);
//						if (m != null) {
//							allMetrics.put(m.getMID(),m);
//						}
//					}
//				}
//				else {
//					Activator.getPlugin().getLog().log(new Status(
//							IStatus.ERROR, 
//							Activator.PLUGIN_ID, 
//							"Failed to load a metric definition from a metric DSL file. Some metrics might" +
//							"be unavailable and further errors may occur during SoMoX runs"));
//				}
			}
		}
		return allMetrics;
	}

//	private static IMetric parseMetricDefintion(Metric o) {
//		logger.info("Parsing metric definition "+o.getName());
//		return new DSLConfigurationVisitor().doSwitch(o);
//	}

//	private static MetricModel loadResource(String resourcePath) {
//		ResourceSet rs = new ResourceSetImpl();
//		Resource r = rs.createResource(URI.createPlatformPluginURI(resourcePath,true));
//		try {
//			r.load(new HashMap<Object, Object>());
//		} catch (IOException e) {
//			Activator.getPlugin().getLog().log(new Status(
//					IStatus.ERROR, 
//					Activator.PLUGIN_ID, 
//					Status.OK, 
//					"Failed to load metrics from stored DSL", e));
//			return null;
//		}
//		return (MetricModel)r.getContents().get(0);
//	}
}
