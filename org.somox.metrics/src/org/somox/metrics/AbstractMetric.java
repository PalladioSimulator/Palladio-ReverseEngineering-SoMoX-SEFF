package org.somox.metrics;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.emftext.language.java.types.Type;
import org.jgrapht.DirectedGraph;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.metrics.helper.ClassAccessGraphEdge;
import org.somox.metrics.helper.ComponentToImplementingClassesHelper;
import org.somox.metrics.parameter.ParameterDescriptor;
import org.somox.metrics.tabs.MetricTab;
import org.somox.metrics.util.AccessCacheGraph;

//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Abstract base class of all metrics, contains convenience functions and basic 
 * method implementations
 * 
 * @author Steffen Becker
 */
public abstract class AbstractMetric implements IMetric {
	
	/**
	 * Logger of this class 
	 */
	private Logger logger = Logger.getLogger(AbstractMetric.class);
	
	/**
	 * Helper used to convert source code decorators 
	 * into sets of implementing classes
	 */
	private ComponentToImplementingClassesHelper componentToClassHelper;

	/**
	 * Cache used to query to number of accesses between
	 * classes
	 */
	private AccessCacheGraph accessGraphCache;
	
	/* (non-Javadoc)
	 * @see org.somox.metrics.IMetric#computeDirected(eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink, eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink, java.util.List)
	 */
	public ClusteringRelation computeDirected (
			ClusteringRelation relationToCompute) {
		assert checkMetricPreCondition(relationToCompute);
		ClusteringRelation result = internalComputeDirected(relationToCompute);
		assert checkMetricPostCondition(relationToCompute);
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.somox.metrics.IMetric#getLaunchConfigurationTab()
	 */
	public MetricTab getLaunchConfigurationTab() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.somox.metrics.IMetric#getMetricParameters()
	 */
	public ParameterDescriptor[] getMetricParameters() {
		return new ParameterDescriptor[] {};
	}

	/* (non-Javadoc)
	 * @see org.somox.metrics.IMetric#isCommutative()
	 */
	public abstract boolean isCommutative();

	/* (non-Javadoc)
	 * @see org.somox.metrics.IMetric#initialize(de.fzi.gast.core.Root, org.somox.configuration.SoMoXConfiguration, java.util.Map, org.jgrapht.DirectedGraph, org.somox.metrics.helper.ComponentToImplementingClassesHelper)
	 */
	public void initialize (
			Root gastModel,
			SoMoXConfiguration somoxConfiguration, 
			Map<MetricID,IMetric> allMetrics, 
			DirectedGraph<Type, ClassAccessGraphEdge> accessGraph,
			ComponentToImplementingClassesHelper componentToClassHelper) {
		if (accessGraph == null) {
			logger.error("No access cache graph passed");
			throw new IllegalArgumentException("Access graph must not be null");
		}
		this.accessGraphCache = new AccessCacheGraph(accessGraph);
		this.componentToClassHelper = componentToClassHelper;
	}

	/** 
	 * Template method to be implemented by subclasses to effectively compute the 
	 * metric value
	 * @param relationToCompute The relation to compute
	 * @return The computed relation, it is assumed to be the same than relationToCompute
	 */
	protected abstract ClusteringRelation internalComputeDirected (
			ClusteringRelation relationToCompute);

	/**
	 * Method used to implement a metric postcondition. Used if Somox runs with assertions
	 * enabled and helps in debugging the metrics
	 * @param relationToCompute The computed metric relation
	 * @return true if the postcondition holds
	 */
	protected boolean checkMetricPostCondition(
			ClusteringRelation relationToCompute) {
		return relationToCompute.getResult().containsKey(getMID());
	}

	/**
	 * Method used to implement a metric precondition. Used if Somox runs with assertions
	 * enabled and helps in debugging the metrics
	 * @param relationToCompute The computed metric relation
	 * @return true if the precondition holds
	 */
	protected boolean checkMetricPreCondition(ClusteringRelation relationToCompute) {
		return !relationToCompute.getResult().containsKey(getMID());
	}

	/**
	 * @return the accessGraphCache
	 */
	protected AccessCacheGraph getAccessGraphCache() {
		return accessGraphCache;
	}

	/** Helper method used to securely retrieve a metric
	 * from a map of metrics
	 * @param allMetrics Map of metrics and their IDs
	 * @param metricId ID to retrieve
	 * @return The requested metric
	 */
	protected IMetric getMetric(Map<MetricID, IMetric> allMetrics, MetricID metricId) {
		IMetric result = allMetrics.get(metricId);
		
		if (result == null)
			throw new RuntimeException("Configuration error, Metric "+metricId+" needed but not available");
		
		return result;
	}

	/**
	 * Helper method which computes the union of two sets of
	 * GASTClasses
	 * @param classes1 first set
	 * @param classes2 second set
	 * @return Union of set1 and set2
	 */
	protected Set<Type> calculateUnion(Set<Type> classes1, Set<Type> classes2) {
		Set<Type> allInnerClasses = new HashSet<Type>(classes1);
		allInnerClasses.addAll(classes2);
		return allInnerClasses;
	}
	
	/**
	 * Helper method which computes the union of the classes
	 * implementing the two components
	 * @param component1 First component
	 * @param component2 Second component
	 * @return Union of all classes implementing component1 and component2
	 */
	protected Set<Type> calculateUnion(ComponentImplementingClassesLink component1, ComponentImplementingClassesLink component2) {
		return calculateUnion(
				this.getComponentToClassHelper().deriveImplementingClasses(component1),
				this.getComponentToClassHelper().deriveImplementingClasses(component2));		
	}

	/**
	 * @return the componentToClassHelper
	 */
	protected ComponentToImplementingClassesHelper getComponentToClassHelper() {
		return componentToClassHelper;
	}
}
