package org.annotationsmox.graphlearner.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.annotationsmox.graphlearner.DiffListener;
import org.annotationsmox.graphlearner.GraphLearner;
import org.annotationsmox.graphlearner.Path;
import org.annotationsmox.graphlearner.PathIntegrationListener;
import org.annotationsmox.graphlearner.ReorganizationListener;
import org.annotationsmox.graphlearner.Sequence;
import org.annotationsmox.graphlearner.node.Node;
import org.annotationsmox.graphlearner.visitor.TikZTreeVisitor;

/**
 * Use this tracer to track the actions of a {@link GraphLearner} as more and more paths are
 * integrated. The tracer result is TikZ code.
 * 
 * @author Philipp Merkle
 *
 */
public class TikzTracer<T> {

    private GraphLearner<T> learner;

    private DiffTracer diffTracer = new DiffTracer();

    private RecorganizationTracer reorganizationTracer = new RecorganizationTracer();

    private IntegationTracer integrationTracer = new IntegationTracer();

    private StringBuilder builder = new StringBuilder();

    private int[] levels = { 0, 0, 0 };

    private TikzTreeVisitorFactory visitorFactory;

    private TikzTracer(GraphLearner<T> learner, TikzTreeVisitorFactory visitorFactory) {
        this.learner = learner;
        this.visitorFactory = visitorFactory;
    }

    public DiffTracer getDiffTracer() {
        return diffTracer;
    }

    public RecorganizationTracer getReorganizationTracer() {
        return reorganizationTracer;
    }

    public IntegationTracer getIntegrationTracer() {
        return integrationTracer;
    }

    public void announceIntegration(Sequence<T> s) {
        printNumbering();
        builder.append("Integrate " + printSequence(s) + "\\\\\n");
    }

    private void printNumbering() {
        builder.append("\n\\ex. ");
        levels[1] = 0;
        levels[2] = 0;
    }

    private static String indent(int length) {
        String indentation = "";
        for (int i = 0; i < length; i++) {
            indentation += "\t";
        }
        return indentation;
    }

    public void forcePlot() {
        generateTikZ(Collections.emptyList());
    }

    public static <T> TikzTracer<T> trace(GraphLearner<T> learner, TikzTreeVisitorFactory visitorFactory) {
        TikzTracer<T> tracer = new TikzTracer<>(learner, visitorFactory);
        learner.addDiffListener(tracer.getDiffTracer());
        learner.addReorganizationListener(tracer.getReorganizationTracer());
        learner.addIntegrationListener(tracer.getIntegrationTracer());
        return tracer;
    }

    public String getResult() {
        return builder.toString();
    }

    private class DiffTracer implements DiffListener {

        @Override
        public void insertBefore(Node reference, List<Node> insertNodes) {
            printNumbering();
            String explanation = "Insert " + printNodes(insertNodes) + " before " + printNode(reference);
            builder.append(explanation + "\n");
        }

        @Override
        public void insertAfter(Node reference, List<Node> insertNodes) {
            printNumbering();
            String explanation = "Insert " + printNodes(insertNodes) + " after " + printNode(reference);
            builder.append(explanation + "\n");
        }

        @Override
        public void delete(Path deletePath) {
            printNumbering();
            String explanation = "Delete " + printPath(deletePath);
            builder.append(explanation + "\n");
        }

        @Override
        public void change(Path original, List<Node> revised) {
            printNumbering();
            String explanation = "Change " + printPath(original) + " to " + printNodes(revised);
            builder.append(explanation + "\n");
        }

        private void printNumbering() {
            builder.append(indent(1));
            if (levels[1] == 0) {
                builder.append("\\a. ");
            } else {
                builder.append("\\b. ");
            }
            levels[1]++;
            levels[2] = 0;
        }

    }

    private class RecorganizationTracer implements ReorganizationListener {

        private List<Node> boldNodes = new ArrayList<>();

        @Override
        public void insertSeriesSuccessor(Node reference, Node inserted) {
            printNumbering();
            String explanation = "Insert " + printNode(inserted) + " as series successor of " + printNode(reference);
            boldNodes.add(inserted);
            builder.append(explanation + ":\\\\\n");
            generateTikZ(boldNodes);
            boldNodes.clear();
        }

        @Override
        public void insertParallel(Node reference, Node inserted) {
            printNumbering();
            String explanation = "Insert " + printNode(inserted) + " parallel to " + printNode(reference);
            boldNodes.add(inserted);
            builder.append(explanation + ":\\\\\n");
            generateTikZ(boldNodes);
            boldNodes.clear();
        }

        @Override
        public void insertSeriesPredecessor(Node reference, Node inserted) {
            printNumbering();
            String explanation = "Insert " + printNode(inserted) + " as series predecessor of " + printNode(reference);
            boldNodes.add(inserted);
            builder.append(explanation + ":\\\\\n");
            generateTikZ(boldNodes);
            boldNodes.clear();
        }

        private void printNumbering() {
            builder.append(indent(2));
            if (levels[2] == 0) {
                builder.append("\\a. ");
            } else {
                builder.append("\\b. ");
            }
            levels[2]++;
        }

    }

    private class IntegationTracer implements PathIntegrationListener {

        @Override
        public void notifyIntegration(Path originalPath, Sequence<?> addPath, Path combinedPath) {
            // do nothing
        }

        @Override
        public void notifyClosestPath(Path path) {
            builder.append(" into closest path " + printPath(path) + "\n");
        }

    }

    private void generateTikZ(List<Node> boldNodes) {
        TikZTreeVisitor visitor = visitorFactory.create();
        visitor.setBoldNodes(boldNodes);
        learner.getGraph().traverse(visitor, 0);

        builder.append(visitor.asString());
        builder.append("\n");
    }

    private String printSequence(Sequence<T> sequence) {
        return "\\texttt{" + sequence.toString().replaceAll(" ", "") + "}";
    }

    private String printNodes(List<Node> nodes) {
        return "\\texttt{" + nodes.toString().replaceAll(" ", "") + "}";
    }

    private String printPath(Path path) {
        return "\\texttt{" + path.excludeEpsilon().excludeNonLeaves().toString().replaceAll(" ", "") + "}";
    }

    private String printNode(Node node) {
        return "\\texttt{" + node.toString().replace("e", "$\\epsilon$") + "}";
    }

}
