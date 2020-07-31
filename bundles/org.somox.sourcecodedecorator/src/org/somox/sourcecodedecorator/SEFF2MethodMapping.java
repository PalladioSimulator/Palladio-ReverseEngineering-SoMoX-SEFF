/**
 */
package org.somox.sourcecodedecorator;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.StatementListContainer;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SEFF2 Method Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.SEFF2MethodMapping#getSeff <em>Seff</em>}</li>
 *   <li>{@link org.somox.sourcecodedecorator.SEFF2MethodMapping#getStatementListContainer <em>Statement List Container</em>}</li>
 * </ul>
 *
 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSEFF2MethodMapping()
 * @model
 * @generated
 */
public interface SEFF2MethodMapping extends EObject {
    /**
	 * Returns the value of the '<em><b>Seff</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Seff</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Seff</em>' reference.
	 * @see #setSeff(ServiceEffectSpecification)
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSEFF2MethodMapping_Seff()
	 * @model required="true"
	 * @generated
	 */
    ServiceEffectSpecification getSeff();

    /**
	 * Sets the value of the '{@link org.somox.sourcecodedecorator.SEFF2MethodMapping#getSeff <em>Seff</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seff</em>' reference.
	 * @see #getSeff()
	 * @generated
	 */
    void setSeff(ServiceEffectSpecification value);

    /**
	 * Returns the value of the '<em><b>Statement List Container</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Statement List Container</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Statement List Container</em>' reference.
	 * @see #setStatementListContainer(StatementListContainer)
	 * @see org.somox.sourcecodedecorator.SourcecodedecoratorPackage#getSEFF2MethodMapping_StatementListContainer()
	 * @model required="true"
	 * @generated
	 */
    StatementListContainer getStatementListContainer();

    /**
	 * Sets the value of the '{@link org.somox.sourcecodedecorator.SEFF2MethodMapping#getStatementListContainer <em>Statement List Container</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Statement List Container</em>' reference.
	 * @see #getStatementListContainer()
	 * @generated
	 */
    void setStatementListContainer(StatementListContainer value);

} // SEFF2MethodMapping
