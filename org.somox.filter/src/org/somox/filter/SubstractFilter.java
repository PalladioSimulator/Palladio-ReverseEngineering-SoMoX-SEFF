package org.somox.filter;

import java.util.Set;


import de.fzi.gast.types.GASTClass;

/**
 * Removes one set from another set.
 *
 */
public class SubstractFilter extends BaseFilter<GASTClass> {

	private Set<GASTClass> classesToRemove;
	
	public SubstractFilter(Set<GASTClass> classesToRemove) {
		super();
		this.classesToRemove = classesToRemove;
	}

	@Override
	public boolean passes(GASTClass object) {
		return !classesToRemove.contains(object);		
	}
	

}
