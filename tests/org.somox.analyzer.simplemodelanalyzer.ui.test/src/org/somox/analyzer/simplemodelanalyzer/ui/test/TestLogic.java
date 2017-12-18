package org.somox.analyzer.simplemodelanalyzer.ui.test;

import org.somox.analyzer.simplemodelanalyzer.jobs.SoMoXBlackboard;

/**
 * Functional interface for test logic on a extending job. Meant to be used to program the tests
 * that will be executed if a test extending job is executed.
 *
 * @author Joshua Gleitze
 *
 */
public interface TestLogic {
    /**
     * Executes the test logic.
     *
     * @param blackboard
     *            The blackboard as passed to the job executing this test.
     */
    void test(SoMoXBlackboard blackboard);
}