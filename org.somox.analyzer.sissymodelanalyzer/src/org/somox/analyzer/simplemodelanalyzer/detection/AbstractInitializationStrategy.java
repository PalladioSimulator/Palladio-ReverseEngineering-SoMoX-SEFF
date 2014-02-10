package org.somox.analyzer.simplemodelanalyzer.detection;


import org.emftext.language.java.members.Method;
import org.emftext.language.java.types.Type;
import org.somox.filter.BaseFilter;
import org.somox.filter.DataObjectFilter;
import org.somox.kdmhelper.KDMHelper;

//import de.fzi.gast.functions.Method;
//import de.fzi.gast.types.GASTClass;
//import de.fzi.gast.types.GASTStruct;

/**
 * Abstract class for {@link IInitializationStrategy} providing default class filters.
 * @author Klaus Krogmann
 *
 */
public abstract class AbstractInitializationStrategy implements IInitializationStrategy {

	protected static final BaseFilter<Type> primitiveClassesFilter = new BaseFilter<Type>() {
			@Override
			public boolean passes(Type object) {
				return ! KDMHelper.isPrimitive(object);
			}
		};
	/**
	 * Filter invalid classes provided by SISSy
	 */
	protected static final BaseFilter<Type> unknownClassTypeFilter = new BaseFilter<Type>() {
			@Override
			public boolean passes(Type object) {			 
				return !  KDMHelper.getName(object).equals("<unknownClassType>");
			}
		};
	protected static final BaseFilter<Type> improperStructFilter = new BaseFilter<Type>() {
			@Override
			// 	Checks whether the class type is a Struct, that actually should be seen as a Class, because it has virtual methods.
			public boolean passes(Type object) {
				return true;//! ( hasVirtualMethod(object));//SOMOXTODOCHANGE (object instanceof GASTStruct) && removed
			}
	
			/**
			 * Checks whether the class has virtual methods.
			 */
			private boolean hasVirtualMethod(Type clazz) {
				boolean hasVirtualMethod = false;
				for (Method method : KDMHelper.getMethods(clazz)) {
					if (KDMHelper.isVirtual(method)) {
						hasVirtualMethod = true;
						break;
					} 
				}
				
				return hasVirtualMethod;
			}
		};
	protected static final BaseFilter<Type> dataObjectFilter = new DataObjectFilter();

	public AbstractInitializationStrategy() {
		super();
	}

}