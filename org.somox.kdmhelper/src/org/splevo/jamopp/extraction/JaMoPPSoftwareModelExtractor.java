package org.splevo.jamopp.extraction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.splevo.jamopp.extraction.FileResourceHandling.ResourceHandlingOptions;
import org.splevo.jamopp.extraction.cache.ReferenceCache;
import org.splevo.jamopp.extraction.resource.JavaSourceOrClassFileResourceCachingFactoryImpl;

import com.google.common.collect.Lists;

/**
 * Software Model Extractor for the Java technology based on the Java Model Parser and Printer
 * (JaMoPP). JaMoPPSoftwareModelExtractor are copied from splevo and adapted in order to work with
 * SoMoX. And adapted to work without further dependencies to the splevo code.
 */
public class JaMoPPSoftwareModelExtractor {

    private static final Logger LOGGER = Logger.getLogger(JaMoPPSoftwareModelExtractor.class);

    public static final boolean EXTRACTOR_EXTRACT_LAYOUT_BY_DEFAULT = false;
    public static final String EXTRACTOR_ID = "JaMoPPSoftwareModelExtractor";
    private static final String EXTRACTOR_LABEL = "JaMoPP Software Model Extractor";

    private List<Resource> sourceResources;

    /**
     * Extract the source model of a list of java projects. One project is the main project while a
     * list of additional projects to analyze can be specified. The reason for one main project is,
     * that this one is used for example for the naming of the root inventory produced etc.
     *
     * @param projectPaths
     *            The projects to be extracted.
     * @param monitor
     *            The monitor to report the progress to.
     * @param sourceModelPath
     *            The absolute path to the directory to store information for extracted source model
     *            in. If {@code null}, no reference cache will be used for result resolving, which
     *            has negative impacts on performance.
     * @param extractLayoutInfo
     *            Option to extract layout information.
     * @return The set of resources containing the extracted model. @ Identifies the extraction was
     *         not successful.
     */
    public ResourceSet extractSoftwareModelFromProjects(final List<IJavaProject> projects,
            final IProgressMonitor monitor, final String sourceModelPath, final boolean extractLayoutInfo) {
        Collection<File> javaFiles = new HashSet<>();
        try {
            for (final IJavaProject sourceProject : projects) {
                for (final IPackageFragmentRoot packageFragmentRoot : sourceProject.getAllPackageFragmentRoots()) {
                    for (final IJavaElement packageFragment : packageFragmentRoot.getChildren()) {
                        for (final IJavaElement sourceFile : ((IPackageFragment) packageFragment).getChildren()) {
                            if (sourceFile.getElementType() == IJavaElement.COMPILATION_UNIT) {
                                javaFiles.add(sourceFile.getResource().getRawLocation().toFile());
                            }
                        }
                    }
                }
            }
        } catch (final JavaModelException javaModelError) {
            LOGGER.warn("Error while accessing an analysed project:\n%s", javaModelError);
        }

        return this.loadJavaFilesIntoResourceSet(javaFiles, monitor, sourceModelPath, extractLayoutInfo);
    }

    /**
     * Extract the source model of a list of source folders. One source folder is the main project
     * while a list of additional projects to analyze can be specified. The reason for one main
     * folder is, that this one is used for example for the naming of the root inventory produced
     * etc. All {@code .java} files recursively contained in the source folders will be used.
     *
     * @param sourceFolders
     *            The source folders to be extracted.
     * @param monitor
     *            The monitor to report the progress to.
     * @param sourceModelPath
     *            The absolute path to the directory to store information for extracted source model
     *            in. If {@code null}, no reference cache will be used for result resolving, which
     *            has negative impacts on performance.
     * @param extractLayoutInfo
     *            Option to extract layout information.
     * @return The set of resources containing the extracted model. @ Identifies the extraction was
     *         not successful.
     */
    public ResourceSet extractSoftwareModelFromFolders(final Iterable<File> sourceFolders, final IProgressMonitor monitor,
            final String sourceModelPath, final boolean extractLayoutInfo) {
        List<File> javaFiles = new ArrayList<>();
        for (File sourceFolder : sourceFolders) {
            javaFiles.addAll(FileUtils.listFiles(sourceFolder, new String[] { "java" }, true));
        }

        return this.loadJavaFilesIntoResourceSet(javaFiles, monitor, sourceModelPath, extractLayoutInfo);
    }

    private ResourceSet loadJavaFilesIntoResourceSet(final Collection<File> javaFiles, final IProgressMonitor monitor,
            final String sourceModelPath, final boolean extractLayoutInfo) {
        if (sourceModelPath != null) {
            LOGGER.info("Using cache file: " + sourceModelPath);
        }

        final ResourceSet targetResourceSet = this.setUpResourceSet(sourceModelPath, extractLayoutInfo);
        final List<Resource> resources = new ArrayList<>();

        int currentFile = 0;
        for (final File javaFile : javaFiles) {
            LOGGER.info("loading file: " + (++currentFile) + "/" + javaFiles.size() + " currentFile name: "
                    + javaFile.getAbsolutePath() + " currentFile size: " + javaFile.length());
            Resource resource = null;

            try {
                resource = this.parseResource(javaFile, targetResourceSet);
            } catch (IOException e) {
                // will be handled in else below
            }

            if (resource != null) {
                resources.add(resource);
            } else {
                LOGGER.warn("Failed to load resource: " + javaFile);
            }
        }

        // trigger the resource resolving as soon as all resources are parsed.
        final ReferenceCache cache = this.getReferenceCache(targetResourceSet);
        int resourceCount = 0;
        for (final Resource resource : resources) {
            LOGGER.info("Resolving resource: " + (++resourceCount) + "/" + resources.size() + " resourceName: "
                    + resource.getURI().toString());
            cache.resolve(resource);
        }

        this.triggerCacheSave(targetResourceSet);

        this.sourceResources = resources;

        return targetResourceSet;
    }

