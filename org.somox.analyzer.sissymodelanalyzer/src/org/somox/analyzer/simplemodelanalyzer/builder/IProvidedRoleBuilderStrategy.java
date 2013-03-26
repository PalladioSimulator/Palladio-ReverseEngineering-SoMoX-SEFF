package org.somox.analyzer.simplemodelanalyzer.builder;

import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Interface for strategies assigning / creating interface providing roles
 * for composite components. Actually interface providing roles are created for 
 * previously existing interfaces. This is a builder strategy.
 * @author Klaus Krogmann
 */
public interface IProvidedRoleBuilderStrategy {

	/**
	 * Creates interface providing role(s) for the composite component passed as result.
	 * @param result Composite component for which to create a provided role / roles.
	 */
	public void buildProvidedRole(ComponentImplementingClassesLink result);

}