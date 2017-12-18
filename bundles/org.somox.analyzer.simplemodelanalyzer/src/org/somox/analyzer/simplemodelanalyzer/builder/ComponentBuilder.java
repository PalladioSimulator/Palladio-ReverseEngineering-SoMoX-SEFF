package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.types.Type;
import org.jgrapht.Graph;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.CompositionFactory;
import org.palladiosimulator.pcm.repository.CompositeComponent;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;
import org.somox.analyzer.AnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.metrics.ClusteringRelation;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.FileLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

/**
 * Builder for SAMM structures. Takes care of updating the source code decorator. The core builder
 * facility.
 *
 */
public class ComponentBuilder extends AbstractBuilder {

    /*
     * ------------------- Sub-Builder used by this builder -------------------
     */
    private ComponentAndTypeNaming componentNamingStrategy = null;
    private InterfaceBuilder interfaceBuilder = null;
    private IAssemblyConnectorStrategy assemblyConnectorDeFactoBuilder = null;
    private IAssemblyConnectorStrategy assemblyConnectorInnerBuilder = null;
    private IRoleBuilderStrategy roleBuilder = null;

    private static Logger logger = Logger.getLogger(ComponentBuilder.class);

    /**
     * Main builder used to create model elements of the SAMM during component detection with SoMoX.
     *
     * @param gastModel
     *            The GAST model containing the analyzed source code
     * @param somoxConfiguration
     *            SoMoX configuration object
     * @param analysisResult
     *            Object holding the root elements of the models to create
     */
    public ComponentBuilder(final Root gastModel, final SoMoXConfiguration somoxConfiguration,
            final AnalysisResult analysisResult) {
        super(gastModel, somoxConfiguration, analysisResult);

        logger.debug("Initialising PCM model builder");

        this.componentNamingStrategy = new ComponentAndTypeNaming();
        this.interfaceBuilder = new InterfaceBuilder(gastModel, somoxConfiguration, analysisResult);

        final AssemblyConnectorBuilder connectorBuilder = new AssemblyConnectorBuilder(gastModel, somoxConfiguration,
                analysisResult);

        // TODO: get concrete strategy instance from configuration:
        this.assemblyConnectorDeFactoBuilder = new AssemblyConnectorDeFactoStrategy(connectorBuilder);
        this.assemblyConnectorInnerBuilder = new AssemblyConnectorsInsideCompositeComponentStrategy();

        // outdated builder: this.providedInterfaceBuilder = new
        // BasicProvidedInterfaceBuilder(gastModel, somoxConfiguration, analysisResult);
        this.roleBuilder = new NonDuplicatingInterfacePortBuilder(gastModel, somoxConfiguration, analysisResult,
                this.componentNamingStrategy);

        // debug-like option for non-assigned interfaces:
        if (somoxConfiguration.isReverseEngineerInterfacesNotAssignedToComponent()) {
            this.interfaceBuilder.reverseEngineerRemainingInterfacesAsFreeFloatingInterfaces(analysisResult, gastModel);
        }
    }

