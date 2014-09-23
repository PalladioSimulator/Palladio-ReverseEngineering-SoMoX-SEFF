package org.somox.analyzer.simplemodelanalyzer.metrics;

import java.util.Map;

import org.apache.log4j.Logger;
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
 * components indicating a merge into one component.
 * @author Klaus Krogmann
 */
public class DefaultMergeIndicatingMetric extends AbstractComposedMetric {
	
	/**
	 * Logger of this class
	 */
	private static final Logger logger = Logger.getLogger(DefaultMergeIndicatingMetric.class);
	
	/**
	 * ID of this metric
	 */
	public static final MetricID METRIC_ID = new MetricID("org.somox.analyzer.simplemodelanalyzer.metrics.DefaultMergeIndicatingMetric");

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
		logger.debug("Initialising overall computation function for merge operation");
		return new MergeIndicationFunction(somoxConfiguration);
	}

	/* (non-Javadoc)
	 * @see org.somox.analyzer.simplemodelanalyzer.metrics.ComposedMetric#getChildMetrics(java.util.Map)
	 */
	@Override
	protected IMetric[] getChildMetrics(Map<MetricID, IMetric> allMetrics) {
		return new IMetric[] {
				/* getMetric(allMetrics, Coupling.METRIC_ID),
				getMetric(allMetrics, AdherenceToInterfaceCommunication.METRIC_ID),
				getMetric(allMetrics, NameResemblance.METRIC_ID),
				getMetric(allMetrics, DMS.METRIC_ID),
				getMetric(allMetrics, PackageMapping.METRIC_ID),
				getMetric(allMetrics, DirectoryMapping.METRIC_ID),
				getMetric(allMetrics, SubsystemComponent.METRIC_ID),
				getMetric(allMetrics, SliceLayerArchitectureQuality.METRIC_ID) */
				getMetric(allMetrics, Coupling.METRIC_ID),
				getMetric(allMetrics, AdherenceToInterfaceCommunication.METRIC_ID),
				getMetric(allMetrics, NameResemblance.METRIC_ID),
				getMetric(allMetrics, DMS.METRIC_ID),
				getMetric(allMetrics, PackageMapping.METRIC_ID),
				getMetric(allMetrics, DirectoryMapping.METRIC_ID),
				getMetric(allMetrics, SubsystemComponent.METRIC_ID),
				getMetric(allMetrics, SliceLayerArchitectureQuality.METRIC_ID),
				
				getMetric(allMetrics, DefaultCompositionIndicatingMetric.METRIC_ID), // FIXME: unwanted dependency to composition; makes assumption that merge is executed first!
		};
	}
	
	/**
	 * {@inheritDoc}
	 */
	public MetricTab getLaunchConfigurationTab() {
		return null;
		// return new WeightsTab(); //TODO: fix me and return a separate tab.
	}

	@Override
	public boolean isNormalised() {
		return true;
	}
}

