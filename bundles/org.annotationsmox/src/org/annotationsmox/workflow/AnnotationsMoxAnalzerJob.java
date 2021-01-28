package org.annotationsmox.workflow;

import org.annotationsmox.analyzer.AnnotationsMoxAnalyzer;
import org.annotationsmox.analyzer.AnnotationsMoxAnalyzerConfiguration;
import org.annotationsmox.analyzer.AnnotationsMoxConfiguration;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.somox.analyzer.AnalysisResult;
import org.somox.analyzer.ModelAnalyzerException;
import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

public class AnnotationsMoxAnalzerJob extends AbstractBlackboardInteractingJob<SoMoXBlackboard> {

    private final AnnotationsMoxConfiguration ejbmoxConfiguration;

    public AnnotationsMoxAnalzerJob(final AnnotationsMoxAnalyzerConfiguration config) {
        this.ejbmoxConfiguration = config.getMoxConfiguration();
    }

    @Override
    public void execute(final IProgressMonitor arg0) throws JobFailedException, UserCanceledException {
        final AnnotationsMoxAnalyzer ejbMoxAnalzer = new AnnotationsMoxAnalyzer();
        try {
            final AnalysisResult analysisResult = ejbMoxAnalzer.analyze(this.ejbmoxConfiguration, null,
                    new NullProgressMonitor());
            this.myBlackboard.setAnalysisResult(analysisResult);
        } catch (final ModelAnalyzerException e) {
            throw new JobFailedException("SoMoX Failed", e);
        }
    }

    @Override
    public String getName() {
        return "EJBmox Analyzer Job";
    }

    @Override
    public void cleanup(final IProgressMonitor arg0) throws CleanupFailedException {
        // TODO Auto-generated method stub

    }

}
