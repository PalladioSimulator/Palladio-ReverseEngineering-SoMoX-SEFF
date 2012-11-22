package org.somox.analyzer.simplemodelanalyzer.detection;

import org.somox.filter.BaseFilter;
import org.somox.filter.DataObjectFilter;

import de.fzi.gast.functions.Method;
import de.fzi.gast.types.GASTClass;
import de.fzi.gast.types.GASTStruct;

/**
 * Abstract class for {@link IInitializationStrategy} providing default class filters.
 * @author Klaus Krogmann
 *
 */
public abstract class AbstractInitializationStrategy implements IInitializationStrategy {

	protected static final BaseFilter<GASTClass> primitiveClassesFilter = new BaseFilter<GASTClass>() {
			@Override
			public boolean passes(GASTClass object) {
				return !object.isPrimitive();
			}
		};
	/**
	 * Filter invalid classes provided by SISSy
	 */
	protected static final BaseFilter<GASTClass> unknownClassTypeFilter = new BaseFilter<GASTClass>() {
			@Override
			public boolean passes(GASTClass object) {			
				return !object.getSimpleName().equals("<unknownClassType>");
			}
		};
	protected static final BaseFilter<GASTClass> improperStructFilter = new BaseFilter<GASTClass>() {
			@Override
			// 	Checks whether the class type is a Struct, that actually should be seen as a Class, because it has virtual methods.
			public boolean passes(GASTClass object) {
				return ! ((object instanceof GASTStruct) && hasVirtualMethod(object));
			}
	
			/**
			 * Checks whether the class has virtual methods.
			 */
			private boolean hasVirtualMethod(GASTClass clazz) {
				boolean hasVirtualMethod = false;
				for (Method method : clazz.getMethods()) {
					if (method.isVirtual()) {
						hasVirtualMethod = true;
						break;
					} 
				}
				
				return hasVirtualMethod;
			}
		};
	protected static final BaseFilter<GASTClass> dataObjectFilter = new DataObjectFilter();

	public AbstractInitializationStrategy() {
		super();
	}

}