    /**
     * Compose case. Method to create a new composite component. The new composite component will
     * contain the components referenced by the list of innerComponents as sub-components.
     *
     * @param compositeComponentSubgraph
     *            Already detected components that should become the subcomponent instances of the
     *            new composite component
     * @return The {@link ComponentImplementingClassesLink} annotation describing the new composite
     *         component and its code origin
     */
    public ComponentImplementingClassesLink createCompositeComponent(
            final Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph) {

        // For the found pair of component candidates: merge them into a new component candidate
        final ComponentImplementingClassesLink result = SourcecodedecoratorFactory.eINSTANCE
                .createComponentImplementingClassesLink();
        final CompositeComponent newComponentType = RepositoryFactory.eINSTANCE.createCompositeComponent();

        final String componentName = this.componentNamingStrategy
                .createCompositeComponentName(compositeComponentSubgraph.vertexSet());
        logger.info("Creating composite component with name: " + componentName);
        newComponentType.setEntityName(componentName);
        // newComponentType.setDocumentation(
        // this.componentNamingStrategy.createCompositeComponentName(
        // compositeComponentSubgraph.vertexSet(), false)); //full name

        this.createAssemblyContext(compositeComponentSubgraph.vertexSet(), newComponentType);

        result.setComponent(newComponentType);
        result.getSubComponents().addAll(compositeComponentSubgraph.vertexSet());

        this.analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink().add(result);
        this.analysisResult.getInternalArchitectureModel().getComponents__Repository().add(newComponentType);

        this.assemblyConnectorDeFactoBuilder.buildAssemblyConnectors(result, compositeComponentSubgraph);
        this.assemblyConnectorInnerBuilder.buildAssemblyConnectors(result, compositeComponentSubgraph);

        this.roleBuilder.buildProvidedRole(result);
        this.roleBuilder.buildRequiredRole(result);

        return result;
    }

    /**
     * Create subcomponent instances for the components
     *
     * @param subComponents
     *            Inner Components for which to create the instances
     * @param newComponentType
     *            The outer component for which to add the instances
     * @return created subcomponent instances
     */
    public List<AssemblyContext> createAssemblyContext(final Set<ComponentImplementingClassesLink> subComponents,
            final ComposedStructure newComponentType) {
        final ArrayList<AssemblyContext> subComponentInstance = new ArrayList<AssemblyContext>(subComponents.size());

        for (final ComponentImplementingClassesLink innerComponent : subComponents) {
            final AssemblyContext assemblyContext = CompositionFactory.eINSTANCE.createAssemblyContext();
            assemblyContext.setParentStructure__AssemblyContext(newComponentType);
            assemblyContext.setEncapsulatedComponent__AssemblyContext(innerComponent.getComponent());
            assemblyContext.setEntityName(
                    this.componentNamingStrategy.createComponentInstanceName(innerComponent.getComponent()));

            // for those inner components which might have been initial ones: no more initial when
            // used in composite component:
            innerComponent.setIsInitialComponent(false);
        }

        return subComponentInstance;
    }

    /**
     * Merge case. Creates a new primitive component: either as subcomponent of a given composite
     * component or as a separate primitive component.
     *
     * @param compositeComponentSubgraph
     *            if composite component contained: The primitive component becomes child of this
     *            component.
     * @return Existing composite with children or new primitive component
     */
    public ComponentImplementingClassesLink createMergedComponent(
            final Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph) {

        final ComponentImplementingClassesLink compositeComponentLink = this
                .findExistingComposite(compositeComponentSubgraph.vertexSet());

        if (compositeComponentLink != null) { // add to existing composite component
            // case currently not used since no composite component link is included in the subgraph
            logger.trace("creating merged component CC + children");
            return this.addAsChildPrimitiveComponentToExistingComposite(compositeComponentSubgraph,
                    compositeComponentLink);

        } else { // new primitive component from classes (these component link only represent
            // classes)
            // default case
            logger.trace("creating merged single component");
            return this.createSinglePrimitiveComponent(compositeComponentSubgraph);

        }
    }

    /**
     * Method to create a primitive component, its source decoration and its provided and required
     * interfaces. The primitive component generated contains the given GASTClass plus all inner
     * classes of that GASTClass as its implementation.
     *
     * @param gastClass
     *            The main GASTClass for which a new primitive component is being created
     * @return The {@link ComponentImplementingClassesLink} annotation describing the new component
     *         and its origin in the source code
     */
    public ComponentImplementingClassesLink createPrimitiveComponentFromGASTClass(final ConcreteClassifier gastClass) {

        final List<ConcreteClassifier> singleClassList = new ArrayList<ConcreteClassifier>();
        singleClassList.add(gastClass);
        return this.createSinglePrimitiveComponentFromGASTClasses(singleClassList);
    }

