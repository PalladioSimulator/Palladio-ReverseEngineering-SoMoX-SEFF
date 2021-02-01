package org.annotationsmox.graphlearner;

import org.annotationsmox.graphlearner.node.EpsilonLeafNode;
import org.annotationsmox.graphlearner.node.LeafNode;
import org.annotationsmox.graphlearner.node.ParallelNode;
import org.annotationsmox.graphlearner.node.RootNode;
import org.annotationsmox.graphlearner.node.SeriesNode;

/**
 * 
 * @author Philipp Merkle
 *
 * @param <T>
 *            the type of the visit method's second parameter
 */
public interface Visitor<T> {

    void visit(LeafNode n, T arg);

    void visit(EpsilonLeafNode n, T arg);

    void visit(ParallelNode n, T arg);

    void visit(SeriesNode n, T arg);

    void visit(RootNode n, T arg);

}
