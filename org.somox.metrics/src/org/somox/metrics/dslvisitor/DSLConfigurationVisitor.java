package org.somox.metrics.dslvisitor;

import org.somox.metrics.IMetric;
import org.somox.metrics.dSL.InternalMetric;
import org.somox.metrics.dSL.Metric;
import org.somox.metrics.dSL.RatioMetric;
import org.somox.metrics.dSL.StepwiseMetric;
import org.somox.metrics.dSL.WeightedMetric;
import org.somox.metrics.dSL.util.DSLSwitch;

public class DSLConfigurationVisitor extends DSLSwitch<IMetric> {

	/* (non-Javadoc)
	 * @see org.somox.metrics.dSL.util.DSLSwitch#caseMetric(org.somox.metrics.dSL.Metric)
	 */
	@Override
	public IMetric caseInternalMetric(InternalMetric object) {
		if (object.getDefinition() == null)
			return null;
		return this.doSwitch(object.getDefinition());
	}

	/* (non-Javadoc)
	 * @see org.somox.metrics.dSL.util.DSLSwitch#caseRatioMetric(org.somox.metrics.dSL.RatioMetric)
	 */
	@Override
	public IMetric caseRatioMetric(RatioMetric object) {
		return new ConfigurableRatioMetric(
				((Metric)object.eContainer()).getName(), 
				object);
	}

	/* (non-Javadoc)
	 * @see org.somox.metrics.dSL.util.DSLSwitch#caseStepwiseMetric(org.somox.metrics.dSL.StepwiseMetric)
	 */
	@Override
	public IMetric caseStepwiseMetric(StepwiseMetric object) {
		return new ConfigurableStepwiseMetric(
				((Metric)object.eContainer()).getName(), 
				object);
	}

	/* (non-Javadoc)
	 * @see org.somox.metrics.dSL.util.DSLSwitch#caseWeightedMetric(org.somox.metrics.dSL.WeightedMetric)
	 */
	@Override
	public IMetric caseWeightedMetric(WeightedMetric object) {
		return new ConfigurableWeightedComposedMetric(
				((Metric)object.eContainer()).getName(), 
				object);
	}
}
