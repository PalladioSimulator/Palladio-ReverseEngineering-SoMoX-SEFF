package org.somox.analyzer.simplemodelanalyzer.builder.util;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.Role;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;

/**
 * Builder-related information for proper connector creation to inner components.
 */
public class SubComponentInformation {

    private static Logger logger = Logger.getLogger(SubComponentInformation.class);

    private final InterfaceSourceCodeLink interfaceSourceCodeLink;
    private final Role role;
    private final AssemblyContext assemblyContext;

    public SubComponentInformation(final InterfaceSourceCodeLink interfaceSourceCodeLink, final Role role,
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

        if (!(obj instanceof SubComponentInformation)) {
            return false;
        } else {
            final SubComponentInformation instance = (SubComponentInformation) obj;
            return (instance.getRole().equals(this.getRole())
                    && instance.getInterfaceSourceCodeLink().equals(this.getInterfaceSourceCodeLink())
                    && instance.getAssemblyContext().equals(this.getAssemblyContext()));
        }
    }
}
