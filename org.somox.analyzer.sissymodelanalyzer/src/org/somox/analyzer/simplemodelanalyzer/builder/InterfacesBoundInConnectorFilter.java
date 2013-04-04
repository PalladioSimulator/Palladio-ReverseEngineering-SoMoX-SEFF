package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.somox.analyzer.simplemodelanalyzer.builder.util.SubComponentInformation;
import org.somox.filter.BaseFilter;

import de.uka.ipd.sdq.pcm.repository.Interface;
import de.uka.ipd.sdq.pcm.repository.OperationProvidedRole;
import de.uka.ipd.sdq.pcm.repository.Role;

/**
 * Removes role interface accesses already captured in connector
 * 
 * @author Klaus Krogmann
 * 
 */
public class InterfacesBoundInConnectorFilter extends
		BaseFilter<SubComponentInformation> {
	
	private static Logger logger = Logger.getLogger(InterfacesBoundInConnectorFilter.class);

	private Collection<Role> connectorRoles;

	public InterfacesBoundInConnectorFilter(Collection<Role> connectorRoles) {
		super();
		this.connectorRoles = connectorRoles;
	}

	@Override
	public boolean passes(SubComponentInformation subComponentInformation) {
		for (Role currentRole : connectorRoles) {
			if (currentRole instanceof OperationProvidedRole) {
				Interface interfaceFromConnector = ((OperationProvidedRole) currentRole).getProvidedInterface__OperationProvidedRole();

				// if already in connector: remove
				if (subComponentInformation.getInterfaceSourceCodeLink()
						.getInterface().equals(interfaceFromConnector)) {
					return false;
				}
			} else {
				logger.warn("Role type not yet supported: "
						+ currentRole.getClass().getSimpleName());
			}
		}
		return true;
	}

}
