/**
 */
package org.somox.seff2javaast.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource Factory</b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.somox.seff2javaast.util.Seff2javaastResourceImpl
 * @generated
 */
public class Seff2javaastResourceFactoryImpl extends ResourceFactoryImpl {
	/**
     * Creates an instance of the resource factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Seff2javaastResourceFactoryImpl() {
        super();
    }

	/**
     * Creates an instance of the resource.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Resource createResource(URI uri) {
        Resource result = new Seff2javaastResourceImpl(uri);
        return result;
    }

} //Seff2javaastResourceFactoryImpl
