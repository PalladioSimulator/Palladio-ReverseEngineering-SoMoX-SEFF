package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.builder.util.DefaultResourceEnvironment;
import org.somox.analyzer.simplemodelanalyzer.builder.util.InterfacePortBuilderHelper;
import org.somox.analyzer.simplemodelanalyzer.builder.util.SubComponentInformation;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorFactory;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.allocation.AllocationFactory;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.CompositionFactory;
import de.uka.ipd.sdq.pcm.core.composition.Connector;
import de.uka.ipd.sdq.pcm.repository.Interface;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;
import de.uka.ipd.sdq.pcm.repository.RequiredRole;
import de.uka.ipd.sdq.pcm.repository.Role;
import de.uka.ipd.sdq.pcm.system.System;
import de.uka.ipd.sdq.pcm.system.SystemFactory;
//import de.fzi.gast.core.Root;

/**
 * Builder for SAMM system + architecture model structures.
 * Additionally creates default allocation.
 * @author Klaus Krogmann
 */
public class PCMSystemBuilder extends AbstractBuilder {
		
	private static Logger logger = Logger.getLogger(PCMSystemBuilder.class);

	private System defaultSystem;
	private Repository defaultContainer;
	
	private ComponentAndTypeNaming namingStrategy;
	private ComponentBuilder componentBuilder;
		
