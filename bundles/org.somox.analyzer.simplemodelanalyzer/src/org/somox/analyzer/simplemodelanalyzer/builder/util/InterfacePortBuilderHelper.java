package org.somox.analyzer.simplemodelanalyzer.builder.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.core.composition.ProvidedDelegationConnector;
import org.palladiosimulator.pcm.core.composition.RequiredDelegationConnector;
import org.palladiosimulator.pcm.core.composition.util.CompositionSwitch;
import org.palladiosimulator.pcm.repository.CompositeComponent;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.Role;
import org.somox.analyzer.simplemodelanalyzer.builder.InterfacesBoundInConnectorFilter;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink;

/**
 * Query functionality useful when creating interface ports.
 *
 * @author Klaus Krogmann
 *
 */
public class InterfacePortBuilderHelper {

    private static final Logger logger = Logger.getLogger(InterfacePortBuilderHelper.class);

    // TODO: make configurable
    private static final boolean EXHIBIT_ALL_INNER_PROVIDED_INTERFACES = true;

    /**
     * Collects information on interfaces which are not bound in connectors (in the required case)
     * and all interfaces in the provided case.
     *
     * @param componentLink
     *            fitting to the component argument.
     * @param outerComponentToCheck
     *            Component to look up non-connected interfaces for.
     * @param isProvided
     *            Check for provided or required interfaces.
     * @return
     */
    public static Iterable<EndpointInformation> collectInformationOnNonBoundInterfaces(
            final ComponentImplementingClassesLink componentLink, final ComposedStructure outerComponentToCheck,
            final boolean isProvided) {

        final Collection<EndpointInformation> allSubComponentEndpoints = collectComponentEndpoints(componentLink,
                isProvided);
        final Collection<EndpointInformation> connectorEndpoints = collectConnectorEndpoints(outerComponentToCheck);

        Iterable<EndpointInformation> interfaceLinksNotUsedInConnectors;
        if (isProvided) {
            if (EXHIBIT_ALL_INNER_PROVIDED_INTERFACES) {
                // exhibit all provided interfaces of inner components
                interfaceLinksNotUsedInConnectors = allSubComponentEndpoints;
            } else {
                // delegate only provided interfaces which are not bound internally
                // filter: allSubComponentInterfaceLinks - connectorEndpoints
                final InterfacesBoundInConnectorFilter filter = new InterfacesBoundInConnectorFilter(
                        connectorEndpoints);
                interfaceLinksNotUsedInConnectors = filter.filter(allSubComponentEndpoints);
            }
        } else {
            // require only interfaces which are not bound internally
            // filter: allSubComponentInterfaceLinks - connectorEndpoints
            final InterfacesBoundInConnectorFilter filter = new InterfacesBoundInConnectorFilter(connectorEndpoints);
            interfaceLinksNotUsedInConnectors = filter.filter(allSubComponentEndpoints);
        }
        return interfaceLinksNotUsedInConnectors;
    }

    /**
     * Collects inner connector roles of this composite component (first level no inner
     * containments).
     *
     * @param compositeComponent
     *            Connectors of this component (inner)
     * @return
     */
    private static Collection<EndpointInformation> collectConnectorEndpoints(final ComposedStructure compositeComponent) {
        final Collection<EndpointInformation> connectorEndpoints = new LinkedList<EndpointInformation>();
        final CompositionSwitch<Collection<EndpointInformation>> connectorSwitch = new CompositionSwitch<Collection<EndpointInformation>>() {

            @Override
            public Collection<EndpointInformation> caseProvidedDelegationConnector(
                    final ProvidedDelegationConnector object) {
                final List<EndpointInformation> result = new LinkedList<EndpointInformation>();
                result.add(new EndpointInformation(null, object.getInnerProvidedRole_ProvidedDelegationConnector(),
                        object.getAssemblyContext_ProvidedDelegationConnector()));
                return result;
            }

            @Override
            public Collection<EndpointInformation> caseRequiredDelegationConnector(
                    final RequiredDelegationConnector object) {
                final List<EndpointInformation> result = new LinkedList<EndpointInformation>();
                result.add(new EndpointInformation(null, object.getInnerRequiredRole_RequiredDelegationConnector(),
                        object.getAssemblyContext_RequiredDelegationConnector()));
                return result;
            }

            @Override
            public Collection<EndpointInformation> caseAssemblyConnector(final AssemblyConnector object) {
                final List<EndpointInformation> result = new LinkedList<EndpointInformation>();
                result.add(new EndpointInformation(null, object.getProvidedRole_AssemblyConnector(),
                        object.getProvidingAssemblyContext_AssemblyConnector()));
                result.add(new EndpointInformation(null, object.getRequiredRole_AssemblyConnector(),
                        object.getRequiringAssemblyContext_AssemblyConnector()));
                return result;
            }

            @Override
            public Collection<EndpointInformation> defaultCase(final EObject object) {
                logger.warn("Unknown connector type found when searching for bound endpoints : " + object.eClass());
                return Collections.emptyList();
            }

        };

        for (final Connector connector : compositeComponent.getConnectors__ComposedStructure()) {
            connectorEndpoints.addAll(connectorSwitch.doSwitch(connector));
        }

        return connectorEndpoints;
    }

