package org.somox.analyzer.simplemodelanalyzer.builder.util;

import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.repository.OperationRequiredRole;

/**
 * Builder-related information for proper connector
 * creation to inner components.
 */
public class SubComponentInformation {
	InterfaceSourceCodeLink interfaceSourceCodeLink;
	OperationRequiredRole operationRequiredRole;
	AssemblyContext assemblyContext;
	
	public SubComponentInformation(
			InterfaceSourceCodeLink interfaceSourceCodeLink,
			OperationRequiredRole operationRequiredRole,
			AssemblyContext assemblyContext) {
		this.interfaceSourceCodeLink = interfaceSourceCodeLink;
		this.operationRequiredRole = operationRequiredRole;
		this.assemblyContext = assemblyContext;
	}
	
	public InterfaceSourceCodeLink getInterfaceSourceCodeLink() {
		return interfaceSourceCodeLink;
	}
	
	public OperationRequiredRole getOperationRequiredRole() {
		return operationRequiredRole;
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
			return(instance.getOperationRequiredRole().equals(this.getOperationRequiredRole())
					&& instance.getInterfaceSourceCodeLink().equals(this.getInterfaceSourceCodeLink())
					&& instance.getAssemblyContext().equals(this.getAssemblyContext())
			);
		}
	}
}
