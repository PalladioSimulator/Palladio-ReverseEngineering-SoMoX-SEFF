package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Set;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.CompositionFactory;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.system.System;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.builder.util.InstanceComponentTuple;
import org.somox.analyzer.simplemodelanalyzer.builder.util.SubComponentInformation;

/**
 * Creates a system level dummy component.
 *
 * @author Klaus Krogmann
 *
 */
public class DummyComponentBuilder {

    private static Logger logger = Logger.getLogger(DummyComponentBuilder.class);

    /**
     * Creates a single dummy primitive Components and adds it as instance to the passed composite
     * structure and creates assembly connectors to the newly created component. <br>
     * Does NOT update the source code decorator!
     *
     * @param sammSystem
     *            The SAMM system to create the dummy in.
     * @param container
     *            the container to allocate to
     * @return the newly created component
     */
    public static BasicComponent createDummyComponent(
            final Set<SubComponentInformation> subComponentInformationOnNonBoundInterfacePorts, final System pcmSystem,
            final ResourceEnvironment resourceEnv, final AnalysisResult analysisResult) {
        final InstanceComponentTuple dummyComponentInfo = createDummyComponent(pcmSystem, resourceEnv, analysisResult);

        // add all interfaces as provided to component:
        for (final SubComponentInformation subCompInfo : subComponentInformationOnNonBoundInterfacePorts) {

            final OperationProvidedRole newProvidedRole = RepositoryFactory.eINSTANCE.createOperationProvidedRole();
            newProvidedRole.setEntityName(subCompInfo.getRole().getEntityName() + " (prov dummy)");
            newProvidedRole.setEntityName(subCompInfo.getRole().getEntityName() + " (prov dummy)");
            // newProvidedInterfacePort.setDocumentation("SoMoX created provided port");
            dummyComponentInfo.basicComponent.getProvidedRoles_InterfaceProvidingEntity().add(newProvidedRole);

            // create assembly connector:
            if (subCompInfo.getRole() instanceof OperationRequiredRole) {
                AssemblyConnectorBuilder.createAssemblyConnector(pcmSystem,
                        (OperationRequiredRole) subCompInfo.getRole(), newProvidedRole,
                        subCompInfo.getAssemblyContext(), dummyComponentInfo.assemblyContext);
            } else {
                logger.warn("Role type not supported yet: " + subCompInfo.getRole().getClass().getSimpleName());
            }
        }

        return dummyComponentInfo.basicComponent;
    }

    private static InstanceComponentTuple createDummyComponent(final System pcmSystem,
            final ResourceEnvironment resourceEnv, final AnalysisResult analysisResult) {
        final BasicComponent basicComponent = RepositoryFactory.eINSTANCE.createBasicComponent();
        basicComponent.setEntityName("SoMoX System-Level Dummy Component");
        // basicComponent.setDocumentation("Captures calls to system-external services.");

        final AssemblyContext assemblyContext = CompositionFactory.eINSTANCE.createAssemblyContext();
        assemblyContext.setEncapsulatedComponent__AssemblyContext(basicComponent);
        assemblyContext.setEntityName("SoMoX Dummy Component Instance");
        pcmSystem.getAssemblyContexts__ComposedStructure().add(assemblyContext);

        // allocate service
        // Allocation allocation =
        final Allocation allocation = analysisResult.getAllocation();
        allocation.setTargetResourceEnvironment_Allocation(resourceEnv);

        final InstanceComponentTuple instanceComponentTuple = new InstanceComponentTuple();
        instanceComponentTuple.basicComponent = basicComponent;
        instanceComponentTuple.assemblyContext = assemblyContext;

        return instanceComponentTuple;
    }

}
