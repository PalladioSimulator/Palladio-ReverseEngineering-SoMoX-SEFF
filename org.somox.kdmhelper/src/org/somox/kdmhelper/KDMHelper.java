package org.somox.kdmhelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.parser.TagElement;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.Final;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Private;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.VariableLengthParameter;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.Type;
//CompilationUnit statt Model
//Commentable statt AstNode
import org.emftext.language.java.types.TypeReference;

/**
 * This class contains a set of methods that are missing in the MoDisco Java meta model in
 * comparison to the SISSy G-AST meta model.
 *
 * @author Oliver
 *
 */
public class KDMHelper {

    /**
     * SISSy does not model type parameters.<br>
     * For example:
     *
     * <pre>
     * List &lt;String&gt;
     * </pre>
     *
     * only detects List as type access. <br>
     * If this variable is true, then this class works like SISSy. Else, for the example it also
     * returns the String type.
     */
    private static boolean SISSYMODE = false;

    // /**
    // *
    // * @param clazz
    // * @return the real methods (not constructors) of a Class
    // */
    // public static List<AbstractMethodDeclaration> getMethods(ClassDeclaration
    // clazz){
    // List<AbstractMethodDeclaration> result = new
    // ArrayList<AbstractMethodDeclaration>();
    // for(BodyDeclaration bodyDec : clazz.getBodyDeclarations()){
    // if(bodyDec instanceof MethodDeclaration){
    // result.add((AbstractMethodDeclaration) bodyDec);
    // }
    // }
    // return result;
    // }
    public static String getName(final Type type) {
        if (null == type) {
            return null;
        }
        if (type instanceof NamedElement) {
            return ((NamedElement) type).getName();
        } else if (type instanceof Classifier) {
            return ((Classifier) type).getName();
        } else if (type instanceof Package) {
            return ((Package) type).getName();
        } else if (type instanceof CompilationUnit) {
            return ((CompilationUnit) type).getName();
        } else if (type instanceof Method) {
            return ((Method) type).getName();
        } else if (type instanceof Parameter) {
            return ((Parameter) type).getName();
        } else if (type instanceof Member) {
            return ((Member) type).getName();
        } else if (type instanceof Interface) {
            return ((Interface) type).getName();
        } else if (type instanceof PrimitiveType) {
            return getName((PrimitiveType) type);
        } else {
            return type.toString();
        }
    }

    public static String getName(final PrimitiveType type) {
        if (type instanceof org.emftext.language.java.types.Boolean) {
            return "bool";
        } else if (type instanceof org.emftext.language.java.types.Byte) {
            return "byte";
        } else if (type instanceof org.emftext.language.java.types.Char) {
            return "char";
        } else if (type instanceof org.emftext.language.java.types.Double) {
            return "double";
        } else if (type instanceof org.emftext.language.java.types.Float) {
            return "float";
        } else if (type instanceof org.emftext.language.java.types.Int) {
            return "int";
        } else if (type instanceof org.emftext.language.java.types.Long) {
            return "long";
        } else if (type instanceof org.emftext.language.java.types.Short) {
            return "short";
        } else if (type instanceof org.emftext.language.java.types.Void) {
            return "void";
        } else {
            return type.toString();
        }
    }

    /**
     * Returns the qualified name for a type.
     *
     * @param astClass
     *            the {@link ASTNode} object
     * @return the full qualified name of the input object
     */
    public static String computeFullQualifiedName(final Commentable astClass) {
        EObject pack = astClass;

        String result = "";

        if (pack instanceof NamedElement) {
            result = getNameOfNamedElement((NamedElement) pack);
        }

        while (pack != null) {

            if (pack.eContainer() != null && pack.eContainer() instanceof NamedElement) {
                pack = pack.eContainer();
                result = getNameOfNamedElement((NamedElement) pack) + "." + result;
            } else {
                pack = pack.eContainer();
            }
        }
        result = removeLastPoint(result);
        return result;
    }

