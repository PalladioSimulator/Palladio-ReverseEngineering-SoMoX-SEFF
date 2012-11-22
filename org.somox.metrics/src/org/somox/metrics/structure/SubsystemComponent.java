package org.somox.metrics.structure;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.jgrapht.DirectedGraph;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.metrics.AbstractMetric;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.metrics.helper.ClassAccessGraphEdge;
import org.somox.metrics.helper.ComponentToImplementingClassesHelper;

import de.fzi.gast.core.Package;
import de.fzi.gast.core.Root;
import de.fzi.gast.types.GASTClass;

/**
 * SubsystemComponent metric
 * 
 * @author Grischa Liebel
 *
 */
public class SubsystemComponent extends AbstractMetric{
	public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.SubsystemComponent");
	
	private IMetric sliceArchitectureMetric = null;
	
	
	/* (non-Javadoc)
	 * @see org.somox.metrics.Metric#initialize(de.fzi.gast.core.Root, org.somox.configuration.SoMoXConfiguration, java.util.Map, org.jgrapht.DirectedGraph)
	 */
	@Override
	public void initialize(Root gastModel,
			SoMoXConfiguration somoxConfiguration,
			Map<MetricID, IMetric> allMetrics,
			DirectedGraph<GASTClass, ClassAccessGraphEdge> accessGraph,
			ComponentToImplementingClassesHelper componentToImplementingClassesHelper) {
		super.initialize(gastModel, somoxConfiguration, allMetrics, accessGraph,componentToImplementingClassesHelper);
		
		this.sliceArchitectureMetric = getMetric(allMetrics,SliceLayerArchitectureQuality.METRIC_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	protected ClusteringRelation internalComputeDirected (
			ClusteringRelation relationToCompute) {

		//TODO: Refactor me!!!!
		Set<GASTClass> classes1 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getComponentA());
		Set<GASTClass> classes2 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getComponentB());

		//compute overall prefix
		Package prefixPackage = computePrefix(classes1, classes2);
		
		if (prefixPackage == null) {
			relationToCompute.setResultMetric(getMID(), 0.0);
			return relationToCompute;
		}
		
		EList<de.fzi.gast.core.Package> slices = prefixPackage.getSubPackages();
		EList<de.fzi.gast.core.Package> layers = null;
		
		String subLayer = null;
		
		//compute the maximum number of layers in a slice
		int max = 0;
		for (Package current : slices) {
			if (current.getSubPackages().size()>=max) {
				layers = current.getSubPackages();
				max = layers.size();
			}
		}
		
		//0 expected Subsystems, return 0.0
		if (max == 0 || layers.size() == 0 || layers == null) {
			relationToCompute.setResultMetric(getMID(), 0.0);
			return relationToCompute;
		}
		Package currentPackage = null;
		for (GASTClass currentElement : classes1) {
			currentPackage = currentElement.getSurroundingPackage();

			if (currentPackage != null) {
				if (subLayer == null) {
					for (Package slicePackage : slices) {
						if (currentPackage.getQualifiedName().startsWith(slicePackage.getQualifiedName())) {
							for (Package layerPackage : layers) {
								if (currentPackage.getQualifiedName().startsWith(slicePackage.getQualifiedName() + "." + layerPackage.getSimpleName())) {
									subLayer = slicePackage.getQualifiedName() + "." + layerPackage.getSimpleName();
									break;
								}
							}
							break;
						}
					}
				} else {
					if (!currentPackage.getQualifiedName().startsWith(subLayer)) {
						relationToCompute.setResultMetric(getMID(), 0.0);
						return relationToCompute;
					}
				}
			}
		}
		
		for (GASTClass currentElement : classes2) {
			currentPackage = currentElement.getSurroundingPackage();
			if (currentPackage != null) {
				if (subLayer == null) {
					for (Package slicePackage : slices) {
						if (currentPackage.getQualifiedName().startsWith(slicePackage.getQualifiedName())) {
							for (Package layerPackage : layers) {
								if (currentPackage.getQualifiedName().startsWith(slicePackage.getQualifiedName() + "." + layerPackage.getSimpleName())) {
									subLayer = slicePackage.getQualifiedName() + "." + layerPackage.getSimpleName();
									break;
								}
							}
							break;
						}
					}
				} else {
					if (!currentPackage.getQualifiedName().startsWith(subLayer)) {
						relationToCompute.setResultMetric(getMID(), 0.0);
						return relationToCompute;
					}
				}
			}

		}
		relationToCompute = sliceArchitectureMetric.computeDirected(relationToCompute);
		assert relationToCompute.getResult().containsKey(sliceArchitectureMetric.getMID());
		double slaq = relationToCompute.getResult().get(sliceArchitectureMetric.getMID());
		relationToCompute.setResultMetric(getMID(), slaq);
		return relationToCompute;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isCommutative () {
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public MetricID getMID () {
		return METRIC_ID;
	}
	
	/**
	 * Computes the longest prefix for the given packages excluding the blacklisted packages and classes
	 * 
	 * @param packages a given package-hierarchy
	 * @return the last package in the package-hierarchy in which all non-blacklisted elements are included
	 */
	private Package computePrefix (Set<GASTClass> elements1, Set<GASTClass> elements2) {
		
		Package prefix = null;
		
		LinkedList<GASTClass> elementsLeft = new LinkedList<GASTClass>();
		
		elementsLeft.addAll(elements1);
		elementsLeft.addAll(elements2);
		
		java.util.ListIterator<GASTClass> iterator = elementsLeft.listIterator();
		
		while (iterator.hasNext()) {
			GASTClass current = iterator.next();
			if (prefix == null && current.getSurroundingPackage() != null) {
				prefix = current.getSurroundingPackage();
			}
			
			if (prefix != null && current.getSurroundingPackage() != null && !current.getQualifiedName().startsWith(prefix.getQualifiedName())) {
				prefix = prefix.getSurroundingPackage();
				if (prefix == null) {
					return null;
				} else {
					iterator = elementsLeft.listIterator();
				}
			}
		}
		
		return prefix;
	}

	@Override
	public boolean isNormalised() {
		return true;
	}
}