    /**
     * Trigger the cache enabled JaMoPP resource cache to be saved.
     *
     * @param targetResourceSet
     *            The resource set to save the assigned cache in.
     */
    private void triggerCacheSave(final ResourceSet targetResourceSet) {
        final ReferenceCache cache = this.getReferenceCache(targetResourceSet);
        JaMoPPSoftwareModelExtractor.LOGGER
                .debug("References not resolved from Cache: " + cache.getNotResolvedFromCacheCounterReference());
        cache.save();
    }

    /**
     * Get the reference cache of the JaMoPP java resource factory registered in a resource set.
     *
     * @param resourceSet
     *            The resource set to look up the cache.
     * @return The cache or null if none could be found.
     */
    private ReferenceCache getReferenceCache(final ResourceSet resourceSet) {
        final Map<String, Object> factoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
        final Object factoryObject = factoryMap.get("java");
        final JavaSourceOrClassFileResourceCachingFactoryImpl factory = (JavaSourceOrClassFileResourceCachingFactoryImpl) factoryObject;
        final ReferenceCache cache = factory.getReferenceCache();
        return cache;
    }

    /**
     * Load a specific resource.
     *
     * @param file
     *            The file object to load as resource.
     * @param rs
     *            The resource set to add it to.
     * @return The loaded resource.
     * @throws IOException
     *             An exception during resource access.
     */
    private Resource parseResource(final File file, final ResourceSet rs) throws IOException {
        return this.loadResource(file.getCanonicalPath(), rs);
    }

    /**
     * Load a specific resource.
     *
     * @param filePath
     *            The path of the file to load.
     * @param rs
     *            The resource set to add it to.
     * @return The loaded resource.
     * @throws IOException
     *             An exception during resource access.
     */
    private Resource loadResource(final String filePath, final ResourceSet rs) throws IOException {
        return this.loadResource(URI.createFileURI(filePath), rs);
    }

    /**
     * Load a specific resource.
     *
     * @param uri
     *            The uri of the resource.
     * @param rs
     *            The resource set to add it to.
     * @return The loaded resource.
     * @throws IOException
     *             An exception during resource access.
     */
    private Resource loadResource(final URI uri, final ResourceSet rs) throws IOException {
        return rs.getResource(uri, true);
    }

    /**
     * Setup the JaMoPP resource set and prepare the parsing options for the java resource type.
     *
     * The jar files contained in the extracted projects are registered to the class path as well.
     *
     * @param sourceModelDirectory
     *            The path to the directory assigned to the extracted copy.
     * @param extractLayoutInfo
     *            Flag for extraction of layout information. True means that layout information is
     *            extracted.
     * @return The initialized resource set.
     */
    private ResourceSet setUpResourceSet(final String sourceModelDirectory, final boolean extractLayoutInfo) {
        final ArrayList<String> directories = Lists.newArrayList();
        if (sourceModelDirectory != null) {
            directories.add(sourceModelDirectory);
        }

        final SPLevoResourceSet rs = new SPLevoResourceSet();
        rs.setURIResourceMap(new HashMap<URI, Resource>());
        this.initResourceSet(rs, directories, extractLayoutInfo);
        return rs;
    }

    public String getId() {
        return JaMoPPSoftwareModelExtractor.EXTRACTOR_ID;
    }

    public String getLabel() {
        return JaMoPPSoftwareModelExtractor.EXTRACTOR_LABEL;
    }

    public void prepareResourceSet(final ResourceSet rs, final List<String> sourceModelPaths,
            final boolean loadLayoutInformation) {
        this.initResourceSet(rs, sourceModelPaths, loadLayoutInformation);
    }

    private void initResourceSet(final ResourceSet rs, final List<String> sourceModelPaths,
            final boolean loadLayoutInformation) {
        final Boolean disableLayoutOption = loadLayoutInformation ? Boolean.FALSE : Boolean.TRUE;

        final Map<Object, Object> options = rs.getLoadOptions();
        options.put(JavaClasspath.OPTION_USE_LOCAL_CLASSPATH, Boolean.TRUE);
        options.put(JavaClasspath.OPTION_REGISTER_STD_LIB, Boolean.TRUE);
        // options.put(IJavaOptions.DISABLE_EMF_VALIDATION, Boolean.TRUE);
        options.put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, disableLayoutOption);
        options.put(IJavaOptions.DISABLE_LOCATION_MAP, disableLayoutOption);
        options.put(ResourceHandlingOptions.USE_PLATFORM_RESOURCE,
                ResourceHandlingOptions.USE_PLATFORM_RESOURCE.getDefault());
        // options.put(ResourceHandlingOptions.USE_PLATFORM_RESOURCE, false);

        final Factory originalFactory = new JavaSourceOrClassFileResourceFactoryImpl();
        final Factory cachedJaMoPPFactory = new JavaSourceOrClassFileResourceCachingFactoryImpl(originalFactory,
                sourceModelPaths);

        JavaClasspath.get(rs);

        final Map<String, Object> factoryMap = rs.getResourceFactoryRegistry().getExtensionToFactoryMap();
        factoryMap.put("java", cachedJaMoPPFactory);
        // DesignDecision No caching for byte code resources to improve performance
        factoryMap.put("class", originalFactory);
    }

    public List<Resource> getSourceResources() {
        return this.sourceResources;
    }

}
