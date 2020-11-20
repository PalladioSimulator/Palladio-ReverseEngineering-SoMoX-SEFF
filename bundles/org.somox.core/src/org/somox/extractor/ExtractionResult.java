package org.somox.extractor;

import java.util.List;

import org.somox.common.Message;

/**
 * The result of an extraction process
 *
 * @author Benjamin Klatt
 *
 */
public interface ExtractionResult {

    // ---------------------------------
    // Static Data Fields
    // ---------------------------------

    /**
     * The status of the extraction result
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
     * The result status of the extraction. Will be one of the constants
     *
     * @return
     */
    public ExtractionResult.ResultStatus getResultStatus();

    /**
     * Get the Software Extractor that was performed
     *
     * @return
     */
    public SoftwareExtractor getSoftwareExtractor();

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
