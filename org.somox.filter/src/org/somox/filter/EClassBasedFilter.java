package org.somox.filter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * Filters accesses based on their type
 *
 * @author Steffen Becker
 */
public class EClassBasedFilter<T extends EObject> extends BaseFilter<T> {

    private final EClass[] filteredEClasses;

    /**
     * Constructor of this filter
     *
     * @param eClasses
     *            The Accesses' EClass which should be removed by this filter
     */
    public EClassBasedFilter(final EClass... eClasses) {
        super();

        this.filteredEClasses = eClasses;
    }

    @Override
    public boolean passes(final EObject object) {
        for (final EClass clazz : this.filteredEClasses) {
            if (object.eClass() == clazz) {
                return false;
            }
        }
        return true;
    }

}
