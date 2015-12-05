package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.Role;
import org.somox.analyzer.simplemodelanalyzer.builder.util.SubComponentInformation;
import org.somox.filter.BaseFilter;

/**
 * Removes role interface accesses already captured in connector
 *
 * @author Klaus Krogmann
 *
 */
public class InterfacesBoundInConnectorFilter extends BaseFilter<SubComponentInformation> {

    private static Logger logger = Logger.getLogger(InterfacesBoundInConnectorFilter.class);

    private final Collection<Role> connectorRoles;

    public InterfacesBoundInConnectorFilter(final Collection<Role> connectorRoles) {
        super();
        this.connectorRoles = connectorRoles;
    }

    @Override
    public boolean passes(final SubComponentInformation subComponentInformation) {
        for (final Role currentRole : this.connectorRoles) {
            if (currentRole instanceof OperationProvidedRole) {
                final Interface interfaceFromConnector = ((OperationProvidedRole) currentRole)
                        .getProvidedInterface__OperationProvidedRole();

                // if already in connector: remove
                if (subComponentInformation.getInterfaceSourceCodeLink().getInterface()
                        .equals(interfaceFromConnector)) {
                    return false;
                }

            } else if (currentRole instanceof OperationRequiredRole) {
                final Interface interfaceFromConnector = ((OperationRequiredRole) currentRole)
                        .getRequiredInterface__OperationRequiredRole();

                // if already in connector: remove
                if (subComponentInformation.getInterfaceSourceCodeLink().getInterface()
                        .equals(interfaceFromConnector)) {
                    return false;
                }

            } else {
                logger.warn("Role type not yet supported: " + currentRole.getClass().getSimpleName());
            }
        }
        return true;
    }

}
