/**
 */
package org.somox.sourcecodedecorator.provider;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.emftext.commons.layout.provider.LayoutEditPlugin;
import org.emftext.language.java.annotations.provider.JavaEditPlugin;
import org.palladiosimulator.pcm.core.provider.PalladioComponentModelEditPlugin;

import de.uka.ipd.sdq.identifier.provider.IdentifierEditPlugin;
import de.uka.ipd.sdq.probfunction.provider.ProbabilityFunctionEditPlugin;
import de.uka.ipd.sdq.stoex.provider.StoexEditPlugin;
import de.uka.ipd.sdq.units.provider.UnitsEditPlugin;

/**
 * This is the central singleton for the F edit plugin. <!-- begin-user-doc --> <!-- end-user-doc
 * -->
 *
 * @generated
 */
public final class FEditPlugin extends EMFPlugin {
    /**
     * Keep track of the singleton.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static final FEditPlugin INSTANCE = new FEditPlugin();

    /**
     * Keep track of the singleton.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static Implementation plugin;

    /**
     * Create the instance.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FEditPlugin() {
        super
          (new ResourceLocator [] {
             IdentifierEditPlugin.INSTANCE,
             JavaEditPlugin.INSTANCE,
             LayoutEditPlugin.INSTANCE,
             PalladioComponentModelEditPlugin.INSTANCE,
             ProbabilityFunctionEditPlugin.INSTANCE,
             StoexEditPlugin.INSTANCE,
             UnitsEditPlugin.INSTANCE,
           });
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
    public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    public static class Implementation extends EclipsePlugin {
        /**
         * Creates an instance.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        public Implementation() {
            super();

            // Remember the static instance.
            //
            plugin = this;
        }
    }

}
