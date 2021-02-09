package org.annotationsmox.seffhelper.functionclassification;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Method;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.somox.gast2seff.visitors.AbstractFunctionClassificationStrategy;
import org.somox.gast2seff.visitors.MethodCallFinder;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

public class EJBFunctionClassificationStrategy extends AbstractFunctionClassificationStrategy {

	private static final String EVENT_FIRE_METHOD = "fire";
	private final SourceCodeDecoratorRepository sourceCodeDecoratorRepository;
	private final BasicComponent basicComponent;
	private final Root root;

	public EJBFunctionClassificationStrategy(final SourceCodeDecoratorRepository sourceCodeDecoratorRepository,
			final BasicComponent basicComponent, final Root root, final MethodCallFinder methodCallFinder) {
		super(methodCallFinder);
		this.sourceCodeDecoratorRepository = sourceCodeDecoratorRepository;
		this.basicComponent = basicComponent;
		this.root = root;
	}

	/**
	 * External calls are all calls to an interface that is required by the
	 * current component
	 */
	@Override
	protected boolean isExternalCall(final Method method) {
		final ConcreteClassifier containingClassifier = method.getContainingConcreteClassifier();
		final List<OperationRequiredRole> requiredRoles = this.basicComponent
				.getRequiredRoles_InterfaceRequiringEntity().stream()
				.filter(requiredRole -> requiredRole instanceof OperationRequiredRole)
				.map(requiredRole -> (OperationRequiredRole) requiredRole).collect(Collectors.toList());
		final Set<OperationInterface> requiredIfs = requiredRoles.stream()
				.map(reqRole -> reqRole.getRequiredInterface__OperationRequiredRole()).collect(Collectors.toSet());
		for (final InterfaceSourceCodeLink interfaceSourceCodeLink : this.sourceCodeDecoratorRepository
				.getInterfaceSourceCodeLink()) {
			if (requiredIfs.contains(interfaceSourceCodeLink.getInterface())) {
				final ConcreteClassifier currentClassifier = interfaceSourceCodeLink.getGastClass();
				if (currentClassifier == containingClassifier) {
					// the method is in a required interface of the component
					// --> external call
					// found
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * We consider calls to the fire method javax.enterprise.event.Event as emit
	 * event call
	 */
	@Override
	protected boolean isEmitEventCall(final Method method) {
		return method.getName().equals(EVENT_FIRE_METHOD);
	}

	/**
	 * Library calls are all calls that 1) target a method "outside" the current
	 * compilation units or 2) calls to a data type class
	 */
	@Override
	protected boolean isLibraryCall(final Method method) {
		final CompilationUnit currentCompilationUnit = method.getContainingCompilationUnit();
		if (!this.root.getCompilationUnits().contains(currentCompilationUnit)) {
			return true;
		}
		final ConcreteClassifier containingClassifier = method.getContainingConcreteClassifier();
		final boolean isCallToADatatypeClass = this.sourceCodeDecoratorRepository.getDataTypeSourceCodeLink().stream()
				.filter(dataTypeSourceCodeLink -> dataTypeSourceCodeLink.getJaMoPPType().equals(containingClassifier))
				.findAny().isPresent();
		return isCallToADatatypeClass;
	}

}
