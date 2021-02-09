package org.annotationsmox.graphlearner;

import java.util.ArrayList;
import java.util.List;

import org.annotationsmox.graphlearner.GraphLearner;
import org.annotationsmox.graphlearner.Path;
import org.annotationsmox.graphlearner.util.PathBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAllPaths {

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
    public void testA() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("A", "C");

        List<Path> expected = new ArrayList<>();
        expected.add(PathBuilder.path("A", "B", "C"));
        expected.add(PathBuilder.path("A", "C"));

        List<Path> actual = learner.getGraph().allPaths();

        Assert.assertEquals(TestUtils.pathToSetOfStrings(expected), TestUtils.pathToSetOfStrings(actual));
    }

    @Test
    public void testB() {
        learner.integrateSequence("A", "B");
        learner.integrateSequence("A");

        List<Path> expected = new ArrayList<>();
        expected.add(PathBuilder.path("A", "B"));
        expected.add(PathBuilder.path("A"));

        List<Path> actual = learner.getGraph().allPaths();

        Assert.assertEquals(TestUtils.pathToSetOfStrings(expected), TestUtils.pathToSetOfStrings(actual));
    }

    @Test
    public void testC() {
        learner.integrateSequence("A", "B", "C");
        learner.integrateSequence("A", "C");
        learner.integrateSequence("C");

        List<Path> expected = new ArrayList<>();
        expected.add(PathBuilder.path("A", "B", "C"));
        expected.add(PathBuilder.path("A", "C"));
        expected.add(PathBuilder.path("C"));

        /*
         * the following path has not been integrated explicitly but arises as a side effect of the
         * other integrations. It is disputable whether that path is actually "expected" or not.
         */
        expected.add(PathBuilder.path("B", "C"));

        List<Path> actual = learner.getGraph().allPaths();

        Assert.assertEquals(TestUtils.pathToSetOfStrings(expected), TestUtils.pathToSetOfStrings(actual));
    }

}
