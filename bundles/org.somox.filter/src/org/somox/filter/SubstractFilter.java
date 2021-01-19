package org.somox.filter;

import java.util.Set;

import org.emftext.language.java.types.Type;

//import de.fzi.gast.types.GASTClass;

/**
 * Removes one set from another set.
 *
 */
public class SubstractFilter extends BaseFilter<Type> {

    private final Set<Type> classesToRemove;

    public SubstractFilter(final Set<Type> classesToRemove) {
        super();
        this.classesToRemove = classesToRemove;
    }

    @Override
    public boolean passes(final Type object) {
        return !this.classesToRemove.contains(object);
    }

}
