package org.somox.kdmhelper;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

public class MethodAssociation {
	
	private ASTNode astNode;
	private ResourceDemandingSEFF seff;
	private BasicComponent basicComponent;
		
	public MethodAssociation(ASTNode node, ResourceDemandingSEFF seff, BasicComponent basicComponent) {
		this.astNode = node;
		this.seff = seff;
		this.basicComponent = basicComponent;
	}

	public BasicComponent getBasicComponent() {
		return basicComponent;
	}

	public ASTNode getAstNode() {
		return astNode;
	}

	public ResourceDemandingSEFF getSeff() {
		return seff;
	}
	
	public void setSeff(ResourceDemandingSEFF seff) {
		this.seff = seff;
	}
	

}
