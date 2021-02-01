package org.annotationsmox.graphlearner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.annotationsmox.graphlearner.node.EpsilonLeafNode;
import org.annotationsmox.graphlearner.node.LeafNode;
import org.annotationsmox.graphlearner.node.NestableNode;
import org.annotationsmox.graphlearner.node.Node;

public class Path implements Cloneable {

    private List<Node> nodes = new ArrayList<>();

    public Path() {
        nodes = new ArrayList<>();
    }

    public static Path emptyPath() {
        return new Path();
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public static Path commonPrefix(List<Path> pathList) {
        if (pathList.isEmpty()) {
            throw new IllegalArgumentException("Path list may not be null.");
        }
        if (pathList.size() == 1) {
            Path path = pathList.get(0);
            return path.subPath(0, path.size() - 1);
        }

        Path referencePath = pathList.get(0);

        // determine shortest path
        int shortestLength = Integer.MAX_VALUE;
        for (Path p : pathList) { // TODO what about epsilon nodes?
            int pathLength = p.getNodes().size();
            shortestLength = Math.min(shortestLength, pathLength);
        }

        int commonPrefixLength = 0;
        outer: for (int i = 0; i < shortestLength; i++) {
            Node referenceNode = referencePath.getNodes().get(i);
            for (Path p : pathList) {
                Node node = p.getNodes().get(i);
                if (!node.equals(referenceNode)) {
                    break outer;
                }
            }
            commonPrefixLength++;
        }

        // determine and return prefix for the given prefix length
        if (commonPrefixLength > 0) {
            return Path.fromNodes(referencePath.getNodes().subList(0, commonPrefixLength));
        } else {
            return Path.emptyPath();
        }
    }

    public static Path commonPrefix(Path path1, Path path2, Path... paths) {
        List<Path> pathList = new ArrayList<>();
        pathList.add(path1);
        pathList.add(path2);
        pathList.addAll(Arrays.asList(paths));

        return commonPrefix(pathList);
    }

    public static Path fromNodes(Node... nodes) {
        return fromNodes(Arrays.asList(nodes));
    }

    public static <T> Path fromSequence(Sequence<T> sequence) {
        List<Node> nodes = new LinkedList<>();
        for (T e : sequence) {
            nodes.add(new LeafNode(e));
        }
        return fromNodes(nodes);
    }

    public static Path fromNodes(List<Node> nodes) {
        Path path = new Path();
        path.nodes = nodes;
        return path;
    }

    @Override
    public String toString() {
        // return this.excludeNonLeaves().getNodes().toString();
        return this.getNodes().toString();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public int size() {
        return nodes.size();
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    public Node first() {
        return nodes.get(0);
    }

    /**
     * 
     * @param fromIndex
     *            inclusive
     * @param toIndex
     *            exclusive
     * 
     * @return
     */
    public Path subPath(int fromIndex, int toIndex) {
        return fromNodes(nodes.subList(fromIndex, toIndex));
    }

    /**
     * 
     * @param fromIndex
     *            inclusive
     * @return
     */
    public Path subPathStartingAt(int fromIndex) {
        return subPath(fromIndex, nodes.size());
    }

    // TODO better use visitor to avoid instanceof?
    public Path excludeEpsilon() {
        List<Node> result = new ArrayList<>();
        for (Node n : getNodes()) {
            if (!(n instanceof EpsilonLeafNode)) {
                result.add(n);
            }
        }
        return fromNodes(result);
    }

    // TODO better use visitor to avoid instanceof?
    public Path excludeNonLeaves() {
        List<Node> result = new ArrayList<>();
        for (Node n : getNodes()) {
            if (!(n instanceof NestableNode)) {
                result.add(n);
            }
        }
        return fromNodes(result);
    }

    @Override
    public Path clone() throws CloneNotSupportedException {
        Path cloned = new Path();
        cloned.nodes = new ArrayList<>(nodes);
        return cloned;
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
        Path other = (Path) obj;
        if (nodes == null) {
            if (other.nodes != null)
                return false;
        } else if (!nodes.equals(other.nodes))
            return false;
        return true;
    }

}
