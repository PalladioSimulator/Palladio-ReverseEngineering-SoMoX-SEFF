package org.somox.kdmhelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
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
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.resource.java.util.JavaResourceUtil;
import org.somox.kdmhelper.metamodeladdition.Root;


public class KDMReader {

	private Root root;
	//Resource 


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
		List<IPackageFragmentRoot> sourceFolders = new ArrayList<IPackageFragmentRoot>();
		try {
			IPackageFragmentRoot[] packageFragmentRoots = javaProject.getAllPackageFragmentRoots();
			for (IPackageFragmentRoot pfr : packageFragmentRoots) {
				if (pfr.getKind() == IPackageFragmentRoot.K_SOURCE) {
					sourceFolders.add(pfr);
				}
			}
		} catch (JavaModelException e1) {
			e1.printStackTrace();
		}

		ResourceSet rs = new ResourceSetImpl();
		for (IPackageFragmentRoot src : sourceFolders) {
			String path = project.getLocation().toString() + "/.." + src.getPath().toString();
			File directory = new File(path);
	        Collection<File> javaFiles = FileUtils.listFiles(directory, new String[] { "java" }, true);
	        for (File javaFile : javaFiles) {
	            Resource resource = null;
				try {
					resource = rs.getResource(URI.createFileURI(javaFile.getCanonicalPath()), true);
					// TODO fix: execution of following statement leads to ClassCastException
					// Map<Object, Object> loadOptions = setupLoadOptions(resource);
					resource.load(null);
					boolean resolvedAll = JavaResourceUtil.resolveAll(resource);
					if(!resolvedAll){
						logger.info("Could not resolve all proxies in resource " + resource.getURI());
						Set<EObject> proxies = JavaResourceUtil.findUnresolvedProxies(resource);
						List<String> identifiers = new ArrayList<String>();
						for (EObject proxy : proxies) {
							identifiers.add(JavaResourceUtil.getProxyIdentifier(proxy));
						}
						logger.info("Unresolved proxies: " + identifiers);
					}
					addModelToRoot(resource);
				} catch (IOException e) {
					e.printStackTrace();
	                logger.warn("Failed to load resource: " + javaFile + "\n Reason: " + e);
				}
	        }
		}
	}

	public void loadFiles(Collection<String> filesLocationList)
			throws IOException {
		for (String fileLocation : filesLocationList) {
			File file = new File(fileLocation);
			URI URIfile = URI.createFileURI(file.getAbsolutePath());

			//Resource resource = ModelUtils.loadModel(URIfile);
			
			ResourceSet rs= new ResourceSetImpl();
			Resource resource = rs.getResource(URIfile,true);
			Map<Object, Object> loadOptions = setupLoadOptions(resource);
			resource.load(null);
			addModelToRoot(resource);

		}
	}

	// TODO test
	public void loadFile(URI file) throws IOException {
		
		ResourceSet rs= new ResourceSetImpl();
		Resource resource = rs.getResource(file,true);
		
//		resource = ModelUtils.loadModel(file);
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
		Map<Object, Object> loadOptions = ((XMLResource) resource)
				.getDefaultLoadOptions();
		loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		loadOptions
				.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		loadOptions
				.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL,
				new XMLParserPoolImpl());
		loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP,
				new HashMap<Object, Object>());
		return loadOptions;
	}

}
