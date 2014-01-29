/**
 */
package org.somox.sourcecodedecorator.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SourcecodedecoratorXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourcecodedecoratorXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		SourcecodedecoratorPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the SourcecodedecoratorResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new SourcecodedecoratorResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new SourcecodedecoratorResourceFactoryImpl());
		}
		return registrations;
	}

} //SourcecodedecoratorXMLProcessor
