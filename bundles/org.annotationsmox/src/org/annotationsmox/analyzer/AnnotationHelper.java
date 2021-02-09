package org.annotationsmox.analyzer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.types.Type;
import org.somox.kdmhelper.KDMHelper;

public final class AnnotationHelper {

	private static final Logger logger = Logger.getLogger(AnnotationHelper.class.getSimpleName());

	private static final String EJB_STATELESS_ANNOTATION_NAME = "Stateless";
	private static final String EJB_STATEFUL_ANNOTATION_NAME = "Stateful";
	private static final String EJB_MESSAGE_DRIVEN_ANNOTATION_NAME = "MessageDriven";
	private static final String EJB_LOCAL_ANNOTATION_NAME = "Local";
	private static final String EJB_REMOTE_ANNOTATION_NAME = "Remote";
	private static final String EJB_EJB_ANNOTATION_NAME = "EJB";
	private static final String EJB_DEPENDENT_ANNOTATION_NAME = "Dependent";
	private static final String EJB_INJECT_ANNOTATION_NAME = "Inject";
	private static final String JAVAX_ENTERPRISE_EVENT_CLASS_FQN = "javax.enterprise.event.Event";
	private static final String EJB_OBSERVES_ANNOTATION_NAME = "Observes";
	
	private static final String JAX_RS_APPLICATION_PATH_ANNOTATION_NAME = "ApplicationPath";
	private static final String JAX_RS_CONSTRAINED_TO_ANNOTATION_NAME = "ConstrainedTo";
	private static final String JAX_RS_CONSUMES_ANNOTATION_NAME = "Consumes";
	private static final String JAX_RS_PATH_ANNOTATION_NAME = "Path";
	private static final String JAX_RS_PRE_MATCHING_ANNOTATION_NAME = "PreMatching";
	private static final String JAX_RS_PRODUCES_ANNOTATION_NAME = "Produces";
	private static final String JAX_RS_PROVIDER_ANNOTATION_NAME = "Provider";
	private static final String JAX_RS_ENCODED_ANNOTATION_NAME = "Encoded";
	
	private static final String SERVLET_HANDLES_TYPES_ANNOTATION_NAME = "HandlesTypes";
	private static final String SERVLET_MULTIPART_CONFIG_ANNOTATION_NAME = "MultipartConfig";
	private static final String SERVLET_SERVLET_SECURITY_ANNOTATION_NAME = "ServletSecurity";
	private static final String SERVLET_WEB_FILTER_ANNOTATION_NME = "WebFilter";
	private static final String SERVLET_WEB_INIT_PARAM_ANNOTATION_NAME = "WebInitParam";
	private static final String SERVLET_WEB_LISTENER_ANNOTATION_NAME = "WebListener";
	private static final String SERVLET_WEB_SERVLET_ANNOTATION_NAME = "WebServlet";
	
	private static final String SPRING_BOOT_SPRING_BOOT_APPLICATION_ANNOTATION_NAME = "SpringBootApplication";
	private static final String SPRING_BOOT_JSON_COMPONENT_ANNOTATION_NAME = "JsonComponent";
	private static final String SPRING_BOOT_ENDPOINT_ANNOTATION_NAME = "Endpoint";
	private static final String SPRING_BOOT_ENDPOINT_EXTENSION_ANNOTATION_NAME = "EndpointExtension";
	private static final String SPRING_BOOT_FILTERED_ENDPOINT_ANNOTATION_NAME = "FilteredEndpoint";
	private static final String SPRING_BOOT_ENDPOINT_CLOUD_FOUNDRY_EXTENSION_ANNOTATION_NAME = "EndpointCloudFoundryExtension";
	private static final String SPRING_BOOT_JMX_ENDPOINT_ANNOTATION_NAME = "JmxEndpoint";
	private static final String SPRING_BOOT_ENDPOINT_JMX_EXTENSION_ANNOTATION_NAME = "EndpointJmxExtension";
	private static final String SPRING_BOOT_CONTROLLER_ENDPOINT_ANNOTATION_NAME = "ControllerEndpoint";
	private static final String SPRING_BOOT_REST_CONTROLLER_ENDPOINT_ANNOTATION_NAME = "RestControllerEndpoint";
	private static final String SPRING_BOOT_WEB_ENDPOINT_ANNOTATION_NAME = "WebEndpoint";
	private static final String SPRING_BOOT_ENDPOINT_WEB_EXTENSION_ANNOTATION_NAME = "EndpointWebExtension";
	private static final String SPRING_BOOT_SERVLET_ENDPOINT_ANNOTATION_NAME = "ServletEndpoint";
	

