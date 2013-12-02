package org.somox.metrics.helper;

import java.util.HashMap;
import org.emftext.language.java.types.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



//import de.fzi.gast.types.GASTClass;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;

/**
 * Helper for calculating recursively associated classes for a component.
 * @author Steffen Becker, Klaus Krogmann
 */
public class ComponentToImplementingClassesHelper {

	/**
	 * collects all implementing GAST classes (recursively derived) for 
	 * a {@link ComponentImplementingClassesLink}.
	 */
	private Map<ComponentImplementingClassesLink, Set<Type>> mapOfImplementingClasses;

	/**
	 * Constructs a new helper 
	 */
	public ComponentToImplementingClassesHelper() {
		mapOfImplementingClasses = new HashMap<ComponentImplementingClassesLink, Set<Type>>();		
	}
	
	/**
	 * Calculates a list of implementing classes derived for the input <pre>componentCandidate</pre>.
	 * The list of implementing classes is derived recursively for the component candidate.
	 * @param componentCandidate The component for which to return its implementing classes
	 * @return Implementing classes of this and all sub-components.
	 */
	public synchronized Set<Type> deriveImplementingClasses(ComponentImplementingClassesLink componentCandidate) {
		if(!mapOfImplementingClasses.containsKey(componentCandidate)) {
			
			//removelater
//			if(componentCandidate.getImplementingClasses().get(0).getName().equals("ProductStockItemTableModel")){
//				int i =0;
//			}
			
			Set<Type> classSet = new HashSet<Type>();
			 // Collect our own classes
			 classSet.addAll(componentCandidate.getImplementingClasses());
			 // Collect all implementing classes of all sub components
			 for(ComponentImplementingClassesLink subComponent : componentCandidate.getSubComponents()) {
				 classSet.addAll(deriveImplementingClasses(subComponent));				 
			 }
			 // Add the components provided interfaces class sources to the component, needed for 
			 // metrics like coupling, etc.
			 for (InterfaceSourceCodeLink providedIfLink : componentCandidate.getProvidedInterfaces()) {
				 if (!classSet.contains(providedIfLink.getGastClass())) {
					 classSet.add(providedIfLink.getGastClass());
				 }
			 }
			 if(classSet.size() == 0) {
				 throw new RuntimeException("Component must have associated classes");
			 }
			 mapOfImplementingClasses.put(componentCandidate, classSet);
		}
		Set<Type> result = mapOfImplementingClasses.get(componentCandidate);
		if (result == null) {
			throw new IllegalStateException("The component to class cache did not contain a value which has been asserted to be there. " +
					"There might be a concurrency issue here");
		}
		return result;			
	}

	/**
	 * Collects all implementation classes for the given list of component candidates
	 * @param componentCandidates A list of component candidates for which to collect their implementing classes
	 * @return The set of classes used to implement the list of components
	 */
	public Set<Type> collectAllClasses(
			List<ComponentImplementingClassesLink> componentCandidates) {
		Set<Type> allOtherComponentClasses = new HashSet<Type>();
		for(ComponentImplementingClassesLink classLink : componentCandidates) {
			allOtherComponentClasses.addAll(deriveImplementingClasses(classLink));
		}
		return allOtherComponentClasses;
	}
}
