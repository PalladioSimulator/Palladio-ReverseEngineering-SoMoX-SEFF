package org.somox.filter;

import java.util.HashSet;
import java.util.Set;

public class FilteredCollectionsFactory {
	
	public static <T> Set<T> getFilteredHashSet(BaseFilter<T> filter, Iterable<T> originalSet) {
		Set<T> result = new HashSet<T>();
		
		for (T element : filter.filter(originalSet))
			result.add(element);
		
		return result;
	}
}
