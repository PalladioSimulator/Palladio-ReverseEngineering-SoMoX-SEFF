package org.annotationsmox.graphlearner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.annotationsmox.graphlearner.node.LeafNode;
import org.annotationsmox.graphlearner.node.Node;

public class Sequence<T> implements Cloneable, Iterable<T> {

    private List<T> nodes = new ArrayList<>();

    public Sequence() {
        nodes = new ArrayList<>();
    }

    public static <T> Sequence<T> emptySequence() {
        return new Sequence<>();
    }

    public void add(T element) {
        nodes.add(element);
    }

    @Override
    public String toString() {
        return nodes.toString();
    }

    public int size() {
        return nodes.size();
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sequence other = (Sequence) obj;
        if (nodes == null) {
            if (other.nodes != null)
                return false;
        } else if (!nodes.equals(other.nodes))
            return false;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return nodes.iterator();
    }

    public static <T> Sequence<T> from(T... elements) {
        Sequence<T> sequence = new Sequence<>();
        for (T e : elements) {
            sequence.add(e);
        }
        return sequence;
    }

    public static <T> Sequence<T> from(List<Node> nodes) {
        Sequence<T> sequence = new Sequence<>();
        for (Node n : nodes) {
            sequence.add((T) ((LeafNode) n).getContent()); // TODO
        }
        return sequence;
    }

}
