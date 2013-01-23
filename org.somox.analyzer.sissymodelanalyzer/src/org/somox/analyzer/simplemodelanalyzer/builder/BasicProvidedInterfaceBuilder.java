package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.List;

import org.apache.log4j.Logger;
import org.somox.analyzer.AnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;

//import de.fzi.gast.core.Root;
import eu.qimpress.samm.staticstructure.InterfacePort;
import eu.qimpress.samm.staticstructure.StaticstructureFactory;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorFactory;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * Simple strategy for deriving provided interfaces. Creates a new provided
 * role for all inner interfaces and updates the source code decorator accordingly.
 * @author Klaus Krogmann
 */
public class BasicProvidedInterfaceBuilder extends AbstractBuilder implements IInterfacePortBuilderStrategy {

	static final Logger logger = Logger.getLogger(BasicProvidedInterfaceBuilder.class);
	
	public BasicProvidedInterfaceBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration, AnalysisResult analysisResult) {
		super(gastModel, somoxConfiguration, analysisResult);
	}

	/**
	 * Current strategy: create a new interface for all inner interfaces.
	 * @param result
	 * @param compositeComponentSubgraph
	 */
	public void buildInterfacePort(
			ComponentImplementingClassesLink result) {

		for(ComponentImplementingClassesLink componentLink : result.getSubComponents()) {
			List<InterfaceSourceCodeLink> interfaceLinkSubList = componentLink.getProvidedInterfaces();
			for(InterfaceSourceCodeLink currentInterfaceLinkSub : interfaceLinkSubList) {
				
				// SAMM:
				InterfacePort newProvidedPort = StaticstructureFactory.eINSTANCE.createInterfacePort();
				newProvidedPort.setInterfaceType(currentInterfaceLinkSub.getInterface());
				newProvidedPort.setName(currentInterfaceLinkSub.getInterface().getName());
				newProvidedPort.setProvidingComponentType(componentLink.getComponent());
				result.getComponent().getProvided().add(newProvidedPort);

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
			}
		}
		
	}

}
