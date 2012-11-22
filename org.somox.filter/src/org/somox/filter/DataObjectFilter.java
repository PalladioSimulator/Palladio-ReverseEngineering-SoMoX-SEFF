package org.somox.filter;

import java.util.List;

import org.apache.log4j.Logger;

import de.fzi.gast.functions.Method;
import de.fzi.gast.types.GASTClass;
import de.fzi.gast.types.Visibilities;

/**
 * Filter all classes which are only data classes, i.e., all public methods are only 
 * getter and setter methods
 * 
 * @author Steffen Becker
 */
public class DataObjectFilter extends BaseFilter<GASTClass> {

	private final static Logger logger = Logger.getLogger(DataObjectFilter.class);
	
	@Override
	public boolean passes(GASTClass object) {
		List<Method> allMethods = object.getMethods();
		for (Method m : allMethods) {
			if (m.getVisibility() == Visibilities.VISIBILITYPUBLIC) {
				if (isNotGetterOrSetter(m)) {
					return true;
				}
			}
		}
		logger.debug("Removed GAST Class "+object.getSimpleName()+" from component candidates as it is a datatype");
		return false;
	}
	
	private final String[] filteredPrefixes = new String[]{
			"is", "get", "set", "equals", "hashcode"
	};
	
	private boolean isNotGetterOrSetter(Method m) {
		boolean result = false;
		String simpleMethodName = m.getSimpleName().toLowerCase();
		for (String prefix : filteredPrefixes) {
			result |= simpleMethodName.startsWith(prefix);
		}
		return !result;
	}

}
