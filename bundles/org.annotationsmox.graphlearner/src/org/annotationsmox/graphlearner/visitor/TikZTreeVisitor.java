package org.annotationsmox.graphlearner.visitor;

import java.util.Collections;
import java.util.List;

import org.annotationsmox.graphlearner.Visitor;
import org.annotationsmox.graphlearner.node.EpsilonLeafNode;
import org.annotationsmox.graphlearner.node.LeafNode;
import org.annotationsmox.graphlearner.node.Node;
import org.annotationsmox.graphlearner.node.ParallelNode;
import org.annotationsmox.graphlearner.node.RootNode;
import org.annotationsmox.graphlearner.node.SeriesNode;
import org.annotationsmox.graphlearner.util.TikzTreeVisitorFactory;

public class TikZTreeVisitor implements Visitor<Integer> {

    private StringBuilder builder = new StringBuilder();

    private List<Node> boldNodes = Collections.emptyList();

    protected enum Shape {
        SQUARED, CIRCLED;
    }

    public void setBoldNodes(List<Node> boldNodes) {
        this.boldNodes = boldNodes;
    }

    @Override
    public void visit(LeafNode n, Integer depth) {
        builder.append(indent(depth));

        boolean bold = boldNodes.contains(n); // && boldNodes.get(boldNodes.indexOf(n)) == n
        builder.append("[" + printNode(n, n.getContent().toString(), Shape.SQUARED, bold));
        builder.append("]\n");
    }

    @Override
    public void visit(EpsilonLeafNode n, Integer depth) {
        builder.append(indent(depth));
        boolean bold = boldNodes.contains(n); // && boldNodes.get(boldNodes.indexOf(n)) == n
        builder.append("[" + printNode(n, "$\\epsilon$", Shape.SQUARED, bold) + "]\n");
    }

    @Override
    public void visit(ParallelNode n, Integer depth) {
        builder.append(indent(depth));
        builder.append("[" + printNode(n, "P", Shape.CIRCLED));
        builder.append("\n");
        for (Node child : n.getChildren()) {
            child.accept(this, depth + 1);
        }
        builder.append("]");
    }

    @Override
    public void visit(SeriesNode n, Integer depth) {
        builder.append(indent(depth));
        builder.append("[" + printNode(n, "S", Shape.CIRCLED));
        builder.append("\n");
        for (Node child : n.getChildren()) {
            child.accept(this, depth + 1);
        }
        builder.append("]");
    }

    @Override
    public void visit(RootNode n, Integer depth) {
        builder.append("\\tikzset{every label/.style={font=\\scriptsize}}\n");
        builder.append("\\begin{forest}\n");
        builder.append("baseline,\n");
        builder.append("circled/.style={circle,draw},\n");
        builder.append("squared/.style={rectangle,draw}\n");
        n.getChild().accept(this, depth + 1);
        builder.append("\\end{forest}");
    }

    public String asString() {
        return builder.toString();
    }

    private static String indent(int length) {
        String indentation = "";
        for (int i = 0; i < length; i++) {
            indentation += "\t";
        }
        return indentation;
    }

    protected String printNode(Node node, String name, Shape shape) {
        return printNode(node, name, shape, false);
    }

    protected String printNode(Node node, String name, Shape shape, boolean bold) {
        return printNode(node, name, shape, bold, null);
    }

    protected String printNode(Node node, String name, Shape shape, String label) {
        return printNode(node, name, shape, false, label);
    }

    protected String printNode(Node node, String name, Shape shape, boolean bold, String label) {
        String shapeCode;
        switch (shape) {
        case CIRCLED:
            shapeCode = ",circled";
            break;
        case SQUARED:
            shapeCode = ",squared";
            break;
        default:
            throw new RuntimeException("Don't know how to handle shapes of type " + shape);
        }
        String boldCode = bold ? ",very thick" : "";
        String labelCode = label != null ? ",label=" + label : "";
        return name + shapeCode + boldCode + labelCode;
    }
    
    public static TikzTreeVisitorFactory getFactory() {
        return new TikzTreeVisitorFactory() {
            
            @Override
            public TikZTreeVisitor create() {
                return new TikZTreeVisitor();
            }
        };
    }

}
