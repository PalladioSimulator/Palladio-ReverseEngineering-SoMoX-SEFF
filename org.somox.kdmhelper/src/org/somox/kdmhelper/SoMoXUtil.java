package org.somox.kdmhelper;

import org.eclipse.core.resources.ResourcesPlugin;

public class SoMoXUtil {

    private SoMoXUtil() {

    }

    /**
     * Determines whether SoMoX is executed within a new Eclipse or whether it runs standalone
     *
     * @return
     */
    public static boolean isStandalone() {
        try {
            ResourcesPlugin.getWorkspace();
        } catch (final IllegalStateException e) {
            // this means we run standalone
            return true;
        }
        return false;
    }
}
