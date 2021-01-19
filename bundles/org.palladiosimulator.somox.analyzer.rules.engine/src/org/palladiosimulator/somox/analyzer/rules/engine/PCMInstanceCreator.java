package org.palladiosimulator.somox.analyzer.rules.engine;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.containers.impl.CompilationUnitImpl;
import org.emftext.language.java.generics.QualifiedTypeArgument;
import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.types.Boolean;
import org.emftext.language.java.types.Byte;
import org.emftext.language.java.types.Char;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.Double;
import org.emftext.language.java.types.Float;
import org.emftext.language.java.types.Int;
import org.emftext.language.java.types.Long;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.Short;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;
import org.emftext.language.java.types.Void;
import org.emftext.language.java.variables.Variable;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.ParameterModifier;
import org.palladiosimulator.pcm.repository.Repository;

import apiControlFlowInterfaces.Repo;
import factory.FluentRepositoryFactory;
import repositoryStructure.components.BasicComponentCreator;
import repositoryStructure.interfaces.OperationInterfaceCreator;
import repositoryStructure.interfaces.OperationSignatureCreator;
import repositoryStructure.internals.Primitive;
import repositoryStructure.types.CompositeDataTypeCreator;

// Class to create a pcm instance out of all results from the detector class
public class PCMInstanceCreator {

	private final static String REPO_NAME = "Software Architecture Repository";
    private static final FluentRepositoryFactory create = new FluentRepositoryFactory();
    private static Repo repository = create.newRepository().withName(REPO_NAME);
    private static Map<String, CompositeDataTypeCreator> existingDataTypesMap;
    private static Map<String, DataType> existingCollectionDataTypes;

    /**
    * Returns a PCM Repository model. It first creates the interfaces, then the components.
    *
    * @param  mapping  	a mapping between microservice names and java model instances
    * @return      		the PCM repository model
    */
    public static Repository createPCM(Map<String, List<CompilationUnitImpl>> mapping) {
        final List<CompilationUnitImpl> components = PCMDetectorSimple.getComponents();
        final List<Classifier> interfaces = PCMDetectorSimple.getOperationInterfaces();
        existingDataTypesMap = new HashMap<>();
        existingCollectionDataTypes = new HashMap<>();
        repository.addToRepository(create.newCompositeDataType().withName("Void"));

        createPCMInterfaces(interfaces);

        createPCMComponents(components);

        Repository repo = repository.createRepositoryNow();

        saveRepository(repo, "/home/nightcrawler/Dokumente/", "meaninglessExample.repository", false);

        return repo;
    }

    private static void createPCMInterfaces(List<Classifier> interfaces) {
        interfaces.forEach(inter -> {
            final ConcreteClassifier concreteInter = (ConcreteClassifier) inter;
            final String interfaceName = concreteInter.getQualifiedName().replaceAll("\\.", "_");

            System.out.println("Current PCM Interface: "+interfaceName);

            OperationInterfaceCreator pcmInterface = create.newOperationInterface().withName(interfaceName);

            for (final Method m : concreteInter.getMethods()) {
                OperationSignatureCreator signature = create.newOperationSignature().withName(m.getName());

                // parameter type
                for (final Parameter p : m.getParameters()) {

                    final TypeReference ref = p.getTypeReference();
                    signature = handleSignatureDataType(signature, p.getName(), p.getTypeReference(), false);
                }

                // Return type: Cast Method Return Type to Variable
                signature = handleSignatureDataType(signature, "", m.getTypeReference(), true);

                pcmInterface.withOperationSignature(signature);
            }

            repository.addToRepository(pcmInterface);
        });
    }

    private static void createPCMComponents(List<CompilationUnitImpl> components) {
        for (final CompilationUnitImpl comp : components) {
            BasicComponentCreator pcmComp = create.newBasicComponent().withName(getCompName(comp));


            final List<ProvidesRelation> providedRelations = PCMDetectorSimple.getProvidedInterfaces(comp);

            Set<ConcreteClassifier> realInterfaces =  providedRelations.stream().map(relation->(ConcreteClassifier) relation.getOperationInterface()).collect(Collectors.toSet());
            for(ConcreteClassifier realInterface : realInterfaces) {
            	pcmComp.provides(create.fetchOfOperationInterface(realInterface.getQualifiedName().replaceAll("\\.", "_")),"dummy name");
            }

            final List<Variable> requiredIs = PCMDetectorSimple.getRequiredInterfaces(comp);
            Set<ConcreteClassifier> requireInterfaces = requiredIs.stream().map(variable -> getConcreteFromVar(variable)).collect(Collectors.toSet());

            for(ConcreteClassifier requInter: requireInterfaces) {
            	pcmComp.requires(create.fetchOfOperationInterface(requInter.getQualifiedName().replaceAll("\\.", "_")),"dummy require name");
            }

            repository.addToRepository(pcmComp);
        }
    }

