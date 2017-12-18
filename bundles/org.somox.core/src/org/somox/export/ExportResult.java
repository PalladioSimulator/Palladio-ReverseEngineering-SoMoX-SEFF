package org.somox.export;

import java.util.List;

import org.somox.analyzer.AnalysisResult;
import org.somox.common.Message;

/**
 * The result of an extraction process.
 *
 * This result object contains all information about the last export
 *
 * @author Benjamin Klatt
 *
 */
public interface ExportResult {

    // ---------------------------------
    // Static Data Fields
    // ---------------------------------

    /**
     * The result status of the export step
     */
    public enum ResultStatus {
        /**
         * @uml.property name="nOT_EXECUTED"
         * @uml.associationEnd
         */
        NOT_EXECUTED,
        /**
         * @uml.property name="sUCCESS"
         * @uml.associationEnd
         */
        SUCCESS,
        /**
         * @uml.property name="fAILED"
         * @uml.associationEnd
         */
        FAILED
    }

    // ---------------------------------
    // Business Methods
    // ---------------------------------

    // ---------------------------------
    // Getters / Setters
    // ---------------------------------

    /**
     * The result status of the export. Will be one of the constants AnalysisResult.ResultStatus
     *
     * @return The status of the export
     */
    public AnalysisResult.ResultStatus getResultStatus();

    /**
     * Get the Exporter that was executed
     *
     * @return The exporter that was executed
     */
    public Exporter getModelAnalyzer();

    /**
     * Add a message object to the result
     *
     * @param message
     *            The message object
     */
    public void addMessage(Message message);

    /**
     * Get a list of all message objects assigned to this result
     *
     * @return List of all assigned message objects
     */
    public List<Message> getMessages();
}
