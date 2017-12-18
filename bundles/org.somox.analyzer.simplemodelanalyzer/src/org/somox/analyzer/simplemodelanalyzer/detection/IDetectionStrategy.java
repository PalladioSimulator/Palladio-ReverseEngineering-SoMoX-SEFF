package org.somox.analyzer.simplemodelanalyzer.detection;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.somox.analyzer.ModelAnalyzerException;
import org.somox.analyzer.simplemodelanalyzer.builder.ComponentBuilder;
import org.somox.configuration.AbstractMoxConfiguration;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

public interface IDetectionStrategy {

    public List<ComponentImplementingClassesLink> startDetection(ComponentBuilder sammBuilder,
            AbstractMoxConfiguration somoxConfig, IProgressMonitor progressMonitor,
            List<ComponentImplementingClassesLink> components) throws ModelAnalyzerException;
}