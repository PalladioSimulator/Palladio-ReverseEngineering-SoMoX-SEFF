package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.builder.util.InterfacePortBuilderHelper;
import org.somox.analyzer.simplemodelanalyzer.builder.util.SubComponentInformation;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.resources.defaultmodels.DefaultModelLoader;

//import de.fzi.gast.core.Root;
import eu.qimpress.samm.deployment.allocation.AllocationFactory;
import eu.qimpress.samm.deployment.allocation.Service;
import eu.qimpress.samm.staticstructure.ComponentEndpoint;
import eu.qimpress.samm.staticstructure.Connector;
import eu.qimpress.samm.staticstructure.Interface;
import eu.qimpress.samm.staticstructure.InterfacePort;
import eu.qimpress.samm.staticstructure.Port;
import eu.qimpress.samm.staticstructure.PrimitiveComponent;
import eu.qimpress.samm.staticstructure.ServiceArchitectureModel;
import eu.qimpress.samm.staticstructure.StaticstructureFactory;
import eu.qimpress.samm.staticstructure.SubcomponentEndpoint;
import eu.qimpress.samm.staticstructure.SubcomponentInstance;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SammSystemImplementatingClassesLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorFactory;

/**
 * Builder for SAMM system + architecture model structures.
 * Additionally creates default allocation.
 * @author Klaus Krogmann
 */
public class SammSystemBuilder extends AbstractBuilder {
		
	private static Logger logger = Logger.getLogger(SammSystemBuilder.class);

	private DefaultModelLoader defaultModelLoader;
	
	private ComponentAndTypeNaming namingStrategy;
	private ComponentBuilder componentBuilder;
		
	/**
	 * Main builder used to create model elements of the SAMM during component detection with SoMoX. 
	 * @param gastModel The GAST model containing the analyzed source code
	 * @param somoxConfiguration SoMoX configuration object
	 * @param analysisResult Object holding the root elements of the models to create
	 */
	public SammSystemBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration,
			AnalysisResult analysisResult,
			ComponentBuilder componentBuilder) {
		super(gastModel, somoxConfiguration, analysisResult);		
	
		logger.debug("Initialising SAMM system builder");

		this.componentBuilder = componentBuilder;
		this.namingStrategy = componentBuilder.getComponentAndTypeNamingStrategy();
				
		this.defaultModelLoader = new DefaultModelLoader();
	}
	
	/**
	 * Replicates ports of inner components and establishes delegation connectors for them.
	 * Updates the SAMM system. Creates a SAMM for the last composite component of the
	 * repository model. Creates a default allocation with to a default target environment.
	 */
	public void buildServiceArchitectureModel() {
		buildServiceArchitectureModel(				
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
				logger.debug("non-contained component: " + compLinkToCheckWhetherContained.getComponent().getName() + " used for the system level");
			}
		}
				
		return nonContainedComponents;
	}
	
	/**
	 * Replicates ports of inner components and establishes delegation connectors for them.
	 * Updates the SAMM system.
	 * @param innerComponents List of Component which shall become instances of the SAMM system.
	 */
	private void buildServiceArchitectureModel(List<ComponentImplementingClassesLink> innerComponents) {
		ServiceArchitectureModel sammSystem = analysisResult.getServiceArchitectureModel();		
		sammSystem.setName("SoMoX Reverse Engineered System");
		
		SammSystemImplementatingClassesLink sammLink =
			SourceCodeDecoratorFactory.eINSTANCE.createSammSystemImplementatingClassesLink();
		sammLink.setServiceArchitectureModel(sammSystem);
		// FIXME: currently saving results in invalid serialisation
		//this.analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink().add(sammLink);
				
		Set<SubComponentInformation> subComponentInformationSet = new HashSet<SubComponentInformation>();
		
		for(ComponentImplementingClassesLink compLink : innerComponents) {
		
			// create subcomponent instances
			// TODO: change to builder use
			//componentBuilder.createSubComponentInstances(listToSet(innerComponents), sammSystem);			
			SubcomponentInstance subComponentInstance =
				StaticstructureFactory.eINSTANCE.createSubcomponentInstance();
			subComponentInstance.setRealizedBy(compLink.getComponent());
			subComponentInstance.setName(compLink.getComponent().getName());
			sammSystem.getSubcomponents().add(subComponentInstance);			
			sammLink.getSubComponents().add(compLink);
									
			// Service allocation
			Service service = AllocationFactory.eINSTANCE.createService();
			sammSystem.getService().add(service);
			service.setSubcomponentInstance(subComponentInstance);
			service.setName(compLink.getComponent().getName());
			service.setContainer(defaultModelLoader.getDefaultContainer());

			// replicate provided ports for SAMM system:
			for(InterfaceSourceCodeLink provIfLink : compLink.getProvidedInterfaces()) {
				createPortAndDelegationConnector(sammSystem, compLink,
						subComponentInstance, provIfLink, true);
			}	
		}
		
		// create assembly connectors among system components
		// execute only once: possible since here no decorator is used
		componentBuilder.getInsideCompositeComponentAssemblyConnectorStrategy().
			buildAssemblyConnectors(sammSystem, innerComponents); 			
				
		// collect information on non-connected interfaces
		Iterable<SubComponentInformation> subComponentInformation =
			InterfacePortBuilderHelper.collectInformationOnNonBoundInterfaces(sammLink, sammSystem, false); //Link must be SAMM!
		Iterator<SubComponentInformation> iterator = subComponentInformation.iterator();
		while(iterator.hasNext()) {
			subComponentInformationSet.add(iterator.next());
		}			
		
		// required ports not allowed/supported for SAMM system thus capture by dummy component
		// create dummy components for non-connected interfaces and
		// build assembly connectors for the newly created dummy component:
		PrimitiveComponent dummyComponent = DummyComponentBuilder.createDummyComponent(
				subComponentInformationSet, sammSystem, defaultModelLoader.getDefaultContainer());
		this.analysisResult.getInternalArchitectureModel().getComponenttype().add(dummyComponent);
		
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
			ServiceArchitectureModel sammSystem,
			ComponentImplementingClassesLink compLink,
			SubcomponentInstance subComponentInstance,
			InterfaceSourceCodeLink ifLink,
			boolean isProvided) {
		InterfacePort outerIfPort = StaticstructureFactory.eINSTANCE.createInterfacePort();
		outerIfPort.setProvidingComponentType(sammSystem);
		outerIfPort.setInterfaceType(ifLink.getInterface());

		// naming:
		if(isProvided) {			
			outerIfPort.setName(
					namingStrategy.createProvidedSystemPortName(ifLink.getInterface(), compLink.getComponent()));
		} else {
			outerIfPort.setName(
					namingStrategy.createRequiredSystemPortName(ifLink.getInterface(), compLink.getComponent()));
		}
		
		// subcomponent endpoint
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
		
		Connector connector = StaticstructureFactory.eINSTANCE.createConnector();				
		connector.getEndpoints().add(subComponentEndpoint);
		connector.getEndpoints().add(componentEndpoint);		
		
		sammSystem.getConnector().add(connector);
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
