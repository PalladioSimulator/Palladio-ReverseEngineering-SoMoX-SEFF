package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.somox.analyzer.AnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.metamodeladdition.Root;

//import de.fzi.gast.core.Root;
//import de.fzi.gast.functions.Method;
//import de.fzi.gast.types.GASTClass;
//import de.fzi.gast.types.Visibilities;
//import de.fzi.gast.variables.FormalParameter;
import eu.qimpress.samm.datatypes.CollectionDataType;
import eu.qimpress.samm.datatypes.ComplexDataType;
import eu.qimpress.samm.datatypes.DatatypesFactory;
import eu.qimpress.samm.datatypes.InnerElement;
import eu.qimpress.samm.datatypes.PrimitiveDataType;
import eu.qimpress.samm.datatypes.XSDPrimitiveDataTypes;
import eu.qimpress.samm.staticstructure.Interface;
import eu.qimpress.samm.staticstructure.MessageType;
import eu.qimpress.samm.staticstructure.Operation;
import eu.qimpress.samm.staticstructure.Parameter;
import eu.qimpress.samm.staticstructure.Repository;
import eu.qimpress.samm.staticstructure.StaticstructureFactory;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorFactory;

/**
 * Builder for operations, parameters, message types, and data types. Keeps the source code
 * decorator updated.
 * @author Michael Hauck, Steffen Becker, Klaus Krogmann
 *
 */
public class OperationBuilder extends AbstractBuilder {
	
	public static final String voidType = "void";

	private static Logger logger = Logger.getLogger(OperationBuilder.class);
	
	public OperationBuilder(Root gastModel,
			SoMoXConfiguration somoxConfiguration, AnalysisResult analysisResult) {
		super(gastModel, somoxConfiguration, analysisResult);
		
	}

	public void createOperations(Type implementationClass, Type interfaceClass, Interface interf) {
		
		for (MethodDeclaration method : KDMHelper.getMethods(interfaceClass)) {
			if ( (KDMHelper.isModifierOfKind(method, VisibilityKind.NONE))
				|| KDMHelper.isModifierOfKind(method,
						VisibilityKind.PUBLIC)) {	
				MethodDeclaration realMethod = method;
				if (implementationClass != null) {
					realMethod = getRealMethod(implementationClass,method);
					if (realMethod == null) {
						realMethod = method;
						logger.error("GAST Model misses a method "+method.getName());
					}
				} else {
					logger.warn("no implementation class for method "+method.getName() + " of interface " + interfaceClass.getName());
				}
				Operation op = createOperationParametersAndMessageType(realMethod);
				interf.getSignatures().add(op);
			}
		}
	}
	
	/**
	 * 
	 * @param implementationClass
	 * @param method interface method
	 * @return null if no implementation method was found; the queried method otherwise
	 */
	private MethodDeclaration getRealMethod(Type implementationClass, MethodDeclaration method) {
		assert implementationClass != null;
 
		for (MethodDeclaration m : KDMHelper.getMethods(implementationClass)) {
			if (m == method)
				return m;
			if (m.getName().equals(method.getName())) {
				MethodDeclaration overrideMethod = (MethodDeclaration) KDMHelper.getOverriddenMember(m);
				while (overrideMethod != null) {
					if (overrideMethod == method)
						return m;
					else 
						overrideMethod = (MethodDeclaration) KDMHelper.getOverriddenMember(overrideMethod);
				}
			}
		}
		for (Type superClass : KDMHelper.getSuperTypes(implementationClass)) {
			if (! KDMHelper.isAbstract(superClass) && ! KDMHelper.isInterface(superClass)) {
				MethodDeclaration real = getRealMethod(superClass, method);
				if (real != null) {
					return real;
				}
			}
		}
		return null;
	}

	/**
	 * Adds MessageTypes to the resultRepository, set parameter names and types.
	 * First looks if a MessageType already exists and creates one only if it does not exist in the repository.
	 * @param method GAST method to add 
	 * @param resultRepository repository to write to
	 * @return a new operation for which parameter names and types already exist in the resultRepository  
	 */
	private Operation createOperationParametersAndMessageType(MethodDeclaration method) {
		
		Operation operation = StaticstructureFactory.eINSTANCE.createOperation();
		operation.setName(method.getName());
		
		updateSourceCodeDecorator(operation,method);
		
		ArrayList<String> paramNames = new ArrayList<String>();
		ArrayList<Type> paramTypes = new ArrayList<Type>();
		for (SingleVariableDeclaration inputParameter : method.getParameters()) {
				
			paramNames.add(inputParameter.getName());
			if(inputParameter.getType() != null && inputParameter.getType().getType() != null) {
				// derive GASTClass from input parameter:
				paramTypes.add(GetAccessedType.getAccessedType(inputParameter.getType()));
			} else {
				logger.error("Input parameter type was null. Could not set the parameter type \"" +
						inputParameter.getName() + "\" of method \"" + method.getName() + "\"");
			}
		}
		if (paramNames.size() > 0) {
			MessageType messageType = findMessageTypeInRepository(paramNames, paramTypes);
			if (messageType == null) {
				messageType = createMessageType(paramNames, paramTypes);
			}
			if(messageType != null) { //newly created message type can still be null
				operation.setInput(messageType);
			}
		}
		
		return operation;
	}

