package org.somox.metrics.structure;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.types.Type;
import org.jgrapht.DirectedGraph;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractMetric;
import org.somox.metrics.helper.ClassAccessGraphEdge;
import org.somox.metrics.helper.ComponentToImplementingClassesHelper;

//import de.fzi.gast.core.Package;
//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;

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
			DirectedGraph<Type, ClassAccessGraphEdge> accessGraph,
			ComponentToImplementingClassesHelper componentToImplementingClassesHelper) {
		super.initialize(gastModel, somoxConfiguration, allMetrics, accessGraph,componentToImplementingClassesHelper);
		
		this.sliceArchitectureMetric = getMetric(allMetrics,SliceLayerArchitectureQuality.METRIC_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void internalComputeDirected (
			ClusteringRelation relationToCompute) {
		 

		//removelater
//		java.util.List<Type> type1 = relationToCompute.getComponentA().getImplementingClasses();
//		java.util.List<Type> type2 = relationToCompute.getComponentB().getImplementingClasses();
//		if(type1!= null & type2!=null & type1.size()>0 & type2.size()>0){
//			if(type1.get(0).getName().equals("StoreQueryImplTest") &
//					type2.get(0).getName().equals("TransactionContextImpl")){
//				String fileName = "interfacecount.txt";; 
////				org.somox.changetest.Helper.writeToFile(fileName, "---" +type1.get(0).getName() + " " + type2.get(0).getName());
//			}
//		}
		
		//TODO: Refactor me!!!!
		Set<Type> classes1 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getSourceComponent());
		Set<Type> classes2 = this.getComponentToClassHelper().deriveImplementingClasses(relationToCompute.getTargetComponent());

		//compute overall prefix
		Package prefixPackage = computePrefix(classes1, classes2);
		
		if (prefixPackage == null) {
			relationToCompute.setResultMetric(getMID(), 0.0);
			return;
		}
		
		EList<Package> slices = prefixPackage.getChildrenByType(Package.class);
		//check if is same getOwnedPackages()
		EList<Package> layers = null;
		
		String subLayer = null;
		
		//compute the maximum number of layers in a slice
		int max = 0;
		for (Package current : slices) {
			if (current.getChildrenByType(Package.class).size()>=max) {
				layers = current.getChildrenByType(Package.class);
				max = layers.size();
			}
		}
		
		//0 expected Subsystems, return 0.0
		if (max == 0 || layers.size() == 0 || layers == null) {
			relationToCompute.setResultMetric(getMID(), 0.0);
			return;
		}
		Package currentPackage = null;
		for (Type currentElement : classes1) {
			//currentPackage = KDMHelper.getSurroundingPackage(currentElement);
			//.getChildrenByType(Package.class)
			if (currentPackage != null) {
				if (subLayer == null) {
					for (Package slicePackage : slices) {
						if (KDMHelper.computeFullQualifiedName((Commentable) currentPackage).startsWith(KDMHelper.computeFullQualifiedName(slicePackage))) {
							for (Package layerPackage : layers) {
								if (KDMHelper.computeFullQualifiedName(currentPackage).startsWith(KDMHelper.computeFullQualifiedName(slicePackage) + "." + layerPackage.getName())) {
									subLayer = KDMHelper.computeFullQualifiedName(slicePackage) + "." + layerPackage.getName();
									break;
								}
							}
							break;
						}
					}
				} else {
					if (! KDMHelper.computeFullQualifiedName(currentPackage).startsWith(subLayer)) {
						relationToCompute.setResultMetric(getMID(), 0.0);
						return;
					}
				}
			}
		}
		
		for (Type currentElement : classes2) {
			currentPackage = KDMHelper.getSurroundingPackage(currentElement);
			if (currentPackage != null) {
				if (subLayer == null) {
					for (Package slicePackage : slices) {
						if (KDMHelper.computeFullQualifiedName(currentPackage).startsWith(KDMHelper.computeFullQualifiedName(slicePackage))) {
							for (Package layerPackage : layers) {
								if (KDMHelper.computeFullQualifiedName(currentPackage).startsWith(KDMHelper.computeFullQualifiedName(slicePackage) + "." + layerPackage.getName())) {
									subLayer = KDMHelper.computeFullQualifiedName(slicePackage) + "." + layerPackage.getName();
									break;
								}
							}
							break;
						}
					}
				} else {
					if (! KDMHelper.computeFullQualifiedName(currentPackage).startsWith(subLayer)) {
						relationToCompute.setResultMetric(getMID(), 0.0);
						return;
					}
				}
			}

		}
        sliceArchitectureMetric.computeDirected(relationToCompute);
        assert relationToCompute.getResult().containsKey(sliceArchitectureMetric.getMID());
        final double slaq = relationToCompute.getResult().get(sliceArchitectureMetric.getMID());
        relationToCompute.setResultMetric(getMID(), slaq);
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
	private Package computePrefix (Set<Type> elements1, Set<Type> elements2) {
		
		Package prefix = null;
		
		LinkedList<Type> elementsLeft = new LinkedList<Type>();
		
		elementsLeft.addAll(elements1);
		elementsLeft.addAll(elements2);
		
		java.util.ListIterator<Type> iterator = elementsLeft.listIterator();
		
		while (iterator.hasNext()) {
			Type current = iterator.next();
			if (prefix == null && KDMHelper.getSurroundingPackage(current) != null) {
				prefix = KDMHelper.getSurroundingPackage(current);
			}
			
			if (prefix != null && KDMHelper.getSurroundingPackage(current) != null && 
					!KDMHelper.computeFullQualifiedName(current).startsWith(KDMHelper.computeFullQualifiedName(prefix))) {
				//prefix = prefix.getPackage();
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
