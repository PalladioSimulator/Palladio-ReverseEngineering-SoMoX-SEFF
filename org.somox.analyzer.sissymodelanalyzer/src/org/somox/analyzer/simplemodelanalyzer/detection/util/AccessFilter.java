package org.somox.analyzer.simplemodelanalyzer.detection.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.somox.filter.EClassBasedFilter;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.GetAccessedType;

//import de.fzi.gast.accesses.Access;
//import de.fzi.gast.accesses.CompositeAccess;
//import de.fzi.gast.types.GASTClass;

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
	public static List<Type> filterAccessList(
			List<ASTNode> allAccesses,
			EClassBasedFilter<ASTNode> filter) {
		
		ArrayList<Type> returnAccessedClasses = new ArrayList<Type>();

		for (ASTNode access : filter.filter(allAccesses)) {
			
			
			if(access instanceof TypeAccess){//REALLYADDED//SOMOXTODOCHANGE ugly hack, should be an own filter, filterAccessList is referenced only once
				if(KDMHelper.isInheritanceTypeAccess((TypeAccess) access)){//REALLYADDED//SOMOXTODOCHANGE
					//logger.warn("found Inheritance type access, will not be considered");
					continue;//REALLYADDED//SOMOXTODOCHANGE
				}//REALLYADDED//SOMOXTODOCHANGE
			}//REALLYADDED//SOMOXTODOCHANGE
			
			if(GetAccessedType.getAccessedType(access) != null) { // composite accesses are not considered
				returnAccessedClasses.add(GetAccessedType.getAccessedType(access));
			} else {					
				logger.warn("found empty access: accessed class null, "+ KDMHelper.getSISSyID(access));
//				//removelater
//				if(access instanceof ClassInstanceCreation){
//					System.out.println();
//				}
//				if(access instanceof SingleVariableAccess){
//					System.out.println();
//				}
				
//				if(!(access instanceof CompositeAccess)) { //SOMOXTODOCHANGE CompositeAccess has no equivalent in MoDisco Java
//					// not composite access
//				}
			}
		}
		
		return returnAccessedClasses;
	}
}