    /**
     * Create a NEW single large primitive component (SAMM and Class link) comprising multiple
     * classes. Method to create a primitive component, its source decoration and its provided and
     * required interfaces. The primitive component generated contains the given GASTClass plus all
     * inner classes of that GASTClass as its implementation.
     *
     * @param gastClasses
     *            The main GASTClasses for which a new primitive component is being created
     * @return The {@link ComponentImplementingClassesLink} annotation describing the new component
     *         and its origin in the source code
     */
    public ComponentImplementingClassesLink createSinglePrimitiveComponentFromGASTClasses(
            final List<ConcreteClassifier> gastClasses) {
        final ComponentImplementingClassesLink newPrimitiveComponent = SourcecodedecoratorFactory.eINSTANCE
                .createComponentImplementingClassesLink();

        return this.createSinglePrimitiveComponentFromGASTClasses(gastClasses, newPrimitiveComponent);
    }

    /**
     * Create a new single large SAMM primitive component comprising multiple classes using an
     * existing class link. Method to create a primitive component, its source decoration and its
     * provided and required interfaces. The primitive component generated contains the given
     * GASTClass plus all inner classes of that GASTClass as its implementation.
     *
     * @param gastClasses
     *            The main GASTClasses for which a new primitive component is being created
     * @param primitiveComponent
     *            Existing component link for which to add the SAMM component
     * @return The {@link ComponentImplementingClassesLink} annotation describing the new component
     *         and its origin in the source code
     */
    public ComponentImplementingClassesLink createSinglePrimitiveComponentFromGASTClasses(
            final List<ConcreteClassifier> gastClasses, final ComponentImplementingClassesLink primitiveComponent) {

        // removelater
        // String componentName = componentNamingStrategy.createSimpleComponentName(gastClasses,
        // true);
        final String componentName = this.componentNamingStrategy.createSimpleComponentName(gastClasses, false);
        // for metric compare reasons

        logger.info("Creating primitive component " + componentName);

        this.analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink()
                .add(primitiveComponent);

        final RepositoryComponent newComponentType = RepositoryFactory.eINSTANCE.createBasicComponent();
        newComponentType.setEntityName(componentName); // short name
        // newComponentType.setDocumentation(componentNamingStrategy.createSimpleComponentName(gastClasses,
        // false)); //long description
        this.analysisResult.getInternalArchitectureModel().getComponents__Repository().add(newComponentType);
        primitiveComponent.setComponent(newComponentType);

        // TODO: check whether now duplicate classes are added
        for (final ConcreteClassifier currentGASTclass : gastClasses) {
            primitiveComponent.getImplementingClasses()
                    .addAll(this.getInnerClasses(currentGASTclass, newComponentType));
        }

        this.interfaceBuilder.findAndAddRequiredInterfaces(primitiveComponent);
        this.interfaceBuilder.addProvidedInterfaces(primitiveComponent);
        // remove duplicate interfaces which are provided AND required
        this.interfaceBuilder.removeInterfaceSelfAccesses(primitiveComponent);

        return primitiveComponent;
    }

    /**
     * Create a component link from a GAST class only. Attention: Does not create the SAMM
     * component! Only sets the gast class.
     *
     * @param gastClass
     *            The main GASTClass for which a component link is being created
     * @return The {@link ComponentImplementingClassesLink} annotation describing the new component
     *         link and its origin in the source code
     */
    public ComponentImplementingClassesLink createComponentLinkFromGASTClass(final ConcreteClassifier gastClass) {

        final ComponentImplementingClassesLink newPrimitiveComponent = SourcecodedecoratorFactory.eINSTANCE
                .createComponentImplementingClassesLink();
        this.analysisResult.getSourceCodeDecoratorRepository().getComponentImplementingClassesLink()
                .add(newPrimitiveComponent);

        newPrimitiveComponent.getImplementingClasses().addAll(this.getInnerClasses(gastClass));

        return newPrimitiveComponent;
    }

