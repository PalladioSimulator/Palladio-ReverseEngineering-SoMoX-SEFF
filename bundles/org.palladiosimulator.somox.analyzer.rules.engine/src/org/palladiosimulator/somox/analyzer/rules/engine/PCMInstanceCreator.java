package org.palladiosimulator.somox.analyzer.rules.engine;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.impl.CompilationUnitImpl;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.types.Boolean;
import org.emftext.language.java.types.Byte;
import org.emftext.language.java.types.Char;
import org.emftext.language.java.types.Double;
import org.emftext.language.java.types.Float;
import org.emftext.language.java.types.Int;
import org.emftext.language.java.types.Long;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.Short;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;
import org.emftext.language.java.types.Void;
import org.emftext.language.java.variables.Variable;
import org.palladiosimulator.pcm.repository.ParameterModifier;
import org.palladiosimulator.pcm.repository.Repository;

import apiControlFlowInterfaces.Repo;
import factory.FluentRepositoryFactory;
import repositoryStructure.components.BasicComponentCreator;
import repositoryStructure.interfaces.OperationInterfaceCreator;
import repositoryStructure.interfaces.OperationSignatureCreator;
import repositoryStructure.internals.Primitive;

// Class to create a pcm instance out of all results from the detector class
// The pcm fluent api can be found at: https://github.com/PalladioSimulator/Palladio-Addons-FluentApiModelGenerator
public class PCMInstanceCreator {

    private static final FluentRepositoryFactory create = new FluentRepositoryFactory();
    private static Repo repository = create.newRepository().withName("Software Architecture, " + Instant.now());

    public static Repository createPCM(Map<String, List<CompilationUnitImpl>> mapping) {
        final List<CompilationUnitImpl> components = PCMDetectorSimple.getComponents();
        final List<Classifier> interfaces = PCMDetectorSimple.getOperationInterfaces();

        createPCMInterfaces(interfaces);

        createPCMComponents(components);

        return repository.createRepositoryNow();

    }

    private static void createPCMComponents(List<CompilationUnitImpl> components) {
        for (final CompilationUnitImpl comp : components) {
            BasicComponentCreator pcmComp = create.newBasicComponent().withName(getCompName(comp));

            final List<ProvidesRelation> providedRelations = PCMDetectorSimple.getProvidedInterfaces(comp);
            for (final ProvidesRelation prov : providedRelations) {
                final ConcreteClassifier concreteInter = (ConcreteClassifier) prov.getOperationInterface();
                final String interName = concreteInter.getQualifiedName();
                pcmComp = pcmComp.provides(create.fetchOfOperationInterface(interName),
                        getProvidesName(getCompName(comp), interName));
            }

            final List<Variable> requiredIs = PCMDetectorSimple.getRequiredInterfaces(comp);
            for (final Variable requ : requiredIs) {
                final ConcreteClassifier concreteInter = getConcreteFromVar(requ);
                final String interName = concreteInter.getQualifiedName();
                pcmComp = pcmComp.requires(create.fetchOfOperationInterface(interName),
                        getRequiresName(getCompName(comp), interName));
            }

            repository.addToRepository(pcmComp);
        }
    }

    private static void createPCMInterfaces(List<Classifier> interfaces) {
        interfaces.forEach(inter -> {
            final ConcreteClassifier concreteInter = (ConcreteClassifier) inter;
            final String interfaceName = concreteInter.getQualifiedName();
            OperationInterfaceCreator pcmInterface = create.newOperationInterface().withName(interfaceName);

            for (final Method m : concreteInter.getMethods()) {
                OperationSignatureCreator signature = create.newOperationSignature().withName(m.getName());

                for (final Parameter p : m.getParameters()) {
                    final ConcreteClassifier varInterf = getConcreteFromVar(p);
                    final TypeReference ref = p.getTypeReference();
                    if (ref instanceof PrimitiveType) {
                        signature = signature.withParameter(p.getName(), getPrimitive((PrimitiveType) ref),
                                ParameterModifier.IN);
                    } else {
                        signature = signature.withParameter(p.getName(),
                                create.newCompositeDataType().withName(varInterf.getName()).build(),
                                ParameterModifier.IN);
                    }
                }

                // get return type
                if (m.getTypeReference() instanceof PrimitiveType) {
                    signature = signature.withReturnType(getPrimitive((PrimitiveType) m.getTypeReference()));
                } else {
                    signature = signature.withReturnType(
                            create.newCompositeDataType().withName(getConcreteFromVar(m).getName()).build());
                }

                pcmInterface = pcmInterface.withOperationSignature(signature);
            }

            repository.addToRepository(pcmInterface);
        });
    }

    private static String getProvidesName(String compName, String opName) {
        return (compName + " provides " + opName);
    }

    private static String getRequiresName(String compName, String opName) {
        return (compName + " requires " + opName);
    }

    private static String getCompName(CompilationUnitImpl comp) {
        return (comp.getNamespacesAsString() + comp.getName());
    }

    private static ConcreteClassifier getConcreteFromVar(TypedElement var) {

        return ((ConcreteClassifier) var.getTypeReference().getPureClassifierReference().getTarget());
    }

    private static Primitive getPrimitive(PrimitiveType primT) {
        if (primT instanceof Boolean) {
            return Primitive.BOOLEAN;
        } else if (primT instanceof Byte) {
            return Primitive.BYTE;
        } else if (primT instanceof Char) {
            return Primitive.CHAR;
        } else if (primT instanceof Double) {
            return Primitive.DOUBLE;
        } else if (primT instanceof Float) {
            return Primitive.LONG;
        } else if (primT instanceof Int) {
            return Primitive.INTEGER;
        } else if (primT instanceof Long) {
            return Primitive.LONG;
        } else if (primT instanceof Short) {
            return Primitive.INTEGER;
        } else if (primT instanceof Void) {
            return null;
        }

        return null;
    }

}
