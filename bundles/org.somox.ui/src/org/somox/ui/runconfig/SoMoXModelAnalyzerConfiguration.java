package org.somox.ui.runconfig;

import org.somox.configuration.SoMoXConfiguration;

public class SoMoXModelAnalyzerConfiguration extends ModelAnalyzerConfiguration<SoMoXConfiguration> {

    @Override
    public void setDefaults() {
        this.moxConfiguration = new SoMoXConfiguration();
    }

}