	private void updateSourceCodeDecorator(Operation operation, MethodDeclaration method) {
		//assert method.getStatus() == Status.NORMAL; //TODO: check re-enabling other status implies empty method body and causes trouble during later stages
		
		MethodLevelSourceCodeLink link = SourceCodeDecoratorFactory.eINSTANCE.createMethodLevelSourceCodeLink();
		
		link.setFunction(method);
		link.setOperation(operation);
		
		if(KDMHelper.getJavaNodeSourceRegion(method) != null &&
				KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(method)) != null) {
			link.setFile(KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(method)));
		}
		
		this.analysisResult.getSourceCodeDecoratorRepository().getMethodLevelSourceCodeLink().add(link);
	}

	/**
	 * Look if a message type that contains the parameters specified by name and
	 * type already exists in the repository
	 * 
	 * @return the MessageType. Returns null, if no message type is found, or if
	 *         the size of parameterNames does not equal the size of
	 *         parameterTypes.
	 */
	private MessageType findMessageTypeInRepository(
			List<String> parameterNames, 
			List<Type> parameterTypes) {
		if (parameterNames == null) {
			parameterNames = new ArrayList<String>();
		}
		if (parameterTypes == null) {
			parameterTypes = new ArrayList<Type>();
		}
		if (parameterNames.size() != parameterTypes.size()) {
			return null;
		}
		for (MessageType messageType : this.analysisResult.getInternalArchitectureModel().getMessagetype()) {
			if (messageType.getParameters().size() != parameterNames.size()) {
				continue;
			}
			boolean parametersMatch = true;
			for (int i = 0; i < messageType.getParameters().size(); i++) {
				Parameter param = messageType.getParameters().get(i);
				if (!param.getName().equals(parameterNames.get(i))) {
					parametersMatch = false;
					break;
				}
				if (param.getType() != null && param.getType().getName() != null && //null pointer protection
						parameterTypes.get(i).getName() != null &&
						!param.getType().getName().toLowerCase().equals(
								parameterTypes.get(i).getName().toLowerCase())) {
					parametersMatch = false;
					break;
				}
			}
			if (parametersMatch == true) {
				return messageType;
			}
		}
		return null;
	}

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
	 *         parameterNames does not equal the size of parameterTypes or 
	 *         if only void parameters are present
	 */
	private MessageType createMessageType(List<String> parameterNames,
			List<Type> parameterTypes) {
		if (parameterNames == null) {
			parameterNames = new ArrayList<String>();
		}
		if (parameterTypes == null) {
			parameterTypes = new ArrayList<Type>();
		}
		if (parameterNames.size() != parameterTypes.size()) {
			return null;
		}
		MessageType messageType = StaticstructureFactory.eINSTANCE.createMessageType();
		String messageTypeName = "";
		if (parameterTypes.size() > 0) {
			for (int i = 0; i < parameterTypes.size(); i++) {
				if(!parameterTypes.get(i).getName().equals(voidType)) { //do not create void pointers
				
					if (messageTypeName.length() > 0) {
						messageTypeName += "_";
					}
					messageTypeName += parameterTypes.get(i).getName();
					Parameter param = StaticstructureFactory.eINSTANCE.createParameter();
					param.setName(parameterNames.get(i));
					param.setType(getType(parameterTypes.get(i), this.analysisResult.getInternalArchitectureModel()));
					messageType.getParameters().add(param);
				}
			}
		}
		if(messageType.getParameters().size() > 0) {
			messageType.setName(messageTypeName);
			this.analysisResult.getInternalArchitectureModel().getMessagetype().add(messageType);
			return messageType;
		} else {
			return null; // only void parameters which are omitted
		}

	}

	
	/**
	 * Data type creation or look up for existing data types.
	 * @param typeName type name to create
	 * @param repository repository containing all present types
	 * @return a new data type for non-existing ones; the existing
	 * instance else
	 */
	private eu.qimpress.samm.datatypes.Type getType(Type gastType, Repository repository) {
		eu.qimpress.samm.datatypes.Type type = getExistingType(gastType, repository);
		
		if(type == null) {		
			type = createDataType(repository, gastType);			
		}
		return type;
	}

	/**
	 * Creates a new data type for the given gastType.
	 * @param repository The repository to add the new data type to
	 * @param gastType The type to create a SAMM data type for 
	 * @return
	 */
	private eu.qimpress.samm.datatypes.Type createDataType(Repository repository, Type gastType) {				
		String typeName = gastType.getName();
		typeName = getUnifiedTypeName(typeName);

		eu.qimpress.samm.datatypes.Type newType = null;
		if (typeName.toLowerCase().equals(voidType)) {
			// do nothing
		} else if (typeName.toLowerCase().equals("integer")) {
			newType = DatatypesFactory.eINSTANCE.createPrimitiveDataType();
			newType.setName("INTEGER");
			((PrimitiveDataType) newType).setType(XSDPrimitiveDataTypes.INT);
			repository.getType().add(newType);
		} else if (typeName.toLowerCase().equals("float")) {
			newType = DatatypesFactory.eINSTANCE.createPrimitiveDataType();
			newType.setName("FLOAT");
			((PrimitiveDataType) newType).setType(XSDPrimitiveDataTypes.FLOAT);
			repository.getType().add(newType);
		} else if (typeName.toLowerCase().equals("string")) {
			newType = DatatypesFactory.eINSTANCE.createPrimitiveDataType();
			newType.setName("STRING");
			((PrimitiveDataType) newType).setType(XSDPrimitiveDataTypes.STRING);
			repository.getType().add(newType);
		} else if (typeName.toLowerCase().equals("boolean")) {
			newType = DatatypesFactory.eINSTANCE.createPrimitiveDataType();
			newType.setName("BOOLEAN");
			((PrimitiveDataType) newType).setType(XSDPrimitiveDataTypes.BOOLEAN);
			repository.getType().add(newType);	
		} else if (typeName.endsWith("[]")) {
			// Create a collection data type:			
			newType = DatatypesFactory.eINSTANCE.createCollectionDataType();

			newType.setName(typeName);
			repository.getType().add(newType);
			logger.debug("found collection type " + typeName);
			// set inner type:
			String tmpInnerTypeName = typeName.substring(0, typeName.length() - 2);
			((CollectionDataType) newType).setInnertype(getExistingTypeByName(tmpInnerTypeName, repository));			
		} else {		
			if(KDMHelper.getAllAccessedClasses(gastType).size() > 1) {
				// create a complex data type:
				newType = DatatypesFactory.eINSTANCE.createComplexDataType();
				newType.setName(typeName);
				repository.getType().add(newType);
				
				// set inner types:			
				for(Type currentClass : KDMHelper.getAllAccessedClasses(gastType)) {
					// avoid self-references and void as access
					if(!currentClass.equals(gastType) && !currentClass.getName().equals("void")) {
						String tmpInnerTypeName = currentClass.getName();;
						InnerElement innerElement = DatatypesFactory.eINSTANCE.createInnerElement();						
						innerElement.setType(getType(currentClass, repository));
						innerElement.setName(tmpInnerTypeName);
						((ComplexDataType) newType).getElements().add(innerElement);
					}
				}
			} else {
				// create a non-default primitive data type:
				newType = DatatypesFactory.eINSTANCE.createPrimitiveDataType();
				newType.setName(typeName);
				repository.getType().add(newType);				
			}
		}
		
		return newType;
	}
	
	/**
	 * Reduces comparable types to a single type and copes with potentially different
	 * naming of the same type.
	 * @param typeName
	 * @return
	 */
	private String getUnifiedTypeName(String typeName) {
		if (typeName.toLowerCase().equals("int") || typeName.toLowerCase().equals("long")) {
			// Do not create 2 datatypes for int and integer
			// maps int and long to integer
			typeName = "integer";
		} else if (typeName.toLowerCase().equals("bool")) { 
			// Do not create 2 datatypes for bool and boolean
			typeName = "boolean";
		} else if (typeName.toLowerCase().equals("char")) {
			typeName = "string"; // map char to string
		} else if (typeName.toLowerCase().equals("double")) {
			typeName = "float"; // map double to float
		}
		return typeName;
	}	

	/**
	 * 
	 * @param gastType
	 * @param repository
	 * @return null if not found
	 */
	private eu.qimpress.samm.datatypes.Type getExistingType(Type gastType, Repository repository) {
		return getExistingTypeByName(gastType.getName(), repository);
	}
	
	/**
	 * 
	 * @param gastTypeName
	 * @param repository
	 * @return null if not found
	 */
	private eu.qimpress.samm.datatypes.Type getExistingTypeByName(String gastTypeName, Repository repository) {
		gastTypeName = getUnifiedTypeName(gastTypeName);
		
		for (eu.qimpress.samm.datatypes.Type currentType : repository.getType()) {
			if (currentType.getName().toLowerCase().equals(gastTypeName.toLowerCase())) {
				return currentType;
			}
		}
		
		logger.info("no type found for " + gastTypeName);
		return null;
	}

}
