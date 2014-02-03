package org.somox.metrics.basic;

import java.util.Set;

import org.apache.log4j.Logger;
import org.emftext.language.java.types.Type;
import org.somox.filter.BaseFilter;
import org.somox.filter.FilteredCollectionsFactory;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.AbstractCountingMetric;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;

//import de.fzi.gast.types.GASTClass;

public class InterfaceAccessesCount extends AbstractCountingMetric {

	private static final Logger logger = Logger.getLogger(InterfaceAccessesCount.class);
	
	public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.basic.InterfaceAccessesCount");

	private final static BaseFilter<Type> interfaceClassesFilter = new BaseFilter<Type>() {

		@Override
		public boolean passes(Type clazz) {
			return KDMHelper.isInterface(clazz);
		}
	};
	
	@Override
	protected ClusteringRelation internalComputeDirected (
			ClusteringRelation relationToCompute) {

		//removelater
//		java.util.List<Type> type1 = relationToCompute.getComponentA().getImplementingClasses();
//		java.util.List<Type> type2 = relationToCompute.getComponentB().getImplementingClasses();
//		if(type1!= null & type2!=null & type1.size()>0 & type2.size()>0){
//			if(type1.get(0).getName().equals("EnterpriseQueryImpl") &
//					type2.get(0).getName().equals("PersistenceContextImpl")){
//				String fileName = "interfacecount.txt";; 
////				org.somox.changetest.Helper.writeToFile(fileName, "---" +type1.get(0).getName() + " " + type2.get(0).getName());
//			}
//		}

		Set<Type> classes1 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getComponentA());
		Set<Type> classes2 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getComponentB());
		
		long accessesToInterfaces = 
			getAccessGraphCache().calculateNumberOfAccessesToClassesInSet(
					classes1,
					FilteredCollectionsFactory.getFilteredHashSet(interfaceClassesFilter, classes2));		
		if(logger.isTraceEnabled()) {
			logger.trace(relationToCompute.getComponentA() + " --> "+relationToCompute.getComponentB() + " Interface Accesses = "+accessesToInterfaces);
		}
		
		relationToCompute.setResultMetric(getMID(), (double)accessesToInterfaces);
		return relationToCompute;
	}

	@Override
	public MetricID getMID() {
		return METRIC_ID;
	}

	@Override
	public boolean isCommutative() {
		return false;
	}

}
