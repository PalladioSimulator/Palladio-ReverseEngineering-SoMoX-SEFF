package org.palladiosimulator.somox.analyzer.rules.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.containers.impl.CompilationUnitImpl;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.variables.Variable;

/**
* This class is used to detect and hold all relevant elements found during the processing of rules.
* It provides methods to detect and retrieve PCM elements.
* After all rules are parsed, this class holds the results as "simple" java objects not yet transformed to real PCM objects like PCM Basic Components.
*/
public class PCMDetectorSimple {
    private static List<CompilationUnitImpl> components = new ArrayList<>();

    private static Map<String, List<ProvidesRelation>> providedRelations = new HashMap<>();

    private static Map<String, List<Variable>> requiredInterfaces = new HashMap<>();

    private static List<Classifier> operationInterfaces = new ArrayList<>();
    
    private static Set<String> interfaceNames = new HashSet<>();

    private static String getFullUnitName(CompilationUnitImpl unit) {
        return unit.getNamespacesAsString() + "." +unit.getName();
    }

    public static void detectComponent(CompilationUnitImpl unit) {
        for (final ConcreteClassifier classi : unit.getClassifiers()) {
            if ((classi instanceof Class) || (classi instanceof Interface)) {
                components.add(unit);
            }
        }
    }

    public static void detectOperationInterface(CompilationUnitImpl unit) {
        for (final ConcreteClassifier classi : unit.getClassifiers()) {
            detectOperationInterface(classi);
        }

    }
    
    private static void detectOperationInterface(Classifier classifier) {
    	if(interfaceNames.contains(classifier.getName())) {
    		return;
    	}
    	if ((classifier instanceof Class) || (classifier instanceof Interface)) {
            operationInterfaces.add(classifier);
            interfaceNames.add(classifier.getName());
        }
    }

    public static void detectOperationInterface(Interface in) {
    	detectOperationInterface((Classifier) in);
    }

    public static void detectRequiredInterface(CompilationUnitImpl unit, Variable v) {
        final String unitName = getFullUnitName(unit);
        if (requiredInterfaces.get(unitName) == null) {
            final List<Variable> fields = new ArrayList<>();
            requiredInterfaces.put(unitName, fields);
        }
        requiredInterfaces.get(unitName).add(v);
        Classifier currentClassi = v.getTypeReference().getPureClassifierReference().getTarget();
        detectOperationInterface(currentClassi);

    }

    public static void detectProvidedInterface(CompilationUnitImpl unit, Method method) {
        detectProvidedInterface(unit, unit.getClassifiers().get(0), method);
    }

    public static void detectProvidedInterface(CompilationUnitImpl unit, Classifier opI, Method method) {
        final String unitName = getFullUnitName(unit);
        final ProvidesRelation relation = new ProvidesRelation(opI, method);
        if (providedRelations.get(unitName) == null) {
            providedRelations.put(unitName, new ArrayList<ProvidesRelation>());
        }
        providedRelations.get(unitName).add(relation);

    }

    protected static List<CompilationUnitImpl> getComponents() {
        return components;
    }

    protected static List<ProvidesRelation> getProvidedInterfaces(CompilationUnitImpl unit) {
        final String name = getFullUnitName(unit);
        if (providedRelations.get(name) == null) {
            return new ArrayList<>();
        }
        return providedRelations.get(name);
    }

    protected static List<Variable> getRequiredInterfaces(CompilationUnitImpl unit) {
        final String name = getFullUnitName(unit);
        if (requiredInterfaces.get(name) == null) {
            return new ArrayList<>();
        }
        return requiredInterfaces.get(name);
    }

    protected static List<Classifier> getOperationInterfaces() {
        return operationInterfaces.stream().distinct().collect(Collectors.toList());
    }
    
    public static void showInsides() {
    	System.out.println("\ncomps:");
    	components.forEach(comp->{
    		System.out.println(comp.getNamespacesAsString()+"."+comp.getName());
    	});
    	System.out.println("\ninters");
    	operationInterfaces.forEach(op->System.out.println(op.getName()));
    	System.out.println("\nprovide things:");
    	providedRelations.entrySet().forEach(entry->{
    		System.out.println("key: "+entry.getKey());
    		entry.getValue().forEach(value->System.out.println(value));
    	});
    	System.out.println("\nrequire things:");
    	requiredInterfaces.entrySet().forEach(entry->{
    		System.out.println("key: "+entry.getKey());
    		entry.getValue().forEach(value->System.out.println(value));
    	});
    }

}