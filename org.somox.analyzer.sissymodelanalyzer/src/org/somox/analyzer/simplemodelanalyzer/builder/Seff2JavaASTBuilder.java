package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.types.Type;
import org.somox.analyzer.AnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.EqualityChecker;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.seff2javaast.SEFF2JavaAST;
import org.somox.seff2javaast.SEFF2MethodMapping;
import org.somox.seff2javaast.Seff2javaastFactory;
//import org.somox.seff2javaast.Seff2methodFactory;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.EventGroup;
import de.uka.ipd.sdq.pcm.repository.EventType;
import de.uka.ipd.sdq.pcm.repository.OperationInterface;
import de.uka.ipd.sdq.pcm.repository.OperationProvidedRole;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.RepositoryComponent;
import de.uka.ipd.sdq.pcm.repository.Signature;
import de.uka.ipd.sdq.pcm.repository.SinkRole;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.seff.SeffFactory;

/**
 * Builder used to add GAST behaviour to methods detected as provided operations of components
 *
 * @author Steffen Becker, Michael Hauck
 */
public class Seff2JavaASTBuilder extends AbstractBuilder {

    private static final Logger logger = Logger.getLogger(Seff2JavaASTBuilder.class);
    private final SourceCodeDecoratorRepository sourceCodeDecorator;
    private final SEFF2JavaAST seff2JavaAST;

    /**
     * Constructor of the GAST behaviour builder
     *
     * @param gastModel
     *            GAST model used for queries to the source code representation
     * @param somoxConfiguration
     *            Somox configuaration used to retrieve settings
     * @param analysisResult
     *            Contains the root model elemts used to store the generated model elements
     */
    public Seff2JavaASTBuilder(final Root gastModel, final SoMoXConfiguration somoxConfiguration,
            final AnalysisResult analysisResult) {
        super(gastModel, somoxConfiguration, analysisResult);
        this.sourceCodeDecorator = analysisResult.getSourceCodeDecoratorRepository();
        this.seff2JavaAST = analysisResult.getSeff2JavaAST();
    }

    /**
     * Add seffs to the given basic component for all methods passed in the provided roles parameter
     *
     * @param component
     *            The component to which the behaviour will be added
     * @param providedRole
     *            The provided role for which each of its operations is to be added.
     */
    public void addSeffsToPrimitiveComponent(final BasicComponent component, final ProvidedRole providedRole) {
        if (providedRole instanceof OperationProvidedRole) {
            final OperationInterface providedInterface = ((OperationProvidedRole) providedRole)
                    .getProvidedInterface__OperationProvidedRole();
            for (final OperationSignature signature : providedInterface.getSignatures__OperationInterface()) {
                this.addSeffToBasicComponent(component, signature);
            }
        } else if (providedRole instanceof SinkRole) {
            final EventGroup providedInterface = ((SinkRole) providedRole).getEventGroup__SinkRole();
            for (final EventType signature : providedInterface.getEventTypes__EventGroup()) {
                this.addSeffToBasicComponent(component, signature);
            }
        }
    }

