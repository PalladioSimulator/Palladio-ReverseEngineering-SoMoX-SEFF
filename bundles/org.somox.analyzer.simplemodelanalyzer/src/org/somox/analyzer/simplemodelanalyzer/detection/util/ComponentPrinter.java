package org.somox.analyzer.simplemodelanalyzer.detection.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.emftext.language.java.types.Type;
import org.somox.kdmhelper.KDMHelper;
//import de.fzi.gast.types.GASTClass;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

public class ComponentPrinter {

    /**
     * Print the given list of components detected in source code including the classes used to
     * implement them
     *
     * @param components
     *            The list of components to print to the debug facility of the given logger
     * @param logger
     *            The logger used to print the components
     */
    public static void printComponents(final List<ComponentImplementingClassesLink> components, final Logger logger) {

        // removelater
        // String fileName = "resultPCKDMComponents.txt";

        int i = 0;
        for (final ComponentImplementingClassesLink currentComponent : components) {
            i++;
            logger.debug("Comp Nr." + i + ":");
            // org.somox.changetest.Helper.writeToFile(fileName, "Comp Nr." + i + ":");//removelater

            for (final Type clazz : currentComponent.getImplementingClasses()) {
                logger.debug(KDMHelper.computeFullQualifiedName(clazz));
                // org.somox.changetest.Helper.writeToFile(fileName,
                // GASTClassHelper.computeFullQualifiedName(clazz));//removelater
            }
            if (currentComponent.isIsCompositeComponent()) {
                logger.debug("Inner Components:");
                // org.somox.changetest.Helper.writeToFile(fileName, " Inner
                // Components:");//removelater
                printComponents(currentComponent.getSubComponents(), logger);
                logger.debug("End Inner Components");
                // org.somox.changetest.Helper.writeToFile(fileName, " End Inner
                // Components");//removelater

            }
        }
    }

}
