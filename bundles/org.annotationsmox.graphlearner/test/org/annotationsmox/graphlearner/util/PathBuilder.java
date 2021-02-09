package org.annotationsmox.graphlearner.util;

import java.util.ArrayList;
import java.util.List;

import org.annotationsmox.graphlearner.Path;
import org.annotationsmox.graphlearner.node.LeafNode;
import org.annotationsmox.graphlearner.node.Node;

public class PathBuilder {

    public static Path path(String... nodeNames) {
        List<Node> nodes = new ArrayList<>();
        for (String name : nodeNames) {
            nodes.add(new LeafNode(name));
        }
        return Path.fromNodes(nodes);
    }

}
