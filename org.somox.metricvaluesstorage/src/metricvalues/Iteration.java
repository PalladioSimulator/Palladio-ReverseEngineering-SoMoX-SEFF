/**
 */
package metricvalues;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Iteration</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link metricvalues.Iteration#getComponentCandidates <em>Component Candidates</em>}</li>
 * <li>{@link metricvalues.Iteration#getComponents <em>Components</em>}</li>
 * <li>{@link metricvalues.Iteration#getNumber <em>Number</em>}</li>
 * <li>{@link metricvalues.Iteration#getCurCompThreshold <em>Cur Comp Threshold</em>}</li>
 * <li>{@link metricvalues.Iteration#getCurMergeThreshold <em>Cur Merge Threshold</em>}</li>
 * <li>{@link metricvalues.Iteration#isIsMergeIteration <em>Is Merge Iteration</em>}</li>
 * </ul>
 * </p>
 *
 * @see metricvalues.MetricvaluesPackage#getIteration()
 * @model
 * @generated
 */
public interface Iteration extends EObject {
    /**
     * Returns the value of the '<em><b>Component Candidates</b></em>' containment reference list.
     * The list contents are of type {@link metricvalues.ComponentCandidate}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Component Candidates</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Component Candidates</em>' containment reference list.
     * @see metricvalues.MetricvaluesPackage#getIteration_ComponentCandidates()
     * @model containment="true"
     * @generated
     */
    EList<ComponentCandidate> getComponentCandidates();

    /**
     * Returns the value of the '<em><b>Components</b></em>' containment reference list. The list
     * contents are of type {@link metricvalues.Component}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Components</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Components</em>' containment reference list.
     * @see metricvalues.MetricvaluesPackage#getIteration_Components()
     * @model containment="true"
     * @generated
     */
    EList<Component> getComponents();

    /**
     * Returns the value of the '<em><b>Number</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Number</em>' attribute isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Number</em>' attribute.
     * @see #setNumber(int)
     * @see metricvalues.MetricvaluesPackage#getIteration_Number()
     * @model
     * @generated
     */
    int getNumber();

    /**
     * Sets the value of the '{@link metricvalues.Iteration#getNumber <em>Number</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Number</em>' attribute.
     * @see #getNumber()
     * @generated
     */
    void setNumber(int value);

    /**
     * Returns the value of the '<em><b>Cur Comp Threshold</b></em>' attribute. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Cur Comp Threshold</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Cur Comp Threshold</em>' attribute.
     * @see #setCurCompThreshold(double)
     * @see metricvalues.MetricvaluesPackage#getIteration_CurCompThreshold()
     * @model
     * @generated
     */
    double getCurCompThreshold();

    /**
     * Sets the value of the '{@link metricvalues.Iteration#getCurCompThreshold
     * <em>Cur Comp Threshold</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Cur Comp Threshold</em>' attribute.
     * @see #getCurCompThreshold()
     * @generated
     */
    void setCurCompThreshold(double value);

    /**
     * Returns the value of the '<em><b>Cur Merge Threshold</b></em>' attribute. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Cur Merge Threshold</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Cur Merge Threshold</em>' attribute.
     * @see #setCurMergeThreshold(double)
     * @see metricvalues.MetricvaluesPackage#getIteration_CurMergeThreshold()
     * @model
     * @generated
     */
    double getCurMergeThreshold();

    /**
     * Sets the value of the '{@link metricvalues.Iteration#getCurMergeThreshold
     * <em>Cur Merge Threshold</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Cur Merge Threshold</em>' attribute.
     * @see #getCurMergeThreshold()
     * @generated
     */
    void setCurMergeThreshold(double value);

    /**
     * Returns the value of the '<em><b>Is Merge Iteration</b></em>' attribute. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Is Merge Iteration</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Is Merge Iteration</em>' attribute.
     * @see #setIsMergeIteration(boolean)
     * @see metricvalues.MetricvaluesPackage#getIteration_IsMergeIteration()
     * @model
     * @generated
     */
    boolean isIsMergeIteration();

    /**
     * Sets the value of the '{@link metricvalues.Iteration#isIsMergeIteration
     * <em>Is Merge Iteration</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Is Merge Iteration</em>' attribute.
     * @see #isIsMergeIteration()
     * @generated
     */
    void setIsMergeIteration(boolean value);

} // Iteration
