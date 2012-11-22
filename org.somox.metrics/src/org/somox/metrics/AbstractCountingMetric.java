package org.somox.metrics;

public abstract class AbstractCountingMetric extends AbstractMetric {

	@Override
	public boolean isNormalised() {
		return false;
	}

}
