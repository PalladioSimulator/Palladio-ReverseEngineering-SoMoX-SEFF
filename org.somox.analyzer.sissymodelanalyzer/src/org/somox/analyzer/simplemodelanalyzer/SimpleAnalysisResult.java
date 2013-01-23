package org.somox.analyzer.simplemodelanalyzer;

import java.util.LinkedList;
import java.util.List;

import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.ModelAnalyzer;
import org.somox.common.Message;

import eu.qimpress.qimpressgast.GASTBehaviourRepository;
import eu.qimpress.samm.qosannotation.QosAnnotations;
import eu.qimpress.samm.staticstructure.Repository;
import eu.qimpress.samm.staticstructure.ServiceArchitectureModel;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

//import eu.qimpress.commonmodel.commonmodel.StaticStructure.Repository;

/**
 * @author  Snowball
 */
public class SimpleAnalysisResult implements AnalysisResult {

	// ---------------------------------
	// Static Data Fields
	// ---------------------------------

	// ---------------------------------
	// Data fields
	// ---------------------------------

	/**
	 * The executed model analyzer
	 * @uml.property  name="modelAnalyzer"
	 * @uml.associationEnd  
	 */
	private ModelAnalyzer modelAnalyzer = null;

	/** 
	 * The result status
	 * @uml.property name="resultStatus"
	 * @uml.associationEnd 
	 */
	private ResultStatus resultStatus = AnalysisResult.ResultStatus.NOT_EXECUTED;

	/**
	 * the internal architecture model resulting from the performed analysis
	 * @uml.property  name="internalArchitectureModel"
	 * @uml.associationEnd  
	 */
	private Repository internalArchitectureModel = null;
	
	/**
	 * the gast behaviour repository
	 * @uml.property  name="gastBehaviourRepository"
	 * @uml.associationEnd  
	 */
	private GASTBehaviourRepository gastBehaviourRepository = null;
	
	/**
	 * the source code decorator repository
	 * @uml.property  name="sourceCodeDecoratorRepository"
	 * @uml.associationEnd  
	 */
	private SourceCodeDecoratorRepository sourceCodeDecoratorRepository = null;
	
	private ServiceArchitectureModel serviceArchitectureModel = null;
	
	private QosAnnotations qosAnnotationModel = null;

	/**
	 * The list of messages
	 * @uml.property  name="messages"
	 */
	private List<Message> messages = new LinkedList<Message>();

	// ---------------------------------
	// Constructor
	// ---------------------------------

	public SimpleAnalysisResult(ModelAnalyzer analyzer){
		this.modelAnalyzer = analyzer;
	}

	// ---------------------------------
	// Business Methods
	// ---------------------------------

	// ---------------------------------
	// Helper Methods
	// ---------------------------------

	// ---------------------------------
	// Getters / Setters
	// ---------------------------------

	/**
	 * @return
	 * @uml.property  name="modelAnalyzer"
	 */
	public ModelAnalyzer getModelAnalyzer() {
		return this.modelAnalyzer;
	}

	/** 
	 * @return
	 * @uml.property  name="resultStatus"
	 */
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	/**
	 * @return
	 * @uml.property  name="internalArchitectureModel"
	 */
	public Repository getInternalArchitectureModel() {
		return internalArchitectureModel;
	}
	


	/**
	 * @param internalArchitectureModel  the internalArchitectureModel to set
	 * @uml.property  name="internalArchitectureModel"
	 */
	public void setInternalArchitectureModel(Repository internalArchitectureModel) {
		this.internalArchitectureModel = internalArchitectureModel;
	}
	
	/**
	 * @return
	 * @uml.property  name="gastBehaviourRepository"
	 */
	public GASTBehaviourRepository getGastBehaviourRepository() {
		return gastBehaviourRepository;
	}

	/**
	 * @param gastBehaviourRepository
	 * @uml.property  name="gastBehaviourRepository"
	 */
	public void setGastBehaviourRepository(
			GASTBehaviourRepository gastBehaviourRepository) {
				this.gastBehaviourRepository = gastBehaviourRepository;
			}

	/**
	 * @return
	 * @uml.property  name="sourceCodeDecoratorRepository"
	 */
	public SourceCodeDecoratorRepository getSourceCodeDecoratorRepository() {
		return sourceCodeDecoratorRepository;
	}

	/**
	 * @param sourceCodeDecoratorRepository
	 * @uml.property  name="sourceCodeDecoratorRepository"
	 */
	public void setSourceCodeDecoratorRepository(
			SourceCodeDecoratorRepository sourceCodeDecoratorRepository) {
				this.sourceCodeDecoratorRepository = sourceCodeDecoratorRepository;
			}

	public void addMessage(Message message) {
		this.messages.add(message);
	}

	/**
	 * @return
	 * @uml.property  name="messages"
	 */
	public List<Message> getMessages() {
		return this.messages;
	}

	/** 
	 * @param status  the status to set
	 * @uml.property  name="resultStatus"
	 */
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	/*
	 * (non-Javadoc)
	 * @see org.somox.analyzer.AnalysisResult#getServiceArchitectureModel()
	 */
	public ServiceArchitectureModel getServiceArchitectureModel() {
		return this.serviceArchitectureModel;
	}
	
	public void setServiceArchitectureModel(
			ServiceArchitectureModel serviceArchitectureModel) {
		this.serviceArchitectureModel = serviceArchitectureModel;		
	}

	public QosAnnotations getQosAnnotationModel() {
		return qosAnnotationModel;
	}

	public void setQosAnnotationModel(QosAnnotations qosAnnotationModel) {
		this.qosAnnotationModel = qosAnnotationModel;
	}
	
}
