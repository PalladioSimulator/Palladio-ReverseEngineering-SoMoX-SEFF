package org.somox.gast2seff.visitors;

import java.util.ArrayList;

import org.emftext.language.java.statements.Break;
import org.emftext.language.java.statements.NormalSwitchCase;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.SwitchCase;




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
			Switch switchStatement) {
		ArrayList<ArrayList<Statement>> blockList = new ArrayList<ArrayList<Statement>>();

		for (int actStatementNo = 0; actStatementNo < switchStatement.getCases().size(); actStatementNo++) {

			Statement statement = (Statement) switchStatement.getCases().get(actStatementNo);
			if (statement instanceof SwitchCase) {
				int currentPointer = actStatementNo;
				ArrayList<Statement> block = new ArrayList<Statement>();

				while (true) {
					// if last statement
					if (actStatementNo == switchStatement.getCases().size() - 1) {
						SwitchCase lastStatement = switchStatement.getCases().get(actStatementNo); 
						if (lastStatement instanceof Break | lastStatement instanceof NormalSwitchCase) {
							break;
						} else{
							block.addAll(lastStatement.getStatements());
						}
						
					}
					//goto next statement
					actStatementNo++;
					SwitchCase nextStatement = switchStatement.getCases()
							.get(actStatementNo);
					if (nextStatement instanceof NormalSwitchCase) {
						continue;
					}
					//if the next Statement is not break, then add it
					if (!(nextStatement instanceof Break )) {
						block.addAll(nextStatement.getStatements());
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
