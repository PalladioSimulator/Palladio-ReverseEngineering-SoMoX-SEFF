package org.somox.gast2seff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public final class MethodDeclarationFinder extends ASTVisitor {
	private final List<MethodDeclaration> methods = new ArrayList<>();

	public static List<MethodDeclaration> perform(ASTNode node) {
		MethodDeclarationFinder finder = new MethodDeclarationFinder();
		node.accept(finder);
		return finder.getMethods();
	}

	@Override
	public boolean visit(final MethodDeclaration method) {
		methods.add(method);
		return super.visit(method);
	}

	/**
	 * @return an immutable list view of the methods discovered by this visitor
	 */
	public List<MethodDeclaration> getMethods() {
		return Collections.unmodifiableList(methods);
	}
}
