package org.somox.metrics.helper;

import java.util.Set;

import org.emftext.language.java.*;
import org.emftext.language.java.types.Type;
import org.somox.filter.BaseFilter;

//import de.fzi.gast.types.GASTClass;

public class SourceClassEdgeFilter extends BaseFilter<ClassAccessGraphEdge> { 
 
	private Set<Type> filteredTarget;
	
	/**
	 * 
	 * @param filteredTarget positive list of non-filtered target class accesses
	 */
	public SourceClassEdgeFilter(Set<Type> filteredTarget) {
		this.filteredTarget = filteredTarget;
	}
	
	@Override
	public boolean passes(ClassAccessGraphEdge object) {
		return filteredTarget.contains(object.getSourceClazz());
	}
		
}
