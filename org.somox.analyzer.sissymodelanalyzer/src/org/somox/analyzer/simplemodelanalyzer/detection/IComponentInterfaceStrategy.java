package org.somox.analyzer.simplemodelanalyzer.detection;

import org.emftext.language.java.types.Type;

//import de.fzi.gast.types.GASTClass;

/**
 * Recognition strategy for identifying classes as potential component
 * interfaces. This strategy decides for a class whether it can
 * be considered as a interface. For Java interfaces this seems to be straight
 * forward, but it is not for C/C++ code where interface are not a core
 * language concept. 
 * <br>
 * This strategy is NOT responsible for assigning interfaces to components
 * (provided or required role/port)
 * @author Klaus Krogmann
 *
 */
public interface IComponentInterfaceStrategy {

	/**
	 * Checks whether a class is interpreted as a component
	 * interface.
	 * @param classToCheck The class to check
	 * @return true if considered as an interface; false else
	 */
	public abstract boolean isComponentInterface(Type classToCheck);

}