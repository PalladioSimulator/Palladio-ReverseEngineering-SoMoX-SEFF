package org.somox.test.gast2seff.examplesrc.requiringcomponent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.somox.test.gast2seff.examplesrc.contracts.InterfaceA;
import org.somox.test.gast2seff.examplesrc.contracts.ProvidingInterface;
import org.somox.test.gast2seff.examplesrc.providingcomponent.ProvidingComponentImpl;

public class RequiringComponentImpl implements ProvidingInterface {
    private final InterfaceA providingComponentImpl;

    private final InputStream inStream;
	private final InterfaceA interfaceA;

    public RequiringComponentImpl(final ProvidingComponentImpl providingComponentImpl, InterfaceA interfaceA) {
        this.providingComponentImpl = providingComponentImpl;
        this.interfaceA = interfaceA;
        inStream = new ByteArrayInputStream("test".getBytes());
    }
    
    public void testDoExternalCallViaInterface(){
    	interfaceA.testExternalCall();
    }
    
    public String testDoExternalCallWithSimpleParametersAndReturnTypeViaInterface(final String str1, final String str2) {
        return interfaceA.testExternalCallWithSimpleParametersAndReturnType(str1, str2);
    }

    public void testDoExternalCall() {
        this.providingComponentImpl.testExternalCall();
    }

    public String testDoExternalCallWithSimpleParametersAndReturnType(final String str1, final String str2) {
        return this.providingComponentImpl.testExternalCallWithSimpleParametersAndReturnType(str1, str2);
    }
    
    public void testDoLibraryCall() throws IOException {
        inStream.close();
    }

    public void testSimpleConditionWithElse() {
        boolean test = false;
        if (test) {
            internalCall();
        } else {
            testDoInternalCall();
        }
    }

    public void testConditionWithExternalCallInIf(boolean test) {
        if (test) {
            this.providingComponentImpl.testExternalCall();
        } else {
            testDoInternalCall();
        }
    }

    public void testConditionWithExternalCallInElse(boolean test) {
        if (test) {
            testDoInternalCall();
        } else {
            this.providingComponentImpl.testExternalCall();
        }
    }

    public void testConditionWithExternalCallInCondition(boolean test) {
        if (this.providingComponentImpl.testExternalCallWithSimpleParametersAndReturnType(null, null) == null) {
            testDoInternalCall();
        } else {
            internalCall();
        }
    }

    public void testConditionWithLibraryCallInCondition(boolean test) throws IOException {
        if (inStream.available() > 1) {
            testDoInternalCall();
        } else {
            internalCall();
        }
    }

    public void testConditionWithExternalCallInIfAndElse(boolean test) {
        if (test) {
            this.providingComponentImpl.testExternalCall();
        } else {
            this.providingComponentImpl.testExternalCall();
        }
    }

    public void testSwitchCaseWithExternalCallInCase(int i) {
        switch (i) {
        case 1:
            this.providingComponentImpl.testExternalCall();
            break;
        case 2:
            this.providingComponentImpl.testExternalCall();
            break;
        case 3:
            this.providingComponentImpl.testExternalCall();
            break;
        default:
            this.providingComponentImpl.testExternalCall();
        }
    }

    public void testSwitchCaseWithFourListAnd10_9_7_4Statements(int i) {
        switch (i) {
        case 1:
            this.providingComponentImpl.testExternalCall();
        case 2:
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
        case 3:
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
        default:
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
        }
    }
    
    public void testSwitchCaseWithFourListAnd2_9_7_4Statements(int i) {
        switch (i) {
        case 1:
            this.providingComponentImpl.testExternalCall();
            break;
        case 2:
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
        case 3:
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
        default:
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
        }
    }
    
    public void testSwitchCaseWithFourListAnd7_6_4_4Statements(int i) {
        switch (i) {
        case 1:
            this.providingComponentImpl.testExternalCall();
        case 2:
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
        case 3:
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
            break;
        default:
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
            this.providingComponentImpl.testExternalCall();
        }
    }
    
    public void testSwitchCaseWithExternalCallInFirstCase(int i){
        switch (i) {
        case 1:
            this.providingComponentImpl.testExternalCall();
        case 2:
        case 3:
            break;
        default:
        }
    }
    
