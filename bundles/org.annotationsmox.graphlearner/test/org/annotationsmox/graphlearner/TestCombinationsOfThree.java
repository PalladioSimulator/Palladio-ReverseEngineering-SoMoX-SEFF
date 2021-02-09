package org.annotationsmox.graphlearner;

import org.annotationsmox.graphlearner.GraphLearner;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCombinationsOfThree {

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
    public void sequenceOfThree() {
        learner.integrateSequence("A", "B", "C");
        Assert.assertEquals("ABC", learner.getGraph().toString());
    }

    @Test
    public void missingStart() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("B", "C");
        Assert.assertEquals("[A|]BC", learner.getGraph().toString());
    }

    @Test
    public void missingMiddle() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("A", "C");
        Assert.assertEquals("A[B|]C", learner.getGraph().toString());
    }

    @Test
    public void missingEnd() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("A", "B");
        Assert.assertEquals("AB[C|]", learner.getGraph().toString());
    }

    @Test
    public void missingStartAndEnd() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("B");
        Assert.assertEquals("[A|]B[C|]", learner.getGraph().toString());
    }

    @Test
    public void missingStartAndMiddle() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("C");
        Assert.assertEquals("[AB|]C", learner.getGraph().toString()); // or [A|][B|]C?
    }

    @Test
    public void missingMiddleAndEnd() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("A");
        Assert.assertEquals("A[BC|]", learner.getGraph().toString()); // or A[B|][C|]?
    }

    @Test
    public void missingAll() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("D");
        Assert.assertEquals("[ABC|D]", learner.getGraph().toString()); // or [ABC|D]?
    }

}
