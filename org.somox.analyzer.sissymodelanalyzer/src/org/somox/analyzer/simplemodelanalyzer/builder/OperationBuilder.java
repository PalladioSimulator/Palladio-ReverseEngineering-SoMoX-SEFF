package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.arrays.ArrayTypeable;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.generics.QualifiedTypeArgument;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.variables.Variable;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.simplemodelanalyzer.builder.util.DefaultResourceEnvironment;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.DataTypeSourceCodeLink;
import org.somox.sourcecodedecorator.InnerDatatypeSourceCodeLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

import org.palladiosimulator.pcm.core.entity.NamedElement;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;

/**
 * Builder for operations, parameters, message types, and data types. Keeps the source code
 * decorator updated.
 *
 * @author Michael Hauck, Steffen Becker, Klaus Krogmann
 *
 */
public class OperationBuilder extends AbstractBuilder {

    private static Logger logger = Logger.getLogger(OperationBuilder.class);
    private CompositeDataType objectDataType;
    SourceCodeDecoratorRepository sourceCodeDecorator;

    public OperationBuilder(final Root gastModel, final SoMoXConfiguration somoxConfiguration,
            final AnalysisResult analysisResult) {
        super(gastModel, somoxConfiguration, analysisResult);
        this.sourceCodeDecorator = analysisResult.getSourceCodeDecoratorRepository();
    }

    public void createOperations(final ConcreteClassifier implementationClass, final ConcreteClassifier interfaceClass,
            final OperationInterface interf) {
        final List<Method> methods = KDMHelper.getMethods(interfaceClass);
        for (final Method method : methods) {
            if (method.isPublic()) {
                Method realMethod = method;
                if (implementationClass != null) {
                    realMethod = this.getRealMethod(implementationClass, method);
                    if (realMethod == null) {
                        realMethod = method;
                        logger.error("GAST Model misses a method " + method.getName());
                    }
                } else {
                    logger.warn("no implementation class for method " + method.getName() + " of interface "
                            + KDMHelper.getName(interfaceClass));
                }
                final OperationSignature op = this.createOperationSignature(realMethod, interf);
                interf.getSignatures__OperationInterface().add(op);
            }
        }
    }

    /**
     *
     * @param implementationClass
     * @param inputMethod
     *            interface method
     * @return null if no implementation method was found; the queried method otherwise
     */
    private Method getRealMethod(final ConcreteClassifier implementationClass, final Method inputMethod) {
        assert implementationClass != null;

        for (final Method methodFromClass : KDMHelper.getMethods(implementationClass)) {

            if (methodFromClass == inputMethod) {
                return methodFromClass;
            }

            if (methodFromClass.getName().equals(inputMethod.getName())) {
                // TODO burkha 23.5.2013 getOverriddenMember does not work
                // correct in contrast to SISSy
                Method overrideMethod = KDMHelper.getOverriddenASTNode(methodFromClass);
                while (overrideMethod != null) {
                    if (overrideMethod == inputMethod) {
                        return methodFromClass;
                    } else {
                        overrideMethod = KDMHelper.getOverriddenASTNode(overrideMethod);
                    }
                }
            }
        }
        for (final ConcreteClassifier superClass : KDMHelper.getSuperTypes(implementationClass)) {
            if (!KDMHelper.isAbstract(superClass) && !KDMHelper.isInterface(superClass)
                    && superClass instanceof org.emftext.language.java.classifiers.Class) {
                final Method real = this.getRealMethod(superClass, inputMethod);
                if (real != null) {
                    return real;
                }
            }
        }
        return null;
    }

