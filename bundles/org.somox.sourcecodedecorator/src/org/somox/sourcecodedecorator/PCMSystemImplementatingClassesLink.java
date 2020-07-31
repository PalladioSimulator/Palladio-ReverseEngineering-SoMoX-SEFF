/**
 */
package org.somox.sourcecodedecorator;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>PCM System Implementating Classes Link</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink#getSystemModel <em>System Model</em>}</li>
 * </ul>
 *
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getPCMSystemImplementatingClassesLink()
 * @model
 * @generated
 */
public interface PCMSystemImplementatingClassesLink extends ComponentImplementingClassesLink {
    /**
	 * Returns the value of the '<em><b>System Model</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>System Model</em>' reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>System Model</em>' reference.
	 * @see #setSystemModel(org.palladiosimulator.pcm.system.System)
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getPCMSystemImplementatingClassesLink_SystemModel()
	 * @model ordered="false"
	 * @generated
	 */
    org.palladiosimulator.pcm.system.System getSystemModel();

    /**
	 * Sets the value of the '{@link org.somox.sourcecodedecorator.PCMSystemImplementatingClassesLink#getSystemModel <em>System Model</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Model</em>' reference.
	 * @see #getSystemModel()
	 * @generated
	 */
    void setSystemModel(org.palladiosimulator.pcm.system.System value);

} // PCMSystemImplementatingClassesLink
