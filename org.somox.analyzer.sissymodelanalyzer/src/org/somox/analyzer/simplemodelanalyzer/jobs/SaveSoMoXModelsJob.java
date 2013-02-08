package org.somox.analyzer.simplemodelanalyzer.jobs;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.somox.analyzer.AnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.qimpressgast.GASTBehaviourRepository;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

import de.uka.ipd.sdq.workflow.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;
import eu.qimpress.samm.qosannotation.QosAnnotations;
import eu.qimpress.samm.staticstructure.Repository;
import eu.qimpress.samm.staticstructure.ServiceArchitectureModel;
import eu.qimpress.seff.SeffRepository;

/**
 * Job to save the SoMoX models from the SoMoX Blackboard.
 * 
 * @author Oliver Burkhardt
 * @author Benjamin Klatt
 * 
 */
public class SaveSoMoXModelsJob implements
		IBlackboardInteractingJob<SoMoXBlackboard> {

	// ---------------------------------
	// Static Data Fields
	// ---------------------------------

	/** The path inside the project to store the internal architecture model */
	private static final String PATH_INTERNAL_ARCHITECTUREMODEL = "/internal_architecture_model.samm_repository";
	private static final String PATH_GAST_BEHAVIOUR_REPOSITORY = "/internal_architecture_model.samm_gastbehaviour";
	private static final String PATH_SOURCECODE_DECORATOR_REPOSITORY = "/internal_architecture_model.sourcecodedecorator";
	private static final String PATH_SAMM_MODEL = "/internal_architecture_model.samm_servicearchitecturemodel";
	private static final String PATH_QOS_ANNOTATIONS_MODEL = "/internal_architecture_model.samm_qosannotation";
	private static final String PATH_SEFF_REPOSITORY_MODEL = "/internal_architecture_model.samm_seff";

	private Logger logger = Logger.getLogger(SaveSoMoXModelsJob.class);

	/** The somox blackboard to interact with. */
	private SoMoXBlackboard blackboard = null;

	/** The resource set of the core to work with */
	private ResourceSet resourceSet = null;

	/** The somox configuration. */
	private SoMoXConfiguration somoxConfiguration = null;

	public SaveSoMoXModelsJob(SoMoXConfiguration somoxConfiguration) {
		this.somoxConfiguration = somoxConfiguration;
		resourceSet = new ResourceSetImpl();
		resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());
	}

	/**
	 * @param blackBoard
	 *            the blackBoard to set
	 */
	public void setBlackboard(SoMoXBlackboard blackBoard) {
		this.blackboard = blackBoard;
	}

	@Override
	public void execute(IProgressMonitor arg0) throws JobFailedException,
			UserCanceledException {

		AnalysisResult result = blackboard.getAnalysisResult();

		String projectIdentifier = somoxConfiguration.getFileLocations()
				.getProjectName();
		String outputFolder = somoxConfiguration.getFileLocations()
				.getOutputFolder();

		// save the new internal architecture model
		try {
			saveInternalArchitectureModel(
					result.getInternalArchitectureModel(), projectIdentifier,
					outputFolder);
			saveGastBehaviourRepository(result.getGastBehaviourRepository(),
					projectIdentifier, outputFolder);
			saveSourceCodeDecoratorRepository(
					result.getSourceCodeDecoratorRepository(),
					projectIdentifier, outputFolder);
			saveSammModel(result.getServiceArchitectureModel(),
					projectIdentifier, outputFolder);
			saveQoSAnnotationsModel(result.getQosAnnotationModel(),
					projectIdentifier, outputFolder);
			saveSeffRepositoryModel(blackboard.getSeffRepository(),
					projectIdentifier, outputFolder);
		} catch (IOException e) {
			logger.error("Model Analyzer failed.", e);
			throw new JobFailedException("Unable to save SoMoX Models", e);
		}

	}

	/**
	 * Save the internal architecture model
	 * 
	 * @param internalArchitectureModel
	 *            The model to be saved
	 * @param projectIdentifier
	 *            The identifier for the project to save the model in
	 * @throws IOException
	 *             An exception about problems reading or writing the internal
	 *             architecture model
	 */
	private void saveInternalArchitectureModel(
			Repository internalArchitectureModel, String projectIdentifier,
			String outputFolder) throws IOException {
		save(internalArchitectureModel, projectIdentifier, outputFolder
				+ PATH_INTERNAL_ARCHITECTUREMODEL);
	}

	private void saveGastBehaviourRepository(
			GASTBehaviourRepository repository, String projectIdentifier,
			String outputFolder) throws IOException {
		save(repository, projectIdentifier, outputFolder
				+ PATH_GAST_BEHAVIOUR_REPOSITORY);
	}

	private void saveSourceCodeDecoratorRepository(
			SourceCodeDecoratorRepository repository, String projectIdentifier,
			String outputFolder) throws IOException {
		save(repository, projectIdentifier, outputFolder
				+ PATH_SOURCECODE_DECORATOR_REPOSITORY);
	}

	private void saveSammModel(
			ServiceArchitectureModel serviceArchitectureModel,
			String projectIdentifier, String outputFolder) throws IOException {
		save(serviceArchitectureModel, projectIdentifier, outputFolder
				+ PATH_SAMM_MODEL);
	}

	private void saveQoSAnnotationsModel(
			QosAnnotations serviceArchitectureModel, String projectIdentifier,
			String outputFolder) throws IOException {
		save(serviceArchitectureModel, projectIdentifier, outputFolder
				+ PATH_QOS_ANNOTATIONS_MODEL);
	}


	private void saveSeffRepositoryModel(SeffRepository seffRepository,
			String projectIdentifier, String outputFolder) throws IOException {
		save(seffRepository, projectIdentifier, outputFolder
				+ PATH_SEFF_REPOSITORY_MODEL);
	}

	private void save(EObject emfObject, String projectIdentifier, String path)
			throws IOException {
		ResourceSet resourceSet = getResourceSetForURI();
		// URI scriptURI = fileURI;
		URI uri = URI.createURI("platform:/resource/" + projectIdentifier
				+ path);
		// scriptURI = scriptURI.appendFileExtension(fileExtension);
		// Create a resource for this file.
		Resource resource = resourceSet.createResource(uri);
		// Add object to the contents.
		resource.getContents().add(emfObject);

		HashMap<Object, Object> saveOptions = new HashMap<Object, Object>();
		saveOptions.put(XMLResource.OPTION_PROCESS_DANGLING_HREF,
				XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);

		resource.save(saveOptions);
	}

	private ResourceSet getResourceSetForURI() {
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the default resource factory -- only needed for stand-alone!
		resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());
		return resourceSet;
	}

	/**
	 * Get the EMF resource to work with
	 * 
	 * @param projectIdentifier
	 *            The identifier for the project to work with
	 * @return The requested EMF resource
	 * @throws IOException
	 */
	private Resource getResource(String projectIdentifier, String path,
			EObject defaultObject) throws IOException {

		URI uri = URI.createURI("platform:/resource/" + projectIdentifier
				+ path);

		Resource resource = resourceSet.getResource(uri, false);

		// GASTReader gastReader;
		// Resource resource = null;
		// try {
		// gastReader = new GASTReader();
		// gastReader.loadFile(uri.toString());
		// resource = gastReader.getResourceGAST();
		// } catch (IOException e1) {
		// logger.error("Error loading model: " + e1);
		// e1.printStackTrace();
		// }

		// if the resource does not exist or is empty create a new one with
		// an appropriate model inside
		if (resource == null || resource.getContents().isEmpty()) {
			resource = resourceSet.createResource(uri);
			resource.getContents().add(defaultObject);
			resource.save(null);
		}

		return resource;
	}

	@Override
	public String getName() {
		return "Save SoMoX Models Job";
	}

	@Override
	public void rollback(IProgressMonitor arg0) throws RollbackFailedException {
		// nothing reasonable to do here
	}

}
