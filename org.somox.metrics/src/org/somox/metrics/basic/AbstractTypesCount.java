package org.somox.metrics.basic;

import java.util.Set;

import org.emftext.language.java.types.Type;
import org.somox.filter.BaseFilter;
import org.somox.filter.FilteredCollectionsFactory;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.AbstractCountingMetric;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;

//import de.fzi.gast.types.GASTClass;

public class AbstractTypesCount extends AbstractCountingMetric {

	public static final MetricID METRIC_ID = new MetricID("org.somox.metric.basic.AbstractTypesCount");

	// filter used in this metric, gets abstract classes and interfaces
	private static final BaseFilter<Type> abstractClassesFilter = new BaseFilter<Type>() {

		@Override
		public boolean passes(Type object) {
			return KDMHelper.isAbstract(object) || KDMHelper.isInterface(object);
		}
	};
	
	@Override
	protected ClusteringRelation internalComputeDirected (
			ClusteringRelation relationToCompute) {
		
		//removelater
//		java.util.List<Type> type1 = relationToCompute.getComponentA().getImplementingClasses();
//		java.util.List<Type> type2 = relationToCompute.getComponentB().getImplementingClasses();
//		if(type1!= null & type2!=null & type1.size()>0 & type2.size()>0){
//			if(type1.get(0).getName().equals("GUIEventHandlerImpl") &
//					type2.get(0).getName().equals("StoreImpl")){
//				System.out.println("a");
//			}
//		}
		
		Set<Type> allClasses = calculateUnion(relationToCompute.getComponentA(), relationToCompute.getComponentB());
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
