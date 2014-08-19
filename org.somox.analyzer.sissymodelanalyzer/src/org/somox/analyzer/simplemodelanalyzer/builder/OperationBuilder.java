package org.somox.analyzer.simplemodelanalyzer.builder;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
//import org.eclipse.gmt.modisco.java.ArrayType;
//import org.eclipse.gmt.modisco.java.MethodDeclaration;
//import org.eclipse.gmt.modisco.java.PrimitiveTypeVoid;
//import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
//import org.eclipse.gmt.modisco.java.Type;
//import org.eclipse.gmt.modisco.java.VisibilityKind;

import org.emftext.language.java.members.Method;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.*;
import org.emftext.language.java.arrays.ArrayDimension;
import org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped;
import org.emftext.language.java.arrays.ArrayTypeable;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.variables.Variable;



import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.builder.util.DefaultResourceEnvironment;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;
//import de.fzi.gast.core.Root;
//import de.fzi.gast.functions.Method;
//import de.fzi.gast.types.GASTClass;
//import de.fzi.gast.types.Visibilities;
//import de.fzi.gast.variables.FormalParameter;
//import eu.qimpress.samm.datatypes.CollectionDataType;
//import eu.qimpress.samm.datatypes.ComplexDataType;
//import eu.qimpress.samm.datatypes.DatatypesFactory;
//import eu.qimpress.samm.datatypes.InnerElement;
//import eu.qimpress.samm.datatypes.PrimitiveDataType;
//import eu.qimpress.samm.datatypes.XSDPrimitiveDataTypes;
//import eu.qimpress.samm.staticstructure.Interface;
//import eu.qimpress.samm.staticstructure.MessageType;
//import eu.qimpress.samm.staticstructure.Operation;
//import eu.qimpress.samm.staticstructure.Parameter;
//import eu.qimpress.samm.staticstructure.Repository;
//import eu.qimpress.samm.staticstructure.StaticstructureFactory;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

import de.uka.ipd.sdq.pcm.repository.CompositeDataType;
import de.uka.ipd.sdq.pcm.repository.DataType;
import de.uka.ipd.sdq.pcm.repository.InnerDeclaration;
import de.uka.ipd.sdq.pcm.repository.OperationInterface;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.pcm.repository.Parameter;
import de.uka.ipd.sdq.pcm.repository.RepositoryFactory;

/**
 * Builder for operations, parameters, message types, and data types. Keeps the
 * source code decorator updated.
 * 
 * @author Michael Hauck, Steffen Becker, Klaus Krogmann
 * 
 */
public class OperationBuilder extends AbstractBuilder {

	private static Logger logger = Logger.getLogger(OperationBuilder.class);

