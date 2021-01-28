package org.annotationsmox.analyzer.creators;

import java.util.List;
import java.util.stream.Collectors;

import org.annotationsmox.analyzer.AnnotationHelper;
import org.annotationsmox.analyzer.AnnotationsMoxPCMRepositoryModelCreator;
import org.apache.log4j.Logger;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.generics.QualifiedTypeArgument;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.Type;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.repository.SourceRole;
import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.KDMHelper;
import org.somox.util.SourceCodeDecoratorHelper;

public class RequiredRoleCreator {

	private static final Logger logger = Logger.getLogger(RequiredRoleCreator.class.getSimpleName());

	private final SourceCodeDecoratorHelper sourceCodeDecoratorHelper;

	public RequiredRoleCreator(SourceCodeDecoratorHelper sourceCodeDecoratorHelper) {
		this.sourceCodeDecoratorHelper = sourceCodeDecoratorHelper;
	}

	public void createRequiredRoles(final BasicComponent basicComponent, final Class ejbClass) {
		final List<Field> ejbFields = ejbClass.getMembers().stream().filter(member -> member instanceof Field)
				.map(member -> (Field) member).filter(field -> isEJBRelevantField(field)).collect(Collectors.toList());
		for (final Field ejbField : ejbFields) {
			final Type accessedType = GetAccessedType.getAccessedType(ejbField.getTypeReference());
			if (isEventEJBField(accessedType, ejbField)) {
				createRequiredEventInterface(ejbField, basicComponent);
			} else {
				createOperationRequiredRole(accessedType, basicComponent);
			}
		}
	}

	private boolean isEventEJBField(final Type accessedType, Field ejbField) {
		if (null != accessedType) {
			return AnnotationHelper.isEJBEventType(accessedType);
		}
		List<ClassifierReference> childrenByType = ejbField.getTypeReference()
				.getChildrenByType(ClassifierReference.class);
		return childrenByType.stream().filter(possibleEventClass -> possibleEventClass.getTarget() != null
				&& possibleEventClass.getTarget().getName().equals("Event")).findAny().isPresent();
	}

	private void createRequiredEventInterface(Field ejbField, BasicComponent basicComponent) {
		SourceRole sourceRole = RepositoryFactory.eINSTANCE.createSourceRole();
		sourceRole.setRequiringEntity_RequiredRole(basicComponent);
		final QualifiedTypeArgument qta = KDMHelper.getFirstChildWithType(ejbField, QualifiedTypeArgument.class);
		if (null == qta || null == qta.getTypeReference() || null == qta.getTypeReference().getTarget()) {
			logger.warn("Could not create source role. Reason: Could not find a QualifiedTypeArgument for field: "
					+ ejbField);
			return;
		}
		final Type emitedDataType = qta.getTypeReference().getTarget();
		final EventGroup requiredEventGroup = this.sourceCodeDecoratorHelper
				.findPCMInterfaceForJaMoPPType(emitedDataType, EventGroup.class);
		sourceRole.setEventGroup__SourceRole(requiredEventGroup);
		sourceRole.setEntityName(basicComponent.getEntityName() + "_requires_" + requiredEventGroup.getEntityName());

	}

	private void createOperationRequiredRole(Type accessedType, BasicComponent basicComponent) {
		final OperationInterface requiredInterface = this.sourceCodeDecoratorHelper
				.findPCMInterfaceForJaMoPPType(accessedType, OperationInterface.class);
		if (null == requiredInterface) {
			logger.warn(
					"Could not find an OperationInterface for the EJB type: " + accessedType + ". Maybe the interface "
							+ accessedType + " is not provided by a component within the source code");
			return;
		}
		final OperationRequiredRole orr = RepositoryFactory.eINSTANCE.createOperationRequiredRole();
		orr.setRequiredInterface__OperationRequiredRole(requiredInterface);
		orr.setRequiringEntity_RequiredRole(basicComponent);
		orr.setEntityName(basicComponent.getEntityName() + "_requires_" + requiredInterface.getEntityName());
	}

	/**
	 * A field is EJB relevant if it has an @EJB or an @Inject annotation.If
	 * extensions are enabled EJBmox also considers fields that have an
	 * corresponding architectural interface as EJB interfaces.
	 * 
	 * @param field
	 *            the field to investigate
	 * @return whether the field is architectural relevant (true) or not (false)
	 */
	private boolean isEJBRelevantField(Field field) {
		if (AnnotationHelper.hasEJBAnnotation(field)) {
			return true;
		}
		if (AnnotationsMoxPCMRepositoryModelCreator.EXTENSIONS_FOR_FIELDS_AND_INTERFACES) {
			final Type accessedType = GetAccessedType.getAccessedType(field.getTypeReference());
			// check if the accessed type is an EJB relevant interface
			OperationInterface opIf = this.sourceCodeDecoratorHelper.findPCMInterfaceForJaMoPPType(accessedType,
					OperationInterface.class);
			if (null != opIf) {
				return true;
			}
			// check if the accessed type is an EJB event
			return isEventEJBField(accessedType, field);
		}
		return false;
	}
}
