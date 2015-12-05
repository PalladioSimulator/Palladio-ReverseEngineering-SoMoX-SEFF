/**
 */
package metricvalues;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.types.Type;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Component</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link metricvalues.Component#getSubComponents <em>Sub Components</em>}</li>
 * <li>{@link metricvalues.Component#getName <em>Name</em>}</li>
 * <li>{@link metricvalues.Component#getId <em>Id</em>}</li>
 * <li>{@link metricvalues.Component#getClasses <em>Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see metricvalues.MetricvaluesPackage#getComponent()
 * @model
 * @generated
 */
public interface Component extends EObject {
    /**
     * Returns the value of the '<em><b>Sub Components</b></em>' containment reference list. The
     * list contents are of type {@link metricvalues.Component}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Components</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sub Components</em>' containment reference list.
     * @see metricvalues.MetricvaluesPackage#getComponent_SubComponents()
     * @model containment="true"
     * @generated
     */
    EList<Component> getSubComponents();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see metricvalues.MetricvaluesPackage#getComponent_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link metricvalues.Component#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see metricvalues.MetricvaluesPackage#getComponent_Id()
     * @model
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link metricvalues.Component#getId <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Classes</b></em>' reference list. The list contents are of
     * type {@link org.emftext.language.java.types.Type}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Classes</em>' reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Classes</em>' reference list.
     * @see metricvalues.MetricvaluesPackage#getComponent_Classes()
     * @model
     * @generated
     */
    EList<Type> getClasses();

} // Component
