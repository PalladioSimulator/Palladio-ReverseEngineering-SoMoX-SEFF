package org.annotationsmox.graphlearner;

import org.annotationsmox.graphlearner.GraphLearner;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSpecialCases {

    private GraphLearner<String> learner;

    @BeforeClass
    public static void setup() {
        // log4j basic setup
        BasicConfigurator.configure();
    }

    @Before
    public void beforeTest() {
        learner = new GraphLearner<>();
    }

    @Test
    public void inverseOrderWithCommonPrefix() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("A", "C", "B");
        Assert.assertEquals("A[B|]C[B|]", learner.getGraph().toString()); // or A[BC|CB]?
    }

    @Test
    public void inverseOrderWithoutCommonPrefix() {
        learner.integrateSequence("B", "C");
        learner.integrateSequence("C", "B");
        Assert.assertEquals("[B|]C[B|]", learner.getGraph().toString()); // or [BC|CB]?
    }

    @Test
    public void insertHeadOneNode() {
        learner.integrateSequence("B", "C");
        learner.integrateSequence("A", "B", "C");
        Assert.assertEquals("[A|]BC", learner.getGraph().toString());
    }

    @Test
    public void insertHeadTwoNodes() {
        learner.integrateSequence("C", "D");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("[AB|]CD", learner.getGraph().toString()); // TODO or [A|][B|]CD?
    }

    @Test
    public void insertTailOneNode() {
        learner.integrateSequence("A", "B");
        learner.integrateSequence("A", "B", "C");
        Assert.assertEquals("AB[C|]", learner.getGraph().toString());
    }

    @Test
    public void insertTailTwoNodes() {
        learner.integrateSequence("A", "B");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("AB[CD|]", learner.getGraph().toString());
    }

    @Test
    public void repeatedNodes() {
        learner.integrateSequence("A", "B", "A", "B");
        learner.integrateSequence("A", "B", "A", "B");
        Assert.assertEquals("ABAB", learner.getGraph().toString());
    }

}
