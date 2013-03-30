package org.somox.analyzer.simplemodelanalyzer.builder.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.somox.analyzer.simplemodelanalyzer.builder.InterfacesBoundInConnectorFilter;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;

import de.uka.ipd.sdq.pcm.core.composition.ComposedStructure;
import de.uka.ipd.sdq.pcm.core.composition.Connector;
import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.repository.CompositeComponent;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.Role;

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
		Collection<EndPoint> connectorEndpoints = collectConnectorEndpoints(outerComponentToCheck);
			
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
	 * Collects inner connector endpoints of this composite component 
	 * (first level no inner containments). 
	 * @param compositeComponent Connectors of this component (inner)
	 * @return
	 */
	private static Collection<EndPoint> collectConnectorEndpoints(			
			CompositeStructure compositeComponent) {
		Collection<EndPoint> connectorEndpoints = new ArrayList<EndPoint>();
		for(Connector currentConnector : compositeComponent.getConnector()) { //
			if(currentConnector.getEndpoints().size() > 2) {
				throw new RuntimeException("n to m connectors (n/m > 2) not supported!"); //actually could be supported here, but semantics unclear...
			}
			for(EndPoint currentEndPoint : currentConnector.getEndpoints()) {
				connectorEndpoints.add(currentEndPoint);
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
				SubcomponentInstance matchingSubComponentInstance = null;
				if(componentLink.getComponent() != null) { // regular case: a component link
					matchingSubComponentInstance = 
						getSubComponentInstance( (CompositeComponent)componentLink.getComponent(), currentSubComponentLink);
				} else { // SAMM system architecture case:
					if(componentLink instanceof SammSystemImplementatingClassesLink) {
						matchingSubComponentInstance = 
							getSubComponentInstance( ((SammSystemImplementatingClassesLink)componentLink).getServiceArchitectureModel(), currentSubComponentLink);
					}
				}
				InterfacePort interfacePort = getInterfacePort(currentSubComponentLink, currentInterfaceLinkSub, collectProvided);
				
				allInterfaceLinks.add(new SubComponentInformation(currentInterfaceLinkSub, interfacePort, matchingSubComponentInstance));
			}
		}
		return allInterfaceLinks;
	}
	

	
	/**
	 * Search matching InterfacePort. Condition:
	 * The linked subcomponent must provide the given linked interface
	 * @param subComponentLink
	 * @param interfaceLinkSub
	 * @param searchProvidedPort switch provided / required search
	 * @return
	 */
	public static Role getInterfacePort(
			ComponentImplementingClassesLink subComponentLink,
			InterfaceSourceCodeLink interfaceLinkSub,
			boolean searchProvidedPort) {
		EList<ProvidedRole> entities;
		
		if(searchProvidedPort) {
			entities = subComponentLink.getComponent().getProvidedRoles_InterfaceProvidingEntity();
		} else {
			entities = subComponentLink.getComponent().getRequiredRoles_InterfaceRequiringEntity();
		}
		for(Entity currentInterfacePort : entities) {
			if(interfaceLinkSub.getInterface().equals(currentInterfacePort.getInterfaceType())) {
				return currentInterfacePort;
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
	private static SubcomponentInstance getSubComponentInstance(
			CompositeStructure outerCompositeComponent,
			ComponentImplementingClassesLink subComponentLink) {
		for(SubcomponentInstance currentComponentInstance : outerCompositeComponent.getSubcomponents()) {
			if(currentComponentInstance.getRealizedBy().equals(subComponentLink.getComponent())) {
				return currentComponentInstance;
			}			
		}
		logger.warn("no subcomponent instance found");
		return null;
	}	
	


}
