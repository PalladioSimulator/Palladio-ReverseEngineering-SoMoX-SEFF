package org.somox.configuration;

import java.util.LinkedList;
import java.util.List;

/**
 * Configuration Object defining a configuration Element
 *
 * @author Benjamin Klatt
 */
public class ConfigurationDefinition {

    // ---------------------------------
    // Static Data Fields
    // ---------------------------------

    /**
     * The available types for configuration fields
     */
    public enum Type {
        /**
         * @uml.property name="dIRECTORY"
         * @uml.associationEnd
         */
        DIRECTORY,
        /**
         * @uml.property name="sTRING"
         * @uml.associationEnd
         */
        STRING,
        /**
         * @uml.property name="bOOLEAN"
         * @uml.associationEnd
         */
        BOOLEAN,
        /**
         * @uml.property name="cHOICE"
         * @uml.associationEnd
         */
        CHOICE
    }

    /** Indicator for an unlimited multiplicity */
    public static int MULIPLICITY_UNLIMITED = -1;

    // ---------------------------------
    // Data fields
    // ---------------------------------

    /**
     * The identifier for this configuration item
     *
     * @uml.property name="id"
     */
    private String id = null;

    /**
     * The name for this configuration item
     *
     * @uml.property name="name"
     */
    private String name = null;

    /**
     * The type for this configuration item
     *
     * @uml.property name="type"
     * @uml.associationEnd
     */
    private ConfigurationDefinition.Type type = null;

    /**
     * The default value for this configuration item
     *
     * @uml.property name="defaultValue"
     */
    private String defaultValue = null;

    /**
     * The list of possibleValues
     *
     * @uml.property name="possibleValues"
     */
    private List<String> possibleValues = new LinkedList<String>();

    /**
     * The number of allowed possibleValues. A value equals to
     * ConfigurationDefinition.MULIPLICITY_UNLIMITED means an unlimited number of possibleValues is
     * allowed
     *
     * @uml.property name="multiplicity"
     */
    private int multiplicity = 1;

    /**
     * Flag whether this option is required
     *
     * @uml.property name="required"
     */
    private boolean required = true;

    // ---------------------------------
    // Constructor
    // ---------------------------------

    /**
     * Constructor requiring the complete set of definitions
     *
     * @param id
     *            The identifier for the parameter
     * @param name
     *            The name of the parameter
     * @param type
     *            The type of the parameter
     * @param defaultValue
     *            The defaultValue
     * @param possibleValues
     *            The possible values for this configuration
     * @param multiplicity
     *            The maximum number of possibleValues for this configuration
     * @param required
     *            Flag whether this configuration is required
     */
    public ConfigurationDefinition(final String id, final String name, final ConfigurationDefinition.Type type,
            final String defaultValue, final List<String> possibleValues, final int multiplicity,
            final boolean required) {
        this(id, name, type, defaultValue);
        this.possibleValues = possibleValues;
        this.multiplicity = multiplicity;
        this.setRequired(required);
    }

    /**
     * Constructor requiring the basic information and the default value
     *
     * @param id
     *            The identifier for the parameter
     * @param name
     *            The name of the parameter
     * @param type
     *            The type of the parameter
     * @param defaultValue
     *            The defaultValue
     */
    public ConfigurationDefinition(final String id, final String name, final ConfigurationDefinition.Type type,
            final String defaultValue) {
        this(id, name, type);
        this.defaultValue = defaultValue;
    }

    /**
     * Constructor requiring the basic information and the default value
     *
     * @param id
     *            The identifier for the parameter
     * @param name
     *            The name of the parameter
     * @param type
     *            The type of the parameter
     * @param defaultValue
     *            The defaultValue
     * @param required
     *            Flag whether this configuration is required
     */
    public ConfigurationDefinition(final String id, final String name, final ConfigurationDefinition.Type type,
            final String defaultValue, final boolean required) {
        this(id, name, type, defaultValue);
        this.setRequired(required);
    }

    /**
     * Constructor requiring the minimum of definitions
     *
     * @param id
     *            The identifier for the parameter
     * @param name
     *            The name of the parameter
     * @param type
     *            The type of the parameter
     */
    public ConfigurationDefinition(final String id, final String name, final ConfigurationDefinition.Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
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
     * @return the id
     * @uml.property name="id"
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param id
     *            the id to set
     * @uml.property name="id"
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return the name
     * @uml.property name="name"
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            the name to set
     * @uml.property name="name"
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the type
     * @uml.property name="type"
     */
    public ConfigurationDefinition.Type getType() {
        return this.type;
    }

    /**
     * @param type
     *            the type to set
     * @uml.property name="type"
     */
    public void setType(final ConfigurationDefinition.Type type) {
        this.type = type;
    }

    /**
     * @return the defaultValue
     * @uml.property name="defaultValue"
     */
    public String getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * @param defaultValue
     *            the defaultValue to set
     * @uml.property name="defaultValue"
     */
    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @param possibleValues
     *            the possibleValues to set
     * @uml.property name="possibleValues"
     */
    public void setPossibleValues(final List<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    /**
     * @return the possibleValues
     * @uml.property name="possibleValues"
     */
    public List<String> getPossibleValues() {
        return this.possibleValues;
    }

    /**
     * @param multiplicity
     *            the multiplicity to set
     * @uml.property name="multiplicity"
     */
    public void setMultiplicity(final int multiplicity) {
        this.multiplicity = multiplicity;
    }

    /**
     * @return the multiplicity
     * @uml.property name="multiplicity"
     */
    public int getMultiplicity() {
        return this.multiplicity;
    }

    /**
     * @param required
     *            the required to set
     * @uml.property name="required"
     */
    public void setRequired(final boolean required) {
        this.required = required;
    }

    /**
     * @return the required
     * @uml.property name="required"
     */
    public boolean isRequired() {
        return this.required;
    }
}
