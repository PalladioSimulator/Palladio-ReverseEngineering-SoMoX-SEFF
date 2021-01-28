package org.somox.analyzer.simplemodelanalyzer.builder.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.palladiosimulator.pcm.core.CoreFactory;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.pcm.resourcetype.ProcessingResourceType;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;
import org.palladiosimulator.pcm.resourcetype.ResourceType;
import org.palladiosimulator.pcm.resourcetype.SchedulingPolicy;

/**
 * Utility method for creating an empty resource environment where the reconstructed components can
 * be put. Only static access via {@link #getDefaultResourceEnvironment()}
 *
 * @author kuester
 *
 */
public class DefaultResourceEnvironment {
    
    public static final String RESOURCETYPE_URI = "pathmap://PCM_MODELS/Palladio.resourcetype";

    public static final String PRIMITIVETYPES_URI = "pathmap://PCM_MODELS/PrimitiveTypes.repository";
    
    private static final String CPU_RESOURCETYPE_NAME = "CPU";
    
    private static final String DELAY_RESOURCETYPE_NAME = "DELAY";

    /** cached instance of default resource environment. */
    private static ResourceEnvironment resourceEnvironment;

    private static ResourceRepository resourceRepository;

    private static Repository primitiveTypesRepository;

    /** Prohibited. Only static access to class. */
    private DefaultResourceEnvironment() {
        // prohibited.
    }

    /**
     * Retrieves a cached instance of
     * {@link org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment} as created by
     * {@link #createDefaultResourceEnvironment()}.
     *
     * @return A cached instance of resource environment with default values. .
     */
    public static ResourceEnvironment getDefaultResourceEnvironment() {
        if (resourceEnvironment == null) {
            resourceEnvironment = createDefaultResourceEnvironment();
        }
        return resourceEnvironment;
    }

    /**
     * Retrieves a map of {@link org.palladiosimulator.pcm.repository.PrimitiveDataType}s as defined
     * in the standard PCM resource repository.
     *
     * @return A cached map of primitive data types.
     */
    private static Map<String, PrimitiveDataType> getPrimitiveDataTypes() {
        final Map<String, PrimitiveDataType> primitives = new HashMap<String, PrimitiveDataType>();
        if (primitiveTypesRepository == null) {
            primitiveTypesRepository = getPrimitiveTypesRepository();
        }
        for (final DataType d : primitiveTypesRepository.getDataTypes__Repository()) {
            if (d instanceof PrimitiveDataType) {
                final PrimitiveDataType pdt = (PrimitiveDataType) d;
                primitives.put(pdt.getType().getName(), pdt);
            }
        }
        return primitives;
    }

    public static PrimitiveDataType getPrimitiveDataTypeInteger() {
        return getPrimitiveDataTypes().get("INT");
    }

    public static PrimitiveDataType getPrimitiveDataTypeDouble() {
        return getPrimitiveDataTypes().get("DOUBLE");
    }

    public static PrimitiveDataType getPrimitiveDataTypeBool() {
        return getPrimitiveDataTypes().get("BOOL");
    }

    public static PrimitiveDataType getPrimitiveDataTypeChar() {
        return getPrimitiveDataTypes().get("CHAR");
    }

    public static PrimitiveDataType getPrimitiveDataTypeByte() {
        return getPrimitiveDataTypes().get("BYTE");
    }

    public static PrimitiveDataType getPrimitiveDataTypeString() {
        return getPrimitiveDataTypes().get("STRING");
    }

    /**
     * Creates and returns a new instance of
     * {@link org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment} with initial
     * values.
     *
     * @return A new instance of resource environment with default values.
     */
    public static ResourceEnvironment createDefaultResourceEnvironment() {
        final ResourceenvironmentFactory factory = ResourceenvironmentPackage.eINSTANCE.getResourceenvironmentFactory();
        final ResourceEnvironment resourceEnvironment = factory.createResourceEnvironment();
        resourceEnvironment.setEntityName("SoMoX Default ResourceEnvironment");

        final ResourceContainer container = factory.createResourceContainer();
        container.setEntityName("SoMoX Default ResourceContainer");

        final ProcessingResourceSpecification spec = factory.createProcessingResourceSpecification();
        spec.setActiveResourceType_ActiveResourceSpecification(getCPUProcessingResourceType());
        spec.setNumberOfReplicas(1);
        spec.setSchedulingPolicy(getProcessorSharingSchedulingPolicy());
        final PCMRandomVariable processingRate = CoreFactory.eINSTANCE.createPCMRandomVariable();
        processingRate.setSpecification("1");
        spec.setProcessingRate_ProcessingResourceSpecification(processingRate);

        container.getActiveResourceSpecifications_ResourceContainer().add(spec);
        resourceEnvironment.getResourceContainer_ResourceEnvironment().add(container);
        return resourceEnvironment;

    }

    protected static SchedulingPolicy getProcessorSharingSchedulingPolicy() {
        return getResourceRepository().getSchedulingPolicies__ResourceRepository().get(0);
    }

    public static ProcessingResourceType getCPUProcessingResourceType() {
        return getProcessingResourceType(CPU_RESOURCETYPE_NAME);
    }

    public static ProcessingResourceType getDelayProcessingResourceType() {
        return getProcessingResourceType(DELAY_RESOURCETYPE_NAME);
    }

    private static ProcessingResourceType getProcessingResourceType(String name) {
        List<ResourceType> resourceTypes = getResourceRepository().getAvailableResourceTypes_ResourceRepository();
        for (ResourceType type : resourceTypes) {
            if (type.getEntityName().equals(name)) {
                return (ProcessingResourceType) type;
            }
        }
        throw new RuntimeException("Could not find resource type named \"" + name + "\" in " + RESOURCETYPE_URI);
    }
    
    protected static Repository getPrimitiveTypesRepository() {
        if (primitiveTypesRepository != null) {
            return primitiveTypesRepository;
        }

        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        final Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("repository", new XMIResourceFactoryImpl());

        final URI uri = URI.createURI(PRIMITIVETYPES_URI);

        final ResourceSet resSet = new ResourceSetImpl();
        final Resource resource = resSet.getResource(uri, true);

        primitiveTypesRepository = (Repository) resource.getContents().get(0);
        return primitiveTypesRepository;
    }

    protected static ResourceRepository getResourceRepository() {
        if (resourceRepository != null) {
            return resourceRepository;
        }

        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        final Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("resourcetype", new XMIResourceFactoryImpl());

        final URI uri = URI.createURI(RESOURCETYPE_URI);

        final ResourceSet resSet = new ResourceSetImpl();
        final Resource resource = resSet.getResource(uri, true);
        resourceRepository = (ResourceRepository) resource.getContents().get(0);

        return resourceRepository;
    }
}
