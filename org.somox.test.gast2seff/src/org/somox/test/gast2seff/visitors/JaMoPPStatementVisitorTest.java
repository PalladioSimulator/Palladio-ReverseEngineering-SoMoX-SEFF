package org.somox.test.gast2seff.visitors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Statement;
import org.junit.Test;
import org.somox.gast2seff.visitors.AbstractJaMoPPStatementVisitor;
import org.somox.gast2seff.visitors.BasicFunctionClassificationStrategy;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor.FunctionCallType;
import org.somox.gast2seff.visitors.JaMoPPStatementVisitor;
import org.somox.gast2seff.visitors.IFunctionClassificationStrategy;

import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.OperationRequiredRole;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import de.uka.ipd.sdq.pcm.seff.AbstractBranchTransition;
import de.uka.ipd.sdq.pcm.seff.BranchAction;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.seff.InternalAction;
import de.uka.ipd.sdq.pcm.seff.InternalCallAction;
import de.uka.ipd.sdq.pcm.seff.LoopAction;
import de.uka.ipd.sdq.pcm.seff.ProbabilisticBranchTransition;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.seff.SeffFactory;
import de.uka.ipd.sdq.pcm.seff.StartAction;
import de.uka.ipd.sdq.pcm.seff.StopAction;

public class JaMoPPStatementVisitorTest extends JaMoPP2SEFFBaseTest {

    private static final String REQUIRED_ROLE_NAME = "Required_InterfaceA_RequiringComponent";
    private static final String OPERATION_SIGNATURE_NAME = "testExternalCall";

