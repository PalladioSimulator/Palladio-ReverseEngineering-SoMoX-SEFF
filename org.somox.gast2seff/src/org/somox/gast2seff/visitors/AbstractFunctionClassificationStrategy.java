package org.somox.gast2seff.visitors;

import java.util.BitSet;

import org.apache.log4j.Logger;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.statements.Statement;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor.FunctionCallType;
import org.somox.kdmhelper.KDMHelper;

/**
 * Base implementation of {@link IFunctionClassificationStrategy}. Delagates the decisions on the
 * function call types to subclasses
 *
 * @author Steffen Becker, Klaus Krogmann
 */
public abstract class AbstractFunctionClassificationStrategy implements IFunctionClassificationStrategy {

    private final Logger logger = Logger.getLogger(BasicFunctionClassificationStrategy.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.somox.gast2seff.visitors.IFunctionClassificationStrategy#classifySimpleStatement(de.fzi
     * .gast.statements.SimpleStatement)
     */
    @Override
    public BitSet classifySimpleStatement(final Statement object) {// GAST2SEFFCHANGE //can/should
        // be replaced with Statement

        final BitSet result = new BitSet();
        final MethodCall functionAccess = VisitorUtils.getMethodCall(object);// GAST2SEFFCHANGE
        if (functionAccess != null) {
            if (this.isExternalCall(functionAccess)) {
                this.logger.debug("Found external call: " + KDMHelper.getMethod(functionAccess).getName());// GAST2SEFFCHANGE//GAST2SEFFCHANGE
                result.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.EXTERNAL));
            } else if (this.isLibraryCall(functionAccess)) {
                this.logger.debug("Found library call: " + KDMHelper.getMethod(functionAccess).getName());// GAST2SEFFCHANGE//GAST2SEFFCHANGE
                result.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.LIBRARY));
            } else { // default: internal call
                this.logger.debug("Found internal call: " + KDMHelper.getMethod(functionAccess).getName());// GAST2SEFFCHANGE//GAST2SEFFCHANGE
                result.set(FunctionCallClassificationVisitor.getIndex(FunctionCallType.INTERNAL));
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.somox.gast2seff.visitors.IFunctionClassificationStrategy#mergeFunctionCallType(java.util
     * .BitSet, java.util.BitSet)
     */
    @Override
    public void mergeFunctionCallType(final BitSet myType, final BitSet functionCallType) {
        myType.or(functionCallType);
    }

    /**
     * Decide whether the given simple statement which is the given function access is an external
     * call, i.e., a call which results in a external call action in the SEFF
     *
     * @param functionAccess
     *            The function access to test
     * @return true if the function access is an external call
     */
    protected abstract boolean isExternalCall(MethodCall functionAccess);

    /**
     * Decide whether the given simple statement which is the given function access is a library
     * call, i.e., a call to an internal method which should be inlined into the SEFF
     *
     * @param functionAccess
     *            The function access to test
     * @return true if the function access is
     */
    protected abstract boolean isLibraryCall(MethodCall functionAccess);

}
