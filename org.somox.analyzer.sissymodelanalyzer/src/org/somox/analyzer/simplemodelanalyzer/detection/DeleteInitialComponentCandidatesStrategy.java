package org.somox.analyzer.simplemodelanalyzer.detection;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.somox.analyzer.simplemodelanalyzer.SimpleAnalysisResult;
import org.somox.configuration.SoMoXConfiguration;

import de.uka.ipd.sdq.workflow.ExecutionTimeLoggingProgressMonitor;
import eu.qimpress.samm.behaviour.OperationBehaviour;
import eu.qimpress.samm.staticstructure.ComponentType;
import eu.qimpress.samm.staticstructure.Operation;
import eu.qimpress.samm.staticstructure.PrimitiveComponent;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.FileLevelSourceCodeLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;

/**
 * Deletes initial components which have been replaced by merged primitive components and converts
 * the kept initial components into real primitive component (is initial flag).
 * Cleans up SAMM and source code decorator.
 * <br>
 * Does expect that composite components have NO references to transitively contained inner classes.
 * @author Klaus Krogmann
 *
 */
public class DeleteInitialComponentCandidatesStrategy implements
		IPostComponentDetectionStrategy {
	
	/**
	 * The logger of this strategy
	 */
	private static Logger logger = Logger.getLogger(DeleteInitialComponentCandidatesStrategy.class);

	/*
	 * (non-Javadoc)
	 * @see org.somox.analyzer.simplemodelanalyzer.detection.IPostComponentDetectionStrategy#postComponentDetection(java.util.List, org.somox.configuration.SoMoXConfiguration)
	 */
	public void postComponentDetection(
			SoMoXConfiguration somoxConfiguration,
			SimpleAnalysisResult analysisResult,
			IProgressMonitor progressMonitor) {		
		
		IProgressMonitor subProgressMonitor = 
			new ExecutionTimeLoggingProgressMonitor(progressMonitor, 
					analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink().size());
		subProgressMonitor.beginTask("Post component detection", IProgressMonitor.UNKNOWN);
		logger.trace("Post component detection");
		
		Set<ComponentType> componentsToDelete = 
			new HashSet<ComponentType>();
		Set<ComponentImplementingClassesLink> componentLinksToDelete = 
			new HashSet<ComponentImplementingClassesLink>();
		boolean lastCollectedForDeletion = false;
		
		for(ComponentImplementingClassesLink componentLinkToCheck : analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink()) {

			if(componentLinkToCheck.isInitialComponent()) {
				for(ComponentImplementingClassesLink innerComponentLink : analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink()) {
					if(innerComponentLink != componentLinkToCheck &&
							innerComponentLink.getImplementingClasses().containsAll(componentLinkToCheck.getImplementingClasses()) ) {
						
						logger.trace("Deleting initial component: " + componentLinkToCheck.getComponent().getName());

						// collect entities to delete:
						componentLinksToDelete.add(componentLinkToCheck);
						componentsToDelete.add(componentLinkToCheck.getComponent());	
						
						lastCollectedForDeletion = true;
						break;
					}
				}
				
				if(!lastCollectedForDeletion) {
					//make a non-initial component
					componentLinkToCheck.setIsInitialComponent(false);
					lastCollectedForDeletion = false;
				}
			}			
			
			subProgressMonitor.worked(1);
		}						
		
		cleanUpGastBehaviour(analysisResult, componentLinksToDelete);	
		cleanUpSourceCodeDecorator(analysisResult, componentLinksToDelete);
		
		//Delete identified initial components:
		for(ComponentImplementingClassesLink compLink : componentLinksToDelete) {
			EcoreUtil.delete(compLink, true);
		}
		
		for(ComponentType comp : componentsToDelete) {
			EcoreUtil.delete(comp, true);
		}			
	
		subProgressMonitor.done();
	}

	private void cleanUpSourceCodeDecorator(
			SimpleAnalysisResult analysisResult,
			Set<ComponentImplementingClassesLink> componentLinksToDelete) {
		
		Set<FileLevelSourceCodeLink> fileLevelSourceCodeLinksToDelete = new HashSet<FileLevelSourceCodeLink>();
		Set<MethodLevelSourceCodeLink> methodLevelSourceCodeLinksToDelete = new HashSet<MethodLevelSourceCodeLink>();
		for(ComponentImplementingClassesLink compLink : componentLinksToDelete) {
			for(FileLevelSourceCodeLink sourceLink : analysisResult.getSourceCodeDecoratorRepository().getFileLevelSourceCodeLink()) {
				if(sourceLink.getComponentType().equals(compLink.getComponent())) {
					fileLevelSourceCodeLinksToDelete.add(sourceLink);
				}
			}
			for(MethodLevelSourceCodeLink sourceLink : analysisResult.getSourceCodeDecoratorRepository().getMethodLevelSourceCodeLink()) {
				if(sourceLink.getComponentType() != null && sourceLink.getComponentType().equals(compLink.getComponent())) {
					methodLevelSourceCodeLinksToDelete.add(sourceLink);
				}
			}			
		}
		for(FileLevelSourceCodeLink sourceCodeLink : fileLevelSourceCodeLinksToDelete) {
			EcoreUtil.delete(sourceCodeLink, true);
		}
		for(MethodLevelSourceCodeLink sourceCodeLink : methodLevelSourceCodeLinksToDelete) {
			EcoreUtil.delete(sourceCodeLink, true);
		}
		
	
	}

	/**
	 * Cleans up the gast behaviour: behaviour of deleted components is also removed
	 * @param analysisResult results containing the gast behaviour
	 * @param componentLinksToDelete component which are going to be deleted.
	 */
	private void cleanUpGastBehaviour(SimpleAnalysisResult analysisResult,
			Set<ComponentImplementingClassesLink> componentLinksToDelete) {
		
		Set<eu.qimpress.qimpressgast.GASTBehaviour> behavioursToDelete = new HashSet<eu.qimpress.qimpressgast.GASTBehaviour>();
		for(eu.qimpress.qimpressgast.GASTBehaviour gastBehaviour : analysisResult.getGastBehaviourRepository().getGastbehaviour()) {			
			Operation operation = gastBehaviour.getGastbehaviourstub().getOperation();			
			
			outer:
			for(ComponentImplementingClassesLink compLink : componentLinksToDelete) {
				for(OperationBehaviour operationBehaviour : compLink.getComponent().getOperationBehaviour()) {
					
					if(gastBehaviour.getGastbehaviourstub().eContainer() instanceof PrimitiveComponent) {
						PrimitiveComponent compOfBehaviour = (PrimitiveComponent)gastBehaviour.getGastbehaviourstub().eContainer();
						
						// must belong to right component & operation must fit
						if(operationBehaviour.getOperation().equals(operation) && compOfBehaviour.equals(compLink.getComponent())) { 
							// found a behaviour declared by component to be deleted

							behavioursToDelete.add(gastBehaviour);
							break outer;
						}												
					} else {
						logger.warn("Parent of gast behaviour stub should be a primitive component.");
					}					
					
				}
			}			
		}
		
		for(eu.qimpress.qimpressgast.GASTBehaviour gastBehaviour : behavioursToDelete) {
			logger.trace("deleting gast behaviour in decorator: " + gastBehaviour.getGastbehaviourstub().getName() + " " + gastBehaviour);
			EcoreUtil.delete(gastBehaviour, false);
		}
	}

}
