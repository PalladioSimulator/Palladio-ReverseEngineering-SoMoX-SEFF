package org.somox.kdmhelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.containers.CompilationUnit;
import org.somox.kdmhelper.metamodeladdition.Root;

public class KDMReader {

    private final Root root;
    // Resource

    private final static Logger logger = Logger.getLogger(KDMReader.class.getName());

    public KDMReader() {
        this.root = new Root();
    }

    public Root getRoot() {
        return this.root;
    }

    public void loadProject(final IProject project) throws IOException {
        try {
            if (!project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
                throw new IOException("Given project is no Java project.");
            }
        } catch (final CoreException e) {
            throw new IOException("Checking nature of project failed.", e);
        }
        final IJavaProject javaProject = JavaCore.create(project);
        this.loadJavaProject(javaProject);
    }

    private void loadJavaProject(final IJavaProject javaProject) throws IOException {
        final ResourceSet rs = new ResourceSetImpl();
        try {
            final IPackageFragmentRoot[] packageFragmentRoots = javaProject.getAllPackageFragmentRoots();
            for (final IPackageFragmentRoot pfr : packageFragmentRoots) {
                String path = pfr.getPath().toString();
                if (!pfr.isExternal()) {
                    // every internal path starts with "/ProjectName"
                    path = javaProject.getProject().getLocation().toString() + "/.." + path;
                }
                if (pfr.getKind() == IPackageFragmentRoot.K_SOURCE) {
                    final boolean addModelsToRoot = false;
                    this.loadSourceFolder(path, rs, addModelsToRoot);
                } else if (pfr.getKind() == IPackageFragmentRoot.K_BINARY) {
                    this.registerJarFile(path, rs);
                }
            }
        } catch (final JavaModelException e1) {
            e1.printStackTrace();
        }
        // if (!resolveAllProxies(rs)) {
        // logger.error("Resolution of some Proxies failed...");
        // }
        this.addModelsToRoot(rs);
    }

    public void loadSourceFolder(final String absolutePath, final ResourceSet rs, final boolean addModelsToRoot)
            throws IOException {
        final File directory = new File(absolutePath);
        if (!directory.isDirectory()) {
            throw new IOException("Given path is not a directory: " + absolutePath);
        }
        this.loadSourceFolder(directory, rs);
        if (addModelsToRoot) {
            this.addModelsToRoot(rs);
        }
    }

    private void loadSourceFolder(final File directory, final ResourceSet rs) {
        Collection<File> javaFiles = FileUtils.listFiles(directory, new String[] { "java" }, true);
        if (javaFiles.isEmpty()) {
            javaFiles = FileUtils.listFiles(directory, new String[] { "class" }, true);
        }
        for (final File javaFile : javaFiles) {
            Resource resource = null;
            try {
                resource = rs.getResource(URI.createFileURI(javaFile.getCanonicalPath()), true);
                // TODO fix: execution of following statement leads to ClassCastException
                // Map<Object, Object> loadOptions = setupLoadOptions(resource);
                resource.load(null);
            } catch (final IOException e) {
                e.printStackTrace();
                logger.warn("Failed to load resource: " + javaFile + "\n Reason: " + e);
            }
        }
    }

    private void registerJarFile(final String jarPath, final ResourceSet rs) throws IOException {
        final File jar = new File(jarPath);
        this.registerJarFile(jar, rs);
    }

    private void registerJarFile(final File jarPath, final ResourceSet rs) throws IOException {
        final JavaClasspath cp = JavaClasspath.get(rs);
        cp.registerClassifierJar(URI.createFileURI(jarPath.getCanonicalPath()));
    }

    private boolean resolveAllProxies(final ResourceSet rs) {
        return this.resolveAllProxies(0, rs);
    }

    private boolean resolveAllProxies(final int resourcesProcessedBefore, final ResourceSet rs) {
        boolean failure = false;
        final List<EObject> eobjects = new LinkedList<EObject>();
        for (final Iterator<Notifier> i = rs.getAllContents(); i.hasNext();) {
            final Notifier next = i.next();
            if (next instanceof EObject) {
                eobjects.add((EObject) next);
            }
        }
        final int resourcesProcessed = rs.getResources().size();
        if (resourcesProcessed == resourcesProcessedBefore) {
            return true;
        }

        for (final EObject next : eobjects) {
            final InternalEObject nextElement = (InternalEObject) next;
            for (EObject crElement : nextElement.eCrossReferences()) {
                crElement = EcoreUtil.resolve(crElement, rs);
                if (crElement.eIsProxy()) {
                    failure = true;
                    logger.warn("Can not find referenced element in classpath: "
                            + ((InternalEObject) crElement).eProxyURI());
                }
            }
        }

        // call this method again, because the resolving might have triggered loading
        // of additional resources that may also contain references that need to be resolved.
        return !failure && this.resolveAllProxies(resourcesProcessed, rs);
    }

    public void loadFiles(final Collection<String> filesLocationList) throws IOException {
        for (final String fileLocation : filesLocationList) {
            final File file = new File(fileLocation);
            final URI URIfile = URI.createFileURI(file.getAbsolutePath());

            // Resource resource = ModelUtils.loadModel(URIfile);

            final ResourceSet rs = new ResourceSetImpl();
            final Resource resource = rs.getResource(URIfile, true);
            final Map<Object, Object> loadOptions = this.setupLoadOptions(resource);
            resource.load(null);
        }
    }

    // TODO remove
    public void loadFile(final URI file) throws IOException {
    }

    private void addModelsToRoot(final ResourceSet rs) {
        final Collection<Resource> resources = rs.getResources();
        for (final Resource resource : resources) {
            this.root.addModels(this.getModelsFromResource(resource));
        }
    }

    // CompilationUnit statt Model
    // JavaRoot statt JavaAplication
    private Collection<CompilationUnit> getModelsFromResource(final Resource resource) {
        final List<CompilationUnit> modelList = new ArrayList<CompilationUnit>();
        for (final EObject obj : resource.getContents()) {
            if (obj instanceof CompilationUnit) {
                final CompilationUnit model = (CompilationUnit) obj;
                modelList.add(model);
            }
        }
        return modelList;
    }

    private Map<Object, Object> setupLoadOptions(final Resource resource) {
        final Map<Object, Object> loadOptions = ((XMLResource) resource).getDefaultLoadOptions();
        loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
        loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap<Object, Object>());
        return loadOptions;
    }

}
