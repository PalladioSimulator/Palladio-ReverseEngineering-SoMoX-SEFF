package org.somox.gast2seff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.junit.jupiter.api.Test;
import org.somox.gast2seff.visitors.SwitchStatementHelper;

public class SwitchStatementHelperTest {
	
    @Test
	public void testBreakInEveryCase() {
    	AST ast = AST.newAST(AST.getJLSLatest(), false);
    	SwitchStatement switchStatement = ast.newSwitchStatement();
    	SwitchCase switchCase1 = ast.newSwitchCase();
    	SwitchCase switchCase2 = ast.newSwitchCase();
    	BreakStatement breakStatement1 = ast.newBreakStatement();
    	BreakStatement breakStatement2 = ast.newBreakStatement();
    	switchStatement.statements().add(switchCase1);
    	switchStatement.statements().add(breakStatement1);
    	switchStatement.statements().add(switchCase2);
    	switchStatement.statements().add(breakStatement2);
    	List<List<Statement>> blockList = SwitchStatementHelper.createBlockListFromSwitchStatement(switchStatement);
    	assertEquals(blockList.get(0).size(), 2);
    	assertEquals(blockList.size(), 2);
	}
    
    @Test
	public void testbreakForTwoCases() {
    	AST ast = AST.newAST(AST.getJLSLatest(), false);
    	SwitchStatement switchStatement = ast.newSwitchStatement();
    	SwitchCase switchCase1 = ast.newSwitchCase();
    	SwitchCase switchCase2 = ast.newSwitchCase();
    	BreakStatement breakStatement1 = ast.newBreakStatement();
    	BreakStatement breakStatement2 = ast.newBreakStatement();
    	switchStatement.statements().add(switchCase1);
    	switchStatement.statements().add(switchCase2);
    	switchStatement.statements().add(breakStatement2);
    	List<List<Statement>> blockList = SwitchStatementHelper.createBlockListFromSwitchStatement(switchStatement);
    	assertEquals(blockList.size(), 1);
    	assertEquals(blockList.get(0).size(), 3);
	}
    
    @Test
	public void testWithoutBreakAtTheEnd() {
    	AST ast = AST.newAST(AST.getJLSLatest(), false);
    	SwitchStatement switchStatement = ast.newSwitchStatement();
    	SwitchCase switchCase1 = ast.newSwitchCase();
    	SwitchCase switchCase2 = ast.newSwitchCase();
    	BreakStatement breakStatement1 = ast.newBreakStatement();
    	BreakStatement breakStatement2 = ast.newBreakStatement();
    	switchStatement.statements().add(switchCase1);
    	switchStatement.statements().add(switchCase2);
    	List<List<Statement>> blockList = SwitchStatementHelper.createBlockListFromSwitchStatement(switchStatement);
    	assertEquals(blockList.size(), 1);
    	assertEquals(blockList.get(0).size(), 2);
    	
	}
    
    @Test
	public void testEmptySwitchStatement() {
    	AST ast = AST.newAST(AST.getJLSLatest(), false);
    	SwitchStatement switchStatement = ast.newSwitchStatement();
    	List<List<Statement>> blockList = SwitchStatementHelper.createBlockListFromSwitchStatement(switchStatement);
    	assertEquals(blockList.size(), 0);
	}

}
