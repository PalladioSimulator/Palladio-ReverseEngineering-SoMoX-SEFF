/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.qimpressgast;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GAST Behaviour Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.qimpress.qimpressgast.GASTBehaviourRepository#getGastbehaviour <em>Gastbehaviour</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.qimpress.qimpressgast.qimpressgastPackage#getGASTBehaviourRepository()
 * @model
 * @generated
 */
public interface GASTBehaviourRepository extends EObject {
	/**
	 * Returns the value of the '<em><b>Gastbehaviour</b></em>' containment reference list.
	 * The list contents are of type {@link eu.qimpress.qimpressgast.GASTBehaviour}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gastbehaviour</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gastbehaviour</em>' containment reference list.
	 * @see eu.qimpress.qimpressgast.qimpressgastPackage#getGASTBehaviourRepository_Gastbehaviour()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<GASTBehaviour> getGastbehaviour();

} // GASTBehaviourRepository
