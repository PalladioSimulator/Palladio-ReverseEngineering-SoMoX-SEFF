package org.somox.analyzer.simplemodelanalyzer.builder.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.uka.ipd.sdq.pcm.core.CoreFactory;
import de.uka.ipd.sdq.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.repository.DataType;
import de.uka.ipd.sdq.pcm.repository.PrimitiveDataType;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentFactory;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceenvironmentPackage;
import de.uka.ipd.sdq.pcm.resourcetype.ProcessingResourceType;
import de.uka.ipd.sdq.pcm.resourcetype.ResourceRepository;
import de.uka.ipd.sdq.pcm.resourcetype.SchedulingPolicy;

/**
 * Utility method for creating an empty resource environment where the reconstructed components can be put. 
 * Only static access via {@link #getDefaultResourceEnvironment()}
 * 
 * @author kuester
 *
 */
public class DefaultResourceEnvironment {

	private static final String RESOURCETYPE_URI = "platform:/plugin/de.uka.ipd.sdq.pcm.resources/defaultModels/Palladio.resourcetype";

	private static final String PRIMITIVETYPES_URI = "platform:/plugin/de.uka.ipd.sdq.pcm.resources/defaultModels/PrimitiveTypes.repository";

	
	/** cached instance of default resource environment.  */
	private static ResourceEnvironment resourceEnvironment;
	
	private static ResourceRepository resourceRepository; 

	private static Repository primitiveTypesRepository; 
	
	/** Prohibited. Only static access to class. */
	private DefaultResourceEnvironment() {
		// prohibited.
	}
	
	/**
	 * Retrieves a cached instance of {@link de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment} 
	 * as created by {@link #createDefaultResourceEnvironment()}.
	 * 
	 * @return 
	 * 		A cached instance of resource environment with default values. . 
	 */
	public static ResourceEnvironment getDefaultResourceEnvironment() {
		if (resourceEnvironment == null) {
			resourceEnvironment = createDefaultResourceEnvironment();	
		}
		return resourceEnvironment;
	}
	
	/**
	 * Retrieves a list of {@link de.uka.ipd.sdq.pcm.repository.PrimitiveDataType}s as defined in the standrad PCM resource repository. 
	 * @return
	 * 		A cached list of primitive data types.
	 */
	public static List<PrimitiveDataType> getPrimitiveDataTypes() {
		List<PrimitiveDataType> primitives = new ArrayList<PrimitiveDataType>();
		if (primitiveTypesRepository == null) {
			primitiveTypesRepository = getPrimitiveTypesRepository();	
		}
		for (DataType d : primitiveTypesRepository.getDataTypes__Repository()) {
			if (d instanceof PrimitiveDataType) 
				primitives.add((PrimitiveDataType)d);
		}
		return primitives;
	}
	
	/**
	 * Creates and returns a new instance of {@link de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment} 
	 * with initial values. 
	 * 
	 * @return
	 * 		A new instance of resource environment with default values. 
	 */
	public static ResourceEnvironment createDefaultResourceEnvironment() {
		ResourceenvironmentFactory factory = ResourceenvironmentPackage.eINSTANCE.getResourceenvironmentFactory();
		ResourceEnvironment resourceEnvironment = factory.createResourceEnvironment();
		resourceEnvironment.setEntityName("SoMoX Default ResourceEnvironment");
		
		ResourceContainer container = factory.createResourceContainer();
		container.setEntityName("SoMoX Default ResourceContainer");
		
		ProcessingResourceSpecification spec = factory.createProcessingResourceSpecification();
		spec.setActiveResourceType_ActiveResourceSpecification(getCPUProcessingResourceType());
		spec.setNumberOfReplicas(1);
		spec.setSchedulingPolicy(getProcessorSharingSchedulingPolicy());
		PCMRandomVariable processingRate = CoreFactory.eINSTANCE.createPCMRandomVariable();
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
		return (ProcessingResourceType)getResourceRepository().getAvailableResourceTypes_ResourceRepository().get(0);
	}
	
	protected static Repository getPrimitiveTypesRepository() {
		if (primitiveTypesRepository != null) return primitiveTypesRepository;
		
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put("repository", new XMIResourceFactoryImpl());

	    URI uri = URI.createURI(PRIMITIVETYPES_URI);
	    
		ResourceSet resSet = new ResourceSetImpl();
		Resource resource = resSet.getResource(uri, true);
		
		primitiveTypesRepository = (Repository)resource.getContents().get(0);
		return primitiveTypesRepository;
	}

	protected static ResourceRepository getResourceRepository() {
		if (resourceRepository != null) return resourceRepository;
		
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put("resourcetype", new XMIResourceFactoryImpl());

	    URI uri = URI.createURI(RESOURCETYPE_URI);
	    
		ResourceSet resSet = new ResourceSetImpl();
		Resource resource = resSet.getResource(uri, true);
		
		resourceRepository = (ResourceRepository)resource.getContents().get(0);
		return resourceRepository;
	}

}
