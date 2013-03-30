package org.somox.analyzer.simplemodelanalyzer.builder;


import org.apache.log4j.Logger;
import org.somox.analyzer.AnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyConnector;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.ComposedStructure;
import de.uka.ipd.sdq.pcm.core.composition.CompositionFactory;
import de.uka.ipd.sdq.pcm.core.composition.Connector;
import de.uka.ipd.sdq.pcm.core.entity.NamedElement;
import de.uka.ipd.sdq.pcm.repository.OperationProvidedRole;
import de.uka.ipd.sdq.pcm.repository.OperationRequiredRole;
//import de.fzi.gast.core.Root;

/**
 * Builder for Assembly Connectors.
 * This builder is complemented by {@link IAssemblyConnectorStrategy}
 * @author Klaus Krogmann
 */
public class AssemblyConnectorBuilder extends AbstractBuilder {

	private static final Logger logger = Logger.getLogger(AssemblyConnectorBuilder.class);
	
	public AssemblyConnectorBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration, AnalysisResult analysisResult) {
		super(gastModel, somoxConfiguration, analysisResult);
	}

	/**
	 * Create a new instance of an assembly connector.
	 * @param parentComponent the outer composite component
	 * @param requiredPort the required port of an inner component
	 * @param providedPort the provided port of an inner component
	 * @param requiredComponentInstance
	 * @param providedComponentInstance
	 */
	/*public static Connector createAssemblyConnector(
			ComposedStructure parentComponent,
			OperationRequiredRole requiredPort,
			OperationProvidedRole providedRole,
			ComposedProvidingRequiringEntity requiredComponentInstance,
			ComposedProvidingRequiringEntity providedComponentInstance) {

		AssemblyConnector newConnector = createAssemblyConnectorEntity(parentComponent,
				requiredComponentInstance, providedComponentInstance);
		
		newConnector.setProvidedRole_AssemblyConnector(providedRole);
		newConnector.setRequiredRole_AssemblyConnector(requiredPort);
		newConnector.setProvidingAssemblyContext_AssemblyConnector(providedComponentInstance);
		newConnector.setRequiringAssemblyContext_AssemblyConnector(requiredComponentInstance);
		
		return newConnector;		
	}*/
	
	/**
	 * Create a new instance of an assembly connector.
	 * @param parentComponent the outer composite component
	 * @param requiredPort the required port of an inner component
	 * @param providedPort the provided port of an inner component
	 * @param requiredComponentType
	 * @param providedComponentType
	 */
	public static Connector createAssemblyConnector(
			ComposedStructure parentComponent,
			OperationRequiredRole requiredRole,
			OperationProvidedRole providedRole,
			AssemblyContext requiredComponentType,
			AssemblyContext providedComponentType) {
		
		AssemblyConnector newConnector = createAssemblyConnectorEntity(parentComponent, 
						requiredComponentType, providedComponentType);
		
		newConnector.setProvidedRole_AssemblyConnector(providedRole);
		newConnector.setRequiredRole_AssemblyConnector(requiredRole);
		newConnector.setProvidingAssemblyContext_AssemblyConnector(providedComponentType);
		newConnector.setRequiringAssemblyContext_AssemblyConnector(requiredComponentType);
		
		return newConnector;
	}

	private static AssemblyConnector createAssemblyConnectorEntity(
			ComposedStructure parentComponent,
			NamedElement requiredComponentType,
			NamedElement providedComponentType) {
		logger.debug("Creating new assembly connector from "+requiredComponentType.getEntityName()+" to "+providedComponentType.getEntityName());		
		AssemblyConnector newConnector = CompositionFactory.eINSTANCE.createAssemblyConnector();
		parentComponent.getConnectors__ComposedStructure().add(newConnector);
		//newConnector.setDocumentation("Assembly Connector from "+requiredComponentType.getEntityName()+" to "+providedComponentType.getEntityName());
		return newConnector;
	}	

	
//	public static SubcomponentInstance findComponentInstance(
//			CompositeStructure parentComponent,
//			ComponentType componentTypeOfPort) {
//		SubcomponentInstance resultSubcomponentInstance = null;
//		boolean found = false;
//		for(SubcomponentInstance subcomponentInstance : parentComponent.getSubcomponents()) {
//			
//			if(subcomponentInstance.getRealizedBy().equals(componentTypeOfPort)) {
//				if(found) {
//					throw new IllegalArgumentException("Assumption on input model does not hold. " +
//							"Only one instance per component type per composite component assumed!");
//				}
//				resultSubcomponentInstance = subcomponentInstance;
//				found = true;
//			}
//		}
//		if(!found) {
//			logger.warn("No subcomponent instance found for parent " + parentComponent.getName() +
//					" and child component " + componentTypeOfPort.getName());
//		}
//		return resultSubcomponentInstance;
//		
//	}

}
