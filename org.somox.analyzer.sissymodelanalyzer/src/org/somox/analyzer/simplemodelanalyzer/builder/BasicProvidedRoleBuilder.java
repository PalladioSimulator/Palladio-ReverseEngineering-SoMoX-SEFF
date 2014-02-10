package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.somox.analyzer.AnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import de.uka.ipd.sdq.pcm.repository.OperationInterface;
import de.uka.ipd.sdq.pcm.repository.OperationProvidedRole;
import de.uka.ipd.sdq.pcm.repository.OperationRequiredRole;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;

/**
 * Simple strategy for deriving provided interfaces. Creates a new provided
 * role for all inner interfaces and updates the source code decorator accordingly.
 * @author Klaus Krogmann
 */
public class BasicProvidedRoleBuilder extends AbstractBuilder implements IRoleBuilderStrategy {

	static final Logger logger = Logger.getLogger(BasicProvidedRoleBuilder.class);
	
	public BasicProvidedRoleBuilder(Root astModel,
			SoMoXConfiguration somoxConfiguration, AnalysisResult analysisResult) {
		super(astModel, somoxConfiguration, analysisResult);
	}

	/**
	 * Current strategy: create a new provided role for all inner interfaces.
	 * @param result
	 * @param compositeComponentSubgraph
	 * @return 
	 */
	public List<OperationProvidedRole> buildProvidedRole(
			ComponentImplementingClassesLink result) {

		List<OperationProvidedRole> roles = new LinkedList<OperationProvidedRole>();
		
		for(ComponentImplementingClassesLink componentLink : result.getSubComponents()) {
			List<InterfaceSourceCodeLink> interfaceLinkSubList = componentLink.getProvidedInterfaces();
			for(InterfaceSourceCodeLink currentInterfaceLinkSub : interfaceLinkSubList) {
				
				if(currentInterfaceLinkSub.getInterface() instanceof OperationInterface){
				
					OperationInterface operationInterface = (OperationInterface) currentInterfaceLinkSub.getInterface();
					// PCM:
					OperationProvidedRole newProvidedRole = RepositoryFactory.eINSTANCE.createOperationProvidedRole();
					newProvidedRole.setProvidedInterface__OperationProvidedRole(operationInterface);
					newProvidedRole.setEntityName(operationInterface.getEntityName());
					//removelater start
					if(operationInterface.getEntityName().contains("Refresh")){
						
					}
					//removelater end
					
					newProvidedRole.setProvidingEntity_ProvidedRole(componentLink.getComponent());
					result.getComponent().getProvidedRoles_InterfaceProvidingEntity().add(newProvidedRole);
	
					roles.add(newProvidedRole);
					
					// Source code decorator:				
					if(currentInterfaceLinkSub.getInterface() != null && currentInterfaceLinkSub.getInterface() != null) {
						InterfaceSourceCodeLink newInterfaceLink = SourcecodedecoratorFactory.eINSTANCE.createInterfaceSourceCodeLink();
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
		
		return roles;
	}

	@Override
	public List<OperationRequiredRole> buildRequiredRole(
			ComponentImplementingClassesLink result) {
		// Yes we know this is a bloody dirty hack but we want to get this nasty migration run the first time.
		// if you see this comment in more than 1 year from now (today: 2013-04-04) just shake your had and curse us
		throw new RuntimeException("this method should not be executed.");
	}

}
