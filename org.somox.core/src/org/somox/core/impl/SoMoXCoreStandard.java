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

	/** The map of all configured Software Extractors	 */
	private HashMap<String, SoftwareExtractor> softwareExtractorMap = new HashMap<String, SoftwareExtractor>();
	
	/** The map of all configured Model Analyzers	 */
	private HashMap<String, ModelAnalyzer> modelAnalyzerMap = new HashMap<String, ModelAnalyzer>();

	/** The map of all existing extraction results [etxractorID,resultObject]	 */
	private HashMap<String, ExtractionResult> extractionResultMap = new HashMap<String, ExtractionResult>();

	/** The list of executed software extractors */
	private List<SoftwareExtractor> executedList = new LinkedList<SoftwareExtractor>();

	/** The resource set of the core to work with */
	private ResourceSet resourceSet = null;
	
	private Logger logger = Logger.getLogger(SoMoXCoreStandard.class.getName());


	// ---------------------------------
	// Constructor
	// ---------------------------------

	/**
	 * Default constructor preparing the ecore resource set
	 */
	public SoMoXCoreStandard() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry()
					.getExtensionToFactoryMap()
					.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
	}

	// ---------------------------------
	// Business Methods
	// ---------------------------------

	// TODO Handle multiple extractors
	public void runExtraction(IProgressMonitor progressMonitor, HashMap<String, String> preferences) {
		logger.info("extraction started");
		Iterator<Entry<String, SoftwareExtractor>> iter = this.softwareExtractorMap.entrySet().iterator();;
		while(iter.hasNext()){
			Entry<String, SoftwareExtractor> entry = iter.next();
			ExtractionResult result = entry.getValue().runExtraction(entry.getKey(),preferences);
			this.extractionResultMap.put(entry.getKey(), result);
			executedList.add(entry.getValue());
		}
		logger.info("extraction finished");
	}

	public AnalysisResult runAnalyzer(String analyzerID, IProgressMonitor progressMonitor, HashMap<String, String> preferences, SoMoXConfiguration somoxConfiguration) throws ModelAnalyzerException {
		logger.info("analysis started");
		logger.info("analysis of results: "+this.extractionResultMap);
		
		// perform the analysis
		logger.info("Start model analyzer ("+analyzerID+")");
		ModelAnalyzer analyzer = modelAnalyzerMap.get(analyzerID);
		if (analyzer == null) {
			logger.error("Model Analyzer " + analyzerID + " not available.", null);
			throw new ModelAnalyzerException("Model Analyzer not available");
		}
				
		AnalysisResult result = analyzer.analyze(somoxConfiguration,extractionResultMap,progressMonitor);
		logger.info("Analysis finished with result: "+result.getResultStatus());
		
		return result;
	}


	public void runExport(IProgressMonitor progressMonitor) {
		// TODO implement the export
	}

	// ---------------------------------
	// Helper Methods
	// ---------------------------------

	public void addSoftwareExtractor(String id, SoftwareExtractor extractor) {
		this.softwareExtractorMap.put(id,extractor);
	}

	public void removeSoftwareExtractor(String id) {
		this.softwareExtractorMap.remove(id);
	}
	
	public void addModelAnalyzer(String id, ModelAnalyzer analyzer) {
		this.modelAnalyzerMap.put(id,analyzer);
	}

	public void removeModelAnalyzer(String id) {
		this.modelAnalyzerMap.remove(id);
	}


	// ---------------------------------
	// Getters / Setters
	// ---------------------------------
	
	public List<SoftwareExtractor> getExecutedSoftwareExtractors() {
		return executedList;
	}

	public LinkedList<ConfigurationDefinition> getConfigurationDefinitions() {
		LinkedList<ConfigurationDefinition> definitions = new LinkedList<ConfigurationDefinition>();

		// add core configurations
		definitions.addAll(getCoreConfigurationDefinitions());

		// add extractor configurations
		Iterator<SoftwareExtractor> extractors = softwareExtractorMap.values().iterator();
		while(extractors.hasNext()){
			definitions.addAll(extractors.next().getConfigurationDefinitions());
		}

		// add analyzer configurations
		//definitions.addAll(modelAnalyzer.getConfigurationDefinitions());

		return definitions;
	}
	
	public LinkedList<ConfigurationDefinition> getGlobalConfigurationDefinitions() {
		LinkedList<ConfigurationDefinition> definitions = new LinkedList<ConfigurationDefinition>();

		return definitions;
	}

	/**
	 * Get the list of required configuration settings for the core
	 *
	 * @return The list of configuration definition objects
	 */
	private Collection<ConfigurationDefinition> getCoreConfigurationDefinitions() {
		LinkedList<ConfigurationDefinition> configs = new LinkedList<ConfigurationDefinition>();
		return configs;
	}
}
