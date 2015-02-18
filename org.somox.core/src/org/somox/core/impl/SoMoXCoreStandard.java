package org.somox.core.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.ModelAnalyzer;
import org.somox.analyzer.ModelAnalyzerException;
import org.somox.configuration.ConfigurationDefinition;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.core.SoMoXCore;
import org.somox.extractor.ExtractionResult;
import org.somox.extractor.SoftwareExtractor;

/**
 * Standard Implementation of the SoMoX Core.
 *
 * @author Benjamin Klatt
 *
 */
public class SoMoXCoreStandard implements SoMoXCore {

    // ---------------------------------
    // Data fields
    // ---------------------------------

    /** The map of all configured Software Extractors */
    private final HashMap<String, SoftwareExtractor> softwareExtractorMap = new HashMap<String, SoftwareExtractor>();

    /** The map of all configured Model Analyzers */
    private final HashMap<String, ModelAnalyzer> modelAnalyzerMap = new HashMap<String, ModelAnalyzer>();

    /** The map of all existing extraction results [etxractorID,resultObject] */
    private final HashMap<String, ExtractionResult> extractionResultMap = new HashMap<String, ExtractionResult>();

    /** The list of executed software extractors */
    private final List<SoftwareExtractor> executedList = new LinkedList<SoftwareExtractor>();

    /** The resource set of the core to work with */
    private ResourceSet resourceSet = null;

    private final Logger logger = Logger.getLogger(SoMoXCoreStandard.class.getName());

    // ---------------------------------
    // Constructor
    // ---------------------------------

    /**
     * Default constructor preparing the ecore resource set
     */
    public SoMoXCoreStandard() {
        this.resourceSet = new ResourceSetImpl();
        this.resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

    }

    // ---------------------------------
    // Business Methods
    // ---------------------------------

    // TODO Handle multiple extractors
    @Override
    public void runExtraction(final IProgressMonitor progressMonitor, final HashMap<String, String> preferences) {
        this.logger.info("extraction started");
        final Iterator<Entry<String, SoftwareExtractor>> iter = this.softwareExtractorMap.entrySet().iterator();
        ;
        while (iter.hasNext()) {
            final Entry<String, SoftwareExtractor> entry = iter.next();
            final ExtractionResult result = entry.getValue().runExtraction(entry.getKey(), preferences);
            this.extractionResultMap.put(entry.getKey(), result);
            this.executedList.add(entry.getValue());
        }
        this.logger.info("extraction finished");
    }

    @Override
    public AnalysisResult runAnalyzer(final String analyzerID, final IProgressMonitor progressMonitor,
            final HashMap<String, String> preferences, final SoMoXConfiguration somoxConfiguration)
            throws ModelAnalyzerException {
        this.logger.info("analysis started");
        this.logger.info("analysis of results: " + this.extractionResultMap);

        // perform the analysis
        this.logger.info("Start model analyzer (" + analyzerID + ")");
        final ModelAnalyzer analyzer = this.modelAnalyzerMap.get(analyzerID);
        if (analyzer == null) {
            this.logger.error("Model Analyzer " + analyzerID + " not available.", null);
            throw new ModelAnalyzerException("Model Analyzer not available");
        }

        final AnalysisResult result = analyzer.analyze(somoxConfiguration, this.extractionResultMap, progressMonitor);
        this.logger.info("Analysis finished with result: " + result.getResultStatus());

        return result;
    }

    @Override
    public void runExport(final IProgressMonitor progressMonitor) {
        // TODO implement the export
    }

    // ---------------------------------
    // Helper Methods
    // ---------------------------------

    @Override
    public void addSoftwareExtractor(final String id, final SoftwareExtractor extractor) {
        this.softwareExtractorMap.put(id, extractor);
    }

    @Override
    public void removeSoftwareExtractor(final String id) {
        this.softwareExtractorMap.remove(id);
    }

    @Override
    public void addModelAnalyzer(final String id, final ModelAnalyzer analyzer) {
        this.modelAnalyzerMap.put(id, analyzer);
    }

    @Override
    public void removeModelAnalyzer(final String id) {
        this.modelAnalyzerMap.remove(id);
    }

    // ---------------------------------
    // Getters / Setters
    // ---------------------------------

    @Override
    public List<SoftwareExtractor> getExecutedSoftwareExtractors() {
        return this.executedList;
    }

    @Override
    public LinkedList<ConfigurationDefinition> getConfigurationDefinitions() {
        final LinkedList<ConfigurationDefinition> definitions = new LinkedList<ConfigurationDefinition>();

        // add core configurations
        definitions.addAll(this.getCoreConfigurationDefinitions());

        // add extractor configurations
        final Iterator<SoftwareExtractor> extractors = this.softwareExtractorMap.values().iterator();
        while (extractors.hasNext()) {
            definitions.addAll(extractors.next().getConfigurationDefinitions());
        }

        // add analyzer configurations
        // definitions.addAll(modelAnalyzer.getConfigurationDefinitions());

        return definitions;
    }

    @Override
    public LinkedList<ConfigurationDefinition> getGlobalConfigurationDefinitions() {
        final LinkedList<ConfigurationDefinition> definitions = new LinkedList<ConfigurationDefinition>();

        return definitions;
    }

    /**
     * Get the list of required configuration settings for the core
     *
     * @return The list of configuration definition objects
     */
    private Collection<ConfigurationDefinition> getCoreConfigurationDefinitions() {
        final LinkedList<ConfigurationDefinition> configs = new LinkedList<ConfigurationDefinition>();
        return configs;
    }
}
