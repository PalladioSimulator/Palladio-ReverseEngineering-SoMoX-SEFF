package org.somox.util;

import org.apache.log4j.Logger;
import org.emftext.language.java.arrays.ArrayTypeable;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.types.Type;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.repository.EventType;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.builder.OperationBuilder;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

public class PcmModelCreationHelper {

	private static final Logger logger = Logger.getLogger(PcmModelCreationHelper.class.getSimpleName());

	/**
	 * OperationBuilder from the SoMoX framework is used here to create the data
	 * types.
	 */
	private final OperationBuilder operationBuilder;
	private final SourceCodeDecoratorHelper sourceCodeDecoratorHelper;

	public PcmModelCreationHelper(final OperationBuilder operationBuilder,
			final SourceCodeDecoratorRepository sourceCodeDecorator,
			SourceCodeDecoratorHelper sourceCodeDecoratorHelper) {
		this.operationBuilder = operationBuilder;
		this.sourceCodeDecoratorHelper = sourceCodeDecoratorHelper;
	}

	public PcmModelCreationHelper(final AnalysisResult analysisResult,
			SourceCodeDecoratorHelper sourceCodeDecoratorHelper) {
		this(new OperationBuilder(analysisResult.getRoot(), null, analysisResult),
				analysisResult.getSourceCodeDecoratorRepository(), sourceCodeDecoratorHelper);
	}

	public OperationSignature createOperationSignatureInInterfaceForJaMoPPMemberAndUpdateSourceCodeDecorator(
			final OperationInterface opInterface, final Repository repo, final Member jaMoPPMember) {
		if (!(jaMoPPMember instanceof Method) && !(jaMoPPMember instanceof Constructor)) {
			return null;
		}
		final OperationSignature opSignature = RepositoryFactory.eINSTANCE.createOperationSignature();
		opSignature.setEntityName(jaMoPPMember.getName());
		if (jaMoPPMember instanceof org.emftext.language.java.members.Method) {
			final org.emftext.language.java.members.Method jaMoPPMethod = (org.emftext.language.java.members.Method) jaMoPPMember;
			if (null != jaMoPPMethod.getTypeReference() && null != jaMoPPMethod.getTypeReference().getTarget()) {
				final DataType returnType = this.getDataTypeAndUpdateSourceCodeDecorator(repo,
						jaMoPPMethod.getTypeReference().getTarget(), jaMoPPMethod);
				opSignature.setReturnType__OperationSignature(returnType);
			} else {
				PcmModelCreationHelper.logger
						.info("Could not find an approoriate type for: " + jaMoPPMethod + " in class "
								+ (null != jaMoPPMember ? jaMoPPMember.getContainingConcreteClassifier() : "null")
								+ " use Object instead");
				final DataType defaultDataType = this.operationBuilder.returnDefaultDataType(jaMoPPMethod, repo);
				opSignature.setReturnType__OperationSignature(defaultDataType);

			}
			for (final org.emftext.language.java.parameters.Parameter jaMoPPParam : jaMoPPMethod.getParameters()) {
				this.createParameterAndAddParameter(repo, opSignature, jaMoPPParam);
			}
		} else if (jaMoPPMember instanceof Constructor) {
			final Constructor ctor = (Constructor) jaMoPPMember;
			for (final org.emftext.language.java.parameters.Parameter ctorParam : ctor.getParameters()) {
				this.createParameterAndAddParameter(repo, opSignature, ctorParam);
			}
		}
		final OperationSignature retOpSignature = this.hasSignature(opSignature, opInterface);
		// this means that the finalOpSig is the same as the just create opSig
		// --> add to the
		// interface and update the SCDM
		if (retOpSignature == opSignature) {
			opInterface.getSignatures__OperationInterface().add(opSignature);
			this.sourceCodeDecoratorHelper.createMethodLevelSourceCodeLink(opSignature, jaMoPPMember);
		}
		return retOpSignature;
	}

	public EventGroup createEventGroupAndEventTypeAndUpdateSourceCodeDecorator(ConcreteClassifier observedEventDataType,
			Repository repository, org.emftext.language.java.parameters.Parameter observedJaMoPPParameter,
			Member jaMoPPMember) {
		EventGroup eventGroup = this.createEventGroupAndUpdateSCDM(observedEventDataType);
		createEventType(eventGroup, repository, observedEventDataType, observedJaMoPPParameter, jaMoPPMember);
		return eventGroup;
	}

	private EventType createEventType(final EventGroup eventGroup, final Repository repoitory,
			final ConcreteClassifier observedEventDataType,
			org.emftext.language.java.parameters.Parameter observedJaMoPPParameter, Member jaMoPPMember) {
		EventType eventType = RepositoryFactory.eINSTANCE.createEventType();
		eventType.setEntityName(observedEventDataType.getName());
		eventGroup.getEventTypes__EventGroup().add(eventType);
		Parameter parameter = this.createParameter(repoitory, observedJaMoPPParameter);
		this.sourceCodeDecoratorHelper.createMethodLevelSourceCodeLink(eventType, jaMoPPMember);
		eventType.setParameter__EventType(parameter);
		return eventType;
	}

	private EventGroup createEventGroupAndUpdateSCDM(ConcreteClassifier jaMoPPClassifier) {
		EventGroup eventGroup = RepositoryFactory.eINSTANCE.createEventGroup();
		eventGroup.setEntityName(jaMoPPClassifier.getName());
		this.sourceCodeDecoratorHelper.createInterfaceSourceCodeLink(eventGroup, jaMoPPClassifier);
		return eventGroup;
	}

