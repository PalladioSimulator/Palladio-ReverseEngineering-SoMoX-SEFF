package eu.qimpress.reverseengineering.gast2seff.visitors;

 import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.AssertStatement;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.BreakStatement;
import org.eclipse.gmt.modisco.java.EnhancedForStatement;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.ForStatement;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.SwitchCase;
import org.eclipse.gmt.modisco.java.SwitchStatement;
import org.eclipse.gmt.modisco.java.TryStatement;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.WhileStatement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.eclipse.gmt.modisco.java.emf.impl.BlockImpl;
import org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl;
import org.eclipse.gmt.modisco.java.emf.util.JavaSwitch;
import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.KDMHelper;

//import de.fzi.gast.accesses.BaseAccess;//GAST2SEFFCHANGE
//import de.fzi.gast.accesses.FunctionAccess;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.BlockStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.Branch;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.BranchStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.LoopStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.SimpleStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.Statement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.util.statementsSwitch;//GAST2SEFFCHANGE

/**
 * Classifies function calls are internal, library, or external calls.
 * Transitively assigns that type to outer statements like loops
 * or branches.
 * @author Steffen Becker, Klaus Krogmann
 *
 */
public class FunctionCallClassificationVisitor extends JavaSwitch<BitSet> {//GAST2SEFFCHANGE

	private static final Logger logger = Logger.getLogger(GastStatementVisitor.class);

	public FunctionCallClassificationVisitor(IFunctionClassificationStrategy strategy) {
		super();
		
		this.myStrategy = strategy;
	}
	
	public enum FunctionCallType {
		/**
		 * Classifies a component external call 
		 */
		EXTERNAL,
		
		/**
		 * Classifies a call to a system library 
		 */
		LIBRARY,
		
		/**
		 * Classifies an call to a method in the same class
		 */
		INTERNAL,
		
		/**
		 * Indicates whether a statements which has been visited before
		 */
		VISITED
	}
	
	private HashMap<Statement,BitSet> annotations = new HashMap<Statement, BitSet>();
	private IFunctionClassificationStrategy myStrategy = null;
	
	@Override
	public BitSet caseStatement(Statement object) {
		safePut(object,new BitSet());
		return new BitSet();
	}

	@Override
	public BitSet caseBlock(Block object) {//GAST2SEFFCHANGE
		if (annotations.containsKey(object)) 
			return annotations.get(object);

		BitSet myType = computeChildAnnotations(new BitSet(),object.getStatements());
		annotations.put(object, myType);
		
		return myType;
	}

	//use two overridden methods -> IfStatement, SwitchStatement

	//use three overridden methods: WhileStatement, ForStatement,EnhancedForStatement

	@Override
	public BitSet caseIfStatement(IfStatement object) {
		if (annotations.containsKey(object)) 
			return annotations.get(object);

		//If: ThenStatement, ElseStatement
		//Switch: hier mit HilfsKlasse ersetzen:
		//Neu: SwitchBranch: HelperMethode erstellen
//		for (Branch branch : object.getBranches()) {
//			doSwitch(branch.getStatement());
//		}
		//the following four lines replace the former 3 lines
		doSwitch(object.getThenStatement());
		if(object.getElseStatement() != null){
			doSwitch(object.getElseStatement());
		}
		

		
		List<Statement> branchStatements = new ArrayList<Statement>();
//		for (Branch branch : object.getBranches()) {
//			branchStatements.add(branch.getStatement());
//		}
		//the following four lines replace the former 3 lines
		branchStatements.add(object.getThenStatement());
		if(object.getElseStatement() != null){
			branchStatements.add(object.getElseStatement());
		}

		
		BitSet myType = computeChildAnnotations(new BitSet(), branchStatements);
		annotations.put(object, myType);
		
		return myType;
	}

