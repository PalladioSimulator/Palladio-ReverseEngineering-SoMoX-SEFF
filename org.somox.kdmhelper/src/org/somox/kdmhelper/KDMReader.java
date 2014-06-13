package org.somox.kdmhelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
//import org.emftext.ecore.xmi.XMLResource;
import org.emftext.language.*;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.types.Type;
//import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
//import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
//import org.eclipse.gmt.modisco.infra.common.core.internal.utils.ModelUtils;
//import Model;
//import org.eclipse.modisco.java.composition.javaapplication.JavaApplication;

import org.somox.kdmhelper.metamodeladdition.Root;


//@SuppressWarnings("restriction")
public class KDMReader {

	private Root root;
	//Resource 


	private final static Logger logger = Logger.getLogger(KDMReader.class
			.getName());

	public KDMReader() {
		root = new Root();
		
	}

	public Root getRoot() {
		return root;
	}
	
//	public void loadProject(IProject project) throws IOException {
//		try {
//			if (!project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
//				throw new ModelAnalyzerException("Given project is no Java project.");
//			}
//		} catch (CoreException e) {
//			throw new ModelAnalyzerException("", e);
//		}
//		IJavaProject javaProject = JavaCore.create(project);
//		List<IPackageFragmentRoot> sourceFolders = new ArrayList<IPackageFragmentRoot>();
//		try {
//			IPackageFragmentRoot[] packageFragmentRoots = javaProject.getAllPackageFragmentRoots();
//			for (IPackageFragmentRoot pfr : packageFragmentRoots) {
//				if (pfr.getKind() == IPackageFragmentRoot.K_SOURCE) {
//					sourceFolders.add(pfr);
//				}
//			}
//		} catch (JavaModelException e1) {
//			e1.printStackTrace();
//		}
//
//		ResourceSet rs = new ResourceSetImpl();
//		for (IPackageFragmentRoot src : sourceFolders) {
//			
//			List<Resource> resources = new ArrayList<Resource>();
//	        Collection<File> javaFiles = FileUtils.listFiles(src.getPath().toFile(), new String[] { "java" }, true);
//	        for (File javaFile : javaFiles) {
//	            Resource resource = null;
//				try {
//					resource = rs.getResource(URI.createFileURI(javaFile.getCanonicalPath()), true);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//	            if (resource != null) {
//	                resources.add(resource);
//	            } else {
//	                logger.warn("Failed to load resource: " + javaFile);
//	            }
//	        }
//		}
//		
//		// addModelToRoot(resource);
//
//	}

	public void loadFiles(Collection<String> filesLocationList)
			throws IOException {
		for (String fileLocation : filesLocationList) {
			File file = new File(fileLocation);
			URI URIfile = URI.createFileURI(file.getAbsolutePath());

			//Resource resource = ModelUtils.loadModel(URIfile);
			
			ResourceSet rs= new ResourceSetImpl();
			Resource resource = rs.getResource(URIfile,true);
			// TODO fix
			Map<Object, Object> loadOptions = setupLoadOptions(resource);
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
	
//CompilationUnit statt Model 
	// JavaRoot statt JavaAplication
	private Collection<CompilationUnit> getModelsFromResource(Resource resource) { 
		List<CompilationUnit> modelList = new ArrayList<CompilationUnit>();
		for (EObject obj : resource.getContents()) {
			if (obj instanceof JavaRoot) {
				JavaRoot javaApp = (JavaRoot) obj;
				for (Object ref : javaApp.eCrossReferences()) {
					if (ref instanceof CompilationUnit) {
						CompilationUnit model = (CompilationUnit) ref;
						modelList.add(model);
					}
				}
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
