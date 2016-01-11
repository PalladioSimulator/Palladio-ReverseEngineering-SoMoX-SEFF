package org.somox.analyzer.simplemodelanalyzer.detection;

import java.util.List;

import org.somox.analyzer.simplemodelanalyzer.builder.ComponentBuilder;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;
//import de.fzi.gast.core.Root;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * This strategy judges how to initially handle GAST classes and how or whether at all to convert to
 * components.
 *
 * @author Klaus Krogmann
 *
 */
public interface IInitializationStrategy {
    /**
     * Create an initial list of component candidates by following a simple heuristic: each
     * GASTClass is a component. Its implementation consists of the GAST class itself and the set of
     * all inner, i.e., nested, classes
     *
     * @return A list of initial component candidates. This method guarantees that all components
     *         are {@link IPrimitiveComponentCandidate}s.
     */
    public List<ComponentImplementingClassesLink> createInitialComponentCandidates(Root root, SoMoXConfiguration config,
            ComponentBuilder builder);
}