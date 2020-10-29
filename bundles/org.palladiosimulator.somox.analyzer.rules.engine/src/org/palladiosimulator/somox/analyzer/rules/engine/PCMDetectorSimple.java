package org.palladiosimulator.somox.analyzer.rules.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.containers.impl.CompilationUnitImpl;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.variables.Variable;

public class PCMDetectorSimple {
    private static List<CompilationUnitImpl> components = new ArrayList<>();

    private static Map<String, List<ProvidesRelation>> providedRelations = new HashMap<>();

    private static Map<String, List<Variable>> requiredInterfaces = new HashMap<>();

    private static List<Classifier> operationInterfaces = new ArrayList<>();

    private static String getFullUnitName(CompilationUnitImpl unit) {
        return unit.getNamespacesAsString() + unit.getName();
    }

    public static void detectComponent(CompilationUnitImpl unit) {
        for (final ConcreteClassifier classi : unit.getClassifiers()) {
            if ((classi instanceof Class) || (classi instanceof Interface)) {
                components.add(unit);
            }
        }
    }

    public static void detectOperationInterface(CompilationUnitImpl unit) {
        // TODO: when not abstract: create a new one with name "I_name"
        for (final ConcreteClassifier classi : unit.getClassifiers()) {
            if ((classi instanceof Class) || (classi instanceof Interface)) {
                operationInterfaces.add(classi);
            }
        }

    }

    public static void detectOperationInterface(Interface in) {
        operationInterfaces.add(in);
    }

    // comp <unit> requires interface <requI> via variable <v>
    public static void detectRequiredInterface(CompilationUnitImpl unit, Variable v) {
        final String unitName = getFullUnitName(unit);
        v.getTypeReference().getPureClassifierReference().getTarget();
        if (requiredInterfaces.get(unitName) == null) {
            final List<Variable> fields = new ArrayList<>();
            requiredInterfaces.put(unitName, fields);
        }
        requiredInterfaces.get(unitName).add(v);

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

}