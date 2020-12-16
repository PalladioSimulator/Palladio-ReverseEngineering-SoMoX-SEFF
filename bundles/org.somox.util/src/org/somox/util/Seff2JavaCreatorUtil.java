package org.somox.util;

import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.somox.analyzer.AnalysisResult;
// import org.somox.analyzer.simplemodelanalyzer.builder.Seff2JavaASTBuilder;

public final class Seff2JavaCreatorUtil {

    private Seff2JavaCreatorUtil() {

    }

    public static void executeSeff2JavaAST(final AnalysisResult analysisResult,
            final org.somox.kdmhelper.metamodeladdition.Root root) {
        // final Seff2JavaASTBuilder seff2JavaASTBuilder = new Seff2JavaASTBuilder(root, null,
        // analysisResult);
        final Repository repo = analysisResult.getInternalArchitectureModel();
        for (final RepositoryComponent repoComponent : repo.getComponents__Repository()) {
            if (repoComponent instanceof BasicComponent) {
                final BasicComponent bc = (BasicComponent) repoComponent;
                for (final ProvidedRole providedRole : bc.getProvidedRoles_InterfaceProvidingEntity()) {
                    // seff2JavaASTBuilder.addSeffsToBasicComponent(bc, providedRole);
                }
            }
        }
    }

}
