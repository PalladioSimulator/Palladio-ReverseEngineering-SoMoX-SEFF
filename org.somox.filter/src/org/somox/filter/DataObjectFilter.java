package org.somox.filter;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.somox.kdmhelper.KDMHelper;

//import de.fzi.gast.functions.Method;
//import de.fzi.gast.types.GASTClass;
//import de.fzi.gast.types.Visibilities;

/**
 * Filter all classes which are only data classes, i.e., all public methods are only 
 * getter and setter methods
 * 
 * @author Steffen Becker
 */
public class DataObjectFilter extends BaseFilter<Type> {

	private final static Logger logger = Logger.getLogger(DataObjectFilter.class);
	
	@Override
	public boolean passes(Type object) {
		if(object instanceof EnumDeclaration){//REALLYADDED
			return true;                      //REALLYADDED
		}                                     //REALLYADDED
		List<MethodDeclaration> allMethods = KDMHelper.getMethods(object);
		for (MethodDeclaration m : allMethods) {
			if (KDMHelper.isModifierOfKind(m, VisibilityKind.PUBLIC)) {
				if (isNotGetterOrSetter(m)) {
					return true;
				}
			}
		}
		logger.debug("Removed GAST Class "+object.getName()+" from component candidates as it is a datatype");
		return false;
	}
	
	private final String[] filteredPrefixes = new String[]{
			"is", "get", "set", "equals", "hashcode"
	};
	
	private boolean isNotGetterOrSetter(MethodDeclaration m) {
		boolean result = false;
		String simpleMethodName = m.getName().toLowerCase();
		for (String prefix : filteredPrefixes) {
			result |= simpleMethodName.startsWith(prefix);
		}
		return !result;
	}

}
