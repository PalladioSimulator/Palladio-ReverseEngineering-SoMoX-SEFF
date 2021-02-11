package org.annotationsmox.commandline;

import java.util.Arrays;
import java.util.HashSet;

import org.annotationsmox.analyzer.AnnotationsMoxAnalyzerConfiguration;
import org.annotationsmox.analyzer.AnnotationsMoxConfiguration;
import org.annotationsmox.util.AnnotationsMoxUtil;
import org.annotationsmox.workflow.AnnotationsMoxJob;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.somox.analyzer.AnalysisResult;
import org.somox.gast2seff.jobs.SoMoXBlackboard;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

public class AnnotationsMoxCommandLine {

    private static final Logger logger = Logger.getLogger(AnnotationsMoxCommandLine.class.getSimpleName());

    private final String inputPath;
    private final String outputPath;
    private final boolean reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour;

    public static void main(final String[] args) {
        AnnotationsMoxUtil.initializeLogger();
        AnnotationsMoxUtil.registerMetamodels();
        AnnotationsMoxUtil.setupURIPathmaps();

        final AnnotationsMoxCommandLine ejbMoxCommandLine = new AnnotationsMoxCommandLine(args);
        ejbMoxCommandLine.runEJBmox();
    }

    public AnnotationsMoxCommandLine(final String... args) {
        if (args.length < 2 || args.length > 4) {
            this.printUsage();
            System.exit(0);
        }
        this.inputPath = args[0];
        this.outputPath = args[1];
        this.reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour = args.length > 2
                && args[2].equalsIgnoreCase("-rdib");
    }

    public AnalysisResult runEJBmox() {
        final long start = System.nanoTime();
        final AnnotationsMoxAnalyzerConfiguration modelAnalyzerConfig = new AnnotationsMoxAnalyzerConfiguration();
        final AnnotationsMoxConfiguration configuration = new AnnotationsMoxConfiguration();
        configuration.getFileLocations().setAnalyserInputFile(this.inputPath);
        configuration.getFileLocations().setProjectNames(new HashSet<String>(Arrays.asList(this.inputPath)));
        configuration.getFileLocations().setOutputFolder(this.outputPath);
        configuration.setReverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour(
                this.reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour);
        modelAnalyzerConfig.setMoxConfiguration(configuration);
        SequentialBlackboardInteractingJob<SoMoXBlackboard> ejbMoxWorkflow;
        try {
            ejbMoxWorkflow = new AnnotationsMoxJob(modelAnalyzerConfig);
            ejbMoxWorkflow.execute(new NullProgressMonitor());
            logger.info("Finished EJBmox run in " + (System.nanoTime() - start) / 1000000000 + " seconds.");
            return ejbMoxWorkflow.getBlackboard().getAnalysisResult();
        } catch (JobFailedException | UserCanceledException e) {
            throw new RuntimeException("Could not create and execute EJBmox workflow.", e);
        }
    }

    private void printUsage() {
        logger.info("Usage: " + AnnotationsMoxCommandLine.class.getSimpleName() + " <inputPath> <outputPath> [<-rdib>]");
    }

}
