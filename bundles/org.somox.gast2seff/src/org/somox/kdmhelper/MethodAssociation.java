package org.somox.kdmhelper;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

public class MethodAssociation {
	
	private String bundleName;
	private ASTNode astNode;
	private ResourceDemandingSEFF seff;
	private BasicComponent basicComponent;
	private BasicComponentCreator basicComponentCreator;
		
	public MethodAssociation(String bundleName, ASTNode node, ResourceDemandingSEFF seff, BasicComponent basicComponent) {
		this.bundleName = bundleName;
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
	public String getBundleName() {
		return bundleName;
	}
	public BasicComponentCreator getBasicComponentCreator() {
		return basicComponentCreator;
	}
	
	public void setSeff(ResourceDemandingSEFF seff) {
		this.seff = seff;
	}
	public void setBasicComponentCreator(BasicComponentCreator basicComponentCreator) {
		this.basicComponentCreator = basicComponentCreator;
	}
}
