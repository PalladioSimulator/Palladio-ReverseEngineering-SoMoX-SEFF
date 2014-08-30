/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.MetricAndWeight;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metric And Weight</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.metrics.dsl.metricDSL.impl.MetricAndWeightImpl#getMetric <em>Metric</em>}</li>
 *   <li>{@link org.somox.metrics.dsl.metricDSL.impl.MetricAndWeightImpl#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetricAndWeightImpl extends MinimalEObjectImpl.Container implements MetricAndWeight
{
  /**
   * The cached value of the '{@link #getMetric() <em>Metric</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMetric()
   * @generated
   * @ordered
   */
  protected Metric metric;

  /**
   * The cached value of the '{@link #getWeight() <em>Weight</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWeight()
   * @generated
   * @ordered
   */
  protected org.somox.metrics.dsl.metricDSL.Number weight;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MetricAndWeightImpl()
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
    return MetricDSLPackage.Literals.METRIC_AND_WEIGHT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Metric getMetric()
  {
    if (metric != null && metric.eIsProxy())
    {
      InternalEObject oldMetric = (InternalEObject)metric;
      metric = (Metric)eResolveProxy(oldMetric);
      if (metric != oldMetric)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MetricDSLPackage.METRIC_AND_WEIGHT__METRIC, oldMetric, metric));
      }
    }
    return metric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Metric basicGetMetric()
  {
    return metric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMetric(Metric newMetric)
  {
    Metric oldMetric = metric;
    metric = newMetric;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.METRIC_AND_WEIGHT__METRIC, oldMetric, metric));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.somox.metrics.dsl.metricDSL.Number getWeight()
  {
    if (weight != null && weight.eIsProxy())
    {
      InternalEObject oldWeight = (InternalEObject)weight;
      weight = (org.somox.metrics.dsl.metricDSL.Number)eResolveProxy(oldWeight);
      if (weight != oldWeight)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT, oldWeight, weight));
      }
    }
    return weight;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.somox.metrics.dsl.metricDSL.Number basicGetWeight()
  {
    return weight;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWeight(org.somox.metrics.dsl.metricDSL.Number newWeight)
  {
    org.somox.metrics.dsl.metricDSL.Number oldWeight = weight;
    weight = newWeight;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT, oldWeight, weight));
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
      case MetricDSLPackage.METRIC_AND_WEIGHT__METRIC:
        if (resolve) return getMetric();
        return basicGetMetric();
      case MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT:
        if (resolve) return getWeight();
        return basicGetWeight();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MetricDSLPackage.METRIC_AND_WEIGHT__METRIC:
        setMetric((Metric)newValue);
        return;
      case MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT:
        setWeight((org.somox.metrics.dsl.metricDSL.Number)newValue);
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
      case MetricDSLPackage.METRIC_AND_WEIGHT__METRIC:
        setMetric((Metric)null);
        return;
      case MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT:
        setWeight((org.somox.metrics.dsl.metricDSL.Number)null);
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
      case MetricDSLPackage.METRIC_AND_WEIGHT__METRIC:
        return metric != null;
      case MetricDSLPackage.METRIC_AND_WEIGHT__WEIGHT:
        return weight != null;
    }
    return super.eIsSet(featureID);
  }

} //MetricAndWeightImpl
