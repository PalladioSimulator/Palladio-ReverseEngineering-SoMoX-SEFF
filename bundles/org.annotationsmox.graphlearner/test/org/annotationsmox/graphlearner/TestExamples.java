package org.annotationsmox.graphlearner;

import org.annotationsmox.graphlearner.GraphLearner;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestExamples {

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
    public void testExample1() {
        learner.integrateSequence("A", "B", "C", "G");
        learner.integrateSequence("A", "B");
        learner.integrateSequence("A", "F", "G");
        Assert.assertEquals("A[B|F][[C|]G|]", learner.getGraph().toString());
    }

}
