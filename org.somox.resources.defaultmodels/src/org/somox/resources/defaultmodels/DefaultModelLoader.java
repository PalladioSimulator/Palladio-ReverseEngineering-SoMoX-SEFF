package org.somox.resources.defaultmodels;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import eu.qimpress.samm.deployment.hardware.HardwareDescriptorRepository;
import eu.qimpress.samm.deployment.hardware.HardwarePackage;
import eu.qimpress.samm.deployment.targetenvironment.Container;
import eu.qimpress.samm.deployment.targetenvironment.ExecutionResource;
import eu.qimpress.samm.deployment.targetenvironment.TargetEnvironment;
import eu.qimpress.samm.deployment.targetenvironment.TargetenvironmentPackage;

/**
 * Default model handling / loading / accessing. Provides eased access
 * to SoMoX default models which are included by the pathmap.
 * @author Klaus Krogmann
 * TODO: clean up code
 */
public class DefaultModelLoader {
		
	private static Logger logger = Logger.getLogger(DefaultModelLoader.class);	

	// Default model locations:
	private static final String PATH_SAMM_HARDWARE_DEFAULT = "pathmap://SOMOX_MODELS/somox-default.samm_hardware";
	private static final String PATH_SAMM_TARGETENVIRONMENT_DEFAULT = "pathmap://SOMOX_MODELS/somox-default.samm_targetenvironment";
	private static final String PATH_SAMM_QOS_ANNOTATIONS_DEFAULT = "pathmap://SOMOX_MODELS/somox-default.samm_qosannotation";	
	
	// Default models:
	private HardwareDescriptorRepository hardwareRepositoryModel;
	private TargetEnvironment targetEnvironmentModel;		
	private QosAnnotations qosAnnotationModel;	
	
	public DefaultModelLoader() {
		loadDefaultModels();
	}	
	
	/**
	 * Relies on fixed default SoMoX models
	 * @return
	 */
	public Container getDefaultContainer() {
		return targetEnvironmentModel.getNodes().get(0).getContainers().get(0);
	}	
	
	/**
	 * Relies on fixed default SoMoX models
	 * @return
	 */
	public ExecutionResource getDefaultExecutionResource() {
		return targetEnvironmentModel.getNodes().get(0).getContainers().get(0).getExecutionResources().get(0);
	}		
	
	private void loadDefaultModels() {
		// load default models:
		URI	fileURI = null;
		Resource resource = null;
		try {
			fileURI = URI.createURI(PATH_SAMM_HARDWARE_DEFAULT);
			resource = loadFile(fileURI);
			hardwareRepositoryModel = (HardwareDescriptorRepository)
				retrieveRootFromResource(resource, HardwarePackage.eINSTANCE.getHardwareDescriptorRepository());

			fileURI = URI.createURI(PATH_SAMM_TARGETENVIRONMENT_DEFAULT);
			resource = loadFile(fileURI);
			targetEnvironmentModel = (TargetEnvironment)
				retrieveRootFromResource(resource, TargetenvironmentPackage.eINSTANCE.getTargetEnvironment());			
			
			fileURI = URI.createURI(PATH_SAMM_QOS_ANNOTATIONS_DEFAULT);
			resource = loadFile(fileURI);
			qosAnnotationModel = (QosAnnotations)
				retrieveRootFromResource(resource, QosannotationPackage.eINSTANCE.getQosAnnotations());
			
		} catch (IOException e) {
			logger.error("Failed to load SoMoX default model " + fileURI,e);					
		}		
	}		
	 
	/**
	 * Loads a performance-optimized models instance.
	 * @param filename File to load model from
	 * @throws IOException Thrown if the file is not present in the file system.
	 */
	public Resource loadFile(URI fileURI) throws IOException {
		
		Resource resource = new XMIResourceImpl(fileURI);
		
		//TODO: change to XMI
//		Map<Object, Object> loadOptions = ((XMLResourceImpl)resource).getDefaultLoadOptions();
//		loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
//		loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
//		loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
//		loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
//		loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap());
//
//		((ResourceImpl)resource).setIntrinsicIDToEObjectMap(new HashMap());

		resource.load(new HashMap<Object, Object>());
				
		return resource;		
	}
	
	private EObject retrieveRootFromResource(Resource res, EClass typeToLoad) {
		return res.getContents().get(0);

		//FIXME
//		for (Iterator<Object> iter = EcoreUtil.getAllContents(res, true); iter.hasNext();) {
//			EObject eObject = (EObject) iter.next();
//						
//			if ( EcoreUtil.isAncestor(typeToLoad, eObject) ) {				
//				return eObject;
//			}
//		}
//		logger.error("could no resolve model element " + typeToLoad.getName());
//		return null;
	}	
}