    /**
     * Collect all interfaces links of all direct sub components.
     *
     * @param componentLink
     *            Component Link which to find subcomponents and collect the sub component's
     *            interface links
     * @param collectProvided
     *            switch for collecting provided or required interfaces
     * @return interface links of direct sub component
     */
    private static Collection<EndpointInformation> collectComponentEndpoints(
            final ComponentImplementingClassesLink componentLink, final boolean collectProvided) {
        final Collection<EndpointInformation> allInterfaceLinks = new ArrayList<EndpointInformation>();
        for (final ComponentImplementingClassesLink currentSubComponentLink : componentLink.getSubComponents()) {
            List<InterfaceSourceCodeLink> interfaceLinkSubList;
            if (collectProvided) {
                interfaceLinkSubList = currentSubComponentLink.getProvidedInterfaces();
            } else {
                interfaceLinkSubList = currentSubComponentLink.getRequiredInterfaces();
            }
            for (final InterfaceSourceCodeLink currentInterfaceLinkSub : interfaceLinkSubList) {
                // collect additional information for connector creation:
                AssemblyContext matchingSubComponentInstance = null;
                if (componentLink.getComponent() != null) { // regular case: a component link
                    matchingSubComponentInstance = getSubComponentInstance(
                            (CompositeComponent) componentLink.getComponent(), currentSubComponentLink);
                } else { // SAMM system architecture case:
                    if (componentLink instanceof PCMSystemImplementatingClassesLink) {
                        matchingSubComponentInstance = getSubComponentInstance(
                                ((PCMSystemImplementatingClassesLink) componentLink).getSystemModel(),
                                currentSubComponentLink);
                    }
                }
                final Role role = getInterfacePort(currentSubComponentLink, currentInterfaceLinkSub, collectProvided);

                if (role != null) {
                    allInterfaceLinks
                            .add(new EndpointInformation(currentInterfaceLinkSub, role, matchingSubComponentInstance));
                } else {
                    // if no matching role was found for the component do not try
                    // to create a sub component information for the role.
                    logger.warn("No role found for interface " + currentInterfaceLinkSub.getInterface().getEntityName()
                            + " in Component " + currentSubComponentLink.getComponent().getEntityName());
                }
            }
        }
        return allInterfaceLinks;
    }

    /**
     * Search matching Role.<br>
     * Condition: The linked subcomponent must provide/require the given linked interface
     *
     * @param subComponentLink
     * @param interfaceLinkSub
     * @param searchProvidedRoles
     *            switch provided / required search
     * @return
     */
    public static Role getInterfacePort(final ComponentImplementingClassesLink subComponentLink,
            final InterfaceSourceCodeLink interfaceLinkSub, final boolean searchProvidedRoles) {

        final RepositoryComponent linkComponent = subComponentLink.getComponent();

        if (searchProvidedRoles) {
            final EList<ProvidedRole> roles = linkComponent.getProvidedRoles_InterfaceProvidingEntity();
            for (final ProvidedRole currentRole : roles) {
                if (currentRole instanceof OperationProvidedRole) {
                    final Interface componentInterface = ((OperationProvidedRole) currentRole)
                            .getProvidedInterface__OperationProvidedRole();
                    if (interfaceLinkSub.getInterface().equals(componentInterface)) {
                        return currentRole;
                    }
                } else {
                    logger.warn("Role type not supported: " + currentRole.getClass().getSimpleName());
                }
            }

        } else {
            final EList<RequiredRole> roles = linkComponent.getRequiredRoles_InterfaceRequiringEntity();
            for (final RequiredRole currentRole : roles) {
                if (currentRole instanceof OperationRequiredRole) {
                    final Interface componentInterface = ((OperationRequiredRole) currentRole)
                            .getRequiredInterface__OperationRequiredRole();
                    if (interfaceLinkSub.getInterface().equals(componentInterface)) {
                        return currentRole;
                    }
                } else {
                    logger.warn("Role type not supported: " + currentRole.getClass().getSimpleName());
                }
            }
        }
        logger.warn("should find an interface port");
        return null;
    }

    /**
     * Search matching SubcomponentInstance. Condition: outerCompositeComponent must have the linked
     * subcomponent.
     *
     * @param outerCompositeComponent
     * @param subComponentLink
     * @return
     */
    private static AssemblyContext getSubComponentInstance(final ComposedStructure outerCompositeComponent,
            final ComponentImplementingClassesLink subComponentLink) {
        for (final AssemblyContext innerAssemblyContext : outerCompositeComponent
                .getAssemblyContexts__ComposedStructure()) {
            if (innerAssemblyContext.getEncapsulatedComponent__AssemblyContext()
                    .equals(subComponentLink.getComponent())) {
                return innerAssemblyContext;
            }
        }
        logger.warn("no subcomponent instance found");
        return null;
    }

}
