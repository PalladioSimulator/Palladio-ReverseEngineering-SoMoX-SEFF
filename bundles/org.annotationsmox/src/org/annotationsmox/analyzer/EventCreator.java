package org.annotationsmox.analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.annotationsmox.analyzer.creators.ProvidedRoleCreator;
import org.apache.log4j.Logger;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.types.Type;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.repository.Repository;
import org.somox.kdmhelper.SoMoXUtil;
import org.somox.analyzer.simplemodelanalyzer.builder.util.PcmModelCreationHelper;
import org.somox.util.SourceCodeDecoratorHelper;

public class EventCreator {

	private static final Logger logger = Logger.getLogger(EventCreator.class.getSimpleName());

	private final Repository repository;
	private final SourceCodeDecoratorHelper sourceCodeDecoratorHelper;
	private final PcmModelCreationHelper pcmModelCreationHelper;
	private final ProvidedRoleCreator providedRoleCreator;

	public EventCreator(Repository repository, SourceCodeDecoratorHelper sourceCodeDecoratorHelper,
			PcmModelCreationHelper pcmModelCreationHelper, ProvidedRoleCreator providedRoleCreator) {
		this.repository = repository;
		this.sourceCodeDecoratorHelper = sourceCodeDecoratorHelper;
		this.pcmModelCreationHelper = pcmModelCreationHelper;
		this.providedRoleCreator = providedRoleCreator;
	}

	public void createEventGroups(Set<CompilationUnit> compilationUnits,
			Map<BasicComponent, Class> basicComponent2ClassMap) {
		Map<ConcreteClassifier, BasicComponent> class2BasicComponentMap = basicComponent2ClassMap.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
		List<ConcreteClassifier> classifiers = compilationUnits.stream().map(compUnit -> compUnit.getClassifiers())
				.flatMap(classifier -> classifier.stream()).collect(Collectors.toList());
		List<Method> eventGroupMethods = classifiers.stream().map(classif -> classif.getMethods())
				.flatMap(method -> method.stream()).filter(method -> AnnotationHelper.hasEventParameter(method))
				.collect(Collectors.toList());
		logger.info("Found " + eventGroupMethods.size() + " event methods");
		Map<EventGroup, List<Method>> eventGroup2MethodMap = createEventGroupsAndEventTypes(eventGroupMethods);
		createProvidedRolesForEventGroups(eventGroup2MethodMap, class2BasicComponentMap);
	}

	private void createProvidedRolesForEventGroups(Map<EventGroup, List<Method>> eventGroup2MethodMap,
			Map<ConcreteClassifier, BasicComponent> class2BasicComponentMap) {
		for (EventGroup eventGroup : eventGroup2MethodMap.keySet()) {
			Collection<BasicComponent> implementingBasicComponents = findImplementingBasicComponents(eventGroup,
					eventGroup2MethodMap.get(eventGroup), class2BasicComponentMap);
			implementingBasicComponents.forEach(basicComponent -> providedRoleCreator
					.createProvidedRoles(basicComponent, Arrays.asList(eventGroup)));
		}
	}

	private Collection<BasicComponent> findImplementingBasicComponents(EventGroup eventGroup, List<Method> methods,
			Map<ConcreteClassifier, BasicComponent> class2BasicComponentMap) {
		Set<BasicComponent> implementingBasicComponents = new HashSet<BasicComponent>();
		for (Method method : methods) {
			ConcreteClassifier concreteClassifier = method.getContainingConcreteClassifier();
			if (concreteClassifier instanceof Class) {
				// method is contained within an EJB class directly
				addCorrespondingBasicComponent(concreteClassifier, implementingBasicComponents,
						class2BasicComponentMap);
			} else if (concreteClassifier instanceof Interface && method instanceof InterfaceMethod) {
				// method is contained within an Interface --> get all
				// implementing classes of the interface, which have a
				// corresponding BasicComponent
				Collection<StatementListContainer> implementingMethods = SoMoXUtil
						.findImplementingMethods((InterfaceMethod) method, class2BasicComponentMap.keySet());
				if (implementingMethods.isEmpty()) {
					logger.error("No implementing method found for interface method " + method);
				}
				implementingMethods.stream()
						.map(implementingMethod -> implementingMethod.getContainingConcreteClassifier())
						.forEach(concreteClass -> addCorrespondingBasicComponent(concreteClass,
								implementingBasicComponents, class2BasicComponentMap));
			} else {
				throw new RuntimeException(
						"Event method is neither contained within a class nor contained within an Interface");
			}

		}
		return implementingBasicComponents;
	}

	private void addCorrespondingBasicComponent(ConcreteClassifier concreteClassifier,
			Collection<BasicComponent> implementingBasicComponents,
			Map<ConcreteClassifier, BasicComponent> class2BasicComponentMap) {
		BasicComponent basicComponent = class2BasicComponentMap.get(concreteClassifier);
		if (null != basicComponent) {
			implementingBasicComponents.add(basicComponent);
		} else {
			logger.error("Class " + concreteClassifier
					+ " does not having corresponding BasicComponent. Currently, we only support event methods in component-implementing classes.");
		}
	}

	private Map<EventGroup, List<Method>> createEventGroupsAndEventTypes(List<Method> eventGroupMethods) {
		Map<EventGroup, List<Method>> eventGroup2MethodMap = new HashMap<EventGroup, List<Method>>();
		for (Method method : eventGroupMethods) {
			Parameter observedJaMoPPParameter = findRelevantJaMoPPParameter(method);
			ConcreteClassifier observedEventDataType = getObservedEventDataType(observedJaMoPPParameter);
			if(null == observedEventDataType){
				continue;
			}
			EventGroup eventGroup = this.sourceCodeDecoratorHelper.findPCMInterfaceForJaMoPPType(observedEventDataType,
					EventGroup.class);
			if (null == eventGroup) {
				eventGroup = this.pcmModelCreationHelper.createEventGroupAndEventTypeAndUpdateSourceCodeDecorator(
						observedEventDataType, repository, observedJaMoPPParameter, method);
				this.repository.getInterfaces__Repository().add(eventGroup);
				eventGroup2MethodMap.put(eventGroup, new ArrayList<Method>());
			}
			eventGroup2MethodMap.get(eventGroup).add(method);
		}
		return eventGroup2MethodMap;
	}

	private ConcreteClassifier getObservedEventDataType(Parameter relevantJaMoPPParameter) {		Type targetType = relevantJaMoPPParameter.getTypeReference().getTarget();
		if(null == targetType){
			logger.info("targetType is null -- not considering the following Parameter as event relevant: " + relevantJaMoPPParameter);
			return null;
		}
		if (!(targetType instanceof ConcreteClassifier)) {
			throw new RuntimeException("Parameter " +  relevantJaMoPPParameter + " has wrong target type: " + targetType);
		}
		return (ConcreteClassifier) targetType;
	}

	private Parameter findRelevantJaMoPPParameter(Method method) {
		Parameter relevantJaMoPPParameter = method.getParameters().stream()
				.filter(param -> AnnotationHelper.isEventParameter(param)).findFirst().get();
		return relevantJaMoPPParameter;
	}

}
