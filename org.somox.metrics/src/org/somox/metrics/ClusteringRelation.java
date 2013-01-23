package org.somox.metrics;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * This class stores the computed metric of the relationship between component A and component B. Notice,
 * that instances of this class are immutable, i.e., they can be safely used in hashmaps, etc.
 * 
 * @author  Klaus Krogmann, Steffen Becker
 */
public class ClusteringRelation {

	private final ComponentImplementingClassesLink componentA;

	private final ComponentImplementingClassesLink componentB;

	private final Map<MetricID, Double> result = new HashMap<MetricID, Double>();

	private final Map<MetricID, Double> unmodifiableResult = Collections.unmodifiableMap(result);
	
	private static DecimalFormat format = new DecimalFormat(".00");
	
	public ClusteringRelation (ComponentImplementingClassesLink componentA, ComponentImplementingClassesLink componentB) {
		if (componentA == null || componentB == null || componentA == componentB)
			throw new IllegalArgumentException("Components must not be null or the same in a clustering relation");
		
		this.componentA = componentA;
		this.componentB = componentB;
	}
	
	// TODO: Move to somewhere else
	//	private boolean isInvalid(Map<MetricID, Double> result) {
	//		return result2 < -1.0 || result2 > 1.0 || Double.isNaN(result2) || Double.isInfinite(result2);
	//	}
	
	public ComponentImplementingClassesLink getComponentA() {
		return componentA;
	}
	
	public ComponentImplementingClassesLink getComponentB() {
		return componentB;
	}
	
	public List<ComponentImplementingClassesLink> getComponents() {
		List<ComponentImplementingClassesLink> result = new LinkedList<ComponentImplementingClassesLink>();
		
		result.add(componentA);
		result.add(componentB);
		
		return result;
	}
	
	public void setResultMetric(MetricID metric, double value) {
		assert !result.containsKey(metric);
		result.put(metric,value);
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Map.Entry<MetricID, Double> metricResult : result.entrySet()) {
			String metricFullName = metricResult.getKey().getMetricID();
			stringBuilder.append(metricFullName.substring(metricFullName.lastIndexOf(".")+1));
			stringBuilder.append(" = ");
			stringBuilder.append(format.format(metricResult.getValue()));
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	public Map<MetricID, Double> getResult() {
		return unmodifiableResult;
	}
}
