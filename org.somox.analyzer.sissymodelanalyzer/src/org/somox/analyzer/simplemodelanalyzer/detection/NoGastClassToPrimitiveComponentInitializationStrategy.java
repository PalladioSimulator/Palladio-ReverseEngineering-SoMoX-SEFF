package org.somox.analyzer.simplemodelanalyzer.detection;

import java.util.ArrayList;
import java.util.List;

import org.somox.analyzer.simplemodelanalyzer.builder.ComponentBuilder;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.filter.ComposedFilter;
import org.somox.filter.EClassBasedFilter;

import de.fzi.gast.core.Root;
import de.fzi.gast.types.GASTClass;
import de.fzi.gast.types.typesPackage;
import eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Initialization class which only lists a filtered set of classes which potentially can become a component. 
 * Does NOT create a SAMM primitive components only the component link of the source code decorator. 
 * @author Klaus Krogmann
 *
 */
public class NoGastClassToPrimitiveComponentInitializationStrategy extends AbstractInitializationStrategy {	
	
	/**
	 * Only creates a component link for GAST classes and the set of all inner, i.e., nested, classes
	 * Real structs, unions and enumerations are omitted. Does not create a SAMM primitive component
	 */
	@SuppressWarnings("unchecked")
	public List<ComponentImplementingClassesLink> createInitialComponentCandidates(Root root, SoMoXConfiguration config, ComponentBuilder builder) {
		List<ComponentImplementingClassesLink> result = new ArrayList<ComponentImplementingClassesLink>();
		List<GASTClass> classList = root.getAllNormalClasses();
		
		ComposedFilter<GASTClass> gastClassFilter = new ComposedFilter<GASTClass>(
				config.getBlacklistFilter(),
				new EClassBasedFilter<GASTClass>(
						typesPackage.eINSTANCE.getGASTEnumeration(),
						typesPackage.eINSTANCE.getGASTUnion()),
				primitiveClassesFilter,
				improperStructFilter,
				dataObjectFilter,
				unknownClassTypeFilter);

		for (GASTClass clazz : gastClassFilter.filter(classList)){
			// Attention: does only create the component links but not the SAMM primitive component:
			ComponentImplementingClassesLink newPrimitiveComponent = builder.createComponentLinkFromGASTClass(clazz);
			result.add(newPrimitiveComponent);
		}
		
		return result;
	}
}
