package org.somox.analyzer.simplemodelanalyzer.detection;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.somox.analyzer.simplemodelanalyzer.SimpleAnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.seff2javaast.SEFF2MethodMapping;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.FileLevelSourceCodeLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;

import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.repository.EventType;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.repository.SinkRole;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import de.uka.ipd.sdq.workflow.ExecutionTimeLoggingProgressMonitor;

/**
 * Deletes initial components which have been replaced by merged basic components and converts
 * the kept initial components into real basic component (is initial flag).
 * Cleans up palladio model and source code decorator.
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
		
		Set<RepositoryComponent> componentsToDelete = 
			new HashSet<RepositoryComponent>();
		Set<ComponentImplementingClassesLink> componentLinksToDelete = 
			new HashSet<ComponentImplementingClassesLink>();
		boolean lastCollectedForDeletion = false;
		
		for(ComponentImplementingClassesLink componentLinkToCheck : analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink()) {

			if(componentLinkToCheck.isIsInitialComponent()) {
				for(ComponentImplementingClassesLink innerComponentLink : analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink()) {
					if(innerComponentLink != componentLinkToCheck &&
							innerComponentLink.getImplementingClasses().containsAll(componentLinkToCheck.getImplementingClasses()) ) {
						
						logger.trace("Deleting initial component: " + componentLinkToCheck.getComponent().getEntityName());

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
		
		for(RepositoryComponent comp : componentsToDelete) {
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
				if(sourceLink.getRepositoryComponent().equals(compLink.getComponent())) {
					fileLevelSourceCodeLinksToDelete.add(sourceLink);
				}
			}
			for(MethodLevelSourceCodeLink sourceLink : analysisResult.getSourceCodeDecoratorRepository().getMethodLevelSourceCodeLink()) {
				if(sourceLink.getRepositoryComponent() != null && sourceLink.getRepositoryComponent().equals(compLink.getComponent())) {
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
		
		Set<SEFF2MethodMapping> mappingsToDelete = new HashSet<SEFF2MethodMapping>();
		for(SEFF2MethodMapping seff2MethodMapping : analysisResult.getSeff2JavaAST().getSeff2MethodMappings()) {			
			Signature signature = seff2MethodMapping.getSeff().getDescribedService__SEFF();			
			
			outer:
			for(ComponentImplementingClassesLink compLink : componentLinksToDelete) {
				for(ProvidedRole providedRole : compLink.getComponent().getProvidedRoles_InterfaceProvidingEntity()){
					
					if(providedRole instanceof OperationProvidedRole){
						OperationInterface operationInterface = ((OperationProvidedRole) providedRole).getProvidedInterface__OperationProvidedRole();
						for(OperationSignature operationSignature : operationInterface.getSignatures__OperationInterface()) {
							
							if(seff2MethodMapping.getSeff().eContainer() instanceof BasicComponent) {
								BasicComponent compOfBehaviour = (BasicComponent)seff2MethodMapping.getSeff().eContainer();
								
								// must belong to right component & operation must fit
								if(operationSignature.equals(signature) && compOfBehaviour.equals(compLink.getComponent())) { 
									// found a behaviour declared by component to be deleted
									mappingsToDelete.add(seff2MethodMapping);
									break outer;
								}												
							} else {
								logger.warn("Parent of gast behaviour stub should be a primitive component.");
							}					
						}
					} else if(providedRole instanceof SinkRole){
						EventGroup eventGroup = ((SinkRole) providedRole).getEventGroup__SinkRole();
						for(EventType eventType : eventGroup.getEventTypes__EventGroup()) {
							
							if(seff2MethodMapping.getSeff().eContainer() instanceof BasicComponent) {
								BasicComponent compOfBehaviour = (BasicComponent)seff2MethodMapping.getSeff().eContainer();
								
								// must belong to right component & operation must fit
								if(eventType.equals(signature) && compOfBehaviour.equals(compLink.getComponent())) { 
									// found a behaviour declared by component to be deleted
									mappingsToDelete.add(seff2MethodMapping);
									break outer;
								}												
							} else {
								logger.warn("Parent of gast behaviour stub should be a primitive component.");
							}					
						}
					} else {
						logger.warn("Unsupported operation type: "+providedRole.getEntityName()+" ["+providedRole.getClass().getSimpleName()+"]");
					}
				}
			}			
		}
		
		for(SEFF2MethodMapping seff2MethodMapping : mappingsToDelete) {
			ResourceDemandingSEFF seff = (ResourceDemandingSEFF) seff2MethodMapping.getSeff();
			//TODO burkha 16.05.2013 check if this make sense in logging
			logger.trace("deleting Seff2MethodMapping in decorator: " + seff.getId() + ": "+ seff.getDescribedService__SEFF().getEntityName() + " " + seff2MethodMapping);
			EcoreUtil.delete(seff2MethodMapping, false);
		}
	}

}
