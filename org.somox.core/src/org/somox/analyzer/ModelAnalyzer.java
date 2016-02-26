package org.somox.analyzer;

import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.allocation.AllocationFactory;
import org.palladiosimulator.pcm.qosannotations.QoSAnnotations;
import org.palladiosimulator.pcm.qosannotations.QosannotationsFactory;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.system.SystemFactory;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.extractor.ExtractionResult;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

/**
 * Interface for Analyzer extensions to be accessed from the SoMoX core
 *
 * @author Benjamin Klatt
 *
 */
public interface ModelAnalyzer {

    /**
     * The result status of the model analyzer
     */
    public enum Status {
        /**
         * @uml.property name="rEADY"
         * @uml.associationEnd
         */
        READY, /**
                * @uml.property name="rUNNING"
                * @uml.associationEnd
                */
        RUNNING, /**
                  * @uml.property name="fINISHED"
                  * @uml.associationEnd
                  */
        FINISHED, /**
                   * @uml.property name="wAITING"
                   * @uml.associationEnd
                   */
        WAITING
    }

    /**
     * initialize the analyzer
     */
    public void init();

    /**
     * Execute the analyzer
     *
     * @param preferences
     *            The preferences for this analyzer
     * @param somoxConfiguration
     *            Configuration of this analyzer
     * @param extractionResultMap
     *            The map of extraction results
     * @return The analysis result object
     */
    public AnalysisResult analyze(SoMoXConfiguration somoxConfiguration,
            HashMap<String, ExtractionResult> extractionResultMap, IProgressMonitor progressMonitor)
                    throws ModelAnalyzerException;

    /**
     * Get the status from the analyzer
     *
     * @return The current status of the analyzer. Has to be one of the defined status in this
     *         interface
     */
    public ModelAnalyzer.Status getStatus();

    /**
     * Create an analysis result with newly initialized root models
     *
     * @param internalArchitectureModel
     * @return A new analysis result
     */
    default SimpleAnalysisResult initializeAnalysisResult() {
        final SimpleAnalysisResult analysisResult = new SimpleAnalysisResult(this);
        final SourceCodeDecoratorRepository sourceCodeDecoratorRepository = SourcecodedecoratorFactory.eINSTANCE
                .createSourceCodeDecoratorRepository();
        final org.palladiosimulator.pcm.system.System system = SystemFactory.eINSTANCE.createSystem();
        final QoSAnnotations qosAnnotationModel = QosannotationsFactory.eINSTANCE.createQoSAnnotations();
        final Repository newInternalArchitectureModel = RepositoryFactory.eINSTANCE.createRepository();
        final Allocation allocation = AllocationFactory.eINSTANCE.createAllocation();

        analysisResult.setInternalArchitectureModel(newInternalArchitectureModel);
        analysisResult.setSourceCodeDecoratorRepository(sourceCodeDecoratorRepository);
        analysisResult.setSystemModel(system);
        analysisResult.setQosAnnotationModel(qosAnnotationModel);
        analysisResult.setAllocation(allocation);

        return analysisResult;
    }
}
