package org.somox.test.gast2seff.visitors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Statement;
import org.junit.Test;
import org.somox.gast2seff.visitors.BasicFunctionClassificationStrategy;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor.FunctionCallType;
import org.somox.gast2seff.visitors.GastStatementVisitor;

import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.OperationRequiredRole;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.seff.InternalCallAction;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.seff.SeffFactory;

public class GastStatementVisitorTest extends Gast2SEFFBaseTest {

    private static final String REQUIRED_ROLE_NAME = "Required_InterfaceA_RequiringComponent";
    private static final String OPERATION_SIGNATURE_NAME = "testExternalCall";

    @Test
    public void testDoExternalCall() {
        // create expected SEFF
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCall = this.createExternalCallAction();
        expectedSeff.getSteps_Behaviour().add(externalCall);
        // execute the test
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    @Test
    public void testDoInternalCall() {
        // create expected SEFF
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        // this.createInternalCallActionAndAddToSEFF(expectedSeff);
        // a empty SEFF is expected here
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    @Test
    public void testDoExternalCallViaInterface() {
        // we expect the same as in testDoExternalCall
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCall = this.createExternalCallAction();
        expectedSeff.getSteps_Behaviour().add(externalCall);
        // execute the test
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    @Test
    public void testDoExternalCallWithSimpleParametersAndReturnTypeViaInterface() {
        // create expected SEFF
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCall = this.createExternalCallAction();
        expectedSeff.getSteps_Behaviour().add(externalCall);
        // execute the test
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    @Test
    public void testDoLibraryCall() {
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), null);
    }

    @Test
    public void testConditionWithExternalCallInIf() {
        final String methodName = super.getTestMethodName();
        this.testConditionMethod(methodName, FunctionCallType.EXTERNAL, FunctionCallType.INTERNAL);
    }

    @Test
    public void testConditionWithExternalCallInElse() {
        final String methodName = super.getTestMethodName();
        this.testConditionMethod(methodName, FunctionCallType.INTERNAL, FunctionCallType.EXTERNAL);
    }

    @Test
    public void testConditionWithExternalCallInIfAndElse() {
        final String methodName = super.getTestMethodName();
        this.testConditionMethod(methodName, FunctionCallType.EXTERNAL);
    }

    @Test
    public void testConditionWithExternalCallInCondition() {
        final String methodName = super.getTestMethodName();
        this.testConditionMethod(methodName, FunctionCallType.EXTERNAL, FunctionCallType.INTERNAL);
    }

    @Test
    public void testConditionWithLibraryCallInCondition() {
        final String methodName = super.getTestMethodName();
        this.testConditionMethod(methodName, FunctionCallType.LIBRARY, FunctionCallType.INTERNAL);
    }

    @Test
    public void testForLoopWithExternalCall() {
        this.testForLoopMethod(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testForLoopWithInternalCall() {
        this.testForLoopMethod(super.getTestMethodName(), FunctionCallType.INTERNAL);
    }

    @Test
    public void testForLoopWithInternalLibraryAndExternalCall() {
        this.testForLoopMethod(super.getTestMethodName(), FunctionCallType.INTERNAL, FunctionCallType.LIBRARY,
                FunctionCallType.EXTERNAL);
    }

    @Test
    public void testWhileLoopWithExternalCall() {
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), null);
    }

    @Test
    public void testDoWhileLoopWithExternalCall() {
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), null);
    }

