package org.annotationsmox.analyzer;

import org.somox.ui.runconfig.ModelAnalyzerConfiguration;

public class AnnotationsMoxAnalyzerConfiguration extends ModelAnalyzerConfiguration<AnnotationsMoxConfiguration> {

    @Override
    public void setDefaults() {
        this.moxConfiguration = new AnnotationsMoxConfiguration();
    }

}
