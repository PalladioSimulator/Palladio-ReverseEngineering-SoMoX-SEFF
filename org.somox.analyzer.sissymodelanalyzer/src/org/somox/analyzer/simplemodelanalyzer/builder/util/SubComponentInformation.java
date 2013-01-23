package org.somox.analyzer.simplemodelanalyzer.builder.util;

import eu.qimpress.samm.staticstructure.InterfacePort;
import eu.qimpress.samm.staticstructure.SubcomponentInstance;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;

/**
 * Builder-related information for proper connector
 * creation to inner components.
 */
public class SubComponentInformation {
	InterfaceSourceCodeLink interfaceSourceCodeLink;
	InterfacePort interfacePort;
	SubcomponentInstance subComponentInstance;
	
	public SubComponentInformation(
			InterfaceSourceCodeLink interfaceSourceCodeLink,
			InterfacePort interfacePort,
			SubcomponentInstance subComponentInstance) {
		this.interfaceSourceCodeLink = interfaceSourceCodeLink;
		this.interfacePort = interfacePort;
		this.subComponentInstance = subComponentInstance;
	}
	
	public InterfaceSourceCodeLink getInterfaceSourceCodeLink() {
		return interfaceSourceCodeLink;
	}
	
	public InterfacePort getInterfacePort() {
		return interfacePort;
	}

	public SubcomponentInstance getSubComponentInstance() {
		return subComponentInstance;
	}		
	
	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof SubComponentInformation)) {
			return false;
		} else {
			SubComponentInformation instance = (SubComponentInformation) obj;
			return(instance.getInterfacePort().equals(this.getInterfacePort())
					&& instance.getInterfaceSourceCodeLink().equals(this.getInterfaceSourceCodeLink())
					&& instance.getSubComponentInstance().equals(this.getSubComponentInstance())
			);
		}
	}
}
