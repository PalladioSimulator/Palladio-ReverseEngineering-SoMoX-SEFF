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
import java.util.Set;

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
import org.emftext.language.java.resource.java.util.JavaResourceUtil;
import org.somox.kdmhelper.metamodeladdition.Root;

public class KDMReader {

	private Root root;
	// Resource

	private final static Logger logger = Logger.getLogger(KDMReader.class.getName());

	public KDMReader() {
		root = new Root();
	}

	public Root getRoot() {
		return root;
	}

	public void loadProject(IProject project) throws IOException {
		try {
			if (!project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
				throw new IOException("Given project is no Java project.");
			}
		} catch (CoreException e) {
			throw new IOException("Checking nature of project failed.", e);
		}
		IJavaProject javaProject = JavaCore.create(project);
		loadJavaProject(javaProject);
	}
	
	private void loadJavaProject(IJavaProject javaProject) throws IOException {
		ResourceSet rs = new ResourceSetImpl();
		try {
			IPackageFragmentRoot[] packageFragmentRoots = javaProject.getAllPackageFragmentRoots();
			for (IPackageFragmentRoot pfr : packageFragmentRoots) {
				String path = pfr.getPath().toString();
				if (!pfr.isExternal()) {
					path = javaProject.getProject().getLocation().toString() + "/.." + path;
				}
				if (pfr.getKind() == IPackageFragmentRoot.K_SOURCE) {
					loadSourceFolder(path, rs);
				} else if (pfr.getKind() == IPackageFragmentRoot.K_BINARY) {
					registerJarFile(path, rs);
				}
			}
		} catch (JavaModelException e1) {
			e1.printStackTrace();
		}
		if (!resolveAllProxies(rs)) {
			logger.error("Resolution of some Proxies failed...");
		}
	}
	
	private void loadSourceFolder(String absolutePath, ResourceSet rs) throws IOException {
		File directory = new File(absolutePath);
		if (!directory.isDirectory()) {
			throw new IOException("Given path is not a directory.");
		}
		loadSourceFolder(directory, rs);
	}
		
	private void loadSourceFolder(File directory, ResourceSet rs) {
		Collection<File> javaFiles = FileUtils.listFiles(directory,	new String[] { "java" }, true);
		for (File javaFile : javaFiles) {
			Resource resource = null;
			try {
				resource = rs.getResource(URI.createFileURI(javaFile.getCanonicalPath()), true);
				// TODO fix: execution of following statement leads to ClassCastException
				// Map<Object, Object> loadOptions = setupLoadOptions(resource);
				logger.info("Parsing resource: " + javaFile);
				resource.load(null);
				addModelToRoot(resource);
			} catch (IOException e) {
				e.printStackTrace();
				logger.warn("Failed to load resource: " + javaFile + "\n Reason: " + e);
			}
		}
	}
	
	private void registerJarFile(String jarPath, ResourceSet rs) throws IOException {
		File jar = new File(jarPath);
		registerJarFile(jar, rs);
	}
	
	private void registerJarFile(File jarPath, ResourceSet rs) throws IOException {
		JavaClasspath cp = JavaClasspath.get(rs);
		logger.info("Registering JAR " + jarPath.getCanonicalPath());
		cp.registerClassifierJar(URI.createFileURI(jarPath.getCanonicalPath()));
	}
	
	private boolean resolveAllProxies(ResourceSet rs) {
		return resolveAllProxies(0, rs);
	}
	
	private boolean resolveAllProxies(int resourcesProcessedBefore, ResourceSet rs) {
		boolean failure = false;
		List<EObject> eobjects = new LinkedList<EObject>();
		for (Iterator<Notifier> i = rs.getAllContents(); i.hasNext();) {
			Notifier next = i.next();
			if (next instanceof EObject) {
				eobjects.add((EObject) next);
			}
		}
		int resourcesProcessed = rs.getResources().size();
		if (resourcesProcessed == resourcesProcessedBefore) {
			return true;
		}

		logger.info("Resolving cross-references of " + eobjects.size() + " EObjects.");
		int resolved = 0;
		int notResolved = 0;
		int eobjectCnt = 0;
		for (EObject next : eobjects) {
			eobjectCnt++;
			if (eobjectCnt % 1000 == 0) {
				logger.info(eobjectCnt + "/" + eobjects.size()
						+ " done: Resolved " + resolved + " crossrefs, "
						+ notResolved + " crossrefs could not be resolved.");
			}

			InternalEObject nextElement = (InternalEObject) next;
			for (EObject crElement : nextElement.eCrossReferences()) {
				crElement = EcoreUtil.resolve(crElement, rs);
				if (crElement.eIsProxy()) {
					failure = true;
					notResolved++;
					logger.info("Can not find referenced element in classpath: "
									+ ((InternalEObject) crElement).eProxyURI());
				} else {
					resolved++;
				}
			}
		}

		logger.info(eobjectCnt + "/" + eobjects.size()
				+ " done: Resolved " + resolved + " crossrefs, " + notResolved
				+ " crossrefs could not be resolved.");
		
		//call this method again, because the resolving might have triggered loading
		//of additional resources that may also contain references that need to be resolved.
		return !failure && resolveAllProxies(resourcesProcessed, rs);
	}

	public void loadFiles(Collection<String> filesLocationList)
			throws IOException {
		for (String fileLocation : filesLocationList) {
			File file = new File(fileLocation);
			URI URIfile = URI.createFileURI(file.getAbsolutePath());

			// Resource resource = ModelUtils.loadModel(URIfile);

			ResourceSet rs = new ResourceSetImpl();
			Resource resource = rs.getResource(URIfile, true);
			Map<Object, Object> loadOptions = setupLoadOptions(resource);
			resource.load(null);
			addModelToRoot(resource);

		}
	}

	// TODO test
	public void loadFile(URI file) throws IOException {

		ResourceSet rs = new ResourceSetImpl();
		Resource resource = rs.getResource(file, true);

		// resource = ModelUtils.loadModel(file);
		addModelToRoot(resource);

	}

	private void addModelToRoot(Resource resource) {
		root.addModels(getModelsFromResource(resource));
	}

	// CompilationUnit statt Model
	// JavaRoot statt JavaAplication
	private Collection<CompilationUnit> getModelsFromResource(Resource resource) {
		List<CompilationUnit> modelList = new ArrayList<CompilationUnit>();
		for (EObject obj : resource.getContents()) {
			if (obj instanceof CompilationUnit) {
				CompilationUnit model = (CompilationUnit) obj;
				modelList.add(model);
			}
		}
		return modelList;
	}

	private Map<Object, Object> setupLoadOptions(Resource resource) {
		Map<Object, Object> loadOptions = ((XMLResource) resource).getDefaultLoadOptions();
		loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
		loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap<Object, Object>());
		return loadOptions;
	}

}
