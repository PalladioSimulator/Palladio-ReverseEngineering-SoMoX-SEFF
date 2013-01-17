/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.qimpressgast;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.Block;

import eu.qimpress.samm.behaviour.GastBehaviourStub;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GAST Behaviour</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.qimpress.qimpressgast.GASTBehaviour#getBlockstatement <em>Blockstatement</em>}</li>
 *   <li>{@link eu.qimpress.qimpressgast.GASTBehaviour#getGastbehaviourstub <em>Gastbehaviourstub</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.qimpress.qimpressgast.qimpressgastPackage#getGASTBehaviour()
 * @model
 * @generated
 */
public interface GASTBehaviour extends EObject {
	/**
	 * Returns the value of the '<em><b>Blockstatement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Blockstatement</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Blockstatement</em>' reference.
	 * @see #setBlockstatement(Block)
	 * @see eu.qimpress.qimpressgast.qimpressgastPackage#getGASTBehaviour_Blockstatement()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Block getBlockstatement();

	/**
	 * Sets the value of the '{@link eu.qimpress.qimpressgast.GASTBehaviour#getBlockstatement <em>Blockstatement</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Blockstatement</em>' reference.
	 * @see #getBlockstatement()
	 * @generated
	 */
	void setBlockstatement(Block value);

	/**
	 * Returns the value of the '<em><b>Gastbehaviourstub</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gastbehaviourstub</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gastbehaviourstub</em>' reference.
	 * @see #setGastbehaviourstub(GastBehaviourStub)
	 * @see eu.qimpress.qimpressgast.qimpressgastPackage#getGASTBehaviour_Gastbehaviourstub()
	 * @model ordered="false"
	 * @generated
	 */
	GastBehaviourStub getGastbehaviourstub();

	/**
	 * Sets the value of the '{@link eu.qimpress.qimpressgast.GASTBehaviour#getGastbehaviourstub <em>Gastbehaviourstub</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gastbehaviourstub</em>' reference.
	 * @see #getGastbehaviourstub()
	 * @generated
	 */
	void setGastbehaviourstub(GastBehaviourStub value);

} // GASTBehaviour
