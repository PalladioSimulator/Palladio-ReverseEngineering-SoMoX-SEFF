package org.somox.metrics.ratio;


import java.util.Map;

import org.somox.configuration.SoMoXConfiguration;
import org.somox.metrics.ICompositionFunction;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractComposedMetric;
import org.somox.metrics.basic.AfferentCoupling;
import org.somox.metrics.basic.EfferentCoupling;

/**
 * Instability metric
 * 
 * The ratio of efferent coupling (Ce) to total coupling. I = Ce / (Ce + Ca). 
 * This metric is an indicator of the package's resilience to change. The range 
 * for this metric is 0 to 1, with I=0 indicating a completely stable package and I=1 
 * indicating a completely instable package.
 * see http://www.ndepend.com/Metrics.aspx#Instability
 *  
 * @author Steffen Becker, Grischa Liebel
 *
 */
public class Instability extends AbstractComposedMetric {
	public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.Instability");
	
	private final ICompositionFunction instabilityFunction = new ICompositionFunction() {
		
		@Override
		public double computeOverallDirectedMetricValue(
				Map<MetricID, Double> metricValues) {
			double denominator = metricValues.get(EfferentCoupling.METRIC_ID)+metricValues.get(AfferentCoupling.METRIC_ID);
			if (denominator == 0.0) {
				return 0;
			}
			return metricValues.get(EfferentCoupling.METRIC_ID) / denominator;
		}
		
	}; 
	
	@Override
	protected IMetric[] getChildMetrics(
			Map<MetricID, IMetric> allMetrics) {
		return new IMetric[] {
				getMetric(allMetrics, EfferentCoupling.METRIC_ID),
				getMetric(allMetrics, AfferentCoupling.METRIC_ID)
		};
	}

	@Override
	protected ICompositionFunction getCompositionFunction(
			SoMoXConfiguration somoxConfiguration) {
		return instabilityFunction;
	}
	
	/* (non-Javadoc)
	 * @see org.somox.metrics.AbstractComposedMetric#isCommutative()
	 */
	@Override
	public boolean isCommutative() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.somox.metrics.AbstractMetric#getMID()
	 */
	@Override
	public MetricID getMID() {
		return METRIC_ID;
	}

	/* (non-Javadoc)
	 * @see org.somox.metrics.IMetric#isNormalised()
	 */
	@Override
	public boolean isNormalised() {
		return true;
	}
}
