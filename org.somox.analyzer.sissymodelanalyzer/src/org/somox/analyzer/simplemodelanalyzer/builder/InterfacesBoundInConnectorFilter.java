package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Collection;

import org.somox.analyzer.simplemodelanalyzer.builder.util.SubComponentInformation;
import org.somox.filter.BaseFilter;

import eu.qimpress.samm.staticstructure.EndPoint;
import eu.qimpress.samm.staticstructure.Interface;
import eu.qimpress.samm.staticstructure.InterfacePort;

/**
 * Removes accesses already captured in connector endpoints
 * @author Klaus Krogmann
 *
 */
public class InterfacesBoundInConnectorFilter extends BaseFilter<SubComponentInformation> {
	
	private Collection<EndPoint> connectorEndpoints;
	
	public InterfacesBoundInConnectorFilter(Collection<EndPoint> connectorEndpoints) {
		super();
		this.connectorEndpoints = connectorEndpoints;		
	}

	@Override
	public boolean passes(SubComponentInformation subComponentInformation) {
		for(EndPoint currentEndpoint : connectorEndpoints) {
			if(currentEndpoint.getPort() instanceof InterfacePort) {
				Interface interfaceFromConnector = ((InterfacePort)currentEndpoint.getPort()).getInterfaceType();
				if(subComponentInformation.getInterfaceSourceCodeLink().getInterface().equals(interfaceFromConnector)) { // already in connector: remove
					return false;
				}
			}
		}			
		return true;
	}


}
