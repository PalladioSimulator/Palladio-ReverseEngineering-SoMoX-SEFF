package org.somox.analyzer.simplemodelanalyzer.builder;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.detection.util.EqualityChecker;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;

//import de.fzi.gast.core.Root;
//import de.fzi.gast.functions.Function;
//import de.fzi.gast.functions.Method;
//import de.fzi.gast.statements.BlockStatement;
//import de.fzi.gast.types.GASTClass;
import org.somox.qimpressgast.GASTBehaviour;
import org.somox.qimpressgast.qimpressgastFactory;
import eu.qimpress.samm.behaviour.BehaviourFactory;
import eu.qimpress.samm.behaviour.GastBehaviourStub;
import eu.qimpress.samm.staticstructure.ComponentType;
import eu.qimpress.samm.staticstructure.InterfacePort;
import eu.qimpress.samm.staticstructure.Operation;
import eu.qimpress.samm.staticstructure.PrimitiveComponent;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;

/**
 * Builder used to add GAST behaviour to methods detected as provided operations of components
 * 
 * @author Steffen Becker, Michael Hauck
 */
public class GASTBehaviourBuilder extends AbstractBuilder {
	
	private static final Logger logger = Logger.getLogger(GASTBehaviourBuilder.class);
	
	/**
	 * Constructor of the GAST behaviour builder
	 * @param gastModel GAST model used for queries to the source code representation
	 * @param somoxConfiguration Somox configuaration used to retrieve settings
	 * @param analysisResult Contains the root model elemts used to store the generated model elements
	 */
	public GASTBehaviourBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration, AnalysisResult analysisResult) {
		super(gastModel, somoxConfiguration, analysisResult);
	}	
	
	/**
	 * Adds GAST behaviours to the given primitive component for all methods passed in the publicMethods parameter
	 * @param component The component to which the behaviour will be added
	 * @param providedRole The provided role for which each of its operations is to be added. 
	 */
	public void addGASTBehaviourToPrimitiveComponent(PrimitiveComponent component, InterfacePort providedRole) {		
		for (Operation operation : providedRole.getInterfaceType().getSignatures()) {
			addGASTBehaviourToPrimitiveComponent(component, operation);
		}
	}
	
	/**
	 * 
	 * @param component The component to add the GAST behaviour for
	 * @param operation The interface operation
	 */
	private void addGASTBehaviourToPrimitiveComponent(
			PrimitiveComponent component, Operation operation) {
		
		MethodLevelSourceCodeLink link = getMethodLevelSourceCodeLink(operation); 

		if (link == null) {
			throw new RuntimeException("Found interface with operations for which no method link exists. This should never happen!");
		}
		
		link.setComponentType(component);

		this.analysisResult.getSourceCodeDecoratorRepository().getMethodLevelSourceCodeLink().add(link);
	
		GastBehaviourStub gastBehaviourStub = BehaviourFactory.eINSTANCE.createGastBehaviourStub();
		gastBehaviourStub.setOperation(link.getOperation());
		GASTBehaviour gastBehaviour = qimpressgastFactory.eINSTANCE.createGASTBehaviour();
		component.getOperationBehaviour().add(gastBehaviourStub);
		
		// links steems from interface; thus get component-specific implementation:
		Block methodBody = getFunctionImplementation(link.getFunction(), findComponenentLink(component));
		
		gastBehaviour.setBlockstatement(methodBody); 
		if (gastBehaviour.getBlockstatement() == null || gastBehaviour.getBlockstatement().getStatements().size() == 0) {
			logger.warn("Empty behaviour added for " + gastBehaviourStub.getName() + 
					"! Reverse engineering of behaviour will NOT be able to succeed for this method!");
		}
		gastBehaviour.setGastbehaviourstub(gastBehaviourStub);
		this.analysisResult.getSEFF2JavaAST().getGastbehaviour().add(gastBehaviour);
		
	}

	/**
	 * Finds a implementation block statement for the function realised
	 * by the passed component.
	 * @param function interface function
	 * @param component The component to find the method implementation for
	 * @return The block statement realising the function for the component; null in a case of error.
	 */
	private Block getFunctionImplementation(AbstractMethodDeclaration function,
			ComponentImplementingClassesLink component) {		
		
		for(Type implementingClass : component.getImplementingClasses()) {
			for(MethodDeclaration implementedMethod : KDMHelper.getMethods(implementingClass)) {				
				if(EqualityChecker.areFunctionsEqual(function, implementedMethod)) { //FIXME: check why equal fails 
				//if(implementedMethod.equals(function)) {
					return implementedMethod.getBody();
				}				
			}
		}

		logger.error("No method implemementation found for method " + function.getName() + " for component " + component.getComponent().getName());
		return null;
	}

	
	/**
	 * Reverse lookup for component to componentLink.
	 * @param component
	 * @return ComponentLink for component.
	 */
	private ComponentImplementingClassesLink findComponenentLink(ComponentType component) {
		for(ComponentImplementingClassesLink compLink : this.analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink()) {
			if(compLink.getComponent().equals(component)) {
				return compLink;
			}
		}
		logger.error("No component link found for component " + component.getName());
		return null; 
	}

	private MethodLevelSourceCodeLink getMethodLevelSourceCodeLink(Operation operation) {
		assert operationUnique(operation, this.analysisResult.getSourceCodeDecoratorRepository().getMethodLevelSourceCodeLink());
		for (MethodLevelSourceCodeLink link : this.analysisResult.getSourceCodeDecoratorRepository().getMethodLevelSourceCodeLink()) {
			if (operation == link.getOperation())
				return link;
		}
		return null;
	}

	/**
	 * For assertion only
	 * @param operation
	 * @param methodLevelSourceCodeLink
	 * @return
	 */
	private boolean operationUnique(Operation operation,
			EList<MethodLevelSourceCodeLink> methodLevelSourceCodeLink) {
		int i = 0;
		for (MethodLevelSourceCodeLink link : this.analysisResult.getSourceCodeDecoratorRepository().getMethodLevelSourceCodeLink()) {
			if (operation == link.getOperation()) {
				i++;
			}				
		}
		return (i == 1);
	}
}
