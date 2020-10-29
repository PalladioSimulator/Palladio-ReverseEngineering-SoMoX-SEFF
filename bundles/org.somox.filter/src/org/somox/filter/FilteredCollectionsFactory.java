package org.somox.filter;

import java.util.HashSet;
import java.util.Set;

public class FilteredCollectionsFactory {

    public static <T> Set<T> getFilteredHashSet(final BaseFilter<T> filter, final Iterable<T> originalSet) {
        final Set<T> result = new HashSet<T>();

        for (final T element : filter.filter(originalSet)) {
            result.add(element);
        }

        return result;
    }
}
