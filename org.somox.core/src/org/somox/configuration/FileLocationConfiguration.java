package org.somox.configuration;

public class FileLocationConfiguration {
    private String analyserInputFile;
    private String projectName;
    private String outputFolder;

    public FileLocationConfiguration() {
    }

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
     * @param projectName
     *            the projectName to set
     */
    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return this.projectName;
    }

    public void setOutputFolder(final String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public String getOutputFolder() {
        return this.outputFolder;
    }
}