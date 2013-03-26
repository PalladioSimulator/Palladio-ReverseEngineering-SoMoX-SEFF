package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.List;

import org.apache.log4j.Logger;
import org.somox.analyzer.AnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;

import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorFactory;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

import de.uka.ipd.sdq.pcm.repository.OperationInterface;
import de.uka.ipd.sdq.pcm.repository.OperationProvidedRole;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;

/**
 * Simple strategy for deriving provided interfaces. Creates a new provided
 * role for all inner interfaces and updates the source code decorator accordingly.
 * @author Klaus Krogmann
 */
public class BasicProvidedRoleBuilder extends AbstractBuilder implements IProvidedRoleBuilderStrategy {

	static final Logger logger = Logger.getLogger(BasicProvidedRoleBuilder.class);
	
	public BasicProvidedRoleBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration, AnalysisResult analysisResult) {
		super(gastModel, somoxConfiguration, analysisResult);
	}

	/**
	 * Current strategy: create a new provided role for all inner interfaces.
	 * @param result
	 * @param compositeComponentSubgraph
	 */
	public void buildProvidedRole(
			ComponentImplementingClassesLink result) {

		for(ComponentImplementingClassesLink componentLink : result.getSubComponents()) {
			List<InterfaceSourceCodeLink> interfaceLinkSubList = componentLink.getProvidedInterfaces();
			for(InterfaceSourceCodeLink currentInterfaceLinkSub : interfaceLinkSubList) {
				
				if(currentInterfaceLinkSub.getInterface() instanceof OperationInterface){
				
					OperationInterface operationInterface = (OperationInterface) currentInterfaceLinkSub.getInterface();
					// PCM:
					OperationProvidedRole newProvidedRole = RepositoryFactory.eINSTANCE.createOperationProvidedRole();
					newProvidedRole.setProvidedInterface__OperationProvidedRole(operationInterface);
					newProvidedRole.setEntityName(operationInterface.getEntityName());
					newProvidedRole.setProvidingEntity_ProvidedRole(componentLink.getComponent());
					result.getComponent().getProvidedRoles_InterfaceProvidingEntity().add(newProvidedRole);
	
					// Source code decorator:				
					if(currentInterfaceLinkSub.getInterface() != null && currentInterfaceLinkSub.getInterface() != null) {
						InterfaceSourceCodeLink newInterfaceLink = SourceCodeDecoratorFactory.eINSTANCE.createInterfaceSourceCodeLink();
						newInterfaceLink.setInterface(currentInterfaceLinkSub.getInterface());
						newInterfaceLink.setGastClass(currentInterfaceLinkSub.getGastClass());
						result.getProvidedInterfaces().add(newInterfaceLink);
	
						// add to parent repository:
						SourceCodeDecoratorRepository parentRepository = (SourceCodeDecoratorRepository) result.eContainer();
						parentRepository.getInterfaceSourceCodeLink().add(newInterfaceLink);
					} else {
						logger.warn("Source code decorator: InterfaceLink had no interface or class set.");
					}
				} else {
					logger.error("Unsupported PCM interface type: "+currentInterfaceLinkSub.getInterface().getClass().getSimpleName());
				}
			}
		}
		
	}

}
