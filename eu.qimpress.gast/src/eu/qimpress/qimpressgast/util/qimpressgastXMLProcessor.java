/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.qimpressgast.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import eu.qimpress.qimpressgast.qimpressgastPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class qimpressgastXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public qimpressgastXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		qimpressgastPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the qimpressgastResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new qimpressgastResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new qimpressgastResourceFactoryImpl());
		}
		return registrations;
	}

} //qimpressgastXMLProcessor
