package org.somox.export;

import java.util.HashMap;

import org.palladiosimulator.pcm.repository.Repository;
import org.somox.configuration.ConfigurableComponent;

/**
 * Interface for model exporter that are respoonsible to produce the target software architecture
 * model out of the internal software architecture model.
 *
 * @author Benjamin Klatt
 *
 */
public interface Exporter extends ConfigurableComponent {

    /**
     * initialize the analyzer
     */
    public void init();

    /**
     * Execute the analyzer
     *
     * @param preferences
     *            The preferences for this analyzer
     * @param internalArchitectureModel
     *            The internal architecture model represented through a Q-IMPREss repository
     * @param extractionResultMap
     *            The map of extraction results
     * @return The analysis result object
     */
    public ExportResult export(HashMap<String, String> preferences, Repository internalArchitectureModel);

}
