/**
 */
package org.somox.sourcecodedecorator.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.pcm.seff.ResourceDemandingInternalBehaviour;
import org.somox.sourcecodedecorator.MethodLevelResourceDemandingInternalBehaviorLink;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Method Level Resource Demanding Internal Behavior Link</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.somox.sourcecodedecorator.impl.MethodLevelResourceDemandingInternalBehaviorLinkImpl#getResourceDemandingInternalBehaviour <em>Resource Demanding Internal Behaviour</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MethodLevelResourceDemandingInternalBehaviorLinkImpl extends AbstractMethodLevelSourceCodeLinkImpl
        implements MethodLevelResourceDemandingInternalBehaviorLink {
    /**
	 * The cached value of the '{@link #getResourceDemandingInternalBehaviour() <em>Resource Demanding Internal Behaviour</em>}' reference.
	 * <!-- begin-user-doc --> <!--
     * end-user-doc -->
	 * @see #getResourceDemandingInternalBehaviour()
	 * @generated
	 * @ordered
	 */
    protected ResourceDemandingInternalBehaviour resourceDemandingInternalBehaviour;

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    protected MethodLevelResourceDemandingInternalBehaviorLinkImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return SourcecodedecoratorPackage.Literals.METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public ResourceDemandingInternalBehaviour getResourceDemandingInternalBehaviour() {
		if (resourceDemandingInternalBehaviour != null && ((EObject)resourceDemandingInternalBehaviour).eIsProxy()) {
			InternalEObject oldResourceDemandingInternalBehaviour = (InternalEObject)resourceDemandingInternalBehaviour;
			resourceDemandingInternalBehaviour = (ResourceDemandingInternalBehaviour)eResolveProxy(oldResourceDemandingInternalBehaviour);
			if (resourceDemandingInternalBehaviour != oldResourceDemandingInternalBehaviour) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourcecodedecoratorPackage.METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK__RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR, oldResourceDemandingInternalBehaviour, resourceDemandingInternalBehaviour));
			}
		}
		return resourceDemandingInternalBehaviour;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public ResourceDemandingInternalBehaviour basicGetResourceDemandingInternalBehaviour() {
		return resourceDemandingInternalBehaviour;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setResourceDemandingInternalBehaviour(
            ResourceDemandingInternalBehaviour newResourceDemandingInternalBehaviour) {
		ResourceDemandingInternalBehaviour oldResourceDemandingInternalBehaviour = resourceDemandingInternalBehaviour;
		resourceDemandingInternalBehaviour = newResourceDemandingInternalBehaviour;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourcecodedecoratorPackage.METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK__RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR, oldResourceDemandingInternalBehaviour, resourceDemandingInternalBehaviour));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SourcecodedecoratorPackage.METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK__RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR:
				if (resolve) return getResourceDemandingInternalBehaviour();
				return basicGetResourceDemandingInternalBehaviour();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SourcecodedecoratorPackage.METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK__RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR:
				setResourceDemandingInternalBehaviour((ResourceDemandingInternalBehaviour)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eUnset(int featureID) {
		switch (featureID) {
			case SourcecodedecoratorPackage.METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK__RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR:
				setResourceDemandingInternalBehaviour((ResourceDemandingInternalBehaviour)null);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SourcecodedecoratorPackage.METHOD_LEVEL_RESOURCE_DEMANDING_INTERNAL_BEHAVIOR_LINK__RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR:
				return resourceDemandingInternalBehaviour != null;
		}
		return super.eIsSet(featureID);
	}

} // MethodLevelResourceDemandingInternalBehaviorLinkImpl
