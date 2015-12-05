package org.somox.ui.runconfig.configbuilder;

import java.util.Map;

import org.somox.configuration.SOMOXConfigurationBuilderByPreferences;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.ui.runconfig.ModelAnalyzerConfiguration;

import de.uka.ipd.sdq.workflow.launchconfig.configbuilder.AbstractUIBasedConfigurationBuilder;

public class UIBasedSomoxConfigurationBuilder extends AbstractUIBasedConfigurationBuilder<ModelAnalyzerConfiguration> {

    public UIBasedSomoxConfigurationBuilder(final Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    protected ModelAnalyzerConfiguration internalBuild(final Map<String, Object> attributes) {
        final ModelAnalyzerConfiguration myConfig = new ModelAnalyzerConfiguration();

        final SoMoXConfiguration somoxConfiguration = (new SOMOXConfigurationBuilderByPreferences())
                .createSOMOXConfiguration(attributes);
        myConfig.setSomoxConfiguration(somoxConfiguration);

        return myConfig;
    }
}
