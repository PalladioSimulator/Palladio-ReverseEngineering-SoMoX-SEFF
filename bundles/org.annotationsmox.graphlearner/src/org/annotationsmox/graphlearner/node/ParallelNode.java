package org.annotationsmox.graphlearner.node;

import org.annotationsmox.graphlearner.ReturnOrientedVisitor;
import org.annotationsmox.graphlearner.Visitor;

public class ParallelNode extends NestableNode {

    @Override
    public <R> void accept(Visitor<R> v, R arg) {
        v.visit(this, arg);
    }

    @Override
    public <R> R accept(ReturnOrientedVisitor<R> v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        return "(P)";
    }
    
}
