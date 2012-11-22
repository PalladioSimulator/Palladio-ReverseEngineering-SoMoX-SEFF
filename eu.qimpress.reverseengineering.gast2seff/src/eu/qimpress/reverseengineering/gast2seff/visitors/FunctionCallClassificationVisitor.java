package eu.qimpress.reverseengineering.gast2seff.visitors;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import de.fzi.gast.accesses.BaseAccess;
import de.fzi.gast.accesses.FunctionAccess;
import de.fzi.gast.statements.BlockStatement;
import de.fzi.gast.statements.Branch;
import de.fzi.gast.statements.BranchStatement;
import de.fzi.gast.statements.LoopStatement;
import de.fzi.gast.statements.SimpleStatement;
import de.fzi.gast.statements.Statement;
import de.fzi.gast.statements.util.statementsSwitch;

/**
 * Classifies function calls are internal, library, or external calls.
 * Transitively assigns that type to outer statements like loops
 * or branches.
 * @author Steffen Becker, Klaus Krogmann
 *
 */
public class FunctionCallClassificationVisitor extends statementsSwitch<BitSet> {

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
	public BitSet caseBlockStatement(BlockStatement object) {
		if (annotations.containsKey(object)) 
			return annotations.get(object);

		BitSet myType = computeChildAnnotations(new BitSet(),object.getStatements());
		annotations.put(object, myType);
		
		return myType;
	}

	@Override
	public BitSet caseBranchStatement(BranchStatement object) {
		if (annotations.containsKey(object)) 
			return annotations.get(object);

		for (Branch branch : object.getBranches()) {
			doSwitch(branch.getStatement());
		}
		List<Statement> branchStatements = new ArrayList<Statement>();
		for (Branch branch : object.getBranches()) {
			branchStatements.add(branch.getStatement());
		}
		BitSet myType = computeChildAnnotations(new BitSet(), branchStatements);
		annotations.put(object, myType);
		
		return myType;
	}

	@Override
	public BitSet caseLoopStatement(LoopStatement object) {
		if (annotations.containsKey(object)) 
			return annotations.get(object);
		
		doSwitch(object.getBody());
		
		BitSet myType = annotations.get(object.getBody());
		annotations.put(object, myType);
		
		return myType;
	}
	
	@Override
	public BitSet caseExceptionHandler(de.fzi.gast.statements.ExceptionHandler object) {
		if (annotations.containsKey(object)) 
			return annotations.get(object);
		List<Statement> allChildStatements = new ArrayList<Statement>();

		// handle guarded block
		doSwitch(object.getGuardedBlock());
		allChildStatements.addAll(object.getGuardedBlock().getStatements());

		// handle finally block
		if(object.getFinallyBlock() != null) {
			doSwitch(object.getFinallyBlock());
			allChildStatements.addAll(object.getFinallyBlock().getStatements());
		}
 		
		BitSet myType = computeChildAnnotations(new BitSet(), allChildStatements);
		
		annotations.put(object, myType);
		
		return myType;
	};

	@Override
	public BitSet caseSimpleStatement(SimpleStatement object) {
		if (annotations.containsKey(object)) 
			return annotations.get(object);

		BitSet myType = this.myStrategy.classifySimpleStatement(object);
		annotations.put(object,myType);
		
		if (myType.get(getIndex(FunctionCallType.INTERNAL))) {
			// Also annotate the internal method
			FunctionAccess functionAccess = getFunctionAccess(object);
			BlockStatement targetFunctionBody = functionAccess.getTargetFunction().getBody();
			if (targetFunctionBody != null) {
				logger.trace("visiting internal call. accessed class: " + functionAccess.getAccessedClass());
				doSwitch(targetFunctionBody);
			} else {
				logger.warn("Behaviour not set in GAST for "+functionAccess.getTargetFunction().getSimpleName());
			}
		}
		
		return myType;
	}
	
	private FunctionAccess getFunctionAccess(SimpleStatement object) {
		for (BaseAccess a : object.getAccesses()) {
			if (a instanceof FunctionAccess) {
				return (FunctionAccess) a;
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
}