    /**
     * Adds MessageTypes to the resultRepository, set parameter names and types. First looks if a
     * MessageType already exists and creates one only if it does not exist in the repository.
     *
     * @param method
     *            GAST method to add
     * @param interf
     * @return a new operation for which parameter names and types already exist in the
     *         resultRepository
     */
    private OperationSignature createOperationSignature(final Method method, final OperationInterface interf) {

        final OperationSignature operation = RepositoryFactory.eINSTANCE.createOperationSignature();
        final String nameForMethod = this.createNonExistingNameInInterface(method, interf);
        operation.setEntityName(nameForMethod);

        this.updateSourceCodeDecorator(operation, method);
        // Variable statt SingleVariableDeclaration
        logger.info("processing input params for " + method.getName() + "; #params: " + method.getParameters().size()); // PDF
        for (final Variable inputParameter : method.getParameters()) {
            final Parameter opSigParam = RepositoryFactory.eINSTANCE.createParameter();
            opSigParam.setParameterName(inputParameter.getName());
            // inputParameter.getTypeReference() statt inputParameter.getType()

            final Type accessedType = GetAccessedType.getAccessedType(inputParameter.getTypeReference());
            if (inputParameter.getTypeReference() != null && null != accessedType) {
                final DataType type = this.getType(accessedType, this.analysisResult.getInternalArchitectureModel(),
                        inputParameter);

                opSigParam.setDataType__Parameter(type);
                logger.info("type to build for variable: " + inputParameter + ":" + type);
            } else {
                logger.error("Input parameter type was null. Could not set the parameter type \""
                        + inputParameter.getName() + "\" of method \"" + method.getName() + "\"");
                // if(inputParameter.getTypeReference() == null)
                // logger.error("Reason: type reference is null");//PDF
                // if(accessedType == null) logger.error("Reason: accessed type is null ");//PDF
                // logger.error(inputParameter.getTypeReference() + " and " +
                // inputParameter.getTypeReference().getTarget()); //PDF
                continue;
            }
            opSigParam.setOperationSignature__Parameter(operation);
        }

        if (null != method.getTypeReference() && null != GetAccessedType.getAccessedType(method.getTypeReference())
                && !(method.getTypeReference() instanceof org.emftext.language.java.types.Void)) {
            final DataType returnType = this.getType(GetAccessedType.getAccessedType(method.getTypeReference()),
                    this.analysisResult.getInternalArchitectureModel(), method);
            operation.setReturnType__OperationSignature(returnType);
        } else if (null != method.getTypeReference()
                && !(method.getTypeReference() instanceof org.emftext.language.java.types.Void)) {
            final Type accessedType = GetAccessedType.getAccessedType(method.getTypeReference());
            final DataType type = this
                    .getType(accessedType, this.analysisResult.getInternalArchitectureModel(), method);
            operation.setReturnType__OperationSignature(type);
        } else {
            logger.info("no fitting return type found " + method.getName() + "-- ret type" + method.getTypeReference());
        }

        return operation;
    }

    private String createNonExistingNameInInterface(final Method method, final OperationInterface interf) {
        final String methodName = method.getName();
        if (!this.containsName(interf, methodName)) {
            return methodName;
        }

        int counter = 1;

        while (this.containsName(interf, this.createMethodNameWithNumber(methodName, counter))) {
            counter++;
        }
        return this.createMethodNameWithNumber(methodName, counter);

    }

    private String createMethodNameWithNumber(final String methodName, final int counter) {
        return methodName + "_" + counter;
    }

    private boolean containsName(final OperationInterface interf, final String methodName) {
        final EList<OperationSignature> signatures = interf.getSignatures__OperationInterface();
        for (final OperationSignature signature : signatures) {
            if (signature.getEntityName().equals(methodName)) {
                return true;
            }
        }
        return false;
    }

    private void updateSourceCodeDecorator(final OperationSignature operation, final Method method) {
        // assert method.getStatus() == Status.NORMAL; //TODO: check re-enabling
        // other status implies empty method body and causes trouble during
        // later stages

        final MethodLevelSourceCodeLink link = SourcecodedecoratorFactory.eINSTANCE.createMethodLevelSourceCodeLink();

        link.setFunction(method);
        link.setOperation(operation);

        if (KDMHelper.getJavaNodeSourceRegion(method) != null) {
            link.setFile(KDMHelper.getJavaNodeSourceRegion(method));
        }

        this.analysisResult.getSourceCodeDecoratorRepository().getMethodLevelSourceCodeLink().add(link);
    }

