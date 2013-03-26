package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.builder.util.InterfacePortBuilderHelper;
import org.somox.analyzer.simplemodelanalyzer.builder.util.SubComponentInformation;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;

//import de.fzi.gast.core.Root;
import eu.qimpress.samm.staticstructure.ComponentEndpoint;
import eu.qimpress.samm.staticstructure.CompositeComponent;
import eu.qimpress.samm.staticstructure.CompositeStructure;
import eu.qimpress.samm.staticstructure.Connector;
import eu.qimpress.samm.staticstructure.Interface;
import eu.qimpress.samm.staticstructure.InterfacePort;
import eu.qimpress.samm.staticstructure.StaticstructureFactory;
import eu.qimpress.samm.staticstructure.SubcomponentEndpoint;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorFactory;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * Idea: All interfaces which are not required by internal interfaces (and consequently captured in
 * assembly connectors) are exposed as provided interfaces (interface ports) of the composite component.
 * <br>
 * Additionally creates provided and required delegation connectors.
 * @author Klaus Krogmann
 *
 */
public class NonDuplicatingInterfacePortBuilder extends AbstractBuilder implements IProvidedRoleBuilderStrategy {

	private static final Logger logger = Logger.getLogger(NonDuplicatingInterfacePortBuilder.class);
	
	private ComponentAndTypeNaming componentTypeNaming;
	
	/**
	 * Mode of the builder
	 */
	private boolean isProvidedBuilder;
	
	public NonDuplicatingInterfacePortBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration, AnalysisResult analysisResult, 
			boolean isProvidedBuilder, ComponentAndTypeNaming componentTypeNaming) {
		super(gastModel, somoxConfiguration, analysisResult);
		this.isProvidedBuilder = isProvidedBuilder;
		this.componentTypeNaming = componentTypeNaming;
	}

	/**
	 * Strategy: Create a new interface port for all inner interfaces which are not associated in
	 * assembly connectors. Additionally creates provided and required delegation connectors.
	 * @param componentLink The composite component for which to build interface ports 
	 */
	public void buildProvidedRole(
			ComponentImplementingClassesLink componentLink) {
		assert (componentLink.isCompositeComponent());

		if(componentLink.getComponent() instanceof CompositeStructure) {
			CompositeStructure resultCompositeComponent = (CompositeStructure) componentLink.getComponent();

			Iterable<SubComponentInformation> interfaceLinksNotUsedInConnectors = InterfacePortBuilderHelper.collectInformationOnNonBoundInterfaces(
					componentLink, resultCompositeComponent, this.isProvidedBuilder);
				
			// build each interface + connector
			for(SubComponentInformation currentInterfaceLink : interfaceLinksNotUsedInConnectors) {
								
				// port building
				if(this.isProvidedBuilder) {
					createProvidedInterfacePortAndConnector(componentLink, currentInterfaceLink);
				} else {
					createRequiredInterfacePortAndConnector(componentLink, currentInterfaceLink); 
				}
			}
			
		} else {
			logger.warn("Only composite components should use this builder.");
		}
	}


	/**
	 * Creates provided interface port + provided delegation connector
	 * <br>
	 * ATTENTION: nearly a clone of createProvidedInterfacePortAndConnector() 
	 * @param compositeComponentLink
	 * @param subComponentInformation
	 */
	private void createProvidedInterfacePortAndConnector(
			ComponentImplementingClassesLink compositeComponentLink,
			SubComponentInformation subComponentInformation) {
		if( !(compositeComponentLink.getComponent() instanceof CompositeComponent) ) {
			throw new RuntimeException("must be a composite component");
		}
		
		Set<Interface> allRequiredInterfaces = collectInterfacesForComponent(compositeComponentLink, true);
		if(!allRequiredInterfaces.contains(subComponentInformation.getInterfaceSourceCodeLink().getInterface())) { //avoid duplicate interfaces

			// SAMM:
			InterfacePort newProvidedPort = StaticstructureFactory.eINSTANCE.createInterfacePort();
			newProvidedPort.setInterfaceType(subComponentInformation.getInterfaceSourceCodeLink().getInterface());
			newProvidedPort.setName(
					componentTypeNaming.createProvidedPortName(
							subComponentInformation.getInterfaceSourceCodeLink().getInterface(),
							compositeComponentLink.getComponent()));
			newProvidedPort.setDocumentation(subComponentInformation.getInterfaceSourceCodeLink().getInterface().getName());
			newProvidedPort.setProvidingComponentType(compositeComponentLink.getComponent());
			compositeComponentLink.getComponent().getProvided().add(newProvidedPort);
			
			createDelegationConnector(compositeComponentLink,
					newProvidedPort, subComponentInformation, true);			
			
			// Source code decorator:				
			if(subComponentInformation.getInterfaceSourceCodeLink().getInterface() != null && 
					subComponentInformation.getInterfaceSourceCodeLink().getInterface() != null) {
				InterfaceSourceCodeLink newInterfaceLink = SourceCodeDecoratorFactory.eINSTANCE.createInterfaceSourceCodeLink();
				newInterfaceLink.setInterface(subComponentInformation.getInterfaceSourceCodeLink().getInterface());
				newInterfaceLink.setGastClass(subComponentInformation.getInterfaceSourceCodeLink().getGastClass());
				compositeComponentLink.getProvidedInterfaces().add(newInterfaceLink);
	
				
				// add to parent repository:
				SourceCodeDecoratorRepository parentRepository = (SourceCodeDecoratorRepository) compositeComponentLink.eContainer();					
				parentRepository.getInterfaceSourceCodeLink().add(newInterfaceLink);
			} else {
				logger.warn("Source code decorator: InterfaceLink had no interface or class set.");
			}
		} else {
			//OK: do not add the same interface twice
		}
	}
	
	/**
	 * Creates required interface port + required delegation connector.
	 * <br>
	 * ATTENTION: nearly a clone of createProvidedInterfacePortAndConnector() //TODO: clean up CHECK FOR DUPLICATION IN SAMM BUILDER
	 * @param compositeComponentLink
	 * @param subComponentInformation
	 */
	private void createRequiredInterfacePortAndConnector(
			ComponentImplementingClassesLink compositeComponentLink,
			SubComponentInformation subComponentInformation) {	
		
		
		InterfacePort requiredPort = null;
		Set<Interface> allRequiredInterfaces = collectInterfacesForComponent(compositeComponentLink, false);
		if(!allRequiredInterfaces.contains(subComponentInformation.getInterfaceSourceCodeLink().getInterface())) { //avoid duplicate interfaces
			
			// SAMM:
			requiredPort = StaticstructureFactory.eINSTANCE.createInterfacePort();
			requiredPort.setInterfaceType(subComponentInformation.getInterfaceSourceCodeLink().getInterface());
			requiredPort.setName(
					componentTypeNaming.createRequiredPortName(
							subComponentInformation.getInterfaceSourceCodeLink().getInterface(),
							compositeComponentLink.getComponent()));
			requiredPort.setDocumentation(subComponentInformation.getInterfaceSourceCodeLink().getInterface().getName());
			requiredPort.setRequiringComponentType(compositeComponentLink.getComponent());
			compositeComponentLink.getComponent().getRequired().add(requiredPort);
						
			// Source code decorator:				
			if(subComponentInformation.getInterfaceSourceCodeLink().getInterface() != null &&
					subComponentInformation.getInterfaceSourceCodeLink().getInterface() != null) {
				InterfaceSourceCodeLink newInterfaceLink = SourceCodeDecoratorFactory.eINSTANCE.createInterfaceSourceCodeLink();
				newInterfaceLink.setInterface(subComponentInformation.getInterfaceSourceCodeLink().getInterface());
				newInterfaceLink.setGastClass(subComponentInformation.getInterfaceSourceCodeLink().getGastClass());
				compositeComponentLink.getRequiredInterfaces().add(newInterfaceLink);
	
				// add to parent repository:
				SourceCodeDecoratorRepository parentRepository = (SourceCodeDecoratorRepository) compositeComponentLink.eContainer();					
				parentRepository.getInterfaceSourceCodeLink().add(newInterfaceLink);
			} else {
				logger.warn("Source code decorator: InterfaceLink had no interface or class set.");
			}			
		} else {
			// do not add the same interface twice but added a required delegation connector
			// query existing port
			for(InterfacePort currentRequiredInterfacePort : compositeComponentLink.getComponent().getRequired()) {
				if(currentRequiredInterfacePort.getInterfaceType().equals(
						subComponentInformation.getInterfaceSourceCodeLink().getInterface())) {
					requiredPort = currentRequiredInterfacePort;
				}
			}
		}		
		// create required delegation connector -- even for already created required ports (can be used by multiple components)
		if(requiredPort != null) {
			createDelegationConnector(compositeComponentLink,
					requiredPort, subComponentInformation, false);
		} else {
			logger.warn("Could not find a required interface port which should have existed.");
		}

	}
	

	/**
	 * Creates a new provides delegation connector.
	 * @param compositeComponentLink Outer Composite component
	 * @param newOuterPort newly added outer port
	 * @param subComponentInformation information on the inner component instance and its ports
	 * @param isProvidedDelegationConnector switch provided and required
	 */
	private void createDelegationConnector(
			ComponentImplementingClassesLink compositeComponentLink,
			InterfacePort newOuterPort,
			SubComponentInformation subComponentInformation,
			boolean isProvidedDelegationConnector) {
		// new provides delegation connector:
		Connector delegationConnector = StaticstructureFactory.eINSTANCE.createConnector();
		((CompositeComponent)compositeComponentLink.getComponent()).getConnector().add(delegationConnector);

		//documentation name:
		String documentation = "";
		if(isProvidedDelegationConnector) {
			documentation += "provided";
		} else {
			documentation += "required";
		}
		documentation += " delegation connector " + 
			compositeComponentLink.getComponent().getName() + " to " +
			subComponentInformation.getSubComponentInstance().getRealizedBy().getName();
		delegationConnector.setDocumentation(documentation);

		//outer:
		ComponentEndpoint componentEndpoint = StaticstructureFactory.eINSTANCE.createComponentEndpoint(); 
		componentEndpoint.setPort(newOuterPort);
		delegationConnector.getEndpoints().add(componentEndpoint);
		
		//inner:
		SubcomponentEndpoint subcomponentEndpoint = StaticstructureFactory.eINSTANCE.createSubcomponentEndpoint();
		subcomponentEndpoint.setPort(subComponentInformation.getInterfacePort());
		subcomponentEndpoint.setSubcomponent(subComponentInformation.getSubComponentInstance());
		delegationConnector.getEndpoints().add(subcomponentEndpoint);
	}

	/**
	 * Collects all interfaces of a given component.
	 * @param compositeComponentLink component for which to search existing interfaces
	 * @param collectProvided if set to true collects provided interface; required interfaces otherwise
	 * @return
	 */
	private Set<Interface> collectInterfacesForComponent(
			ComponentImplementingClassesLink compositeComponentLink,
			boolean collectProvided) {
		Set<Interface> returnInterfaces = new HashSet<Interface>();
		
		EList<InterfaceSourceCodeLink> ifLinks;
		if(collectProvided) {
			ifLinks = compositeComponentLink.getProvidedInterfaces();
		} else {
			ifLinks = compositeComponentLink.getRequiredInterfaces();
		}
		for(InterfaceSourceCodeLink currentIfLink : ifLinks) {
			returnInterfaces.add(currentIfLink.getInterface());
		}
		
		return returnInterfaces;
	}

}
