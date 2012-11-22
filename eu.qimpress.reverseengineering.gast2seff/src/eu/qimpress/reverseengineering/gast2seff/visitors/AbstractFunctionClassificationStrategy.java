package eu.qimpress.reverseengineering.gast2seff.visitors;

import java.util.BitSet;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import de.fzi.gast.accesses.BaseAccess;
import de.fzi.gast.accesses.FunctionAccess;
import de.fzi.gast.statements.SimpleStatement;
import de.fzi.gast.statements.Statement;
import de.fzi.gast.types.GASTClass;
import eu.qimpress.reverseengineering.gast2seff.visitors.FunctionCallClassificationVisitor.FunctionCallType;

/**
 * Base implementation of {@link IFunctionClassificationStrategy}. Delagates the decisions on the function
 * call types to subclasses
 * @author Steffen Becker, Klaus Krogmann
 */
public abstract class AbstractFunctionClassificationStrategy
implements
	IFunctionClassificationStrategy {

	private Logger logger = Logger.getLogger(BasicFunctionClassificationStrategy.class);	

	/* (non-Javadoc)
	 * @see eu.qimpress.reverseengineering.gast2seff.visitors.IFunctionClassificationStrategy#classifySimpleStatement(de.fzi.gast.statements.SimpleStatement)
	 */
	public BitSet classifySimpleStatement(SimpleStatement object) {		
		
		BitSet result = new BitSet();
		FunctionAccess functionAccess = getFunctionAccess(object);
		if (functionAccess != null) {
			if(isExternalCall(functionAccess)) {
				logger.debug("Found external call: "+functionAccess.getTargetFunction().getSimpleName());
				result.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.EXTERNAL));
			} else if (isLibraryCall(functionAccess)) {
				logger.debug("Found library call: "+functionAccess.getTargetFunction().getSimpleName());
				result.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.LIBRARY));			 				
			} else { //default: internal call
				logger.debug("Found internal call: "+functionAccess.getTargetFunction().getSimpleName());
				result.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.INTERNAL));
			}
		} 
		return result;
	}

	/* (non-Javadoc)
	 * @see eu.qimpress.reverseengineering.gast2seff.visitors.IFunctionClassificationStrategy#mergeFunctionCallType(java.util.BitSet, java.util.BitSet)
	 */
	public void mergeFunctionCallType(BitSet myType, BitSet functionCallType) {
		myType.or(functionCallType);
	}
		
	/** 
	 * Decide whether the given simple statement which is the given function access is an external call,
	 * i.e., a call which results in a external call action in the SEFF
	 * @param functionAccess The function access to test
	 * @return true if the function access is an external call
	 */
	protected abstract boolean isExternalCall(FunctionAccess functionAccess);	

	/** 
	 * Decide whether the given simple statement which is the given function access is a library call,
	 * i.e., a call to an internal method which should be inlined into the SEFF
	 * @param functionAccess The function access to test
	 * @return true if the function access is 
	 */
	protected abstract boolean isLibraryCall(FunctionAccess functionAccess);

	/**
	 * @param statement A statement
	 * @return The GAST class in which the statement is contained or null if the statement is not
	 * part of a GAST class
	 */
	protected GASTClass findHostingClass(Statement statement) {
		EObject current = statement;
		while (current != null && !(current instanceof GASTClass))
			current = current.eContainer();
		return (GASTClass) current;
	}

	/**
	 * @param object A statement
	 * @return The function access of the statement or null if the statement is not a function access
	 */
	private FunctionAccess getFunctionAccess(SimpleStatement object) {
		for (BaseAccess a : object.getAccesses()) {
			if (a instanceof FunctionAccess) {
				return (FunctionAccess) a;
			}
		}
		return null;
	}

}
