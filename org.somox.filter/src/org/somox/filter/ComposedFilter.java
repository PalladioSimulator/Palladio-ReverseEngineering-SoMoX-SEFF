package org.somox.filter;

public class ComposedFilter<T> extends BaseFilter<T> {

    final private BaseFilter<T>[] innerFilter;

    public ComposedFilter(final BaseFilter<T>... innerFilter) {
        super();

        this.innerFilter = innerFilter;
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

}
