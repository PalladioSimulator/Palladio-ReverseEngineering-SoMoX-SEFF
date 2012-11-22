package org.somox.analyzer.simplemodelanalyzer.detection.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.somox.filter.EClassBasedFilter;

import de.fzi.gast.accesses.Access;
import de.fzi.gast.accesses.CompositeAccess;
import de.fzi.gast.types.GASTClass;

/**
 * Blacklist-based filtering of access types.
 * @author kelsaka
 *
 */
public class AccessFilter {
	
	private static Logger logger = Logger.getLogger(AccessFilter.class);

	/**
	 * Filters a black list of access types
	 * @param allAccesses
	 * @param blacklistedAccessTypes
	 * @return List of accessed GASTClasses after filtering blacklisted access types 
	 */
	public static List<GASTClass> filterAccessList(
			List<Access> allAccesses, 
			EClassBasedFilter<Access> filter) {
		
		ArrayList<GASTClass> returnAccessedClasses = new ArrayList<GASTClass>();

		for (Access access : filter.filter(allAccesses)) {
			if(access.getAccessedClass() != null) { // composite accesses are not considered
				returnAccessedClasses.add(access.getAccessedClass());
			} else {					
				if(!(access instanceof CompositeAccess)) {
					// not composite access
					logger.warn("found empty access: accessed class null, "+ access.getSissyId());
				}
			}
		}
		
		return returnAccessedClasses;
	}
}
