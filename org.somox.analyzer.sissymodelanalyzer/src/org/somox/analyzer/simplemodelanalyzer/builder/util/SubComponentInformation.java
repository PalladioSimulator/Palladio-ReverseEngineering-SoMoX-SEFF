package org.somox.analyzer.simplemodelanalyzer.builder.util;

import org.apache.log4j.Logger;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.Role;

/**
 * Builder-related information for proper connector
 * creation to inner components.
 */
public class SubComponentInformation {
	
	private static Logger logger = Logger.getLogger(SubComponentInformation.class);
	
	private InterfaceSourceCodeLink interfaceSourceCodeLink;
	private Role role;
	private AssemblyContext assemblyContext;
	
	public SubComponentInformation(
			InterfaceSourceCodeLink interfaceSourceCodeLink,
			Role role,
			AssemblyContext assemblyContext) {
		this.interfaceSourceCodeLink = interfaceSourceCodeLink;
		this.role = role;
		this.assemblyContext = assemblyContext;		
	}

	public InterfaceSourceCodeLink getInterfaceSourceCodeLink() {
		return interfaceSourceCodeLink;
	}
	
	public Role getRole() {
		return role;
	}

	public AssemblyContext getAssemblyContext() {
		return assemblyContext;
	}		
	
	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof SubComponentInformation)) {
			return false;
		} else {
			SubComponentInformation instance = (SubComponentInformation) obj;
			return(instance.getRole().equals(this.getRole())
					&& instance.getInterfaceSourceCodeLink().equals(this.getInterfaceSourceCodeLink())
					&& instance.getAssemblyContext().equals(this.getAssemblyContext())
			);
		}
	}
}
