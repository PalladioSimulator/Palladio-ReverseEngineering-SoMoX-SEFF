package org.somox.core.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.ModelAnalyzer;
import org.somox.analyzer.ModelAnalyzerException;
import org.somox.configuration.ConfigurationDefinition;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.core.SoMoXCore;
import org.somox.extractor.ExtractionResult;
import org.somox.extractor.SoftwareExtractor;

import eu.qimpress.qimpressgast.GASTBehaviourRepository;
import eu.qimpress.samm.qosannotation.QosAnnotations;
import eu.qimpress.samm.staticstructure.Repository;
import eu.qimpress.samm.staticstructure.ServiceArchitectureModel;
import eu.qimpress.samm.staticstructure.StaticstructureFactory;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * Standard Implementation of the SoMoX Core.
 *
 * @author Benjamin Klatt
 *
 */
public class SoMoXCoreStandard implements SoMoXCore {

	// ---------------------------------
	// Static Data Fields
	// ---------------------------------

	/** The path inside the project to store the internal architecture model */
	private static final String PATH_INTERNAL_ARCHITECTUREMODEL = "/internal_architecture_model.samm_repository";
	private static final String PATH_GAST_BEHAVIOUR_REPOSITORY = "/internal_architecture_model.samm_gastbehaviour";
	private static final String PATH_SOURCECODE_DECORATOR_REPOSITORY = "/internal_architecture_model.sourcecodedecorator";
	private static final String PATH_SAMM_MODEL = "/internal_architecture_model.samm_servicearchitecturemodel";
	private static final String PATH_QOS_ANNOTATIONS_MODEL = "/internal_architecture_model.samm_qosannotation";


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

	public void runAnalyzer(String analyzerID, IProgressMonitor progressMonitor, HashMap<String, String> preferences, SoMoXConfiguration somoxConfiguration) throws ModelAnalyzerException {
		logger.info("analysis started");
		logger.info("analysis of results: "+this.extractionResultMap);
		
		// load the current internal architecture model
		String projectIdentifier = somoxConfiguration.getFileLocations().getProjectName(); 
		String outputFolder = somoxConfiguration.getFileLocations().getOutputFolder();
		Repository internalArchitectureModel = null;
		try {
			internalArchitectureModel = getInternalArchitectureModel(projectIdentifier,outputFolder);
		} catch (IOException e) {
			logger.error("Model Resource load failed for Analyzer. Location was: "+projectIdentifier,e);
			throw new ModelAnalyzerException("Model Resource load failed for Analyzer.",e);
		}

		// perform the analysis
		logger.info("Start model analyzer ("+analyzerID+")");
		ModelAnalyzer analyzer = modelAnalyzerMap.get(analyzerID);
		if (analyzer == null) {
			logger.error("Model Analyzer " + analyzerID + " not available.", null);
			throw new ModelAnalyzerException("Model Analyzer not available");
		}
				
		AnalysisResult result = analyzer.analyze(somoxConfiguration,internalArchitectureModel,extractionResultMap,progressMonitor);
		logger.info("Analysis finished with result: "+result.getResultStatus());

		// save the new internal architecture model
		try {
			saveInternalArchitectureModel(
					result.getInternalArchitectureModel(), projectIdentifier,
					outputFolder);
			saveGastBehaviourRepository(result.getGastBehaviourRepository(),
					projectIdentifier, outputFolder);
			saveSourceCodeDecoratorRepository(result
					.getSourceCodeDecoratorRepository(), projectIdentifier,
					outputFolder);
			saveSammModel(result
					.getServiceArchitectureModel(), projectIdentifier,
					outputFolder);
			saveQoSAnnotationsModel(result
					.getQosAnnotationModel(), projectIdentifier,
					outputFolder);
		} catch (IOException e) {
			logger.error("Model Analyzer failed.",e);
			throw new ModelAnalyzerException("Model Analyzer failed with IOException",e);
		}
	}

