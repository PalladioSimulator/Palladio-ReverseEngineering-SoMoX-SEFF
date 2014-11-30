package org.somox.kdmhelper;

import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;

public class GetAccessedType {

	/**
	 * Computes the accessed type for an access.
	 * 
	 * @param input
	 *            The input access.
	 * @return The accessed Type from the access.
	 */
	public static Type getAccessedType(Commentable input) {
		if (input instanceof IdentifierReference) {
			return getAccessedType((IdentifierReference) input);
		} else if (input instanceof TypeReference) {
			return getAccessedType((TypeReference) input);
		} else if (input instanceof MethodCall) {
			return getAccessedType((MethodCall) input);
		} else {
			return null;
		}
	}
	
	public static Type getAccessedType(IdentifierReference reference) {
		if (reference != null) {
			return reference.getType();
		} else {
			return null;
		}
	}

	public static Type getAccessedType(TypeReference reference) {
		if (reference != null) {
			return reference.getTarget();
		} else {
			return null;
		}
	}

	//PDF24.11.14: changed if-statements to avoid classcastexception in some cases
	public static Type getAccessedType(MethodCall methodCall) {
		if (methodCall != null) {
			if(methodCall instanceof ClassMethod)
			{
				ClassMethod method = (ClassMethod) methodCall.getTarget();
				return getAccessedType(method.getTypeReference());
			}
			return null; //TODO: shouldn't there be even more cases? is null correct for all !methodCall instances?
		} else {
			return null;
		}
	}

}