	@Override
	public BitSet caseSwitchStatement(SwitchStatement switchStatement) {
		if (annotations.containsKey(switchStatement)) 
			return annotations.get(switchStatement);

		ArrayList<ArrayList<Statement>> branches = SwitchStatementHelper.createBlockListFromSwitchStatement(switchStatement);
		
//		Iterator<Statement> iterator = object.getStatements().iterator();
		//TODO change this algorithm for case without break
		//TODO extract method
		
//		for (Branch branch : switchStatement.getBranches()) {
//			doSwitch(branch.getStatement());
//		}
		for (ArrayList<Statement> branch : branches) {
			computeChildAnnotations(new BitSet(), branch);//copied from the BlockCase
		}
		
		List<Statement> branchStatements = new ArrayList<Statement>();
		for (ArrayList<Statement> branch : branches) {
			branchStatements.addAll(branch);
		}
		BitSet myType = computeChildAnnotations(new BitSet(), branchStatements);
		annotations.put(switchStatement, myType);
		
		return myType;
	}
	//TODO implement caseSwitchStatement
//	@Override
//	public BitSet caseSwitchStatement(SwitchStatement switchStatement) {
//		if (annotations.containsKey(switchStatement)) 
//			return annotations.get(switchStatement);
//
//		//If: ThenStatement, ElseStatement
//		//Switch: hier mit HilfsKlasse ersetzen:
//		//Neu: SwitchBranch: HelperMethode erstellen
//		
////		for (Branch branch : object.getBranches()) {
////			doSwitch(branch.getStatement());
////		}
//		ArrayList<ArrayList<Statement>> blockList = new ArrayList<ArrayList<Statement>>();
//		
////		Iterator<Statement> iterator = object.getStatements().iterator();
//		//TODO change this algorithm for case without break
//		//TODO extract method
//		for(int i=0 ; i < switchStatement.getStatements().size() ; i++){
//			
//			Statement statement = switchStatement.getStatements().get(i);
//			if(statement instanceof SwitchCase){
//				ArrayList<Statement> block = new ArrayList<Statement>();
//				
//				while(true){
//					//if is last statement cancel
//					if (i == switchStatement.getStatements().size() - 1) {
//						block.add(statement);
//						break;
//					}
//					Statement nextStatement = switchStatement.getStatements().get(++i);
//					if(!(nextStatement instanceof BreakStatement)){
//						block.add(nextStatement);
//					}
//					else{
//						break;
//					}
//				}
//				blockList.add(block);
//			}
//		}
//		
//		for (ArrayList<Statement> block : blockList) {
//			doSwitch(block);
//		}
//		
//		List<Statement> branchStatements = new ArrayList<Statement>();
////		for (Branch branch : object.getBranches()) {
////			branchStatements.add(branch.getStatement());
////		}
//		for (ArrayList<Statement> block : blockList) {
//			branchStatements.add(block);
//		}
//		
//		BitSet myType = computeChildAnnotations(new BitSet(), branchStatements);
//		annotations.put(switchStatement, myType);
//		
//		return myType;
//	}

//	@Override
//	public BitSet caseLoopStatement(LoopStatement object) {
//		if (annotations.containsKey(object)) 
//			return annotations.get(object);
//		
//		doSwitch(object.getBody());
//		
//		BitSet myType = annotations.get(object.getBody());
//		annotations.put(object, myType);
//		
//		return myType;
//	}
	

	//TODO refactor the following three methods; extract method
	@Override
	public BitSet caseEnhancedForStatement(EnhancedForStatement object) {
		if (annotations.containsKey(object))
			return annotations.get(object);

		doSwitch(object.getBody());

		BitSet myType = annotations.get(object.getBody());
		annotations.put(object, myType);

		return myType;
	}


	@Override
	public BitSet caseForStatement(ForStatement object) {
		if (annotations.containsKey(object))
			return annotations.get(object);

		doSwitch(object.getBody());

		BitSet myType = annotations.get(object.getBody());
		annotations.put(object, myType);

		return myType;
	}

	@Override
	public BitSet caseWhileStatement(WhileStatement object) {
		if (annotations.containsKey(object))
			return annotations.get(object);

		doSwitch(object.getBody());

		BitSet myType = annotations.get(object.getBody());
		annotations.put(object, myType);

		return myType;
	}


	//replace with TryStatement
	@Override
	public BitSet caseTryStatement(TryStatement object) {//GAST2SEFFCHANGE//GAST2SEFFCHANGE
		if (annotations.containsKey(object)) 
			return annotations.get(object);
		List<Statement> allChildStatements = new ArrayList<Statement>();

		// handle guarded block
		doSwitch(object.getBody());//GAST2SEFFCHANGE
		allChildStatements.addAll(object.getBody().getStatements());//GAST2SEFFCHANGE//GAST2SEFFCHANGE

		// handle finally block
		if(object.getFinally() != null) {//GAST2SEFFCHANGE
			doSwitch(object.getFinally());//GAST2SEFFCHANGE
			allChildStatements.addAll(object.getFinally().getStatements());//GAST2SEFFCHANGE//GAST2SEFFCHANGE
		}
 		
		BitSet myType = computeChildAnnotations(new BitSet(), allChildStatements);
		
		annotations.put(object, myType);
		
		return myType;
	}

