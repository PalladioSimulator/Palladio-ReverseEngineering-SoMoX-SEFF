package org.somox.gast2seff.visitors;

import java.util.BitSet;
import java.util.Collection;
import java.util.List;

import org.emftext.language.java.statements.Statement;

/**
 * Interface for the function call classification strategy. Implementing classes are used by the
 * {@link FunctionCallClassificationVisitor} to decide which statements are external calls, internal
 * calls, and library calls
 *
 * @author Steffen Becker
 */
public interface IFunctionClassificationStrategy {

    /**
     * Update the passed type with the type info to be merged into the existing type. This method is
     * used to calculate the annotations of control flow nodes which have child elements like loops
     * and branches. For example, if a loop has a child statement which is an external call the
     * loops annotation should be updated to indicate that itself should be treated as containing an
     * external call
     *
     * @param typeToUpdate
     *            The type flags into which the new type flags should be merged into
     * @param typeInfosToMerge
     *            The type flags of one of the child nodes in the GAST to be merged into the parent
     *            node
     */
    void mergeFunctionCallType(BitSet typeToUpdate, Collection<BitSet> typeInfosToMerge);

    /**
     * Given a simple statement there are four options what it could mean: an internal calculation,
     * an internal method call, an external call or a call to a library function. This method is
     * used to decide on the type of the simple statement
     *
     * @param object
     *            The simple statement to be classified
     * @return A BitSet where the flags indicate the type of the statement. See
     *         {@link FunctionCallClassificationVisitor} for details on this.
     */
    List<BitSet> classifySimpleStatement(Statement object);// GAST2SEFFCHANGE
}
