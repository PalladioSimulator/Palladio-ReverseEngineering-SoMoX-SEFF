package org.annotationsmox.graphlearner;

import org.annotationsmox.graphlearner.GraphLearner;
import org.annotationsmox.graphlearner.Sequence;
import org.annotationsmox.graphlearner.util.TikzTracer;
import org.annotationsmox.graphlearner.util.TikzTreeVisitorFactory;
import org.annotationsmox.graphlearner.visitor.TikZTreeVisitor;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * Hint: use \columnbreak to refine output
 */
public class GenerateExample {

    private GraphLearner<String> learner;

    private TikzTreeVisitorFactory visitorFactory;

    @BeforeClass
    public static void setup() {
        // log4j basic setup
        BasicConfigurator.configure();
    }

    @Before
    public void beforeTest() {
        learner = new GraphLearner<>();
        visitorFactory = TikZTreeVisitor.getFactory();
    }

    @Test
    public void generateExampleA() {
        TikzTracer<String> tracer = TikzTracer.trace(learner, visitorFactory);

        Sequence<String> p1 = Sequence.from("A", "X", "Y", "Z", "B");
        tracer.announceIntegration(p1);
        learner.integrateSequence(p1);
        tracer.forcePlot();

        Sequence<String> p2 = Sequence.from("A", "X", "B");
        tracer.announceIntegration(p2);
        learner.integrateSequence(p2);

        Sequence<String> p3 = Sequence.from("A", "Z", "B");
        tracer.announceIntegration(p3);
        learner.integrateSequence(p3);

        System.out.println(tracer.getResult());
    }

    @Test
    public void generateExampleB() {
        TikzTracer<String> tracer = TikzTracer.trace(learner, visitorFactory);

        Sequence<String> p1 = Sequence.from("X", "Y", "Z", "U");
        tracer.announceIntegration(p1);
        learner.integrateSequence(p1);
        tracer.forcePlot();

        Sequence<String> p2 = Sequence.from("A", "D", "Z", "U");
        tracer.announceIntegration(p2);
        learner.integrateSequence(p2);

        Sequence<String> p3 = Sequence.from("U");
        tracer.announceIntegration(p3);
        learner.integrateSequence(p3);

        System.out.println(tracer.getResult());
    }

    @Test
    public void generateExampleC() {
        TikzTracer<String> tracer = TikzTracer.trace(learner, visitorFactory);

        Sequence<String> p1 = Sequence.from("C", "D", "E", "F", "G");
        tracer.announceIntegration(p1);
        learner.integrateSequence(p1);
        tracer.forcePlot();

        Sequence<String> p2 = Sequence.from("A", "B", "C", "F", "G");
        tracer.announceIntegration(p2);
        learner.integrateSequence(p2);

        Sequence<String> p3 = Sequence.from("A", "B", "G");
        tracer.announceIntegration(p3);
        learner.integrateSequence(p3);

        System.out.println(tracer.getResult());
    }

    @Test
    public void generateExampleD() {
        TikzTracer<String> tracer = TikzTracer.trace(learner, visitorFactory);

        Sequence<String> p1 = Sequence.from("A", "B", "C", "G");
        tracer.announceIntegration(p1);
        learner.integrateSequence(p1);
        tracer.forcePlot();

        Sequence<String> p2 = Sequence.from("F", "G");
        tracer.announceIntegration(p2);
        learner.integrateSequence(p2);

        Sequence<String> p3 = Sequence.from("A", "F", "G");
        tracer.announceIntegration(p3);
        learner.integrateSequence(p3);

        System.out.println(tracer.getResult());

    }

    @Test
    public void generateExampleE() {
        TikzTracer<String> tracer = TikzTracer.trace(learner, visitorFactory);

        Sequence<String> p1 = Sequence.from("A", "B", "C");
        tracer.announceIntegration(p1);
        learner.integrateSequence(p1);
        tracer.forcePlot();

        Sequence<String> p2 = Sequence.from("A", "C");
        tracer.announceIntegration(p2);
        learner.integrateSequence(p2);

        Sequence<String> p3 = Sequence.from("C");
        tracer.announceIntegration(p3);
        learner.integrateSequence(p3);

        System.out.println(tracer.getResult());
    }

    @Test
    public void generateExampleG() {
        TikzTracer<String> tracer = TikzTracer.trace(learner, visitorFactory);

        Sequence<String> p1 = Sequence.from("A", "B", "C");
        tracer.announceIntegration(p1);
        learner.integrateSequence(p1);
        tracer.forcePlot();

        Sequence<String> p2 = Sequence.from("A", "D");
        tracer.announceIntegration(p2);
        learner.integrateSequence(p2);

        System.out.println(tracer.getResult());
    }

}
