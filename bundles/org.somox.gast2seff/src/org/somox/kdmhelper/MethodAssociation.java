package org.somox.kdmhelper;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;

public class MethodAssociation {
	
	private MethodDeclaration methodDeclaration;
	private ResourceDemandingSEFF seff;
	private BasicComponent basicComponent;
		
	public MethodAssociation(MethodDeclaration methodDeclaration, ResourceDemandingSEFF seff, BasicComponent basicComponent) {
		this.methodDeclaration = methodDeclaration;
		this.seff = seff;
		this.basicComponent = basicComponent;
	}

	public BasicComponent getBasicComponent() {
		return basicComponent;
	}

	public MethodDeclaration getMethodDeclaration() {
		return methodDeclaration;
	}

	public ResourceDemandingSEFF getSeff() {
		return seff;
	}
	
	

}
