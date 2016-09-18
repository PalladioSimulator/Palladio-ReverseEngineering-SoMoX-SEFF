package org.somox.filter;

import java.util.ArrayList;
import java.util.Arrays;

public class ComposedFilter<T> extends BaseFilter<T> {

    final private ArrayList<BaseFilter<T>> innerFilter = new ArrayList<>();

    public ComposedFilter(final BaseFilter<T>... innerFilter) {
        super();

        this.addFilter(innerFilter);
    }

    @Override
    public boolean passes(final T object) {
        for (final BaseFilter<T> inner : this.innerFilter) {
            if (!inner.passes(object)) {
                return false;
            }
        }
        return true;
    }
    
    public void addFilter(BaseFilter<T>... filters) {
        this.innerFilter.addAll(Arrays.asList(filters));
    }

}
