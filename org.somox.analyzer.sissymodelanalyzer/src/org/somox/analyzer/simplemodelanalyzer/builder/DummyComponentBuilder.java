package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Set;

import org.somox.analyzer.simplemodelanalyzer.builder.util.InstanceComponentTuple;
import org.somox.analyzer.simplemodelanalyzer.builder.util.SubComponentInformation;

import eu.qimpress.samm.deployment.allocation.AllocationFactory;
import eu.qimpress.samm.deployment.allocation.Service;
import eu.qimpress.samm.deployment.targetenvironment.Container;
import eu.qimpress.samm.staticstructure.InterfacePort;
import eu.qimpress.samm.staticstructure.PrimitiveComponent;
import eu.qimpress.samm.staticstructure.ServiceArchitectureModel;
import eu.qimpress.samm.staticstructure.StaticstructureFactory;
import eu.qimpress.samm.staticstructure.SubcomponentInstance;

/** 
 * Creates a system level dummy component.
 * @author Klaus Krogmann
 *
 */
public class DummyComponentBuilder {
	
	/**
	 * Creates a single dummy primitive Components and adds it
	 * as instance to the passed composite structure and creates
	 * assembly connectors to the newly created component.
	 * <br>
	 * Does NOT update the source code decorator!
	 * @param sammSystem The SAMM system to create the dummy in.
	 * @param container the container to allocate to
	 * @return the newly created component
	 */
	public static PrimitiveComponent createDummyComponent(
			Set<SubComponentInformation> subComponentInformationOnNonBoundInterfacePorts,
			ServiceArchitectureModel sammSystem, Container container) {
		InstanceComponentTuple dummyComponentInfo = createDummyComponent(sammSystem, container);
		
		// add all interfaces as provided to component:
		for(SubComponentInformation subCompInfo : subComponentInformationOnNonBoundInterfacePorts) {
			InterfacePort newProvidedInterfacePort = StaticstructureFactory.eINSTANCE.createInterfacePort();
			newProvidedInterfacePort.setInterfaceType(subCompInfo.getInterfacePort().getInterfaceType());
			newProvidedInterfacePort.setName(subCompInfo.getInterfacePort().getInterfaceType().getName() + " (prov dummy)");
			newProvidedInterfacePort.setDocumentation("SoMoX created provided port");
			dummyComponentInfo.primitiveComponentType.getProvided().add(newProvidedInterfacePort);
			
			// create assembly connector:
			AssemblyConnectorBuilder.createAssemblyConnector(
					sammSystem, 
					subCompInfo.getInterfacePort(), newProvidedInterfacePort, 
					subCompInfo.getSubComponentInstance(), dummyComponentInfo.componentInstance);
		}		
		
		return dummyComponentInfo.primitiveComponentType;		
	}
	
	private static InstanceComponentTuple createDummyComponent(
			ServiceArchitectureModel sammSystem, Container container) {
		PrimitiveComponent primitiveComponent = StaticstructureFactory.eINSTANCE.createPrimitiveComponent();
		primitiveComponent.setName("SoMoX System-Level Dummy Component");
		primitiveComponent.setDocumentation("Captures calls to system-external services.");
			
		SubcomponentInstance subComponentInstance =
			StaticstructureFactory.eINSTANCE.createSubcomponentInstance();
		subComponentInstance.setRealizedBy(primitiveComponent);
		subComponentInstance.setName("SoMoX Dummy Component Instance");
		sammSystem.getSubcomponents().add(subComponentInstance);
		
		// allocate service
		Service service = AllocationFactory.eINSTANCE.createService();
		sammSystem.getService().add(service);
		service.setSubcomponentInstance(subComponentInstance);
		service.setName("SoMoX Dummy Component Allocation Service");
		service.setContainer(container);	
		
		InstanceComponentTuple instanceComponentTuple = new InstanceComponentTuple();
		instanceComponentTuple.componentInstance = subComponentInstance;
		instanceComponentTuple.primitiveComponentType = primitiveComponent;
		
		return instanceComponentTuple;
	}	
	

}
