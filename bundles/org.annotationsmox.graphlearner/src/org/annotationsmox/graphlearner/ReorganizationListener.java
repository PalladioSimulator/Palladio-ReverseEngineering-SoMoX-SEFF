package org.annotationsmox.graphlearner;

import org.annotationsmox.graphlearner.node.Node;

public interface ReorganizationListener {

    public void insertParallel(Node node, Node parallel);

    public void insertSeriesSuccessor(Node node, Node successor);

    public void insertSeriesPredecessor(Node node, Node predecessor);
    
}
