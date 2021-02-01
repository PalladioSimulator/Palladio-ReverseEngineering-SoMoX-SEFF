package org.annotationsmox.graphlearner;

import org.annotationsmox.graphlearner.GraphLearner;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCombinationOfFourAdditional {

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
    public void additionalFirst() {
        learner.integrateSequence("B", "C", "D");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("[A|]BCD", learner.getGraph().toString());
    }

    @Test
    public void additionalSecond() {
        learner.integrateSequence("A", "C", "D");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("A[B|]CD", learner.getGraph().toString());
    }

    @Test
    public void additionalThird() {
        learner.integrateSequence("A", "B", "D");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("AB[C|]D", learner.getGraph().toString());
    }

    @Test
    public void additionalFourth() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("ABC[D|]", learner.getGraph().toString());
    }

    ////////////////////////////////////////////////
    // 2 Nodes Missing
    ///////////////////////////////////////////////

    @Test
    public void additionalFirstSecond() {
        learner.integrateSequence("C", "D");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("[AB|]CD", learner.getGraph().toString()); // or [A|][B|]CD?
    }

    @Test
    public void additionalSecondThird() {
        learner.integrateSequence("A", "D");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("A[BC|]D", learner.getGraph().toString()); // or A[B|][C|]D?
    }

    @Test
    public void additionalThirdFourth() {
        learner.integrateSequence("A", "B");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("AB[CD|]", learner.getGraph().toString()); // or AB[C|][D|]?
    }

    @Test
    public void additionalFirstThird() {
        learner.integrateSequence("B", "D");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("[A|]B[C|]D", learner.getGraph().toString());
    }

    @Test
    public void additionalFirstFourth() {
        learner.integrateSequence("B", "C");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("[A|]BC[D|]", learner.getGraph().toString());
    }

    @Test
    public void additionalSecondFourth() {
        learner.integrateSequence("A", "C");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("A[B|]C[D|]", learner.getGraph().toString());
    }

    ////////////////////////////////////////////////
    // 3 Nodes Missing
    ///////////////////////////////////////////////

    @Test
    public void additionalFirstSecondThird() {
        learner.integrateSequence("D");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("[ABC|]D", learner.getGraph().toString()); // or [A|][B|][C|]D?
    }

    @Test
    public void additionalSecondThirdFourth() {
        learner.integrateSequence("A");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("A[BCD|]", learner.getGraph().toString()); // or A[B|][C|][D|]?
    }

    @Test
    public void additionalFirstSecondFourth() {
        learner.integrateSequence("C");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("[AB|]C[D|]", learner.getGraph().toString()); // or [A|][B|]C[D|]?
    }

    @Test
    public void additionalFirstThirdFourth() {
        learner.integrateSequence("B");
        learner.integrateSequence("A", "B", "C", "D");
        Assert.assertEquals("[A|]B[CD|]", learner.getGraph().toString()); // or [A|]B[C|][D|]?
    }

}
