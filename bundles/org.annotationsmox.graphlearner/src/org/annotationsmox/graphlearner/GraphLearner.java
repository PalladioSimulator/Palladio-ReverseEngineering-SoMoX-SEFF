package org.annotationsmox.graphlearner;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.annotationsmox.graphlearner.node.EpsilonLeafNode;
import org.annotationsmox.graphlearner.node.LeafNode;
import org.annotationsmox.graphlearner.node.Node;
import org.annotationsmox.graphlearner.node.ParallelNode;
import org.annotationsmox.graphlearner.node.SeriesNode;
import org.apache.log4j.Logger;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import difflib.myers.Equalizer;

/**
 * Constructs a two-terminal series-parallel graph [1] from a set of symbol sequences (e.g. a set of
 * invoked methods) such that the resulting graph contains every sequence.
 * 
 * [1] http://www.graphclasses.org/classes/gc_875.html
 * 
 * @author Philipp Merkle
 *
 */
public class GraphLearner<T> {

    private static final Logger LOG = Logger.getLogger(GraphLearner.class);

    private SPGraph graph;

    private List<PathIntegrationListener> integrationListeners = new CopyOnWriteArrayList<>();

    private List<DiffListener> diffListeners = new CopyOnWriteArrayList<>();

    private List<ReorganizationListener> reorganizationListeners = new CopyOnWriteArrayList<>();

    public void integrateSequence(T... elements) {
        integrateSequence(Sequence.from(elements));
    }

    public void integrateSequence(Sequence<T> sequence) {
        if (graph == null) {
            graph = SPGraph.fromSequence(sequence);
            Path integratedPath = findPathClosestTo(sequence);
            notifyIntegrationListeners(null, sequence, integratedPath);
        } else {
            Path closestPath = findPathClosestTo(sequence);
            integrationListeners.forEach(l -> l.notifyClosestPath(closestPath));
            integrate(closestPath, sequence);
            notifyIntegrationListeners(closestPath, sequence, findPathClosestTo(sequence));
        }
    }

    public void addIntegrationListener(PathIntegrationListener l) {
        integrationListeners.add(l);
    }

    public void removeIntegrationListener(PathIntegrationListener l) {
        integrationListeners.remove(l);
    }

    public void addReorganizationListener(ReorganizationListener l) {
        reorganizationListeners.add(l);
    }

    public void removeReorganizationListener(ReorganizationListener l) {
        reorganizationListeners.remove(l);
    }

    public void addDiffListener(DiffListener l) {
        diffListeners.add(l);
    }

    public void removeDiffListener(DiffListener l) {
        diffListeners.remove(l);
    }

    /**
     * @param originalPath
     *            the unmodified path before integration of {@code addPath}; {@code null}, if a path
     *            is integrated into an empty {@link SPGraph}.
     * @param addPath
     *            the path to be integrated into {@code originalPath}
     * @param combinedPath
     *            the path resulting from integrating {@code addPath} into {@code originalPath}
     * 
     */
    private void notifyIntegrationListeners(Path originalPath, Sequence<?> addPath, Path combinedPath) {
        integrationListeners.forEach(l -> l.notifyIntegration(originalPath, addPath, combinedPath));
    }

    public SPGraph getGraph() {
        return graph;
    }

