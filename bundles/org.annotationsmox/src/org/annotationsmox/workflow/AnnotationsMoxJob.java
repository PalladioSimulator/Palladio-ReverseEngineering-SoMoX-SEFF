package org.annotationsmox.workflow;

import java.util.Map;

import org.annotationsmox.analyzer.AnnotationsMoxAnalyzerConfiguration;
import org.annotationsmox.analyzer.AnnotationsMoxConfiguration;
import org.annotationsmox.seffhelper.externalcallfinder.EJBInterfaceOfExternalCallFindingFactory;
import org.annotationsmox.seffhelper.functionclassification.AnnotationsMoxFunctionClassificationStrategyFactory;
import org.somox.gast2seff.jobs.SaveSoMoXModelsJob;
import org.somox.gast2seff.jobs.SoMoXBlackboard;
import org.somox.gast2seff.jobs.GAST2SEFFJob;

import de.uka.ipd.sdq.workflow.extension.AbstractExtendableJob;
import de.uka.ipd.sdq.workflow.extension.ExtendableJobConfiguration;

public class AnnotationsMoxJob extends AbstractExtendableJob<SoMoXBlackboard> {

    private final AnnotationsMoxAnalyzerConfiguration modelAnalyzerConfig;

    public AnnotationsMoxJob(final AnnotationsMoxAnalyzerConfiguration modelAnalyzerConfig) {
        this.modelAnalyzerConfig = modelAnalyzerConfig;
        final SoMoXBlackboard soMoXBlackboard = new SoMoXBlackboard();
        this.setBlackboard(soMoXBlackboard);
        final AnnotationsMoxConfiguration ejbmoxConfiguration = modelAnalyzerConfig.getMoxConfiguration();
        soMoXBlackboard.addPartition(AnnotationsMoxConfiguration.EJBMOX_INSPECTIT_FILE_PATHS,
                ejbmoxConfiguration.getInspectITFilePaths());

        this.add(new AnnotationsMoxAnalzerJob(modelAnalyzerConfig));

        final boolean reverseEngineerResourceDemandingInternalBehaviour = ejbmoxConfiguration
                .isReverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour();
        GAST2SEFFJob ejb2SEFFJob = new GAST2SEFFJob(reverseEngineerResourceDemandingInternalBehaviour,
                new AnnotationsMoxFunctionClassificationStrategyFactory(), new EJBInterfaceOfExternalCallFindingFactory());
        this.add(ejb2SEFFJob);

        this.handleJobExtensions(AnnotationsMoxWorkflowHooks.PRE_SAVE_MODELS, new Configuration());

        this.add(new SaveSoMoXModelsJob(modelAnalyzerConfig.getMoxConfiguration()));

        this.handleJobExtensions(AnnotationsMoxWorkflowHooks.POST_SAVE_MODELS, new Configuration());
    }

    private class Configuration implements ExtendableJobConfiguration {

        @Override
        public Map<String, Object> getAttributes() {
            return AnnotationsMoxJob.this.modelAnalyzerConfig.getMoxConfiguration().getAttributes();
        }

    }

}