	static final Set<String> COMPONENT_ANNOTATION_NAMES = new HashSet<String>(
			Arrays.asList(
					AnnotationHelper.EJB_STATELESS_ANNOTATION_NAME,
					AnnotationHelper.EJB_STATEFUL_ANNOTATION_NAME,
					AnnotationHelper.EJB_MESSAGE_DRIVEN_ANNOTATION_NAME,
					AnnotationHelper.EJB_DEPENDENT_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_APPLICATION_PATH_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_CONSTRAINED_TO_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_CONSUMES_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_PATH_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_PRE_MATCHING_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_PRODUCES_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_PROVIDER_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_ENCODED_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_HANDLES_TYPES_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_MULTIPART_CONFIG_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_SERVLET_SECURITY_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_WEB_FILTER_ANNOTATION_NME,
					AnnotationHelper.SERVLET_WEB_INIT_PARAM_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_WEB_LISTENER_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_WEB_SERVLET_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_SPRING_BOOT_APPLICATION_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_JSON_COMPONENT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_CONTROLLER_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_ENDPOINT_CLOUD_FOUNDRY_EXTENSION_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_ENDPOINT_EXTENSION_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_ENDPOINT_JMX_EXTENSION_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_ENDPOINT_WEB_EXTENSION_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_FILTERED_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_JMX_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_REST_CONTROLLER_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_SERVLET_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_WEB_ENDPOINT_ANNOTATION_NAME));
	static final Set<String> COMPONENT_INTERFACE_ANNOTATION_NAMES = new HashSet<String>(
			Arrays.asList(
					AnnotationHelper.EJB_LOCAL_ANNOTATION_NAME,
					AnnotationHelper.EJB_REMOTE_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_APPLICATION_PATH_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_CONSTRAINED_TO_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_CONSUMES_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_PATH_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_PRE_MATCHING_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_PRODUCES_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_PROVIDER_ANNOTATION_NAME,
					AnnotationHelper.JAX_RS_ENCODED_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_HANDLES_TYPES_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_MULTIPART_CONFIG_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_SERVLET_SECURITY_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_WEB_FILTER_ANNOTATION_NME,
					AnnotationHelper.SERVLET_WEB_INIT_PARAM_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_WEB_LISTENER_ANNOTATION_NAME,
					AnnotationHelper.SERVLET_WEB_SERVLET_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_SPRING_BOOT_APPLICATION_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_JSON_COMPONENT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_CONTROLLER_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_ENDPOINT_CLOUD_FOUNDRY_EXTENSION_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_ENDPOINT_EXTENSION_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_ENDPOINT_JMX_EXTENSION_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_ENDPOINT_WEB_EXTENSION_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_FILTERED_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_JMX_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_REST_CONTROLLER_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_SERVLET_ENDPOINT_ANNOTATION_NAME,
					AnnotationHelper.SPRING_BOOT_WEB_ENDPOINT_ANNOTATION_NAME));

	private AnnotationHelper() {
	}

	public static boolean isComponentClass(final Class jamoppClass) {
		final boolean found = AnnotationHelper.hasAnnoations(jamoppClass, AnnotationHelper.COMPONENT_ANNOTATION_NAMES);
		if (found) {
			logger.info("Found component class: " + jamoppClass.getQualifiedName());
		}
		return found;
	}

	public static boolean filterAnnotationName(final AnnotationInstance annotation,
			final Set<String> annotationClassifiersToCheck) {
		final Classifier annotationClassifier = annotation.getAnnotation();
		if (null != annotationClassifier) {
			return annotationClassifiersToCheck.contains(annotationClassifier.getName());
		}
		return false;
	}

	public static boolean hasAnnoations(final AnnotableAndModifiable annotableAndModifiable,
			final Set<String> annotationClassifiersToCheck) {
		return annotableAndModifiable.getAnnotationsAndModifiers().stream()
				.filter(annotationOrModifier -> annotationOrModifier instanceof AnnotationInstance)
				.map(annotation -> (AnnotationInstance) annotation).filter(annotation -> AnnotationHelper
						.filterAnnotationName(annotation, annotationClassifiersToCheck))
				.collect(Collectors.toList()).size() > 0;
	}

	public static List<AnnotationInstance> getAnnotations(final AnnotableAndModifiable annotableAndModifiable,
			final String annotationClassifierToCheck) {
		Set<String> classifierSet = new HashSet<>();
		classifierSet.add(annotationClassifierToCheck);
		return getAnnotations(annotableAndModifiable, classifierSet);
	}

	public static List<AnnotationInstance> getAnnotations(final AnnotableAndModifiable annotableAndModifiable,
			final Set<String> annotationClassifiersToCheck) {
		return annotableAndModifiable.getAnnotationsAndModifiers().stream()
				.filter(annotationOrModifier -> annotationOrModifier instanceof AnnotationInstance)
				.map(annotation -> (AnnotationInstance) annotation).filter(annotation -> AnnotationHelper
						.filterAnnotationName(annotation, annotationClassifiersToCheck))
				.collect(Collectors.toList());
	}

	public static boolean isComponentInterface(final Interface implemententedInterface) {
		final boolean found = AnnotationHelper.hasAnnoations(implemententedInterface,
				AnnotationHelper.COMPONENT_INTERFACE_ANNOTATION_NAMES);
		if (found) {
			logger.info("Found component interface " + implemententedInterface.getQualifiedName());
		}
		return found;
	}

	public static boolean hasEJBAnnotation(final Field field) {
		final boolean found = AnnotationHelper.hasAnnoations(field, new HashSet<String>(
				Arrays.asList(AnnotationHelper.EJB_EJB_ANNOTATION_NAME, AnnotationHelper.EJB_INJECT_ANNOTATION_NAME)));
		if (found) {
			logger.info("Found field with EJB annotation: " + field.getName() + " in class: "
					+ field.getContainingConcreteClassifier().getQualifiedName());
		}
		return found;
	}

	public static boolean isEJBEventType(final Type accessedType) {
		if (accessedType instanceof Classifier) {
			final Classifier accessedClassifier = (Classifier) accessedType;
			if (KDMHelper.computeFullQualifiedName(accessedClassifier).equals(JAVAX_ENTERPRISE_EVENT_CLASS_FQN)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasEventParameter(Method method) {
		boolean hasEventParam = method.getParameters().stream()
				.filter(param -> isEventParameter(param))
				.findAny().isPresent();
		return hasEventParam;
	}

	public static boolean isEventParameter(Parameter param) {
		return AnnotationHelper.hasAnnoations(param,
				new HashSet<String>(Arrays.asList(AnnotationHelper.EJB_OBSERVES_ANNOTATION_NAME)));
	}

}
