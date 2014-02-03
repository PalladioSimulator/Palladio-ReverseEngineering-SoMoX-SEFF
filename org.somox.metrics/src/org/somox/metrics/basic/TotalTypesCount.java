package org.somox.metrics.basic;

import java.util.Set;

import org.emftext.language.java.types.Type;
import org.somox.metrics.AbstractCountingMetric;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;

//import de.fzi.gast.types.GASTClass;

public class TotalTypesCount extends AbstractCountingMetric {

	public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.basic.TotalTypesCount");

	@Override
	protected ClusteringRelation internalComputeDirected (
			ClusteringRelation relationToCompute) {
		Set<Type> allClasses = calculateUnion(relationToCompute.getComponentA(), relationToCompute.getComponentB());
		relationToCompute.setResultMetric(getMID(), (double)allClasses.size());
		return relationToCompute;
	}

	@Override
	public MetricID getMID() {
		return METRIC_ID;
	}

	@Override
	public boolean isCommutative() {
		return true;
	}

}
