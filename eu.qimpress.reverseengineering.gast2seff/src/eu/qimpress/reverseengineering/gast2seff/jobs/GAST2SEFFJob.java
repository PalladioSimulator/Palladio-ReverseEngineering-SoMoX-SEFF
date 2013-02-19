/**
 * 
 */
package eu.qimpress.reverseengineering.gast2seff.jobs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.qimpressgast.GASTBehaviour;
import org.somox.qimpressgast.GASTBehaviourRepository;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

//import de.fzi.gast.statements.BlockStatement;//GAST2SEFFCHANGE
import de.uka.ipd.sdq.workflow.IJob;
import de.uka.ipd.sdq.workflow.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;
import eu.qimpress.reverseengineering.gast2seff.visitors.BasicFunctionClassificationStrategy;
import eu.qimpress.reverseengineering.gast2seff.visitors.FunctionCallClassificationVisitor;
import eu.qimpress.reverseengineering.gast2seff.visitors.GastStatementVisitor;
import eu.qimpress.samm.behaviour.Behaviour;
import eu.qimpress.samm.behaviour.BehaviourFactory;
import eu.qimpress.samm.behaviour.GastBehaviourStub;
import eu.qimpress.samm.behaviour.SeffBehaviourStub;
import eu.qimpress.samm.qosannotation.QosAnnotations;
import eu.qimpress.samm.staticstructure.PrimitiveComponent;
import eu.qimpress.samm.staticstructure.ServiceArchitectureModel;
import eu.qimpress.seff.AbstractAction;
import eu.qimpress.seff.ResourceDemandingBehaviour;
import eu.qimpress.seff.ResourceDemandingSEFF;
import eu.qimpress.seff.SeffRepository;
import eu.qimpress.seff.StartAction;
import eu.qimpress.seff.StopAction;
import eu.qimpress.seff.seffFactory;

/**
 * Transformation Job transforming a SAM instance with GAST Behaviours into a SAM instance with SEFF
 * behaviours
 * 
 * @author Steffen Becker, Klaus Krogmann
 */
public class GAST2SEFFJob  implements IBlackboardInteractingJob<SoMoXBlackboard> {


	private Logger logger = Logger.getLogger(GAST2SEFFJob.class);
	
	/** The somox blackboard to interact with. */
	private SoMoXBlackboard blackboard = null;
	
	/**
	 * The resource set used to load and store all reources needed for the transformation
	 */
	private ResourceSet resourceSet = new ResourceSetImpl();
	
	/**
	 * Resources containing the models
	 */
//	private Resource sammInstance = null;
	private Resource gastInstance = null; //required to load only
//	private Resource gastBehaviourRepository = null;
//	private Resource seffBehaviourRepository = null;
//	private Resource sammQosAnnotations = null;
	private GASTBehaviourRepository gastBehaviourRepositoryModel = null;
	private Resource sourceCodeDecorator = null;
	private SourceCodeDecoratorRepository sourceCodeDecoratorModel = null;
	private QosAnnotations sammQosAnnotationsModel = null;

	/** The somox configuration. */
	private SoMoXConfiguration somoxConfiguration = null;

	private FunctionCallClassificationVisitor typeVisitor = null;
	
	private HashMap<String, EObject> idToeObjectMap;
	
	private HashMap<Object,Object> xmlNameToFeatureMap; 
	
	public GAST2SEFFJob(SoMoXConfiguration somoxConfiguration) {
		super();
				
		// performance optimisation:
		Map<URI, Resource> cache = new HashMap<URI, Resource>();
		((ResourceSetImpl)resourceSet).setURIResourceMap(cache);	
		idToeObjectMap = new HashMap<String, EObject>();
		xmlNameToFeatureMap = new HashMap<Object,Object>();
		this.somoxConfiguration = somoxConfiguration;
	}
	
