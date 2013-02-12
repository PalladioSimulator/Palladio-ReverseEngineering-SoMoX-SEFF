package eu.qimpress.reverseengineering.gast2seff.visitors;

import java.util.BitSet;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.Type;
import org.somox.kdmhelper.KDMHelper;

//import de.fzi.gast.accesses.BaseAccess;//GAST2SEFFCHANGE
//import de.fzi.gast.accesses.FunctionAccess;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.SimpleStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.Statement;//GAST2SEFFCHANGE
//import de.fzi.gast.types.GASTClass;//GAST2SEFFCHANGE
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
	public BitSet classifySimpleStatement(Statement object) {//GAST2SEFFCHANGE	//can/should be replaced with Statement	
		
		BitSet result = new BitSet();
		AbstractMethodInvocation functionAccess = getFunctionAccess(object);//GAST2SEFFCHANGE
		if (functionAccess != null) {
			if(isExternalCall(functionAccess)) {
				logger.debug("Found external call: "+functionAccess.getMethod().getName());//GAST2SEFFCHANGE//GAST2SEFFCHANGE
				result.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.EXTERNAL));
			} else if (isLibraryCall(functionAccess)) {
				logger.debug("Found library call: "+functionAccess.getMethod().getName());//GAST2SEFFCHANGE//GAST2SEFFCHANGE
				result.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.LIBRARY));			 				
			} else { //default: internal call
				logger.debug("Found internal call: "+functionAccess.getMethod().getName());//GAST2SEFFCHANGE//GAST2SEFFCHANGE
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
	protected abstract boolean isExternalCall(AbstractMethodInvocation functionAccess);//GAST2SEFFCHANGE

	/** 
	 * Decide whether the given simple statement which is the given function access is a library call,
	 * i.e., a call to an internal method which should be inlined into the SEFF
	 * @param functionAccess The function access to test
	 * @return true if the function access is 
	 */
	protected abstract boolean isLibraryCall(AbstractMethodInvocation functionAccess);//GAST2SEFFCHANGE

	/**
	 * @param statement A statement
	 * @return The GAST class in which the statement is contained or null if the statement is not
	 * part of a GAST class
	 */
	protected Type findHostingClass(org.eclipse.gmt.modisco.java.Statement statement) {//GAST2SEFFCHANGE//GAST2SEFFCHANGE
		EObject current = statement;
		while (current != null && !(current instanceof Type))//GAST2SEFFCHANGE
			current = current.eContainer();
		return (Type) current;//GAST2SEFFCHANGE
	}

	/**
	 * @param object A statement
	 * @return The function access of the statement or null if the statement is not a function access
	 */
	private AbstractMethodInvocation getFunctionAccess(Statement object) {//GAST2SEFFCHANGE//GAST2SEFFCHANGE
		//can should be replaced by Statement
		for (ASTNode a : KDMHelper.getAllAccesses(object)) {//GAST2SEFFCHANGE//GAST2SEFFCHANGE
			if (a instanceof AbstractMethodInvocation) {//GAST2SEFFCHANGE
				return (AbstractMethodInvocation) a;//GAST2SEFFCHANGE
			}
		}
		return null;
	}

}
