package org.somox.filter;

public class NotFilter<T> extends BaseFilter<T> {

    private BaseFilter<T> innerFilter = null;

    public NotFilter(final BaseFilter<T> innerFilter) {
        super();
        this.innerFilter = innerFilter;
    }

    @Override
    public boolean passes(final T object) {
        return !this.innerFilter.passes(object);
    }

}
