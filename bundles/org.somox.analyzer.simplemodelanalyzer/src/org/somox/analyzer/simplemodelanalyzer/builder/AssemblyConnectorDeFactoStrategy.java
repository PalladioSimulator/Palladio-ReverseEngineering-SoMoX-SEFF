package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.jgrapht.Graph;
import org.palladiosimulator.pcm.core.entity.ComposedProvidingRequiringEntity;
import org.palladiosimulator.pcm.repository.CompositeComponent;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.somox.metrics.ClusteringRelation;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Try to add assembly connectors for each edge in the subgraph of relations which formed the parent
 * composite component. Assembly connector are established along the graph structure.
 *
 * @author Klaus Krogmann
 *
 */
public class AssemblyConnectorDeFactoStrategy implements IAssemblyConnectorStrategy {

    private static final Logger logger = Logger.getLogger(AssemblyConnectorDeFactoStrategy.class);

    /**
     * Default ctor.
     *
     * @param connectorBuilder
     *            The builder to use when actually creating instances of assembly connectors.
     */
    public AssemblyConnectorDeFactoStrategy(final AssemblyConnectorBuilder connectorBuilder) {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.analyzer.simplemodelanalyzer.builder.IAssemblyConnectorStrategy#
     * buildAssemblyConnectors(eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink,
     * org.jgrapht.Graph)
     */
    @Override
    public void buildAssemblyConnectors(final ComponentImplementingClassesLink compositeComponentCandidate,
            final Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph) {
        if (!compositeComponentCandidate.isIsCompositeComponent()) {
            throw new IllegalArgumentException("Add assembly connectors not allowed for primitive components");
        }
        for (final ClusteringRelation edge : compositeComponentSubgraph.edgeSet()) {
            this.probeAssemblyConnector((CompositeComponent) compositeComponentCandidate.getComponent(),
                    compositeComponentSubgraph.getEdgeSource(edge), compositeComponentSubgraph.getEdgeTarget(edge));
        }
    }

    /**
     * Tries to establish an assembly connector for matching interfaces; i.e. a provided and
     * required role match within a component.
     *
     * @param component
     * @param edgeSource
     * @param edgeTarget
     */
    private void probeAssemblyConnector(final CompositeComponent component,
            final ComponentImplementingClassesLink edgeSource, final ComponentImplementingClassesLink edgeTarget) {
        final Collection<RequiredRole> requiredRoles = edgeSource.getComponent()
                .getRequiredRoles_InterfaceRequiringEntity();
        final Collection<ProvidedRole> providedRoles = edgeTarget.getComponent()
                .getProvidedRoles_InterfaceProvidingEntity();
        for (final RequiredRole requiredRole : requiredRoles) {
            for (final ProvidedRole providedRole : providedRoles) {
                logger.debug("compare reqrole.getReqEnt and prorole.getProEnt");
                logger.debug("reqrole.getReqEnt = " + requiredRole.getRequiringEntity_RequiredRole());
                logger.debug("prorole.getProEnt = " + providedRole.getProvidingEntity_ProvidedRole());
                if (requiredRole instanceof OperationRequiredRole && providedRole instanceof OperationProvidedRole) {
                    final OperationRequiredRole opReqRole = (OperationRequiredRole) requiredRole;
                    final OperationProvidedRole opProvRole = (OperationProvidedRole) providedRole;
                    if (opReqRole.getRequiredInterface__OperationRequiredRole()
                            .equals(opProvRole.getProvidedInterface__OperationProvidedRole())) {
                        AssemblyConnectorBuilder.createAssemblyConnector(component, opReqRole, opProvRole,
                                edgeSource.getComponent(), edgeTarget.getComponent());
                    }
                } else {
                    logger.warn("Role types: " + providedRole.getClass().getSimpleName() + " and "
                            + requiredRole.getClass().getSimpleName() + " not yet supported.");
                }
            }
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.analyzer.simplemodelanalyzer.builder.IAssemblyConnectorStrategy#
     * buildAssemblyConnectors(eu.qimpress.samm.staticstructure.ServiceArchitectureModel,
     * java.util.List)
     */
    @Override
    public void buildAssemblyConnectors(final ComposedProvidingRequiringEntity compositeStructure,
            final List<ComponentImplementingClassesLink> subComponents) {

        throw new RuntimeException("NOT IMPLEMENTED");

    }
}
