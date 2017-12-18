package org.somox.analyzer.simplemodelanalyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.ModelAnalyzer;
import org.somox.analyzer.ModelAnalyzerException;
import org.somox.analyzer.SimpleAnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.builder.ComponentBuilder;
import org.somox.analyzer.simplemodelanalyzer.builder.PCMSystemBuilder;
import org.somox.analyzer.simplemodelanalyzer.detection.util.ComponentPrinter;
import org.somox.analyzer.simplemodelanalyzer.factories.BasicSoMoXStrategiesFactory;
import org.somox.analyzer.simplemodelanalyzer.factories.ISoMoXStrategiesFactory;
import org.somox.configuration.AbstractMoxConfiguration;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.extractor.ExtractionResult;
import org.somox.kdmhelper.KDMReader;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

import de.uka.ipd.sdq.workflow.ExecutionTimeLoggingProgressMonitor;

/**
 * This class runs a component detection based on a GAST input model and returns the results to the
 * SoMoX core for storing or further processing
 *
 * @author Michael Hauck, Klaus Krogmann, Steffen Becker
 */
public class SimpleModelAnalyzer implements ModelAnalyzer<SoMoXConfiguration> {

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

    /*
     * (non-Javadoc)
     *
     * @see org.somox.analyzer.ModelAnalyzer#init()
     */
    @Override
    public void init() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.analyzer.ModelAnalyzer#analyze(java.util.HashMap,
     * eu.qimpress.samm.staticstructure.Repository, java.util.HashMap,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public AnalysisResult analyze(final SoMoXConfiguration somoxConfiguration,
            final HashMap<String, ExtractionResult> extractionResultMap, final IProgressMonitor progressMonitor)
            throws ModelAnalyzerException {
        this.status = ModelAnalyzer.Status.RUNNING;
        SimpleModelAnalyzer.logger.info("SISSy Analyzer started with" + "\n SOMOX Configuration: " + somoxConfiguration
                + "\n extractionResultMap " + extractionResultMap);

        AnalysisResult analysisResult = null;

        final KDMReader modelReader = new KDMReader();
        try {
            modelReader.loadProject(somoxConfiguration.getFileLocations().getProjectNames().toArray(new String[0]));
        } catch (final IOException e) {
            SimpleModelAnalyzer.logger.error("Failed to load GAST Model", e);
            throw new ModelAnalyzerException("Failed to load GAST model", e);
        }
        final Root root = modelReader.getRoot();
        analysisResult = this.analyzeGASTModel(root, somoxConfiguration, progressMonitor);
        analysisResult.setRoot(root);
        this.status = ModelAnalyzer.Status.FINISHED;
        return analysisResult;
    }

    /**
     * Analyze the given GAST model to find components
     *
     * @param astModel
     *            The root of the GAST model to analyze
     * @param preferences
     *            Preferences containing the configuration of the analysis
     * @param progressMonitor
     *            Progress monitor used to indicate detection progress
     * @throws ModelAnalyzerException
     */
    private SimpleAnalysisResult analyzeGASTModel(final Root astModel, final SoMoXConfiguration somoxConfiguration,
            final IProgressMonitor progressMonitor) throws ModelAnalyzerException {

        // Set up result
        final SimpleAnalysisResult analysisResult = this.initializeAnalysisResult();
        analysisResult.setResultStatus(AnalysisResult.ResultStatus.FAILED);

        // Set up model builder
        final ComponentBuilder pcmComponentBuilder = new ComponentBuilder(astModel, somoxConfiguration, analysisResult);
        final ISoMoXStrategiesFactory strategiesFactory = new BasicSoMoXStrategiesFactory(astModel, somoxConfiguration);

        // Initial Components
        final List<ComponentImplementingClassesLink> initialComponents = this.detectInitialComponentCandidates(astModel,
                somoxConfiguration, pcmComponentBuilder, strategiesFactory, progressMonitor);

        // removelater
        // String fileName = "01initialComponentsPCKDM.txt";
        // int PCnumber = 0;
        // for(ComponentImplementingClassesLink element : initialComponents){
        // // org.somox.changetest.Helper.writeToFile(fileName, String.valueOf(PCnumber++));
        // for(Type type : element.getImplementingClasses()){
        // org.somox.changetest.Helper.writeToFile(fileName,
        // GASTClassHelper.computeFullQualifiedName(type));
        // }
        // }
        // org.somox.changetest.Helper.sortFile(fileName);

        // Component Detection
        this.clusterComponents(initialComponents, somoxConfiguration, pcmComponentBuilder, strategiesFactory,
                progressMonitor);

        // Post Detection Phase
        this.postComponentDetection(somoxConfiguration, analysisResult, strategiesFactory, progressMonitor);

        // Create PCM System
        final PCMSystemBuilder pcmSystemBuilder = new PCMSystemBuilder(astModel, somoxConfiguration, analysisResult,
                pcmComponentBuilder);
        pcmSystemBuilder.buildSystemModel();

        analysisResult.setResultStatus(AnalysisResult.ResultStatus.SUCCESS);

        return analysisResult;
    }