    /**
     * Add a seff to a basic component including the mapping to the method in the AST model.
     *
     * @param component
     *            The component to add the seff for
     * @param operation
     *            The interface operation
     */
    private void addSeffToBasicComponent(final BasicComponent component, final Signature operation) {

        final MethodLevelSourceCodeLink link = this.getMethodLevelSourceCodeLink(operation);

        if (link == null) {
            throw new RuntimeException(
                    "Found interface with operations for which no method link exists. This should never happen!");
        }

        link.setRepositoryComponent(component);

        // this.sourceCodeDecorator.getMethodLevelSourceCodeLink().add(link);

        final ResourceDemandingSEFF seff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        // TODO burkha 22.05.2013 this can violate a OCL constraint, when there is more than one
        // seff implementing the same signature
        if (link.getOperation().getEntityName().equals("refresh")) {
            @SuppressWarnings("unused")
            final int a = 0;
        }
        seff.setDescribedService__SEFF(link.getOperation());
        final SEFF2MethodMapping seff2MethodMapping = Seff2javaastFactory.eINSTANCE.createSEFF2MethodMapping();
        component.getServiceEffectSpecifications__BasicComponent().add(seff);

        // links steems from interface; thus get component-specific implementation:
        final ComponentImplementingClassesLink compClassLink = this.findComponenentLink(component);
        final StatementListContainer methodBody = this.getFunctionImplementation(link.getFunction(), compClassLink,
                this.astModel);

        seff2MethodMapping.setBlockstatement(methodBody);
        if (seff2MethodMapping.getBlockstatement() == null
                || seff2MethodMapping.getBlockstatement().getStatements().size() == 0) {
            logger.warn("Empty behaviour added for " + seff.getDescribedService__SEFF().getEntityName()
                    + " linked method is " + seff2MethodMapping.getBlockstatement()
                    + "! Reverse engineering of behaviour will NOT be able to succeed for this method!");
        }
        seff2MethodMapping.setSeff(seff);

        this.seff2JavaAST.getSeff2MethodMappings().add(seff2MethodMapping);

    }

    /**
     * Finds a implementation block statement for the function realised by the passed component.
     *
     * @param function
     *            interface function
     * @param component
     *            The component to find the method implementation for
     * @return The block statement realising the function for the component; null in a case of
     *         error.
     */
    private StatementListContainer getFunctionImplementationFromClassMethod(final ClassMethod function,
            final ComponentImplementingClassesLink component) {

        for (final ConcreteClassifier implementingClass : component.getImplementingClasses()) {
            for (final Method implementedMethod : KDMHelper.getMethods(implementingClass)) {
                if (EqualityChecker.areFunctionsEqual(function, implementedMethod)) { // FIXME:
                    return KDMHelper.getBody(implementedMethod);
                }
            }
        }

        logger.error("No method implemementation found for method " + function.getName() + " for component "
                + component.getComponent().getEntityName());
        return null;
    }

    private StatementListContainer getFunctionImplementationFromConstructor(final Constructor constructor,
            final ComponentImplementingClassesLink component) {

        for (final Type implementingClass : component.getImplementingClasses()) {
            for (final Constructor currentConstructor : KDMHelper.getConstructors(implementingClass)) {
                if (EqualityChecker.areConstructorsEqual(constructor, currentConstructor)) { // FIXME:
                    return KDMHelper.getBody(currentConstructor);
                }
            }
        }

        logger.error("No method implemementation found for method " + constructor.getName() + " for component "
                + component.getComponent().getEntityName());
        return null;
    }

    /**
     * Finds a implementation block statement for the Member realised by the passed component.
     *
     * @param function
     *            interface function
     * @param component
     *            The component to find the method implementation for
     * @param astModel
     * @return The block statement realising the function for the component; null in a case of
     *         error.
     */
    private StatementListContainer getFunctionImplementation(final Member member,
            final ComponentImplementingClassesLink component, final Root astModel) {

        if (member instanceof ClassMethod) {
            return this.getFunctionImplementationFromClassMethod((ClassMethod) member, component);
        } else if (member instanceof Constructor) {
            return this.getFunctionImplementationFromConstructor((Constructor) member, component);
        } else if (member instanceof InterfaceMethod) {
            logger.info("Found interface method " + member.getName());
            return this.getFunctionImplementationFromInterfaceMethod((InterfaceMethod) member, component, astModel);
        }

        logger.error("No method implemementation found for member " + member.getName() + " for component "
                + component.getComponent().getEntityName());
        return null;
    }

    /**
     * Since an interface method does not have any implementation the method looks for a method in
     * the current component that implements the interface method. if the method is not found:
     * return an empty statement list container. If more than one method are found just return the
     * first one.
     *
     *
     * @param interfaceMethod
     * @param component
     * @param astModel
     */
    private StatementListContainer getFunctionImplementationFromInterfaceMethod(final InterfaceMethod interfaceMethod,
            final ComponentImplementingClassesLink component, final Root astModel) {
        final boolean searchInAstModelIfNotFound = true;
        return this.getFunctionImplementationFromInterfaceMethod(interfaceMethod, component.getImplementingClasses(),
                astModel, searchInAstModelIfNotFound);
    }

