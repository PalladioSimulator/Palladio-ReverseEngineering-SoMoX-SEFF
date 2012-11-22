package org.somox.filter;

public class NotFilter<T> extends BaseFilter<T> {
	
	private BaseFilter<T> innerFilter = null;
	
	public NotFilter(BaseFilter<T> innerFilter) {
		super();
		this.innerFilter = innerFilter;
	}


	@Override
	public boolean passes(T object) {
		return !innerFilter.passes(object);
	}

}