	/**
	 * creates a mapping for all non-primitives data types
	 *
	 * @param repo
	 * @param jaMoPPType
	 * @return
	 */
	private DataType getDataTypeAndUpdateSourceCodeDecorator(final Repository repo, final Type jaMoPPType,
			final ArrayTypeable arrayTypeable) {
		final DataType pcmDataType = this.operationBuilder.getType(jaMoPPType, repo, arrayTypeable);
		// getDataType should create the sourcecode decorator
		// if (!this.createdPCMTypeMap.contains(pcmDataType)) {
		// this.createdPCMTypeMap.add(pcmDataType);
		// if (null != pcmDataType && !(pcmDataType instanceof
		// PrimitiveDataType)) {
		// final SourceCodeDecoratorRepository sourceCodeDecorator =
		// this.myBlackboard.getAnalysisResult()
		// .getSourceCodeDecoratorRepository();
		// final DataTypeSourceCodeLink dataTypeSourceCodeLink =
		// SourcecodedecoratorFactory.eINSTANCE
		// .createDataTypeSourceCodeLink();
		// dataTypeSourceCodeLink.setFile(jaMoPPType.getContainingCompilationUnit());
		// dataTypeSourceCodeLink.setJaMoPPType(jaMoPPType);
		// dataTypeSourceCodeLink.setPcmDataType(pcmDataType);
		// sourceCodeDecorator.getDataTypeSourceCodeLink().add(dataTypeSourceCodeLink);
		// }
		// }
		return pcmDataType;
	}

	private void createParameterAndAddParameter(final Repository repo, final OperationSignature opSignature,
			final org.emftext.language.java.parameters.Parameter jaMoPPParam) {
		final Parameter pcmParam = createParameter(repo, jaMoPPParam);
		opSignature.getParameters__OperationSignature().add(pcmParam);
	}

	private Parameter createParameter(final Repository repo,
			final org.emftext.language.java.parameters.Parameter jaMoPPParam) {
		final Parameter pcmParam = RepositoryFactory.eINSTANCE.createParameter();
		pcmParam.setParameterName(jaMoPPParam.getName());

		if (null != jaMoPPParam.getTypeReference() && null != jaMoPPParam.getTypeReference().getTarget()) {
			final DataType dataType = this.getDataTypeAndUpdateSourceCodeDecorator(repo,
					jaMoPPParam.getTypeReference().getTarget(), jaMoPPParam);
			pcmParam.setDataType__Parameter(dataType);
		} else {
			PcmModelCreationHelper.logger.info("No PCM param build for parameter: " + jaMoPPParam + " for parameter "
					+ (null != jaMoPPParam ? jaMoPPParam.getContainingConcreteClassifier() : "null")
					+ " use Object instead");
			final DataType defaultDataType = this.operationBuilder.returnDefaultDataType(jaMoPPParam, repo);
			pcmParam.setDataType__Parameter(defaultDataType);
		}
		return pcmParam;
	}

	/**
	 * checks if the operation interface already has the operaiton signature
	 * Compares the name, the number of parameters and the parameters itself as
	 * well as the return type
	 *
	 * @param newOpSignature
	 * @param opInterface
	 * @return
	 */
	private OperationSignature hasSignature(final OperationSignature newOpSignature,
			final OperationInterface opInterface) {
		for (final OperationSignature opSignature : opInterface.getSignatures__OperationInterface()) {
			if (this.signatureEquals(opSignature, newOpSignature)) {
				return opSignature;
			}
		}
		return newOpSignature;
	}

	/**
	 * test whether the signatures are equal. A Signature is considered equal to
	 * another one if the name mathches, the return types are the same and all
	 * parameter types are equal
	 *
	 * @param opSignature
	 * @param newOpSignature
	 * @return
	 */
	private boolean signatureEquals(final OperationSignature opSignature, final OperationSignature newOpSignature) {
		if (opSignature == newOpSignature) {
			return true;
		}
		if (null == opSignature && null != newOpSignature) {
			return false;
		}
		if (null != opSignature && null == newOpSignature) {
			return false;
		}
		if (!this.entityNameEquals(opSignature, newOpSignature)) {
			return false;
		}
		if (!this.dataTypeEquals(opSignature.getReturnType__OperationSignature(),
				newOpSignature.getReturnType__OperationSignature())) {
			return false;
		}
		if (opSignature.getParameters__OperationSignature().size() != newOpSignature.getParameters__OperationSignature()
				.size()) {
			return false;
		}
		int i = 0;
		Parameter oldParam = null;
		Parameter newParam = null;
		while (i < opSignature.getParameters__OperationSignature().size()) {
			oldParam = opSignature.getParameters__OperationSignature().get(i);
			newParam = newOpSignature.getParameters__OperationSignature().get(i);
			if (!this.dataTypeEquals(newParam.getDataType__Parameter(), oldParam.getDataType__Parameter())) {
				return false;
			}
			i++;
		}
		return true;
	}

	private boolean entityNameEquals(final OperationSignature first, final OperationSignature secound) {
		if (first == null && secound == null) {
			return true;
		}
		if (first == null && secound != null) {
			return false;
		}
		if (first != null && secound == null) {
			return false;
		}
		return first.getEntityName().equals(secound.getEntityName());
	}

	private boolean dataTypeEquals(final DataType first, final DataType secound) {
		if (first == null && secound == null) {
			return true;
		}
		if (first == null && secound != null) {
			return false;
		}
		if (first != null && secound == null) {
			return false;
		}
		return first.equals(secound);
	}

	public OperationInterface createOperationInterfaceAndUpdateSCDM(final ConcreteClassifier concreteClassifier) {
		final OperationInterface opInterface = RepositoryFactory.eINSTANCE.createOperationInterface();
		opInterface.setEntityName(concreteClassifier.getName());
		this.sourceCodeDecoratorHelper.createInterfaceSourceCodeLink(opInterface, concreteClassifier);
		return opInterface;
	}

}