    /**
     * Look if a message type that contains the parameters specified by name and type already exists
     * in the repository
     *
     * @return the MessageType. Returns null, if no message type is found, or if the size of
     *         parameterNames does not equal the size of parameterTypes.
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
    // org.palladiosimulator.pcm.repository.Parameter param =
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
     *            the type names of the parameter. SAMM types are created (if they do not already
     *            exist) for these types
     * @param repository
     *            the Repository in which the MessageType has to be stored
     * @return the created message type. Returns null if the size of parameterNames does not equal
     *         the size of parameterTypes or if only void parameters are present
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
     * @param arrayDimensions
     *            array dimensions for the type
     * @return a new data type for non-existing ones; the existing instance else
     */
    public DataType getType(final Type gastType, final Repository repository, final ArrayTypeable arrayTypeable) {
        DataType innerType = this.getExistingType(gastType, repository);

        if (innerType == null) {
            innerType = this.createDataType(repository, gastType);
        }
        DataType returnType = innerType;

        if (null != arrayTypeable && null != returnType) {
            final String unifiedListName = this.getUnifiedTypeName(KDMHelper.getName(gastType)) + "List";
            final DataType exisingCollection = this.getExistingTypeByName(unifiedListName, repository);
            if (null == exisingCollection) {
                returnType = this.createCollectionDatatypeForArray(innerType, arrayTypeable, repository);
            } else {
                returnType = exisingCollection;
            }
        }

        return returnType;
    }

    /**
     * Creates a collection data type for each array dimension for the inner Data Type. For example:
     *
     * String[] --> CollectionDataType with String as InnerType
     *
     * String[][]--> CollectionDataType with "CollectionDataType with String as inner type" as inner
     * type
     *
     * @param innerDataType
     *            the innertype
     * @param arrayTypeable
     *            the element that may is an array
     * @return
     */
    private DataType createCollectionDatatypeForArray(final DataType innerDataType, final ArrayTypeable arrayTypeable,
            final Repository repository) {
        DataType currentInnerType = innerDataType;
        // can not use arrayTypeable.getArrayDimension() since it is not implemented in JaMoPP
        final int arrayDimenstions = arrayTypeable.getArrayDimensionsAfter().size()
                + arrayTypeable.getArrayDimensionsBefore().size();
        for (long i = 0; i < arrayDimenstions; i++) {
            final CollectionDataType collectionDataType = RepositoryFactory.eINSTANCE.createCollectionDataType();
            collectionDataType.setEntityName(this.getNameFromPCMDataType(currentInnerType) + "List");
            collectionDataType.setInnerType_CollectionDataType(currentInnerType);
            collectionDataType.setRepository__DataType(repository);
            currentInnerType = collectionDataType;
        }
        return currentInnerType;
    }

    private String getNameFromPCMDataType(final DataType dataType) {
        if (dataType instanceof NamedElement) {
            return ((NamedElement) dataType).getEntityName();
        }
        if (dataType instanceof PrimitiveDataType) {
            final PrimitiveDataType primitiveDataType = (PrimitiveDataType) dataType;
            return primitiveDataType.getType().getName();
        }
        return null;
    }

    /**
     * Creates a new PCM data type for the given gastType. Note: in order to not return null the
     * method implements a fall backmethod: if no type could be created (which actually should not
     * happen) it just creates an empty CompositeDataType with the name of the type. If we even can
     * not determine the name of the datatype we just return the generic type (java.lang.)Object. It
     * also creates an entry for in the sourceCodeDecorator if its possible
     *
     * @param repository
     *            The repository to add the new data type to
     * @param gastType
     *            The type to create a PCM data type for
     * @return the newly created PCM data type
     */
    private DataType createDataType(final org.palladiosimulator.pcm.repository.Repository repository, final Type gastType) {
        if (null == gastType) {
            return this.returnDefaultDataType(gastType, repository);
        }
        String typeName = KDMHelper.getName(gastType);
        if (null == typeName) {
            return this.returnDefaultDataType(gastType, repository);
        }
        if (typeName.equals("void")) {
            return null;
        }
        DataType newType = null;
        typeName = this.getUnifiedTypeName(typeName);
        newType = this.checkAndCreatePrimitiveDataType(typeName);
        if (null != newType) {
            // do not create source code decorator entry for prmitive datatype
            return newType;
        }
        newType = this.checkAndCreateComplexDataType(gastType, typeName, repository);
        if (null != newType) {
            return newType;
        }
        logger.warn("Datatype " + gastType + " with name " + typeName + " is neither a primitive nor a composite "
                + "nor a collection datatype. Creating an empty PCM datatype with name " + typeName + " for it.");
        newType = RepositoryFactory.eINSTANCE.createCompositeDataType();
        ((CompositeDataType) newType).setEntityName(typeName);
        newType.setRepository__DataType(repository);
        return newType;
    }