    public static Method getMethod(final MethodCall methodCall) {
        final ReferenceableElement re = methodCall.getTarget();
        if (re instanceof Method) {
            final Method method = (Method) re;
            return method;
        } else {
            return null;
            // TODO: log error
        }

    }

    public static String removeLastPoint(final String result) {
        if (result != null) {
            if (result.charAt(result.length() - 1) == '.') {
                return result.substring(0, result.length() - 1);
            } else {
                return result;
            }
        }
        return null;
    }

    private static String getNameOfNamedElement(final NamedElement input) {
        String result = "";
        if (input instanceof Method) {
            result = input.getName() + "()";
        } else {
            result = input.getName();
        }
        return result;
    }

    // TODO refactor with
    /**
     * For an access, returns the accessed types. <b>The result set does not contain null
     * pointer.</b>
     *
     * @param element
     *            an access element
     * @return the set of accessed types
     */
    private static Set<Type> getAccessedTypes(final Commentable element) {
        final Set<Type> result = new HashSet<Type>();

        final Type accessedType = GetAccessedType.getAccessedType(element);
        if (SISSYMODE) {
            if (accessedType != null) {
                result.add(accessedType);
            }
        } else {// KDM Mode

            if (accessedType instanceof VariableLengthParameter) {
                final Parameter paramType = (Parameter) accessedType;
                // 1. add main type
                result.add(paramType.getTypeReference().getTarget());
                // 2. add type arguments
                for (final TypeArgument typeAccess : paramType.getTypeArguments()) {
                    if (((TypeReference) typeAccess).getTarget() instanceof Parameter) {
                        // recursive call
                        result.addAll(getAccessedTypes(typeAccess));
                    } else {
                        result.add(GetAccessedType.getAccessedType(typeAccess));
                    }
                }
            } else {// if a normal Type
                result.add(accessedType);
            }
        }
        return result;

    }

    /**
     * For a {@link ParameterizedType} objects returns the accessed types.
     *
     * @param input
     *            a {@link ParameterizedType} object
     * @return a set of the accessed types
     */

    /**
     * Returns all accessed types inside a type.
     *
     * @param input
     *            the input {@link Type}
     * @return the list of accessed types
     */
    public static List<Type> getAllAccessedClasses(final Type input) {
        final Set<Type> resultList = new HashSet<Type>();
        final List<TypeReference> accesses = getAllAccesses(input);

        for (final Commentable node : accesses) {
            resultList.addAll(getAccessedTypes(node));
        }
        final ArrayList<Type> returnSet = new ArrayList<Type>();
        returnSet.addAll(resultList);
        return returnSet;
    }

    /**
     * Returns <b>all accesses inside an ASTNode object</b>. <br>
     * Accesses <b>inside an {@link TagElement}</b> (for example in JavaDoc comments) <br>
     * are <b>not in the result set</b>.
     *
     * @param input
     *            an {@link ASTNode} object
     * @return all accesses inside the ASTNode object
     */
    public static List<TypeReference> getAllAccesses(final Commentable input) {
        final List<TypeReference> result = new ArrayList<TypeReference>();
        final TreeIterator<EObject> iterator = input.eAllContents();

        while (iterator.hasNext()) {
            final EObject element = iterator.next();
            if (element instanceof TypeReference) {
                if (isAccess((TypeReference) element)) {
                    result.add((TypeReference) element);

                }
            }
        }
        return result;
    }

    /**
     * For a type returns all inner classes.
     *
     * @param clazz
     *            the input type
     * @return the list of inner classes
     */
    // SOMOXTODOCHANGE inner classes in inner classes???
    public static List<ConcreteClassifier> getInnerClasses(final ConcreteClassifier clazz) {
        final List<ConcreteClassifier> result = new ArrayList<ConcreteClassifier>();
        if (!(clazz instanceof Class)) {
            return result;
        }
        for (final Iterator<EObject> iterator = clazz.eAllContents(); iterator.hasNext();) {
            final EObject element = iterator.next();
            if (element instanceof Class) {
                if (isInnerClass((ConcreteClassifier) element)) {
                    result.add((Class) element);
                }
            }
        }
        return result;
    }

