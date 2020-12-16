package org.somox.gast2seff.visitors;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.statements.Break;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.SwitchCase;

/**
 * Computes for a {@link SwitchStatement} the case branches in a way that to the case branches, that
 * do not end with break, the following case branch is added.
 *
 * @author Oliver, Michael
 *
 */
public class SwitchStatementHelper {

    private static final Logger logger = Logger.getLogger(SwitchStatementHelper.class.getSimpleName());

    public static List<List<Statement>> createBlockListFromSwitchStatement(final Switch switchStatement) {
        final ArrayList<List<Statement>> blockList = new ArrayList<List<Statement>>();

        for (final SwitchCase switchCase : switchStatement.getCases()) {
            final List<Statement> currentBlock = findNextBlock(switchCase, switchStatement);
            blockList.add(currentBlock);
        }
        return blockList;
    }

    private static List<Statement> findNextBlock(final SwitchCase switchCase, final Switch switchStatement) {
        if (endsWithBreakStatement(switchCase) || isLastBlock(switchCase, switchStatement.getCases())) {
            return switchCase.getStatements();
        }
        final List<Statement> currentBlock = new ArrayList<Statement>(switchCase.getStatements().size() * 4);
        currentBlock.addAll(switchCase.getStatements());
        final int nextIndex = switchStatement.getCases().indexOf(switchCase) + 1;
        final SwitchCase nextSwitchCase = switchStatement.getCases().get(nextIndex);
        final List<Statement> nextBlock = findNextBlock(nextSwitchCase, switchStatement);
        currentBlock.addAll(nextBlock);
        return currentBlock;

    }

    private static boolean isLastBlock(final SwitchCase switchCase, final EList<SwitchCase> cases) {
        return cases.indexOf(switchCase) == cases.size() - 1;
    }

    private static boolean endsWithBreakStatement(final SwitchCase switchCase) {
        if (switchCase.getStatements().isEmpty()) {
            return false;
        }
        final Statement lastStatement = switchCase.getStatements().get(switchCase.getStatements().size() - 1);
        if (lastStatement instanceof Break) {
            return true;
        }
        final List<Break> breaks = switchCase.getChildrenByType(Break.class);
        if (0 < breaks.size()) {
            logger.warn("Break found in switch case statement " + switchCase
                    + " - it is, however, not the last statement in the switch case.");
            return true;
        }
        return false;
    }

}
