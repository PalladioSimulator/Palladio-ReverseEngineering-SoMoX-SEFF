package org.somox.configuration;

import java.util.LinkedList;

/**
 * Configurable component which can be asked for the list of required configuration settings.
 *
 * @author Benjamin Klatt
 */
public interface ConfigurableComponent {

    /** Get the list of configuration definitions */
    public LinkedList<ConfigurationDefinition> getConfigurationDefinitions();

}
