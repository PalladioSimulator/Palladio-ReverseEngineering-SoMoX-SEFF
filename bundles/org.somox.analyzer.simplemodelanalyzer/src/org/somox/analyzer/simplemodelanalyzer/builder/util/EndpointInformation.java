package org.somox.analyzer.simplemodelanalyzer.builder.util;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.Role;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;

/**
 * Builder-related information for proper connector creation to inner components. An
 * EndpointInformation stores all information to identify an endpoint of a connector on a component.
 * An endpoint can be a provided role or required role offered by a component instance identified by
 * its assembly context.
 */
public class EndpointInformation {

    private static Logger logger = Logger.getLogger(EndpointInformation.class);

    private final InterfaceSourceCodeLink interfaceSourceCodeLink;
    private final Role role;
    private final AssemblyContext assemblyContext;

    public EndpointInformation(final InterfaceSourceCodeLink interfaceSourceCodeLink, final Role role,
            final AssemblyContext assemblyContext) {
        this.interfaceSourceCodeLink = interfaceSourceCodeLink;
        this.role = role;
        this.assemblyContext = assemblyContext;
    }

    public InterfaceSourceCodeLink getInterfaceSourceCodeLink() {
        return this.interfaceSourceCodeLink;
    }

    public Role getRole() {
        return this.role;
    }

    public AssemblyContext getAssemblyContext() {
        return this.assemblyContext;
    }

    @Override
    public boolean equals(final Object obj) {

        if (!(obj instanceof EndpointInformation)) {
            return false;
        } else {
            final EndpointInformation instance = (EndpointInformation) obj;
            return (instance.getRole().equals(this.getRole())
                    && instance.getInterfaceSourceCodeLink().equals(this.getInterfaceSourceCodeLink())
                    && instance.getAssemblyContext().equals(this.getAssemblyContext()));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EndpointInformation [role=" + role + ", assemblyContext=" + assemblyContext + "]";
    }
}
