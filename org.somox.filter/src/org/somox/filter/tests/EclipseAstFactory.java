package org.somox.filter.tests;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Handles creation of {@link CompilationUnit Compilation Units} from java files. This
 * class is thread safe.
 *
 * <p>To enable this class to optimise its performance, it should be instantiated as
 * infrequently as possible. Usually, one instance can be used throughout the whole
 * runtime.
 *
 *
 * @author Joshua Gleitze
 */
public class EclipseAstFactory {

	/**
	 * A provider of {@link ASTParser} instances, producing a new instance for each
	 * Thread.
	 */
	private static final ThreadLocal<ASTParser> AST_PARSER =
		ThreadLocal.withInitial(() -> ASTParser.newParser(AST.JLS8));

	/**
	 * The {@link IWorkspaceRoot} we’re acting in.
	 */
	private final IWorkspaceRoot rootWorkspace = ResourcesPlugin.getWorkspace().getRoot();

	/**
	 * Reads in the Java file at the provided {@code path} and returns the
	 * {@linkplain CompilationUnit} found in the file.
	 *
	 * <p>Calling this method with the argument {@code path} is equivalent to calling
	 * {@link #getCompilationUnit(Path, boolean) this.getCompilationUnit(path, false)}.
	 *
	 * @param path The path to a Java file.
	 * @return The compilation representing the java file.
	 * @throws IOException If reading in the file at {@code path} fails.
	 */
	public CompilationUnit getCompilationUnit(final Path path) throws IOException {
		return this.getCompilationUnit(path, false);
	}

	/**
	 * Reads in the Java file at the provided {@code path} and returns the
	 * {@linkplain CompilationUnit} found in the file. If {@code withBindings} is
	 * {@code true}, the returned compilation unit will have resolved bindings. Bindings
	 * are useful to gain information about referenced types, methods, and more, but have
	 * a significant impact on time and space when creating the AST. See
	 * {@link ASTParser#setResolveBindings(boolean)} for more information. If bindings are
	 * being resolved, {@linkplain ASTParser#setBindingsRecovery(boolean) bindings
	 * recovery} will be enabled.
	 *
	 * @param path The path to a Java file.
	 * @param withBindings Whether to resolve bindings while creating the AST.
	 * @return The compilation unit representing the java file.
	 * @throws IOException If reading the file at {@code path} fails.
	 */
	public CompilationUnit getCompilationUnit(final Path path, final boolean withBindings) throws IOException {
		final IPath eclipsePath = new org.eclipse.core.runtime.Path(path.toString());
		final IFile eclipseResource = this.rootWorkspace.getFileForLocation(eclipsePath);
		final IJavaElement eclipseElement = JavaCore.create(eclipseResource);

		if (!(eclipseElement != null && eclipseElement.getElementType() == IJavaElement.COMPILATION_UNIT)) {
			throw new IllegalArgumentException(path + " is not a valid compilation unit of the analysed project.");
		}

		return this.getCompilationUnit((ICompilationUnit) eclipseElement, withBindings);
	}

	/**
	 * Returns the AST {@linkplain CompilationUnit} of the provided JDT
	 * {@linkplain ICompilationUnit}. If {@code withBindings} is {@code true}, the
	 * returned compilation unit will have resolved bindings. Bindings are useful to gain
	 * information about referenced types, methods, and more, but have a significant
	 * impact on time and space when creating the AST. See
	 * {@link ASTParser#setResolveBindings(boolean)} for more information. If bindings are
	 * being resolved, {@linkplain ASTParser#setBindingsRecovery(boolean) bindings
	 * recovery} will be enabled.
	 *
	 * @param compilationUnit A JDT compilation unit.
	 * @param withBindings Whether to resolve bindings while creating the AST.
	 * @return The compilation unit representing the java file.
	 * @throws IOException If reading the file at {@code path} fails.
	 */
	public CompilationUnit getCompilationUnit(final ICompilationUnit compilationUnit, final boolean withBindings)
		throws IOException {
		final ASTParser parser = AST_PARSER.get();
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(compilationUnit);
		parser.setResolveBindings(withBindings);

		return (CompilationUnit) parser.createAST(null);
	}
}
