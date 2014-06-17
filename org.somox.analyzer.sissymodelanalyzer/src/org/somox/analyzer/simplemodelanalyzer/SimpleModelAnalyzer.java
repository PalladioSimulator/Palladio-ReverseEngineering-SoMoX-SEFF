package org.somox.analyzer.simplemodelanalyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.ModelAnalyzer;
import org.somox.analyzer.ModelAnalyzerException;
import org.somox.analyzer.simplemodelanalyzer.builder.ComponentBuilder;
import org.somox.analyzer.simplemodelanalyzer.builder.PCMSystemBuilder;
import org.somox.analyzer.simplemodelanalyzer.detection.util.ComponentPrinter;
import org.somox.analyzer.simplemodelanalyzer.factories.BasicSoMoXStrategiesFactory;
import org.somox.analyzer.simplemodelanalyzer.factories.ISoMoXStrategiesFactory;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.extractor.ExtractionResult;
import org.somox.kdmhelper.KDMReader;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.seff2javaast.SEFF2JavaAST;
import org.somox.seff2javaast.Seff2javaastFactory;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.allocation.AllocationFactory;
import de.uka.ipd.sdq.pcm.qosannotations.QoSAnnotations;
import de.uka.ipd.sdq.pcm.qosannotations.QosannotationsFactory;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;
import de.uka.ipd.sdq.pcm.system.SystemFactory;
import de.uka.ipd.sdq.workflow.ExecutionTimeLoggingProgressMonitor;
//import de.fzi.gast.core.Root;
//import de.fzi.gast.helpers.GASTReader;

/**
 * This class runs a component detection based on a GAST input model and returns the results
 * to the SoMoX core for storing or further processing
 * 
 * @author Michael Hauck, Klaus Krogmann, Steffen Becker
 */
public class SimpleModelAnalyzer implements ModelAnalyzer {

	/**
	 * The logger of this analyser 
	 */
	private static Logger logger = Logger.getLogger(SimpleModelAnalyzer.class);
	
	/**
	 * The current status of the analyzer
	 */
	private ModelAnalyzer.Status status = ModelAnalyzer.Status.READY;
		
	// ---------------------------------
	// Business Methods
	// ---------------------------------
	
	/* (non-Javadoc)
	 * @see org.somox.analyzer.ModelAnalyzer#init()
	 */
	public void init() {}

	/* (non-Javadoc)
	 * @see org.somox.analyzer.ModelAnalyzer#analyze(java.util.HashMap, eu.qimpress.samm.staticstructure.Repository, java.util.HashMap, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public AnalysisResult analyze(
			// jamopp xmi generieren 
			
			SoMoXConfiguration somoxConfiguration,
			HashMap<String, ExtractionResult> extractionResultMap,
			IProgressMonitor progressMonitor) throws ModelAnalyzerException {

		this.status = ModelAnalyzer.Status.RUNNING;
		logger.info("SISSy Analyzer started with"
				+"\n SOMOX Configuration: "+somoxConfiguration
				+"\n extractionResultMap "+extractionResultMap);

		AnalysisResult analysisResult = null;
		
		String projectName = somoxConfiguration.getFileLocations().getProjectName();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
	    IWorkspaceRoot workspaceRoot = workspace.getRoot();
	    IProject project = workspaceRoot.getProject(projectName);
	    KDMReader modelReader = new KDMReader();
	    try {
	    	modelReader.loadProject(project);
		} catch (IOException e) {
			logger.error("Failed to load GAST Model",e);
			throw new ModelAnalyzerException("Failed to load GAST model",e);
		}
		Root root = modelReader.getRoot();
		analysisResult = analyzeGASTModel(root, somoxConfiguration, progressMonitor);
		this.status = ModelAnalyzer.Status.FINISHED;
		return analysisResult;
	}

	/**
	 * Analyze the given GAST model to find components
	 * @param astModel The root of the GAST model to analyze
	 * @param preferences Preferences containing the configuration of the analysis
	 * @param progressMonitor Progress monitor used to indicate detection progress
	 * @throws ModelAnalyzerException
	 */
	private SimpleAnalysisResult analyzeGASTModel(
			Root astModel,
			SoMoXConfiguration somoxConfiguration,
			IProgressMonitor progressMonitor) throws ModelAnalyzerException {
		
		// Set up result
		SimpleAnalysisResult analysisResult = initializeAnalysisResult();
		analysisResult.setResultStatus(AnalysisResult.ResultStatus.FAILED);
		
		// Set up model builder
		ComponentBuilder pcmComponentBuilder = new ComponentBuilder(astModel, somoxConfiguration, analysisResult);
		ISoMoXStrategiesFactory strategiesFactory = new BasicSoMoXStrategiesFactory(astModel, somoxConfiguration);
		
		// Initial Components
		List<ComponentImplementingClassesLink> initialComponents = detectInitialComponentCandidates(
				astModel, somoxConfiguration, pcmComponentBuilder, strategiesFactory,
				progressMonitor);
		
		//removelater
//		String fileName = "01initialComponentsPCKDM.txt";
//		int PCnumber = 0;
//		for(ComponentImplementingClassesLink element : initialComponents){
////			org.somox.changetest.Helper.writeToFile(fileName, String.valueOf(PCnumber++));
//			for(Type type : element.getImplementingClasses()){
//				org.somox.changetest.Helper.writeToFile(fileName, GASTClassHelper.computeFullQualifiedName(type));
//			}
//		}
//		org.somox.changetest.Helper.sortFile(fileName);

		// Component Detection
		clusterComponents(initialComponents, somoxConfiguration, pcmComponentBuilder,
				strategiesFactory, progressMonitor);

		// Post Detection Phase
		postComponentDetection(somoxConfiguration, analysisResult, strategiesFactory, progressMonitor);
		
		// Create PCM System
		PCMSystemBuilder pcmSystemBuilder = new PCMSystemBuilder(astModel, somoxConfiguration, analysisResult, pcmComponentBuilder);		
		pcmSystemBuilder.buildSystemModel();
		
		analysisResult.setResultStatus(AnalysisResult.ResultStatus.SUCCESS);
		
		return analysisResult;
	}

