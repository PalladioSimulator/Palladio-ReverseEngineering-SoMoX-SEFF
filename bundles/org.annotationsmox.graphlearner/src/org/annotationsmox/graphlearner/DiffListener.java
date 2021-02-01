package org.annotationsmox.graphlearner;

import java.util.List;

import org.annotationsmox.graphlearner.node.Node;

public interface DiffListener {

    void change(Path original, List<Node> revised);

    void delete(Path path);

    void insertAfter(Node reference, List<Node> insertNodes);

    void insertBefore(Node reference, List<Node> insertNodes);

}
