package org.somox.test.gast2seff.visitors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Statement;
import org.junit.BeforeClass;
import org.junit.Test;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ProbabilisticBranchTransition;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.somox.gast2seff.visitors.AbstractJaMoPPStatementVisitor;
import org.somox.gast2seff.visitors.BasicFunctionClassificationStrategy;
import org.somox.gast2seff.visitors.DefaultResourceDemandingBehaviourForClassMethodFinder;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor.FunctionCallType;
import org.somox.gast2seff.visitors.IFunctionClassificationStrategy;
import org.somox.gast2seff.visitors.JaMoPPStatementVisitor;
import org.somox.gast2seff.visitors.MethodCallFinder;
import org.somox.gast2seff.visitors.ResourceDemandingBehaviourForClassMethodFinding;

public class JaMoPPStatementVisitorTest extends JaMoPP2SEFFBaseTest {

    protected static final String TEST_EXTERNAL_CALL_WITH_SIMPLE_PARAMETERS_AND_RETURN_TYPE =
            "testExternalCallWithSimpleParametersAndReturnType";
    private static final String REQUIRED_ROLE_NAME = "Required_InterfaceA_RequiringComponent";
    protected static final String OPERATION_SIGNATURE_NAME = "testExternalCall";
    protected static boolean createResourceDemandingInternalBehaviourForClassMethods;

    @BeforeClass
    public static void beforeClass() throws IOException, CoreException {
        JaMoPP2PCMBaseTest.beforeClass();
        createResourceDemandingInternalBehaviourForClassMethods = false;
    }

