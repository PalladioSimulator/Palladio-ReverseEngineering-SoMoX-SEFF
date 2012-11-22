package org.somox.analyzer.simplemodelanalyzer.detection.util;

import de.fzi.gast.functions.Function;

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
	public static boolean areFunctionsEqual(Function function1, Function function2) {
		//preconditions
		if(function1.getReturnTypeDeclaration() == null || function2.getReturnTypeDeclaration() == null ||
				function1.getFormalParameters() == null || function2.getFormalParameters() == null) {
			return false;
		}
		
		//checks
		if(! ( function1.getSimpleName().equals(function2.getSimpleName()) && //name				
				function1.getReturnTypeDeclaration().getTargetType() == function2.getReturnTypeDeclaration().getTargetType() //return type
			) ) {
			return false;
		}
		
		if(function1.getFormalParameters() != null &&  function2.getFormalParameters() != null) { //parameter size (faster than directly checking parameter types) 
			if(! (function1.getFormalParameters().size() == function2.getFormalParameters().size()) ) {
				return false;
			}
		}					
		
		for(int i = 0; i < function1.getFormalParameters().size(); i++) { //parameter types
			if(! function1.getFormalParameters().get(i).getType().equals(function2.getFormalParameters().get(i).getType()) ) {
				return false;
			}
			
		}		
		
		return true;
	}
}
