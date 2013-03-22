package org.somox.gast2seff.visitors;

import java.util.ArrayList;

import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.gmt.modisco.java.BreakStatement;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.SwitchCase;
import org.eclipse.gmt.modisco.java.SwitchStatement;

/**
 * Computes for a {@link SwitchStatement} the case branches in a way that to the
 * case branches, that do not end with break, the following case branch is
 * added.
 * 
 * @author Oliver
 * 
 */
public class SwitchStatementHelper {

	public static ArrayList<ArrayList<Statement>> createBlockListFromSwitchStatement(
			SwitchStatement switchStatement) {
		ArrayList<ArrayList<Statement>> blockList = new ArrayList<ArrayList<Statement>>();

		for (int actStatementNo = 0; actStatementNo < switchStatement.getStatements().size(); actStatementNo++) {

			Statement statement = switchStatement.getStatements().get(actStatementNo);
			if (statement instanceof SwitchCase) {
				int currentPointer = actStatementNo;
				ArrayList<Statement> block = new ArrayList<Statement>();

				while (true) {
					// if last statement
					if (actStatementNo == switchStatement.getStatements().size() - 1) {
						Statement lastStatement = switchStatement.getStatements().get(actStatementNo); 
						if (lastStatement instanceof BreakStatement | lastStatement instanceof SwitchCase) {
							break;
						} else{
							block.add(lastStatement);
						}
						
					}
					//goto next statement
					actStatementNo++;
					Statement nextStatement = switchStatement.getStatements()
							.get(actStatementNo);
					if (nextStatement instanceof SwitchCase) {
						continue;
					}
					//if the next Statement is not break, then add it
					if (!(nextStatement instanceof BreakStatement )) {
						block.add(nextStatement);
					} else {
						break;
					}
				}
				
				blockList.add(block);
				actStatementNo = currentPointer;
			}
		}
		return blockList;
	}

}
