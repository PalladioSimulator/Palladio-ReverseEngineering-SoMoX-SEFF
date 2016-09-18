package org.somox.analyzer.simplemodelanalyzer.detection;

import java.util.ArrayList;
import java.util.List;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.somox.analyzer.simplemodelanalyzer.builder.ComponentBuilder;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.filter.ComposedFilter;
import org.somox.filter.EClassBasedFilter;
import org.somox.kdmhelper.metamodeladdition.Root;
//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;
//import de.fzi.gast.types.typesPackage;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Initialization class for the conversion from GAST class to component. <br>
 * Classes are extracted from the Resource and "bundled" with their inner classes. Each such bundle
 * immediately becomes a primitive component.
 *
 * @author Steffen Becker, Johannes Stammel, Grischa Liebel, Klaus Krogmann
 *
 */
public class GastToPrimitiveComponentInitializationStrategy extends AbstractInitializationStrategy {

    /**
     * Create an initial list of component candidates by following a simple heuristic: each
     * GASTClass is a component. Its implementation consists of the GAST class itself and the set of
     * all inner, i.e., nested, classes Real structs, unions and enumerations are omitted.
     */
    @Override
    public List<ComponentImplementingClassesLink> createInitialComponentCandidates(final Root root,
            final SoMoXConfiguration config, final ComponentBuilder builder) {
        final List<ComponentImplementingClassesLink> result = new ArrayList<ComponentImplementingClassesLink>();
        final List<ConcreteClassifier> classList = root.getNormalClasses();

        // removelater
        // String fileName = "00getNormalClassesPCKDM.txt";
        // for(Type element : classList){
        // org.somox.changetest.Helper.writeToFile(fileName,
        // GASTClassHelper.computeFullQualifiedName(element));
        // }
        // org.somox.changetest.Helper.sortFile(fileName);

        for (final ConcreteClassifier clazz : this.getFilter(config).filter(classList)) {
            final ComponentImplementingClassesLink newPrimitiveComponent = builder
                    .createPrimitiveComponentFromGASTClass(clazz);
            newPrimitiveComponent.setIsInitialComponent(true);
            result.add(newPrimitiveComponent);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private ComposedFilter<ConcreteClassifier> getFilter(final SoMoXConfiguration config) {
        final ComposedFilter<ConcreteClassifier> gastClassFilter = new ComposedFilter<ConcreteClassifier>(
                config.getClassifierFilter(), new EClassBasedFilter<ConcreteClassifier>(
        /* JavaPackage.eINSTANCE.getEnumDeclaration() */), // SOMOXTODOCHANGE removed because
                // old version passes enums
                // /*typesPackage.eINSTANCE.getGASTUnion())*/,//SOMOXTODOCHANGE
                primitiveClassesFilter, improperStructFilter, dataObjectFilter, unknownClassTypeFilter);

        return gastClassFilter;
    }

}