	/**
	 * Main builder used to create model elements of the SAMM during component detection with SoMoX. 
	 * @param gastModel The GAST model containing the analyzed source code
	 * @param somoxConfiguration SoMoX configuration object
	 * @param analysisResult Object holding the root elements of the models to create
	 */
	public PCMSystemBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration,
			AnalysisResult analysisResult,
			ComponentBuilder componentBuilder) {
		super(gastModel, somoxConfiguration, analysisResult);		
	
		logger.debug("Initialising SAMM system builder");

		this.componentBuilder = componentBuilder;
		this.namingStrategy = componentBuilder.getComponentAndTypeNamingStrategy();
				
		this.defaultSystem = SystemFactory.eINSTANCE.createSystem();
		this.defaultContainer = RepositoryFactory.eINSTANCE.createRepository();
	}
	
	/**
	 * Replicates ports of inner components and establishes delegation connectors for them.
	 * Updates the SAMM system. Creates a SAMM for the last composite component of the
	 * repository model. Creates a default allocation with to a default target environment.
	 */
	public void buildSystemModel() {
		buildSystemModel(				
				getNonContainedComponents()		
		);	
	}

	/**
	 * Returns all components which are not used (subcomponent) in another composite
	 * component.
	 * @return List of non-contained components
	 */
	private List<ComponentImplementingClassesLink> getNonContainedComponents() {
		ArrayList<ComponentImplementingClassesLink> nonContainedComponents = new ArrayList<ComponentImplementingClassesLink>();
		
		for(ComponentImplementingClassesLink compLinkToCheckWhetherContained : this.analysisResult.
				getSourceCodeDecoratorRepository().getComponentImplementingClassesLink()) {
			
			boolean isComponentLinkToCheckContained = false;
			for(ComponentImplementingClassesLink potentialOuterCompLink : this.analysisResult.
						getSourceCodeDecoratorRepository().getComponentImplementingClassesLink()) {
				if(potentialOuterCompLink.getSubComponents().contains(compLinkToCheckWhetherContained)) {				
					isComponentLinkToCheckContained = true;
					break; // contained; no more looping required
				}
			}
			if(!isComponentLinkToCheckContained) {
				nonContainedComponents.add(compLinkToCheckWhetherContained);
				logger.debug("non-contained component: " + compLinkToCheckWhetherContained.getComponent().getEntityName()
							+ " used for the system level");
			}
		}
				
		return nonContainedComponents;
	}
	
	/**
	 * Replicates ports of inner components and establishes delegation connectors for them.
	 * Updates the SAMM system.
	 * @param innerComponents List of Component which shall become instances of the SAMM system.
	 */
	private void buildSystemModel(List<ComponentImplementingClassesLink> innerComponents) {
		System pcmSystem = analysisResult.getSystemModel();		
		pcmSystem.setEntityName("SoMoX Reverse Engineered System");
		
		PCMSystemImplementatingClassesLink pcmLink =
			SourceCodeDecoratorFactory.eINSTANCE.createPCMSystemImplementatingClassesLink();
		pcmLink.setSystemModel(pcmSystem);
		// FIXME: currently saving results in invalid serialisation
		//this.analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink().add(sammLink);
				
		Set<SubComponentInformation> subComponentInformationSet = new HashSet<SubComponentInformation>();
		
		Allocation allocation = analysisResult.getAllocation();
		allocation.setEntityName("SoMoX Reverse Engineered Allocation");
		allocation.setSystem_Allocation(pcmSystem);
		allocation.setTargetResourceEnvironment_Allocation(DefaultResourceEnvironment.getDefaultResourceEnvironment());
		
		for(ComponentImplementingClassesLink compLink : innerComponents) {
		
			// create subcomponent instances
			AssemblyContext assemblyContext = CompositionFactory.eINSTANCE.createAssemblyContext();
			assemblyContext.setEncapsulatedComponent__AssemblyContext(compLink.getComponent());
			assemblyContext.setEntityName(compLink.getComponent().getEntityName());
			pcmSystem.eContents().add(assemblyContext);
			pcmLink.getSubComponents().add(compLink);
									
			// replicate provided ports for SAMM system:
			for(InterfaceSourceCodeLink provIfLink : compLink.getProvidedInterfaces()) {
				createPortAndDelegationConnector(pcmSystem, compLink,
						assemblyContext, provIfLink, true);
			}	
		}
		
		// create assembly connectors among system components
		// execute only once: possible since here no decorator is used
		componentBuilder.getInsideCompositeComponentAssemblyConnectorStrategy().
			buildAssemblyConnectors(pcmSystem, innerComponents);
				
		// collect information on non-connected interfaces
		Iterable<SubComponentInformation> subComponentInformation =
			InterfacePortBuilderHelper.collectInformationOnNonBoundInterfaces(pcmLink, pcmSystem, false); //Link must be SAMM!
		Iterator<SubComponentInformation> iterator = subComponentInformation.iterator();
		while(iterator.hasNext()) {
			subComponentInformationSet.add(iterator.next());
		}			
		
		// required ports not allowed/supported for SAMM system thus capture by dummy component
		// create dummy components for non-connected interfaces and
		// build assembly connectors for the newly created dummy component:
		// TODO: Can PCM systems have required ports?
		/*BasicComponent dummyComponent = DummyComponentBuilder.createDummyComponent(
				subComponentInformationSet, pcmSystem, defaultContainer);
		this.analysisResult.getInternalArchitectureModel().getComponents__Repository().add(dummyComponent);*/		
	}
	

	private Set<ComponentImplementingClassesLink> listToSet(List<ComponentImplementingClassesLink> list) {
		Set<ComponentImplementingClassesLink> set = new HashSet<ComponentImplementingClassesLink>();
		for(ComponentImplementingClassesLink link : list) {
			boolean success = set.add(link);
			assert (success == true);
		}		
		return set;
	}

	private void createPortAndDelegationConnector(
			System pcmSystem,
			ComponentImplementingClassesLink compLink,
			AssemblyContext assemblyContext,
			InterfaceSourceCodeLink ifLink,
			boolean isProvided) {
		Role providedRequiredRole = null;
		if(isProvided){
			ProvidedRole providedRole = RepositoryFactory.eINSTANCE.createInfrastructureProvidedRole();
			providedRole.setProvidingEntity_ProvidedRole(pcmSystem);
			providedRequiredRole = providedRole;
		}
		else{
			RequiredRole requiredRole = RepositoryFactory.eINSTANCE.createInfrastructureRequiredRole();
			requiredRole.setRequiringEntity_RequiredRole(pcmSystem);
			providedRequiredRole = requiredRole;
		}
		
		// naming:
		if(isProvided) {			
			providedRequiredRole.setEntityName(
					namingStrategy.createProvidedSystemPortName(ifLink.getInterface(), compLink.getComponent()));
		} else {
			providedRequiredRole.setEntityName(
					namingStrategy.createRequiredSystemPortName(ifLink.getInterface(), compLink.getComponent()));
		}
		
		
		// subcomponent endpoint
		Provi
		SubcomponentEndpoint subComponentEndpoint = StaticstructureFactory.eINSTANCE.createSubcomponentEndpoint();
		subComponentEndpoint.setSubcomponent(subComponentInstance);

		if(isProvided) {
			subComponentEndpoint.setPort( 
					getMatchingPort(compLink.getComponent().getProvided(), ifLink.getInterface()) ); //inner port
		} else {
			subComponentEndpoint.setPort( 
					getMatchingPort(compLink.getComponent().getRequired(), ifLink.getInterface()) ); //inner port			
		}

		// component endpoint
		
		ComponentEndpoint componentEndpoint = StaticstructureFactory.eINSTANCE.createComponentEndpoint();
		componentEndpoint.setPort(outerIfPort); //outer port				
		
		Connector connector;
		connector.setParentStructure__Connector(componentEndpoint);
		Connector connector = StaticstructureFactory.eINSTANCE.createConnector();				
		connector.getEndpoints().add(subComponentEndpoint);
		connector.getEndpoints().add(componentEndpoint);		
		
		pcmSystem.getConnectors__ComposedStructure().add(connector);
	}
	
	private Port getMatchingPort(EList<InterfacePort> interfacePorts, Interface theInterface) {
		for(InterfacePort currentIfPort : interfacePorts) {
			if(currentIfPort.getInterfaceType().equals(theInterface)) {
				return currentIfPort;
			}
		}
		logger.warn("no interface port found for " + theInterface.getName());
		return null;
	}


}
