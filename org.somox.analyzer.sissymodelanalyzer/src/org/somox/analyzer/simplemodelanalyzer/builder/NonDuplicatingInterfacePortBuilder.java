package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.builder.util.InterfacePortBuilderHelper;
import org.somox.analyzer.simplemodelanalyzer.builder.util.SubComponentInformation;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.CompositionFactory;
import org.palladiosimulator.pcm.core.composition.ProvidedDelegationConnector;
import org.palladiosimulator.pcm.core.composition.RequiredDelegationConnector;
import org.palladiosimulator.pcm.repository.CompositeComponent;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.Role;

/**
 * Idea: All interfaces which are not required by internal interfaces (and consequently captured in
 * assembly connectors) are exposed as provided interfaces (interface ports) of the composite component.
 * <br>
 * Additionally creates provided and required delegation connectors.
 * @author Klaus Krogmann
 *
 */
public class NonDuplicatingInterfacePortBuilder extends AbstractBuilder implements IRoleBuilderStrategy {

	private static final Logger logger = Logger.getLogger(NonDuplicatingInterfacePortBuilder.class);
	
	private ComponentAndTypeNaming componentTypeNaming;
	
	public NonDuplicatingInterfacePortBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration, AnalysisResult analysisResult, 
			ComponentAndTypeNaming componentTypeNaming) {
		super(gastModel, somoxConfiguration, analysisResult);
		this.componentTypeNaming = componentTypeNaming;
	}

	/**
	 * Strategy: Create a new provided role for all inner interfaces which are not associated in
	 * assembly connectors. Additionally creates provided and required delegation connectors.
	 * 
	 * @param componentLink The composite component for which to build interface ports 
	 */
	public List<OperationProvidedRole> buildProvidedRole(
			ComponentImplementingClassesLink componentLink) {
		assert (componentLink.isIsCompositeComponent());

		List<OperationProvidedRole> roles = new LinkedList<OperationProvidedRole>();
		
		if(componentLink.getComponent() instanceof ComposedStructure) {
			ComposedStructure resultCompositeComponent = (ComposedStructure) componentLink.getComponent();

			Iterable<SubComponentInformation> interfaceLinksNotUsedInConnectors = InterfacePortBuilderHelper.collectInformationOnNonBoundInterfaces(
					componentLink, resultCompositeComponent, true);
				
			// build each interface + connector
			for(SubComponentInformation currentInterfaceLink : interfaceLinksNotUsedInConnectors) {
				roles.add(createProvidedRoleAndConnector(componentLink, currentInterfaceLink));
			}
			
		} else {
			throw new IllegalArgumentException("Only composite components should use this builder.");
		}
		
		return roles;
	}

	/**
	 * Strategy: Create a new interface port for all inner interfaces which are not associated in
	 * assembly connectors. Additionally creates provided and required delegation connectors.
	 * @param componentLink The composite component for which to build interface ports 
	 */
	public List<OperationRequiredRole> buildRequiredRole(
			ComponentImplementingClassesLink componentLink) {
		assert (componentLink.isIsCompositeComponent());

		List<OperationRequiredRole> roles = new LinkedList<OperationRequiredRole>();

		if(componentLink.getComponent() instanceof ComposedStructure) {
			ComposedStructure resultCompositeComponent = (ComposedStructure) componentLink.getComponent();

			Iterable<SubComponentInformation> interfaceLinksNotUsedInConnectors = InterfacePortBuilderHelper.collectInformationOnNonBoundInterfaces(
					componentLink, resultCompositeComponent, false);
				
			// build each interface + connector
			for(SubComponentInformation currentInterfaceLink : interfaceLinksNotUsedInConnectors) {
				roles.add(createRequiredRoleAndConnector(componentLink, currentInterfaceLink)); 
			}
			
		} else {
			throw new IllegalArgumentException("Only composite components should use this builder.");
		}
		
		return roles;
	}


	/**
	 * Creates provided interface port + provided delegation connector
	 * <br>
	 * ATTENTION: nearly a clone of createProvidedInterfacePortAndConnector() 
	 * @param compositeComponentLink
	 * @param subComponentInformation
	 */
	private OperationProvidedRole createProvidedRoleAndConnector(
			ComponentImplementingClassesLink compositeComponentLink,
			SubComponentInformation subComponentInformation) {
		if( !(compositeComponentLink.getComponent() instanceof CompositeComponent) ) {
			throw new RuntimeException("must be a composite component");
		}
		
		Interface searchedInterface = subComponentInformation.getInterfaceSourceCodeLink().getInterface();
		OperationProvidedRole providedRole = findProvidedRoleInComponent(compositeComponentLink.getComponent(), searchedInterface);
		
		// add a role for the interface if it does not exist yet
		if(providedRole == null) {

			providedRole = RepositoryFactory.eINSTANCE.createOperationProvidedRole();
			
			providedRole.setEntityName(
					componentTypeNaming.createProvidedPortName(searchedInterface,compositeComponentLink.getComponent()));
			
			if(searchedInterface instanceof OperationInterface){
				providedRole.setProvidedInterface__OperationProvidedRole((OperationInterface) searchedInterface);
			} else {
				logger.error("interface type not yet supported: "+searchedInterface.getClass().getSimpleName());
			}
			
			//newProvidedRole.setDocumentation(subComponentInformation.getInterfaceSourceCodeLink().getInterface().getEntityName());
			compositeComponentLink.getComponent().getProvidedRoles_InterfaceProvidingEntity().add(providedRole);
			
			createDelegationConnector(compositeComponentLink,
					providedRole, subComponentInformation, true);			
			
			// Source code decorator:
			// Create interface source code link for parent class.
			// TODO: Check if this really makes sense. The method already operates on a SourceCodeLink (see above: subComponentInformation.getInterfaceSourceCodeLink()) 
			if(subComponentInformation.getInterfaceSourceCodeLink().getInterface() != null) {
				InterfaceSourceCodeLink newInterfaceLink = SourcecodedecoratorFactory.eINSTANCE.createInterfaceSourceCodeLink();
				newInterfaceLink.setInterface(subComponentInformation.getInterfaceSourceCodeLink().getInterface());
				newInterfaceLink.setGastClass(subComponentInformation.getInterfaceSourceCodeLink().getGastClass());
				compositeComponentLink.getProvidedInterfaces().add(newInterfaceLink);
	
				
				// add to parent repository:
				SourceCodeDecoratorRepository parentRepository = (SourceCodeDecoratorRepository) compositeComponentLink.eContainer();					
				parentRepository.getInterfaceSourceCodeLink().add(newInterfaceLink);
			} else {
				logger.warn("Source code decorator: InterfaceLink had no interface or class set.");
			}
		}
		
		return providedRole;
	}

	/**
	 * Creates required operation role + required delegation connector.<br>
	 * 
	 * This method produces OperationRequiredRoles only.<br>
	 * 
	 * <br>
	 * ATTENTION: nearly a clone of createProvidedInterfacePortAndConnector()<br>
	 * TODO: clean up CHECK FOR DUPLICATION IN SAMM BUILDER
	 * 
	 * 
	 * @param compositeComponentLink
	 * @param subComponentInformation
	 */
	private OperationRequiredRole createRequiredRoleAndConnector(
			ComponentImplementingClassesLink compositeComponentLink,
			SubComponentInformation subComponentInformation) {	
		
		
		OperationRequiredRole requiredRole = null;
		Set<Interface> allRequiredInterfaces = collectInterfacesForComponent(compositeComponentLink, false);
		
		Interface linkedInterface = subComponentInformation.getInterfaceSourceCodeLink().getInterface();
		
		if(!allRequiredInterfaces.contains(linkedInterface)) { //avoid duplicate interfaces
			
			requiredRole = RepositoryFactory.eINSTANCE.createOperationRequiredRole();
			
			if(linkedInterface == null){
				logger.warn("Source code decorator: InterfaceLink had no interface or class set.");
			} else if(linkedInterface instanceof OperationInterface){
					requiredRole.setRequiredInterface__OperationRequiredRole((OperationInterface) linkedInterface);
				requiredRole.setEntityName(
						componentTypeNaming.createRequiredPortName(
								subComponentInformation.getInterfaceSourceCodeLink().getInterface(),
								compositeComponentLink.getComponent()));
				// requiredRole.setDocumentation(subComponentInformation.getInterfaceSourceCodeLink().getInterface().getEntityName());
				requiredRole.setRequiringEntity_RequiredRole(compositeComponentLink.getComponent());
				compositeComponentLink.getComponent().getRequiredRoles_InterfaceRequiringEntity().add(requiredRole);
						
				// add link to source code decorator model
				InterfaceSourceCodeLink newInterfaceLink = SourcecodedecoratorFactory.eINSTANCE.createInterfaceSourceCodeLink();
				newInterfaceLink.setInterface(subComponentInformation.getInterfaceSourceCodeLink().getInterface());
				newInterfaceLink.setGastClass(subComponentInformation.getInterfaceSourceCodeLink().getGastClass());
				compositeComponentLink.getRequiredInterfaces().add(newInterfaceLink);
	
				// add to parent repository:
				SourceCodeDecoratorRepository parentRepository = (SourceCodeDecoratorRepository) compositeComponentLink.eContainer();					
				parentRepository.getInterfaceSourceCodeLink().add(newInterfaceLink);
			} else {
				logger.warn("Interface type yet not supported: "+linkedInterface.getClass().getSimpleName());
			}			
		} else {
			// do not add the same interface twice but added a required delegation connector
			// query existing port
			for(RequiredRole currentRequiredRole : compositeComponentLink.getComponent().getRequiredRoles_InterfaceRequiringEntity()) {
				if(currentRequiredRole instanceof OperationRequiredRole){
					OperationRequiredRole opReqRole = (OperationRequiredRole) currentRequiredRole;
					if(opReqRole.getRequiredInterface__OperationRequiredRole().equals(linkedInterface)) {
						requiredRole = opReqRole;
					}
					
				}else {
					logger.warn("Required role type yet not supported: "+currentRequiredRole.getClass().getSimpleName());
				}
			}
		}		
		// create required delegation connector -- even for already created required ports (can be used by multiple components)
		if(requiredRole != null) {
			createDelegationConnector(compositeComponentLink,
					requiredRole, subComponentInformation, false);
		} else {
			logger.warn("Could not find a required interface port which should have existed.");
		}
		
		return requiredRole;

	}
	

	/**
	 * Creates a new provides delegation connector.
	 * 
	 * @param compositeComponentLink Outer Composite component
	 * @param outerRole newly added outer port
	 * @param subComponentInformation information on the inner component instance and its ports
	 * @param isProvidedDelegationConnector switch provided and required
	 */
	private void createDelegationConnector(
			ComponentImplementingClassesLink compositeComponentLink,
			OperationProvidedRole outerRole,
			SubComponentInformation subComponentInformation,
			boolean isProvidedDelegationConnector) {
		// new provides delegation connector:
		ProvidedDelegationConnector delegationConnector = CompositionFactory.eINSTANCE.createProvidedDelegationConnector();
		
		((CompositeComponent)compositeComponentLink.getComponent()).getConnectors__ComposedStructure().add(delegationConnector);

		//outer:
		delegationConnector.setOuterProvidedRole_ProvidedDelegationConnector(outerRole);
		Role innerRole = subComponentInformation.getRole();

		if(innerRole instanceof OperationProvidedRole){
			delegationConnector.setInnerProvidedRole_ProvidedDelegationConnector((OperationProvidedRole) innerRole);
			delegationConnector.setAssemblyContext_ProvidedDelegationConnector(subComponentInformation
					.getAssemblyContext());
		} else {
			logger.warn("Role not supported yet: "+innerRole.getClass().getSimpleName());
		}
	}
	

	/**
	 * Creates a new required delegation connector.
	 * 
	 * @param compositeComponentLink Outer Composite component
	 * @param outerRole newly added outer port
	 * @param subComponentInformation information on the inner component instance and its ports
	 * @param isProvidedDelegationConnector switch provided and required
	 */
	private void createDelegationConnector(
			ComponentImplementingClassesLink compositeComponentLink,
			OperationRequiredRole outerRole,
			SubComponentInformation subComponentInformation,
			boolean isProvidedDelegationConnector) {
		
		RequiredDelegationConnector delegationConnector = CompositionFactory.eINSTANCE.createRequiredDelegationConnector();
		
		delegationConnector.setOuterRequiredRole_RequiredDelegationConnector(outerRole);
		Role innerRole = subComponentInformation.getRole();
		if(innerRole instanceof OperationRequiredRole){
			// TODO burkha 23.4.2013 check this if assembly context works
			// correct here
			delegationConnector.setInnerRequiredRole_RequiredDelegationConnector((OperationRequiredRole) innerRole);
			delegationConnector.setAssemblyContext_RequiredDelegationConnector(subComponentInformation
					.getAssemblyContext());
		} else {
			logger.warn("Role not supported yet: "+innerRole.getClass().getSimpleName());
		}

		((CompositeComponent)compositeComponentLink.getComponent()).getConnectors__ComposedStructure().add(delegationConnector);
	}
	
	/**
	 * Try to find a provided role for a specific interface in a component.
	 * 
	 * @param component The component to check
	 * @param searchedInterface The interface to search for.
	 * @return
	 */
	private OperationProvidedRole findProvidedRoleInComponent(
			RepositoryComponent component,
			Interface searchedInterface) {
		
		
		for(ProvidedRole existingRole : component.getProvidedRoles_InterfaceProvidingEntity()){
			
			if(existingRole instanceof OperationProvidedRole){
				
				OperationProvidedRole opProvRole = (OperationProvidedRole) existingRole;
				if(opProvRole.getProvidedInterface__OperationProvidedRole() != null 
						&& opProvRole.getProvidedInterface__OperationProvidedRole().equals(searchedInterface)){
					return opProvRole;
				}
				
			}
			
		}
		
		return null;
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
