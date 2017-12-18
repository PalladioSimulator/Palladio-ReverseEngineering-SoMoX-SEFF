package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.Role;
import org.somox.analyzer.simplemodelanalyzer.builder.util.EndpointInformation;
import org.somox.filter.BaseFilter;

/**
 * Removes role interface accesses already captured in connector
 *
 * @author Klaus Krogmann
 *
 */
public class InterfacesBoundInConnectorFilter extends BaseFilter<EndpointInformation> {

    private static final Logger LOGGER = Logger.getLogger(InterfacesBoundInConnectorFilter.class);

    private final Collection<EndpointInformation> connectorEndpoints;
    
    /**
     * Filter constructor
     *
     * @param connectorEndpoints
     *            Collection of all roles which are part of any connector in the surrounding
     *            composed structure
     */
    public InterfacesBoundInConnectorFilter(final Collection<EndpointInformation> connectorEndpoints) {
        super();
        this.connectorEndpoints = connectorEndpoints;
    }

    @Override
    public boolean passes(final EndpointInformation endpointInformation) {
        boolean isUnboundEndpoint = true;

        for (final EndpointInformation current : connectorEndpoints) {
            if (current.getAssemblyContext().equals(endpointInformation.getAssemblyContext()) &&
                    current.getRole().equals(endpointInformation.getRole())) {
                isUnboundEndpoint = false;
                break;
            }
        }

        if (LOGGER.isDebugEnabled() && isUnboundEndpoint) {
            LOGGER.debug("Found unbound endpoint " + endpointInformation);
        }
        return isUnboundEndpoint;
    }

}
