package org.annotationsmox.analyzer.creators;

import java.util.HashMap;
import java.util.Map;

import org.emftext.language.java.classifiers.Class;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.somox.util.SourceCodeDecoratorHelper;

public class BasicComponentCreator {
	
	private final Repository repository;
	private final SourceCodeDecoratorHelper sourceCodeDecoratorHelper;
	
	/**
	 * This map stores the mapping of basic components to its realizing classes
	 * internally. It is used in createRequiredRoles to create the required
	 * roles for the component.
	 */
	private final Map<BasicComponent, Class> basicComponent2EJBClassMap;
	
	public BasicComponentCreator(Repository repository, SourceCodeDecoratorHelper sourceCodeDecoratorHelper){
		this.repository = repository;
		this.sourceCodeDecoratorHelper = sourceCodeDecoratorHelper;
		this.basicComponent2EJBClassMap = new HashMap<BasicComponent, Class>();
	}
	
	public BasicComponent createBasicComponentForEJBClass(final Class ejbClass) {
		final BasicComponent basicComponent = RepositoryFactory.eINSTANCE.createBasicComponent();
		basicComponent.setEntityName(ejbClass.getName());
		this.repository.getComponents__Repository().add(basicComponent);
		this.sourceCodeDecoratorHelper.createComponentImplementingClassesLink(basicComponent, ejbClass);
		this.basicComponent2EJBClassMap.put(basicComponent, ejbClass);
		return basicComponent;
	}

	public Map<BasicComponent, Class> getBasicComponent2EJBClassMap() {
		return basicComponent2EJBClassMap;
	}
}