    /**
     * New primitive component from classes (these component links only represent classes)
     *
     * @param compositeComponentSubgraph
     * @return
     */
    private ComponentImplementingClassesLink createSinglePrimitiveComponent(
            final Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph) {
        logger.trace("creating single primitive component (merge)");
        final EList<ConcreteClassifier> classesOfPrimitiveComponent = new BasicEList<ConcreteClassifier>();
        for (final ComponentImplementingClassesLink currentComponent : compositeComponentSubgraph.vertexSet()) {
            assert currentComponent.isIsInitialComponent();
            classesOfPrimitiveComponent.addAll(currentComponent.getImplementingClasses());
        }
        // Create a single large primitive component comprising multiple classes:
        ComponentImplementingClassesLink result = SourcecodedecoratorFactory.eINSTANCE
                .createComponentImplementingClassesLink();
        result = this.createSinglePrimitiveComponentFromGASTClasses(classesOfPrimitiveComponent);
        return result;
    }

    /**
     * Attaches children to existing composite component. The attached classes are all reside in a
     * single large primitive components which becomes child of the composite component.
     *
     * @param compositeComponentSubgraph
     *            the associated component links only represent classes
     * @param compositeComponentLink
     * @return
     */
    private ComponentImplementingClassesLink addAsChildPrimitiveComponentToExistingComposite(
            final Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph,
            final ComponentImplementingClassesLink compositeComponentLink) {
        for (final ComponentImplementingClassesLink innerComponent : compositeComponentSubgraph.vertexSet()) {

            // create primitive components for empty component links:
            if (innerComponent.isIsInitialComponent()) { // a component from the initialisation
                // phase
                // Create a single large primitive component comprising multiple classes / handle
                // the new :
                final ComponentImplementingClassesLink newInnerPrimitiveComponent = this
                        .createSinglePrimitiveComponentFromGASTClasses(innerComponent.getImplementingClasses());

                // for all other inner components corresponding inner components have already been
                // created:
                final AssemblyContext assemblyContext = CompositionFactory.eINSTANCE.createAssemblyContext();
                assemblyContext.setEncapsulatedComponent__AssemblyContext(newInnerPrimitiveComponent.getComponent());
                assemblyContext.setEntityName(this.componentNamingStrategy
                        .createComponentInstanceName(newInnerPrimitiveComponent.getComponent()));

                ((CompositeComponent) compositeComponentLink.getComponent()).getAssemblyContexts__ComposedStructure()
                        .add(assemblyContext);

                // update the result source code decorator
                compositeComponentLink.getSubComponents().add(newInnerPrimitiveComponent);
            } else {
                // do not handle an existing component
            }

        }

        this.roleBuilder.buildProvidedRole(compositeComponentLink);
        this.roleBuilder.buildRequiredRole(compositeComponentLink);

        this.assemblyConnectorDeFactoBuilder.buildAssemblyConnectors(compositeComponentLink,
                compositeComponentSubgraph);
        this.assemblyConnectorInnerBuilder.buildAssemblyConnectors(compositeComponentLink, compositeComponentSubgraph);

        return compositeComponentLink;
    }

    /**
     * Finds an existing composite component in the set of component links.
     *
     * @param componentLinks
     *            Structure to search. Must not contain more than one composite component!
     * @return the composite component if found; null else
     */
    private ComponentImplementingClassesLink findExistingComposite(
            final Set<ComponentImplementingClassesLink> componentLinks) {
        assert this.assertOnlyASingleComposite(componentLinks);

        // find a composite component for which to add the classes to merge as a primitive component
        for (final ComponentImplementingClassesLink innerComponent : componentLinks) {
            if (innerComponent.isIsCompositeComponent()) {
                return innerComponent;
            }
        }
        return null;
    }

