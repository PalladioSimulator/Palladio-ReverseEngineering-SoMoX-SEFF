package org.somox.filter;

import java.util.Set;

import org.emftext.language.java.types.Type;


//import de.fzi.gast.types.GASTClass;

/**
 * Removes one set from another set.
 *
 */
public class SubstractFilter extends BaseFilter<Type> {

	private Set<Type> classesToRemove;
	
	public SubstractFilter(Set<Type> classesToRemove) {
		super();
		this.classesToRemove = classesToRemove;
	}

	@Override
	public boolean passes(Type object) {
		return !classesToRemove.contains(object);		
	}
	

}
