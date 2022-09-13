package org.palladiosimulator.somox.ast2seff.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;

public class ComponentInformation {

	private BasicComponentCreator basicComponentCreator;
	private Map<String, List<String>> componentRequiredListMap = new HashMap<>();
	private boolean isPassiveResourceSet = false;
	
	public ComponentInformation(BasicComponentCreator basicComponentCreator) {
		this.basicComponentCreator = basicComponentCreator;
	}

	public Map<String, List<String>> getComponentRequiredListMap() {
		return componentRequiredListMap;
	}
	
	public BasicComponentCreator getBasicComponentCreator() {
		return basicComponentCreator;
	} 
	
	public void setPassiveResourceSetTrue() {
		this.isPassiveResourceSet = true;
	}
	
	public boolean getIsPassiveResourceSet() {
		return this.isPassiveResourceSet;
	}
}
