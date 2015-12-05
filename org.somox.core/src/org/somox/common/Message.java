package org.somox.common;

/**
 * This class provides a data object for message encapsulation. It can be used to create more
 * structures messages especially if messages with variable levels have to be supported.
 *
 * @author Benjamin Klatt
 */
public class Message {

    // ---------------------------------
    // Static Data Fields
    // ---------------------------------

    /**
     * The possible levels of a message
     */
    public enum MessageLevel {
        /**
         * @uml.property name="dEBUG"
         * @uml.associationEnd
         */
        DEBUG,
        /**
         * @uml.property name="iNFO"
         * @uml.associationEnd
         */
        INFO,
        /**
         * @uml.property name="wARN"
         * @uml.associationEnd
         */
        WARN,
        /**
         * @uml.property name="eRROR"
         * @uml.associationEnd
         */
        ERROR
    }

    // ---------------------------------
    // Data fields
    // ---------------------------------

    /**
     * The level of the message
     *
     * @uml.property name="level"
     * @uml.associationEnd
     */
    private Message.MessageLevel level = MessageLevel.INFO;

    /**
     * The message itself
     *
     * @uml.property name="message"
     */
    private String message = null;

    /**
     * An optional throwable to be assigned to the message. This is useful especially for error or
     * debug messages.
     *
     * @uml.property name="throwable"
     */
    private Throwable throwable = null;

    // ---------------------------------
    // Constructor
    // ---------------------------------

    /**
     * Constructor requiring all information
     */
    public Message(final Message.MessageLevel level, final String message, final Throwable throwable) {
        this.level = level;
        this.message = message;
        this.throwable = throwable;
    }

    /**
     * Constructor requiring common information
     */
    public Message(final Message.MessageLevel level, final String message) {
        this.level = level;
        this.message = message;
    }

    /**
     * Constructor requiring minimum information. This will result in an info message.
     */
    public Message(final String message) {
        this.message = message;
    }

    // ---------------------------------
    // Business Methods
    // ---------------------------------

    // ---------------------------------
    // Helper Methods
    // ---------------------------------

    // ---------------------------------
    // Getters / Setters
    // ---------------------------------

    /**
     * @param message
     *            the message to set
     * @uml.property name="message"
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return the message
     * @uml.property name="message"
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param level
     *            the level to set
     * @uml.property name="level"
     */
    public void setLevel(final Message.MessageLevel level) {
        this.level = level;
    }

    /**
     * @return the level
     * @uml.property name="level"
     */
    public Message.MessageLevel getLevel() {
        return this.level;
    }

    /**
     * @param throwable
     *            the throwable to set
     * @uml.property name="throwable"
     */
    public void setThrowable(final Throwable throwable) {
        this.throwable = throwable;
    }

    /**
     * @return the throwable
     * @uml.property name="throwable"
     */
    public Throwable getThrowable() {
        return this.throwable;
    }
}