    /**
     * ComplexDataTypes are extracted as follows: 1) if type is a ConcreteClassifier we generate a
     * complex data type 2) if type is an instance of "Collection": we create a collection datatype
     * 3) if not: create a CompositeDataType 4) innerDataTypes: get fields of classifier and their
     * type
     *
     *
     * @param gastType
     *            the actual type
     * @param typeName
     *            the name of the type
     * @param repository
     *            the containing repository
     * @return
     */
    private DataType checkAndCreateComplexDataType(final Type gastType, final String typeName,
            final Repository repository) {
        DataType complexDataType = null;
        // 1)
        if (gastType instanceof ConcreteClassifier) {
            final ConcreteClassifier concreteClassifier = (ConcreteClassifier) gastType;
            // 2)+6)
            complexDataType = this.checkAndCreateCollectionDataType(concreteClassifier, typeName, repository);
            if (null == complexDataType) {
                // 3+4)
                complexDataType = this.createCompositeDataType(concreteClassifier, typeName, repository);
            }
        }
        return complexDataType;
    }

    private CompositeDataType createCompositeDataType(final ConcreteClassifier concreteClassifier,
            final String typeName, final Repository repository) {
        final CompositeDataType compositeDataType = RepositoryFactory.eINSTANCE.createCompositeDataType();
        compositeDataType.setEntityName(typeName);
        repository.getDataTypes__Repository().add(compositeDataType);
        // only create sourceCodeDecorator Entry for the classifier and investigate fields if the
        // classifier is in the java-source of the project and not on the blacklist
        final boolean inSource = this.isClassifierInSourceProject(concreteClassifier);
        final boolean isOnBlackList = this.isClassifierOnBlacklist(concreteClassifier);
        if (inSource && !isOnBlackList) {
            final DataTypeSourceCodeLink datatypeSourceCodeLink = this
                    .createSourceCodeDecoratorEntryEntryForClassifier2DataType(concreteClassifier, compositeDataType);
            this.investigateFields(concreteClassifier, typeName, repository, compositeDataType, datatypeSourceCodeLink);
        }
        return compositeDataType;
    }

    private void investigateFields(final ConcreteClassifier concreteClassifier, final String typeName,
            final Repository repository, final CompositeDataType compositeDataType,
            final DataTypeSourceCodeLink datatypeSourceCodeLink) {
        for (final Field field : concreteClassifier.getFields()) {
            // set inner types:
            if (null == field.getTypeReference()) {
                continue;
            }
            final Type fieldType = field.getTypeReference().getTarget();
            if (null == fieldType) {
                continue;
            }
            final String fieldTypeName = KDMHelper.getName(fieldType);
            // avoid self-references and void as access
            if (!fieldType.equals(concreteClassifier) && !fieldTypeName.equals(typeName)
                    && !fieldTypeName.equals("void")) {
                final InnerDeclaration innerElement = RepositoryFactory.eINSTANCE.createInnerDeclaration();
                final DataType innerDataType = this.getType(fieldType, repository, field);
                final String innerTypeName = field.getName();
                innerElement.setEntityName(innerTypeName);
                if (innerDataType instanceof CollectionDataType) {
                    // 6) the inner data type is a generic collection data type, e.g. ArrayList:
                    // create a copy with the concrete innertype e.g. ArrayList<String>
                    final CollectionDataType concreteCollectionDataType = RepositoryFactory.eINSTANCE
                            .createCollectionDataType();
                    concreteCollectionDataType.setEntityName(((CollectionDataType) innerDataType).getEntityName() + "_"
                            + innerTypeName);
                    concreteCollectionDataType.setRepository__DataType(repository);
                    innerElement.setDatatype_InnerDeclaration(concreteCollectionDataType);
                    final QualifiedTypeArgument qta = this.getFirstChildWithType(field, QualifiedTypeArgument.class);
                    if (null != qta) {
                        if (null != qta.getTypeReference() && null != qta.getTypeReference().getTarget()) {
                            final Type type = qta.getTypeReference().getTarget();
                            final DataType collectionInnerType = this.getType(type, repository, qta);
                            concreteCollectionDataType.setInnerType_CollectionDataType(collectionInnerType);
                        }
                    }
                    logger.debug("created inner collection datatype composite data type " + innerTypeName);
                } else {
                    innerElement.setDatatype_InnerDeclaration(innerDataType);
                    logger.debug("created inner element" + innerElement.getEntityName());
                }
                this.createSourceCodeDecoratorEntryForField2InnerDeclaration(field, innerElement,
                        datatypeSourceCodeLink);
                compositeDataType.getInnerDeclaration_CompositeDataType().add(innerElement);
            }
        }
    }

