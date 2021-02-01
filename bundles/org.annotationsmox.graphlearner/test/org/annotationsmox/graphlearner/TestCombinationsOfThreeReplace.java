package org.annotationsmox.graphlearner;

import org.annotationsmox.graphlearner.GraphLearner;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCombinationsOfThreeReplace {

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
    public void differentStart() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("X", "B", "C");
        Assert.assertEquals("[A|X]BC", learner.getGraph().toString());
    }

    @Test
    public void differentMiddle() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("A", "X", "C");
        Assert.assertEquals("A[B|X]C", learner.getGraph().toString());
    }

    @Test
    public void differentEnd() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("A", "B", "X");
        Assert.assertEquals("AB[C|X]", learner.getGraph().toString());
    }

    @Test
    public void differentStartAndEnd() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("X", "B", "Y");
        Assert.assertEquals("[A|X]B[C|Y]", learner.getGraph().toString());
    }

    @Test
    public void differentStartAndMiddle() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("X", "Y", "C");
        Assert.assertEquals("[AB|XY]C", learner.getGraph().toString());
    }

    @Test
    public void differentMiddleAndEnd() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("A", "X", "Y");
        Assert.assertEquals("A[BC|XY]", learner.getGraph().toString());
    }

}