    @Test
    public void testForEachLoopWithExternalCall() {
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), null);
    }

    @Test
    public void testTryBlockWithExternalCallInTry() {
        this.testTryBlock(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testTryBlockWithExternalCallInCatch() {
        this.testTryBlock(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testTryBlockWithExternalCallInFinally() {
        this.testTryBlock(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testTryBlockWithInternalCallInTryLibraryCallInCatchAndExternalCallInFinally() {
        this.testTryBlock(super.getTestMethodName(), FunctionCallType.INTERNAL, FunctionCallType.LIBRARY,
                FunctionCallType.EXTERNAL);
    }

    @Test
    public void testSwitchCaseWithExternalCallInFirstCase() {
        this.testSwitchCaseMethod(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    public void testSwitchCaseWithExternalCallInFirstAndSecondCase() {
        this.testSwitchCaseMethod(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testSwitchCaseWithInternalCallInCaseAndExternalCallDefault() {
        this.testSwitchCaseMethod(super.getTestMethodName(), FunctionCallType.INTERNAL, FunctionCallType.EXTERNAL);
    }

    private void testTryBlock(final String methodName, final FunctionCallType... expectedFunctionCallTypes) {
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), null);
    }

    private void testForLoopMethod(final String methodName, final FunctionCallType... expectedFunctionCallTypes) {
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), null);
    }

    private void testSwitchCaseMethod(final String methodName, final FunctionCallType... expectedFunctionCallTypes) {
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), null);
    }

    private void testConditionMethod(final String methodName, final FunctionCallType... expectedTypesForCondition) {
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), null);
    }

    private void doMethodTestGastStatementVisitor(final String methodName, final ResourceDemandingSEFF expectedSeff) {
        // initialize the test
        final ClassMethod method = (ClassMethod) super.findMethodInClassifier(methodName, REQUIRED_COMPONENT_NAME
                + "Impl");
        final BasicComponent basicComponent = (BasicComponent) super.findComponentInPCMRepo(REQUIRED_COMPONENT_NAME);
        final ResourceDemandingSEFF seff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final BasicFunctionClassificationStrategy basicFunctionClassifierStrategy = new BasicFunctionClassificationStrategy(
                this.sourceCodeDecorator, basicComponent, compilationUnits);
        final FunctionCallClassificationVisitor functionCallClassificationVisitor = new FunctionCallClassificationVisitor(
                basicFunctionClassifierStrategy);
        final GastStatementVisitor gastStatementVisitor = new GastStatementVisitor(
                functionCallClassificationVisitor.getAnnotations(), seff, this.sourceCodeDecorator, basicComponent);

        // execute the test
        for (final Statement statement : method.getStatements()) {
            functionCallClassificationVisitor.doSwitch(statement);
            gastStatementVisitor.doSwitch(statement);
        }

        // assert
        this.assertSeffEquals(seff, expectedSeff);
    }

    private void assertSeffEquals(final ResourceDemandingSEFF seff, final ResourceDemandingSEFF expectedSEFF) {
        final List<AbstractAction> abstractActions = seff.getSteps_Behaviour();
        final List<AbstractAction> expectedAbstractActions = expectedSEFF.getSteps_Behaviour();
        assertEquals("Seff should have the same number of actions as expected", abstractActions.size(),
                expectedAbstractActions.size());

        for (int i = 0; i < abstractActions.size(); i++) {
            final AbstractAction abstractAction = abstractActions.get(i);
            final AbstractAction expectedAbstractAction = expectedAbstractActions.get(i);
            assertEquals("Abstract action " + abstractAction + " should have the expected class "
                    + expectedAbstractAction.getClass(), abstractAction.getClass(), expectedAbstractAction.getClass());
            if (abstractAction instanceof ExternalCallAction) {
                this.assertExternalCallActionEquals((ExternalCallAction) abstractAction,
                        (ExternalCallAction) expectedAbstractAction);
            } else if (abstractAction instanceof InternalCallAction) {
                this.assertInternalCallActionEquals((InternalCallAction) abstractAction,
                        (InternalCallAction) expectedAbstractAction);
            } else {
                fail("Can not asssert AbstractAction " + abstractAction);
            }

        }

    }

    private void assertInternalCallActionEquals(final InternalCallAction abstractAction,
            final InternalCallAction expectedAbstractAction) {
        // nothing to compare for internal call action yet?
    }

    private void assertExternalCallActionEquals(final ExternalCallAction externalCallAction,
            final ExternalCallAction expectedExternalCallAction) {
        assertEquals("Role of external actions is not the same", expectedExternalCallAction.getRole_ExternalService(),
                externalCallAction.getRole_ExternalService());
        assertEquals("Call service of external call actions is not the same",
                expectedExternalCallAction.getCalledService_ExternalService(),
                externalCallAction.getCalledService_ExternalService());
    }

    private ExternalCallAction createExternalCallAction() {
        final ExternalCallAction externalCall = SeffFactory.eINSTANCE.createExternalCallAction();
        final BasicComponent basicComponent = (BasicComponent) super.findComponentInPCMRepo(REQUIRED_COMPONENT_NAME);
        final OperationRequiredRole operationRequiredRole = super.findOperaitonRequiredRoleInBasicComponent(
                basicComponent, REQUIRED_ROLE_NAME);
        final OperationSignature operationSignature = super.findRequiredOperationSignatureInOperationRequiredRole(
                operationRequiredRole, OPERATION_SIGNATURE_NAME);
        externalCall.setRole_ExternalService(operationRequiredRole);
        externalCall.setCalledService_ExternalService(operationSignature);
        return externalCall;
    }

    private InternalCallAction createInternalCallActionAndAddToSEFF(final ResourceDemandingSEFF expectedSeff) {
        final InternalCallAction internalCall = SeffFactory.eINSTANCE.createInternalCallAction();
        internalCall.setEntityName("expectedInternalCall");
        expectedSeff.getSteps_Behaviour().add(internalCall);
        return internalCall;
    }

}
