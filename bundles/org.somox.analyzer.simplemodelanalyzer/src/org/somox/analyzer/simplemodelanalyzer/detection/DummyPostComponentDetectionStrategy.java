package org.somox.analyzer.simplemodelanalyzer.detection;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.somox.analyzer.SimpleAnalysisResult;
import org.somox.configuration.AbstractMoxConfiguration;

/**
 * Dummy with empty behaviour.
 *
 * @author Klaus Krogmann
 *
 */
public class DummyPostComponentDetectionStrategy implements IPostComponentDetectionStrategy {

    /**
     * The logger of this strategy
     */
    private static Logger logger = Logger.getLogger(DummyPostComponentDetectionStrategy.class);

    /*
     * (non-Javadoc)
     *
     * @see org.somox.analyzer.simplemodelanalyzer.detection.IPostComponentDetectionStrategy#
     * postComponentDetection(java.util.List, org.somox.configuration.SoMoXConfiguration)
     */
    @Override
    public void postComponentDetection(final AbstractMoxConfiguration somoxConfiguration,
            final SimpleAnalysisResult analysisResult, final IProgressMonitor progressMonitor) {

        logger.trace("DUMMY Post component detection");

    }

}
