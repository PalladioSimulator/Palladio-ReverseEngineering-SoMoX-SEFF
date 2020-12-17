package org.palladiosimulator.somox.analyzer.rules.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EcorePackage;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.containers.impl.CompilationUnitImpl;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.variables.Variable;

/**
* This class is used as a supporting library for writing rules for the rule engine.
* It contains numerous methods to query a certain state of a java model instance. For example, is a class is annotated with a specific annotation name.
* Also the helper contains methods for retrieving aspects of a class like the interfaces it is implementing.
*/
public class RuleHelper {

    public static boolean isAbstraction(CompilationUnitImpl unit) {
        for (final ConcreteClassifier classi : unit.getClassifiers()) {
            if (classi.eClass().getClassifierID() == ClassifiersPackage.INTERFACE) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUnitAnnotatedWithName(CompilationUnitImpl unit, String... names) {
        for (final String name : names) {
            for (final ConcreteClassifier classi : unit.getClassifiers()) {
                if (isClassifierAnnotatedWithName(classi, name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isObjectAnnotatedWithName(AnnotationInstanceOrModifier mod, String name) {
        if (mod.eClass().getClassifierID() == EcorePackage.EANNOTATION) {
            final AnnotationInstance anno = (AnnotationInstance) mod;

            if ((anno == null) || (anno.getAnnotation() == null)) {
                System.out.println("annotation was null, returning false");
                return false;
            }

            final String annoName = anno.getAnnotation().getName();
            if (annoName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isClassifierAnnotatedWithName(Classifier classifier, String name) {
        if (classifier instanceof ConcreteClassifier) {
            final ConcreteClassifier classi = (ConcreteClassifier) classifier;
            for (final AnnotationInstanceOrModifier mod : classi.getAnnotationsAndModifiers()) {
                return isObjectAnnotatedWithName(mod, name);
            }
        }
        return false;
    }

    public static List<Method> getMethods(CompilationUnitImpl unit) {
        final List<Method> methods = new ArrayList<>();

        for (final ConcreteClassifier classi : unit.getClassifiers()) {
            for (final Member member : classi.getMembers()) {

                if (member instanceof Method) {
                    final Method method = (Method) member;
                    methods.add(method);
                }
            }
        }
        return methods;
    }

    public static List<Method> getMethods(Interface inter) {
        final List<Method> methods = new ArrayList<>();

        for (final Member member : inter.getMembers()) {

            if (member instanceof Method) {
                final Method method = (Method) member;
                methods.add(method);
            }
        }

        return methods;
    }

    public static List<Field> getFields(CompilationUnitImpl unit) {
        final List<Field> fields = new ArrayList<>();

        for (final ConcreteClassifier classi : unit.getClassifiers()) {
            for (final Member member : classi.getMembers()) {

                if (member.eClass().getName().equals("Field")) {
                    final Field field = (Field) member;
                    fields.add(field);
                }
            }
        }

        return fields;
    }

    public static boolean isMethodAnnotatedWithName(Method member, String... names) {
        for (final String name : names) {
            for (final AnnotationInstance a : member.getAnnotationInstances()) {
                if (a.getAnnotation().getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isFieldAbstract(Field field) {
        if (field.getTypeReference() == null) {
            System.out.println("field: " + field.getName() + ", has type reference null");
            System.out.println("=> returning false for isFieldAbstract");
            return false;
        }
        final Type ref = field.getTypeReference().getTarget();

        if (ref instanceof Interface) {
            return true;
        }
        return false;
    }

    public static boolean isParameterAbstract(Parameter p) {
        if (p.getTypeReference().getTarget() instanceof Interface) {
            return true;
        }
        return false;
    }

    public static boolean isParameterAClassAnnotatedWith(Parameter para, String... names) {
        for (final String name : names) {
            final Classifier classi = para.getTypeReference().getPureClassifierReference().getTarget();
            if (isClassifierAnnotatedWithName(classi, name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFieldModifiedExactlyWith(Field field, String... names) {
        return isObjectModifiedExactlyWith(field, names);
    }

    private static boolean isObjectModifiedExactlyWith(AnnotableAndModifiable modi, String... names) {
        final int numberOfModsToDetect = names.length;
        int detectionCounter = 0;
        for (final Modifier m : modi.getModifiers()) {
            final String modifierName = m.getClass().getSimpleName().toLowerCase();
            for (final String name : names) {
                if (modifierName.contains(name.toLowerCase())) {
                    detectionCounter++;
                }
            }
        }
        if (detectionCounter == numberOfModsToDetect) {
            return true;
        }
        return false;
    }

    public static boolean isParameterAnnotatedWith(Parameter p, String name) {
        for (final AnnotationInstanceOrModifier a : p.getAnnotationsAndModifiers()) {
            if (isObjectAnnotatedWithName(a, name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUnitNamedWith(CompilationUnitImpl unit, String name) {
        if ((unit != null) && (unit.getName() != null)) {
            if (unit.getName().contains(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUnitAnEnum(CompilationUnitImpl unit) {
        final Classifier classi = unit.getClassifiers().get(0);
        if (classi instanceof Enumeration) {
            return true;
        }
        return false;
    }

    public static List<Interface> getAllInterfaces(CompilationUnitImpl unit) {
        final List<Interface> interfaces = new ArrayList<>();
        if (unit.getName().contains("LocalDateAttr")) {
            final Class blubi = (Class) unit.getClassifiers().get(0);
            blubi.getImplements().forEach(im -> System.out
                    .println("imp: " + im + ", target: " + im.getPureClassifierReference().getTarget()));
        }
        for (final ConcreteClassifier classifier : unit.getClassifiers()) {
            if (classifier instanceof Class) {
                final Class cl = (Class) classifier;
                final EList<TypeReference> references = cl.getImplements();

                if (references.size() > 0) {
                    for (final TypeReference ref : references) {
                        final Classifier innerClassi = ref.getPureClassifierReference().getTarget();
                        if (innerClassi instanceof Interface) {
                            interfaces.add((Interface) innerClassi);
                        }
                    }
                }
            }
        }
        return interfaces;
    }

    public static boolean isFieldAnnotatedWithName(Field field, String name) {
        for (final AnnotationInstance anno : field.getAnnotationInstances()) {
            if (anno.getAnnotation().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isClassImplementing(CompilationUnitImpl unit) {

        for (final ConcreteClassifier classifier : unit.getClassifiers()) {
            if (classifier instanceof Class) {
                final Class cl = (Class) classifier;
                final EList<TypeReference> references = cl.getImplements();
                if (references.size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isClassExtending(CompilationUnitImpl unit) {
        for (final ConcreteClassifier classifier : unit.getClassifiers()) {
            if (classifier instanceof Class) {
                final Class cl = (Class) classifier;
                final TypeReference reference = cl.getExtends();
                if (reference != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Classifier getExtends(CompilationUnitImpl unit) {
        if (!isClassExtending(unit)) {
            return null;
        }
        final Classifier classi = unit.getClassifiers().get(0);
        if (classi instanceof Class) {
            final Class cl = (Class) classi;
            final TypeReference reference = cl.getExtends();
            return reference.getPureClassifierReference().getTarget();
        }
        return null;
    }

    public static boolean isClassModifiedExactlyWith(CompilationUnitImpl unit, String... names) {
        for (final ConcreteClassifier classi : unit.getClassifiers()) {
            if (classi instanceof Class) {
                return isObjectModifiedExactlyWith(classi, names);
            }
        }
        return false;
    }

    public static boolean isMethodModifiedExactlyWith(Method m, String... names) {
        return isObjectModifiedExactlyWith(m, names);
    }

    public static List<Method> getAllPublicMethods(CompilationUnitImpl unit) {
        return getMethods(unit).stream().filter(Method::isPublic).collect(Collectors.toList());
    }

    public static List<Constructor> getConstructors(CompilationUnitImpl unit) {
        final List<Constructor> constructors = new ArrayList<>();
        for (final ConcreteClassifier classi : unit.getClassifiers()) {
            for (final Member member : classi.getMembers()) {
                if (member instanceof Constructor) {
                    constructors.add((Constructor) member);
                }
            }
        }
        return constructors;
    }

    public static boolean isConstructorAnnotatedWithName(Member member, String name) {
        if (member instanceof Constructor) {
            final Constructor c = (Constructor) member;
            for (final AnnotationInstance anno : c.getAnnotationInstances()) {
                if (anno.getAnnotation().getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isClassOfFieldAnnotatedWithName(Variable v, String... names) {
    	if(v == null || v.getTypeReference() == null || v.getTypeReference().getPureClassifierReference() == null) {
    		return false;
    	}
    	Classifier currentClassifier = v.getTypeReference().getPureClassifierReference().getTarget();
    	for(String name: names) {
    		if(isClassifierAnnotatedWithName(currentClassifier, name)) {
    			return true;
    		}
    	}
    	return false;
    }

}