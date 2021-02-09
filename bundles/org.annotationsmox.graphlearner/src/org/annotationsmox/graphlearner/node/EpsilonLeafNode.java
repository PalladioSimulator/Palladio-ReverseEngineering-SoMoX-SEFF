package org.annotationsmox.graphlearner.node;

import org.annotationsmox.graphlearner.ReturnOrientedVisitor;
import org.annotationsmox.graphlearner.Visitor;

public class EpsilonLeafNode extends LeafNode {

    public static String EPSILON_CONTENT = "e";

    public EpsilonLeafNode() {
        super(EPSILON_CONTENT);
    }

    @Override
    public <R> R accept(ReturnOrientedVisitor<R> v) {
        return v.visit(this);
    }

    @Override
    public <R> void accept(Visitor<R> v, R arg) {
        v.visit(this, arg);
    }

}
