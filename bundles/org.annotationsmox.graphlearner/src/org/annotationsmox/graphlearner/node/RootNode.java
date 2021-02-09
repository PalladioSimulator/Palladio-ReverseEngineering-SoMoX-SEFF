package org.annotationsmox.graphlearner.node;

import org.annotationsmox.graphlearner.ReturnOrientedVisitor;
import org.annotationsmox.graphlearner.Visitor;

public class RootNode extends NestableNode {

    public Node getChild() {
        if (getChildren().isEmpty()) {
            return null;
        }
        return getChildren().get(0);
    }

    @Override
    public void insertParent(NestableNode newParent) {
        throw new RuntimeException("Cannot insert parent for root node");
    }

    @Override
    public void setParent(NestableNode parent) {
        throw new RuntimeException("Cannot set parent for root node");
    }

    @Override
    public <R> void accept(Visitor<R> v, R arg) {
        v.visit(this, arg);
    }

    @Override
    public <R> R accept(ReturnOrientedVisitor<R> v) {
        return v.visit(this);
    }

    @Override
    public void insertSeriesSuccessor(Node successor) {
        SeriesNode n = new SeriesNode();
        n.setParent(this);
        successor.setParent(n);
    }

    @Override
    public void insertSeriesPredecessor(Node predecessor) {
        SeriesNode n = new SeriesNode();
        n.setParent(this);
        predecessor.setParent(n);
    }

    @Override
    public void insertParallel(Node parallel) {
        ParallelNode n = new ParallelNode();
        n.setParent(this);
        parallel.setParent(n);
    }

    @Override
    public String toString() {
        return "(R)";
    }
    
}
