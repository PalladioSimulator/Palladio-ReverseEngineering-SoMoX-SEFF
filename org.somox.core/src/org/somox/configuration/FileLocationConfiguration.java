package org.somox.configuration;

public class FileLocationConfiguration {
	private String analyserInputFile;
	private String projectName;
	private String outputFolder;

	public FileLocationConfiguration() {
	}

	/**
	 * @param analyserInputFile the analyserInputFile to set
	 */
	public void setAnalyserInputFile(String analyserInputFile) {
		this.analyserInputFile = analyserInputFile;
	}

	/**
	 * @return the analyserInputFile
	 */
	public String getAnalyserInputFile() {
		return analyserInputFile;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}

	public String getOutputFolder() {
		return this.outputFolder;
	}
}