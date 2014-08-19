package org.somox.filter;

import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.somox.kdmhelper.KDMHelper;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.types.*;
//import de.fzi.gast.types.GASTClass;

public class BlacklistFilter extends BaseFilter<Type> {

	private static Logger logger = Logger.getLogger(BlacklistFilter.class);
	
	private Pattern matchPattern = null;

	public BlacklistFilter(Set<String> blacklist){
		super();
		
		this.matchPattern = deriveMatchPattern(blacklist);
	}
	
	@Override
	public boolean passes(Type object) {
		return !classMatchesBlacklist(object);
	}

	/**
	 * Compile a sinlge {@link Pattern} containing all elements of the blacklist
	 * @param blacklist The list of blacklist patterns
	 * @return A corresponding {@link Pattern} used to match component FQNs
	 */
	private static Pattern deriveMatchPattern(Set<String> blacklist) {
		StringBuffer sw = new StringBuffer();
		for (String s : blacklist) {
			sw.append(s);
			sw.append("|");
		}
		if (sw.length() > 0) sw.deleteCharAt(sw.length()-1);
		Pattern matchPattern = Pattern.compile(sw.toString(),Pattern.CASE_INSENSITIVE);
		
		logger.debug("Initialised Blacklist filter with pattern "+matchPattern.toString());
		
		return matchPattern;
	}

	/** Uses Regular Expressions to match FQNs of components
	 * @param matchPattern The pattern to match against
	 * @param currentClass The class of the component
	 * @return true if the FQN of the class matches the given pattern
	 */
	private boolean classMatchesBlacklist(Type currentClass) {

		boolean result = false;

		// use the full qualified name of the container 
		// which is either a package or a class(in case of an inner class)
		// anyway, both are ASTNodes
		EObject container = currentClass.eContainer();
		if(container instanceof Member) {
			if (container instanceof ClassMethod) {
				container = container.eContainer();
			}
			String fqn = KDMHelper.computeFullQualifiedName((Type) container);
			result = matchPattern.matcher(fqn).matches();
			if (logger.isTraceEnabled()) {
				logger.trace("Blacklist filter matches " + fqn + ": " + result);
			}
		}
		return result;
	}
	
}
