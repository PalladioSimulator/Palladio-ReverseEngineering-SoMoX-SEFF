package org.splevo.jamopp.extraction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.splevo.jamopp.extraction.cache.ReferenceCache;
import org.splevo.jamopp.extraction.resource.JavaSourceOrClassFileResourceCachingFactoryImpl;

/**
 * Software Model Extractor for the Java technology based on the Java Model Parser and Printer
 * (JaMoPP). JaMoPPSoftwareModelExtractor are copied from splevo and adapted in order to work with
 * SoMoX. And adapted to work without further dependencies to the splevo code.
 */
public class JaMoPPSoftwareModelExtractor {

    private static Logger logger = Logger.getLogger(JaMoPPSoftwareModelExtractor.class);

    public static final String EXTRACTOR_ID = "JaMoPPSoftwareModelExtractor";
    private static final String EXTRACTOR_LABEL = "JaMoPP Software Model Extractor";

    private List<Resource> sourceResources;

    /**
     * Extract the source model of a list of java projects. One project is the main project while a
     * list of additional projects to analyze can be specified. The reason for one main project is,
     * that this one is used for example for the naming of the root inventory produced etc.
     *
     * @param projectPaths
     *            Source Paths of the projects to be extracted.
     * @param monitor
     *            The monitor to report the progress to.
     * @return The set of resources containing the extracted model.
     * @throws SoftwareModelExtractionException
     *             Identifies the extraction was not successful.
     */
    public ResourceSet extractSoftwareModel(final List<String> projectPaths, final IProgressMonitor monitor) {
        return this.extractSoftwareModel(projectPaths, monitor, null);
    }

    /**
     * Extract all java files and referenced resources to a file named according to
     * {@link JaMoPPSoftwareModelExtractor#XMI_FILE_SEGMENT} within the provided targetURI.
     *
     * <p>
     * If the sourceModelPath is null, the extractor will not use a cache file for improved
     * reference resolving.
     * </p>
     *
     */
    public ResourceSet extractSoftwareModel(final List<String> projectPaths, final IProgressMonitor monitor,
            final String sourceModelPath) {
        return this.extractSoftwareModel(projectPaths, monitor, sourceModelPath, false);
    }

    /**
     * Extract all java files and referenced resources to a file named according to
     * {@link JaMoPPSoftwareModelExtractor#XMI_FILE_SEGMENT} within the provided targetURI.
     *
     * <p>
     * If the sourceModelPath is null, the extractor will not use a cache file for improved
     * reference resolving.
     * </p>
     *
     *
     * @param projectPaths
     *            Source Paths of the projects to be extracted.
     * @param monitor
     *            The monitor to report the progress to.
     * @param sourceModelPath
     *            The absolute path to the directory to store information for extracted source model
     *            in.
     * @param extractLayoutInfo
     *            Option to extract layout information.
     * @return The set of resources containing the extracted model.
     * @throws SoftwareModelExtractionException
     *             Identifies the extraction was not successful.
     *
     */
    public ResourceSet extractSoftwareModel(final List<String> projectPaths, final IProgressMonitor monitor,
            final String sourceModelPath, final boolean extractLayoutInfo) {

        if (sourceModelPath != null) {
            logger.info("Use cache file: " + sourceModelPath);
        }

        // TODO: Refactor Code for more intuitive
        // loading-resolving-caching-workflow
        final List<String> jarFiles = this.getAllJarFiles(projectPaths);
        final ResourceSet targetResourceSet = this.setUpResourceSet(sourceModelPath, jarFiles, extractLayoutInfo);

        final List<Resource> resources = new ArrayList<Resource>();
        this.sourceResources = new ArrayList<Resource>();

        for (final String projectPath : projectPaths) {
            final List<Resource> projectResources = this.loadProjectJavaFiles(targetResourceSet, projectPath);
            resources.addAll(projectResources);
            this.sourceResources.addAll(projectResources);
        }

        logger.info(String.format("%d Java and %d Jar Files added to resource set", resources.size(), jarFiles.size()));

        // trigger the resource resolving as soon as all resources are parsed.
        final ReferenceCache cache = this.getReferenceCache(targetResourceSet);
        int resourceCount = 0;
        for (final Resource resource : resources) {
            logger.info("Resolving resource: " + (++resourceCount) + "/" + resources.size() + " resourceName: "
                    + resource.getURI().toString());
            cache.resolve(resource);
        }

        this.triggerCacheSave(targetResourceSet);

        return targetResourceSet;
    }

    /**
     * Load all java files found in a project into a ResourceSet and return the list of created
     * resources.
     *
     * @param targetResourceSet
     *            The preconfigured resource set to load to.
     * @param projectPath
     *            The base path of the project containing the java files.
     * @return The list of newly created resources.
     * @throws SoftwareModelExtractionException
     *             thrown if a java file could not be parsed successfully.
     */
    private List<Resource> loadProjectJavaFiles(final ResourceSet targetResourceSet, final String projectPath) {
        List<Resource> projectResources;
        try {
            final File srcFolder = new File(projectPath);
            srcFolder.isDirectory();
            projectResources = this.loadAllJavaFilesInResourceSet(srcFolder, targetResourceSet);
        } catch (final Exception e) {
            throw new RuntimeException("Failed to parse project resources. Project: " + projectPath, e);
        }
        return projectResources;
    }