    private StatementListContainer getFunctionImplementationFromInterfaceMethod(final InterfaceMethod interfaceMethod,
            final List<ConcreteClassifier> implementingClasses, final Root astModel,
            final boolean searchInAstModelIfNotFound) {
        final ConcreteClassifier interfaceOfMethod = interfaceMethod.getContainingConcreteClassifier();
        final Set<StatementListContainer> implementingStatementListContainers = new HashSet<StatementListContainer>();
        for (final ConcreteClassifier classInComponent : implementingClasses) {
            if (KDMHelper.getSuperTypes(classInComponent).contains(interfaceOfMethod)) {
                // find the overriden interface method
                for (final Method methodInClass : classInComponent.getMethods()) {
                    if (EqualityChecker.areFunctionsEqual(interfaceMethod, methodInClass)
                            && methodInClass instanceof ClassMethod) {
                        logger.info("Found StatementListContainer for interface method " + interfaceMethod.getName());
                        implementingStatementListContainers.add(KDMHelper.getBody(methodInClass));
                    }
                }
            }
        }
        if (1 < implementingStatementListContainers.size()) {
            logger.info("Found more than one statement list container for interface method "
                    + interfaceMethod.getName());
        }
        if (0 == implementingStatementListContainers.size()) {
            if (searchInAstModelIfNotFound) {
                logger.info("Not found any statement list container (aka. overriden method) for interface method "
                        + interfaceMethod.getName() + " in component implementing classes. Looking in all classes...");
                final List<ConcreteClassifier> classifiersToLookAt = new ArrayList<ConcreteClassifier>();
                for (final CompilationUnit cu : astModel.getCompilationUnits()) {
                    classifiersToLookAt.addAll(cu.getClassifiers());
                }
                return this.getFunctionImplementationFromInterfaceMethod(interfaceMethod, classifiersToLookAt,
                        astModel, false);
            } else {
                logger.info("Not found any statement list container (aka. overriden method) for interface method "
                        + interfaceMethod.getName() + " in astModel. Returning null.");
                return null;
            }

        }
        return implementingStatementListContainers.iterator().next();
    }

    /**
     * Reverse lookup for component to componentLink.
     *
     * @param component
     * @return ComponentLink for component.
     */
    private ComponentImplementingClassesLink findComponenentLink(final RepositoryComponent component) {
        for (final ComponentImplementingClassesLink compLink : this.analysisResult.getSourceCodeDecoratorRepository()
                .getComponentImplementingClassesLink()) {
            if (compLink.getComponent().equals(component)) {
                return compLink;
            }
        }
        logger.error("No component link found for component " + component.getEntityName());
        return null;
    }

    private MethodLevelSourceCodeLink getMethodLevelSourceCodeLink(final Signature operation) {
        assert this.operationUnique(operation);
        for (final MethodLevelSourceCodeLink link : this.sourceCodeDecorator.getMethodLevelSourceCodeLink()) {
            if (operation == link.getOperation()) {
                return link;
            }
        }
        return null;
    }

    /**
     * Check if an operation is already present in the source code decorator repository.
     *
     * Attention: For assertion only!
     *
     * @param signature
     *            The signature to look up in the repository.
     * @return true/false whether already present or not.
     */
    private boolean operationUnique(final Signature signature) {
        boolean alreadyFound = false;
        for (final MethodLevelSourceCodeLink link : this.sourceCodeDecorator.getMethodLevelSourceCodeLink()) {
            if (signature == link.getOperation()) {

                if (alreadyFound) {
                    return false;
                }

                alreadyFound = true;
            }
        }
        return alreadyFound;
    }
}