    /**
     * Finds and returns the path closest to the specified path.
     * 
     * @param path
     * @return
     */
    public Path findPathClosestTo(List<Node> nodes) {
        List<Path> paths = graph.allPaths();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Collected paths: " + paths);
        }
        int minCost = Integer.MAX_VALUE;
        Path minPath = null;
        for (Path p : paths) {
            Patch<Node> patch = DiffUtils.diff(p.excludeNonLeaves().excludeEpsilon().getNodes(), nodes,
                    new NodeEqualiser());
            int cost = cost(patch);
            if (cost < minCost) {
                minCost = cost;
                minPath = p;
            }
        }
        return minPath;
    }

    public Path findPathClosestTo(Sequence<T> sequence) {
        return findPathClosestTo(Node.from(sequence));
    }

    // TODO cost calculation could be improved
    protected int cost(Patch<Node> patch) {
        int cost = 0;
        for (Delta<Node> d : patch.getDeltas()) {
            cost += Math.max(d.getOriginal().size(), d.getRevised().size());
        }
        return cost;
    }

    public boolean contains(Path path) {
        Path closestPath = findPathClosestTo(path.getNodes());
        Patch<Node> patch = differences(closestPath, path);
        return patch.getDeltas().isEmpty();
    }

    protected void integrate(Path closestPath, Sequence<T> sequence) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Integrating sequence " + sequence + " into path " + closestPath);
        }

        Patch<Node> patch = differences(closestPath, sequence);
        for (Delta<Node> delta : patch.getDeltas()) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Delta: " + delta);
            }
            Path original = Path.fromNodes(delta.getOriginal().getLines());
            List<Node> revised = delta.getRevised().getLines();
            switch (delta.getType()) {
            case CHANGE:
                diffListeners.forEach(l -> l.change(original, revised));
                addAlternative(original, revised);
                break;
            case DELETE: {
                diffListeners.forEach(l -> l.delete(original));
                addAlternative(original, Node.asList(createEpsilonNode()));
                break;
            }
            case INSERT:
                /*
                 * special case because there is no node to the *left* to be used as a reference;
                 * instead we used the source node (to be be confused with the root) as a reference
                 * and insert to the left of the reference node.
                 */
                if (delta.getOriginal().getPosition() == 0) {
                    diffListeners.forEach(l -> l.insertBefore(graph.getSource(), revised));
                    Node reference = graph.getSource();
                    insertLeft(reference, revised);
                } else { // regular case
                    Node reference = closestPath.excludeEpsilon().excludeNonLeaves().getNodes()
                            .get(delta.getOriginal().getPosition() - 1);
                    diffListeners.forEach(l -> l.insertAfter(reference, revised));
                    insertRight(reference, revised);
                }
                break;
            }
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("Result: " + graph);
        }
    }

    /**
     * Introduces an {@code alternative} path to the given {@code original} path.
     */
    private void addAlternative(Path original, List<Node> alternative) {
        List<Node> originalSubtrees = Node.findCompletelyCoveredSubtrees(original.getNodes());
        List<Node> alternativeSubtrees = Node.findCompletelyCoveredSubtrees(alternative);

        List<Path> groups = groupBySiblings(originalSubtrees);
        arrangeParallel(groups.get(0), alternativeSubtrees);
        for (int i = 1; i < groups.size(); i++) {
            arrangeParallel(groups.get(i), Node.asList(createEpsilonNode()));
        }
    }

    /**
     * Inserts {@code nodes} to the left of the given {@code reference} node.
     * 
     * @param reference
     *            the reference node
     * @param insert
     *            the nodes to be inserted
     */
    private void insertLeft(Node reference, List<Node> insert) {
        Node firstInsertNode = insert.get(0);
        SPGraph.insertSeriesPredecessor(reference, firstInsertNode);
        reorganizationListeners.forEach(l -> l.insertSeriesPredecessor(reference, firstInsertNode));
        arrangeSeries(insert);

        Node epsilon = createEpsilonNodeFor(reference);
        addAlternative(Path.fromNodes(insert), Node.asList(epsilon));
    }

    /**
     * Inserts {@code nodes} to the right of the given {@code reference} node.
     * 
     * @param reference
     *            the reference node
     * @param insert
     *            the nodes to be inserted
     */
    private void insertRight(Node reference, List<Node> insert) {
        Node firstInsertNode = insert.get(0);
        SPGraph.insertSeriesSuccessor(reference, firstInsertNode);
        reorganizationListeners.forEach(l -> l.insertSeriesSuccessor(reference, firstInsertNode));
        arrangeSeries(insert);

        Node epsilon = createEpsilonNodeFor(reference);
        addAlternative(Path.fromNodes(insert), Node.asList(epsilon));
    }

    /**
     * Arranges path and nodes parallel to each other so that 1) {@code path} and {@code nodes} both
     * are arranged as a series, and 2) the resulting two {@link SeriesNode} parents are arrange as
     * children of a {@link ParallelNode}.
     * 
     * @param path
     *            the path to be arranged as series under a parallel node
     * @param nodes
     *            the nodes to be arranged as another series under the same parallel node
     */
    private void arrangeParallel(Path path, List<Node> nodes) {
        SPGraph.insertParallel(path.getNodes().get(0), nodes.get(0));
        reorganizationListeners.forEach(l -> l.insertParallel(path.getNodes().get(0), nodes.get(0)));
        arrangeSeries(path.getNodes());
        arrangeSeries(nodes);
    }

    /**
     * Arranges {@code nodes} as children of a common {@link SeriesNode} parent. If necessary, that
     * series node will be created.
     * 
     * @param nodes
     *            the nodes to be arrange as siblings under a series node
     */
    private void arrangeSeries(List<Node> nodes) {
        for (int i = 1; i < nodes.size(); i++) {
            Node reference = nodes.get(i - 1);
            Node successor = nodes.get(i);
            SPGraph.insertSeriesSuccessor(reference, successor);
            reorganizationListeners.forEach(l -> l.insertSeriesSuccessor(reference, successor));
        }
    }

    private EpsilonLeafNode createEpsilonNode() {
        return new EpsilonLeafNode();
    }

    private EpsilonLeafNode createEpsilonNodeFor(Node node) {
        EpsilonLeafNode epsilonNode = new EpsilonLeafNode();
        epsilonNode.copyAttributesFrom(node);
        return epsilonNode;
    }

    /**
     * @return group of nodes so that within each group the contained nodes form a chain with
     *         respect to the predecessor-successor relationship.
     */
    private List<Path> groupBySiblings(List<Node> node) {
        List<Path> siblingGroups = new LinkedList<>();
        Node lastNode = null;
        List<Node> currentGroup = null;
        for (Node currentNode : node) {
            if (lastNode == null) {
                currentGroup = new LinkedList<>();
                siblingGroups.add(Path.fromNodes(currentGroup));
            } else if (!areSiblings(lastNode, currentNode)) {
                currentGroup = new LinkedList<>();
                siblingGroups.add(Path.fromNodes(currentGroup));
            }
            currentGroup.add(currentNode);
            lastNode = currentNode;
        }
        return siblingGroups;
    }

    /**
     * @return true, if both nodes have the same parent node and both nodes are in a direct
     *         successor-predecessor relationship, without any other node in between.
     */
    private boolean areSiblings(Node nodeOne, Node nodeTwo) {
        if (!nodeOne.getParent().equals(nodeTwo.getParent())) {
            return false;
        }
        int idxOne = nodeOne.getParent().getChildren().indexOf(nodeOne);
        int idxTwo = nodeTwo.getParent().getChildren().indexOf(nodeTwo);
        int distance = Math.abs(idxOne - idxTwo);
        return distance == 1;
    }

    private Patch<Node> differences(Path left, Path right) {
        List<Node> leftNodes = left.excludeEpsilon().excludeNonLeaves().getNodes();
        List<Node> rightNodes = right.excludeEpsilon().excludeNonLeaves().getNodes();
        Patch<Node> patch = DiffUtils.diff(leftNodes, rightNodes, new NodeEqualiser());
        return patch;
    }

    private Patch<Node> differences(Path left, Sequence<T> right) {
        List<Node> leftNodes = left.excludeEpsilon().excludeNonLeaves().getNodes();
        List<Node> rightNodes = Node.from(right);
        Patch<Node> patch = DiffUtils.diff(leftNodes, rightNodes, new NodeEqualiser());
        return patch;
    }

    private static class NodeEqualiser implements Equalizer<Node> {
        @Override
        public boolean equals(Node original, Node revised) {
            return (((LeafNode) original).getContent().equals(((LeafNode) revised).getContent()));
        }
    }

}
