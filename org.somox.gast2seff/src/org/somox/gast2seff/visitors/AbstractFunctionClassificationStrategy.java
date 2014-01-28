package org.somox.gast2seff.visitors;

import java.util.BitSet;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.types.Type;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor.FunctionCallType;
import org.somox.kdmhelper.KDMHelper;

//import de.fzi.gast.accesses.BaseAccess;//GAST2SEFFCHANGE
//import de.fzi.gast.accesses.FunctionAccess;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.SimpleStatement;//GAST2SEFFCHANGE
//import de.fzi.gast.statements.Statement;//GAST2SEFFCHANGE
//import de.fzi.gast.types.GASTClass;//GAST2SEFFCHANGE

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
	 * @see org.somox.gast2seff.visitors.IFunctionClassificationStrategy#classifySimpleStatement(de.fzi.gast.statements.SimpleStatement)
	 */
	public BitSet classifySimpleStatement(Statement object) {//GAST2SEFFCHANGE	//can/should be replaced with Statement	
		
		BitSet result = new BitSet();
		MethodCall functionAccess = getFunctionAccess(object);//GAST2SEFFCHANGE
		if (functionAccess != null) {
			if(isExternalCall(functionAccess)) {
				logger.debug("Found external call: "+KDMHelper.getMethod(functionAccess ).getName());//GAST2SEFFCHANGE//GAST2SEFFCHANGE
				result.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.EXTERNAL));
			} else if (isLibraryCall(functionAccess)) {
				logger.debug("Found library call: "+KDMHelper.getMethod(functionAccess ).getName());//GAST2SEFFCHANGE//GAST2SEFFCHANGE
				result.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.LIBRARY));			 				
			} else { //default: internal call
				logger.debug("Found internal call: "+KDMHelper.getMethod(functionAccess ).getName());//GAST2SEFFCHANGE//GAST2SEFFCHANGE
				result.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.INTERNAL));
			}
		} 
		return result;
	}

	/* (non-Javadoc)
	 * @see org.somox.gast2seff.visitors.IFunctionClassificationStrategy#mergeFunctionCallType(java.util.BitSet, java.util.BitSet)
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
	protected abstract boolean isExternalCall(MethodCall functionAccess);//GAST2SEFFCHANGE

	/** 
	 * Decide whether the given simple statement which is the given function access is a library call,
	 * i.e., a call to an internal method which should be inlined into the SEFF
	 * @param functionAccess The function access to test
	 * @return true if the function access is 
	 */
	protected abstract boolean isLibraryCall(MethodCall functionAccess);//GAST2SEFFCHANGE

	/**
	 * @param statement A statement
	 * @return The GAST class in which the statement is contained or null if the statement is not
	 * part of a GAST class
	 */
	protected Type findHostingClass(Statement statement) {//GAST2SEFFCHANGE//GAST2SEFFCHANGE
		EObject current = statement;
		while (current != null && !(current instanceof Type))//GAST2SEFFCHANGE
			current = current.eContainer();
		return (Type) current;//GAST2SEFFCHANGE
	}

	/**
	 * @param object A statement
	 * @return The function access of the statement or null if the statement is not a function access
	 */
	private MethodCall getFunctionAccess(Statement object) {//GAST2SEFFCHANGE//GAST2SEFFCHANGE
		//can should be replaced by Statement
		for (Commentable a : KDMHelper.getAllAccesses(object)) {//GAST2SEFFCHANGE//GAST2SEFFCHANGE
			if (a instanceof MethodCall) {//GAST2SEFFCHANGE
				return (MethodCall) a;//GAST2SEFFCHANGE
			}
		}
		return null;
	}

}