    @Test
    public void testDoExternalCall() {
        // create expected SEFF
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCall = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        expectedSeff.getSteps_Behaviour().add(externalCall);
        // execute the test
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Test
    public void testDoInternalCall() {
        // create expected SEFF
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        // this.createInternalCallActionAndAddToSEFF(expectedSeff);
        // a empty SEFF is expected here
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Test
    public void testDoExternalCallViaInterface() {
        // we expect the same as in testDoExternalCall
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCall = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        expectedSeff.getSteps_Behaviour().add(externalCall);
        // execute the test
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Test
    public void testDoExternalCallWithSimpleParametersAndReturnTypeViaInterface() {
        // create expected SEFF
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCall =
                this.createExternalCallAction(TEST_EXTERNAL_CALL_WITH_SIMPLE_PARAMETERS_AND_RETURN_TYPE);
        expectedSeff.getSteps_Behaviour().add(externalCall);
        // execute the test
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Test
    public void testDoLibraryCall() {
        // create expected SEFF
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        this.createAndAddInternalActionToSeff(expectedSeff);
        // execute the test
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
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

    // @Test
    public void testConditionWithExternalCallInCondition() {
        final String methodName = super.getTestMethodName();
        this.testConditionMethod(methodName, FunctionCallType.EXTERNAL, FunctionCallType.INTERNAL);
    }

    // @Test
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
        this.testLoop(getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testForLoopWithInternalCallContainingExternalCall() {
        this.testLoop(super.getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testDoWhileLoopWithExternalCall() {
        this.testLoop(getTestMethodName(), FunctionCallType.EXTERNAL);
    }

    @Test
    public void testForEachLoopWithExternalCall() {
        this.testLoop(getTestMethodName(), FunctionCallType.EXTERNAL);
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
        final List<FunctionCallType[]> expectedFunctionCallTypes =
                new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    @Test
    public void testSwitchCaseWithExternalCallInFirstAndSecondCase() {
        final List<FunctionCallType[]> expectedFunctionCallTypes =
                new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes
                .add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    @Test
    public void testSwitchCaseWithInternalCallInCaseAndExternalCallDefault() {
        final List<FunctionCallType[]> expectedFunctionCallTypes =
                new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray());
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL));
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    @Test
    public void testSwitchCaseWithFourListAnd10_9_7_4Statements() {
        final List<FunctionCallType[]> expectedFunctionCallTypes =
                new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    @Test
    public void testSwitchCaseWithFourListAnd2_9_7_4Statements() {
        final List<FunctionCallType[]> expectedFunctionCallTypes =
                new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    @Test
    public void testSwitchCaseWithFourListAnd7_6_4_4Statements() {
        final List<FunctionCallType[]> expectedFunctionCallTypes =
                new ArrayList<FunctionCallClassificationVisitor.FunctionCallType[]>();
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL));
        expectedFunctionCallTypes.add(this.toFunctionCallTypeArray(FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL,
                FunctionCallType.EXTERNAL, FunctionCallType.EXTERNAL));
        this.testSwitchCaseMethod(super.getTestMethodName(), expectedFunctionCallTypes);
    }

    @Test
    public void testExternalCallInInternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCallAction = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        expectedSeff.getSteps_Behaviour().add(externalCallAction);
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Test
    public void testExternalCallAsInputForInternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final ExternalCallAction externalCallAction = this.createExternalCallAction(OPERATION_SIGNATURE_NAME);
        expectedSeff.getSteps_Behaviour().add(externalCallAction);
        expectedSeff.getSteps_Behaviour().add(this.createInternalAction());
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Test
    public void testInternalCallAsInputForInternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        expectedSeff.getSteps_Behaviour().add(this.createInternalAction());
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Test
    public void testInternalCallAsInputForExternalCall() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        expectedSeff.getSteps_Behaviour().add(this.createInternalAction());
        final ExternalCallAction externalCallAction =
                this.createExternalCallAction(TEST_EXTERNAL_CALL_WITH_SIMPLE_PARAMETERS_AND_RETURN_TYPE);
        expectedSeff.getSteps_Behaviour().add(externalCallAction);
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    @Test
    public void testSimpleStatement() {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        expectedSeff.getSteps_Behaviour().add(this.createInternalAction());
        this.doMethodTestGastStatementVisitor(getTestMethodName(), expectedSeff);
    }

    protected void doMethodTestGastStatementVisitor(final String methodName, final ResourceDemandingSEFF expectedSeff) {
        // initialize the test
        final ClassMethod method =
                (ClassMethod) super.findMethodInClassifier(methodName, REQUIRED_COMPONENT_NAME + "Impl");
        final BasicComponent basicComponent = (BasicComponent) super.findComponentInPCMRepo(REQUIRED_COMPONENT_NAME);
        final ResourceDemandingSEFF seff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final MethodCallFinder methodCallFinder = new MethodCallFinder();
        final IFunctionClassificationStrategy basicFunctionClassifierStrategy = new BasicFunctionClassificationStrategy(
                this.sourceCodeDecorator, basicComponent, compilationUnits, methodCallFinder);
        final FunctionCallClassificationVisitor functionCallClassificationVisitor =
                new FunctionCallClassificationVisitor(basicFunctionClassifierStrategy, methodCallFinder);
        ResourceDemandingBehaviourForClassMethodFinding resourceDemandingBehaviourForClassMethodFinding = null;
        if (createResourceDemandingInternalBehaviourForClassMethods) {
            resourceDemandingBehaviourForClassMethodFinding =
                    new DefaultResourceDemandingBehaviourForClassMethodFinder(this.sourceCodeDecorator, basicComponent);
        }
        final AbstractJaMoPPStatementVisitor gastStatementVisitor = new JaMoPPStatementVisitor(
                functionCallClassificationVisitor.getAnnotations(), seff, this.sourceCodeDecorator, basicComponent,
                null, resourceDemandingBehaviourForClassMethodFinding, methodCallFinder);

        // execute the test
        for (final Statement statement : method.getStatements()) {
            functionCallClassificationVisitor.doSwitch(statement);
            gastStatementVisitor.doSwitch(statement);
        }

        try {
            // assert
            AssertSEFFHelper.assertSeffEquals(seff, expectedSeff);
        } finally {
            // add seff to basic component in order to make it visible after the test
            final ResourceDemandingSEFF serviceEffectSpecification =
                    (ResourceDemandingSEFF) basicComponent.getServiceEffectSpecifications__BasicComponent().get(0);
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
        final LoopAction loopAction = this.createLoopAction(expectedFunctionCallTypes);
        expectedSeff.getSteps_Behaviour().add(loopAction);
        this.doMethodTestGastStatementVisitor(methodName, expectedSeff);
    }

    private LoopAction createLoopAction(final FunctionCallType... expectedFunctionCallTypes) {
        final LoopAction loopAction = SeffFactory.eINSTANCE.createLoopAction();
        loopAction.setEntityName("expectedLoopAction");
        final ResourceDemandingBehaviour loopBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
        loopBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStartAction());
        for (final FunctionCallType expectedFunctionCallType : expectedFunctionCallTypes) {
            this.addBehaviorForFunctionCallType(loopBehaviour, expectedFunctionCallType);
        }
        loopBehaviour.getSteps_Behaviour().add(SeffFactory.eINSTANCE.createStopAction());
        loopAction.setBodyBehaviour_Loop(loopBehaviour);
        return loopAction;
    }

    /**
     * test switch-case method
     *
     * @param methodName
     * @param expectedFunctionCallTypes
     *            : list that contains for each case the expected function call types
     */
    protected void testSwitchCaseMethod(final String methodName,
            final List<FunctionCallType[]> expectedFunctionCallTypes) {
        final ResourceDemandingSEFF expectedSeff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
        for (final FunctionCallType[] expectedFunctionCallTypesForCurrentCase : expectedFunctionCallTypes) {
            final ProbabilisticBranchTransition branchTransition =
                    SeffFactory.eINSTANCE.createProbabilisticBranchTransition();
            final ResourceDemandingBehaviour behaviorOfCurrentBranch =
                    SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
            branchTransition.setBranchBehaviour_BranchTransition(behaviorOfCurrentBranch);
            final StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
            behaviorOfCurrentBranch.getSteps_Behaviour().add(startAction);
            for (final FunctionCallType expectedInternalFunctionCallType : expectedFunctionCallTypesForCurrentCase) {
                if (expectedInternalFunctionCallType == FunctionCallType.EXTERNAL) {
                    behaviorOfCurrentBranch.getSteps_Behaviour()
                            .add(this.createExternalCallAction(OPERATION_SIGNATURE_NAME));
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
            final ProbabilisticBranchTransition branchTransition =
                    SeffFactory.eINSTANCE.createProbabilisticBranchTransition();
            final ResourceDemandingBehaviour behaviorOfCurrentBranch =
                    SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
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

    private void createAndAddInternalActionToSeff(final ResourceDemandingBehaviour expectedSeff) {
        final InternalAction internalAction = this.createInternalAction();
        expectedSeff.getSteps_Behaviour().add(internalAction);
    }

    protected InternalAction createInternalAction() {
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

    protected ExternalCallAction createExternalCallAction(final String operationSignatureName) {
        final ExternalCallAction externalCall = SeffFactory.eINSTANCE.createExternalCallAction();
        final BasicComponent basicComponent = (BasicComponent) super.findComponentInPCMRepo(REQUIRED_COMPONENT_NAME);
        final OperationRequiredRole operationRequiredRole =
                super.findOperaitonRequiredRoleInBasicComponent(basicComponent, REQUIRED_ROLE_NAME);
        final OperationSignature operationSignature = super.findRequiredOperationSignatureInOperationRequiredRole(
                operationRequiredRole, operationSignatureName);
        externalCall.setRole_ExternalService(operationRequiredRole);
        externalCall.setCalledService_ExternalService(operationSignature);
        return externalCall;
    }

    private FunctionCallType[] toFunctionCallTypeArray(final FunctionCallType... functionCallTypeArray) {
        return functionCallTypeArray;
    }

}
