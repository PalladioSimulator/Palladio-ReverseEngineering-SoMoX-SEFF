package org.somox.metrics.abstractmetrics;

public abstract class AbstractCountingMetric extends AbstractMetric {

	@Override
	public boolean isNormalised() {
		return false;
	}

}
