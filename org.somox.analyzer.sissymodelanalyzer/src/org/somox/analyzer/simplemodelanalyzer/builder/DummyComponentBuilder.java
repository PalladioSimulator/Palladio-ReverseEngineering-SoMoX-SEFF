package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Set;

import org.somox.analyzer.simplemodelanalyzer.SimpleAnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.builder.util.InstanceComponentTuple;
import org.somox.analyzer.simplemodelanalyzer.builder.util.SubComponentInformation;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.CompositionFactory;
import de.uka.ipd.sdq.pcm.core.entity.InterfaceProvidingEntity;
import de.uka.ipd.sdq.pcm.core.entity.InterfaceProvidingRequiringEntity;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.OperationInterface;
import de.uka.ipd.sdq.pcm.repository.OperationProvidedRole;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;

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
	public static BasicComponent createDummyComponent(
			Set<SubComponentInformation> subComponentInformationOnNonBoundInterfacePorts,
			System pcmSystem, ResourceEnvironment resourceEnv, SimpleAnalysisResult simpleAnalysisResult) {
		InstanceComponentTuple dummyComponentInfo = createDummyComponent(pcmSystem, resourceEnv, simpleAnalysisResult );
		
		// add all interfaces as provided to component:
		for(SubComponentInformation subCompInfo : subComponentInformationOnNonBoundInterfacePorts) {
			
			OperationProvidedRole newProvidedRole = RepositoryFactory.eINSTANCE.createOperationProvidedRole();
			newProvidedRole.setEntityName(subCompInfo.getOperationRequiredRole().getEntityName() + " (prov dummy)");
			newProvidedRole.setEntityName(subCompInfo.getOperationRequiredRole().getEntityName() + " (prov dummy)");
			//newProvidedInterfacePort.setDocumentation("SoMoX created provided port");
			dummyComponentInfo.basicComponent.getProvidedRoles_InterfaceProvidingEntity().add(newProvidedRole);
			
			
			// create assembly connector:
			AssemblyConnectorBuilder.createAssemblyConnector(
					pcmSystem, 
					subCompInfo.getOperationRequiredRole(), newProvidedRole, 
					subCompInfo.getAssemblyContext(), dummyComponentInfo.assemblyContext);
		}		
		
		return dummyComponentInfo.basicComponent;		
	}
	
	private static InstanceComponentTuple createDummyComponent(
			System pcmSystem, ResourceEnvironment resourceEnv, SimpleAnalysisResult sar) {
		BasicComponent basicComponent = RepositoryFactory.eINSTANCE.createBasicComponent();
		basicComponent.setEntityName("SoMoX System-Level Dummy Component");
		//basicComponent.setDocumentation("Captures calls to system-external services.");
			
		AssemblyContext assemblyContext = CompositionFactory.eINSTANCE.createAssemblyContext();
		assemblyContext.setEncapsulatedComponent__AssemblyContext(basicComponent);
		assemblyContext.setEntityName("SoMoX Dummy Component Instance");
		pcmSystem.getAssemblyContexts__ComposedStructure().add(assemblyContext);
		
		
		// allocate service
		//Allocation allocation = 
		Allocation allocation = sar.getAllocation();
		allocation.setTargetResourceEnvironment_Allocation(resourceEnv);
		
		InstanceComponentTuple instanceComponentTuple = new InstanceComponentTuple();
		instanceComponentTuple.basicComponent = basicComponent;
		instanceComponentTuple.assemblyContext = assemblyContext;
		
		return instanceComponentTuple;
	}	
	

}
