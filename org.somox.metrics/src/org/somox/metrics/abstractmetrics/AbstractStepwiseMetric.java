package org.somox.metrics.abstractmetrics;

import java.util.Map;

import org.somox.configuration.SoMoXConfiguration;
import org.somox.metrics.ICompositionFunction;
import org.somox.metrics.MetricID;


public abstract class AbstractStepwiseMetric extends AbstractComposedMetric {

	public class BoundAndWeightStruct {
		private final double upperBound;
		private final double weight;
		
		public BoundAndWeightStruct(double upperBound, double weight) {
			super();
			this.upperBound = upperBound;
			this.weight = weight;
		}

		/**
		 * @return the upperBound
		 */
		public double getUpperBound() {
			return upperBound;
		}
		
		/**
		 * @return the weight
		 */
		public double getWeight() {
			return weight;
		}
	}
	
	protected BoundAndWeightStruct[] boundsAndWeights = getBoundsAndWeights();
	private final ICompositionFunction function = new ICompositionFunction() {
		
		@Override
		public double computeOverallDirectedMetricValue(
				Map<MetricID, Double> metricValues) {
			assert AbstractStepwiseMetric.this.getAllChildMetrics().length == 1;
			double innerMetricValue = metricValues.get(AbstractStepwiseMetric.this.getAllChildMetrics()[0].getMID());
			for (BoundAndWeightStruct range : AbstractStepwiseMetric.this.boundsAndWeights) {
				if (innerMetricValue < range.getUpperBound()) {
					return innerMetricValue * range.getWeight();
				}
			}
			return innerMetricValue;
		}
	};


	@Override
	protected ICompositionFunction getCompositionFunction(
			SoMoXConfiguration somoxConfiguration) {
		return function;
	}

	@Override
	public boolean isNormalised() {
		return true;
	}

	protected abstract BoundAndWeightStruct[] getBoundsAndWeights();
}
