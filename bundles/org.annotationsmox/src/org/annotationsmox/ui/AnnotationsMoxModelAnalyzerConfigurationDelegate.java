package org.annotationsmox.ui;

import java.util.Map;

import org.annotationsmox.analyzer.AnnotationsMoxAnalyzerConfiguration;
import org.annotationsmox.analyzer.AnnotationsMoxConfiguration;
import org.annotationsmox.workflow.AnnotationsMoxJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;

import de.uka.ipd.sdq.workflow.Workflow;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.launchconfig.AbstractWorkflowBasedLaunchConfigurationDelegate;

/**
 * Class that creates the EJBmox workflow.
 *
 * @author langhamm
 *
 */
public class AnnotationsMoxModelAnalyzerConfigurationDelegate
        extends AbstractWorkflowBasedLaunchConfigurationDelegate<AnnotationsMoxAnalyzerConfiguration, Workflow> {

    @Override
    protected IJob createWorkflowJob(final AnnotationsMoxAnalyzerConfiguration modelAnalyzerConfig, final ILaunch mode)
            throws CoreException {
        return new AnnotationsMoxJob(modelAnalyzerConfig);
    }

    @Override
    protected AnnotationsMoxAnalyzerConfiguration deriveConfiguration(final ILaunchConfiguration launchconfiguration,
            final String mode) throws CoreException {
        final AnnotationsMoxAnalyzerConfiguration modelAnalyzerConfig = new AnnotationsMoxAnalyzerConfiguration();
        final Map<String, Object> attributeMap = launchconfiguration.getAttributes();
        final AnnotationsMoxConfiguration ejbMoxConfiguration = new AnnotationsMoxConfiguration(attributeMap);
        modelAnalyzerConfig.setMoxConfiguration(ejbMoxConfiguration);
        return modelAnalyzerConfig;
    }

}
