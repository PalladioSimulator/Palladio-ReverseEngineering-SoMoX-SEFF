package org.somox.gast2seff.jobs;

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
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.qosannotations.QoSAnnotations;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.system.System;
import org.somox.analyzer.AnalysisResult;
import org.somox.util.DefaultResourceEnvironment;
import org.somox.configuration.AbstractMoxConfiguration;
import org.somox.kdmhelper.SoMoXUtil;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

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
public class SaveSoMoXModelsJob implements IBlackboardInteractingJob<SoMoXBlackboard> {

    // ---------------------------------
    // Static Data Fields
    // ---------------------------------

    /** The path inside the project to store the internal architecture model */
    private static final String PATH_SOURCECODE_DECORATOR_REPOSITORY = "/internal_architecture_model.sourcecodedecorator";
    private static final String PATH_SYSTEM_MODEL = "/internal_architecture_model.system";
    private static final String PATH_QOS_ANNOTATIONS_MODEL = "/internal_architecture_model.samm_qosannotation";
    private static final String PATH_REPOSITORY_MODEL = "/internal_architecture_model.repository";
    private static final String PATH_ALLOCATION_MODEL = "/internal_architecture_model.allocation";
    private static final String PATH_RESOURCE_ENVIRONMENT_MODEL = "/internal_architecture_model.resourceenvironment";

    private final Logger logger = Logger.getLogger(SaveSoMoXModelsJob.class);

    /** The somox blackboard to interact with. */
    private SoMoXBlackboard blackboard = null;

    /** The resource set of the core to work with */
    private ResourceSet resourceSet = null;

    /** The somox configuration. */
    private AbstractMoxConfiguration somoxConfiguration = null;

    public SaveSoMoXModelsJob(final AbstractMoxConfiguration somoxConfiguration) {
        this.somoxConfiguration = somoxConfiguration;
        this.resourceSet = new ResourceSetImpl();
        this.resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
    }

    /**
     * @param blackBoard
     *            the blackBoard to set
     */
    @Override
    public void setBlackboard(final SoMoXBlackboard blackBoard) {
        this.blackboard = blackBoard;
    }

    @Override
    public void execute(final IProgressMonitor arg0) throws JobFailedException, UserCanceledException {

        final AnalysisResult result = this.blackboard.getAnalysisResult();

        final String outputFolder = this.somoxConfiguration.getFileLocations().getOutputFolder();

        // save the new internal architecture model
        try {
            this.saveRepositoryModel(result.getInternalArchitectureModel(), outputFolder);
            this.saveSourceCodeDecoratorRepository(result.getSourceCodeDecoratorRepository(), outputFolder);
            this.saveSystemModel(result.getSystemModel(), outputFolder);
            this.saveQoSAnnotationsModel(result.getQosAnnotationModel(), outputFolder);
            this.saveResourceEnvironmentModel(DefaultResourceEnvironment.getDefaultResourceEnvironment(), outputFolder);
            this.saveAllocationModel(result.getAllocation(), outputFolder);
        } catch (final IOException e) {
            this.logger.error("Model Analyzer failed.", e);
            throw new JobFailedException("Unable to save SoMoX Models", e);
        }

    }

    private void saveSourceCodeDecoratorRepository(final SourceCodeDecoratorRepository repository,
            final String outputFolder) throws IOException {
        this.save(repository, outputFolder + PATH_SOURCECODE_DECORATOR_REPOSITORY);
    }

    private void saveSystemModel(final System system, final String outputFolder) throws IOException {
        this.save(system, outputFolder + PATH_SYSTEM_MODEL);
    }

    private void saveQoSAnnotationsModel(final QoSAnnotations serviceArchitectureModel, final String outputFolder)
            throws IOException {
        this.save(serviceArchitectureModel, outputFolder + PATH_QOS_ANNOTATIONS_MODEL);
    }

    private void saveRepositoryModel(final Repository repository, final String outputFolder) throws IOException {
        this.save(repository, outputFolder + PATH_REPOSITORY_MODEL);
    }

    private void saveAllocationModel(final Allocation allocation, final String outputFolder) throws IOException {
        this.save(allocation, outputFolder + PATH_ALLOCATION_MODEL);
    }

    private void saveResourceEnvironmentModel(final ResourceEnvironment resourceEnvironment, final String outputFolder)
            throws IOException {
        this.save(resourceEnvironment, outputFolder + PATH_RESOURCE_ENVIRONMENT_MODEL);
    }

    private void save(final EObject emfObject, final String path) throws IOException {
        final ResourceSet resourceSet = this.getResourceSetForURI();
        // URI scriptURI = fileURI;
        URI uri = null;
        if (!SoMoXUtil.isStandalone()) {
            uri = URI.createPlatformResourceURI(path, true);
        } else {
            uri = URI.createFileURI(path);
        }


        // Create a resource for this file.
        final Resource resource = resourceSet.createResource(uri);
        // Add object to the contents.
        resource.getContents().add(emfObject);

        final HashMap<Object, Object> saveOptions = new HashMap<Object, Object>();
        saveOptions.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);

        resource.save(saveOptions);
    }

    private ResourceSet getResourceSetForURI() {
        final ResourceSet resourceSet = new ResourceSetImpl();
        // Register the default resource factory -- only needed for stand-alone!
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
        return resourceSet;
    }

    @Override
    public String getName() {
        return "Save SoMoX Models Job";
    }

    @Override
    public void cleanup(final IProgressMonitor arg0) throws CleanupFailedException {
        // TODO Auto-generated method stub

    }

}
