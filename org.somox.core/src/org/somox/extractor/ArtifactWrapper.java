package org.somox.extractor;

/**
 * A wrapper for a common Software artifact
 */
public interface ArtifactWrapper {

    /** Get the id of the wrapped artifact */
    public String getId();

    /** Get the identifier for the extractor instance this artifact has come from */
    public String getExtractorId();

    /** Get the wrapped software artifact */
    public Object getArtefact();

}
