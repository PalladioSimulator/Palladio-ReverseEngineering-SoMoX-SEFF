package org.somox.kdmhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;

public class ComponentInformation {

	private BasicComponentCreator basicComponentCreator;
	private Map<String, List<String>> componentRequiredListMap = new HashMap<>();
	
	public ComponentInformation(BasicComponentCreator basicComponentCreator) {
		this.basicComponentCreator = basicComponentCreator;
	}

	public Map<String, List<String>> getComponentRequiredListMap() {
		return componentRequiredListMap;
	}
	
	public BasicComponentCreator getBasicComponentCreator() {
		return basicComponentCreator;
	} 
	
}