    @Test
    public void testDoExternalCall() {
        // create expected SEFF
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCall = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
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
        final ExternalCallAction externalCall = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        expectedSeff.getSteps_Behaviour().add(externalCall);
        // execute the test
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    @Test
    public void testDoExternalCallWithSimpleParametersAndReturnTypeViaInterface() {
        // create expected SEFF
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCall = this
                .createExternalCallAction("testExternalCallWithSimpleParametersAndReturnType");
        expectedSeff.getSteps_Behaviour().add(externalCall);
        // execute the test
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
    }

    @Test
    public void testDoLibraryCall() {
        // create expected SEFF
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        this.createAndAddInternalActionToSeff(expectedSeff);
        // execute the test
        this.doMethodTestGastStatementVisitor(this.getTestMethodName(), expectedSeff);
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
        this.testConditionMethod(methodName, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL);
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
        this.testLoop(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testForLoopWithInternalCall() {
        // since there is no external call in the loop it becomes an internal action instead of a
        // LoopAction
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        this.createAndAddInternalActionToSeff(expectedSeff);
        this.doMethodTestGastStatementVisitor(super.getTestMethodName(), expectedSeff);
    }

    @Test
    public void testForLoopWithInternalLibraryAndExternalCall() {
        this.testLoop(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testWhileLoopWithExternalCall() {
        this.testLoop(this.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testDoWhileLoopWithExternalCall() {
        this.testLoop(this.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testForEachLoopWithExternalCall() {
        this.testLoop(this.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testTryBlockWithExternalCallInTry() {
        this.testTryBlock(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testTryBlockWithExternalCallInCatch() {
        // no external call expected, since we ignore catch
        this.testTryBlock(super.getTestMethodName());
    }

    @Test
    public void testTryBlockWithExternalCallInFinally() {
        this.testTryBlock(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testTryBlockWithInternalCallInTryLibraryCallInCatchAndExternalCallInFinally() {
        this.testTryBlock(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testTryBlockWithExternalCallInInternalCallInTryBlock() {
        this.testTryBlock(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testSwitchCaseWithExternalCallInFirstCase() {
        final List<FunctionCallType[]> expectedFunctionCallTypes = new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    @Test
    public void testSwitchCaseWithExternalCallInFirstAndSecondCase() {
        final List<FunctionCallType[]> expectedFunctionCallTypes = new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes.add(this
                .toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    @Test
    public void testSwitchCaseWithInternalCallInCaseAndExternalCallDefault() {
        final List<FunctionCallType[]> expectedFunctionCallTypes = new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL));
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    @Test
    public void testSwitchCaseWithFourListAnd10_9_7_4Statements() {
        final List<FunctionCallType[]> expectedFunctionCallTypes = new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    @Test
    public void testSwitchCaseWithFourListAnd2_9_7_4Statements() {
        final List<FunctionCallType[]> expectedFunctionCallTypes = new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    @Test
    public void testSwitchCaseWithFourListAnd7_6_4_4Statements() {
        final List<FunctionCallType[]> expectedFunctionCallTypes = new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    private void doMethodTestGastStatementVisitor(final String methodName, final ResourceDemandingSEFF expectedSeff) {
        // initialize the test
        final ClassMethod method = (ClassMethod) super.findMethodInClassifier(methodName, REQUIRED_COMPONENT_NAME
                + "Impl");
        final BasicComponent basicComponent = (BasicComponent) super.findComponentInPCMRepo(REQUIRED_COMPONENT_NAME);
        final ResourceDemandingSEFF seff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final IFunctionClassificationStrategy basicFunctionClassifierStrategy = new BasicFunctionClassificationStrategy(
                this.sourceCodeDecorator, basicComponent, compilationUnits);
        final FunctionCallClassificationVisitor functionCallClassificationVisitor = new FunctionCallClassificationVisitor(
                basicFunctionClassifierStrategy);
        final AbstractJaMoPPStatementVisitor gastStatementVisitor = new JaMoPPStatementVisitor(
                functionCallClassificationVisitor.getAnnotations(), seff, this.sourceCodeDecorator, basicComponent);

        // execute the test
        for (final Statement statement : method.getStatements()) {
            functionCallClassificationVisitor.doSwitch(statement);
            gastStatementVisitor.doSwitch(statement);
        }

        try {
            // assert
            this.assertSeffEquals(seff, expectedSeff);
        } finally {
            // add seff to basic component in order to make it visible after the test
            final ResourceDemandingSEFF serviceEffectSpecification = (ResourceDemandingSEFF) basicComponent
                    .getServiceEffectSpecifications__BasicComponent().get(0);
            serviceEffectSpecification.getSteps_Behaviour().addAll(seff.getSteps_Behaviour());
        }
    }

    private void testTryBlock(final String methodName, final FunctionCallType... expectedFunctionCallTypes) {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        for (final FunctionCallType expectedFunctionCallType : expectedFunctionCallTypes) {
            this.addBehaviorForFunctionCallType(expectedSeff, expectedFunctionCallType);
        }
        this.doMethodTestGastStatementVisitor(methodName, expectedSeff);
    }

    private void testLoop(final String methodName, final FunctionCallType... expectedFunctionCallTypes) {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final LoopAction loopAction = SeffFactory.eINSTANCE.createLoopAction();
        loopAction.setEntityName("expectedLoopAction");
        final ResourceDemandingBehaviour loopBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        loopBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStartAction());
        for (final FunctionCallType expectedFunctionCallType : expectedFunctionCallTypes) {
            this.addBehaviorForFunctionCallType(loopBehaviour, expectedFunctionCallType);
        }
        loopBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
        loopAction.setBodyBehaviour_Loop(loopBehaviour);
        expectedSeff.getSteps_Behaviour().add(loopAction);
        this.doMethodTestGastStatementVisitor(methodName, expectedSeff);
    }

    /**
     * test switch-case method
     *
     * @param methodName
     * @param expectedFunctionCallTypes
     *            : list that contains for each case the expected function call types
     */
    private void testSwitchCaseMethod(final String methodName, final List<FunctionCallType[]> expectedFunctionCallTypes) {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
        for (final FunctionCallType[] expectedFunctionCallTypesForCurrentCase : expectedFunctionCallTypes) {
            final ProbabilisticBranchTransition branchTransition = SeffFactory.eINSTANCE
                    .createProbabilisticBranchTransition();
            final ResourceDemandingBehaviour behaviorOfCurrentBranch = SeffFactory.eINSTANCE
                    .createResourceDemandingBehaviour();
            branchTransition.setBranchBehaviour_BranchTransition(behaviorOfCurrentBranch);
            final StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
            behaviorOfCurrentBranch.getSteps_Behaviour().add(startAction);
            for (final FunctionCallType expectedInternalFunctionCallType : expectedFunctionCallTypesForCurrentCase) {
                if (expectedInternalFunctionCallType == FunctionCallType.EXTERNAL) {
                    behaviorOfCurrentBranch.getSteps_Behaviour().add(
                            this.createExternalCallAction(OPERATION_SIGNATURE_NAME));
                } else if (expectedInternalFunctionCallType == FunctionCallType.INTERNAL) {
                    // nothing todo: call still null
                } else if (expectedInternalFunctionCallType == FunctionCallType.LIBRARY) {
                    behaviorOfCurrentBranch.getSteps_Behaviour().add(this.createInternalAction());
                }
            }
            final StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
            behaviorOfCurrentBranch.getSteps_Behaviour().add(stopAction);
            branchTransition.setEntityName("branch transition");
            branchAction.getBranches_Branch().add(branchTransition);
        }
        branchAction.setEntityName("expectedBranchAction");
        expectedSeff.getSteps_Behaviour().add(branchAction);
        this.doMethodTestGastStatementVisitor(methodName, expectedSeff);
    }

    /**
     * expecting one branch action with expectedTypesForCondition.length branches
     *
     * @param methodName
     *            name of method under test
     * @param expectedTypesForCondition
     *            expected number of branches with calls in branches
     */
    private void testConditionMethod(final String methodName, final FunctionCallType... expectedTypesForCondition) {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
        for (final FunctionCallType functionCallType : expectedTypesForCondition) {
            final ProbabilisticBranchTransition branchTransition = SeffFactory.eINSTANCE
                    .createProbabilisticBranchTransition();
            final ResourceDemandingBehaviour behaviorOfCurrentBranch = SeffFactory.eINSTANCE
                    .createResourceDemandingBehaviour();
            branchTransition.setBranchBehaviour_BranchTransition(behaviorOfCurrentBranch);
            final StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
            AbstractAction call = null;
            if (functionCallType == FunctionCallType.EXTERNAL) {
                call = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
            } else if (functionCallType == FunctionCallType.INTERNAL) {
                // nothing todo: call still null
            } else if (functionCallType == FunctionCallType.LIBRARY) {
                call = this.createInternalAction();
            }
            final StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
            if (null != call) {
                startAction.setSuccessor_AbstractAction(call);
                call.setSuccessor_AbstractAction(stopAction);
            } else {
                startAction.setSuccessor_AbstractAction(stopAction);
            }
            behaviorOfCurrentBranch.getSteps_Behaviour().add(startAction);
            if (null != call) {
                behaviorOfCurrentBranch.getSteps_Behaviour().add(call);
            }
            behaviorOfCurrentBranch.getSteps_Behaviour().add(stopAction);
            branchTransition.setEntityName("branch transition");
            branchAction.getBranches_Branch().add(branchTransition);
        }
        branchAction.setEntityName("expectedBranchAction");
        expectedSeff.getSteps_Behaviour().add(branchAction);
        this.doMethodTestGastStatementVisitor(methodName, expectedSeff);
    }

    private void assertSeffEquals(final ResourceDemandingBehaviour seff, final ResourceDemandingBehaviour expectedSEFF) {
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
            } else if (abstractAction instanceof InternalAction) {
                this.assertInternalAction((InternalAction) abstractAction, (InternalAction) expectedAbstractAction);
            } else if (abstractAction instanceof BranchAction) {
                this.assertBranchAction((BranchAction) abstractAction, (BranchAction) expectedAbstractAction);
            } else if (abstractAction instanceof StartAction || abstractAction instanceof StopAction) {
                // no need to check anything else
            } else if (abstractAction instanceof LoopAction) {
                this.assertLoopAction((LoopAction) abstractAction, (LoopAction) expectedAbstractAction);
            } else {
                fail("Can not asssert AbstractAction " + abstractAction);
            }
        }

    }

    private void assertLoopAction(final LoopAction loopAction, final LoopAction expectedloopAction) {
        final ResourceDemandingBehaviour resourceDemandingBehaviour = loopAction.getBodyBehaviour_Loop();
        final ResourceDemandingBehaviour expectedResourceDemandingBehaviour = expectedloopAction
                .getBodyBehaviour_Loop();
        this.assertSeffEquals(resourceDemandingBehaviour, expectedResourceDemandingBehaviour);
    }

    private void assertBranchAction(final BranchAction branchAction, final BranchAction expectedBranchAction) {
        assertEquals("BranchActions should have the same size", expectedBranchAction.getBranches_Branch().size(),
                branchAction.getBranches_Branch().size());
        for (int i = 0; i < branchAction.getBranches_Branch().size(); i++) {
            final AbstractBranchTransition branchTransition = branchAction.getBranches_Branch().get(i);
            final AbstractBranchTransition expectedBranchTransition = expectedBranchAction.getBranches_Branch().get(i);
            assertEquals("Branch transitions must have the same class", expectedBranchTransition.getClass(),
                    branchTransition.getClass());
            final ResourceDemandingBehaviour behaviour = branchTransition.getBranchBehaviour_BranchTransition();
            final ResourceDemandingBehaviour expectedBehaviour = expectedBranchTransition
                    .getBranchBehaviour_BranchTransition();
            this.assertSeffEquals(behaviour, expectedBehaviour);
        }
    }

    private void assertInternalAction(final InternalAction abstractAction, final InternalAction expectedAbstractAction) {
        // nothing to compare for internal action.
    }

    private void assertInternalCallActionEquals(final InternalCallAction abstractAction,
            final InternalCallAction expectedAbstractAction) {
        throw new RuntimeException(
                "Internal call actions should not be generated since internal call action is not working.");
    }

    private void assertExternalCallActionEquals(final ExternalCallAction externalCallAction,
            final ExternalCallAction expectedExternalCallAction) {
        assertEquals("Role of external actions is not the same", expectedExternalCallAction.getRole_ExternalService(),
                externalCallAction.getRole_ExternalService());
        assertEquals("Call service of external call actions is not the same",
                expectedExternalCallAction.getCalledService_ExternalService(),
                externalCallAction.getCalledService_ExternalService());
    }

    private ExternalCallAction createExternalCallAction(final String operationSignatureName) {
        final ExternalCallAction externalCall = SeffFactory.eINSTANCE.createExternalCallAction();
        final BasicComponent basicComponent = (BasicComponent) super.findComponentInPCMRepo(REQUIRED_COMPONENT_NAME);
        final OperationRequiredRole operationRequiredRole = super.findOperaitonRequiredRoleInBasicComponent(
                basicComponent, REQUIRED_ROLE_NAME);
        final OperationSignature operationSignature = super.findRequiredOperationSignatureInOperationRequiredRole(
                operationRequiredRole, operationSignatureName);
        externalCall.setRole_ExternalService(operationRequiredRole);
        externalCall.setCalledService_ExternalService(operationSignature);
        return externalCall;
    }

    private void createAndAddInternalActionToSeff(final ResourceDemandingBehaviour expectedSeff) {
        final InternalAction internalAction = this.createInternalAction();
        expectedSeff.getSteps_Behaviour().add(internalAction);
    }

    private InternalAction createInternalAction() {
        final InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();
        internalAction.setEntityName("expectedInternalAction");
        return internalAction;
    }

    private void addBehaviorForFunctionCallType(final ResourceDemandingBehaviour expectedSeff,
            final FunctionCallType expectedFunctionCallType) {
        if (expectedFunctionCallType == FunctionCallType.EXTERNAL) {
            final ExternalCallAction externalCallAction = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
            expectedSeff.getSteps_Behaviour().add(externalCallAction);
        } else if (expectedFunctionCallType == FunctionCallType.INTERNAL) {

        } else if (expectedFunctionCallType == FunctionCallType.LIBRARY) {
            this.createAndAddInternalActionToSeff(expectedSeff);
        }
    }

    private FunctionCallType[] toFunctionCallTypeArray(final FunctionCallType... functionCallTypeArray) {
        return functionCallTypeArray;
    }

}
