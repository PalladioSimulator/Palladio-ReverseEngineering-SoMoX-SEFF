package org.somox.test.gast2seff.visitors;

import static org.junit.Assert.assertEquals;

import java.util.BitSet;

import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Statement;
import org.junit.Test;
import org.somox.gast2seff.visitors.BasicFunctionClassificationStrategy;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor.FunctionCallType;
import org.somox.gast2seff.visitors.IFunctionClassificationStrategy;

import org.palladiosimulator.pcm.repository.BasicComponent;

public class BasicFunctionClassificationStrategyTest extends JaMoPP2SEFFBaseTest {

    @Test
    public void testExternalCall() {
        this.testCall(REQUIRED_COMPONENT_NAME, TEST_DO_EXTERNAL_CALL, 1, FunctionCallType.EXTERNAL);
    }

    @Test
    public void testLibraryCall() {
        this.testCall(REQUIRED_COMPONENT_NAME, TEST_DO_LIBRARY_CALL, 1, FunctionCallType.LIBRARY);
    }

    @Test
    public void testInternalCall() {
        this.testCall(REQUIRED_COMPONENT_NAME, TEST_DO_INTERNAL_CALL, 1, FunctionCallType.INTERNAL);
    }

    @Test
    public void testMergeFunctionCallType() {
        final BasicComponent bc = (BasicComponent) this.findComponentInPCMRepo(REQUIRED_COMPONENT_NAME);
        final IFunctionClassificationStrategy basicFunctionClassificationStrategy = new BasicFunctionClassificationStrategy(
                this.sourceCodeDecorator, bc, compilationUnits);
        final BitSet myType = new BitSet();
        myType.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.INTERNAL));
        final BitSet functionCallType = new BitSet();
        functionCallType.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.EXTERNAL));
        basicFunctionClassificationStrategy.mergeFunctionCallType(myType, functionCallType);
        this.assertBitSet(2, myType, FunctionCallType.INTERNAL, FunctionCallType.EXTERNAL);
        this.assertBitSet(1, functionCallType, FunctionCallType.EXTERNAL);
    }

    private Statement getStatementInClassifier(final String classifierName, final String methodName) {
        final ClassMethod methodInClassifier = (ClassMethod) super.findMethodInClassifier(methodName, classifierName
                + "Impl");
        assertEquals("Exact one statement should be in the statement list container", 1, methodInClassifier
                .getStatements().size());
        final Statement statement = methodInClassifier.getStatements().get(0);
        return statement;
    }

    private void testCall(final String componentName, final String methodName, final int expectedBitSetCardinality,
            final FunctionCallType... expectedFunctionCallTypes) {
        // prepare
        final Statement statement = this.getStatementInClassifier(componentName, methodName);
        final BasicComponent basicComponentForClass = (BasicComponent) this.findComponentInPCMRepo(componentName);

        // the test
        final IFunctionClassificationStrategy functionClassificationStrategy = new BasicFunctionClassificationStrategy(
                this.sourceCodeDecorator, basicComponentForClass, compilationUnits);
        final BitSet bitSet = functionClassificationStrategy.classifySimpleStatement(statement);

        this.assertBitSet(expectedBitSetCardinality, bitSet, expectedFunctionCallTypes);
    }

}
