package org.palladiosimulator.somox.ast2seff.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;

/**
 * Computes for a {@link SwitchStatement} the case branches in a way that to the case branches, that do not end with
 * break, the following case branch is added.
 *
 * @author Fabian Wenzel
 *
 */
public class SwitchStatementUtil {
    private static final Logger LOGGER = Logger.getLogger(SwitchStatementUtil.class.getSimpleName());

    /**
     *
     * Switch Statement Util function to define the blocks which get modeled as branch transitions in the SEFF model
     * This function generates a list of all statements from the beginning of the case to the next break or end of the
     * switch statement Therefore statements can be included into several lists when a case is not ended with a break
     * statement
     *
     * @param switchStatement Switch statement which should get get broken in blocks
     * @return List of lists with statements of one block
     */
    public static List<List<Statement>> createBlockListFromSwitchStatement(final SwitchStatement switchStatement) {
        final ArrayList<List<Statement>> blockList = new ArrayList<>();
        final List<Statement> statementList = switchStatement.statements();
        int statementListSize = statementList.size();
        boolean firstSwitchCase = true;

        List<Statement> currentBlock = new ArrayList<>();
        List<List<Statement>> currentBreakList = new ArrayList<>();
        currentBreakList.add(currentBlock);

        for (int index = 0; index < statementListSize; index++) {
            Statement statementElement = statementList.get(index);

            if (statementElement instanceof SwitchCase) {
                if (firstSwitchCase) {
                    firstSwitchCase = false;
                } else {
                    List<Statement> newSwitchCaseBlock = new ArrayList<>();
                    currentBreakList.add(newSwitchCaseBlock);
                }
            }

            currentBreakList.forEach(block -> block.add(statementElement));

            if (index == statementListSize - 1) {
                currentBreakList.forEach(block -> blockList.add(block));
            } else if (statementElement instanceof BreakStatement) {
                currentBreakList.forEach(block -> blockList.add(block));
                currentBlock = new ArrayList<>();
                currentBreakList = new ArrayList<>();
                currentBreakList.add(currentBlock);
                firstSwitchCase = true;
            }
        }

        return blockList;
    }

}
