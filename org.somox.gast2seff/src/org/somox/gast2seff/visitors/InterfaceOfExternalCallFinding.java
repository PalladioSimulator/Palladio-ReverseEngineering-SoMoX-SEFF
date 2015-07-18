package org.somox.gast2seff.visitors;

import org.emftext.language.java.members.Method;

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
     * @return interface port and operation for corresponding to the access.
     */
    public InterfacePortOperationTuple getCalledInterfacePort(final Method calledMethod);

    public class InterfacePortOperationTuple {
        public Role role;
        public Signature signature;
    }
}
