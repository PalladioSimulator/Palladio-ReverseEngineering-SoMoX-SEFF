package org.somox.filter.tests;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;

import java.util.List;
import java.util.stream.Stream;

/**
 * Utility class containing convenience methods to interact with the Eclipse AST.
 *
 * @author Joshua Gleitze
 */
public final class EclipseAst {

	/**
	 * This utility class is not meant to be instantiated.
	 */
	private EclipseAst() {
	}

	/**
	 * Creates a stream of all body declarations recursively contained in the provided
	 * {@code declaration}, including {@code declaration} itself.
	 *
	 * @param declaration A body declaration.
	 * @return If {@code declaration} is a type declaration, a stream of all body
	 *         declarations contained in {@code declaration}, including
	 *         {@code declaration} itself. A stream of {@code declaration} otherwise.
	 */
	@SuppressWarnings("unchecked")
	public static Stream<BodyDeclaration> withSubDeclarations(final BodyDeclaration declaration) {
		if (isTypeDeclaration(declaration)) {
			final List<BodyDeclaration> nextDeclarations = ((AbstractTypeDeclaration) declaration).bodyDeclarations();
			final Stream<BodyDeclaration> containedDeclarations =
				nextDeclarations.stream().flatMap(EclipseAst::withSubDeclarations);
			return Stream.concat(Stream.of(declaration), containedDeclarations);
		}

		return Stream.of(declaration);
	}

	/**
	 * Checks whether the given {@code declaration} declares any java type.
	 *
	 * @param declaration A declaration. Must not be {@code null}.
	 * @return {@code true} iff the {@code declaration} declares an annotation, class or
	 *         enum.
	 */
	public static boolean isTypeDeclaration(final BodyDeclaration declaration) {
		switch (declaration.getNodeType()) {
			case ASTNode.TYPE_DECLARATION:
			case ASTNode.ANNOTATION_TYPE_DECLARATION:
			case ASTNode.ENUM_DECLARATION:
				return true;
			default:
				return false;
		}
	}
}
