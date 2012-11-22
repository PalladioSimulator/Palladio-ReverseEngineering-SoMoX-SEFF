package org.somox.analyzer.simplemodelanalyzer.detection.util;

import java.util.List;

import org.apache.log4j.Logger;

import de.fzi.gast.types.GASTClass;
import eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink;

public class ComponentPrinter {

	/**
	 * Print the given list of components detected in source code including the classes used to implement them
	 * @param components The list of components to print to the debug facility of the given logger
	 * @param logger The logger used to print the components
	 */
	public static void printComponents(List<ComponentImplementingClassesLink> components, Logger logger) {
		int i = 0;
		for (ComponentImplementingClassesLink currentComponent : components) {
			i++;
			logger.debug("Comp Nr." + i + ":");
			for (GASTClass clazz : currentComponent.getImplementingClasses()) {
				logger.debug(clazz.getQualifiedName());
			}
			if (currentComponent.isCompositeComponent()) {
				logger.debug("Inner Components:");
				printComponents(currentComponent.getSubComponents(), logger);
				logger.debug("End Inner Components");
			}
		}
	}

}
