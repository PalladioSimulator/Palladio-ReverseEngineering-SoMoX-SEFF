package org.annotationsmox.graphlearner;

import org.annotationsmox.graphlearner.GraphLearner;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCombinationsOfThreeAdditional {

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
    public void additionalStart() {
        learner.integrateSequence("B", "C");
        learner.integrateSequence("A", "B", "C");
        Assert.assertEquals("[A|]BC", learner.getGraph().toString());
    }

    @Test
    public void additionalMiddle() {
        learner.integrateSequence("A", "C");
        learner.integrateSequence("A", "B", "C");
        Assert.assertEquals("A[B|]C", learner.getGraph().toString());
    }

    @Test
    public void additionalEnd() {
        learner.integrateSequence("A", "B");
        learner.integrateSequence("A", "B", "C");
        Assert.assertEquals("AB[C|]", learner.getGraph().toString());
    }

    @Test
    public void additionalStartAndEnd() {
        learner.integrateSequence("B");
        learner.integrateSequence("A", "B", "C");
        Assert.assertEquals("[A|]B[C|]", learner.getGraph().toString());
    }

    @Test
    public void additionalStartAndMiddle() {
        learner.integrateSequence("C");
        learner.integrateSequence("A", "B", "C");
        Assert.assertEquals("[AB|]C", learner.getGraph().toString());
    }

    @Test
    public void additionalMiddleAndEnd() {
        learner.integrateSequence("A");
        learner.integrateSequence("A", "B", "C");
        Assert.assertEquals("A[BC|]", learner.getGraph().toString());
    }

}