    private boolean isClassifierOnBlacklist(final ConcreteClassifier concreteClassifier) {
        if (null == this.somoxConfiguration || null == this.somoxConfiguration.getBlacklistFilter()) {
            // if we do not have a somox configuration or a blacklist filter we assume that the
            // classifier is not on the blacklist
            return false;
        }
        return !super.somoxConfiguration.getBlacklistFilter().passes(concreteClassifier);
    }

    private boolean isClassifierInSourceProject(final ConcreteClassifier concreteClassifier) {
        for (final CompilationUnit cu : this.astModel.getCompilationUnits()) {
            for (final ConcreteClassifier currentClassifier : cu.getChildrenByType(ConcreteClassifier.class)) {
                if (concreteClassifier == currentClassifier) {
                    return true;
                }
            }
        }
        return false;
    }

    private void createSourceCodeDecoratorEntryForField2InnerDeclaration(final Field field,
            final InnerDeclaration innerDeclaration, final DataTypeSourceCodeLink datatypeSourceCodeLink) {
        final InnerDatatypeSourceCodeLink innerTypeDecorator = SourcecodedecoratorFactory.eINSTANCE
                .createInnerDatatypeSourceCodeLink();
        innerTypeDecorator.setField(field);
        innerTypeDecorator.setInnerDeclaration(innerDeclaration);
        datatypeSourceCodeLink.getInnerDatatypeSourceCodeLink().add(innerTypeDecorator);
    }

    private DataTypeSourceCodeLink createSourceCodeDecoratorEntryEntryForClassifier2DataType(
            final ConcreteClassifier concreteClassifier, final DataType compositeDataType) {
        final DataTypeSourceCodeLink dataTypeSourceCodeLink = SourcecodedecoratorFactory.eINSTANCE
                .createDataTypeSourceCodeLink();
        dataTypeSourceCodeLink.setFile(concreteClassifier.getContainingCompilationUnit());
        dataTypeSourceCodeLink.setJaMoPPType(concreteClassifier);
        dataTypeSourceCodeLink.setPcmDataType(compositeDataType);
        this.sourceCodeDecorator.getDataTypeSourceCodeLink().add(dataTypeSourceCodeLink);
        return dataTypeSourceCodeLink;
    }

    private <T> T getFirstChildWithType(final Commentable commentable, final Class<T> classType) {
        final EList<T> children = commentable.getChildrenByType(classType);
        if (null != children && 0 < children.size()) {
            return children.get(0);
        }
        return null;
    }

    /**
     * Creates a CollectionDataType if the concreteClassifier is an instance of "Collection". We do
     * not use any inner type here. If the type has an inner type the type will be created in
     * createCompositeDatatype
     *
     * @param gastType
     * @param typeName
     * @param repository
     * @return
     */
    private CollectionDataType checkAndCreateCollectionDataType(final ConcreteClassifier concreteClassifier,
            final String typeName, final Repository repository) {
        CollectionDataType collectionDataType = null;
        if (this.extendsJavaLangCollection(concreteClassifier)) {
            collectionDataType = RepositoryFactory.eINSTANCE.createCollectionDataType();
            collectionDataType.setEntityName(concreteClassifier.getName());
            collectionDataType.setRepository__DataType(repository);
            this.createSourceCodeDecoratorEntryEntryForClassifier2DataType(concreteClassifier, collectionDataType);
        }
        return collectionDataType;
    }

    private boolean extendsJavaLangCollection(final Type type) {
        if (type instanceof ConcreteClassifier) {
            final ConcreteClassifier concreteClassifier = (ConcreteClassifier) type;
            for (final ConcreteClassifier superClassifier : concreteClassifier.getAllSuperClassifiers()) {
                if (superClassifier.getName().equals("Collection")
                        || superClassifier.getName().equals("java.lang.Collection")) {
                    return true;
                }
            }
        }
        return false;
    }

