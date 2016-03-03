package org.somox.analyzer.simplemodelanalyzer.detection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.somox.analyzer.SimpleAnalysisResult;
import org.somox.configuration.AbstractMoxConfiguration;

/**
 * Strategy for cleaning up components, remove potentially initial components, etc.
 *
 * @author Klaus Krogmann
 *
 */
public interface IPostComponentDetectionStrategy {

    /**
     * Used to clean up components, remove potentially initial components, etc.
     *
     * @param detectedComponents
     * @param somoxConfiguration
     * @param analysisResult
     * @param progressMonitor
     */
    public void postComponentDetection(AbstractMoxConfiguration somoxConfiguration, SimpleAnalysisResult analysisResult,
            IProgressMonitor progressMonitor);
}
