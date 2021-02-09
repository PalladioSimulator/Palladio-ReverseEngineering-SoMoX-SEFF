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
 * @param <R>
 *            the visit method's return type
 */
public interface ReturnOrientedVisitor<R> {

    R visit(LeafNode n);

    R visit(EpsilonLeafNode n);

    R visit(ParallelNode n);

    R visit(SeriesNode n);

    R visit(RootNode n);

}
