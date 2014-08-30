/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.somox.metrics.dsl.metricDSL.BoundAndWeight;
import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.StepwiseMetric;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stepwise Metric</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.metrics.dsl.metricDSL.impl.StepwiseMetricImpl#getInnerMetric <em>Inner Metric</em>}</li>
 *   <li>{@link org.somox.metrics.dsl.metricDSL.impl.StepwiseMetricImpl#getSteps <em>Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StepwiseMetricImpl extends MetricDefinitionImpl implements StepwiseMetric
{
  /**
   * The cached value of the '{@link #getInnerMetric() <em>Inner Metric</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInnerMetric()
   * @generated
   * @ordered
   */
  protected Metric innerMetric;

  /**
   * The cached value of the '{@link #getSteps() <em>Steps</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSteps()
   * @generated
   * @ordered
   */
  protected EList<BoundAndWeight> steps;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StepwiseMetricImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return MetricDSLPackage.Literals.STEPWISE_METRIC;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Metric getInnerMetric()
  {
    if (innerMetric != null && innerMetric.eIsProxy())
    {
      InternalEObject oldInnerMetric = (InternalEObject)innerMetric;
      innerMetric = (Metric)eResolveProxy(oldInnerMetric);
      if (innerMetric != oldInnerMetric)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC, oldInnerMetric, innerMetric));
      }
    }
    return innerMetric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Metric basicGetInnerMetric()
  {
    return innerMetric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInnerMetric(Metric newInnerMetric)
  {
    Metric oldInnerMetric = innerMetric;
    innerMetric = newInnerMetric;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC, oldInnerMetric, innerMetric));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<BoundAndWeight> getSteps()
  {
    if (steps == null)
    {
      steps = new EObjectContainmentEList<BoundAndWeight>(BoundAndWeight.class, this, MetricDSLPackage.STEPWISE_METRIC__STEPS);
    }
    return steps;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case MetricDSLPackage.STEPWISE_METRIC__STEPS:
        return ((InternalEList<?>)getSteps()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC:
        if (resolve) return getInnerMetric();
        return basicGetInnerMetric();
      case MetricDSLPackage.STEPWISE_METRIC__STEPS:
        return getSteps();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC:
        setInnerMetric((Metric)newValue);
        return;
      case MetricDSLPackage.STEPWISE_METRIC__STEPS:
        getSteps().clear();
        getSteps().addAll((Collection<? extends BoundAndWeight>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC:
        setInnerMetric((Metric)null);
        return;
      case MetricDSLPackage.STEPWISE_METRIC__STEPS:
        getSteps().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case MetricDSLPackage.STEPWISE_METRIC__INNER_METRIC:
        return innerMetric != null;
      case MetricDSLPackage.STEPWISE_METRIC__STEPS:
        return steps != null && !steps.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //StepwiseMetricImpl
