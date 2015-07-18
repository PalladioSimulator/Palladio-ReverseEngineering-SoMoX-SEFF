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
import org.somox.seff2javaast.SEFF2JavaAST;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

import org.palladiosimulator.pcm.qosannotations.QoSAnnotations;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.system.System;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

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
	private static final String PATH_SEFF2JAVAAST_REPOSITORY = "/internal_architecture_model.seff2javaast";
	private static final String PATH_SOURCECODE_DECORATOR_REPOSITORY = "/internal_architecture_model.sourcecodedecorator";
	private static final String PATH_SYSTEM_MODEL = "/internal_architecture_model.system";
	private static final String PATH_QOS_ANNOTATIONS_MODEL = "/internal_architecture_model.samm_qosannotation";
	private static final String PATH_REPOSITORY_MODEL = "/internal_architecture_model.repository";

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
			saveRepositoryModel(
					result.getInternalArchitectureModel(), projectIdentifier,
					outputFolder);
			saveSourceCodeDecoratorRepository(
					result.getSourceCodeDecoratorRepository(),
					projectIdentifier, outputFolder);
			saveSEFF2JavaASTModel(result.getSeff2JavaAST(), 
					projectIdentifier, outputFolder);
			saveSammModel(result.getSystemModel(),
					projectIdentifier, outputFolder);
			saveQoSAnnotationsModel(result.getQosAnnotationModel(),
					projectIdentifier, outputFolder);
		} catch (IOException e) {
			logger.error("Model Analyzer failed.", e);
			throw new JobFailedException("Unable to save SoMoX Models", e);
		}

	}

	private void saveSEFF2JavaASTModel(
			SEFF2JavaAST repository, String projectIdentifier,
			String outputFolder) throws IOException {
		save(repository, projectIdentifier, outputFolder
				+ PATH_SEFF2JAVAAST_REPOSITORY);
	}

	private void saveSourceCodeDecoratorRepository(
			SourceCodeDecoratorRepository repository, String projectIdentifier,
			String outputFolder) throws IOException {
		save(repository, projectIdentifier, outputFolder
				+ PATH_SOURCECODE_DECORATOR_REPOSITORY);
	}

	private void saveSammModel(
			System system,
			String projectIdentifier, String outputFolder) throws IOException {
		save(system, projectIdentifier, outputFolder
				+ PATH_SYSTEM_MODEL);
	}

	private void saveQoSAnnotationsModel(
			QoSAnnotations serviceArchitectureModel, String projectIdentifier,
			String outputFolder) throws IOException {
		save(serviceArchitectureModel, projectIdentifier, outputFolder
				+ PATH_QOS_ANNOTATIONS_MODEL);
	}


	private void saveRepositoryModel(Repository repository,
			String projectIdentifier, String outputFolder) throws IOException {
		save(repository, projectIdentifier, outputFolder
				+ PATH_REPOSITORY_MODEL);
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

	@Override
	public String getName() {
		return "Save SoMoX Models Job";
	}

	@Override
	public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
		// TODO Auto-generated method stub
		
	}

}