    /**
     * Get the list of paths of the involved jar files of all projects.
     *
     * @param projectPaths
     *            The project base directories.
     * @return The list of jar files found and accessible.
     */
    private List<String> getAllJarFiles(final List<String> projectPaths) {

        final List<String> jarPaths = new ArrayList<String>();

        for (final String projectPath : projectPaths) {
            final File projectDirectory = new File(projectPath);
            final Collection<File> jarFiles = FileUtils.listFiles(projectDirectory, new String[] { "jar" }, true);
            for (final File jarPath : jarFiles) {
                if (jarPath.exists()) {
                    try {
                        jarPaths.add(jarPath.getCanonicalPath());
                    } catch (final IOException e) {
                        logger.warn("Unable to access jar file: " + jarPath);
                    }
                }
            }
        }

        return jarPaths;
    }

    /**
     * Trigger the cache enabled JaMoPP resource cache to be saved.
     *
     * @param targetResourceSet
     *            The resource set to save the assigned cache in.
     */
    private void triggerCacheSave(final ResourceSet targetResourceSet) {
        final ReferenceCache cache = this.getReferenceCache(targetResourceSet);
        logger.debug("References not resolved from Cache: " + cache.getNotResolvedFromCacheCounterReference());
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
     * Load a all files as resource in a specific folder and it's sub folders.
     *
     * @param rootFolder
     *            The root folder to recursively load all resources from.
     * @param rs
     *            The resource set to add it to.
     * @return The number of loaded java files.
     * @throws IOException
     *             An exception during resource access.
     */
    private List<Resource> loadAllJavaFilesInResourceSet(final File rootFolder, final ResourceSet rs)
            throws IOException {

        final List<Resource> resources = new ArrayList<Resource>();

        final Collection<File> javaFiles = FileUtils.listFiles(rootFolder, new String[] { "java" }, true);
        int currentFile = 0;
        for (final File javaFile : javaFiles) {
            logger.info("loading file: " + (++currentFile) + "/" + javaFiles.size() + " currentFile name: "
                    + javaFile.getAbsolutePath() + " currentFile size: " + javaFile.length());
            final Resource resource = this.parseResource(javaFile, rs);
            if (resource != null) {
                resources.add(resource);
            } else {
                logger.warn("Failed to load resource: " + javaFile);
            }
        }

        return resources;
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
     * @param jarPaths
     *            Absolute paths to the jar files of the source project.
     *
     * @return The initialized resource set.
     */
    private ResourceSet setUpResourceSet(final String sourceModelDirectory, final List<String> jarPaths,
            final boolean extractLayoutInfo) {

        final ResourceSet rs = new ResourceSetImpl();

        // further resource set enhancement for the extraction specific needs
        final Map<Object, Object> options = rs.getLoadOptions();
        // TODO: next three lines commented out in order to retrieve layout information (even if
        // extractLayoutInformation is set to true it was not working before)
        // final Boolean disableLayoutOption = extractLayoutInfo ? Boolean.FALSE : Boolean.TRUE;
        // options.put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, false);
        // options.put(IJavaOptions.DISABLE_LOCATION_MAP, false);
        // options.put(IJavaOptions.DISABLE_EMF_VALIDATION, Boolean.TRUE);
        options.put(JavaClasspath.OPTION_USE_LOCAL_CLASSPATH, Boolean.TRUE);
        options.put(JavaClasspath.OPTION_REGISTER_STD_LIB, Boolean.TRUE);

        EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);

        final ArrayList<String> directories = new ArrayList<String>();
        if (sourceModelDirectory != null) {
            directories.add(sourceModelDirectory);
        }

        final Map<String, Object> factoryMap = rs.getResourceFactoryRegistry().getExtensionToFactoryMap();
        final JavaClasspath javaClasspath = JavaClasspath.get(rs);
        final JavaSourceOrClassFileResourceCachingFactoryImpl factory = new JavaSourceOrClassFileResourceCachingFactoryImpl(
                directories, javaClasspath, jarPaths);
        factoryMap.put("java", factory);
        // DesignDecision No caching for byte code resources to improve performance
        factoryMap.put("class", new JavaSourceOrClassFileResourceFactoryImpl());

        return rs;
    }

    public String getId() {
        return EXTRACTOR_ID;
    }

    public String getLabel() {
        return EXTRACTOR_LABEL;
    }

    public void prepareResourceSet(final ResourceSet rs, final List<String> sourceModelPaths) {

        final Map<Object, Object> options = rs.getLoadOptions();
        options.put(JavaClasspath.OPTION_USE_LOCAL_CLASSPATH, Boolean.TRUE);
        options.put(JavaClasspath.OPTION_REGISTER_STD_LIB, Boolean.TRUE);
        // options.put(IJavaOptions.DISABLE_EMF_VALIDATION, Boolean.TRUE);
        EPackage.Registry.INSTANCE.put("http://www.emftext.org/java", JavaPackage.eINSTANCE);

        final Map<String, Object> factoryMap = rs.getResourceFactoryRegistry().getExtensionToFactoryMap();
        final JavaClasspath javaClasspath = JavaClasspath.get(rs);
        final JavaSourceOrClassFileResourceCachingFactoryImpl factory = new JavaSourceOrClassFileResourceCachingFactoryImpl(
                sourceModelPaths, javaClasspath);
        factoryMap.put("java", factory);
        // DesignDecision No caching for byte code resources to improve performance
        factoryMap.put("class", new JavaSourceOrClassFileResourceFactoryImpl());
    }

    public List<Resource> getSourceResources() {
        return this.sourceResources;
    }
}
