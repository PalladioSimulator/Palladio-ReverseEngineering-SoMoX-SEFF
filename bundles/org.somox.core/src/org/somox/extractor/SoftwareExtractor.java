package org.somox.extractor;

import java.util.HashMap;
import java.util.List;

import org.somox.configuration.ConfigurableComponent;

/**
 * An extractor for general software artifacts.
 *
 * <p>
 * Software extractor instances provide the functionality to execute the extraction process as well
 * as access to the Software Artifact repository
 * </p>
 *
 * @author Benjamin Klatt
 *
 */
public interface SoftwareExtractor extends ConfigurableComponent {

    /**
     * Execute this software extractor
     *
     * @param extractorId
     *            The id of this extractor instance
     * @param preferences
     *            The preferences to run the extractor with
     * @return The result of the extraction process
     */
    public ExtractionResult runExtraction(String extractorId, HashMap<String, String> preferences);

    /**
     * Get a list of all extracted software artifacts. The persistence is handled within the
     * extractor component but is this method provides the required access
     *
     * @return A list of wrappers for the extracted artifacts.<br>
     *         Depending on the concrete extraction this list may
     *         <ul>
     *         <li>be empty,</li>
     *         <li>contain only the top level element or</li>
     *         <li>contain wrappers for all elements</li>
     *         </ul>
     *         but will never be null.
     */
    public List<ArtifactWrapper> getSoftwareArtefacts();
}
