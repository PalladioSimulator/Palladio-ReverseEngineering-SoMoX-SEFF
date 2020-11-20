package org.somox.configuration;

import java.util.Set;

public class FileLocationConfiguration {
    private String analyserInputFile;
    private Set<String> projectNames;
    private String outputFolder;

    /**
     * @param analyserInputFile
     *            the analyserInputFile to set
     */
    public void setAnalyserInputFile(final String analyserInputFile) {
        this.analyserInputFile = analyserInputFile;
    }

    /**
     * @return the analyserInputFile
     */
    public String getAnalyserInputFile() {
        return this.analyserInputFile;
    }

    /**
     * @param projectNames
     *            the projectName to set
     */
    public void setProjectNames(final Set<String> projectNames) {
        this.projectNames = projectNames;
    }

    /**
     * @return The names of all projects to be analysed.
     */
    public Set<String> getProjectNames() {
        return this.projectNames;
    }

    /**
     * @param outputFolder
     *            The (workspace-relative) path to the folder to put all analysis results in.
     */
    public void setOutputFolder(final String outputFolder) {
        this.outputFolder = outputFolder;
    }

    /**
     * @return The (workspace-relative) path to the folder to put all analysis results in.
     */
    public String getOutputFolder() {
        return this.outputFolder;
    }
}