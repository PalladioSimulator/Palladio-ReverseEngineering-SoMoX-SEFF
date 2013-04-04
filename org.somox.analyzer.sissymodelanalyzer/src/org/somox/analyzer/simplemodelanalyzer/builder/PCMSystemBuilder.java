package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
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
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.allocation.AllocationFactory;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.CompositionFactory;
import de.uka.ipd.sdq.pcm.core.composition.ProvidedDelegationConnector;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.OperationInterface;
import de.uka.ipd.sdq.pcm.repository.OperationProvidedRole;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;

//import de.fzi.gast.core.Root;

/**
 * Builder for SAMM system + architecture model structures. Additionally creates
 * default allocation.
 * 
 * @author Klaus Krogmann
 */
public class PCMSystemBuilder extends AbstractBuilder {

	private static Logger logger = Logger.getLogger(PCMSystemBuilder.class);

	private ComponentAndTypeNaming namingStrategy;
	private ComponentBuilder componentBuilder;
	private NonDuplicatingInterfacePortBuilder providedRoleBuilder;

	/**
	 * Main builder used to create model elements of the SAMM during component
	 * detection with SoMoX.
	 * 
	 * @param gastModel
	 *            The GAST model containing the analyzed source code
	 * @param somoxConfiguration
	 *            SoMoX configuration object
	 * @param analysisResult
	 *            Object holding the root elements of the models to create
	 */
	public PCMSystemBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration,
			AnalysisResult analysisResult, ComponentBuilder componentBuilder) {
		super(gastModel, somoxConfiguration, analysisResult);

		logger.debug("Initialising PCM system builder");

		this.componentBuilder = componentBuilder;
		this.namingStrategy = componentBuilder
				.getComponentAndTypeNamingStrategy();
		
		this.providedRoleBuilder = new NonDuplicatingInterfacePortBuilder(gastModel, somoxConfiguration, analysisResult, namingStrategy);
	}

	/**
	 * Replicates ports of inner components and establishes delegation
	 * connectors for them. Updates the SAMM system. Creates a SAMM for the last
	 * composite component of the repository model. Creates a default allocation
	 * with to a default target environment.
	 */
	public void buildSystemModel() {
		buildSystemModel(getNonContainedComponents());
	}

	/**
	 * Returns all components which are not used (subcomponent) in another
	 * composite component.
	 * 
	 * @return List of non-contained components
	 */
	private List<ComponentImplementingClassesLink> getNonContainedComponents() {
		ArrayList<ComponentImplementingClassesLink> nonContainedComponents = new ArrayList<ComponentImplementingClassesLink>();

		for (ComponentImplementingClassesLink compLinkToCheckWhetherContained : this.analysisResult
				.getSourceCodeDecoratorRepository()
				.getComponentImplementingClassesLink()) {

			boolean isComponentLinkToCheckContained = false;
			for (ComponentImplementingClassesLink potentialOuterCompLink : this.analysisResult
					.getSourceCodeDecoratorRepository()
					.getComponentImplementingClassesLink()) {
				if (potentialOuterCompLink.getSubComponents().contains(
						compLinkToCheckWhetherContained)) {
					isComponentLinkToCheckContained = true;
					break; // contained; no more looping required
				}
			}
			if (!isComponentLinkToCheckContained) {
				nonContainedComponents.add(compLinkToCheckWhetherContained);
				logger.debug("non-contained component: "
						+ compLinkToCheckWhetherContained.getComponent()
								.getEntityName() + " used for the system level");
			}
		}

		return nonContainedComponents;
	}

	/**
	 * Replicates ports of inner components and establishes delegation
	 * connectors for them. Updates the SAMM system.
	 * 
	 * @param innerComponents
	 *            List of Component which shall become instances of the SAMM
	 *            system.
	 */
	private void buildSystemModel(
			List<ComponentImplementingClassesLink> innerComponents) {
		System pcmSystem = analysisResult.getSystemModel();
		pcmSystem.setEntityName("SoMoX Reverse Engineered System");

		PCMSystemImplementatingClassesLink pcmLink = SourceCodeDecoratorFactory.eINSTANCE
				.createPCMSystemImplementatingClassesLink();
		pcmLink.setSystemModel(pcmSystem);
		// FIXME: currently saving results in invalid serialisation
		// this.analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink().add(sammLink);

		Set<SubComponentInformation> subComponentInformationSet = new HashSet<SubComponentInformation>();

		ResourceEnvironment resourceEnvironment = DefaultResourceEnvironment
				.getDefaultResourceEnvironment();
		ResourceContainer defaultContainer = resourceEnvironment
				.getResourceContainer_ResourceEnvironment().get(0);

		Allocation allocation = analysisResult.getAllocation();
		allocation.setEntityName("SoMoX Reverse Engineered Allocation Model");
		allocation.setSystem_Allocation(pcmSystem);
		allocation.setTargetResourceEnvironment_Allocation(resourceEnvironment);

		for (ComponentImplementingClassesLink compLink : innerComponents) {

			// create subcomponent instances
			AssemblyContext assemblyContext = CompositionFactory.eINSTANCE
					.createAssemblyContext();
			assemblyContext.setEncapsulatedComponent__AssemblyContext(compLink
					.getComponent());
			assemblyContext.setEntityName(compLink.getComponent()
					.getEntityName());
			pcmSystem.eContents().add(assemblyContext);
			pcmLink.getSubComponents().add(compLink);

			// create allocation context for system component
			AllocationContext allocationContext = AllocationFactory.eINSTANCE
					.createAllocationContext();
			allocation.getAllocationContexts_Allocation()
					.add(allocationContext);
			allocationContext
					.setAssemblyContext_AllocationContext(assemblyContext);
			allocationContext.setEntityName(compLink.getComponent()
					.getEntityName());
			allocationContext
					.setResourceContainer_AllocationContext(defaultContainer);

			// create delegation connectors between system and inner component
			// provided roles
			for (InterfaceSourceCodeLink provIfLink : compLink
					.getProvidedInterfaces()) {
				createProvidedRoleAndDelegationConnector(pcmSystem, compLink,
						assemblyContext, provIfLink);
			}
		}

		// create assembly connectors among system components
		// execute only once: possible since here no decorator is used
		componentBuilder.getInsideCompositeComponentAssemblyConnectorStrategy()
				.buildAssemblyConnectors(pcmSystem, innerComponents);

		// collect information on non-connected interfaces
		Iterable<SubComponentInformation> subComponentInformation = InterfacePortBuilderHelper
				.collectInformationOnNonBoundInterfaces(pcmLink, pcmSystem,
						false); // Link must be SAMM!
		Iterator<SubComponentInformation> iterator = subComponentInformation
				.iterator();
		while (iterator.hasNext()) {
			subComponentInformationSet.add(iterator.next());
		}

		// required ports not allowed/supported for SAMM system thus capture by
		// dummy component
		// create dummy components for non-connected interfaces and
		// build assembly connectors for the newly created dummy component:
		BasicComponent dummyComponent = DummyComponentBuilder
				.createDummyComponent(subComponentInformationSet, pcmSystem,
						resourceEnvironment, analysisResult);
		this.analysisResult.getInternalArchitectureModel()
				.getComponents__Repository().add(dummyComponent);
	}

	// TODO: Check in the svn history what this method was used for. Maybe
	// garbage
	// private Set<ComponentImplementingClassesLink>
	// listToSet(List<ComponentImplementingClassesLink> list) {
	// Set<ComponentImplementingClassesLink> set = new
	// HashSet<ComponentImplementingClassesLink>();
	// for(ComponentImplementingClassesLink link : list) {
	// boolean success = set.add(link);
	// assert (success == true);
	// }
	// return set;
	// }

	private void createProvidedRoleAndDelegationConnector(System pcmSystem,
			ComponentImplementingClassesLink compLink,
			AssemblyContext assemblyContext, InterfaceSourceCodeLink ifLink) {

		if(ifLink.getInterface() instanceof OperationInterface){
			OperationInterface operationInterface = (OperationInterface) ifLink.getInterface();
		
			OperationProvidedRole providedRole = RepositoryFactory.eINSTANCE
					.createOperationProvidedRole();
			providedRole.setProvidingEntity_ProvidedRole(pcmSystem);
			providedRole.setEntityName(namingStrategy.createProvidedSystemPortName(
					ifLink.getInterface(), compLink.getComponent()));
			providedRole.setProvidedInterface__OperationProvidedRole(operationInterface);
			
			// ensure the inner component has a provided role for our interface
			// theoretically, multiple ones are created internally, but this is assumed to not 
			// happen in practice, so we just pick up the first one.
			List<OperationProvidedRole> innerProvidedRoles = providedRoleBuilder.buildProvidedRole(compLink);
			if(innerProvidedRoles.size() > 1){
				logger.warn("assumption did not hold ;) ");
			}
			OperationProvidedRole innerProvidedRole = innerProvidedRoles.get(0);
	
			ProvidedDelegationConnector delegationConnector = CompositionFactory.eINSTANCE.createProvidedDelegationConnector();
			delegationConnector.setAssemblyContext_ProvidedDelegationConnector(assemblyContext);
			delegationConnector.setInnerProvidedRole_ProvidedDelegationConnector(innerProvidedRole);
			delegationConnector.setParentStructure__Connector(pcmSystem);
			delegationConnector.setEntityName(ifLink.getInterface().getEntityName());

			pcmSystem.getConnectors__ComposedStructure().add(delegationConnector);
			
		} else {
			logger.warn("Interface type not yet supported: "+ifLink.getInterface().getClass().getSimpleName());
		}
	}

}
