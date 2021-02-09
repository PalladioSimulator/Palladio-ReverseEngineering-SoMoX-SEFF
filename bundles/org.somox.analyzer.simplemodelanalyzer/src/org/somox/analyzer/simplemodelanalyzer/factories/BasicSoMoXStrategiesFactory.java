/**
 *
 */
package org.somox.analyzer.simplemodelanalyzer.factories;

import java.util.List;

import org.apache.log4j.Logger;
import org.somox.analyzer.simplemodelanalyzer.detection.ComponentDetectionByClustering;
import org.somox.analyzer.simplemodelanalyzer.detection.DeleteInitialComponentCandidatesStrategy;
import org.somox.analyzer.simplemodelanalyzer.detection.DummyPostComponentDetectionStrategy;
import org.somox.analyzer.simplemodelanalyzer.detection.GastToPrimitiveComponentInitializationStrategy;
import org.somox.analyzer.simplemodelanalyzer.detection.IDetectionStrategy;
import org.somox.analyzer.simplemodelanalyzer.detection.IInitializationStrategy;
import org.somox.analyzer.simplemodelanalyzer.detection.IPostComponentDetectionStrategy;
import org.somox.analyzer.simplemodelanalyzer.detection.NoGastClassToPrimitiveComponentInitializationStrategy;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;
//import de.fzi.gast.core.Root;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * @author Snowball, Klaus Krogmann
 *
 */
public class BasicSoMoXStrategiesFactory implements ISoMoXStrategiesFactory {

    private static Logger logger = Logger.getLogger(BasicSoMoXStrategiesFactory.class);

    private Root gastModel = null;
    private SoMoXConfiguration somoxConfiguration = null;

    public BasicSoMoXStrategiesFactory(final Root gastModel, final SoMoXConfiguration somoxConfiguration) {
        super();
        this.gastModel = gastModel;
        this.somoxConfiguration = somoxConfiguration;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.somox.analyzer.simplemodelanalyzer.factories.ISoMoXStrategiesFactory#getDetectionStrategy
     * ()
     */
    @Override
    public IDetectionStrategy getDetectionStrategy(final List<ComponentImplementingClassesLink> components) {
        logger.info("Use Clustering Detection Strategy");
        return new ComponentDetectionByClustering(this.gastModel, components, this.somoxConfiguration);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.analyzer.simplemodelanalyzer.factories.ISoMoXStrategiesFactory#
     * getInitializationStrategy()
     */
    @Override
    public IInitializationStrategy getInitializationStrategy() {
        final boolean gastToPrimitiveComponentStrategy = true; // make configurable via
                                                               // configuration
        if (gastToPrimitiveComponentStrategy) {
            logger.info("GAST to primitive component initialisation strategy.");
            return new GastToPrimitiveComponentInitializationStrategy();
        } else {
            logger.info("GAST classes component initialisation strategy.");
            logger.error("Currently does NOT WORK!.");
            return new NoGastClassToPrimitiveComponentInitializationStrategy();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.analyzer.simplemodelanalyzer.factories.ISoMoXStrategiesFactory#
     * getPostComponentDetectionStrategy()
     */
    @Override
    public IPostComponentDetectionStrategy getPostComponentDetectionStrategy() {
        final boolean PostDetectionStrategyDummy = false; // TODO: make configurable via
                                                          // configuration;
        // should later be false by default
        if (PostDetectionStrategyDummy) {
            return new DummyPostComponentDetectionStrategy();
        } else {
            return new DeleteInitialComponentCandidatesStrategy();
        }
    }
}
