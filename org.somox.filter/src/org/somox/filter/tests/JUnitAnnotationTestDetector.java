package org.somox.filter.tests;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Detects a files containing a java class containing appropriate Annotations of JUnit as being a
 * test file. This also applies to classes that extends such a class.
 *
 * <h2>Rationale</h2>
 *
 * <p>
 * JUnit is a framework solely used for testing purposes. The chosen annotations declare methods as
 * either containing test logic or preparing tests. If a file contains such a method, it is a test
 * file (see also the rationale in {@link NotASourceFileTestDetector}). Even if JUnit or a plugin
 * contributing to it was analysed, production code would not be annotated with these annotations.
 *
 * <p>
 * The annotations that are being searched for are:
 *
 * <ul>
 *
 * <li><code>@Test</code>
 *
 * <li><code>@Before</code>
 *
 * <li><code>@BeforeClass</code>
 *
 * <li><code>@After</code>
 *
 * <li><code>@AfterClass</code>
 *
 * </ul>
 *
 * <p>
 * Super classes are being searched because the information of these annotations are being inherited
 * (If the parent class has a test method, the child also has it).
 *
 * @author Joshua Gleitze
 */
public class JUnitAnnotationTestDetector implements TestDetector {

    /**
     * The annotations we’re looking for.
     */
    private static final Set<String> SEARCHED_ANNOTATIONS = new HashSet<>(Arrays.asList("org.junit.Test",
            "org.junit.After", "org.junit.AfterClass", "org.junit.Before", "org.junit.BeforeClass"));

    /**
     * The factory to obtain ASTs from.
     */
    private final EclipseAstFactory astFactory = new EclipseAstFactory();

    /**
     * Saves IOExceptions that might occur during stream operations to throw them after the
     * operation.
     */
    private Optional<IOException> ioError;

    @Override
    public Optional<Boolean> isTest(final Path path) throws IOException {
        this.ioError = Optional.empty();
        final CompilationUnit compilationUnit = this.astFactory.getCompilationUnit(path, true);

        final boolean isTest = this.isTest(compilationUnit);

        if (this.ioError.isPresent()) {
            throw this.ioError.get();
        }

        if (isTest) {
            return Optional.of(Boolean.TRUE);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Checks whether the provided {@code sourceFile} is a test file. This method is called when
     * recursively checking superclasses.
     *
     * @param sourceFile
     *            A source file.
     * @return {@code true} if {@code sourceFile} is surely a test file.
     */
    private boolean isTest(final ICompilationUnit sourceFile) {
        try {
            final CompilationUnit compilationUnit = this.astFactory.getCompilationUnit(sourceFile, true);
            return this.isTest(compilationUnit);
        } catch (final IOException ioException) {
            this.ioError = Optional.of(ioException);
            return false;
        }
    }

    /**
     * Checks whether the provided {@code compilationUnit} is a test file. This method contains the
     * actual logic and is called by {@link #isTest(ICompilationUnit)} and {@link #isTest(Path)}
     * once the AST was obtained.
     *
     * @param compilationUnit
     *            A source file’s AST.
     * @return {@code true} if {@code sourceFile} is surely a test file.
     */
    // The Eclipse AST returns untyped collections
    @SuppressWarnings("unchecked")
    private boolean isTest(final CompilationUnit compilationUnit) {
        final List<AbstractTypeDeclaration> containedTypes = compilationUnit.types();
        final ITypeBinding objectType = compilationUnit.getAST().resolveWellKnownType("java.lang.Object");

        // does any method in the unit contain a JUnit annotation?
        final boolean hasJunit = containedTypes.stream().flatMap(EclipseAst::withSubDeclarations)
                .filter(declaration -> declaration.getNodeType() == ASTNode.METHOD_DECLARATION)
                .map(declaration -> ((MethodDeclaration) declaration).resolveBinding())
                .filter(binding -> binding != null).flatMap(method -> Arrays.stream(method.getAnnotations())).anyMatch(
                        annotation -> SEARCHED_ANNOTATIONS.contains(annotation.getAnnotationType().getQualifiedName()));

        if (hasJunit) {
            return true;
        }

        // does any type in the unit have a parent type with such a method?
        return containedTypes.stream().flatMap(EclipseAst::withSubDeclarations).filter(EclipseAst::isTypeDeclaration)
                .map(type -> ((AbstractTypeDeclaration) type).resolveBinding()).filter(binding -> binding != null)
                .map(ITypeBinding::getSuperclass)
                // java.lang.Object does surely not have such a method but will very often be
                // a superclass
                .filter(superclass -> superclass != null && superclass != objectType).map(ITypeBinding::getJavaElement)
                .filter(javaElement -> javaElement != null
                        && javaElement.getElementType() == IJavaElement.COMPILATION_UNIT)
                .anyMatch(parentUnit -> this.isTest((ICompilationUnit) parentUnit));
    }

    @Override
    public boolean mayReturnFalse() {
        return false;
    }

}
