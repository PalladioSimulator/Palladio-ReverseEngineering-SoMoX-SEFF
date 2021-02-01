package org.annotationsmox.graphlearner.visitor;

import java.util.LinkedList;
import java.util.List;

import org.annotationsmox.graphlearner.Visitor;
import org.annotationsmox.graphlearner.node.EpsilonLeafNode;
import org.annotationsmox.graphlearner.node.LeafNode;
import org.annotationsmox.graphlearner.node.Node;
import org.annotationsmox.graphlearner.node.ParallelNode;
import org.annotationsmox.graphlearner.node.RootNode;
import org.annotationsmox.graphlearner.node.SeriesNode;

public class AllLeavesVisitor implements Visitor<Void> {

    private List<Node> leaves = new LinkedList<>();

    private boolean includeEpsilon = false; // TODO make configurable

    @Override
    public void visit(LeafNode n, Void arg) {
        leaves.add(n);
    }

    @Override
    public void visit(EpsilonLeafNode n, Void arg) {
        if (includeEpsilon) {
            leaves.add(n);
        }
    }

    @Override
    public void visit(ParallelNode n, Void arg) {
        for (Node child : n.getChildren()) {
            child.accept(this, arg);
        }
    }

    @Override
    public void visit(SeriesNode n, Void arg) {
        for (Node child : n.getChildren()) {
            child.accept(this, arg);
        }
    }

    @Override
    public void visit(RootNode n, Void arg) {
        n.getChild().accept(this, arg);
    }

    public List<Node> getResult() {
        return leaves;
    }

}
