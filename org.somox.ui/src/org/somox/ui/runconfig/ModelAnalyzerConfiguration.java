package org.somox.ui.runconfig;

import org.somox.configuration.SoMoXConfiguration;

import de.uka.ipd.sdq.workflow.launchconfig.AbstractWorkflowBasedRunConfiguration;

public class ModelAnalyzerConfiguration extends AbstractWorkflowBasedRunConfiguration {

    SoMoXConfiguration somoxConfiguration = null;

    public ModelAnalyzerConfiguration() {
        super();
    }

    public SoMoXConfiguration getSomoxConfiguration() {
        return this.somoxConfiguration;
    }

    public void setSomoxConfiguration(final SoMoXConfiguration somoxConfiguration) {
        this.somoxConfiguration = somoxConfiguration;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public void setDefaults() {
        this.somoxConfiguration = new SoMoXConfiguration();
    }
}
