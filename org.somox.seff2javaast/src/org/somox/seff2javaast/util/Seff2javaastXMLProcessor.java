/**
 */
package org.somox.seff2javaast.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.somox.seff2javaast.Seff2javaastPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 *
 * @generated
 */
public class Seff2javaastXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Seff2javaastXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        Seff2javaastPackage.eINSTANCE.eClass();
    }

    /**
     * Register for "*" and "xml" file extensions the Seff2javaastResourceFactoryImpl factory. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (this.registrations == null) {
            super.getRegistrations();
            this.registrations.put(XML_EXTENSION, new Seff2javaastResourceFactoryImpl());
            this.registrations.put(STAR_EXTENSION, new Seff2javaastResourceFactoryImpl());
        }
        return this.registrations;
    }

} // Seff2javaastXMLProcessor
