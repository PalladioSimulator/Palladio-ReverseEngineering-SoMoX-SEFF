package eu.qimpress.reverseengineering.gast2seff.visitors;

import java.util.ArrayList;

import org.eclipse.gmt.modisco.java.BreakStatement;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.SwitchCase;
import org.eclipse.gmt.modisco.java.SwitchStatement;

public class SwitchStatementHelper {


	
		public static ArrayList<ArrayList<Statement>> createBlockListFromSwitchStatement(
				SwitchStatement switchStatement) {
			ArrayList<ArrayList<Statement>> blockList = new ArrayList<ArrayList<Statement>>();
			
	//		Iterator<Statement> iterator = object.getStatements().iterator();
			//TODO change this algorithm for case without break
			//TODO extract method
			for(int i=0 ; i < switchStatement.getStatements().size() ; i++){
				
				Statement statement = switchStatement.getStatements().get(i);
				if(statement instanceof SwitchCase){
					ArrayList<Statement> block = new ArrayList<Statement>();
					
					while(true){
						//if is last statement cancel
						if (i == switchStatement.getStatements().size() - 1) {
							block.add(statement);
							break;
						}
						Statement nextStatement = switchStatement.getStatements().get(++i);
						if(!(nextStatement instanceof BreakStatement)){
							block.add(nextStatement);
						}
						else{
							break;
						}
					}
					blockList.add(block);
				}
			}
			return blockList;
		}

}