	/* (non-Javadoc)
	 * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void execute(IProgressMonitor monitor) throws JobFailedException,
			UserCanceledException {
		
//		//check preconditons:
//		if(sammInstanceURI.isEmpty() || !sammInstanceURI.isFile() ||
//				gastBehaviourRepositoryURI.isEmpty() || !gastBehaviourRepositoryURI.isFile()) {
//			String error = "missing model files (samm or gast behaviour repository) to apply GAST2SEFF";
//			logger.error(error);
//			throw new JobFailedException(error);
//		}
//		
//		monitor.subTask("loading models from resources");
//
//		this.sammInstance = loadResource(sammInstanceURI, false);
//		
		//TODO adapt to MoDisco Java
		//FIXME 
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
        String basePath = workspace.getRoot().getRawLocation().toOSString();
		String gastModelPath = somoxConfiguration.getFileLocations().getAnalyserInputFile();
		URI gastURI = URI.createFileURI(basePath + File.separator + gastModelPath);
		logger.debug(gastURI);
		//URI gastURI = sammInstanceURI.trimFileExtension().path() + "testsomoxgast2seff_java2kdm.xmi".appendFileExtension(GAST_EXTENSION);
		this.gastInstance = loadResource(gastURI, true); //FIXME: implement automated search for GAST file
//		
//		this.gastBehaviourRepository = loadResource(gastBehaviourRepositoryURI, false);
//		this.gastBehaviourRepositoryModel = (GASTBehaviourRepository) this.gastBehaviourRepository.getContents().get(0);
//		this.sammQosAnnotations = loadResource(sammQosAnnotationsURI, false);
//		this.sammQosAnnotationsModel = (QosAnnotations) this.sammQosAnnotations.getContents().get(0);
//		this.sourceCodeDecorator = loadResource(sourceCodeDecoratorURI, false);
//		this.sourceCodeDecoratorModel = (SourceCodeDecoratorRepository) this.sourceCodeDecorator.getContents().get(0);
		
		monitor.subTask("loading models from blackboard");

//		this.sammInstance = loadResource(sammInstanceURI, false);
//		this.gastBehaviourRepository = loadResource(gastBehaviourRepositoryURI, false);
//		this.sammQosAnnotations = loadResource(sammQosAnnotationsURI, false);
//		this.sourceCodeDecorator = loadResource(sourceCodeDecoratorURI, false);

		AnalysisResult result = blackboard.getAnalysisResult();
		ServiceArchitectureModel samm = result.getServiceArchitectureModel();
		this.gastBehaviourRepositoryModel = result.getGastBehaviourRepository();
		this.sammQosAnnotationsModel = result.getQosAnnotationModel();
		this.sourceCodeDecoratorModel = result.getSourceCodeDecoratorRepository();

		// resource to write to
		// this.seffBehaviourRepository = createResource(seffBehaviourRepositoryURI);
		
		SeffRepository seffRepository = seffFactory.eINSTANCE.createSeffRepository();
		
		IProgressMonitor subMonitor = new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN);
		subMonitor.setTaskName("Creating SEFF behaviour");
		// TreeIterator<EObject> iterator = sammInstance.getAllContents();	
//		TreeIterator<EObject> iterator = samm.eAllContents();
		Iterator<GASTBehaviour> iterator = this.gastBehaviourRepositoryModel.getGastbehaviour().iterator();
		while (iterator.hasNext()) {
			GASTBehaviour gASTBehaviour = iterator.next();
			GastBehaviourStub stub = gASTBehaviour.getGastbehaviourstub();
			String name = stub.getName();
			logger.info("Found GAST behaviour, generating SEFF behaviour for it: " + name);
			
			ResourceDemandingSEFF resourceDemandingSEFF = generateSEFFForGASTBehaviour(stub);				
			seffRepository.getResourceDemandingSeff().add(resourceDemandingSEFF);
			monitor.worked(1);
			
//			EObject eObject = iterator.next();
//			if (eObject instanceof GastBehaviourStub) {
//				String name = ((GastBehaviourStub) eObject).getName();
//				logger.info("Found GAST behaviour, generating SEFF behaviour for it: " + name);
//				
//				ResourceDemandingSEFF resourceDemandingSEFF = generateSEFFForGASTBehaviour((GastBehaviourStub) eObject);				
//				seffRepository.getResourceDemandingSeff().add(resourceDemandingSEFF);
//				monitor.worked(1);
//			}
		}
		
		// Create default annotations
		DefaultQosAnnotationsBuilder qosAnnotationBuilder = new DefaultQosAnnotationsBuilder(
				this.sammQosAnnotationsModel, seffRepository);
		qosAnnotationBuilder.buildDefaultQosAnnotations();
		
		subMonitor.done();
		
		monitor.subTask("saving models");
		
		blackboard.setSeffRepository(seffRepository);
		//saveResources(seffRepository);
	}

//	/**
//	 * FIXME: currently generates new IDs upon saving the models
//	 * @param seffRepository
//	 * @throws JobFailedException
//	 */
//	private void saveResources(SeffRepository seffRepository)
//			throws JobFailedException {
//		seffBehaviourRepository.getContents().add(seffRepository);
//		saveResource(seffBehaviourRepository);
//		saveResource(sammInstance);
//		saveResource(sammQosAnnotations);		
//		saveResource(gastBehaviourRepository);
//	}

