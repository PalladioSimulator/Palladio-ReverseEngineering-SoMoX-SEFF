package org.somox.test.gast2seff.visitors;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.Switch;
import org.junit.Test;
import org.somox.gast2seff.visitors.SwitchStatementHelper;

public class SwitchStatementHelperTest extends JaMoPP2SEFFBaseTest {

    @Test
    public void testSwitchCaseWithFourListAnd10_9_7_4Statements() {
        final String methodName = super.getTestMethodName();
        final List<Integer> expectedStatementList = this.createExpectedStatementList(10, 9, 7, 4);
        this.testCreateBlock(methodName, expectedStatementList);
    }

    @Test
    public void testSwitchCaseWithFourListAnd2_9_7_4Statements() {
        final String methodName = super.getTestMethodName();
        final List<Integer> expectedStatementList = this.createExpectedStatementList(2, 9, 7, 4);
        this.testCreateBlock(methodName, expectedStatementList);
    }

    @Test
    public void testSwitchCaseWithFourListAnd7_6_4_4Statements() {
        final String methodName = super.getTestMethodName();
        final List<Integer> expectedStatementList = this.createExpectedStatementList(7, 6, 4, 4);
        this.testCreateBlock(methodName, expectedStatementList);
    }

    private List<Integer> createExpectedStatementList(final int... statementsInList) {
        final List<Integer> intList = new ArrayList<Integer>(statementsInList.length);
        for (final Integer statementsInStatement : statementsInList) {
            intList.add(statementsInStatement);
        }
        return intList;
    }

    private void testCreateBlock(final String methodName, final List<Integer> expectedStatementListSize) {
        final ClassMethod method = (ClassMethod) super.findMethodInClassifier(methodName, REQUIRED_COMPONENT_NAME
                + "Impl");
        assertEquals("There should be only one statement (the switch statement) in the method " + method.getName(), 1,
                method.getStatements().size());

        // create the block list
        final List<List<Statement>> blockList = SwitchStatementHelper
                .createBlockListFromSwitchStatement((Switch) method.getStatements().get(0));

        assertEquals("Wrong number of expected statement lists", expectedStatementListSize.size(), blockList.size());
        for (int i = 0; i < blockList.size(); i++) {
            final List<Statement> statementList = blockList.get(i);
            assertEquals("Wrong number of expected statements in stament list with number " + i,
                    (int) expectedStatementListSize.get(i), statementList.size());
        }
    }

}
