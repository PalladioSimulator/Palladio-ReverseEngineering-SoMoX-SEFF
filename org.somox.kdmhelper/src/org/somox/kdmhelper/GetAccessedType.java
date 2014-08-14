package org.somox.kdmhelper;

import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.Method;
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
		if (input instanceof TypeReference) {
			return getAccessedType((TypeReference) input);
		} else if (input instanceof Method) {
			return getAccessedType((Method) input);
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

	public static Type getAccessedType(Method method) {
		if (method != null) {
			TypeReference reference = method.getTypeReference();
			return getAccessedType(reference);
		} else {
			return null;
		}
	}

}
