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
 * Initialization class for the conversion from GAST class to component. 
 * <br>
 * Classes are extracted from the Resource and "bundled" with their inner classes. 
 * Each such bundle immediately becomes a primitive component.
 * 
 * @author Steffen Becker, Johannes Stammel, Grischa Liebel, Klaus Krogmann
 *
 */
public class GastToPrimitiveComponentInitializationStrategy extends AbstractInitializationStrategy {	
	
	/**
	 * Create an initial list of component candidates by following a simple heuristic: each GASTClass is 
	 * a component. Its implementation consists of the GAST class itself and the set of all inner, i.e., nested, classes
	 * Real structs, unions and enumerations are omitted.
	 */
	public List<ComponentImplementingClassesLink> createInitialComponentCandidates(Root root, SoMoXConfiguration config, ComponentBuilder builder) {
		List<ComponentImplementingClassesLink> result = new ArrayList<ComponentImplementingClassesLink>();
		List<GASTClass> classList = root.getAllNormalClasses();
		

		for (GASTClass clazz : getFilter(config).filter(classList)){ 
			ComponentImplementingClassesLink newPrimitiveComponent = builder.createPrimitiveComponentFromGASTClass(clazz);
			newPrimitiveComponent.setIsInitialComponent(true);
			result.add(newPrimitiveComponent);
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	private ComposedFilter<GASTClass> getFilter(SoMoXConfiguration config) {
		ComposedFilter<GASTClass> gastClassFilter = new ComposedFilter<GASTClass>(
				config.getBlacklistFilter(),
				new EClassBasedFilter<GASTClass>(
						typesPackage.eINSTANCE.getGASTEnumeration(),
						typesPackage.eINSTANCE.getGASTUnion()),
				primitiveClassesFilter,
				improperStructFilter,
				dataObjectFilter,
				unknownClassTypeFilter);
		
		return gastClassFilter;		
	}	
	
}
