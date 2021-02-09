package org.annotationsmox.graphlearner.node;

import org.annotationsmox.graphlearner.ReturnOrientedVisitor;

public class EnsureSeriesParent implements ReturnOrientedVisitor<SeriesNode> {

    private Node node;

    public EnsureSeriesParent(Node node) {
        this.node = node;
    }

    @Override
    public SeriesNode visit(SeriesNode currentParent) {
        return currentParent; // return unmodified parent
    }

    @Override
    public SeriesNode visit(ParallelNode currentParent) {
        SeriesNode newParent = new SeriesNode();

        // TODO copying attributes may not be appropriate in any case
        newParent.copyAttributesFrom(node);

        currentParent.replaceChild(node, newParent);
        node.setParent(newParent);
        return newParent;
    }

    @Override
    public SeriesNode visit(RootNode currentParent) {
        SeriesNode newParent = new SeriesNode();

        // TODO copying attributes may not be appropriate in any case
        newParent.copyAttributesFrom(node);

        currentParent.replaceChild(node, newParent);
        node.setParent(newParent);
        return newParent;
    }

    @Override
    public SeriesNode visit(LeafNode n) {
        throw new RuntimeException("This visitor should be applied to parent nodes only");
    }

    @Override
    public SeriesNode visit(EpsilonLeafNode n) {
        throw new RuntimeException("This visitor should be applied to parent nodes only");
    }

}