	/**
	 * Load the internal architecture model. if none is present now create a new one
	 *
	 * @throws IOException
	 */
	private Repository getInternalArchitectureModel(String projectIdentifier, String outputFolder) throws IOException {

		Repository result = null;

		Resource resource = getResource(projectIdentifier, outputFolder + PATH_INTERNAL_ARCHITECTUREMODEL, StaticstructureFactory.eINSTANCE.createRepository());

		if(resource.getContents().size() == 0){
			logger.error("The internal architecture model file does not contain a model.",
					new Exception("No internal architecture model repository available."));

		} else {
			EObject content = resource.getContents().get(0);
			if(content instanceof Repository){
				result = (Repository) content;
			} else {
				logger.error("The internal architecture model resource contains a model of a wrong type.",
						new Exception("Wrong emf model type in resource "+content));
			}
		}
		return result;
	}

	/**
	 * Save the internal architecture model
	 *
	 * @param internalArchitectureModel	The model to be saved
	 * @param projectIdentifier			The identifier for the project to save the model in
	 * @throws IOException	An exception about problems reading or writing the internal architecture model
	 */
	private void saveInternalArchitectureModel(Repository internalArchitectureModel, String projectIdentifier, String outputFolder)
			throws IOException {
		save(internalArchitectureModel, projectIdentifier, outputFolder + PATH_INTERNAL_ARCHITECTUREMODEL);
	}
	
	private void saveGastBehaviourRepository(GASTBehaviourRepository repository, String projectIdentifier, String outputFolder) throws IOException {
		save(repository, projectIdentifier, outputFolder + PATH_GAST_BEHAVIOUR_REPOSITORY);
	}
	
	private void saveSourceCodeDecoratorRepository(SourceCodeDecoratorRepository repository, String projectIdentifier, String outputFolder) throws IOException {
		save(repository, projectIdentifier, outputFolder + PATH_SOURCECODE_DECORATOR_REPOSITORY);
	}
	
	private void saveSammModel(ServiceArchitectureModel serviceArchitectureModel, String projectIdentifier, String outputFolder) throws IOException {
		save(serviceArchitectureModel, projectIdentifier, outputFolder + PATH_SAMM_MODEL);
	}
	
	private void saveQoSAnnotationsModel(QosAnnotations serviceArchitectureModel, String projectIdentifier, String outputFolder) throws IOException {
		save(serviceArchitectureModel, projectIdentifier, outputFolder + PATH_QOS_ANNOTATIONS_MODEL);
	}		
	
	private void save(EObject emfObject, String projectIdentifier, String path) throws IOException {
		ResourceSet resourceSet = getResourceSetForURI();
		//URI scriptURI = fileURI;
		URI uri = URI.createURI("platform:/resource/"+projectIdentifier+path);
		//scriptURI = scriptURI.appendFileExtension(fileExtension);
		// Create a resource for this file.
		Resource resource = resourceSet.createResource(uri);
		// Add object to the contents.
		resource.getContents().add(emfObject);
		
		HashMap<Object, Object> saveOptions = new HashMap<Object, Object>();		
		saveOptions.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);		
		
		resource.save(saveOptions);
	}
	
	private ResourceSet getResourceSetForURI() {
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the default resource factory -- only needed for stand-alone!
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());
		return resourceSet;
	}
	
	

	/**
	 * Get the EMF resource to work with
	 * @param projectIdentifier The identifier for the project to work with
	 * @return	The requested EMF resource
	 * @throws IOException
	 */
	private Resource getResource(String projectIdentifier, String path, EObject defaultObject) throws IOException {

		URI uri = URI.createURI("platform:/resource/"+projectIdentifier+path);

		Resource resource = resourceSet.getResource(uri, false);
		
//		GASTReader gastReader;
//		Resource resource = null;
//		try {
//			gastReader = new GASTReader();
//			gastReader.loadFile(uri.toString());
//			resource = gastReader.getResourceGAST();
//		} catch (IOException e1) {
//			logger.error("Error loading model: " + e1);
//			e1.printStackTrace();
//		}

		// if the resource does not exist or is empty create a new one with
		// an appropriate model inside
		if(resource == null || resource.getContents().isEmpty()){
			resource = resourceSet.createResource(uri);
			resource.getContents().add(defaultObject);
			resource.save(null);
		}

		return resource;
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
