package org.somox.analyzer.simplemodelanalyzer.metrics;


import java.util.Map;

import org.apache.log4j.Logger;
import org.somox.analyzer.simplemodelanalyzer.metrics.tabs.WeightsTab;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.metrics.ICompositionFunction;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractComposedMetric;
import org.somox.metrics.hierarchy.DirectoryMapping;
import org.somox.metrics.hierarchy.PackageMapping;
import org.somox.metrics.naming.NameResemblance;
import org.somox.metrics.ratio.AdherenceToInterfaceCommunication;
import org.somox.metrics.ratio.Coupling;
import org.somox.metrics.ratio.DMS;
import org.somox.metrics.structure.SliceLayerArchitectureQuality;
import org.somox.metrics.structure.SubsystemComponent;
import org.somox.metrics.tabs.MetricTab;


/**
 * This class encapsulates the strategy used to compute the overall metric of the relationship of two
 * components
 * @author Steffen Becker, Klaus Krogmann, Grischa Liebel
 */
public class DefaultCompositionIndicatingMetric extends AbstractComposedMetric {
	
	/**
	 * Logger of this class
	 */
	private static final Logger logger = Logger.getLogger(DefaultCompositionIndicatingMetric.class);
	
	/**
	 * ID of this metric
	 */
	public static final MetricID METRIC_ID = new MetricID("org.somox.analyzer.simplemodelanalyzer.metrics.DefaultCompositionIndicatingMetric");

	/* (non-Javadoc)
	 * @see org.somox.metrics.Metric#getMID()
	 */
	@Override
	public MetricID getMID() {
		return METRIC_ID;
	}

	/* (non-Javadoc)
	 * @see org.somox.analyzer.simplemodelanalyzer.metrics.ComposedMetric#getCompositionFunction(org.somox.configuration.SoMoXConfiguration)
	 */
	@Override
	protected ICompositionFunction getCompositionFunction(
			SoMoXConfiguration somoxConfiguration) {
		logger.debug("Initialising overall computation function for composition operation");
		return new CompositionIndicationFunction(somoxConfiguration);
	}

	/* (non-Javadoc)
	 * @see org.somox.analyzer.simplemodelanalyzer.metrics.ComposedMetric#getChildMetrics(java.util.Map)
	 */
	@Override
	protected IMetric[] getChildMetrics(Map<MetricID, IMetric> allMetrics) {
		return new IMetric[] {
				getMetric(allMetrics, Coupling.METRIC_ID),
				getMetric(allMetrics, AdherenceToInterfaceCommunication.METRIC_ID),
				getMetric(allMetrics, NameResemblance.METRIC_ID),
				getMetric(allMetrics, DMS.METRIC_ID),
				getMetric(allMetrics, PackageMapping.METRIC_ID),
				getMetric(allMetrics, DirectoryMapping.METRIC_ID),
				getMetric(allMetrics, SubsystemComponent.METRIC_ID),
				getMetric(allMetrics, SliceLayerArchitectureQuality.METRIC_ID)
		};
	}
	
	/**
	 * {@inheritDoc}
	 */
	public MetricTab getLaunchConfigurationTab() {
		return new WeightsTab();
	}

	@Override
	public boolean isNormalised() {
		return true;
	}
}

