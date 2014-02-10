package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.types.Type;
import org.jgrapht.Graph;
import org.somox.analyzer.AnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.metrics.ClusteringRelation;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.FileLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.ComposedStructure;
import de.uka.ipd.sdq.pcm.core.composition.CompositionFactory;
import de.uka.ipd.sdq.pcm.repository.CompositeComponent;
import de.uka.ipd.sdq.pcm.repository.RepositoryComponent;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;
//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;

/**
 * Builder for SAMM structures. Takes care of updating the source code decorator.
 * The core builder facility.
 *
 */
public class ComponentBuilder extends AbstractBuilder {
	
	/* -------------------
	 * Sub-Builder used by this builder
	 * -------------------
	 */
	private ComponentAndTypeNaming componentNamingStrategy = null;
	private InterfaceBuilder interfaceBuilder = null;
	private IAssemblyConnectorStrategy assemblyConnectorDeFactoBuilder = null;
	private IAssemblyConnectorStrategy assemblyConnectorInnerBuilder = null;
	private IRoleBuilderStrategy roleBuilder = null;
	
	private static Logger logger = Logger.getLogger(ComponentBuilder.class);
	
	/**
	 * Main builder used to create model elements of the SAMM during component detection with SoMoX. 
	 * @param gastModel The GAST model containing the analyzed source code
	 * @param somoxConfiguration SoMoX configuration object
	 * @param analysisResult Object holding the root elements of the models to create
	 */
	public ComponentBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration,
			AnalysisResult analysisResult) {
		super(gastModel, somoxConfiguration, analysisResult);
		
		logger.debug("Initialising PCM model builder");
		
		this.componentNamingStrategy = new ComponentAndTypeNaming();
		this.interfaceBuilder = new InterfaceBuilder(gastModel, somoxConfiguration, analysisResult);

		AssemblyConnectorBuilder connectorBuilder = new AssemblyConnectorBuilder(gastModel, somoxConfiguration, analysisResult);
		
		//TODO: get concrete strategy instance from configuration:		
		this.assemblyConnectorDeFactoBuilder = new AssemblyConnectorDeFactoStrategy(connectorBuilder);
		this.assemblyConnectorInnerBuilder = new AssemblyConnectorsInsideCompositeComponentStrategy();
		
		// outdated builder: this.providedInterfaceBuilder = new BasicProvidedInterfaceBuilder(gastModel, somoxConfiguration, analysisResult);
		this.roleBuilder = new NonDuplicatingInterfacePortBuilder(
				gastModel, somoxConfiguration, analysisResult, this.componentNamingStrategy);
		
		// debug-like option for non-assigned interfaces:
		if(somoxConfiguration.isReverseEngineerInterfacesNotAssignedToComponent()) {
			this.interfaceBuilder.reverseEngineerRemainingInterfacesAsFreeFloatingInterfaces(
				analysisResult, gastModel);
		}
	}
	
	/**
	 * Compose case.
	 * Method to create a new composite component. The new composite component will contain the components referenced by the list of innerComponents as sub-components.
	 * @param compositeComponentSubgraph Already detected components that should become the subcomponent instances of the new composite component
	 * @return The {@link ComponentImplementingClassesLink} annotation describing the new composite component and its code origin
	 */
	public ComponentImplementingClassesLink createCompositeComponent(
			Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph) {

		// For the found pair of component candidates: merge them into a new component candidate
		ComponentImplementingClassesLink result = SourcecodedecoratorFactory.eINSTANCE.createComponentImplementingClassesLink();
		CompositeComponent newComponentType = RepositoryFactory.eINSTANCE.createCompositeComponent();

		String componentName = this.componentNamingStrategy.createCompositeComponentName(compositeComponentSubgraph.vertexSet());
		logger.info("Creating composite component with name: "+componentName);
		newComponentType.setEntityName(componentName);
		//newComponentType.setDocumentation(
		//this.componentNamingStrategy.createCompositeComponentName(
		//compositeComponentSubgraph.vertexSet(), false)); //full name
				
		createAssemblyContext(compositeComponentSubgraph.vertexSet(),
				newComponentType);
		
		result.setComponent(newComponentType);
		result.getSubComponents().addAll(compositeComponentSubgraph.vertexSet());
		
		this.analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink().add(result);
		this.analysisResult.getInternalArchitectureModel().getComponents__Repository().add(newComponentType);

		this.assemblyConnectorDeFactoBuilder.buildAssemblyConnectors(result,compositeComponentSubgraph);
		this.assemblyConnectorInnerBuilder.buildAssemblyConnectors(result,compositeComponentSubgraph);
		
		this.roleBuilder.buildProvidedRole(result);
		this.roleBuilder.buildRequiredRole(result);
		
		return result;		
	}

	/**
	 * Create subcomponent instances for the components
	 * @param subComponents Inner Components for which to create the instances
	 * @param newComponentType The outer component for which to add the instances
	 * @return created subcomponent instances
	 */
	public List<AssemblyContext> createAssemblyContext(
			Set<ComponentImplementingClassesLink> subComponents,
			ComposedStructure newComponentType) {
		ArrayList<AssemblyContext> subComponentInstance = new ArrayList<AssemblyContext>(subComponents.size());
		
		for(ComponentImplementingClassesLink innerComponent : subComponents) {
			AssemblyContext assemblyContext = CompositionFactory.eINSTANCE.createAssemblyContext();
			assemblyContext.setParentStructure__AssemblyContext(newComponentType);
			assemblyContext.setEncapsulatedComponent__AssemblyContext(innerComponent.getComponent());
			assemblyContext.setEntityName(this.componentNamingStrategy.createComponentInstanceName(innerComponent.getComponent()));
			
			// for those inner components which might have been initial ones: no more initial when used in composite component:
			innerComponent.setIsInitialComponent(false);
		}
		
		return subComponentInstance;
	}	
	
	/**
	 * Merge case. Creates a new primitive component: either as subcomponent of a given composite component or as a separate
	 * primitive component. 
	 * @param compositeComponentSubgraph if composite component contained: The primitive component becomes
	 * child of this component.
	 * @return Existing composite with children or new primitive component
	 */
	public ComponentImplementingClassesLink createMergedComponent(
			Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph) {
		
		ComponentImplementingClassesLink compositeComponentLink = findExistingComposite(compositeComponentSubgraph.vertexSet());		

		if(compositeComponentLink != null) {	// add to existing composite component		
			// case currently not used since no composite component link is included in the subgraph
			logger.trace("creating merged component CC + children");
			return addAsChildPrimitiveComponentToExistingComposite(compositeComponentSubgraph,
					compositeComponentLink);
			
		} else { // new primitive component from classes (these component link only represent classes)
			// default case
			logger.trace("creating merged single component");
			return createSinglePrimitiveComponent(compositeComponentSubgraph);
			
		}			
	}	
	
	/**
	 * Method to create a primitive component, its source decoration and its provided and required interfaces. The primitive component generated 
	 * contains the given GASTClass plus all inner classes of that GASTClass as its implementation.
	 * @param gastClass The main GASTClass for which a new primitive component is being created 
	 * @return The {@link ComponentImplementingClassesLink} annotation describing the new component and its origin in the source code
	 */
	public ComponentImplementingClassesLink createPrimitiveComponentFromGASTClass(
			Type gastClass) {
			
		List<Type> singleClassList = new ArrayList<Type>();
		singleClassList.add(gastClass);
		return createSinglePrimitiveComponentFromGASTClasses(singleClassList);
	}
	
	/**
	 * Create a NEW single large primitive component (SAMM and Class link) comprising multiple classes. 
	 * Method to create a primitive component, 
	 * its source decoration and its provided and required interfaces. The primitive component generated 
	 * contains the given GASTClass plus all inner classes of that GASTClass as its implementation.
	 * @param gastClasses The main GASTClasses for which a new primitive component is being created 
	 * @return The {@link ComponentImplementingClassesLink} annotation describing the new component
	 * and its origin in the source code
	 */
	public ComponentImplementingClassesLink createSinglePrimitiveComponentFromGASTClasses(
			List<Type> gastClasses) {
		ComponentImplementingClassesLink newPrimitiveComponent = 
			SourcecodedecoratorFactory.eINSTANCE.createComponentImplementingClassesLink();
	
		return createSinglePrimitiveComponentFromGASTClasses(gastClasses, newPrimitiveComponent);
	}
	
	/**
	 * Create a new single large SAMM primitive component comprising multiple classes using an existing class link.
	 * Method to create a primitive component, its source decoration and its provided and required interfaces.
	 * The primitive component generated contains the given GASTClass plus all inner classes of that 
	 * GASTClass as its implementation.
	 * @param gastClasses The main GASTClasses for which a new primitive component is being created
	 * @param primitiveComponent Existing component link for which to add the SAMM component
	 * @return The {@link ComponentImplementingClassesLink} annotation describing the new component and its origin in the source code
	 */
	public ComponentImplementingClassesLink createSinglePrimitiveComponentFromGASTClasses(
			List<Type> gastClasses, ComponentImplementingClassesLink primitiveComponent) {
		
		//removelater
		//String componentName = componentNamingStrategy.createSimpleComponentName(gastClasses, true);	
		String componentName = componentNamingStrategy.createSimpleComponentName(gastClasses, false);//for metric compare reasons
		
		logger.info("Creating primitive component "+componentName);
		
		this.analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink().add(primitiveComponent);
		
		RepositoryComponent newComponentType = RepositoryFactory.eINSTANCE.createBasicComponent();
		newComponentType.setEntityName(componentName); //short name
		//newComponentType.setDocumentation(componentNamingStrategy.createSimpleComponentName(gastClasses, false)); //long description
		this.analysisResult.getInternalArchitectureModel().getComponents__Repository().add(newComponentType);			
		primitiveComponent.setComponent(newComponentType);
		
		// TODO: check whether now duplicate classes are added
		for(Type currentGASTclass : gastClasses) {						
			primitiveComponent.getImplementingClasses().addAll(getInnerClasses(currentGASTclass,newComponentType));
		}		
		
		interfaceBuilder.findAndAddRequiredInterfaces(primitiveComponent);
		interfaceBuilder.addProvidedInterfaces(primitiveComponent);			
		// remove duplicate interfaces which are provided AND required
		interfaceBuilder.removeInterfaceSelfAccesses(primitiveComponent);
		
		return primitiveComponent;		
	}
	
	/**
	 * Create a component link from a GAST class only. Attention: Does not create the SAMM component! 
	 * Only sets the gast class.
	 * @param gastClass The main GASTClass for which a component link is being created 
	 * @return The {@link ComponentImplementingClassesLink} annotation describing the new component link and its origin in the source code
	 */
	public ComponentImplementingClassesLink createComponentLinkFromGASTClass(
			Type gastClass) {
		
		ComponentImplementingClassesLink newPrimitiveComponent = 
			SourcecodedecoratorFactory.eINSTANCE.createComponentImplementingClassesLink();
		this.analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink().add(newPrimitiveComponent);
				
		newPrimitiveComponent.getImplementingClasses().addAll(getInnerClasses(gastClass));
				
		return newPrimitiveComponent;
	}	
	
	/**
	 * New primitive component from classes (these component links only represent classes) 
	 * @param compositeComponentSubgraph 
	 * @return
	 */
	private ComponentImplementingClassesLink createSinglePrimitiveComponent(
			Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph) {
		logger.trace("creating single primitive component (merge)");
		EList<Type> classesOfPrimitiveComponent = new BasicEList<Type>();
		for(ComponentImplementingClassesLink currentComponent : compositeComponentSubgraph.vertexSet()) {	
			assert(currentComponent.isIsInitialComponent());
			classesOfPrimitiveComponent.addAll(currentComponent.getImplementingClasses());
		}
		//Create a single large primitive component comprising multiple classes:
		ComponentImplementingClassesLink result = SourcecodedecoratorFactory.eINSTANCE.createComponentImplementingClassesLink();
		result = 
			createSinglePrimitiveComponentFromGASTClasses(classesOfPrimitiveComponent); 
		return result;
	}

	/**
	 * Attaches children to existing composite component. The attached classes
	 * are all reside in a single large primitive components which becomes
	 * child of the composite component.	
	 * @param compositeComponentSubgraph the associated component links only represent classes
	 * @param compositeComponentLink
	 * @return
	 */
	private ComponentImplementingClassesLink addAsChildPrimitiveComponentToExistingComposite(
			Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph,
			ComponentImplementingClassesLink compositeComponentLink) {
		for(ComponentImplementingClassesLink innerComponent : compositeComponentSubgraph.vertexSet()) {							
			
			// create primitive components for empty component links: 
			if(innerComponent.isIsInitialComponent()) { //a component from the initialisation phase
				//Create a single large primitive component comprising multiple classes / handle the new :				
				ComponentImplementingClassesLink newInnerPrimitiveComponent = 
					createSinglePrimitiveComponentFromGASTClasses(innerComponent.getImplementingClasses());
				
				// for all other inner components corresponding inner components have already been created:
				AssemblyContext assemblyContext = CompositionFactory.eINSTANCE.createAssemblyContext();
				assemblyContext.setEncapsulatedComponent__AssemblyContext(newInnerPrimitiveComponent.getComponent());
				assemblyContext.setEntityName(this.componentNamingStrategy.createComponentInstanceName(newInnerPrimitiveComponent.getComponent()));
				
				((CompositeComponent)compositeComponentLink.getComponent()).getAssemblyContexts__ComposedStructure().add(assemblyContext);

				// update the result source code decorator
				compositeComponentLink.getSubComponents().add(newInnerPrimitiveComponent);
			} else {
				// do not handle an existing component
			}
			
		}
		
		this.roleBuilder.buildProvidedRole(compositeComponentLink);
		this.roleBuilder.buildRequiredRole(compositeComponentLink);
		
		this.assemblyConnectorDeFactoBuilder.buildAssemblyConnectors(compositeComponentLink,compositeComponentSubgraph); 
		this.assemblyConnectorInnerBuilder.buildAssemblyConnectors(compositeComponentLink,compositeComponentSubgraph);				
		
		return compositeComponentLink;
	}

	/**
	 * Finds an existing composite component in the set of component links.
	 * @param componentLinks Structure to search. Must not contain more than one composite component!
	 * @return the composite component if found; null else
	 */
	private ComponentImplementingClassesLink findExistingComposite(
			Set<ComponentImplementingClassesLink> componentLinks) {
		assert assertOnlyASingleComposite(componentLinks);
	
		// find a composite component for which to add the classes to merge as a primitive component
		for(ComponentImplementingClassesLink innerComponent : componentLinks) {
			if(innerComponent.isIsCompositeComponent()) {
				return innerComponent;
			}
		}
		return null;
	}
	
	/**
	 * Assert at most one composite component.
	 * @param componentLinks
	 * @return
	 */
	private boolean assertOnlyASingleComposite(
			Set<ComponentImplementingClassesLink> componentLinks) {
		int compositeCount = 0; 
		for(ComponentImplementingClassesLink innerComponent : componentLinks) {
			if(innerComponent instanceof CompositeComponent) {
				compositeCount++;
			}
		}
		if(compositeCount <= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/*
	 * ----------------------------
	 * Begin of helper methods
	 * ----------------------------
	 */
	
	/**
	 * Returns a list of the given class itself and all of its inner classes
	 * @param element A GASTClass object
	 * @param newComponentType 
	 * @return a list containing the given class plus all inner classes
	 */
	//SOMOXTODOCHANGE rename this class to getInputElementAndInnerClasses
	private Set<Type> getInnerClasses (Type element, RepositoryComponent newComponentType) {
		Set<Type> currentList = new HashSet<Type>();
		currentList.add(element);
		storeFileLocationInSourceCodeDecorator(element, newComponentType);
		
		List<Type> innerClasses = KDMHelper.getInnerClasses(element);
		
		if (innerClasses != null) {
			currentList.addAll(innerClasses);
		}
		for (Type innerClass : innerClasses) {
			currentList.addAll(getInnerClasses(innerClass, newComponentType));
		}
		
		return currentList;
	}
	
	/**
	 * Returns a list of the given class itself and all of its inner classes.
	 * Does NOT update the file location in the source code decorator!
	 * @param element A GASTClass object
	 * @param newComponentType 
	 * @return a list containing the given class plus all inner classes
	 */
	private Set<Type> getInnerClasses (Type element) {
		Set<Type> currentList = new HashSet<Type>();
		currentList.add(element);
		
		List<Type> innerClasses = KDMHelper.getInnerClasses(element);
		
		if (innerClasses != null) {
			currentList.addAll(innerClasses);
		}
		for (Type innerClass : innerClasses) {
			currentList.addAll(getInnerClasses(innerClass));
		}
		
		return currentList;
	}	
	
	/**
	 * Stores class names here in source code decorator.
	 * @param result
	 * @param gastClass
	 * @param newComponent
	 */
	private void storeFileLocationInSourceCodeDecorator(
			Type gastClass, RepositoryComponent newComponent) {
		//TODO inner classes?
		FileLevelSourceCodeLink link = SourcecodedecoratorFactory.eINSTANCE.createFileLevelSourceCodeLink();
		link.setRepositoryComponent(newComponent);
		if(KDMHelper.getJavaNodeSourceRegion(gastClass) != null ) { // can be null for C code
			link.setFile(KDMHelper.getJavaNodeSourceRegion(gastClass));
		}
		this.analysisResult.getSourceCodeDecoratorRepository().getFileLevelSourceCodeLink().add(link);
	}

	/**
	 * Updates the component interfaces of all interface
	 * existing until now in the source code decorator. The interfaces
	 * might have changed due to newly discovered interfaces during reverse
	 * engineering.
	 */
	public void updateRequiredInterfacesOfExistingPrimitiveComponents() {
		this.interfaceBuilder.updateRequiredInterfacesOfExistingPrimitiveComponents();
	}

	/*
	 * Getters for Sub-Builder
	 */
	
	public InterfaceBuilder getInterfaceBuilder() {
		return interfaceBuilder;
	}

	public ComponentAndTypeNaming getComponentAndTypeNamingStrategy() {
		return componentNamingStrategy;
	}
	
	public IAssemblyConnectorStrategy getInsideCompositeComponentAssemblyConnectorStrategy() {
		return assemblyConnectorInnerBuilder;
	}
	
}
