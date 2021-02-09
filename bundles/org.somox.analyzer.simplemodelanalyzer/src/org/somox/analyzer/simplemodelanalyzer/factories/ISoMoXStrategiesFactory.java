/**
 *
 */
package org.somox.analyzer.simplemodelanalyzer.factories;

import java.util.List;

import org.somox.analyzer.simplemodelanalyzer.detection.IDetectionStrategy;
import org.somox.analyzer.simplemodelanalyzer.detection.IInitializationStrategy;
import org.somox.analyzer.simplemodelanalyzer.detection.IPostComponentDetectionStrategy;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Abstract factory design pattern used to create all strategies which have an impact on the model
 * extractor using this class.
 *
 * @author Steffen Becker, Klaus Krogmann
 */
public interface ISoMoXStrategiesFactory {

    /**
     * @return The strategy used to detect initial primitive components in the source code. These
     *         initial components are then passed on to the clustering algorithm
     */
    public IInitializationStrategy getInitializationStrategy();

    /**
     * @param components
     * @return The strategy used to detect composite components based on the initial components
     *         found. Usually this strategy is not exchanged and clustering is used by default.
     */
    public IDetectionStrategy getDetectionStrategy(List<ComponentImplementingClassesLink> components);

    /**
     * @return The strategy is used to clean up components, remove potentially initial components,
     *         etc.
     */
    public IPostComponentDetectionStrategy getPostComponentDetectionStrategy();

}
