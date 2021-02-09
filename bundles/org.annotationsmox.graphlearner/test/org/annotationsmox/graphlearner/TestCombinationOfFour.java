package org.annotationsmox.graphlearner;

import org.annotationsmox.graphlearner.GraphLearner;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCombinationOfFour {

    private GraphLearner<String> learner;

    @BeforeClass
    public static void setup() {
        // log4j basic setup
        BasicConfigurator.configure();
    }

    @Before
    public void beforeTest() {
        learner = new GraphLearner<String>();
    }

    ////////////////////////////////////////////////
    // 1 Node Missing
    ///////////////////////////////////////////////

    @Test
    public void missingFirst() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("B", "C", "D");
        Assert.assertEquals("[A|]BCD", learner.getGraph().toString());
    }

    @Test
    public void missingSecond() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("A", "C", "D");
        Assert.assertEquals("A[B|]CD", learner.getGraph().toString());
    }

    @Test
    public void missingThird() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("A", "B", "D");
        Assert.assertEquals("AB[C|]D", learner.getGraph().toString());
    }

    @Test
    public void missingFourth() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("A", "B", "C");
        Assert.assertEquals("ABC[D|]", learner.getGraph().toString());
    }

    ////////////////////////////////////////////////
    // 2 Nodes Missing
    ///////////////////////////////////////////////

    @Test
    public void missingFirstSecond() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("C", "D");
        Assert.assertEquals("[AB|]CD", learner.getGraph().toString()); // or [A|][B|]CD?
    }

    @Test
    public void missingSecondThird() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("A", "D");
        Assert.assertEquals("A[BC|]D", learner.getGraph().toString()); // or A[B|][C|]D?
    }

    @Test
    public void missingThirdFourth() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("A", "B");
        Assert.assertEquals("AB[CD|]", learner.getGraph().toString()); // or AB[C|][D|]?
    }

    @Test
    public void missingFirstThird() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("B", "D");
        Assert.assertEquals("[A|]B[C|]D", learner.getGraph().toString());
    }

    @Test
    public void missingFirstFourth() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("B", "C");
        Assert.assertEquals("[A|]BC[D|]", learner.getGraph().toString());
    }

    @Test
    public void missingSecondFourth() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("A", "C");
        Assert.assertEquals("A[B|]C[D|]", learner.getGraph().toString());
    }

    ////////////////////////////////////////////////
    // 3 Nodes Missing
    ///////////////////////////////////////////////

    @Test
    public void missingFirstSecondThird() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("D");
        Assert.assertEquals("[ABC|]D", learner.getGraph().toString()); // or [A|][B|][C|]D?
    }

    @Test
    public void missingSecondThirdFourth() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("A");
        Assert.assertEquals("A[BCD|]", learner.getGraph().toString()); // or A[B|][C|][D|]?
    }

    @Test
    public void missingFirstSecondFourth() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("C");
        Assert.assertEquals("[AB|]C[D|]", learner.getGraph().toString()); // or [A|][B|]C[D|]?
    }

    @Test
    public void missingFirstThirdFourth() {
        learner.integrateSequence("A", "B", "C", "D");
        learner.integrateSequence("B");
        Assert.assertEquals("[A|]B[CD|]", learner.getGraph().toString()); // or [A|]B[C|][D|]?
    }

}