    /**
     * For an ASTNode computes the {@link JavaNodeSourceRegion} object.
     *
     * @param node
     *            the ASTNode object
     * @return the {@link JavaNodeSourceRegion}
     */
    // JavaNodeSourceRegion
    // Commentable statt
    public static CompilationUnit getJavaNodeSourceRegion(final Commentable node) {

        return node.getContainingCompilationUnit();

    }

    /**
     * Returns all real methods (not constructors) of a type.
     *
     * @param input
     *            the
     * @return the real methods (not constructors) of a Class
     */
    public static List<Method> getMethods(final ConcreteClassifier input) {
        return input.getMethods();
    }

    /**
     * Returns, if exist, the overridden member, else null.
     *
     * @param methDecInput
     *            the method object
     * @return the overridden method
     */
    public static Method getOverriddenASTNode(final Method methDecInput) {

        final ConcreteClassifier classifierOfMethod = methDecInput.getContainingConcreteClassifier();
        final Collection<ConcreteClassifier> superTypes = getSuperTypes(classifierOfMethod);

        for (final ConcreteClassifier concreteClassifier : superTypes) {
            final List<Method> method = KDMHelper.getMethods(concreteClassifier);
            for (final Method methodDeclaration : method) {
                if (EqualityChecker.areFunctionsEqual(methDecInput, methodDeclaration)) {
                    return methodDeclaration;
                }
            }
        }

        return null;
    }

    /**
     * Returns a string representing the {@link Commentable} object.
     *
     * @param node
     *            the {@link ASTNode} object
     * @return the toString string of the input object
     */
    public static String getSISSyID(final Commentable node) {
        return node.toString();
    }

    /**
     * Queries the {@link SourceFile} object for a {@link JavaNodeSourceRegion} object.
     *
     * @param sourceRegion
     *            the input object
     * @return the {@link SourceFile} object
     */

    /**
     * Returns all super types of a type.
     *
     * @param concreteClassifier
     *            the input {@link ConcreteClassifier}
     * @return the list of super types
     */
    public static List<ConcreteClassifier> getSuperTypes(final ConcreteClassifier concreteClassifier) {
        if (concreteClassifier == null) {
            return Collections.emptyList();
        }
        return concreteClassifier.getAllSuperClassifiers();
    }

    /**
     * Returns the surrounding package of a type, else null.
     *
     * @param input
     *            the input {@link Type}
     * @return the {@link Package} containing the type
     */
    public static Package getSurroundingPackage(final Type input) {
        return null;
        // input.getChildrenByType((Type)Package);//
        // input.getContainingPackageName()

    }

    /**
     * Returns whether the AnnotableAndModifiable object is abstract.
     *
     * @param input
     *            the {@link Type} object
     * @return true or false
     */
    public static boolean isAbstract(final AnnotableAndModifiable input) {
        return input.getModifiers().contains(ModifiersFactory.eINSTANCE.createAbstract());
    }

    // TODO check refactor switch class
    // is fast, no refactoring needed
    /**
     *
     * @param element
     * @return true or false
     */
    public static boolean isAccess(final Commentable element) {

        if (element instanceof MethodCall) {
            return true;
        }
        if (element instanceof ClassifierReference) {
            return true;
        }
        if (element instanceof IdentifierReference) {
            return true;
        }

        // TODO check if handles all accesses
        // is an AbstractMethodInvocation, but contains a type access, would
        // else create the TypeAccess twice
        // if (element instanceof TypeReference)
        // if (element instanceof Class) {
        // return false;
        // }
        // if (element instanceof Method) {
        // return true;
        // }
        // if (element instanceof ArraysPackage) {
        // return true;
        // }
        // if (element instanceof Field) {
        // return true;
        // }
        // if (element instanceof TypeReference) {
        // return true;
        // }
        // if (element instanceof SelfReference) {
        // return true;
        // }
        return false;
    }

