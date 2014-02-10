package org.somox.analyzer.simplemodelanalyzer.builder;

import org.apache.log4j.Logger;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.types.Type;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.detection.util.EqualityChecker;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.seff2javaast.SEFF2JavaAST;
import org.somox.seff2javaast.SEFF2MethodMapping;
import org.somox.seff2javaast.Seff2javaastFactory;
//import org.somox.seff2javaast.Seff2methodFactory;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;

import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.EventGroup;
import de.uka.ipd.sdq.pcm.repository.EventType;
import de.uka.ipd.sdq.pcm.repository.OperationInterface;
import de.uka.ipd.sdq.pcm.repository.OperationProvidedRole;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.RepositoryComponent;
import de.uka.ipd.sdq.pcm.repository.Signature;
import de.uka.ipd.sdq.pcm.repository.SinkRole;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.seff.SeffFactory;

/**
 * Builder used to add GAST behaviour to methods detected as provided operations of components
 * 
 * @author Steffen Becker, Michael Hauck
 */
public class Seff2JavaASTBuilder extends AbstractBuilder {
	
	private static final Logger logger = Logger.getLogger(Seff2JavaASTBuilder.class);
	
	/**
	 * Constructor of the GAST behaviour builder
	 * @param gastModel GAST model used for queries to the source code representation
	 * @param somoxConfiguration Somox configuaration used to retrieve settings
	 * @param analysisResult Contains the root model elemts used to store the generated model elements
	 */
	public Seff2JavaASTBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration, AnalysisResult analysisResult) {
		super(gastModel, somoxConfiguration, analysisResult);
	}	
	
	/**
	 * Add seffs to the given basic component for all methods passed in the provided roles parameter
	 * @param component The component to which the behaviour will be added
	 * @param providedRole The provided role for which each of its operations is to be added. 
	 */
	public void addSeffsToPrimitiveComponent(BasicComponent component, ProvidedRole providedRole) {	
		if(providedRole instanceof OperationProvidedRole){
			OperationInterface providedInterface = ((OperationProvidedRole) providedRole).getProvidedInterface__OperationProvidedRole();
			for (OperationSignature signature : providedInterface.getSignatures__OperationInterface()) {
				addSeffToBasicComponent(component, signature);
			}
		} else if(providedRole instanceof SinkRole){
			EventGroup providedInterface = ((SinkRole) providedRole).getEventGroup__SinkRole();
			for (EventType signature : providedInterface.getEventTypes__EventGroup()) {
				addSeffToBasicComponent(component, signature);
			}
		}
	}
	
	/**
	 * Add a seff to a basic component including the mapping to the method in the AST model.
	 * @param component The component to add the seff for
	 * @param operation The interface operation
	 */
	private void addSeffToBasicComponent(
			BasicComponent component, Signature operation) {
		
		MethodLevelSourceCodeLink link = getMethodLevelSourceCodeLink(operation); 

		if (link == null) {
			throw new RuntimeException("Found interface with operations for which no method link exists. This should never happen!");
		}
		
		link.setRepositoryComponent(component);

		this.analysisResult.getSourceCodeDecoratorRepository().getMethodLevelSourceCodeLink().add(link);
	
		ResourceDemandingSEFF seff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
		//TODO burkha 22.05.2013 this can violate a OCL constraint, when there is more than one seff implementing the same signature
		if(link.getOperation().getEntityName().equals("refresh")){
			int a = 0;
		}
		seff.setDescribedService__SEFF(link.getOperation());
		SEFF2MethodMapping seff2MethodMapping = Seff2javaastFactory.eINSTANCE.createSEFF2MethodMapping();
		component.getServiceEffectSpecifications__BasicComponent().add(seff);
		
		// links steems from interface; thus get component-specific implementation:
		Block methodBody = getFunctionImplementation((Method) link.getFunction(), findComponenentLink(component));
		
		seff2MethodMapping.setBlockstatement(methodBody); 
		if (seff2MethodMapping.getBlockstatement() == null || seff2MethodMapping.getBlockstatement().getStatements().size() == 0) {
			logger.warn("Empty behaviour added for " + seff.getDescribedService__SEFF().getEntityName() + 
					"! Reverse engineering of behaviour will NOT be able to succeed for this method!");
		}
		seff2MethodMapping.setSeff(seff);
		this.analysisResult.getSeff2JavaAST().getSeff2MethodMappings().add(seff2MethodMapping);
		
	}

	/**
	 * Finds a implementation block statement for the function realised
	 * by the passed component.
	 * @param function interface function
	 * @param component The component to find the method implementation for
	 * @return The block statement realising the function for the component; null in a case of error.
	 */
	private Block getFunctionImplementation(Method function,
			ComponentImplementingClassesLink component) {		
		
		for(Type implementingClass : component.getImplementingClasses()) {
			for(Method implementedMethod : KDMHelper.getMethods(implementingClass)) {				
				if(EqualityChecker.areFunctionsEqual(function, implementedMethod)) { //FIXME: check why equal fails 
				//if(implementedMethod.equals(function)) {
					return KDMHelper.getBody( implementedMethod);
				}				
			}
		}

		logger.error("No method implemementation found for method " + function.getName() + " for component " + component.getComponent().getEntityName());
		return null;
	}

	
	/**
	 * Reverse lookup for component to componentLink.
	 * @param component
	 * @return ComponentLink for component.
	 */
	private ComponentImplementingClassesLink findComponenentLink(RepositoryComponent component) {
		for(ComponentImplementingClassesLink compLink : this.analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink()) {
			if(compLink.getComponent().equals(component)) {
				return compLink;
			}
		}
		logger.error("No component link found for component " + component.getEntityName());
		return null; 
	}

	private MethodLevelSourceCodeLink getMethodLevelSourceCodeLink(Signature operation) {
		assert operationUnique(operation);
		for (MethodLevelSourceCodeLink link : this.analysisResult.getSourceCodeDecoratorRepository().getMethodLevelSourceCodeLink()) {
			if (operation == link.getOperation()){
				return link;
			}
		}
		return null;
	}

	/**
	 * Check if an operation is already present in the source code decorator repository.
	 * 
	 * Attention: For assertion only!
	 * 
	 * @param signature The signature to look up in the repository.
	 * @return true/false whether already present or not.
	 */
	private boolean operationUnique(Signature signature) {
		boolean alreadyFound = false;
		for (MethodLevelSourceCodeLink link : this.analysisResult.getSourceCodeDecoratorRepository().getMethodLevelSourceCodeLink()) {
			if (signature == link.getOperation()) {
				
				if(alreadyFound){
					return false;
				}
				
				alreadyFound = true;
			}				
		}
		return alreadyFound;
	}
}
