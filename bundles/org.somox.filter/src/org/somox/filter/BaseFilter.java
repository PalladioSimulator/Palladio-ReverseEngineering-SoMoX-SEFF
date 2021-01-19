package org.somox.filter;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * See documentation at
 * http://www.erik-rasmussen.com/blog/2008/01/18/the-filter-pattern-java-conditional-abstraction-
 * with-iterables/
 *
 * @author Steffen Becker
 * @param <T>
 *            type of the objects to be filtered
 */
public abstract class BaseFilter<T> {
    public abstract boolean passes(T object);

    public Iterator<T> filter(final Iterator<T> iterator) {
        return new FilterIterator(iterator);
    }

    public Iterable<T> filter(final Iterable<T> iterable) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return BaseFilter.this.filter(iterable.iterator());
            }
        };
    }

    private class FilterIterator implements Iterator<T> {
        private final Iterator<T> iterator;
        private T next;

        private FilterIterator(final Iterator<T> iterator) {
            this.iterator = iterator;
            this.toNext();
        }

        @Override
        public boolean hasNext() {
            return this.next != null;
        }

        @Override
        public T next() {
            if (this.next == null) {
                throw new NoSuchElementException();
            }
            final T returnValue = this.next;
            this.toNext();
            return returnValue;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void toNext() {
            this.next = null;
            while (this.iterator.hasNext()) {
                final T item = this.iterator.next();
                if (item != null && BaseFilter.this.passes(item)) {
                    this.next = item;
                    break;
                }
            }
        }
    }
}