    public void testSwitchCaseWithExternalCallInFirstAndSecondCase(int i){
        switch (i) {
        case 1:
            this.providingComponentImpl.testExternalCall();
        case 2:
            this.providingComponentImpl.testExternalCall();
        case 3:
            break;
        default:
        }
    }
    
    public void testSwitchCaseWithInternalCallInCaseAndExternalCallDefault(int i){
        switch (i) {
        case 1:
            internalCall();
            break;
        case 3:
            break;
        default:
            this.providingComponentImpl.testExternalCall();
        }
    }

    public void testInternalCallThatContainsInternalActionAndForLoopWithExternalCall(){
        System.out.println("Dummy Internal Action");
        internalCallContainingInternalActionAndForLoopWithExternalCall();
    }
    
    public void testForLoopWithExternalCall() {
        for (int i = 0; i < 10; i++) {
            this.providingComponentImpl.testExternalCall();
        }
    }

    public void testForLoopWithInternalCall() {
        for (int i = 0; i < 10; i++) {
            internalCall();
        }
    }

    public void testForLoopWithInternalLibraryAndExternalCall() throws IOException {
        for (int i = 0; i < 10; i++) {
            internalCall();
            this.inStream.available();
            this.providingComponentImpl.testExternalCall();
        }
    }

    public void testWhileLoopWithExternalCall(int i) {
        while (i < 10) {
            this.providingComponentImpl.testExternalCall();
        }
    }

    public void testDoWhileLoopWithExternalCall(int i) {
        do {
            this.providingComponentImpl.testExternalCall();
        } while (i < 10);
    }

    public void testForEachLoopWithExternalCall(List<Object> objectList) {
        for (Object object : objectList) {
            this.providingComponentImpl.testExternalCall();
        }
    }

    public void testTryBlockWithExternalCallInTry() {
        try {
            this.providingComponentImpl.testExternalCall();
        } catch (Exception e) {

        }
    }

    public void testTryBlockWithExternalCallInCatch() {
        try {

        } catch (Exception e) {
            this.providingComponentImpl.testExternalCall();
        }
    }

    public void testTryBlockWithExternalCallInFinally() {
        try {

        } catch (Exception e) {

        } finally {
            this.providingComponentImpl.testExternalCall();
        }
    }

    public void testTryBlockWithInternalCallInTryLibraryCallInCatchAndExternalCallInFinally() throws IOException {
        try {
            internalCall();
        } catch (Exception e) {
            inStream.available();
        } finally {
            this.providingComponentImpl.testExternalCall();
        }
    }
    
    public void testTryBlockWithExternalCallInInternalCallInTryBlock() throws Throwable{
        try{
            internalCallContainingExternalCall();
        }catch(Exception e){
            inStream.available();
        }
    }

    public void testDoInternalCall() {
        internalCall();
    }
    
    public void testExternalCallInInternalCall(){
        internalCallContainingExternalCall();
    }
    
    public void testExternalCallAsInputForInternalCall(){
        internalCallWithDummyStatement(this.providingComponentImpl.testExternalCall());
    }
    
    public void testInternalCallAsInputForInternalCall(){
        internalCall(internalCall);
    }
    
    private void internalCallWithDummyStatement(Object ob){
        System.out.println("");
    }

    private Object internalCall() {
        internalCall();
    }
    
    private Object internalCall(Object obj){
        internalCall(obj);
    }
    
    private void internalCallContainingExternalCall(){
        this.providingComponentImpl.testExternalCall();
    }
    
    private void internalCallContainingInternalActionAndForLoopWithExternalCall(){
        System.out.println("Dummy Internal Action");
        for(int i = 0; i < 10; i++){
            this.providingComponentImpl.testExternalCall();
        }
    }
    
    private void testForLoopWithInternalCallContainingExternalCall(){
        for(int i = 0; i < 10;i++){
            internalCallContainingExternalCall();
        }
    }
    
    @Override
    public void providingMethod() {
        System.out.println("providingMethod");
    }
    
    public void testInternalCallAsInputForExternalCall(){
        this.providingComponentImpl.testExternalCallWithSimpleParametersAndReturnType(internalCallRetString(), internalCallRetString());
    }
    
    public void testSimpleStatement(){
        int i = 0;
        i++;
        --i;
    }
    
    private String internalCallRetString(){
        System.out.println("internalCallRetString");
        return "";
    }

}