    private DataType checkAndCreatePrimitiveDataType(final String typeName) {
        if (typeName.equalsIgnoreCase("void")) {
            // do nothing
        } else if (typeName.equalsIgnoreCase("integer")) {
            return DefaultResourceEnvironment.getPrimitiveDataTypeInteger();
        } else if (typeName.equalsIgnoreCase("double")) {
            return DefaultResourceEnvironment.getPrimitiveDataTypeDouble();
        } else if (typeName.equalsIgnoreCase("string")) {
            return DefaultResourceEnvironment.getPrimitiveDataTypeString();
        } else if (typeName.equalsIgnoreCase("boolean")) {
            return DefaultResourceEnvironment.getPrimitiveDataTypeBool();
        } else if (typeName.equalsIgnoreCase("char")) {
            return DefaultResourceEnvironment.getPrimitiveDataTypeChar();
        } else if (typeName.equalsIgnoreCase("byte")) {
            return DefaultResourceEnvironment.getPrimitiveDataTypeByte();
            // PDF27.11: added java String data type as PCM-Repo composite datatype TODO: not
            // working?
        } else if (typeName.equals("String") || typeName.equals("java.lang.String")) {
            return DefaultResourceEnvironment.getPrimitiveDataTypeString();
        }
        return null;
    }

    /**
     * Reduces comparable types to a single type and copes with potentially different naming of the
     * same type.
     *
     * @param typeName
     * @return
     */
    private String getUnifiedTypeName(String typeName) {
        if (null == typeName) {
            return null;
        }
        if (typeName.toLowerCase().equals("int") || typeName.toLowerCase().equals("long")
                || typeName.toLowerCase().equals("short")) {
            // Do not create 2 datatypes for int and integer
            // maps int and long as well as short to integer
            typeName = "integer";
        } else if (typeName.toLowerCase().equals("bool")) {
            // Do not create 2 datatypes for bool and boolean
            typeName = "boolean";
        } else if (typeName.toLowerCase().equals("char")) {
            typeName = "char";
        } else if (typeName.toLowerCase().equals("float")) {
            typeName = "double"; // map double to float
        } else if (typeName.equals("String")) {
            typeName = "STRING";
        }
        return typeName;
    }

    public DataType returnDefaultDataType(final Commentable type, final Repository repository) {
        logger.warn("could not determine type name for type: " + type + " returning default object datatype)");
        if (null == this.objectDataType) {
            this.objectDataType = RepositoryFactory.eINSTANCE.createCompositeDataType();
            this.objectDataType.setEntityName("Object");
            this.objectDataType.setRepository__DataType(repository);
        }
        return this.objectDataType;
    }

    /**
     *
     * @param gastType
     * @param repository
     * @return null if not found
     */
    private DataType getExistingType(final Type gastType, final org.palladiosimulator.pcm.repository.Repository repository) {
        return this.getExistingTypeByName(KDMHelper.getName(gastType), repository);
    }

    /**
     *
     * @param gastTypeName
     * @param repository
     * @return the found data type null if not found
     */
    private DataType getExistingTypeByName(String gastTypeName,
            final org.palladiosimulator.pcm.repository.Repository repository) {
        if (null == gastTypeName) {
            logger.warn("Type name is null. Could not get an exisiting data type.");
            return null;
        }
        gastTypeName = this.getUnifiedTypeName(gastTypeName);
        // TODO: use hash map to look up instead of iterating over all datatypes
        for (final DataType currentType : repository.getDataTypes__Repository()) {
            String pcmTypeName = null;
            if (currentType instanceof CompositeDataType) {
                pcmTypeName = ((CompositeDataType) currentType).getEntityName();

            } else if (currentType instanceof CollectionDataType) {
                pcmTypeName = ((CollectionDataType) currentType).getEntityName();
            } else if (currentType instanceof PrimitiveDataType) {
                pcmTypeName = ((PrimitiveDataType) currentType).getType().getName();
            }
            if (gastTypeName.equals(pcmTypeName)) {
                return currentType;
            }
        }
        logger.trace("no type found for " + gastTypeName + ". Type will be created.");
        return null;
    }
}
