package org.somox.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.ModelAnalyzer;
import org.somox.analyzer.ModelAnalyzerException;
import org.somox.configuration.ConfigurationDefinition;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.extractor.SoftwareExtractor;

public interface SoMoXCore {

    /**
     * Add a Software Extractor to the stack that should be executed Already existing Extractor
     * instances with the same id will be overridden
     *
     * @param id
     *            The identifier for the extractor instance
     * @param extractor
     *            The Software Extractor to be executed. If null is handed over nothing is added to
     *            the stack
     */
    public void addSoftwareExtractor(String id, SoftwareExtractor extractor);

    /**
     * Remove the Software Extractor instance with the given identifier from the process stack
     *
     * @param id
     *            The internal id of the extractor
     */
    public void removeSoftwareExtractor(String id);

    /**
     * Add a Model Analyzer to the stack that should be executed Already existing Analyzer instances
     * with the same id will be overridden
     *
     * @param id
     *            The identifier for the analyzer instance
     * @param analyzer
     *            The Model Analyzer to be executed. If null is handed over nothing is added to the
     *            stack
     */
    public void addModelAnalyzer(String id, ModelAnalyzer analyzer);

    /**
     * Remove the Model Analyzer instance with the given identifier from the process stack
     *
     * @param id
     *            The internal id of the analyzer
     */
    public void removeModelAnalyzer(String id);

    /**
     * Execute all software extractors
     *
     * @param progressMonitor
     *            A listener object for the process status
     * @param preferences
     */
    public void runExtraction(IProgressMonitor progressMonitor, HashMap<String, String> preferences);

    /**
     * Execute the configured analyzer
     *
     * @param progressMonitor
     *            A listener object for the process status
     * @param preferences
     *            Preferences for the analysis
     * @throws ModelAnalyzerException
     */
    public AnalysisResult runAnalyzer(String analyzerID, IProgressMonitor progressMonitor,
            HashMap<String, String> globalpreferences, SoMoXConfiguration somoxConfiguration)
                    throws ModelAnalyzerException;

    /**
     * Execute the configured export
     *
     * @param progressMonitor
     *            A listener object for the process status
     */
    public void runExport(IProgressMonitor progressMonitor);

    /**
     * Get the software extractors which have been successfully performed
     */
    public List<SoftwareExtractor> getExecutedSoftwareExtractors();

    /**
     * Get the list of configuration definitions
     *
     * @return
     */
    public LinkedList<ConfigurationDefinition> getConfigurationDefinitions();

    /**
     * Get the list of configuration definitions
     *
     * @return
     */
    public LinkedList<ConfigurationDefinition> getGlobalConfigurationDefinitions();
}