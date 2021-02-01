package org.annotationsmox.analyzer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.annotationsmox.analyzer.creators.BasicComponentCreator;
import org.annotationsmox.analyzer.creators.InterfaceCreator;
import org.annotationsmox.analyzer.creators.ProvidedRoleCreator;
import org.annotationsmox.analyzer.creators.RequiredRoleCreator;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.containers.CompilationUnit;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.Repository;
import org.somox.analyzer.AnalysisResult;
import org.somox.util.PcmModelCreationHelper;
import org.somox.util.Seff2JavaCreatorUtil;
import org.somox.util.SourceCodeDecoratorHelper;

/**
 * Creates the PCM repository based on EJB components
 *
 * @author langhamm
 *
 */
public class AnnotationsMoxPCMRepositoryModelCreator {

	public static final boolean EXTENSIONS_FOR_FIELDS_AND_INTERFACES = true;

	private final HashSet<CompilationUnit> compilationUnits;

	private final Repository repository;
	private final PcmModelCreationHelper pcmModelCreationHelper;
	private final SourceCodeDecoratorHelper sourceCodeDecoratorHelper;
	private final AnalysisResult analysisResult;

	private final BasicComponentCreator basicComponentCreator;
	private final InterfaceCreator interfaceCreator;
	private final EventCreator eventCreator;
	private final ProvidedRoleCreator providedRoleCreator;
	private final RequiredRoleCreator requiredRoleCreator;

	public AnnotationsMoxPCMRepositoryModelCreator(final Collection<CompilationUnit> compilationUnits,
			final AnalysisResult analysisResult) {
		this(new HashSet<CompilationUnit>(compilationUnits), analysisResult);
	}

	public AnnotationsMoxPCMRepositoryModelCreator(final HashSet<CompilationUnit> compilationUnits,
			final AnalysisResult analysisResult) {
		this.compilationUnits = compilationUnits;
		this.analysisResult = analysisResult;
		this.repository = analysisResult.getInternalArchitectureModel();
		this.sourceCodeDecoratorHelper = new SourceCodeDecoratorHelper(
				analysisResult.getSourceCodeDecoratorRepository());
		this.pcmModelCreationHelper = new PcmModelCreationHelper(analysisResult, this.sourceCodeDecoratorHelper);

		this.basicComponentCreator = new BasicComponentCreator(this.repository, this.sourceCodeDecoratorHelper);
		this.interfaceCreator = new InterfaceCreator(this.repository,
				this.analysisResult.getSourceCodeDecoratorRepository(), this.sourceCodeDecoratorHelper,
				this.pcmModelCreationHelper);
		this.requiredRoleCreator = new RequiredRoleCreator(this.sourceCodeDecoratorHelper);
		this.providedRoleCreator = new ProvidedRoleCreator();
		this.eventCreator = new EventCreator(this.repository, this.sourceCodeDecoratorHelper, this.pcmModelCreationHelper, this.providedRoleCreator);
	}

	public Repository createStaticArchitectureModel() {
		this.compilationUnits.forEach(compilationUnit -> compilationUnit.getClassifiers().stream()
				.filter(classifier -> classifier instanceof Class).map(classifier -> (Class) classifier)
				.filter(jamoppClass -> AnnotationHelper.isComponentClass(jamoppClass))
				.forEach(ejbClass -> this.createBasicComponentAndProvidedOperationInterfacesForEJBClass(ejbClass)));
		Map<BasicComponent, Class> basicComponent2EJBClassMap = this.basicComponentCreator
				.getBasicComponent2EJBClassMap();
		this.eventCreator.createEventGroups(this.compilationUnits, basicComponent2EJBClassMap);
		basicComponent2EJBClassMap.keySet()
				.forEach(component -> this.requiredRoleCreator.createRequiredRoles(component, basicComponent2EJBClassMap.get(component)));
		this.createEmptySEFFs();
		return this.repository;
	}

	private void createBasicComponentAndProvidedOperationInterfacesForEJBClass(final Class ejbClass) {
		final BasicComponent basicComponent = this.basicComponentCreator.createBasicComponentForEJBClass(ejbClass);
		final Collection<org.palladiosimulator.pcm.repository.Interface> providedInterfaces = this.interfaceCreator
				.createProvidedInterfacesForEJBClass(ejbClass);
		providedRoleCreator.createProvidedRoles(basicComponent, providedInterfaces);
	}


	private void createEmptySEFFs() {
		Seff2JavaCreatorUtil.executeSeff2JavaAST(this.analysisResult, this.analysisResult.getRoot());
	}

}
