package org.splevo.jamopp.extraction;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Utility class for handling of file-based resources.
 */
public class FileResourceHandling {

    /**
     * Options for the resource handling. These options can be used as part of the load options of a
     * resource set in {@link SPLevoResourceSet}.
     */
    public enum ResourceHandlingOptions {

        USE_PLATFORM_RESOURCE(ResourcesPlugin.getPlugin() != null);

        private Object defaultValue;

        private ResourceHandlingOptions(final Object defaultValue) {
            this.defaultValue = defaultValue;
        }

        /**
         * @return The default value of the option.
         */
        public Object getDefault() {
            return defaultValue;
        }

        /**
         * Sets the new default value for the option.
         * 
         * @param defaultValue
         *            The new default value.
         */
        public void setDefault(Object defaultValue) {
            this.defaultValue = defaultValue;
        }

    }

    /**
     * Determines if platform resources shall be used in the given {@link ResourceSet}.
     * 
     * @param rs
     *            The resource set to be checked.
     * @return True if platform resources shall be used.
     */
    public static boolean usePlatformResource(ResourceSet rs) {
        return (Boolean) (rs.getLoadOptions().containsKey(ResourceHandlingOptions.USE_PLATFORM_RESOURCE) ? rs
                .getLoadOptions().get(ResourceHandlingOptions.USE_PLATFORM_RESOURCE)
                : ResourceHandlingOptions.USE_PLATFORM_RESOURCE.getDefault());
    }

    /**
     * Determines the physical path of the given resource.
     * 
     * @param r
     *            The resource to check.
     * @return The file object representing the physical path of the resource.
     */
    public static File getPhysicalFilePath(Resource r) {
        URI resourceURI = r.getURI();
        return getPhysicalFilePath(resourceURI, r.getResourceSet());
    }

    /**
     * Determines the physical path of the given {@link URI} with respect to the given
     * {@link ResourceSet}.
     * 
     * @param resourceURI
     *            The resource URI to check.
     * @param rs
     *            The resource set to be used.
     * @return The file object representing the physical path of the resource.
     */
    public static File getPhysicalFilePath(URI resourceURI, ResourceSet rs) {
        if (resourceURI.isPlatformResource()) {
            IPath p = new Path(resourceURI.toPlatformString(true));
            IFile f = ResourcesPlugin.getWorkspace().getRoot().getFile(p);
            return f.getLocation().toFile();
        }
        if (resourceURI.isFile()) {
            return new File(resourceURI.toFileString());
        }
        return null;
    }
}
