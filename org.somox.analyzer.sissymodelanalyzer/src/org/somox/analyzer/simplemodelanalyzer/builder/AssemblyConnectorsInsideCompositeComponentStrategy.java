package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.jgrapht.Graph;
import org.somox.metrics.ClusteringRelation;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

import de.uka.ipd.sdq.pcm.core.composition.Connector;
import de.uka.ipd.sdq.pcm.core.composition.ProvidedDelegationConnector;
import de.uka.ipd.sdq.pcm.repository.CompositeComponent;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.RepositoryComponent;
import de.uka.ipd.sdq.pcm.repository.RequiredRole;

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
		
		if(!(compositeComponentCandidate.getComponent() instanceof CompositeComponent)) {
			throw new IllegalArgumentException("must be a composite component!");
		}		
		CompositeComponent outerComposite = (CompositeComponent)compositeComponentCandidate.getComponent();
		establishAssemblyConnectorsForNonConnectedPorts(outerComposite, compositeComponentCandidate.getSubComponents());
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.somox.analyzer.simplemodelanalyzer.builder.IAssemblyConnectorStrategy#buildAssemblyConnectors(eu.qimpress.samm.staticstructure.ServiceArchitectureModel, java.util.List)
	 */
	public void buildAssemblyConnectors(RepositoryComponent compositeStructure, List<ComponentImplementingClassesLink> subComponents) {
		establishAssemblyConnectorsForNonConnectedPorts(compositeStructure, subComponents);
	}
	
	/**
	 * Establish assembly connector for remaining non-connected ports. These
	 * are connectors which cannot be enabled along with the clustering graph
	 * structure.
	 * @param compositeComponentCandidate
	 */
	private void establishAssemblyConnectorsForNonConnectedPorts(RepositoryComponent outerComposite,
			List<ComponentImplementingClassesLink> subComponents) {
		
		// loop required ports
		for(ComponentImplementingClassesLink firstComponent : subComponents) {
 			for(RequiredRole requiredPort : firstComponent.getComponent().getRequiredRoles_InterfaceRequiringEntity()) {

 				// connect only if not yet connected
 				if(!isBoundInConnector(outerComposite.getConnector(), requiredPort)) {
 					
	 				findMatchingProvidedPortAndCreateAssemblyConnector(
	 						subComponents, outerComposite,
							firstComponent, requiredPort);
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
			CompositeStructure outerComposite,
			ComponentImplementingClassesLink requiringComponent,
			InterfacePort requiredPort) {
		
		// loop provided ports and search for a match
		for(ComponentImplementingClassesLink providingComponent : subComponents) {
			for(InterfacePort providedPort : providingComponent.getComponent().getProvided()) {
				if(requiredPort.getInterfaceType().equals(providedPort.getInterfaceType())) {
					
					Connector newAssemblyConnector = AssemblyConnectorBuilder.createAssemblyConnector(
								outerComposite,
								requiredPort,
								providedPort,
								requiringComponent.getComponent(),
								providingComponent.getComponent());
					outerComposite.getConnector().add(newAssemblyConnector);
				}
			}
		}
	}

	/**
	 * Checks existing connectors for the involvement of a certain port.
	 * @param connectors connectors to search in
	 * @param port the port to find
	 * @return
	 */
	private boolean isBoundInConnector(EList<ProvidedDelegationConnector> connectors,
			ProvidedRole providedRole) {
		for(Connector currentConnectors : connectors) {
			for(EndPoint endpoint : currentConnectors.getEndpoints()) {
				if(endpoint.getPort().equals(port)) {
					return true;
				}
			}
		}
		
		return false;
	}

}