    /**
     * Checks if a type access is an inheritance type access.
     *
     * @param inputTypeAccess
     *            The type access to verify.
     * @return true or false.
     */
    public static boolean isInheritanceTypeAccess(final TypeReference inputTypeAccess) {
        if (inputTypeAccess.eContainer() instanceof ConcreteClassifier) {
            // Type statt AbstractTypeDeclaration
            final ConcreteClassifier concreteClassifier = (ConcreteClassifier) inputTypeAccess.eContainer();
            for (final TypeReference ta : getInheritanceTypeAccesses(concreteClassifier)) {
                if (ta == inputTypeAccess) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<ClassifierReference> getInheritanceTypeAccesses(final ConcreteClassifier concreteClassifier) {
        return concreteClassifier.getSuperTypeReferences();
    }

    /**
     * Returns if the type is an inner class.
     *
     * @param clazz
     *            the input {@link Type}
     * @return true or false
     */
    public static boolean isInnerClass(final ConcreteClassifier clazz) {
        return clazz.eContainer() instanceof Class;
    }

    public static EClass[] getNewEClassEnumeration() {
        final EClass[] en = null;
        return en;
    }

    /**
     * Returns if the type is an interface.
     *
     * @param input
     *            the input object
     * @return true or false
     */
    public static boolean isInterface(final Commentable input) {
        return input instanceof Interface;
    }

    /**
     *
     * @param method
     *            The method to check the visibility.
     * @param inputVisKind
     *            the visibility kind to compare with
     * @return true or false
     */

    /**
     * Returns whether the type is primitive or not.
     *
     * @param input
     *            the input {@link Type}
     * @return true or false
     */
    public static boolean isPrimitive(final Type input) {
        return input instanceof PrimitiveType;
    }

    /**
     * A virtual method can be overridden. In Java 1. Static methods cannot be overridden. 2. Non
     * static private and final methods cannot be overridden.
     *
     * @param method
     *            the {@link BodyDeclaration} object
     * @return true or false
     */
    public static boolean isVirtual(final Method method) {
        if (method == null || method.getModifiers() == null) {
            return false;
        }

        for (final Modifier modifier : method.getModifiers()) {
            if (modifier instanceof Private || modifier instanceof Static || modifier instanceof Final) {
                return false;
            }
        }

        return true;
    }

    /**
     * retruns the body of a method Since we use jamopp we return the class method itself since the
     * ClassMethod is a StatementListContainer If the method is not a class method we just return an
     * empty block (which is also a StatementListContainer)
     *
     * @param member
     * @return
     */
    public static StatementListContainer getBody(final Member member) {
        if (member instanceof StatementListContainer) {
            return (StatementListContainer) member;
        }
        final Block emptyBlock = StatementsFactory.eINSTANCE.createBlock();
        return emptyBlock;
    }

    public static EList<Package> getOwnedPackages(final Package prefixPackage) {
        // TODO Test
        return null;// ( (Object) prefixPackage).getSubpackages();

        // EList<Package> ownedPackages=null;
        // org.emftext.language.java.containers.Package p;
        //
        // for (CompilationUnit comUnit :prefixPackage.getCompilationUnits())
        // {
        // comUnit.getNamespaces();
        // if(comUnit.getContainingPackageName())
        // }
        // return null;
    }

    public static Object getPackage(final Package element) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Collection<Package> getOwnedElements(final Package element) {
        // TODO Auto-generated method stub
        return null;
    }

    public static List<Constructor> getConstructors(final Type implementingClass) {
        final List<Constructor> constructors = new ArrayList<Constructor>();
        if (implementingClass instanceof ConcreteClassifier) {
            final ConcreteClassifier concreteClassifier = (ConcreteClassifier) implementingClass;
            for (final Member member : concreteClassifier.getMembers()) {
                if (member instanceof Constructor) {
                    constructors.add((Constructor) member);
                }
            }
        }
        return constructors;
    }

}
