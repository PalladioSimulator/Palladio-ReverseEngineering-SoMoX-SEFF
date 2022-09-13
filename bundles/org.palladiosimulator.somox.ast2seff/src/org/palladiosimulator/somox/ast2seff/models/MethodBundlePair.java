package org.palladiosimulator.somox.ast2seff.models;

import org.eclipse.jdt.core.dom.ASTNode;

public class MethodBundlePair {
	
	private String bundleName;
	private ASTNode astNode;
		
	public MethodBundlePair(String bundleName, ASTNode node) {
		this.bundleName = bundleName;
		this.astNode = node;
	}

	public ASTNode getAstNode() {
		return astNode;
	}
	
	public String getBundleName() {
		return bundleName;
	}
}
