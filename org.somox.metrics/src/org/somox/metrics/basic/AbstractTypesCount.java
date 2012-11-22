package org.somox.metrics.basic;

import java.util.Set;

import org.somox.filter.BaseFilter;
import org.somox.filter.FilteredCollectionsFactory;
import org.somox.metrics.AbstractCountingMetric;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;

import de.fzi.gast.types.GASTClass;

public class AbstractTypesCount extends AbstractCountingMetric {

	public static final MetricID METRIC_ID = new MetricID("org.somox.metric.basic.AbstractTypesCount");

	// filter used in this metric, gets abstract classes and interfaces
	private static final BaseFilter<GASTClass> abstractClassesFilter = new BaseFilter<GASTClass>() {

		@Override
		public boolean passes(GASTClass object) {
			return object.isAbstract() || object.isInterface();
		}
	};
	
	@Override
	protected ClusteringRelation internalComputeDirected (
			ClusteringRelation relationToCompute) {
		Set<GASTClass> allClasses = calculateUnion(relationToCompute.getComponentA(), relationToCompute.getComponentB());
		relationToCompute.setResultMetric(getMID(), (double)FilteredCollectionsFactory.getFilteredHashSet(abstractClassesFilter, allClasses).size());
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
