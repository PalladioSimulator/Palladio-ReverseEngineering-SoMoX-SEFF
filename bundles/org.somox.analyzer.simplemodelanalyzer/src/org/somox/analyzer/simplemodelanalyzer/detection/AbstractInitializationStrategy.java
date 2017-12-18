package org.somox.analyzer.simplemodelanalyzer.detection;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Method;
import org.somox.filter.BaseFilter;
import org.somox.filter.DataObjectFilter;
import org.somox.kdmhelper.KDMHelper;

//import de.fzi.gast.functions.Method;
//import de.fzi.gast.types.GASTClass;
//import de.fzi.gast.types.GASTStruct;

/**
 * Abstract class for {@link IInitializationStrategy} providing default class filters.
 *
 * @author Klaus Krogmann
 *
 */
public abstract class AbstractInitializationStrategy implements IInitializationStrategy {

    protected static final BaseFilter<ConcreteClassifier> primitiveClassesFilter = new BaseFilter<ConcreteClassifier>() {
        @Override
        public boolean passes(final ConcreteClassifier object) {
            return !KDMHelper.isPrimitive(object);
        }
    };
    /**
     * Filter invalid classes provided by SISSy
     */
    protected static final BaseFilter<ConcreteClassifier> unknownClassTypeFilter = new BaseFilter<ConcreteClassifier>() {
        @Override
        public boolean passes(final ConcreteClassifier object) {
            return !KDMHelper.getName(object).equals("<unknownClassType>");
        }
    };
    protected static final BaseFilter<ConcreteClassifier> improperStructFilter = new BaseFilter<ConcreteClassifier>() {
        @Override
        // Checks whether the class type is a Struct, that actually should be seen as a Class,
        // because it has virtual methods.
        public boolean passes(final ConcreteClassifier object) {
            return true;// ! ( hasVirtualMethod(object));//SOMOXTODOCHANGE (object instanceof
            // GASTStruct) && removed
        }

        /**
         * Checks whether the class has virtual methods.
         */
        private boolean hasVirtualMethod(final ConcreteClassifier clazz) {
            boolean hasVirtualMethod = false;
            for (final Method method : KDMHelper.getMethods(clazz)) {
                if (KDMHelper.isVirtual(method)) {
                    hasVirtualMethod = true;
                    break;
                }
            }

            return hasVirtualMethod;
        }
    };
    protected static final BaseFilter<ConcreteClassifier> dataObjectFilter = new DataObjectFilter();

    public AbstractInitializationStrategy() {
        super();
    }

}