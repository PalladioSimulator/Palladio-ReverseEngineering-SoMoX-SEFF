package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.jgrapht.Graph;
import org.somox.metrics.ClusteringRelation;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.core.entity.ComposedProvidingRequiringEntity;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.RequiredRole;

/**
 * Strategy: Prefer establishing assembly connectors INSIDE a composite
 * component instead of delegating to required ports.
 * <br>
 * Establish assembly connector for remaining non-connected ports. These
 * are connectors which cannot be enabled along with the clustering graph
 * structure.
 * @author Klaus Krogmann
 */
public class AssemblyConnectorsInsideCompositeComponentStrategy implements IAssemblyConnectorStrategy {

	private static final Logger logger = Logger.getLogger(AssemblyConnectorsInsideCompositeComponentStrategy.class);
	
	/**
	 * Outdated ctor. Use argument-less ctor instead.
	 * @param connectorBuilder The builder to use when actually creating instances of
	 * assembly connectors.
	 */	
	@Deprecated
	public AssemblyConnectorsInsideCompositeComponentStrategy(AssemblyConnectorBuilder connectorBuilder) {

	}
	
	/**
	 * Default ctor.
	 */	
	public AssemblyConnectorsInsideCompositeComponentStrategy() {

	}	
	
	/*
	 * (non-Javadoc)
	 * @see org.somox.analyzer.simplemodelanalyzer.builder.IAssemblyConnectorStrategy#buildAssemblyConnectors(eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink, org.jgrapht.Graph)
	 */
	public void buildAssemblyConnectors(
			ComponentImplementingClassesLink compositeComponentCandidate,
			Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph) {
		
		if(!(compositeComponentCandidate.getComponent() instanceof ComposedProvidingRequiringEntity)) {
			throw new IllegalArgumentException("must be a ComposedProvidingRequiringEntity!");
		}		
		ComposedProvidingRequiringEntity outerComposite = (ComposedProvidingRequiringEntity)compositeComponentCandidate.getComponent();
		establishAssemblyConnectorsForNonConnectedPorts(outerComposite, compositeComponentCandidate.getSubComponents());
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.somox.analyzer.simplemodelanalyzer.builder.IAssemblyConnectorStrategy#buildAssemblyConnectors(eu.qimpress.samm.staticstructure.ServiceArchitectureModel, java.util.List)
	 */
	public void buildAssemblyConnectors(ComposedProvidingRequiringEntity compositeStructure, List<ComponentImplementingClassesLink> subComponents) {
		establishAssemblyConnectorsForNonConnectedPorts(compositeStructure, subComponents);
	}
	
	/**
	 * Establish assembly connector for remaining non-connected ports. These
	 * are connectors which cannot be enabled along with the clustering graph
	 * structure.
	 * @param compositeComponentCandidate
	 */
	private void establishAssemblyConnectorsForNonConnectedPorts(ComposedProvidingRequiringEntity outerComposite,
			List<ComponentImplementingClassesLink> subComponents) {
		// loop required ports
		// TODO burkha 24.04.2013 check here for change
		for(ComponentImplementingClassesLink component : subComponents) {
 			for(RequiredRole requiredRole : component.getComponent().getRequiredRoles_InterfaceRequiringEntity()) {
				if (!isBoundInConnector(outerComposite.getConnectors__ComposedStructure(), requiredRole)) {
 	 					findMatchingProvidedPortAndCreateAssemblyConnector(
 		 						subComponents, outerComposite,
 								component, requiredRole);
 				} 				
 			}
		}
	}

	/**
	 * Find a matching provided port (if any) and create a new
	 * assembly connector between require port and found provided port
	 * and set assembly connector for composite component.
	 * <br>
	 * Searches a provided port only inside the composite component.
	 * @param compositeComponentCandidate
	 * @param outerComposite
	 * @param requiringComponent
	 * @param requiredPort
	 */
	private void findMatchingProvidedPortAndCreateAssemblyConnector(
			List<ComponentImplementingClassesLink> subComponents,
			ComposedStructure outerComposite,
			ComponentImplementingClassesLink requiringComponent,
			RequiredRole requiredRole) {
		
		// loop provided ports and search for a match
		for(ComponentImplementingClassesLink providingComponent : subComponents) {
			for(ProvidedRole providedRole : providingComponent.getComponent().getProvidedRoles_InterfaceProvidingEntity()) {
				if (requiredRole instanceof OperationRequiredRole && providedRole instanceof OperationProvidedRole) {
					OperationRequiredRole opReqRole = (OperationRequiredRole) requiredRole;
					OperationProvidedRole opProvRole = (OperationProvidedRole) providedRole;
					if (opReqRole.getRequiredInterface__OperationRequiredRole().equals(
							opProvRole.getProvidedInterface__OperationProvidedRole())) {
						Connector newAssemblyConnector = AssemblyConnectorBuilder.createAssemblyConnector(
								outerComposite,
								opReqRole,
								opProvRole,
								requiringComponent.getComponent(),
								providingComponent.getComponent());
						outerComposite.getConnectors__ComposedStructure().add(newAssemblyConnector);

					}
				} else {
					logger.warn("Provided role type: " + providedRole.getClass().getSimpleName()
							+ " and required role type: " + requiredRole.getClass().getSimpleName()
							+ " not yet supported");
				}
			}
		}
	}

	/**
	 * Checks existing connectors for the involvement of a certain port.
	 * 
	 * @param connectors
	 *            connectors to search in
	 * @param requiredRole
	 *            the RequiredRole to find
	 * @return
	 */
	private boolean isBoundInConnector(EList<Connector> connectors, RequiredRole requiredRole) {
		for (Connector connector : connectors) {
			if (connector instanceof AssemblyConnector) {
				AssemblyConnector assemblyConnector = (AssemblyConnector) connector;
				if (assemblyConnector.getRequiredRole_AssemblyConnector().equals(requiredRole)) {
					return true;
				}
			} else {
				logger.warn("Connector type " + connector.getClass().getSimpleName() + " not yet supported.");
			}
		}
		return false;
	}

}
