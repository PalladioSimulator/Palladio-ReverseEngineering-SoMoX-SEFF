package org.somox.ui.runconfig;

import org.somox.configuration.SOMOXConfigurationBuilderByPreferences;
import org.somox.configuration.SoMoXConfiguration;

import de.uka.ipd.sdq.workflow.launchconfig.AbstractWorkflowBasedRunConfiguration;

public class ModelAnalyzerConfiguration 
extends
		AbstractWorkflowBasedRunConfiguration {
		
	SoMoXConfiguration somoxConfiguration = null;
	
	public ModelAnalyzerConfiguration() {
		super();
	}

	public SoMoXConfiguration getSomoxConfiguration() {
		return somoxConfiguration;
	}
	
	public void setSomoxConfiguration(SoMoXConfiguration somoxConfiguration) {
		this.somoxConfiguration = somoxConfiguration;
	}

	@Override
	public String getErrorMessage() {
		return null;
	}

	@Override
	public void setDefaults() {
		this.somoxConfiguration = (new SOMOXConfigurationBuilderByPreferences()).createSOMOXConfiguration(null);
	}
}
