package org.somox.metrics.structure;

import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.somox.metrics.AbstractMetric;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;

import de.fzi.gast.types.GASTClass;

/**
 * SliceLayerArchitectureQuality (SLAQ) metric
 * 
 * @author Grischa Liebel
 *
 */
public class SliceLayerArchitectureQuality extends AbstractMetric {
	public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.SliceLayerArchitectureQuality");
	
	//implemented abstract methods from Metric
	
	/**
	 * {@inheritDoc}
	 */
	protected ClusteringRelation internalComputeDirected (
			ClusteringRelation relationToCompute) {

		Set<GASTClass> classes1 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getComponentA());
		Set<GASTClass> classes2 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getComponentB());
		
		//compute overall prefix
		de.fzi.gast.core.Package prefixPackage = computePrefix(classes1, classes2);
		
		if (prefixPackage == null) {
			relationToCompute.setResultMetric(getMID(), 0.0);
			return relationToCompute;
		}
		EList<de.fzi.gast.core.Package> slices = prefixPackage.getSubPackages();
		EList<de.fzi.gast.core.Package> layers = null;

		//compute the maximum number of layers in a slice
		int max = 0;
		for (de.fzi.gast.core.Package current : slices) {
			if (current.getSubPackages().size()>=max) {
				layers = current.getSubPackages();
				max = layers.size();
			}
		}

		//check how many of the computed layers exist in every slice 
		if (max == 0) {
			relationToCompute.setResultMetric(getMID(), 1.0);
			return relationToCompute;
		} else {
			int expectedSubsystems = slices.size()*layers.size();
			int existingSubsystems = 0;
			
			for (de.fzi.gast.core.Package currentSlice : slices) {
				EList<de.fzi.gast.core.Package> currentLayers = currentSlice.getSubPackages();
				for (de.fzi.gast.core.Package currentReferencePackage : layers) {
					for (de.fzi.gast.core.Package currentLayer : currentLayers) {
						if (currentLayer.getSimpleName().equals(currentReferencePackage.getSimpleName())) {
							existingSubsystems++;
							break;
						}
					}
				}
			}
			
			if (expectedSubsystems == 0) {
				relationToCompute.setResultMetric(getMID(), 1.0);
				return relationToCompute;
			} else {
				relationToCompute.setResultMetric(getMID(), (double)existingSubsystems/(double)expectedSubsystems);
				return relationToCompute;
			}
		}
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
	 * Computes the longest prefix for the given elements
	 * 
	 * @param elements1 first list of elements
	 * @param elements2 second list of elements
	 * @return the last package in the package-hierarchy in which all elements are included
	 */
	private de.fzi.gast.core.Package computePrefix(Set<GASTClass> elements1, Set<GASTClass> elements2) {
		String prefix = "";
		boolean prefixFound = false;
		de.fzi.gast.core.Package currentPackage = null;
		
		for (GASTClass current : elements1) {
			if (current.getSurroundingPackage() != null) {
				currentPackage = current.getSurroundingPackage();
				break;
			}
		}

		if (currentPackage == null) {
			for (GASTClass current : elements2) {
				if (current.getSurroundingPackage() != null) {
					currentPackage = current.getSurroundingPackage();
					break;
				}
			}
		}
		
		if (currentPackage == null) {
			return null;
		}
		
		prefix = currentPackage.getQualifiedName();
		
		while(!prefixFound) {
			prefixFound = true;
			
			for (GASTClass current : elements1) {
				if (!current.isInner()) {
					if (current.getSurroundingPackage() != null) {
						if (!current.getSurroundingPackage().getQualifiedName().contains(prefix)) {
							prefixFound = false;
							break;
						}
					}
				}
			}
			
			if (!prefixFound) {
				currentPackage = currentPackage.getSurroundingPackage();
				if (currentPackage == null) {
					return null;
				} else {
					prefix = currentPackage.getQualifiedName();
				}
			}
		}
		prefixFound = false;
		
		while(!prefixFound) {
			prefixFound = true;
			
			for (GASTClass current : elements2) {
				if (!current.isInner()) {
					if (current.getSurroundingPackage() != null) {
						if (!current.getSurroundingPackage().getQualifiedName().contains(prefix)) {
							prefixFound = false;
							break;
						}
					}
				}
			}

			if (!prefixFound) {
				currentPackage = currentPackage.getSurroundingPackage();
				if (currentPackage == null) {
					return null;
				} else {
					prefix = currentPackage.getQualifiedName();
				}
			}
		}
		return currentPackage;
	}

	@Override
	public boolean isNormalised() {
		return true;
	}
}
