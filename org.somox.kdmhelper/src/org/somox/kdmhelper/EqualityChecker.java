package org.somox.kdmhelper;

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.ConstructorDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.emftext.language.java.members.Method;

//import de.fzi.gast.functions.Function;


//SOMOXTODOCHANGE more precise compare of Type (->Parameterized Type)
//TODO burkha this code was copied because of a cycle. Change the plugin dependency.
/**
 * Helper class
 * @author Klaus Krogmann
 *
 */
public class EqualityChecker {

	/**
	 * Checks the equality of two functions by comparing their signatures.
	 * Checks name, return type, number of parameters and types of parameters.
	 * @param function1
	 * @param function2
	 * @return true if both functions are equal; false else
	 */
	public static boolean areFunctionsEqual(Method function1, Method function2) {
		//preconditions
		
		
		if(getReturnTypeAccess(function1) == null || getReturnTypeAccess(function2) == null ||
				function1.getParameters() == null || function2.getParameters() == null) {
			return false;
		}
		
		//checks
		if(! ( function1.getName().equals(function2.getName()) && //name				
				getReturnTypeAccess(function1).getType() == getReturnTypeAccess(function2).getType() //return type
			) ) {
			return false;
		}
		
		if(function1.getParameters() != null &&  function2.getParameters() != null) { //parameter size (faster than directly checking parameter types)
			if(! (function1.getParameters().size() == function2.getParameters().size()) ) {
				return false;
			}
		}					
		
		for(int i = 0; i < function1.getParameters().size(); i++) { //parameter types
			if(! function1.getParameters().get(i).getType().getType().equals(function2.getParameters().get(i).getType().getType()) ) {
				return false;
			}
			
		}		
		
		return true;
	}
	
	
	private static TypeAccess getReturnTypeAccess(AbstractMethodDeclaration function){//REALLYADDED
		if(function instanceof MethodDeclaration){//REALLYADDED
			MethodDeclaration method = (MethodDeclaration) function;//REALLYADDED
			return method.getReturnType();//REALLYADDED
		} else {//REALLYADDED
			return null;//REALLYADDED
		}//REALLYADDED
	}//REALLYADDED
	
	//SOMOXTODOCHANGE
//	private static Type getTypeFromTypeAccess(TypeAccess typeAccess){
//		if(typeAccess == null){
//			return null;
//		} else{
//			return typeAccess.getType();
//		}
//	}
}