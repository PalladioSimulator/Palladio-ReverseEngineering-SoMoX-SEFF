package org.somox.metrics.util;


import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.emftext.language.java.types.Type;
import org.jgrapht.DirectedGraph;
import org.somox.filter.BaseFilter;
import org.somox.filter.NotFilter;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.helper.ClassAccessGraphEdge;
import org.somox.metrics.helper.SourceClassEdgeFilter;
import org.somox.metrics.helper.TargetClassEdgeFilter;

//import de.fzi.gast.types.GASTClass;

/**
 * Class used to encapsulate all computations based on the number
 * of accesses between two pairwise GASTClasses
 * 
 * @author Steffen Becker
 */
public class AccessCacheGraph {

	/**
	 * Logger of this class 
	 */
	private Logger logger = Logger.getLogger(AccessCacheGraph.class);
	
	/**
	 * A graph serving as cache for the number of accesses from each 
	 * GASTClass to each other GASTClass
	 */
	private final DirectedGraph<Type, ClassAccessGraphEdge> accessGraph;

	/**
	 * Constructor of the access cache
	 * @param accessGraph A graph containing as nodes
	 * all GASTClasses considered in the Somox run and 
	 * as edges the number of directed accesses between the
	 * two connected classes 
	 */
	public AccessCacheGraph(
			DirectedGraph<Type, ClassAccessGraphEdge> accessGraph) {
		super();
		this.accessGraph = accessGraph;
	}

	/**
	 * Counts all directed accesses from any of the source classes to any of the
	 * target classes. (Does not consider inverse accesses.) 
	 * @param sourceClasses A set of source classes (nodes in the cache graph)
	 * @param targetClasses A set of target classes (nodes in the cache graph)
	 * @return count of accesses The number of total accesses from sourceClasses to targetClasses
	 */
	public long calculateNumberOfAccessesToClassesInSet(
			Set<Type> sourceClasses,
			Set<Type> targetClasses) {
		
		if (sourceClasses == null || targetClasses == null)
			throw new IllegalArgumentException("Source or target classes must not be null");
		
		TargetClassEdgeFilter filter = new TargetClassEdgeFilter(targetClasses);
		return getNumberOfFilteredOutgoingAccesses(sourceClasses, filter);
	}

	/**
	 * Compute the number of accesses coming from any class outside and going
	 * to the set of classes given 
	 * @param sourceClasses A set of classes for which the incoming accesses are counted
	 * @return The total number of incoming accesses to the set of classes
	 */
	public long calculateNumberOfIncommingAccesses(
			Set<Type> sourceClasses) {
	
		BaseFilter<ClassAccessGraphEdge> filter = 
			new NotFilter<ClassAccessGraphEdge>(new SourceClassEdgeFilter(sourceClasses));		
	
		long result = getNumberOfFilteredIncomingAccesses(sourceClasses, filter);
		
		return result;
	}

	/**
	 * Calculates the number of outgoing accesses of any class
	 * in <pre>sourceClasses</pre> to any class not contained in source classes.
	 * @param sourceClasses The set of classes
	 * @return Count of accesses
	 */
	public long calculateNumberOfExternalAccesses(
			Set<Type> sourceClasses) {
	
		BaseFilter<ClassAccessGraphEdge> filter = 
			new NotFilter<ClassAccessGraphEdge>(new TargetClassEdgeFilter(sourceClasses));		
	
		long result = getNumberOfFilteredOutgoingAccesses(sourceClasses, filter);
		
		if (logger.isDebugEnabled()) {
			Set<Type> otherClasses = substractSet(this.accessGraph.vertexSet(),sourceClasses);
			assert result == calculateNumberOfAccessesToClassesInSet(sourceClasses, otherClasses);
		}
		
		return result;	
	}

	/**
	 * Calculates the number of inner accesses of any class
	 * in <pre>sourceClasses</pre> to any class contained in source classes.
	 * @param sourceClasses
	 * @return Count of accesses
	 */
	public long calculateNumberOfInternalAccesses(
			Set<Type> sourceClasses) {

		BaseFilter<ClassAccessGraphEdge> filter = 
			new TargetClassEdgeFilter (sourceClasses);		

		long result = getNumberOfFilteredOutgoingAccesses(sourceClasses, filter);
		
		if (logger.isDebugEnabled()) {
			assert result == calculateNumberOfAccessesToClassesInSet(sourceClasses, sourceClasses);
		}
		
		return result;
	}
	
	/**
	 * Computes source - setToRemove
	 * @param source Source Set
	 * @param setToRemove Elements to be removed from source set
	 * @return source - setToRemove
	 */
	private Set<Type> substractSet(Set<Type> source, Set<Type> setToRemove) {
		Set<Type> otherClasses = new HashSet<Type>();
		for (Type clazz : source) {
			if (!setToRemove.contains(clazz)){
				otherClasses.add(clazz);
			}
		}
		return otherClasses;
	}

	private long getNumberOfFilteredOutgoingAccesses(
			Set<Type> sourceClasses,
			BaseFilter<ClassAccessGraphEdge> filter) {
		//removelater
//		Helper.writeToFile("interfacecount.txt", "start");
		if (sourceClasses == null)
			throw new IllegalArgumentException("Source classes must not be null.");
		
		long numberOfReferences = 0;
		for(Type clazz : sourceClasses) {
			try {
				if(clazz.toString().startsWith("I")) {
					int i = 0; // debug
				}

				if(!this.accessGraph.vertexSet().contains(clazz)) {
					int i = 0; // debug
				}
				assert this.accessGraph.vertexSet().contains(clazz);
				if (this.accessGraph.outDegreeOf(clazz) > 0) {
					for(ClassAccessGraphEdge edge : filter.filter(this.accessGraph.outgoingEdgesOf(clazz))) {
						numberOfReferences += edge.getCount();
						//removelater
//						Helper.writeToFile("interfacecount.txt", GASTClassHelper.computeFullQualifiedName(edge.getTargetClazz()) + " " + edge.getCount());
					}
				}
			} catch(IllegalArgumentException e) {
				// if edge of type clazz is not present, an illegal argument
				// exception is thrown
				throw new RuntimeException("This should never happen as outDegree was > 0",e);
			}
		}
		//removelater
//		Helper.writeToFile("interfacecount.txt", "end");

		return numberOfReferences;
	}	

	private long getNumberOfFilteredIncomingAccesses(
			Set<Type> sourceClasses,
			BaseFilter<ClassAccessGraphEdge> filter) {
		long numberOfReferences = 0;
		for(Type clazz : sourceClasses) {
			try {
				assert this.accessGraph.vertexSet().contains(clazz);
				if (this.accessGraph.inDegreeOf(clazz) > 0) {
					for(ClassAccessGraphEdge edge : filter.filter(this.accessGraph.incomingEdgesOf(clazz))) {
						numberOfReferences += edge.getCount(); 
					}
				}
			} catch(IllegalArgumentException e) {
				// if edge of type clazz is not present, an illegal argument
				// exception is thrown: do nothing
				throw new RuntimeException("This should never happen as outDegree was > 0",e);
			}
		}
		return numberOfReferences;
	}	
}
