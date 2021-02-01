package org.annotationsmox.seffhelper.externalcallfinder;

import java.util.List;
import java.util.stream.Collectors;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.types.Type;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.SourceRole;
import org.somox.gast2seff.visitors.DefaultInterfaceOfExternalCallFinder;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

public class EJBInterfaceOfExternalCallFinder extends DefaultInterfaceOfExternalCallFinder{

	private static final String EVENT_FIRE_METHOD_NAME = "fire";

	public EJBInterfaceOfExternalCallFinder(SourceCodeDecoratorRepository sourceCodeDecoratorRepository,
			BasicComponent basicComponent) {
		super(sourceCodeDecoratorRepository, basicComponent);
	}

	/**
	 * The EJB implementation of the external call checks whether the called
	 * method is the fire method. If this is the case it returns the matching
	 * interface port operation tuple for the event.
	 * 
	 */
	@Override
	public InterfacePortOperationTuple getCalledInterfacePort(Method calledMethod, Statement statement) {
		if (calledMethod.getName().equals(EVENT_FIRE_METHOD_NAME)) {
			List<MethodCall> methodCalls = statement.getChildrenByType(MethodCall.class).stream()
					.map(eObj -> (MethodCall) eObj).collect(Collectors.toList());
			MethodCall fireMethodCall = methodCalls.stream().filter(mc -> mc.getTarget() == calledMethod).findFirst()
					.get();
			if (null == fireMethodCall) {
				throw new RuntimeException("Could not find fireMethod call in method calls");
			}
			if (0 == fireMethodCall.getArguments().size()) {
				throw new RuntimeException("No arguments in fire Method");
			}
			// find type in fire event
			Expression expression = fireMethodCall.getArguments().get(0);
			Type type = expression.getType();
			// find correponding type for type in SCDM
			InterfaceSourceCodeLink interfaceSourceCodeLink = this.getSourceCodeDecoratorRepository()
					.getInterfaceSourceCodeLink().stream()
					.filter(ifSourceCodeLink -> ifSourceCodeLink.getInterface() instanceof EventGroup
							&& ifSourceCodeLink.getGastClass() == type)
					.findFirst().get();
			EventGroup eventGroup = (EventGroup) interfaceSourceCodeLink.getInterface();
			if (eventGroup.getEventTypes__EventGroup().size() != 1) {
				throw new RuntimeException("Each event group should have exactly one event type");
			}
			InterfacePortOperationTuple interfacePortOperationTuple = new InterfacePortOperationTuple();
			// from type, have the signature
			interfacePortOperationTuple.signature = eventGroup.getEventTypes__EventGroup().get(0);
			// to get the role: find providing of class and use first matching
			// role
			ConcreteClassifier classifier = statement.getContainingConcreteClassifier();
			ComponentImplementingClassesLink classLink = this.getSourceCodeDecoratorRepository()
					.getComponentImplementingClassesLink().stream()
					.filter(impClassLink -> impClassLink.getImplementingClasses().contains(classifier)).findFirst()
					.get();
			RequiredRole role = classLink.getComponent().getRequiredRoles_InterfaceRequiringEntity().stream()
					.filter(requiredRole -> requiredRole instanceof SourceRole &&
							((SourceRole)requiredRole).getEventGroup__SourceRole() == eventGroup).findFirst()
					.get();
			interfacePortOperationTuple.role = role;
			return interfacePortOperationTuple;
		}
		return super.getCalledInterfacePort(calledMethod, statement);
	}

}