    /**
     * Assert at most one composite component.
     *
     * @param componentLinks
     * @return
     */
    private boolean assertOnlyASingleComposite(final Set<ComponentImplementingClassesLink> componentLinks) {
        int compositeCount = 0;
        for (final ComponentImplementingClassesLink innerComponent : componentLinks) {
            if (innerComponent instanceof CompositeComponent) {
                compositeCount++;
            }
        }
        if (compositeCount <= 1) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * ---------------------------- Begin of helper methods ----------------------------
     */

    /**
     * Returns a list of the given class itself and all of its inner classes
     *
     * @param element
     *            A GASTClass object
     * @param newComponentType
     * @return a list containing the given class plus all inner classes
     */
    // SOMOXTODOCHANGE rename this class to getInputElementAndInnerClasses
    private Set<ConcreteClassifier> getInnerClasses(final ConcreteClassifier element,
            final RepositoryComponent newComponentType) {
        final Set<ConcreteClassifier> currentList = new HashSet<ConcreteClassifier>();
        currentList.add(element);
        this.storeFileLocationInSourceCodeDecorator(element, newComponentType);

        final List<ConcreteClassifier> innerClasses = KDMHelper.getInnerClasses(element);

        if (innerClasses != null) {
            currentList.addAll(innerClasses);
        }
        for (final ConcreteClassifier innerClass : innerClasses) {
            currentList.addAll(this.getInnerClasses(innerClass, newComponentType));
        }

        return currentList;
    }

    /**
     * Returns a list of the given class itself and all of its inner classes. Does NOT update the
     * file location in the source code decorator!
     *
     * @param element
     *            A GASTClass object
     * @param newComponentType
     * @return a list containing the given class plus all inner classes
     */
    private Set<ConcreteClassifier> getInnerClasses(final ConcreteClassifier element) {
        final Set<ConcreteClassifier> currentList = new HashSet<ConcreteClassifier>();
        currentList.add(element);

        final List<ConcreteClassifier> innerClasses = KDMHelper.getInnerClasses(element);

        if (innerClasses != null) {
            currentList.addAll(innerClasses);
        }
        for (final ConcreteClassifier innerClass : innerClasses) {
            currentList.addAll(this.getInnerClasses(innerClass));
        }

        return currentList;
    }

    /**
     * Stores class names here in source code decorator.
     *
     * @param result
     * @param gastClass
     * @param newComponent
     */
    private void storeFileLocationInSourceCodeDecorator(final Type gastClass, final RepositoryComponent newComponent) {
        // TODO inner classes?
        final FileLevelSourceCodeLink link = SourcecodedecoratorFactory.eINSTANCE.createFileLevelSourceCodeLink();
        link.setRepositoryComponent(newComponent);
        if (KDMHelper.getJavaNodeSourceRegion(gastClass) != null) { // can be null for C code
            link.setFile(KDMHelper.getJavaNodeSourceRegion(gastClass));
        }
        this.analysisResult.getSourceCodeDecoratorRepository().getFileLevelSourceCodeLink().add(link);
    }

    /**
     * Updates the component interfaces of all interface existing until now in the source code
     * decorator. The interfaces might have changed due to newly discovered interfaces during
     * reverse engineering.
     */
    public void updateRequiredInterfacesOfExistingPrimitiveComponents() {
        this.interfaceBuilder.updateRequiredInterfacesOfExistingPrimitiveComponents();
    }

    /*
     * Getters for Sub-Builder
     */

    public InterfaceBuilder getInterfaceBuilder() {
        return this.interfaceBuilder;
    }

    public ComponentAndTypeNaming getComponentAndTypeNamingStrategy() {
        return this.componentNamingStrategy;
    }

    public IAssemblyConnectorStrategy getInsideCompositeComponentAssemblyConnectorStrategy() {
        return this.assemblyConnectorInnerBuilder;
    }

}