	private void postComponentDetection(
			SoMoXConfiguration somoxConfiguration,
			SimpleAnalysisResult analysisResult,
			ISoMoXStrategiesFactory strategiesFactory,
			IProgressMonitor progressMonitor) {
		strategiesFactory.getPostComponentDetectionStrategy().
			postComponentDetection(somoxConfiguration, analysisResult, progressMonitor);
	}

	/**
	 * Runs the clustering step on the detected initial components. In the clustering step, the initial components can either be merged or 
	 * composed. Details are implemented in a clustering strategy. 
	 * @param initialComponentCandidates List of initial components detected in the source code
	 * @param somoxConfiguration The configuration of this SoMoX run
	 * @param sammBuilder The SAM model builder used to create the component SAM model elements
	 * @param strategiesFactory Factory used to create the clustering strategy
	 * @param progressMonitor Progress monitor to update the Eclipse UI
	 * @return list of detected components
	 * @throws ModelAnalyzerException Thrown if some initialization or metric computation fails
	 */
	private void clusterComponents(
			List<ComponentImplementingClassesLink> initialComponentCandidates,
			SoMoXConfiguration somoxConfiguration,
			ComponentBuilder sammBuilder,
			ISoMoXStrategiesFactory strategiesFactory,
			IProgressMonitor progressMonitor) throws ModelAnalyzerException {
		IProgressMonitor subProgressMonitor = new ExecutionTimeLoggingProgressMonitor(progressMonitor, 0);
		subProgressMonitor.beginTask("Cluster components", IProgressMonitor.UNKNOWN);
		
		List<ComponentImplementingClassesLink> componentsFound = 
			strategiesFactory.getDetectionStrategy(initialComponentCandidates).startDetection(sammBuilder, somoxConfiguration, 
				progressMonitor,initialComponentCandidates);

	
		
		if (logger.isDebugEnabled()) {
			logger.debug("Printing detected components");
			ComponentPrinter.printComponents(componentsFound, logger);
		}
		
		subProgressMonitor.done();
	}

	/**
	 * Method called to derive initial component candidates based on the passed source code in GAST format
	 * @param gastModel The source code in its GAST representation //ESTIMATEDBYDOCQUERY
	 * @param somoxConfiguration The SoMoX configuration containing configuration options for the component detecting like the name blacklist
	 * @param sammBuilder The SAM model builder used to create the component SAM model elements
	 * @param strategiesFactory Factory used to create the clustering strategy
	 * @param progressMonitor Progess monitor to update the Eclipse UI
	 * @return A list of initial component candidates as defined by the source code decorator meta-model
	 */
	private List<ComponentImplementingClassesLink> detectInitialComponentCandidates(
			Root gastModel, SoMoXConfiguration somoxConfiguration,
			ComponentBuilder sammBuilder,
			ISoMoXStrategiesFactory strategiesFactory,
			IProgressMonitor progressMonitor) {
		IProgressMonitor subProgressMonitor = new ExecutionTimeLoggingProgressMonitor(progressMonitor, 0);
		subProgressMonitor.beginTask("Detecting primitive components", IProgressMonitor.UNKNOWN);

		List<ComponentImplementingClassesLink> components = 
			strategiesFactory.getInitializationStrategy().createInitialComponentCandidates(
					gastModel, somoxConfiguration, sammBuilder);
		logger.debug("Finished detection of primitive components. Found "+components.size()+" candidates");
		
		subProgressMonitor.done();
		return components;
	}

	/**
	 * Create an analysis result with newly initialized root models
	 * @param internalArchitectureModel 
	 * @return A new analysis result
	 */
	private SimpleAnalysisResult initializeAnalysisResult() {
		SimpleAnalysisResult analysisResult = new SimpleAnalysisResult(this);
		SourceCodeDecoratorRepository sourceCodeDecoratorRepository = SourcecodedecoratorFactory.eINSTANCE.createSourceCodeDecoratorRepository();
		SEFF2JavaAST seff2JavaAST = Seff2javaastFactory.eINSTANCE.createSEFF2JavaAST();
		de.uka.ipd.sdq.pcm.system.System system = SystemFactory.eINSTANCE.createSystem();
		QoSAnnotations qosAnnotationModel = QosannotationsFactory.eINSTANCE.createQoSAnnotations();
		Repository newInternalArchitectureModel = RepositoryFactory.eINSTANCE.createRepository();
		Allocation allocation = AllocationFactory.eINSTANCE.createAllocation();
		
		analysisResult.setInternalArchitectureModel(newInternalArchitectureModel);
		analysisResult.setSEFF2JavaAST(seff2JavaAST);
		analysisResult.setSourceCodeDecoratorRepository(sourceCodeDecoratorRepository);
		analysisResult.setSystemModel(system);
		analysisResult.setQosAnnotationModel(qosAnnotationModel);
		analysisResult.setAllocation(allocation);
		
		return analysisResult;
	}

	/* (non-Javadoc)
	 * @see org.somox.analyzer.ModelAnalyzer#getStatus()
	 */
	public ModelAnalyzer.Status getStatus() {
		return status;
	}
}
