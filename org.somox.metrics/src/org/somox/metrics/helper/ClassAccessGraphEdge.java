package org.somox.metrics.helper;

import de.fzi.gast.types.GASTClass;

/**
 * This class is a struct containing a {@link GASTClass} and a counter. It is used to model an 
 * a link from a node in a graph of classes to the contained class in this structure. The count
 * is the weight of the link. It is used to model the number of links going from the source node
 * to the class in this struct. 
 * @author Steffen Becker
 *
 */
public class ClassAccessGraphEdge {
	private GASTClass sourceClazz = null, targetClazz = null;
	private int count = 0;
	
	public ClassAccessGraphEdge(GASTClass source, GASTClass target, int count) {
		super();
		this.sourceClazz = source;
		this.targetClazz = target;
		this.count = count;
	}
	public ClassAccessGraphEdge(GASTClass source, GASTClass target) {
		this(source,target,0);
	}
	/**
	 * @return the clazz
	 */
	public GASTClass getSourceClazz() {
		return sourceClazz;
	}
	
	public GASTClass getTargetClazz() {
		return targetClazz;
	}
	
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	
	public void incrementCount() {
		this.count++;	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "From: "+sourceClazz.getSimpleName()+" To: "+targetClazz.getSimpleName()+" Count: "+count;
	}
	
	
}
