package org.annotationsmox.graphlearner;

import java.util.List;

import org.annotationsmox.graphlearner.GraphLearner;
import org.annotationsmox.graphlearner.node.Node;
import org.annotationsmox.graphlearner.visitor.AllLeavesVisitor;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestNodes {

    private GraphLearner<String> learner1;

    private GraphLearner<String> learner2;

    @BeforeClass
    public static void setup() {
        // log4j basic setup
        BasicConfigurator.configure();
    }

    @Before
    public void beforeTest() {
        learner1 = new GraphLearner<>();
        learner2 = new GraphLearner<>();
    }

    @Test
    public void equalsTest1() {
        learner1.integrateSequence("A");
        learner2.integrateSequence("A");
        Node n1 = learner1.getGraph().allPaths().get(0).getNodes().get(0);
        Node n2 = learner2.getGraph().allPaths().get(0).getNodes().get(0);
        Assert.assertEquals(n1, n2);
    }

    @Test
    public void equalsTest2() {
        learner1.integrateSequence("A", "A");
        learner2.integrateSequence("A", "A");
        Node first1 = learner1.getGraph().allPaths().get(0).getNodes().get(0);
        Node second1 = learner1.getGraph().allPaths().get(0).getNodes().get(1);
        Node first2 = learner2.getGraph().allPaths().get(0).getNodes().get(0);
        Node second2 = learner2.getGraph().allPaths().get(0).getNodes().get(1);
        Assert.assertEquals(first1, first2);
        Assert.assertEquals(second1, second2);
        Assert.assertNotEquals(first1, second2);
        Assert.assertNotEquals(first2, second1);
    }

    @Test
    public void testPathToRoot() {
        learner1.integrateSequence("A", "B");
        learner1.integrateSequence("A", "B", "C");
        learner1.integrateSequence("A", "C");

        Node aNode = null;
        Node bNode = null;
        Node cNode = null;

        // find leaf nodes
        AllLeavesVisitor visitor = new AllLeavesVisitor();
        learner1.getGraph().traverse(visitor);
        List<Node> leaves = visitor.getResult();

        for (Node lastNode : leaves) {
            if (lastNode.toString().equals("A")) {
                aNode = lastNode;
            }
            if (lastNode.toString().equals("B")) {
                bNode = lastNode;
            }
            if (lastNode.toString().equals("C")) {
                cNode = lastNode;
            }
        }

        Assert.assertEquals(aNode.pathToRoot().toString(), "[(R), (S), A]");
        Assert.assertEquals(bNode.pathToRoot().toString(), "[(R), (S), (P), B]");
        Assert.assertEquals(cNode.pathToRoot().toString(), "[(R), (S), (P), C]");
    }

    @Test
    public void testCommonParent() {
        // TODO
    }

}
