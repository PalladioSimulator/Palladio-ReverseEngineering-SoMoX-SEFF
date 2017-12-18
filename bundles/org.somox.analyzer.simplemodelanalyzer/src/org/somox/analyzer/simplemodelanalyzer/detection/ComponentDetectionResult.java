package org.somox.analyzer.simplemodelanalyzer.detection;

import java.util.LinkedList;
import java.util.List;

import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * This class seems to be useless.... Replace with {@link ComponentImplementingClassesLink}
 *
 */
public class ComponentDetectionResult {
    private final List<ComponentImplementingClassesLink> foundComponents;

    public ComponentDetectionResult() {
        this.foundComponents = new LinkedList<ComponentImplementingClassesLink>();
    }

    public List<ComponentImplementingClassesLink> getFoundComponents() {
        return this.foundComponents;
    }

    // extend this by composite-information and "reason for component" information

}
