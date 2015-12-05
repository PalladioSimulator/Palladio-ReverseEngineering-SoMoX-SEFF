package org.somox.analyzer.simplemodelanalyzer.builder;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.CompositionFactory;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.core.entity.NamedElement;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.somox.analyzer.AnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;

/**
 * Builder for Assembly Connectors. This builder is complemented by
 * {@link IAssemblyConnectorStrategy}
 *
 * @author Klaus Krogmann
 */
public class AssemblyConnectorBuilder extends AbstractBuilder {

    private static final Logger logger = Logger.getLogger(AssemblyConnectorBuilder.class);

    public AssemblyConnectorBuilder(final Root astModel, final SoMoXConfiguration somoxConfiguration,
            final AnalysisResult analysisResult) {
        super(astModel, somoxConfiguration, analysisResult);
    }

    /**
     * Create a new instance of an assembly connector.
     *
     * @param parentComponent
     *            the outer composite component
     * @param requiredRole
     *            the required port of an inner component
     * @param providedPort
     *            the provided port of an inner component
     * @param requiredComponentInstance
     * @param providedComponentInstance
     */
    public static Connector createAssemblyConnector(final ComposedStructure parentComponent,
            final OperationRequiredRole requiredRole, final OperationProvidedRole providedRole,
            final RepositoryComponent requiredComponentInstance, final RepositoryComponent providedComponentInstance) {

        final AssemblyConnector newConnector = createAssemblyConnectorEntity(parentComponent, requiredComponentInstance,
                providedComponentInstance);

        newConnector.setProvidedRole_AssemblyConnector(providedRole);
        newConnector.setRequiredRole_AssemblyConnector(requiredRole);

        final AssemblyContext providingAssemblyContext = findAssemblyContext(parentComponent,
                providedComponentInstance);
        newConnector.setProvidingAssemblyContext_AssemblyConnector(providingAssemblyContext);

        final AssemblyContext requiringAssemblyContext = findAssemblyContext(parentComponent,
                requiredComponentInstance);
        newConnector.setRequiringAssemblyContext_AssemblyConnector(requiringAssemblyContext);

        return newConnector;
    }

    /**
     * Create a new instance of an assembly connector.
     *
     * @param parentComponent
     *            the outer composite component
     * @param requiredPort
     *            the required port of an inner component
     * @param providedPort
     *            the provided port of an inner component
     * @param requiredComponentType
     * @param providedComponentType
     */
    public static Connector createAssemblyConnector(final ComposedStructure parentComponent,
            final OperationRequiredRole requiredRole, final OperationProvidedRole providedRole,
            final AssemblyContext requiredComponentType, final AssemblyContext providedComponentType) {

        final AssemblyConnector newConnector = createAssemblyConnectorEntity(parentComponent, requiredComponentType,
                providedComponentType);

        newConnector.setProvidedRole_AssemblyConnector(providedRole);
        newConnector.setRequiredRole_AssemblyConnector(requiredRole);
        newConnector.setProvidingAssemblyContext_AssemblyConnector(providedComponentType);
        newConnector.setRequiringAssemblyContext_AssemblyConnector(requiredComponentType);

        return newConnector;
    }

    private static AssemblyConnector createAssemblyConnectorEntity(final ComposedStructure parentComponent,
            final NamedElement requiredComponentType, final NamedElement providedComponentType) {
        logger.debug("Creating new assembly connector from " + requiredComponentType.getEntityName() + " to "
                + providedComponentType.getEntityName());
        final AssemblyConnector newConnector = CompositionFactory.eINSTANCE.createAssemblyConnector();
        parentComponent.getConnectors__ComposedStructure().add(newConnector);
        newConnector.setEntityName("Assembly Connector from " + requiredComponentType.getEntityName() + " to "
                + providedComponentType.getEntityName());
        return newConnector;
    }

    private static AssemblyContext findAssemblyContext(final ComposedStructure parentComponent,
            final RepositoryComponent componentOfRole) {
        AssemblyContext resultSubcomponentInstance = null;
        boolean found = false;
        for (final AssemblyContext subcomponentInstance : parentComponent.getAssemblyContexts__ComposedStructure()) {

            if (subcomponentInstance.getEncapsulatedComponent__AssemblyContext().equals(componentOfRole)) {
                if (found) {
                    throw new IllegalArgumentException("Assumption on input model does not hold. "
                            + "Only one instance per component type per composite component assumed!");
                }
                resultSubcomponentInstance = subcomponentInstance;
                found = true;
            }
        }
        if (!found) {
            logger.warn("No subcomponent instance found for parent " + parentComponent.getEntityName()
                    + " and child component " + componentOfRole.getEntityName());
        }
        return resultSubcomponentInstance;

    }

}
