package org.somox.analyzer;

import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.extractor.ExtractionResult;

/**
 * Interface for Analyzer extensions to be accessed from the SoMoX core
 *
 * @author Benjamin Klatt
 *
 */
public interface ModelAnalyzer {

    /**
     * The result status of the model analyzer
     */
    public enum Status {
        /**
         * @uml.property name="rEADY"
         * @uml.associationEnd
         */
        READY,
        /**
         * @uml.property name="rUNNING"
         * @uml.associationEnd
         */
        RUNNING,
        /**
         * @uml.property name="fINISHED"
         * @uml.associationEnd
         */
        FINISHED,
        /**
         * @uml.property name="wAITING"
         * @uml.associationEnd
         */
        WAITING
    }

    /**
     * initialize the analyzer
     */
    public void init();

    /**
     * Execute the analyzer
     *
     * @param preferences
     *            The preferences for this analyzer
     * @param somoxConfiguration
     *            Configuration of this analyzer
     * @param extractionResultMap
     *            The map of extraction results
     * @return The analysis result object
     */
    public AnalysisResult analyze(SoMoXConfiguration somoxConfiguration,
            HashMap<String, ExtractionResult> extractionResultMap, IProgressMonitor progressMonitor)
                    throws ModelAnalyzerException;

    /**
     * Get the status from the analyzer
     *
     * @return The current status of the analyzer. Has to be one of the defined status in this
     *         interface
     */
    public ModelAnalyzer.Status getStatus();
}
