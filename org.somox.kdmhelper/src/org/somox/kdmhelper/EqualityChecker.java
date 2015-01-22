package org.somox.kdmhelper;

import org.emftext.language.java.members.Method;
import org.emftext.language.java.types.TypeReference;

//import de.fzi.gast.functions.Function;

//SOMOXTODOCHANGE more precise compare of Type (->Parameterized Type)
//TODO burkha this code was copied because of a cycle. Change the plugin dependency.
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
        if (function1 == function2) {
            return true;
        }

        if (getReturnTypeReference(function1) == null || getReturnTypeReference(function2) == null
                || function1.getParameters() == null || function2.getParameters() == null) {
            return false;
        }

        // checks
        if (!(function1.getName().equals(function2.getName()) && getReturnTypeReference(function1) == getReturnTypeReference(function2))) {
            return false;
        }

        if (function1.getParameters() != null && function2.getParameters() != null) {
            // parameter size (faster than directly checking parameter types)
            if (!(function1.getParameters().size() == function2.getParameters().size())) {
                return false;
            }
        }

        for (int i = 0; i < function1.getParameters().size(); i++) { // parameter types
            if (!function1.getParameters().get(i).getTypeReference()
                    .equals(function2.getParameters().get(i).getTypeReference())) {
                return false;
            }

        }

        return true;
    }

    private static TypeReference getReturnTypeReference(final Method function) {
        return function.getTypeReference();
    }
}
