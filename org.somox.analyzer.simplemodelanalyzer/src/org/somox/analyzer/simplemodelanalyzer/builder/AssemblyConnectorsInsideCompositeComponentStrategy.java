package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.jgrapht.Graph;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.core.composition.ProvidedDelegationConnector;
import org.palladiosimulator.pcm.core.composition.RequiredDelegationConnector;
import org.palladiosimulator.pcm.core.entity.ComposedProvidingRequiringEntity;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.somox.metrics.ClusteringRelation;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Strategy: Prefer establishing assembly connectors INSIDE a composite component instead of
 * delegating to required ports. <br>
 * Establish assembly connector for remaining non-connected ports. These are connectors which cannot
 * be enabled along with the clustering graph structure.
 *
 * @author Klaus Krogmann
 */
public class AssemblyConnectorsInsideCompositeComponentStrategy implements IAssemblyConnectorStrategy {

    private static final Logger logger = Logger.getLogger(AssemblyConnectorsInsideCompositeComponentStrategy.class);

    /**
     * Outdated ctor. Use argument-less ctor instead.
     *
     * @param connectorBuilder
     *            The builder to use when actually creating instances of assembly connectors.
     */
    @Deprecated
    public AssemblyConnectorsInsideCompositeComponentStrategy(final AssemblyConnectorBuilder connectorBuilder) {

    }

    /**
     * Default ctor.
     */
    public AssemblyConnectorsInsideCompositeComponentStrategy() {

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

        if (!(compositeComponentCandidate.getComponent() instanceof ComposedProvidingRequiringEntity)) {
            throw new IllegalArgumentException("must be a ComposedProvidingRequiringEntity!");
        }
        final ComposedProvidingRequiringEntity outerComposite = (ComposedProvidingRequiringEntity) compositeComponentCandidate
                .getComponent();
        this.establishAssemblyConnectorsForNonConnectedPorts(outerComposite,
                compositeComponentCandidate.getSubComponents());
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
        this.establishAssemblyConnectorsForNonConnectedPorts(compositeStructure, subComponents);
    }

    /**
     * Establish assembly connector for remaining non-connected ports. These are connectors which
     * cannot be enabled along with the clustering graph structure.
     *
     * @param compositeComponentCandidate
     */
    private void establishAssemblyConnectorsForNonConnectedPorts(final ComposedProvidingRequiringEntity outerComposite,
            final List<ComponentImplementingClassesLink> subComponents) {
        // loop required ports
        // TODO burkha 24.04.2013 check here for change
        for (final ComponentImplementingClassesLink component : subComponents) {
            for (final RequiredRole requiredRole : component.getComponent()
                    .getRequiredRoles_InterfaceRequiringEntity()) {
                if (!this.isBoundInConnector(outerComposite.getConnectors__ComposedStructure(), requiredRole)) {
                    this.findMatchingProvidedPortAndCreateAssemblyConnector(subComponents, outerComposite, component,
                            requiredRole);
                }
            }
        }
    }

    /**
     * Find a matching provided port (if any) and create a new assembly connector between require
     * port and found provided port and set assembly connector for composite component. <br>
     * Searches a provided port only inside the composite component.
     *
     * @param compositeComponentCandidate
     * @param outerComposite
     * @param requiringComponent
     * @param requiredPort
     */
    private void findMatchingProvidedPortAndCreateAssemblyConnector(
            final List<ComponentImplementingClassesLink> subComponents, final ComposedStructure outerComposite,
            final ComponentImplementingClassesLink requiringComponent, final RequiredRole requiredRole) {

        // loop provided ports and search for a match
        for (final ComponentImplementingClassesLink providingComponent : subComponents) {
            for (final ProvidedRole providedRole : providingComponent.getComponent()
                    .getProvidedRoles_InterfaceProvidingEntity()) {
                if (requiredRole instanceof OperationRequiredRole && providedRole instanceof OperationProvidedRole) {
                    final OperationRequiredRole opReqRole = (OperationRequiredRole) requiredRole;
                    final OperationProvidedRole opProvRole = (OperationProvidedRole) providedRole;
                    if (opReqRole.getRequiredInterface__OperationRequiredRole()
                            .equals(opProvRole.getProvidedInterface__OperationProvidedRole())) {
                        final Connector newAssemblyConnector = AssemblyConnectorBuilder.createAssemblyConnector(
                                outerComposite, opReqRole, opProvRole, requiringComponent.getComponent(),
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
    private boolean isBoundInConnector(final EList<Connector> connectors, final RequiredRole requiredRole) {
        for (final Connector connector : connectors) {
            if (connector instanceof AssemblyConnector) {
                final AssemblyConnector assemblyConnector = (AssemblyConnector) connector;
                if (assemblyConnector.getRequiredRole_AssemblyConnector().equals(requiredRole)) {
                    return true;
                }
            } else if (connector instanceof ProvidedDelegationConnector) {
                /* Provided delegation connectors can never be connected to required roles */
                continue;
            } else if (connector instanceof RequiredDelegationConnector) {
                final RequiredDelegationConnector reqDelegationConnector = (RequiredDelegationConnector) connector;
                if (reqDelegationConnector.getInnerRequiredRole_RequiredDelegationConnector().equals(requiredRole)) {
                    return true;
                }
            } else {
                logger.warn("Connector type " + connector.getClass().getSimpleName() + " not yet supported.");
            }
        }
        return false;
    }

}