	public OperationBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration, AnalysisResult analysisResult) {
		super(gastModel, somoxConfiguration, analysisResult);
	}

	public void createOperations(Type implementationClass, Type interfaceClass,
			OperationInterface interf) {

		for (Method method : KDMHelper.getMethods(interfaceClass)) {

			if (method.isPublic())// (KDMHelper.isModifierOfKind(method, VisibilityKind.NONE)) || KDMHelper .isModifierOfKind(method, VisibilityKind.PUBLIC)) 
				{

				Method realMethod = method;

				if (implementationClass != null) {
					realMethod = getRealMethod(implementationClass, method);
					if (realMethod == null) {
						realMethod = method;
						// removelater was for debug reason
						if (method.getName().equals("refresh")) {
							int a = 0;
						}
						// removelater
						logger.error("GAST Model misses a method "
								+ method.getName());
					}
				} else {
					logger.warn("no implementation class for method "
							+ method.getName() + " of interface "
							+KDMHelper.getName(interfaceClass));
				}
				OperationSignature op = createOperationSignature(realMethod,
						interf);
				interf.getSignatures__OperationInterface().add(op);
			}
		}
	}

	/**
	 * 
	 * @param implementationClass
	 * @param inputMethod
	 *            interface method
	 * @return null if no implementation method was found; the queried method
	 *         otherwise
	 */
	private Method getRealMethod(Type implementationClass,
			Method inputMethod) {
		assert implementationClass != null;

		for (Method methodFromClass : KDMHelper
				.getMethods(implementationClass)) {

			if (methodFromClass == inputMethod) {
				return methodFromClass;
			}

			if (methodFromClass.getName().equals(inputMethod.getName())) {
				// TODO burkha 23.5.2013 getOverriddenMember does not work
				// correct in contrast to SISSy
				Method overrideMethod = (Method) KDMHelper
						.getOverriddenASTNode(methodFromClass);
				while (overrideMethod != null) {
					if (overrideMethod == inputMethod)
						return methodFromClass;
					else
						overrideMethod = (Method) KDMHelper
								.getOverriddenASTNode(overrideMethod);
				}
			}
		}
		for (Type superClass : KDMHelper.getSuperTypes(implementationClass)) {
			if (!KDMHelper.isAbstract(superClass)
					&& !KDMHelper.isInterface(superClass)) {
				Method real = getRealMethod(superClass, inputMethod);
				if (real != null) {
					return real;
				}
			}
		}
		return null;
	}

	/**
	 * Adds MessageTypes to the resultRepository, set parameter names and types.
	 * First looks if a MessageType already exists and creates one only if it
	 * does not exist in the repository.
	 * 
	 * @param method
	 *            GAST method to add
	 * @param interf
	 * @return a new operation for which parameter names and types already exist
	 *         in the resultRepository
	 */
	private OperationSignature createOperationSignature(Method method, OperationInterface interf) {
		
		OperationSignature operation = RepositoryFactory.eINSTANCE.createOperationSignature();
		String nameForMethod = createNonExistingNameInInterface(method, interf);
		operation.setEntityName(nameForMethod);
		
		updateSourceCodeDecorator(operation, method);
		// Variable statt SingleVariableDeclaration
		for (Variable inputParameter : method.getParameters()) {
			Parameter opSigParam = RepositoryFactory.eINSTANCE.createParameter();
			opSigParam.setParameterName(inputParameter.getName());
			Type accessedType = GetAccessedType.getAccessedType(inputParameter.getTypeReference());
			if(inputParameter.getTypeReference() != null && null != accessedType) {
				opSigParam.setDataType__Parameter(
						//inputParameter.getTypeReference() statt inputParameter.getType()
						getType(accessedType, 
						this.analysisResult.getInternalArchitectureModel()));				
			} else {
				logger.error("Input parameter type was null. Could not set the parameter type \"" +
						inputParameter.getName() + "\" of method \"" + method.getName() + "\"");
				continue;
			}
			opSigParam.setOperationSignature__Parameter(operation);
		}
		
		if(null != method.getTypeReference() && null != GetAccessedType.getAccessedType(method.getTypeReference()) &&
				!(method.getTypeReference() instanceof org.emftext.language.java.types.Void)){
			operation.setReturnType__OperationSignature(
					getType(GetAccessedType.getAccessedType(method.getTypeReference()), 
							this.analysisResult.getInternalArchitectureModel()));				
		}
		
		
//		TODO SAMM2PCM removed
//		if (paramNames.size() > 0) {
//			MessageType messageType = findMessageTypeInRepository(paramNames, paramTypes);
//			if (messageType == null) {
//				messageType = createMessageType(paramNames, paramTypes);
//			}
//			if(messageType != null) { //newly created message type can still be null
//				operation.setInput(messageType);
//			}
//		}
		
		return operation;
	}

	private String createNonExistingNameInInterface(Method method,
			OperationInterface interf) {
		String methodName = method.getName();
		if (!containsName(interf, methodName)) {
			return methodName;
		}

		int counter = 1;

		while (containsName(interf,
				createMethodNameWithNumber(methodName, counter))) {
			counter++;
		}
		return createMethodNameWithNumber(methodName, counter);

	}

	private String createMethodNameWithNumber(String methodName, int counter) {
		return methodName + "_" + counter;
	}

	private boolean containsName(OperationInterface interf, String methodName) {
		EList<OperationSignature> signatures = interf
				.getSignatures__OperationInterface();
		for (OperationSignature signature : signatures) {
			if (signature.getEntityName().equals(methodName)) {
				return true;
			}
		}
		return false;
	}

	private void updateSourceCodeDecorator(OperationSignature operation,
			Method method) {
		// assert method.getStatus() == Status.NORMAL; //TODO: check re-enabling
		// other status implies empty method body and causes trouble during
		// later stages

		MethodLevelSourceCodeLink link = SourcecodedecoratorFactory.eINSTANCE
				.createMethodLevelSourceCodeLink();

		link.setFunction(method);
		link.setOperation(operation);

		if (KDMHelper.getJavaNodeSourceRegion(method) != null){
			link.setFile(KDMHelper.getJavaNodeSourceRegion(method));
		}

		this.analysisResult.getSourceCodeDecoratorRepository()
				.getMethodLevelSourceCodeLink().add(link);
	}

	/**
	 * Look if a message type that contains the parameters specified by name and
	 * type already exists in the repository
	 * 
	 * @return the MessageType. Returns null, if no message type is found, or if
	 *         the size of parameterNames does not equal the size of
	 *         parameterTypes.
	 */
	// private MessageType findMessageTypeInRepository(
	// List<String> parameterNames,
	// List<Type> parameterTypes) {
	// if (parameterNames == null) {
	// parameterNames = new ArrayList<String>();
	// }
	// if (parameterTypes == null) {
	// parameterTypes = new ArrayList<Type>();
	// }
	// if (parameterNames.size() != parameterTypes.size()) {
	// return null;
	// }
	// for (MessageType messageType :
	// this.analysisResult.getInternalArchitectureModel().getMessagetype()) {
	// if (messageType.getParameters().size() != parameterNames.size()) {
	// continue;
	// }
	// boolean parametersMatch = true;
	// for (int i = 0; i < messageType.getParameters().size(); i++) {
	// de.uka.ipd.sdq.pcm.repository.Parameter param =
	// messageType.getParameters().get(i);
	// if (!param.getParameterName().equals(parameterNames.get(i))) {
	// parametersMatch = false;
	// break;
	// }
	// if (param.getDataType__Parameter() != null &&
	// param.getDataType__Parameter().getName() != null && //null pointer
	// protection
	// parameterTypes.get(i).getName() != null &&
	// !param.getDataType__Parameter().getName().toLowerCase().equals(
	// parameterTypes.get(i).getName().toLowerCase())) {
	// parametersMatch = false;
	// break;
	// }
	// }
	// if (parametersMatch == true) {
	// return messageType;
	// }
	// }
	// return null;
	// }

	/**
	 * Create a message type
	 * 
	 * @param parameterNames
	 *            the names of the parameters
	 * @param parameterTypes
	 *            the type names of the parameter. SAMM types are created (if
	 *            they do not already exist) for these types
	 * @param repository
	 *            the Repository in which the MessageType has to be stored
	 * @return the created message type. Returns null if the size of
	 *         parameterNames does not equal the size of parameterTypes or if
	 *         only void parameters are present
	 */
	// private MessageType createMessageType(List<String> parameterNames,
	// List<Type> parameterTypes) {
	// if (parameterNames == null) {
	// parameterNames = new ArrayList<String>();
	// }
	// if (parameterTypes == null) {
	// parameterTypes = new ArrayList<Type>();
	// }
	// if (parameterNames.size() != parameterTypes.size()) {
	// return null;
	// }
	// MessageType messageType =
	// StaticstructureFactory.eINSTANCE.createMessageType();
	// String messageTypeName = "";
	// if (parameterTypes.size() > 0) {
	// for (int i = 0; i < parameterTypes.size(); i++) {
	// if(!parameterTypes.get(i).getName().equals(voidType)) { //do not create
	// void pointers
	//
	// if (messageTypeName.length() > 0) {
	// messageTypeName += "_";
	// }
	// messageTypeName += parameterTypes.get(i).getName();
	// Parameter param = RepositoryFactory.eINSTANCE.createParameter();
	// param.setParameterName(parameterNames.get(i));
	// param.setDataType__Parameter(getType(parameterTypes.get(i),
	// this.analysisResult.getInternalArchitectureModel()));
	// messageType.getParameters().add(param);
	// }
	// }
	// }
	// if(messageType.getParameters().size() > 0) {
	// messageType.setName(messageTypeName);
	// this.analysisResult.getInternalArchitectureModel().getMessagetype().add(messageType);
	// return messageType;
	// } else {
	// return null; // only void parameters which are omitted
	// }
	//
	// }

	/**
	 * Data type creation or look up for existing data types.
	 * 
	 * @param typeName
	 *            type name to create
	 * @param repository
	 *            repository containing all present types
	 * @return a new data type for non-existing ones; the existing instance else
	 */
	private DataType getType(Type gastType,
			de.uka.ipd.sdq.pcm.repository.Repository repository) {
		DataType type = getExistingType(gastType, repository);

		if (type == null) {
			type = createDataType(repository, gastType);
		}
		return type;
	}

	/**
	 * Creates a new data type for the given gastType.
	 * 
	 * @param repository
	 *            The repository to add the new data type to
	 * @param gastType
	 *            The type to create a SAMM data type for
	 * @return
	 */
	private DataType createDataType(
			de.uka.ipd.sdq.pcm.repository.Repository repository, Type gastType) {
		String typeName = KDMHelper.getName(gastType);
		typeName = getUnifiedTypeName(typeName);

		DataType newType = null;
		if (typeName.toLowerCase().equals("void")) {
			// do nothing
		} else if (typeName.toLowerCase().equals("integer")) {
			return DefaultResourceEnvironment.getPrimitiveDataTypeInteger();
		} else if (typeName.toLowerCase().equals("double")) {
			return DefaultResourceEnvironment.getPrimitiveDataTypeDouble();
		} else if (typeName.toLowerCase().equals("string")) {
			return DefaultResourceEnvironment.getPrimitiveDataTypeString();
		} else if (typeName.toLowerCase().equals("boolean")) {
			return DefaultResourceEnvironment.getPrimitiveDataTypeBool();
		} else if (typeName.toLowerCase().equals("char")) {
			return DefaultResourceEnvironment.getPrimitiveDataTypeChar();
		} else if (typeName.toLowerCase().equals("byte")) {
			return DefaultResourceEnvironment.getPrimitiveDataTypeByte();
		} else if (gastType instanceof ArrayInstantiationByValuesTyped) {
			// ArrayTypeable statt ArrayType
			ArrayInstantiationByValuesTyped  arrayType = (ArrayInstantiationByValuesTyped) gastType;
			// Create a collection data type:
			newType = RepositoryFactory.eINSTANCE.createCollectionDataType();
			repository.getDataTypes__Repository().add(newType);
			logger.debug("found collection type " + typeName);
			// set inner type:
			DataType innerType = getType(arrayType.getReferencedType(), repository);
			if(innerType == null){
				logger.error("Unsupported inner type: " + arrayType.getReferencedType());
				// TODO switch to real type checks!!!
			}
			((de.uka.ipd.sdq.pcm.repository.CollectionDataType) newType)
					.setInnerType_CollectionDataType(innerType);
		} else {
			// TODO handling of class causes an infinity loop -> abort
			if (KDMHelper.getName(gastType).equalsIgnoreCase("class")) {
				return null;
			}
			if (KDMHelper.getAllAccessedClasses(gastType).size() > 1) {
				// create a complex data type:
				newType = RepositoryFactory.eINSTANCE.createCompositeDataType();
				repository.getDataTypes__Repository().add(newType);

				// set inner types:
				for (Type currentClass : KDMHelper
						.getAllAccessedClasses(gastType)) {
					// avoid self-references and void as access
					if (!currentClass.equals(gastType)
							&& !KDMHelper.getName(currentClass).equals("void")) {
						String tmpInnerTypeName = KDMHelper.getName(currentClass);
						;
						InnerDeclaration innerElement = RepositoryFactory.eINSTANCE
								.createInnerDeclaration();
						innerElement.setDatatype_InnerDeclaration(getType(
								currentClass, repository));
						innerElement.setEntityName(tmpInnerTypeName);
						((CompositeDataType) newType)
								.getInnerDeclaration_CompositeDataType().add(
										innerElement);
					}
				}
			}
		}
		return newType;
	}

	/**
	 * Reduces comparable types to a single type and copes with potentially
	 * different naming of the same type.
	 * 
	 * @param typeName
	 * @return
	 */
	private String getUnifiedTypeName(String typeName) {
		if (typeName.toLowerCase().equals("int")
				|| typeName.toLowerCase().equals("long")) {
			// Do not create 2 datatypes for int and integer
			// maps int and long to integer
			typeName = "integer";
		} else if (typeName.toLowerCase().equals("bool")) {
			// Do not create 2 datatypes for bool and boolean
			typeName = "boolean";
			// } else if (typeName.toLowerCase().equals("char")) {//TODO
			// SAMM2PCM removed
			// typeName = "string"; // map char to string
		} else if (typeName.toLowerCase().equals("float")) {
			typeName = "double"; // map double to float
		}
		return typeName;
	}

	/**
	 * 
	 * @param gastType
	 * @param repository
	 * @return null if not found
	 */
	private DataType getExistingType(Type gastType,
			de.uka.ipd.sdq.pcm.repository.Repository repository) {
		return getExistingTypeByName(KDMHelper.getName(gastType), repository);
	}

	/**
	 * 
	 * @param gastTypeName
	 * @param repository
	 * @return null if not found
	 */
	private DataType getExistingTypeByName(String gastTypeName,
			de.uka.ipd.sdq.pcm.repository.Repository repository) {
		gastTypeName = getUnifiedTypeName(gastTypeName);

		for (DataType currentType : repository.getDataTypes__Repository()) {
			if (currentType.getRepository__DataType().getEntityName()
					.toLowerCase().equals(gastTypeName.toLowerCase())) {
				return currentType;
			}
		}

		logger.info("no type found for " + gastTypeName + ". Type will be created.");
		return null;
	}

}
