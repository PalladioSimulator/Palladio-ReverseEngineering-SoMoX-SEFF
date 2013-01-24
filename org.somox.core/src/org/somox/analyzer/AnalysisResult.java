package org.somox.analyzer;

import java.util.List;

import org.somox.common.Message;

import org.somox.qimpressgast.GASTBehaviourRepository;
import eu.qimpress.samm.qosannotation.QosAnnotations;
import eu.qimpress.samm.staticstructure.Repository;
import eu.qimpress.samm.staticstructure.ServiceArchitectureModel;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

//import eu.qimpress.commonmodel.commonmodel.StaticStructure.Repository;

/**
 * The result of an analysis iteration.
 *
 * This result object contains all information about the last
 * model analyzer result like the status or the analyzed model
 *
 * @author Benjamin Klatt
 *
 */
public interface AnalysisResult {

	// ---------------------------------
	// Static Data Fields
	// ---------------------------------

	/**
	 * The result status of the analysis step
	 */
	public enum ResultStatus {/**
	 * @uml.property  name="nOT_EXECUTED"
	 * @uml.associationEnd  
	 */
	NOT_EXECUTED,/**
	 * @uml.property  name="sUCCESS"
	 * @uml.associationEnd  
	 */
	SUCCESS,/**
	 * @uml.property  name="fAILED"
	 * @uml.associationEnd  
	 */
	FAILED}

	// ---------------------------------
	// Business Methods
	// ---------------------------------

	// ---------------------------------
	// Getters / Setters
	// ---------------------------------

	/**
	 * The result status of the extraction.
	 * Will be one of the constants AnalysisResult.ResultStatus
	 * @return The status of the analysis
	 */
	public AnalysisResult.ResultStatus getResultStatus();


	/**
	 * Get  the Model Analyzer that was performed
	 * @return The model analyzer that has been executed
	 */
	public ModelAnalyzer getModelAnalyzer();

	/**
	 * Get the resulting internal architectural model
	 * @return Q-IMPRESS model repository.
	 */
	public Repository getInternalArchitectureModel();
	
	/**
	 * GAST behaviour repository to create
	 */
	public GASTBehaviourRepository getGastBehaviourRepository();

	/**
	 * Source code decorator to update
	 * @return
	 */
	public SourceCodeDecoratorRepository getSourceCodeDecoratorRepository();

	/**
	 * SAMM System (outer most component encapsulated).
	 */
	public ServiceArchitectureModel getServiceArchitectureModel();
	
	/**
	 * QoS Annotation model
	 */
	public QosAnnotations getQosAnnotationModel();

	/**
	 * Add a message object to the result
	 *
	 * @param message The message object
	 */
	public void addMessage(Message message);

	/**
	 * Get a list of all message objects assigned to this result
	 * @return List of all assigned message objects
	 */
	public List<Message> getMessages();

}
