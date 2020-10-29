/**
 */
package metricvalues.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import metricvalues.Component;
import metricvalues.ComponentCandidate;
import metricvalues.Iteration;
import metricvalues.MetricvaluesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Iteration</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link metricvalues.impl.IterationImpl#getComponentCandidates <em>Component Candidates</em>}
 * </li>
 * <li>{@link metricvalues.impl.IterationImpl#getComponents <em>Components</em>}</li>
 * <li>{@link metricvalues.impl.IterationImpl#getNumber <em>Number</em>}</li>
 * <li>{@link metricvalues.impl.IterationImpl#getCurCompThreshold <em>Cur Comp Threshold</em>}</li>
 * <li>{@link metricvalues.impl.IterationImpl#getCurMergeThreshold <em>Cur Merge Threshold</em>}
 * </li>
 * <li>{@link metricvalues.impl.IterationImpl#isIsMergeIteration <em>Is Merge Iteration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IterationImpl extends MinimalEObjectImpl.Container implements Iteration {
    /**
     * The cached value of the '{@link #getComponentCandidates() <em>Component Candidates</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getComponentCandidates()
     * @generated
     * @ordered
     */
    protected EList<ComponentCandidate> componentCandidates;

    /**
     * The cached value of the '{@link #getComponents() <em>Components</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getComponents()
     * @generated
     * @ordered
     */
    protected EList<Component> components;

    /**
     * The default value of the '{@link #getNumber() <em>Number</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNumber()
     * @generated
     * @ordered
     */
    protected static final int NUMBER_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getNumber()
     * @generated
     * @ordered
     */
    protected int number = NUMBER_EDEFAULT;

    /**
     * The default value of the '{@link #getCurCompThreshold() <em>Cur Comp Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCurCompThreshold()
     * @generated
     * @ordered
     */
    protected static final double CUR_COMP_THRESHOLD_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getCurCompThreshold() <em>Cur Comp Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCurCompThreshold()
     * @generated
     * @ordered
     */
    protected double curCompThreshold = CUR_COMP_THRESHOLD_EDEFAULT;

    /**
     * The default value of the '{@link #getCurMergeThreshold() <em>Cur Merge Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCurMergeThreshold()
     * @generated
     * @ordered
     */
    protected static final double CUR_MERGE_THRESHOLD_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getCurMergeThreshold() <em>Cur Merge Threshold</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCurMergeThreshold()
     * @generated
     * @ordered
     */
    protected double curMergeThreshold = CUR_MERGE_THRESHOLD_EDEFAULT;

    /**
     * The default value of the '{@link #isIsMergeIteration() <em>Is Merge Iteration</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isIsMergeIteration()
     * @generated
     * @ordered
     */
    protected static final boolean IS_MERGE_ITERATION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsMergeIteration() <em>Is Merge Iteration</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isIsMergeIteration()
     * @generated
     * @ordered
     */
    protected boolean isMergeIteration = IS_MERGE_ITERATION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected IterationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MetricvaluesPackage.Literals.ITERATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ComponentCandidate> getComponentCandidates() {
        if (this.componentCandidates == null) {
            this.componentCandidates = new EObjectContainmentEList<ComponentCandidate>(ComponentCandidate.class, this,
                    MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES);
        }
        return this.componentCandidates;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Component> getComponents() {
        if (this.components == null) {
            this.components = new EObjectContainmentEList<Component>(Component.class, this,
                    MetricvaluesPackage.ITERATION__COMPONENTS);
        }
        return this.components;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getNumber() {
        return this.number;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setNumber(final int newNumber) {
        final int oldNumber = this.number;
        this.number = newNumber;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MetricvaluesPackage.ITERATION__NUMBER, oldNumber,
                    this.number));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getCurCompThreshold() {
        return this.curCompThreshold;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCurCompThreshold(final double newCurCompThreshold) {
        final double oldCurCompThreshold = this.curCompThreshold;
        this.curCompThreshold = newCurCompThreshold;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.ITERATION__CUR_COMP_THRESHOLD, oldCurCompThreshold, this.curCompThreshold));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getCurMergeThreshold() {
        return this.curMergeThreshold;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCurMergeThreshold(final double newCurMergeThreshold) {
        final double oldCurMergeThreshold = this.curMergeThreshold;
        this.curMergeThreshold = newCurMergeThreshold;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.ITERATION__CUR_MERGE_THRESHOLD, oldCurMergeThreshold, this.curMergeThreshold));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isIsMergeIteration() {
        return this.isMergeIteration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIsMergeIteration(final boolean newIsMergeIteration) {
        final boolean oldIsMergeIteration = this.isMergeIteration;
        this.isMergeIteration = newIsMergeIteration;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MetricvaluesPackage.ITERATION__IS_MERGE_ITERATION, oldIsMergeIteration, this.isMergeIteration));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES:
            return ((InternalEList<?>) this.getComponentCandidates()).basicRemove(otherEnd, msgs);
        case MetricvaluesPackage.ITERATION__COMPONENTS:
            return ((InternalEList<?>) this.getComponents()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES:
            return this.getComponentCandidates();
        case MetricvaluesPackage.ITERATION__COMPONENTS:
            return this.getComponents();
        case MetricvaluesPackage.ITERATION__NUMBER:
            return this.getNumber();
        case MetricvaluesPackage.ITERATION__CUR_COMP_THRESHOLD:
            return this.getCurCompThreshold();
        case MetricvaluesPackage.ITERATION__CUR_MERGE_THRESHOLD:
            return this.getCurMergeThreshold();
        case MetricvaluesPackage.ITERATION__IS_MERGE_ITERATION:
            return this.isIsMergeIteration();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES:
            this.getComponentCandidates().clear();
            this.getComponentCandidates().addAll((Collection<? extends ComponentCandidate>) newValue);
            return;
        case MetricvaluesPackage.ITERATION__COMPONENTS:
            this.getComponents().clear();
            this.getComponents().addAll((Collection<? extends Component>) newValue);
            return;
        case MetricvaluesPackage.ITERATION__NUMBER:
            this.setNumber((Integer) newValue);
            return;
        case MetricvaluesPackage.ITERATION__CUR_COMP_THRESHOLD:
            this.setCurCompThreshold((Double) newValue);
            return;
        case MetricvaluesPackage.ITERATION__CUR_MERGE_THRESHOLD:
            this.setCurMergeThreshold((Double) newValue);
            return;
        case MetricvaluesPackage.ITERATION__IS_MERGE_ITERATION:
            this.setIsMergeIteration((Boolean) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES:
            this.getComponentCandidates().clear();
            return;
        case MetricvaluesPackage.ITERATION__COMPONENTS:
            this.getComponents().clear();
            return;
        case MetricvaluesPackage.ITERATION__NUMBER:
            this.setNumber(NUMBER_EDEFAULT);
            return;
        case MetricvaluesPackage.ITERATION__CUR_COMP_THRESHOLD:
            this.setCurCompThreshold(CUR_COMP_THRESHOLD_EDEFAULT);
            return;
        case MetricvaluesPackage.ITERATION__CUR_MERGE_THRESHOLD:
            this.setCurMergeThreshold(CUR_MERGE_THRESHOLD_EDEFAULT);
            return;
        case MetricvaluesPackage.ITERATION__IS_MERGE_ITERATION:
            this.setIsMergeIteration(IS_MERGE_ITERATION_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case MetricvaluesPackage.ITERATION__COMPONENT_CANDIDATES:
            return this.componentCandidates != null && !this.componentCandidates.isEmpty();
        case MetricvaluesPackage.ITERATION__COMPONENTS:
            return this.components != null && !this.components.isEmpty();
        case MetricvaluesPackage.ITERATION__NUMBER:
            return this.number != NUMBER_EDEFAULT;
        case MetricvaluesPackage.ITERATION__CUR_COMP_THRESHOLD:
            return this.curCompThreshold != CUR_COMP_THRESHOLD_EDEFAULT;
        case MetricvaluesPackage.ITERATION__CUR_MERGE_THRESHOLD:
            return this.curMergeThreshold != CUR_MERGE_THRESHOLD_EDEFAULT;
        case MetricvaluesPackage.ITERATION__IS_MERGE_ITERATION:
            return this.isMergeIteration != IS_MERGE_ITERATION_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }

        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (number: ");
        result.append(this.number);
        result.append(", curCompThreshold: ");
        result.append(this.curCompThreshold);
        result.append(", curMergeThreshold: ");
        result.append(this.curMergeThreshold);
        result.append(", isMergeIteration: ");
        result.append(this.isMergeIteration);
        result.append(')');
        return result.toString();
    }

} // IterationImpl
