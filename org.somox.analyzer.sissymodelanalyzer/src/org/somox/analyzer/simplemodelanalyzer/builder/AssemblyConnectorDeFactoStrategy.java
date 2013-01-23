package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Collection;
import java.util.List;

import org.jgrapht.Graph;
import org.somox.metrics.ClusteringRelation;

import eu.qimpress.samm.staticstructure.CompositeComponent;
import eu.qimpress.samm.staticstructure.CompositeStructure;
import eu.qimpress.samm.staticstructure.InterfacePort;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Try to add assembly connectors for each edge in the subgraph of relations which formed the
 * parent composite component. Assembly connector are established along the graph structure.
 * @author Klaus Krogmann
 *
 */
public class AssemblyConnectorDeFactoStrategy implements IAssemblyConnectorStrategy {

	/**
	 * Default ctor.
	 * @param connectorBuilder The builder to use when actually creating instances of
	 * assembly connectors.
	 */
	public AssemblyConnectorDeFactoStrategy(AssemblyConnectorBuilder connectorBuilder) {
	}
	
	/* (non-Javadoc)
	 * @see org.somox.analyzer.simplemodelanalyzer.builder.IAssemblyConnectorStrategy#buildAssemblyConnectors(eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink, org.jgrapht.Graph)
	 */
	public void buildAssemblyConnectors(
			ComponentImplementingClassesLink compositeComponentCandidate,
			Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph) {
		if (!compositeComponentCandidate.isCompositeComponent()) {
			throw new IllegalArgumentException("Add assembly connectors not allowed for primitive components");
		}
		for (ClusteringRelation edge : compositeComponentSubgraph.edgeSet()) {
			probeAssemblyConnector(
					(CompositeComponent)compositeComponentCandidate.getComponent(),
					compositeComponentSubgraph.getEdgeSource(edge),
					compositeComponentSubgraph.getEdgeTarget(edge));
		}
	}
	
	/**
	 * Tries to establish an assembly connector for matching interfaces;
	 * i.e. a provided and required role match within a component. 
	 * @param component
	 * @param edgeSource
	 * @param edgeTarget
	 */
	private void probeAssemblyConnector(
			CompositeComponent component,
			ComponentImplementingClassesLink edgeSource,
			ComponentImplementingClassesLink edgeTarget) {
		Collection<InterfacePort> requiredRoles = edgeSource.getComponent().getRequired();
		Collection<InterfacePort> providedRoles = edgeTarget.getComponent().getProvided();
		for (InterfacePort requiredRole : requiredRoles) {
			for (InterfacePort providedRole : providedRoles) {
				if (requiredRole.getInterfaceType() == providedRole.getInterfaceType()) {
					AssemblyConnectorBuilder.createAssemblyConnector(
							component,
							requiredRole,
							providedRole,
							edgeSource.getComponent(),
							edgeTarget.getComponent());
				}
			}
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.somox.analyzer.simplemodelanalyzer.builder.IAssemblyConnectorStrategy#buildAssemblyConnectors(eu.qimpress.samm.staticstructure.ServiceArchitectureModel, java.util.List)
	 */
	public void buildAssemblyConnectors(
			CompositeStructure compositeStructure,
			List<ComponentImplementingClassesLink> subComponents) {
		
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}	
}