	//replace with three:"ExpressionStatement, VariableDeclarationStatement, AssertStatement,	more..."
//	@Override
//	public BitSet caseSimpleStatement(SimpleStatement object) {
//		if (annotations.containsKey(object)) 
//			return annotations.get(object);
//
//		BitSet myType = this.myStrategy.classifySimpleStatement(object);
//		annotations.put(object,myType);
//		
//		if (myType.get(getIndex(FunctionCallType.INTERNAL))) {
//			// Also annotate the internal method
//			AbstractMethodInvocation functionAccess = getFunctionAccess(object);//GAST2SEFFCHANGE
//			Block targetFunctionBody = functionAccess.getMethod().getBody();//GAST2SEFFCHANGE//GAST2SEFFCHANGE//GAST2SEFFCHANGE
//			if (targetFunctionBody != null) {
//				logger.trace("visiting internal call. accessed class: " + GetAccessedType.getAccessedType(functionAccess));//GAST2SEFFCHANGE
//				doSwitch(targetFunctionBody);
//			} else {
//				logger.warn("Behaviour not set in GAST for "+functionAccess.getMethod().getName());//GAST2SEFFCHANGE//GAST2SEFFCHANGE
//			}
//		}
//		
//		return myType;
//	}
	
	//was former SimpleStatement
	@Override
	public BitSet caseAssertStatement(AssertStatement object) {
		return handleFormerSimpleStatement(object);
	}

	/**
	 * Helper Method to handle former SimpleStatement
	 * Used in AssertStatement, ExpressionStatement, VariableDeclarationStatement
	 * @param object
	 * @return
	 */
	private BitSet handleFormerSimpleStatement(Statement object) {
		if (annotations.containsKey(object)) 
			return annotations.get(object);

		BitSet myType = this.myStrategy.classifySimpleStatement(object);
		annotations.put(object,myType);
		
		if (myType.get(getIndex(FunctionCallType.INTERNAL))) {
			// Also annotate the internal method
			AbstractMethodInvocation functionAccess = getFunctionAccess(object);//GAST2SEFFCHANGE
			Block targetFunctionBody = functionAccess.getMethod().getBody();//GAST2SEFFCHANGE//GAST2SEFFCHANGE//GAST2SEFFCHANGE
			if (targetFunctionBody != null) {
				logger.trace("visiting internal call. accessed class: " + GetAccessedType.getAccessedType(functionAccess));//GAST2SEFFCHANGE
				doSwitch(targetFunctionBody);
			} else {
				logger.warn("Behaviour not set in GAST for "+functionAccess.getMethod().getName());//GAST2SEFFCHANGE//GAST2SEFFCHANGE
			}
		}
		
		return myType;
	}

	//was former SimpleStatement
	@Override
	public BitSet caseExpressionStatement(ExpressionStatement object) {
		return handleFormerSimpleStatement(object);	}

	//was former SimpleStatement
	@Override
	public BitSet caseVariableDeclarationStatement(
			VariableDeclarationStatement object) {
		return handleFormerSimpleStatement(object);	}


	private AbstractMethodInvocation getFunctionAccess(Statement object) {//GAST2SEFFCHANGE//GAST2SEFFCHANGE
		for (ASTNode a : KDMHelper.getAllAccesses(object)) {//GAST2SEFFCHANGE//GAST2SEFFCHANGE
			if (a instanceof AbstractMethodInvocation) {//GAST2SEFFCHANGE
				return (AbstractMethodInvocation) a;//GAST2SEFFCHANGE
			}
		}
		return null;
	}
	
	private void safePut(Statement object, BitSet type) {
		if (!annotations.containsKey(object))
			annotations.put(object,type);
	}

	private BitSet computeChildAnnotations(BitSet initalValue, List<Statement> childStatements) {
		// 1. visit all sub statements
		for (Statement s : childStatements) {
			doSwitch(s);
		}

		// 2. compute own type iteratively
		BitSet myType = initalValue;
		for (Statement s : childStatements) {
			this.myStrategy.mergeFunctionCallType(myType,annotations.get(s));
		}
		
		return myType;
	}	
	
	public static int getIndex(FunctionCallType type){
		switch(type){
			case INTERNAL: return 0;
			case EXTERNAL: return 1;
			case LIBRARY: return 2;
			case VISITED: return 3;
		}
		throw new UnsupportedOperationException();
	}

	public Map<Statement, BitSet> getAnnotations() {
		return Collections.unmodifiableMap(this.annotations);
	}
	
	@Override
	public BitSet defaultCase(EObject object) {
		System.out.println("------------------Not handled object by function call visitor:\n  " + object);

		// TODO Auto-generated method stub
		return super.defaultCase(object);
	}
}
