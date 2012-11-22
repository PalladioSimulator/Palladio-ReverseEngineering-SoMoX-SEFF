package org.somox.ui.runconfig.configbuilder;

import java.util.Map;

import org.somox.common.SoMoXProjectPreferences;
import org.somox.configuration.SOMOXConfigurationBuilderByPreferences;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.ui.runconfig.ModelAnalyzerConfiguration;
import de.uka.ipd.sdq.workflow.launchconfig.configbuilder.AbstractUIBasedConfigurationBuilder;

public class UIBasedSomoxConfigurationBuilder extends
		AbstractUIBasedConfigurationBuilder<ModelAnalyzerConfiguration> {

	public UIBasedSomoxConfigurationBuilder(Map<String,Object> attributes) {
		super(attributes);
	}

	@Override
	protected ModelAnalyzerConfiguration internalBuild(
			Map<String, Object> attributes) {
		ModelAnalyzerConfiguration myConfig = new ModelAnalyzerConfiguration();
		
		SoMoXConfiguration somoxConfiguration = (new SOMOXConfigurationBuilderByPreferences()).createSOMOXConfiguration(attributes);
		myConfig.setSomoxConfiguration(somoxConfiguration);
		
		return myConfig;
	}
}
