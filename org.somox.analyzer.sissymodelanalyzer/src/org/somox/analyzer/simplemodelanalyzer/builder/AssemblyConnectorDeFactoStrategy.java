package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.jgrapht.Graph;
import org.somox.metrics.ClusteringRelation;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

import de.uka.ipd.sdq.pcm.core.entity.ComposedProvidingRequiringEntity;
import de.uka.ipd.sdq.pcm.repository.CompositeComponent;
import de.uka.ipd.sdq.pcm.repository.OperationProvidedRole;
import de.uka.ipd.sdq.pcm.repository.OperationRequiredRole;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.RequiredRole;

/**
 * Try to add assembly connectors for each edge in the subgraph of relations which formed the
 * parent composite component. Assembly connector are established along the graph structure.
 * @author Klaus Krogmann
 *
 */
public class AssemblyConnectorDeFactoStrategy implements IAssemblyConnectorStrategy {

	private static final Logger logger = Logger.getLogger(AssemblyConnectorDeFactoStrategy.class);
	
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
		if (!compositeComponentCandidate.isIsCompositeComponent()) {
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
		Collection<RequiredRole> requiredRoles = edgeSource.getComponent().getRequiredRoles_InterfaceRequiringEntity();
		Collection<ProvidedRole> providedRoles = edgeTarget.getComponent().getProvidedRoles_InterfaceProvidingEntity();
		for (RequiredRole requiredRole : requiredRoles) {
			for (ProvidedRole providedRole : providedRoles) {
				logger.debug("compare reqrole.getReqEnt and prorole.getProEnt");
				logger.debug("reqrole.getReqEnt = " + requiredRole.getRequiringEntity_RequiredRole());
				logger.debug("prorole.getProEnt = " + providedRole.getProvidingEntity_ProvidedRole());
				if(requiredRole instanceof OperationRequiredRole && providedRole instanceof OperationProvidedRole){
					OperationRequiredRole opReqRole = (OperationRequiredRole) requiredRole;
					OperationProvidedRole opProvRole = (OperationProvidedRole) providedRole;
					if (opReqRole.getRequiredInterface__OperationRequiredRole().equals(opProvRole.getProvidedInterface__OperationProvidedRole())) {
						AssemblyConnectorBuilder.createAssemblyConnector(
								component,
								opReqRole,
								opProvRole,
								edgeSource.getComponent(),
								edgeTarget.getComponent());
					}
				} else {
					logger.warn("Role types: "
							+ providedRole.getClass().getSimpleName()
							+ " and "
							+ requiredRole.getClass().getSimpleName()
							+ " not yet supported.");
				}
			}
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.somox.analyzer.simplemodelanalyzer.builder.IAssemblyConnectorStrategy#buildAssemblyConnectors(eu.qimpress.samm.staticstructure.ServiceArchitectureModel, java.util.List)
	 */
	public void buildAssemblyConnectors(
			ComposedProvidingRequiringEntity compositeStructure,
			List<ComponentImplementingClassesLink> subComponents) {
		
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}
}
