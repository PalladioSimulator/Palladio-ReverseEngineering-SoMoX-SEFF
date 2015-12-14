package org.somox.test.gast2seff.visitors;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.resource.JaMoPPUtil;
import org.emftext.language.java.statements.Statement;
import org.junit.BeforeClass;
import org.junit.Test;
import org.somox.gast2seff.visitors.MethodCallFinder;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;

public class MethodCallFinderTest {

    private static final Logger logger = Logger.getLogger(MethodCallFinderTest.class.getSimpleName());

    private static final String PROJECT_PATH = "testworkspace/MockupProject/src-for-tests";

    private static ConcreteClassifier concreteClassifier;

    @BeforeClass
    public static void beforeClass() {
        // get the statement from the prepared test compilation unit
        JaMoPP2PCMBaseTest.initializeLogger();
        JaMoPPUtil.initialize();
        final JaMoPPSoftwareModelExtractor softwareModelExtractor = new JaMoPPSoftwareModelExtractor();
        final ResourceSet resourceSet =
                softwareModelExtractor.extractSoftwareModel(Arrays.asList(PROJECT_PATH), new NullProgressMonitor());
        final CompilationUnit cu = (CompilationUnit) resourceSet.getResources().get(0).getContents().get(0);
        concreteClassifier = cu.getClassifiers().get(0);
    }

    @Test
    public void visitorUtilTestMethodWithOneMethodCallAppend() {
        this.testDoGetMethodCallsForFirstStatementInMethodWithName(JaMoPP2PCMBaseTest.getTestMethodName(), "append");
    }

    @Test
    public void visitorUtilTestMethodWithTwoMethodCallsAppendToString() {
        this.testDoGetMethodCallsForFirstStatementInMethodWithName(JaMoPP2PCMBaseTest.getTestMethodName(), "append",
                "toString");
    }

    @Test
    public void visitorUtilTestMethodWithTwoMethodCallsReverseAppend() {
        this.testDoGetMethodCallsForFirstStatementInMethodWithName(JaMoPP2PCMBaseTest.getTestMethodName(), "reverse",
                "append");
    }

    @Test
    public void visitorUtilTestMethodWithThreeMethodCallsReverseAppendToString() {
        this.testDoGetMethodCallsForFirstStatementInMethodWithName(JaMoPP2PCMBaseTest.getTestMethodName(), "reverse",
                "append", "toString");
    }

    @Test
    public void visitorUtilTestMethodWithFourMethodCallsReverseAppendgetIntAppendCodePoint() {
        this.testDoGetMethodCallsForFirstStatementInMethodWithName(JaMoPP2PCMBaseTest.getTestMethodName(), "reverse",
                "append", "getInt", "appendCodePoint");
    }

    @Test
    public void visitorUtilTestMethodWithThreeMethodCallsCapacityLengthSubSequence() {
        this.testDoGetMethodCallsForFirstStatementInMethodWithName(JaMoPP2PCMBaseTest.getTestMethodName(), "capacity",
                "length", "subSequence");
    }

    /**
     * explicitly tests the bug we have experienced by analyzing long methodCall chains e.g.
     * method1.doSmth(new Class("test").doIt().doItAgain()).doSmth(new
     * Class("test2").doIt().doItAgain()); The test passes if we are not ending in an infinite loop,
     * which we consider is happened if the test has not finished after one second
     */
    @Test(timeout = 1000)
    public void visitorUtilTestMethodWithManyMethodCalls() {
        final String testMethodName = JaMoPP2PCMBaseTest.getTestMethodName();
        final List<Method> calledMethods =
                this.testDoGetMethodCallsForFirstStatementInMethodWithNameWithoutAssert(testMethodName);

        logger.debug("Found " + calledMethods.size() + " calledMethods for method " + testMethodName);
    }

    private List<Method> testDoGetMethodCallsForFirstStatementInMethodWithNameWithoutAssert(final String methodName) {
        final ClassMethod classMethod = this.findMethodByName(methodName);
        final Statement statmentWithMethodCalls = classMethod.getStatements().get(0);

        // execute the actual test
        final MethodCallFinder methodCallFinder = new MethodCallFinder();
        final List<Method> methodCalls = methodCallFinder.getMethodCalls(statmentWithMethodCalls);

        this.traceMethodCalls(methodCalls, methodName);

        return methodCalls;
    }

    private void testDoGetMethodCallsForFirstStatementInMethodWithName(final String methodName,
            final String... expectedMethodNames) {
        final List<Method> methodCalls =
                this.testDoGetMethodCallsForFirstStatementInMethodWithNameWithoutAssert(methodName);

        // assert the correct number of method calls
        assertEquals("Could not find the expected size of method calls", expectedMethodNames.length,
                methodCalls.size());
        // assert that methods do have the correct name
        for (int i = 0; i < methodCalls.size(); i++) {
            assertEquals("Method " + i + " does not have the expected method name", expectedMethodNames[i],
                    methodCalls.get(i).getName());
        }
    }

    private void traceMethodCalls(final List<Method> methodCalls, final String methodName) {
        final StringBuilder message = new StringBuilder("Found method calls for " + methodName + ": ");
        for (final Method method : methodCalls) {
            message.append(method.getName()).append(", ");
        }
        logger.debug(message);
    }

    private ClassMethod findMethodByName(final String methodName) {
        for (final Method method : concreteClassifier.getMethods()) {
            if (method instanceof ClassMethod && method.getName().equals(methodName)) {
                return (ClassMethod) method;
            }
        }
        throw new RuntimeException(
                "Could not find method '" + methodName + "' in classifier " + concreteClassifier.getName());
    }

}
