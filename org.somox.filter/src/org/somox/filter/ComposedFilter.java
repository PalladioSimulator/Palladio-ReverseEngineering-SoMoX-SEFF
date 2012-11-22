package org.somox.filter;

public class ComposedFilter<T> extends BaseFilter<T> {

	final private BaseFilter<T>[] innerFilter;
	
	public ComposedFilter(BaseFilter<T>...innerFilter) {
		super();
		
		this.innerFilter = innerFilter;
	}
	
	@Override
	public boolean passes(T object) {
		for (BaseFilter<T> inner : innerFilter) {
			if (!inner.passes(object)) {
				return false;
			}
		}
		return true;
	}

}