    private void postComponentDetection(final AbstractMoxConfiguration somoxConfiguration,
            final SimpleAnalysisResult analysisResult, final ISoMoXStrategiesFactory strategiesFactory,
            final IProgressMonitor progressMonitor) {
        strategiesFactory.getPostComponentDetectionStrategy().postComponentDetection(somoxConfiguration, analysisResult,
                progressMonitor);
    }

    /**
     * Runs the clustering step on the detected initial components. In the clustering step, the
     * initial components can either be merged or composed. Details are implemented in a clustering
     * strategy.
     *
     * @param initialComponentCandidates
     *            List of initial components detected in the source code
     * @param somoxConfiguration
     *            The configuration of this SoMoX run
     * @param sammBuilder
     *            The SAM model builder used to create the component SAM model elements
     * @param strategiesFactory
     *            Factory used to create the clustering strategy
     * @param progressMonitor
     *            Progress monitor to update the Eclipse UI
     * @return list of detected components
     * @throws ModelAnalyzerException
     *             Thrown if some initialization or metric computation fails
     */
    private void clusterComponents(final List<ComponentImplementingClassesLink> initialComponentCandidates,
            final AbstractMoxConfiguration somoxConfiguration, final ComponentBuilder sammBuilder,
            final ISoMoXStrategiesFactory strategiesFactory, final IProgressMonitor progressMonitor)
            throws ModelAnalyzerException {
        final IProgressMonitor subProgressMonitor = new ExecutionTimeLoggingProgressMonitor(progressMonitor, 0);
        subProgressMonitor.beginTask("Cluster components", IProgressMonitor.UNKNOWN);

        final List<ComponentImplementingClassesLink> componentsFound = strategiesFactory
                .getDetectionStrategy(initialComponentCandidates)
                .startDetection(sammBuilder, somoxConfiguration, progressMonitor, initialComponentCandidates);

        if (SimpleModelAnalyzer.logger.isDebugEnabled()) {
            SimpleModelAnalyzer.logger.debug("Printing detected components");
            ComponentPrinter.printComponents(componentsFound, SimpleModelAnalyzer.logger);
        }

        subProgressMonitor.done();
    }

    /**
     * Method called to derive initial component candidates based on the passed source code in GAST
     * format
     *
     * @param gastModel
     *            The source code in its GAST representation //ESTIMATEDBYDOCQUERY
     * @param somoxConfiguration
     *            The SoMoX configuration containing configuration options for the component
     *            detecting like the name blacklist
     * @param sammBuilder
     *            The SAM model builder used to create the component SAM model elements
     * @param strategiesFactory
     *            Factory used to create the clustering strategy
     * @param progressMonitor
     *            Progess monitor to update the Eclipse UI
     * @return A list of initial component candidates as defined by the source code decorator
     *         meta-model
     */
    private List<ComponentImplementingClassesLink> detectInitialComponentCandidates(final Root gastModel,
            final SoMoXConfiguration somoxConfiguration, final ComponentBuilder sammBuilder,
            final ISoMoXStrategiesFactory strategiesFactory, final IProgressMonitor progressMonitor) {
        final IProgressMonitor subProgressMonitor = new ExecutionTimeLoggingProgressMonitor(progressMonitor, 0);
        subProgressMonitor.beginTask("Detecting primitive components", IProgressMonitor.UNKNOWN);

        final List<ComponentImplementingClassesLink> components = strategiesFactory.getInitializationStrategy()
                .createInitialComponentCandidates(gastModel, somoxConfiguration, sammBuilder);
        SimpleModelAnalyzer.logger
                .debug("Finished detection of primitive components. Found " + components.size() + " candidates");

        subProgressMonitor.done();
        return components;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.analyzer.ModelAnalyzer#getStatus()
     */
    @Override
    public ModelAnalyzer.Status getStatus() {
        return this.status;
    }
}
