package org.somox.analyzer.simplemodelanalyzer.builder.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.somox.analyzer.simplemodelanalyzer.builder.InterfacesBoundInConnectorFilter;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.repository.CompositeComponent;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.Role;

/**
 * Query functionality useful when creating interface ports.
 * @author Klaus Krogmann
 *
 */
public class InterfacePortBuilderHelper {

	private static final Logger logger = Logger.getLogger(InterfacePortBuilderHelper.class);
	
	// TODO: make configurable
	private static final boolean EXHIBIT_ALL_INNER_PROVIDED_INTERFACES = true;
	
	/**
	 * Collects information on interfaces which are
	 * not bound in connectors (in the required case)
	 * and all interfaces in the provided case.
	 * 
	 * @param componentLink fitting to the component argument.
	 * @param outerComponentToCheck Component to look up non-connected interfaces for.
	 * @param isProvided Check for provided or required interfaces.
	 * @return
	 */
	public static Iterable<SubComponentInformation> collectInformationOnNonBoundInterfaces(
			ComponentImplementingClassesLink componentLink,
			ComposedStructure outerComponentToCheck, 
			boolean isProvided) {
		
		Collection<SubComponentInformation> allSubComponentInterfaceLinks = 
			collectInterfaceLinksOfSubComponents(componentLink, isProvided);						
		Collection<Role> connectorEndpoints = collectConnectorEndpoints(outerComponentToCheck);
			
		Iterable<SubComponentInformation> interfaceLinksNotUsedInConnectors;
		if(isProvided && EXHIBIT_ALL_INNER_PROVIDED_INTERFACES) {
			// exhibit all provided interfaces of inner components
			interfaceLinksNotUsedInConnectors = allSubComponentInterfaceLinks;
		} else {
			// require only interfaces which are not bound internally
			// filter: allSubComponentInterfaceLinks - connectorEndpoints
			InterfacesBoundInConnectorFilter filter = new InterfacesBoundInConnectorFilter(connectorEndpoints);			
			interfaceLinksNotUsedInConnectors = filter.filter(allSubComponentInterfaceLinks);
		}
		return interfaceLinksNotUsedInConnectors;
	}
	
	/**
	 * Collects inner connector roles of this composite component 
	 * (first level no inner containments).
	 *  
	 * @param compositeComponent Connectors of this component (inner)
	 * @return
	 */
	private static Collection<Role> collectConnectorEndpoints(			
			ComposedStructure compositeComponent) {
		Collection<Role> connectorEndpoints = new ArrayList<Role>();
		for(AssemblyContext context : compositeComponent.getAssemblyContexts__ComposedStructure()){
			RepositoryComponent component = context.getEncapsulatedComponent__AssemblyContext();
			for(ProvidedRole providedRole : component.getProvidedRoles_InterfaceProvidingEntity()) { 
				connectorEndpoints.add(providedRole);
			}
			for(RequiredRole requiredRole : component.getRequiredRoles_InterfaceRequiringEntity()) { 
				connectorEndpoints.add(requiredRole);
			}
		}
		
		return connectorEndpoints;
	}