	/* (non-Javadoc)
	 * @see de.uka.ipd.sdq.workflow.IJob#getName()
	 */
	@Override
	public String getName() {
		return "GAST2SEFF Transformation Job";
	}

	/* (non-Javadoc)
	 * @see de.uka.ipd.sdq.workflow.IJob#rollback(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void rollback(IProgressMonitor monitor)
			throws RollbackFailedException {
		// Not needed
	}

	/**
	 * Load an existing resource in this class' resource set
	 * @param uri
	 * @return
	 * @throws JobFailedException
	 */
	private Resource loadResource(URI uri, boolean loadFailAcceptable) throws JobFailedException {
		logger.debug("load start " + uri);
		
		Resource resource = this.resourceSet.createResource(uri);		
		// performance optimisation:		
		Map<Object, Object> loadOptions = ((XMIResourceImpl)resource).getDefaultLoadOptions();
		loadOptions.put(XMIResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		loadOptions.put(XMIResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		loadOptions.put(XMIResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE);
		loadOptions.put(XMIResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());		
		loadOptions.put(XMIResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, xmlNameToFeatureMap);
		
		((ResourceImpl)resource).setIntrinsicIDToEObjectMap(idToeObjectMap);
		try {
			resource.load(loadOptions);		
		} catch (IOException e) {
			if(loadFailAcceptable) {
				logger.warn("Loading of a required resource failed. This could result in a reduced model loading performance.", e);
			} else {
				throw new JobFailedException("Loading of a required resource failed",e);
			}
		}
		logger.debug("load end");
		return resource;
	}	

	/**
	 * Create a new resource
	 * @param uri
	 * @return
	 */
	private Resource createResource(URI uri) {
		Resource resource = this.resourceSet.createResource(uri);			
		
		return resource;
	}
	
	/**
	 * Save the given resource
	 * @param resource
	 * @throws JobFailedException
	 */
	private void saveResource(Resource resource) throws JobFailedException {
		try {
			resource.save(new HashMap<Object, Object>());
		} catch (IOException e) {
			throw new JobFailedException("Saving model output failed",e);
		}
	}
	
	/**
	 * Create a new SAMM SEFF based on the given GAST Behaviour stub
	 * @param gastBehaviourStub The GAST Behaviour stub whose behaviour is used in the transformation
	 * to derive the SAMM SEFF
	 * @param seff The SEFF which is filled by this method
	 * @return The completed SEFF, returned for convinience 
	 * @throws JobFailedException 
	 */
	private ResourceDemandingSEFF createSeff(
			GastBehaviourStub gastBehaviourStub,
			ResourceDemandingSEFF seff) throws JobFailedException {		
		StartAction start = seffFactory.eINSTANCE.createStartAction();
		StopAction stop = seffFactory.eINSTANCE.createStopAction();

		// initialise for new component / seff to reverse engineer:
		PrimitiveComponent primitiveComponent = (PrimitiveComponent)gastBehaviourStub.eContainer();
		typeVisitor = new FunctionCallClassificationVisitor(new BasicFunctionClassificationStrategy(
				sourceCodeDecoratorModel, primitiveComponent));		
		
		seff.getSteps().add(start);
		
		Block body = findBody(gastBehaviourStub);//GAST2SEFFCHANGE
		logger.trace("visiting (seff entry): " + gastBehaviourStub.getName());
		if (body != null) {	
			
			//removelater
			AbstractMethodDeclaration method = (AbstractMethodDeclaration) body.eContainer();
			if (method.getName().equals("orderProducts")) {
				int a=0; a=a+1;
			};
			//removelater
			typeVisitor.doSwitch(body); 
		
			GastStatementVisitor visitor = new GastStatementVisitor(typeVisitor.getAnnotations(), 
					seff, this.sourceCodeDecoratorModel, primitiveComponent);
			visitor.doSwitch(body);
		} else {
			logger.warn("Found GAST behaviour (" + gastBehaviourStub.getName() + ") without a method body... Skipping it...");
		}
			
		seff.getSteps().add(stop);
		
		connectActions(seff);
		
		return seff;
	}
	
	/**
	 * Retrieve the matching GAST behaviour stub from the GAST Behaviour repository
	 * @param gastBehaviourStub The gast behaviour stub for which a matching GAST behaviour is needed
	 * @return The GAST behaviour matching the gast behaviour stub
	 * @throws JobFailedException Thrown if the gast behaviour is missing in the model file
	 */
	private Block findBody(GastBehaviourStub gastBehaviourStub) throws JobFailedException {//GAST2SEFFCHANGE

		assert onlyOnceAsGastBehaviour(this.gastBehaviourRepositoryModel.getGastbehaviour(), gastBehaviourStub);
		if(!onlyOnceAsGastBehaviour(this.gastBehaviourRepositoryModel.getGastbehaviour(), gastBehaviourStub)){
			logger.error("Assertion fails - onlyOnceAsGastBehaviour");
		}
		
		for (GASTBehaviour behaviour : this.gastBehaviourRepositoryModel.getGastbehaviour()) {
			//removelater start
			logger.info("To search for: " + gastBehaviourStub.getId());
			logger.info("Iteration ID   " + behaviour.getGastbehaviourstub().getId());
			//removelater end
			if (behaviour.getGastbehaviourstub().getId().equals(gastBehaviourStub.getId())) { 
				logger.debug("Matching GastBehaviourSub found "+ gastBehaviourStub.getId());
				return behaviour.getBlockstatement();		
			}
		}
		logger.warn("Checked gastBehaviourRepository for " + gastBehaviourStub.getName() + " " + gastBehaviourStub.getId() + " but found none");
		return null; //FIXME: re-enable: exception 
		//throw new JobFailedException("Unable to find operation body for given method");	
	}

	/**
	 * For assertion only
	 * @param gastbehaviour
	 * @param gastBehaviourStub
	 * @return
	 */
	private boolean onlyOnceAsGastBehaviour(EList<GASTBehaviour> gastbehaviour,
			GastBehaviourStub gastBehaviourStub) {
		int i = 0;
		
		for (GASTBehaviour behaviour : gastbehaviour) {
			if (behaviour.getGastbehaviourstub().getId().equals(gastBehaviourStub.getId())) { 
				i++;
			}
		}
		
		return i == 1; //must be exactly one
	}

	/**
	 * Add connections to the SEFF actions assuming the actions are stored in a sequential order
	 * @param seff The behaviour for which connections will be created
	 */
	public static void connectActions(ResourceDemandingBehaviour seff) {
		AbstractAction previous = null;
		for (AbstractAction a : seff.getSteps()) {
			a.setPredecessor_AbstractAction(previous);
			previous = a;
		}
	}	
	
	/**
	 * Generate a SEFF for the given GAST behaviour
	 * @param gastBehaviourStub The gast behaviour stub for whose behaviour a SEFF is generated
	 * @return The generated SEFF
	 * @throws JobFailedException
	 */
	private ResourceDemandingSEFF generateSEFFForGASTBehaviour(
			GastBehaviourStub gastBehaviourStub) throws JobFailedException {
		ResourceDemandingSEFF resourceDemandingSEFF = seffFactory.eINSTANCE.createResourceDemandingSEFF();
				
		createSeff(gastBehaviourStub,resourceDemandingSEFF);
		
		SeffBehaviourStub seffBehaviourStub = findOrCreateBehaviourStub(gastBehaviourStub); 
		resourceDemandingSEFF.setSeffBehaviourStub(seffBehaviourStub);
		
		return resourceDemandingSEFF;
	}

	/**
	 * Finds an existing SEFF behaviour stub and reuses it or creates a new SEFF behaviour stub if there 
	 * is none for the given GAST behaviour stub
	 * @param gastBehaviourStub The GAST behaviour stub for which a matching SEFF behaviour stub is searched
	 * @return The found or newly created SEFF behaviour stub
	 */
	private SeffBehaviourStub findOrCreateBehaviourStub(GastBehaviourStub gastBehaviourStub) {
		PrimitiveComponent parentComponent = (PrimitiveComponent) gastBehaviourStub.eContainer();
		SeffBehaviourStub seffBehaviourStub = null;

		for (Behaviour behaviour : parentComponent.getOperationBehaviour()) {
			if (behaviour instanceof SeffBehaviourStub) {
				SeffBehaviourStub candidateStub = (SeffBehaviourStub) behaviour;
				if (candidateStub.getOperation() == gastBehaviourStub.getOperation()) {
					logger.debug("Found SEFF behaviour stub, reusing it...");
					seffBehaviourStub = candidateStub;
					break;
				}
			}
		}
		
		if (seffBehaviourStub == null)
			seffBehaviourStub = BehaviourFactory.eINSTANCE.createSeffBehaviourStub();
		
		seffBehaviourStub.setOperation(gastBehaviourStub.getOperation());
		parentComponent.getOperationBehaviour().add(seffBehaviourStub);
		
		return seffBehaviourStub;
	}

	/**
	 * @param blackBoard the blackBoard to set
	 */
	public void setBlackboard(SoMoXBlackboard blackBoard) {
		this.blackboard = blackBoard;
	}
}
