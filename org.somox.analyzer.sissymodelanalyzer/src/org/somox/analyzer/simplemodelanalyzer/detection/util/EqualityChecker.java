package org.somox.analyzer.simplemodelanalyzer.detection.util;

import org.emftext.language.java.members.Method;
import org.emftext.language.java.types.TypeReference;

//import de.fzi.gast.functions.Function;

//SOMOXTODOCHANGE more precise compare of Type (->Parameterized Type)

/**
 * Helper class
 * 
 * @author Klaus Krogmann
 *
 */
public class EqualityChecker {

    /**
     * Checks the equality of two functions by comparing their signatures. Checks name, return type,
     * number of parameters and types of parameters.
     * 
     * @param function1
     * @param function2
     * @return true if both functions are equal; false else
     */
    public static boolean areFunctionsEqual(final Method function1, final Method function2) {
        // preconditions

        if (getReturnTypeAccess(function1) == null || getReturnTypeAccess(function2) == null
                || function1.getParameters() == null || function2.getParameters() == null) {
            return false;
        }

        // checks
        if (!(function1.getName().equals(function2.getName()) && // name
        getReturnTypeAccess(function1).getTarget() == getReturnTypeAccess(function2).getTarget() // return
                                                                                                 // type
        )) {
            return false;
        }

        if (function1.getParameters() != null && function2.getParameters() != null) { // parameter
                                                                                      // size
                                                                                      // (faster
                                                                                      // than
                                                                                      // directly
                                                                                      // checking
                                                                                      // parameter
                                                                                      // types)
            if (!(function1.getParameters().size() == function2.getParameters().size())) {
                return false;
            }
        }

        for (int i = 0; i < function1.getParameters().size(); i++) { // parameter types
            // PDF24.11.14: handles null-references (avoids some errors)
            if (function1.getParameters().get(i).getTypeReference().getTarget() == null
                    && function2.getParameters().get(i).getTypeReference().getTarget() == null) {
                continue;
            }
            if (function1.getParameters().get(i).getTypeReference().getTarget() == null) {
                return false;
            }
            if (function2.getParameters().get(i).getTypeReference().getTarget() == null) {
                return false;
            }
            // PDF END
            if (!function1.getParameters().get(i).getTypeReference().getTarget()
                    .equals(function2.getParameters().get(i).getTypeReference().getTarget())) {

                return false;
            }

        }

        return true;
    }

    private static TypeReference getReturnTypeAccess(final Method function) {// REALLYADDED
        if (function instanceof Method) {// REALLYADDED
            final Method method = function;// REALLYADDED
            return method.getTypeReference();// REALLYADDED
        } else {// REALLYADDED
            return null;// REALLYADDED
        }// REALLYADDED
    }// REALLYADDED

    // SOMOXTODOCHANGE
    // private static Type getTypeFromTypeAccess(TypeAccess typeAccess){
    // if(typeAccess == null){
    // return null;
    // } else{
    // return typeAccess.getType();
    // }
    // }
}