	/**
	 * Collect all interfaces links of all direct sub components. 
	 * @param componentLink Component Link which to find subcomponents and collect the sub
	 * component's interface links
	 * @param collectProvided switch for collecting provided or required interfaces
	 * @return interface links of direct sub component
	 */
	private static Collection<SubComponentInformation> collectInterfaceLinksOfSubComponents(
			ComponentImplementingClassesLink componentLink, boolean collectProvided) {		
		Collection<SubComponentInformation> allInterfaceLinks = new ArrayList<SubComponentInformation>();
		for(ComponentImplementingClassesLink currentSubComponentLink : componentLink.getSubComponents()) {
			List<InterfaceSourceCodeLink> interfaceLinkSubList;
			if(collectProvided) {
				interfaceLinkSubList = currentSubComponentLink.getProvidedInterfaces();
			} else {
				interfaceLinkSubList = currentSubComponentLink.getRequiredInterfaces();
			}
			for(InterfaceSourceCodeLink currentInterfaceLinkSub : interfaceLinkSubList) {
				//collect additional information for connector creation:
				AssemblyContext matchingSubComponentInstance = null;
				if(componentLink.getComponent() != null) { // regular case: a component link
					matchingSubComponentInstance = 
						getSubComponentInstance( (CompositeComponent)componentLink.getComponent(), currentSubComponentLink);
				} else { // SAMM system architecture case:
					if(componentLink instanceof PCMSystemImplementatingClassesLink) {
						matchingSubComponentInstance = 
							getSubComponentInstance( ((PCMSystemImplementatingClassesLink)componentLink).getSystemModel(), currentSubComponentLink);
					}
				}
				Role role = getInterfacePort(currentSubComponentLink, currentInterfaceLinkSub, collectProvided);
				
				if(role != null){
					allInterfaceLinks.add(new SubComponentInformation(currentInterfaceLinkSub, role, matchingSubComponentInstance));
				} else {
					// if no matching role was found for the component do not try
					// to create a sub component information for the role.
					logger.warn("No role found for interface "+currentInterfaceLinkSub.getInterface().getEntityName()+" in Component "+currentSubComponentLink.getComponent().getEntityName());
				}
			}
		}
		return allInterfaceLinks;
	}
	

	
	/**
	 * Search matching Role.<br> 
	 * Condition: The linked subcomponent must provide/require the given linked interface
	 * 
	 * @param subComponentLink
	 * @param interfaceLinkSub
	 * @param searchProvidedRoles switch provided / required search
	 * @return
	 */
	public static Role getInterfacePort(
			ComponentImplementingClassesLink subComponentLink,
			InterfaceSourceCodeLink interfaceLinkSub,
			boolean searchProvidedRoles) {
		
		RepositoryComponent linkComponent = subComponentLink.getComponent();
		
		if(searchProvidedRoles) {
			EList<ProvidedRole> roles = linkComponent.getProvidedRoles_InterfaceProvidingEntity();
			for(ProvidedRole currentRole : roles) {
				if(currentRole instanceof OperationProvidedRole){
					Interface componentInterface = ((OperationProvidedRole)currentRole).getProvidedInterface__OperationProvidedRole();
					if(interfaceLinkSub.getInterface().equals(componentInterface)) {
						return currentRole;
					}
				} else {
					logger.warn("Role type not supported: "+currentRole.getClass().getSimpleName());
				}
			}
			
		} else {
			EList<RequiredRole> roles = linkComponent.getRequiredRoles_InterfaceRequiringEntity();
			for(RequiredRole currentRole : roles) {
				if(currentRole instanceof OperationRequiredRole){
					Interface componentInterface = ((OperationRequiredRole)currentRole).getRequiredInterface__OperationRequiredRole();
					if(interfaceLinkSub.getInterface().equals(componentInterface)) {
						return currentRole;
					}
				} else {
					logger.warn("Role type not supported: "+currentRole.getClass().getSimpleName());
				}
			}
		}
		logger.warn("should find an interface port");
		return null;
	}

	/**
	 * Search matching SubcomponentInstance. Condition: outerCompositeComponent
	 * must have the linked subcomponent.
	 * @param outerCompositeComponent
	 * @param subComponentLink
	 * @return
	 */
	private static AssemblyContext getSubComponentInstance(
			ComposedStructure outerCompositeComponent,
			ComponentImplementingClassesLink subComponentLink) {
		for(AssemblyContext innerAssemblyContext : outerCompositeComponent.getAssemblyContexts__ComposedStructure()) {
			if(innerAssemblyContext.getEncapsulatedComponent__AssemblyContext().equals(subComponentLink.getComponent())) {
				return innerAssemblyContext;
			}			
		}
		logger.warn("no subcomponent instance found");
		return null;
	}	
	


}
