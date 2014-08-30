/**
 */
package org.somox.metrics.dsl.metricDSL.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.somox.metrics.dsl.metricDSL.Metric;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.RatioMetric;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ratio Metric</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.somox.metrics.dsl.metricDSL.impl.RatioMetricImpl#getNominatorMetric <em>Nominator Metric</em>}</li>
 *   <li>{@link org.somox.metrics.dsl.metricDSL.impl.RatioMetricImpl#getDenominatorMetric <em>Denominator Metric</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RatioMetricImpl extends MetricDefinitionImpl implements RatioMetric
{
  /**
   * The cached value of the '{@link #getNominatorMetric() <em>Nominator Metric</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNominatorMetric()
   * @generated
   * @ordered
   */
  protected Metric nominatorMetric;

  /**
   * The cached value of the '{@link #getDenominatorMetric() <em>Denominator Metric</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDenominatorMetric()
   * @generated
   * @ordered
   */
  protected Metric denominatorMetric;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RatioMetricImpl()
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
    return MetricDSLPackage.Literals.RATIO_METRIC;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Metric getNominatorMetric()
  {
    if (nominatorMetric != null && nominatorMetric.eIsProxy())
    {
      InternalEObject oldNominatorMetric = (InternalEObject)nominatorMetric;
      nominatorMetric = (Metric)eResolveProxy(oldNominatorMetric);
      if (nominatorMetric != oldNominatorMetric)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC, oldNominatorMetric, nominatorMetric));
      }
    }
    return nominatorMetric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Metric basicGetNominatorMetric()
  {
    return nominatorMetric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNominatorMetric(Metric newNominatorMetric)
  {
    Metric oldNominatorMetric = nominatorMetric;
    nominatorMetric = newNominatorMetric;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC, oldNominatorMetric, nominatorMetric));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Metric getDenominatorMetric()
  {
    if (denominatorMetric != null && denominatorMetric.eIsProxy())
    {
      InternalEObject oldDenominatorMetric = (InternalEObject)denominatorMetric;
      denominatorMetric = (Metric)eResolveProxy(oldDenominatorMetric);
      if (denominatorMetric != oldDenominatorMetric)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC, oldDenominatorMetric, denominatorMetric));
      }
    }
    return denominatorMetric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Metric basicGetDenominatorMetric()
  {
    return denominatorMetric;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDenominatorMetric(Metric newDenominatorMetric)
  {
    Metric oldDenominatorMetric = denominatorMetric;
    denominatorMetric = newDenominatorMetric;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC, oldDenominatorMetric, denominatorMetric));
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
      case MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC:
        if (resolve) return getNominatorMetric();
        return basicGetNominatorMetric();
      case MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC:
        if (resolve) return getDenominatorMetric();
        return basicGetDenominatorMetric();
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
      case MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC:
        setNominatorMetric((Metric)newValue);
        return;
      case MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC:
        setDenominatorMetric((Metric)newValue);
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
      case MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC:
        setNominatorMetric((Metric)null);
        return;
      case MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC:
        setDenominatorMetric((Metric)null);
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
      case MetricDSLPackage.RATIO_METRIC__NOMINATOR_METRIC:
        return nominatorMetric != null;
      case MetricDSLPackage.RATIO_METRIC__DENOMINATOR_METRIC:
        return denominatorMetric != null;
    }
    return super.eIsSet(featureID);
  }

} //RatioMetricImpl
