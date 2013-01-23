package org.somox.analyzer.simplemodelanalyzer.builder;

import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Interface for strategies assigning / creating provided interfaces
 * for composite components. Actually interface ports are created for 
 * previously existing interfaces. This is a builder strategy.
 * @author Klaus Krogmann
 */
public interface IInterfacePortBuilderStrategy {

	/**
	 * Creates provided interface(s) (interfaces port(s)) for the composite component
	 * passed as result.
	 * @param result Composite component for which to create a provided interface / provided interfaces.
	 */
	public void buildInterfacePort(ComponentImplementingClassesLink result);

}