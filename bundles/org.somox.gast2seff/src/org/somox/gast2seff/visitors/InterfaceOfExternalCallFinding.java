package org.somox.gast2seff.visitors;

import org.emftext.language.java.members.Method;
import org.emftext.language.java.statements.Statement;
import org.palladiosimulator.pcm.repository.Role;
import org.palladiosimulator.pcm.repository.Signature;

/**
 * Implementations of the interface are used by {@link JaMoPPStatementVisitor} to find the called
 * interface port and the interface operation of an external call.
 *
 * @author langhamm
 */
public interface InterfaceOfExternalCallFinding {

    
    /**
     * Query the interface port for the function access.
     *
     * @param calledMethod
     *            The access to find in the PCM
     * @param statement
     *            The statement that issued the method call            
     * @return interface port and operation corresponding to the access.
     */
    public InterfacePortOperationTuple getCalledInterfacePort(final Method calledMethod, Statement statement);
    
    default public InterfacePortOperationTuple getCalledInterfacePort(final Method calledMethod){
        return getCalledInterfacePort(calledMethod, null);
    }

    public class InterfacePortOperationTuple {
        public Role role;
        public Signature signature;
    }
}
