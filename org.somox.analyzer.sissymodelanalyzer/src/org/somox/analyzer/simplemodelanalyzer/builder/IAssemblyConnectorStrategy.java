package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.List;

import org.jgrapht.Graph;
import org.somox.metrics.ClusteringRelation;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

import org.palladiosimulator.pcm.core.entity.ComposedProvidingRequiringEntity;

public interface IAssemblyConnectorStrategy {

	/**
	 * Create assembly connectors for the composite component candidate.
	 * @param compositeComponentCandidate The SAM model element representing the composite component found
	 * @param compositeComponentSubgraph The graph of relations between the subcomponents of the composite component 
	 * used when detecting the composite component
	 */
	public void buildAssemblyConnectors(
			ComponentImplementingClassesLink compositeComponentCandidate,
			Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph);
	
	/**
	 * Builder method for the SAMM system architecture. Creates internal assembly connectors.
	 * @param sammArchitecture The outer system
	 * @param subComponents The inner components for which to establish the connectors.
	 */
	public void buildAssemblyConnectors(ComposedProvidingRequiringEntity compositeStructure, 
			List<ComponentImplementingClassesLink> subComponents);

}