    private static String getProvidesName(String compName, String opName) {
        return (compName + " provides " + opName);
    }

    private static String getRequiresName(String compName, String opName) {
        return (compName + " requires " + opName);
    }

    private static String getCompName(CompilationUnitImpl comp) {
        return (comp.getNamespacesAsString().replaceAll("\\.", "_") + "_" +comp.getName());
    }

    private static ConcreteClassifier getConcreteFromVar(TypedElement var) {
        return ((ConcreteClassifier) var.getTypeReference().getPureClassifierReference().getTarget());
    }


    private static Primitive convertPrimitive(PrimitiveType primT) {
        if (primT instanceof Boolean) {
            return Primitive.BOOLEAN;
        } else if (primT instanceof Byte) {
            return Primitive.BYTE;
        } else if (primT instanceof Char) {
            return Primitive.CHAR;
        } else if (primT instanceof Double) {
            return Primitive.DOUBLE;
        } else if (primT instanceof Float) {
            return Primitive.DOUBLE;
        } else if (primT instanceof Int) {
            return Primitive.INTEGER;
        } else if (primT instanceof Long) {
            return Primitive.LONG;
        } else if (primT instanceof Short) {
            return Primitive.INTEGER;
        }

        return null;
    }

    // Copied out from fluent api project
    public static void saveRepository(Repository repo, String path, String name, boolean printToConsole) {
		String outputFile = path + name;
		String[] fileExtensions = new String[] { "repository", "xml" };

		// Create File
		ResourceSet rs = new ResourceSetImpl();
		for (String fileext : fileExtensions)
			rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(fileext, new XMLResourceFactoryImpl());

		URI uri = URI.createFileURI(outputFile);

		Resource resource = rs.createResource(uri);
		((ResourceImpl) resource).setIntrinsicIDToEObjectMap(new HashMap<>());

		// Put content to file resource
		resource.getContents().add(repo);

		// Save file
		((XMLResource) resource).setEncoding("UTF-8");
		Map<Object, Object> saveOptions = ((XMLResource) resource).getDefaultSaveOptions();
		saveOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, true);
		saveOptions.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, new ArrayList<>());

		try {
			resource.save(saveOptions);
			if (printToConsole)
				((XMLResource) resource).save(System.out, ((XMLResource) resource).getDefaultSaveOptions());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

    private static OperationSignatureCreator handleSignatureDataType(OperationSignatureCreator signature, String varName, TypeReference var, boolean asReturnType) {

    	// Check if type is a primitive type
    	Primitive prim = handlePrimitive(var);
    	if(prim!=null) {
    		if(asReturnType) {
        		return signature.withReturnType(prim);
        	}
            return signature.withParameter(varName, prim, ParameterModifier.IN);
    	}

    	// Check if type is void (not part of pcm primitives)
    	if(var instanceof Void) {
    		if(asReturnType) {
    			return signature.withReturnType(create.fetchOfDataType("Void"));
    		}
    	}

        // Parameter is a collection
        DataType collectionType = handleCollectionType(var);
        if(collectionType != null) {
        	if(asReturnType) {
        		return signature.withReturnType(collectionType);
        	}
        	return signature.withParameter(varName, collectionType, ParameterModifier.IN);
        }

        // Parameter is Composite Type
        DataType compositeType = handleCompositeType(var);
        if(compositeType != null) {
        	if(asReturnType) {
        		return signature.withReturnType(compositeType);
        	}
            return signature.withParameter(varName,compositeType,ParameterModifier.IN);
        }

        return null;
    }

    private static DataType handleCollectionType(TypeReference ref) {

    	Classifier classifier = ref.getPureClassifierReference().getTarget();

    	if(!isCollectionType(classifier)) {
    		return null;
    	}
    	// name of the collection data type
    	String typeName = classifier.getName();

    	CollectionDataType collectionType = null;

    	for(TypeArgument typeArg : ref.getPureClassifierReference().getTypeArguments()) {
    		if(typeArg instanceof QualifiedTypeArgument) {
    			QualifiedTypeArgument qualiType = (QualifiedTypeArgument) typeArg;
    			String argumentTypeName = qualiType.getTypeReference().getPureClassifierReference().getTarget().getName();
    			String finalCollectionTypeName = typeName+"<"+argumentTypeName+">";

    			if(existingCollectionDataTypes.containsKey(finalCollectionTypeName)) {
    				return existingCollectionDataTypes.get(finalCollectionTypeName);
    			}

    			System.out.println("Current Argument type name: " + argumentTypeName);

    			// Type argument is primitive
    			Primitive primitiveArg = handlePrimitive(qualiType.getTypeReference());
    			if(primitiveArg != null) {
    				collectionType = create.newCollectionDataType(finalCollectionTypeName, primitiveArg);
    			}

    			// Type argument is a collection again
    			DataType collectionArg = handleCollectionType(qualiType.getTypeReference());
    			if(collectionArg != null) {
    				collectionType = create.newCollectionDataType(finalCollectionTypeName, collectionArg);
    			}

    			// Type argument is a composite data type
    			DataType compositeArg = handleCompositeType(qualiType.getTypeReference());
    			if(compositeArg != null) {
    				collectionType = create.newCollectionDataType(finalCollectionTypeName, compositeArg);
    			}

    			existingCollectionDataTypes.put(finalCollectionTypeName, collectionType);
    			repository.addToRepository(collectionType);
    			return collectionType;
    		}
    	}
    	return null;
    }

    private static boolean isCollectionType(Classifier varClassifier) {

    	List<TypeReference> refs = new ArrayList<>();

        if(varClassifier instanceof Class) {


        	Class varClass = (Class)varClassifier;
        	refs = varClass.getImplements();
        }
        else if(varClassifier instanceof Interface) {


        	Interface varInterf = (Interface) varClassifier;
        	if(varInterf.getName().equals("Collection")) {
        		return true;
        	}else {
        		refs = varInterf.getExtends();
        	}
        }

    	for(TypeReference ref: refs) {
    		String interfaceName = ref.getPureClassifierReference().getTarget().getName();

    		if(interfaceName.equals("Collection")) {
    			return true;
    		}
    	}

    	return false;
    }

    private static Primitive handlePrimitive(TypeReference var) {
    	if (var instanceof PrimitiveType) {
    		return convertPrimitive((PrimitiveType) var);
        }
        // Parameter is String, which counts for PCM as Primitive
        if(var.getTarget().toString().contains("(name: String)")){
        	return Primitive.STRING;
        }
    	return null;
    }

    private static DataType handleCompositeType(TypeReference ref) {
    	Classifier classifier = ref.getPureClassifierReference().getTarget();
    	String classifierName = classifier.getName();

    	if(!existingDataTypesMap.containsKey(classifierName)) {
    		//existingDataTypesMap.put(type.getName(), createTypesRecursively(type));
    		existingDataTypesMap.put(classifierName, create.newCompositeDataType().withName(classifierName));
    		repository.addToRepository(existingDataTypesMap.get(classifierName));
    	}

    	return create.fetchOfCompositeDataType(classifierName);
    }

    private static CompositeDataTypeCreator createTypesRecursively(ConcreteClassifier type) {
    	if(existingDataTypesMap.containsKey(type.getName())) {
    		return existingDataTypesMap.get(type.getName());
    	}

    	CompositeDataTypeCreator currentDataType = create.newCompositeDataType().withName(type.getName());
    	for(Field f: type.getFields()) {

    		if(f.getTypeReference() instanceof PrimitiveType) {
    			currentDataType = currentDataType.withInnerDeclaration(f.getName(), convertPrimitive((PrimitiveType) f.getTypeReference()));
    		}
    		else if(f.getTypeReference().getTarget().toString().equals("String")) {
    			currentDataType = currentDataType.withInnerDeclaration(f.getName(), Primitive.STRING);
    		}
    		else if(f.getTypeReference().getTarget().toString().equals("List")) {
    			currentDataType = currentDataType.withInnerDeclaration(f.getName(), create.newCollectionDataType(f.getName(), Primitive.BYTE));
    		}
    		else{
    			currentDataType = currentDataType.withInnerDeclaration(f.getName(), createTypesRecursively(getConcreteFromVar(f)).build());
    		}
    	}

    	repository.addToRepository(currentDataType);
    	return currentDataType;
    }

}
