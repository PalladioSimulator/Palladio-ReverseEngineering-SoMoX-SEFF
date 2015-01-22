package org.somox.metrics.helper;

import java.util.Set;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.somox.filter.BaseFilter;

//import de.fzi.gast.types.GASTClass;

public class TargetClassEdgeFilter extends BaseFilter<ClassAccessGraphEdge> {

    private final Set<ConcreteClassifier> filteredTarget;

    /**
     * 
     * @param filteredTarget
     *            positive list of non-filtered target class accesses
     */
    public TargetClassEdgeFilter(final Set<ConcreteClassifier> filteredTarget) {
        this.filteredTarget = filteredTarget;
    }

    @Override
    public boolean passes(final ClassAccessGraphEdge object) {
        return this.filteredTarget.contains(object.getTargetClazz());
    }

}
